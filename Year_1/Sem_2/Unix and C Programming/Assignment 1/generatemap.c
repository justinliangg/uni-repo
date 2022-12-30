/*---------------------------------------------------------------------------
FILE: generatemap.c
AUTHOR: Justin Liang(19821986)
UNIT: COMP1000
LAST MOD: 10/09/2021
PURPOSE: store functions used to create the map based on the metadata.
REQUIRES: nil
----------------------------------------------------------------------------*/
#include "generatemap.h"



/*----------------------------------------------------------------------------
SUBMODULE: mallocMap
IMPORT: mapRow(int), mapCol(int)
EXPORT: map(char**)
ASSERTION: allocating memory for the map and then returning the pointer to the
map.
----------------------------------------------------------------------------*/

char **mallocMap( int mapRow, int mapCol )
{
    /*declaring variables*/
    char** map;
    int ii;

    /*Allocating memory for map*/ 
    /* Memory for whole 2d array */
    map = (char**) malloc(sizeof(char*) * mapRow); 
    
    /* Memory for the rows */
    for(ii = 0; ii < mapRow; ii++)
    {
        map[ii] = (char*) malloc(sizeof(char) * mapCol);
    }
    
    return map;
}




/*----------------------------------------------------------------------------
SUBMODULE: makeBorders
IMPORT: map(char**), mapRow(int), mapCol(int)
ASSERTION: padding the map with spaces first, then making the borders and 
then lastly add the corners.
----------------------------------------------------------------------------*/

void makeBorders( char **map, int mapRow, int mapCol )
{
    int ii;
    int jj;

    /* padding the map with empty spaces first*/
    for( ii = 0; ii < mapRow; ii++ )
    {
        for( jj= 0; jj < mapCol; jj++ )
        {
            map[ii][jj] = ' ';
        }
    }

    /* Creating the Borders */
    for( ii = 0; ii < mapRow; ii++ )
    {
        for( jj = 0; jj < mapCol; jj++ )
        {
            /* filling first and last row with '-' */
            if( ii == 0 || ii == (mapRow -1) )
            {
                map[ii][jj] = '-';
            }
            /* filling first and last columm with '|' */
            else if( jj == 0 || jj == (mapCol - 1) )
            {   
                map[ii][jj] = '|';
            }
        }
    }
    
    /* Making Corners */ 
    map[0][0] = '#';
    map[mapRow-1][0] = '#'; 
    map[0][mapCol-1] = '#';
    map[mapRow-1][mapCol-1] = '#';
}




/*----------------------------------------------------------------------------
SUBMODULE: userMetadataTable
IMPORT: map(char**), metadataAmount(int), metadataTable(int**)
EXPORT: void
ASSERTION: use metadataTable to determine locations of player, goal and walls.
Adding it to map.
----------------------------------------------------------------------------*/

void useMetadataTable( char **map, int metadataAmount, int **metadataTable )
{
    int xCord;
    int yCord;
    char symbol;
    int ii;
    

    for( ii= 0; ii< metadataAmount; ii++ )
    {   
        /*Getting Coordinates*/
        yCord = metadataTable[ii][0];
        xCord = metadataTable[ii][1];

        /*Determine what object symbol for the coordinate*/
        if( metadataTable[ii][2] == 0 ) /*Checking if player object*/
        {
            symbol = '^';
        }
        else if( metadataTable[ii][2] == 1 )/*Checking if goal object*/
        {
            symbol = 'x';
        }
        else if( metadataTable[ii][2] == 2 )/*Checking if wall object*/
        {
            symbol = 'o';
        }
        
        /*Using the Coordinates to input the symbol into the map*/ 
        map[yCord][xCord] = symbol;
    }
}
   


