import java.util.*;
public class CelsToFaren
{ 
    public static void main(String[] args)
    {

        Scanner sc = new Scanner(System.in);
        //Asking user for input of a Char variable
        System.out.println("What Temperature scale are you converting from? Enter C for Celcius or F for fahrenheit ");
 
        char choice = sc.next().charAt(0);
        
        //If Choice is C, then run 
        if(choice =='C')
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
        else if(choice == 'F') 
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

        //Else if input other than C or F
        else 
        { 
        System.out.println("Error! Please reset program");
        
        } 
      

sc.close();

    }   
}



