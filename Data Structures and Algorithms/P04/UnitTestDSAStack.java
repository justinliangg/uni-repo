/*-------------------------------                                               
FILE: UnitTestDSAStack.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA          
PURPOSE: Test harness for DSAStack
REQUIRES: DSAStack.java                                                    
Last Mod: 12/08/2021                                                        
--------------------------------*/  
import java.io.*;
import java.util.*;
public class UnitTestDSAStack
{
    public static void main(String [] args)
    {
        //Variable Declarations
        int numPassed = 0;
        int numTests = 0;
        DSAStack test = null;
        String testString;
        
        //BEGIN TESTS
        System.out.println("\nDSAStack Unit Test");
//------------------------------------------------------------------------------
        //TESTING CONSTRUCTOR

        //Testing Default Constructor
        System.out.println();
        System.out.println("=================================================");
        System.out.println("Testing Default Constructor");
        try
        {
            numTests++;
            test = new DSAStack();
            if(!test.isEmpty())
            {
                throw new IllegalArgumentException("New Stack should be empty");
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


        //Testing push() method
        System.out.println("\nTesting Push method");
        try
        {
            numTests++;
            test.push("1234");
            test.push("5678");
            test.push("9ABC");
            
            //Checking if top value is 9ABC
            testString = (String)test.top();
            if(!testString.equals("9ABC"))
            {
                throw new IllegalArgumentException();
            }

            numPassed++;
            System.out.println("PUSH METHOD PASSED");
        }
        catch(Exception e)
        {
            System.out.println("PUSH METHOD FAILED");
        }


        //Testing pop() method
        System.out.println("\nTesting Pop Method");
        try
        {
            numTests++;
            
            testString = (String)test.pop();
            if(!testString.equals("9ABC"))
            {
                throw new IllegalArgumentException();
            }
            
            testString = (String)test.pop();
            if(!testString.equals("5678"))
            {
                throw new IllegalArgumentException();
            }
            
            testString = (String)test.pop();
            if(!testString.equals("1234"))
            {
                throw new IllegalArgumentException();
            }

            numPassed++;

            System.out.println("pop METHOD PASSED");
        }
        catch(Exception e)
        {
            System.out.println("pop METHOD FAILED");
        }

        
        //Testing pop() #2 method
        System.out.println("\n Testing Pop Method #2");
        try
        {
            numTests++;
            
            //Should throw exception as stack is empty
            testString = (String)test.pop();

            System.out.println("pop METHOD FAILED");
        }
        catch(NullPointerException e)
        {   
            numPassed++;
            System.out.println("Pop METHOD #2 PASSED");
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


        //Testing top()
        System.out.println("\nTesting top Method");
        try
        {
            numTests++;

            //This will throw an exception because accessing empty stack
            test.top();
            
            System.out.println("top METHOD FAILED");
        }
        catch(NullPointerException e)
        {
            numPassed++;
            System.out.println("top METHOD PASSED");
        }


        //Testing top() #2
        System.out.println("\nTesting top Method #2");
        try
        {
            numTests++;
            
            //Pushing onto stack
            test.push("1234");
            test.push("5678");
            test.push("9ABC");
            
            testString = (String)test.top();
            if(!testString.equals("9ABC"))
            {
                throw new IllegalArgumentException();
            }

            numPassed++;
            System.out.println("Top METHOD #2 PASSED");
        }
        catch(Exception e)
        { 
            System.out.println("top METHOD #2 FAILED");
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
            if(!testString.equals("9ABC"))
            {
                throw new IllegalArgumentException();
            }
            testString = (String) iter.next();
            if(!testString.equals("5678"))
            {
                throw new IllegalArgumentException();
            }
            testString = (String) iter.next();
            if(!testString.equals("1234"))
            {
                throw new IllegalArgumentException();
            }

            numPassed++;
            System.out.println("Iterator Method PASSED");
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


    
