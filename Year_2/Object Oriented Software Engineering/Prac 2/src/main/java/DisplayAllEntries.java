package edu.curtin.addressbook;

import java.util.*;

/**
 * Option to display all entries in a given AddressBook.
 * 
 * @author Justin Liang(19821986)
 */
public class DisplayAllEntries implements Option
{   
    //Class Field
    private AddressBook addressBook;
    

    //Constructor
    public DisplayAllEntries(AddressBook addressBook)
    {
        this.addressBook = addressBook;
    }



    //Interface Methods
    @Override  
    public String doOption(String s)
    {
        String displayString = "";
        Map<String, Entry> entries = addressBook.getAllEntries();
        
        displayString += "\n------------ Displaying All Entries ------------\n";
        //Iterating over all entries in addressBook 
        //Appending to displayString
        int entryNum = 1;
        for(Entry entry : entries.values())
        {   
            displayString += "(" + entryNum + ")\n" + entry.toString() + "\n";
            entryNum += 1;
        }

        return displayString;
    }


    @Override
    public boolean requiresText()
    {
        return false;
    }
}
