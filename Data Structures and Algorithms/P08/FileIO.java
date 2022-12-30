/*-------------------------------                                               
FILE: FileIO.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES:                                                     
Last Mod: 23/09/2021                                                            
--------------------------------*/  
import java.io.*;
import java.util.*;

public class FileIO
{     
    //SUBMODULE: fileRead
    //IMPORT: fileName(String), array(Array of DSAHeapEntry)
    //EXPORT: nil 
    //ASSERTION: get priority and value from file and store in array

    public static void fileRead(String fileName, DSAHeapEntry[] array)
    {
        FileInputStream fileInput = null;
        InputStreamReader reader;
        BufferedReader bfr;
        String line;
        
        try
        {   
            //initalising objects and variables
            fileInput = new FileInputStream(fileName);
            reader = new InputStreamReader(fileInput);
            bfr = new BufferedReader(reader);
            line = bfr.readLine();
            int numElements = 0;
            
            while(line != null)
            {
                String[] column = processLine(line); 
                
                //This ensures that row can only have two values
                if(column.length == 2)
                {  
                    try
                    {   
                        int priority = Integer.parseInt(column[0]);
                        String value = column[1];
                        
                        array[numElements] = new DSAHeapEntry(priority,value);
                        numElements++;
                    }
                    //Exception thrown by Integer.parseInt
                    catch(NumberFormatException x)
                    {}
                    //Exception thrown by put() if there is duplicate values
                    catch(IllegalArgumentException x2)
                    {}
                }
                line = bfr.readLine();
            }
            fileInput.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Please enter a valid file name!\n");
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
    }


    //SUBMODULE: processLine
    //IMPORT: pRow(String)
    //EXPORT: inColumn(Array of Strings)
    //ASSERTION: parse pRow every " "

    public static String[] processLine(String pRow)
    {
        String [] inColumn;
        //splits every " " 
        inColumn = pRow.split(",");
        
        //Removing all white spaces between " " 
        for(int i = 0; i < inColumn.length; i++)
        {
            inColumn[i] = inColumn[i].trim();
        }
        return inColumn;
    }




    //SUBMODULE: writeToFile
    //IMPORT: array(array of DSAHeapEntry) 
    //EXPORT: nil
    //ASSERTION: write sorted array to file

    public static void writeToFile(DSAHeapEntry[] array)
    {   
        FileOutputStream stream = null;
        PrintWriter pw;
        String fileName = null;
        try
        {   
            //Getting userInput
            System.out.print("filename: ");
            Scanner sc = new Scanner(System.in);
            fileName = sc.nextLine();
            
            stream = new FileOutputStream(fileName);
            pw = new PrintWriter(stream);
            
            for(int i = 0; i < array.length; i++)
            {   
                int priority = array[i].getPriority();
                String value = (String) array[i].getValue();
                pw.println(priority+ "," + value);
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
