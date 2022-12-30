package edu.curtin.maze;

import java.util.List;

public class CyanDoor extends Door
{   
    public CyanDoor(MazeObject object)
    {
        super(object);
    }


    @Override
    protected void printDoorColor()
    {
        System.out.print("\033[36m");
    }

    
    @Override
    protected void addColor(List<String> types)
    {
        types.add("Cyan Door");
    }
}
