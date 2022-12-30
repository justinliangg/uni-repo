#include <stdio.h>
#include "bounds.h"
#include "powers.h"

int main(void)
{   
    int input = 0;
    int i;

    printf("Please enter input number \n Input: ");
    scanf("%d", &input);
    while( !BETWEEN(input,31,1))
    {   
        scanf("%d", &input);
    }
        
    for(i = 0; i<input; i++)
    {
        powerOfTwo();
    }

    return 0;

}   


char getInput(void)
{
    char input = 0;
    scanf(" %c", &input);

    return input;
}

/*Checks if value is between the two ranges */
int checkBetweenRange(int value,int ub,int lb)
{   
    int isBetween = FALSE;
    if((lb <= value ) && (ub >= value))
    {
        isBetween = TRUE;
    }

    return isBetween;
}

int checkBetweenRangeD(double value,double ub, double lb)
{
    int isBetween = FALSE;
    if((lb <= value) && (ub >= value))
    {
        isBetween = TRUE;
    }

    return isBetween;

}

int checkBetweenRangeC(char value,char ub, char lb)
{
    int isBetween = FALSE;
    if(BETWEEN(value,ub,lb))
    {
        isBetween = TRUE;
    }

    return isBetween;
}
