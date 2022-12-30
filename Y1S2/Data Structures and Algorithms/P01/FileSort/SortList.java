/*-------------------------------                                               
FILE: SortList.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT:DSA                                                                       
REQUIRES: Student.java, FileIO.java, Sorts.java                                                      
Last Mod: 01/08/2021                                                            
--------------------------------*/  
import java.io.*;
import java.util.*;

public class SortList
{
    public static void main(String[] args)
    {   
        //Checking only one string in args array
        if(args.length != 1)
        {
            instructions();
        }
        else
        {   
            //Checking if only one character in args[0]
            if(args[0].length() == 1)
            {   
                //Getting sort type that user inputted.
                char sortType = args[0].charAt(0);

                //processing the file and sorting based on sortType
                //writing to file.
                processFile(sortType);

            }
            else
            {
                instructions();
            }
        }
    }



    
    //SUBMODULE: processFile
    //IMPORT: pSortType(Character)
    //EXPORT: nil
    //ASSERTION: read from file, store values into studentArray 
    //sort array based on Id and write back to a file. 
    
    private static void processFile(char pSortType)
    {   
        String fileName = "RandomNames7000(2).csv";

        //Initialising array
        Student [] studentArray = FileIO.numRows(fileName);
        
        //Storing values into array
        studentArray = FileIO.fileRead(fileName, studentArray);        

        //Sorting the student based on pSortType
        Student [] sortedStudentArray = sortData(pSortType, studentArray);

        //Write to File
        FileIO.writeToFile(sortedStudentArray);
    }


    

    //SUBMODULE: sortData
    //IMPORT: pSortType(Character), pStudentArray(Array of Student)
    //EXPORT: pStudentArray(Array of Student)
    //ASSERTION: Sort pStudentArray based on student Id.

    private static Student [] sortData(char pSortType, Student [] pStudentArray)
    {
        switch(pSortType)
        {
            case 'b':
                //Sorting with bubbleSort
                Sorts.bubbleSort(pStudentArray);
                break;

            case 'i':
                //Sorting with insertionSort
                Sorts.insertionSort(pStudentArray);
                break;

            case 's':
                //Sorting with selectionSort
                Sorts.selectionSort(pStudentArray);

                break;
            
            default: 
                throw new IllegalArgumentException("No sort exists");
        }
        return pStudentArray;
        
    }



    
    //SUBMODULE: instructions
    //IMPORT: nill
    //EXPORT: nil
    //ASSERTION: print instructions for command line arguments

    private static void instructions()
    {   
        System.out.println("Usage: java Main x");
        System.out.println("Where x is either");
        System.out.println("b - bubblesort");
        System.out.println("i - insertion sort");
        System.out.println("s - selection sort");
    }

}
