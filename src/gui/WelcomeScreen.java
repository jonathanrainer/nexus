/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author jonathanrainer
 */

import java.awt.GridLayout;
import javax.swing.*;

public class WelcomeScreen {
    
    private JFrame mainFrame;
    private JPanel contentPanel;
    private JLabel title;
    private JLabel welcomeText;
    private GridLayout oneColumnFiveRows;
    private GridLayout oneRowFiveColumns;
    
    public WelcomeScreen()
    {
        // Create main frame to place content into
        mainFrame = new JFrame("Welcome to NWNE - NEXUS");
        // Create the layout for the above frame
        oneColumnFiveRows = new GridLayout(5,1);
        mainFrame.setLayout(oneColumnFiveRows);
        mainFrame.setSize(700,875);
        title = new JLabel("<html><h1><b>Welcome to NWNE - NEXUS</b>"
                + "</h1></html>", JLabel.CENTER);
        mainFrame.add(title);
        mainFrame.setVisible(true);        
    }
    
}
