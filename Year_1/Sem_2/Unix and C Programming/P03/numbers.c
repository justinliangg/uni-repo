#include <stdio.h>
#include "numbers.h"
int main(void)
{
    int num1;
    int num2;
    int num3;
    char character;
    Arrange ptr; 
    /*getting user input*/
    readInts(&num1, &num2, &num3, &character);
    
    ptr = order(character);
    
    (*ptr)(&num1, &num2, &num3);   
    
    printf("num1= %d \n num2= %d \n num3= %d \n",num1 ,num2 ,num3);
    
    return 0;
}
