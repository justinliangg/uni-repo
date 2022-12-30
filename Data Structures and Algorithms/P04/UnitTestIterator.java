/*-------------------------------                                               
FILE: UnitTestIterator.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES: DSALinkedList
PURPOSE: Test the iterator method in DSALinkedList
Last Mod: 20/08/2021                                                            
--------------------------------*/  

import java.util.*;
public class UnitTestIterator
{
    public static void main(String[] args)
    {   
        //VARIABLE DECLARATIONS
        DSALinkedList ll = new DSALinkedList();
        Iterator llIterator = null;
        String testString;
        int numPassed = 0;
        int numTests = 0;
        

        //Adding values into linkedList
        ll.insertFirst("1234");
        ll.insertFirst("5678");
        ll.insertFirst("9ABC");



//==============================================================================
        //START TESTS
        System.out.println("Unit Test for Linked list Iterator");
        System.out.println("=================================================");

        //Test 1: Constructing Iterator
        try
        {   
            System.out.print("Test 1 Iterator(): ");
            numTests++;
            
            //Creating an interator from ll.
            llIterator = ll.iterator();
            
            //Checking if hasNext() is empty
            if(!llIterator.hasNext())
            {   
                throw new IllegalArgumentException();
            }
            
            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("Failed");
        }
        

        //Test 2: hasNext()
        try
        {
            System.out.print("Test 2 hasNext(): ");
            numTests++;
           
            //If hasNext returns false then failed
            if(!llIterator.hasNext())
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


        //Test 3: next()
        try
        {
            System.out.print("Test 3 next(): ");
            numTests++;
            
            testString = (String) llIterator.next();
            if(!testString.equals("9ABC"))
            {
                throw new IllegalArgumentException();
            }
            testString = (String) llIterator.next();
            if(!testString.equals("5678"))
            {
                throw new IllegalArgumentException();
            }
            testString = (String) llIterator.next();
            if(!testString.equals("1234"))
            {
                throw new IllegalArgumentException();
            }

            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("FAILED");
        }


        //Test 4: next() #2
        try
        {
            System.out.print("Test 4 next() #2: ");
            numTests++;
            
            //LinkedList is empty, so testString should be null
            testString = (String) llIterator.next();
            if(testString != null)
            {
                throw new IllegalArgumentException();
            }

            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("FAILED");
        }


        //Test 5: remove()
        try
        {
            System.out.print("Test 5 remove(): ");
            numTests++;
            
            //remove() method should throw UnsupportedOperationException
            llIterator.remove();

            System.out.println("FAILED");
        }
        catch(UnsupportedOperationException e)
        {
            numPassed++;
            System.out.println("passed");
        }



//==============================================================================
        //Print Test Summary
        System.out.print("\nNumber PASSED: " + numPassed + "/" + numTests);
        System.out.print(" -> " + (int)(double)numPassed/numTests*100 + "%\n");


    }

}       
