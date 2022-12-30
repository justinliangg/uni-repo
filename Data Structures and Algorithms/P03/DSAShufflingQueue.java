/*-------------------------------                                               
FILE: DSAShuffingQueue.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA
PURPOSE: Queue Data Structure using the Shuffling Method
REQUIRES: DSAQueue                                                     
Last Mod: 10/08/2021                                                            
--------------------------------*/  
import java.util.*;
import java.io.*;

public class DSAShufflingQueue extends DSAQueue
{
    //CLASS CONSTANTS
    int FRONT = 0;


//==============================================================================
    //CONSTRUCTORS
    
    //Default Constructor
    public DSAShufflingQueue()
    {
        super();
    }
    
    //Parameterized Constructor
    public DSAShufflingQueue(int maxCapacity) throws IllegalArgumentException
    {
        super(maxCapacity);
    }


//==============================================================================
    //MUTATOR METHODS

    //enqueue
    public void enqueue(Object value) throws IndexOutOfBoundsException
    {
        if(super.isFull())
        {   
            throw new IndexOutOfBoundsException();
        }
        else
        {
            super.queue[super.count] = value; 
            super.count++;
        }
    }

    //dequeue
    public Object dequeue() throws NullPointerException
    {   
        
        //Getting front of queue
        //Validates in peek()
        Object frontValue = peek();

        //Shuffle all other data foward 
        for(int i = 0; i < (count - 1) ; i++)
        {
            super.queue[i] = super.queue[i+1];
        }
        
        //Decrementing count
        super.count--;

        return frontValue;
    }

    //peek
    public Object peek() throws NullPointerException
    {
        if(isEmpty())
        {
            throw new NullPointerException();
        }
        else
        {
            return super.queue[FRONT];
        }
    }
} 
