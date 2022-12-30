package edu.curtin.matheval;

import java.util.Scanner;

import javax.swing.text.html.HTMLEditorKit.Parser;
import java.util.logging.Logger;

/**
 * Entry point for out mathematical expression app.
 */
public class Main
{
    //Logger
    private static Logger logger = Logger.getLogger(ExprParser.class.getName());


    public static void main(String[] args)
    {
        System.out.print("Enter a mathematical expression: ");
        String expression;
        try(Scanner sc = new Scanner(System.in))
        {
            expression = sc.nextLine();
        }

        ExprNode exprRoot = null;
        try 
        {
            exprRoot = new ExprParser().parse(expression);

            System.out.printf("Parsed expression: '%s'\nEvaluating for different values of x...\n", exprRoot.toString());
            for(double x = 0.0; x <= 10.00001; x += 0.5)
            {
                System.out.printf("(%.2f, %.2f)\n", x, exprRoot.evaluate(x));
            }
        }
        catch(ParserException e)
        {
            System.out.println("\nError! " + e.getMessage());
            logger.info(() -> "Error! " + e.getMessage());
        }
        
    }
}
