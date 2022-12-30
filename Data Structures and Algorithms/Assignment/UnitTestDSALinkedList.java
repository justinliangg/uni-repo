 /***************************************************************************
 *  FILE: TestLinkedList.java
 *  AUTHOR: Connor Beardsmore - 15504319
 *  UNIT: DSA120 Prac 8
 *  PURPOSE: Basic Test Harness For Linked List
 *  LAST MOD: 12/10/15
 *  REQUIRES: NONE
    COMMENT: Code supplied in DSA Prac 04. Have added some extra methods.
 ***************************************************************************/

import java.io.*;

public class UnitTestDSALinkedList
{
	public static void main(String args[])
	{
        // VARIABLE DECLARATIONS
        int iNumPassed = 0;
        int iNumTests = 0;
        DSALinkedList ll = null;
        String sTestString;
        Object nodeValue;

//---------------------------------------------------------------------------

        System.out.println("\n\nTesting Normal Conditions - Constructor");
        System.out.println("=======================================");

        // TEST 1 : CONSTRUCTOR
        try {
            iNumTests++;
            ll = new DSALinkedList();
            System.out.print("Testing creation of DSALinkedList (isEmpty()): ");
            if (ll.isEmpty() == false)
                throw new IllegalArgumentException("Head must be null.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

//---------------------------------------------------------------------------

        System.out.println("\nTest inserting first and removing first (stack behaviour)");
        System.out.println("==========================================================");

        // TEST 2 : INSERT FIRST
        try {
            iNumTests++;
            System.out.print("Testing insertFirst(): ");
            ll.insertFirst("abc");
            ll.insertFirst("jkl");
            ll.insertFirst("xyz");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 3 : PEEK LAST
        try {
            iNumTests++;
            System.out.print("Testing peekLast(): ");
            sTestString = (String)ll.peekLast();
            if (sTestString != "abc")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed   ");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 4 : REMOVE FIRST
        try {
            iNumTests++;
            System.out.print("Testing removeFirst(): ");
            sTestString = (String)ll.removeFirst();
            if (sTestString != "xyz")
                throw new IllegalArgumentException("FAILED.");
            sTestString = (String)ll.removeFirst();
            if (sTestString != "jkl")
                throw new IllegalArgumentException("FAILED.");
            sTestString = (String)ll.removeFirst();
            if (sTestString != "abc")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 5 : IS EMPTY
        try {
            iNumTests++;
            System.out.print("Testing isEmpty(): ");
            sTestString = (String)ll.removeFirst();
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

//---------------------------------------------------------------------------

        System.out.println("\nTest inserting last and removing first (queue behaviour)");
        System.out.println("========================================================");

        // TEST 6 : INSERT LAST()
        try {
            iNumTests++;
            System.out.print("Testing insertLast(): ");
            ll.insertLast("abc");
            ll.insertLast("jkl");
            ll.insertLast("xyz");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 7 : PEEK LAST
        try {
            iNumTests++;
            System.out.print("Testing peekFirst(): ");
            sTestString = (String)ll.peekFirst();
            if (sTestString != "abc")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 8 : REMOVE FIRST
   try {
            iNumTests++;
            System.out.print("Testing removeFirst(): ");
            sTestString = (String)ll.removeFirst();
            if (sTestString != "abc")
                throw new IllegalArgumentException("FAILED.");
            sTestString = (String)ll.removeFirst();
            if (sTestString != "jkl")
                throw new IllegalArgumentException("FAILED.");
            sTestString = (String)ll.removeFirst();
            if (sTestString != "xyz")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 9 : IS EMPTY 2
        try {
            iNumTests++;
            System.out.print("Testing isEmpty(): ");
            sTestString = (String)ll.removeFirst();
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        // TEST 10 : INSERT FIRST
        try {
            iNumTests++;
            System.out.print("Testing insertFirst()");
            ll.insertFirst("abc");
            ll.insertFirst("jkl");
            ll.insertFirst("xyz");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        //TEST 11 : REMOVE LAST
        try
        {
            iNumTests++;
            System.out.print("Testing removeLast(): ");
           
            sTestString = (String) ll.removeLast();
            if(!sTestString.equals("abc"))
            {
                throw new IllegalArgumentException("FAILED.");
            }
            sTestString = (String) ll.removeLast();
            if(!sTestString.equals("jkl"))
            {
                throw new IllegalArgumentException("FAILED.");
            }
            sTestString = (String) ll.removeLast();
            if(!sTestString.equals("xyz"))
            {
                throw new IllegalArgumentException("FAILED.");
            }
            
            iNumPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("FAILED");
        }

        //TEST 12: REMOVE
        try
        {
            iNumTests++;
            System.out.print("Testing remove(): ");
            
            //Adding into linkedlist
            ll.insertLast("1234");
            ll.insertLast("avd");
            ll.insertLast("5678");
            
            //Removing value
            ll.remove("avd"); 

            //Checking if removed
            sTestString = (String) ll.removeFirst();
            if(!sTestString.equals("1234"))
            {
                throw new IllegalArgumentException("FAILED.");
            }
            sTestString = (String) ll.removeLast();
            if(!sTestString.equals("5678"))
            {
                throw new IllegalArgumentException("FAILED");
            }

            iNumPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }




//---------------------------------------------------------------------------

        // PRINT TEST SUMMARY
        System.out.print("\nNumber PASSED: " + iNumPassed + "/" + iNumTests);
        System.out.print(" -> " + (int)(double)iNumPassed/iNumTests*100 + "%\n");
    }
//---------------------------------------------------------------------------
}
