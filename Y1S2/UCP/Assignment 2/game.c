/*---------------------------------------------------------------------------
FILE: game.c
AUTHOR: Justin Liang(19821986)
UNIT: COMP1000
LAST MOD: 10/09/2021
PURPOSE: encapsulates the game into startGame function.
REQUIRES: map.c, movement.c, free.c, terminal.c, LinkedList.c 
----------------------------------------------------------------------------*/
#include "game.h"



/*----------------------------------------------------------------------------
SUBMODULE: startGame
IMPORT: fp(FILE*)
EXPORT: nil
ASSERTION: create the map, enter the game and then once finished, free 
memory allocations.
----------------------------------------------------------------------------*/

void startGame( FILE *fp )
{    
    /*Variables*/
    Map *map = NULL;
    Coordinate *coords = NULL;
    LinkedList* prevMoves = NULL;

    /*Allocating memory for structs*/
    map = (Map*) malloc(sizeof(Map));
    coords = (Coordinate*) malloc(sizeof(Coordinate));
    prevMoves = createLinkedList();

    /*CREATING MAP*/
    mallocMap(map, fp); 
    makeBorders(map);
    useFileData(map, coords, fp);
    fclose(fp);
    
    /*enter Game*/
    enterGame( map, coords, prevMoves ); 
    
    /*Freeing all memory allocations*/
    freeMallocs( map, coords, prevMoves );
}




/*----------------------------------------------------------------------------
SUBMODULE: enterGame
IMPORT: map(Map*), coords(Coordinate*), prevMoves(LinkedList*)
EXPORT: nil
ASSERTION: allow player to control the game and end the game once player
reaches goal or hits the enemy.
----------------------------------------------------------------------------*/

void enterGame( Map *map, Coordinate *coords, LinkedList *prevMoves )
{     
    /*Boolean variable to know when game is finished*/
    int finished;

    disableBuffer();
    
    /*Allow player to control game*/
    printMap( map );
    finished = FALSE;
    while( !finished )
    {   
        char input;      
        /*Getting user input for movement*/
        scanf( " %c", &input );
        if( input == 'w' || input  == 'a'|| input == 's'|| input == 'd' )
        {
            /*store coords in linkedlist first*/
            storeCoords( prevMoves, coords );

            /* move player and snake */
            movePlayer( map->table, coords, input );
            moveSnake( map, coords );
        }
        else if( input == 'u' ) /*undo feature*/
        {
            undo( map, coords, prevMoves );
        }

        /*Printing the updated map*/
        printMap( map );

        /*Checking if player reached goal or hit enemy*/
        finished = checkFinished( coords );
    }
    /*Game ended so turn buffer back on*/
    enableBuffer();
}




/*----------------------------------------------------------------------------
SUBMODULE: storeCoords
IMPORT: prevMoves(LinkedList*), coords(Coordinate*)
EXPORT: nil
ASSERTION: make a copy of the current coords and store it in prevMoves
----------------------------------------------------------------------------*/

void storeCoords( LinkedList *prevMoves, Coordinate *coords )
{   
    /* copying coords and inserting into LinkedList */
    Coordinate *prevCoords = createPrevCoords( coords );
    insertLast( prevMoves, prevCoords ); 
}




/*----------------------------------------------------------------------------
SUBMODULE: createPrevCoords
IMPORT: coords(Coordinate*)
EXPORT: nil
ASSERTION: make a copy of the current coords 
----------------------------------------------------------------------------*/

Coordinate* createPrevCoords( Coordinate *coords )
{   
    /*Copying over current coords to prevCoords*/
    Coordinate *prevCoords = (Coordinate*) malloc(sizeof(Coordinate));
    *prevCoords = *coords;

    return prevCoords;
}




/*----------------------------------------------------------------------------
SUBMODULE: checkFinished
IMPORT: coords(Coordinate*)
EXPORT: nil
ASSERTION: returns true if players hits the goal or the enemy
COMMMENTS: contains code retrieved from assignment01.
----------------------------------------------------------------------------*/

int checkFinished( Coordinate *coords )
{   
    int isFinished = FALSE;
    
    /*code reused from assignment01*/
    /*checking if player hit goal or enemy*/
    if( coords->goalX == coords->playerX && 
        coords->goalY == coords->playerY )
    {
        isFinished = TRUE;
        printf("YOU WIN!\n");
    }
    /*end code used from assignment01*/
    else if( coords->snakeX == coords->playerX && 
             coords->snakeY == coords->playerY )
    {
        isFinished = TRUE;
        printf("YOU LOSE!\n");
    }

    return isFinished;
}


   
