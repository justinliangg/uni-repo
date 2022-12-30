public class Plant extends Garden
{
    //Class fields
    String name;
    int quantity;
    int year;

    //Constructor
    public Plant(String name, int quantity, int year, String gardenName, String suburb)
    {
        this.name = name;
        this.quantity = quantity;
        this.year = year;
        super.gardenName = gardenName;        
        super.suburb = suburb;           
    }

    //Getters
    public String getName()
    {
        return name;
    }
    
    public int getQuantity()
    {
        return quantity;
    }

    public int getYear()
    {
        return year;
    }
}