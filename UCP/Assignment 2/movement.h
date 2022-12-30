/*---------------------------------------------------------------------------
FILE: movement.h
AUTHOR: Justin Liang(19821986)
UNIT: COMP1000
LAST MOD: 10/09/2021
REQUIRES: nil 
----------------------------------------------------------------------------*/
#ifndef MOVEMENT_H
#define MOVEMENT_H

/* INCLUDES */
#include "LinkedList.h"
#include "map.h"
#include <math.h>

/*Constants*/
#define FALSE 0
#define TRUE !FALSE

/*Function prototypes*/
void movePlayer( char** mapTable, Coordinate *coords, char direction );
void undo( Map *map, Coordinate *coords, LinkedList *prevMoves );
void moveSnake( Map *map, Coordinate *coords );
int tryMoveSnake( char **mapTable, Coordinate *coords, char direction );

#endif

