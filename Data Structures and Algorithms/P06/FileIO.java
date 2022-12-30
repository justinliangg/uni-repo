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
    //SUBMODULE: fileReadEdges
    //IMPORT: fileName(String), DSAGraph graph
    //EXPORT: nil 
    //ASSERTION: get edges from a file

    public static void fileReadEdges(String fileName, DSAGraph graph)
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
                        String vertex1 = column[0];
                        String vertex2 = column[1];
                       
                        graph.addEdge(vertex1, vertex2, "0");
                    }
                    //Exception thrown by addEdge to validate the 
                    //values
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


    //SUBMODULE: fileReadVertices
    //IMPORT: fileName(String), DSAGraph graph
    //EXPORT: nil 
    //ASSERTION: get vertices from a file

    public static void fileReadVertices(String fileName, DSAGraph graph)
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
                    String vertex = column[0];
                    String vertex2 = column[1];

                    graph.addVertex(vertex,0);
                    graph.addVertex(vertex2,0);
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



/* 
    //SUBMODULE: writeToFile
    //IMPORT: queue(DSAQueue), graph(DSAGraph) 
    //EXPORT: nil
    //ASSERTION: write tree values to a csv file.

    public static void writeToFile(DSAQueue queue, DSAGraph graph)
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
  */  
}
