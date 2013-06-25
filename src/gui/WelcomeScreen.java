/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 * The first screen that will be seen when the Nexus application is started. 
 * Really only for informative purposes and not much functionality is contained
 * herein. 
 * @author jonathanrainer
 */

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.*;

public class WelcomeScreen {
    
    private JFrame mainFrame;
    private JLabel titleLabel;
    private JLabel subtitleLabel;
    private JLabel welcomeTextLabel;
    private JButton continueButton;
    private Template template;
    // The code below can be included if you want to change the font that
    // appears on buttons etc.
    //private Font rockwell = new Font("ExpressWay", Font.BOLD, 14);
    
    /**
     * Construct a welcome screen object. No need for parameters in as form,
     * in this case, is more important than function.
     */
    public WelcomeScreen()
    {
        template = new Template();
        // Create mainv templated frame to place content into
        mainFrame = template.giveTemplatedJFrame("Welcome to NWNE - NEXUS");
        // Create the text to go in the title 
        String title;
        title = template.headingString("Welcome to NWNE - NEXUS", 1);
        String subtitle;
        subtitle = template.headingString("Project Version 1.0 (Kuuga)",3);
        // Create the label itself, adding in the text
        titleLabel = new JLabel(title, JLabel.CENTER);
        subtitleLabel = new JLabel(subtitle,JLabel.CENTER);
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
        continueButton = new JButton(template.headingString(
                "Continue</b></h2></html>",2));
        // Can be included if you wish to change the button font.
        // continueButton.setFont(rockwell);
        // Create the various constraints such that the parts are laid out 
        // correctly
        GridBagConstraints titleConstraints = template.
                createGridBagConstraints(0, 1, GridBagConstraints.BOTH, 0, 0, 
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1, 
                1);
        mainFrame.add(titleLabel, titleConstraints);
        GridBagConstraints subtitleConstraints = template.
                createGridBagConstraints(0, 2, GridBagConstraints.BOTH, 0, 0, 
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1, 
                1);
        mainFrame.add(subtitleLabel, subtitleConstraints);
        GridBagConstraints welcomeTextConstraints = template.
                createGridBagConstraints(0, 3, GridBagConstraints.BOTH, 0, 0, 
                new Insets(0, 0, 0, 0),GridBagConstraints.CENTER, 0.0, 0.0, 1, 
                1);
        mainFrame.add(welcomeTextLabel, welcomeTextConstraints);
        GridBagConstraints continueButtonConstraints = template.
                createGridBagConstraints(0, 4, GridBagConstraints.BOTH, 0, 0, 
                new Insets(0, 0, 0, 0),GridBagConstraints.CENTER, 0.0, 0.0, 1, 
                1);
        mainFrame.add(continueButton, continueButtonConstraints);
    }
    
    /**
     * Get the continue button.
     * @return The continue button.
     */
    public JButton getContinueButton()
    {
        return continueButton;
    }
   
    /**
     * Get the main frame.
     * @return The main frame.
     */
    public JFrame getMainFrame()
    {
        return mainFrame;
    }
}
