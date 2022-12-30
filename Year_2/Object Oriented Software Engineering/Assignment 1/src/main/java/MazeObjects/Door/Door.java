package edu.curtin.maze;

import java.util.List;
import java.util.LinkedList;

public abstract class Door extends ObjectDecorator
{   
    public Door(MazeObject object)
    {
        super(object);
    }

    @Override 
    public void display()
    {
        printDoorColor();
        System.out.print("▒");
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

    protected abstract void printDoorColor();
    protected abstract void addColor(List<String> types);
}