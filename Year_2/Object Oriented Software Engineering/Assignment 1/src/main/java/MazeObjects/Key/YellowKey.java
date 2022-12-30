package edu.curtin.maze;

import java.util.List;

public class YellowKey extends Key
{   
    public YellowKey(MazeObject object)
    {
        super(object);
    }


    @Override
    protected void printKeyColor()
    {
        System.out.print("\033[33m");
    }

    
    @Override
    protected void addColor(List<String> types)
    {
        types.add("Yellow Key");
    }
}


