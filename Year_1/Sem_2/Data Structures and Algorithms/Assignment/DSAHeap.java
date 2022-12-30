/*-------------------------------                                               
FILE: DSAHeap.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES: DSAHeapEntry                                       
Last Mod: 22/09/2021  
COMMENTS: Used previously in DSA Prac 08
--------------------------------*/  
import java.util.*;
import java.io.*;

public class DSAHeap
{
    //Class Fields
    private DSAHeapEntry[] heap;
    private int count;
    



/*---------------------------------------------------------------------------*/ 
    
    //CONSTRUCTOR
    public DSAHeap(int maxSize)
    {
        heap = new DSAHeapEntry[maxSize];
        count = 0;
    }




/*---------------------------------------------------------------------------*/

    //Mutator


    //add
    
    public void add(int priority, Object value)
    {   
        if(count == heap.length)
        {
            throw new ArrayIndexOutOfBoundsException("Heap is full");
        }
        else
        {    
            //Inserting into heap array
            DSAHeapEntry newEntry = new DSAHeapEntry(priority, value);
            heap[count] = newEntry;

            //Calling trickleUp to move it into the right place in the heap
            trickleUp(count);

            count++;
        }
    }
    


    //remove
    
    public Object remove()
    {      
        if(count == 0)
        {
            throw new NullPointerException("Heap is empty");
        }
        else
        {
            //Remove front of heap array and replace with last in heap array
            DSAHeapEntry removedEntry = heap[0];
            heap[0] = heap[count-1];
            heap[count-1] = null;

            //Since top of heap is in the wrong spot, call trickleDown to fix
            //the order of the heap again 
            trickleDown(0,(count-1));
            count--;
            
            return removedEntry.getValue();
        }
    }



    //heapSort

    public DSAHeapEntry[] heapSort(Object[] unsortedArr, int numElements)
    {
        heap = (DSAHeapEntry[]) unsortedArr;
        count = numElements;

        //heapify the unsortedArr
        heapify();

        //Sorting the array
        for(int i = (numElements - 1); i >= 0; i--)
        {
            //Push largest element to the back
            DSAHeapEntry temp = heap[0];
            heap[0] = heap[i];
            heap[i] = temp;

            //Top of heap in wrong spot, call trickleDown to fix.
            trickleDown(0, i);
        }

        return heap; 
    }

    /* heapSort for sorting current heapArray*/
    public DSAHeapEntry[] heapSort()
    {   
        int numElements = count;
        //Sorting the array
        for(int i = (numElements - 1); i >= 0; i--)
        {
            //Push largest element to the back
            DSAHeapEntry temp = heap[0];
            heap[0] = heap[i];
            heap[i] = temp;

            //Top of heap in wrong spot, call trickleDown to fix.
            trickleDown(0, i);
        }

        return heap; 
    }
        




/*---------------------------------------------------------------------------*/ 
    //Private Methods
    
    private void trickleUp(int index)
    {
        int parentIdx = (index-1) / 2;
        if(index > 0)
        {
            if(heap[index].getPriority() > heap[parentIdx].getPriority())
            {
                //Swap
                DSAHeapEntry temp = heap[parentIdx];
                heap[parentIdx] = heap[index];
                heap[index] = temp;

                //Checking the next level
                trickleUp(parentIdx);
            }
        }
    }



    //trickleDown
    
    private void trickleDown(int index, int numElements)
    {   
        int leftChildIdx =  index * 2 + 1;
        int rightChildIdx = leftChildIdx + 1;
            
        /* Don't want to touch if leftChildIdx or rightChildIdx is last index
        for sorting algorithm */
        if(leftChildIdx < numElements) 
        {   
            int largerIdx = leftChildIdx;
            if(rightChildIdx < numElements)
            {
                if(heap[leftChildIdx].getPriority() < heap[rightChildIdx].getPriority())
                {
                    largerIdx = rightChildIdx;
                }
            }
            
            /*if rightChildIdx is last index still check leftChildIdx*/
            if(heap[index].getPriority() < heap[largerIdx].getPriority())
            {
                //swap
                DSAHeapEntry temp = heap[largerIdx];
                heap[largerIdx] = heap[index];
                heap[index] = temp;

                //call trickleDown to check next level
                trickleDown(largerIdx, numElements);
            }
        }
        
    }

 

    //heapify

    private void heapify()
    {
        for(int i = (count/2)-1; i >= 0; i--)
        {
            trickleDown(i, count);
        }
    }
        
        


}//End DSAHeap Class



