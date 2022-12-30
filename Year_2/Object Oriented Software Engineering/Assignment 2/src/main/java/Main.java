package edu.curtin.emergencysim;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;
import edu.curtin.emergencysim.emergencies.fire.FireEmergency;
import edu.curtin.emergencysim.emergencies.flood.FloodEmergency;
import edu.curtin.emergencysim.emergencies.chemical.ChemicalEmergency;
import edu.curtin.emergencysim.responders.*;
import edu.curtin.emergencysim.fileio.*;

public class Main
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args)
    {
        try
        {   
            if(args.length == 1)
            {   
                //Initialising responder comms
                ResponderComm responderComm = new ResponderCommImpl();

                //Getting data from the input file.
                FileProcessor fileProcessor = new FileProcessor();
                String fileName = args[0];
                logger.info("Reading input file");
                List<String> emergencies = fileProcessor.read(fileName);
                
                //Adding the approriate observers for the simulator.
                logger.info("Creating the observers of the simulation");
                List<EmergencyObserver> observers = createObservers(emergencies, responderComm);
                SimulatorApp emergencyApp = new SimulatorApp();
                emergencyApp.addObservers(observers);
                
                //start simulation.
                logger.info("Starting simulation");
                emergencyApp.startSimulation(responderComm);
            }
            else
            {   
                logger.info("Missing filename in command line argument");
                System.out.println("Error! Missing input filename in command " + 
                                                               "line argument");
            }
        }
        catch(FileNotFoundException e)
        { 
            logger.log(Level.SEVERE, "Error! file cannot be found.", e); 
            System.out.println("Error! " + e.getMessage());
        }
        catch(IOException e2)
        {
            logger.log(Level.SEVERE, "Error with reading file", e2);   
            System.out.println("Error! " + e2.getMessage());
        }
        catch(FileFormatException e3)
        {   
            logger.log(Level.SEVERE, "Error inside input file", e3);    
            System.out.println("Error! " + e3.getMessage());
        }

    }


    private static List<EmergencyObserver> createObservers(List<String> emergencies,
                                                        ResponderComm responderComm)
    {   
        List<EmergencyObserver> observers = new ArrayList<>();

        //creating EmergencyObservers from emergencies.
        for(String emergency : emergencies)
        {   
            EmergencyObserver emergencyObserver = null;
    
            String[] parts = emergency.split(" ", 3);
            int startTime = Integer.parseInt(parts[0]);
            String emergencyType = parts[1];
            String location = parts[2];

            //determining which concrete class to create based on emergencyType.
            if(emergencyType.equals("fire"))
            {
                emergencyObserver = new FireEmergency(startTime, location, 
                                                        responderComm);
            }
            else if(emergencyType.equals("flood"))
            {
                emergencyObserver = new FloodEmergency(startTime, location,
                                                        responderComm);
            }
            else if(emergencyType.equals("chemical"))
            {
                emergencyObserver = new ChemicalEmergency(startTime, location,
                                                            responderComm);
            }

            observers.add(emergencyObserver);
        }

        return observers;
    }
}
