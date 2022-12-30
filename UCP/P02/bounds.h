#ifndef BOUNDS_H
#define BOUNDS_H

#include "macros.h"

char getInput(void);
int checkBetweenRange(int value, int ub, int lb);
int checkBetweenRangeD(double value, double ub, double lb);
int checkBetweenRangeC(char value, char ub, char lb);

#endif

