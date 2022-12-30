/*-------------------------------                                               
FILE: GCD.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES: nil                                                     
Last Mod: 09/08/2021                                                            
--------------------------------*/  
import java.util.*;
import java.io.*;

public class GCD 
{
    public static void main(String[] args) 
    {
        System.out.println("Greater Common Denominator Calculator");
        int number1= 0;
        int number2 = 0;
        while (number1 <= 0 || number2 <= 0) 
        {
            try 
            {   
                //Getting user input
                Scanner sc = new Scanner(System.in);
                System.out.println("First Integer: ");
                number1= sc.nextInt();
                System.out.println("Second Integer: ");
                number2 = sc.nextInt();
                
                //Calculation GCD
                long ans = calcGCD(number1, number2);
                System.out.println("Answer = " + ans);
            }
            //Exception thrown by Scanner
            catch (InputMismatchException e) 
            {
                System.out.println("Please enter integers only");
            }
            //Exception thrown by calcGCD
            catch (IllegalArgumentException e2) 
            {
                System.out.println("Please enter integers greater than 0!");
            }
        }
    }
    


    
    //SUBMODULE: gcd
    //IMPORT: n1(Integer), n2(Integer)
    //EXPORT: ans(Long)
    //ASSERTION: Calculate the greater common denominator between n1 and n2
    //COMMENTS: Contains code obtained from external source.

    private static long gcd(int n1, int n2) 
    {
        long ans;
        
        //Obtained from Programiz
        //https://www.programiz.com/java-programming/examples/gcd-hcf-recursion
        //(Accessed on 7 August 2021)
        if (n2 != 0)
        {
            ans = gcd(n2, n1 % n2);
        }
        else
        {
            ans =  n1;
        }
        //End Code Obtained from Programiz
        
        return ans;
    }

    //SUBMODULE: calcGCD
    //IMPORT: n1(Integer), n2(Integer)
    //EXPORT: ans(Long)
    //ASSERTION: Wrapper function to validate input for gcd() method

    public static long calcGCD(int n1, int n2) 
    {   
        long ans;

        if (n1 > 0 && n2 > 0) 
        {   
            //Starting time
            long startTime = System.nanoTime();
            ans = gcd(n1,n2);
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
