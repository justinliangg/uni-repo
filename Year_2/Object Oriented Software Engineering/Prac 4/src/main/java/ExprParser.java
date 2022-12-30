package edu.curtin.matheval;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.Logger;

/**
 * Parses mathematical expressions, and builds a tree of ExprNode objects to represent the parsed 
 * expression.
 */
public class ExprParser
{
    //Logger
    private static Logger logger = Logger.getLogger(ExprParser.class.getName());

    // The regex pattern used for tokenisation purposes. Basically, this skips any amount of 
    // whitespace, then checks for a fractional number (containing and possibly starting with "."),
    // then checks for an integer, and finally falls back to a single other character of any type.
    private static final Pattern TOKEN = Pattern.compile("\\s*([0-9]*\\.[0-9]+|[0-9]+|.)");
    
    public ExprParser() {}

    /**
     * Parses a string, which is assumed to contain a mathematical expression. Returns the root 
     * node of the resulting object tree.
     */
    public ExprNode parse(String s) throws ParserException
    {
        logger.info("Start of parse method");
        List<String> tokens = new LinkedList<>();

        // Tokenise the string, by repeatedly applying the 'TOKEN' regular expression until it 
        // doesn't match anymore (which should only happen at the end of the string).
        String substr = s;
        boolean done = false;
        do
        {
            Matcher matcher = TOKEN.matcher(substr);
            if(matcher.lookingAt())
            {
                tokens.add(matcher.group(1));
                substr = substr.substring(matcher.end());
            }
            else
            {
                done = true;
            }
        }
        while(!done);

        // Invoke the actual parsing logic.
        if(tokens.isEmpty()) //should not be parsing an empty tokens list.
        {  
            throw new ParserException("No equation entered");
        }

        ExprNode nodeToReturn = parseAdd(tokens);
        
        //Tokens list should be empty after parsing.
        if(!tokens.isEmpty()) 
        {   
            String message = null;
            String firstToken = tokens.remove(0);
            if(firstToken.equals("x"))
            {   
                message = "Missing operator after literal value!";
            }
            else if(firstToken.equals(")"))
            {
                message = "Missing opening brackets!";
            }
            else
            {
                message = "Missing variable x or an operator after variable x!";
            }

            throw new ParserException(message);
        } 
        
        return nodeToReturn;
    }

    /** 
     * Parses a sequence of zero-or-more "+" / "-" operators (the lowest operator precedence
     * level).
     */
    private ExprNode parseAdd(List<String> tokens) throws ParserException
    {
        logger.info("Start of parseAdd method, " + "Tokens: " + tokens);

        ExprNode node = parseMul(tokens);
        boolean end = false;
        while(!end && !tokens.isEmpty())
        {  
            logger.info(() -> ("Current Tokens: " + tokens + ""));
            // Expect next token to be '+' or '-'
            String token = tokens.remove(0);
            switch(token)
            {
                case "+": 
                    logger.info("In + case");
                    node = new AddOperator(node, parseMul(tokens));
                    logger.info("End of + case");
                    break;
                    
                case "-":
                    logger.info("In - case");
                    node = new SubOperator(node, parseMul(tokens));
                    logger.info("End of - case");
                    break;
                    
                default:
                    logger.info("In default case");
                    // The next token isn't "+" or "-", which means we assume this additive 
                    // sequence is over, so push the token back onto the list, and end.
                    tokens.add(0, token); 
                    end = true;
                    break;
            }
        }
        return node;
    }
    
    /**
     * Parses a sequence of zero-or-more "*" / "/" operators.
     */
    private ExprNode parseMul(List<String> tokens) throws ParserException
    {   
        logger.info("Start of parseMul method, " + "Tokens: " + tokens);

        ExprNode node = parsePrimary(tokens);
        boolean end = false;
        while(!end && !tokens.isEmpty())
        {   
            logger.info(() -> ("Current Tokens: " + tokens + ""));
            // Expect next token to be "*" or "/"
            String token = tokens.remove(0);
            switch(token)
            {
                case "*":
                    logger.info("In * case");
                    node = new MulOperator(node, parsePrimary(tokens));
                    logger.info("End of * case");
                    break;
                    
                case "/":
                    logger.info("In / case");
                    node = new DivOperator(node, parsePrimary(tokens));
                    logger.info("End of / case");
                    break;
                    
                default:
                    logger.info("In default case");
                    // The next token isn't "*" or "/", which means we assume this multiplicative
                    // sequence is over, so push the token back onto the list, and end.
                    tokens.add(0, token);
                    end = true;
                    break;
            }
        }
        return node;
    }
    
    /**
     * Parses a "primary" value, which can be either a sub-expression in brackets, a negation "-"
     * operator, a reference to the "x" variable, or a literal number.
     */
    private ExprNode parsePrimary(List<String> tokens) throws ParserException
    {   
        logger.info(() -> "Start of parsePrimary, " + "Tokens: " + tokens);
        
        //Missing value after operator.
        if(tokens.isEmpty())
        {
            throw new ParserException("Missing value after operator");
        }
        String token = tokens.remove(0); // Obtain the next token
        ExprNode node;
        
        switch(token)
        {
            case "(":
                logger.info("In ( case");
                // Sub-expression inside brackets.
                node = parseAdd(tokens);

                if(tokens.size() == 0) //missing closing brackets
                {
                    throw new ParserException("Missing closing brackets");
                }
                else if(!tokens.remove(0).equals(")")) //next token should be                                  
                {                                      //a closing bracket
                    throw new ParserException("Invalid value in place of " +
                                              "closing bracket");
                }

                logger.info("End of ( case");
                break;
                
            case "-":
                logger.info("In negation case");
                // Inverted value (e.g., -(x+1))
                node = new NegationOperator(parsePrimary(tokens));
                logger.info("End of negation case");
                break;
                
            case "x":
                logger.info("In x case");
                // Variable value
                node = new XValue();
                logger.info("End of x case");
                break;
                
            default:
                logger.info("In default case");
                // Literal number
                try 
                {
                    node = new Value(Double.parseDouble(token));
                }
                catch(NumberFormatException e)
                {
                    throw new ParserException("Only variable x allowed", e);
                }
                break;
        }
        return node;
    }
}
