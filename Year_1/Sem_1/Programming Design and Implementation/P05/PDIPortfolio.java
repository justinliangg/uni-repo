import java.util.*;

public class PDIPortfolio
{
    public static void main(String[] args)

    {   

        //Print the title out for user
        System.out.println ("Welcome to the Calculator for 2 integers!");
			
        //Allow input from user to choose the integer
        Scanner sc = new Scanner(System.in);
                
            
            
        int choice;
	int value1=0;
	int value2=0;


	do
	{
	System.out.println();

	
        System.out.println("What Would you like to do " + "\n" + "1. Sum of 2 Integers" + "\n" 
        + "2. Product of 2 Integers" + "\n" + "3. Determine if 2 Integers are divisible" + "\n"
        + "0.Exit" + "\n") ;

    	choice = sc.nextInt();
       	
        if (choice <= 3 &&  choice >= 1){
        //initialising variables 
 	value1 = InputInteger("Value 1");
	value2 = InputInteger("Value 2");
	}
   


        //Switch Statements
	//
        switch(choice) 
        
        //For choice 1. Sum of 2 Integers 
        	{
	
        case 1: 

  	int sumofvalue = sum(value1,value2);
           
        //Print sum

        System.out.println("The value of " + (value1) + "+" + (value2) + "=" + (sumofvalue)
       ) ;

        break;

        //For choice 2 . Product of 2 Integers
        case 2:
        int productValue = product(value1,value2);

                 
        //Product of the two value
        System.out.println("The Value of " + (value1) + "x" + (value2) + "=" + (productValue)
        );

        break;
 
        //For choice 3. If 2 integers are divisible
        case 3:
        System.out.println(checkDivisibility(value1,value2));


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
	System.out.println();


       	sc.close();
       
    }

//Method for UserInput for Value1,Value2
	public static int InputInteger(String numOfValue)
	{
	
	 Scanner sc = new Scanner(System.in);
	 System.out.println(numOfValue);
	 int inputValue = sc.nextInt();
	 return inputValue;
	}

	
	
		
   	//Method for Sum
        public static int sum(int value1, int value2)
	{
	int sumValue = value1 + value2;

	return sumValue;
	}

	//Method for Product 
	public static int product(int value1,int value2)
	{
	int productValue = value1 * value2;

	return productValue;
	}

	//Method for Divisibility Check
	public static String checkDivisibility(int value1,int value2)
 	{      
	int  x = Math.abs(value1%value2);
         //Checking for divisibility
         	if ( x > 0) 
        	{
           	return "Value 1 and Value 2 is not divisible";
       	        }	

       		 else  
        	{ 
          	 return "Value 1 and Value 2 is divisible";
        	}
	
	}


  
	




}
        
   
        
    
        
        

        
        
        
        
        
