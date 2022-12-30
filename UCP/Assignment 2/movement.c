/*---------------------------------------------------------------------------
FILE: movement.c
AUTHOR: Justin Liang(19821986)
UNIT: COMP1000
LAST MOD: 09/10/2021
PURPOSE: store functions for any movement in the game.
REQUIRES: map.h, LinkedList.h
----------------------------------------------------------------------------*/
#include "movement.h"

/*Static Function declarations*/
static double getDist( Coordinate *coords, char direction );
static void bubbleSort( double distFromPlayer[], char directionMoved[], 
                                                 int length );



/*----------------------------------------------------------------------------
SUBMODULE: movePlayer
IMPORT: mapTable(char**), coords(Coordinate*), direction(char)
EXPORT: nil
ASSERTION: given the direction, move player and update it on the mapTable.
----------------------------------------------------------------------------*/

void movePlayer( char **mapTable, Coordinate *coords, char direction )
{   
    int newYCoord = coords->playerY;
    int newXCoord = coords->playerX;
    char newSymbol;
    char newLocation;

    switch(direction)
    {
        case 'w':
            newSymbol = '^';
            newYCoord = newYCoord - 1 ;
            break;

        case 'a': 
            newSymbol = '<';
            newXCoord = newXCoord - 1;
            break;

        case 's':
            newSymbol = 'v';
            newYCoord = newYCoord + 1;
            break;

        case 'd': 
            newSymbol = '>';
            newXCoord = newXCoord + 1;
            break;
    }

    /*Update map and player symbol to the new symbol first*/
    mapTable[coords->playerY][coords->playerX] = newSymbol;
    coords->playerSymbol = newSymbol;
    
    /*Checking if next location is a free spot*/
    newLocation = mapTable[newYCoord][newXCoord];
    if( newLocation == ' ' || newLocation == 'x' )
    {   
        /*Changing previous player location to ' '*/
        mapTable[coords->playerY][coords->playerX] = ' ';
        
        /*Changing new location to player symbol*/
        mapTable[newYCoord][newXCoord] = newSymbol;;
        
        /*Update Player coord*/
        coords->playerX = newXCoord;
        coords->playerY = newYCoord;
    }
}




/*----------------------------------------------------------------------------
SUBMODULE: undo
IMPORT: map(Map*), coords(Coordinate*), prevMoves(LinkedList*)
EXPORT: nil
ASSERTION: retrieve prevCoords and update map using prevCoords. Assign 
currCoords as prevCoords.
----------------------------------------------------------------------------*/

void undo( Map *map, Coordinate *coords, LinkedList *prevMoves )
{   
    /* remove from prevMoves */
    Coordinate *prevCoords = (Coordinate*) removeLast( prevMoves );

    if(prevCoords != NULL)
    { 
        /*Updating the map*/
        /* Update enemy position */
        map->table[coords->snakeY][coords->snakeX] = ' ';
        map->table[prevCoords->snakeY][prevCoords->snakeX] = '~';
       
        /* Update Player position */
        map->table[coords->playerY][coords->playerX] = ' ';
        map->table[prevCoords->playerY][prevCoords->playerX] = 
                                                      prevCoords->playerSymbol;
 
        /*Setting current cords as prevCoords*/
        *coords = *prevCoords;
        free(prevCoords);
    }
}




/*----------------------------------------------------------------------------
SUBMODULE: moveSnake
IMPORT: map(Map*), coords(Coordinate*)
EXPORT: nil
ASSERTION: finding the shortest distance between player and snake if the snake
moved all possible directions. Try to move snake in that direction, if fails 
try another direction.
----------------------------------------------------------------------------*/

void moveSnake( Map *map, Coordinate *coords )
{   
    double distFromPlayer[4];
    char directionMoved[4];
    int moved;
    int index;
    
    /* Getting the distance between snake and player if snake moved 
    all directions */
    distFromPlayer[0] = getDist( coords, 'w' );
    distFromPlayer[1] = getDist( coords, 'a' );
    distFromPlayer[2] = getDist( coords, 's' );
    distFromPlayer[3] = getDist( coords, 'd' );

    /*Aligning all the directions moved with its distance */
    directionMoved[0] = 'w';
    directionMoved[1] = 'a';
    directionMoved[2] = 's';
    directionMoved[3] = 'd';
    
    /* Sorting so the directionMoved with the shortest dist is in front */
    bubbleSort(distFromPlayer, directionMoved, 4);
    
    /*Making sure snake doesn't get stuck*/
    index = 0;
    moved = FALSE;
    while( !moved && index < 4 )
    {   
        moved = tryMoveSnake( map->table, coords, directionMoved[index] );
        if( moved == FALSE )
        {
            /*If snake didn't move, access other directions */
            index++;
        }
        else
        {
            moved = TRUE;
        }
    }
}




/*----------------------------------------------------------------------------
SUBMODULE: getDist
IMPORT: coords(Coordinate*), direction(char)
EXPORT: distance(double)
ASSERTION: getting the shortest distance between player and snake if it moved
a given direction.
----------------------------------------------------------------------------*/

static double getDist( Coordinate *coords, char direction )
{   
    int enemyXCord;
    int enemyYCord;
    double xDist;
    double yDist;
    double distance;
        
    /* altering enemy coordinates based on its direction */
    enemyXCord = coords->snakeX;
    enemyYCord = coords->snakeY;
    switch(direction)
    {   
        case 'w':
            enemyYCord = enemyYCord - 1;
            break;
        case 's':
            enemyYCord = enemyYCord + 1;
            break;
        case 'a':
            enemyXCord = enemyXCord - 1;
            break;
        case 'd':
            enemyXCord = enemyXCord + 1;
            break;
    }

    /* Getting the shortest distance from enemy and player */
    xDist = (double) (coords->playerX - enemyXCord);
    yDist = (double) (coords->playerY - enemyYCord);
    distance = sqrt((yDist)*(yDist) + (xDist)*(xDist));
    
    return distance;
}   




/*----------------------------------------------------------------------------
SUBMODULE: bubbleSort
IMPORT: distFromPlayer(double[]), directionMoved(char[]), length(int)
EXPORT: nil
ASSERTION: sorting distFromPlayer with shortest infront, aliging the 
directionMoved along with.
----------------------------------------------------------------------------*/

static void bubbleSort( double distFromPlayer[], char directionMoved[], 
                                                int length )
{
    int i;
    int j;
    for(i = 0; i < length; i++)
    {
        for(j = 0; j <(length - i - 1); j++)
        {
            if( distFromPlayer[j] > distFromPlayer[j+1] )
            {
                double tempDist;
                char tempDirection;

                /*swap the moves*/
                tempDist = distFromPlayer[j];
                distFromPlayer[j] = distFromPlayer[j+1];
                distFromPlayer[j+1] = tempDist;

                tempDirection = directionMoved[j];
                directionMoved[j] = directionMoved[j+1];
                directionMoved[j+1] = tempDirection;
            }
        }
    }
}




/*----------------------------------------------------------------------------
SUBMODULE: tryMoveSnake
IMPORT: mapTable(char**), coords(Coordinate*), direction(char)
EXPORT: moved(int)
ASSERTION: try to move snake in a given direction, if failed return false.
----------------------------------------------------------------------------*/

int tryMoveSnake(char **mapTable, Coordinate *coords, char direction)
{
    int newYCoord = coords->snakeY;
    int newXCoord = coords->snakeX;  
    int moved; 
    char newLocation;
    
    /* changing the coords based on direction imported */
    switch (direction)
    {
        case 'w':
            newYCoord = newYCoord - 1;
            break;

        case 'a':
            newXCoord = newXCoord - 1;
            break;

        case 's':
            newYCoord = newYCoord + 1;
            break;

        case 'd':
            newXCoord = newXCoord + 1;
            break; 
    }

    /*Checking if newLocation is a free spot or a player*/
    newLocation = mapTable[newYCoord][newXCoord];
    if( newLocation == ' ' || newLocation == coords->playerSymbol )
    {
        /*Changing previous enemy location to ' '*/
        mapTable[coords->snakeY][coords->snakeX] = ' ';

        /*Changing new location to enemy symbol*/
        mapTable[newYCoord][newXCoord] = '~';
        
        /* Updating new coords for enemy */
        coords->snakeY = newYCoord;
        coords->snakeX = newXCoord;

        moved = TRUE;
    }
    else
    {
        moved = FALSE;
    }
    
    return moved;
}




