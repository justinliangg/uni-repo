package edu.curtin.maze;

import java.util.List;

public class BlueDoor extends Door
{   
    public BlueDoor(MazeObject object)
    {
        super(object);
    }


    @Override
    protected void printDoorColor()
    {
        System.out.print("\033[34m");
    }

    
    @Override
    protected void addColor(List<String> types)
    {
        types.add("Blue Door");
    }
}
