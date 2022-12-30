package edu.curtin.spaceprobe;

public class Battery implements Resource<Double>
{
    private double charge = 100.0;

    public void recharge()
    {
        charge = 100.0;
    }

    @Override
    public void useUp(Double amount)
    {
        charge -= amount.doubleValue();
    }

    @Override
    public Double getRemaining()
    {
        return Double.valueOf(charge);
    }

    @Override
    public long getTime(long elapsedTime)
    {
        return (long)((double)elapsedTime / (100.0 - charge) * charge);
    }
}
