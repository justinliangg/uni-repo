import java.util.*;

public class Euler
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the number of terms you would like to calculate for Euler's Constant(Please Enter Between 6 and 100 only)");
		int arrayLength;
		do
		{
		arrayLength = sc.nextInt();
		if (arrayLength < 6 || arrayLength > 100)
			{ 
			System.out.println("Please only enter values between 6 and 100");
			}
		}while (arrayLength < 6 || arrayLength > 100);
	
		double array [] = new double [arrayLength+5];
		
		double w = 1;

		for(int i= 0; i<=arrayLength; i++)
		{
			if(i==0)
			{ array[i] = 1;
			}
			else 
			{
			array[i] = 1/(i*w);
			w= w*i;	
			}
			System.out.print(array[i] + "\t");

		}
		double z = 0;
		for (int i= 0; i<=arrayLength; i++)
		{ 	
		z = z + array[i];
		}

		array[arrayLength+1] = z;

		System.out.print(array[arrayLength+1]);
		
		System.out.println(" ");

	}
}
