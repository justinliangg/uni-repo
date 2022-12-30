package edu.curtin.maze;

import java.util.List;

public class MagentaKey extends Key
{   
    public MagentaKey(MazeObject object)
    {
        super(object);
    }


    @Override
    protected void printKeyColor()
    {
        System.out.print("\033[35m");
    }

    
    @Override
    protected void addColor(List<String> types)
    {
        types.add("Magenta Key");
    }
}

