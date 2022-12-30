/*-------------------------------                                               
FILE: Question5.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA
PURPOSE: Serialize a LinkedList 
REQUIRES: DSALinkedList                                                     
Last Mod: 21/08/2021                                                         
--------------------------------*/  

import java.util.*;
import java.io.*;

public class Question5
{
    public static void main(String[]args)
    {       
        DSALinkedList list = null;
        int option = 1;
        while(option != 4)
        try
        {   
            System.out.println("");
            displayMenu();
            
            //Getting user Input
            Scanner sc = new Scanner(System.in);
            option = sc.nextInt();
            
            //Menu Option
            switch(option)
            {
                case 1:
                    list = readFile();
                    break;
                case 2:
                    displayList(list);
                    break;
                case 3:
                    writeFile(list);
                    break;
                case 4: 
                    System.out.println("you have exited the program!");
            }
        }
        catch(InputMismatchException e)
        {
            System.out.println("Please enter 1-3 only");
        }
    }
    
    


    //SUBMODULE: displayMenu
    //IMPORT: nil
    //EXPORT: nil
    //ASSERTION: print out display to terminal

    private static void displayMenu()
    {
        System.out.println("1) Read a serialized file");
        System.out.println("2) display the list");
        System.out.println("3) write a serialized file");
        System.out.println("4) EXIT");
    }



    
    //SUBMODULE: readFile
    //IMPORT: nil
    //EXPORT: readObj(DSALinkedList)
    //ASSERTION: get user input for a serialized object, read file and 
    //           transfer to readObj

    private static DSALinkedList readFile()
    {   
        String fileName;
        FileInputStream fileStream;
        ObjectInputStream objectStream;
        DSALinkedList readObj = null;
        try
        {
            //Getting file name from user
            System.out.println("Please enter file name: ");
            Scanner sc = new Scanner(System.in);
            fileName = sc.nextLine();
    
            //processing file and getting the serialized object
            fileStream = new FileInputStream(fileName);
            objectStream  = new ObjectInputStream(fileStream);
            readObj = (DSALinkedList) objectStream.readObject();    
            objectStream.close();
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Class DSALinkedList not found");
        }
        catch(Exception e)
        {
            System.out.println("Unable to load object from file");
        }

        return readObj;
    }
    

    

    //SUBMODULE: displayList
    //IMPORT: pList(DSALinkedList)
    //EXPORT: nil
    //ASSERTION: iterate through pList and display to terminal

    private static void displayList(DSALinkedList pList)
    {   
        try
        {
            System.out.println("");
            Iterator iter = pList.iterator();
            while(iter.hasNext())
            {

                System.out.println((int)iter.next());
            }
        }
        catch(NullPointerException e)
        {
            System.out.println("Have not read a serialized file yet!");
        }
    }




    //SUBMODULE: writeFile
    //IMPORT: pList(DSALinkedList)
    //EXPORT: nil
    //ASSERTION: take pList, serialize it and transfer to a file given by user

    private static void writeFile(DSALinkedList pList)
    {        
        FileOutputStream outputStream;
        ObjectOutputStream objectStream;
        String fileName;
        try
        {
            //Getting user Input
            System.out.println("Please enter the file name");
            Scanner sc = new Scanner(System.in);
            fileName = sc.nextLine();
            
            //Writing to file
            outputStream = new FileOutputStream(fileName);
            objectStream = new ObjectOutputStream(outputStream);
            objectStream.writeObject(pList); 
            objectStream.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to save object to file");
        }
    }    
    
}
