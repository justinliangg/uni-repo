package edu.curtin.addressbook;

import java.io.*;
import java.util.*;

/**
 * A simple address book application.
 * @author Dave and Justin Liang(19821986)
 */
public class AddressBookApp 
{   
    //Class Fields
    private Scanner input;
    private Map<Integer, Option> options;

    
    //Constructor
    public AddressBookApp()
    {
        input = new Scanner(System.in);
        options = new HashMap<>();
    }


    public static void main(String[] args)
    {
        String fileName;

        System.out.print("Enter address book filename: ");
        Scanner input = new Scanner(System.in); //NOPMD
        fileName = input.nextLine();

        try
        {   
            //Storing entries into addressBook
            AddressBookApp app = new AddressBookApp();
            AddressBook addressBook = app.readAddressBook(fileName);
            
            //Storing options into addressBookApp
            app.addOption(1, new SearchByName(addressBook));
            app.addOption(2, new SearchByEmail(addressBook));
            app.addOption(3, new DisplayAllEntries(addressBook));

            //Display menu
            app.showMenu(addressBook);

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
     * Show the main menu, offering the user options to (1) search entries by 
     * name, (2) search entries by email, or (3) quit.
     *
     * @param addressBook The AddressBook object to search.
     */
    public void showMenu(AddressBook addressBook)
    {
        boolean done = false;
        while(!done)
        {
            System.out.println("(1) Search by name, (2) Search by email, " + 
                               "(3) Display all entries, (4) Quit");
            
            try
            {
                int userInput = Integer.parseInt(input.nextLine());
                
                if(userInput >= 1 && userInput <= 3) //valid input
                {   
                    String searchQuery = null;

                    //Getting selected option
                    Option optionSelected = options.get(userInput);
                    if(optionSelected.requiresText())
                    {
                        System.out.print("Enter search query: ");
                        searchQuery = input.nextLine();
                    }
                    
                    String entryDetails = optionSelected.doOption(searchQuery);

                    System.out.print(entryDetails);
                }
                else if(userInput == 4) //exit
                {
                    done = true;
                }
                else //invalid input
                {
                    System.out.println("Enter a valid number");
                }
            }
            catch(NumberFormatException e)
            {
                // The user entered something non-numerical.
                System.out.println("Enter a number");
            }
        }
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
}
