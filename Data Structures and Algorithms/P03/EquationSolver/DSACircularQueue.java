/*-------------------------------                                               
FILE: DSACircularQueue.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA
PURPOSE: Queue Data Structure using the circular method
REQUIRES: DSAQueue                                                     
Last Mod: 13/08/2021                                                            
--------------------------------*/  
import java.util.*;
import java.io.*;

public class DSACircularQueue extends DSAQueue
{   
    //Class Fields
    int frontIdx;
    int rearIdx;
    int queueLength;
    

//==============================================================================
    //CONSTRUCTORS

    //Default Constructor
    public DSACircularQueue()
    {
        super();
        frontIdx = 0;
        rearIdx = 0;
        queueLength = 100;
    }

    //Parameterized Constructor
    public DSACircularQueue(int maxCapacity) throws IllegalArgumentException
    {
        super(maxCapacity);
        frontIdx = 0;
        rearIdx = 0;
        queueLength = maxCapacity;
    }


//==============================================================================
    //MUTATOR METHODS

    //enqueue
    public void enqueue(Object value) throws IndexOutOfBoundsException
    {  
        if(isFull())
        {
            throw new IndexOutOfBoundsException();
        }
        else
        {
            //Checking if rearIdx is at the end of array first
            if(rearIdx == queueLength)
            {   
                //Moving the pointer to the front now
                //Wrapping around the queue
                rearIdx = 0;
            }

            //Adding to queue
            super.queue[rearIdx] = value;
            super.count++;
            rearIdx++;
        }
    }

    //dequeue
    public Object dequeue()
    {
        Object frontValue = null;

        if(isEmpty())
        {
            throw new NullPointerException();
        }
        else
        {   
            //checking if frontIdx is at the end of array first
            if(frontIdx == queueLength)
            {   
                //Moving pointer to front now
                //Wrapping around the queue
                frontIdx = 0;
            }
            
            frontValue = super.queue[frontIdx];
            super.count--; 
            frontIdx++;
        }

        return frontValue;
    }

    //peek
    public Object peek()
    {   
        if(isEmpty())
        {
            throw new NullPointerException();
        }
        else
        {
            return super.queue[frontIdx];
        }
    }


}




