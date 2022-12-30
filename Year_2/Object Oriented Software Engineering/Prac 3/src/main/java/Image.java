package edu.curtin.imageviewer;

/**
 * Represents an image in an album.
 */
public class Image implements ImageRecord
{
    private String filename;
    private String caption;
    
    public Image(String newFilename, String newCaption)
    {
        filename = newFilename;
        caption = newCaption;
    }
    
    @Override
    public String getFilename()
    {
        return filename;
    }
    
    @Override
    public String getCaption()
    {
        return caption;
    }

}

