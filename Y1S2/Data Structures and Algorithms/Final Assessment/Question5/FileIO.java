/*-------------------------------                                               
FILE: FileIO.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES:                                                     
Last Mod: 30/08/2021    
COMMENTS: Retrieved from Prac 06 
--------------------------------*/  
import java.io.*;
import java.util.*;

public class FileIO
{     
    
    //SUBMODULE: fileReadEdges
    //IMPORT: fileName(String), Graph graph
    //EXPORT: nil 
    //ASSERTION: get edges from a file

    public static void fileReadEdges(String fileName, Q5Graph graph)
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
                
                //This ensures that row can only have 3 values
                if(column.length == 3)
                {  
                    try
                    {   
                        String vertex1 = column[0];
                        String vertex2 = column[1];
                        int weight = Integer.parseInt(column[2]);
                        
                        graph.addVertex(vertex1);
                        graph.addVertex(vertex2);

                        graph.addEdge(vertex1, vertex2, weight);
                    }
                    catch(NumberFormatException e)
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
        inColumn = pRow.split(" ");
        
        //Removing all white spaces between " " 
        for(int i = 0; i < inColumn.length; i++)
        {
            inColumn[i] = inColumn[i].trim();
        }
        return inColumn;
    }



}
