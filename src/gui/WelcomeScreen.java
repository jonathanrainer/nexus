/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author jonathanrainer
 */

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.*;

public class WelcomeScreen {
    
    private JFrame mainFrame;
    private JLabel titleLabel;
    private JLabel subtitleLabel;
    private JLabel welcomeTextLabel;
    private JPanel titlePanel;
    private JLabel logo;
    private JButton continueButton;
    private GridBagLayout layout;
    //private Font rockwell = new Font("ExpressWay", Font.BOLD, 14);
    
    // Lines to load in the logo 
    ClassLoader cldr = this.getClass().getClassLoader();
    
    java.net.URL nwlogo   = cldr.getResource("gui/nwlogo.png");
    ImageIcon newWineLogo = new ImageIcon(nwlogo);
    
    public WelcomeScreen()
    {
        // Create main frame to place content into
        mainFrame = new JFrame("Welcome to NWNE - NEXUS");
        // Create the layout for the above frame
        layout = new GridBagLayout();
        // Set the layout and size of the window
        mainFrame.setLayout(layout);
        mainFrame.setSize(600,800);
        // Create the text to go in the title 
        String title = "<html><h1><b>Welcome to NWNE - NEXUS</b></h1></html>";
        // Create the layout to layout the titles
        String subtitle = "<html><h3><b> Project Version 1.0 (Kuuga)</b></h3>"
                + "</html>";
        // Create the label itself, adding in the text
        titleLabel = new JLabel(title, JLabel.CENTER);
        subtitleLabel = new JLabel(subtitle,JLabel.CENTER);
        //Add in the logo here
        logo = new JLabel(newWineLogo);
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
        continueButton = new JButton("<html><h2><b>Continue</b></h2></html>");
        //continueButton.setFont(rockwell);
        GridBagConstraints logoConstraints = createGridBagConstraints(
                0, 0, GridBagConstraints.BOTH, 0, 0, new Insets(0,0,0,0), 
                GridBagConstraints.CENTER, 0.0, 0.0, 1, 1);
        mainFrame.add(logo, logoConstraints);
        GridBagConstraints titleConstraints = createGridBagConstraints(
                0, 1, GridBagConstraints.BOTH, 0, 0, new Insets(0,0,0,0), 
                GridBagConstraints.CENTER, 0.0, 0.0, 1, 1);
        mainFrame.add(titleLabel, titleConstraints);
        GridBagConstraints subtitleConstraints = createGridBagConstraints(
                0, 2, GridBagConstraints.BOTH, 0, 0, new Insets(0,0,0,0), 
                GridBagConstraints.CENTER, 0.0, 0.0, 1, 1);
        mainFrame.add(subtitleLabel,subtitleConstraints);
        GridBagConstraints welcomeTextConstraints = createGridBagConstraints(
                0, 3, GridBagConstraints.BOTH, 0, 0, new Insets(0,0,0,0), 
                GridBagConstraints.CENTER, 0.0, 0.0, 1, 1);
        mainFrame.add(welcomeTextLabel,welcomeTextConstraints);
        GridBagConstraints continueButtonConstraints = createGridBagConstraints(
                0, 4, GridBagConstraints.BOTH, 0, 0, new Insets(0,0,0,0), 
                GridBagConstraints.CENTER, 0.0, 0.0, 1, 1);
        mainFrame.add(continueButton, continueButtonConstraints);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private GridBagConstraints createGridBagConstraints(int gridx, int gridy, 
            int fill, int ipadx, int ipady, Insets insets, int anchor,
            double weightx, double weighty, int gridheight, int gridwidth)
    {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.fill = fill;
        constraints.ipadx = ipadx;
        constraints.ipady = ipady;
        constraints.insets = insets;
        constraints.anchor = anchor;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.gridheight = gridheight;
        constraints.gridwidth = gridwidth;
        return constraints;
    }
    
}
