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
    //IMPORT: fileName(String), pTree(DSABinarySearchTree)
    //EXPORT: studentArray(Array of Students)
    //ASSERTION: store values from file into studentArray

    public static void fileRead(String fileName, DSABinarySearchTree pTree)
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
                        int dummy = Integer.parseInt(column[0]);
                        String key = column[0];
                        int value = Integer.parseInt(column[1]);
                        
                        pTree.insert(key,value);
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
            System.out.println("file read complete!\n");
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
    //ASSERTION: parse pRow every ","

    public static String[] processLine(String pRow)
    {
        String [] inColumn;
        //splits every "," 
        inColumn = pRow.split(",");
        
        //Removing all white spaces between ,
        for(int i = 0; i < inColumn.length; i++)
        {
            inColumn[i] = inColumn[i].trim();
        }
        return inColumn;
    }



 
    //SUBMODULE: writeToFile
    //IMPORT: queue(DSAQueue), tree(DSABinarySearchTree) 
    //EXPORT: nil
    //ASSERTION: write tree values to a csv file.

    public static void writeToFile(DSAQueue queue, DSABinarySearchTree tree)
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
            
            for(Object o : queue)
            {   
                int value = (int) o;

                pw.println(value+ "," + value);
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
