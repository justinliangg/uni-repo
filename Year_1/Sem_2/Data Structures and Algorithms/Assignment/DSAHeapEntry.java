/*-------------------------------                                               
FILE: DSAHeapEntry.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES:                                                    
Last Mod: 22/09/2021      
COMMENTS: used previously in DSA Prac 08
--------------------------------*/  
import java.util.*;
import java.io.*;

public class DSAHeapEntry
{
    //Class Fields
    private int priority;
    private Object value;
    

/*---------------------------------------------------------------------------*/
    //Constructor

    public DSAHeapEntry(int inPriority, Object inValue)
    {
        priority = inPriority;
        value = inValue;
    }




/*---------------------------------------------------------------------------*/
    //Mutators
        

    //setPriority
        
    public void setPriority(int inPriority)
    {
        priority = inPriority;
    }
    


    //setValue
        
    public void setValue(Object inValue)
    {
        value = inValue;
    }




/*---------------------------------------------------------------------------*/
    //Accessors


    //getPriority
        
    public int getPriority()
    {
        return priority;
    }



    //getValue
        
    public Object getValue()
    {
        return value;
    }


} //End DSAHeapEntry Class


