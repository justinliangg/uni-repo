package edu.curtin.emergencysim.emergencies.flood.states;

import java.util.logging.Logger;
import edu.curtin.emergencysim.responders.ResponderComm;
import edu.curtin.emergencysim.EmergencyState;
import edu.curtin.emergencysim.emergencies.flood.FloodEmergency;

public class Flooding implements EmergencyState<FloodEmergency>
{   
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(Flooding.class.getName());

    private static final int FLOOD_END_TIME = 10;
    private static final double FLOOD_DAMAGE_PROB = 0.3;
    private static final double FLOOD_CASUALTY_PROB = 0.3;

    private int floodTime;

    public Flooding() 
    {
        floodTime = 0;
    }

    @Override
    public void update(FloodEmergency context)
    {   
        ResponderComm responderComm = context.getResponderComm();
        String location = context.getLocation();

        if(floodTime >= FLOOD_END_TIME)
        {   
            logger.info(()-> "flood ended at " + context.getLocation());
            context.setState(new Idle());
            responderComm.send("flood end " + location);
        }

        floodTime++;
    }
    

    @Override
    public void checkCasualties(FloodEmergency context)
    {   
        ResponderComm responderComm = context.getResponderComm();
        String location = context.getLocation();

        boolean responding = context.getResponding();
        if(!responding) //only have chance of casualties only if not responding.
        {
            if(context.eventHappened(FLOOD_CASUALTY_PROB))
            {   
                logger.info(()-> "flood casualty at " + context.getLocation());
                int casualties = context.getCasualties() + 1;
                context.setCasualties(casualties);
                responderComm.send("flood casualty " + casualties + " " + location);
            }
        }
    }
    

    @Override
    public void checkDamages(FloodEmergency context)
    {
        ResponderComm responderComm = context.getResponderComm();
        String location = context.getLocation();

        if(context.eventHappened(FLOOD_DAMAGE_PROB))
        {
            logger.info(()-> "flood damage at " + context.getLocation());
            int damages = context.getDamages() + 1;
            context.setDamages(damages);
            responderComm.send("flood damage " + damages + " " + location);
        }
    }
}