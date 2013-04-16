/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author jonathanrainer
 */

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

public class WelcomeScreen {
    
    private JFrame mainFrame;
    private JLabel titleLabel;
    private JLabel welcomeTextLabel;
    private JButton continueButton; 
    private GridLayout oneColumnFourRows;
    private GridLayout oneRowFiveColumns;
    //private Font rockwell = new Font("ExpressWay", Font.BOLD, 14);
    
    // Lines to load in the logo 
    //ClassLoader cldr = this.getClass().getClassLoader();
    
    //java.net.URL gb_logo   = cldr.getResource("gui/gb_logo.png");
    //ImageIcon greenbeltLogo = new ImageIcon(gb_logo);
    
    public WelcomeScreen()
    {
        // Create main frame to place content into
        mainFrame = new JFrame("Welcome to NWNE - NEXUS");
        // Create the layout for the above frame
        oneColumnFourRows = new GridLayout(4,1);
        // Set the layout and size of the window
        mainFrame.setLayout(oneColumnFourRows);
        mainFrame.setSize(600,800);
        // Create the text to go in the title 
        String title = "<html><h1><b>Welcome to NWNE - NEXUS</b></h1><br>"
                + "<h3>Project Version - Kuuga (1.0)</h3></html>";
        // Create the label itself, adding in the text
        titleLabel = new JLabel(title, JLabel.CENTER);
        //Add in the logo here
        
        // Create the text to fill out the rest of the welcome screen
        String welcomeText = "<html>We have tried to keep this system as simple"
                + " as we can; it is a work in progress.<br>"
                + "For now, you can: </font></p> <ul>"
                + "<li> Raise Job-Tickets for Control Office to Manage,and</li>"
                + "<li> Send Work-Requests to the Admin Team</font></li></ul>"
                + "<br>(Some users can amend/update the information after it "
                + "has been submitted, it depends on their role)<br>"
                + "<ul><li> To help you to get to what you want to do, "
                + "we need to ask you - Two Questions..."
                + "<li> The answer you give to each question determines the "
                + "next question and the action you can (or cannot) take..."
                + "</ul><br>";
        // Create the label itself
        welcomeTextLabel = new JLabel(welcomeText, JLabel.CENTER);
        // Create the continue button
        continueButton = new JButton("<html><h2><b>Continue"
                + "</b></h2></html>");
        //continueButton.setFont(rockwell);
        mainFrame.add(titleLabel);
        mainFrame.add(welcomeTextLabel);
        mainFrame.add(continueButton);
        mainFrame.setVisible(true);        
    }
    
}
