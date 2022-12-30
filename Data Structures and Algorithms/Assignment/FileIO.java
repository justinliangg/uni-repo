/*-------------------------------                                               
FILE: FileIO.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES:                                                     
Last Mod: 16/10/2021                                                            
--------------------------------*/  
import java.io.*;
import java.util.*;

public class FileIO
{   

    //SUBMODULE: filterFile
    //IMPORT: data(DSALinkedList), fileName(String)
    //EXPORT: nil
    //ASSERTION: read from fileName and store its content into data.

    public static void filterFile(DSALinkedList data, String fileName)
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
                //Ignoring any empty lines
                if(!line.isEmpty())
                {
                    //Ignoring any lines with # as first character
                    if(line.charAt(0) != '#')
                    {
                        /* Insert into linkedList for use later */
                        data.insertLast(line);
                    }
                }  

                line = bfr.readLine();
            }
            fileInput.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.print("File not found");
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
        inColumn = pRow.trim().split("\\s+");
        
        //Removing all white spaces between in the individual string. 
        for(int i = 0; i < inColumn.length; i++)
        {
            inColumn[i] = inColumn[i].replaceAll(" ", "");
        }
        return inColumn;
    }




    //SUBMODULE: writeToFile
    //IMPORT: array(array of DSAHeapEntry), fileName(String), start(String)
    //        target(String)
    //EXPORT: nil
    //ASSERTION: write routes togiven file

    public static void writeToFile(DSAHeapEntry[] array, String fileName,
                                   String start, String target)
    {   
        FileOutputStream stream = null;
        PrintWriter pw;
        try
        {  
            /*opening file stream*/
            stream = new FileOutputStream(fileName,true);
            pw = new PrintWriter(stream);
            
            /*title*/
            pw.println("\n# File Format\n"
                       + "# Route Cost");
            pw.println("For " + start + "->" + target);
            pw.println("");

            /*writing data to file*/
            for(int i = 0; i < array.length; i++)
            {   
                int priority = array[i].getPriority();
                Object value = array[i].getValue();

                pw.println(value + " " + priority);
            }
            System.out.println("Routes written to " + fileName);
            
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




/*---------------------------------------------------------------------------*/
    //Writing graphDisplay to file


    //SUBMODULE: writeGraphToFile
    //IMPORT: graph(DSAGraph), edgeMaps(DSAHashTable)
    //EXPORT: nil
    //ASSERTION: write the graph to a file.
    
    public static void writeGraphToFile(DSAGraph graph, DSAHashTable edgeMaps)
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
            
            //writing to file
            writeMatrixToFile(pw, graph, edgeMaps);
            System.out.println("Graph display written to " + fileName);

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



    
    //SUBMODULE: displayMatrix
    //IMPORT: gameGraph(DSAGraph), edgeMaps(DSAHashTable)
    //EXPORT: nil
    //ASSERTION: display adjacency matrix representation of graph.

    private static void writeMatrixToFile(PrintWriter pw, DSAGraph gameGraph,
                                          DSAHashTable edgeMaps)
    {     
        String[][] matrix = gameGraph.adjacencyMatrix(edgeMaps);
        int matrixLength = matrix[0].length - 1;

        //displaying graph
        for(int i = 0; i <= matrixLength; i++)
        {   
            for(int j = 0; j <= matrixLength; j++)
            {
                int padLength = largestLength(j, matrixLength, matrix); 
                pw.print(padString(matrix[i][j], padLength) + " ");
            }

            pw.println("");
            
        }
    }

    

    //SUBMODULE: largestLength
    //IMPORT: j(int), matrixLength(int), matrix(String[][])
    //EXPORT: largestLength
    //ASSERTION: search through a given column and find the greatest string
    // length in the column

    private static int largestLength(int j, int matrixLength, String[][] matrix)
    {
        int largestLength = matrix[0][j].length();
        
        //for a given column, iterate through the row to find the largest 
        //string length
        for(int i = 1; i < matrixLength; i++)
        {
            if(matrix[i][j].length() > largestLength)
            {
                largestLength = matrix[i][j].length();
            }
        }

        return largestLength;

    }
            
        

    //SUBMODULE: padString
    //IMPORT: input(String), n(int)
    //EXPORT: input(String)
    //ASSERTION: pad a string with space so that the string has length n

    private static String padString(String input, int n)
    {
        //padding only strings that are smaller than the biggest string 
        if(input.length() < n)
        {
            while(input.length() != n)
            {
                input = " " + input;
            }
        }

        return input;
    }




/*---------------------------------------------------------------------------*/
    //Writing worldDisplay to file

    //SUBMODULE: writeWorldToFile
    //IMPORT: world(World)
    //EXPORT: nil
    //ASSERTION: write display of World to file.
    
    public static void writeWorldToFile(World world)
    {
        DSAGraph gameGraph = world.getGraph();
        DSAHashTable nodeMaps = world.getNodeMaps();
        DSAHashTable edgeMaps = world.getEdgeMaps();
        String start = world.getStart();
        DSALinkedList targetList = world.getTargetList();

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
            
            //writing world to file
            writeMatrixToFile(pw, gameGraph);
            writeNodeTypes(pw, gameGraph, nodeMaps);
            writeEdgeTypes(pw, gameGraph, edgeMaps);
            writeTargets(pw, start, targetList);
            System.out.println("World display written to " + fileName);

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



        
    //SUBMODULE: writeMatrix
    //IMPORT: gameGraph(DSAGraph)    
    //EXPORT: nil
    //ASSERTION: write adjacency matrix representation of graph to file

    private static void writeMatrixToFile(PrintWriter pw, DSAGraph gameGraph)
    {   
        //getting matrix from gameGraph
        String[][] matrix = gameGraph.adjacencyMatrix();
        int matrixLength = matrix[0].length - 1;

        //displaying graph
        for(int i = 0; i <= matrixLength; i++)
        {   
            for(int j = 0; j <= matrixLength; j++)
            {
                int padLength = largestLength(j, matrixLength, matrix); 
                pw.print(padString(matrix[i][j], padLength) + " ");
            }

            pw.println("");
            
        }
    }
  



    //SUBMODULE: writeNodeTypes
    //IMPORT: pw(PrintWriter), gameGraph(DSAGraph), nodeMaps(DSAHashTable)
    //EXPORT: nil
    //ASSERTION: write all node types and its corresponding value to file

    private static void writeNodeTypes(PrintWriter pw, DSAGraph gameGraph, 
                                       DSAHashTable nodeMaps)
    {
        //Displaying nodeTypes
        pw.print("\nNode Types: ");
        for(Object obj : nodeMaps)
        {   
            String nodeType = (String) obj;
            pw.print(nodeType + " "); 
        }
        pw.println("");

        //Displaying node Stats
        pw.println("Stats: ");
        for(Object obj : nodeMaps)
        {
            String nodeType = (String) obj;
            int numNodes = gameGraph.countVertexType(nodeType);

            pw.println(nodeType + ": " + numNodes);
        }
    }

    


    //SUBMODULE: writeEdgeTypes
    //IMPORT: pw(PrintWriter), gameGraph(DSAGraph), edgeMaps(DSAHashTable)
    //EXPORT: nil
    //ASSERTION: write all edge types and its corresponding value to file

    private static void writeEdgeTypes(PrintWriter pw, DSAGraph gameGraph,
                                       DSAHashTable edgeMaps)
    {
         //Displaying edge types
        pw.print("\nEdge Types: ");
        for(Object obj : edgeMaps)
        {
            String edgeType = (String) obj;

           pw.print(edgeType + " ");
        }
        pw.println("");
        
        //Displaying edge stats
        pw.println("Stats: ");
        for(Object obj : edgeMaps)
        {
            String edgeType = (String) obj;
            int numEdges = gameGraph.countEdgeType(edgeType);

            pw.println(edgeType + ": " + numEdges);
        }
        pw.println("");
    }




    //SUBMODULE: writeTargets
    //IMPORT: start(String), targetList(DSAHashTable)
    //EXPORT: nil
    //ASSERTION: write targets of the world.
    
    private static void writeTargets(PrintWriter pw, String start, 
                                     DSALinkedList targetList)
    {
        //display start
        pw.println("Start: " + start);
        
        //display targets
        pw.println("Targets: ");
        for(Object target : targetList)
        {
            pw.print(target + " ");
        }
        pw.println("");
    }

 


/*---------------------------------------------------------------------------*/
    //SERIALIZATION 

    //SUBMODULE: serializeToFile
    //IMPORT: objectToSave(Object)
    //EXPORT: nil
    //ASSERTION: take object, serialize it and transfer to a file given by user
    //COMMENTS: contains code obtained from DSA Prac 05

    public static void serializeToFile(Object objectToSave)
    {        
        FileOutputStream outputStream;
        ObjectOutputStream objectStream;
        String fileName;
        try
        {
            //Getting user Input
            System.out.print("filename: ");
            Scanner sc = new Scanner(System.in);
            fileName = sc.nextLine();
            
            //Writing to file
            outputStream = new FileOutputStream(fileName);
            objectStream = new ObjectOutputStream(outputStream);
            objectStream.writeObject(objectToSave); 
            objectStream.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to save object to file");
        }

        System.out.println("write file complete!\n");
    } 



    //SUBMODULE: readSerializedFile
    //IMPORT: world(World)
    //EXPORT: nil
    //ASSERTION: read serialized file and store inside world.
    //COMMENTS: contains code used in DSA Prac 05.

    public static World readSerializedFile()
    {
        String fileName;
        FileInputStream fileStream;
        ObjectInputStream objectStream;
        World newWorld = null;
        try
        {
            //Getting file name from user
            System.out.print("filename: ");
            Scanner sc = new Scanner(System.in);
            fileName = sc.nextLine();

            //processing file and getting the serialized object
            fileStream = new FileInputStream(fileName);
            objectStream  = new ObjectInputStream(fileStream);
            newWorld = (World) objectStream.readObject();
            objectStream.close();
            
            System.out.println("file read complete!\n");
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Class World not found");
        }
        catch(Exception e)
        {
            System.out.println("Unable to load object from file");
        }

        
        return newWorld;
    }
}
