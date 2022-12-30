/*---------------------------------------------------------------------------
FILE: display.c
AUTHOR: Justin Liang(19821986)
UNIT: COMP1000
LAST MOD: 10/09/2021
PURPOSE: store functions used to print map.
REQUIRES: nil
----------------------------------------------------------------------------*/
#include "display.h"

/*Static function declaration*/
static int withinRange( int yCord, int xCord, int darkRange , int row, 
                        int col ); 



/*----------------------------------------------------------------------------
SUBMODULE: printMap
IMPORT: xCord(int), yCord(int), darkRange(int), mapRow(int), mapCol(int),
        map(char**)
EXPORT: nil
ASSERTION: wrapper function to call print map based on darkRange input.
----------------------------------------------------------------------------*/

void printMap( int xCord, int yCord, int darkRange, int mapRow, int mapCol,
               char **map )
{
    if( darkRange == 0 )
    {
        printFullMap(map, mapRow, mapCol);
    }
    else
    {
        printDarkMap( xCord, yCord, darkRange, mapRow, mapCol, map );
    }
}




/*----------------------------------------------------------------------------
SUBMODULE: printFullMap
IMPORT: map(char**), mapRow(int), mapCol(int),
EXPORT: nil
ASSERTION: print the whole map.
----------------------------------------------------------------------------*/

void printFullMap(char **map, int mapRow, int mapCol)
{   
    int ii;
    int jj;

    /*Clearing the screen first*/
    system("clear");

    /*Printing the whole map*/
    for( ii = 0; ii< mapRow; ii++ )
    {
        for( jj = 0; jj< mapCol; jj++ )
        {
            printf( "%c", map[ii][jj] ); 
        }
        /*new line after each row*/
        printf("\n");
    }
    printf("\n");
}




/*----------------------------------------------------------------------------
SUBMODULE: printDarkMap
IMPORT: xCord(int), yCord(int), darkRange(int), mapRow(int), mapCol(int),
        map(char**)
EXPORT: nil
ASSERTION: uses darkRange and only print map within darkRange of player.
----------------------------------------------------------------------------*/

void printDarkMap( int xCord, int yCord, int darkRange, int mapRow, int mapCol
                  , char **map )
{
    int ii;
    int jj;

    /*Clearing screen first*/
    system("clear");

    /*Printing map based on darkRange*/
    for( ii = 0; ii < mapRow; ii++ )
    {
        for( jj = 0; jj < mapCol; jj++ )
        {   
            /*Checks if the current row and col coordinates is within darkRange 
            of player coordinates*/
            if( withinRange(yCord, xCord, darkRange, ii, jj) )
            {
                printf( "%c", map[ii][jj] );
            }
            else
            {
                printf(" ");
            }
        }
        /*new line after each row*/
        printf("\n");
    }
    printf("\n");
}




/*----------------------------------------------------------------------------
SUBMODULE: withinRange
IMPORT: xCord(int), yCord(int), darkRange(int), row(int), col(int)
EXPORT: nil
ASSERTION: checks if a given row and col is within the darkRange of player
coordinates. Returns TRUE if within range.
----------------------------------------------------------------------------*/

static int withinRange( int yCord, int xCord, int darkRange, int row, int col )
{   
    int inRange = FALSE;

    if( row >= (yCord - darkRange) && row <= (yCord + darkRange) )
    {
        if( col >= (xCord - darkRange) && col <= (xCord + darkRange) )
        {
            inRange = TRUE;
        }
    }
    return inRange;
}

