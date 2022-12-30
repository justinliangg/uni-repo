package edu.curtin.maze;

import java.util.List;
import java.util.LinkedList;

public class Message extends ObjectDecorator
{      
    private String message;

    public Message(MazeObject object, String message)
    {
        super(object);
        this.message = message;
    }


    @Override
    public void display()
    {
        object.display();
    }


    @Override
    public List<String> getTypes()
    {      
        //creating a new list and copying the old list over.
        List<String> types = new LinkedList<>();
        types.addAll(object.getTypes());

        types.add("Message:" + message);
        
        return types;
    }
}
