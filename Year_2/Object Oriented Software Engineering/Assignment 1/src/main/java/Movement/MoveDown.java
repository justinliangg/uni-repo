package edu.curtin.maze;

public class MoveDown extends Movement
{
    public MoveDown()
    {
        super();
    }

    @Override
    protected int getMoveX()
    {
        return 2;
    }

    @Override
    protected int getMoveY()
    {
        return 0;
    }
}
