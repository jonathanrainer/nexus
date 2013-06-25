/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 * A class to encapsulate the functionality of the screen in the GUI where users
 * select their user group.
 *
 * @author jonathanrainer
 */
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;

public class TeamSelectionScreen {

    /**
     * The template object that cuts down on the amount of code necessary for
     * creating the GUI.
     */
    private Template template;
    /**
     * The frame into which all the other objects will be placed.
     */
    private JFrame mainFrame;
    /**
     * A JLabel to hold the title of the window.
     */
    private JLabel titleLabel;
    /**
     * A combo box to hold the teams that are possible to select.
     */
    private JComboBox teamSelectionComboBox;
    /**
     * A Button to indicate the user has selected a team and wishes to continue
     * using the application.
     */
    private JButton continueButton;
    /**
     * A basic back button.
     */
    private BasicArrowButton backButton;
    
    
    /**
     * Construct a new Team Selection Screen utilising the teams that are
     * available to select from.
     *
     * @param teamNames The list of teams that are available to be selected.
     */
    public TeamSelectionScreen(ArrayList<String> teamNames) {
        template = new Template();
        // Create the main frame with some predecided elements from the template.
        mainFrame = template.giveTemplatedJFrame("Team Login - NWNE - Nexus");
        String title = template.headingString("Please select the Team you are "
                + "part of:", 2);
        titleLabel = new JLabel(title, JLabel.CENTER);
        // Create the constraints so that when placed into the layout the 
        // elements will appear in the right place.
        GridBagConstraints titleConstraints = template.
                createGridBagConstraints(0, 1, GridBagConstraints.BOTH, 100, 100,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        mainFrame.add(titleLabel, titleConstraints);

        teamSelectionComboBox = new JComboBox();
        // Iterate over the team names and add them into the combo box
        Iterator<String> it1 = teamNames.iterator();
        while (it1.hasNext()) {
            teamSelectionComboBox.addItem(it1.next());
        }
        // Create constraints for placement of combo box
        GridBagConstraints comboBoxConstraints = template.
                createGridBagConstraints(0, 2, GridBagConstraints.BOTH, 20, 20,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        mainFrame.add(teamSelectionComboBox, comboBoxConstraints);

        // Create the continue buttons and its associated constraints.
        continueButton = new JButton(template.headingString("Continue", 2));
        GridBagConstraints continueButtonConstraints = template.
                createGridBagConstraints(0, 3, GridBagConstraints.NORTH, 40, 40,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        mainFrame.add(continueButton, continueButtonConstraints);
        
        // Create back button so that users can go back to the previous screen
        backButton = new BasicArrowButton(BasicArrowButton.WEST);
        GridBagConstraints basicArrowButtonConstraints;
        basicArrowButtonConstraints = template.createGridBagConstraints(0, 4, 
                GridBagConstraints.SOUTHWEST, 40, 40, new Insets(0, 0, 0, 0), 
                GridBagConstraints.SOUTHWEST, 0.0, 0.0, 1,1);
        mainFrame.add(backButton, basicArrowButtonConstraints);
    }

    /**
     * Get the continue button object.
     *
     * @return The continue button object.
     */
    public JButton getContinueButton() {
        return continueButton;
    }

    /**
     * Get the main frame of this window.
     *
     * @return The main frame of this window.
     */
    public JFrame getMainFrame() {
        return mainFrame;
    }

    /**
     * Get the combo box that lists the teams.
     *
     * @return The team listing combo box.
     */
    public JComboBox getTeamComboBox() {
        return teamSelectionComboBox;
    }
}
