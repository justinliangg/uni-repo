#include "readjournal.h"

int main(int argc, char** argv)
{
    if(argc == 3)
    {   
        int index;
        FILE* fp;
        LinkedList* list = createLinkedList();

        index = atoi(argv[2]);
        fp = fopen(argv[1], "r");

        fileRead(fp,list,index);
    }

    return 0;
}

void fileRead(FILE* fp, LinkedList* list, int index)
{   
    if(fp == NULL)
    {
        perror("Error reading in file");
    }
    else
    {   
        int numElements = 0;
        int done = FALSE;
        /*Filling the struct entries with data from file*/
        while(!done)
        {   
            int day;
            int month;
            int year;
            char text[101];
            int nRead;
            char* textCheck = NULL;

            /*retrieving value and put into struct*/
            nRead = fscanf(fp,"%d/%d/%d\n", &day, &month, &year);
            textCheck = fgets(text, 101, fp);

            if( nRead != 3 || textCheck == NULL)
            { 
                done = TRUE;
            }
            else
            {   
                Entries* data = malloc(sizeof(Entries)); 
                data->day = day;
                data->month = month; 
                data->year = year; 
                strcpy(data->text, text);

                /*Insert into linked list*/
                insertLast(list, data);
                numElements++;
            }          

        }
        
        if(ferror(fp))
        {
            perror("Error reading in values from data");
        }

        fclose(fp);

        /*Validating that index is less than array length -1*/
        if(index > (numElements-1) || index < 0)
        {
            printf("Invalid index input");
        }
        else
        {
            void* data = getData(list, index);
            printEntries(data);
        }
        
        freeLinkedList(list, &freeEntries);
    }
}

void printEntries(void* data)
{
    Entries* entry = (Entries*) data;
    printf("%d-%02d-%02d: %s", entry->year, entry->month, entry->day, entry->text);
}

void freeEntries(void* data)
{   
    free(data);
}

    
