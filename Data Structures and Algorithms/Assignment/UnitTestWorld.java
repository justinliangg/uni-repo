/*-------------------------------                                               
FILE: UnitTestWorld.java                                              
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES: DSAGraph.java, DSAHashTable.java, DSALinkedList.java
PURPOSE: test World methods
Last Mod: 13/10/2021                                                            
--------------------------------*/
public class UnitTestWorld
{
    public static void main(String[] args) 
    {
        //Variable declarations
        int numPassed = 0;
        int numTests = 0;
        World world = null;
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
            System.out.print("Testing creation of World: ");
            
            world = new World();
            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }

 
//==============================================================================
        //TESTING Accessors
        System.out.println("\n\nTesting Accessors");
        System.out.println("=================================================");
        
        //test 2: getGraph()
        try 
        {
            numTests++;
            System.out.print("testing getGraph():  ");
            
            DSAGraph graph = world.getGraph();
            if(!graph.isEmpty())
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
 
        

        //test 2: getNodeMaps()
        try 
        {
            numTests++;
            System.out.print("testing getNodeMaps():  ");
            
            DSAHashTable nodeMap = world.getNodeMaps();

            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }



        //test 3: getEdgeMaps()
        try 
        {
            numTests++;
            System.out.print("testing getEdgeMaps():  ");
            
            DSAHashTable edgeMap = world.getEdgeMaps();
            
            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }



        //test 4: getStart()
        try 
        {
            numTests++;
            System.out.print("testing getStart():  ");
            
            testString  = world.getStart();
            if(!testString.equals(""))
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


 
        //test 5: getTargetList()
        try 
        {
            numTests++;
            System.out.print("testing getTargetList():  ");
            
            DSALinkedList targetList = world.getTargetList();
            if(!targetList.isEmpty())
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



//============================================================================
        //TESTING MUTATORS
        System.out.println("\nTesting Mutators");
        System.out.println("================================================");
        
        //test 6: setGraph()
        try 
        {
            numTests++;
            System.out.print("testing setGraph(): ");
            
            //insert graph into setGraph
            DSAGraph newGraph = new DSAGraph();
            newGraph.addVertex("A",5);
            world.setGraph(newGraph);

            if(world.getGraph().isEmpty())
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
 
        

        //test 7: setNodeMaps()
        try 
        {
            numTests++;
            System.out.print("testing setNodeMaps(): ");
            
            //inserting in nodeMap
            DSAHashTable nodeMap = new DSAHashTable();
            nodeMap.put("A",5);
            world.setNodeMaps(nodeMap);

            if(world.getNodeMaps().getLoadFactor() == 0)
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


        //test 8: setEdgeMaps()
        try 
        {
            numTests++;
            System.out.print("testing setEdgeMaps(): ");
            
            //inserting into edgeMap
            DSAHashTable edgeMap = new DSAHashTable();
            edgeMap.put("A",5); 
            world.setEdgeMaps(edgeMap);

            if(world.getEdgeMaps().getLoadFactor() == 0)
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



        //test 9: setStart()
        try 
        {
            numTests++;
            System.out.print("testing setStart(): ");
                       
            world.setStart("1234");
            if(!world.getStart().equals("1234"))
            {
                numPassed++;
                System.out.println("passed");
            }
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }


        //test 10: insertTarget()
        try 
        {   
            System.out.print("Testing insertTarget(): ");
            world.insertTarget("1234");
            
            //getting string out and checking it.
            DSALinkedList targetList = world.getTargetList();
            testString = (String) targetList.peekLast();

            if(!testString.equals("1234"))
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



        //test 11: copy()
        try
        {
            numTests++;
            System.out.print("Testing copy(): ");
            
            //copying world over.
            World newWorld = new World();
            newWorld.copy(world);

            if(!newWorld.getStart().equals("1234"))
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

  


        
