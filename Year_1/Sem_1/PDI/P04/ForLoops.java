import java.util.*;

public class ForLoops

{
	public static void main(String[]args)
	{
		
		//initialise the variables 
		int choice;
		
	
		Scanner sc = new Scanner(System.in);
		
		System.out.print("PLease enter an integers ");

		choice=sc.nextInt();
			

		int x;

		for (x=1 ; x <= choice ; x++)
		{
			
		System.out.print( x + " ");

		}

	 	System.out.println( " ");

		sc.close();


	}
}


	
