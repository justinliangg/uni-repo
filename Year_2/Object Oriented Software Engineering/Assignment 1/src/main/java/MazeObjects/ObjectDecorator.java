package edu.curtin.maze;

import java.util.List;

public abstract class ObjectDecorator implements MazeObject
{   
    protected MazeObject object;

    public ObjectDecorator(MazeObject object)
    {
        this.object = object;
    }


    @Override
    public void display()
    {
        object.display();
    }

    
    @Override
    public List<String> getTypes()
    {
        return object.getTypes();
    }
}
