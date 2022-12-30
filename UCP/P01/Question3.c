#include <stdio.h>

/* declaring function */
int factorial(int n);

int main(void)
{
    /* getting user input */
    int userInt = 0; 
    int factorialValue = 0;
    
    do
    {
        printf("Enter an integer: ");
        scanf("%d", &userInt);
        
        if(userInt > 0)
        {
            /* Calculate the factorial of userInput */
            factorialValue = factorial(userInt);
            printf("User Integer = %d \nAnswer = %d \n", userInt ,factorialValue);
        }
        else
        {
            printf("Please enter an integer greater than 0 \n");
        }
    }
    while(userInt > 0);

    return 0;
}

/* SUBMODULE: factorial
   IMPORT: pUserInt (int)
   EXPORT: total (int) */

int factorial(int pUserInt)
{
    int total = 1;
    
    while(pUserInt > 0)
    {
        total = pUserInt * total;
        pUserInt = pUserInt - 1;
    }

    return total;
}
