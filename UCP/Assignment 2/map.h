/*---------------------------------------------------------------------------
FILE: map.h
AUTHOR: Justin Liang(19821986)
UNIT: COMP1000
LAST MOD: 09/10/2021
REQUIRES: nil
----------------------------------------------------------------------------*/
#ifndef MAP_H
#define MAP_H

/*Includes*/
#include <stdlib.h>
#include <stdio.h>

/*Constants*/
#define FALSE 0
#define TRUE !FALSE

/*Structs*/
/*Map*/
typedef struct Map
{   
    char **table;
    int row;
    int col;
    int numObjects;
}Map;

/*Coordinates*/
typedef struct Coordinate
{   
    /*Player Cords*/
    int playerX;
    int playerY;
    char playerSymbol;
    /*Snake Cords*/
    int snakeX;
    int snakeY;
    /*Goal Cords*/
    int goalX;
    int goalY;
}Coordinate;


/*Function prototypes*/
void mallocMap( Map *map, FILE *fp );
void makeBorders( Map *map );
void useFileData( Map *map, Coordinate *coords, FILE *fp );
void printMap( Map *map );

#endif
