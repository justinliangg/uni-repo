package edu.curtin.maze;

public class NonFatalException extends Exception
{
    public NonFatalException(String message)
    {
        super(message);
    }

    public NonFatalException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
