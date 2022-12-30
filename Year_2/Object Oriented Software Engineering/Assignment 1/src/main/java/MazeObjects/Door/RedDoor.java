package edu.curtin.maze;

import java.util.List;

public class RedDoor extends Door
{   
    public RedDoor(MazeObject object)
    {
        super(object);
    }


    @Override
    protected void printDoorColor()
    {
        System.out.print("\033[31m");
    }

    
    @Override
    protected void addColor(List<String> types)
    {
        types.add("Red Door");
    }
}
