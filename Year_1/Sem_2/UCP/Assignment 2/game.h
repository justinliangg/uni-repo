/*---------------------------------------------------------------------------
FILE: game.h
AUTHOR: Justin Liang(19821986)
UNIT: COMP1000
LAST MOD: 10/09/2021
REQUIRES: map.h, movement.h, free.h, terminal.h, LinkedList.h
---------------------------------------------------------------------------*/
#ifndef GAME_H
#define GAME_H

/*Includes*/
#include "map.h"
#include "movement.h"
#include "free.h"
#include "terminal.h"
#include "LinkedList.h"

/*Constants*/
#define FALSE 0
#define TRUE !FALSE

/*Function prototypes*/
void startGame( FILE *fp );
void enterGame( Map *map, Coordinate *coords, LinkedList *prevMoves );
void storeCoords( LinkedList *prevMoves, Coordinate *coords );
Coordinate* createPrevCoords( Coordinate *coords );
int checkFinished( Coordinate *coords );

#endif
