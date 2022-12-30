/*-------------------------------                                               
FILE: Menu.java                                              
Author: Justin Liang (19821986)                                                 
UNIT: DSA                                                                       
REQUIRES: DSATreeNode.java, DSALinkedList.java, DSAQueue.java                   Last Mod: 30/08/2021                                                            
--------------------------------*/
import java.io.*;
import java.util.*;

public class Menu
{
    public static void main(String[] args)
    {
        DSABinarySearchTree tree = null;
        int userInput = 0;
        do
        {
            try
            {
                //getting user Input
                displayMenu();
                Scanner sc = new Scanner(System.in);
                userInput = sc.nextInt();
                
                //Do actions based on userInput
                switch(userInput)
                {
                    case 1:
                        tree = readCSVFile();
                        break;

                    case 2:
                        tree = readSerializedFile(); 
                        break;
                    
                    case 3:
                        if(tree != null)
                        {
                            tree.display(1);
                            tree.display(2);
                            tree.display(3);
                            System.out.println("");
                        }
                        else
                        {
                            System.out.println("Tree is empty");
                        }
                        break;
                    
                    case 4: 
                        if(tree != null)
                        {
                            writeCSVFile(tree);
                        }
                        else
                        {
                            System.out.println("Tree is empty");
                        }
                        break;
                    
                    case 5:
                        if(tree != null)
                        {
                            writeSerializedFile(tree);
                        }
                        else
                        {
                            System.out.println("Tree is empty");
                        }
                        break;
                    case 6:
                        System.out.println("EXITED!");
                        break;
                    default:
                        System.out.println("Only enter integers 1-6");
                        break;
                }
            }
            catch(InputMismatchException e)
            {
                System.out.println("Please enter an integer");
            }
        }
        while(userInput != 6);
    }

    


//==============================================================================
    //SUBMODULES



    //SUBMODULE: displayMenu
    //IMPORT: nil
    //EXPORT: nil
    //ASSERTION: display menu to terminal

    private static void displayMenu()
    {
        System.out.println("1) read a CSV file");
        System.out.println("2) read a serialized file");
        System.out.println("3) display the tree");
        System.out.println("4) write a CSV file");
        System.out.println("5) write a serialized file");
        System.out.println("6) exit");
    }


    

    //SUBMODULE: readCSVFile
    //IMPORT: nil
    //EXPORT: pTree(DSABinarySearchTree)
    //ASSERTION: read a csv file from the user and return a tree

    private static DSABinarySearchTree readCSVFile()
    {
        DSABinarySearchTree tree = new DSABinarySearchTree();
        
        //Getting User Input for fileName
        Scanner sc = new Scanner(System.in);
        System.out.print("filename: "); 
        String fileName = sc.nextLine();

        //Reading in the csv file
        FileIO.fileRead(fileName, tree);
        return tree;
    }
        
       

  
    //SUBMODULE: writeCSVFile
    //IMPORT: pTree(DSABinarySearchTree)
    //EXPORT: nil
    //ASSERTION: get file name and write pTree to that file

    private static void writeCSVFile(DSABinarySearchTree pTree)
    {
        DSAQueue queue;
        int choice = 0;
        do
        {   
            try
            {
                //getting user input for order of tree
                System.out.println("Select order of tree");
                System.out.println("1) preorder");
                System.out.println("2) inorder");
                System.out.println("3) postorder");
                Scanner sc = new Scanner(System.in);
                choice = sc.nextInt();
            
                //Performing action based on userInput
                switch(choice)
                {
                    case 1: 
                        queue = pTree.preOrder();
                        FileIO.writeToFile(queue, pTree);
                        System.out.println("write file complete!\n");
                        break;
                    case 2:
                        queue = pTree.inOrder();
                        FileIO.writeToFile(queue, pTree);
                        System.out.println("write file complete!\n");
                        break;
                    case 3:
                        queue = pTree.postOrder();
                        FileIO.writeToFile(queue, pTree);
                        System.out.println("write file complete!\n");
                        break;
                    default: 
                        System.out.println("Please enter integers 1 to 3");
                        break;
                }
            }
            catch(InputMismatchException e)
            {
                System.out.println("Please enter a valid integer");
            }
        }
        while(choice < 1 || choice > 3);

    } 




    //SUBMODULE: readSerializedFile
    //IMPORT: nil
    //EXPORT: readObj(DSABinarySearchTree)
    //ASSERTION: get user input for a serialized object, read file and 
    //           transfer to readObj

    private static DSABinarySearchTree readSerializedFile()
    {  
        String fileName;
        FileInputStream fileStream;
        ObjectInputStream objectStream;
        DSABinarySearchTree readObj = null;
        try
        {
            //Getting file name from user
            System.out.print("filename: ");
            Scanner sc = new Scanner(System.in);
            fileName = sc.nextLine();
    
            //processing file and getting the serialized object
            fileStream = new FileInputStream(fileName);
            objectStream  = new ObjectInputStream(fileStream);
            readObj = (DSABinarySearchTree) objectStream.readObject();    
            objectStream.close();
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Class DSABinarySearchTree not found");
        }
        catch(Exception e)
        {
            System.out.println("Unable to load object from file");
        }
        
        System.out.println("file read complete!\n");
        return readObj;
    }
    


     
    //SUBMODULE: writeSerializedFile
    //IMPORT: pTree(DSABinarySearchTree)
    //EXPORT: nil
    //ASSERTION: take pTree, serialize it and transfer to a file given by user

    private static void writeSerializedFile(DSABinarySearchTree pTree)
    {        
        FileOutputStream outputStream;
        ObjectOutputStream objectStream;
        String fileName;
        try
        {
            //Getting user Input
            System.out.print("filename: ");
            Scanner sc = new Scanner(System.in);
            fileName = sc.nextLine();
            
            //Writing to file
            outputStream = new FileOutputStream(fileName);
            objectStream = new ObjectOutputStream(outputStream);
            objectStream.writeObject(pTree); 
            objectStream.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to save object to file");
        }

        System.out.println("write file complete!\n");
    }    
 
}
