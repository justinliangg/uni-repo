
#include "display.h"

int main(int argc, char** argv)
{
    if(argc == 2)
    {
        FILE* fp;
        if(strcmp(argv[1], "-") == 0)
        {
            fp = stdin;
        }
        else
        {
            fp = fopen(argv[1], "r");
        }
        readFile(fp, argv[1]);
    }
    else
    {
        printf("Please input file name");
        printf("format ./display fileName");
    }

    return 0;
}


void readFile(FILE* fp, char* fileName)
{
    /*Checking if FILE open successful*/
    if(fp == NULL)
    {
        perror("Unable to open file");
    }
    else
    {   
        /*Variables for array*/
        int row;
        int column;
        double** array = NULL;
        /* Loop indexes */
        int i;
        int j;

        /*allocating memory for array*/
        array = makeArray(fp, &row, &column);
        
        /*reading rest of values in file and store into array.*/
        for(i = 0; i < row; i++)
        {
            for(j = 0; j < column; j++)
            {
                fscanf(fp, "%lf", &array[i][j]);
            }
        }

        if(ferror(fp))
        {
            perror("Error reading values from file");
        }

        plot(array, row, column);

        freeArray(array, row, column);
        fclose(fp);
    }
}



double** makeArray(FILE* fp, int* row, int* column)
{   
    int i;
    double** array = NULL;

    /*getting row and columns from file*/
    fscanf(fp, "%d %d", row, column);

    /*allocating memory for array*/
    array = (double**) malloc(sizeof(double*) * (*row));
    for(i = 0; i < (*row); i++)
    {   
        array[i] = (double*) malloc(sizeof(double) * (*column));
    }
    
    return array;
}



void freeArray(double** array, int row, int column)
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
    
