import java.util.*;
	
public class Date
{
	//CLASS FIELDS
	private int day;
	private int month;
	private int year;


	//Constructor with parameter
	public Date(int pDay, int pMonth , int pYear)
	{
		day = pDay;
		month = pMonth;
		year = pYear;
	}
	
	//Copy Constructor 
	public Date(Date pDate)
	{

		day = pDate.getDay();
		month = pDate.getMonth();
		year = pDate.getYear();

	}

	//Default Constructor 
	public Date()
	{
		day = 1;
		month = 1;
		year = 2021;

	}


	//ACCESSORS


	//Day Accessor 
	public int getDay()
	{
		return day;
	}

	//Month Accessor
	public int getMonth()
	{
		return month;
	}

	//Year Accessor
	public int getYear()
	{
		return year;
	}


	//Mutators
	
	//Day Mutator
	public void setDay(int pDay)
	{
		day = pDay;
	}

	//Month Mutator
	public void setMonth(int pMonth)
	{
		month = pMonth;
	}

	//year Mutator
	public void setYear(int pYear)
	{
		year = pYear;
	}

	//Equals Function 
	public boolean equals(Object pObject)
	{
		boolean	isEqual = false;
		Date pDate = null;
		if (pObject instanceof Date)
			{ 
				pDate = (Date) pObject;
				
				if (day == pDate.getDay())
				{
					if(month == pDate.getMonth())
					{
						if(year == pDate.getYear())
						{
							isEqual = true;
						}
					}
				}	
			
			}
		return isEqual;
					
						
	}



	//toString Function
	public String toString()
	{	
		String dateString = ( day + "/" + month + "/" + year);
		return dateString;
	}

}	
