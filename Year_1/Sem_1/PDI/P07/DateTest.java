import java.util.*;
public class DateTest
{

	public static void main (String[] args )
	{	
		//Default Constructor test
		Date defaultDate = new Date();

		//Copy Constructor test
		Date copyDate = new Date(defaultDate);
	
		//Test equals
		System.out.println(defaultDate.equals(copyDate));
	
		//parameter constructor test
		Date date1 = new Date(6,6,2001);
		System.out.println(date1.getDay());
		System.out.println(date1.getMonth());
		System.out.println(date1.getYear());

		//Using mutators to change date1

		date1.setDay(06);
		date1.setMonth(06);
		date1.setYear(2001);
	
		System.out.println(date1.toString());

		//test date1 and default
	
		System.out.println(date1.equals(defaultDate));
		int [] intArray = {1,2,3,4,5};	
		Date [] dateArray = new Date[5];
	        
		dateArray[0] = date1;
		dateArray[1] = defaultDate;
		dateArray[2] = copyDate;
			
		for(int i =0; i<3; i++)
		   {
		   	System.out.println(dateArray[i]);
	     	}

	}
}
