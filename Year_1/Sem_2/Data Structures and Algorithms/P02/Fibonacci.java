/*-------------------------------                                               
FILE: Fibonacci.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES: nil                                                     
Last Mod: 09/08/2021                                                            
--------------------------------*/  
import java.util.*;
import java.io.*;

public class Fibonacci
{
    public static void main(String [] args)
    {
        System.out.println("Fibonacci Calculator");
        int userInput = 0;
        while (userInput < 1) 
        {
            try 
            {   
                //Getting user input
                Scanner sc = new Scanner(System.in);
                System.out.println("Integer: ");
                userInput = sc.nextInt();
                
                //Calculating fibonacci
                long ans = calcFibonacci(userInput);
                System.out.println("Answer = " + ans);
            }
            //Exception thrown by Scanner
            catch (InputMismatchException e) 
            {
                System.out.println("Please enter integers only");
            }
            //Exception thrown by calcFibonacci
            catch (IllegalArgumentException e2) 
            {
                System.out.println("Please enter integers greater or equal " +
                "to 1!");
            }
        } 
    }
    
    
    

    //SUBMODULE: fibonacci
    //IMPORT: n(Integer)
    //EXPORT: ans(Long)
    //ASSERTION: calculate n(th) term in the fibonacci sequence

    private static long fibonacci(int n)
    {   
        long ans = 0;
        
        //base case
        if(n == 0)
        {
            ans = 0;
        }
        //Another base case
        else if(n == 1)
        {
            ans = 1;
        }
        else
        {
            ans = fibonacci(n-1) + fibonacci(n-2);
        }

        return ans;
    }
    
    

    //SUBMODULE: calcFibonacci
    //IMPORT: n(Integer)
    //EXPORT: ans(Long)
    //ASSERTION: Wrapper functiont to validate input for fibonacci() method

    public static long calcFibonacci(int n)
    {   
        long ans;

        //n-1 because the fibonacci() method starts from 0 not 1
        n = n-1;    
        if(n >= 0)
        {
            //Starting time
            long startTime = System.nanoTime();
            ans = fibonacci(n);
            long endTime = System.nanoTime();
            
            //Calculating time taken
            //Converting to microSeconds
            int timeTaken = (int)(((double)(endTime - startTime))/1000.0);

            System.out.println("Time Taken: " + timeTaken + " micro seconds");
 
        }
        else
        {
            throw new IllegalArgumentException();
        }

        return ans;
    }

}
