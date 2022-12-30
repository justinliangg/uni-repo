package edu.curtin.imageviewer;

public class GPS extends ImageDecorator
{
    private String value;

    public GPS(ImageRecord image, String value)
    {
        super(image);
        this.value = value;
    }

    @Override 
    public String getCaption()
    {
        return image.getCaption() + ", GPS: " + value;
    }
}