/*-------------------------------                                               
FILE: World.java                                              
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES: DSAGraph.java, DSAHashTable.java, DSALinkedList.java
PURPOSE: stores all relevant information of the "World".
Last Mod: 13/10/2021                                                            
--------------------------------*/
import java.io.*;
import java.util.*;

public class World implements Serializable
{
    //Class Fields
    private DSAGraph graph; 
    private DSAHashTable nodeMaps;
    private DSAHashTable edgeMaps; 
    private String start;
    private DSALinkedList targetList;
    
    
    //Constructor
    public World()
    {
        graph = new DSAGraph();
        nodeMaps = new DSAHashTable();
        edgeMaps = new DSAHashTable();
        start = "";
        targetList = new DSALinkedList();
    }


/*---------------------------------------------------------------------------*/
    //Accessors
    
    public DSAGraph getGraph()
    {
        return graph;
    }

    public DSAHashTable getNodeMaps()
    {
        return nodeMaps;
    }

    public DSAHashTable getEdgeMaps()
    {
        return edgeMaps;
    }

    public String getStart()
    {
        return start;
    }

    public DSALinkedList getTargetList()
    {
        return targetList;
    }



/*---------------------------------------------------------------------------*/
    //Mutators
    
    public void setGraph(DSAGraph pGraph)
    {
        if(pGraph != null)
        {
            graph = pGraph;
        }
    }
    
   

    public void setEdgeMaps(DSAHashTable pEdgeMaps)
    {
        if(pEdgeMaps != null)
        {
            edgeMaps = pEdgeMaps;
        }
    }
   


    public void setNodeMaps(DSAHashTable pNodeMaps)
    {
        if(pNodeMaps != null)
        {
            nodeMaps = pNodeMaps;
        }
    }



    public void setStart(String pStart)
    {
        if(pStart != null && !pStart.isEmpty())
        {
            start = pStart;
        }
        else
        {
            throw new IllegalArgumentException("Start string is empty or null");
        }
    }



    public void insertTarget(String pTarget)
    {
        if(pTarget != null && !pTarget.isEmpty())
        {
            targetList.insertLast(pTarget);
        }
        else
        {
            throw new IllegalArgumentException("Target string is empty or null");
        }
    }



    public void copy(World pWorld)
    {
        if(pWorld != null)
        {
            graph = pWorld.getGraph();
            edgeMaps = pWorld.getEdgeMaps();
            nodeMaps = pWorld.getNodeMaps();
            start = pWorld.getStart();
            targetList= pWorld.getTargetList();
        }
        else
        {
            throw new IllegalArgumentException("World is empty!");
        }
    }
         

        
}

        
