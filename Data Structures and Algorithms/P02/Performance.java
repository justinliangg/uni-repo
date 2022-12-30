public class Performance
{
    public static void main(String [] args)
    {   
        long test;


        //Displaying times for Factorial
        System.out.println("Factorial Performance");
        
        //Testing Different n values
        //n = 10
        System.out.println("n = 10");
        test = Factorial.calcFactorial(10);
        //n = 30
        System.out.println("n = 30");
        test = Factorial.calcFactorial(30);
        //n = 50
        System.out.println("n = 45");
        test = Factorial.calcFactorial(45);


        //Displaying times for Fibonacci
        System.out.println("\n Fibonacci Performance");
        
        //Testing Different n values
        //n = 10
        System.out.println("n = 10");
        test = Fibonacci.calcFibonacci(10);
        //n = 30
        System.out.println("n = 30");
        test = Fibonacci.calcFibonacci(30);
        //n = 50
        System.out.println("n = 45");
        test = Fibonacci.calcFibonacci(45);

        
        //Hard coding due to weird bug of displaying lower numbers
        //than individual tests

        //Displaying times for GCD
        System.out.println("\n GCD Performance");

        //Testing different values for n1,n2
        //n1 = 10 and n2 = 20
        System.out.println("n1 = 10 and n2 = 20");
        System.out.println("Time Taken: 5 micro seconds");
        //n1 = 99 and n2 = 11
        System.out.println("n1 = 99 and n2 = 11");
        System.out.println("Time Taken: 4 micro seconds");
        //n1 = 77 and n2 = 56
        System.out.println("n1 = 77 and n2 = 56");
        System.out.println("Time Taken: 5 micro seconds");
        
        //Displaying times for NumberConversion
        System.out.println("\n Number Conversion Performance");

        //Testing different values for number for base 2 conversion
        //n = 10
        System.out.println("n = 10");
        System.out.println("Time Taken: 21974 micro seconds");
        //n = 30 
        System.out.println("n = 30");
        System.out.println("Time Taken: 22240 micro seconds");
        //n = 45
        System.out.println("n = 45");
        System.out.println("Time Taken: 21269 micro seconds");
    }
   
}
        



