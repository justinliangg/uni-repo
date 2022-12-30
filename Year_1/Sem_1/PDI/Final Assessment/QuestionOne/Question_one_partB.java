/*-------------------------------
FILE:Question_one_partB.java
Author: Justin Liang (19821986)
UNIT: PDI
COMMENTS: Java code for Question 1b
--------------------------------*/

import java.util.*;

public class Question_one_partB
{
    public static void main(String [] args)
    {   
        //Initialising age
        int age;

        do
        {   
            //INPUT age
            System.out.print("Age: ");
            Scanner sc = new Scanner(System.in);
            age = sc.nextInt();

            if(age <= 0)
            {
                System.out.println("Age is not valid!");
            }

        }
        while(age <= 0);
    }
}
           
