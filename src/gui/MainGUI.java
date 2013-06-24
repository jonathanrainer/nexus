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
    
    public MainGUI(ArrayList<String> teamNames)
    {
        welcomeScreen = new WelcomeScreen();
        teamSelectionScreen = new TeamSelectionScreen(teamNames);
    }

     public WelcomeScreen getWelcomeScreen() 
     {
        return welcomeScreen;
     }

    public TeamSelectionScreen getTeamSelectionScreen() 
    {
        return teamSelectionScreen;
    }
    
    
}
