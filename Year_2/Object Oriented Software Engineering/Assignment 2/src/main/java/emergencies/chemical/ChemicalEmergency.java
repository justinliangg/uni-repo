package edu.curtin.emergencysim.emergencies.chemical;

import java.util.List;
import java.util.logging.Logger;
import edu.curtin.emergencysim.responders.*;
import edu.curtin.emergencysim.EmergencyState;
import edu.curtin.emergencysim.emergencies.chemical.states.Idle;
import edu.curtin.emergencysim.emergencies.Emergency;

public class ChemicalEmergency extends Emergency
{ 
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(ChemicalEmergency.class.getName());

    //class fields
    private EmergencyState<ChemicalEmergency> state;
    
    public ChemicalEmergency(int startTime, String location, ResponderComm responderComm)
    {   
        super(startTime, location, responderComm);
        state = new Idle();
    }


    @Override
    public void update(int time, List<String> messages)
    {  
        currentTime = time;
        
        //checking if any response from responders for current emergency.
        checkResponse(messages);
        
        state.update(this);

        //checking if any casualties.
        state.checkCasualties(this);
        state.checkDamages(this);
    }  



    private void checkResponse(List<String> messages)
    {   
        for(String message : messages)
        {
            String[] parts = message.split(" ", 3);

            //checking that the message is for this current emergency.
            if(parts[0].equals("chemical") && parts[2].equals(location))
            {    
                if(parts[1].equals("+")) //started responding.
                {   
                    logger.info(()-> "responders arrived at chem " + super.location);
                    responding = true;
                }
                else if(parts[1].equals("-")) //responders left.
                {
                    logger.info(()-> "responders left chem " + super.location);
                    responding = false;
                }
            }
        }
    }

    
    //setters
    public void setState(EmergencyState<ChemicalEmergency> newState)
    {
        this.state = newState;
    }
}