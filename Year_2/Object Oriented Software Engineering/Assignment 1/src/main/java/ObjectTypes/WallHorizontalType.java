package edu.curtin.maze;

import java.util.Arrays;

public class WallHorizontalType extends ObjectType
{
    public WallHorizontalType()
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
        String symbol = "\u2500";
        MazeObject currObject = maze[xCoord-1][yCoord];
        
        if(currObject.getTypes().isEmpty())
        {
            MazeObject newWall = new Wall(maze[xCoord-1][yCoord], symbol);
            maze[xCoord-1][yCoord] = newWall;
            maze[xCoord-1][yCoord+1] = newWall;
            maze[xCoord-1][yCoord+2] = newWall;
        }
        else
        {
             throw new NonFatalException("At line: " + Arrays.toString(parts) 
                                   + " trying to insert on another wall or door");
        }
    }
}