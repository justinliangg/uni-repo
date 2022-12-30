/*---------------------------------------------------------------------------
FILE: game.c
AUTHOR: Justin Liang(19821986)
UNIT: COMP1000
LAST MOD: 10/09/2021
PURPOSE: encapsulates the game into startGame function.
REQUIRES: generatemap.c, movement.c, display.c, map.c, terminal.c 
----------------------------------------------------------------------------*/
#include "game.h"



/*----------------------------------------------------------------------------
SUBMODULE: startGame
IMPORT: darkRange
EXPORT: nil
ASSERTION: create the map, enter the game and then once finished, free 
memory allocations.
----------------------------------------------------------------------------*/

void startGame( int darkRange )
{    
    /*Variables*/
    int **metadataTable = NULL;
    char **map = NULL;
    int metadataAmount;
    int mapRow;
    int mapCol; 
    
    /*CREATING MAP*/
    getMetadata( &metadataTable, &metadataAmount, &mapRow, &mapCol );
    map = mallocMap( mapRow, mapCol );
    makeBorders( map, mapRow, mapCol );
    useMetadataTable( map, metadataAmount, metadataTable );

    /*enter Game*/
    enterGame( map, metadataTable, mapRow, mapCol, darkRange ); 
    printf( "YOU WIN!\n" );
    
    /*Freeing metadataTable and map*/
    freeMetadataTable( metadataTable, metadataAmount );
    freeMap( map, mapRow );
}




/*----------------------------------------------------------------------------
SUBMODULE: enterGame
IMPORT: map(char**), metadataTable(char**), mapRow(Int), mapCol(Int),
        darkRange(Int)
EXPORT: nil
ASSERTION: get coordinates of starting positions. Disable the buffer and allow
player to control game. Enable buffer once player wins.
----------------------------------------------------------------------------*/

void enterGame( char **map, int **metadataTable, int mapRow, int mapCol,
                int darkRange )
{   
    /*Coordinates of player's current location*/
    int xCord;
    int yCord;
    /*Coordinates of end goal's location*/
    int goalXCord;
    int goalYCord;
    /*Boolean variable to know when game is finished*/
    int finished;

    /*Getting starting player and goal coordinates*/
    getPlayerCord( &xCord, &yCord, metadataTable );
    getGoalCord( &goalXCord, &goalYCord, metadataTable );
    
    disableBuffer();
    
    /*Allow player to control game*/
    printMap( xCord, yCord, darkRange, mapRow, mapCol, map );
    finished = FALSE;
    while( !finished )
    {   
        /*Getting user input for movement*/
        char buttonPressed;
        scanf( " %c", &buttonPressed );
        
        /*Apply changes to the map based on input*/
        switch ( buttonPressed )
        {
            case 'w':
                moveUp( map, &xCord, &yCord );
                break;
            case 's':
                moveDown( map, &xCord, &yCord );
                break;
            case 'a':
                moveLeft( map, &xCord, &yCord );
                break;
            case 'd':
                moveRight( map, &xCord, &yCord );
                break;
        }

        /*Printing the updated map*/
        printMap( xCord, yCord, darkRange, mapRow, mapCol, map );

        /*Checking if player reached goal*/
        finished = checkFinished( xCord, yCord, goalXCord, goalYCord ); 
    }

    /*Game ended so turn buffer back on*/
    enableBuffer();
}




/*----------------------------------------------------------------------------
SUBMODULE: getGoalCord
IMPORT: goalXCord(Int), goalYCord(Int), metedataTable(Int**)
EXPORT: nil
ASSERTION: go through metadataTable to find coordinates of goal.
----------------------------------------------------------------------------*/

void getGoalCord( int *goalXCord, int *goalYCord, int **metadataTable )
{
    int ii;

    /*Looping through the first two rows of metadataTable to find which row 
    goal is located*/ 
    for( ii = 0; ii< 2; ii++ )
    {
        if( metadataTable[ii][2] == 1 ) 
        {
            /*Assigning the coordinates of the goal*/
            *goalYCord = metadataTable[ii][0];
            *goalXCord = metadataTable[ii][1];
        }
    }
}




/*----------------------------------------------------------------------------
SUBMODULE: getPlayerCord
IMPORT: xCord(Int), yCord(Int), metedataTable(Int**)
EXPORT: nil
ASSERTION: go through metadataTable to find coordinates of player.
----------------------------------------------------------------------------*/

void getPlayerCord( int *xCord, int *yCord, int **metadataTable )
{   
    int ii;
    
    /*Looping through the first two rows of metadataTable to find which row 
    player is located*/
    for( ii = 0; ii < 2; ii++ )
    {   
        if( metadataTable[ii][2] == 0 ) 
        {
            /*Assigning the coordinates of player*/
            *yCord = metadataTable[ii][0];
            *xCord = metadataTable[ii][1];
        } 
    }
}




/*----------------------------------------------------------------------------
SUBMODULE: checkFinished
IMPORT: xCord(Int), yCord(Int), goalXCord(Int), goalYCord(Int)
EXPORT: nil
ASSERTION: compare player cord and goal coordinates. If they are the same, 
return TRUE.
----------------------------------------------------------------------------*/

int checkFinished( int xCord, int yCord, int goalXCord, int goalYCord )
{
    int isFinished = FALSE;

    /*if player and goal coordinates are equal means player completed game*/
    if( goalXCord == xCord )
    {
        if( goalYCord == yCord )
        {
            isFinished = TRUE;
        }
    }

    return isFinished;
}




/*----------------------------------------------------------------------------
SUBMODULE: freeMetadataTable
IMPORT: metadataTable(int**), metadataAmount(Int)
EXPORT: nil
ASSERTION: Free metadataTable memory allocation. 
----------------------------------------------------------------------------*/

void freeMetadataTable( int **metadataTable, int metadataAmount )
{
    int ii;

    /*Freeing metadataTable*/
    for( ii = 0; ii < metadataAmount; ii++ )
    {
        free( metadataTable[ii] );/*Freeing pointers to the int*/
        metadataTable[ii] = NULL;
    }   
    free( metadataTable ); /*Freeing pointer to the arrays*/
    metadataTable = NULL;
}




/*----------------------------------------------------------------------------
SUBMODULE: freeMap
IMPORT: map(char**), mapRow(Int)
EXPORT: nil
ASSERTION: Free metadataTable memory allocation. 
----------------------------------------------------------------------------*/

void freeMap( char **map, int mapRow )
{
    int ii;

    /*Freeing map*/
    for( ii = 0; ii < mapRow; ii++ )
    {
        free( map[ii] );/*Freeing pointer to the char*/ 
        map[ii] = NULL;
    }
    free( map );/*Freeing pointer to the array*/
    map = NULL;
}


