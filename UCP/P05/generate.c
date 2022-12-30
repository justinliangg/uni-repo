#include "generate.h"

int main(int argc, char** argv)
{
    if(argc == 4)
    {
        int row, column;
        char* fileName;
        double** array;
        FILE* fp;
        
        if(checkArguments(argv[2],argv[3]))
        {
            fileName = argv[1];
            row = atoi(argv[2]); 
            column = atoi(argv[3]);
            
            /*Making and filling array*/
            array = makeArray(row, column);
            randomArray(array, row, column, 3);
            
            /*Writing to file*/
            if(strcmp(fileName,"-") == 0) /*Checking if filename is -*/
            {
                writeToFile(stdout, array, row, column);
            }
            else
            {
                fp = fopen(fileName, "w");
                writeToFile(fp, array, row, column);
            }

            /*Free memory allocations*/
            freeArray(array,row);
        }
        else
        {
            printf("please enter a valid rows and column");
        }
    }
        
    else
    {
        printf("Please enter correct number of arguments");
    }
    return 0;
}


double** makeArray(int row, int column)
{   
    int i;
    double** array = NULL;

    /*allocating memory for array*/
    array = (double**) malloc(sizeof(double*) * (row));
    for(i = 0; i < (row); i++)
    {   
        array[i] = (double*) malloc(sizeof(double) * (column));
    }
    
    return array;
}


int checkArguments(char* pRow, char* pColumn)
{
    int row, column;
    int isValid;

    row = atoi(pRow);
    column = atoi(pColumn);
    
    isValid = FALSE;
    if(row > 0 && column > 0)
    {
        isValid = TRUE;
    }

    return isValid;

}

void writeToFile(FILE* fp, double** array, int row, int column)
{
    if(fp == NULL)
    {
        perror("failed opening stream to file");
    }
    else
    {   
        int i;
        int j;

        /*put rows and column at the first row in file first*/
        fprintf(fp, "%d %d \n", row, column);

        for(i = 0; i < row; i++)
        {
            for(j = 0; j < column; j++)
            {
                fprintf(fp,"%f ", array[i][j]);
            }
            
            /*new row in file after putting all the columns*/
            fprintf(fp,"\n");
        }
    }
    fclose(fp);
}

void freeArray(double** array, int row)
{   
    int i;
    for(i = 0; i < row; i++)
    {       
        free(array[i]);
        array[i] = NULL;
    }
    free(array);
    array = NULL;
}

            


        


    
