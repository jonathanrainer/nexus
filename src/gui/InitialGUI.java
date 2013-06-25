/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;

/**
 *
 * @author jonathanrainer
 */
public class InitialGUI {

   private WelcomeScreen welcomeScreen;
   private TeamSelectionScreen teamSelectionScreen;
    
    public InitialGUI(ArrayList<String> teamNames)
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
