/*-------------------------------                                               
FILE: FileIO.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES:                                                     
Last Mod: 30/08/2021                                                            
--------------------------------*/  
import java.io.*;
import java.util.*;

public class FileIO
{     
    //SUBMODULE: fileRead
    //IMPORT: fileName(String), DSAHashTable hashTable
    //EXPORT: nil 
    //ASSERTION: get key and value from the file and put into hashTable.

    public static void fileRead(String fileName, DSAHashTable hashTable)
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

            while(line != null)
            {
                String[] column = processLine(line); 
                
                //This ensures that row can only have two values
                if(column.length == 2)
                {  
                    try
                    {   
                        String key = column[0];
                        String value = column[1];
                        
                        hashTable.put(key,value);
                    }
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
    //IMPORT: array(2D array of Objects) 
    //EXPORT: nil
    //ASSERTION: write tree values to a csv file.

    public static void writeToFile(Object [][] array)
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
            
            for(int i = 0; i < array[0].length; i++)
            {   
                String key = (String) array[0][i];
                String value = (String) array[1][i];
                pw.println(key+ "," + value);
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
