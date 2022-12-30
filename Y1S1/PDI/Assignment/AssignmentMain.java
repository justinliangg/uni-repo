/*-------------------------------
FILE:AssignmentMain.java
Author: Justin Liang (19821986)
UNIT: PDI
COMMENTS: Main Class 
REQUIRES: Accident and Location Class
Last Mod: 26/05/2021
--------------------------------*/

import java.util.*;
import java.io.*;
public class AssignmentMain
{
    public static void main(String[] args)
    {   
        //Initialising constants 
        final int LOCATION_DATA = 1;
        final int GENERAL_DATA = 2;  
        final int EXIT = 3;
 
        //geting number of valid accidents
        int numAccidents = sizeOfArray(); 
        //Initalising size of array for Accident objects
        Accident [] arrAccidents = new Accident[numAccidents];
        //Initialising size of array for Location Objects
        Location [] arrLocations = new Location[numAccidents];
         
	    //Running the menu only if numAccidents is greater than 0
       	if(numAccidents > 0)
	    {
            int choice = 0;
            //Loop until user chooses to exit.
            while(choice != EXIT)
            {    
                System.out.println("----------------------------------------------------------------------------" 
    	                           + "----------------------------------");
    		
		        //Reading the csv file and filling the arrays   
        	    arrLocations = fileRead(arrLocations); 
        	    arrAccidents = fileRead(arrAccidents, arrLocations); 
 
		        //Display menu options to user
            	menuDisplay(numAccidents); 
            	//Get user input for the menu options
            	choice = menuUserInput();   

            	if (choice == LOCATION_DATA )
            	{                   
                    //Get user location. Calculate and sort distance between user location and the data locations.
                    //Display data to the user.
                    locationData(arrLocations, arrAccidents);  
            	}
            	else if (choice == GENERAL_DATA)
            	{
                    //user input for choice
                    int choice2 = option2Menu(); 
                    //performing user's choice 
                    runUserOption(choice2,arrAccidents); 
                }
                else if (choice == EXIT)
                {
                    System.out.println("YOU HAVE EXITED THE PROGRAM!");
            	}   
            	else
            	{          
                    System.out.println("Please enter numbers 1 to 3 only");
                }
     
            }

        } 
	    else
	    {
	        System.out.println("Crash_Information_(Last_5_Years).csv File Missing!");
   	    }   
    }	

//SUBMODULES



//SUBMODULES FOR LOCATION_DATA


    //SUBMODULE: sizeOfArray
    //IMPORT: nil
    //EXPORT: rows(Integer)
    //ASSERTION: go through the csv file and count number of valid rows in the file. Return number.

    private static int sizeOfArray()
    {
        String fileName = "Crash_Information_(Last_5_Years).csv";
        FileInputStream fileInput = null;
        InputStreamReader reader;
        BufferedReader bfr;
        String line;    
        int rows;

        rows=0;     
        try
        {   
            //initialiising objects and variables
            fileInput = new FileInputStream(fileName);
            reader = new InputStreamReader(fileInput);
            bfr = new BufferedReader(reader);
            line = bfr.readLine();

            while(line != null)
            {
                String[] column = processLine(line);

                //Checking if strings in column are not empty or null 
                if(!empty(column)) 
                {
                    //converting string to double can throw an exception if the string is not a double
                    //if exception is thrown and caught, row will be rejected.
                    try
                    {
                        //check if long and lat is both a double
                        double longitude = Double.parseDouble(column[0]);
                        double latitude = Double.parseDouble(column[1]);

                        //Checking if longitude and latitude is within the range                    
                        if(longitude >= -180.0 && longitude <= 180.0)
                        {
                            if(latitude >= -90.0 && latitude <= 90.0)
                            {
                                //Adding to counter
                                rows++;  
                            }
                        }
                    }
                    catch(NumberFormatException e)
                    {}
                }

                line = bfr.readLine();
            }

            fileInput.close();
        }

        catch(IOException e)
        {
            if(fileInput != null)
            {
                try 
                {
                    fileInput.close();
                }
                catch(IOException ex2)
                {}
            }
        }
        return rows;
    }



    
    //SUBMODULE:fileRead
    //IMPORT: pArrLocations(Array of Location)
    //EXPORT: pArrLocations(Array of Location)
    //ASSERTION: read a file and store values into pArrLocations, return pArrLocations
    
    private static Location[] fileRead(Location pArrLocations[]) 
    {
        String fileName = "Crash_Information_(Last_5_Years).csv";
        FileInputStream fileInput = null;
        InputStreamReader reader;
        BufferedReader bfr;
        String line;
        int elementNum;
        
        elementNum = 0;    
        try
        {   
            //initalising objects and variables
            fileInput = new FileInputStream(fileName);
            reader = new InputStreamReader(fileInput);
            bfr = new BufferedReader(reader);
            line = bfr.readLine();

            while(line != null)
            {
                String[] column = processLine(line); 
                
                //checking if strings in column are not empty or null
                if(!empty(column)) 
                {
                    //converting string to double can throw an exception if the string cannot be converted to a double
                    //if exception is thrown and caught, row will be rejected.
                    try
                    {
                        //Check if longitude and latitude are doubles 
                        double longitude = Double.parseDouble(column[0]);
                        double latitude = Double.parseDouble(column[1]);
                        String roadNumber = column[4];

                        //Check if longitude and latitude within range
                        if(longitude >= -180 && longitude <= 180)
                        {
                            if(latitude >= -90 && latitude <= 90)
                            {
                                //Constructing Location Objects        
                                pArrLocations[elementNum] = new Location(latitude,longitude,roadNumber);
                                
                                elementNum++;
                            }
                        }
                    }
                    catch(NumberFormatException e)
                    {}
                }
                line = bfr.readLine();
            }

            fileInput.close();
        }
        catch(IOException e)
        {
            if(fileInput != null)
            {
                try 
                {
                    fileInput.close();
                }
                catch(IOException ex2)
                {}
            }
        }
        return pArrLocations;
    }



    
    //SUBMODULE:fileRead
    //IMPORT: pArrAccidents(Array of Accident),pArrLocations(Array of Location)
    //EXPORT: pArrAccidents(Array of Accident)
    //ASSERTION: read a file and store values into pArrAccidents, return pArrAccidents
    
    private static Accident [] fileRead(Accident [] pArrAccidents, Location [] pArrLocations) 
    {
        String fileName = "Crash_Information_(Last_5_Years).csv";
        FileInputStream fileInput = null;
        InputStreamReader reader;
        BufferedReader bfr;
        String line;
        int elementNum;
        
        elementNum = 0;    
        try
        {   
            //initalising objects and variables
            fileInput = new FileInputStream(fileName);
            reader = new InputStreamReader(fileInput);
            bfr = new BufferedReader(reader);
            line = bfr.readLine();

            while(line != null)
            {
                String[] column = processLine(line); 
               	if(column.length == 24)
		{	
                //Checking if strings in column are empty or null
                if(!empty(column)) 
                {
                    //converting string to double can throw an exception if the string cannot be converted to a double
                    //if exception is thrown and caught, row will be rejected.
                    try
                    {   
                        //Check if longitude and latitude are doubles 
                        double longitude = Double.parseDouble(column[0]);
                        double latitude = Double.parseDouble(column[1]);
                        
                        //for accident constructor
                        String id = column[3];
                        String date = column[13];
                        String time = column[14];
                        String severity = column[15];
                        String roadName = column[5];
                        String intersectionNumber = column[9];
                        String eventNature = column[16];
                        
                        //Check if longitude and latitude within range
                        if(longitude >= -180 && longitude <= 180)
                        {
                            if(latitude >= -90 && latitude <= 90)
                            {
                                //Constructing Accident Object      
                                pArrAccidents[elementNum] = new Accident(id,date,time,severity,roadName,intersectionNumber
                                                                         ,eventNature,pArrLocations[elementNum]);
                                
                                elementNum++;
                            }
                        }
                    }
                    catch(NumberFormatException e)
                    {}
                }
		}
                line = bfr.readLine();
            }

            fileInput.close();
        }
        catch(IOException e)
        {
            if(fileInput != null)
            {
                try 
                {
                    fileInput.close();
                }
                catch(IOException ex2)
                {}
            }
        }
        return pArrAccidents;
	
    }




    //SUBMODULE: empty
    //IMPORT: pColumn(Array of Strings)
    //EXPORT: isEmpty(Boolean)
    //ASSERTION: Imports pColumn and checks if any elements used for accident and location constructors are blank.
    
    private static boolean empty(String pColumn[])
    {
        boolean isEmpty = true;
        {
            //checking if all columns are not empty.
            if(!pColumn[0].equals("") && pColumn[0] != null)
            {
                if(!pColumn[1].equals("") && pColumn[1] != null)
                {
                    if(!pColumn[3].equals("") && pColumn[3] != null)
                    {
                        if(!pColumn[4].equals("") && pColumn[4] != null)
                        {
                            if(!pColumn[5].equals("") && pColumn[5] != null)
                            {
                                if(!pColumn[9].equals("") && pColumn[9] != null)
                                {
                                    if(!pColumn[13].equals("") && pColumn[13] != null)
                                    {
                                        if(!pColumn[14].equals("") && pColumn[14] != null)
                                        {
                                            if(!pColumn[15].equals("") && pColumn[15] != null)
                                            {
                                                if(!pColumn[16].equals("") && pColumn[16] != null)
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
                }
            }
        }

        return isEmpty;
    }




    //SUBMODULE: processLine
    //IMPORT: pRow(String)
    //EXPORT: inColumn(Array of Strings)
    //ASSERTION: split pRow every ",", store each element seperated in inColumn 
                 //export inColumn
    
    private static String[] processLine(String pRow)
    {   
        String [] inColumn;
        //splits every "," 
        inColumn = pRow.split(",");
        return inColumn;
    }
    

   
    
    //SUBMODULE: menuDisplay
    //IMPORT: pNumAccidents (Integer)
    //EXPORT: NIL
    //ASSERTION: Display MENU screen    
    
    private static void menuDisplay(int pNumAccidents)
    {   
        System.out.println("Welcome to the Main Roads WA crash data program. There are a total of "+ pNumAccidents 
                            + " accidents within this dataset.");
        System.out.println("Please make a selection from the Menu below.");
        System.out.println("1. Examine data in relationship to a specified location.");
        System.out.println("2. Examine the data generally.");
        System.out.println("3. Exit Program");
    }




    //SUBMODULE: menuUserInput
    //IMPORT: nil
    //EXPORT: inChoice(Integer)
    //ASSERTION: get user input for the menu and returns the choice

    private static int menuUserInput()
    {
        System.out.println("");
        
        int inChoice = 0;        
        //Try-Catch to only allow integers to be inputted by the user.
        try
        {
            Scanner sc = new Scanner(System.in);
            inChoice = sc.nextInt();
        }
        catch(InputMismatchException error)
        { 
            System.out.println("Please only enter integers");
        }  

        return inChoice;     
    }   




    //SUBMODULE: locationData
    //IMPORT: pArrLocations(Array of Location), pArrAccidents(Array of Accident)
    //EXPORT: nil
    //ASSERTION: get user input, calculate the distances between the user location and the location in the data. 
    //           Sort the data and then display the results

    private static void locationData(Location [] pArrLocations, Accident [] pArrAccidents)
    {
        System.out.println("----------------------------------------------------------------------------" 
                           + "----------------------------------");

        System.out.println("The following details are required: Location latitude, location longitude and location name.\n");
        
        String locationName = inputString("Location Name");
        double userLongitude = inputDouble("Longitude", (-180.0), 180);
        double userLatitude = inputDouble("Latitude",(-90.0 ),90.0); 
                    
        //Creating array of doubles for the distances between the user location and the array of Locations    
        double [] arrDistance = createArrDist(pArrLocations, userLongitude, userLatitude); 
        
        //sorting pArrAccidents to align with arrDistance
        Accident [] sortedArrAccidents = insertionSort(arrDistance, pArrAccidents); 
        //Sort arrDistance[] into ascending order
        double [] sortedArrDistance = insertionSort(arrDistance); 
       
        //Calculate the results and display it to the user
        System.out.println("");
        displayResults(sortedArrDistance, sortedArrAccidents, locationName); 
    }




    //SUBMODULE: inputString
    //IMPORT: stringName(String)
    //EXPORT: userInput(String)
    //ASSERTION: get a user input for a string 

    private static String inputString(String stringName)
    {
        String userInput;
        
        //validating userInput to ensure String is not empty or blank
        do
        {
            Scanner sc = new Scanner(System.in);
            System.out.print(stringName + ": ");
            userInput = sc.nextLine();
        }
        //userInput.charAt(0) == 32 ensures that first character of the string cannot be a space.
        //userInput == "" ensures that string cannot be empty
        while(userInput.equals("") || userInput.charAt(0) == 32); 

        return userInput;
    }




    //SUBMODULE: inputDouble
    //IMPORT: valueName(String), minValue(Double),maxValue(Double)
    //EXPORT: userInput(Double)
    //ASSERTION: Get userInput, validate user input so that it is between minValue and maxValue.
    
    private static double inputDouble(String valueName, double minValue, double maxValue)
    {   
        //set userInput to max + 1  so it keeps looping until user changes it value
        double userInput = (maxValue + 1);
        
        //validating user input to be between the specified range
        //Loops if outside of range
        while(userInput > maxValue || userInput < minValue)
        {
            try
            {   
                Scanner sc = new Scanner(System.in);
                System.out.print(valueName + ": ");
                userInput = sc.nextDouble();
                
                //Prints a statement to user if input value is outside of the specified range.
                if(userInput > maxValue || userInput < minValue)
                {
                    System.out.println("Please enter a number between " + minValue + " and " + maxValue + " only");
                }
            }
            catch(InputMismatchException e)
            {
                System.out.println("Please only enter real numbers");
            }
            
        }

        return userInput;
    }




    //SUBMODULE: createArrDist
    //IMPORT: pArrLocations(Array of Location), pUserLongitude(double), pUserLatitude(double)
    //EXPORT: arrDistance(Array of Doubles)
    //ASSERTION: Calculate distance between user location and locations in pArrLocations. 
    //           Store the distances in the array arrDistance
    //COMMENTS: Contains code obtain from another person

    private static double[] createArrDist(Location [] pArrLocations, double pUserLongitude, double pUserLatitude)
    {
        double arrDistance[] = new double [pArrLocations.length];
        for (int i = 0; i < arrDistance.length;i++)
        {
            double distance = haverSineDistance(pUserLatitude, pUserLongitude, pArrLocations[i].getLatitude()
                                                , pArrLocations[i].getLongitude());
            
            //Round distance to 3 decimal places
            //code from user :asterite
            //https://stackoverflow.com/a/153753
            //(Accessed on 12th May 2021)
            arrDistance[i] = (double) Math.round(distance*1000.0)/1000.0;  
            ////end of code obtained from asterite
        }
        return arrDistance;
    }




    //SUBMODULE: haverSineDistance
    //IMPORT: lat1(Real), lon1(Real), lat2(Real) , lon 2(Real)
    //EXPORT: distance(Real)
    //ASSERTION: Calculate distance between two coordinates
    //COMMENTS: nil 
    
    private static double haverSineDistance(double lat1, double lon1, double lat2, double lon2)
    {
        final int EARTHRADIUS = 6371; // Radius of the earth
        double latDistance = convertToRadians(lat2 - lat1);
        double lonDistance = convertToRadians(lon2 - lon1);
        double a = (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)) + (Math.cos(convertToRadians(lat1)) * Math.cos(convertToRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = EARTHRADIUS * c;
        return distance;
    }




    //SUBMODULE: convertToRadians
    //IMPORT: value(Real)
    //EXPORT: radValue(Real)
    //ASSERTION: convert degrees to radians
    //COMMENTS: nil
    
    private static double convertToRadians(double value)
    {
        double radValue = value * Math.PI / 180;
        return radValue;
    }




    //SUBMODULE: insertionSort
    //IMPORT:pArrDistance(Array of Doubles), pArrAccidents(Array of Accident)
    //EXPORT:pArrAccidents(Array of Accident) 
    //ASSERTION: sort distances in pArrDistance into ascending order and sort pArrAccidents according to pArrDistance as well
    //           return pArrAccidents
    
    private static Accident [] insertionSort(double [] pArrDistance, Accident [] pArrAccidents)
    {
        for(int i = 1; i< pArrDistance.length; i++)
        {
            //creates a temp storage for the values
            double currentDist = pArrDistance[i];
            Accident currentAcc = pArrAccidents[i];
            
            int j = i;
            while(j>0 && pArrDistance[j-1] > currentDist)
            {
                //shifts values to the right if loop is ran
                pArrDistance[j] = pArrDistance[j-1];
                pArrAccidents[j] = pArrAccidents[j-1];

                j--;
            }

            //Swapping the distances
            pArrDistance[j] = currentDist;
            pArrAccidents[j] = currentAcc;
        }
        return pArrAccidents;        
    }




    //SUBMODULE: insertionSort
    //IMPORT:pArrDistance(Array of Doubles)
    //EXPORT:pArrDistance(Array of Doubles)
    //ASSERTION: sort distances in pArrDistance into ascending order. Return pArrDistance

    private static double [] insertionSort(double [] pArrDistance)
    {
        for(int i = 1; i< pArrDistance.length; i++)
        {
            //creates a temp storage for the values
            double currentDist = pArrDistance[i];

            int j = i;
            while(j>0 && pArrDistance[j-1] > currentDist)
            {
                //shifts values to the right if loop is ran
                pArrDistance[j] = pArrDistance[j-1];

                j--;
            }

            //Swapping the distances
            pArrDistance[j] = currentDist;
        }        
        return pArrDistance;
    }




    //SUBMODULE: displayResults
    //IMPORT: pSortedArrDistance(array of Doubles), pSortedArrAccidents(Array of Accident), pLocationName(String)
    //EXPORT: nil
    //ASSERTION: Calculate the results and display the results to the user.
    //COMMENTS: Contains externally sourced code.
    
    private static void displayResults(double [] pSortedArrDistance, Accident [] pSortedArrAccidents, String pLocationName)
    {  
        //getting the element number for the max and min distance of a fatal accident from user location
        int maxFatal = maxDistSeverity("Fatal", pSortedArrAccidents); 
        int minFatal = minDistSeverity("Fatal", pSortedArrAccidents); 
        //getting the element number for the max and min distance of a hospital accident from user location
        int maxHospital = maxDistSeverity("Hospital", pSortedArrAccidents); 
        int minHospital = minDistSeverity("Hospital", pSortedArrAccidents); 

        //Number of Accidents within 10km from user location
        int numAccidents10Km = numAccidents10Km(pSortedArrDistance); 
            
        //Calculating percentage of all accidents within 10km
        double accidentPercent = percentOfAccidents(numAccidents10Km, pSortedArrAccidents.length);

        //Displaying results to user
        
        //Furthest and closest accident
        System.out.println("Furthest accident from " + pLocationName + " is " 
                           + pSortedArrDistance[(pSortedArrAccidents.length-1)] +"km away.");
       	System.out.println("Closest accident to " +pLocationName + " is " + pSortedArrDistance[0] + "km  away.");
        System.out.println("");

        //Furthest and closest fatal accident
        if(maxFatal > 0 || maxFatal == minFatal)
        {
            System.out.println("Furthest fatal accident from " + pLocationName + " is " + pSortedArrDistance[maxFatal] 
                               + "km away.");
            System.out.println("Closest fatal accident to " + pLocationName + " is " +  pSortedArrDistance[minFatal] + "km away.");
            System.out.println("");
        }
        //If maxFatal =0 and does not equal to minFatal that means there are no fatal accidents
        else
        { 
            System.out.println("There are no fatal accidents");
            System.out.println("");   
        }

        //Furthest and closest hospital accident
        if(maxHospital > 0 || maxHospital == minHospital)
        {
            System.out.println("Furthest hospital accident from " + pLocationName + " is " + pSortedArrDistance[maxHospital] 
                               + "km away.");
            System.out.println("Closest hospital accident to " + pLocationName + " is " + pSortedArrDistance[minHospital] 
                               + "km away.");
            System.out.println("");
        }
        //If maxHospital = 0 and does not equal to minHospital that means there are no hospital accidents
        else 
        {   
            System.out.println("There are no hospital accidents");
            System.out.println("");
        }

        //Total number of accidents within 10km
        System.out.println("Total number of accidents within 10km of " + pLocationName + " is " + numAccidents10Km 
                           + " Accidents.");
        System.out.println("Percentage of all accidents within 10km of " + pLocationName + " is " + accidentPercent + "% .");
       
    }




    //SUBMODULE: maxDistSeverity
    //IMPORT: pSeverityType (String), pSortedArrAccidents (Array of Accident)
    //EXPORT: elementNum(Integer)
    //ASSERTION: search through pSortedArrAccidents starting from the end to find pSeverityType. 
    //           After pSeverityType is found, stop searching and get the element number.
    
    private static int maxDistSeverity(String pSeverityType, Accident []pSortedArrAccidents)
    {   
        int elementNum = pSortedArrAccidents.length-1;
        boolean found = false;
        //looping until found or reached the end of the array
        while(!found && elementNum > 0)
        { 
            //searching for furthest pSeverityType, hence starting from the top of the array.
            if(pSeverityType.equals(pSortedArrAccidents[elementNum].getSeverity()))
            {
                found = true;
            }
            else
            {
                elementNum--;
            }
        }
        return elementNum;
    }




    //SUBMODULE: minDistSeverity
    //IMPORT: pSeverityType (String), pSortedArrAccidents (Array of Accident)
    //EXPORT: elementNum(Integer)
    //ASSERTION: search through pSortedArrAccidents starting from the front to find pSeverityType. 
    //           After pSeverityType is found, stop searching and get the element number.
    
    private static int minDistSeverity(String pSeverityType, Accident []pSortedArrAccidents)
    {   
        int elementNum;
        boolean found = false;

        elementNum = 0;
        //looping until found or reached the end of the array
        while(!found && elementNum < pSortedArrAccidents.length - 1)
        { 
            //searching for cloest pSeverityType, hence starting from the bottom of the array
            if(pSeverityType.equals(pSortedArrAccidents[elementNum].getSeverity()))
            {
                found = true;
            }
            else 
            {
                elementNum++;
            }
        }
        return elementNum;
    }




    //SUBMODULE: numAccidents10Km 
    //IMPORT: pArrDistance (Array of Doubles)
    //EXPORT: counter(Integer)
    //ASSERTION: search through pArrDistance and count the number of accidents within 10 km of user location. Return counter/
    private static int numAccidents10Km(double [] pArrDistance)
    {
        int counter = 0; 
        for(int i = 0; i < pArrDistance.length; i++)
        {
            //Adding to counter distance is less than or equal to 10
            if(pArrDistance[i] <= 10.0)
            {
                counter++;
            }
        }

        return counter;
    }
                           



    //SUBMODULE: percentOfAccidents
    //IMPORT: numerator(Integer), accidentArrLength(Integer))
    //EXPORT: percentage(double)
    //ASSERTION: calculate the percentage of a given numerator against the total number of Accidents 
    //           (Rounded to two decimal places)
    //COMMENTS: Contains externally sourced code

    private static double percentOfAccidents( int numerator, int accidentArrLength)
    {
        double x = ((double) numerator / ((double) accidentArrLength)) * 100;
        //Round percentage to 3 decimal places
        //code from user :asterite
        //https://stackoverflow.com/a/153753
        //(Accessed on 18th May 2021)  
        double percentage= (double) Math.round(x*100.0)/100.0;  
        //end code from user asterite

        return percentage;
    }




/*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
//SUBMODULES FOR GENERAL_DATA
     

    //SUBMODULE: option2Menu
    //IMPORT: nil
    //EXPORT: inChoice(Integer)
    //ASSERTION: Display option 2 Menu,get user input and return the choice
    
    private static int option2Menu()
    {   
        //Dont want it to loop more than once
        int counter = 0;
        int inChoice = 0;
        while(inChoice != 4 && counter <1)
        {
            System.out.println("----------------------------------------------------------------------------" 
                               + "----------------------------------");

            //display option 2 menu
            System.out.println("How would you like to see a summary of the data? Please make a selection from the Menu below.");
            System.out.println("> 1. Data displayed on the screen.");
            System.out.println("> 2. Data displayed on the screen and written to file.");
            System.out.println("> 3. Data written to file.");
            System.out.println("> 4. To Main Menu.");
    
            //getting user input
            inChoice= menuUserInput();
            
            //If inChoice is less than 1 or greater than 4, that means user has input an incorrect value
            //Adding to counter only if user enters correct input.
            if(inChoice >= 1 && inChoice <= 4)
            {
                counter ++;
            }
            else
            {
                System.out.println("Please enter numbers between 1 - 4 only");
            }
        }
        return inChoice;
    }


   

    //SUBMODULE: runUserOption
    //IMPORT: pChoice2(Integer), pArrAccidents(Array of Accident)
    //EXPORT: nil
    //ASSERTION: run actions based on user's choice
    
    private static void runUserOption( int pChoice2, Accident [] pArrAccidents)
    {
        //Display summary only
        if(pChoice2 == 1)
        {   
            displaySummary(pArrAccidents); 
        }
        //Display summary and write to file
        else if(pChoice2 == 2)
        {
            displaySummary(pArrAccidents); 
            writeToFile(pArrAccidents);
        }
        //Write to file only
        else if(pChoice2 == 3)
        {
            writeToFile(pArrAccidents); 
        }
        //EXIT to Main Menu
        else if(pChoice2 == 4)
        {
          System.out.println("In Main Menu");
        }
    }




    //SUBMODULE: displaySummary
    //IMPORT: pArrAccidents(Array of Accident)
    //EXPORT: NIL
    //ASSERTION: calculate and display the summary of data
    
    private static void displaySummary(Accident [] pArrAccidents)
    {
        System.out.println("");

        //Calculating number of accidents for given severity type
        int numFatal = numSeverityType("Fatal", pArrAccidents);
        int numHospital = numSeverityType("Hospital", pArrAccidents);

        //Calculating number of accidents for given Event Nature
        int numRearEnd = numEventNature("Rear End", pArrAccidents); 
        int numRightAngle = numEventNature("Right Angle", pArrAccidents);
        
        //Calculating percentage of all accidents for a given severity type
        double percentFatal = percentOfAccidents(numFatal, pArrAccidents.length); 
        double percentHospital = percentOfAccidents(numHospital, pArrAccidents.length);
        double percentRearEnd = percentOfAccidents(numRearEnd, pArrAccidents.length);
        double percentRightAngle = percentOfAccidents(numRightAngle, pArrAccidents.length);
            
        //Displaying summary of data
        System.out.println("The total number of accidents:" + pArrAccidents.length);
        System.out.println("");

        //fatal accidents 
        System.out.println("The total number of fatal accidents: " + numFatal); 
        System.out.println("The total number of fatal accidents as a percentage of all accidents: " + percentFatal + "%.");
        System.out.println("");
        
        //hospital accidents
        System.out.println("The total number of hospital accidents: " + numHospital);
        System.out.println("The total number of hospital accidents as a percentage of all accidents: " 
                           + percentHospital + "%.");
        System.out.println("");

        //Rear End accidents
        System.out.println("The total number of Rear End accidents: " + numRearEnd);
        System.out.println("The total number of Rear End accidents as a percentage of all accidents: " 
                           +  percentRearEnd + "%.");
        System.out.println("");

        //Right Angle accidents
        System.out.println("The total number of Right Angle accidents: " + numRightAngle);
        System.out.println("The total number of Right Angle accidents as a percentage of all accidents: " 
                           + percentRightAngle + "%.");
 
        System.out.println("");
    }  
    



    //SUBMODULE: numSeverityType
    //IMPORT: pSeverityType(String), pArrAccidents (Array of Accident)
    //EXPORT: counter(Integer)
    //ASSERTION: Search through pArrAccidents and count the number of accidents for a given severity type
    
    private static int numSeverityType(String pSeverityType, Accident[] pArrAccidents)
    {  
        int counter  = 0;
        for(int i = 0; i < pArrAccidents.length ; i++)
        {
            if(pSeverityType.equals(pArrAccidents[i].getSeverity()))
            {
                counter++;
            }
        }

        return counter;
    }




    //SUBMODULE: numEventNature
    //IMPORT: pEventNature (String), pArrAccidents(Array of Accident)
    //EXPORT: counter(Integer)
    //ASSERTION: Search througn pArrAccidents and count the number of accidents for a given event nature

    private static int numEventNature(String pEventNature , Accident [] pArrAccidents)
    {
        int counter = 0;
        for (int i = 0; i< pArrAccidents.length ; i++)
        {
            if (pEventNature.equals(pArrAccidents[i].getEventNature()))
            {
                counter++;
            }
        }

        return counter;
    }




    //SUBMODULE: writeToFile
    //IMPORT: pArrAccidents(Array of Accident)
    //EXPORT: nil
    //ASSERTION: write the summary data to a file provided by the user

    private static void writeToFile(Accident [] pArrAccidents) 
    {   
        //getting user input for file name
        String fileName = fileNameInput();
        
        //Initialising objects of FileOutputStream and PrintWriter
        FileOutputStream fileStream = null;
        PrintWriter pw; 

        try 
        {     
            //Calculating number of accidents for given severity type
            int numFatal = numSeverityType("Fatal" , pArrAccidents); 
            int numHospital = numSeverityType("Hospital", pArrAccidents);

            //Calculating number of accidents for given Event Nature
            int numRearEnd = numEventNature("Rear End" , pArrAccidents); 
            int numRightAngle = numEventNature("Right Angle" , pArrAccidents);
        
            //Calculating percentage of all accidents for a given severity type
            double percentFatal = percentOfAccidents(numFatal, pArrAccidents.length); 
            double percentHospital = percentOfAccidents(numHospital, pArrAccidents.length);
            double percentRearEnd = percentOfAccidents(numRearEnd, pArrAccidents.length);
            double percentRightAngle = percentOfAccidents(numRightAngle, pArrAccidents.length);
        
            fileStream = new FileOutputStream(fileName); 
            pw = new PrintWriter(fileStream);

            //Print to file 
            pw.println("The total number of accidents:" + pArrAccidents.length);

            //fatal accidents 
            pw.println("The total number of fatal accidents: " + numFatal); 
            pw.println("The total number of fatal accidents as a percentage of all accidents: " + percentFatal + "%.");
        
            //hospital accidents
            pw.println("The total number of hospital accidents: " + numHospital);
            pw.println("The total number of hospital accidents as a percentage of all accidents: " + percentHospital + "%.");

            //Rear End accidents
            pw.println("The total number of Rear End accidents: " + numRearEnd);
            pw.println("The total number of Rear End accidents as a percentage of all accidents: " +  percentRearEnd + "%.");

            //Right Angle accidents
            pw.println("The total number of Right Angle accidents: " + numRightAngle);
            pw.println("The total number of Right Angle accidents as a percentage of all accidents: " 
                       + percentRightAngle + "%.");

            pw.close();

            //Let user know file write success
            System.out.println("\nSummary of data successfully written to " + fileName + "!");
         } 
         catch(IOException e) 
         {  
            if (fileStream != null)
            {
                try
                {
                    fileStream.close();
                }       
                catch(IOException ex2)
                {}
            }           
            System.out.println("Error in writing to file!");
         } 
    } 




    //SUBMODULE: fileNameInput
    //IMPORT: nil
    //EXPORT: fileName(String)
    //ASSERTION: get user input for a file name, validate file name so that it is a .txt file
    
    private static String fileNameInput()
    {
       
        System.out.println("Please enter the name of a text file below");
        
        String fileName;  
        do
        {
            System.out.print("File Name: ");
            Scanner sc = new Scanner(System.in);
            fileName = sc.nextLine();
        }
        //Loops until fileName is not empty, first character is not a space and textFileCheck = true
        //used (!textFileCheck) so it stops when textFileCheck returns true. 
        while(fileName.equals("") || fileName.charAt(0) == 32 || !textFileCheck(fileName));  
      
       return fileName; 
    }




    //SUBMODULE: textFileCheck
    //IMPORT: pFileName(String)
    //EXPORT: isValid(Boolean)
    //ASSERTION: check if a string contains .txt

    private static boolean textFileCheck(String pFileName)
    {
        boolean isValid = false;
        if(pFileName.length() >= 4)
        {   
            //Checks if the last four characters forms .txt in this order. 
            if(pFileName.charAt(pFileName.length() - 4) == 46)
            {
                if(pFileName.charAt(pFileName.length() -3) == 116)
                {
                    if(pFileName.charAt(pFileName.length() -2) == 120)
                    {
                        if(pFileName.charAt(pFileName.length() - 1) == 116)
                        {
                            isValid = true;
                        }
                    }
                }
            }
        }
        //If isValid is false, it will print that to user
        if(!isValid)
        {
            System.out.println("Please enter a .txt file");
        }
        return isValid;
    }
                

}
