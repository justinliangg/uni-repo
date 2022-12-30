/*-------------------------------                                               
FILE: TowersOfHanoi.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES: nil                                                     
Last Mod: 09/08/2021                                                            
--------------------------------*/  
import java.util.*;
import java.io.*;

public class TowersOfHanoi 
{
    public static void main(String[] args) 
    {
        int numDisks = 0;
        do 
        {
            System.out.println("Please enter number of disks: ");
            try 
            {
                //Getting user input for number of disks
                Scanner sc= new Scanner(System.in);
                numDisks= sc.nextInt(); 

                towersOfHanoi(numDisks);
            }
            catch (InputMismatchException e) 
            {
                System.out.println("Please enter only integers!");
            }
            catch(IllegalArgumentException e2)
            {
                System.out.println("Please enter an integer greater than 0");
            }
        } 
        while (numDisks < 1);
        
    }
    

    
    
    //SUBMODULE: towersOfHanoi
    //IMPORT: numDisks(Integer)
    //EXPORT: nil
    //ASSERTION: Wrapper function for towers() method to make input simpler
    //           and also to validate input

    public static void towersOfHanoi(int numDisks) 
    {
        if(numDisks >= 1)
        {
            towers(numDisks, 1, 3, "");
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
    


    
    //SUBMODULE: towers
    //IMPORT: n(Integer), src(Integer), dest(Integer), indent(String)
    //EXPORT: nil
    //ASSERTION: Move disks from source to destination

    private static void towers(int n, int src, int dest, String indent) 
    {   

        //Adding to indent for each tower call
        String tempString = "  " + indent;
        System.out.println(tempString + "Towers(" + n + "," + src + "," + dest + ")"); 

        if (n == 1)
        {
            moveDisk(src, dest, tempString);
        } 
        else 
        {
            int temp = 6 - src - dest;
            towers(n - 1, src, temp, tempString);
            moveDisk(src, dest, tempString);
            towers(n - 1, temp, dest, tempString);
        }
    }

    

    
    //SUBMODULDE: moveDisk
    //IMPORT: src(Integer), dest(Integer), indent(String)
    //EXPORT: nil
    //ASSERTION: print movement of disk to the terminal

    private static void moveDisk(int src, int dest, String indent) 
    {
        String tempString = "  " + indent;
        System.out.println(tempString + "Moving disk from peg " + src
                           + " to peg " + dest);
    }
}
