package edu.curtin.emergencysim.emergencies.fire;

import java.util.logging.Logger;
import edu.curtin.emergencysim.responders.*;
import edu.curtin.emergencysim.emergencies.Emergency;
import edu.curtin.emergencysim.EmergencyState;
import edu.curtin.emergencysim.emergencies.fire.states.Idle;
import java.util.List;


public class FireEmergency extends Emergency
{ 
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(FireEmergency.class.getName());

    //class fields
    private EmergencyState<FireEmergency> state;
    private int fireBurnTime;

    public FireEmergency(int startTime, String location, ResponderComm responderComm)
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

        //updating the state based on messages and time.
        state.update(this);

        state.checkCasualties(this);
        state.checkDamages(this);
      }  

      

    private void checkResponse(List<String> messages)
    {   
        for(String message : messages)
        {
            String[] parts = message.split(" ", 3);

            //checking that the message is for this current emergency.
            if(parts[0].equals("fire") && parts[2].equals(location))
            {    
                if(parts[1].equals("+")) //started responding.
                {
                    logger.info(()-> "responders arrived at fire " + super.location);
                    responding = true;
                }
                else if(parts[1].equals("-")) //responders left.
                {
                    logger.info(()-> "responders left fire " + super.location);
                    responding = false;
                }
            }
        }
    }


    //Getters
    public int getFireBurnTime()
    { 
        return fireBurnTime; 
    }
    

    //Mutators
    public void incrementFireBurnTime()
    {
        fireBurnTime++;
    }


    public void setState(EmergencyState<FireEmergency> newState)
    {
        this.state = newState;
    }

}