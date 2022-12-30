import java.util.*;
import java.io.*;

public class BubbleSort
{
    public static void main(String[] args)
    {
        System.out.println("Please enter integer values you would like to store in an array");
        Scanner sc = new Scanner(System.in);
        int array [] = new int [8];

        for(int i = 0; i<8 ; i++)
        {
            System.out.println("Value " + (i+1) );
            array[i] = sc.nextInt();
        }

        int sortedArray[] = bubbleSort(array);

        System.out.println(Arrays.toString(sortedArray));
    }
    

    public static int[] bubbleSort(int [] pArray) 
    {
        for(int i = 0; i<pArray.length ; i++)
        {
            for(int j = 0; j<pArray.length - i-1 ; j++)
            {
                if(pArray[j] > pArray[j+1])
                {
                    int temp = pArray[j];
                    pArray[j] = pArray[j+1];
                    pArray[j+1] = temp;
                }
            }
        }
        return pArray;
    }

}                 
