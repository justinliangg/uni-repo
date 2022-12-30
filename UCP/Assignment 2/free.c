/*---------------------------------------------------------------------------
FILE: free.c
AUTHOR: Justin Liang(19821986)
UNIT: COMP1000
LAST MOD: 10/09/2021
PURPOSE: free memory allocations
REQUIRES: map.c, LinkedList.c  
----------------------------------------------------------------------------*/
#include "free.h"



/*----------------------------------------------------------------------------
SUBMODULE: freeMallocs
IMPORT: map(Map*), coords(Coordinate*), prevMoves(LinkedList*) 
EXPORT: nil
ASSERTION: Free all memory allocations 
----------------------------------------------------------------------------*/

void freeMallocs( Map *map, Coordinate *coords, LinkedList *prevMoves )
{
    freeTable( map );

    /*Free structs*/
    free( map );
    map = NULL;
    free( coords );
    coords = NULL;

    /*freeing LinkedList*/
    freeLinkedList( prevMoves, freeCoords );
    prevMoves = NULL;
}




/*----------------------------------------------------------------------------
SUBMODULE: freeTable
IMPORT: map(Map*)
EXPORT: nil
ASSERTION: freeing the table in map.
COMMENTS: code reused from assignment01.
----------------------------------------------------------------------------*/

void freeTable( Map *map )
{
    int mapRow = map->row;
    char **mapTable = map->table;
    int ii;

    /*Freeing table*/
    for( ii = 0; ii < mapRow; ii++ )
    {
        free( mapTable[ii] );/*Freeing pointer to the char*/ 
        mapTable[ii] = NULL;
    }
    free( mapTable );/*Freeing pointer to the array*/
    mapTable = NULL;
}




/*----------------------------------------------------------------------------
SUBMODULE: freeCoords
IMPORT: data(void*)
EXPORT: nil
ASSERTION: freeing the data stored in a linkedlist
----------------------------------------------------------------------------*/

void freeCoords( void *data)
{
    Coordinate *coords = (Coordinate*) data;
    free(coords);
    coords = NULL;
}


