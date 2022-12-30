import java.util.*;

public class Fibonacci
{
	public static void main(String[] args)
	{	
		
		int choice=0;
		int numElements =0;
		int digit1 =0;
		int digit2= 0;
		int choice2 = 0;
		do
		{	
		Scanner sc = new Scanner(System.in);
	
		choice = choice("1)Subtration","2)Addition");

		numElements = intInput(5,50,"for number of element");

		digit1= intInput(-100,100," ");
		digit2= intInput(-100,100," ");

		//CREATING ARRAY NOW
		
		int array [] = new int[numElements];
		array[0]= digit1;
		array[1]= digit2;
		if (choice == 2)
		{
			for(int i=2; i<numElements; i++)
			{
				int n3= digit1+digit2;
				array[i] = n3;
				digit1 = digit2;
				digit2 = n3;
				
			}
		}
		else 
		{
			for (int i=2; i<numElements; i++)
			{	
				int n3 = digit1-digit2;
				array[i] = n3; 
				digit1 = digit2;
				digit2 = n3;
				
			}
		}
		
		do
		{
		choice2 = choice2("1)view an element","2)view the entire sequence","3)Restart program","4) Exit Program");
		
		if (choice2 == 1)
		{
			System.out.println("Please enter integer for which element you want to view");
			int elementChoice = sc.nextInt();
			System.out.println("Element " + elementChoice +" = " + array[elementChoice - 1]);
		}
		else if (choice2 == 2)
		{
			for(int i=0;i<numElements;i++)
				{
				System.out.print(array[i] + "\t");
				}
		}
		System.out.println(" ");
		}while(choice2 !=3 && choice2 !=4 );
	
		}while (choice2 ==3);

		
	}
	//submodule for user input
	public static int intInput(int x ,int y,String z)
	{
			int userInput = 0;
			do
			{
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter a value between "+ x + " and "+ y +" " + z);
			userInput = sc.nextInt();
			if(userInput < x || userInput > y) 
			{	
				System.out.println("Please enter values between " + x + " and " + y);
			}
			}while(userInput < x || userInput > y);
				return userInput;
	}	
	

	//submodule for choice
	public static int choice(String x, String y)
	{
	System.out.println("Please select either " + x + " or " + y );
		Scanner sc = new Scanner(System.in);
                int choice = sc.nextInt();
                if (choice <1 || choice >2)
                        { System.out.println("Please select only 1 or 2");
                        }
               	while (choice <1 || choice >2);
		return choice;
	}

	public static int choice2(String x, String y,String z,String c)
        {
        System.out.println("Please select either " + x + " or " + y + " or " + z+ " or " + c);
		int choice=0;
		
		Scanner sc = new Scanner(System.in);
                choice = sc.nextInt();
                if (choice < 1 || choice > 4)
                {
		System.out.println("Please select only 1,2,3 or 4");
		}
		
                return choice;
        }
}
