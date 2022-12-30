package edu.curtin.maze;

public class MoveRight extends Movement
{
    public MoveRight()
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
        return 4;
    }
}
