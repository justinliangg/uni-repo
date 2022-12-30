/*-------------------------------                                               
FILE: EquationSolver.java                                                       
Author: Justin Liang (19821986)                                                 
UNIT: DSA          
Purpose: Intake an infix and convert to postfix and then evaluating it
REQUIRES: nil                                                     
Last Mod: 14/08/2021                                                            
--------------------------------*/  
import java.util.*;
import java.io.*;

public class EquationSolver
{   
    //Constructor
    public EquationSolver()
    {
        //Left empty, just to make a instance of EquationSolver
    }




    //ACCESSOR: solve
    //IMPORT: equation(String)
    //EXPORT: answer(Double)
    //ASSERTION: takes in an equation and solves it, returning the answer
    // as a double.

    public double solve(String equation)
    {
        DSAQueue postfixQueue = parseInfixToPostfix(equation);
        double answer = evaluatePostFix(postfixQueue);
        
        return answer;
    }
    



    //SUBMODULE: parseInfixToPostfix
    //IMPORT: equation(String)
    //EXPORT: postFix(DSAQueue)
    //ASSERTION: convert an equation into postfix.

    private DSAQueue parseInfixToPostfix(String equation)
    {   
        //Initialising variables
        String [] infix;
        DSACircularQueue postFix;
        DSAStack opStack;
        
        //removing spaces from equation and storing it in infix
        infix = equation.split(" ");
        
        //Intialising stack and queues with infix length
        postFix = new DSACircularQueue(infix.length);
        opStack = new DSAStack(infix.length);

        //converting Infix to Postfix
        for(int i= 0; i < infix.length; i++)
        {   
            //extracting term
            String term  = infix[i];
            
            //evaluate the term
            if(term.equals("("))
            {   
                opStack.push(term.charAt(0));
            }
            else if(term.equals(")"))
            {
                while((char) opStack.top() != '(')
                {
                    //queues all the operators before ')' 
                    postFix.enqueue(opStack.pop());
                }
                
                //pops '(' off the stack
                //not need anymore.
                char throwaway = (char) opStack.pop();
            }
            else if(equalsOp(term))
            {  
                //If not first term and opStack has greater precedence than
                //term, then add to queue
                while((!firstTerm(opStack) && greaterPrecedence(opStack,term)))
                {   
                    //operator on stack has greater precedence
                    postFix.enqueue(opStack.pop());
                }
                
                //It is a new term or term has lower precedence    
                opStack.push(term.charAt(0));
            }
            else
            {
                //term is a number in the equation, add to postFix
                //Double.parseDouble to convert string to double
                postFix.enqueue(Double.parseDouble(term));
            }
        }
        
        while(!opStack.isEmpty())
        {   
            postFix.enqueue(opStack.pop());
        }

        return postFix;
    }




    //SUBMODULE: evauluatePostFix
    //IMPORT: postfixQueue(DSAQueue)
    //EXPORT: finalAns(Double)
    //ASSERTION: intakes a postfix, processes it and return the ans

    private double evaluatePostFix(DSAQueue postfixQueue)
    {   
        //Creating a stack to push values onto
        DSAStack postfixStack = new DSAStack(postfixQueue.getCount());
        
        //Iterating through the queue
        while(!postfixQueue.isEmpty())
        {  
            //If value is a number, put on stack
            if(postfixQueue.peek() instanceof Double)
            { 
                postfixStack.push((double)postfixQueue.dequeue());
            }
            //if value is an operator
            else if(postfixQueue.peek() instanceof Character)
            {   
                //getting the values to add
                char operator = (char) postfixQueue.dequeue();
                double value2 = (double) postfixStack.pop();
                double value1 = (double) postfixStack.pop();
                
                //excuting the operation
                double ans = executeOperation(operator,value1,value2);

                //pushing it back onto the stack
                postfixStack.push(ans);
            }
        }
        
        //end of loop, the final value in the stack is the ans
        double finalAns = (double) postfixStack.pop();

        return finalAns;
            
    }



    //SUBMODULE: precedenceOf
    //IMPORT: theOp (Character)
    //EXPORT: number(Integer)
    //ASSERTION: returns 1 if + or -, returns 2 if * or /

    private int precedenceOf(char theOp)
    {   
        int number = 0;

        switch (theOp)
        {
            case '+':
                number = 1;
                break;

            case '-':
                number = 1;
                break;

            case '*':
                number = 2;
                break;

            case '/':
                number = 2;
                break;
        }
        
        return number;
    }




    //SUBMODULE: executeOperation
    //IMPORT: op(Character), op1(Double), op2(Double)
    //EXPORT: ans(Double)
    //ASSERTION: evaluautes op1 and op2 based on operator

    private double executeOperation(char op, double op1, double op2)
    {   
        double ans = 0.0;

        switch(op)
        {
            case '+': 
                ans = op1 + op2;
                break;

            case '-':
                ans = op1 - op2;
                break;

            case'*':
                ans = op1 * op2;
                break;

            case'/':
                ans = op1/op2;
                break;
        }
        
        return ans;
    }
    



    //SUBMODULE: equalsOp
    //IMPORT: pTerm(String)
    //EXPORT: equals(Boolean)
    //ASSSERTION: check if pTerm is an operator, return true if it is

    private boolean equalsOp(String pTerm)
    {   
        boolean equals = false;

        if(pTerm.equals("+") || pTerm.equals("-") || pTerm.equals("*") ||
           pTerm.equals("/") )
        {
            equals = true;
        }

        return equals;
    }



    
    //SUBMODULE: firstTerm
    //IMPORT: pOpStack(DSAStack)
    //EXPORT: equals(Boolean)
    //ASSERTION: checks if pOpStack is empty or the top is '('
    
    private boolean firstTerm(DSAStack pOpStack)
    {   
        boolean equals = false;
        if(pOpStack.isEmpty())
        {
            equals = true;
        }
        else if((char) pOpStack.top() == '(')
        {
            equals = true;
        }

        return equals;
    }




    //SUBMODULE: greaterPrecedence
    //IMPORT: pOpStack(DSAStack), pTerm(String)
    //EXPORT: equals(Boolean)
    //ASSERTION: checks if pOpStack.top() has greater precedence than pTerm.

    private boolean greaterPrecedence(DSAStack pOpStack, String pTerm)
    {
        boolean equals = false;
        if(precedenceOf((char) pOpStack.top()) >= precedenceOf(pTerm.charAt(0)))
        {   
            equals = true;
        }

        return equals;
    }

}
