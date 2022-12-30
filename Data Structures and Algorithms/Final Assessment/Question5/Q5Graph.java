/**
 * DSA Final Assessment Question 5 - Q5Graph.java                             4
 *
 * Name : Justin Liang 
 * ID   : 19821986
 *
 **/
import java.util.*;

public class Q5Graph 
{
    private int maxsize;
    private int wmatrix[][];
    private String labels[];
    private int vertexCount;

    public Q5Graph() 
    {
	maxsize = 20;
	wmatrix = new int[maxsize][maxsize];
	labels = new String[maxsize];
        for(int i=0; i < maxsize; i++)
	    for (int j=0; j< maxsize; j++)
            { 
		wmatrix[i][j] = 0;
            }
        vertexCount = 0;
    }

    public void addVertex(String label)
    {
        if (vertexCount == maxsize)
	{
		// do nothing, but should throw exception
	}
	else if (!(hasVertex(label))) 
        {
            labels[vertexCount] = label;
            vertexCount++;
        }
    }

    public void addEdge(String label1, String label2, int weight)
    {
        int v1, v2;

        v1 = getIndex(label1); 
        v2 = getIndex(label2);   

        wmatrix[v1][v2] = weight;
    }

    public boolean hasVertex(String label) 
    {
        boolean has = false;
        for (int i=0; i < vertexCount; i++) 
        {
           if (labels[i].equals(label))
                has = true;
        }
        return has;
    }

    public int getIndex(String label) 
    {
        int theVertex = -1;
        for (int i=0; i < vertexCount; i++) 
            {
            if (labels[i].equals(label))
                theVertex = i;
            }
	return theVertex;    
	}


    public void displayAsList() 
    {
        System.out.println("Adjacency list for graph is: \n");
        for(int i = 0; i < vertexCount; i++)
        {
            System.out.print(labels[i] + ": ");
            for(int j = 0; j < vertexCount; j++)
            {   
                //finding all connections
                if(wmatrix[i][j] != 0)
                {
                    System.out.print(labels[j] + " ");
                }
            }
            System.out.println("");
        }
    }

    public void displayAsWeightMatrix()
    {
        System.out.println("Weight matrix for graph is: \n");
        
        //Printing the horizontal labels first
        System.out.print("   ");
        for(int i = 0; i < vertexCount; i++)
        {
            System.out.print(labels[i] + " ");
        }
        System.out.println("\n--------");

        //going through each row 
        for(int i = 0; i < vertexCount; i++)
        {   
            System.out.print(labels[i] + ": ");
            for(int j = 0; j < vertexCount; j++)
            {
                System.out.print(wmatrix[i][j] + " ");
            }
            System.out.println("");
        }
    }
                
                

	// put your methods here
    
    //Implemented getEdgeWeight to test reading in data for graph.
    public int getEdgeWeight(String source, String sink)
    {
        int sourceIndex = getIndex(source);
        int sinkIndex = getIndex(sink);

        return wmatrix[sourceIndex][sinkIndex];
    }
            
        
}  
