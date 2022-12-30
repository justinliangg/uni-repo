/*---------------------------------------------------------------------------
FILE: free.h
AUTHOR: Justin Liang(19821986)
UNIT: COMP1000
LAST MOD: 10/09/2021
REQUIRES: map.h, LinkedList.h
---------------------------------------------------------------------------*/
#ifndef FREE_H
#define FREE_H

/*Includes*/
#include "map.h"
#include "LinkedList.h"

/*Function prototypes*/
void freeMallocs( Map *map, Coordinate *coords, LinkedList *prevMoves );
void freeTable( Map *map );
void freeCoords( void *data );

#endif
