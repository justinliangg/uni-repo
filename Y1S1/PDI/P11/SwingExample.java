import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingExample implements ActionListener
{   
    private JLabel label;
    private JButton button;

    public static void main(String[] args)
    {
        SwingExample e = new SwingExample();
    }
    
    public SwingExample()
    {
        JFrame frame = new JFrame("Swing Example");
        
        frame.setSize(330,330);

        frame.setLayout(null);

        frame.setVisible(true);


        //Creating JLabel
        label = new JLabel("Hello World");
        label.setLocation(50,50);
        label.setSize(150,50);
        frame.add(label);

        //Creating Button
        button = new JButton("Click Me");
        button.setLocation(50,200);
        button.setSize(150,50);
        frame.add(button);
        button.addActionListener(this);
        
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(label.getText().equals("Hello World"))
        {
            label.setText("Goodbye World");
        }

        else if (label.getText().equals("Goodbye World"))
        {
            label.setText("Hello World");   
        }
    }

    
}
