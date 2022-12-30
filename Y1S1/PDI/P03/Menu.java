import java.util.*;

public class Menu
{
    public static void main(String[] args)

    {   
        //Print the title out for user
        System.out.println ("Welcome to the Calculator for 2 integers!");
	
        //Allow input from user to choose the integer
        Scanner sc = new Scanner(System.in);



	int choice;
        int value1, value2 ;



	do
	{
        System.out.println("What Would you like to do " + "\n" + "1. Sum of 2 Integers" + "\n" 
        + "2. Product of 2 Integers" + "\n" + "3. Determine if 2 Integers are divisible" + "\n"
        + "0.Exit" + "\n") ;

	choice = sc.nextInt();
        
        //initialising variables 
 
    
        //Switch Statements
        switch(choice) 
        
        //For choice 1. Sum of 2 Integers 
        {
        case 1: 

        //input value 1
        System.out.println("Value 1 =  "); 
        value1 = sc.nextInt();
        
        //input value 2
        System.out.println("Value 2 = ");
        value2 = sc.nextInt();

    
        //Print sum

        System.out.println("The value of " + (value1) + "+" + (value2) + "=" + (value1 + value2)
       ) ;

        break;

        //For choice 2 . Product of 2 Integers
        case 2:
        
        //input value 1 
        System.out.println("Value 1 = ");
        value1 = sc.nextInt();
        
        //input value 2
        System.out.println("Value 2 = ");
        value2= sc.nextInt(); 

 
         
        //Product of the two value
        System.out.println("The Value of " + (value1) + "x" + (value2) + "=" + (value1*value2)
        );

        break;
 
        //For choice 3. If 2 integers are divisible
        case 3:
        
        //input value 1 
        System.out.println("Value 1 = ");
        value1 = sc.nextInt();
        
        //input value 2
        System.out.println("Value 2 = ");
        value2= sc.nextInt(); 
        
      
        
        //Initialising variable for value of 1/2
        // x is 1/2
        int  x = Math.abs(value1%value2);
         //Checking for divisibility
         if ( x > 0) 
        {
            System.out.println("Value 1" + "/" + "Value 2 " + "is not divisible");
        }

        else  
        { 
            System.out.println ("Value 1" + "/" + "Value 2 " + "is divisible");
        }

        break;
        
        //For choice 4 Exit 
        case 0: 
        
   
        System.out.println("You have exited the program!");

        break;

        //If he selected any other int
        
        default:
        System.out.println("Please enter only number 1 to 4. Reset program to restart.");
        
      
        break;
	
	}
	}while(choice != 0);
	
	System.out.println(choice);

       	sc.close();

        

    }

}
        
   
        
    
        
        

        
        
        
        
        
