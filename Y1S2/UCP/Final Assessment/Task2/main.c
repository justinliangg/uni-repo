/*---------------------------------------------------------------------------           
FILE: main.c                                                                    
AUTHOR: Justin Liang(19821986)                                                  
UNIT: COMP1000                                                                  
LAST MOD: 07/11/2021                                                            
PURPOSE: Validate command line arguments                                        
REQUIRES: calculator.c                                                                
---------------------------------------------------------------------------*/
#include "main.h"

int main( int argc, char **argv )
{
    if( argc == 2 )
    {  
        /* opening file for writing */
        char *fileName = argv[1];
        FILE *fpWrite = fopen( fileName, "w" );

        /* error check file first before entering calc*/
        if( fpWrite == NULL )
        {
            perror("Error opening file stream");
        }
        else
        {   
            startCalculator( fpWrite );
        }
    }
    else /* display usage */
    {
        printf("Invalid number of arguments!\n");
        printf("Usage: ./calculator <filename>\n");
    }

    return 0;
}
