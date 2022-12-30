package edu.curtin.maze;

import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.logging.Logger;

public class MazeGame
{   
    private Player player;
    private MazeObject [][] map;
    private static Logger logger = Logger.getLogger(MazeGame.class.getName());

    public MazeGame(Player player, MazeObject[][] map)
    {   
        this.player = player;
        this.map = map;
    }

    public void run(Map<String, ObjectType> objectTypes, 
                    Map<Character, Movement> moves)
    {   
        //variables
        int playerX = player.getXCoord();
        int playerY = player.getYCoord();
        boolean done;

        //Done to check if start and end on the same spot at the start.
        logger.info("Placed player at starting position");
        done = checkNewPositionInfo(); 
        map[playerX][playerY] = player;
        displayMap(map);
        printKeys();
        printMessages();

        try(Scanner sc = new Scanner(System.in))
        {   
            logger.info("User controlling movement of player");
            while(!done)
            {  
                System.out.print("next move: ");
                String userInput = sc.nextLine();

                //updating player coords based on user input.
                char nextMove = 0;
                if(!userInput.equals("")) //user input empty string
                {
                    nextMove = userInput.charAt(0);
                }

                if(nextMove != 0) //there was a valid userInput
                {
                    Movement moveSelected = moves.get(nextMove);
                    if(moveSelected != null)
                    {   
                        moveSelected.move(player, map);
                    }
                }

                //checking info on the new player's position before moving.
                done = checkNewPositionInfo();
                map[player.getXCoord()][player.getYCoord()] = player;
                displayMap(map);
                printKeys();
                printMessages();
            }
        }
    }


    private void printKeys()
    {
        List<String> keys = player.getKeys();  
        String printStatement = "";

        if(!keys.isEmpty())
        {
            printStatement = "Keys: ";

            int numKeys = 0;
            for(String key : keys)
            {       
                if(numKeys == 0)
                {
                    printStatement += key;
                }
                else
                {
                    printStatement += ", " + key;
                }
                numKeys++;
            }
            printStatement += "\n";
        }

        System.out.print(printStatement);
    }


    private void printMessages()
    {
        List<String> messages = player.getMessages();
        
        if(!messages.isEmpty())
        {
            System.out.println("");
            for(String message : messages)
            {
                System.out.println(message);
            }
            System.out.println("");
        }
    }


    private boolean checkNewPositionInfo()
    {
        int playerX = player.getXCoord();
        int playerY = player.getYCoord();
        boolean finished = false;

        List<String> spaceInfo = map[playerX][playerY].getTypes();
        for(String info : spaceInfo)
        {   
            if(info.contains("Message:"))
            {
                String message = info.split(":")[1];
                player.insertMessage(message);
            }
            else if(info.contains("Key"))
            {   
                String keyColor = info.split(" ")[0];
                player.insertKey(keyColor);
            }
            else if(info.equals("End"))
            {
                finished = true;
            }
        }

        return finished;
    }


    public void displayMap(MazeObject[][] map)
    {    
        if(map != null) //guard, only can display once map has been init.
        {
            System.out.print("\033[2J"); //to clear the screen

            for(MazeObject[] columns : map)
            {
                for(MazeObject object : columns)
                {
                    object.display();
                }
                System.out.println("");
            }
        }
    }
}
