/*---------------------------------------------------------------------------
FILE: map.c
AUTHOR: Justin Liang(19821986)
UNIT: COMP1000
LAST MOD: 09/10/2021
PURPOSE: store functions related to the map (generate map and print map).
REQUIRES: nil
----------------------------------------------------------------------------*/
#include "map.h"
   


/*----------------------------------------------------------------------------
SUBMODULE: mallocMap
IMPORT: map(Map*), fp(FILE*)
EXPORT: nil
ASSERTION: getting dimensions of the map from file and allocate memory for the 
table inside map struct.
----------------------------------------------------------------------------*/

void mallocMap(Map *map, FILE *fp)
{
    /*Getting metadataAmount, rows and col*/
    int metadataAmount;
    int mapRow;
    int mapCol;
    int nRead;
    int ii;
    
    /*reading the first row in file*/
    nRead = fscanf(fp,"%d %d %d\n", &metadataAmount, &mapRow, &mapCol);
    if(nRead == 3)
    {
        map->row = mapRow;
        map->col = mapCol;
        map->numObjects = metadataAmount;
    }
    /*checking if any error occured*/
    if(ferror(fp))
    {
        perror("Error reading in first row");
    }

    /*Allocating memory for map*/ 
    /* Memory for whole 2d array */
    map->table = (char**) malloc(sizeof(char*) * mapRow); 
    
    /* Memory for the rows */
    for(ii = 0; ii < mapRow; ii++)
    {
        map->table[ii] = (char*) malloc(sizeof(char) * mapCol);
    }
}




/*----------------------------------------------------------------------------
SUBMODULE: makeBorders
IMPORT: map(Map*)
ASSERTION: padding the map with spaces first, then making the borders and 
then lastly add the corners.
COMMENTS: code reused from assignment01.
----------------------------------------------------------------------------*/

void makeBorders(Map *map)
{   
    int ii;
    int jj;
    
    /* padding the map with empty spaces first*/
    for( ii = 0; ii < map->row; ii++ )
    {
        for( jj= 0; jj < map->col; jj++ )
        {
            map->table[ii][jj] = ' ';
        }
    }

    /* Creating the Borders */
    for( ii = 0; ii < map->row; ii++ )
    {
        for( jj = 0; jj < map->col; jj++ )
        {
            /* filling first and last row with '-' */
            if( ii == 0 || ii == (map->row-1) )
            {
                map->table[ii][jj] = '-';
            }
            /* filling first and last columm with '|' */
            else if( jj == 0 || jj == (map->col - 1) )
            {   
                map->table[ii][jj] = '|';
            }
        }
    }
    
    /* Making Corners */ 
    map->table[0][0] = '#';
    map->table[(map->row-1)][0] = '#'; 
    map->table[0][(map->col-1)] = '#';
    map->table[(map->row-1)][(map->col-1)] = '#';

}




/*----------------------------------------------------------------------------
SUBMODULE: useFileData
IMPORT: map(Map*), coords(Coordinate*), fp(FILE*)
EXPORT: void
ASSERTION: use filedata to determine locations of player, goal, walls and enemy.
Adding it to map and store the coordinates in coords.
----------------------------------------------------------------------------*/

void useFileData( Map *map, Coordinate *coords, FILE *fp )
{   
    int count = 0;
    int done = FALSE;
    while( !done && (count < map->numObjects) ) 
    {   
        int xCoord;
        int yCoord;
        int objectCode;
        char object;
        int nRead;

        /*getting info from file*/
        nRead = fscanf(fp, "%d %d %d\n", &yCoord, &xCoord, &objectCode);
        if(nRead == 3)
        { 
            /*Determine what object symbol for the coordinate*/
            if( objectCode == 0 ) /*Checking if player object*/
            {   
                coords->playerX = xCoord;
                coords->playerY = yCoord;
                coords->playerSymbol = '^';
                object = '^';
            }
            else if( objectCode == 1 )/*Checking if enemy object*/
            {
                coords->snakeX = xCoord;
                coords->snakeY = yCoord;
                object = '~';
            }
            else if( objectCode == 2 )/*Checking if goal object*/
            {
                coords->goalX = xCoord;
                coords->goalY = yCoord;
                object = 'x';
            }
            else if( objectCode == 3 )/*Checking if wall object*/
            {
                object = 'o';
            }
        }
        else /*reached end of file or error has occured*/
        {   
            done = TRUE;
        }

        /*Using the Coordinates to input the object into the map table*/ 
        map->table[yCoord][xCoord] = object;
        count++;
    }
    
    /* Checking if file read was complete */
    if(ferror(fp))
    {
        perror("Error reading in values from data");
    }
}
  



/*----------------------------------------------------------------------------
SUBMODULE: printMap
IMPORT: map(Map*)
EXPORT: nil
ASSERTION: print the whole map to terminal.
COMMENTS: code reused from assignment01.
----------------------------------------------------------------------------*/

void printMap( Map *map )
{   
    int ii;
    int jj;

    /*Clearing the screen first*/
    system("clear");

    /*Printing the whole map*/
    for( ii = 0; ii< (map->row); ii++ )
    {
        for( jj = 0; jj< (map->col); jj++ )
        {
            printf( "%c", map->table[ii][jj] ); 
        }
        /*new line after each row*/
        printf("\n");
    }
    printf("\n");
}

