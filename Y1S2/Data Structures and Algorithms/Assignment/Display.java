/*-------------------------------                                               
FILE: Display.java                                              
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES: World.java
Last Mod: 16/10/2021         
PURPOSE: Contains methods used to display graph and world.                                                   
--------------------------------*/
import java.io.*;
import java.util.*;

public class Display
{

    //DISPLAY GRAPH 
    
    //SUBMODULE: displayGraph
    //IMPORT: iWorld(World)
    //EXPORT: nil
    //ASSERTION: display the graph as an adjacency matrix. 
    public static void displayGraph(World iWorld)
    {
        DSAHashTable edgeMaps = iWorld.getEdgeMaps();
        DSAGraph gameGraph = iWorld.getGraph();
        
        //display graph
        if(!gameGraph.isEmpty())
        {
            displayMatrix(gameGraph, edgeMaps);
            optionToSaveDisplay(gameGraph, edgeMaps);
        }
        else
        {
            System.out.println("Graph is empty");
        }
    }


    
    //SUBMODULE: displayMatrix
    //IMPORT: gameGraph(DSAGraph), edgeMaps(DSAHashTable)
    //EXPORT: nil
    //ASSERTION: display adjacency matrix representation of graph.

    private static void displayMatrix(DSAGraph gameGraph, DSAHashTable edgeMaps)
    {    
        //getting matrix
        String[][] matrix = gameGraph.adjacencyMatrix(edgeMaps);
        int matrixLength = matrix[0].length - 1;

        //displaying graph
        for(int i = 0; i <= matrixLength; i++)
        {   
            for(int j = 0; j <= matrixLength; j++)
            {
                int padLength = largestLength(j, matrixLength, matrix); 
                System.out.print(padString(matrix[i][j], padLength) + " ");
            }

            System.out.println("");
            
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



    
    //SUBMODULE: optionToSaveDisplay
    //IMPORT: gameGraph(DSAGraph)
    //EXPORT: nil
    //ASSERTION: provide option to user to serialize graph and save to a file.
    
    private static void optionToSaveDisplay(DSAGraph gameGraph, 
                                            DSAHashTable edgeMaps)
    {   
        System.out.println("Save display?");
        System.out.println("1) Yes \n" + 
                           "2) No");
        
        //user input
        try
        {
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            
            //selected yes
            if(choice == 1)
            {
                //write display to file
                FileIO.writeGraphToFile(gameGraph, edgeMaps);
            }
        }
        catch(InputMismatchException e)
        {
            System.out.println("Input integers only!");
        }
    }
    



/*---------------------------------------------------------------------------*/
    //DISPLAY WORLD
    

    //SUBMODULE: displayWorld
    //IMPORT: iWorld(World)
    //EXPORT: nil
    //ASSERTION: display all details about the world.
    
    public static void displayWorld(World iWorld)
    {   
        //World variables
        DSAHashTable nodeMaps = iWorld.getNodeMaps();
        DSAHashTable edgeMaps = iWorld.getEdgeMaps();
        DSAGraph gameGraph = iWorld.getGraph();
        String start = iWorld.getStart();
        DSALinkedList targetList = iWorld.getTargetList();

        //graph is not empty
        if(!gameGraph.isEmpty())
        {
            //Displaying world
            System.out.println("\nInteractive World\n");
            displayMatrix(gameGraph);
            //Node stats and types
            displayNodeTypes(gameGraph, nodeMaps);
            //Edge stats and types
            displayEdgeTypes(gameGraph, edgeMaps);
            //targets
            displayTargets(start, targetList);
            
            //Option to save world
            optionToSaveWorld(iWorld);         
        }
        else
        {
            System.out.println("World is empty!");
        }
    }


 
    //SUBMODULE: displayMatrix
    //IMPORT: gameGraph(DSAGraph)    
    //EXPORT: nil
    //ASSERTION: display adjacency matrix representation of graph.

    private static void displayMatrix(DSAGraph gameGraph)
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
                System.out.print(padString(matrix[i][j], padLength) + " ");
            }

            System.out.println("");
            
        }
    }
  



    //SUBMODULE: displayNodeTypes
    //IMPORT: gameGraph(DSAGraph), nodeMaps(DSAHashTable)
    //EXPORT: nil
    //ASSERTION: display all node types and its corresponding stats

    private static void displayNodeTypes(DSAGraph gameGraph, DSAHashTable nodeMaps)
    {
        //Displaying nodeTypes
        System.out.print("\nNode Types: ");
        for(Object obj : nodeMaps)
        {   
            String nodeType = (String) obj;
            System.out.print(nodeType + " "); 
        }
        System.out.println("");

        //Displaying node Stats
        System.out.println("Stats: ");
        for(Object obj : nodeMaps)
        {
            String nodeType = (String) obj;
            int numNodes = gameGraph.countVertexType(nodeType);

            System.out.println(nodeType + ": " + numNodes);
        }
    }




    //SUBMODULE: displayEdgeTypes
    //IMPORT: DSAGraph gameGraph, edgeMaps(DSAHashTable)
    //EXPORT: nil
    //ASSERTION: display all edge types and its corresponding stats

    private static void displayEdgeTypes(DSAGraph gameGraph, DSAHashTable edgeMaps)
    {
        //Displaying edge types
        System.out.print("\nEdge Types: ");
        for(Object obj : edgeMaps)
        {
            String edgeType = (String) obj;

            System.out.print(edgeType + " ");
        }
        System.out.println("");
        
        //Displaying edge stats
        System.out.println("Stats: ");
        for(Object obj : edgeMaps)
        {
            String edgeType = (String) obj;
            int numEdges = gameGraph.countEdgeType(edgeType);

            System.out.println(edgeType + ": " + numEdges);
        }
        System.out.println("");
    }




    //SUBMODULE: displayTargets
    //IMPORT: start(String), targetList(DSAHashTable)
    //EXPORT: nil
    //ASSERTION: display targets of the world.
    
    private static void displayTargets(String start, DSALinkedList targetList)
    {
        //display start
        System.out.println("Start: " + start);
        
        //display targets
        System.out.print("Targets: ");
        for(Object target : targetList)
        {
            System.out.print(target + " ");
        }
        System.out.println("\n");
    }


    

    //SUBMODULE: optionToSaveWorld
    //IMPORT: iWorld(World)
    //EXPORT: nil
    //ASSERTION: user input to save representation of world.

    private static void optionToSaveWorld(World iWorld)
    {
        System.out.println("Save World display?");
        System.out.println("1) Yes \n" + 
                           "2) No");
        
        //user input
        try
        {
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            if(choice == 1)
            {
                FileIO.writeWorldToFile(iWorld);
            }
        }
        catch(InputMismatchException e)
        {
            System.out.println("Input integers only!");
        }
    }

}//End Display



