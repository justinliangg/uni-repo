/*-------------------------------                                               
FILE: UnitTestDSAQueue.java                                                    
Author: Justin Liang (19821986)                                                 
UNIT: DSA 
PURPOSE: Test DSAQueue Methods
REQUIRES: DSALinkedList                       
Last Mod: 21/08/2021                                                            
--------------------------------*/  
import java.io.*;
import java.util.*;

public class UnitTestDSAQueue
{
    public static void main(String [] args)
    {   
        //Variable Declarations
        int numPassed = 0;
        int numTests = 0;
        DSAQueue test = null;
        String testString;


//==============================================================================
        //BEGIN TESTS
        System.out.println("DSAQueue Unit Test");


        //Testing Default Constructors
        System.out.println();
        System.out.println("=================================================");
        System.out.println("Testing Default Constructor");
        try
        {
            numTests++;
            test = new DSAQueue();
            if(!test.isEmpty())
            {
                throw new IllegalArgumentException();
            }

            numPassed++;
            System.out.println("Default Constructor Test Passed");
        }
        catch(Exception e)
        {
            System.out.println("Default Constructor Test Failed");
        }



//------------------------------------------------------------------------------
        //TESTING MUTATOR METHODS
        System.out.println();
        System.out.println("=================================================");
        System.out.println("Testing MUTATOR METHODS");


        //Testing enqueue method
        System.out.println("\nTesting enqueue method");
        try
        {
            numTests++;
            test.enqueue("1234");
            test.enqueue("5678");
            test.enqueue("9ABC");
            
            //Checking if first value is "1234"
            testString = (String)test.peek();
            if(!testString.equals("1234"))
            {
                throw new IllegalArgumentException();
            }

            numPassed++;
            System.out.println("enqueue METHOD PASSED");
        }
        catch(Exception e)
        {
            System.out.println("enqueue METHOD FAILED");
        }


        //Testing dequeue method
        System.out.println("\nTesting dequeue Method");
        try
        {
            numTests++;
            
            testString = (String)test.dequeue();
            if(!testString.equals("1234"))
            {
                throw new IllegalArgumentException();
            }
            
            testString = (String)test.dequeue();
            if(!testString.equals("5678"))
            {
                throw new IllegalArgumentException();
            }
            
            testString = (String)test.dequeue();
            if(!testString.equals("9ABC"))
            {
                throw new IllegalArgumentException();
            }

            numPassed++;

            System.out.println("dequeue METHOD PASSED");
        }
        catch(Exception e)
        {
            System.out.println("dequeue METHOD FAILED");
        }


        //Testing dequeue Method #2
        System.out.println("\nTesting dequeue Method #2");
        try
        {   
            numTests++;

            //Will throw exception because dequeueing empty queue
            test.dequeue();
            
            System.out.println("dequeue METHOD #2 FAILED");
        }
        catch(NullPointerException e)
        {
            numPassed++;
            System.out.println("dequeue METHOD #2 PASSED");
        }



//------------------------------------------------------------------------------
        //TESTING ACCESSOR METHODS;
        System.out.println();
        System.out.println("=================================================");
        System.out.println("Testing ACCESSOR METHODS");


        //Testing isEmpty
        System.out.println("\nTesting isEmpty Method");
        try
        {
            numTests++;

            if(!test.isEmpty())
            {
                throw new IllegalArgumentException();
            }

            numPassed++;

            System.out.println("isEmpty METHOD PASSED");
        }
        catch(Exception e)
        {
            System.out.println("isEmpty METHOD FAILED");
        }


        //Testing peek
        System.out.println("\nTesting peek Method");
        try
        {
            numTests++;

            //This will throw an exception because accessing empty queue
            test.peek();
            
            System.out.println("peek METHOD FAILED");
        }
        catch(NullPointerException e)
        {
            numPassed++;
            System.out.println("peek METHOD PASSED");
        }


        //Testing peek #2
        System.out.println("\nTesting peek Method #2");
        try
        {
            numTests++;

            test.enqueue("1234");
            test.enqueue("5678");
            test.enqueue("9ABC");
            
            testString = (String)test.peek();
            if(!testString.equals("1234"))
            {
                throw new IllegalArgumentException();
            }

            numPassed++;
            System.out.println("Peek METHOD #2 PASSED");
        }
        catch(Exception e)
        { 
            System.out.println("Peek METHOD #2 FAILED");
        }


        //Testing iterator()
        System.out.println("\nTesting Iterator Method");
        try
        {
            numTests++;
            
            //Creating iter object
            Iterator iter = test.iterator();
            
            //Iterating through stack
            testString = (String) iter.next();
            if(!testString.equals("1234"))
            {
                throw new IllegalArgumentException();
            }
            testString = (String) iter.next();
            if(!testString.equals("5678"))
            {
                throw new IllegalArgumentException();
            }
            testString = (String) iter.next();
            if(!testString.equals("9ABC"))
            {
                throw new IllegalArgumentException();
            }

            numPassed++;
            System.out.println("Iterator METHOD PASSED");
        }
        catch(Exception e)
        {
            System.out.println("Iterator Method Failed!");
        }
 


//==============================================================================
        //Displaying Final Test Results
        System.out.println("=================================================");
        System.out.println("Number of Tests Conducted: " + numTests);
        System.out.println("Number of Tests Passed: " + numPassed);
        System.out.println("Number of Tests Failed: " + (numTests - numPassed));
        System.out.println("% of Tests Passed: " 
                            + ((double)numPassed/(double)numTests * 100)
                            + "%");
    }
}


    
    
