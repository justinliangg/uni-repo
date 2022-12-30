/*-------------------------------                                               
FILE: NumberConversion.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES: nil                                                     
Last Mod: 09/08/2021                                                            
--------------------------------*/  
import java.util.*;
import java.io.*;

public class NumberConversion 
{
    public static void main(String[] args) 
    {
        System.out.println("Decimal Number Conversion");
        int number = 0;
        int base = 0;
        while (number <= 0 || base <= 0) 
        {
            try 
            {
                Scanner sc = new Scanner(System.in);
                System.out.println("Number: ");
                number = sc.nextInt();
                System.out.println("Base: ");
                base = sc.nextInt();

                String ans = calcNumConversion(number, base);
                System.out.println("Answer = " + ans);
            }
            //Catch Exception thrown by Scanner 
            catch (InputMismatchException e)
            {
                System.out.println("Please enter integers only");
            }
            //Catch Exception thrown by calcNum
            catch (IllegalArgumentException e2) 
            {
                System.out.println("Please enter integers greater than 0!");
            }
        }
    }
   

    
    
    //SUBMODULE: numberConversion
    //IMPORT: number(Integer), base(Integer)
    //EXPORT: answer(String)
    //ASSERTION: Convert a decimal number to the corresponding base
    //           and output the answer as a String

    private static String numberConversion(int number, int base) 
    {
        String answer;
        
        //Base Case
        if ( number == 0) 
        {
            answer = "";
        } 
        else 
        {
            String remainderString;
            int remainder= number % base;   

            //If remainder 10-15, return hexadecimal equivalent.
            switch (remainder) 
            {
                case 10: 
                {
                    remainderString = "A";
                    break;
                }
                case 11: 
                {
                    remainderString = "B";
                    break;
                }
                case 12: 
                {
                    remainderString = "C";
                    break;
                }
                case 13: 
                {
                    remainderString = "D";
                    break;
                }
                case 14: 
                {
                    remainderString = "E";
                    break;
                }
                case 15: 
                {
                    remainderString = "F";
                    break;
                }
                default: 
                {       
                    //if remainder is less than 10, return string value of remainder.
                    remainderString = String.valueOf(number % base);
                }
            }
            
            answer = numberConversion(number/base, base) + remainderString;
        }
        
        return answer;
    }



    
    //SUBMODULE: calcNumConversion
    //IMPORT: number(Integer), base(Integer)
    //EXPORT: ans(String)
    //ASSERTION: Wrapper function to validate input for numberConversion() 
    //           Method

    public static String calcNumConversion(int number, int base) 
    {   
        String ans;

        if (number > 0 && (base > 0 && base <= 16)) 
        {
            //Starting time
            long startTime = System.nanoTime();
            ans = numberConversion(number,base);
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
