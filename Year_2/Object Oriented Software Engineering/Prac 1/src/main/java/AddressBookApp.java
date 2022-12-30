package edu.curtin.addressbook;

import java.io.*;
import java.util.*;

/**
 * A simple address book application.
 * @author Dave and Justin Liang(19821986)
 */
public class AddressBookApp 
{
    /** Used to obtain user input. */
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args)
    {
        String fileName;
        
        System.out.print("Enter address book filename: ");
        fileName = input.nextLine();
        
        try
        {
            AddressBook addressBook = readAddressBook(fileName);
            showMenu(addressBook);
        }
        catch(IOException e)
        {
            System.out.println("Could not read from " + fileName + ": " + e.getMessage());
        }
    }
    
    /**
     * Read the address book file, containing all the names and email addresses.
     *
     * @param fileName The name of the address book file.
     * @return A new AddressBook object containing all the information.
     * @throws IOException If the file cannot be read.
     */
    private static AddressBook readAddressBook(String fileName) throws IOException
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
     * Show the main menu, offering the user options to (1) search entries by 
     * name, (2) search entries by email, or (3) quit.
     *
     * @param addressBook The AddressBook object to search.
     */
    private static void showMenu(AddressBook addressBook)
    {
        boolean done = false;
        while(!done)
        {
            System.out.println("(1) Search by name, (2) Search by email, (3) Quit");
            
            try
            {
                switch(Integer.parseInt(input.nextLine()))
                {   
                    case 1:
                        System.out.print("Enter name: ");
                        String name = input.nextLine();
                        
                        //Searching in addressBook and displaying entry
                        Entry entryFound = addressBook.getEntryByName(name);
                        if(entryFound != null)
                        {
                            entryFound.display();
                        }
                        else
                        {
                            System.out.println("Entry cannot be found, " + 
                                               "please enter a valid name!");
                        }

                        break;
                        
                    case 2:
                        System.out.print("Enter email address: ");
                        String email = input.nextLine();
                        
                        //Searching in addressBook and displaying entry
                        entryFound = addressBook.getEntryByEmail(email);
                        if(entryFound != null)
                        {
                            entryFound.display();
                        }
                        else
                        {
                            System.out.println("Entry cannot be found, " + 
                                               "please enter a valid email!");
                        }
 
                        break;
                        
                    case 3:
                        done = true;
                        break;
                        
                    default:
                        System.out.println("Enter a valid number");
                        break;
                }
            }
            catch(NumberFormatException e)
            {
                // The user entered something non-numerical.
                System.out.println("Enter a number");
            }
        }
    }
}
