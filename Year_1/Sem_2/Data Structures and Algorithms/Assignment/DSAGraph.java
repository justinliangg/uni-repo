/*-------------------------------                                               
FILE: DSAGraph.java                                              
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES: DSAHashTable.java, DSALinkedList.java
Last Mod: 10/10/2021  
COMMENTS: Used previously in DSA Prac 06
--------------------------------*/
import java.util.*;
import java.io.*;

public class DSAGraph implements Serializable
{
    //Class Fields
    private DSAHashTable vertices;
    private DSAHashTable edges;
    private int vertexCount;
    private int edgeCount;



/*----------------------------------------------------------------------------*/
    //CONSTRUCTOR
    
    public DSAGraph()
    {
        //HashTable will automatically resize
        vertices = new DSAHashTable();
        edges = new DSAHashTable();
        vertexCount = 0;
        edgeCount = 0;
    }
    


/*----------------------------------------------------------------------------*/
    //MUTATORS


    //MUTATOR: addVertex
    //IMPORT: label(String), value(Object)
    //EXPORT: nil
    //ASSERTION: create a DSAGraphVertex and insert into vertices.

    public void addVertex(String label, Object value) throws 
                                                      IllegalArgumentException
    {   
        //Creating a new vertex object
        DSAGraphVertex newVertex = new DSAGraphVertex(label, value); 
        
        //Inserting into the hashtable of vertices
        vertices.put(label, newVertex);
        vertexCount++;
    }
    



    //MUTATOR: addEdge
    //IMPORT: source(String), sink(String), weight(Object)
    //EXPORT: nil
    //ASSERTION: create an DSAGraphEdge and insert into edges.

    public void addEdge(String source, String sink, Object weight)
    {
        //Validated in getVertex, if vertex exists 
        DSAGraphVertex sourceVertex = getVertex(source);
        DSAGraphVertex sinkVertex = getVertex(sink);

        //Creating a newEdge 
        DSAGraphEdge newEdge = new DSAGraphEdge(sourceVertex, sinkVertex
                                                ,weight);
  
        //Adding to edges table
        edges.put(newEdge.getName(), newEdge); 
        //Adding to sourceVertex links list
        sourceVertex.getLinks().insertLast(newEdge);
      
        edgeCount++;
    }




    //MUTATOR: setVertexValue
    //IMPORT: label(String), value(Object)
    //EXPORT: nil
    //ASSERTION: find vertex and change it value.

    public void setVertexValue(String label, Object value)
    {
        //getting vertex first
        DSAGraphVertex vertex = getVertex(label);

        //Changing vertex weight
        vertex.setValue(value); 
    }
    
    


    //MUTATOR: setEdgeWeight
    //IMPORT: source(String), sink(String), value(Object)
    //EXPORT: nil
    //ASSSERTION: find a given edge and change its weight

    public void setEdgeWeight(String source, String sink, Object value)
    {
        //get edge
        DSAGraphEdge edge = getEdge(source, sink);

        //changing weight
        edge.setWeight(value);
    }
           

    
    //MUTATOR: removeVertex
    //IMPORT: label(String)
    //EXPORT: nil
    //ASSERTION: find a vertex, remove it from table and also find all its
    //connections and remove it. 

    public void removeVertex(String label)
    {   
        //Get vertex to remove.
        DSAGraphVertex vertexToRemove = getVertex(label);
        //Remove any connections to vertexToRemove
        removeConnections(vertexToRemove);
        
        vertices.remove(label);
        vertexCount--;
    }


    

    //MUTATOR: removeEdge
    ///IMPORT: source(String), sink(String)
    //EXPORT: nil
    //ASSERTION: find the edge and remove it.

    public void removeEdge(String source, String sink)
    {  
        DSAGraphEdge edgeToRemove = getEdge(source, sink);
        DSALinkedList adjacentE = getAdjacentE(source);
        
        //Remove from list
        adjacentE.remove(edgeToRemove);
        //Remove from hashtable
        edges.remove(edgeToRemove.getName());

        edgeCount--;
    }
            
        



/*----------------------------------------------------------------------------*/ 
    //ACCESSORS


    //ACCESSOR: hasVertex
    //IMPORT: label(String)
    //EXPORT: boolean
    //ASSERTION: checks if a vertex exists

    public boolean hasVertex(String label)
    { 
        return (vertices.hasKey(label));
    }

    

    //ACCESSOR: hasEdge
    //IMPORT: source(String), sink(source)
    //EXPORT: boolean
    //ASSERTION: checks if an edge exists

    public boolean hasEdge(String source, String sink)
    {   
        String edgeLabel = source + sink;
        return (edges.hasKey(edgeLabel));
    }


     
    //ACCESSOR: getVertexCount
    //IMPORT: nil
    //EXPORT: count(Int)
    //ASSERTION: return num vertex

    public int getVertexCount()
    {
        return vertexCount;
    }


    
    //ACCESSOR: getEdgeCount
    //IMPORT: nil
    //EXPORT: count(int)
    //ASSERTION: return num edges
    
    public int getEdgeCount()
    {
        return edgeCount;
    }
    


    //ASSESSOR: getEdgeWeight
    //IMPORT: source(String), sink(String)
    //EXPORT: weight(Object)
    //ASSERTION: find edge and return its weight

    public Object getEdgeWeight(String source, String sink)
    {
        DSAGraphEdge edge = (DSAGraphEdge) getEdge(source, sink);
        
        return edge.getWeight();
    }



    //ACCESSOR: getVertexValue
    //IMPORT: label(String)
    //EXPORT: value(Object)
    //ASSERTION: find vertex and return its value

    public Object getVertexValue(String label)
    {
        DSAGraphVertex vertex = (DSAGraphVertex) getVertex(label);

        return vertex.getValue();
    }
    

    
    //ACCESSOR: isEmpty
    //IMPORT: nil
    //EXPORT: empty(Boolean)
    //ASSERTION: checks if graph is empty

    public boolean isEmpty()
    {
        boolean empty = false;
        
        if(vertexCount == 0)
        {
            empty = true;
        }

        return empty;
    }


    
    //ACCESSOR: countVertexType
    //IMPORT: vertexType(String)
    //EXPORT: count(int)
    //ASSERTION: counts the number of vertices with vertexType as its value

    public int countVertexType(String vertexType)
    {
        int count = 0; 
        //iterating through vertices to find number of vertex value equal to
        // vertexType
        for(Object obj : vertices)
        {
            //getting DSAGraphVertex
            String vertexLabel = (String) obj;
            DSAGraphVertex vertex = (DSAGraphVertex) vertices.get(vertexLabel);
            
            if(vertexType.equals(vertex.getValue()))
            {
                count++;
            }
        }
        
        return count;
    }

    

    //ACCESSOR: countEdgeType
    //IMPORT: edgeType(String)
    //EXPORT: count(Int)
    //ASSERTION: counts the number of edges with edgeType as its weight

    public int countEdgeType(String edgeType)
    {
        int count = 0;
        //iterating through the edges to find number of edge weights with
        // the same edgeType.
        for(Object obj : edges)
        {
            // getting DSAGraphEdge
            String edgeLabel = (String) obj;
            DSAGraphEdge edge = (DSAGraphEdge) edges.get(edgeLabel);
            
            if(edgeType.equals(edge.getWeight()))
            {
                count++;
            }
        }

        return count;
    }




/*---------------------------------------------------------------------------*/
    //DISPLAY METHODS
   
    
    //SUBMODULE: adjacencyMatrix
    //IMPORT: nil
    //EXPORT: matrix(String[][])
    //ASSERTION: return adjacencyMatrix representation of the graph

    public String[][] adjacencyMatrix()
    { 
        //Make a 2D array
        int matrixLength = vertices.getCount();
        String[][] matrix = new String[matrixLength+1][matrixLength+1];
        
        /*Filling the first row and column with the vertex labels*/
        //first row
        Iterator iter = vertices.iterator();
        for(int i = 1; i <= matrixLength; i++)
        {
            matrix[0][i] = (String) iter.next();
        }
        //first column
        iter = vertices.iterator();
        for(int i = 1; i <= matrixLength; i++)
        {
            matrix[i][0] = (String) iter.next();
        }

        //Filling the rest of the matrix
        matrix[0][0] = "*";
        for(int i = 1; i <= matrixLength; i++)
        {
            for(int j = 1; j <= matrixLength; j++)
            {
                //selecting a column and going through each row. 
                String vertexRow = (String) matrix[i][0];
                String vertexColumn = (String) matrix[0][j];
                    
                //checking if it has a weight
                if(hasEdge(vertexRow, vertexColumn))
                { 
                    //getting edge weight from edgeMaps
                    String edgeType = (String) getEdgeWeight(vertexRow, vertexColumn);

                    matrix[i][j] = edgeType;
                }
                else
                {
                    matrix[i][j] = "0";
                }
            }
        }

        return matrix;
    
    }//end adjacencyMatrix();


    

    //SUBMODULE: adjacencyMatrix
    //IMPORT: edgeMaps(DSAHashTable)
    //EXPORT: matrix(String[][])
    //ASSERTION: return adjacencyMatrix representation of the graph with
    //its edgeWeight

    public String[][] adjacencyMatrix(DSAHashTable edgeMaps)
    {
        //Make a 2D array
        int matrixLength = vertices.getCount();
        String[][] matrix = new String[matrixLength+1][matrixLength+1];
        
        /*Filling the first row and column with the vertex labels*/
        //first row
        Iterator iter = vertices.iterator();
        for(int i = 1; i <= matrixLength; i++)
        {
            matrix[0][i] = (String) iter.next();
        }
        //first column
        iter = vertices.iterator();
        for(int i = 1; i <= matrixLength; i++)
        {
            matrix[i][0] = (String) iter.next();
        }

        //Filling the rest of the matrix
        matrix[0][0] = "*";
        for(int i = 1; i <= matrixLength; i++)
        {
            for(int j = 1; j <= matrixLength; j++)
            {
                //selecting a column and going through each row. 
                String vertexRow = (String) matrix[i][0];
                String vertexColumn = (String) matrix[0][j];
                    
                //checking if it has a weight
                if(hasEdge(vertexRow, vertexColumn))
                { 
                    //getting edge weight from edgeMaps
                    String edgeType = (String) getEdgeWeight(vertexRow, vertexColumn);
                    String edgeWeight = String.valueOf(edgeMaps.get(edgeType));

                    matrix[i][j] = edgeWeight;
                }
                else
                {
                    matrix[i][j] = "-";
                }
            }
        }

        return matrix;
        
    }//end AdjacencyMatrix
        



/*---------------------------------------------------------------------------*/
    //TRAVERSALS
    
        

    //SUBMODULE: getAllPaths
    //IMPORT: start(String), end(String), nodeMaps(DSAHashTable),
    //        edgeMaps(DSAHashTable)
    //EXPORT: allPaths(DSAHeap)
    //ASSERTION: get all simple paths in a given graph and its associated cost

    public DSAHeap getAllPaths(String start, String end, DSAHashTable nodeMaps, 
                               DSAHashTable edgeMaps)
    {   
        /* mark all vertices as new first*/
        markVerticesNew();
        
        /* Store path information */
        String currPath = "";
        int cost = 0;
        DSAHeap allPaths = new DSAHeap(numPaths(start, end));
       
        /* recurse to get all simple paths*/
        currPath = currPath + start;
        getPathsRec(start, end, nodeMaps, edgeMaps, currPath, cost, allPaths);

        return allPaths;
    }




    //SUBMODULE: getPathsRec
    //IMPORT: start(String), end(String), nodeMaps(DSAHashTable),
    //        edgeMaps(DSAHashTable), currPath(String), cost(int),
    //        allPaths(DSAHeap)
    //EXPORT: nil
    //ASSERTION: recursively find simple paths, adding each path
    //           and its associated cost to the heap.
    //COMMENTS: contains code obtained from 
    // https://www.geeksforgeeks.org/find-paths-given-source-destination/

    private void getPathsRec(String start, String end, DSAHashTable nodeMaps,
                             DSAHashTable edgeMaps, String currPath, int cost,
                             DSAHeap allPaths)
    {
        /* Getting the corresponding graph vertices */
        DSAGraphVertex startNode = (DSAGraphVertex) vertices.get(start);
        DSAGraphVertex endNode = (DSAGraphVertex) vertices.get(end);

        //Found the end target 
        if(startNode == endNode)
        { 
            //add into heap
            allPaths.add(cost, currPath);
        }
        else /*Continue finding a path*/
        {
            startNode.setVisited();
            
            /* for each adjacent nodes recursively find a path to end*/
            DSALinkedList adjList = startNode.getAdjacent();
            for(Object obj : adjList)
            {   
                /* finding an unvisited vertex to recurse*/
                DSAGraphVertex currVertex = (DSAGraphVertex) obj;
                if(!currVertex.getVisited())
                {   
                    /*getting total edge cost between start and currVertex*/
                    int edgeCost = totalEdgeCost(start, currVertex.getLabel(),
                                                 nodeMaps, edgeMaps);
                    cost = cost + edgeCost;
                    /* insert currVertex in the path*/
                    String newPath = "->" + currVertex.getLabel();
                    currPath = currPath + newPath;

                    /*Recurse again for that currVertex to the end */
                    getPathsRec(currVertex.getLabel(), end, nodeMaps, 
                                edgeMaps, currPath, cost, allPaths);
                    
                    /*delete the currVertex and cost from path*/
                    currPath = currPath.replace(newPath,"");
                    cost = cost - edgeCost;
                }
            }
            startNode.clearVisited();
        }
    }




    //SUBMODULE: totalEdgeCost
    //IMPORT: source(String), sink(String), nodeMaps(DSAHashTable),
    //        edgeMaps(DSAHashTable)
    //EXPORT: edgeCost(int)
    //ASSERTION: calculate the edge cost between two edges

    private int totalEdgeCost(String source, String sink, DSAHashTable nodeMaps,
                              DSAHashTable edgeMaps)
    {   
        /* getting edge and vertex type stored in graph*/
        String edgeType = (String) getEdgeWeight(source, sink); 
        String vertexType = (String) getVertexValue(source);

        /*Using node and edgemap to get the int value*/
        int vertexCost = (int) nodeMaps.get(vertexType);
        int edgeCost = (int) edgeMaps.get(edgeType) + vertexCost;

        return edgeCost;
    }




    //SUBMODULE : numPaths
    //IMPORT: start(String), end(String)
    //EXPORT: totalPaths(int)
    //ASSERTION: count number of simple paths between two vertices

    private int numPaths(String start, String end)
    {
        int totalPaths;
        int count;
        
        markVerticesNew();
 
        /*getting start and end nodes */
        DSAGraphVertex startNode = (DSAGraphVertex) vertices.get(start);
        DSAGraphVertex endNode = (DSAGraphVertex) vertices.get(end);
        
        /*Recursively count paths*/
        count = 0;
        totalPaths = countPathsRec(startNode, endNode, count);
        
        return totalPaths;
    }
    



    //SUBMODULE: countPathsRec
    //IMPORT: start(DSAGraphVertex), end(DSAGraphVertex), count(int)
    //EXPORT: count(int)
    //ASSERTION: recursive helper function to get number of simple paths

    private int countPathsRec(DSAGraphVertex start, DSAGraphVertex end,
                              int count)
    {
        /* Reached the target*/
        if(start == end)
        {
            count++;
        }
        else /*Continue searching for a path*/
        {
            start.setVisited();
        
            /* for each node, recursively find a path to end target*/
            DSALinkedList adjList = start.getAdjacent();
            for(Object obj : adjList)
            {
                DSAGraphVertex currVertex = (DSAGraphVertex) obj;
                if(!currVertex.getVisited())
                {   
                    count = countPathsRec(currVertex, end, count);
                }

            }

            start.clearVisited();
        }

        return count;
    }
     



/*----------------------------------------------------------------------------*/
    //PRIVATE SUBMODULES
   

    //SUBMODULE: getVertex(label)
    //IMPORT: label(String)
    //EXPORT: vertex(DSAGraphVertex)
    //ASSERTION: get vertex and return it

    private DSAGraphVertex getVertex(String label) 
    {       
        DSAGraphVertex vertex;
        //Change message of exception
        try
        {
            vertex = (DSAGraphVertex) vertices.get(label);
        }
        catch(IllegalArgumentException e)
        {
            throw new IllegalArgumentException("Vertex does not exist");
        }

        return vertex;
    }
       
   


    //SUBMODULE: getEdge(label)
    //IMPORT: source(String), sink(String)
    //EXPORT: edge(DSAGraphEdge)
    //ASSERTION: get edgeand return it

    private DSAGraphEdge getEdge(String source, String sink)
    { 
        DSAGraphEdge edge;
        String edgeName = source + sink;
        
        //Change message of exception
        try
        {
            edge = (DSAGraphEdge) edges.get(edgeName);
        }
        catch(IllegalArgumentException e)
        {
            throw new IllegalArgumentException("Edge does not exist");
        }

        return edge;
    }        
    



    //SUBMODULE: getAdjacent 
    //IMPORT: label(String)
    //EXPORT: vertexList(DSALinkedList)
    //ASSERTION: get adjacent vertices for a given vertex

    private DSALinkedList getAdjacent(String label)
    {       

        //Find the vertex 
        DSAGraphVertex vertex = getVertex(label); 
        //get adjacent list
        DSALinkedList vertexList = vertex.getAdjacent();

        return vertexList;
    }
    


 
    //SUBMODULE: getAdjacentE 
    //IMPORT: label(String)
    //EXPORT: edgesList(DSALinkedList)
    //ASSERTION: get adjacentEdge for a given vertex

    private DSALinkedList getAdjacentE(String label)
    {   
        //find vertex
        DSAGraphVertex vertex = getVertex(label);
        //use vertex to find adjacent edges
        DSALinkedList edgesList = vertex.getLinks();

        return edgesList;
    }

    
    
    
    //SUBMODULE: removeConnections
    //IMPORT: vertexToRemove(DSAGraphVertex)
    //EXPORT: nil
    //ASSERTION: remove all connections related to vertexRemove
    
    private void removeConnections(DSAGraphVertex vertexToRemove)
    {  
        //Remove adjacent edges with vertexToRemove as the source
        DSALinkedList adjVertices = vertexToRemove.getAdjacent();
        for(Object obj : adjVertices)
        {   
            DSAGraphVertex sink = (DSAGraphVertex) obj;
            removeEdge(vertexToRemove.getLabel(), sink.getLabel());
        }
            
        //remove any edges with vertexToRemove as the sink
        for(Object obj : vertices)
        {
            try
            {   
                String sourceLabel = (String) obj;
                removeEdge(sourceLabel, vertexToRemove.getLabel());
            }
            catch(IllegalArgumentException e)
            {
                //for vertices with no connection to vertexToRemove
            }
        }
    }

    

    
    //SUBMODULE: markVerticesNew()
    //IMPORT: nil
    //EXPORT: nil
    //ASSERTION: mark all vertices in vertices tables as unvisited

    private void markVerticesNew()
    {
        for(Object obj : vertices)
        {   
            String vertexLabel = (String) obj;
            //For each vertex, clear visited.
            DSAGraphVertex currVertex = (DSAGraphVertex) vertices.get(vertexLabel);
            currVertex.clearVisited();
        }
    }




/*============================================================================*/

    //Private Inner DSAGraphVertex Class
    private class DSAGraphVertex implements Serializable
    {   
        //Class Fields
        private String label;
        private Object value;
        private boolean visited;
        private DSALinkedList links;


        //CONSTRUCTORS
        public DSAGraphVertex(String pLabel, Object pValue)
        {
            label = pLabel;
            value = pValue;
            visited = false;
            links = new DSALinkedList();
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
        
        
        
        //getLinks
        public DSALinkedList getLinks()
        {
            return links;
        }



        //getAdjVertices
        public DSALinkedList getAdjacent()
        {
            DSALinkedList adjVertices = new DSALinkedList();

            //Iterating through its links to get adjacent vertices
            for(Object obj : links)
            {
                DSAGraphEdge currEdge = (DSAGraphEdge) obj;
                adjVertices.insertLast(currEdge.getTo());
            }

            return adjVertices;
        }

         

/*----------------------------------------------------------------------------*/
        //MUTATORS

        //setValue
        public void setValue(Object pValue)
        {
            value = pValue;
        }


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
        private Object weight;
        private String name;
        

        //Constructor
        public DSAGraphEdge(DSAGraphVertex pFrom, DSAGraphVertex pTo,
                            Object pWeight)
        {
            from = pFrom;
            to = pTo;
            weight = pWeight;
            name = from.getLabel() + to.getLabel();
        }


/*----------------------------------------------------------------------------*/
        //ACCESSORS


        //getLabel
        public Object getWeight()
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
        


        //getName
        public String getName()
        {
            return name;
        }


/*----------------------------------------------------------------------------*/
        //MUTATORS

        //setEdgeWeight
        public void setWeight(Object pWeight)
        {
            weight = pWeight;
        }
            
            
    }

}//End DSAGraph
        

            


        
            
