
/** 
** Software Technology 152
** Class to hold various static sort methods.
*/
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
    }//mergeSort()
    private static void mergeSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
    }//mergeSortRecurse()
    private static void merge(int[] A, int leftIdx, int midIdx, int rightIdx)
    {
    }//merge()


    // quickSort - front-end for kick-starting the recursive algorithm
    public static void quickSort(int[] A)
    {
    }//quickSort()
    private static void quickSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
    }//quickSortRecurse()
    private static int doPartitioning(int[] A, int leftIdx, int rightIdx, int pivotIdx)
    {
		return 0;	// TEMP - Replace this when you implement QuickSort
    }//doPartitioning


}//end Sorts calss
