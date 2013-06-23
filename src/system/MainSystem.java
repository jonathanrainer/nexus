/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import gui.MainGUI;

/**
 *
 * @author jonathanrainer
 */

public class MainSystem {
    
    private MainGUI mainGUI;
    
    public MainSystem()
    {
        mainGUI = new MainGUI();
    }
    
    public static void main(String[] args)
    {
        MainSystem mainSystem  = new MainSystem();
    }
}
