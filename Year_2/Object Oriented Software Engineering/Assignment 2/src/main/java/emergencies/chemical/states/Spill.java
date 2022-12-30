package edu.curtin.emergencysim.emergencies.chemical.states;

import java.util.logging.Logger;
import edu.curtin.emergencysim.responders.ResponderComm;
import edu.curtin.emergencysim.emergencies.chemical.ChemicalEmergency;
import edu.curtin.emergencysim.EmergencyState;

public class Spill implements EmergencyState<ChemicalEmergency>
{ 
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(Spill.class.getName());

    private static final int CHEM_CLEANUP_TIME = 10;
    private static final double CHEM_DAMAGE_PROB = 0.3;
    private static final double CHEM_CASUALTY_PROB = 0.3;
    
    //class fields
    private int responseTime;

    public Spill() 
    {   
        responseTime = 0;
    }


    @Override
    public void update(ChemicalEmergency context)
    {   
        ResponderComm responderComm = context.getResponderComm();  
        boolean responding = context.getResponding();
        String location = context.getLocation();

        if(responseTime >= CHEM_CLEANUP_TIME)
        {   
            logger.info(() -> "chem spill end at " + context.getLocation());
            context.setState(new Idle());
            responderComm.send("chemical end " + location);
        }
        else
        {   
            if(responding)
            {
                responseTime++;
            }
            else
            {   
                //reset response time as not responding anymore.
                responseTime = 0;
            }
        }
    }

    

    @Override
    public void checkCasualties(ChemicalEmergency context)
    {   
        ResponderComm responderComm = context.getResponderComm();
        String location = context.getLocation();

        if(context.eventHappened(CHEM_CASUALTY_PROB))
        {
            logger.info(() -> "chem casualty at " + context.getLocation());
            int casualties = context.getCasualties() + 1;
            context.setCasualties(casualties);
            responderComm.send("chemical casualty " + casualties + " " + location);
        }

    }
    

    @Override
    public void checkDamages(ChemicalEmergency context)
    {
        ResponderComm responderComm = context.getResponderComm();
        String location = context.getLocation();

        if(context.eventHappened(CHEM_DAMAGE_PROB))
        {
            logger.info(() -> "chem contam at " + context.getLocation());
            int damages = context.getDamages() + 1;
            context.setDamages(damages);
            responderComm.send("chemical contam " + damages + " " + location);
        }
    }
}