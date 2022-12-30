#include "user_input.h"
#include <stdio.h>

void readInts(int* num1, int* num2, int* num3, char* character)
{   
    printf("Enter three ints and a character \n");
    printf("Num 1: ");
    scanf("%d", num1);

    printf("Num2: ");
    scanf("%d", num2);

    printf("Num3: ");
    scanf("%d", num3);

    printf("Character: ");
    scanf(" %c", character);

}
