#include "order.h"

Arrange order(char character)
{   
    Arrange ptr;
    if(character == 'A')
    {
        ptr = &ascending3;
    }
    else if(character == 'D')
    {
        ptr = &descending3;
    }
    else
    {
        ptr = NULL;
    }
    
    return ptr;
}


static void ascending2(int* x, int* y)
{
    if(*x > *y)
    {   
        int temp =*y;
        *y = *x;
        *x = temp;
    }
}

void ascending3(int* x, int* y, int* z)
{
    ascending2(x,y);
    ascending2(y,z);
    ascending2(x,y);
}   

void descending3(int *x, int*y, int* z)
{   
    int temp;

    ascending3(x,y,z);

    temp = *z;
    *z = *x;
    *x = temp;
}
