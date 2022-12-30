#include <stdio.h>
#include "powers.h"

void powerOfTwo(void)
{
    static int ans = 2;
    
    printf("%d \n", ans);
    ans = ans * 2;
   
    #ifdef DEBUG
    printf("it works");
    #endif

}

