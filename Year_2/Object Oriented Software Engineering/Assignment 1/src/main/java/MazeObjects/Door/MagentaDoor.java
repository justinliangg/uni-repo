package edu.curtin.maze;

import java.util.List;

public class MagentaDoor extends Door
{   
    public MagentaDoor(MazeObject object)
    {
        super(object);
    }


    @Override
    protected void printDoorColor()
    {
        System.out.print("\033[35m");
    }

    
    @Override
    protected void addColor(List<String> types)
    {
        types.add("Magenta Door");
    }
}
