/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author jonathanrainer
 */
public class TaskSelectionScreen {

    private JFrame mainFrame;
    private Template template;
    private JLabel titleLabel;
    private ArrayList<JButton> buttons;

    public TaskSelectionScreen(String team) {
        buttons = new ArrayList<>();
        template = new Template();
        mainFrame = template.giveGridBagTemplatedJFrame("Task Selection - " + team
                + "- NWNE - Nexus");
        String title;
        title = template.headingString("Please select the task you wish to "
                + "perform:", 2);
        titleLabel = new JLabel(title, JLabel.CENTER);
        GridBagConstraints titleConstraints = template.
                createGridBagConstraints(0, 2, GridBagConstraints.BOTH, 100, 100,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        mainFrame.add(titleLabel, titleConstraints);
        switch (team) {
            case "Administration Team":
                adminTeamTasks(mainFrame);
                break;
            case "Control Office":
                controlOfficeTasks(mainFrame);
                break;
            case "Information Team":
                infoTeamTasks(mainFrame);
                break;
        }
    }

    private JFrame adminTeamTasks(JFrame mainFrame) {
        JButton writeNewATTicketButton;
        writeNewATTicketButton = new JButton(template.headingString(
                "Write New Administration Team Job Ticket", 2));
        GridBagConstraints writeNewATTicketButtonConstraints = template.
                createGridBagConstraints(0, 3, GridBagConstraints.BOTH, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        buttons.add(writeNewATTicketButton);
        mainFrame.add(writeNewATTicketButton, writeNewATTicketButtonConstraints);
        JButton writeNewCOTicketButton;
        writeNewCOTicketButton = new JButton(template.headingString(
                "Write New Control Office Job Ticket", 2));
        GridBagConstraints writeNewCOTicketButtonConstraints = template.
                createGridBagConstraints(0, 4, GridBagConstraints.BOTH, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        buttons.add(writeNewCOTicketButton);
        mainFrame.add(writeNewCOTicketButton, writeNewCOTicketButtonConstraints);
        JButton viewAmendAOTicketButton;
        viewAmendAOTicketButton = new JButton(template.headingString(
                "View/Amend Administration Team Job Tickets", 2));
        GridBagConstraints viewAmendAOTicketButtonConstraints = template.
                createGridBagConstraints(0, 5, GridBagConstraints.BOTH, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        buttons.add(viewAmendAOTicketButton);
        mainFrame.add(viewAmendAOTicketButton, viewAmendAOTicketButtonConstraints);
        return mainFrame;
    }

    private JFrame controlOfficeTasks(JFrame mainFrame) {
        JButton writeNewATTicketButton;
        writeNewATTicketButton = new JButton(template.headingString(
                "Write New Administration Team Job Ticket", 2));
        GridBagConstraints writeNewATTicketButtonConstraints = template.
                createGridBagConstraints(0, 3, GridBagConstraints.BOTH, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        buttons.add(writeNewATTicketButton);
        mainFrame.add(writeNewATTicketButton, writeNewATTicketButtonConstraints);
        JButton writeNewCOTicketButton;
        writeNewCOTicketButton = new JButton(template.headingString(
                "Write New Control Office Job Ticket", 2));
        GridBagConstraints writeNewCOTicketButtonConstraints = template.
                createGridBagConstraints(0, 4, GridBagConstraints.BOTH, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        buttons.add(writeNewCOTicketButton);
        mainFrame.add(writeNewCOTicketButton, writeNewCOTicketButtonConstraints);
        JButton viewAmendCOTicketButton;
        viewAmendCOTicketButton = new JButton(template.headingString(
                "View/Amend Control Office Job Tickets", 2));
        GridBagConstraints viewAmendCOTicketButtonConstraints = template.
                createGridBagConstraints(0, 5, GridBagConstraints.BOTH, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        buttons.add(viewAmendCOTicketButton);
        mainFrame.add(viewAmendCOTicketButton, viewAmendCOTicketButtonConstraints);
        return mainFrame;
    }

    private JFrame infoTeamTasks(JFrame mainFrame) {
        JButton writeNewATTicketButton;
        writeNewATTicketButton = new JButton(template.headingString(
                "Write New Administration Team Job Ticket", 2));
        GridBagConstraints writeNewATTicketButtonConstraints = template.
                createGridBagConstraints(0, 3, GridBagConstraints.BOTH, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        buttons.add(writeNewATTicketButton);
        mainFrame.add(writeNewATTicketButton, writeNewATTicketButtonConstraints);
        JButton writeNewCOTicketButton;
        writeNewCOTicketButton = new JButton(template.headingString(
                "Write New Control Office Job Ticket", 2));
        GridBagConstraints writeNewCOTicketButtonConstraints = template.
                createGridBagConstraints(0, 4, GridBagConstraints.BOTH, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        buttons.add(writeNewCOTicketButton);
        mainFrame.add(writeNewCOTicketButton, writeNewCOTicketButtonConstraints);
        return mainFrame;
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
    public ArrayList<JButton> getButtons() {
        return buttons;
    }
    
}
