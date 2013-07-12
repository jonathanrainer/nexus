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
public class MainGUI {

 
    
    private TaskSelectionScreen taskSelectionScreen;
    private ArrayList<String> teamNames;
    
    public MainGUI(String team, ArrayList<String> teamNames)
    {
        taskSelectionScreen = new TaskSelectionScreen(team);
        taskSelectionScreen.getMainFrame().setVisible(true);
    }
    
    /**
     * Gets the Task Selection screen as a whole object
     * @return The Task Selection Screen object
     */
    public TaskSelectionScreen getTaskSelectionScreen() {
        return taskSelectionScreen;
    }
    
}
