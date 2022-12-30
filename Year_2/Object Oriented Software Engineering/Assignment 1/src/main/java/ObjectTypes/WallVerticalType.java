package edu.curtin.maze;

import java.util.Arrays;

public class WallVerticalType extends ObjectType
{
    public WallVerticalType()
    {}


    @Override
    protected int getPartsLength()
    {
        return 3;
    }


    @Override
    protected void insertType(int xCoord, int yCoord, MazeObject[][] maze, String[] parts)
                                                    throws NonFatalException
    {       
        
        MazeObject currObject = maze[xCoord][yCoord-1];
        
        if(currObject.getTypes().isEmpty())
        {
            maze[xCoord][yCoord-1] = new Wall(maze[xCoord][yCoord-1], "│");
        }
        else
        {
            throw new NonFatalException("At line: " + Arrays.toString(parts) 
            + " trying to insert on another wall or door");
        }
    }
}