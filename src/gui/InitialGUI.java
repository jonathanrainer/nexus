/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;

/**
 * A class to encapsulate functionality for the initial GUI that will be seen by
 * users, the idea being that this exists to pick a user group before allowing
 * access to the main part of Nexus.
 *
 * @author jonathanrainer
 */
public class InitialGUI {

    /**
     * The initial welcome screen as seen by users.
     */
    private WelcomeScreen welcomeScreen;
    /**
     * The screen where users will select the team they are part of, to allow
     * partial system login since not every action will require complete login.
     */
    private TeamSelectionScreen teamSelectionScreen;

    /**
     * Construct the initial GUI by creating both a Welcome Screen and a team
     * selection screen, though the latter is initially hidden from the users 
     * perspective.
     * @param teamNames An ArrayList of the names of the teams present in the
     * database to display in the team selection screen.
     */
    public InitialGUI(ArrayList<String> teamNames) {
        welcomeScreen = new WelcomeScreen();
        teamSelectionScreen = new TeamSelectionScreen(teamNames);
    }
    
    /**
     * Return the Welcome Screen object
     * @return The welcome screen object.
     */
    public WelcomeScreen getWelcomeScreen() {
        return welcomeScreen;
    }

    /**
     * Return the Team Selection Screen object
     * @return The team selection screen.
     */
    public TeamSelectionScreen getTeamSelectionScreen() {
        return teamSelectionScreen;
    }
}
