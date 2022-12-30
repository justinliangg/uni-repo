/*-------------------------------                                               
FILE: UnitTestDSACircularQueue.java                                                    
Author: Justin Liang (19821986)                                                 
UNIT: DSA 
PURPOSE: Test DSACircularQueue Methods
REQUIRES: DSAQueue, DSACircularQueue                           
Last Mod: 12/08/2021                                                            
--------------------------------*/  
import java.io.*;

public class UnitTestDSACircularQueue
{
    public static void main(String [] args)
    {   
        //Variable Declarations
        int numPassed = 0;
        int numTests = 0;
        DSACircularQueue test = null;
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
            test = new DSACircularQueue();
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

        
        //Testing Parameter Constructor
        DSACircularQueue test2 = null;
        System.out.println("\nTesting Parameter Constructor");
        try
        {
            numTests++;
            test2 = new DSACircularQueue(50);
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
                test2.enqueue("1234");
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


         //Testing isFull()
        System.out.println("\nTesting isFull Method");
        try
        {   
            numTests++;
            //Populating queue
            for(int i = 0; i < 100; i++)
            {
                test.enqueue("1234");
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


        //Testing enqueue #2
        System.out.println("\nTesting enqueue Method #2");
        try
        {
            numTests++;

            //Will throw an exception because queue is full
            test.enqueue("nope");

            System.out.println("enqueue METHOD #2 FAILED");
        }
        catch(IndexOutOfBoundsException e)
        {
            numPassed++;
            System.out.println("enqueue METHOD #2 PASSED");
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
       

        //Testing peek #2
        System.out.println("\nTesting peek Method #2");
        try
        {
            numTests++;

            //making space in queue
            String dummy = (String)test.dequeue();
            test.enqueue("Finally");
            
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



//==============================================================================
    //ADDITIONAL TESTS FOR POINTERS 
    System.out.println("\nAdditional Tests for Circular Queue Pointers");
    
    //Test 1 
    try
    {  
        numTests++;

        DSACircularQueue test3 = new DSACircularQueue(3);
        
        //filling the queue
        test3.enqueue("1234");
        test3.enqueue("5678");
        test3.enqueue("9ABC");
        
        //Removing one
        //Front Pointer should move forward
        String x = (String) test3.dequeue();
        
        //Adding again
        //Back pointer should now wrap around
        test3.enqueue("Hello");

        //Testing if last data in queue is "Hello"
        x = (String) test3.dequeue();
        x = (String) test3.dequeue();
        x = (String) test3.dequeue();
        
        if(!x.equals("Hello"))
        {
            throw new IllegalArgumentException();
        }
        else
        {
            numPassed++;
            System.out.println("Additional Test PASSED");
        }
    }
    catch(Exception e)
    {
        System.out.println("Addtional Test FAILED");
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


                 
