/*-------------------------------                                               
FILE: DSAStack.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES: DSALinkedList                                                    
Last Mod: 20/08/2021                                                         
--------------------------------*/  
import java.util.*;
import java.io.*;
public class DSAStack implements Iterable,Serializable
{   
    //Class Fields
    private DSALinkedList stack; 

/*----------------------------------------------------------------------------*/
    //CONSTRUCTORS
        
    //Default Constructor
    public DSAStack()
    {
        stack = new DSALinkedList();
    }
   
    //Iterator 
    public Iterator iterator()
    {
        return stack.iterator();
    }
        
/*----------------------------------------------------------------------------*/
    //ACCESSOR METHODS
    
    //isEmpty
    public boolean isEmpty()
    {
        return (stack.isEmpty());
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
            topValue = stack.peekFirst();
        }

        return topValue;
    }


/*----------------------------------------------------------------------------*/

    //MUTATOR METHODS

    public void push(Object value)
    {
        stack.insertFirst(value);
    }
            
    public Object pop() throws NullPointerException
    {   
        
        return (stack.removeFirst());
    }

}



       
            


    
