package edu.curtin.emergencysim.emergencies.fire.states;

import java.util.logging.Logger;
import edu.curtin.emergencysim.responders.*;
import edu.curtin.emergencysim.emergencies.fire.FireEmergency;
import edu.curtin.emergencysim.EmergencyState;

public class HighIntensity implements EmergencyState<FireEmergency>
{   
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(HighIntensity.class.getName());

    //constants
    private static final int FIRE_HIGH_TO_LOW_TIME = 5;
    private static final double FIRE_HIGH_CASUALTY_PROB = 0.3;
    private static final double FIRE_HIGH_DAMAGE_PROB = 0.4;

    //class fields
    private int responseTime;


    public HighIntensity()
    {
        responseTime = 0;
    }


    @Override
    public void update(FireEmergency context)
    {   
        ResponderComm responderComm = context.getResponderComm();
        String location = context.getLocation();
        boolean responding = context.getResponding();
        
        if(responseTime >= FIRE_HIGH_TO_LOW_TIME)
        {   
            logger.info(() -> "fire high to low at " + context.getLocation());
            context.setState(new LowIntensity());
            responderComm.send("fire low " + location);
        }
        else
        {
            if(responding) 
            {
                responseTime++;
            }
            else 
            {
                //have left or not responding so can reset responseTime.
                responseTime = 0;            
            }
        }
    }

    
    @Override
    public void checkCasualties(FireEmergency context)
    {
        ResponderComm responderComm = context.getResponderComm();
        String location = context.getLocation();

        if(context.eventHappened(FIRE_HIGH_CASUALTY_PROB))
        {   
            logger.info(()-> "fire casualty at " + context.getLocation());
            int casualties = context.getCasualties() + 1;
            context.setCasualties(casualties);
            responderComm.send("fire casualty " + casualties + " " + location);
        }

    }

    @Override
    public void checkDamages(FireEmergency context)
    {
        ResponderComm responderComm = context.getResponderComm();
        String location = context.getLocation();

        if(context.eventHappened(FIRE_HIGH_DAMAGE_PROB))
        {   
            logger.info(()-> "fire damage at " + context.getLocation());
            int damages = context.getDamages() + 1;
            context.setDamages(damages);
            responderComm.send("fire damage " + damages + " " + location);
        }
    }

}
