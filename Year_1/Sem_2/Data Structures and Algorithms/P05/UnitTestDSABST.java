/*-------------------------------                                               
FILE: UnitTestDSABST.java                                              
Author: Justin Liang (19821986)                                                 
UNIT: DSA           
PURPOSE: Unit testing DSABinarySearchTree.java
REQUIRES: DSABinarySearchTree.java, DSAQueue.java, DSALinkedList.java                   
Last Mod: 27/08/2021                                                            
--------------------------------*/
import java.io.*;
import java.util.*;

public class UnitTestDSABST
{
    public static void main(String[]args)
    {
        //Variable Declarations
        int numPassed = 0;
        int numTests = 0;
        DSABinarySearchTree test = null;
        DSABinarySearchTree test2 = null;
        int testInteger;
        String testString; 

//==============================================================================
        //TESTING CONSTRUCTOR
        System.out.println("\n\nTesting Constructor");
        System.out.println("=================================================");
        
        //Test 1 : Constructor
        try 
        {
            numTests++;
            System.out.print("Testing creation of DSABinarySearchTree() : ");
            
            test = new DSABinarySearchTree();
            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }


//==============================================================================
        //TESTING MUTATOR METHODS
        System.out.println("\nTesting Mutator Methods");
        System.out.println("=================================================");


        //Test 2 : insert()
        try
        {
            numTests++;
            System.out.print("Testing insert(): ");
            
            test.insert("50",50);
            test.insert("60",60);
            test.insert("20",20);
            test.insert("70",70);
            test.insert("30",30);
            test.insert("10",10);
            test.insert("40",40);

            /* Tree shape 
                         50
                        /  \
                       20   60
                      /  \    \      
                     10  30   70
                           \
                            40
            */

            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }


        //Test 3: insert() #2
        try
        {
            numTests++;
            System.out.print("Testing insert() #2: ");

            //Trying to insert a duplicate key
            //Should throw an Exception
            test.insert("20",20);

            System.out.println("failed");
        }
        catch(IllegalArgumentException e)
        {
            numPassed++;
            System.out.println("passed");
        }

        
        //Test 4: delete() 
        try
        {
            numTests++;
            System.out.print("Testing delete(): ");
            
            test2 = new DSABinarySearchTree();

            //Inserting into test2 
            test2.insert("10",10);
            test2.insert("3",3);
            test2.insert("1",1);
            test2.insert("7",7);
            test2.insert("4",4);
            test2.insert("9",9);
            test2.insert("16",16);
            test2.insert("12",12);
            test2.insert("20",20);
            test2.insert("14",14);
            test2.insert("19",19);
            test2.insert("26",26);

            //Deleting
            test2.delete("1");
            test2.delete("3");
            test2.delete("16");
            test2.delete("26");
            test2.delete("20");

            //Checking if tree is in predicted order
            DSAQueue testQueue = new DSAQueue();
            testQueue = test2.preOrder();
            
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 10)
            {   
                throw new IllegalArgumentException();
            }
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 7)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 4)
            {   
                throw new IllegalArgumentException();
            }
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 9)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 19)
            {   
                throw new IllegalArgumentException();
            }
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 12)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 14)
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
              
        
//==============================================================================
        //TESTING ACCESSOR METHODS
        
        System.out.println("\nTesting Accessor Methods");
        System.out.println("=================================================");

        //Test 5: find()
        try
        {
            numTests++;
            System.out.print("Testing find(): ");
            
            //If value inside key is not correct
            //throw exception
            testInteger = (int) test.find("20");
            if(testInteger != 20)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) test.find("10");
            if(testInteger != 10)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) test.find("30");
            if(testInteger != 30)
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


        //Test 6: find() #2
        try
        {
            numTests++;
            System.out.print("Testing find() #2: ");

            //Trying to find a key that does not exist
            //Should throw a null pointer exception
            testInteger = (int) test.find("100");
            
            System.out.println("failed");
        }
        catch(NullPointerException e)
        {   
            numPassed++;
            System.out.println("passed");
        }


        //Test 7: height()
        try
        {   
            numTests++;
            System.out.print("Testing height(): ");

            int height = test.height();
            //height of tree should be 3, else failed
            if(height != 3)
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
        

        //Test 8: min()
        try
        {
            numTests++;
            System.out.print("Testing min(): ");
            
            //min key should be 10;
            testString = test.min();
            if(!testString.equals("10"))
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


        //Test 9: max()
        try
        {
            numTests++;
            System.out.print("Testing max(): ");

            //max key should be 70
            testString = test.max();
          
          if(!testString.equals("70"))
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
        
        
        //Test 10: balance()
        try
        {
            numTests++;
            System.out.print("Testing balance(): ");

            //balance should be 75% because difference in height is 1
            testInteger = test.balance();
            if(testInteger != 75)
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

        

//==============================================================================
        //TRAVERSAL METHODS
        System.out.println("\nTraversal Methods");
        System.out.println("=================================================");

        
        //Test 11: preOrder()
        try
        {
            numTests++;
            System.out.print("Testing preOrder(): ");
            
            //Order of queue should be 50,20,10,30,40,60,70
            DSAQueue testQueue = test.preOrder();

            testInteger = (int) testQueue.dequeue();
            if(testInteger != 50)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 20)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 10)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 30)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 40)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 60)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 70)
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


        //Test 12: inOrder()
        try
        {
            numTests++;
            System.out.print("Testing inOrder(): ");
            
            //Order of queue should be 10,20,30,40,50,60,70
            DSAQueue testQueue = test.inOrder();
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 10)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 20)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 30)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 40)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 50)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 60)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 70)
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


        //Test 13: postOrder()
        try
        {
            numTests++;
            System.out.print("Testing postOrder(): ");
            
            //Order of queue should be 10,40,30,20,70,60,50
            DSAQueue testQueue = test.postOrder();
           
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 10)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 40)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 30)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 20)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 70)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 60)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) testQueue.dequeue();
            if(testInteger != 50)
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
        

        //Testing Display()
        try
        {
            System.out.println("\nTesting Display(): ");
            test.display(1);
            test.display(2);
            test.display(3);
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }

            

//==============================================================================
        //Print Test Summary
        System.out.print("\nNumber PASSED: " + numPassed + "/" + numTests);
        System.out.print(" -> " + (int)(double)numPassed/numTests*100 + "%\n");



    }
}
        



