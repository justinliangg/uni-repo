package edu.curtin.emergencysim.emergencies.fire.states;

import java.util.logging.Logger;
import edu.curtin.emergencysim.responders.*;
import edu.curtin.emergencysim.emergencies.fire.FireEmergency;
import edu.curtin.emergencysim.EmergencyState;

public class Idle implements EmergencyState<FireEmergency>
{   
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(Idle.class.getName());

    public Idle()
    {}
    
    @Override
    public void update(FireEmergency context)
    {   
        ResponderComm responderComm = context.getResponderComm();
        String location = context.getLocation();
        
        if(context.getCurrentTime() == context.getStartTime())
        {   
            logger.info(() -> "fire start at " + context.getLocation());
            context.setState(new LowIntensity());
            responderComm.send("fire start " + location);
        }
    }

    @Override 
    public void checkCasualties(FireEmergency context)
    {
        //do nothing
    }

    @Override
    public void checkDamages(FireEmergency context)
    {
        //do nothing
    }
}