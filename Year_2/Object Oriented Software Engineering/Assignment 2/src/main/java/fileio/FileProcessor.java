package edu.curtin.emergencysim.fileio;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.io.FileReader;

public class FileProcessor
{   
    private static final Pattern INPUT_REGEX  = Pattern.compile(
        "([0-9]+) (fire|flood|chemical) (.+)");

    public FileProcessor()
    {}

    public List<String> read(String fileName) throws IOException,
                                                     FileFormatException
    {   
        List<String> lines = new ArrayList<>();

        try(BufferedReader bfr = new BufferedReader(new FileReader(fileName)))
        {
            String line = bfr.readLine();
            while(line != null)
            {   
                processLine(line, lines);
                line = bfr.readLine();
            }
        }  

        return lines;
    }


    private static void processLine(String line, List<String> lines) 
                                                     throws FileFormatException
    {
        //Checking if line in file matches format.
        Matcher m = INPUT_REGEX.matcher(line); 
        if(!m.matches())
        {   
            throw new FileFormatException(line + " has Invalid format found!");
        }

        //checking if current line has duplicated location and emergency type.
        if(hasDuplicate(line, lines))
        {
            throw new FileFormatException(line + " already exists!");
        }
        
        lines.add(line);
    }

    
    private static boolean hasDuplicate(String currentLine, List<String> lines)
    {
        boolean hasDuplicate = false;

        String[] currParts = currentLine.split(" ");
        String emergencyType = currParts[1];
        String location = currParts[2];

        //checking if previous lines have the same emergencyType and
        //location as current line.
        for(String line: lines)
        {
            String[] prevParts = line.split(" ");
            if(prevParts[1].equals(emergencyType) && prevParts[2].equals(location))
            {
                hasDuplicate = true;
            }
        }

        return hasDuplicate;
    }
}