/*-------------------------------                                               
FILE: DSABinarySearchTree.java                                              
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES: DSATreeNode.java, DSALinkedList.java, DSAQueue.java                           
Last Mod: 27/08/2021                                                            
--------------------------------*/
import java.util.*;
import java.io.*;
public class DSABinarySearchTree implements Serializable
{   
    //CLASS FIELD
    private DSATreeNode root;


    //CONSTRUCTOR
    public DSABinarySearchTree()
    {
        root = null;
    }




//==============================================================================
    //ACCESSORS


    //ACCESSOR: find
    //IMPORT: pKey(String)
    //EXPORT: value(Object)
    //ASSERTION: find a node with the same key as pKey, return node value.

    public Object find(String pKey)
    {
        Object value = recFind(pKey, root);
        return value;
    }




    //SUBMODULE: display
    //IMPORT: choice(Integer)
    //EXPORT: nil
    //ASSERTION: display the structure of the tree.

    public void display(int choice)
    {
        DSAQueue queue = null;
        switch(choice)
        {   
            case 1: 
                System.out.println("Preorder");
                queue = preOrder();
                break;
            case 2: 
                System.out.println("Inorder");
                queue = inOrder();
                break;
            case 3: 
                System.out.println("Postorder");
                queue = postOrder();
                break;
            default:
                throw new IllegalArgumentException("Only integers 1 - 3 for "
                                                   + "for import");
        }
        
        //Printing out the queue
        printQueue(queue);
    }




    //SUBMODULE: height
    //IMPORT: nil
    //EXPORT: height(Integer)
    //ASSERTION: get the max height of the tree
    
    public int height()
    {   
        int height;
        if(root == null)
        {
            height = 0;
        }
        else
        {
            height = heightRec(root); 
        }

        return height;
    }




    //SUBMODULE: min()
    //IMPORT: nil
    //EXPORT: key(String)
    //ASSERTION: returns the key of the smallest object in the tree.

    public String min()
    {   
        DSATreeNode currNode = root;
        if(root == null)
        {   
            throw new NullPointerException("Tree is empty!");
        }
        else
        {
            //Going to the bottom left most node of the tree
            while(currNode.leftChild != null)
            {   
                currNode = currNode.leftChild;
            }
        }
        return currNode.key;
    }




    //SUBMODULE: max()
    //IMPORT: nil
    //EXPORT: key(String)
    //ASSERTION: returns the key of the largest object in the tree.
    
    public String max()
    {
        DSATreeNode currNode = root;
        if(root == null)
        {
            throw new NullPointerException("Tree is empty!");
        }
        else
        {
            //Going to the bottom right most node of the tree
            while(currNode.rightChild != null)
            {
                currNode = currNode.rightChild;
            }
        }
        
        return currNode.key;
    }
    
    
    

    //SUBMODULE: balance
    //IMPORT: nil
    //EXPORT: balancePercent(Integer)
    //ASSERTION: determines the balance of the tree and returns the balance 
    // as a percentage.

    public int balance()
    {
        //Getting height of right and left subtree
        int leftHeight = heightRec(root.leftChild);
        int rightHeight = heightRec(root.rightChild);
        
        //Calculating the balance based on difference in height
        int balancePercent;
        int difference = Math.abs(leftHeight - rightHeight);

        //Comparing the heights of right and left subtrees
        if(difference == 0)
        {   
            balancePercent = 100;
        }
        else if(difference == 1)
        {   
            balancePercent = 75;
        }
        else if(difference == 2)
        {
            balancePercent = 50;
        }
        else if(difference == 3)
        {   
            balancePercent = 25;
        }
        else 
        {   
            balancePercent = 0;
        }        
        return balancePercent;
    }
    



//==============================================================================
    //TRAVERSALS
    

    //SUBMODULE: preOrder
    //IMPORT: nil
    //EXPORT: queue(DSAQueue)
    //ASSERTION: returns a queue that contains values of the tree in preorder. 

    public DSAQueue preOrder()
    {   
        DSAQueue queue = new DSAQueue();
        preOrderRec(root, queue);

        return queue;
    }


    
    
    //SUBMODULE: inOrder()
    //IMPORT: nil
    //EXPORT: queue(DSAQueue)
    //ASSERTION: returns a queue that contains values of the tree in inOrder.

    public DSAQueue inOrder()
    {
        DSAQueue queue = new DSAQueue();
        inOrderRec(root, queue);

        return queue;
    }
    
    
    
    

    //SUBMODULE: postOrder
    //IMPORT: nil
    //EXPORT: queue(DSAQueue)
    //ASSERTION: returns a queue that contains values of the tree in postOrder.

    public DSAQueue postOrder()
    {
        DSAQueue queue = new DSAQueue();
        postOrderRec(root, queue);

        return queue;
    }

    


//==============================================================================
    //MUTATORS
    

    //SUBMODULE: insert
    //IMPORT: pKey(String), pValue(Object)
    //EXPORT: nil
    //ASSERTION: insert a value and key pair into the tree

    public void insert(String pKey, Object pValue)
    {   
        //reinforcing root node
        //or if inserting first value into tree
        root = recInsert(pKey, pValue, root);
    }




    //SUBMODULE: delete
    //IMPORT: pKey(String)
    //EXPORT: 
    //ASSERTION: delete a node containing pKey.

    public void delete(String pKey)
    {
        root = recDelete(pKey, root);    
    }



//==============================================================================
    //PRIVATE RECURSIVE METHODS
    
    
    //SUBMODULE: recFind
    //IMPORT: pKey(String), pNode(DSATreeNode)
    //EXPORT: value(Object)
    //ASSERTION: private recursive method to find a node containing pKey

    private Object recFind(String pKey,DSATreeNode pNode)
    {   
        Object value = null;

        if(pNode == null)
        {   
            //If null that means no node has that key
            throw new NullPointerException("Key Does Not Exist!");
        }
        else if(pKey.equals(pNode.key))
        {       
            //found the key
            value = pNode.value;
        }
        else if(Integer.valueOf(pKey) < Integer.valueOf(pNode.key))
        {   
            //if key is less than pNode.key go to the left
            value = recFind(pKey,pNode.leftChild);
        }
        else if(Integer.valueOf(pKey) > Integer.valueOf(pNode.key))
        {
            //if key is greater than pNode.key go the right
            value = recFind(pKey,pNode.rightChild);
        }
        
        return value;
    }

    


    //SUBMODULE: recInsert
    //IMPORT: pKey(String), pValue(Object), pNode(DSATreeNode)
    //EXPORT: node(DSATreeNode)
    //ASSERTION: private recursive method to insert pKey and pValue pair into
    // the tree.

    private DSATreeNode recInsert(String pKey, Object pValue, DSATreeNode pNode)
    {
        DSATreeNode currentNode = pNode;

        if(pNode == null)
        {   
            //reached the end so can create new node now
            currentNode = new DSATreeNode(pKey, pValue);
        }
        else if(pKey.equals(pNode.key))
        {
            throw new IllegalArgumentException("Cannot have duplicated keys");
        }
        else if(Integer.valueOf(pKey) < Integer.valueOf(pNode.key))
        { 
            pNode.leftChild = recInsert(pKey, pValue, pNode.leftChild);
        }
        else if(Integer.valueOf(pKey) > Integer.valueOf(pNode.key))
        {   
            pNode.rightChild = recInsert(pKey, pValue, pNode.rightChild);
        }

        return currentNode; 
    }
 



    //SUBMODULE: recDelete
    //IMPORT: pKey(String), pNode(DSATreeNode)
    //EXPORT: updateNode(DSATreeNode)
    //ASSERTION: find node to be deleted with pKey and return it.

    private DSATreeNode recDelete(String pKey, DSATreeNode pNode)
    {
        DSATreeNode updateNode = pNode;
        
        if(pNode == null)
        {
            throw new IllegalArgumentException("Key does not exist in the "+
                                                "tree!");
        }
        else if(pKey.equals(pNode.key))
        {
            updateNode = deleteNode(pNode);
        }
        else if(Integer.valueOf(pKey) < Integer.valueOf(pNode.key))
        {
            pNode.leftChild = recDelete(pKey, pNode.leftChild);
        }
        else if(Integer.valueOf(pKey) > Integer.valueOf(pNode.key))
        {
            pNode.rightChild = recDelete(pKey, pNode.rightChild);
        }

        return updateNode;
    }
       

    

    //SUBMODULE: deleteNode
    //IMPORT: delNode(DSATreeNode)
    //EXPORT: updateNode(DSATreeNode)
    //ASSERTION: Checking number of children on pNode to detemine what
    //updateNode should be

    private DSATreeNode deleteNode(DSATreeNode delNode)
    {
        DSATreeNode updateNode;

        //if no children
        if(delNode.leftChild == null && delNode.rightChild == null)
        {
            updateNode = null;
        }
        //checking if one children
        else if(delNode.leftChild != null && delNode.rightChild == null)
        {   
            //leftChild is the orphan
            updateNode = delNode.leftChild;
        }
        else if(delNode.leftChild == null && delNode.rightChild != null)
        {
            //rightChild is orphan
            updateNode = delNode.rightChild;
        }
        //have two children
        else
        {
            updateNode = promoteSuccessor(delNode.rightChild);
            
            //to avoid referencing its rightChild as itself
            if(updateNode != delNode.rightChild)
            {
                updateNode.rightChild = delNode.rightChild;
            }
            updateNode.leftChild = delNode.leftChild;    
        }

        return updateNode;
   } 
    



    //SUBMODULE: promoteSuccessor
    //IMPORT: currNode(DSATreeNode)
    //EXPORT: successorNode(DSATreeNode)
    //ASSERTION: find the successorNode and return it

    private DSATreeNode promoteSuccessor(DSATreeNode currNode)
    {
        DSATreeNode successorNode = currNode;
            
        if(currNode.leftChild != null)
        {   
            //Keep going left    
            successorNode = promoteSuccessor(currNode.leftChild);
            
            //if currNode is parent of successorNode
            if(successorNode == currNode.leftChild)
            {   
                //grandparent adopt
                currNode.leftChild = successorNode.rightChild;
            }
        }
        
        return successorNode;
    }
                
            
    

    //SUBMODULE: heightRec
    //IMPORT: currNode(DSATreeNode)
    //EXPORT: currentHeight(integer)
    //ASSERTION: private recursive method to determine the max height of the
    // tree.

    private int heightRec(DSATreeNode currNode)
    {   
        int currentHeight;
        int leftHeight;
        int rightHeight;

        if(currNode == null)
        {
            currentHeight = -1;
        }
        else
        {   
            //Recursive call to get the left and right subtree heights
            leftHeight = heightRec(currNode.leftChild);
            rightHeight = heightRec(currNode.rightChild);

            //Comparing which sub tree has greater height
            if(leftHeight > rightHeight)
            {
                currentHeight = leftHeight + 1;
            }
            else
            {
                currentHeight = rightHeight + 1;
            }
        }
        
        return currentHeight;
    }

            
    
    
    //SUBMODULE: preOrderRec
    //IMPORT: currNode(DSATreeNode), queue(DSAQueue)
    //EXPORT: nil
    //ASSERTION: private recursive method to go through the tree in preorder.

    private void preOrderRec(DSATreeNode currNode, DSAQueue queue)
    {   
        if(currNode == null)
        { 
            //Left empty, just to break the recursive call
        }
        else
        {
            //enqueueing the currNode value first because parent node is first.
            queue.enqueue(currNode.value);
            
            //traversing through the left sub tree first.
            preOrderRec(currNode.leftChild, queue);
            //traversing through the right sub tree next
            preOrderRec(currNode.rightChild, queue);
        }   
    }

    


    //SUBMODULE: inOrderRec
    //IMPORT: currNode(DSATreeNode), queue(DSAQueue)
    //EXPORT: nil
    //ASSERTION: private recursive method to go through the tree inOrder.

    private void inOrderRec(DSATreeNode currNode, DSAQueue queue)
    {
        if(currNode == null)
        {
            //Left empty, just to break the recursive call
        }
        else
        {   
            //traversing all the way to the left most node
            inOrderRec(currNode.leftChild, queue); 
            
            //once it returns can enqueue currNode
            queue.enqueue(currNode.value);

            //do the same for the right side
            inOrderRec(currNode.rightChild, queue);            

        }
    }

    
    

    //SUBMODULE: postOrderRec
    //IMPORT: currNode(DSATreeNode), queue(DSAQueue)
    //EXPORT: nil
    //ASSERTION: private recursive method to go through the tree in postOrder

    private void postOrderRec(DSATreeNode currNode, DSAQueue queue)
    {
        if(currNode == null)
        {
            //Left empty, just to break the recursive call
        }
        else
        {
            postOrderRec(currNode.leftChild, queue);
            postOrderRec(currNode.rightChild, queue);
            
            //because parent node is always last, it goes through the 
            //childs first before enqueueing the parent.
            queue.enqueue(currNode.value);
        }
    }
    
    
    

    //SUBMODULE: printQueue
    //IMPORT: pQueue(DSAQueue)
    //EXPORT: nil
    //ASSERTION: gets pQueue and iterate through and print.

    private void printQueue(DSAQueue pQueue)
    {
        //Iterating through the queue to get number of objects
        int numObjects= 0;
        for(Object o : pQueue)
        {   
            numObjects++;
        }
        
        //Iterating through the queue again to print.
        Iterator iter = pQueue.iterator();
        System.out.print("{");
        for(int i =0; i < (numObjects - 1); i++)
        {
            System.out.print(iter.next() + ",");
        }
        System.out.println(iter.next() + "}");
    }

            
                    
//==============================================================================
    //PRIVATE INNER CLASS
    
    private class DSATreeNode implements Serializable
    {   
        //CLASS FIELDS
        //Fields are set public because it is a private inner class inside
        //DSABinarySearchTree.
        public String key;
        public Object value;
        public DSATreeNode leftChild;
        public DSATreeNode rightChild;

        //CONSTRUCTOR
        public DSATreeNode(String pKey, Object pValue)
        {
            key = pKey;
            value = pValue;
            leftChild = null;
            rightChild = null;
        }
    }
}
