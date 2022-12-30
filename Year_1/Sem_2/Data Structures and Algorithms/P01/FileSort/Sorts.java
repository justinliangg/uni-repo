
/** 
** Software Technology 152
** Class to hold various static sort methods.
*/
class Sorts
{
    // bubble sort
    public static void bubbleSort(Student [] array)
    {   
        int pass = 0;
        boolean sorted;
        do
        {   
            sorted = true;
            //Iterating through the array
            //Each iteration pushes the largest element to the end hence
            //A.Length - pass - 1 as the end is already sorted.
            for(int i = 0; i < (array.length - pass - 1 ); i++)
            {   
                //Comparing elements to their right 
                if(array[i].getId() > array[i+1].getId())
                {   
                    //Swap takes place
                    Student temp = array[i+1];
                    array[i+1] = array[i];
                    array[i] = temp;
                    
                    //If swaps are ran, it is still unsorted.
                    sorted = false;
                }
            }
            pass = pass + 1;
        }
        while(!sorted);
                        
    }//bubbleSort()

    // selection sort
    public static void selectionSort(Student [] array)
    {
        
        for(int i = 0; i < array.length; i++)
        {
            //after every pass, the smallest element will be on the left
            //therefore starts at i, after each pass
            int minIdx = i;
            for(int j = (i + 1); j < array.length; j++)
            {   
                //finding the index with the smallest element 
                //storing that index as minIdx
                if(array[j].getId() < array[minIdx].getId())
                {
                    minIdx = j;
                }
            }
            //Moving the element in minIdx to the front. 
            Student temp  = array[minIdx];
            array[minIdx] = array[i];
            array[i] = temp;
        
        }
    }
   
    // selectionSort()

    // insertion sort
    public static void insertionSort(Student [] array)
    {    
        for(int i = 1; i < array.length; i++)
        {   
            int j = i;
            Student temp = array[j];
            
            //Checks element to its left if its larger
            while((j > 0) && (array[j - 1].getId() > temp.getId()))
            {   
                //If true, it shifts elements up one index
                //To make space for insertion    
                array[j] = array[j-1];
                j = j - 1;
            }
            //inserts when temp is larger than the element to its left.  
            array[j] = temp;
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
