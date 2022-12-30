/*-------------------------------                                               
FILE: FileIO.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES: Student.java                                                     
Last Mod: 01/08/2021                                                            
--------------------------------*/  
import java.io.*;
import java.util.*;

public class FileIO
{      
    //SUBMODULE: numRows
    //IMPORT: fileName(String)
    //EXPORT: studentArray(Array of Students)
    //ASSERTION: count number of rows to initialise studentArray
    public static Student[] numRows(String fileName)
    {
        FileInputStream fileInput = null;
        InputStreamReader reader;
        BufferedReader bfr;
        String line;
        int elementNum;

        elementNum = 0;
        try
        {   
            //initalising objects and variables
            fileInput = new FileInputStream(fileName);
            reader = new InputStreamReader(fileInput);
            bfr = new BufferedReader(reader);
            line = bfr.readLine();

            while(line != null)
            {
                String[] column = processLine(line); 
                
                //This ensures that row will be rejected if missing commas
                if(column.length == 2)
                {  
                    elementNum++;
                }
        
                line = bfr.readLine();
            }
            fileInput.close();
        } 
        catch(IOException e)
        {
            if(fileInput != null)
            {
                try 
                {
                    fileInput.close();
                }
                catch(IOException ex2)
                {}
            }
        }
     
     //Initialising array
     //Take 1 to remove the header row
     Student [] studentArray = new Student[elementNum];
     return studentArray;


    }




    //SUBMODULE: fileRead
    //IMPORT: fileName(String)
    //EXPORT: studentArray(Array of Students)
    //ASSERTION: store values from file into studentArray

    public static Student[] fileRead(String fileName, Student [] studentArray)
    {
        FileInputStream fileInput = null;
        InputStreamReader reader;
        BufferedReader bfr;
        String line;
        int elementNum;
        
        elementNum = 0;
        try
        {   
            //initalising objects and variables
            fileInput = new FileInputStream(fileName);
            reader = new InputStreamReader(fileInput);
            bfr = new BufferedReader(reader);
            line = bfr.readLine();

            while(line != null)
            {
                String[] column = processLine(line); 
                
                //This ensures that row will be rejected if missing commas
                if(column.length == 2)
                {  
                    try
                    {   
                        int id = Integer.parseInt(column[0]);
                        String name = column[1];
                        //Constructing studentArray
                        studentArray[elementNum] = new Student(id,name);
                        
                        //If no exceptions are thrown, add 1 to element num
                        elementNum++;
                    }
                    //Exception thrown by Integer.parseInt or Double.parseDouble
                    //If String cannot be converted to an integer or Double
                    catch(NumberFormatException x)
                    {}
                    //Exception thrown by Student Constructor to validate the 
                    //values
                    catch(IllegalArgumentException x2)
                    {}
                }
                line = bfr.readLine();
            }
            fileInput.close();
        }
        catch(IOException e)
        {
            if(fileInput != null)
            {
                try 
                {
                    fileInput.close();
                }
                catch(IOException ex2)
                {}
            }
        }
        
     return studentArray;

    }
    



    //SUBMODULE: processLine
    //IMPORT: pRow(String)
    //EXPORT: inColumn(Array of Strings)
    //ASSERTION: parse pRow every ","

    public static String[] processLine(String pRow)
    {
        String [] inColumn;
        //splits every "," 
        inColumn = pRow.split(",");

        return inColumn;
    }



    
    //SUBMODULE: writeToFile
    //IMPORT: sortedStudentArray(Array of Students)
    //EXPORT: nil
    //ASSERTION: write pSortedStudentArray to a csv file(SortedList.csv).

    public static void writeToFile(Student [] sortedStudentArray)
    {   
        String fileOutputName = "SortedList.csv";
        FileOutputStream stream = null;
        PrintWriter pw;

        try
        {    
            stream = new FileOutputStream(fileOutputName);
            pw = new PrintWriter(stream);

            for(int i = 0; i < sortedStudentArray.length; i++)
            {   
                //Writing each student to file
                pw.println(sortedStudentArray[i].writeToFileString());
            }
            System.out.println("File Write Completed");
            
            pw.close();
        }
        catch(IOException e) 
         {  
            if (stream != null)
            {
                try
                {
                    stream.close();
                }       
                catch(IOException ex2)
                {}
            }           
            System.out.println("Error in writing to file!");
         } 
    } 
 
    
}
