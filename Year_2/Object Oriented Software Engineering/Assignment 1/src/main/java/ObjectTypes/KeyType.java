package edu.curtin.maze;

public class KeyType extends ObjectType
{
    public KeyType()
    {}


    @Override
    protected int getPartsLength()
    {
        return 4;
    }


    @Override
    protected void insertType(int xCoord, int yCoord, MazeObject[][] maze,
                                                            String[] parts)
    {
        //Decorating key
        MazeObject currObject = maze[xCoord][yCoord+1];
        int color = Integer.parseInt(parts[3]);
        currObject = decorateKey(currObject, color);

        //Inserting decorated object back
        maze[xCoord][yCoord+1] = currObject;
    }


    private static MazeObject decorateKey(MazeObject currObject, int color) 
    {
        switch(color)
        {
            case 1:
                currObject = new RedKey(currObject);
                break;

            case 2:
                currObject = new GreenKey(currObject);
                break;

            case 3:
                currObject = new YellowKey(currObject);
                break;
            
            case 4:
                currObject = new BlueKey(currObject);
                break;
            
            case 5:
                currObject = new MagentaKey(currObject);
                break;
            
            case 6:
                currObject = new CyanKey(currObject);
                break;   
                
            default: //do nothing
                break;
        }

        return currObject;
    }
}
