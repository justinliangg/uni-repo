/*-------------------------------                                               
FILE: UnitTestDSAGraph.java                                              
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
Last Mod: 08/09/2021    
COMMENTS: Used previously in DSA Prac 06.
--------------------------------*/
public class UnitTestDSAGraph
{
    public static void main(String [] args)
    {   
        //Variable declarations
        int numPassed = 0;
        int numTests = 0;
        DSAGraph graph = null;
        int testInteger;
        String testString; 

//==============================================================================
        //TESTING CONSTRUCTOR
        System.out.println("\n\nTesting Constructor");
        System.out.println("=================================================");
        
        //Test 1 : Constructor
        try 
        {
            numTests++;
            System.out.print("Testing creation of DSAGraph : ");
            
            graph = new DSAGraph();
            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }



//=============================================================================
        //TESTING MUTATORS 
        
        System.out.println("\nTesting Mutators");
        System.out.println("=================================================");

        //Test 2 : addVertex
        try
        {
            numTests++;
            System.out.print("addVertex(): ");
            
            //Adding vertices
            graph.addVertex("A",1);
            graph.addVertex("B",2);
            graph.addVertex("C",3);
            graph.addVertex("D",4);
            graph.addVertex("E",5);
            graph.addVertex("F",6);
            graph.addVertex("G",7);
            graph.addVertex("H",7);

            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }
       

        
        //Test 3: addEdge
        try
        {   
            numTests++; 
            System.out.print("addEdge(): ");

            //Adding edges
            graph.addEdge("A","B",1);
            graph.addEdge("A","D",2);
            graph.addEdge("A","C",3);
            graph.addEdge("A","E",4);
            graph.addEdge("B","E",5);
            graph.addEdge("C","D",6);
            graph.addEdge("D","F",7);
            graph.addEdge("E","F",8);
            graph.addEdge("E","G",9);
            graph.addEdge("F","G",10);
            graph.addEdge("F","E",11);
            graph.addEdge("H","G",12);
            graph.addEdge("G","H",13);

            /*Graph should look like
            A | B C D E 
            B | E
            C | D 
            D | F
            E | F G
            F | G E
            G | H
            H | G
            */

            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
           System.out.println("failed");
        }



        //Test 4: addEdge #2
        try
        {
            numTests++;
            System.out.print("addEdge() #2: ");

            //trying to add an edge with an unknown vertex
            //Should throw exception
            graph.addEdge("A","L",1);
            
            System.out.println("failed");
        }
        catch(IllegalArgumentException e)
        {
            numPassed++;
            System.out.println("passed");
        }



        //Test 5: addEdge #3
        try
        {
            numTests++;
            System.out.print("addEdge() #3: ");
            
            //Adding a duplicate edge should throw exception
            graph.addEdge("A","B", 1);
            
            System.out.println("failed");
        }
        catch(IllegalArgumentException e)
        {
            numPassed++;
            System.out.println("passed");
        }
           
        
        //Test 6: setVertexValue
        try
        {
            numTests++;
            System.out.print("setVertexValue(): ");

            //Changing vertexValue
            graph.setVertexValue("A",5);
            
            //Checking 
            testInteger = (int) graph.getVertexValue("A");
            if(testInteger != 5)
            {
                throw new IllegalArgumentException();
            }

            System.out.println("passed");
            numPassed++;
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("failed");
        }


        //Test 7: setEdgeWeight
        try
        {
            numTests++;
            System.out.print("setEdgeWeight(): ");

            //changing edge weight
            graph.setEdgeWeight("A","B",5);

            //Checking edge weight
            testInteger = (int) graph.getEdgeWeight("A","B");
            if(testInteger != 5)
            {
                throw new IllegalArgumentException();
            }

            numPassed++;
            System.out.println("passed");

        }
        catch(IllegalArgumentException e)
        {
            System.out.println("failed");
        }



        //Test 8`: removeVertex 
        try
        {
            numTests++;
            System.out.print("removeVertex(): ");

            //remove vertex H
            graph.removeVertex("H");
                        
            int count = 0;
            try
            {
                //getting vertex values should throw exception
                graph.getVertexValue("H");
            }
            catch(IllegalArgumentException e)
            {
                count++;
            }
        
            //Any connections to vertex H should be gone too
            try
            {
                graph.getEdgeWeight("G","H");
            }
            catch(IllegalArgumentException e)
            {
                count++;
            }
            try
            {
                graph.getEdgeWeight("H","G");
            }
            catch(IllegalArgumentException e)
            {
                count++;
            }
            if(count != 3)
            {
                throw new IllegalArgumentException("FAILED");
            }
            
            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }


        //Test 9: removeEdge
        try
        {
            numTests++;
            System.out.print("removeEdge(): ");

            graph.removeEdge("F","E");
            
            //Getting edgeWeight for edge FE should throw exception
            graph.getEdgeWeight("F","E");

            System.out.println("failed");
        }
        catch(IllegalArgumentException e)
        {
            numPassed++;
            System.out.println("passed");
        }




//=============================================================================
        //TESTING ACCESSORS
        
        System.out.println("\nTesting Accessors");
        System.out.println("=================================================");



        //Test 10: hasVertex()
        try
        {
            numTests++;
            System.out.print("hasVertex(): ");

            //if hasVertex returns false, then throw exception
            if(!graph.hasVertex("A"))
            {
                throw new IllegalArgumentException();
            }
            if(!graph.hasVertex("B"))
            {
                throw new IllegalArgumentException();
            }
            if(!graph.hasVertex("C"))
            {
                throw new IllegalArgumentException();
            }
            if(!graph.hasVertex("D"))
            {
                throw new IllegalArgumentException();
            }
            if(!graph.hasVertex("E"))
            {
                throw new IllegalArgumentException();
            }
            //if hasVertex returns true, then throw exception
            //Vertex does not exist
            if(graph.hasVertex("H"))
            {
                throw new IllegalArgumentException();
            }

            numPassed++;
            System.out.print("passed");
        }
        catch(Exception e)
        {
            System.out.print("failed");
        }



        //Test 11: getVertexCount()
        try
        {
            numTests++;
            System.out.print("\ngetVertexCount(): ");
 
            //Should have 5 vertices in the graph.
            if(graph.getVertexCount() != 7)
            {
                throw new IllegalArgumentException();
            }

            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }



        //Test 12: getEdgeCount()
        try
        {
            numTests++;
            System.out.print("getEdgeCount(): ");

            //Should have 14 edges
            if(graph.getEdgeCount() != 10)
            {
                throw new IllegalArgumentException();
            }

            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");

        }


        //Test 13: getEdgeWeight()
        try
        {
            numTests++; 
            System.out.print("getEdgeWeight(): ");
            
            //getting edge weight
            testInteger = (int) graph.getEdgeWeight("A", "B"); 
            if(testInteger != 5)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) graph.getEdgeWeight("B", "E");
            if(testInteger != 5)
            {
                throw new IllegalArgumentException();
            }
            testInteger= (int) graph.getEdgeWeight("E", "F");
            if(testInteger != 8)
            {
                throw new IllegalArgumentException();
            }
        
            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }
        


        //Test 14: getVertexValue
        try
        {
            numTests++;
            System.out.print("getVertexValue(): ");

            //getting vertex value
            testInteger = (int) graph.getVertexValue("A");
            if(testInteger != 5)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) graph.getVertexValue("B");
            if(testInteger != 2)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) graph.getVertexValue("C");
            if(testInteger != 3)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) graph.getVertexValue("D");
            if(testInteger != 4)
            {
                throw new IllegalArgumentException();
            }
            testInteger = (int) graph.getVertexValue("E");
            if(testInteger != 5)
            {
                throw new IllegalArgumentException();
            }
            
            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }
        
        //TEST RESULTS:
        System.out.print("\nNumber PASSED: " + numPassed + "/" + numTests);
        System.out.print(" -> " + (int)(double)numPassed/numTests*100 + "%\n");
    }
}

//END TESTS
//============================================================================
        

