package edu.curtin.maze;

import java.util.List;

public class BlueKey extends Key
{   
    public BlueKey(MazeObject object)
    {
        super(object);
    }


    @Override
    protected void printKeyColor()
    {
        System.out.print("\033[34m");
    }

    
    @Override
    protected void addColor(List<String> types)
    {
        types.add("Blue Key");
    }
}

