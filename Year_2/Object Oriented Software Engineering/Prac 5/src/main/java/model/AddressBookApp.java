package edu.curtin.addressbook.model;

import java.io.*;
import java.util.*;

/**
 * A simple address book application.
 * @author Dave and Justin Liang(19821986)
 */
public class AddressBookApp 
{   
    //Class Fields
    private Map<Integer, Option> options;
    
    //Constructor
    public AddressBookApp()
    {
        options = new HashMap<>();
    }

    
    /**
     * Read the address book file, containing all the names and email addresses.
     *
     * @param fileName The name of the address book file.
     * @return A new AddressBook object containing all the information.
     * @throws IOException If the file cannot be read.
     */
    public AddressBook readAddressBook(String fileName) throws IOException
    {
        AddressBook addressBook = new AddressBook();
        
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line = reader.readLine();
            while(line != null)
            {
                String[] parts = line.split(":");
                
                String name = parts[0];

                //Collecting all email addresses and adding into list
                //start from index 1 to skip name
                List<String> emailAddresses = new LinkedList<>();
                for(int i = 1; i < parts.length; i++)
                {
                    emailAddresses.add(parts[i]);
                }

                //Adding to addressBook
                Entry newEntry = new Entry(name, emailAddresses);
                addressBook.addEntry(newEntry);

                line = reader.readLine();
            }
        }
        
        return addressBook;
    }

    /**
     * Allow additional options to be added to AddressBookApp
     *
     * @param label //number for option
     * @param newOption //option in addressBookApp
    */
    public void addOption(int label, Option newOption)
    {
        options.put(label, newOption);
    }


    public Map<Integer, Option> getOptions()
    {   
        Map<Integer, Option> mapToReturn = null;
        if(!options.isEmpty()) //guard 
        {
            mapToReturn = new HashMap<>(); 
            mapToReturn.putAll(options);
        }

        return mapToReturn;

    }


}
