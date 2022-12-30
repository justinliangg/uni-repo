/*----------------------------------------------------------------------------          
FILE: selector.h                                                                  
AUTHOR: Justin Liang(19821986)                                                  
UNIT: COMP1000                                                                  
LAST MOD: 07/11/2021                                                            
REQUIRES: keypad.h, LinkedList.h
----------------------------------------------------------------------------*/
#ifndef SELECTOR_H
#define SELECTOR_H

/*Includes*/
#include "keypad.h"
#include "LinkedList.h"

/*Struct*/
typedef struct Coordinate
{
    int xCoord;
    int yCoord;
} Coordinate;

/*Constants*/
#define FALSE 0
#define TRUE !FALSE

/*Function prototypes*/
void getSelectorCoords( Coordinate *selector, CalcKeypad *keypad );
void moveSelector( CalcKeypad *keypad, Coordinate *selector, char choice );
int executeSelected( CalcKeypad *keypad, Coordinate *selector, int *screenNum,
                     int *totalSum, LinkedList *operands );
int *copyScreenNum( int* screenNum );

#endif

