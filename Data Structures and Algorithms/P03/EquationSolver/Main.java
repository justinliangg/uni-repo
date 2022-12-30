import java.util.*;
import java.io.*;

public class Main
{
    public static void main(String [] args)
    {   
        EquationSolver eSolver = new EquationSolver();
        boolean validEquation;
        double ans = 0.0;

        validEquation = false;
        while(!validEquation)
        {
            try
            {
                //Getting user Input
                Scanner sc = new Scanner(System.in);
                System.out.println("Please enter an equation");
                System.out.println("Equation: ");
                String equation = sc.nextLine();
        
                //Evaluate the equation
                ans = eSolver.solve(equation);

                validEquation = true;
            }  
            catch(NullPointerException e)
            {
                System.out.println("Ensure that every operator/operand "
                                   + " is seperated with a space");
            }
            catch(Exception e)
            {
                System.out.println("Please enter a valid equation");
            }
               
        }
        System.out.println("Answer = " + ans);
    }

}
