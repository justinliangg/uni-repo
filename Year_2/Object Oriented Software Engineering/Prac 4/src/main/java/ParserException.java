package edu.curtin.matheval;

public class ParserException extends Exception
{
    public ParserException(String message)
    {
        super(message);
    }

    public ParserException(String message, Throwable cause)
    {
        super(message, cause);
    }

}