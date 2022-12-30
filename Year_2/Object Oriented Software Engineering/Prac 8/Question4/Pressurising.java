public class Pressurising implements AirlockState
{
    public Pressurising()
    {}

    @Override
    public void pressurise(Airlock context)
    {   
        System.out.println("Airlock already pressurising!");
    }

    @Override
    public void depressurise(Airlock context)
    {   
        double pressure = context.getSensor().getPressure();
        Pump pump = context.getPump();

        if(pressure < 5)
        {
            pump.stop();
            context.setState(new Depressurised());
        }
        else //need to depressurise again
        {
            pump.beginExtraction();
            context.setState(new Depressurising());
        }
    }

    @Override
    public void openInnerDoor(Airlock context)
    {
        System.out.println("Cannot open inner door while pressurising!");
    }

    @Override
    public void openOuterDoor(Airlock context)
    {
        System.out.println("Cannot open outer door while pressurising!");
    }

    @Override
    public void run(Airlock context)
    {   
        double pressure = context.getSensor().getPressure();
        Pump pump = context.getPump();

        if(pressure > 90) //pressure above 90 that means its pressurised.
        {
            pump.stop();
            context.setState(new Pressurised());
        }
    }
}