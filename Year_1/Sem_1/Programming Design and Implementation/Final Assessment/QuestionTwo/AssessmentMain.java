/*-------------------------------
  FILE: AssessmentMain.java
  Author: Justin Liang (19821986)
  UNIT: PDI
  REQUIRES: WeatherData.java
  Last Mod: 13/06/2021
--------------------------------*/
import java.util.*;
import java.io.*;

public class AssessmentMain
{
    public static void main(String [] args)
    {   
        boolean exit = false;
        do
        {
            //Intro
            System.out.println("\nWelcome to the BOM Weather Station Application.");
            System.out.print("Please enter the name of the data file or x to exit> ");
            
            //Getting user input for file name
            String userInput = getInput();
            
            //performing actions based on userInput
            //validFileType returns true if userInput is a .csv file
            if(validFileType(userInput))
            {
                processFile(userInput);                 
            }
            else if(checkIfExit(userInput))
            {   
                exit = true;
                System.out.println("YOU HAVE EXITED THE PROGRAM!");
            }
        }
        while(!exit);       
    }




    //SUBMODULE: getInput
    //IMPORT: nil
    //EXPORT: input(String)
    //ASSERTION: get user input for a string
    //           return input
    
    private static String getInput()
    {
        String input;
        
        //validating userInput to ensure String is not empty or blank
        do
        {
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
        }
        //input.charAt(0) == 32 ensures that first character of the string cannot be a space.
        //input == "" ensures that string cannot be empty
        while(input.equals("") || input.charAt(0) == 32); 

        return input; 
   }




    //SUBMODULE: validFileType
    //IMPORT: pFileName(String)
    //EXPORT: isValid(Boolean) 
    //ASSERTION: check pFileName is a .csv file type
    
    private static boolean validFileType(String pFileName)
    {
        boolean isValid = false;

        if(pFileName.length() >= 4)
        {   
            //Checks if the last four characters forms .csv in this order. 
            if(pFileName.charAt(pFileName.length() - 4) == 46)
            {
                if(pFileName.charAt(pFileName.length() -3) == 99)
                {
                    if(pFileName.charAt(pFileName.length() -2) == 115)
                    {
                        if(pFileName.charAt(pFileName.length() - 1) == 118)
                        {
                            isValid = true;
                        }
                    }
                }
            }
        }

        //If isValid is false and pFileName does not equal 'x' 
        //then it will prompt the user to enter a .csv file
        if(!isValid && !checkIfExit(pFileName))
        {
            System.out.print("\nPlease enter a .csv file");
        }
        return isValid;
    }




    //SUBMODULE: checkIfExit
    //IMPORT: pUserInput(String)
    //EXPORT: isExit(Boolean)
    //ASSERTION: check if pUserInput is equal to 'x' or 'X'
    //           Returns true if equal

    private static boolean checkIfExit(String pUserInput)
    {   
        boolean isExit = false;
        
        //Checking if user input is one character long
        if(pUserInput.length() == 1)
        {   
            //Checking if user input is 'x' or 'X' 
            if(pUserInput.charAt(0) == 88 || pUserInput.charAt(0) == 120)
            {
                isExit = true;
            }
        }

        return isExit;
    }




/*---------------------------------------------------------------------------------------------------------*/
//SUBMODULES FOR processFile()

    //SUBMODULE: processFile
    //IMPORT: pFileName(String)
    //EXPORT: nil
    //ASSERTION: process the file from the user
    
    private static void processFile(String pFileName)
    {   
        //countNumRows() - 1 to exclude header row
        int totalNumRows = countNumRows(pFileName) - 1;
        if(totalNumRows > 0)
        {   
            //Counting number of valid rows
            int numValidRows = countNumValidRows(totalNumRows,pFileName);
            //Num of invalid rows
            int numInvalidRows = totalNumRows - numValidRows;

            //Constructing arrWeatherData 
            WeatherData [] arrWeatherData = fileRead(numValidRows, pFileName);     

            //Sorting arrWeatherData based on temperature
            //Highest temp to lowest temp
            WeatherData [] sortedArrWeatherData = insertionSort(arrWeatherData);
            
            //Display Data
            displayData(totalNumRows, numInvalidRows, sortedArrWeatherData);
        }
        else
        {
            System.out.print("\nFile is empty! OR Invalid File!");
        }
    }
    



    //SUBMODULE: countNumRows
    //IMPORT: pFileName(String)
    //EXPORT: numRows(Integer)
    //ASSERTION: count the number of rows in the file
    
    private static int countNumRows(String pFileName)
    {
        FileInputStream fileInput = null;
        InputStreamReader reader;
        BufferedReader bfr;
        String line;    
        int numRows;
        
        numRows=0;     
        try
        {   
            //initialising objects and variables
            fileInput = new FileInputStream(pFileName);
            reader = new InputStreamReader(fileInput);
            bfr = new BufferedReader(reader);
            line = bfr.readLine();

            while(line != null)
            {   
                //Adding to rows count 
                numRows++;  
                
                //Read next line
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

        return numRows;
    }




    //SUBMODULE: countNumValidRows
    //IMPORT: pTotalNumRows(Integer), pFileName(String)
    //EXPORT: elementNum(Integer)
    //ASSERTION: count number of valid rows
    //COMMENTS: created a dummyWeatherData which will be destroyed outside the scope of this submodule
    
    private static int countNumValidRows(int pTotalNumRows, String pFileName)
    {   
        WeatherData [] dummyWeatherData = new WeatherData[pTotalNumRows];

        FileInputStream fileInput = null;
        InputStreamReader reader;
        BufferedReader bfr;
        String line;
        int elementNum;

        elementNum = 0;
        try
        {   
            //initalising objects and variables
            fileInput = new FileInputStream(pFileName);
            reader = new InputStreamReader(fileInput);
            bfr = new BufferedReader(reader);
            line = bfr.readLine();

            while(line != null)
            {
                String[] column = processLine(line); 
                
                //This ensures that row will be rejected if missing commas
                if(column.length == 7)
                {  
                    try
                    {   
                        //Getting the values from the row
                        String code = column[0];
                        String stationNumber = column[1];
                        int year = Integer.parseInt(column[2]);
                        int month = Integer.parseInt(column[3]);
                        int day = Integer.parseInt(column[4]);
                        double maxTemp = Double.parseDouble(column[5]);
                        
                        //Constructing WeatherData object
                        dummyWeatherData[elementNum] = new WeatherData(code, stationNumber, year, month,
                                                        day, maxTemp);

                        //If not exceptions are thrown, add 1 to element num
                        elementNum++;
                    }
                    //Exception thrown by Integer.parseInt or Double.parseDouble
                    //If String cannot be converted to an integer or Double
                    catch(NumberFormatException x)
                    {}
                    //Exception throw by WeatherData constructor
                    //If values in parameters are not valid
                    catch(IllegalArgumentException x2)   
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
        return elementNum;
    }
 



    //SUBMODULE: fileRead
    //IMPORT: pNumValidRows(Integer), pFileName(String)
    //EXPORT: inArrWeatherData(Array of WeatherData)
    //ASSERTION: read the file and store values in inArrWeatherData
    
    private static WeatherData [] fileRead(int pNumValidRows, String pFileName)
    {   
        //Initialising array
        WeatherData [] inArrWeatherData = new WeatherData[pNumValidRows];

        FileInputStream fileInput = null;
        InputStreamReader reader;
        BufferedReader bfr;
        String line;
        int elementNum;

        elementNum = 0;
        try
        {   
            //initalising objects and variables
            fileInput = new FileInputStream(pFileName);
            reader = new InputStreamReader(fileInput);
            bfr = new BufferedReader(reader);
            line = bfr.readLine();

            while(line != null)
            {
                String[] column = processLine(line); 
                
                //This ensures that row will be rejected if missing commas
                if(column.length == 7)             
                {   
                    try
                    {   
                        //Getting the values from the row
                        String code = column[0];
                        String stationNumber = column[1];
                        int year = Integer.parseInt(column[2]);
                        int month = Integer.parseInt(column[3]);
                        int day = Integer.parseInt(column[4]);
                        double maxTemp = Double.parseDouble(column[5]);
                        
                        //Constructing WeatherData object
                        inArrWeatherData[elementNum] = new WeatherData(code, stationNumber, year, month,
                                                                day, maxTemp);
                        //elementNum below the construction of WeatherData object
                        //If any values are invalid, row will be rejected.
                        elementNum++;
                    }
                    //Exception thrown by Integer.parseInt or Double.parseDouble
                    //If String cannot be converted to an integer or double
                    catch(NumberFormatException x)
                    {}

                    //Exception throw by WeatherData constructor
                    //If values in parameters are not valid
                    catch(IllegalArgumentException x2)   
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
        return inArrWeatherData;
    }
    



    //SUBMODULE: processLine
    //IMPORT: pRow(String)
    //EXPORT: inColumn(Array of Strings)
    //ASSERTION: split pRow every ",", store each element seperated in inColumn 
     
    private static String[] processLine(String pRow)
    {   
        String [] inColumn;
        
        //splits every "," and stopping when inColumn.length() = 7
        //groups Days of accumulation and Quality columns together
        //As they will not be needed 
        inColumn = pRow.split(",",7);
        return inColumn;
    }




    //SUBMODULE: insertionSort
    //IMPORT: pArrWeatherData(Array of WeatherData)
    //EXPORT: pArrWeatherData(Array of WeatherData)
    //ASSERTION: sort pArrWeatherData based on temp
    //           highest temp to lowest temp 
    
    private static WeatherData [] insertionSort(WeatherData [] pArrWeatherData)
    {
        for(int i = 1; i< pArrWeatherData.length; i++)
        {
            //creates a temp storage for the values
            WeatherData currentWeatherData = pArrWeatherData[i];

            int j = i;
            while(j>0 && pArrWeatherData[j-1].getMaxTemp() < currentWeatherData.getMaxTemp())
            {
                //shifts values to the right if loop is ran
                pArrWeatherData[j] = pArrWeatherData[j-1];

                j--;
            }

            //Swapping the weatherData
            pArrWeatherData[j] = currentWeatherData;
        }

        return pArrWeatherData;
    }




 
    //SUBMODULE: displayData 
    //IMPORT: pTotalNumRows(Integer), pNumInvalidRows(Integer), pSortedArrWeatherData(Array of WeatherData)
    //EXPORT: nil
    //ASSERTION: display summary of data from csv to user
    
    private static void displayData(int pTotalNumRows, int pNumInvalidRows, WeatherData [] pSortedArrWeatherData)
    {
        //Total num entries and num invalid entries
        System.out.print("\nThe data file contains " + pTotalNumRows + " entries.\n"
                         + "There were " + pNumInvalidRows + " incomplete data entries.\n");
        
        //Max Temp
        displayMaxTemp(pSortedArrWeatherData);

        //For a user selected month
        //Getting user input for the month
        int monthInput = getMonthInput(); 
        //Getting avg temp for monthInput
        double avgTemp = calculateAvgTemp(monthInput, pSortedArrWeatherData);
        
        //Displaying it to user
        System.out.println("The average temperature for month " + getMonthName(monthInput) + " was: "
                          + avgTemp + "°");
    }



    
    //SUBMODULE: displayMaxTemp 
    //IMPORT: pSortedArrWeatherData(Array of WeatherData)
    //EXPORT: nil
    //ASSERTION: Displays details of max temp
    //COMMENTS: this was used in case there is more than one max temp
    
    private static void displayMaxTemp(WeatherData [] pSortedArrWeatherData)
    {  
        System.out.println("\nDetails of the maximum temperature");
        System.out.println("Temperature: " + pSortedArrWeatherData[0].getMaxTemp() + "°");
            
        //Used just in case there is more than one max temp
        int elementNum = 0;
        boolean found = false;
        while(!found)
        {   
            //Looping from bottom of the array and stopping when maxTemp(s) are found/.
            if(pSortedArrWeatherData[elementNum].getMaxTemp() < pSortedArrWeatherData[0].getMaxTemp())
            {   
                found = true;
            }
            else
            {   
                //Display information
                System.out.println("Code: " + pSortedArrWeatherData[elementNum].getCode());
                System.out.println("Station Number: " + pSortedArrWeatherData[elementNum].getStationNumber());
                System.out.println("Date: " + pSortedArrWeatherData[elementNum].formattedDate());

                //Checking next element in array
                elementNum ++;
            }

        }
    }



   
    //SUBMODULE: getMonthInput
    //IMPORT: nil
    //EXPORT: monthNum(Integer)
    //ASSERTION: get user input for a selected month
    
    private static int getMonthInput()
    {   
        int monthNum = 0;
        boolean monthAcquired = false;
        //Loops until month is acquired
        while(!monthAcquired)
        {
            System.out.print("\nWhat month would you like to know the average temperature for? > ");
            
            //Getting month from user;
            String month= getInput();
            //month.toUpperCase() makes all letters in month to uppercase
            //so ie. maRcH will still work
            String upperCaseMonth = month.toUpperCase();
            
            //Checking what month it is
            if(upperCaseMonth.equals("JANUARY") || month.equals("1"))
            {   
                monthAcquired = true;
                monthNum = 1;
            }
            else if(upperCaseMonth.equals("FEBRUARY") || month.equals("2"))
            {
                monthAcquired = true;
                monthNum = 2;
            }
            else if(upperCaseMonth.equals("MARCH") || month.equals("3"))
            {
                monthAcquired = true;
                monthNum = 3;
            }
            else if(upperCaseMonth.equals("APRIL") || month.equals("4"))
            {
                monthAcquired = true;
                monthNum = 4;
            }
            else if(upperCaseMonth.equals("MAY") || month.equals("5"))
            {
                monthAcquired = true;
                monthNum = 5;
            }
            else if(upperCaseMonth.equals("JUNE") || month.equals("6"))
            {
                monthAcquired = true;
                monthNum = 6;
            }
            else if(upperCaseMonth.equals("JULY") || month.equals("7"))
            {
                monthAcquired = true;
                monthNum = 7;
            }
            else if(upperCaseMonth.equals("AUGUST") || month.equals("8"))
            {
                monthAcquired = true;
                monthNum = 8;
            }
            else if(upperCaseMonth.equals("SEPTEMBER") || month.equals("9"))
            {
                monthAcquired = true;
                monthNum = 9;
            }
            else if(upperCaseMonth.equals("OCTOBER") || month.equals("10"))
            {
                monthAcquired = true;
                monthNum = 10;
            }
            else if(upperCaseMonth.equals("NOVEMBER") || month.equals("11"))
            {
                monthAcquired = true;
                monthNum = 11;
            }
            else if(upperCaseMonth.equals("DECEMBER") || month.equals("12"))
            {
                monthAcquired = true;
                monthNum = 12;
            }
            else
            {
                System.out.print("\nPlease enter a valid month!");
            }
        }

        return monthNum;
    }



     
    //SUBMODULE: calculateAvgTemp
    //IMPORT: pMonthInput(Integer), pSortedArrWeatherData(Array of WeatherData)
    //EXPORT: inAvgTemp(Double)
    //ASSERTION: calculate the average temp for a given month

    private static double calculateAvgTemp(int pMonthInput, WeatherData [] pSortedArrWeatherData)
    {   
        double totalTemp = 0.0;
        int numTemp = 0;
        for(int i = 0; i < pSortedArrWeatherData.length; i++)
        {   
            //Searching through pSortedArrWeatherData 
            if(pSortedArrWeatherData[i].getMonth() == pMonthInput)
            {   
                //Adding to totalTemp
                totalTemp = totalTemp + pSortedArrWeatherData[i].getMaxTemp();
                //Keeping a counter for given pMonthInput
                numTemp++;
            }
        }
        //Calculating the average temp for pMonthInput
        double inAvgTemp = totalTemp / (double) numTemp;
        
        //Rounding inAvgTemp to 2 decimal places
        ////code from user :asterite
        //https://stackoverflow.com/a/153753
        //(Accessed on 12th June 2021)  
        inAvgTemp= (double) Math.round(inAvgTemp*100.0)/100.0;
        //end code from user asterite

        return inAvgTemp;
    }





    //SUBMODULE: getMonthName
    //IMPORT: pMonthInput(Integer)
    //EXPORT: monthName(String)
    //ASSERTION: get a month as an integer and return its name
    
    private static String getMonthName(int pMonthInput)
    {   
        String monthName = null;
        if(pMonthInput == 1)
        {
            monthName = "January";
        }
        else if(pMonthInput == 2)
        {
            monthName = "February";
        }
        else if(pMonthInput == 3)
        {
            monthName = "March";
        }
        else if(pMonthInput == 4)
        {
            monthName = "April";
        }
        else if(pMonthInput == 5)
        {
            monthName = "May";
        }
        else if(pMonthInput == 6)
        {
            monthName = "June";
        }
        else if(pMonthInput == 7)
        {
            monthName = "July";
        }
        else if(pMonthInput == 8)
        {
            monthName = "August";
        }
        else if(pMonthInput == 9)
        {
            monthName = "September";
        }
        else if(pMonthInput == 10)
        {
            monthName = "October";
        }
        else if(pMonthInput == 11)
        {
            monthName = "November";
        }
        else if(pMonthInput == 12)
        {
            monthName = "December";
        }

        return monthName;
    }


}
