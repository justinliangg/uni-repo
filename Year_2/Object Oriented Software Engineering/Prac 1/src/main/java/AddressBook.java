package edu.curtin.addressbook;

import java.util.*;

/**
 * Contains all the address book entries.
 * 
 * @author Justin Liang(19821986)
 */
public class AddressBook
{   
    //Class Fields
    private Map<String, Entry> nameToEntries;
    private Map<String, Entry> emailToEntries;



    //Constructor
    public AddressBook()
    {
        nameToEntries = new HashMap<>();
        emailToEntries = new HashMap<>();
    }


    
    //Mutator
    public void addEntry(Entry newEntry)
    {
        nameToEntries.put(newEntry.getName(), newEntry);
        //storing all email addresses as key, mapping to the same newEntry.
        for(String email : newEntry.getEmailAddresses())
        {
            emailToEntries.put(email, newEntry);
        }
    }

    

    //Accessors
    public Entry getEntryByName(String name)
    {
        return nameToEntries.get(name);
    }

    
    public Entry getEntryByEmail(String emailAddress)
    {   
        return emailToEntries.get(emailAddress);
    }
    
}
