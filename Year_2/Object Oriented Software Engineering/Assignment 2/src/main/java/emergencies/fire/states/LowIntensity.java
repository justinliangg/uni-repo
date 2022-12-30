package edu.curtin.emergencysim.emergencies.fire.states;

import java.util.logging.Logger;
import edu.curtin.emergencysim.responders.*;
import edu.curtin.emergencysim.emergencies.fire.FireEmergency;
import edu.curtin.emergencysim.EmergencyState;

public class LowIntensity implements EmergencyState<FireEmergency>
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(LowIntensity.class.getName());

    //constants
    private static final int FIRE_LOW_TO_HIGH_TIME = 5;
    private static final int FIRE_LOW_CLEANUP_TIME = 2;
    private static final double FIRE_LOW_CASUALTY_PROB = 0.1;
    private static final double FIRE_LOW_DAMAGE_PROB = 0.1;

    //class fields
    private int responseTime;

    public LowIntensity()
    {
        responseTime = 0;
    }


    @Override
    public void update(FireEmergency context)
    {   
        ResponderComm responderComm = context.getResponderComm();
        String location = context.getLocation();
        int fireBurnTime = context.getFireBurnTime();
        boolean responding = context.getResponding();

        if(responseTime >= FIRE_LOW_CLEANUP_TIME) //fire ended
        {   
            logger.info(() -> "fire ended at " + context.getLocation());
            context.setState(new Idle());
            responderComm.send("fire end " + location);
        }
        else 
        {   
            if(responding)
            {
                responseTime++;
            }
            else
            {   
                //can only transition to high when no responders.
                //+ 1 to check ahead of time as transition should 
                //happen within that second not after.
                if(fireBurnTime + 1 >= FIRE_LOW_TO_HIGH_TIME)
                {   
                    logger.info(() -> "fire low to high at " + context.getLocation());
                    context.setState(new HighIntensity());
                    responderComm.send("fire high " + location);
                }
                //only increment burn time when not responding.
                context.incrementFireBurnTime();

                //reset responseTime because responders not responding.
                responseTime = 0;
            }
        }
    }


    @Override
    public void checkCasualties(FireEmergency context)
    {
        ResponderComm responderComm = context.getResponderComm();
        String location = context.getLocation();

        if(context.eventHappened(FIRE_LOW_CASUALTY_PROB))
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

        if(context.eventHappened(FIRE_LOW_DAMAGE_PROB))
        {   
            logger.info(()-> "fire damage at " + context.getLocation());
            int damages = context.getDamages() + 1;
            context.setDamages(damages);
            responderComm.send("fire damage " + damages + " " + location);
        }
    }
    
}
