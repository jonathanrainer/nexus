/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author jonathanrainer
 */
public class MainGUI {
    
    private TaskSelectionScreen taskSelectionScreen;
    
    public MainGUI(String team)
    {
        taskSelectionScreen = new TaskSelectionScreen(team);
        taskSelectionScreen.getMainFrame().setVisible(true);
    }
    
}
