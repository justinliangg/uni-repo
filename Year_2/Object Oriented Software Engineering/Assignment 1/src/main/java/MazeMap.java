package edu.curtin.maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.logging.Logger;

public class MazeMap
{      
    private int rows;
    private int columns;
    private MazeObject[][] maze;
    private static Logger logger = Logger.getLogger(MazeMap.class.getName());

    public MazeMap()
    {
        rows = 0;
        columns = 0;
        maze = null;
    }

    
    public MazeObject[][] createMazeStructure(String fileName, List<String> nonFatalErrors,
                                              Map<String, ObjectType> objectTypes)
                                              throws FileFormatException, IOException
    {                   
        /* Reading from input file and constructing the maze */
        try(BufferedReader bfr = new BufferedReader(new FileReader(fileName)))
        {   
            //First line contains maze dimensions
            String line = bfr.readLine().trim();
            setMazeDimensions(line);
            fillMazeSpaces();
            createBoundary();
            logger.info(() -> "Created maze array " + rows + "x" + columns);

            //Subsequent lines are objects in maze.
            logger.info("Inserting objects into the array");
            line = bfr.readLine();
            while(line != null)
            {   
                processLine(line, nonFatalErrors, objectTypes);
                line = bfr.readLine();
            }

            //checking if theres valid start and end points
            if(!hasStart())
            {   
                maze[1][2] = new Start(maze[1][2]);
                nonFatalErrors.add("No start point found, default set to " + 
                                   " location [0,0]");
            }
            if(!hasEnd())
            {
                int yCoord = maze[0].length - 3;
                maze[1][yCoord] = new End(maze[1][yCoord]);
                nonFatalErrors.add("No end point found, default set to " + 
                                     " location [0," + yCoord / 4 + "]");
            }
        }

        //Joining any walls that have an adjoining walls.
        joinInnerWalls();
        joinParameterWalls();

        return maze;
    }  
    

    private void processLine(String line, List<String> nonFatalErrors, 
                             Map<String, ObjectType> objectTypes) 
                                                    throws FileFormatException
    {
        String[] parts = line.trim().split(" ", 4);  

        //parts[0] provides the maze object type
        ObjectType objType = objectTypes.get(parts[0]);
        if(objType != null)
        {   
            try
            {
                //inserting correct objType into the maze.
                objType.insert(maze, parts);
            }
            catch(NonFatalException e)
            {   
                nonFatalErrors.add(e.getMessage());
            }
        }
    }


    private void setMazeDimensions(String line) throws FileFormatException
    {
        String[] parts = line.trim().split(" ", 2); //ensure no white spaces
        try
        {   
            //converting grid squares to appropriate character spaces
            rows = Integer.parseInt(parts[0]) * 2 + 1; 
            columns = Integer.parseInt(parts[1]) * 4 + 1;

            maze = new MazeObject[rows][columns]; 
        }
        catch(NumberFormatException e)
        {
            throw new FileFormatException("Maze dimensions not integer type", e);
        }
    }


    private void fillMazeSpaces()
    {   
        
        //filled all spaces in maze with EmptySpace to decorate later.
        for(int i = 0; i < maze.length; i++)
        {
            for(int j = 0; j < maze[0].length; j++)
            {
                maze[i][j] = new EmptySpace();
            }
        }
    }


    private void createBoundary()
    {   
        /* Making the four corners */
        //top left corner
        maze[0][0] = new Wall(maze[0][0], "┌");
        //top right corner
        maze[0][columns-1] = new Wall(maze[0][columns-1], "┐");
        //bottom left corner
        maze[rows-1][0] = new Wall(maze[rows-1][0], "└");
        //bottom right corner
        maze[rows-1][columns-1] = new Wall(maze[rows-1][columns-1], "┘");

        /* Making the horizontal boundaries */
        for(int i = 1; i < columns - 1; i++) 
        {      
            //top section 
            maze[0][i] = new Wall(maze[0][i], "\u2500");
            
            //bottom section
            maze[rows-1][i] = new Wall(maze[rows-1][i], "\u2500");
        }

        /* Making the vertical boundaries */
        for(int i = 1; i < rows - 1; i++)
        {
            //left section
            maze[i][0] = new Wall(maze[i][0], "\u2502");
            //right section
            maze[i][columns-1] = new Wall(maze[i][columns-1], "\u2502");
        }
    }


    private void joinParameterWalls()
    {    
        //For Horizontal parameter
        for(int i = 4; i < columns - 1; i = i + 4) 
        {      
            //top section 
            if(!maze[1][i].getTypes().isEmpty()) 
            {
                maze[0][i] = new Wall(maze[0][i], "┬");
            }
            
            //bottom section
            if(!maze[rows-2][i].getTypes().isEmpty())
            {
                maze[rows-1][i] = new Wall(maze[rows-1][i], "┴");
            }
        }

        //Vertical Parameter
        for(int i = 2; i < rows - 1; i = i + 2)
        {
            //left section
            if(!maze[i][1].getTypes().isEmpty())
            {
                maze[i][0] = new Wall(maze[i][0], "├");
            }

            //right section
            if(!maze[i][columns-2].getTypes().isEmpty())
            {
                maze[i][columns-1] = new Wall(maze[i][columns-1], "┤");
            }
        }
    }


    private void joinInnerWalls()   
    {   
        //i = i + 2 and j = j + 4 as only need to check those spots 
        //for the corners
        for(int i = 2; i < rows - 1; i = i + 2) 
        {
            for(int j = 4; j < columns - 1; j = j + 4)
            {   
                //checking all four adjoining walls for a given coordinate.
                boolean hasTop = !maze[i-1][j].getTypes().isEmpty();
                boolean hasBottom = !maze[i+1][j].getTypes().isEmpty();
                boolean hasRight = !maze[i][j+1].getTypes().isEmpty();
                boolean hasLeft = !maze[i][j-1].getTypes().isEmpty();  
                
                //determining what wall to insert into maze based on 
                //adjoining walls
                String symbol = getWallSymbol(hasTop, hasBottom, hasRight, hasLeft);
                if(symbol != null)
                {
                    maze[i][j] = new Wall(maze[i][j], symbol);
                }
            }
        }
    }


    private String getWallSymbol(boolean hasTop, boolean hasBottom,
                                 boolean hasRight, boolean hasLeft)
    {   
        String symbol;

        if(hasTop && hasBottom && hasRight && hasLeft)
        {
            symbol = "┼";
        }
        else if(hasTop && hasRight && hasLeft)
        {
            symbol = "┴";
        }
        else if(hasBottom && hasRight && hasLeft)
        {
            symbol = "┬";
        }
        else if(hasTop && hasBottom && hasLeft)
        {
            symbol = "┤";
        }
        else if(hasTop && hasBottom && hasRight)
        {
            symbol = "├";
        }
        else
        {
            symbol = joinTwoWalls(hasTop, hasBottom, hasRight, hasLeft);
        }
        
        return symbol;
    }


    private String joinTwoWalls(boolean hasTop, boolean hasBottom,
                                boolean hasRight, boolean hasLeft)
    {
        String symbol = null;

        if(hasTop && hasLeft)
        {
            symbol = "┘";
        }
        else if(hasTop && hasRight)
        {
            symbol = "└";
        }
        else if(hasBottom && hasRight)
        {
            symbol = "┌";
        }
        else if(hasBottom && hasLeft)
        {
            symbol = "┐";
        }
        else if(hasRight || hasLeft)
        {
            symbol = "─";
        }
        else if(hasTop || hasBottom)
        {
            symbol = "│";
        }

        return symbol;
    }

    
    private boolean hasStart()
    {      
        boolean hasStart = false;

        //iterating through map to find if there is a start object type
        for(int i = 1; i < maze.length; i = i + 2)
        {
            for(int j = 2; j < maze[0].length; j = j + 4)
            {
                List<String> objectTypes = maze[i][j].getTypes();
                for(String objType : objectTypes)
                {
                    if(objType.equals("Start"))
                    {
                        hasStart = true;
                    }
                }
            }
        }

        return hasStart;
    }


    private boolean hasEnd()
    {      
        boolean hasEnd = false;

        //iterating through map to find if there is a start object type
        for(int i = 1; i < maze.length; i = i + 2)
        {
            for(int j = 2; j < maze[0].length; j = j + 4)
            {
                List<String> objectTypes = maze[i][j].getTypes();
                for(String objType : objectTypes)
                {
                    if(objType.equals("End"))
                    {
                        hasEnd = true;
                    }
                }
            }
        }

        return hasEnd;
    }
}
