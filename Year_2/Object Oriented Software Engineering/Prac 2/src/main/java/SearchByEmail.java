package edu.curtin.addressbook;

/**
 * Option to search in an AddressBook given an email.
 * 
 * @author Justin Liang(19821986)
 */
public class SearchByEmail implements Option
{      
    //Class Fields
    private AddressBook addressBook;


    //Constructor
    public SearchByEmail(AddressBook addressBook)
    {
        this.addressBook = addressBook;
    }



    //Interface Methods
    @Override
    public String doOption(String email)
    {   
        String displayString = "";
        //Searching for entry in addressBook
        Entry entryFound = addressBook.getEntryByEmail(email);
        if(entryFound != null)
        {   
            displayString += "\n-------------Entry Found-------------\n";
            displayString += entryFound.toString() + "\n";
        }
        else
        {
            displayString = "Entry cannot be found, please enter a valid email!";
        }

        return displayString;
    }
    
    
    @Override
    public boolean requiresText()
    {
        return true;
    }
}
