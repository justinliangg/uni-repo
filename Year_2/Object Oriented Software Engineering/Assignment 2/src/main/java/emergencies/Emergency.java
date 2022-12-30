package edu.curtin.emergencysim.emergencies;

import edu.curtin.emergencysim.EmergencyObserver;
import edu.curtin.emergencysim.responders.ResponderComm;
import java.util.List;

public abstract class Emergency implements EmergencyObserver
{
    protected int startTime;
    protected String location;
    protected boolean responding;
    protected ResponderComm responderComm;
    protected int currentTime;
    protected int casualties;
    protected int damages;

    public Emergency(int startTime, String location, ResponderComm responderComm)
    {
        this.startTime = startTime;
        this.location = location;
        this.responderComm = responderComm;
        responding = false;
        currentTime = 0;
        casualties = 0;
        damages = 0;
    }
    
    @Override
    public abstract void update(int time, List<String> messages);

    public boolean getResponding() { return responding; }
    public int getStartTime(){ return startTime; }
    public String getLocation(){ return location; }
    public int getCurrentTime(){ return currentTime; }
    public ResponderComm getResponderComm(){ return responderComm; }
    public int getCasualties(){ return casualties; }
    public int getDamages(){ return damages; }

    
    public void setCasualties(int newAmount)
    {
        casualties = newAmount;
    }

    public void setDamages(int newAmount)
    {
        damages = newAmount;
    }


    /* uses random number generator to determine if an event has happened
    *  given a probability of success.
    */
    public boolean eventHappened(double successProbability)
    {
        boolean success = false;

        double num = Math.random();
        if(num <= successProbability) //if less than or equal that means success.
        {
            success = true;;
        }

        return success;
    }
}
