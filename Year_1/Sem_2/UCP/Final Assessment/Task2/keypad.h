/*----------------------------------------------------------------------------          
FILE: keypad.h                                                                  
AUTHOR: Justin Liang(19821986)                                                  
UNIT: COMP1000                                                                  
LAST MOD: 07/11/2021                                                            
REQUIRES:                                                                
----------------------------------------------------------------------------*/
#ifndef KEYPAD_H
#define KEYPAD_H

/*Includes*/
#include <stdlib.h>

/*Structs*/
typedef struct CalcKeypad
{
    char **table;
    int row;
    int column;
} CalcKeypad;

/*function prototypes*/
void makeKeypad( int choice, CalcKeypad *keypad );
char** mallocKeypad();
void padKeypad( char** keypad, int row, int column );
void keypadLayoutOne( char** keypad );
void keypadLayoutTwo( char** keypad );

#endif 
