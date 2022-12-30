/*-------------------------------                                               
FILE: InteractiveMode.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES: World.java                                               
Last Mod: 16/10/2021                                                            
--------------------------------*/  
import java.io.*;
import java.util.*;

public class InteractiveMode
{
    public static void enterMenu()
    {   
        /*Information of the interactive world*/
        World iWorld = new World();
        DSALinkedList allRoutes = new DSALinkedList();

        //Looping through menu
        int choice = 0;
        while(choice != 11)
        {
            displayMenu();         
            try
            {
                //Getting user input
                Scanner sc = new Scanner(System.in);
                choice = sc.nextInt();
                
                //perform actions based on choice
                performChoice(choice, iWorld, allRoutes);
            }
            catch(InputMismatchException e)
            {
                System.out.println("Please enter integers only");
            }
        }
    }
    



    //SUBMODULE: displayMenu
    //IMPORT: nil
    //EXPORT: nil
    //ASSERTION: Display the interactive menu to the user.

    private static void displayMenu()
    { 
        System.out.println("\nInteractive Mode");
        System.out.println("================================================");   
        System.out.println("(1) Load input file\n"
                         + "(2) Node operations\n" 
                         + "(3) Edge operations\n"
                         + "(4) Parameter tweaks\n"
                         + "(5) Display graph\n"
                         + "(6) Display world\n"
                         + "(7) Change targets\n"
                         + "(8) Generate routes\n"
                         + "(9) Display routes\n"
                         + "(10) Save network\n"
                         + "(11) Exit");
    }
    



    //SUBMODULE: performChoice
    //IMPORT: choice(int), iWorld(World), allRoutes(DSALinkedList)
    //EXPORT: nil   
    //ASSERTION: perform an action in Interactive Mode based on choice

    private static void performChoice(int choice, World iWorld,
                                      DSALinkedList allRoutes)
    {   
        switch (choice)
        {   
            /*file input*/
            case 1:
                fileInputOptions(iWorld);
                break; 

            //Node Operations
            case 2: 
                Operations.nodeOperations(iWorld);
                break;
            
            //Edge Operations
            case 3: 
                Operations.edgeOperations(iWorld);
                break;

            //Parameter Tweaks
            case 4: 
                ParameterTweak.paramTweaks(iWorld);
                break;
            
            //Display graph
            case 5:
                Display.displayGraph(iWorld);
                break;
            
            //Display World
            case 6: 
                Display.displayWorld(iWorld);
                break;
            
            case 7: 
                ModifyTarget.changeTargets(iWorld);
                break;
            
            //Generate routes
            case 8:
                generateRoutes(iWorld, allRoutes);  
                break;

            //Display routes
            case 9:
                displayRoutes(iWorld, allRoutes); 
                break;
            
            //Save networks
            case 10:
                saveNetwork(iWorld);
                break;
            
            //Exit
            case 11:
                System.out.println("EXITED!");
                break;

            default: 
                System.out.println("Please enter numbers 1 - 10 only");
                break;
        }

    }//end performChoice()
    



/*---------------------------------------------------------------------------*/
    //fileInputOptions 

    //SUBMODULE: fileInputOptions
    //IMPORT: iWorld(World)
    //EXPORT: nil
    //ASSERTION: get user input for which file input options they would like.

    private static void fileInputOptions(World iWorld)
    {
        System.out.println("1) read from txt file\n" + 
                           "2) read a serialized World object");

        //getting user input
        try
        {
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            if(choice == 1) 
            {   
                //read from txt file
                fileInput(iWorld);
            }
            else if(choice == 2)
            {
                //get World object from serialized file and copy over into
                //current world.
                World newWorld = FileIO.readSerializedFile();
                iWorld.copy(newWorld);
            }
            else
            {
                System.out.println("Invalid input!");
            }
        }
        catch(InputMismatchException e)
        {
            System.out.println("Invalid input!");
        }
        catch(IllegalArgumentException e) //thrown from iWorld.copy()
        {
            System.out.println(e.getMessage());
        }

    }


    
    //SUBMODULE: fileInput
    //IMPORT: iWorld(World)
    //EXPORT: nil
    //ASSERTION: get user input for a file, process file and store the
    //data of the World.

    private static void fileInput(World iWorld)
    {   
        //reading from file and storing into fileData
        String fileName = getFileName();
        DSALinkedList fileData = new DSALinkedList();
        FileIO.filterFile(fileData, fileName);
        
        //file read successful
        if(!fileData.isEmpty())
        {
            //processing file
            try
            { 
                //getting data for the world from file
                SimulationMode.getMappings(fileData, iWorld);
                SimulationMode.getGraphData(fileData, iWorld);
                System.out.println("File read successful");
            }
            catch(IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
                System.out.println("Please fix errors in file");
            }
        }
    }




    //SUBMODULE: getFileName
    //IMPORT: nil
    //EXPORT: fileName(String)
    //ASSERTION: gets a .txt file type from user.

    private static String getFileName()
    {
        String fileName;
        do
        {
            System.out.print("\nfilename: ");
            Scanner sc = new Scanner(System.in);
            fileName= sc.nextLine();
        }
        while(!isTextFile(fileName));

        return fileName;
    }
    
    


    //SUBMODULE: isTextFile
    //IMPORT: pFileName(String)
    //EXPORT: isValid(Boolean)
    //ASSERTION: check if a string contains .txt

    private static boolean isTextFile(String pFileName)
    {
        boolean isValid = false;
        if(pFileName.length() >= 4)
        {   
            //Checks if the last four characters forms .txt in this order. 
            if(pFileName.charAt(pFileName.length() - 4) == 46)
            {
                if(pFileName.charAt(pFileName.length() -3) == 116)
                {
                    if(pFileName.charAt(pFileName.length() -2) == 120)
                    {
                        if(pFileName.charAt(pFileName.length() - 1) == 116)
                        {
                            isValid = true;
                        }
                    }
                }
            }
        }
        //If isValid is false, it will print that to user
        if(!isValid)
        {
            System.out.println("Please enter a .txt file");
        }
        return isValid;
    }




/*---------------------------------------------------------------------------*/
    //generateRoutes
    
    
    //SUBMODULE: generateRoutes
    //IMPORT: iWorld(World), allRoutes(DSALinkedList)
    //EXPORT: nil
    //ASSERTION: generate all routes for target(s) and store them in allRoutes.

    private static void generateRoutes(World iWorld, DSALinkedList allRoutes)
    {
        DSALinkedList targetList = iWorld.getTargetList();
        
        //wiping all routes and regenerate routes
        if(!allRoutes.isEmpty())
        {
            clearRoutes(allRoutes);
        }

        //For every target get its routes
        for(Object obj : targetList)
        {   
            //getting routes from start to target.
            String target = (String) obj;
            DSAHeapEntry[] route = SimulationMode.getRoutes(iWorld, target);
            allRoutes.insertLast(route);
        }
        
        System.out.println("Routes generated!");
    }

    

    //SUBMODULE: clearRoutes
    //IMPORT: allRoutes(DSALinkedList)
    //EXPORT: nil
    //ASSERTION: clear routes in allRoutes to insert new ones.
    
    private static void clearRoutes(DSALinkedList allRoutes)
    {   
        //clear allRoutes till it is empty
        while(!allRoutes.isEmpty())
        {
            allRoutes.removeLast();
        }
    }
        


/*---------------------------------------------------------------------------*/
    //displayRoutes



    //SUBMODULE: displayRoutes
    //IMPORT: iWorld(World), allRoutes(DSALinkedList)
    //EXPORT: nil
    //ASSERTION: print 10 routes for each target to terminal
    
    private static void displayRoutes(World iWorld, DSALinkedList allRoutes)
    {   
        DSALinkedList targetList = iWorld.getTargetList();
        String start = iWorld.getStart();
        
        //If user hasnt generated routes, dont display.
        if(!allRoutes.isEmpty())
        {
            //Iterators
            Iterator targetIter = targetList.iterator();
            Iterator routesIter = allRoutes.iterator();
            
            System.out.println("All Routes");
            //Iterating through allRoutes and print out data.
            while(routesIter.hasNext())
            {   
                //getting the curr route.
                DSAHeapEntry[] currRoute = (DSAHeapEntry[]) routesIter.next();
                
                /*displaying only max 10 routes to screen*/
                System.out.println("\nFor " + start + "->" + targetIter.next());
                int index = 0;
                while(index < 10 && index < currRoute.length)
                {   
                    //printing cost and path 
                    int cost = currRoute[index].getPriority();
                    Object path  = currRoute[index].getValue();
                    System.out.println(path + " " + cost);

                    index++;
                }
            }

            //Option to save routes
            optionToSaveRoutes(iWorld);
        }
        else
        {
            System.out.println("No routes have been generated!");
        }
    }


    

    //SUBMODULE: optionToSaveRoutes
    //IMPORT: iWorld(World)
    //EXPORT: nil
    //ASSERTION: get user input for file name, generate routes and 
    // write to the file.
 
    private static void optionToSaveRoutes(World iWorld)
    {   
        DSALinkedList targetList = iWorld.getTargetList();
        String start = iWorld.getStart();
        
        //printing menu
        System.out.println("Save routes?");
        System.out.println("1) Yes \n" + 
                           "2) No");
        
        //getting user input
        try
        {
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            if(choice == 1)
            {   
                String fileName = stringInput("File name: ");
                //Generating and writing routes to fileName 
                for(Object obj : targetList)
                {   
                    //generating routes
                    String target = (String) obj; 
                    DSAHeapEntry[] sortedRoutes = SimulationMode.getRoutes(iWorld
                                                  , target);
               
                    /* appending file*/
                    FileIO.writeToFile(sortedRoutes, fileName, start, target);
                }
            }
        }
        catch(InputMismatchException e)
        {
            System.out.println("Input integers only!");
        }
        catch(IllegalArgumentException e) /*thrown by stringInput*/
        {
            e.getMessage();
        }
    }



/*---------------------------------------------------------------------------*/
    //saveNetworks
    
    //SUBMODULE: saveNetwork
    //IMPORT: iWorld(World)
    //EXPORT: nil
    //ASSERTION: serialize world object.

    private static void saveNetwork(World iWorld)
    {
        DSAGraph gameGraph = iWorld.getGraph();
        
        //Dont serialize empty worlds.
        if(!gameGraph.isEmpty())
        {   
            FileIO.serializeToFile(iWorld);
        }
        else
        {
            System.out.println("World is empty!");
        }
    }



    
    //SUBMODULE: stringInput
    //IMPORT: label(String)
    //EXPORT: nil
    //ASSERTION: print label to terminal, get user input for a string.
     
    private static String stringInput(String label) throws 
                                                    IllegalArgumentException
    {
        Scanner sc = new Scanner(System.in);
        System.out.print(label);
        String input = sc.nextLine().replaceAll(" ", "");
        
        if(input.equals(""))
        {
            throw new IllegalArgumentException("Invalid string input!");
        }

        return input;
    }
    

}//End InteractiveMode

