/*---------------------------------------------------------------------------
FILE: display.h
AUTHOR: Justin Liang(19821986)
UNIT: COMP1000
LAST MOD: 10/09/2021
REQUIRES: nil
----------------------------------------------------------------------------*/
#ifndef DISPLAY_H
#define DISPLAY_H

/*includes*/
#include <stdio.h>
#include <stdlib.h>

/*Constants*/
#define FALSE 0
#define TRUE !FALSE

/*Function prototypes*/
void printMap( int xCord , int yCord, int darkRange, int mapRow, int mapCol, 
               char **map );
void printFullMap( char **map , int mapRow, int mapCol );
void printDarkMap( int xCord, int yCord, int darkRange, int mapRow, int mapCol,
                   char **map );

#endif
