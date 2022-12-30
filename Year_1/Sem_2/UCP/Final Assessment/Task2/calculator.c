/*----------------------------------------------------------------------------          
FILE: calculator.c                                                                    
AUTHOR: Justin Liang(19821986)                                                  
UNIT: COMP1000                                                                  
LAST MOD: 07/11/2021                                                            
PURPOSE: encapsulates the calculator into startCalculator function                      
REQUIRES: keypad.c, display.c, LinkedList.c, terminal.c, selector.c                     
----------------------------------------------------------------------------*/
#include "calculator.h"



/*----------------------------------------------------------------------------
SUBMODULE: startCalculator
IMPORT: fpWrite(FILE*)
EXPORT: nil
ASSERTION: make the keypad, enter the calculator and then write the equation to 
file. Freeing all memory used once done.
----------------------------------------------------------------------------*/

void startCalculator( FILE *fpWrite )
{
    /*variables*/
    CalcKeypad *keypad = NULL;
    LinkedList *operands = NULL;
    int layoutChoice;
    int totalSum;

    /*Allocating memory*/
    keypad = (CalcKeypad*) malloc(sizeof(CalcKeypad));
    operands = createLinkedList();
    
    /* GENERATE KEYPAD */
    srand(time(NULL));
    layoutChoice = rand() % 2; /*generating a random number for keypad layout*/ 
    makeKeypad( layoutChoice, keypad );
    
    /* ENTER CALCULATOR */
    totalSum = enterCalculator( keypad, operands );

    /* writing equation to file */
    writeEquationToFile( operands, totalSum, fpWrite );
    fclose( fpWrite );

    /* Freeing memory used */
    freeKeypadTable( keypad );
    free( keypad );
    keypad = NULL;
    free( operands );
    operands = NULL;
} 




/*----------------------------------------------------------------------------
SUBMODULE: entercalculator
IMPORT: keypad(CalcKeypad*), operands(LinkedList*)
EXPORT: totalSum(int)
ASSERTION: allow user to use the calculator, returning the total sum of the
calculations done by the user.
----------------------------------------------------------------------------*/

int enterCalculator( CalcKeypad *keypad, LinkedList *operands )
{   
    /* variables */
    Coordinate *selector = (Coordinate*) malloc(sizeof(Coordinate));
    int screenNum;
    int totalSum;
    int finished;
    
    /* setting up calculator */
    screenNum = 0;
    totalSum = 0;
    disableBuffer();
    displayCalc( screenNum, keypad, totalSum );
    
    /* Using calculator */
    getSelectorCoords( selector, keypad );
    finished = FALSE;
    while( !finished )
    {
        /* getting user input to move selector or execute selector */
        char input;
        scanf(" %c", &input);
        
        if( input == 'w' || input == 'a' || input == 's' || input == 'd' )
        {
            moveSelector( keypad, selector, input ); 
        }
        else if( input == 'e' ) 
        {
            /* executing selected and checking if finished */
            finished = executeSelected( keypad, selector, &screenNum, &totalSum,
                                        operands );
        }            

        displayCalc( screenNum, keypad, totalSum );
    } 
    
    /*freeing allocations*/
    free(selector);
    selector = NULL;

    enableBuffer();

    return totalSum;
}  




/*----------------------------------------------------------------------------
SUBMODULE: writeEquationToFile
IMPORT: operands(LinkedList*), totalSum(int), fpWrite(FILE*)
EXPORT: nil
ASSERTION: remove operands from LinkedList and write both the total sum and
operands to the file in the form of an equation.
----------------------------------------------------------------------------*/

void writeEquationToFile( LinkedList* operands, int totalSum, FILE *fpWrite )
{   
    int *currOperand;

    /* Actions based on number of items in the linkedlist */
    if( operands->length == 0 )
    {   
        /* nothing in linkedlist just write 0 to file */
        fprintf( fpWrite, "%d\n", 0 );
    }   
    else if( operands->length == 1 )
    {   
        /* only one item, just write number to file */
        currOperand = (int*) removeLast( operands );
        fprintf( fpWrite, "%d\n", *currOperand );
        free( currOperand );
    }
    else if( operands->length >= 2 )
    {   
        /* writing the first operand to file first */
        currOperand = (int*) removeLast( operands );
        fprintf( fpWrite, "%d ", *currOperand );
        free( currOperand );
        
        /* Writing subsequent operands to file */
        while( operands->length > 0 )
        {
            currOperand = (int*) removeLast( operands );
            fprintf( fpWrite, "+ %d ", *currOperand );
            free( currOperand );
        }
        
        /* printing the total sum of all the operands */
        fprintf( fpWrite, "= %d\n", totalSum );
    }

    /* Checking if error occured while writing */
    if( ferror(fpWrite) )
    {
       perror("Error writing to file");
    }
}




/*----------------------------------------------------------------------------
SUBMODULE: freeKeypadTable
IMPORT: keypad(CalcKeypad*)
EXPORT: nil
ASSERTION: free the memory of the 2D array stored inside the keypad struct.
----------------------------------------------------------------------------*/

void freeKeypadTable( CalcKeypad *keypad )
{
    int i;
    
    /* freeing the rows */
    for( i = 0; i < keypad->row; i++ )
    {
        free(keypad->table[i]);
    }
    /* freeing the whole table */
    free(keypad->table);
}



