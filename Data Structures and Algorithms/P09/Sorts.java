
/** 
** Software Technology 152
** Class to hold various static sort methods.
*/
import java.util.*;
class Sorts
{
    // bubble sort
    public static void bubbleSort(int[] A)
    {   
        int pass = 0;
        boolean sorted;
        do
        {   
            sorted = true;
            //Iterating through the array
            //Each iteration pushes the largest element to the end hence
            //A.Length - pass - 1 as the end is already sorted.
            for(int i = 0; i < (A.length - pass - 1 ); i++)
            {   
                //Comparing elements to their right 
                if(A[i] > A[i+1])
                {   
                    //Swap takes place
                    int temp = A[i+1];
                    A[i+1] = A[i];
                    A[i] = temp;
                    
                    //If swaps are ran, it is still unsorted.
                    sorted = false;
                }
            }
            pass = pass + 1;
        }
        while(!sorted);
                        
    }//bubbleSort()

    // selection sort
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
   
    // selectionSort()

    // insertion sort
    public static void insertionSort(int[] A)
    {    
        for(int i = 1; i < A.length; i++)
        {   
            int j = i;
            int temp = A[j];
            
            //Checks element to its left if its larger
            while((j > 0) && (A[j - 1] > temp))
            {   
                //If true, it shifts elements up one index
                //To make space for insertion    
                A[j] = A[j-1];
                j = j - 1;
            }
            //inserts when temp is larger than the element to its left.  
            A[j] = temp;
        }
    }// insertionSort()



    // mergeSort - front-end for kick-starting the recursive algorithm
    public static void mergeSort(int[] A)
    {
        mergeSortRecurse(A, 0, (A.length - 1));
    }//mergeSort()

    private static void mergeSortRecurse(int[] A, int leftIdx, int rightIdx)
    {   
        if( leftIdx < rightIdx )
        {
            //Getting half way point
            int midIdx = (leftIdx + rightIdx) / 2;

            //recurse
            //Do left Array first
            mergeSortRecurse(A, leftIdx, midIdx);
            //Do right array
            mergeSortRecurse(A, (midIdx + 1), rightIdx);

            //Once it unwinds start merging.
            merge(A, leftIdx, midIdx, rightIdx);
        }
 
    }//mergeSortRecurse()

    private static void merge(int[] A, int leftIdx, int midIdx, int rightIdx)
    {
        //Temp array
        int[] temp = new int[A.length];
        
        //Pointers
        int i = leftIdx;
        int j = midIdx + 1;
        int k = 0;

        while( (i <= midIdx) && (j <= rightIdx) )
        {
            if( A[i] <= A[j] )
            {
                temp[k] = A[i];
                i++;
            }
            else if ( A[i] >= A[j] )
            {
                temp[k] = A[j];
                j++;
            }
            k++;
        }

        for(i = i; i <= midIdx; i++)
        {   
            temp[k] = A[i];
            k++;
        }
        
        for(j = j; j <= rightIdx; j++)
        {
            temp[k] = A[j];
            k++;
        }

        //Copying temp array to the old array
        for(k = leftIdx; k <= rightIdx; k++)
        {
            A[k] = temp[k - leftIdx];
        }
        
    }//merge()



    // quickSort - front-end for kick-starting the recursive algorithm
    public static void quickSort(int[] A)
    {
        quickSortRecurse(A, 0, (A.length - 1));
        
    }//quickSort()

    private static void quickSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
        if(leftIdx < rightIdx)
        {
            int pivotIdx = getPivotIdx(A, leftIdx, rightIdx, 3);
            int newPivotIdx = doPartitioning(A, leftIdx, rightIdx, pivotIdx);

            //Do the same for left and right array
            quickSortRecurse(A, leftIdx, (newPivotIdx - 1)); /*left side*/
            quickSortRecurse(A, (newPivotIdx + 1), rightIdx); /*right side*/
        }
    }//quickSortRecurse()

    private static int getPivotIdx(int[] A, int leftIdx, int rightIdx, int choice)
    {   
        int pivotIdx = 0;
        switch (choice)
        {
            case 1: /* Left most index */ 
                pivotIdx = leftIdx;
                break;

            case 2: /* median of three */
                int midIdx = (leftIdx + rightIdx)/2;
                int [] array = new int[3];
                
                //transfer to new array and sort.
                array[0] = A[leftIdx];
                array[1] = A[midIdx];
                array[2] = A[rightIdx];
                bubbleSort(array);

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
                break;

            case 3: /* random pivot */
    
                //Obtained from Edpresso
                //https://www.educative.io/edpresso/how-to-generate-random-numbers-in-java
                //(Accessed on 1st October 2021)
                pivotIdx = (int)Math.floor(Math.random()*(rightIdx-leftIdx+1)+leftIdx); 
                //End code obtained from Edpresso
        } 
        return pivotIdx;
    }

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
                
		return currIdx;	// TEMP - Replace this when you implement QuickSort
    }//doPartitioning
    
    

}//end Sorts calcs
