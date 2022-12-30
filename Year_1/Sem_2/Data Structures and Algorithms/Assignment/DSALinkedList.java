/*-------------------------------                                               
FILE: DSALinkedList.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES: DSAListNode, DSALinkedListIterator                                                  
Last Mod: 20/08/2021     
COMMENTS: used previously in DSA prac 04
--------------------------------*/  
import java.io.*;
import java.util.*;

public class DSALinkedList implements Serializable, Iterable
{   
    //CLASS FIELDS
    private DSAListNode head;   
    private DSAListNode tail;    

    //CONSTRUCTOR
    public DSALinkedList()
    {
        head = null;
        tail = null;
    }

    
    //Iterator Method
    public Iterator iterator()
    {
        return new DSALinkedListIterator(this);
    }

//==============================================================================
    //ACCESSOR

    //isEmpty
    public boolean isEmpty()
    {   
        boolean empty = false;
        if(head == null)
        {
            empty = true;
        }

        return empty;
    }


    //peekFirst
    public Object peekFirst() throws NullPointerException
    {
        if(isEmpty())
        {
            throw new NullPointerException();
        }   
        else
        {   
            //getting value of head node
            return head.value;
        }
    }

    
    //peekLast
    public Object peekLast() throws NullPointerException
    {   
        if(isEmpty())
        {
            throw new NullPointerException();
        }
        else
        {   
            return tail.value;
        }

    }
                


//==============================================================================
    //MUTATOR


    //insertFirst
    public void insertFirst(Object newValue)
    {   
        DSAListNode newNode = new DSAListNode(newValue);

        if(isEmpty())
        {   
            head = newNode;
            tail = newNode;
        }
        else
        {   
            //Swapping heads

            //set previous head node prev pointer to newNode
            head.prev = newNode;
            //Making the new node point to previous head
            newNode.next = head;
            //Making newNode the new head
            head = newNode;
        }
    }
    
    
    //insertLast
    public void insertLast(Object newValue)
    {
        DSAListNode newNode = new DSAListNode(newValue);
        
        if(isEmpty())
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            DSAListNode currentNode = head;
            //By the end of the loop currentNode is the last node
            while(currentNode.next != null)
            {
                currentNode = currentNode.next;
            }
            
            //previous last node to point towards new last node
            currentNode.next = newNode;
            newNode.prev = currentNode;
        
            //Making tail point to new tail node
            tail = newNode;
        }
    }


    //removeFirst
    public Object removeFirst()
    {
        Object nodeValue;

        if(isEmpty())
        {
            throw new NullPointerException();
        }
        //if head == tail that mean theres only one object in the linkedlist
        else if(head == tail)
        {   
            //getting value
            nodeValue = head.value;
            
            //now setting head and tail to null cause linkedlist is empty
            head = null;
            tail = null;
        }
        else
        {   
            //retrieve value from head
            nodeValue = head.value;
            
            //change head pointer to next node
            head = head.next;

            //current head node prev still pointing to old head
            //therefore initialise to null
            head.prev = null;
        }
        
        return nodeValue;
    }


    //removeLast
    public Object removeLast()
    {
        Object nodeValue;

        if(isEmpty())
        {
            throw new NullPointerException();
        }
        else if(head == tail)
        {
            nodeValue = tail.value;

            //last object so set both tail and head to null
            tail = null;
            head = null;
        }
        else
        {   
            nodeValue = tail.value;
            
            //point to the node behind the tail node
            tail = tail.prev;

            //new tail node still pointing to the old tail node
            //so now making it null
            tail.next = null;
        }

        return nodeValue;
    }



    public void remove(Object value)
    {
        //Locate a Node's value with the same as imported
        DSAListNode currNode = head;
        boolean found = false;
        while(currNode != null && !found)
        {   
            //Comparing object REFERENCES.
            if(currNode.value == value)
            {
                if(currNode == head)
                {
                    removeFirst();
                }
                else if(currNode == tail)
                {
                    removeLast();
                }
                else
                {
                    DSAListNode prevNode = currNode.prev;
                    DSAListNode nextNode = currNode.next;
                
                    //remove any references to currNode
                    prevNode.next = nextNode;
                    nextNode.prev = currNode;
                }
                found = true;
            }

            currNode = currNode.next;
        }

        if(!found)
        {
            throw new IllegalArgumentException("Cannot find value to remove");
        }
    }
                
                
    


//==============================================================================
    //INNER CLASS
    
    //DSAListNode Class
    private class DSAListNode implements Serializable
    {   
        //Class Fields
        public DSAListNode next;
        public DSAListNode prev;
        public Object value;

        //Constructor
        public DSAListNode(Object pValue)
        {
            value = pValue;
            next = null;
            prev = null;
        }
    }


/*----------------------------------------------------------------------------*/


    //DSALinkedListIterator Class
    private class DSALinkedListIterator implements Iterator, Serializable
    {   
        //Class Fields
        private DSAListNode iterNext = null;

        //Constructor
        public DSALinkedListIterator(DSALinkedList list)
        {
            iterNext = list.head;
        }
        
        //Methods
        
        //hasNext()
        public boolean hasNext()
        {
            return (iterNext != null);
        }


        //next()
        public Object next()
        {   
            Object value;
            if(hasNext())
            {   
                //Getting the value from the current iterNext
                value = iterNext.value;
                //Going to next node
                iterNext = iterNext.next;
            }
            else
            {
                value = null;
            }

            return value;
        }


        //remove
        public void remove()
        {
            throw new UnsupportedOperationException("Not Supported");
        }

    }

}

    
