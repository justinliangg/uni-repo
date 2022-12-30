import java.util.*;
import java.io.*;

public class StudentMain
{
    public static void main(String[]args)
    {
        //Initialising variables
        int choice;
        int IMPORT = 1;
        int CALCAVERAGE = 3;
        int ALLDATA = 4;
        int ADDSTU = 2;
        int WRITEFILE = 5;
        int EXIT = 0;
        int numStudents;

        //Display menu, and display other choice options when user selects it
        //do-while loop allows the menu to display again to the user after they finish with the choice they pick
        //try -catch allows only integer values, loops again if not an integer
        choice = 0;
        numStudents = 0;
        Student students[] = new Student[20];
        do
        {
            try
                {
                    //Adding a line after each loop for better readability
                    System.out.println("");
                    menuDisplay(numStudents);
                    Scanner sc = new Scanner(System.in);
                    choice = sc.nextInt();

                    if (choice == IMPORT )
                    {   
                        System.out.print("Please enter a filename: ");
                        sc.nextLine();
                        String fileName = sc.nextLine();
                        numStudents = fileRead(fileName,students,numStudents);
                        bubbleSort(students,numStudents);
                    }
                    else if (choice == CALCAVERAGE)
                    {  
                        System.out.println(numStudents); 
                        double totalMarks;
                        totalMarks = 0.0;
                        for(int i=0; i< numStudents; i++)
                        {
                            totalMarks = totalMarks + students[i].getMark();
                        }

                        double average = totalMarks/ (double) numStudents;
                        System.out.print("The Average is " + average);
    
                    }   
                    else if (choice == ALLDATA)
                    {
                       for(int i= 0; i<numStudents; i++)
                       {
                           System.out.println(students[i].toString());
                       }
                    }
                    else if(choice == ADDSTU)
                    {
                        if(numStudents <= 20) 
                        {   
                            try{
                            System.out.println("Enter Students Name: "+ sc.nextLine());
                            String inName= sc.nextLine(); 
                            System.out.println("Enter Student ID: ");
                            int inID= sc.nextInt();
                            if(inID >99999999 || inID <10000000)
                            {
                                throw new InputMismatchException();
                            }
                            System.out.println("Enter Student Mark: ");
                            double inMark = sc.nextDouble();
                            if(inMark >100.0 || inMark < 0.0)
                            {
                                throw new InputMismatchException();
                            }
                            students [numStudents]= new Student(inName, inID, inMark);
                            numStudents ++;
                            }
                            catch(InputMismatchException e)
                            {
                               System.out.println("Please enter correct student details");
                            }
                        }
                        else
                        {
                            System.out.println("Max Students have been reached");
                        }
                    }
                        else if (choice == WRITEFILE)
                        {   
                            System.out.println(students[0].toFileString());
                            System.out.println("");
                            System.out.println("Please enter the filename you would like to write to");
                            sc.nextLine();
                            String fileName = sc.nextLine();
                            
                            writeFile(students, fileName, numStudents);
                            System.out.println("EXPORT COMPLETE!");
                        }

                    else if(choice == EXIT)
                    {
                        System.out.println("YOU HAVE EXITED THE PROGRAM!");
                    }
                    
                    else 
                    {
                        System.out.println(students[0]);
                    }
                 }
            catch(InputMismatchException error)
                 {
                    System.out.println( error + " Please only enter integers");
                 } 
        }
        while (choice != EXIT) ;
    }
    //MenuDisplay<F4><F4><F4><F4><F4><F4><F4><F4><F4><F4><F4><F4><F4><F3><F4><F3><F4><F3><F4><F3><F4>
    public static void menuDisplay(int pNumStudents)
    {
        System.out.println(" 1. Import student data from CSV.");
        System.out.println("2. Add Student");
        if(pNumStudents != 0)
        {
        System.out.println("3. Calculate test mark average.");
        System.out.println("4. View all student data.");
        System.out.println("5. Export all students to file");
        }
        System.out.println("0. Exit the program.");
    }
    
    public static void bubbleSort(Student pStudent[] ,int pNumStudents)
    {
        for (int i=0; i<pNumStudents-1 ;i++)
        {
            for(int j=0 ; j<pNumStudents - i - 1 ; j++)
            {
                if(pStudent[j].getMark() > pStudent[j+1].getMark())
                {
                    Student temp = pStudent[j+1];
                    pStudent[j+1] = pStudent[j];
                    pStudent[j] = temp;

                }
            }
        }
    }
    //SUBMODULE:fileRead
    //IMPORT: pFileName(String)
    //EXPORT: nil
    //ASSERTION: read a file and store values into objects
    public static int fileRead(String pFileName, Student pStudent[],int pNumStudents)
    {

        FileInputStream fileInput = null;
        int numStudents = 0 + pNumStudents;
        InputStreamReader reader;
        BufferedReader bfr;
        String line;
        
            try
            {
                fileInput = new FileInputStream(pFileName);
                reader = new InputStreamReader(fileInput);
                bfr = new BufferedReader(reader);
                line = bfr.readLine();

                while(line != null && numStudents != 20)
                {   
                    String[] column = processLine(line);
                    if(column.length == 3)
                    { 
                        if(column[0] != "")
                        {
                        if(validInt(column[1]) &&  column[1] !="")
                            { 
                                int studentID = Integer.parseInt(column[1]);
                                if (validDouble(column[2]))
                                {
                                double mark = Double.parseDouble(column[2]);
                                pStudent[numStudents] = new Student (column[0] , studentID, mark);
                                numStudents++;
                                System.out.println(numStudents);
                                }
                            }
                        }
                    }
                    line = bfr.readLine();
                }

                        
             

                if(numStudents == 20)
                { 
                    System.out.println("Max Students have been reached");
                }

                fileInput.close();
            }
            
            catch(IOException e)
            {
                if(fileInput != null)
                {
                    try
                    {   
                        fileInput.close();
                    }
                    catch(IOException ex2)
                    {}
                }
                System.out.println("Please Enter a valid file");
            }
        return numStudents;
    }

    

    public static boolean validInt(String pColumn)
    {
        boolean isTrue = true;
        for(int i =0; i < pColumn.length() ; i++)
        {
            if(pColumn.charAt(i) < 48 ||  pColumn.charAt(i) > 57)
            {
                isTrue = false;
            }
        }
        return isTrue;
    }

    public static boolean validDouble(String pColumn)
    {
        boolean isTrue = true;
        int decimal=0;
        for(int i = 0; i<pColumn.length(); i++)
        {
            if(pColumn.charAt(i) >= 48  &&   pColumn.charAt(i) <= 57 || pColumn.charAt(i) == 46) 
            { 
                if(pColumn.charAt(i) ==46)
                {
                    decimal++;
                    if(decimal>1)
                    {
                        isTrue = false;
                    }
                }
            }
            else
            {
                isTrue = false;
            }
           
        } 
        return isTrue;
    }
                
    public static String[] processLine(String pRow)
    {   
     
        String [] inColumns;
        inColumns = pRow.split(",");
        return inColumns;
    }

    public static void writeFile(Student pStudents[], String pFileName, int pNumStudents)
    {
        FileOutputStream fileStream = null;
        PrintWriter pw;
        try
        {
            fileStream = new FileOutputStream(pFileName);
            pw = new PrintWriter(fileStream);
            for(int i = 0; i<pNumStudents ; i++)
            {
                pw.println(pStudents[i].toFileString());
            }
            pw.close();
        }
        catch(IOException e)
        {
            System.out.println("Please enter valid file name");
        }
    }

}   
        
                            
        
