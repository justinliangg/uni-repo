/*-------------------------------
  FILE: Question_one_partF.java
  Author: Justin Liang (19821986)
  UNIT: PDI
  COMMENTS: Java code for question 1f)
--------------------------------*/

import java.util.*;

public class Question_one_partF
{
    public static void main(String [] args)
    {   
        System.out.println("\nArea Of A Square Calculator");
        System.out.println("\nPlease enter length of side");

        //Getting length
        double length = userInput("Length");

        //Calculating Area
        double area = calculateArea(length);

        //Displaying to user
        System.out.println("\nArea" + " = " + area);

    }
    


    
    //SUBMODULE: userInput
    //IMPORT: sideName(String)
    //EXPORT: input(Double)
    //ASSERTION: get user input for a real number
    //           Return the number

    private static double userInput(String sideName)
    {
        //Initialised input to -1.0 so it keeps looping
        //if user does not enter a number
        double input = -1.0;
        do
        {
            //Try-Catch to only allow for doubles or integers
            try
            {   
                //Getting user input
                Scanner sc = new Scanner(System.in); 
                System.out.print(sideName + ": ");
                input = sc.nextDouble();
                
                if(input <= 0.0)
                {
                    System.out.println(sideName + " has to be greater than 0!");
                }
            }
            catch(InputMismatchException e)
            {
                System.out.println("Please only enter a number greater than 0!");
            }
        }
        while(input <= 0.0);   
        
        return input;         
    }
    



    //SUBMODULE: calculateArea
    //IMPORT: pLength(Double)
    //EXPORT: inArea(Double)
    //ASSERTION: Calculate area of a square using pLength
    //COMMENTS: contains code obtained from an external source
    
    private static double calculateArea(double pLength)
    {
        double inArea = pLength * pLength;
        
        //Rounding inArea to 2 decimal places
        //code from user :asterite
        //https://stackoverflow.com/a/153753
        //(Accessed on 10th June 2021)
        inArea = ((double) Math.round(inArea * 100.0)) / 100.0;
        //end of code obtained from asterite

        return inArea;
    }

}
