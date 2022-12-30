public class Airlock extends TimerTask
{   
    //Constants for the different states.
    private static final int DEPRESSURISED = 0;
    private static final int PRESSURISED = 1;
    private static final int DEPRESSURISING = 2;
    private static final int PRESSURISING = 3;

    //Class fields
    private double pressure;
    private int state;
    private Door innerDoor;
    private Door outerDoor;
    private Pump pump;
    private Sensor sensor;
    private Timer timer;

    public Airlock(Door innerDoor, Door outerDoor, Pump pump, Sensor sensor)
    {
        this.pressure = 101.3;
        this.state = PRESSURISED;
        this.innerDoor = innerDoor;
        innerDoor.open(); //open inner door first.
        this.outerDoor = outerDoor;
        this.pump = pump;
        this.sensor = sensor;

        //timer function
        timer = new Timer();
        start();
    }

    public void pressurise() 
    {   
        switch(state)
        {
            case DEPRESSURISED:
                if(!innerDoor.isOpen() && !outerDoor.isOpen())
                {
                    pump.return();
                    state = PRESSURISING;
                }
                break;
            
            case DEPRESSURISING:
                if(pressure > 90) //still in pressurised state.
                {
                    pump.stop();
                    state = PRESSURISED;
                }
                else //need to repressurise again
                {
                    pump.return();
                    state = PRESSURISING;
                }
                break;        
        }
    }


    public void depressurise()
    {   
        switch(state)
        {
            case PRESSURISED:
                if(!innerDoor.isOpen() && !outerDoor.isOpen())
                {
                    pump.beginExtraction();
                    state = DEPRESSURISING;
                }
                break;
            
            case PRESSURISING:
                if(pressure < 5) //still in depressurised state.
                {
                    pump.stop();
                    state = DEPRESSURISED;
                }
                else //need to depressurise again
                {
                    pump.beginExtraction();
                    state = DEPRESSURISING;
                }
                break; 
        }
    }
    

    public void openInnerDoor()
    {
        if(state == PRESSURISED)
        {
            innerDoor.open();
        }
    }


    public void openOuterDoor()
    {
        if(state == DEPRESSURISED)
        {
            outerDoor.open();
        }
    }


    public void updatePressure(double pressure)
    {
        this.pressure = pressure;
    }


    @Override 
    public void start()
    { 
        TimerTask newTask = new TimerScheduleFixedRateDelay();
        timer.scheduleAtFixedRate(newTask, 0, 1000);
    }


    @Override
    public void run()
    {
        if(state == DEPRESSURISING)
        {
            if(pressure < 5)
            {
                pump.stop();
                state = DEPRESSURISED;
            }
        }  
        else if(state == PRESSURISING)
        {
            if(pressure > 90)
            {
                pump.stop();
                state = PRESSURISED;
            }
        }
    }
    
}
    
