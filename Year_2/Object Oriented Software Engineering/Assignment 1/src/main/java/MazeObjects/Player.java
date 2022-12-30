package edu.curtin.maze;

import java.util.List;
import java.util.LinkedList;

public class Player implements MazeObject
{   
    private int xCoord;
    private int yCoord;
    private List<String> keys;
    private List<String> messages;

    public Player(int xCoord, int yCoord)
    {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        keys = new LinkedList<>();
        messages = new LinkedList<>();
    }

    @Override
    public void display()
    {
        System.out.print("P");
    }

    @Override
    public List<String> getTypes()
    {
        List<String> type = new LinkedList<>();
        type.add("Player");
        return type;
    }

    public int getXCoord()
    {
        return xCoord;
    }

    public int getYCoord()
    {
        return yCoord;
    }

    public void setXCoord(int newXCoord)
    {
        xCoord = newXCoord;
    }

    public void setYCoord(int newYCoord)
    {
        yCoord = newYCoord;
    }

    public List<String> getKeys()
    {   
        //making a copy instead of returning reference.
        List<String> keysToReturn = new LinkedList<>();
        keysToReturn.addAll(keys);

        return keysToReturn;
    }

    public void insertKey(String key)
    {
        keys.add(key);
    }

    public void insertMessage(String message)
    {
        messages.add(message);
    }

    public List<String> getMessages()
    {   
        //allowing it to only access it once, clearing it once retrieved.
        List<String> messagesToReturn = messages;
        messages = new LinkedList<>();

        return messagesToReturn;
    }
}
    
