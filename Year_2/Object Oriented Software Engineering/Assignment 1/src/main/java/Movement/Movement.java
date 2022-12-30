package edu.curtin.maze;

import java.util.List;
import java.util.logging.Logger;

public abstract class Movement
{
    private static Logger logger = Logger.getLogger(Movement.class.getName());

    public Movement()
    {}

    public void move(Player player, MazeObject[][] map)
    {   
        /* Initialising variables */
        int playerX = player.getXCoord();
        int playerY = player.getYCoord();

        //moveX and moveY will be based on what the subclasses have implemented.
        int moveX = getMoveX();
        int moveY = getMoveY();

        //coordinate for the structure in between player and next grid spot.
        int structX = moveX / 2 + playerX;
        int structY = moveY / 2 + playerY;

        
        /* MOVING PLAYER */
        if(structX < map.length && structY < map[0].length)
        {  
            map[playerX][playerY] = new EmptySpace();

            //checking the space in between player and next grid spot.
            List<String> structType = map[structX][structY].getTypes();
            if(validMove(structType, player)) //access to door or empty spot.
            {   
                playerX = playerX + moveX;
                playerY = playerY + moveY;
                
                player.setXCoord(playerX);
                player.setYCoord(playerY);
                
                //have to put into final to use lambda express or 
                //compiler complains
                final int xCoord = playerX;
                final int yCoord = playerY;
                logger.fine(() -> ("Player coordinates: [" + xCoord + ", " 
                                                           + yCoord + "]"));  

            }
        }
    }


    private boolean validMove(List<String> structType, Player player)
    {
        boolean isValid = false;

        if(structType.isEmpty()) //empty spot
        {
            isValid = true;
        }
        else //either door or wall.
        {      
            //if door, need to check player keys and door color.
            String struct = structType.get(0);
            if(struct.contains("Door"))
            {
                String doorColor = struct.split(" ")[0];
                isValid = checkDoorAccess(doorColor, player);
            }
            else
            {   
                player.insertMessage("Walking into wall!");
            }
        }   

        return isValid;
    }


    private boolean checkDoorAccess(String doorColor, Player player)
    {
        boolean doorAccess = false;

        List<String> playerKeys = player.getKeys();

        for(String key : playerKeys)
        {
            if(key.contains(doorColor))
            {  
                doorAccess = true;
            }
        }

        if(doorAccess == false)
        {
            player.insertMessage("No access to door");
        }

        return doorAccess;
    }

    protected abstract int getMoveX();
    protected abstract int getMoveY();

}
