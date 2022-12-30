#ifndef READJOURNAL_H
#define READJOURNAL_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "LinkedList.h"

#define FALSE 0
#define TRUE !FALSE

typedef struct Entries
{
    int day;
    int month;
    int year;
    char text[101];
} Entries;

void fileRead(FILE*, LinkedList*, int);

void printEntries(void*);
void freeEntries(void*);
#endif
