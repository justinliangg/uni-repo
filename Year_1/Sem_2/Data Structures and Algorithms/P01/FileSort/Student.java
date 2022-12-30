public class Student 
{   
    //Class fields
    private int id;
    private String name;




    //Parameter Constructor
    public Student(int pId, String pName)
    {   
        //Validating Id and Name
        if(pId > 0 && pName != null && !pName.equals(""))
        {
            id = pId;
            name = pName;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }




    //Getters
    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }




    //Setters
    public void setId(int pId)
    {
        if(pId > 0)
        {
            id = pId;
        }
    }

    public void setName(String pName)
    {
        if(pName != null && !pName.equals(""))
        {
            name = pName;
        }
    }
    



    //SUBMODULE

    //SUBMODULE: writeToFileString
    //IMPORT: nil
    //EXPORT: writeString(String)
    //ASSERTION: returns a string used for writing to file.      
    public String writeToFileString()
    {
        String writeString = id + "," + name;

        return writeString;
    }

        
}
