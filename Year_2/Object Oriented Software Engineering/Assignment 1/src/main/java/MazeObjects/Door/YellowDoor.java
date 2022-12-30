package edu.curtin.maze;

import java.util.List;

public class YellowDoor extends Door
{   
    public YellowDoor(MazeObject object)
    {
        super(object);
    }


    @Override
    protected void printDoorColor()
    {
        System.out.print("\033[33m");
    }

    
    @Override
    protected void addColor(List<String> types)
    {
        types.add("Yellow Door");
    }
}
