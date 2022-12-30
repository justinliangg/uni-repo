#ifndef MAIN_H
#define MAIN_H


#define FALSE 0
#define TRUE !FALSE
#include "Functions.h"
void usage();
int checkArgument(char* string);
void performArgument(char** arrOfString, int* array, int length);
void toStringUpper(char* string);
#endif
