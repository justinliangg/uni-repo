/*---------------------------------------
FILE: main.c
AUTHOR: Justin Liang(19821986)
UNIT: COMP1000
LAST MOD: 10/09/2021
PURPOSE: Validate command line arguments
REQUIRES: game.c 
----------------------------------------*/
#include "main.h"

int main( int argc, char **argv )
{   
    /* Check number of arguments */
    if( argc == 2 ) 
    {   
        /*opening file*/
        char *fileName = argv[1];   
        FILE *fp = fopen( fileName, "r" );  

        /* Checking file first before starting game */
        if(fp == NULL)
        {
            perror("Error opening file");
        }
        else
        {
            startGame( fp );
        }
    }
    else
    {   
        printf("Invalid number of arguments!\n");
        printf("Usage: ./maze <map_file_name>\n");
    }

    return 0;
}
