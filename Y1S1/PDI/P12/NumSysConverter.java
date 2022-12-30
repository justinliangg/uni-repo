import java.util.*;
import java.io.*;

public class NumSysConverter
{
    public static void main(String [] args)
    {   
        
        int numDecimal = input();

        int [] arrRemainder = null;
        while(numDecimal != 0)
        {
            int remainder = numDecimal%2;
            System.out.println(numDecimal + " /2" + " = " + remainder); 
            arrRemainder = makeArr(arrRemainder, remainder);
            numDecimal = numDecimal/2;
        }
    }
    
    public static int input()
    {
        System.out.println("Please enter a number to convert to binary");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();

        return input;
    }

    public static int [] makeArr(int [] pArrRemainder, pRemainder)
    {   
        if(pArrRemainder = null);
        {
            int [] newArr = new int [1];
            newArr[0] = pRemainder;
        }
        else
        {
            int [] newArr = new int [pArrRemainder.length + 1]; 
        
              
            
            
        
}
