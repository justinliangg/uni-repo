public class Airlock extends TimerTask
{   
    //Class fields
    private double pressure;
    private AirlockState state;
    private Door innerDoor;
    private Door outerDoor;
    private Pump pump;
    private Sensor sensor;
    private Timer timer;

    public Airlock(Door innerDoor, Door outerDoor, Pump pump, Sensor sensor)
    {
        this.pressure = 101.3;
        this.state = new Pressurised();
        this.innerDoor = innerDoor;
        innerDoor.open(); //open inner door first.
        this.outerDoor = outerDoor;
        this.pump = pump;
        this.sensor = sensor;

        //timer function to execute run() every second.
        timer = new Timer();
        start();
    }

    public void pressurise() 
    {   
        state.pressurise(this);
    }


    public void depressurise()
    {   
        state.depressurise(this);
    }
    

    public void openInnerDoor()
    {
        state.openInnerDoor(this);
    }


    public void openOuterDoor()
    {
        state.openOuterDoor(this);
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
        //called every second to check if airlock has reached approriate 
        //pressure levels.
        state.run(this);
    }


    //GETTERS
    public Sensor getSensor()
    {
        return sensor;
    }

    public Pump getPump()
    {
        return pump;
    }

    public Door getInnerDoor()
    {
        return innerDoor;
    }

    public Door getOuterDoor()
    {
        return outerDoor;
    }


    //SETTERS
    public void setState(AirlockState state)
    {
        this.state = state;
    }

    
    
}
    
