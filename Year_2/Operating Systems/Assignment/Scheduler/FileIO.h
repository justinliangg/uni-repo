/*---------------------------------------------------------------------------
FILE: FileIO.h
AUTHOR: Justin Liang(19821986)
UNIT: COMP2006
LAST MOD: 09/05/2022
PURPOSE: contains functions for the different disk scheduling algorithms.
REQUIRES: NIL
----------------------------------------------------------------------------*/

#ifndef FILEIO_H
#define FILEIO_H

/* Includes */
#include <stdio.h>

/* Constants */
#define FALSE 0 
#define TRUE !FALSE

/*Function Prototypes*/
int getArraySize(FILE* fp);
void readFile(int* array, int arraySize, char* fileName);

#endif