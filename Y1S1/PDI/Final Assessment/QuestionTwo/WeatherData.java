/*-------------------------------
  FILE: WeatherData.java
  Author: Justin Liang (19821986)
  UNIT: PDI
  COMMENTS : WeatherData class to store weather information
--------------------------------*/

import java.util.*;
import java.io.*;

public class WeatherData
{
    //CLASS VARIABLES
    private String code;
    private String stationNumber;
    private int year;
    private int month;
    private int day;
    private double maxTemp;




    //CONSTRUCTOR METHODS
    
    //Parameter Constructor
    public WeatherData(String pCode, String pStationNumber, int pYear, int pMonth, int pDay, double pMaxTemp)
    {   
        if(isValidParam(pCode, pStationNumber, pYear, pMonth, pDay, pMaxTemp))
        {
            code = pCode;
            stationNumber = pStationNumber;
            year = pYear;
            month = pMonth;
            day = pDay;
            maxTemp = pMaxTemp;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    //Copy Constructor
    public WeatherData(WeatherData pWeatherData)
    {
        code = pWeatherData.getCode();
        stationNumber = pWeatherData.getStationNumber();
        year = pWeatherData.getYear();
        month = pWeatherData.getMonth();
        day = pWeatherData.getDay();
        maxTemp = pWeatherData.getMaxTemp();
    }

    //Default Constructor
    public WeatherData()
    {
        code = "IDCJAC0010";
        stationNumber = "4083";
        year = 2021;
        month = 1;
        day = 1;
        maxTemp = 30.0;
    }




    //ACCESSOR METHODS
    
    public String getCode()
    {
        return code;
    }

    public String getStationNumber()
    {
        return stationNumber;
    }

    public int getYear()
    {
        return year;
    } 

    public int getMonth()
    {
        return month;
    }

    public int getDay()
    {
        return day;
    }

    public double getMaxTemp()
    {
        return maxTemp;
    }




    //MUTATOR METHODS
    
    public void setCode(String pCode)
    {
        if(!pCode.equals("") && pCode != null)
        {
            code = pCode;
        }
    }

    public void setStationNumber(String pStationNumber)
    {
        if(!pStationNumber.equals("") && pStationNumber != null)
        {
            stationNumber = pStationNumber;
        }
    }

    public void setYear(int pYear)
    {
        if(pYear >= 1600)
        {
            year = pYear;
        }
    }

    public void setMonth(int pMonth)
    {
        if(pMonth >= 1 && pMonth <= 12)
        {
            month = pMonth;
        }
    }

    public void setDay(int pDay)
    {   
        if(isValidDay(year,month,pDay))
        {
            day = pDay;
        }
    }

    public void setMaxTemp(double pMaxTemp)
    {
        if(pMaxTemp >= -100.0 && pMaxTemp <= 70.0)
        {
            maxTemp = pMaxTemp;
        }
    }




    //SUBMODULE: equals
    //IMPORT: pObject(Object)
    //EXPORT: isEqual(Boolean)
    //ASSERTION: used to check if two objects are equal

    public boolean equals(Object pObject)
    {
        boolean isEqual = false;
        WeatherData inWeatherData = null;

        if(pObject instanceof WeatherData)
        {
            inWeatherData = (WeatherData) pObject;
            if(code.equals(inWeatherData.getCode()))
            {
                if(stationNumber.equals(inWeatherData.getStationNumber()))
                {
                    if(year == inWeatherData.getYear())
                    {
                        if(month == inWeatherData.getMonth())
                        {
                            if(day == inWeatherData.getDay())
                            {
                                if(maxTemp == inWeatherData.getMaxTemp())
                                {
                                    isEqual = true;
                                }
                            }
                        }
                    }
                }
            }
        }

        return isEqual;
    }




    //SUBMODULE: toString
    //IMPORT: nil
    //EXPORT: weatherDataString(String)
    //ASSERTION: returns the class variables as strings

    public String toString()
    {
        String weatherDataString = "Code: " + code + "\nStationNumber: " + stationNumber + "\nYear: " + year
                                   + "\nMonth " + month + "\nDay: " + day + "\nMaxTemp: " + maxTemp;
        
        return weatherDataString;
    }




    //SUBMODULE: formattedDate
    //IMPORT: nil 
    //EXPORT: dateString(String)
    //ASSERTION: returns class variables related to date as strings
    //           In day/month/year format
    
    public String formattedDate()
    {
        String dateString = day + "/" + month + "/" + year;

        return dateString;
    }
    



    //SUBMODULE: leapYear
    //IMPORT: pYear(Integer)
    //EXPORT: isLeapYear(Boolean)
    //ASSERTION: Checks if pYear is a leap year
    //           Returns true if pYear is a leap year
    
    private static boolean leapYear(int pYear)
    {   
        boolean isLeapYear = false;

        if(pYear % 4 == 0)
        {
            if(pYear % 100 == 0)
            {
                    if(pYear % 400 == 0)
                    {
                        isLeapYear = true;
                    }
            }
            else if(pYear % 100 != 0)
            {
                isLeapYear = true;
            } 
        }

        return isLeapYear;
    }




    //SUBMODULE: isValidDay
    //IMPORT: pYear(Integer), pMonth(Integer), pDay(Integer)
    //EXPORT: isValid(Boolean)
    //ASSERTION: Determines if a day for a given month and year is valid
    //           Returns true if pDay is valid

    private static boolean isValidDay(int pYear, int pMonth, int pDay)
    {
        boolean isValid = false;
        
        //Months with 30 days
        if(pMonth == 4 || pMonth == 6 || pMonth == 9 || pMonth == 11)
        {
            if(pDay >=1 && pDay <= 30)
            {
                isValid = true;
            }
        }
        //Months with 31 days
        else if(pMonth == 1 || pMonth == 3 || pMonth == 5 || pMonth == 7
                 || pMonth == 8 || pMonth == 10 || pMonth == 12)
        {
            if(pDay >=1 && pDay <= 31)
            {
                isValid = true;
            }
        }
        //Month with 28/29 days
        else if(pMonth == 2)
        {  
            //Checking if year is a leap year 
            if(leapYear(pYear))
            {   
                //Allow pDay to 29 if its a leap year
                if(pDay >= 1 && pDay <= 29)
                {
                    isValid = true;
                }
            }
            else
            {   
                //28 days if not a leap year
                if(pDay >= 1 && pDay <= 28)
                {
                    isValid = true;
                }
            }
        }

        return isValid;
    }


            
    
    //SUBMODULE: isValidParam
    //IMPORT: pCode(String), pStationNumber(String), pYear(Integer), pMonth(Integer),
    //        pDay(Integer), pMaxTemp(Double)
    //EXPORT: isValid(Boolean)
    //ASSERTION: Determines if the parameters used for the constructor are valid
    //           Returns true if valid
    
    private static boolean isValidParam(String pCode, String pStationNumber, int pYear, int pMonth,
                                        int pDay, double pMaxTemp)
    {
        boolean isValid = false;
        
        if(!pCode.equals("") && pCode != null)
        {
            if(!pStationNumber.equals("") && pCode != null)
            {
                if(pYear >= 1600)
                {
                    if(pMonth >= 1 && pMonth <= 12)
                    {
                        if(isValidDay(pYear,pMonth,pDay))
                        {
                            if(pMaxTemp >= -100.0 && pMaxTemp <= 70.0)
                            {
                                isValid = true;
                            }
                        }
                    }
                }
            }
        }

        return isValid;

    }


}
