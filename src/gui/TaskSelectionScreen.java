/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jonathanrainer
 */
public class TaskSelectionScreen {

    private JFrame mainFrame;
    private JPanel frameContent;
    private Template template;
    private JLabel titleLabel;
    private HashMap<String,JButton> buttons;

    public TaskSelectionScreen(String team) {
        buttons = new HashMap<>();
        template = new Template();
        mainFrame = template.giveTemplatedJFrame("Task Selection - " + team
                + "- NWNE - Nexus");
        frameContent = template.giveMenuTemplatedJPanel("Task Selection Screen");
        mainFrame.add(frameContent);
        String title;
        title = template.headingString("Please select the task you wish to "
                + "perform:", 2);
        titleLabel = new JLabel(title, JLabel.CENTER);
        GridBagConstraints titleConstraints = template.
                createGridBagConstraints(0, 2, GridBagConstraints.BOTH, 100, 100,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        frameContent.add(titleLabel, titleConstraints);
        switch (team) {
            case "Administration Team":
                adminTeamTasks(frameContent);
                break;
            case "Control Office":
                controlOfficeTasks(frameContent);
                break;
            case "Information Team":
                infoTeamTasks(frameContent);
                break;
        }
        mainFrame.pack();
    }

    private JPanel adminTeamTasks(JPanel frameContent) {
        JButton writeNewATTicketButton;
        writeNewATTicketButton = new JButton(template.headingString(
                "Write New Administration Team Job Ticket", 2));
        GridBagConstraints writeNewATTicketButtonConstraints = template.
                createGridBagConstraints(0, 3, GridBagConstraints.BOTH, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        buttons.put("writeNewATTicketButton", writeNewATTicketButton);
        frameContent.add(writeNewATTicketButton, writeNewATTicketButtonConstraints);
        JButton writeNewCOTicketButton;
        writeNewCOTicketButton = new JButton(template.headingString(
                "Write New Control Office Job Ticket", 2));
        GridBagConstraints writeNewCOTicketButtonConstraints = template.
                createGridBagConstraints(0, 4, GridBagConstraints.BOTH, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        buttons.put("writeNewCOTicketButton", writeNewCOTicketButton);
        frameContent.add(writeNewCOTicketButton, writeNewCOTicketButtonConstraints);
        JButton viewAmendAOTicketButton;
        viewAmendAOTicketButton = new JButton(template.headingString(
                "View/Amend Administration Team Job Tickets", 2));
        GridBagConstraints viewAmendAOTicketButtonConstraints = template.
                createGridBagConstraints(0, 5, GridBagConstraints.BOTH, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        buttons.put("viewAmendAOTicketButton", viewAmendAOTicketButton);
        frameContent.add(viewAmendAOTicketButton, viewAmendAOTicketButtonConstraints);
        JButton logoutButton;
        logoutButton = new JButton(template.headingString(
                "Logout", 2));
        GridBagConstraints logoutButtonConstraints = template.
                createGridBagConstraints(0, 6, GridBagConstraints.BOTH, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        buttons.put("Logout", logoutButton);
        frameContent.add(logoutButton, logoutButtonConstraints);
        return frameContent;
    }

    private JPanel controlOfficeTasks(JPanel frameContent) {
        JButton writeNewATTicketButton;
        writeNewATTicketButton = new JButton(template.headingString(
                "Write New Administration Team Job Ticket", 2));
        GridBagConstraints writeNewATTicketButtonConstraints = template.
                createGridBagConstraints(0, 3, GridBagConstraints.BOTH, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        buttons.put("writeNewATTicketButton",writeNewATTicketButton);
        frameContent.add(writeNewATTicketButton, writeNewATTicketButtonConstraints);
        JButton writeNewCOTicketButton;
        writeNewCOTicketButton = new JButton(template.headingString(
                "Write New Control Office Job Ticket", 2));
        GridBagConstraints writeNewCOTicketButtonConstraints = template.
                createGridBagConstraints(0, 4, GridBagConstraints.BOTH, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        buttons.put("writeNewCOTicketButton", writeNewCOTicketButton);
        frameContent.add(writeNewCOTicketButton, writeNewCOTicketButtonConstraints);
        JButton viewAmendCOTicketButton;
        viewAmendCOTicketButton = new JButton(template.headingString(
                "View/Amend Control Office Job Tickets", 2));
        GridBagConstraints viewAmendCOTicketButtonConstraints = template.
                createGridBagConstraints(0, 5, GridBagConstraints.BOTH, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        buttons.put("viewAmendCOTicketButton", viewAmendCOTicketButton);
        frameContent.add(viewAmendCOTicketButton, viewAmendCOTicketButtonConstraints);
        JButton logoutButton;
        logoutButton = new JButton(template.headingString(
                "Logout", 2));
        GridBagConstraints logoutButtonConstraints = template.
                createGridBagConstraints(0, 6, GridBagConstraints.BOTH, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        buttons.put("Logout", logoutButton);
        frameContent.add(logoutButton, logoutButtonConstraints);
        return frameContent;
    }

    private JPanel infoTeamTasks(JPanel frameContent) {
        JButton writeNewATTicketButton;
        writeNewATTicketButton = new JButton(template.headingString(
                "Write New Administration Team Job Ticket", 2));
        GridBagConstraints writeNewATTicketButtonConstraints = template.
                createGridBagConstraints(0, 3, GridBagConstraints.BOTH, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        buttons.put("writeNewATTicketButton", writeNewATTicketButton);
        frameContent.add(writeNewATTicketButton, writeNewATTicketButtonConstraints);
        JButton writeNewCOTicketButton;
        writeNewCOTicketButton = new JButton(template.headingString(
                "Write New Control Office Job Ticket", 2));
        GridBagConstraints writeNewCOTicketButtonConstraints = template.
                createGridBagConstraints(0, 4, GridBagConstraints.BOTH, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        buttons.put("writeNewCOTicketButton", writeNewCOTicketButton);
        frameContent.add(writeNewCOTicketButton, writeNewCOTicketButtonConstraints);
        JButton logoutButton;
        logoutButton = new JButton(template.headingString(
                "Logout", 2));
        GridBagConstraints logoutButtonConstraints = template.
                createGridBagConstraints(0, 6, GridBagConstraints.BOTH, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        buttons.put("Logout", logoutButton);
        frameContent.add(logoutButton, logoutButtonConstraints);
        return frameContent;
    }

    /**
     * Returns the main frame into which all the content is placed so other 
     * methods can access the parts of the GUI they need.
     * @return The main JFrame for this part of the GUI.
     */
    public JFrame getMainFrame() {
        return mainFrame;
    }
    
    /**
     * A method to return the ArrayList of buttons accessible to the user.
     * @return The ArrayList of buttons accessible to the user.
     */
    public HashMap<String,JButton> getButtons() {
        return buttons;
    }
    
}
