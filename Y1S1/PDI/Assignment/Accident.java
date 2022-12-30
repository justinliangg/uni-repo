/*-------------------------------
  FILE: Accident.java
  Author: Justin Liang (19821986)
  UNIT: PDI
  COMMENTS: Accident class to store accident information
  REQUIRES: Location.java 
  Last Mod: 25/05/2021   
--------------------------------*/

import java.util.*;

public class Accident
{
    //CLASS VARIABLES
    private String id;
    private String date;
    private String time;
    private String severity;
    private String roadName;
    private String intersectionNumber;
    private String eventNature;
    private Location location;




    //CONSTRUCTORS METHODS
    
    //Parameter Constructor 
    public Accident(String pId , String pDate, String pTime, String pSeverity, String pRoadName, String pIntersectionNumber
                    , String pEventNature, Location pLocation)
    
    {
        //Checks if imported variables are empty OR null
        if(!empty(pId, pDate, pTime, pSeverity, pRoadName, pIntersectionNumber, pEventNature, pLocation))
        {
            id = pId;
            date = pDate;
            time = pTime;
            severity = pSeverity;
            roadName = pRoadName;
            intersectionNumber = pIntersectionNumber;
            eventNature = pEventNature;
            location = pLocation;
        }
        else
        {  
            id = "12345678";
            date = "01/01/21";
            time = "0000";
            severity = "Hospital";
            roadName = "Albany Hwy";
            intersectionNumber = "1234";
            eventNature = "Rear End";
            location = new Location();
        }

    }

    //Copy Constructor
    public Accident(Accident pAccident)
    {
        id = pAccident.getId();
        date = pAccident.getDate();
        time = pAccident.getTime();
        severity = pAccident.getSeverity();
        roadName = pAccident.getRoadName();
        intersectionNumber = pAccident.getIntersectionNumber();
        eventNature = pAccident.getEventNature();
        location = pAccident.getLocation();
    
    }

    //Default Constructor
    public Accident()
    { 
        id = "12345678";
        date = "01/01/21";
        time = "0000";
        severity = "Hospital";
        roadName = "Albany Hwy";
        intersectionNumber = "1234";
        eventNature = "Rear End";
        location = new Location();
    }
    



    //ACCESSORS METHODS
    
    public String getId()
    {
        return id;
    }

    public String getDate()
    {
        return date;
    }
    
    public String getTime()
    {
        return time;
    }
    
    public String getSeverity()
    {
        return severity;
    }

    public String getRoadName()
    {
        return roadName;
    }

    public String getIntersectionNumber()
    {
        return intersectionNumber;
    }

    public String getEventNature()
    {
        return eventNature;
    }

    public Location getLocation()
    {
        return location;
    }




    //MUTATORS METHODS

    public void setId(String pId)
    {
        if(!pId.equals("") && pId != null)
        {
            id = pId;
        }
    }

    public void setDate(String pDate)
    {
        if(!pDate.equals("") && pDate != null)
        {
            date = pDate;
        }
    }
    
    public void setTime(String pTime)
    {
        if(!pTime.equals("") && pTime != null)
        {
            time = pTime;
        }
    } 
    
    public void setSeverity(String pSeverity)
    {
        if(!pSeverity.equals("") && pSeverity != null)
        {
            severity = pSeverity;
        }
    }
    
    public void setRoadName(String pRoadName)
    {
        if(!pRoadName.equals("") && pRoadName != null)
        {
            roadName = pRoadName;
        }
    }

    public void setIntersectionNumber(String pIntersectionNumber)
    {
        if(!pIntersectionNumber.equals("") && pIntersectionNumber != null)
        {
            intersectionNumber = pIntersectionNumber;
        }
    }
        
    public void setEventNature (String pEventNature)
    
    {
        if(!pEventNature.equals("") && pEventNature != null)
        {
            eventNature = pEventNature;
        }
    }
    
    public void setLocation (Location pLocation)
    {
        if(pLocation != null)
        {
            location = pLocation;
        }
    }




    //SUBMODULE: equals
    //IMPORT: pObject(Object)
    //EXPORT: isEqual(Boolean)
    //ASSERTION: used to check if two objects are equal
    
    public boolean equals(Object pObject)
    {
        boolean isEqual = false;
        Accident inAccident = null;
        
        if(pObject instanceof Accident)
        {
            inAccident = (Accident) pObject;
            if(id.equals(inAccident.getId()))
            {
                if(date.equals(inAccident.getDate()))
                {
                    if(time.equals(inAccident.getTime()))
                    {
                        if(severity.equals(inAccident.getSeverity()))
                        {
                            if(roadName.equals(inAccident.getRoadName()))
                            {
                                if(intersectionNumber.equals(inAccident.getIntersectionNumber()))
                                {
                                    if(eventNature.equals(inAccident.getEventNature()))
                                    {
                                        if(location.equals(inAccident.getLocation()))
                                        {
                                            isEqual = true;
                                        }
                                    }
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
    //EXPORT: accidentString(String)
    //ASSERTION: returns the class variables as strings
    public String toString()
    {
        String accidentString = "ID: " + id + "\nDate: " + date + "\nTime: " + time + "\nSeverity: " + severity + "\nRoadName: " + roadName + "\nIntersectionNumber: " + intersectionNumber + "\nEventNature: " + eventNature + "\n" + location;

        return accidentString;
    }




    //SUBMODULE: empty
    //IMPORT: pId(String), pDate(String), pTime(String), pSeverity(String), pRoadName(String), pIntersectionNumber(String)
    //        pEventNature(String), pLocation(Location)  
    //EXPORT: isEmpty(Boolean)
    //ASSERTION: Check whether the imported variables are empty or null
    
    private static boolean empty(String pId , String pDate, String pTime, String pSeverity, String pRoadName
                                 , String pIntersectionNumber, String pEventNature, Location pLocation)
    {
        boolean isEmpty = true;
        
        if(!pId.equals("") && pId != null)
        {
            if(!pDate.equals("") && pDate != null)
            {
                if(!pTime.equals("") && pTime != null)
                {
                    if(!pSeverity.equals("") && pSeverity != null)
                    {
                        if(!pRoadName.equals("") && pRoadName != null)
                        {
                            if(!pIntersectionNumber.equals("") && pIntersectionNumber != null)
                            {
                                if(!pEventNature.equals("") && pEventNature != null)
                                {
                                    if(pLocation != null)
                                    {
                                        isEmpty = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return isEmpty;
    }


}
