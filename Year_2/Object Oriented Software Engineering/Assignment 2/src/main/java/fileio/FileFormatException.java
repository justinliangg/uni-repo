package edu.curtin.emergencysim.fileio;

public class FileFormatException extends Exception
{
    public FileFormatException(String message)
    {
        super(message);
    }

    public FileFormatException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
