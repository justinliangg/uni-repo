/*----------------------------------------------------------------------------          
FILE: selector.c                                                                  
AUTHOR: Justin Liang(19821986)                                                  
UNIT: COMP1000                                                                  
LAST MOD: 07/11/2021                                                            
PURPOSE: stores functions for selector operations       
REQUIRES: keypad.c, LinkedList.c                                                        
----------------------------------------------------------------------------*/
#include "selector.h"



/*----------------------------------------------------------------------------
SUBMODULE: getSelectorCoords
IMPORT: selector(Coordinate*), keypad(CalcKeypad*)
EXPORT: nil
ASSERTION: going through the table in keypad and finding the coordinates of
the selector.
----------------------------------------------------------------------------*/

void getSelectorCoords( Coordinate *selector, CalcKeypad *keypad )
{
    int i;
    int j;
    
    for( i = 0; i < keypad->row; i++ )
    {
        for( j = 0; j < keypad->column; j++ )
        {
            /* getting selector coords */
            if(keypad->table[i][j] == '^')
            {
                selector->yCoord = i;
                selector->xCoord = j;
            }
        }
    }
}




/*----------------------------------------------------------------------------
SUBMODULE: moveSelector
IMPORT: keypad(CalcKeypad*), selector(Coordinate*), choice(char)
EXPORT: nil
ASSERTION: based on user's choice, move the selector. 
----------------------------------------------------------------------------*/

void moveSelector( CalcKeypad *keypad, Coordinate *selector, char choice )
{
    int newXCoord = selector->xCoord;
    int newYCoord = selector->yCoord;

    switch(choice)
    {
        case 'w':
            newYCoord = newYCoord - 2;
            break;
            
        case 'a':
            newXCoord = newXCoord - 1;
            break;

        case 's':
            newYCoord = newYCoord + 2;
            break;

        case 'd':
            newXCoord = newXCoord + 1;
            break;
    }
    
    /* checking if new location is within the bounds of keypad */
    if( newYCoord <= (keypad->row) && newYCoord >= 0 )
    {
        if( newXCoord < (keypad->column) && newXCoord >= 0 )
        {
            /* changing previous selector location to empty */
            keypad->table[selector->yCoord][selector->xCoord] = ' ';

            /* changing new location to '^' */
            keypad->table[newYCoord][newXCoord] = '^';

            /* updating the coords of selector */
            selector->xCoord = newXCoord;
            selector->yCoord = newYCoord;
        }
    }
}




/*----------------------------------------------------------------------------
SUBMODULE: executeSelected
IMPORT: keypad(Calckeypad*), selector(Coordinate*), screenNum(int*), 
        totalSum(int*), operands(LinkedList*)
EXPORT: checkFinished(int)
ASSERTION: execute the command that the selector is pointing to.
----------------------------------------------------------------------------*/

int executeSelected( CalcKeypad *keypad, Coordinate *selector, int *screenNum,
                      int *totalSum, LinkedList *operands )
{  
    /* variables */
    int selectedX;
    int selectedY;
    char selected;  
    int checkFinished;
    
    /* Getting the selected character */
    selectedX = selector->xCoord;
    selectedY = (selector->yCoord) - 1;
    selected = keypad->table[selectedY][selectedX];
    
    /* based on selected char, perform actions */
    checkFinished = FALSE;
    if( selected >= 48 && selected <= 57 ) /* 1 - 9 selected */
    {   
        /* checking that it is within 7 digits */
        if( *screenNum < 1000000 )
        {
            /* converting char to its decimal equivalent */
            int selectedNum = ((int) selected) - 48; 
            /* adding to screenNum */
            *screenNum = *screenNum * 10 + (int) selectedNum;
        }
    }
    else if ( selected == '+' )
    {
        int *copiedNum;
        
        /* adding number greater than 0 to linkedlist only */
        if( *screenNum > 0 )
        {
            /* adding to totalSum */
            *totalSum = *totalSum + *screenNum;
            /*adding to LinkedList*/
            copiedNum = copyScreenNum( screenNum );
            insertLast( operands, copiedNum );

            /* resetting value of screenNum */
            *screenNum = 0;
        }
    }
    else if ( selected == '=' )
    {
        checkFinished = TRUE;
    }
    
    return checkFinished;
}




/*----------------------------------------------------------------------------
SUBMODULE: copyScreenNum
IMPORT: screenNum(int*)
EXPORT: copiedNum(int*)
ASSERTION: creates a copy of the imported screenNum, and returns copiedNum
pointer.
----------------------------------------------------------------------------*/

int* copyScreenNum( int *screenNum )
{
    int *copiedNum = (int*) malloc(sizeof(int));
    *copiedNum = *screenNum;

    return copiedNum;
}


    
