
/** 
** Name: Justin Liang
** ID: 19821986
** Comments: Previously used in prac 09
*/
import java.util.*;
class Sorts
{
    
//=============================================================================
    //SELECTION SORT


    public static void selectionSort(int[] A)
    {
        
        for(int i = 0; i < A.length; i++)
        {
            //after every pass, the smallest element will be on the left
            //therefore starts at i, after each pass
            int minIdx = i;
            for(int j = (i + 1); j < A.length; j++)
            {   
                //finding the index with the smallest element 
                //storing that index as minIdx
                if(A[j] < A[minIdx])
                {
                    minIdx = j;
                }
            }
            //Moving the element in minIdx to the front. 
            int temp  = A[minIdx];
            A[minIdx] = A[i];
            A[i] = temp;
        
        }
    }
   
  


//============================================================================
    //QUICKSORT


    public static void quickSort(int[] A)
    {
        quickSortRecurse(A, 0, (A.length - 1));
        
    }//quickSort()

    private static void quickSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
        if(leftIdx < rightIdx)
        {
            int pivotIdx = getPivotIdx(A, leftIdx, rightIdx);
            int newPivotIdx = doPartitioning(A, leftIdx, rightIdx, pivotIdx);

            //Do the same for left and right array
            quickSortRecurse(A, leftIdx, (newPivotIdx - 1)); /*left side*/
            quickSortRecurse(A, (newPivotIdx + 1), rightIdx); /*right side*/
        }
    }//quickSortRecurse()

    
    //getPivotIdx
    private static int getPivotIdx(int[] A, int leftIdx, int rightIdx)
    {   
        int pivotIdx = 0;
        /* median of three */
        int midIdx = (leftIdx + rightIdx)/2;
        int [] array = new int[3];
                
        //transfer to new array and sort.
        array[0] = A[leftIdx];
        array[1] = A[midIdx];
        array[2] = A[rightIdx];
        selectionSort(array);

        //middle of array is now the median value
        //finding which idx holds the median value
        if(array[1] == A[leftIdx])
        {
            pivotIdx = leftIdx;
        }
        else if(array[1] == A[midIdx])
        {
            pivotIdx = midIdx;
        }
        else
        {
            pivotIdx = rightIdx;
        }    

        return pivotIdx;
    }
    
    
    //doPartitioning
    private static int doPartitioning(int[] A, int leftIdx, int rightIdx, int pivotIdx)
    {
        //Moving pivotVal to the right most, so u can check against all values
        //in one iteration
        int pivotVal = A[pivotIdx];
        A[pivotIdx] = A[rightIdx];
        A[rightIdx] = pivotVal;
        
        int currIdx = leftIdx;
        for(int i = leftIdx; i <= (rightIdx - 1); i++)
        {
            //Moving all values to the left of currIdx
            if( A[i] < pivotVal )
            {
                int temp = A[currIdx];
                A[currIdx] = A[i];
                A[i] = temp;
                currIdx++;
            }
        }
        
        //Everything to the left of currIdx is now smaller than pivotVal
        //Therefore currIdx is the new pivotIdx
        A[rightIdx] = A[currIdx];
        A[currIdx] = pivotVal;
                
		return currIdx;	
    
    }
   



//=============================================================================
    //RADIX SORT (Was only able to implement LSD)


    public static void radixSort(int[] A)
    {   
        int base = 10;

        //finding the largest value first
        int largestValue = A[0];
        for(int i = 0; i < A.length; i++)
        {
            if(A[i] > largestValue)
            {
                largestValue = A[i];
            }
        }
        
        //getting the number of division required.
        int count = 0;
        int temp = 1;
        while(temp != 0)
        {
            temp = largestValue;    
            temp = (int) temp/ (int) (Math.pow(base, (count + 1)));
            count++;
        }
        
    
        //sorting
        //each increment in the for loop sorts the array by its place
        //starting from lsd to msd
        for(int i = 0; i <= count; i++)
        {
            countingSort(A, (int) Math.pow(base,i), base);
        }

      }    
     
    
    private static void countingSort(int[] A, int denominator, int base)
    {
        //array for the count
        int[] count = new int[base];

        //Counting the indexes. 
        for(int i = 0; i < A.length; i++)
        {
            int index = A[i]/denominator % base;
            count[index]++;
        }


        //modify the count so its count[i] is the index for the result array
        for(int i = 1; i < count.length; i++)
        { 
            count[i] = count[i] + count[i-1];
        }
       
        //copying over A into sorted order.
        int[] result = new int[A.length];
        for(int i = A.length - 1; i >= 0; i--)
        {
            int index = A[i]/denominator % base;
            result[count[index] - 1] = A[i];
            count[index]--;
        }

        //copying back again, could possible return result to reduce n
        // traversal again
        for(int i = 0; i < A.length; i++)
        {
            A[i] = result[i];
        }   
    }

}//end Sorts calcs
