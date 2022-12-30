/*-------------------------------                                               
FILE: SimulationMode.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES: FileIO.java
PURPOSE: enter the simulation mode for gameofcatz.java
         read from file, generate routes and write to file
Last Mod: 12/10/2021                                                         
--------------------------------*/  
import java.io.*;
import java.util.*;

public class SimulationMode
{
    public static void enterMode(String [] args)
    {   
        if(args.length == 3)
        {
            /* For use to store information of the world from fileData*/
            World sWorld = new World();
            
            /*filter filedata and store in linkedlist*/
            DSALinkedList fileData = new DSALinkedList();
            FileIO.filterFile(fileData, args[1]);
            
            /*file read successful*/
            if(!fileData.isEmpty())
            {   
                System.out.println("Reading from " + args[1] + "...");
                runSimulation(fileData, sWorld, args[2]);
            }
        }
        else
        {
            System.out.println("Incorrect number of arguments for Simulation "
                                + "mode");
            gameofcatz.printUsage();
        }
    }
    

    

    //SUBMODULE: runSimulation 
    //IMPORT: fileData(DSALinkedList), sWorld(World), writeFileName(String)
    //EXPORT: void
    //ASSERTION: process data from file, generate and rank routes and then
    //write to file.
    //COMMENTS: any line that is faulty will stop the entire simulation

    private static void runSimulation(DSALinkedList fileData, World sWorld,
                                      String writeFileName)
    {
        try
        {
            /*process the data from the file*/
            getMappings(fileData, sWorld);  /* get mappings first */
            getGraphData(fileData, sWorld); /* get the rest of the data */
            
            /* generate routes, ranking and writing to file */
            System.out.println("Generating and ranking routes...");
            String start = sWorld.getStart();
            DSALinkedList targetList = sWorld.getTargetList();
            for(Object obj : targetList)
            {
                String target = (String) obj; 
                DSAHeapEntry[] sortedRoutes = getRoutes(sWorld, target);
               
                /* appending file*/
                FileIO.writeToFile(sortedRoutes, writeFileName, start, target);
            }
        }
        catch(IllegalArgumentException e) /*Thrown when processing file*/
        {
            System.out.println(e.getMessage());
            System.out.println("please fix errors in file!");
        }
    }
        



/*---------------------------------------------------------------------------*/
    //getMappings

    //SUBMODULE: getMappings
    //IMPORT: fileData(DSALinkedlist), sWorld(World)
    //EXPORT: nil
    //ASSERTION: go through fileData and get the node and edge mappings
    
    public static void getMappings(DSALinkedList fileData, World sWorld)
    {   
        DSAHashTable edgeMaps = new DSAHashTable();
        DSAHashTable nodeMaps = new DSAHashTable();

        /*iterating through fileData to get edge and node mappings*/
        for(Object data : fileData)
        {
            String line = (String) data;
            /*split line up into individual columns */
            String[] columns = FileIO.processLine(line);
            
            /* getting edge and node mappings */
            if(columns.length == 3) 
            {            
                if(columns[0].equals("Ncode")) /*node mappings*/
                {
                    getNodeMap(columns, nodeMaps);
                }            
                else if(columns[0].equals("Ecode")) /*edge mappings*/
                {
                    getEdgeMap(columns, edgeMaps);
                }
            }
        }

        //Setting nodeMaps and edgeMaps into sWorld 
        sWorld.setEdgeMaps(edgeMaps);
        sWorld.setNodeMaps(nodeMaps);
    }
    
    

    
    //SUBMODULE: getNodeMap
    //IMPORT: columns(Array of String), nodeMaps(DSAHashTable)
    //EXPORT: nil
    //ASSERTION: for the given line extract the mapping and store in nodeMaps

    private static void getNodeMap(String[] columns, DSAHashTable nodeMaps)
    {
        try
        {
            /* getting the mapping value for the nodeType*/
            String nodeType = columns[1];
            int nodeValue = Integer.parseInt(columns[2]);
            
            /*store the mapping*/
            nodeMaps.put(nodeType, nodeValue);
        }
        catch(NumberFormatException e)
        {
            /*Exception thrown by Integer.parseInt*/
            throw new NumberFormatException("Node Type " + columns[1] + 
                                            " does not map to an integer");
        }
    } 
    



    //SUBMODUE: getEdgeMap
    //IMPORT: columns(Array of String), nodeMaps(DSAHashTable)
    //EXPORT: nil
    //ASSERTION: for the given line extract the mapping and store in edgeMaps
    
    private static void getEdgeMap(String[] columns, DSAHashTable edgeMaps)
    {         
        try
        {
            /* getting the mapping value for the edgeType*/
            String edgeType = columns[1];
            int edgeValue = Integer.parseInt(columns[2]);

            /*store the mapping*/
            edgeMaps.put(edgeType, edgeValue);
        }
        catch(NumberFormatException e)
        {
            /*Exception throw by Integer.parseInt*/
            throw new NumberFormatException("Edge type " + columns[1] 
                                             + "does not map to an " +
                                             "integer");
        }
    }



    
/*---------------------------------------------------------------------------*/
    //getGraphData
    

    //SUBMODULE: getGraphData
    //IMPORT: fileData(DSALinkedList), sWorld(World)
    //EXPORT: nil
    //ASSERTION: go through fileData and get data to input into the graph
    
    public static void getGraphData(DSALinkedList fileData, World sWorld)
    {      
        /* nodeMaps and edgeMaps to validate input into graph*/ 
        DSAHashTable nodeMaps = sWorld.getNodeMaps();
        DSAHashTable edgeMaps = sWorld.getEdgeMaps();
        
        DSAGraph gameGraph = new DSAGraph();

        for(Object data : fileData)
        {   
            /* process line */
            String line = (String) data;
            String[] columns = FileIO.processLine(line);
        
            if(columns.length == 2) /*Should be the start and end targets*/
            {
                getTargets(columns, sWorld, gameGraph);
            }
            else if(columns.length == 3) /* Getting the Nodes */
            {   
                if(columns[0].equals("Node"))
                {
                    getNodes(columns, nodeMaps, gameGraph);
                }
            }
            else if(columns.length == 4) /*getting the edges*/
            {
                if(columns[0].equals("Edge"))
                {
                    getEdges(columns, edgeMaps, gameGraph);
                }
            }    
        }

        //into graph into world
        sWorld.setGraph(gameGraph);
    }
        
    
    
    
    //SUBMODULE: getTargets
    //IMPORT: column(Array of Strings), sWorld(World), DSAGraph gameGraph
    //EXPORT: nil
    //ASSERTION: get the start and end targets from the line

    private static void getTargets(String[] columns, World sWorld, 
                                   DSAGraph gameGraph)
    {   
        try
        {
            String firstWord = columns[0];
            if(firstWord.equals("Start"))
            {
                String start = columns[1].trim();
                gameGraph.getVertexValue(start); //validates if start exist
                 
                //inserting into World 
                sWorld.setStart(start);
            }
            else if (firstWord.equals("Target"))
            {
                String target = columns[1].trim();
                gameGraph.getVertexValue(target); //validates if target exist

                //inserting into World
                sWorld.insertTarget(columns[1].trim());
            }
        }
        catch(IllegalArgumentException e)
        {
            throw new IllegalArgumentException("Start or Target does not exist!");
        }
    } 

 

  
    //SUBMODULE: getNodes
    //IMPORT: column(Array of Strings), nodeMaps(DSAHashTable), gameGraph(DSAGraph)
    //EXPORT: nil
    //ASSERTION: get the start and end targets from the line

    private static void getNodes(String[] columns, DSAHashTable nodeMaps,
                                 DSAGraph gameGraph)
    {
        /* Node to insert into graph*/
        String nodeLabel = columns[1];
        String nodeType = columns[2];
        
        /* checking if node type even exists */
        try
        {   
            nodeMaps.get(nodeType); 
        }
        catch(IllegalArgumentException e)        
        {
            throw new IllegalArgumentException("Node type " + nodeType +
                                               " does not exist");
        }
        
        //inserting into graph
        try
        {
            gameGraph.addVertex(nodeLabel, nodeType);
        }
        catch(IllegalArgumentException e)
        {
            //let it fail silently, might be adding an existing vertex
        }
    } 

  

     
    //SUBMODULE: getEdges
    //IMPORT: column(Array of Strings), edgeMaps(DSAHashTable), gameGraph(DSAGraph)
    //EXPORT: nil
    //ASSERTION: get the start and end targets from the line
 
    private static void getEdges(String[] columns, DSAHashTable edgeMaps, 
                                 DSAGraph gameGraph)
    {
        /*Edge to insert into graph*/
        String source = columns[1];
        String sink = columns[2];
        String edgeType = columns[3];
        
        /* checking if edgeType exists first*/
        try
        {   
            edgeMaps.get(edgeType);
        }
        catch(IllegalArgumentException e)
        {
            throw new IllegalArgumentException("Edge Type " + columns[3] +
                                               "does not exist");
        }
        
        /* inserting into the graph*/
        try
        {
            gameGraph.addEdge(source, sink, edgeType);
        }
        catch(IllegalArgumentException e)
        {
            //let it fail silently, program can carry on if edge is not added.
        }
    } 




/*---------------------------------------------------------------------------*/
    //Generating and Ranking routes

    public static DSAHeapEntry[] getRoutes(World sWorld, String target)
    {
        DSAGraph gameGraph = sWorld.getGraph();
        DSAHashTable nodeMaps = sWorld.getNodeMaps();
        DSAHashTable edgeMaps = sWorld.getEdgeMaps();
        DSAHeapEntry[] sortedRoutes;
        
        /* generating routes*/
        DSAHeap unsortedRoutes = gameGraph.getAllPaths(sWorld.getStart(),
                                                       target, nodeMaps, 
                                                       edgeMaps);
        /* Sorting routes*/
        sortedRoutes = unsortedRoutes.heapSort();

        return sortedRoutes;
    }



}//End SimulationMode





