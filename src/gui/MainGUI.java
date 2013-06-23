/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jonathanrainer
 */
public class MainGUI {
    
    private WelcomeScreen welcomeScreen;
    private TeamSelectionScreen teamSelectionScreen;
    
    public MainGUI()
    {
        welcomeScreen = new WelcomeScreen();
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
                teamSelectionScreen = new TeamSelectionScreen();
            }
        }
        );
        
    }
    
}
