/*-------------------------------                                               
FILE: UnitTestHeapSort.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA
PURPOSE: test heapSort function
REQUIRES:                                                     
Last Mod: 23/09/2021                                                            
--------------------------------*/  

public class UnitTestHeapSort
{
    public static void main(String[] args)
    {
        DSAHeapEntry[] array = new DSAHeapEntry[7000];  
        DSAHeap heap = new DSAHeap(10);
        
        //fillArray
        FileIO.fileRead("RandomNames7000.csv", array);
        
        //Sort array
        heap.heapSort(array,7000);

        //Write to file
        FileIO.writeToFile(array);
    }
}
