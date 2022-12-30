package edu.curtin.maze;

public class MoveLeft extends Movement
{   
    public MoveLeft()
    {
        super();
    }

    @Override
    protected int getMoveX()
    {
        return 0;
    }

    @Override
    protected int getMoveY()
    {
        return -4;
    }
    
}
