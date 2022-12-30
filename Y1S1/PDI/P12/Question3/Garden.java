public class Garden
{
    //Class Fields
    String gardenName;
    String suburb;

    //Setters
    public void setGardenName(String pName)
    {
        gardenName = pName;
    }

    public void setSuburb(String pSuburb)
    {
        suburb = pSuburb;
    }

    //Getters
    public String getGardenName()
    {
        return gardenName;
    }
    public String getSuburb()
    {
        return suburb;
    }
    
    //equals
    public boolean equals(Object pObject)
    {
        boolean isEquals = false;
        Garden pGardenObject;

        if(pObject instanceof Garden)
        {
            pGardenObject = (Garden) pObject;
            if(pGardenObject.getGardenName().equals(gardenName))
            {
                if(pGardenObject.getSuburb().equals(suburb))
                {
                    isEquals = true;
                }
            }
        }

        return isEquals;
    }
}