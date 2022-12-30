package edu.curtin.emergencysim;
import java.util.List;


public interface EmergencyObserver
{  
    public void update(int time, List<String> messages);
}
