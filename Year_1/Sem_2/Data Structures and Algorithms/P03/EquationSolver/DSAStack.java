/*-------------------------------                                               
FILE: DSAStack.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES: nil                                                     
Last Mod: 12/08/2021                                                            
--------------------------------*/  

public class DSAStack
{   
    //Class Fields
    private Object [] stack = null;
    private int count;
    
    //Constants
    private final int DEFAULT_CAPACITY = 100;
    

/*----------------------------------------------------------------------------*/
    //CONSTRUCTORS
        
    //Default Constructor
    public DSAStack()
    {
        stack = new Object[DEFAULT_CAPACITY];
        count = 0;
    }

    //Parameter Constructor
    public DSAStack(int maxCapacity) throws IllegalArgumentException
    {   
        //Validating input
        if(maxCapacity > 0)
        {
            stack = new Object[maxCapacity];
            count = 0;
        }
        else
        {   
            throw new IllegalArgumentException("Capacity not valid");
        }
    }


/*----------------------------------------------------------------------------*/
    //ACCESSOR METHODS

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
        
        if(count == stack.length)
        {
            full = true;
        }

        return full;
    }
    

    //top
    public Object top() throws NullPointerException
    {
        Object topValue = null;

        if(isEmpty())
        {
            throw new NullPointerException();
        }
        else
        {
            topValue = stack[count - 1];
        }

        return topValue;
    }


/*----------------------------------------------------------------------------*/

    //MUTATOR METHODS

    public void push(Object value) throws IndexOutOfBoundsException
    {
        if(isFull())
        {
            throw new IndexOutOfBoundsException();
        }
        else
        {   
            stack[count] = value;

            //Adding to count after adding
            count++;
        }
    }
            
    public Object pop()
    {   
        //Validates in top()
        Object topValue = top();
        count = count - 1;

        return topValue;
    }

}



       
            


    
