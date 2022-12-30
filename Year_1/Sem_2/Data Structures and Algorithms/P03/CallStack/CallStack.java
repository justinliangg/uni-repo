/*-------------------------------                                               
FILE: CallStack.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA   
PURPOSE: Imitate Call Stack
REQUIRES: DSAStack.java                                                    
Last Mod: 14/08/2021                                                            
--------------------------------*/  
import java.io.*;
import java.util.*;

public class CallStack
{   
    public static void main(String[] args)
    { 
        //Initialising callStack
        DSAStack callStack = new DSAStack(5);
        
        //Filling callStack
        //Displaying function when popping off
        long ans = factorial(4,callStack);

        System.out.println("Answer = " + ans);
        
    }


    //SUBMODULE: factorial
    //IMPORT: n(Integer)
    //EXPORT: ans(Long)
    //ASSERTION: calculate n! and added sets of print statements to show
    //how call stacks work
    
    private static long factorial(int n, DSAStack pCallStack)
    {       
        long ans;
         
        //Pushing the name and import onto the stack
        pCallStack.push("Factorial(" + n + ")");
        
        //Displaying stack before next method call
        System.out.println(pCallStack.display());
        
        ans = 0;
        //base case
        if(n == 0)
        {   
            ans = 1;
        }

        else
        {   
            //Printing when base case has been reached.
            if((n-1) == 0)
            {
                System.out.println("Reached base case \n");
            }

            ans = n * factorial((n-1), pCallStack);
        }
        
        //Popping off the stack
        //And displaying what is popped off
        String x= (String) pCallStack.pop();
        System.out.println("\n" + "Pop " + x  ); 

        //Displaying what else is left on the stack
        System.out.println(pCallStack.display());  

        return ans;
    }

}
