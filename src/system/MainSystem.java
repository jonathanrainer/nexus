/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

/**
 *
 * @author jonathanrainer
 */

import gui.WelcomeScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainSystem {
    
    private WelcomeScreen welcomeScreen;
    
    public MainSystem()
    {
        welcomeScreen = new WelcomeScreen();
        addActionListeners();
    }
    
    public static void main(String[] args)
    {
        MainSystem mainSystem  = new MainSystem();
    }
    
    private void addActionListeners()
    {
        welcomeScreen.getContinueButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                welcomeScreen.getMainFrame().dispose();
            }
        }
        );
    }
}
