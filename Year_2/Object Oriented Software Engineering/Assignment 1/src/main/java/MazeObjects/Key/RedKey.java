package edu.curtin.maze;

import java.util.List;

public class RedKey extends Key
{   
    public RedKey(MazeObject object)
    {
        super(object);
    }


    @Override
    protected void printKeyColor()
    {
        System.out.print("\033[31m");
    }

    
    @Override
    protected void addColor(List<String> types)
    {
        types.add("Red Key");
    }
}

