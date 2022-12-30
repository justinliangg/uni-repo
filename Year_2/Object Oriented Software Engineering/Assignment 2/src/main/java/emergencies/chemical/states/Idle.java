package edu.curtin.emergencysim.emergencies.chemical.states;

import java.util.logging.Logger;
import edu.curtin.emergencysim.responders.ResponderComm;
import edu.curtin.emergencysim.emergencies.chemical.ChemicalEmergency;
import edu.curtin.emergencysim.EmergencyState;

public class Idle implements EmergencyState<ChemicalEmergency>
{   
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(Idle.class.getName());

    public Idle() 
    {}


    @Override
    public void update(ChemicalEmergency context)
    {   
        ResponderComm responderComm = context.getResponderComm();
        String location = context.getLocation();
        
        if(context.getCurrentTime() == context.getStartTime())
        {    
            logger.info(() -> "chem spill start at " + context.getLocation());
            context.setState(new Spill());
            responderComm.send("chemical start " + location);
        }
    }

    

    @Override
    public void checkCasualties(ChemicalEmergency context)
    {   
        //do nothing
    }
    

    @Override
    public void checkDamages(ChemicalEmergency context)
    {
        //do nothing
    }
}