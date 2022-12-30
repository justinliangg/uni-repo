package edu.curtin.addressbook.controller;

import edu.curtin.addressbook.model.*;

/**
 * Option to search in an AddressBook given an name
 * 
 * @author Justin Liang(19821986)
 */
public class SearchByName implements Option
{      
    //Class Fields
    private AddressBook addressBook;


    //Constructor
    public SearchByName(AddressBook addressBook)
    {
        this.addressBook = addressBook;
    }



    //Interface Methods
    @Override
    public String doOption(String name)
    {   
        String displayString = "";

        //Searching for entry in addressBook
        Entry entryFound = addressBook.getEntryByName(name);
        if(entryFound != null)
        {   
            displayString += "\n-------------Entry Found------------- \n";
            displayString += entryFound.toString() + "\n";
        }
        else
        {
            displayString = "Entry cannot be found, please enter a valid name!\n";
            
        }

        return displayString;
    }


    @Override 
    public boolean requiresText()
    {
        return true;
    }
    
}
