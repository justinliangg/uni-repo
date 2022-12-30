import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Question3 implements ActionListener
{   
    private JTextField searchBar;
    private JButton searchButton;

    //Labels for Results
    private JLabel results;
    private JLabel results2;

    public static void main(String [] args)
    {   
        new Question3();
    }

    public Question3()
    {   
        //JFrame
        JFrame frame = new JFrame("Searching for Plants");
        frame.setSize(new Dimension(500,500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3,1));

        //Setting up SearchPanel JPanel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        //SearchBar JLabel
        JLabel searchBarLabel = new JLabel("Garden Name:");
        searchBarLabel.setVisible(true);
        searchPanel.add(searchBarLabel);


        searchBar = new JTextField();
        searchBar.setPreferredSize(new Dimension(100,24));
        searchBar.setVisible(true);
        searchPanel.add(searchBar);

         //Results Label
         results = new JLabel();
         results.setVisible(true);

         results2 = new JLabel();
         results2.setVisible(true);

        //Search Button
        searchButton = new JButton("Search");
        searchButton.setVisible(true);
        searchButton.addActionListener(this);
        searchPanel.add(searchButton);

        //HAS TO BE AT THE END
        frame.add(searchPanel);
        frame.add(results);
        frame.add(results2);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {   
        Plant [] arrayOfPlants = FileIO.storeData("data.csv");
        if(e.getSource() == searchButton)
        {   
            
            String gardenName = searchBar.getText();
            ArrayList<Plant> gardenFound = new ArrayList<Plant>();

            for(int i = 0; i < arrayOfPlants.length; i++)
            {
                if(arrayOfPlants[i].getGardenName().equals(gardenName))
                {
                    gardenFound.add(arrayOfPlants[i]);
                }
            }
            
            ArrayList<String> plantData = new ArrayList<String>();
            for(int j = 0; j < gardenFound.size(); j++)
            {
                plantData.add(gardenFound.get(j).getName() + ": " + Integer.toString(gardenFound.get(j).getQuantity()));
            }
            String x = String.join(",", plantData);

            results.setText("Choose Garden: " + gardenName + " You have chosen " + gardenName + ", " + gardenFound.get(0).getSuburb());
            results2.setText(x);
        
        
        }
      
    }

}
