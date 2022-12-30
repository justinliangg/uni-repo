#include "readLogFile.h"

#define FALSE 0
#define TRUE !FALSE

int main(int argc, char** argv)
{
    if(argc == 2)
    {
        FILE* fp = fopen(argv[1], "r");
        readFile(fp, argv[1]);
    }
    else
    {
        printf("please input file name");
    }
    return 0;
}


void readFile(FILE* fp, char* fileName)
{
    if(fp == NULL)
    {
        perror("error reading file");
    }
    else    
    {
        char* month,process,message;
        int day,hour,min,sec;
        int nRead;
        int done;

        month = (char*) malloc(sizeof(char) * 4);
        process = (char*) malloc(sizeof(char) * 20);
        message = (char*) malloc(sizeof(char) * 1000);
        
        done = FALSE;
        while(!done)
        {
            nRead = fscanf(fp,"%3s %d %d:%d:%d %19s", month, &day, &hour, &min, 
                           &sec, process);    

            if(nRead != 6 || fgets(message,1000,fp) == NULL)
            {
                done = TRUE;
            }
            else
            {   
                char* ret;
                int totalSec; 

                ret = strstr(message,"fail");
                if(ret != NULL)
                {
                    totalSec = (hour*60*60) + (min*60) + (sec);
                    printf("%d %s:", totalSec, message);
                }
            }
        }

        if(ferror(fp))
        {
            perror("Error when reading file");
        }
        
        fclose(fp);
        free(month);
        free(process);
        free(message);
    }
}
            


