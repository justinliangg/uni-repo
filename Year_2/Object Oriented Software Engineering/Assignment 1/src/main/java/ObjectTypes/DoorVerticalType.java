package edu.curtin.maze;

import java.util.Arrays;

public class DoorVerticalType extends ObjectType
{
    public DoorVerticalType()
    {}


    @Override
    protected int getPartsLength()
    {
        return 4;
    }


    @Override
    protected void insertType(int xCoord, int yCoord, MazeObject[][] maze, String[] parts)
                                                       throws NonFatalException
    {
        //decorating door
        MazeObject currObject = maze[xCoord][yCoord-1];

        if(currObject.getTypes().isEmpty())
        {   
            int color = Integer.parseInt(parts[3]);
            currObject = decorateDoor(currObject, color);

            //inserting decorated object back 
            maze[xCoord][yCoord-1] = currObject;
        }
        else 
        {
            throw new NonFatalException("At line: " + Arrays.toString(parts) 
                                    + " trying to insert on another wall or door");
        }
    }
  

    private static MazeObject decorateDoor(MazeObject currObject, int color)
    {
        switch(color)
        {
            case 1:
                currObject = new RedDoor(currObject);
                break;

            case 2:
                currObject = new GreenDoor(currObject);
                break;

            case 3:
                currObject = new YellowDoor(currObject);
                break;
            
            case 4:
                currObject = new BlueDoor(currObject);
                break;
            
            case 5:
                currObject = new MagentaDoor(currObject);
                break;
            
            case 6:
                currObject = new CyanDoor(currObject);
                break;   
            
            default: //do nothing
                break;
        }

        return currObject;
    }
}