package edu.curtin.imageviewer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.*;

/**
 * Main class representing the entry point (and controller) of the application.
 */
public class MainApplication extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args); // Run JavaFX
        // This will effectively do 'new MainApplication()' and then call 'start(...)'.
    }
    
    /**
     * Loads an image album and then creates a window to display it.
     */
    @Override
    public void start(Stage stage)
    {
        // Construct an album object.
        Album album = new Album();
        
        // Make a new window.
        MainWindow window = new MainWindow(album, stage);        
        
        // Choose which album to load.
        File albumFile = window.chooseAlbumFile();
        
        if(albumFile == null)
        {
            Platform.exit(); // Otherwise JavaFX keeps the program open, doing nothing.
        }
        else
        {
            try
            {
                // Attempt to read an album file.
                readAlbumFile(albumFile, album);
                
                // Display the window.
                window.show();
            }
            catch(IOException e)
            {
                System.err.println("Error while reading " + albumFile);
                Platform.exit();
            }
        }
    }
    
    /**
     * Reads an album file, given a filename and an Album object. Returns true if
     * successful, or false if the file could not be read.
     *
     * @param albumFile The file storing the list of image filenames and their captions.
     * @param album An Album object to populate.
     * 
     * @throws IOException If the file could not be read.
     */
    private static void readAlbumFile(File albumFile, Album album) throws IOException
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(albumFile)))
        {
            String line = reader.readLine();
            while(line != null)
            {
                if(line.trim().length() > 0) // Ignore blank lines
                {
                    String[] parts = line.split(":");
                    
                    String imageFilename = albumFile.getParent() + File.separatorChar + parts[0];
                    String imageCaption;
                    ImageRecord newImage = null;

                    if(parts.length > 0)
                    {
                        imageCaption = parts[1];
                        newImage = new Image(imageFilename, imageCaption);
                        //getting all metadata info for image.
                        for(int i = 2; i < parts.length; i++)
                        {
                            newImage = addDecorator(parts[i], newImage);
                        }
                    }
                    
                    album.addImage(newImage);
                }
                            
                line = reader.readLine();
            }
        }
    }

      
    /**
     *Adds the approriate decorator for newImage.
     *
     * @param metaInfo String to be parsed for metadata.
     * @param newImage image to add decorator to.
     * 
     * @return ImageRecord 
     */
    private static ImageRecord addDecorator(String metaInfo,
                                            ImageRecord newImage)
    {
        String [] metadata = metaInfo.split("=", 2);
        
        if(metadata.length == 2)
        {
            String metadataType = metadata[0].toLowerCase().trim();
            String metadataValue = metadata[1];
            
            //determining which type and then making the 
            //approriate decorator
            if(metadataType.equals("gps"))
            {
                newImage = new GPS(newImage, metadataValue);
            }
            else if(metadataType.equals("date"))
            {
                newImage = new Date(newImage, metadataValue);
            }
            else if(metadataType.equals("rating"))
            {
                newImage = new Rating(newImage, metadataValue);
            }
        }

        return newImage;
    }

}
