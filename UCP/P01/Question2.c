#include <stdio.h>

int main(void)
{   
    int value1 = 0;
    int value2 = 0;

    printf("Enter two integers \n");
    
    printf("Value 1: ");
    scanf("%d", &value1);
    
    while(value2 == 0)
    {
        printf("Value 2: ");
        scanf("%d", &value2);

        if(value2 == 0)
        {
            printf("Denominator cannot be 0 \n");
        }
    }

    if((value1 % value2) == 0)
    {
        printf("divisible \n");
    }
    else
    {
        printf("not divisible \n");
    }

    return 0;
}

