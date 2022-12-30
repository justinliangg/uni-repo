package edu.curtin.maze;

import java.util.List;
import java.util.LinkedList;

public class GreenKey extends ObjectDecorator
{   
    public GreenKey(MazeObject object)
    {
        super(object);
    }

    @Override 
    public void display()
    {
        System.out.print("\033[32m");
        System.out.print("╕");
        System.out.print("\033[m");
    }

    
    @Override
    public List<String> getTypes()
    {   
        List<String> types = new LinkedList<>();
        types.addAll(object.getTypes());
        types.add("Green Key");

        return types;
    }
}