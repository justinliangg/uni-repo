package edu.curtin.maze;

import java.util.List;
import java.util.LinkedList;

public abstract class Key extends ObjectDecorator
{   
    public Key(MazeObject object)
    {
        super(object);
    }

    @Override 
    public void display()
    {
        printKeyColor();
        System.out.print("╕");
        System.out.print("\033[m");
    }

    @Override 
    public List<String> getTypes()
    {
        List<String> types = new LinkedList<>();
        types.addAll(object.getTypes());
        addColor(types);

        return types;
    }

    protected abstract void printKeyColor();
    protected abstract void addColor(List<String> types);
}
