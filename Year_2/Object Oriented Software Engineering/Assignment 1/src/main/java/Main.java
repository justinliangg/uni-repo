package edu.curtin.maze;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main
{   
    private static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args)
    {
        /* Game Configs */
        logger.info("Initialising game configs");
        Map<String, ObjectType> objectTypes = getObjectTypes(); 
        Map<Character, Movement> moves = getMoves();
        
        try(Scanner sc = new Scanner(System.in))
        {
             /* Getting file name from user*/
            logger.info("Retrieving file name from user");
            String fileName = getFileName(sc);
            List<String> nonFatalErrors = new LinkedList<>(); 
        
            /* Initialising the map for the maze */
            logger.info("Initialising maze map");
            MazeMap map = new MazeMap();
            MazeObject[][] mazeMap = map.createMazeStructure(fileName,
                                                 nonFatalErrors, objectTypes);
            
            /* Starting Game */ 
            boolean start = getStartConfirmation(nonFatalErrors, sc);
            if(start) 
            {   
                logger.info("Starting game");
                /* Initialising player and its starting position */
                int[] startCoords = getStartingPosition(mazeMap);
                Player player = new Player(startCoords[0], startCoords[1]);

                /* Running Maze Game */
                MazeGame game = new MazeGame(player, mazeMap);
                game.run(objectTypes, moves);
                
                logger.info("Game completed");
                System.out.println("You completed the game!");
            }
            
        }
        catch(FileFormatException e)
        {   
            logger.info(() -> e.getMessage());
            System.out.println("Error! " + e.getMessage());
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error! Unable to find file!");
        }
        catch(IOException e)
        {
            System.out.println("Error! " + e.getMessage());
        }
    }


    private static Map<String, ObjectType> getObjectTypes()
    { 
        Map<String, ObjectType> objectTypes = new HashMap<>();

        objectTypes.put("DV", new DoorVerticalType());
        objectTypes.put("DH", new DoorHorizontalType());
        objectTypes.put("WV", new WallVerticalType());
        objectTypes.put("WH", new WallHorizontalType());
        objectTypes.put("K", new KeyType());
        objectTypes.put("M", new MessageType());
        objectTypes.put("S", new StartType());
        objectTypes.put("E", new EndType());
        
        return objectTypes;
    }


    private static Map<Character, Movement> getMoves()
    {
        Map<Character, Movement> moves = new HashMap<>();

        moves.put('n', new MoveUp());
        moves.put('s', new MoveDown());
        moves.put('w', new MoveLeft());
        moves.put('e', new MoveRight());

        return moves;
    }


    private static int[] getStartingPosition(MazeObject[][] mazeMap)
    {   
        //startCoords[0] == x coords
        //startCoords[1] == y coords
        int[] startCoords = new int[2];

        for(int i = 1; i < mazeMap.length; i = i + 2)
        {
            for(int j = 2; j < mazeMap[0].length; j = j + 4)
            {
                List<String> objectTypes = mazeMap[i][j].getTypes();

                for(String type : objectTypes)
                {
                    if(type.equals("Start"))
                    {
                        startCoords[0] = i;
                        startCoords[1] = j;
                    }
                }
            }
        }

        return startCoords;
    }
    

    private static String getFileName(Scanner sc)
    {
     
        System.out.print("Enter file for maze game: ");
        String fileName = sc.nextLine().trim();
      
        return fileName;
    }
    

    private static boolean getStartConfirmation(List<String> nonFatalErrors,
                                                Scanner sc)
    {
        boolean start = false;

        if(nonFatalErrors.isEmpty()) //no errors can just start
        {
            start = true;
        }
        else //display and ask for confirmation.
        {
            //Displaying errors
            System.out.println("\nNon fatal errors found in file: ");
            for(String error : nonFatalErrors)
            {
                System.out.println(error);
            }
            System.out.println("");
            
            //Getting confirmation
            System.out.println("Would you like to continue? Enter yes or no");
            String confirmation = sc.nextLine().toUpperCase();
            if(confirmation.equals("YES") || confirmation.equals("Y"))
            {  
                start = true;
            }
        }

        return start;
    }  

}

