public interface AirlockState
{
    public void pressurise(Airlock context);
    public void depressurise(Airlock context);
    public void openInnerDoor(Airlock context);
    public void openOuterDoor(Airlock context);
    public void run(Airlock context);
}