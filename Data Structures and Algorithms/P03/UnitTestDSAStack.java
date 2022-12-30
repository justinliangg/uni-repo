/*-------------------------------                                               
FILE: UnitTestDSAStack.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA          
PURPOSE: Test harness for DSAStack
REQUIRES: DSAStack.java                                                    
Last Mod: 12/08/2021                                                        
--------------------------------*/  
import java.io.*;

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

        //Testing Parameter Constructor
        DSAStack test2 = null;
        System.out.println("\nTesting Parameter Constructor");
        try
        {
            numTests++;
            test2 = new DSAStack(50);
            if(!test2.isEmpty())
            {
                throw new IllegalArgumentException("New Stack should be empty");
            }
        }
        catch(Exception e)
        {
            System.out.println("Parameter Contructor FAILED");
        }   
        try
        {
            //Populating to test size of Stack
            //Set to above max size to see if it fails
            for(int i = 0; i < 51; i++)
            {
                test2.push("1234");
            }
            
            System.out.println("Parameter Constructor FAILED");
        }
        catch(IndexOutOfBoundsException e)
        {   
            numPassed++;
            System.out.println("Parameter Constructor PASSED");
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

        //Testing isFull()
        System.out.println("\nTesting isFull Method");
        try
        {   
            numTests++;
            //Populating stack
            for(int i = 0; i < 100; i++)
            {
                test.push("1234");
            }
            //Testing method
            if(!test.isFull())
            {
                throw new IllegalArgumentException();
            }
            
            numPassed++;

            System.out.println("isFull METHOD PASSED");
        }
        catch(Exception e)
        {
            System.out.println("isFull METHOD FAILED");
        }

        //Testing push() #2
        System.out.println("\nTesting push Method #2");
        try
        {
            numTests++;

            //Will throw an exception because stack is full
            test.push("nope");

            System.out.println("push METHOD #2 FAILED");
        }
        catch(IndexOutOfBoundsException e)
        {
            numPassed++;
            System.out.println("push METHOD #2 PASSED");
        }

        //Testing getCount()
        System.out.println("\nTesting getCount Method");
        try
        {
            numTests++;
            
            if(test.getCount() == 100)
            {
                numPassed++;
                System.out.println("getCount METHOD PASSED");
            }
            else
            {
                throw new IllegalArgumentException();
            }
        }
        catch(Exception e)
        {
            System.out.println("getCount METHOD FAILED");
        }
        
        //Testing top() #2
        System.out.println("\nTesting top Method #2");
        try
        {
            numTests++;

            //removing top of stack and replacing with a different value
            String dummy = (String)test.pop();
            test.push("Finally");
            
            testString = (String)test.top();
            if(!testString.equals("Finally"))
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


    
