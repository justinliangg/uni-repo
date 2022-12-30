/**
 * DSA Final Assessment Question 3 - FA_MinHeap.java
 *
 * Name : Justin Liang  
 * ID   : 19829186
 *
 **/
import java.io.*; //added to use fileIO  
public class FA_MinHeap //Changed name to FA_MinHeap
{

	public class HeapEntry
	{
	private int priority;
	private Object value;

	public HeapEntry(int priority, Object value)
	{
		this.priority = priority;
		this.value = value;
	}

	public int getPriority()
	{
		return priority;
	}

	public Object getValue()
	{
		return value;
	}

	}

	private HeapEntry[] heap;
	private int count;
	private int MAXSIZE = 10;
	
    
    //Changed Q3MaxHeap to FA_MinHeap()
	public FA_MinHeap(int size) 
	{   
        //modified to allow for size input
        if(size > 0)
        {
		    heap = new HeapEntry[size];
		    count = 0;
        }
        else
        {
            throw new IllegalArgumentException("Size cannot be less than 0");
        }

	}


	public void add(int priority, Object value) throws PracExamException
	{
        //added to throw exception if adding to a full heap
        if( count == heap.length )
        {
	        throw new PracExamException("Heap is full!");   
        }
        else
        {   
            HeapEntry entry = new HeapEntry(priority, value);
		    heap[count] = entry;
		    count++;
		    trickleUp(count-1);
        }
	}

	public Object remove() throws PracExamException
	{   
        //added to throw exception if removing an empty heap
        if( count == 0 )
        {
            throw new PracExamException("Heap is empty!");
        }
        else
        {   
		    Object retValue;
		    HeapEntry entry = heap[0];
		    retValue = entry.getValue();
		    heap[0] = heap[count-1];
		    heap[count-1] = null;
		    count--;
		    trickleDown(0);
		    return retValue;
        }
	}

	private void trickleDown(int index)
	{
	    int leftIdx = index * 2 + 1;
	    int rightIdx = leftIdx + 1;
	    int smallIdx;
	    HeapEntry temp;
    
	    if (leftIdx < count)
	    {   
            //changed instead of largeIdx to smallIdx, finds the smallest
            //out of the two children.
		    smallIdx = leftIdx;			
		    if (rightIdx < count)
		    {
		        if (heap[leftIdx].getPriority() > heap[rightIdx].getPriority())
			    {
				    smallIdx = rightIdx;
			    }
		    }
            //changed so that if index priority is < than parentIndex priority
		    //it swaps.
            if (heap[smallIdx].getPriority() < heap[index].getPriority())
		    {
              	temp = heap[smallIdx];
              	heap[smallIdx] = heap[index];
              	heap[index] = temp;
			    trickleDown(smallIdx);
		    }	
	   }
	}
	
	private void trickleUp(int index)
	{
		int parentIndex;
		HeapEntry temp;

		parentIndex = (index-1)/2;

		if (index > 0 )
		{   
            //changed so that if index priority is < than parentIndex priority
            //it swaps.
			if (heap[index].getPriority() < heap[parentIndex].getPriority())
			{
				temp = heap[parentIndex];
				heap[parentIndex] = heap[index];
				heap[index] = temp;
				trickleUp(parentIndex);
			}
		}
		
	}


//============================================================================
    //Implementations
        
    //Used to print the contents of the queue.
    public void printQueue()
    {   
        System.out.print("Queue: ");
        for(int i = 0; i < count; i++)
        {
            System.out.print("(" + heap[i].getPriority() + ", " + heap[i].getValue()
                             + ") ");
        }
        System.out.println("\n");
    }
    
    
    //Getting data from file and storing in the heap.
    public void storeData(String fileName)
    {
        //Get data count
        int size = countNumRows(fileName);
        
        //Initializing heapEntry array and replacing heap
        HeapEntry[] newHeap = new HeapEntry[size];
        heap = newHeap;
        count = 0;
            
        //Storing values from file
        fileRead(fileName);
    }
        


//============================================================================
    //FILEIO METHODS (Code retrieved from Prac 08)
        
    //SUBMODULE: fileRead
    //IMPORT: fileName(String), array(Array of HeapEntry)
    //EXPORT: nil 
    //ASSERTION: get priority and value from file and store in array

    private void fileRead(String fileName)
    {
        FileInputStream fileInput = null;
        InputStreamReader reader;
        BufferedReader bfr;
        String line;
        
        try
        {   
            //initalising objects and variables
            fileInput = new FileInputStream(fileName);
            reader = new InputStreamReader(fileInput);
            bfr = new BufferedReader(reader);
            line = bfr.readLine();
            int numElements = 0;
            
            while(line != null)
            {
                String[] column = processLine(line); 
                
                //This ensures that row can only have two values
                if(column.length == 2)
                {  
                    try
                    {   
                        int priority = Integer.parseInt(column[0]);
                        String value = column[1];
                        
                        System.out.println("Adding: (" + priority + "," + value
                                           + ")");
                        this.add(priority,value);
                        this.printQueue();

                        numElements++;
                    }
                    //Exception thrown by Integer.parseInt
                    catch(NumberFormatException x)
                    {}
                    catch(PracExamException x2)
                    {}
                }
                line = bfr.readLine();
            }
            fileInput.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Please enter a valid file name!\n");
        }
        catch(IOException e)
        {
            if(fileInput != null)
            {
                try 
                {
                    fileInput.close();
                }
                catch(IOException ex2)
                {}
            }
        }
    }


    //SUBMODULE: processLine
    //IMPORT: pRow(String)
    //EXPORT: inColumn(Array of Strings)
    //ASSERTION: parse pRow every " "

    private String[] processLine(String pRow)
    { 
        String [] inColumn;
        //splits every " " 
        inColumn = pRow.trim().split("\\s+");
        
        //Removing all white spaces between in the individual string. 
        for(int i = 0; i < inColumn.length; i++)
        {
            inColumn[i] = inColumn[i].replaceAll(" ", "");
        }
        
        return inColumn;
    }

  
    //SUBMODULE: countNumRows
    //IMPORT: fileName(String)
    //EXPORT: numRows(int)
    //ASSERTION: countNumRows in file

    private int countNumRows(String fileName)
    {
        FileInputStream fileInput = null;
        InputStreamReader reader;
        BufferedReader bfr;
        String line;
        int numElements = 0;
        
        try
        {   
            //initalising objects and variables
            fileInput = new FileInputStream(fileName);
            reader = new InputStreamReader(fileInput);
            bfr = new BufferedReader(reader);
            line = bfr.readLine();
            
            while(line != null)
            {   
                numElements++;
                line = bfr.readLine();
            }
            fileInput.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Please enter a valid file name!\n");
        }
        catch(IOException e)
        {
            if(fileInput != null)
            {
                try 
                {
                    fileInput.close();
                }
                catch(IOException ex2)
                {}
            }
        }

        return numElements;
    }

        
}
