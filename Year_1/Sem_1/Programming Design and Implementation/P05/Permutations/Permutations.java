import java.util.*;

public class Permutations
{	
	public static void main(String[] Args)
	{
		int n=0;
		do
		{
		Scanner sc = new Scanner(System.in);
		
		do
		{	
		System.out.println("Please enter a number for the elements in the set");
		n = sc.nextInt();
		if(n<5 || n>50 )
		{
			System.out.println("Please enter a number between 5 and 50");
		}
		}while(n<5 || n>50);
		int r=0;
	
		do
		{
		System.out.println("Please enter the number of elements you wish to select from the set");

		r = sc.nextInt();
		if (r<2 || r>n) 
		{
			System.out.println("Please enter a value between 2 and " + n );
		}
		}while(r<2 || r>n);
		int arrayLength = r-1;
		int array[] = new int[r-1];
		for(int i=0; i < arrayLength ; i++)
			{
			array[i] =( (nFactorial(n))/(NminusRFactorial(n,i)));
			System.out.print(array[i] + "\t");

	
			}
		System.out.println("");

		}while(n>5 && n<50);		
	}

	//Submodule for n!
	public static int nFactorial(int x)
	{
		int nFactorial=1;
				
		for (int i=1; i<=x; i++)
		{		

		nFactorial = nFactorial * i;

		}
		return nFactorial;

	}
	//SUBMODULE For (n-r)!
	public static int NminusRFactorial(int x,int y)
	{
		int nFactorial = 1;
		for (int i=1; i<=(x-(2+y));i++)
		{
			nFactorial = nFactorial*i;
		}
		return nFactorial;

	}



}
