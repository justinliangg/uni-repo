#include "copyFile.h"
#define FALSE 0
#define TRUE !FALSE

int main(int argc, char** argv)
{
    if(argc == 3)
    {
        FILE* fpRead = fopen(argv[1], "r");
        FILE* fpWrite = fopen(argv[2], "w");

        copyFile(fpRead, fpWrite, argv[1], argv[2]);
    }
    else
    {
        printf("Please input in this format : /main src.txt dest.txt");
    }
    return 0;
}

void copyFile(FILE* fpRead, FILE* fpWrite, char* srcFile, char* destFile)
{
    if(fpRead == NULL)
    {
        perror("Error reading from file");
    }
    else
    {
        int done;
        int character;
       
        done = FALSE;
        while(!done)
        {
            character = fgetc(fpRead);
            
            if(character == EOF)
            {
                done = TRUE;
            }
            else
            {
                fputc(character, fpWrite);
            }
        }

        if(ferror(fpRead) || ferror(fpWrite))
        {
            perror("Error copying file");
        }
        
        fclose(fpRead);
        fclose(fpWrite);

    }
}
