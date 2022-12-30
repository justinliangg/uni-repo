/**
 * DSA Final Assessment Question 5 - GraphTest.java
 *
 * Name : Justin Liang
 * ID   : 19821986
 *
 **/
public class Q5GraphTest
{
	public static void main(String args[])
	{
			System.out.println("\n===== Question 5: Testing Graphs =====\n");
           
            //Testing File reading into graph.
            System.out.println("-Testing read file and storing data in graph ");
            
            Q5Graph t = new Q5Graph();
            FileIO.fileReadEdges("Q5GraphData.txt", t);
            try
            {
                //Checking all vertices exist.
                System.out.print("Has all vertex: ");
                if( !t.hasVertex("A") )
                {
                    throw new IllegalArgumentException();
                } 
                if( !t.hasVertex("B") )
                {
                    throw new IllegalArgumentException();
                }
                if( !t.hasVertex("C") )
                {
                    throw new IllegalArgumentException();
                }
                if( !t.hasVertex("D") )
                {
                    throw new IllegalArgumentException();
                }
                if( !t.hasVertex("E") )
                {
                    throw new IllegalArgumentException();
                } 
                if( !t.hasVertex("F") )
                {
                    throw new IllegalArgumentException();
                }
                System.out.println("passed");
            }
            catch(IllegalArgumentException e)
            {
                System.out.println("failed");
            }

            try
            {
                //Checking all edges exist
                System.out.print("Has all edges: ");
                int testInteger = t.getEdgeWeight("A","C");
                if(testInteger != 600)
                {
                    throw new IllegalArgumentException();
                } 
                testInteger = t.getEdgeWeight("C","E");
                if(testInteger != 700)
                {
                    throw new IllegalArgumentException();
                } 
                testInteger = t.getEdgeWeight("B","D");
                if(testInteger != 900)
                {
                    throw new IllegalArgumentException();
                } 
                testInteger = t.getEdgeWeight("D","A");
                if(testInteger != 500)
                {
                    throw new IllegalArgumentException();
                } 
                testInteger = t.getEdgeWeight("E","B");
                if(testInteger != 800)
                {
                    throw new IllegalArgumentException();
                }
                testInteger = t.getEdgeWeight("E","F");
                if(testInteger != 400)
                {
                    throw new IllegalArgumentException();
                }
                System.out.println("passed");
            }
            catch(Exception e)
            {
                System.out.println("failed");    
            }



//============================================================================

            //Testing Adjacency List and Weight Matrix Display Method.
            System.out.println("\n-Testing Adjacency List and Weight Matrix method");
            Q5Graph g = new Q5Graph();
			g.addVertex("A");
			g.addVertex("B");
			g.addVertex("C");
			
			g.addEdge("A", "B", 3);
			g.addEdge("A", "C", 1);
			g.addEdge("C", "A", 2);
			g.addEdge("B", "C", 5);
            g.addEdge("B", "A", 7);
		    
            //Displaying List
            System.out.println("");
			g.displayAsList();
			
            //Displaying matrix
            System.out.println();
            g.displayAsWeightMatrix();

			System.out.println("\n===== Tests Complete =====\n");

	}
	
}
