import java.util.*;

public class CelsToFarenV2
{ 
    public static void main(String[] args)
    {
        int userInput =0;
        Scanner sc = new Scanner(System.in);
    	do
	{
        char choice;
        do
        {
        //Asking user for input of a Char variable
        System.out.println("What Temperature scale are you converting from? Enter C for Celcius or F for fahrenheit ");
    	
         choice = sc.next().charAt(0);
        
            
        //If Choice is C, then run 
        if(choice =='C'|| choice == 'c')
     {
        //Initialising Variable
        double celcius=0.0;
        double fahrenheit=0.0;

       //Asking for input in celcius

       System.out.print("Please enter in Temperature in Celcius: ");
       celcius = sc.nextDouble();
       fahrenheit = 9.0/5.0 * celcius + 32.0;

       System.out.println("Fahrenheit= " + fahrenheit);
      }    
        //ElSE IF choice is F, then run
        else if(choice == 'F'|| choice == 'f') 
      { 
        //Initialising Variable
        double fahrenheit=0.0;
        double celcius=0.0;
        
        //Asking for input in fahrenheit

        System.out.print("Please enter in Temperature in Fahrenheit: ");
        fahrenheit = sc.nextDouble();
        celcius= (fahrenheit-32)*5/9; 
        System.out.println("Celcius= " + celcius);   
       } 
        
        else 
            {
                System.out.println ("Please only Input Characters C and F");
            }
        }while( choice != 'C' && choice != 'c' && choice != 'F' &&  choice != 'f');
    System.out.println("Would you like to 1)Continue or 2) Exit");
	userInput = sc.nextInt();
	}while (userInput == 1);
                
      

sc.close();

    }   
}



