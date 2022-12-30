/**
 * DSA Final Assessment Question 1 - Q2TreeTest.java
 *
 * Name : Justin Liang 
 * ID   : 19821986
 *
 **/
public class Q2TreeTest
{
	public static void main(String args[])
	{
		System.out.println("\n===== Question 2: Testing Trees =====\n");
        Q2BinarySearchTree t = new Q2BinarySearchTree();
		
		// put your code here

        //Inserting test tree first
        t.insert(50);
        t.insert(60);
        t.insert(20);
        t.insert(70);
        t.insert(30);
        t.insert(10); 
        t.insert(40);
        /* Tree shape 
                         50
                        /  \
                       20   60
                      /  \    \      
                     10  30   70
                           \ 
                           40
        */
		   

//============================================================================
        //Testing getNodeColour method
        
        //Test 1: Checking if colour is brown by default
        try
        {
            System.out.print("getNodeColour(): ");
            
            String testString;
            
            //Checking if node colours are set to brown by default
            testString = t.getNodeColour(50);
            if( !testString.equals("brown") )
            {
                throw new IllegalArgumentException();
            }
            testString = t.getNodeColour(20);
            if( !testString.equals("brown") )
            {
                throw new IllegalArgumentException();
            }
            testString = t.getNodeColour(30);
            if( !testString.equals("brown") )
            {
                throw new IllegalArgumentException();
            }

            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }


        //Test 2: Invalid node should throw exception
        try
        {
            System.out.print("getNodeColour() #2: ");
            
            //Should throw exception if trying to find using an invalid key
            t.getNodeColour(2);

            System.out.println("failed");
        }
        catch(PracExamException e)
        {
            System.out.println("passed");
        }




//============================================================================
        //Testing colourTree
        
        //Test 3: tree is coloured properly
        try
        {
            System.out.print("\ncolourTree(): ");
            
            //Colouring the tree
            t.colourTree();
            
            //node 50 should be black cause two childrens
            String testString = t.getNodeColour(50);
            if( !testString.equals("black") ) 
            {
                throw new IllegalArgumentException();
            }
            
            //node 40 should be orange cause leaf node at level 3
            testString = t.getNodeColour(40); 
            if( !testString.equals("orange") ) 
            {
                throw new IllegalArgumentException();
            }
            
            //node 10 should be yellow cause leaf node at level 2
            testString = t.getNodeColour(10);
            if( !testString.equals("yellow") ) 
            {
                throw new IllegalArgumentException();
            }

            //node 60 should be brown because it has 1 child.
            testString = t.getNodeColour(60);
            if( !testString.equals("brown") )
            {
                throw new IllegalArgumentException();
            }
            
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }
        
        
        //Test 4: throw exception colouring empty tree.
        try
        {
            System.out.print("colourTree() #2: ");
            Q2BinarySearchTree t2 = new Q2BinarySearchTree();
            
            t2.colourTree();
            System.out.println("failed");
        }
        catch(PracExamException e)
        {
            System.out.println("passed");
        }

        System.out.println("\n===== Tests Complete =====\n");

	}
	
}
