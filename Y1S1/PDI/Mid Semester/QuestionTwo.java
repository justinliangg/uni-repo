/* 
NAME: Justin Liang
ID: 19821986

QUESTION 2
*/


/*<><><><><>><><><>><><><><<><><><><><><><><><><><<><><><><><><><><><<><><><><><><><><><><><><><><><><>*/

import java.util.*;

public class QuestionTwo
{
    public static void main(String[] args)
    {
        //initialising variable outside scope of do while loop
        int choice = 0;

        //using do-while loop to loop until user input 5 to exit
        //try-catch function to only allow for integer inputs
        //if function for different choice values 

        do{    
            try{
                menuDisplay("Decimal Degrees" , "Decimals,Minutes and Seconds");
                Scanner sc = new Scanner(System.in);
                choice = sc.nextInt();
                
                if (choice == 1) 
                {       
                    //Partition for better readability for users
                    System.out.println("---------------------------------------------------------------");
                    
                
                    welcomeMessage("18/04/2021 , 19/04/2021");
                }
                else if (choice == 2) 
                {   
                    //Partition for better readability
                    System.out.println("---------------------------------------------------------------");
                    
                    appExplanation();
                }
                else if (choice == 3)
                {   
                    System.out.println("---------------------------------------------------------------");
                    
		    System.out.println("Converting DD to DMS");
                    //initialising variables
                    String convertedDMSValues[];
                    
                    //method calls
                    int arrayLength = numOfConversions();
                    //adding new line for better readability
                    System.out.println("");
                    convertedDMSValues = deciDegToDegMinSec(arrayLength);
                    displayValuesForDMS(arrayLength,convertedDMSValues);
                } 
                else if (choice == 4)
                {   
                    //Partition for better readability
                    System.out.println("---------------------------------------------------------------");
                    
		    System.out.println("Converting DMS to DD");
                    //initialising array
                    double convertedDDValues[];
                   
                    //method calls
                    int arrayLength = numOfConversions();
                    convertedDDValues = degMinSecToDeciDeg(arrayLength);
                    displayValuesForDD(arrayLength,convertedDDValues);

                }
                else if (choice == 5)
                {
                    System.out.println("YOU HAVE EXITED THE PROGRAM!");
                }
                else    
                {
                    System.out.println("Please enter only values between 1 to 5!");

                }
                }
            catch(InputMismatchException error)
            {}
            System.out.println("");
           }
        while(choice != 5);  
      
    }    

/*<><><><><>><><><>><><><><<><><><><><><><><><><><<><><><><><><><><><<><><><><><><><><><><><><><><><><>*/

//SUBMODULES
    
    //SUBMODULE: menuDisplay
    //FUNCTION : Display MENU screen
    public static void menuDisplay(String unit1 , String unit2)
    {   
        System.out.println("1. Welcome Message");
        System.out.println("2. How to use the app");
        System.out.println("3. Convert " + unit1 +" TO " + unit2);
        System.out.println("4. Convert " + unit2 +" TO " + unit1);
        System.out.println("5. Exit");
    }
    
    //SUBMODULE: welcomeMessage
    //FUNCTION: Display welcome message
    public static void welcomeMessage(String modifDates)
    {
        System.out.println(" Welcome to the Converter for Decimal Degrees!");
        System.out.println(" Convert from decimal degrees to degrees,minutes and seconds and vice versa!");
        System.out.println(" AUTHOR : Justin Liang 19821986");
        System.out.println(" CREATION DATE: 17/04/2021");       
        System.out.println(" MODIFICATION DATES: " +  modifDates);
    }
    
    //SUBMODULE: appExplanation
    //FUNCTION: intructions on how to use the app 
    public static void appExplanation()
    {   
        System.out.println("INSTRUCTIONS");
        System.out.println("1) Select which type of conversion (3 or 4) you would like to make from the menu");
        System.out.println("2) Enter a number for how many conversions you want to make");
        System.out.println("3) Enter values for the conversions");
        System.out.println("The Converter will then display the converted values in the order that you have entered the values");
        System.out.println("You can then exit the program by pressing 5 in the menu or convert more values again by repeating steps 1 to 3");
    }
    
    //SUBMODULE :numOfConversions
    //FUNCTION: Get user input for number of conversions

    public static int numOfConversions()
    {   
        //initialising variables outside scope of do-while loop
        int arrayLength=0;
        
        //do-while loop to only accept values above 0 and integers
        //try-catch to allow only integers 
        do
          {  
            try{
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Number of Conversions: ");
                    arrayLength = sc.nextInt();
                }

            catch(InputMismatchException error)
                {
                    System.out.println("Please enter only integers!" );
                }
            //if statement to tell user to only enter values greater than 0
            if(arrayLength<=0)
                {
                    System.out.println("Value needs to be greater than 0");
                    System.out.println("");
                }
         
          }      
        while (arrayLength<=0);  
        return arrayLength;
    } 
    
    //SUBMODULE : deciDegToDegMinSec
    //FUNCTION :get user input for decimal degrees
    //Convert the decimal degrees to degrees,minutes and seconds and store them in an array
    
    /* Parts of this file comprise externally-obtained code. */ 

    public static String[] deciDegToDegMinSec(int pArrayLength)
    {   
        //Creating an array of string with length of pArrayLength which is number of conversion user has made
        String array[] = new String [pArrayLength];
        
        //For loop to store array with converted DMS values
        for(int i = 0; i < pArrayLength; i++)
            {   
                //initialising variable outside the scope of do-while loop
                double deciDeg = 0.0;
                
                //do-while loop to only allow values other than 0 and integers
                //try catch to only allow for integers 
                do
                    {
                    try
                        {
                            Scanner sc = new Scanner(System.in);
                            System.out.print("Decimal Degree " + (i+1)+  ": ");
                            deciDeg = sc.nextDouble();
                        }
                    catch(InputMismatchException error)
                        {
                            System.out.println("Please enter only decimals! ");
                        }
                            System.out.println("");
                    }
                while(deciDeg == 0.0);

                //Calculations for conversion from decimal degrees to DMS.               
                int degrees = (int) deciDeg;
                double minutes = (deciDeg - (double) degrees) * 60;
                int integerMinutes = (int) minutes;
                double seconds = (minutes - (double) integerMinutes) * 60;

		        //Round seconds to 3 decimal places
                //code from user : asterite
                //https://stackoverflow.com/a/153753
                //(Accessed on 17th April 2021)
                double roundSeconds= (double) Math.round(seconds * 1000d) / 1000d;
                //end of code obtained from asterite
    		    
		    
                //If function for when decimal degree is negative its prints out -degrees ,minutes and seconds, for better readability
                if(deciDeg<0)
                {   
                    //storing DMS into an array of strings
                    array[i] =( "-" + Math.abs(degrees)+ "°" + " " + Math.abs(integerMinutes) + "'" + " " + Math.abs(roundSeconds) + "''");
                }
                else
                { 
                    array[i] =( degrees+ "°" + " " + integerMinutes + "'" + " " + roundSeconds + "''");
                } 
            }
    
        //Returning array of strings with converted degrees,minutes and seconds stored in it
        return array;

    }
        
    //SUBMODULE : degMinSecToDeciDeg
    //FUNCTION: get user input for Degrees,min and seconds
    //convert them to degree decimals and store them into an array
    
    /* Parts of this file comprise externally-obtained code. */

    public static double[] degMinSecToDeciDeg(int pArrayLength)
    {   
        //initialising variables outside scope of for loop   
        double[] array = new double [pArrayLength];
        //initialised variables with values to keep looping even after exception is thrown 
        int decimal=-5000;
        double minutes=-1.0;
        double seconds= -1.0;
        
        //for loop to store DD values into an array
        for(int i = 0; i< pArrayLength; i++)
            {
                System.out.println("Value " + (i+1));
                
               
                do
                    {   
                        try
                        {
                            Scanner sc = new Scanner(System.in); 
                            System.out.print("Decimal: ");  
                            decimal = sc.nextInt();
                        }
                        catch(InputMismatchException error)
                        {
                            System.out.println("Please enter only integers!");
                            System.out.println("");
                        }		
                    }
                 while(decimal>=3600||decimal<=-3600);
                
                //do-while loop to only allow for values between 0 and 60 else it keeps looping
                //try-catch for non-integers
                
                do
                    { 
                        try
                        {   
                            Scanner sc =new Scanner(System.in); 
                            System.out.print("Minutes: ");
                            minutes = sc.nextDouble();
                        }
                        catch(InputMismatchException error)
                        {
                            System.out.println("Please enter only numbers!");
                        }
                        //if statement to prompt user if they enter numbers outside 0 and 60
			            if (minutes <0.0||minutes >= 60.0)
			            {	
	 	      	            System.out.println("Value needs to be between 0 and 60");
                            System.out.println("");
    			        }			    
                    }
                while(minutes <0.0||minutes >= 60.0);
                
                //do-while loop to only allow for values only between 0 and 60 else it keeps looping
                //try catch for non integers
            
                do
                    {
                        try 
                        {   
                            Scanner sc =new Scanner(System.in); 
                            System.out.print("Seconds: ");
                            seconds = sc.nextDouble();
                        }
                        catch(InputMismatchException error)
                        { 
                            System.out.println("Please only enter numbers!");
                            System.out.println("");
                        }
                        //if statement to prompt user if they enter numbers outside 0 and 60
			            if(seconds<0.0||seconds >=60.0)
		            	{                      
			                System.out.println("Value needs to be between 0 and 60");
		    	        }	    
                    }
                while(seconds <0.0||seconds >=60.0);
                
		        //Calculations for DMS to Decimal Degrees.
                double valueMinutes = minutes/60.0;
                double valueSeconds = seconds/3600.0;
                double deciDegrees;

		        /*if function for negative decimal degrees as they require different calculations compared to positive decimals degree*/

                if (decimal < 0)
                    { 
                    deciDegrees = (double) decimal -valueMinutes - valueSeconds;
                    }
                else
                    deciDegrees = valueMinutes + valueSeconds + (double) decimal;

		        //Rounding the Decimal Degrees to 3 decimal places
                //code from user : asterite
                //https://stackoverflow.com/a/153753
                //(Accessed on 17th April 2021)    
                double roundDeciDegrees = (double) Math.round(deciDegrees * 1000d) / 1000d;
                //end of code obtained from asterite
		

		        //storing the value of the rounded Decimal Degrees into an array
                array[i] = roundDeciDegrees;
                
		        //Print line for better readability
                System.out.println("");
                
            }
        //return array of stored decimal degrees
        return array;
     } 
    
    //SUBMODULE: displayValuesForDMS
    //FUNCTION: Display converted values for DMS
    public static void displayValuesForDMS(int pArrayLength,String[] pArray)
    {   
        System.out.println("Converting DD to DMS Results");
        for(int i =0; i < pArrayLength; i++)
            {
            System.out.println(pArray[i]);
            }
    }    

    //SUBMODULE: displayValuesForDD
    //FUNCTION: Display converted values for Decimal Degrees
    public static void displayValuesForDD(int pArrayLength,double[] pArray)
    {   
        System.out.println("Converting DMS to DD Results");
        for(int i =0; i < pArrayLength; i++)
            {
            System.out.println(pArray[i]);
            }
    } 


}

