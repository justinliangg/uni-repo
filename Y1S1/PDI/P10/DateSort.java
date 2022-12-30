import java.util.*;
import java.io.*;

public class DateSort
{
    public static void main(String[] args)
    {
        Date dateArray[] = new Date[8];
        dateArray[0] = new Date(1,2,2007);
        dateArray[1] = new Date(10,2,2002);
        dateArray[2] = new Date(9,2,2010);
        dateArray[3] = new Date(7,5,2009);
        dateArray[4] = new Date(1,2,2007);
        dateArray[5] = new Date(2,2,2001);
        dateArray[6] = new Date(5,6,2004);
        dateArray[7] = new Date(4,2,2003);
    
        bubbleSort3(dateArray);
        bubbleSort2(dateArray); 
        bubbleSort1(dateArray);
       
        for(int i=0 ; i<dateArray.length; i++)
        {
            System.out.println(dateArray[i].toString()); 
        }
 
    }    

    public static void bubbleSort1(Date [] pArray) 
    {
        for(int i = 0; i<pArray.length ; i++)
        {
            for(int j = 0; j<pArray.length - i-1 ; j++)
            {
                if(pArray[j].getYear() > pArray[j+1].getYear())
                {
                    Date temp = pArray[j];
                    pArray[j] = pArray[j+1];
                    pArray[j+1] = temp;
                }
            }
        }
    }

    public static void bubbleSort2(Date [] pArray) 
    {
        for(int i = 0; i<pArray.length ; i++)
        {
            for(int j = 0; j<pArray.length - i-1 ; j++)
            {
                if(pArray[j].getMonth() > pArray[j+1].getMonth())
                {
                    Date temp = pArray[j];
                    pArray[j] = pArray[j+1];
                    pArray[j+1] = temp;
                }
            }
        }
    }

    public static void bubbleSort3(Date [] pArray) 
    {
        for(int i = 0; i<pArray.length ; i++)
        {
            for(int j = 0; j<pArray.length - i-1 ; j++)
            {
                if(pArray[j].getDay() > pArray[j+1].getDay())
                {
                    Date temp = pArray[j];
                    pArray[j] = pArray[j+1];
                    pArray[j+1] = temp;
                }
            }
        }
    }



}                 
