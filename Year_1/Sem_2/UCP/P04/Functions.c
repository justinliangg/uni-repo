#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Functions.h"

int sum(int* array, int length)
{
    int i;
    int ans = 0;
    
    for(i = 0; i < length; i++)
    {
        ans = ans + array[i];
    }

    return ans;
}
       
int max(int* array, int length)
{
    int i;
    int maxIdx = 0;
    
    for(i = 0; i< length; i++)
    {
        if(array[i] > array[maxIdx])
        {
            maxIdx = i;
        }
    }

    return maxIdx;
}

void reverse(int* array, int length)
{
    int i;
    int temp;

    for(i = 0; i < length/2; i++)
    {
        temp = array[length - 1 - i];
        array[length - 1 - i] = array[i];
        array[i] = temp;
    }
}

void stringToInt(char* string, int length, int* array)
{   
    int i = 0;
    while(string[i] != '\0')
    {
        /*converting string to int*/
        array[i] = (int)string[i] - '0';
        i++;
    }

}

void arrayOutput(int* array, int length)
{   
    int i;
    for(i = 0; i<length; i++)
    {
        if(i == 0)
        {   
            printf("{%d, ", array[i]);
        }
        else if(i == (length-1))
        {
            printf("%d}", array[i]);
        }
        else
        {
            printf("%d, ", array[i]);
        }
    }
}


