package edu.curtin.maze;

import java.util.List;

public class CyanKey extends Key
{   
    public CyanKey(MazeObject object)
    {
        super(object);
    }


    @Override
    protected void printKeyColor()
    {
        System.out.print("\033[36m");
    }

    
    @Override
    protected void addColor(List<String> types)
    {
        types.add("Cyan Key");
    }
}

