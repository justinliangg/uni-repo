/**
 * DSA Final Assessment Question 4 - Q4MaxHeapTest.java
 *
 * Name : Justin Liang  
 * ID   : 19829186
 *
 **/
import java.util.*;
public class Q4MaxHeapTest
{
	public static void main(String args[])
	{
		System.out.println("\n===== Question 4: Testing Heaps =====\n");
	    
        //Java built in heap does not take in two parameters, only the value
		//Have modified the code, so it does not take in the string, just the int.
        PriorityQueue<Integer> testHeap = new PriorityQueue<Integer>(Comparator.reverseOrder());
		
        System.out.println("Adding items...");
		for (int i=0; i<10; i++)
		{
			testHeap.add(i);
            System.out.println("Added " + "value- " + i );
		}
		
        System.out.println();
        System.out.println("Removing items...");
		int temp;
		
		for (int i=0; i<10; i++)
		{
			temp = testHeap.remove();
			System.out.println(temp);
		}
		System.out.println("\n===== Tests Complete =====\n");
	}
}
