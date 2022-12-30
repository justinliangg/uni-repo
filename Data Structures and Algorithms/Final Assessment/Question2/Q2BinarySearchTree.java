/**
 * DSA Final Assessment Question 2 - Q2BinarySearchTree.java
 *
 * Name : Justin Liang
 * ID   : 19821986
 *
 **/

public class Q2BinarySearchTree {   
	// Inner class Q2TreeNode
	private class Q2TreeNode {
		public int value;
        public String colour;
		public Q2TreeNode left;
		public Q2TreeNode right;
		
		public Q2TreeNode(int inVal)
		{
			value = inVal;
            colour = "brown";
			left = null;
			right = null;
		}
	}
	// End Inner class
	// Class Q2BinarySearchTree
	private Q2TreeNode root;
	
	public Q2BinarySearchTree()
	{
		root = null;
	}
	
	public void insert(int val)
	{
		if (isEmpty())
		{
			root = new Q2TreeNode(val);
		}
		else
		{
			root = insertRec(val, root);
		}
	}

	public Boolean isEmpty()
	{
		return root == null;
	}

	private Q2TreeNode insertRec(int inVal, Q2TreeNode cur)
	{
		if (cur == null)
		{
			cur = new Q2TreeNode(inVal);
		}
		else
		{
			if (inVal < cur.value)
			{
				cur.left = insertRec(inVal, cur.left);
			}
			else	
			{
				cur.right = insertRec(inVal, cur.right);
			}
		}
		return cur;
	}
	
//----------------------------------------------------------------------------
    //Implemented methods

    //SUBMODULE: getNodeColour
    //IMPORT: inVal(int)
    //EXPORT: nodeColour(String)
    //ASSERTION: gets the colour of node with inVal and return it
    //COMMENTS: method made to test adding a colour variable to TreeNode

    public String getNodeColour(int inVal) throws PracExamException
    {
        Q2TreeNode node = recFind(inVal, root);
        return node.colour;
    }


    //SUBMODULE: colourTree
    //IMPORT: nil
    //EXPORT: nil
    //ASSERTION: colour nodes based on the amount of leaf nodes
    //COMMENTS: method made for 2c

    public void colourTree() throws PracExamException
    {
        if(root != null)
        {
            recColourTree(root, -1);
        }
        else 
        {
            throw new PracExamException("Tree is empty!");
        }
    }
        
    
    //SUBMODULE: recFind
    //IMPORT: , pNode(Q2TreeNode)
    //EXPORT: nodeToFind(Q2TreeNode)
    //ASSERTION: private recursive method to find a node containing inVal
    //COMMENTS: Algorithm taken from Prac 05

    private Q2TreeNode recFind(int inVal, Q2TreeNode pNode) throws 
                                                             PracExamException
    {   
        Q2TreeNode nodeToFind = null;

        if(pNode == null)
        {   
            //If null that means no node has that key
            throw new PracExamException("Value does not exist");
        }
        else if(inVal == pNode.value)
        {       
            //found the key
            nodeToFind = pNode;
        }
        else if(inVal < pNode.value)
        {   
            //if key is less than pNode.value go to the left
            nodeToFind = recFind(inVal, pNode.left);
        }
        else if(inVal > pNode.value)
        {
            //if key is greater than pNode.value go the right
            nodeToFind = recFind(inVal, pNode.right);
        }
        
        return nodeToFind;
    }


    //SUBMODULE: recColourTree
    //IMPORT: node(Q2TreeNode), level(int)
    //EXPORT: nil
    //ASSERTION: private recursive method to colour each node based the amount
    // of childrens and level of node.
    //COMMENTS: Since not stated, assumed that root is level 0. 

    private void recColourTree(Q2TreeNode node, int level)
    {   
        //increasing the level
        level++;

        if(node.right != null && node.left != null) //two children
        {
            node.colour = "black";
            recColourTree(node.right, level);
            recColourTree(node.left, level);
        }
        else if(node.right != null) //has right child
        {
            recColourTree(node.right, level);
        }
        else if(node.left != null) //has left child
        {
            recColourTree(node.left, level);
        }
        else //no child
        {
            if(level <= 2) //yellow 
            {
                node.colour = "yellow";
            }
            else
            {
                node.colour = "orange";
            }
        }
    }
        
              
}
