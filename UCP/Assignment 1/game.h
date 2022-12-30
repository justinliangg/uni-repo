/*---------------------------------------------------------------------------
FILE: game.h
AUTHOR: Justin Liang(19821986)
UNIT: COMP1000
LAST MOD: 10/09/2021
REQUIRES: generatemap.h, movement.h, display.h, map.h, terminal.h 
---------------------------------------------------------------------------*/
#ifndef GAME_H
#define GAME_H

/*Includes*/
#include <stdlib.h>
#include <stdio.h>
#include "map.h"
#include "generatemap.h"
#include "movement.h"
#include "terminal.h"
#include "display.h"

/*Constants*/
#define FALSE 0
#define TRUE !FALSE

/*Function prototypes*/
void startGame( int darkRange );
void enterGame( char **map ,int **metadataTable, int mapRow, int mapCol
                , int darkRange );
void getGoalCord( int *goalxCord, int *goalYCord, int **metadataTable );
void getPlayerCord( int *xCord, int *yCord, int **metadataTable );
int checkFinished( int xCord, int yCord, int goalXCord, int goalYCord );
void freeMetadataTable( int **metadataTable, int metadataAmount);
void freeMap( char **map, int mapRow );

#endif
