/*-------------------------------                                               
FILE: ModifyTarget.java                                              
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES: World.java
Last Mod: 16/10/2021         
PURPOSE: Contains methods used to change the targets in World.
-------------------------------*/
import java.io.*;
import java.util.*;

public class ModifyTarget
{

    //SUBMODULE: changeTargets
    //IMPORT: iWorld(World)
    //EXPORT: nil
    //ASSERTION: allow user to change start and end targets

    public static void changeTargets(World iWorld)
    {
        try
        {   
            //getting user input
            Scanner sc = new Scanner(System.in);
            System.out.println("(1) change start\n" +
                               "(2) add target\n" +
                               "(3) remove target\n" + 
                               "(4) exit");

            int choice = sc.nextInt();

            performChange(choice, iWorld);
        }
        catch(InputMismatchException e)
        {
            System.out.println("Invalid input!");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
    



    //SUBMODULE: performChange
    //IMPORT: choice(int), iWorld(World)
    //EXPORT: nil
    //ASSERTION: change targets based on selection
    
    private static void performChange(int choice, World iWorld)
    {
        switch(choice)
        {
            case 1: 
                changeStart(iWorld);
                break;

            case 2: 
                addTarget(iWorld);
                break;

            case 3: 
                removeTarget(iWorld);
                break;
        }
    }




    //SUBMODULE: changeStart
    //IMPORT: iWorld(World)
    //EXPORT: nil
    //ASSERTION: change start

    private static void changeStart(World iWorld)
    {
        DSAGraph gameGraph = iWorld.getGraph();
        String newStart = stringInput("New start: ");
        
        try
        {   
            //checking if newStart exists in gameGraph
            gameGraph.getVertexValue(newStart);
            
            //inserting into iWorld
            iWorld.setStart(newStart);
            System.out.println("Start changed to " + newStart);
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(newStart + " does not exist!");
        }
    }


    
    
    //SUBMODULE: addTarget
    //IMPORT: iWorld(World)
    //EXPORT: nil
    //ASSERTION: add new target to iWorld.

    private static void addTarget(World iWorld)
    {
        DSAGraph gameGraph = iWorld.getGraph();
        String newTarget = stringInput("New Target: ");

        try
        {   
            //checking if newTarget exists in gameGraph
            gameGraph.getVertexValue(newTarget);

            iWorld.insertTarget(newTarget);
            System.out.println(newTarget + " inserted");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(newTarget + " does not exist!");
        }
    }



    
    //SUBMODULE: removeTarget
    //IMPORT: iWorld(World)
    //EXPORT: nil
    //ASSERTION: remove target from targetList

    private static void removeTarget(World iWorld)
    {
        String targetToRemove = stringInput("Target to remove: ");
        try
        {
            //removing target from list
            DSALinkedList targetList = iWorld.getTargetList();
            checkTargetList(targetList, targetToRemove);
            System.out.println(targetToRemove + " removed");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(targetToRemove + " does not exist in target list");
        }
    }
            
    
    //SUBMODULE: checkTargetList
    //IMPORT: targetList(DSAHashTable), targetToRemove(String)
    //EXPORT: nil
    //ASSERTION: checking if targetToRemove is in targetList

    private static void checkTargetList(DSALinkedList targetList,
                                        String targetToRemove)
    {   
        boolean found = false;
        //checking targetList to see if removedNode is inside.
        for(Object obj : targetList)
        {
            String target = (String) obj;
            if(target.equals(targetToRemove))
            {   
                targetList.remove(target);
                found = true;
            }
        }

        if(found == false)
        {
            throw new IllegalArgumentException();
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


}//End AlterTarget
