package edu.curtin.maze;

import java.util.List;

public class GreenDoor extends Door
{   
    public GreenDoor(MazeObject object)
    {
        super(object);
    }


    @Override
    protected void printDoorColor()
    {
        System.out.print("\033[32m");
    }

    
    @Override
    protected void addColor(List<String> types)
    {
        types.add("Green Door");
    }
}
