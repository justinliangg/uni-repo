#ifndef COPYFILE_H
#define COPYFILE_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define FALSE 0
#define TRUE !FALSE

int checkFormat(char**);
void copyFile(FILE*, FILE*, char*, char*);

#endif


