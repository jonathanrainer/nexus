/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import gui.MainGUI;
import io.MYSQLEngine;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author jonathanrainer
 */

public class MainSystem {
    
    private MYSQLEngine mysqlEngine;
    private ArrayList<String> teamNames;
    private MainGUI mainGUI;
    
    public MainSystem()
    {
        mysqlEngine = new MYSQLEngine("localhost", "Nexus","nexus","nexus2713");
        teamNames = mysqlEngine.enumerateTeamNames();
        mainGUI = new MainGUI(teamNames);
        addActionListeners();
    }
    
    public static void main(String[] args)
    {
        MainSystem mainSystem  = new MainSystem(); 
        mainSystem.getMainGUI().getWelcomeScreen().getMainFrame().setVisible
                (true);
    }
    
    private void addActionListeners()
    {
        mainGUI.getWelcomeScreen().getContinueButton().addActionListener
                (new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mainGUI.getWelcomeScreen().getMainFrame().dispose();
                mainGUI.getTeamSelectionScreen().getMainFrame().setVisible(true);
            }
        }
        );
        
        mainGUI.getTeamSelectionScreen().getContinueButton().addActionListener
                (new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mainGUI.getTeamSelectionScreen().getMainFrame().dispose();
                
            }
        });
       
    }
    
    private MainGUI getMainGUI()
    {
        return mainGUI;
    }
}
