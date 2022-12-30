import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CelciusConverter implements ActionListener
{
    private JLabel celciusLabel;
    private String[] choices = {"Celcius to Fahrenheit" , "Fahrenheit to Celcius"};
    private JComboBox<String> comboBox;
    private JButton button;
        
    public static void main(String[] args)
    {
        new CelciusConverter();
    }
    
    public CelciusConverter()
    {   
        //Creating JFrame
        JFrame frame = new JFrame("Celcius Converter");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setVisible(true);
        
        //Creating Label
        celciusLabel = new JLabel("Celcius");
        
        //Create JComboBox
        comboBox = new JComboBox<String>(choices);
        comboBox.setSize(50,150);
        comboBox.addActionListener(this);
        
        //Set Layout
        frame.setLayout(new FlowLayout());        
        frame.add(comboBox);
        frame.add(celciusLabel);
        
        
        
    }
    
    public void actionPerformed(ActionEvent e)
    {
    }
}

         
