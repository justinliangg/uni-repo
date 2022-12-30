/*----------------------------------------------------------------------------          
FILE: display.h                                                                    
AUTHOR: Justin Liang(19821986)                                                  
UNIT: COMP1000                                                                  
LAST MOD: 07/11/2021     
REQUIRES: keypad.h
----------------------------------------------------------------------------*/
#ifndef DISPLAY_H
#define DISPLAY_H

/*Includes*/
#include <stdio.h>
#include "keypad.h"

/*Function prototypes*/
void displayCalc( int screenNum, CalcKeypad *keypad, int totalSum );
void displayNumber( int screenNum );
void displayKeypad( CalcKeypad *keypad );

#endif
