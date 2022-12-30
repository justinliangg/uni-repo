import java.io.*;
import java.util.*;

public class TestDistribution
{
    public static void main(String [] args)
    {
        fileRead("RandomNames7000.csv");
    }


    private static int hash(String inKey)
    {
        long hashIdx = 0;

        for(int ii = 0; ii < inKey.length(); ii++)
        {
            hashIdx = (31 * hashIdx) + inKey.charAt(ii);
        }
        
        return ( (int) (hashIdx % (long) 8000));
    }


    public static void fileRead(String fileName)
    {
        FileInputStream fileInput = null;
        InputStreamReader reader;
        BufferedReader bfr;
        String line;
        
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
                
                //This ensures that row can only have two values
                if(column.length == 2)
                {  
                    try
                    {   
                        String key = column[0];
                        
                        System.out.println(hash(key));
                    }
                    //Exception thrown by put() if there is duplicate values
                    catch(IllegalArgumentException x2)
                    {}
                }
                line = bfr.readLine();
            }
            fileInput.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Please enter a valid file name!\n");
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
    }
    

    public static String[] processLine(String pRow)
    {
        String [] inColumn;
        //splits every " " 
        inColumn = pRow.split(",");
        
        //Removing all white spaces between " " 
        for(int i = 0; i < inColumn.length; i++)
        {
            inColumn[i] = inColumn[i].trim();
        }
        return inColumn;
    }

}
