/*-------------------------------                                               
FILE: DSAGraph.java                                              
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES: DSALinkedList.java, DSAStack.java, DSAQueue.java
Last Mod: 08/09/2021                                                            
--------------------------------*/
import java.util.*;
import java.io.*;

public class DSAGraph implements Serializable
{
    //Class Fields
    private DSALinkedList vertices;
    private DSALinkedList edges;
    private int vertexCount;
    private int edgeCount;


/*----------------------------------------------------------------------------*/
    //CONSTRUCTOR
    
    public DSAGraph()
    {
        vertices = new DSALinkedList();
        edges = new DSALinkedList();
        vertexCount = 0;
        edgeCount = 0;
    }
    


/*----------------------------------------------------------------------------*/
    //MUTATORS


    //addVertex
    public void addVertex(String label, Object value)
    {   
        //Checking if vertex exists first
        if(hasVertex(label))
        {
            //Let it fail silently
        }
        else
        {
            //Creating a new vertex object
            DSAGraphVertex newVertex = new DSAGraphVertex(label, value); 
            //Inserting into the list of vertices
            vertices.insertLast(newVertex);
            vertexCount++;
        }
    }
    


    //addEdge
    public void addEdge(String label1, String label2, String weight)
    {
        //Validated in getVertex, if vertex exists 
        DSAGraphVertex sourceVertex = getVertex(label1);
        DSAGraphVertex sinkVertex = getVertex(label2);

        //Creating a newEdge 
        DSAGraphEdge newEdge = new DSAGraphEdge(sourceVertex, sinkVertex
                                                ,weight);
        //add to edge list
        edges.insertLast(newEdge);
        edgeCount++;
    }



/*----------------------------------------------------------------------------*/ 
    //ACCESSORS


    //hasVertex
    
    public boolean hasVertex(String label)
    {   
        boolean found = false;
        Iterator vertexList = vertices.iterator();

        //Iterating through vertices
        while(vertexList.hasNext() && !found)
        {
            DSAGraphVertex currVertex = (DSAGraphVertex) vertexList.next();
            //Checking if the imported label matches any in vertices list.
            if(label.equals(currVertex.getLabel()))
            {
                found = true;
            }
        }

        return found;
    }



    //getVertexCount
    
    public int getVertexCount()
    {
        return vertexCount;
    }


    
    //getEdgeCount

    public int getEdgeCount()
    {
        return edgeCount;
    }
    


    //getEdgeWeight

    public String getEdgeWeight(String source, String sink)
    {
        DSAGraphEdge edge = (DSAGraphEdge) getEdge(source, sink);
        
        return edge.getWeight();
    }



    //getVertexValue

    public Object getVertexValue(String label)
    {
        DSAGraphVertex vertex = (DSAGraphVertex) getVertex(label);

        return vertex.getValue();
    }
 


    //display()
    
    public void display()
    {
        //Printing the vertices
        System.out.print("Vertices: ");
        for(Object obj : vertices)
        {   
            DSAGraphVertex currVertex = (DSAGraphVertex) obj;
            System.out.print(currVertex.getLabel() + " ");
        }
        System.out.println("");

        //For each vertices, print its adjacent vertices
        for(Object obj : vertices)
        {   
            DSAGraphVertex currVertex = (DSAGraphVertex) obj; 
            //Get adjacent list for currVertex
            DSALinkedList adjList = currVertex.getAdjacent();
            
            //Iterate through the list to print out adjacent vertices.
            System.out.print(currVertex.getLabel() + ": ");
            for(Object obj2 : adjList)
            {   
                DSAGraphVertex currAdjVertex = (DSAGraphVertex) obj2;
                System.out.print(currAdjVertex.getLabel() + " ");
            }
            System.out.println("");
        }

    }

    

    //depthFirstSearch()

    public DSAGraph depthFirstSearch()
    {  
        //creating the stack and tree
        DSAStack stack = new DSAStack();
        DSAGraph tree = new DSAGraph();
        DSAGraphVertex firstVertex;
        
        //All vertices are new now
        markVerticesNew();
        
        //Getting first vertex
        firstVertex = (DSAGraphVertex) vertices.peekFirst(); 
        firstVertex.setVisited();
        tree.addVertex(firstVertex.getLabel(), firstVertex.getValue());
        
        //pushing onto stack
        stack.push(firstVertex);
        
        while(!stack.isEmpty())
        {  
            DSAGraphVertex currVertex;
            DSAGraphVertex newVertex;
            DSAGraphEdge edge;
                
            //getting currVertex on stack, and find an unvisted vertex
            //from adjacent list of currVertex.
            currVertex = (DSAGraphVertex) stack.top();
            newVertex = getUnvistedVertex(currVertex.getAdjacent());
                
            if(newVertex == null)
            {
                //only pop off when cannot find anymore vertex
                stack.pop();
            }
            else
            {
                //get corresponding edge from currVertex to newVertex
                edge = getEdge(currVertex.getLabel(), newVertex.getLabel()); 

                //Adding newVertex to tree
                tree.addVertex(newVertex.getLabel(), newVertex.getValue()); 
                //Adding the edge to the tree 
                tree.addEdge(edge.getFrom().getLabel(), edge.getTo().getLabel()
                             , edge.getWeight());
                
                //Mark vertex as old
                newVertex.setVisited();
                
                //push newVertex onto stack for next check
                stack.push(newVertex);
            }
        }

        return tree;
    }
            


    //breathFirstSearch

    public DSAGraph breathFirstSearch()
    {
        //mark all vertices as new
        markVerticesNew();
        
        //Getting first vertex and setting visited
        DSAGraphVertex currVertex = (DSAGraphVertex) vertices.peekFirst();
        currVertex.setVisited();
        
        //Adding to tree first
        DSAGraph tree = new DSAGraph();
        tree.addVertex(currVertex.getLabel(), currVertex.getValue());
        
        //Creating new queue and enqueueing the first vertex
        DSAQueue queue = new DSAQueue();
        queue.enqueue(currVertex);
        
        while(!queue.isEmpty())
        {   
            currVertex = (DSAGraphVertex) queue.dequeue();
            //Getting the adjacent list
            DSALinkedList adjList = currVertex.getAdjacent();
            
            for(Object obj : adjList)
            {
                //Retrieving newVertex
                DSAGraphVertex newVertex = (DSAGraphVertex) obj;
                
                //Checking if it is visited yet
                if(!newVertex.getVisited())
                {   
                    //Inserting into tree 
                    DSAGraphEdge edge = getEdge(currVertex.getLabel(), 
                                                newVertex.getLabel()); 
                    tree.addVertex(newVertex.getLabel(), newVertex.getValue());
                    tree.addEdge(edge.getFrom().getLabel(), edge.getTo().getLabel()
                                 , edge.getWeight());
                    
                    //Set visited and insert into queue
                    newVertex.setVisited();
                    queue.enqueue(newVertex);
                }
            }
        }

        return tree;
    }
       


/*----------------------------------------------------------------------------*/
    //PRIVATE SUBMODULES
   

    //getVertex

    private DSAGraphVertex getVertex(String label)
    {   
        DSAGraphVertex vertexFound = null;
        Iterator vertexList = vertices.iterator();
        boolean found = false; 
        
        //Checking if vertex exists first
        if(hasVertex(label))
        {   
            //Iterating vertices list to find the vertex.
            while(vertexList.hasNext() && !found) 
            {   
                DSAGraphVertex currVertex = (DSAGraphVertex) vertexList.next();
                //Finding a matching label
                if(label.equals(currVertex.getLabel()))
                {   
                    vertexFound = currVertex;
                    found = true;
                }
            }
        }
        else
        {
            throw new IllegalArgumentException("Vertex does not exist");
        }
        return vertexFound;
    }
       
   

    //getEdge

    private DSAGraphEdge getEdge(String source, String sink)
    {
        DSAGraphEdge edgeFound = null;
        Iterator edgeList = edges.iterator();
        
        boolean found = false;
        while(edgeList.hasNext() && !found)
        {   
            //retrieving the source and sink label from currEdge 
            DSAGraphEdge currEdge = (DSAGraphEdge) edgeList.next();
            String sourceLabel = currEdge.getFrom().getLabel();
            String sinkLabel = currEdge.getTo().getLabel();
            
            //Finding an edge with the same source and sink
            if(source.equals(sourceLabel) && sink.equals(sinkLabel))
            {
                edgeFound = currEdge;
                found = true;
            }
        }
        
        //If edge does not exist
        if(!found)
        {
            throw new NullPointerException("Edge does not exist");
        }
        else
        {
            return edgeFound;
        }
    }
    
    

    //getAdjacent
    
    private DSALinkedList getAdjacent(String label)
    {       
        DSAGraphVertex vertex;
        DSALinkedList vertexList;

        //Find the vertex 
        vertex = getVertex(label); 
        //use vertex object functions to find adjacent vertices
        vertexList = vertex.getAdjacent();

        return vertexList;
    }
    


    //getAdjacentE

    private DSALinkedList getAdjacentE(String label)
    {
        DSALinkedList edgeList = new DSALinkedList();

        for(Object obj : edges)
        {
            //Getting label of source in currEdge
            DSAGraphEdge currEdge = (DSAGraphEdge) obj;
            String sourceLabel = currEdge.getFrom().getLabel();

            //Collecting any edges with sourceLabel that matches label
            if(sourceLabel.equals(label))
            {
                edgeList.insertLast(currEdge);
            }
        }

        return edgeList;
    }



/*---------------------------------------------------------------------------*/
    //Methods for BFS and DFS
    
    //markVerticesNew

    private void markVerticesNew()
    {
        for(Object obj : vertices)
        {
            //For each vertex in vertices list, clear visited.
            DSAGraphVertex currVertex = (DSAGraphVertex) obj;
            currVertex.clearVisited();
        }
    }


    
    //getNewVertex

    private DSAGraphVertex getUnvistedVertex(DSALinkedList adjacentList)
    {
        DSAGraphVertex currVertex = null;
        
        boolean found = false;
        //Loop until either found or until list is empty
        while(!found && !adjacentList.isEmpty())
        {   
            currVertex = (DSAGraphVertex) adjacentList.removeFirst();            
            if(!currVertex.getVisited())
            {
                found = true;
            }
        }

        //return null if not found
        if(!found)
        {
            currVertex = null;
        }
        
        return currVertex;
        
    }
       
            

        

/*============================================================================*/

    //Private Inner DSAGraphVertex Class
    private class DSAGraphVertex implements Serializable
    {   
        //Class Fields
        private String label;
        private Object value;
        private boolean visited;        

        
        //CONSTRUCTORS
        public DSAGraphVertex(String pLabel, Object pValue)
        {
            label = pLabel;
            value = pValue;
            visited = false;
        }


/*----------------------------------------------------------------------------*/
        //ACCESSORS


        //getLabel
        public String getLabel()
        {
            return label;
        }



        //getValue
        public Object getValue()
        {
            return value;
        }
        


        //GetAdjacent
        public DSALinkedList getAdjacent()
        {
            DSALinkedList vertexList = new DSALinkedList();

            //Interating through the edges list to find neighbour vertices
            //for current vertex.
            for(Object obj : edges)
            {   
                DSAGraphEdge currEdge = (DSAGraphEdge) obj;
                //Comparing sourceVertex from currEdge to this vertex.
                if(this == currEdge.getFrom())
                {
                    vertexList.insertLast(currEdge.getTo());
                }
            }

            return vertexList;
        }
                
        

        //toString
        public String toString()
        {
            String graphString = "Label: " + label +
                                 "\nValue: " + value +
                                 "\nVisited: " + visited;
            
            return graphString;
        }
             

/*----------------------------------------------------------------------------*/
        //MUTATORS


        //setVisited
        public void setVisited()
        {
            visited = true;
        }


        //clearVisited
        public void clearVisited()
        {   
            visited = false;
        }

        
        //getVisited
        public boolean getVisited()
        {
            return visited;
        }
    }

    
    


/*============================================================================*/

    //Private Inner DSAGraphEdge Class
    private class DSAGraphEdge implements Serializable
    {
        //Class Fields
        private DSAGraphVertex from;
        private DSAGraphVertex to;
        private String weight;
        

        //Constructor
        public DSAGraphEdge(DSAGraphVertex pFrom, DSAGraphVertex pTo,
                            String pWeight)
        {
            from = pFrom;
            to = pTo;
            weight = pWeight;
        }



/*----------------------------------------------------------------------------*/
        //ACCESSORS


        //getLabel
        public String getWeight()
        {
            return weight;
        }


        //getFrom
        public DSAGraphVertex getFrom()
        {
            return from;
        }



        //getTo
        public DSAGraphVertex getTo()
        {
            return to;
        }



        //isDirected
        public boolean isDirected()
        {  
            boolean directed = true;

            //Interating through edges list
            for(Object obj : edges)
            {
                DSAGraphEdge currEdge = (DSAGraphEdge) obj; 
                //finding an edge that has the same connection
                //but from and to is the opposite.
                if(to == currEdge.getFrom())
                {
                    if(from == currEdge.getTo())
                    {
                        directed = false;
                    }
                }
            }

            return directed;            
        }


        //toString
        public String toString()
        {
            String edgeString = "From: " + from.toString() +
                                "\nTo: " + to.toString() +
                                "\nWeight: " + weight; 

            return edgeString;
        }
            
    }

}
        

            


        
            
