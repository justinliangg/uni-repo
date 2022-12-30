#ifndef DISPLAY_H
#define DISPLAY_H

#include "plot.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define FALSE 0
#define TRUE !FALSE

void readFile(FILE*, char*);
double** makeArray(FILE*, int*, int*);
void freeArray(double**, int, int);

#endif
