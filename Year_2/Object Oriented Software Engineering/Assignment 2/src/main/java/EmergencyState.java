package edu.curtin.emergencysim;

import edu.curtin.emergencysim.emergencies.Emergency;

public interface EmergencyState<T extends Emergency>
{
    public void update(T context);
    public void checkCasualties(T context);
    public void checkDamages(T context);
}