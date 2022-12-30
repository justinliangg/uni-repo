package edu.curtin.addressbook.view;

import edu.curtin.addressbook.model.*;
import java.util.Scanner;
import java.util.Map;

public class Menu
{   
    private Scanner input;

    public Menu()
    {
        input = new Scanner(System.in);
    }

    
    public String getFileName()
    {
        String fileName;

        System.out.print("Enter address book filename: ");
        fileName = input.nextLine();  

        return fileName;
    }


    /**
     * Show the main menu, offering the user options to (1) search entries by 
     * name, (2) search entries by email, or (3) quit.
     *
     * @param addressBook The AddressBook object to search.
     */
    public void showMenu(AddressBook addressBook, Map<Integer, Option> options)
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
}
