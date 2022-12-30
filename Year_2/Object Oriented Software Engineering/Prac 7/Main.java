public class Main
{
    public static void main(String[] args)
    {
        Hardware hw = new Hardware();
        SensorBundle sens = hw.getSensors();
        HeatSensor heatSensor = sens.getHeatSensor();
        MotionSensor motionSensor = sens.getMotionSensor();
        EmailSystem emailSystem = new EmailSystem();
        Alarm alarm = new Alarm();

        SecuritySystem securitySystem = new SecuritySystem(motionSensor, heatSensor,
                                                           alarm, emailSystem);

        motionSensor.addSensorObserver(securitySystem);
        heatSensor.addSensorObserver(securitySystem);
    }
}
