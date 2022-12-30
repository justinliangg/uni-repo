/*-------------------------------                                               
FILE: gameofcatz.java                                              
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES: InteractiveMode.java SimulationMode.java
Last Mod: 08/09/2021                                                            
--------------------------------*/
import java.util.*;
import java.io.*;

public class gameofcatz
{
    public static void main(String [] args)
    {
        if(args.length >= 1)
        {
            String gameMode = args[0];
            if(gameMode.equals("-i")) //interactive mode
            {
                InteractiveMode.enterMenu();
            }
            else if(gameMode.equals("-s")) //simulation mode
            {
                SimulationMode.enterMode(args);
            }
            else
            {
                printUsage();
            }
        }
        else
        {
            printUsage();
        }
    }

    //printUsage()
    public static void printUsage()
    {
        System.out.println("Usage: gameofcatz mode [x y]");
        System.out.println("Mode options: -i (Interactive)");
        System.out.println("              -s (Simulation)");
        System.out.println("If -s is selected, x: file to be read from");
        System.out.println("                   y: file to write to");

    }
} 
