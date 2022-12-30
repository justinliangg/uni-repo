package edu.curtin.maze;

public class MoveUp extends Movement
{
    public MoveUp()
    {
        super();
    }

    @Override
    protected int getMoveX()
    {
        return -2;
    }

    @Override
    protected int getMoveY()
    {
        return 0;
    }
}
