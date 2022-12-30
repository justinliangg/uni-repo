import java.util.*;

public class TwoDArray
{
	public static void main (String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the amount of rows in the array");
		int arrayRows = sc.nextInt();
		System.out.println("Please enter the amount of columns in the array");
		int arrayColumns = sc.nextInt();
		int array[][] = new int[arrayRows][arrayColumns];
		for(int i=0; i<arrayRows; i++)
		{
			System.out.print("\n");
			for(int j=0; j<arrayColumns;j++)
			{
				array[i][j]= i*j;
				System.out.print(array[i][j] + "\t");
		}
		System.out.print("\n");
		}
	}
}
