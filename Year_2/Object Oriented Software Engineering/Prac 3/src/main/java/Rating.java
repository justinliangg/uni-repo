package edu.curtin.imageviewer;


public class Rating extends ImageDecorator
{
    private String value;

    public Rating(ImageRecord image, String value)
    {
        super(image);
        this.value = value;
    }


    @Override
    public String getCaption()
    {
        return image.getCaption() + ", Rating: " + value;
    }
}
