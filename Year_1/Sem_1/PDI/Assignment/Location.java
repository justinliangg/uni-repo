/*-------------------------------
FILE:Location.java
Author: Justin Liang (19821986)
UNIT: PDI
COMMENTS: Location Class, used to create object used in Accident Class
REQUIRES: NIL
Last Mod: 25/05/2021
--------------------------------*/

import java.util.*;

public class Location
{
    //CLASS VARIABLES
    private double latitude;
    private double longitude;
    private String roadNumber;



    
    //CONSTRUCTOR METHODS

    //parameter constructor
    public Location(double pLatitude,double pLongitude,String pRoadNumber)
    {   
        //Only constructing if pLatitude and pLongitude are within range
        //and pRoadNumber is not empty or null.
        if(pLatitude <=90.0 && pLatitude >= -90.0)
        {   
            if(pLongitude <= 180.0 && pLongitude >= -180.0)
            {
                if(!pRoadNumber.equals("") && pRoadNumber != null)
                {
                    latitude = pLatitude;
                    longitude = pLongitude;
                    roadNumber = pRoadNumber; 
                }
            }
        }
        else
        {
            latitude = 0.0;
            longitude = 0.0;
            roadNumber = "1";
        }
    }
    
    //copy constructor
    public Location(Location pLocation)
    {
        latitude = pLocation.getLatitude();
        longitude = pLocation.getLongitude();
        roadNumber = pLocation.getRoadNumber();
    }

    //default constructor
    public Location()
    {
        latitude = 0.0;
        longitude = 0.0;
        roadNumber = "1";
    }




    //ACCESSOR METHODS

    public double getLatitude()
    {
        return latitude;
    }
    
    public double getLongitude()
    {
        return longitude;
    }
    
    public String getRoadNumber()

    {
        return roadNumber;
    }




    //MUTATOR METHODS
    
    public void setLatitude(double pLatitude)
    {   
        //Allowing mutator to change value of latitude only if imported value is within -90 and 90
        if(pLatitude <= 90.0 && pLatitude >= -90.0)
        {
            latitude = pLatitude;
        }
    }
    
    public void setLongitude(double pLongitude)
    {
        //Allowing mutator to change value of longitude only if imported value is within -180 and 180
        if(pLongitude <= 180.0 && pLongitude >= -180.00)
        {
            longitude = pLongitude;
        }
    }
    
    public void setRoadNumber(String pRoadNumber)
    {
        //Changing roadNumber only if pRoadNumber is not empty or null
        if(!pRoadNumber.equals("") && pRoadNumber != null)
        {
            roadNumber = pRoadNumber; 
        }  
    }
   



    //SUBMODULE: equals
    //IMPORT: pObject(Object) 
    //EXPORT: isEqual(Boolean)
    //ASSERTION: Checks if two objects of type Location are equal
    public boolean equals(Object pObject)
    {
        boolean isEqual = false;
        Location inLocation = null;
        
        if(pObject instanceof Location)
        {
            inLocation = (Location) pObject;
            if(latitude == inLocation.getLatitude())
            {
                if(longitude == inLocation.getLongitude())
                {
                    if(roadNumber.equals(inLocation.getRoadNumber()))
                    {
                        isEqual = true;
                    }
                }
            }
        }
        return isEqual;
    }




    //SUBMODULE: toString
    //IMPORT: nil
    //EXPORT: locationString(String)
    //ASSERTION: returns values of class fields as strings
    
    public String toString()
    {
        String locationString = "Latitude: " + latitude + "\nLongitude: " + longitude + "\nRoadNumber: " + roadNumber;
        return locationString;
    }


}

