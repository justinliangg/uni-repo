/*---------------------------------------
FILE: main.c
AUTHOR: Justin Liang(19821986)
UNIT: COMP1000
LAST MOD: 10/09/2021
PURPOSE: Validate command line arguments
and perform based on input.
REQUIRES: game.c 
----------------------------------------*/
#include "main.h"

int main(int argc, char **argv)
{
    #ifdef DARK
        /*Validating arguments*/
        if( argc == 2 )
        {   
            int darkRange = atoi(argv[1]);
            if( darkRange >= 0 )
            {
                startGame(darkRange);
            }
            else
            {
                printf("Visibility distance has to be greater or equal to 0\n");
            } 
        }
        else
        {
            printf("Please input visibility distance correctly\n" 
                   "Format ./maze x | x is visibility distance\n");
        }
    #else
        /*Just start game with full visibility if DARK is not defined*/
        startGame(0);

    #endif
    
    return 0;
}
