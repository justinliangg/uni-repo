package edu.curtin.maze;

public class EndType extends ObjectType
{
    public EndType()
    {}


    @Override
    protected int getPartsLength()
    {
        return 3;
    }


    @Override
    protected void insertType(int xCoord, int yCoord, MazeObject[][] maze, 
                                                            String[] parts)
    {
        maze[xCoord][yCoord+1]= new End(maze[xCoord][yCoord+1]);
    }
}
