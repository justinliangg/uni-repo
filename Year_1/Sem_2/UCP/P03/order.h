#ifndef ORDER_H
#define ORDER_H
#include <stdio.h>
typedef void(*Arrange)(int *, int*, int*);
void ascending3(int*, int*,int *);
void descending3(int*, int*, int*);
Arrange order(char character);

#endif
