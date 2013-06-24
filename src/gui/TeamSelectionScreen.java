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
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TeamSelectionScreen {
    
    private Template template;
    private JFrame mainFrame;
    private JLabel titleLabel;
    private JComboBox teamSelectionComboBox;
    private JButton continueButton;
    
    public TeamSelectionScreen(ArrayList<String> teamNames)
    {
        template = new Template();
        mainFrame = template.giveTemplatedJFrame("Team Login - NWNE - Nexus");
        String title = template.headingString("Please select the Team you are "
                + "part of:", 2);
        titleLabel = new JLabel(title, JLabel.CENTER);
        GridBagConstraints titleConstraints = template.
                createGridBagConstraints(0, 1, GridBagConstraints.BOTH, 100, 100, 
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1, 
                1);
        mainFrame.add(titleLabel, titleConstraints);
        
        teamSelectionComboBox = new JComboBox();
        Iterator<String> it1 = teamNames.iterator();
        while(it1.hasNext())
        {
            teamSelectionComboBox.addItem(it1.next());
        }
        GridBagConstraints comboBoxConstraints = template.
                createGridBagConstraints(0, 2, GridBagConstraints.BOTH, 20, 20, 
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1, 
                1);
        mainFrame.add(teamSelectionComboBox, comboBoxConstraints);
        
        continueButton = new JButton(template.headingString("Continue", 2));
        GridBagConstraints continueButtonConstraints = template.
                createGridBagConstraints(0, 3, GridBagConstraints.BOTH, 30, 30, 
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1, 
                1);
        mainFrame.add(continueButton,continueButtonConstraints);
    }
    
    public JButton getContinueButton()
    {
        return continueButton;
    }
    
    public JFrame getMainFrame()
    {
        return mainFrame;
    }
    
    public JComboBox getTeamComboBox()
    {
        return teamSelectionComboBox;
    }
}
