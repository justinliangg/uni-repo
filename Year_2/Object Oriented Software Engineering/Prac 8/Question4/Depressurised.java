public class Depressurised implements AirlockState
{
    public Depressurised()
    {}

    @Override
    public void pressurise(Airlock context)
    {   
        Door innerDoor = context.getInnerDoor();
        Door outerDoor = context.getOuterDoor();

        if(!innerDoor.isOpen() && !outerDoor.isOpen())
        {   
            //start pump
            Pump pump = context.getPump();  
            pump.return();
            
            //changing state
            context.setState(new Pressurising());
        }
    }

    @Override
    public void depressurise(Airlock context)
    {
        System.out.println("Airlock already depressurised");
    }

    @Override
    public void openInnerDoor(Airlock context)
    {
        System.out.println("Cannot open inner door while depressurised");
    }

    @Override
    public void openOuterDoor(Airlock context)
    {
        Door outerDoor = context.getOuterDoor();
        outerDoor.open();
    }

    @Override
    public void run(Airlock context)
    {
        //do nothing
    }
}