package edu.curtin.emergencysim.emergencies.flood;

import java.util.logging.Logger;
import edu.curtin.emergencysim.responders.ResponderComm;
import edu.curtin.emergencysim.EmergencyState;
import edu.curtin.emergencysim.emergencies.Emergency;
import edu.curtin.emergencysim.emergencies.flood.states.Idle;

import java.util.List;


public class FloodEmergency extends Emergency
{ 
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(FloodEmergency.class.getName());

    //class fields
    private EmergencyState<FloodEmergency> state;
 
    public FloodEmergency(int startTime, String location, ResponderComm responderComm)
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
        
        //updating state based on time and messages
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
            if(parts[0].equals("flood") && parts[2].equals(location))
            {    
                if(parts[1].equals("+")) //started responding.
                {   
                    logger.info(() -> "responders arrived at flood " + super.location);
                    responding = true;
                }
                else if(parts[1].equals("-")) //responders left.
                {   
                    logger.info(() ->"responders left flood  " + super.location);
                    responding = false;
                }
            }
        }
    }


    //setters
    public void setState(EmergencyState<FloodEmergency> newState)
    {
        this.state = newState;
    }
}