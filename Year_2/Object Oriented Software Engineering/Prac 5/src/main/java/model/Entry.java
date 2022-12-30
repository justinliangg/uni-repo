package edu.curtin.addressbook.model;

import java.util.*;
        
/**
 * Represents a single address book entry.
 * 
 * @author Justin Liang(19821986)
 */
public class Entry 
{
    //Class Fields
    private String name;
    private List <String> emailAddresses;


    //Constructor
    public Entry(String name, List<String> emailAddresses)
    {
        this.name = name;
        this.emailAddresses = emailAddresses;
    }



    //Accessors
    public String getName()
    {
        return name;
    }


    public List<String> getEmailAddresses()
    {
        return emailAddresses;
    }

    

    //Methods
    @Override //had to override to remove PMD warnings.
    public String toString() 
    {   
        String displayString = "Name: " + this.name;
        displayString = emailToString(this.emailAddresses, displayString);

        return displayString;
    }
    
    //helper method for toString()
    private static String emailToString(List<String> emailAddresses,
                                       String displayString)
    {   
        if(emailAddresses.size() > 1)
        {
            displayString += "\nEmail Addresses: ";
            int index = 0;
            for(String address: emailAddresses)
            {   
                //skip adding comma for last address
                if(index == emailAddresses.size() - 1)
                {
                    displayString += address + "\n";
                }
                else
                {
                    displayString += address + ", ";
                }
                index += 1;
            }
        }
        else if(emailAddresses.size() == 1)
        {
            displayString += "\nEmail Address: " + emailAddresses.get(0) + "\n";
        }
        else
        {
            displayString += ("\nEmail Addresses: NIL");
        }

        return displayString;
    }

}

