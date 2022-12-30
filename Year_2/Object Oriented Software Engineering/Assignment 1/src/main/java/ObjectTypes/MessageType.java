package edu.curtin.maze;

public class MessageType extends ObjectType
{
    public MessageType()
    {}


    @Override
    protected int getPartsLength()
    {
        return 4;
    }


    @Override
    protected void insertType(int xCoord, int yCoord, MazeObject[][] maze,
                                                            String[] parts)
    {
        String message = parts[3];
        
        maze[xCoord][yCoord+1] = new Message(maze[xCoord][yCoord+1], message);
    }
}