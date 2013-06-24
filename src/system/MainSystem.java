/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import gui.InitialGUI;
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
    private InitialGUI initialGUI;
    
    public MainSystem()
    {
        mysqlEngine = new MYSQLEngine("localhost", "Nexus","nexus","nexus2713");
        teamNames = mysqlEngine.enumerateTeamNames();
        initialGUI = new InitialGUI(teamNames);
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
        initialGUI.getWelcomeScreen().getContinueButton().addActionListener
                (new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                initialGUI.getWelcomeScreen().getMainFrame().dispose();
                initialGUI.getTeamSelectionScreen().getMainFrame().setVisible(true);
            }
        }
        );
        
        initialGUI.getTeamSelectionScreen().getContinueButton().addActionListener
                (new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                initialGUI.getTeamSelectionScreen().getMainFrame().dispose();
                
            }
        });
       
    }
    
    private InitialGUI getMainGUI()
    {
        return initialGUI;
    }
}
