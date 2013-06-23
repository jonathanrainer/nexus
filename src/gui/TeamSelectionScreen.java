/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author jonathanrainer
 */

import gui.Template;
import javax.swing.JFrame;

public class TeamSelectionScreen {
    
    private Template template;
    private JFrame mainFrame;
    
    public TeamSelectionScreen()
    {
        template = new Template();
        mainFrame = template.giveTemplatedJFrame("Team Login - NWNE - Nexus");
        mainFrame.setVisible(true);
    }
}
