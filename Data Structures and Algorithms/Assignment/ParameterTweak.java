/*-------------------------------                                               
FILE: ParameterTweak.java                                              
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES: World.java
Last Mod: 16/10/2021         
PURPOSE: Contains methods used to tweak parameters in World.
--------------------------------*/ 
import java.io.*;
import java.util.*;

public class ParameterTweak
{    

    //SUBMODULE: paramTweaks
    //IMPORT: iWorld(World)
    //EXPORT: nil
    //ASSERTION: allow user to modify node and edge mappings.

    public static void paramTweaks(World iWorld)
    {
        DSAHashTable nodeMaps = iWorld.getNodeMaps();
        DSAHashTable edgeMaps = iWorld.getEdgeMaps();

        int choice = 0;
        while(choice != 5)
        {   
            try
            {
                //display menu
                paramTweaksMenu();
            
                //get user input
                Scanner sc = new Scanner(System.in);
                choice = sc.nextInt();
            
                //perform choice
                performParamTweaks(choice, nodeMaps, edgeMaps);
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
     
    

    
    //SUBMODULE: paramTweaksMenu
    //IMPORT: nil
    //EXPORT: nil
    //ASSERTION: display the menu for paramtweaks for the user.

    private static void paramTweaksMenu()
    {
        System.out.println("\nParameter Tweaks Menu");
        System.out.println("===============================================\n"+ 
                           "(1) Tweak node type value\n" +
                           "(2) Tweak edge type value\n" +
                           "(3) Add node type\n" +
                           "(4) Add edge type\n" +
                           "(5) return to main menu");
    }
    
    


    //SUBMODULE: performParamTweaks
    //IMPORT: choice(int), nodeMaps(DSAHashTable), edgeMaps(DSAHashTable)
    //EXPORT: nil
    //ASSERTION: perform tweaks based on user's choice from the menu.

    private static void performParamTweaks(int choice, DSAHashTable nodeMaps, 
                                           DSAHashTable edgeMaps)
    {
        switch(choice)
        {
            case 1:
                tweakNodeValue(nodeMaps);     
                break;

            case 2: 
                tweakEdgeValue(edgeMaps);
                break;

            case 3:
                addNodeType(nodeMaps);
                break;

            case 4: 
                addEdgeType(edgeMaps);
                break;
        }
    }
    
    


    //SUBMODULE: tweakNodeValue
    //IMPORT: nodeMaps(DSAHashTable)
    //EXPORT: nil
    //ASSERTION: get user input for nodeType and newNodeValue,
    // change to newNodeValue for the given nodeType.

    private static void tweakNodeValue(DSAHashTable nodeMaps) 
    {
        //User input
        String nodeType = stringInput("Node Type: ");
        int newNodeValue = integerInput("New Node Value: ");
        
        try
        {
            //checking if nodeType exists first
            nodeMaps.get(nodeType);

            //changing nodeValue
            nodeMaps.remove(nodeType);
            nodeMaps.put(nodeType, newNodeValue);
            System.out.println("Node Value changed to " + newNodeValue);
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Error! Node Type: " + nodeType + 
                                               " does not exist");
        }
    }




    //SUBMODULE: tweakEdgeValue
    //IMPORT: edgeMaps(DSAHashTable)
    //EXPORT: nil
    //ASSERTION: get user input for the edgeType and newEdgeValue
    // change to newEdgeValue for the given edgeType.

    private static void tweakEdgeValue(DSAHashTable edgeMaps) 
    {
        //User input
        String edgeType = stringInput("Edge Type: ");
        int newEdgeValue = integerInput("Edge Value: ");

        try
        {
            //checking if edgeType exists first
            edgeMaps.get(edgeType);

            //changing edge value
            edgeMaps.remove(edgeType);
            edgeMaps.put(edgeType, newEdgeValue);
            System.out.println("Edge Value changed to " + newEdgeValue);
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Error! Edge Type: " + edgeType + 
                                " does not exist");
        }
    }
    


    
    //SUBMODULE: addNodeType
    //IMPORT: nodeMaps(DSAHashTable)
    //EXPORT: nil
    //ASSERTION: get user input for a nodeType and nodeValue, insert into
    // nodeMaps.

    private static void addNodeType(DSAHashTable nodeMaps)
    {
        //User input
        String nodeType = stringInput("Node Type: ");
        int nodeValue = integerInput("Node Value: ");
        
        //trying to put into nodeMap
        try
        {   
            //inserting nodeType and nodeValue into table
            nodeMaps.put(nodeType, nodeValue);
            System.out.println("Node Type " + nodeType + " inserted"); 
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Error!  Node Type: " + nodeType + 
                               " already exists");
        }
            
    }
    


    
    //SUBMODULE: addEdgeType
    //IMPORT: edgeMaps(DSAHashTable)
    //EXPORT: nil
    //ASSERTION: get user input for edgeType and nodeValue, insert into
    // edgeMaps
    
    private static void addEdgeType(DSAHashTable edgeMaps)
    {
        //User input
        String edgeType = stringInput("Edge Type: ");
        int edgeValue = integerInput("Edge Value: ");
        
        //trying to put into edgeMap
        try
        {
            edgeMaps.put(edgeType, edgeValue);
            System.out.println("Edge Type " + edgeType + " inserted"); 
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Error! Edge Type: " + edgeType + 
                               " already exists");
        }
    }
        
      

   
    //SUBMODULE: integerInput
    //IMPORT: label(String)
    //EXPORT: int
    //ASSERTION: print label to terminal, get user input for an integer

    private static int integerInput(String label) throws 
                                                      IllegalArgumentException
    {    
        int input;
        try
        {
            //User input
            Scanner sc = new Scanner(System.in);
            System.out.print(label);
            input = sc.nextInt();
        }
        catch(InputMismatchException e)
        {   
            throw new IllegalArgumentException(label + " has to be an" +
                                                " integer!");
        }

        return input;
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


}//End ParameterTweak



