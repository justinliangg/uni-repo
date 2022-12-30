/*-------------------------------                                               
FILE: Factorial.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES: nil                                                     
Last Mod: 10/08/2021                                                            
--------------------------------*/  
import java.io.*;
import java.util.*;

public class Factorial
{
    public static void main(String [] args)
    {
        System.out.println("Factorial Calculator");
        int userInput = -1;
        long ans = 0;

        while (userInput < 0 || ans <= 0) 
        {
            try 
            {   
                //Getting user input
                Scanner sc = new Scanner(System.in);
                System.out.println("Integer: ");
                userInput = sc.nextInt();
                
                //Calculation Factorial
                ans = calcFactorial(userInput);
                System.out.println(userInput + "! = " + ans);
            }
            //Exception thrown by Scanner
            catch (InputMismatchException e) 
            {
                System.out.println("Please enter integers only");
            }
            //Exception thrown by calcFactorial
            catch (IllegalArgumentException e2) 
            {
                System.out.println("Please enter integers greater or equal " +
                "to 0");
            }
        } 
    }
    



    //SUBMODULE: factorial
    //IMPORT: n(Integer)
    //EXPORT: ans(Long)
    //ASSERTION: calculate n!

    private static long factorial(int n)
    {
        long ans = 0;
        
        //base case
        if(n == 0)
        {
            ans = 1;
        }

        else
        {
            ans = n * factorial(n-1);
        }

        return ans;

    }
   


    //SUBMODULE: calcFactorial
    //IMPORT: n(Integer)
    //EXPORT: ans(Long)
    //ASSERTION: Wrapper function to validate input for factorial()

    public static long calcFactorial(int n) throws IllegalArgumentException
    {   
        long ans;
        
        if(n >= 0)
        {
            //Starting time
            long startTime = System.nanoTime();
            ans = factorial(n);
            long endTime = System.nanoTime();
            
            //Calculating time taken
            //Converting to microSeconds
            int timeTaken = (int)(((double)(endTime - startTime))/1000.0);

            System.out.println("Time Taken: " + timeTaken + " micro seconds");

            return ans;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

}

