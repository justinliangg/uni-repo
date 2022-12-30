import java.io.BufferedReader;
import java.io.*;
import java.util.*;

public class FileIO
{
    public static Plant [] storeData(String pFileName)
    {   
        FileReader reader = null;
        BufferedReader bfrReader;
        Plant [] arrayOfPlants = new Plant[5];
        try
        {
            reader = new FileReader(pFileName);
            bfrReader = new BufferedReader(reader);
            String line = bfrReader.readLine();
            int elementNum = 0;
            
            while(line != null)
            {   
                String [] row = processLine(line);
                if(!row[1].equals("PLANT_QUANTITY"))
                {
                    arrayOfPlants[elementNum] = new Plant(row[0], Integer.parseInt(row[1]), Integer.parseInt(row[2]), row[3],row[4]);
                    elementNum++;
                }

                line = bfrReader.readLine();
            }
            
            reader.close();
        }
        catch(IOException x)
        {  
            if(reader != null)
            {
                try
                {
                    reader.close();
                }
                catch(IOException x2)
                {}
            }
        }

        return arrayOfPlants;
    }

    private static String [] processLine(String pLine)
    {   
        String [] row; 
        row = pLine.split(",");
        return row;
    }
}