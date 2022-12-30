/*-------------------------------                                               
FILE: UnitTestDSAHeap.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
PURPOSE: Test DSAHeap methods
REQUIRES:                                                     
Last Mod: 23/09/2021  
COMMENTS: Used previously in DSA Prac 08
--------------------------------*/  

import java.util.*;
import java.io.*;

public class UnitTestDSAHeap
{
    public static void main(String[] args)
    {  
        //Variable declarations
        int numPassed = 0;
        int numTests = 0;
        int testInteger;
        String testString; 
        DSAHeap heap = null;

//==============================================================================
        //TESTING CONSTRUCTOR
        System.out.println("\n\nTesting Constructor");
        System.out.println("=================================================");
        
        //Test 1 : Constructor
        try 
        {
            numTests++;
            System.out.print("Testing creation of DSAHeap: ");
            
            heap = new DSAHeap(7);
            
            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }




//==============================================================================
        //TESTING MUTATORS
        System.out.println("\nTesting Mutators");
        System.out.println("=================================================");
        

        //Test 2: add()
        try
        {
            numTests++;
            System.out.print("Testing add(): ");

            heap.add(5,5);
            heap.add(7,7);
            heap.add(20,20);
            heap.add(15,15);
            heap.add(10,10);
            heap.add(18,18);
            heap.add(10,10);        
            /*Heap array should look like this*/
            /* [20,15,18,5,10,7,10] */

            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }
        

        
        //Test 3: add() #2
        try
        {
            numTests++;
            System.out.print("Testing add() #2: ");

            /* Should throw exception, trying to add into a full array*/
            heap.add(10,10);

            System.out.println("failed");
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            numPassed++;
            System.out.println("passed");
        }



        //Test 4: remove()
        try
        {
            numTests++;
            System.out.print("Testing remove(): ");
            testInteger = (int) heap.remove();
            if(testInteger != 20)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) heap.remove();
            if(testInteger != 18)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) heap.remove();
            if(testInteger != 15)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) heap.remove();
            if(testInteger != 10)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) heap.remove();
            if(testInteger != 10)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) heap.remove();
            if(testInteger != 7)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) heap.remove();
            if(testInteger != 5)
            {
                throw new IllegalArgumentException();
            }

            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }
        
        

        //Test 5: remove() #2
        try
        {
            numTests++;
            System.out.print("Testing remove() #2: ");

            /* heap is empty, should throw exception when trying to remove from
            empty heap */       
            heap.remove();

            System.out.println("failed");
        }
        catch(NullPointerException e)
        {
            numPassed++;
            System.out.println("passed");
        }



        //Test Results
        System.out.print("\nNumber PASSED: " + numPassed + "/" + numTests);
        System.out.print(" -> " + (int)(double)numPassed/numTests*100 + "%\n");
       
 
    }
}
