package edu.curtin.imageviewer;
import java.util.*;

/**
 * Represents an album of images.
 */
public class Album 
{
    //Class Fields 
    private List<ImageRecord> images;
    private int currIndex;



    //Constructor
    public Album()
    {
        images = new LinkedList<>();
        currIndex = -1;
    }


    //Mutators
    public void addImage(ImageRecord newImage)
    {
        images.add(newImage);
    }


    public ImageRecord nextImage()
    {
        ImageRecord nextImage = null;
        if(currIndex < images.size() - 1)
        {
            currIndex++;
            nextImage = images.get(currIndex);
        }

        return nextImage;
    }


    public ImageRecord prevImage()
    {
        ImageRecord prevImage = null;
        if(currIndex > 0)
        {
            currIndex -= 1;
            prevImage = images.get(currIndex);
        }

        return prevImage;
    }
}
