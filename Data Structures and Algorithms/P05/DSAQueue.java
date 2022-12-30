/*-------------------------------                                               
FILE: DSAQueue.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA
REQUIRES: DSALinkedList                                                     
Last Mod: 20/08/2021                                                         
--------------------------------*/  
import java.util.*;
import java.io.*;

public class DSAQueue implements Iterable, Serializable
{
    //Class Fields
    private DSALinkedList queue;

//==============================================================================

    //CONSTRUCTORS

    //Default Constructor
    public DSAQueue()
    {
        queue = new DSALinkedList();
    }

    //Iterator
    public Iterator iterator()
    {
        return (queue.iterator());
    }


//==============================================================================

    //Accessor Methods
    
    //isEmpty
    public boolean isEmpty()
    {        
        return (queue.isEmpty());
    }

    //peek
    public Object peek() throws NullPointerException
    {
        return (queue.peekFirst());
    }
     
//==============================================================================

    //Mutator Methods

    //enqueue
    public void enqueue(Object value)
    {
        queue.insertLast(value);
    }
    
    //dequeue
    public Object dequeue() throws NullPointerException
    {
        return (queue.removeFirst()); 
    }

}
