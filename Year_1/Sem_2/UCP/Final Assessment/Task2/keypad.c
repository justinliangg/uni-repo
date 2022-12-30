/*----------------------------------------------------------------------------          
FILE: keypad.c                                                                  
AUTHOR: Justin Liang(19821986)                                                  
UNIT: COMP1000                                                                  
LAST MOD: 07/11/2021                                                            
PURPOSE: stores functions used to create the keypad for the calculator           
REQUIRES:                                                     
----------------------------------------------------------------------------*/
#include "keypad.h"



/*----------------------------------------------------------------------------
SUBMODULE: makeKeypad
IMPORT: choice(int), keypad (char***), row(int*), column(int*) 
EXPORT: nil
ASSERTION: allocates memory for a 2D char array, fill keypad with a layout 
based on choice given 
----------------------------------------------------------------------------*/

void makeKeypad( int choice, CalcKeypad *keypad )
{
    /* dimension of keypad */
    keypad->row = 8;
    keypad->column = 3;
    
    /*allocating memory for keypad*/
    keypad->table = mallocKeypad( keypad->row , keypad->column );
    padKeypad( keypad->table, keypad->row, keypad->column );

    /*based on choice, fill keypad with the layout */
    switch(choice)
    {
        case 0: 
            keypadLayoutOne( keypad->table );
            break;

        case 1: 
            keypadLayoutTwo( keypad->table );
            break;
    }
}




/*----------------------------------------------------------------------------
SUBMODULE: mallocKeypad
IMPORT: row(int), column(int) 
EXPORT: nil
ASSERTION: allocates memory on the heap for a char 2D array based on row and
column imported
----------------------------------------------------------------------------*/

char** mallocKeypad( int row, int column )
{
    char** keypad = NULL;
    int i;

    /* memory for the whole 2d array */
    /*size of keypad is 8 x 3 */
    keypad = (char**) malloc(sizeof(char*) * row);
    
    /* making the columns */
    for( i = 0; i < 8; i++)
    {
        keypad[i] = (char*) malloc(sizeof(char) * column);
    }

    return keypad;
}




/*----------------------------------------------------------------------------
SUBMODULE: padKeypad
IMPORT: keypad(char**), row(int), column(int) 
EXPORT: nil
ASSERTION: pads the keypad with spaces
----------------------------------------------------------------------------*/

void padKeypad( char** keypad, int row, int column )
{
    int i;
    int j;

    for(i = 0; i < row; i++)
    {
        for(j = 0; j < column; j++)
        {
            keypad[i][j] = ' ';
        }
    }
}




/*----------------------------------------------------------------------------
SUBMODULE: keypadLayoutOne
IMPORT: keypad(char**)
EXPORT: nil
ASSERTION: fills the keypad with a specific layout. 
COMMENTS: Would normally put a newline after the semicolon. However for better
visualisation, I have made it so it mimics the keypad that will be displayed.
----------------------------------------------------------------------------*/

void keypadLayoutOne( char** keypad )
{
    keypad[0][0] = '+'; keypad[0][1] = '0'; keypad[0][2] = '=';
    
    keypad[1][0] = '^'; /*pointer*/

    keypad[2][0] = '7'; keypad[2][1] = '8'; keypad[2][2] = '9';
    
    keypad[4][0] = '4'; keypad[4][1] = '5'; keypad[4][2] = '6'; 
    
    keypad[6][0] = '1'; keypad[6][1] = '2'; keypad[6][2] = '3';
}




/*----------------------------------------------------------------------------
SUBMODULE: keypadLayoutTwo
IMPORT: keypad(char**)
EXPORT: nil
ASSERTION: fills the keypad with a specific layout. 
COMMENTS: Would normally put a newline after the semicolon. However for better
visualisation, I have made it so it mimics the keypad that will be displayed.
----------------------------------------------------------------------------*/

void keypadLayoutTwo( char** keypad )
{
    keypad[0][0] = '1'; keypad[0][1] = '2'; keypad[0][2] = '3';

    keypad[1][0] = '^'; /*pointer*/
    
    keypad[2][0] = '4'; keypad[2][1] = '5'; keypad[2][2] = '6';
    
    keypad[4][0] = '7'; keypad[4][1] = '8'; keypad[4][2] = '9'; 
    
    keypad[6][0] = '+'; keypad[6][1] = '0'; keypad[6][2] = '=';
}

