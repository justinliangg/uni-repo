public class Depressurising implements AirlockState
{
    public Depressurising()
    {}

    @Override
    public void pressurise(Airlock context)
    {   
        double pressure = context.getSensor().getPressure();
        Pump pump = context.getPump();

        if(pressure > 90)
        {
            pump.stop();
            context.setState(new Pressurised());
        }
        else //need to repressurise again
        {
            pump.return();
            context.setState(new Pressurising());
        }
    }

    @Override
    public void depressurise(Airlock context)
    {   
        System.out.println("Airlock depressurising already!");
    }

    @Override
    public void openInnerDoor(Airlock context)
    {
        System.out.println("Cannot open inner door while depressurising!");
    }

    @Override
    public void openOuterDoor(Airlock context)
    {
        System.out.println("Cannot open outer door while depressurising!");
    }

    @Override
    public void run(Airlock context)
    {   
        double pressure = context.getSensor().getPressure();
        Pump pump = context.getPump();

        if(pressure < 5) //pressure below 5 that means its depressurised.
        {
            pump.stop();
            context.setState(new Depressurised());
        }
    }
}