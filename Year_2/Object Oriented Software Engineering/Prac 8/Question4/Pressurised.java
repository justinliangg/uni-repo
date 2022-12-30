public class Pressurised implements AirlockState
{
    public Pressurised()
    {}

    @Override
    public void pressurise(Airlock context)
    {   
        System.out.println("Airlock already pressurised!");
    }

    @Override
    public void depressurise(Airlock context)
    {   
        Door innerDoor = context.getInnerDoor();
        Door outerDoor = context.getOuterDoor();

        if(!innerDoor.isOpen() && !outerDoor.isOpen())
        {   
            Pump pump = context.getPump();
            pump.beginExtraction();

            context.setState(new Depressurising());
        }
    }

    @Override
    public void openInnerDoor(Airlock context)
    {
        Door innerDoor = context.getInnerDoor();
        innerDoor.open();
    }

    @Override
    public void openOuterDoor(Airlock context)
    {
        System.out.println("Cannot open outer door while pressurised");
    }

    @Override
    public void run(Airlock context)
    {
        //do nothing
    }
}