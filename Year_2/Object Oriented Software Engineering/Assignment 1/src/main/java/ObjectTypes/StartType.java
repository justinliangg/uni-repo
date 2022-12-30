package edu.curtin.maze;

import java.util.Arrays;

public class StartType extends ObjectType
{   
    private boolean insertedBefore;

    public StartType()
    {
        insertedBefore = false;
    }


    @Override
    protected int getPartsLength()
    {
        return 3;
    }

    @Override
    protected void insertType(int xCoord, int yCoord, MazeObject[][] maze, String[] parts)
                                                       throws NonFatalException
    {
        if(insertedBefore) //checking to see if insertedBefore.
        {
            throw new NonFatalException("At line: " + Arrays.toString(parts) + 
                                         " additional Start point");
        }

        maze[xCoord][yCoord+1] = new Start(maze[xCoord][yCoord+1]);
        insertedBefore = true;
    }
}
