/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import gui.MainGUI;
import io.MYSQLEngine;

/**
 *
 * @author jonathanrainer
 */

public class MainSystem {
    
    private MainGUI mainGUI;
    private MYSQLEngine mysqlEngine;
    
    public MainSystem()
    {
        mysqlEngine = new MYSQLEngine("localhost", "Nexus","root","");
        mainGUI = new MainGUI(mysqlEngine.enumerateTeamNames());
    }
    
    public static void main(String[] args)
    {
        MainSystem mainSystem  = new MainSystem();      
    }
}
