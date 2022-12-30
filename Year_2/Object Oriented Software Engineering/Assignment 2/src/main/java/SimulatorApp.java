package edu.curtin.emergencysim;

import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;
import edu.curtin.emergencysim.responders.*;

public class SimulatorApp
{   
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(SimulatorApp.class.getName());

    //class fields.
    private List<EmergencyObserver> observers;

    public SimulatorApp()
    {   
        observers = new ArrayList<>();
    }


    public void startSimulation(ResponderComm responderComm)
    {   
        try
        {
            boolean done = false;
            int time = 0;
            while(!done)
            {    
                final int t = time;
                logger.info(() -> "Time: " + t);

                List<String> messages = responderComm.poll();

                if(checkSimulationEnd(messages))
                {   
                    logger.info("simulation ended");
                    done = true;
                }
                else //continue with simulation 
                {   
                    //letting observers know the change in time and 
                    //any messages from responders.
                    notifyObservers(time, messages);
                }

                time++;
                Thread.sleep(1000);
            }
        }
        catch(InterruptedException e)
        {}
    }

    public void addObservers(List<EmergencyObserver> observers)
    {
        this.observers = observers;
    }
    
    
    public void notifyObservers(int time, List<String> messages)
    {   
        for(EmergencyObserver obs : observers)
        {
            obs.update(time, messages);
        }
    }


    private boolean checkSimulationEnd(List<String> messages)
    {   
        boolean end = false;
        if(!messages.isEmpty())
        {
            if(messages.get(0).equals("end"))
            {
                end = true;
            }
        }

        return end;
    }


}
