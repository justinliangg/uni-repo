/*---------------------------------------------------------------------------
FILE: movement.c
AUTHOR: Justin Liang(19821986)
UNIT: COMP1000
LAST MOD: 10/09/2021
PURPOSE: store functions used to move player character in the game.
REQUIRES: nil
----------------------------------------------------------------------------*/
#include "movement.h"



/*----------------------------------------------------------------------------
SUBMODULE: moveUp
IMPORT: map(char**), xCord(int*), yCord(int*)
EXPORT: nil
ASSERTION: updates the map and player coordinates vertically up by one grid 
on the map.
----------------------------------------------------------------------------*/

void moveUp( char **map, int *xCord, int *yCord )
{   
    char symbol;

    /*Changing character symbol first*/
    map[*yCord][*xCord] = '^';

    /*Checking if "up" is an empty space or goal*/
    symbol = map[(*yCord) - 1][*xCord];
    if( symbol == ' ' || symbol == 'x' )
    {   
        /*Changing empty space above to '^'*/
        map[(*yCord) - 1][*xCord] = '^';
        /*Changing previous player location to ' '*/
        map[*yCord][*xCord] = ' ';
        
        /*Update Player cord*/
        *yCord = (*yCord) - 1;
        
    }
}




/*----------------------------------------------------------------------------
SUBMODULE: moveDown
IMPORT: map(char**), xCord(int*), yCord(int*)
EXPORT: nil
ASSERTION: updates the map and player coordinates vertically down by one grid 
on the map.
----------------------------------------------------------------------------*/

void moveDown( char **map, int *xCord, int *yCord )
{   
    char symbol;
    
    /*Changing character symbol first*/
    map[*yCord][*xCord] = 'v';

    /*Checking if "down" is an empty space or goal*/
    symbol = map[(*yCord) + 1][*xCord];
    if( symbol == ' ' || symbol == 'x' )
    {   
        /*Changing empty space below to 'v'*/
        map[(*yCord) + 1][*xCord] = 'v';
        /*Changing previous player location to ' '*/
        map[*yCord][*xCord] = ' ';
        
        /*Update Player cord*/
        *yCord = (*yCord) + 1;    
    }
}




/*----------------------------------------------------------------------------
SUBMODULE: moveLeft
IMPORT: map(char**), xCord(int*), yCord(int*)
EXPORT: nil
ASSERTION: updates the map and player coordinates horizontally left by one grid 
on the map.
----------------------------------------------------------------------------*/

void moveLeft( char **map, int *xCord, int *yCord )
{   
    char symbol;

    /*Changing character symbol first*/
    map[*yCord][*xCord] = '<';

    /*Checking if "left" is an empty space or goal*/
    symbol = map[*yCord][(*xCord) - 1];
    if( symbol == ' ' || symbol == 'x' )
    {   
        /*Changing empty space on the left to '<'*/
        map[(*yCord)][(*xCord) - 1] = '<';
        /*Changing previous player location to ' '*/
        map[*yCord][*xCord] = ' ';
        
        /*Update Player cord*/
        *xCord = (*xCord) - 1; 
    }
}




/*----------------------------------------------------------------------------
SUBMODULE: moveRight
IMPORT: map(char**), xCord(int*), yCord(int*)
EXPORT: nil
ASSERTION: updates the map and player coordinates horizontally right by one 
grid on the map.
----------------------------------------------------------------------------*/

void moveRight( char **map, int *xCord, int *yCord )
{   
    char symbol;

    /*Changing character symbol first*/
    map[*yCord][*xCord] = '>';

    /*Checking if "right" is an empty space or goal*/
    symbol = map[*yCord][(*xCord) + 1];
    if( symbol == ' ' || symbol == 'x' )
    {  
        /*Changing empty space on the right to '>'*/
        map[(*yCord)][(*xCord) + 1] = '>';
        /*Changing previous player location to ' '*/
        map[*yCord][*xCord] = ' ';
        
        /*Update Player cord*/
        *xCord = (*xCord) + 1; 
    }
}



