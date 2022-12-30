/*---------------------------------------------------------------------------
FILE: FileIO.c
AUTHOR: Justin Liang(19821986)
UNIT: COMP2006
LAST MOD: 09/05/2022
PURPOSE: functions used to read input data for scheduler.c
REQUIRES: NIL
----------------------------------------------------------------------------*/
#include "FileIO.h"


/*--------------------------------------------------------------------------
SUBMODULE: getArraySize
IMPORT: fp(FILE*)
EXPORT: arraySize(int)
ASSERTION: reads from the file and determines the size of the input and returns
it.
--------------------------------------------------------------------------*/

int getArraySize(FILE* fp)
{   
    int arraySize = 0;

    int num;
    int count = 0;
    int done = FALSE;
    while(!done) 
    {   
        /*getting info from file*/
        int diskSize, nRead;
        nRead = fscanf(fp, "%d ", &num);
        if(nRead == 1)
        {   
            if(count == 0) /*first value is diskSize*/
            {
                diskSize = num;
                arraySize++;
            }
            else 
            {   
                if(num < diskSize && num >= 0) /*ignore invalid cylinders*/ 
                {
                    arraySize++;
                }
            }

            count++;
        }
        else /*reached end of file or error has occured*/
        {   
            done = TRUE;
        }
    }

    return arraySize;
}



/*--------------------------------------------------------------------------
SUBMODULE: readFile
IMPORT: array(int*), arraySize(int), fileName(char*)
EXPORT: nil
ASSERTION: reads from the file and stores in data from the file into the array.
--------------------------------------------------------------------------*/

void readFile(int* array, int arraySize, char* fileName)
{   
    FILE* fp = fopen(fileName, "r");

    int count = 0;
    int done = FALSE;
    while(!done) 
    {   
        /*getting info from file*/
        int diskSize, nRead, num;
        nRead = fscanf(fp, "%d ", &num);
        if(nRead == 1)
        {   
            if(count == 0) /*first value is diskSize*/
            {
                diskSize = num;
                array[count] = num;
                count++;
            }
            else 
            {   
                if(num < diskSize && num >= 0) /*ignore invalid cylinders*/ 
                {
                    array[count] = num;
                    count++;
                }
            }
        }
        else /*reached end of file or error has occured*/
        {   
            done = TRUE;
        }
    }

    /* Checking if file read was complete */
    if(ferror(fp))
    {
        perror("Error reading in values from data");
    }

    fclose(fp);
}
