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
    private JLabel titleLabel;
    private JLabel welcomeTextLabel;
    private GridLayout oneColumnFiveRows;
    private GridLayout oneRowFiveColumns;
    private String lineBreak = System.getProperty("line.separator");
    
    public WelcomeScreen()
    {
        // Create main frame to place content into
        mainFrame = new JFrame("Welcome to NWNE - NEXUS");
        // Create the layout for the above frame
        oneColumnFiveRows = new GridLayout(5,1);
        mainFrame.setLayout(oneColumnFiveRows);
        mainFrame.setSize(700,875);
        String title = "<html><h1><b>Welcome to NWNE - NEXUS</b></h1></html>";
        titleLabel = new JLabel(title, JLabel.CENTER);
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
                + "next question and the action you can (or cannot) take...";
        welcomeTextLabel = new JLabel(welcomeText, JLabel.CENTER);
        mainFrame.add(titleLabel);
        mainFrame.add(welcomeTextLabel);
        mainFrame.setVisible(true);        
    }
    
}
