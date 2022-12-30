import java.util.*;

public class CelsToFarenV3
{ 
    public static void main(String[] args)
    {
        int choice;
        do
        {
            Scanner sc = new Scanner (System.in);
            displayMenu();
            choice = inputChoice();
            
            if(choice == 1)
            {
                displayMenu2();
                int choice2 = inputChoice();
    
                if (choice2 == 1)
                {   
                    int celsValue;
                    try
                    {   
                        System.out.println("Celcius Value: ");
                        celsValue = sc.nextInt();
                        celsToFahren(celsValue);
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("Please enter only integers");
                    }
                }
                else if(choice2 == 2)
                {
                    System.out.println("Fahrenheit Value: ");
                    int fahrenValue = sc.nextInt();
                    fahrenToCels(fahrenValue);
                }
                else
                {
                    System.out.println("Please enter only values 1 - 2");
                }
            }
        
            else if(choice == 2)
            {
                displayMenu2();
                int choice2 = inputChoice();

                if(choice2 == 1)
                {
                    System.out.println("Celcius Value");
                    double celsValue = sc.nextDouble();
                    celsToFahren(celsValue);
                }
                else if (choice2 == 2)
                {
                    System.out.println("Fahrenheit Value");
                    double fahrenValue = sc.nextDouble();
                    fahrenToCels(fahrenValue);
                }
                else
                {
                    System.out.println("Please enter only real numbers");
                }
            }
        
            else if(choice ==3)
            {
                System.out.println("YOU HAVE EXITED THE PROGRAM");
            }

            else 
            {
                System.out.println("Please enter numbers between 1 - 3");
            }
        }
            
        while(choice != 3);
    }    

               
        
            



    public static int inputChoice()
    {
        Scanner sc  = new Scanner(System.in);
        int inChoice = sc.nextInt();
        return inChoice;
    }

    public static void displayMenu()
    {   
        System.out.println("Enter 1 if you like to use integer");
        System.out.println("Enter 2 if you like to use real numbers");
        System.out.println("Enter 3 to EXIT ");
    }
    
    public static void displayMenu2()
    {
        System.out.println ("1) Convert Celcius to Fahrenheit");
        System.out.println("2) Convert Fahrenheit to Celcius");
    }
    
    //SUBMODULE: celsToFahren
    //IMPORT: pCelcius(int) 
    //EXPORT: NIL
    public static void celsToFahren(int pCelcius)
    {
        double fahrenheit = 9.0/5.0 * (double) pCelcius + 32.0;            
        System.out.println("Fahrenheit= " + fahrenheit);
    }

    //SUBMODULE: celsToFahren
    //IMPORT: pCelcius (double) 
    //EXPORT: NIL  

    public static void celsToFahren(double pCelcius)
    {
        double fahrenheit = 9.0/5.0 * pCelcius + 32.0;
        System.out.println("Fahrenheit= " + fahrenheit);
    }    
    
    //SUBMODULE: fahrenToCels
    //IMPORT: pCelcius(int)
    //EXPORT: NIL
    
    public static void fahrenToCels(int pFahrenheit)
    {   
        double inCelcius = ((double) pFahrenheit-32)*5/9; 
        System.out.println("Celcius= " + inCelcius);  
    }
 
    //SUBMODULE: fahrenToCels
    //IMPORT: pFahrenheit
    //EXPORT: nil
    public static void fahrenToCels (double pFahrenheit)
    {    
        double inCelcius = (pFahrenheit-32)*5.0/9.0; 
        System.out.println("Celcius= " + inCelcius);   
    
    }       
        
      



}
