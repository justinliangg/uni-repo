public class StudentTest
{
    public static void main(String [] args)
    {   
        System.out.println("Test for Student Class\n"); 
        



        //Testing Constructor
        System.out.println("Testing Constructor");
        Student test = new Student(1234,"John");
        if(test.getId() == 1234 && test.getName().equals("John"))
        {
            System.out.println("Constructor Test Passed!\n");
        }
        else
        {
            System.out.println("Constructor Test Failed!");
        }
        
        //Testing Constructor for invalid parameters
        System.out.println("Testing Constructor for invalid parameters");
        //Test 1
        try
        {
            Student test2 = new Student(-1,"Hi");
            System.out.println("Invalid Parameters Test 1 Failed!");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Invalid Parameters Test 1 Passed!");
        }

        //Test 2
        try
        {   
            String x = null;
            Student test3 = new Student(1234,x);
            System.out.println("Invalid Parameters Test 2 Failed!");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Invalid Parameters Test 2 Passed!");
        }
        
        //Test 3
        try
        {
            Student test4 = new Student(1234,"");
            System.out.println("Invalid Parameters Test 3 Failed!");
        }
        catch(IllegalArgumentException e)
        { 
            System.out.println("Invalid Parameters Test 3 Passed!");
        }




        //Testing Setters
        System.out.println("\nTesting Setters");
        System.out.println("");
        
        //Test setId
        System.out.println("Testing Id Setter");
        test.setId(12345);
        if(test.getId() == 12345)
        {
            System.out.println("Id Setter Passed!");
        }
        else
        {
            System.out.println("Id Setter Failed");
        }
        
        //Test for invalid param
        System.out.println("\nTesting Id setter for invalid parameters");
        test.setId(-1);
        if(test.getId() == 12345)
        {
            System.out.println("Invalid Parameters for Id Passed!");
        }
        else
        {
            System.out.println("Failed");
        }

        //Test for setName
        System.out.println("\nTesting Name Setter");
        test.setName("Justin");
        {
            if(test.getName().equals("Justin"))
            {
                System.out.println("setName Passed!");
            }
            else
            {
                System.out.println("setName Failed!");
            }
        }
        //Test for invalid name param
        System.out.println("\nTesting Name setter for invalid parameters");
        test.setName("");
        {
            if(test.getName().equals("Justin"))
            {
                System.out.println("Invalid Parameters for Name Passed!");
            }
            else
            {
                System.out.println("Invalid Parameters for Name Failed!");
            }
        }

        

        
        
        //Test writeToFileString
        System.out.println("\nTesting writeToFileString method");
        String testWrite = test.writeToFileString();

        if(testWrite.equals("12345,Justin"))
        {
            System.out.println("writeToFileString Passed!");
        }
        else
        {
            System.out.println("writeToFileString Failed!");
        }

    }
}
