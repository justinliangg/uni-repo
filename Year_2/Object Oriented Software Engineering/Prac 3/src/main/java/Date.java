package edu.curtin.imageviewer;


public class Date extends ImageDecorator
{
    private String value;

    public Date(ImageRecord image, String value)
    {
        super(image);
        this.value = value;
    }


    @Override
    public String getCaption()
    {
        return image.getCaption() + ", Date: " + value;
    }
}
