#include "Functions.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Main.h"

int main(int argc, char** argv)
{
    /*Checking no of command line arguments*/
    if(argc == 3)
    {   
        /*Allocating memory for int array*/
        int arrLength = strlen(argv[2]);
        int* array = (int*) malloc(arrLength * sizeof(int));
        
        /*Converting argv[1] to upper case*/
        toStringUpper(argv[1]);

        /*Checking first arguments is equal to sum, max or reverse*/
        if(checkArgument(argv[1]) == TRUE)
        {
            performArgument(argv, array, arrLength);
            free(array);
            array = NULL;
        }   
        else
        {
            usage();
        }
    }
    else
    {
        usage();
    }

    return 0;
}


void usage()
{   
    printf("./main x y\n");
    printf("x: sum, max or reverse\n");
    printf("y: integer eg.(123454)\n");
}

int checkArgument(char* string)
{
    int equals = FALSE;

    if(strcmp(string,"SUM") == 0)
    {
        equals = TRUE;
    }
    else if(strcmp(string,"MAX") == 0)
    {
        equals = TRUE;
    }
    else if(strcmp(string,"REVERSE") == 0)
    {
        equals = TRUE;
    }
     
    return equals; 
}

void performArgument(char** arrOfString, int* array, int length)
{   
    int ans;
    /*converting command line argument to int array*/
    stringToInt(arrOfString[2], length, array);
    
    /*execute function based on argv[1]*/
    if(strcmp(arrOfString[1],"SUM") == 0)
    {   
        ans = sum(array, length);
        printf("Answer: %d\n", ans); 
    }
    else if(strcmp(arrOfString[1],"MAX") == 0)
    {   
        ans = max(array, length);
        printf("Answer: %d\n", ans);
    }
    else
    {
        reverse(array, length);
        arrayOutput(array, length);
        printf("\n");
    }
}

void toStringUpper(char* string)
{
    int strLength = 0; 
    int i;
    
    /* a)
    strLength = strlen(string);*/
    
    while(string[strLength] != '\0')
    {
        strLength++;
    }

    for(i = 0; i<strLength; i++)
    {
        if(*(string + i) <= 122 && *(string + i) >= 97)
        {
            *(string + i) = *(string + i) - 32;
        }
    }
}
        
        
        



