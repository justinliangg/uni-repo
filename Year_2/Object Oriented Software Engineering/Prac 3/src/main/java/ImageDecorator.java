package edu.curtin.imageviewer;

public abstract class ImageDecorator implements ImageRecord
{   

    //Class Field
    protected ImageRecord image;


    //Constructor
    public ImageDecorator(ImageRecord image)
    {
        this.image = image;
    }


    //Methods
    @Override
    public String getFilename()
    {
        return image.getFilename();
    }


    @Override 
    public String getCaption()
    {
        return image.getCaption();
    }

}
