/*---------------------------------------------------------------------------
FILE: scheduler.h
AUTHOR: Justin Liang(19821986)
UNIT: COMP2006
LAST MOD: 09/05/2022
REQUIRES: NIL
----------------------------------------------------------------------------*/
#ifndef SCHEDULER_H
#define SCHEDULER_H

/* Includes */
#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <string.h>
#include "FileIO.h"


/* Constants */
#define FALSE 0 
#define TRUE !FALSE

//index for the different data info from input file.
const int REQUEST_START = 3;
const int HEAD_START = 1;
const int PREV_REQUEST = 2;
const int DISK_SIZE = 0;

//direction of which way to traverse the array.
const int UP = 1;
const int DOWN = 0;


/*Function Prototypes*/
int FCFS(int* cylinders, int arraySize);
int SSTF(int* cylinders, int arraySize);
int SCAN(int* inputData, int arraySize);
int C_SCAN(int* inputData, int arraySize);
int LOOK(int* inputData, int arraySize);
int C_LOOK(int* inputData, int arraySize);

int processFile(int** inputData, int* arraySize, char* fileName);
int* createIntArrayCopy(int* array, int arraySize);
void sortRequests(int* inputData, int arraySize);
int findMidPointIndex(int* sortedInputData, int arraySize);

#endif 
