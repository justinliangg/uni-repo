/*---------------------------------------------------------------------------
FILE: generatemap.h
AUTHOR: Justin Liang(19821986)
UNIT: COMP1000
LAST MOD: 10/09/2021
REQUIRES: nil
----------------------------------------------------------------------------*/
#ifndef GENERATEMAP_H
#define GENERATEMAP_H

/*Includes*/
#include <stdlib.h>

/*Function prototypes*/
char **mallocMap( int mapRow, int mapCol );
void makeBorders( char **map, int mapRow, int mapCol );
void useMetadataTable( char **map, int metadataAmount, int **metadataTable );

#endif
