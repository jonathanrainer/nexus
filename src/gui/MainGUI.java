/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author jonathanrainer
 */
public class MainGUI {
    
    private WelcomeScreen welcomeScreen;
    private TeamSelectionScreen teamSelectionScreen;
    private ArrayList<String> teamNames;
    
    public MainGUI(ArrayList<String> teamNames)
    {
        welcomeScreen = new WelcomeScreen();
        this.teamNames = teamNames;
        addActionListeners();
    }
    
    private void addActionListeners()
    {
        welcomeScreen.getContinueButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                welcomeScreen.getMainFrame().dispose();
                teamSelectionScreen = new TeamSelectionScreen(teamNames);
            }
        }
        );
        
    }
    
}
