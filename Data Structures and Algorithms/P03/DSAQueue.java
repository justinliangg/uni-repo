/*-------------------------------                                               
FILE: DSAQueue.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA
PURPOSE: Base Class for DSAShufflingQueue and DSACircularQueue
REQUIRES: nil                                                     
Last Mod: 10/08/2021                                                            
--------------------------------*/  
import java.util.*;
import java.io.*;

public abstract class DSAQueue
{
    //Class Fields
    protected int count;
    protected Object[] queue;

    //Class Constants
    private final int DEFAULT_CAPACITY = 100;

//==============================================================================

    //CONSTRUCTORS

    //Parameterized Constructor
    public DSAQueue(int maxCapacity) throws IllegalArgumentException
    {   
        if(maxCapacity > 0)
        {
            queue = new Object[maxCapacity];
            count = 0;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    //Default Constructor
    public DSAQueue()
    {
        queue = new Object[DEFAULT_CAPACITY];
        count = 0;
    }


//==============================================================================

    //Accessor Methods
    
    //getCount
    public int getCount()
    {
        return count;
    }

    //isEmpty
    public boolean isEmpty()
    {
        boolean empty = false;
        if(count == 0)
        {
            empty = true;
        }

        return empty;
    }

    //isFull
    public boolean isFull()
    {
        boolean full = false;
        if(count == queue.length)
        {
            full = true;
        }

        return full;
    }


//==============================================================================

    //Mutator Methods

    //enqueue
    public abstract void enqueue(Object value);
    
    //dequeue
    public abstract Object dequeue();

    //peek
    public abstract Object peek();
     
}
