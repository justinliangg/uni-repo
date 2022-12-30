/*----------------------------------------------------------------------------          
FILE: display.c                                                                    
AUTHOR: Justin Liang(19821986)                                                  
UNIT: COMP1000                                                                  
LAST MOD: 07/11/2021                                                            
PURPOSE: functions used to display the calculator                     
REQUIRES: keypad.c                                                            
----------------------------------------------------------------------------*/
#include "display.h"


/*----------------------------------------------------------------------------
SUBMODULE: displayCalc
IMPORT: screenNum(int), keypad(CalcKeypad*), totalSum(int)
EXPORT: nil
ASSERTION: Display the whole calculator to terminal screen
----------------------------------------------------------------------------*/

void displayCalc( int screenNum, CalcKeypad *keypad, int totalSum )
{
    system("clear");
    displayNumber( screenNum );
    displayKeypad( keypad ); 
    printf("current total: %d\n", totalSum);
}
     


/*----------------------------------------------------------------------------
SUBMODULE: displayNumber
IMPORT: screenNum(int)
EXPORT: nil
ASSERTION: prints the number display at the top of calculator to terminal
----------------------------------------------------------------------------*/

void displayNumber( int screenNum )
{
    printf("---------\n");
    printf("|%7d|\n", screenNum );
    printf("---------\n");
}
 

/*----------------------------------------------------------------------------
SUBMODULE: displayKeypad
IMPORT: keypad (CalcKeypad*)
EXPORT: nil
ASSERTION: Iterates through the keypad and print contents in the keypad
to terminal
----------------------------------------------------------------------------*/

void displayKeypad( CalcKeypad *keypad )
{
    int i;
    int j;
    
    printf("\n");
    for(i = 0; i < keypad->row; i++)
    {   
        for(j = 0; j < keypad->column; j++)
        {
            printf("%c ", keypad->table[i][j] );
        }
        printf("\n");
    }
}
  
