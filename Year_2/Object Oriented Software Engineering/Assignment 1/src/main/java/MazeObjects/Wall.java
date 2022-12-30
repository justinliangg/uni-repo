package edu.curtin.maze;

import java.util.LinkedList;
import java.util.List;

public class Wall extends ObjectDecorator
{   
    private String symbol;

    public Wall(MazeObject object, String symbol)
    {   
        super(object);
        this.symbol = symbol;
    }

    @Override
    public void display()
    {
        System.out.print(symbol);
    }

    @Override
    public List<String> getTypes()
    {   
        List<String> types = new LinkedList<>();
        types.addAll(object.getTypes());

        types.add("Wall");
        
        return types;
    }
}
