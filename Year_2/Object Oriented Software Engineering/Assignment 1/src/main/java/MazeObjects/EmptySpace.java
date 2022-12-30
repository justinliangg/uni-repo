package edu.curtin.maze;

import java.util.LinkedList;
import java.util.List;

public class EmptySpace implements MazeObject
{   

    public EmptySpace()
    {}

    @Override 
    public void display()
    {
        System.out.print(" ");
    }

    
    @Override
    public List<String> getTypes()
    {
        return new LinkedList<>();
    }
}
