public class UnitTestDSAHashTable
{
    public static void main(String [] args)
    {
        //Variable declarations
        int numPassed = 0;
        int numTests = 0;
        double testDouble;
        String testString; 
        DSAHashTable hashTable = null;

//==============================================================================
        //TESTING CONSTRUCTOR
        System.out.println("\n\nTesting Constructor");
        System.out.println("=================================================");
        
        //Test 1 : Constructor
        try 
        {
            numTests++;
            System.out.print("Testing creation of DSAHashTable: ");
            
            hashTable = new DSAHashTable(10);
            if(hashTable.getLoadFactor() != 0)
            {
                throw new IllegalArgumentException();
            }
            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }



//==============================================================================
        //TESTING MUTATORS
        System.out.println("\nTesting Mutators");
        System.out.println("=================================================");

        //Test 2 : put()
        try
        {   
            numTests++;
            System.out.print("Testing put(): ");
            
            hashTable.put("14495655", "Sofia Bonfiglio");
            hashTable.put("14224671", "Ashlee Capellan");
            hashTable.put("14707100", "Dona Mcinnes");
            hashTable.put("14644633", "Maricela Landreneau");
            hashTable.put("14147356", "Elinor Raco");
            hashTable.put("14393910", "Cody Mcmartin");
            hashTable.put("14799737", "Katy Vacek");

            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }

        

        //Test 3 : put() #2
        try
        {
            numTests++;
            System.out.print("Testing put() #2: ");
            
            /* Duplicate keys should throw IllegalArgumentException */
            hashTable.put("14495655", "Sofia Bonfiglio");
                
            System.out.println("failed");
        }
        catch(IllegalArgumentException e)
        {
            numPassed++;
            System.out.println("passed");
        }
        


        //Test 4 : remove()
        try
        {
            numTests++;
            System.out.print("Testing remove(): ");
            
            hashTable.remove("14799737");
            /* Should not have key inside hashTable anymore */
            if( hashTable.hasKey("14799737") )
            {
                throw new IllegalArgumentException();
            }

            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }

        
        //Test 5: remove() #2 
        try
        {
            numTests++;
            System.out.print("Testing remove() #2: ");

            /*Trying to remove unknown key should throw exception*/
            hashTable.remove("13213");
            
            System.out.println("failed");
        }
        catch(IllegalArgumentException e)
        {   
            numPassed++;
            System.out.println("passed");
        }

            


//==============================================================================
        //TESTING ACCESSORS
        System.out.println("\nTesting Accessors");
        System.out.println("=================================================");
        


        //Test 6: get()
        try
        {
            numTests++;
            System.out.print("Testing get(): ");

            /*Checking if value returned is correct for the key given*/
            testString = (String) hashTable.get("14495655");
            if(!testString.equals("Sofia Bonfiglio"))
            {
                throw new IllegalArgumentException();
            }
            testString = (String) hashTable.get("14224671");
            if(!testString.equals("Ashlee Capellan"))
            {
                throw new IllegalArgumentException();
            }
            testString = (String) hashTable.get("14707100");
            if(!testString.equals("Dona Mcinnes"))
            {
                throw new IllegalArgumentException();
            }
            testString = (String) hashTable.get("14644633");
            if(!testString.equals("Maricela Landreneau"))
            {
                throw new IllegalArgumentException();
            }
            testString = (String) hashTable.get("14147356");
            if(!testString.equals("Elinor Raco"))
            {
                throw new IllegalArgumentException();
            }
            testString = (String) hashTable.get("14393910");
            if(!testString.equals("Cody Mcmartin"))
            {
                throw new IllegalArgumentException();
            }

            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }



        //Test 7: get() #2
        try
        {
            numTests++;
            System.out.print("Testing get() #2: ");
            
            /*Should throw exception for unknown key*/
            hashTable.get("14799737");
            
            System.out.println("failed");
        }
        catch(IllegalArgumentException e)
        {
            numPassed++;
            System.out.println("passed");
        }



        //Test 8: getLoadFactor()
        try
        {
            numTests++;
            System.out.print("Testing getLoadFactor(): ");

            testDouble = hashTable.getLoadFactor();
            if(testDouble != 0.55)
            {
                throw new IllegalArgumentException();
            }
            
            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }



        //Test 9: hasKey()
        try
        {
            numTests++;
            System.out.print("Testing hasKey(): ");

            if( !hashTable.hasKey("14495655") )
            {   
                throw new IllegalArgumentException();
            }
            if( !hashTable.hasKey("14224671") )
            {
                throw new IllegalArgumentException();
            }
            if( !hashTable.hasKey("14707100") )
            {
                throw new IllegalArgumentException();
            }
            if( !hashTable.hasKey("14644633") )
            {
                throw new IllegalArgumentException();
            } 
            if( !hashTable.hasKey("14147356") )
            {
                throw new IllegalArgumentException();
            } 
            if( !hashTable.hasKey("14393910") )
            {
                throw new IllegalArgumentException();
            }
            
            numPassed++;
            System.out.println("passed");
        }
        catch(Exception e)
        {
            System.out.println("failed");
        }



        //Test 10: hasKey() #2
        try
        {
            numTests++;
            System.out.print("Testing hasKey() #2: ");

            /* Should not have unknown key */
            if( hashTable.hasKey("14799737") )
            {
                throw new IllegalArgumentException();
            }

            numPassed++;
            System.out.println("passed");
        }   
        catch(Exception e)
        {
            System.out.println("failed");
        }

        

//=============================================================================
        //TESTING RESIZING
        System.out.println("\nTesting Resizing");
        System.out.println("=================================================");
        
        /*create new hashTable */
        DSAHashTable hashTableTwo = new DSAHashTable(5);

        //Test 11: resize()
        try
        {
            numTests++;
            System.out.print("Testing resize(): ");
            /* Adding to go past the 90 threshold */
            hashTableTwo.put("14799737", "Katy Vacek");
            hashTableTwo.put("14431660", "Mathew Mercedes");
            hashTableTwo.put("14541837", "Karina Rossin");
            hashTableTwo.put("14593654", "Mathew Milian");
            hashTableTwo.put("14492026", "Christian Mullarkey");
            hashTableTwo.put("14397374", "Esmeralda Radovich");
            
            //Checking load factor 
            if( hashTableTwo.getLoadFactor() != 0.55 )
            {
                throw new IllegalArgumentException();
            }
            
            /* Checking if copy worked and no loss of data */
            testString = (String) hashTableTwo.get("14799737");
            if(!testString.equals("Katy Vacek"))
            {
                throw new IllegalArgumentException();
            }
            testString = (String) hashTableTwo.get("14431660");
            if(!testString.equals("Mathew Mercedes"))
            {
                throw new IllegalArgumentException();
            }
            testString = (String) hashTableTwo.get("14541837");
            if(!testString.equals("Karina Rossin"))
            {
                throw new IllegalArgumentException();
            }
            testString = (String) hashTableTwo.get("14593654");
            if(!testString.equals("Mathew Milian"))
            {
                throw new IllegalArgumentException();
            }
            testString = (String) hashTableTwo.get("14492026");
            if(!testString.equals("Christian Mullarkey"))
            {
                throw new IllegalArgumentException(); 
            }
            testString = (String) hashTableTwo.get("14397374");
            if(!testString.equals("Esmeralda Radovich"))
            {
                throw new IllegalArgumentException(); 
            }
    
            numPassed++;
            System.out.println("passed");
         }
        catch(Exception e)
        {
            System.out.println("failed");
        }

        //Testing
        System.out.print("\nNumber PASSED: " + numPassed + "/" + numTests);
        System.out.print(" -> " + (int)(double)numPassed/numTests*100 + "%\n");
       
           
    }
}

   




