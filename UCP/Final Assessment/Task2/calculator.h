/*----------------------------------------------------------------------------          
FILE: calculator.h                                                                   
AUTHOR: Justin Liang(19821986)                                                  
UNIT: COMP1000                                                                  
LAST MOD: 07/11/2021                                                            
REQUIRES: keypad.h, display.h, selector.h, LinkedList.h, terminal.h                     
----------------------------------------------------------------------------*/
#ifndef CALCULATOR_H
#define CALCULATOR_H

/*Includes*/
#include <stdio.h>
#include <time.h>
#include "keypad.h"
#include "display.h"
#include "selector.h"
#include "LinkedList.h"
#include "terminal.h"

/*Constants*/
#define FALSE 0
#define TRUE !FALSE

/*Function prototypes*/
void startCalculator( FILE *fpWrite );
int enterCalculator( CalcKeypad *keypad, LinkedList *operands );
void writeEquationToFile( LinkedList* operands, int totalSum, FILE *fpWrite );
void freeKeypadTable( CalcKeypad *keypad );

#endif
