package edu.curtin.addressbook;

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

    
    public void display()
    {
        System.out.println();
        System.out.println("-------------Entry Found-------------");

        //Displaying details
        System.out.println("Name: " + this.name);
        displayEmails(this.emailAddresses);

    }

    private static void displayEmails(List<String> emailAddresses)
    {   
        if(emailAddresses.size() > 1) 
        {
            System.out.print("Email Addresses: ");
            int index = 0;
            for(String address: emailAddresses)
            {   
                //skip adding comma for last address
                if(index == emailAddresses.size() - 1)
                {
                    System.out.println(address);
                }
                else
                {
                    System.out.print(address + ", ");
                }
                index += 1;
            }
            System.out.println();
        }
        else if(emailAddresses.size() == 1)
        {
            System.out.println("Email Address: " + emailAddresses.get(0) + "\n");
        }
        else
        {
            System.out.println("Email Addresses: NIL");
        }
    }

}

