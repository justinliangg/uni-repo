/*-------------------------------                                               
FILE: DSAHashTable.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES:                                                    
Last Mod: 18/09/2021  
COMMENTS: Used previously in DSA Prac 07
--------------------------------*/  
import java.util.*;
import java.io.*;

public class DSAHashTable implements Iterable, Serializable
{
    //Class Fields
    private DSAHashEntry hashArray[];
    private int count;


/*---------------------------------------------------------------------------*/
    /* Constructor */


    //Parameter Constructor

    public DSAHashTable()
    {   
        /* Initialize array */
        int actualSize = nextPrime(11);
        hashArray = new DSAHashEntry[actualSize];
        /* Initializing DSAHashEntry in array */
        for(int i = 0; i < actualSize; i++)
        {
            hashArray[i] = new DSAHashEntry();
        }
        count = 0;
    }


    //Iterator Method
    public Iterator iterator()
    {
        return new DSAHashTableIterator(this.hashArray);
    }


/*---------------------------------------------------------------------------*/
    
    /* Mutators */


    //put

    public void put(String inKey, Object inValue)
    {
        /* Checking if key already exists */
        if( hasKey(inKey) )
        {
            throw new IllegalArgumentException("Key already exists");
        }
        else
        {  
            /* checking if load factor is past max load factor */
            if( getLoadFactor() >= 0.7)
            {
                resize();
            }

            /*get index first*/
            int hashIdx = hash(inKey);  

            /*Inserting value based on state*/
            int state = hashArray[hashIdx].state;
            if(state != 0)
            {       
                /* finding index with a free spot */
                boolean found = false;
                while(!found)
                {
                    /*Ensuring wrap around*/
                    hashIdx = (hashIdx + stepHash(inKey)) % hashArray.length;
                    
                    /*finding free spot*/
                    state = hashArray[hashIdx].state;
                    if(state == 0 || state == -1)
                    {
                        found = true;
                    }
                }
            }
      
            /*Inserting value into hashArray*/
            hashArray[hashIdx].key = inKey;
            hashArray[hashIdx].value = inValue;
            hashArray[hashIdx].state = 1; /*set state to used now*/
            count++;
        }
    }  



    //remove 
    
    public Object remove(String inKey) throws IllegalArgumentException
    {
        if( getLoadFactor() <= 0.10 )
        {
            resize();
        }

        /* find hashIdx first */
        int hashIdx = findIdx(inKey);
       
        /*get value to return*/
        Object value = hashArray[hashIdx].value;    

        /*reset HashEntry */
        hashArray[hashIdx].key = "";
        hashArray[hashIdx].value = null;
        hashArray[hashIdx].state = -1; /*previously used*/
        count--;

        return value;
    }
        


    
/*---------------------------------------------------------------------------*/
    /* Accessors */
   

    //get

    public Object get(String inKey) throws IllegalArgumentException
    {
        int hashIdx = findIdx(inKey);
        return (hashArray[hashIdx].value);
    }    
     
    

    //getLoadFactor

    public double getLoadFactor()
    {
        double ans = (double) count / (double) (hashArray.length);
        /* Rounding to 2 decimal places */
        double loadFactor = (Math.round(ans * 100.0)) / 100.0;

        return loadFactor;
    }
    

    //hasKey

    public boolean hasKey(String inKey)
    {   
        boolean hasKey = false;
        try
        {
            /* Will throw exception if key does not exist */
            findIdx(inKey);
            hasKey = true;
        }
        catch(IllegalArgumentException e)
        {
            /*Key does not exist */
            hasKey = false;
        }
        
        return hasKey;
    }


    //getCount
    
    public int getCount()
    {
        return count;
    }
    

    //Export
    
    public Object[][] export()
    {   
        Object[][] exportArray = new Object[2][count];
        int j = 0;
        for(int i = 0; i < hashArray.length; i++)
        {   
            //Getting only non empty values;
            if( !hashArray[i].key.equals("") )
            {
                exportArray[0][j] = hashArray[i].key;
                exportArray[1][j] = hashArray[i].value;
                j++; 
            }
        }

        return exportArray;
    }





/*---------------------------------------------------------------------------*/
    //Private Functions 

    private int nextPrime(int tableSize)
    {
        int primeVal;
        
        if( (tableSize % 2) == 0 )
        {   
            /* Even numbers are never prime */
            primeVal = tableSize - 1;
        }
        else
        {
            /*Pre decrement to check if current val is a prime*/
            primeVal = tableSize - 2;
        }
        
        int ii;
        double rootVal;
        boolean isPrime = false;
        do
        {
            primeVal = primeVal + 2;
            ii = 3;
            isPrime = true;
            rootVal = Math.sqrt(primeVal);

            do
            {
                if( (primeVal % ii) == 0 )
                {
                    isPrime = false;
                }   
                else
                {
                    ii = ii + 2;
                }
            }
            while( (ii <= rootVal) && isPrime );
        }
        while(!isPrime);

        return primeVal;
    }



    //hash
    private int hash(String inKey)
    {
        long hashIdx = 0;

        for(int ii = 0; ii < inKey.length(); ii++)
        {
            hashIdx = (33 * hashIdx) + inKey.charAt(ii);
        }
        
        return ( (int) (hashIdx % (long) hashArray.length) );
    }
    
    

    //stepHash
    private int stepHash(String inKey)
    {   
        //Calculating keyValue
        int keyValue = 0;
        for(int i = 0; i < inKey.length(); i++)
        {
            keyValue = keyValue + inKey.charAt(i);
        }
    
        //Calculating maxStep
        int maxStep = nextPrime( (hashArray.length) / 4 );
        
        int hashStep = maxStep - (keyValue % maxStep);

        return hashStep;
    }



    //find 
    private int findIdx(String inKey)
    { 
        int hashIdx = hash(inKey);
        int origIdx = hashIdx;
        boolean found = false;
        boolean giveUp = false;
        
        while(!found && !giveUp)
        {
            if( hashArray[hashIdx].state == 0 )
            {
                giveUp = true;
            }
            else if( inKey.equals(hashArray[hashIdx].key) )
            {
                found = true;
            }
            else
            {   
                /* ensuring wrap around */
                hashIdx = (hashIdx + stepHash(inKey)) % hashArray.length;
                
                if(hashIdx == origIdx)
                {
                    giveUp = true;
                }
            }
        }
            
        /* Throw exception if given up */
        if(!found)
        {
            throw new IllegalArgumentException("Key does not exist");
        }
        
        return hashIdx;
    }
        
       
    
    //RESIZE

    //upsize()
    
    private void resize()
    {   
        /* resize so its 30 percent load factor */
        double ans  = ((double) count / 30.0) * 100.0;
        int newSize = (int) Math.round(ans);
        newSize = nextPrime(newSize); /* Making it a prime */
        DSAHashEntry [] newArray = new DSAHashEntry[newSize];   
       
        /* Initialize newArray first */
        for(int i= 0; i < newArray.length; i++)
        { 
            newArray[i]  = new DSAHashEntry();
        }

        /* Copy over old array onto new Array */
        DSAHashEntry [] oldArray = hashArray;
        hashArray = newArray; /* Swap places with old array */
        count = 0; /*reset count*/
        for(int i = 0; i < oldArray.length; i++)
        {   
            /* Copy only the ones with keys from oldArray */
            if(!oldArray[i].key.equals(""))
            {
                put(oldArray[i].key, oldArray[i].value);
            }
        }
    }    
    
     
     
        
//============================================================================
    //Private Inner Class
    
    private class DSAHashEntry implements Serializable
    {       
        //Class Fields

        /*Set public because it is a private inner class */
        public String key;
        public Object value;
        public int state;


/*---------------------------------------------------------------------------*/  
        //Constructor
        
        //Default Constructor 
        public DSAHashEntry()
        {
            key = "";
            value = null;
            state = 0;
        }
        

        /*No Getters and Setters required due to public class fields*/
    }


/*----------------------------------------------------------------------------*/


    //DSAHashTableIterator Class
    private class DSAHashTableIterator implements Iterator, Serializable
    {   
        //Class Fields
        private DSAHashEntry[] list = null;
        private int index;

        //Constructor
        public DSAHashTableIterator(DSAHashEntry[] pList)
        {
            list = pList;
            index = 0;
        }
        
        //Methods

        //hasNext()
        public boolean hasNext()
        {
            boolean hasNext = false;
            for(int i = index; i < list.length; i++)
            {
                if(!(list[i].key.equals("")))
                {
                    hasNext = true;
                }
            }
            
            return hasNext;
        }

        //next()
        public String next()
        {   
            String key = null;
            boolean found = false;
            
            while(!found && hasNext())
            {
                //Going through the array to find a key.
                if(!(list[index].key.equals("")))
                {  
                    key = list[index].key;
                    found = true;
                }
                index++;
            }
            
            return key;
        }


        //remove
        public void remove()
        {
            throw new UnsupportedOperationException("Not Supported");
        }

    }

}


    
