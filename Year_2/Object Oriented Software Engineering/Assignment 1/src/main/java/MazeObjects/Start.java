package edu.curtin.maze;

import java.util.LinkedList;
import java.util.List;

public class Start extends ObjectDecorator
{   
    public Start(MazeObject object)
    {
        super(object);
    }

    @Override 
    public void display()
    {
        System.out.print("S");
    }

    
    @Override
    public List<String> getTypes()
    {   
        List<String> types = new LinkedList<>();
        types.addAll(object.getTypes());

        types.add("Start");

        return types;
    }
}