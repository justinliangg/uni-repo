/*-------------------------------
  FILE: Question_one_partD.java
  Author: Justin Liang (19821986)
  UNIT: PDI
  COMMENTS: Java Code for Question 1d)
--------------------------------*/

import java.util.*;

public class Question_one_partD
{
    public static void main(String [] args)
    {   
        //Getting user input for the 10 integers
        //Storing them in an array 
        int [] arrIntegers = getInput();
       
        //Calculating sum
        int sum = sumOfIntegers(arrIntegers);
        
        //Calculating average
        double average = averageOfIntegers(sum);

        //Displaying results to user
        System.out.println("\nSum of the integers: " + sum);
        System.out.println("Average of the integers: " + average);
    }





    //SUBMODULE: averageOfIntegers
    //IMPORT: pSum(Integer)
    //EXPORT: inAverage(Double)
    //ASSERTION: Calculate the average of the 10 integers using pSum
    //           Return average
    
    private static double averageOfIntegers(int pSum)
    {
        double inAverage = (double) pSum / 10;
        
        return inAverage;
    }




    //SUBMODULE: sumOfIntegers
    //IMPORT: pArrIntegers(Array of Integers)
    //EXPORT: inSum(Integer)
    //ASSERTION: get the sum of integers from pArrIntegers and return it

    private static int sumOfIntegers(int [] pArrIntegers)
    {   
        int inSum = 0;
        for(int i = 0; i<pArrIntegers.length; i++)
        {
            inSum = inSum + pArrIntegers[i];
        }
        
        return inSum;
    }     
    


            
    //SUBMODULE: getInput
    //IMPORT: NIL
    //EXPORT: inArrIntegers(Array of Integers)
    //ASSERTION: Create an array of integers of size 10 
    //           Get user input for each element of the array
    //           Return the array
        
    private static int [] getInput()
    {   
        //Creating an array of integers of size 10
        int [] inArrIntegers = new int[10];
   
        System.out.println(""); 
        System.out.println("Please enter 10 Even Integers\n");
        for(int i = 0; i < inArrIntegers.length; i++)
        {
            //Getting user input for each element in the array
            inArrIntegers[i] = userInput(i);
        }

        return inArrIntegers;
    }



   
    //SUBMODULE: userInput
    //IMPORT: elementNum(Integer)
    //EXPORT: input(Integer)
    //ASSERTION: get user input for an even integer
    
    private static int userInput(int elementNum)
    {   
        //Adding 1 to elementNum as elementNum starts from 0
       int integerNum = elementNum + 1;

        //Making input 1 to continue looping if user inputs a non-integer
        int input = 1; 
        do
        { 
            //Try-Catch to only allow for integers
            try
            {   
                //Getting user input
                Scanner sc = new Scanner(System.in); 
                System.out.print("Integer " + integerNum + ": ");
                input = sc.nextInt();
                
                if(input%2 != 0)
                {
                    System.out.println("Please enter only even integers\n");
                }
            }
            catch(InputMismatchException e)
            {
                System.out.println("Please enter only integers\n");
            }
        }
        while(input%2 != 0);   
        
        return input;         
    }
        
}
