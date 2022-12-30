package edu.curtin.emergencysim.emergencies.flood.states;

import java.util.logging.Logger;
import edu.curtin.emergencysim.responders.ResponderComm;
import edu.curtin.emergencysim.EmergencyState;
import edu.curtin.emergencysim.emergencies.flood.FloodEmergency;

public class Idle implements EmergencyState<FloodEmergency>
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(Flooding.class.getName());

    public Idle() 
    {}

    @Override
    public void update(FloodEmergency context)
    {
        ResponderComm responderComm = context.getResponderComm();
        String location = context.getLocation();
        
        if(context.getCurrentTime() == context.getStartTime())
        {   
            logger.info(()-> "flooding started at " + context.getLocation());
            context.setState(new Flooding());
            responderComm.send("flood start " + location);
        }
    }


    @Override
    public void checkCasualties(FloodEmergency context)
    {   
        //do nothing
    }
    

    @Override
    public void checkDamages(FloodEmergency context)
    {
        //do nothing
    }
}