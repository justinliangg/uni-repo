#ifndef GENERATE_H
#define GENERATE_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "randomarray.h"

#define FALSE 0
#define TRUE !FALSE

double** makeArray(int row, int column);
int checkArguments(char* pRow, char* pColumn);
void writeToFile(FILE* fp, double** array, int row, int column);
void freeArray(double** array, int row);

#endif

