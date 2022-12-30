/*-------------------------------                                               
FILE: Operations.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES:  nil 
PURPOSE: holds static functions for both node and edge operations. 
COMMMENTS: Edge operations is at second half of the file.                                                 
Last Mod: 13/10/2021                                                            
--------------------------------*/  
import java.io.*;
import java.util.*;

public class Operations
{

    //SUBMODULE: nodeOperations
    //IMPORT: freeWorld(World)
    //EXPORT: nil
    //ASSERTION: perform node operations based on user input, continue
    //looping till exit is selected.

    public static void nodeOperations(World freeWorld)
    {
        int choice = 0;
        while(choice != 5)
        {   
            try
            {
                //display menu
                nodeOperationsMenu();
            
                //get user input
                Scanner sc = new Scanner(System.in);
                choice = sc.nextInt();
            
                //perform choice
                performNodeOperations(choice, freeWorld);
            }
            catch(InputMismatchException e)
            {
                System.out.println("please enter a valid integer");
            }
            catch(IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }




    //SUBMODULE: nodeOperationsMenu
    //IMPORT: nil
    //EXPORT: nil
    //ASSERTION: display the menu for node operations

    private static void nodeOperationsMenu()
    {
        System.out.println("\nNode Operations Menu");
        System.out.println("===============================================\n"+ 
                           "(1) find\n" +
                           "(2) insert\n" +
                           "(3) delete\n" +
                           "(4) update\n" +
                           "(5) return to main menu");
    }

    



    //SUBMODULE: performNodeOperations
    //IMPORT: choice(int), freeWorld(World)
    //EXPORT: nil
    //ASSERTION: based on user input, perform node operations on the gameGraph.

    private static void performNodeOperations(int choice, World freeWorld)
    {   
        /* retriveing data used for operations*/
        DSAGraph gameGraph = freeWorld.getGraph();
        DSAHashTable nodeMaps = freeWorld.getNodeMaps();
        DSALinkedList targetList = freeWorld.getTargetList();

        switch(choice)
        {
            case 1: 
                findNode(gameGraph, nodeMaps); 
                break;

            case 2:
                insertNode(gameGraph, nodeMaps);
                break;

            case 3: 
                deleteNode(gameGraph, targetList);
                break;

            case 4:
                updateNode(gameGraph, nodeMaps);
                break;
        }
    }

    


    //SUBMODULE: findNode
    //IMPORT: gameGraph(DSAGraph)
    //EXPORT: nil
    //ASSERTION: find a node from the node label given by user and return
    // the nodeType.

    private static void findNode(DSAGraph gameGraph, DSAHashTable nodeMaps)
    {
        // user input for node label
        String nodeLabel = stringInput("Node Label: ");

        //searching in graph
        try
        {
            String nodeType = (String) gameGraph.getVertexValue(nodeLabel);
            int nodeValue = (int) nodeMaps.get(nodeType);
            System.out.println("Node Type: " + nodeType);
            System.out.println("Node Value: " + nodeValue); 
        }
        catch(IllegalArgumentException e)  
        {
            System.out.println("Error! " + nodeLabel +" does not exist");
        }
    }

    


    //SUBMODULE: insertNode
    //IMPORT: gameGraph(DSAGraph), nodeMaps(DSAHashTable)
    //EXPORT: nil
    //ASSERTION: get node label and node type from user, insert the node 
    // into gameGraph.

    private static void insertNode(DSAGraph gameGraph, DSAHashTable nodeMaps)
    {   
        //getting user input
        String nodeLabel = stringInput("Node Label: ");
        String nodeType = stringInput("Node Type: ");
            
        //checking if nodeType exists first
        try
        {
            nodeMaps.get(nodeType); 
        }
        catch(IllegalArgumentException e)
        {
            throw new IllegalArgumentException("Error! " + nodeType + 
                                               " does not exist");
        }    
        
        //adding node to graph
        try
        {
            gameGraph.addVertex(nodeLabel, nodeType);
            System.out.println("Node " + nodeLabel + " inserted");
        }
        catch(IllegalArgumentException e)
        {   
            System.out.println("Error! " + nodeLabel + " already exist");
        }

    }

    

    
    //SUBMODULE: deleteNode
    //IMPORT: gameGraph(DSAGraph)
    //EXPORT: nil
    //ASSERTION: get node label from user and delete the node from the graph

    private static void deleteNode(DSAGraph gameGraph, DSALinkedList targetList)
    {        
        //User input
        String nodeLabel = stringInput("Node Label: ");
        
        //removing graph
        try
        {   
            gameGraph.removeVertex(nodeLabel);
            checkTargetList(targetList, nodeLabel); 
            System.out.println(nodeLabel + " deleted");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Error! " + nodeLabel + " does not exist!");
        }
    }

    
    //SUBMODULE: checkTargetList
    //IMPORT: targetList(DSAHashTable), removedNode(String)
    //EXPORT: nil
    //ASSERTION: if deleted node is in targetList, remove the node too.

    private static void checkTargetList(DSALinkedList targetList,
                                        String removedNode)
    {   
        //checking targetList to see if removedNode is inside.
        for(Object obj : targetList)
        {
            String target = (String) obj;
            if(target.equals(removedNode))
            {
                targetList.remove(target);
            }
        }
    }

    


    //SUBMODULE: updateNode
    //IMPORT: gameGraph(DSAGraph), nodeMaps(DSAHashTable)
    //EXPORT: nil
    //ASSERTION: get node label and new nodeType from user, update the node
    // with the new nodeType in gameGraph

    private static void updateNode(DSAGraph gameGraph, DSAHashTable nodeMaps)
    {    
        //User input
        String nodeLabel = stringInput("Node Label: ");
        String nodeType = stringInput("New Node Type: ");
          
        //checking if nodeType exists first
        try
        { 
            nodeMaps.get(nodeType);
        }
        catch(IllegalArgumentException e)
        {
            throw new IllegalArgumentException("Error! " + nodeType + 
                                                " does not exist");
        }   
        
        //updating node value
        try
        {
            gameGraph.setVertexValue(nodeLabel, nodeType);
            System.out.println(nodeType + " updated successfully");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Error! " + nodeLabel + " does not exist");
        }
    }
    



/*---------------------------------------------------------------------------*/
    //EDGE OPERATIONS


    //SUBMODULE: edgeOperations
    //IMPORT: freeWorld(World)
    //EXPORT: nil
    //ASSERTION: perform edge operations based on user input, continue
    //looping till exit is selected.

    public static void edgeOperations(World freeWorld)
    {
        int choice = 0;
        while(choice != 5)
        {   
            try
            {
                //display menu
                edgeOperationsMenu();
            
                //get user input
                Scanner sc = new Scanner(System.in);
                choice = sc.nextInt();
            
                //perform choice
                performEdgeOperations(choice, freeWorld);
            }
            catch(InputMismatchException e)
            {
                System.out.println("please enter a valid integer");
            }
            catch(IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }


 
    
    //SUBMODULE: edgeOperationsMenu
    //IMPORT: nil
    //EXPORT: nil
    //ASSERTION: display the menu for edge operations

    private static void edgeOperationsMenu()
    {
        System.out.println("\nEdge Operations Menu");
        System.out.println("===============================================\n"+ 
                           "(1) find\n" +
                           "(2) add\n" +
                           "(3) remove\n" +
                           "(4) update\n" +
                           "(5) return to main menu");
    }

 


    //SUBMODULE: performEdgeOperations
    //IMPORT: choice(int), freeWorld(World)
    //EXPORT: nil
    //ASSERTION: based on user input, perform edge operations on the gameGraph.

    private static void performEdgeOperations(int choice, World freeWorld)
    {   
        /* retriveing data used for operations*/
        DSAGraph gameGraph = freeWorld.getGraph();
        DSAHashTable edgeMaps = freeWorld.getEdgeMaps();
        
        switch(choice)
        {
            case 1: 
                findEdge(gameGraph, edgeMaps); 
                break;

            case 2:
                addEdge(gameGraph, edgeMaps);
                break;

            case 3: 
                removeEdge(gameGraph, edgeMaps);
                break;

            case 4:
                updateEdge(gameGraph, edgeMaps);
                break;
        }
    }
    



    //SUBMODULE: findEdge
    //IMPORT: gameGraph(DSAGraph), edgeMaps(DSAHashTable)
    //EXPORT: nil
    //ASSERTION: get user input to find edge, return edgeType and value
    
    private static void findEdge(DSAGraph gameGraph, DSAHashTable edgeMaps)
    { 
        //getting user input
        String sourceLabel = stringInput("Edge source: ");
        String sinkLabel = stringInput("Edge sink: ");
        
        //Finding edge in graph
        try
        {
            String edgeType= (String) gameGraph.getEdgeWeight(sourceLabel, 
                                                              sinkLabel);
            int edgeWeight = (int) edgeMaps.get(edgeType);
            
            System.out.println("Edge type: " + edgeType + ", Edge Weight: " +
                               edgeWeight);
        }
        catch(IllegalArgumentException e)
        {   
            System.out.println("Error! " + "Edge " + sourceLabel + sinkLabel + 
                               " does not exist");
        }
    }




    //SUBMODULE: addEdge
    //IMPORT: gameGraph(DSAGraph), edgeMaps(DSAHashTable)
    //EXPORT: nil
    //ASSERTION: get user input for an edge and its edgeType and insert
    // into graph

    private static void addEdge(DSAGraph gameGraph, DSAHashTable edgeMaps)
    {
        //getting user input
        String sourceLabel = stringInput("Edge source: ");
        String sinkLabel = stringInput("Edge sink: ");
        String edgeType = stringInput("Edge Type: ");

        //checking if edgeType exists first
        try
        {
            edgeMaps.get(edgeType);
        }
        catch(IllegalArgumentException e)
        {
            throw new IllegalArgumentException("Error! Edge Type: " + edgeType + 
                                               " does not exist");
        }

        //inserting into graph
        try
        {
            gameGraph.addEdge(sourceLabel, sinkLabel, edgeType);
            System.out.println("Edge: " + sourceLabel + sinkLabel +
                               " inserted");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Error! " + "Node does not exist!");
        }
    }

  


    //SUBMODULE: removeEdge
    //IMPORT: gameGraph(DSAGraph), edgeMaps(DSAHashTable)
    //EXPORT: nil
    //ASSERTION: get user input for an edge and remove it from gameGraph
    
    private static void removeEdge(DSAGraph gameGraph, DSAHashTable edgeMaps)
    {
        //getting user input
        String sourceLabel = stringInput("Edge source: ");
        String sinkLabel = stringInput("Edge sink: ");

        //removing from graph
        try
        {
            gameGraph.removeEdge(sourceLabel, sinkLabel);
            System.out.println("Edge: " + sourceLabel + sinkLabel +
                               " removed");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Error! Edge: " + sourceLabel + sinkLabel + 
                               " does not exist");
        }
    }
          

  

    //SUBMODULE: updateEdge
    //IMPORT: gameGraph(DSAGraph), edgeMaps(DSAHashTable)
    //EXPORT: nil
    //ASSERTION: get user input for an edge and new edgeType and update 
    // the edge

    private static void updateEdge(DSAGraph gameGraph, DSAHashTable edgeMaps)
    {
        //getting user input
        String sourceLabel = stringInput("Edge source: ");
        String sinkLabel = stringInput("Edge sink: ");
        String newEdgeType = stringInput("New Edge Type: ");
        
        //Checking if edgeType exists first
        try
        {
            edgeMaps.get(newEdgeType);
        }
        catch(IllegalArgumentException e)
        {
            throw new IllegalArgumentException("Error! Edge Type: " 
                                               + newEdgeType + " does not exist");
        }

        //updating edge
        try
        {
            gameGraph.setEdgeWeight(sourceLabel, sinkLabel, newEdgeType);
            System.out.println("Edge: " + sourceLabel + sinkLabel +
                               " updated");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Error! ");
        }
    }




    //SUBMODULE: stringInput
    //IMPORT: label(String)
    //EXPORT: nil
    //ASSERTION: print label to terminal, get user input for a string.
     
    private static String stringInput(String label) throws 
                                                    IllegalArgumentException
    {
        Scanner sc = new Scanner(System.in);
        System.out.print(label);
        String input = sc.nextLine().replaceAll(" ", "");
        
        if(input.equals(""))
        {
            throw new IllegalArgumentException("Invalid string input!");
        }

        return input;
    }



}//End Operations
