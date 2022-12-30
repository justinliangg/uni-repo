package edu.curtin.addressbook.model;

/**
 * Interface for the options in AddressBookApp
 * 
 * @author Justin Liang(19821986)
 */
public interface Option
{   
     /**
     * Takes a search query as a parameter and performs an option in 
     * AddressBookApp, returning a string to display in AddressBookApp
     *  
     * @param s //the search query
     * @return //the string used to display to user
     * 
     */
    public String doOption(String s);


    /**
     * Returns a boolean on whether the option requires a search query
     * @return //true if it requires text
     * 
     */
    public boolean requiresText();
}
