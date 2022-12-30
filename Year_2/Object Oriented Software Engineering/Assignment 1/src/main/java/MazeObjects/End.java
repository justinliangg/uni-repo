package edu.curtin.maze;

import java.util.LinkedList;
import java.util.List;

public class End extends ObjectDecorator
{      
    public End(MazeObject object)
    {   
        super(object);
    }

    @Override 
    public void display()
    {
        System.out.print("E");
    }

    
    @Override
    public List<String> getTypes()
    {   
        List<String> types = new LinkedList<>();
        types.addAll(object.getTypes());

        types.add("End");

        return types;
    }
}
