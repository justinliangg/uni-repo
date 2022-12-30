/**
 * DSA Final Assessment Question 3 - MinHeapTest.java
 *
 * Name : Justin Liang   
 * ID   : 19821986
 *
 **/
 
public class MinHeapTest
{
    public static void main(String[] args)
    {
        //Constructing MinHeap
        FA_MinHeap t = new FA_MinHeap(10);
        
		System.out.println("\n===== Question 3: Testing Min Heap =====\n");

//-----------------------------------------------------------------------------
        //Testing MinHeap
        
        //Test 1: Testing MinHeap Works as expected
        try
        {   
            System.out.print("Test MinHeap: ");
            
            //adding first
            for(int i = 1; i < 11; i++)
            {
                t.add(i,i);
            }
            
            //removing and checking that its in order 
            int expectedValue = 1;
            for(int i = 0; i < 10; i++)
            {
                int value = (int) t.remove();
                if(value != expectedValue)
                {
                    throw new IllegalArgumentException();
                }
                //adding to check next one.
                expectedValue++;
            }

            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }

//-----------------------------------------------------------------------------
        //Testing Exceptions thrown by add and remove
        
        //Test 2: add exception
        try
        {
            System.out.print("\nAdd() exception: ");
  
            //filling the heap first
            for(int i = 1; i < 11; i++)
            {
                t.add(i,i);
            }

            //Adding again should throw exception because it is full
            t.add(11,11);

            System.out.println("failed");
        }
        catch(PracExamException e)
        {
            System.out.println("passed");
        }

        
        //Test 3: remove exception
        try
        {
            System.out.print("remove() exception: ");
  
            //making the heap empty
            for(int i = 1; i < 11; i++)
            {
                t.remove();
            }

            //Removing again should throw exception because it is empty.
            t.remove();

            System.out.println("failed");
        }
        catch(PracExamException e)
        {
            System.out.println("passed");
        }

//----------------------------------------------------------------------------
        //Testing Reading from file 
        
        System.out.println("\n=================================================");
        FA_MinHeap t2 = new FA_MinHeap(10);

        try
        {   
            //reading from file
            System.out.println("============ READING FROM FILE ===========");
            t2.storeData("Q3HeapData.txt");

            //removing from queue
            System.out.println("=========== REMOVING FROM QUEUE ==========");
            for(int i = 0; i < 11; i++)
            {
                System.out.println("Removed: " + t2.remove());
                t2.printQueue();
            }
        }
        catch(Exception e)
        {}

	    System.out.println("\n===== End Tests =====\n");
    }
}
            
        
