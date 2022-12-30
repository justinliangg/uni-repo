public class SecuritySystem implements SensorObserver
{
    private MotionSensor motionSensor;
    private HeatSensor heatSensor;
    private Alarm alarm;
    private EmailSystem emailSystem;
    private boolean armed;

    public SecuritySystem(MotionSensor motionSensor, HeatSensor heatSensor,
                          Alarm alarm, EmailSystem emailSystem)
    {
        this.motionSensor = motionSensor;
        this.heatSensor = heatSensor;  
        this.alarm = alarm;
        this.emailSystem = emailSystem;
        armed = false;
    }

    public void setArmed(boolean newArmed)
    {
        armed = newArmed;
        emailSystem.sendMessage("Armed: " + newArmed);
    }

    @Override
    public void sensorDetection(Sensor s)
    {
        if(armed)
        {
            alarm.ring();
            emailSystem.sendMessage("Sensor detection for " +
            s.toString());
        }
    }
}
