/*---------------------------------------------------------------------------
FILE: simulator.c
AUTHOR: Justin Liang(19821986)
UNIT: COMP2006
LAST MOD: 09/05/2022
PURPOSE: uses multithreading to run the different scheduling algorithms, based
on text file read from user input.
REQUIRES: FileIO.c
----------------------------------------------------------------------------*/
#include "simulator.h"

int arraySize;

int exitCondition = TRUE;

pthread_mutex_t inputLock = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t inputCond = PTHREAD_COND_INITIALIZER;

int* buffer1 = NULL; 
pthread_mutex_t buffer1Lock = PTHREAD_MUTEX_INITIALIZER; 
pthread_cond_t buffer1Cond = PTHREAD_COND_INITIALIZER;

int buffer2 = -1;
pthread_mutex_t buffer2Lock = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t buffer2Empty = PTHREAD_COND_INITIALIZER;
pthread_cond_t buffer2Full = PTHREAD_COND_INITIALIZER;

int main()
{   
    //creating new threads.
    pthread_t threads[6];
    threadFunction threadFuncs[6] = {&A, &B, &C, &D, &E, &F};
    for(int i = 0; i < 6; i++)
    {   
        pthread_create(&threads[i], NULL, threadFuncs[i], NULL);
    }

    int done = FALSE;
    while(!done)
    {  
        /*Getting file name*/
        char fileName[11];
        printf("\nDisk Scheduler Simulation: ");
        scanf("%10s", fileName); /*assume that file name is 10 chars long.*/
        
        //processing file and storing into buffer1 and arraySize.
        if(strcmp(fileName, "QUIT") != 0) //user does not want to quit.
        {  
            //release threads waiting on next input.
            pthread_mutex_lock(&inputLock);
            exitCondition = FALSE;
            pthread_cond_broadcast(&inputCond);
            pthread_mutex_unlock(&inputLock);

            //producing for buffer1
            pthread_mutex_lock(&buffer1Lock);
            //processFile return FALSE if failed, hence break out of loop.
            if(!processFile(&buffer1, &arraySize, fileName)){ break; };
            pthread_cond_broadcast(&buffer1Cond);
            pthread_mutex_unlock(&buffer1Lock);

            //consuming from buffer2
            pthread_mutex_lock(&buffer2Lock); 
            for(int i = 0; i< 6; i++)
            {     
                if(buffer2 == -1)
                {
                    pthread_cond_wait(&buffer2Full, &buffer2Lock);
                }
                
                printf("%d\n", buffer2);
                buffer2 = -1;

                pthread_cond_signal(&buffer2Empty);
            }
            pthread_mutex_unlock(&buffer2Lock);

            free(buffer1);
            buffer1 = NULL;
        }
        else
        {   
            //signalling threads to exit.
            pthread_mutex_lock(&inputLock);
            exitCondition = TRUE;
            pthread_cond_broadcast(&inputCond);
            pthread_mutex_unlock(&inputLock);

            done = TRUE; 
        } 
    }
    
    //terminating all the threads.
    for(int i = 0; i < 6; i++)
    {   
        pthread_cancel(threads[i]);
    }

    return 0;
}


/*--------------------------------------------------------------------------
SUBMODULE: processFile
IMPORT: buffer1(int**), int* arraySize 
EXPORT: success(int)
ASSERTION: initialises buffer1 array and arraySize and then returns
TRUE if file was read and stored into array successfully.
--------------------------------------------------------------------------*/

int processFile(int** buffer1, int* arraySize, char* fileName)
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
            //filling the buffer1 array from the input file.
            *buffer1 = (int*) calloc(*arraySize, sizeof(int));
            readFile(*buffer1, *arraySize, fileName);
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
SUBMODULE: A
IMPORT: void* value
EXPORT: void* 
ASSERTION: Thread A, runs the First Come First Serve scheduling algorithm
--------------------------------------------------------------------------*/

void* A(void* value)
{   
    //waiting for next input.
    pthread_mutex_lock(&inputLock);
    pthread_cond_wait(&inputCond, &inputLock);
    pthread_mutex_unlock(&inputLock);

    while(!exitCondition)
    {   
        //waiting on buffer1 to be filled.
        pthread_mutex_lock(&buffer1Lock);
        if(buffer1 == NULL)
        {   
            pthread_cond_wait(&buffer1Cond, &buffer1Lock);  
        }
        pthread_mutex_unlock(&buffer1Lock);

        //running algorithm.
        int totalSeekTime = FCFS(buffer1, arraySize);

        //writing for parent thread to read.
        pthread_mutex_lock(&buffer2Lock);
        while(buffer2 != -1)
        {
            pthread_cond_wait(&buffer2Empty, &buffer2Lock);
        }
        buffer2 = totalSeekTime;
        printf("FCFS: ");

        pthread_cond_signal(&buffer2Full);
        pthread_mutex_unlock(&buffer2Lock);

        //waiting for next input.
        pthread_mutex_lock(&inputLock);
        pthread_cond_wait(&inputCond, &inputLock);
        pthread_mutex_unlock(&inputLock);
    }

    printf("%ld has terminated\n", pthread_self());

    return NULL;

}



/*--------------------------------------------------------------------------
SUBMODULE: B
IMPORT: void* value
EXPORT: void* 
ASSERTION: Thread B, runs the Shortest Seek Time First scheduling algorithm
--------------------------------------------------------------------------*/

void* B(void* value)
{   
    //waiting for next input.
    pthread_mutex_lock(&inputLock);
    pthread_cond_wait(&inputCond, &inputLock);
    pthread_mutex_unlock(&inputLock);

    while(!exitCondition)
    {
        //waiting on buffer1 to be filled.
        pthread_mutex_lock(&buffer1Lock);
        if(buffer1 == NULL)
        {  
            pthread_cond_wait(&buffer1Cond, &buffer1Lock);  
        }
        pthread_mutex_unlock(&buffer1Lock);

        //running algorithm.
        int totalSeekTime = SSTF(buffer1, arraySize);

        //writing for parent thread to read.
        pthread_mutex_lock(&buffer2Lock);
        while(buffer2 != -1)
        {
            pthread_cond_wait(&buffer2Empty, &buffer2Lock);
        }
        buffer2 = totalSeekTime;
        printf("SSTF: ");

        pthread_cond_signal(&buffer2Full);
        pthread_mutex_unlock(&buffer2Lock);

        //waiting for next input.
        pthread_mutex_lock(&inputLock);
        pthread_cond_wait(&inputCond, &inputLock);
        pthread_mutex_unlock(&inputLock);
    }

    printf("%ld has terminated\n", pthread_self());

    return NULL;    
}



/*--------------------------------------------------------------------------
SUBMODULE: C
IMPORT: void* value
EXPORT: void* 
ASSERTION: Thread C, runs the SCAN scheduling algorithm
--------------------------------------------------------------------------*/

void* C(void* value)
{   
    //waiting for next input.
    pthread_mutex_lock(&inputLock);
    pthread_cond_wait(&inputCond, &inputLock);
    pthread_mutex_unlock(&inputLock);

    while(!exitCondition)
    {
        //waiting on buffer1 to be filled.
        pthread_mutex_lock(&buffer1Lock);
        if(buffer1 == NULL)
        {   
            pthread_cond_wait(&buffer1Cond, &buffer1Lock);  
        }
        pthread_mutex_unlock(&buffer1Lock);

        //running algorithm.
        int totalSeekTime = SCAN(buffer1, arraySize);   

        //writing for parent thread to read.
        pthread_mutex_lock(&buffer2Lock);
        while(buffer2 != -1)
        {
            pthread_cond_wait(&buffer2Empty, &buffer2Lock);
        }
        buffer2 = totalSeekTime;
        printf("SCAN: ");

        pthread_cond_signal(&buffer2Full);
        pthread_mutex_unlock(&buffer2Lock);

        //waiting for next input.
        pthread_mutex_lock(&inputLock);
        pthread_cond_wait(&inputCond, &inputLock);
        pthread_mutex_unlock(&inputLock);
    }

    printf("%ld has terminated\n", pthread_self());

    return NULL;
}



/*--------------------------------------------------------------------------
SUBMODULE: D
IMPORT: void* value
EXPORT: void* 
ASSERTION: Thread D, runs the C_SCAN scheduling algorithm
--------------------------------------------------------------------------*/

void* D(void* value)
{
    //waiting for next input.
    pthread_mutex_lock(&inputLock);
    pthread_cond_wait(&inputCond, &inputLock);
    pthread_mutex_unlock(&inputLock);

    while(!exitCondition)
    {
        //waiting on buffer1 to be filled.
        pthread_mutex_lock(&buffer1Lock);
        if(buffer1 == NULL)
        {   
            pthread_cond_wait(&buffer1Cond, &buffer1Lock);  
        }
        pthread_mutex_unlock(&buffer1Lock);

        //running algorithm.
        int totalSeekTime = C_SCAN(buffer1, arraySize);
        
        //writing for parent thread to read.
        pthread_mutex_lock(&buffer2Lock);
        while(buffer2 != -1)
        {
            pthread_cond_wait(&buffer2Empty, &buffer2Lock);
        }
        buffer2 = totalSeekTime;
        printf("C_SCAN: ");

        pthread_cond_signal(&buffer2Full);
        pthread_mutex_unlock(&buffer2Lock);

        //waiting for next input.
        pthread_mutex_lock(&inputLock);
        pthread_cond_wait(&inputCond, &inputLock);
        pthread_mutex_unlock(&inputLock);
    }

    printf("%ld has terminated\n", pthread_self());

    return NULL;
}



/*--------------------------------------------------------------------------
SUBMODULE: E
IMPORT: void* value
EXPORT: void* 
ASSERTION: Thread E, runs the LOOK scheduling algorithm
--------------------------------------------------------------------------*/

void* E(void* value)
{   
    //waiting for next input.
    pthread_mutex_lock(&inputLock);
    pthread_cond_wait(&inputCond, &inputLock);
    pthread_mutex_unlock(&inputLock);

    while(!exitCondition)
    {
        //waiting on buffer1 to be filled.
        pthread_mutex_lock(&buffer1Lock);
        if(buffer1 == NULL)
        {   
            pthread_cond_wait(&buffer1Cond, &buffer1Lock);  
        }
        pthread_mutex_unlock(&buffer1Lock);

        //running algorithm.
        int totalSeekTime = LOOK(buffer1, arraySize);
        
        //writing for parent thread to read.
        pthread_mutex_lock(&buffer2Lock);
        while(buffer2 != -1)
        {
            pthread_cond_wait(&buffer2Empty, &buffer2Lock);
        }
        buffer2 = totalSeekTime;
        printf("LOOK: ");

        pthread_cond_signal(&buffer2Full);
        pthread_mutex_unlock(&buffer2Lock);

        //waiting for next input.
        pthread_mutex_lock(&inputLock);
        pthread_cond_wait(&inputCond, &inputLock);
        pthread_mutex_unlock(&inputLock);
    }

    printf("%ld has terminated\n", pthread_self());

    return NULL;
}



/*--------------------------------------------------------------------------
SUBMODULE: F
IMPORT: void* value
EXPORT: void* 
ASSERTION: Thread F, runs the C_LOOK scheduling algorithm
--------------------------------------------------------------------------*/
void* F(void* value)
{   
    //waiting for next input.
    pthread_mutex_lock(&inputLock);
    pthread_cond_wait(&inputCond, &inputLock);
    pthread_mutex_unlock(&inputLock);

    while(!exitCondition)
    {
        //waiting on buffer1 to be filled.
        pthread_mutex_lock(&buffer1Lock);
        if(buffer1 == NULL)
        {   
            pthread_cond_wait(&buffer1Cond, &buffer1Lock);  
        }
        pthread_mutex_unlock(&buffer1Lock);

        //running algorithm.
        int totalSeekTime = C_LOOK(buffer1, arraySize);

        //writing for parent thread to read.
        pthread_mutex_lock(&buffer2Lock);
        while(buffer2 != -1)
        {
            pthread_cond_wait(&buffer2Empty, &buffer2Lock);
        }
        buffer2 = totalSeekTime;
        printf("C_LOOK: ");

        pthread_cond_signal(&buffer2Full);
        pthread_mutex_unlock(&buffer2Lock);
        

        //waiting for next input.
        pthread_mutex_lock(&inputLock);
        pthread_cond_wait(&inputCond, &inputLock);
        pthread_mutex_unlock(&inputLock);
    }

    printf("%ld has terminated\n", pthread_self());

    return NULL;
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
IMPORT: buffer1(int*), arraySize(int)
EXPORT: NIL
ASSERTION: takes in an buffer1 and sorts the disk requests in ascending order.
--------------------------------------------------------------------------*/

void sortRequests(int* buffer1, int arraySize)
{
    for(int i = REQUEST_START; i < arraySize; i++)
    {   
        int minIndex = i;
        int minValue = buffer1[i];

        //finding smallest value from i to arraySize;
        int j = i + 1;
        while(j < arraySize)
        {
            if(buffer1[j] < minValue)
            {
                minIndex = j;
                minValue = buffer1[j];
            }
            j++;
        }

        //swap the smallest value with i;
        int temp = buffer1[i];
        buffer1[i] = buffer1[minIndex];
        buffer1[minIndex] = temp;
    }
}



/*--------------------------------------------------------------------------
SUBMODULE: findMidPointIndex
IMPORT: sortedbuffer1(int*), arraySize(int)
EXPORT: index(int)
ASSERTION: finds the index of the mid point where head will be inbetween two 
values. Everything after index in the sortedbuffer1 will be greater than 
the head and everything below will be smaller than head. 
--------------------------------------------------------------------------*/

int findMidPointIndex(int* sortedbuffer1, int arraySize)
{
    int head = sortedbuffer1[HEAD_START];
    int index = REQUEST_START - 1;
    int found = FALSE;
    while(!found && index < arraySize - 1)
    {       
        index++;
        if(sortedbuffer1[index] >= head)
        {
            found = TRUE;
        }
    }

    return index;
}

