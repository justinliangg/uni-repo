package edu.curtin.maze;

import java.util.Arrays;
import java.util.logging.Logger;

public abstract class ObjectType
{
    private static Logger logger = Logger.getLogger(ObjectType.class.getName());

    public void insert(MazeObject[][] maze, String[] parts) 
                                  throws FileFormatException, NonFatalException
    {
        if(parts.length == getPartsLength())
        {   
            try
            {
                //converted grid to character spaces.
                int xCoord = Integer.parseInt(parts[1]) * 2 + 1;
                int yCoord = Integer.parseInt(parts[2]) * 4 + 1;
                
                //checking within bounds of maze
                boolean withinXBound = xCoord < maze.length && xCoord >= 0;
                boolean withinYBound = yCoord < maze[0].length && yCoord >= 0;
                if(!withinXBound || !withinYBound)
                {
                    throw new NonFatalException("Line: " + Arrays.toString(parts)
                                                    + " is out of bounds");
                }

                //inserting into the maze
                insertType(xCoord, yCoord, maze, parts);
                logger.fine(() -> ("Inserted " + parts[0] + " at [" + xCoord + 
                                   ", " + yCoord + "]"));

            }
            catch(NumberFormatException e) //non-integer type
            {
                throw new FileFormatException("Invalid line: "
                                               + Arrays.toString(parts), e);
            }
        }
        else
        {
            throw new FileFormatException("Invalid line: "
                                               + Arrays.toString(parts));
        }
    }

    protected abstract int getPartsLength();
    protected abstract void insertType(int xCoord, int yCoord, MazeObject[][] maze,
                                       String[] parts) throws NonFatalException;
                                      
}
