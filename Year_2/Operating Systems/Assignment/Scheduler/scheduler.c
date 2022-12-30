/*---------------------------------------------------------------------------
FILE: scheduler.c
AUTHOR: Justin Liang(19821986)
UNIT: COMP2006
LAST MOD: 09/05/2022
PURPOSE: contains functions for the different disk scheduling algorithms.
REQUIRES: FileIO.c
----------------------------------------------------------------------------*/

#include "scheduler.h"

int main()
{      
    int done = FALSE;
    while(!done)
    {
        char fileName[11];

        /*Getting file name*/
        printf("Disk Scheduler Simulation: ");
        scanf("%10s", fileName); /*assume that file name is 10 chars long.*/
        
        /* Processing file and calling the various scheduling algorithms*/
        if(strcmp(fileName, "QUIT") != 0) //user does not want to quit.
        {   
            int* inputData = NULL;
            int arraySize;
        
            //processing file and storing into inputData and arraySize.
            if(processFile(&inputData, &arraySize, fileName) == TRUE)
            {
                /* running the scheduling algorithms */
                printf("FCFS: %d\n", FCFS(inputData, arraySize));
                printf("SSTF: %d\n", SSTF(inputData, arraySize));
                printf("SCAN: %d\n", SCAN(inputData, arraySize));
                printf("C_SCAN: %d\n", C_SCAN(inputData, arraySize));
                printf("LOOK: %d\n", LOOK(inputData, arraySize));
                printf("C_LOOK: %d\n", C_LOOK(inputData, arraySize));
            }

            free(inputData);
        }
        else
        {
            done = TRUE;
        }
    }

    return 0;
}



/*--------------------------------------------------------------------------
SUBMODULE: processFile
IMPORT: inputData(int**), int* arraySize 
EXPORT: success(int)
ASSERTION: initialises inputData array and arraySize and then returns
true if file was read and stored into array successfully.
--------------------------------------------------------------------------*/

int processFile(int** inputData, int* arraySize, char* fileName)
{   
    int success = TRUE;

    FILE* fp = fopen(fileName, "r");
    if(fp != NULL)
    {
        *arraySize = getArraySize(fp); //getting array size.
        if(ferror(fp))
        {   
            perror("Error reading in values from data");
            success = FALSE;
        }
        else //running if getArraySize was run successfully.
        {   
            //filling the inputData array from the input file.
            *inputData = (int*) calloc(*arraySize, sizeof(int));
            readFile(*inputData, *arraySize, fileName);
            fclose(fp);
        }
    }
    else
    {
        perror("Error Opening File");
        success = FALSE;
    }

    return success;
}



/*--------------------------------------------------------------------------
SUBMODULE: FCFS
IMPORT: inputData(int*), arraySize(int)
EXPORT: totalSeekTime(int)
ASSERTION: computes the total seek time taken for First Come First Serve
scheduling algorithm
--------------------------------------------------------------------------*/

int FCFS(int* inputData, int arraySize)
{   
    int head = inputData[HEAD_START];
    int totalSeekTime = 0;
    
    for(int i = REQUEST_START; i < arraySize; i++)
    {   
        int seekTime = abs(head - inputData[i]);
        head = inputData[i];
        totalSeekTime = totalSeekTime + seekTime;
    }

    return totalSeekTime;
}



/*--------------------------------------------------------------------------
SUBMODULE: SSTF
IMPORT: inputData(int*), arraySize(int)
EXPORT: totalSeekTime(int)
ASSERTION: computes the total seek time taken for Shortest Seek Time First
scheduling algorithm
--------------------------------------------------------------------------*/

int SSTF(int* inputData, int arraySize)
{   
    /*creating copy of inputData as copy will be modified.*/
    int* copy = createIntArrayCopy(inputData, arraySize); 
    
    int head = inputData[HEAD_START];
    for(int i = REQUEST_START; i < arraySize; i++)
    {   
        //finding the index of the smallest seekTime from current head.
        int next = i;
        int minSeekTime = INT_MAX;
        int minIndex;
        while(next < arraySize)
        {   
            int nextCylinder = copy[next];
            int seekTime = abs(head - nextCylinder);
            if(seekTime < minSeekTime) /*found a smaller seekTime from head*/
            {   
                minSeekTime = seekTime;
                minIndex = next;
            }
            next++;
        }

        //swapping closest cylinder from head with position i;
        int temp = copy[i];
        copy[i] = copy[minIndex];
        copy[minIndex] = temp;
        head = copy[i]; //setting the next head
    }  
    
    /*now copy array should be in arranged with SSTF for the requests, so
    can just call FCFS again */
    int totalSeekTime = FCFS(copy, arraySize);
    free(copy);

    return totalSeekTime;
}



/*--------------------------------------------------------------------------
SUBMODULE: SCAN
IMPORT: inputData(int*), arraySize(int)
EXPORT: totalSeekTime(int)
ASSERTION: computes the total seek time taken for the SCAN disk scheduling algorithm
COMMENT: contains code obtained from 
https://www.easycodingzone.com/2021/07/c-program-of-scan-or-elevator-disk.html
--------------------------------------------------------------------------*/

int SCAN(int* inputData, int arraySize)
{   
    int* copy = createIntArrayCopy(inputData, arraySize);
    int head = inputData[HEAD_START];
    int prevRequest = inputData[PREV_REQUEST]; 
    int diskSize = inputData[DISK_SIZE];

    //determine which direction to traverse first.
    int direction = UP;
    if((head - prevRequest) < 0) //coming from a higher point.
    {
        direction = DOWN; 
    }

    /*Start of code obtained from 
    https://www.easycodingzone.com/2021/07/c-program-of-scan-or-elevator-disk.html
    */

    //sorting requests in copy into ascending order.
    sortRequests(copy, arraySize);

    //finding the index for the point where head is inbetween two values.
    int index = findMidPointIndex(copy, arraySize);
    
    /*End of code obtained */

    int totalSeekTime = 0;
    int seekTime; 
    if(direction == UP)
    {      
        //going up first
        for(int i = index; i < arraySize; i++)
        {
            seekTime = abs(head - copy[i]);
            head = copy[i];
            totalSeekTime = totalSeekTime + seekTime;
        }

        //going to topmost cylinder.
        totalSeekTime = totalSeekTime + abs(head - (diskSize - 1));
        head = diskSize - 1;

        //going down 
        for(int i = index - 1; i >= REQUEST_START; i--)
        {
            seekTime = abs(head - copy[i]);
            head = copy[i];
            totalSeekTime = totalSeekTime + seekTime;
        }
    }
    else
    {
        //going down first
        for(int i = index - 1; i >= REQUEST_START; i--)
        {
            int seekTime = abs(head - copy[i]);
            head = copy[i];
            totalSeekTime = totalSeekTime + seekTime;
        }

        //going to last cylinder.
        seekTime = abs(head - 0);
        head = 0;
        totalSeekTime = totalSeekTime + seekTime;

        //going up
        for(int i = index; i < arraySize; i++)
        {
            int seekTime = abs(head - copy[i]);
            head = copy[i];
            totalSeekTime = totalSeekTime + seekTime;
        }
    }

    free(copy);
    return totalSeekTime;
}



/*--------------------------------------------------------------------------
SUBMODULE: C_SCAN
IMPORT: inputData(int*), arraySize(int)
EXPORT: totalSeekTime(int)
ASSERTION: computes the total seek time taken for C_SCAN disk scheduling 
algorithm.
--------------------------------------------------------------------------*/

int C_SCAN(int* inputData, int arraySize)
{
    int* copy = createIntArrayCopy(inputData, arraySize);
    int head = inputData[HEAD_START];
    int prevRequest = inputData[PREV_REQUEST]; 
    int diskSize = inputData[DISK_SIZE];

    //determine which direction to traverse first.
    int direction = UP;
    if((head - prevRequest) < 0) //coming from a higher point.
    {
        direction = DOWN; 
    }

    //sorting requests in copy into ascending order.
    sortRequests(copy, arraySize);

    //finding the index for the point where head is inbetween two values.
    int index = findMidPointIndex(copy, arraySize);

    int totalSeekTime = 0;
    int seekTime;
    if(direction == UP)
    {      
        //going up first
        for(int i = index; i < arraySize; i++)
        {
            seekTime = abs(head - copy[i]);
            head = copy[i];
            totalSeekTime = totalSeekTime + seekTime;
        }

        //going to topmost cylinder then back down to last cylinder.
        totalSeekTime = totalSeekTime + (diskSize - head - 1) + diskSize - 1;
        head = 0;

        //going down 
        for(int i = REQUEST_START; i < index; i++)
        {
            seekTime = abs(head - copy[i]);
            head = copy[i];
            totalSeekTime = totalSeekTime + seekTime;
        }
    }
    else
    {   
        //going down first
        for(int i = index - 1; i >= REQUEST_START; i--)
        {
            int seekTime = abs(head - copy[i]);
            head = copy[i];
            totalSeekTime = totalSeekTime + seekTime;
        }

        //going to last cylinder and then to topmost .
        totalSeekTime = totalSeekTime + (head - 0) + diskSize - 1;
        head = diskSize - 1;

        //going up
        for(int i = arraySize - 1; i >= index; i--)
        {   
            int seekTime = abs(head - copy[i]);
            head = copy[i];
            totalSeekTime = totalSeekTime + seekTime;
        }

    }

    free(copy);
    return totalSeekTime;
}



/*--------------------------------------------------------------------------
SUBMODULE: LOOK
IMPORT: inputData(int*), arraySize(int)
EXPORT: totalSeekTime(int)
ASSERTION: computes the total seek time taken for LOOK disk scheduling 
algorithm
--------------------------------------------------------------------------*/

int LOOK(int* inputData, int arraySize)
{
    int* copy = createIntArrayCopy(inputData, arraySize);
    int head = inputData[HEAD_START];
    int prevRequest = inputData[PREV_REQUEST]; 

    //determine which direction to traverse first.
    int direction = UP;
    if((head - prevRequest) < 0) //coming from a higher point.
    {
        direction = DOWN; 
    }

    //sorting requests in copy into ascending order.
    sortRequests(copy, arraySize);

    //finding the index for the point where head is inbetween two values.
    int index = findMidPointIndex(copy, arraySize);

    int totalSeekTime = 0;
    int seekTime;
    if(direction == UP)
    {      
        //going up first
        for(int i = index; i < arraySize; i++)
        {
            seekTime = abs(head - copy[i]);
            head = copy[i];
            totalSeekTime = totalSeekTime + seekTime;
        }

        //going down 
        for(int i = index - 1; i >= REQUEST_START; i--)
        {
            seekTime = abs(head - copy[i]);
            head = copy[i];
            totalSeekTime = totalSeekTime + seekTime;
        }
    }
    else
    {
        //going down first
        for(int i = index - 1; i >= REQUEST_START; i--)
        {
            int seekTime = abs(head - copy[i]);
            head = copy[i];
            totalSeekTime = totalSeekTime + seekTime;
        }

        //going up
        for(int i = index; i < arraySize; i++)
        {
            int seekTime = abs(head - copy[i]);
            head = copy[i];
            totalSeekTime = totalSeekTime + seekTime;
        }

    }

    free(copy);
    return totalSeekTime;
}



/*--------------------------------------------------------------------------
SUBMODULE: C_LOOK
IMPORT: inputData(int*), arraySize(int)
EXPORT: totalSeekTime(int)
ASSERTION: computes the total seek time taken for C_LOOK disk scheduling 
algorithm
--------------------------------------------------------------------------*/

int C_LOOK(int* inputData, int arraySize)
{
    int* copy = createIntArrayCopy(inputData, arraySize);
    int head = inputData[HEAD_START];
    int prevRequest = inputData[PREV_REQUEST]; 

    //determine which direction to traverse first.
    int direction = UP;
    if((head - prevRequest) < 0)
    {
        direction = DOWN; 
    }

    //sorting disk requests in copy into ascending order.
    sortRequests(copy, arraySize);

    //finding the index for the point where head is inbetween two values.
    int index = findMidPointIndex(copy, arraySize);

    int totalSeekTime = 0;
    int seekTime;
    if(direction == UP)
    {      
        //going up first
        for(int i = index; i < arraySize; i++)
        {
            seekTime = abs(head - copy[i]);
            head = copy[i];
            totalSeekTime = totalSeekTime + seekTime;
        }

        //going down 
        for(int i = REQUEST_START; i < index; i++)
        {
            seekTime = abs(head - copy[i]);
            head = copy[i];
            totalSeekTime = totalSeekTime + seekTime;
        }
    }
    else
    {   
        //going down first
        for(int i = index - 1; i >= REQUEST_START; i--)
        {
            int seekTime = abs(head - copy[i]);
            head = copy[i];
            totalSeekTime = totalSeekTime + seekTime;
        }

        //going up
        for(int i = arraySize - 1; i >= index; i--)
        {   
            int seekTime = abs(head - copy[i]);
            head = copy[i];
            totalSeekTime = totalSeekTime + seekTime;
        }

    }

    free(copy);
    return totalSeekTime;
}



/*--------------------------------------------------------------------------
SUBMODULE: createIntArrayCopy
IMPORT: array(int*), arraySize(int)
EXPORT: copyArray(int*)
ASSERTION: takes in a array and creates a copy and returns the reference.
--------------------------------------------------------------------------*/

int* createIntArrayCopy(int* array, int arraySize)
{   
    int* copyArray = (int*) calloc(arraySize, sizeof(int));

    int i;
    for(i = 0; i < arraySize; i++)
    {
        copyArray[i] = array[i];
    }

    return copyArray;
}



/*--------------------------------------------------------------------------
SUBMODULE: sortRequests
IMPORT: inputData(int*), arraySize(int)
EXPORT: NIL
ASSERTION: takes in an inputData and sorts the disk requests in ascending order.
--------------------------------------------------------------------------*/

void sortRequests(int* inputData, int arraySize)
{
    for(int i = REQUEST_START; i < arraySize; i++)
    {   
        int minIndex = i;
        int minValue = inputData[i];

        //finding smallest value from i to arraySize;
        int j = i + 1;
        while(j < arraySize)
        {
            if(inputData[j] < minValue)
            {
                minIndex = j;
                minValue = inputData[j];
            }
            j++;
        }

        //swap the smallest value with i;
        int temp = inputData[i];
        inputData[i] = inputData[minIndex];
        inputData[minIndex] = temp;
    }
}



/*--------------------------------------------------------------------------
SUBMODULE: findMidPointIndex
IMPORT: sortedInputData(int*), arraySize(int)
EXPORT: index(int)
ASSERTION: finds the index of the mid point where head will be inbetween two 
values. Everything after index in the sortedInputData will be greater than 
the head and everything below will be smaller than head. 
--------------------------------------------------------------------------*/

int findMidPointIndex(int* sortedInputData, int arraySize)
{
    int head = sortedInputData[HEAD_START];
    int index = REQUEST_START - 1;
    int found = FALSE;
    while(!found && index < arraySize - 1)
    {       
        index++;
        if(sortedInputData[index] >= head)
        {
            found = TRUE;
        }
    }

    return index;
}

