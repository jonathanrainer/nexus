/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import gui.ControlOfficeEntryForm;
import gui.InitialGUI;
import gui.MainGUI;
import io.MYSQLEngine;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The highest level of the system as a whole, all major functionality will,
 * at least in principle, be controlled from here.
 * @author jonathanrainer
 */

public class MainSystem {
    
    private MYSQLEngine mysqlEngine;
    private ArrayList<String> teamNames;
    private InitialGUI initialGUI;
    private MainGUI mainGUI;
    private User user;
    
    /**
     * Construct all the components of the Main System and such that it can have
     * control over them.
     */
    public MainSystem()
    {
       mysqlEngine = new MYSQLEngine("localhost", "Nexus","nexus","nexus2713");
       teamNames = mysqlEngine.enumerateTeamNames();
       initialGUI = new InitialGUI(teamNames);
       addActionListenersInitialGUI();
    }
    
    /**
     * Construct the Main System and then set the first GUI window so it's
     * visible.
     * @param args Any command line arguments
     */
    public static void main(String[] args)
    {
        MainSystem mainSystem  = new MainSystem(); 
        mainSystem.getInitialGUI().getWelcomeScreen().getMainFrame().setVisible(true);
    }
    
    /**
     * Add ActionListeners to the GUI elements that require them. All
     * ActionListeners are declared in-line rather than being written as 
     * separate classes. Any larger ActionListeners are declared in separate
     * classes.
     */
    private void addActionListenersInitialGUI()
    {
        /**
         * Add an ActionListener to the continue button on the welcome screen. 
         * Simply moves the system on a stage.
         */
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
        
        /**
         * Add an ActionListener to the continue button on the Team Selection 
         * screen. This creates a user object and then begins to construct the
         * underlying system properly.
         */
        initialGUI.getTeamSelectionScreen().getContinueButton().addActionListener
                (new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                user = new User(initialGUI.getTeamSelectionScreen().
                        getTeamComboBox().getSelectedItem().toString());
                initialGUI.getTeamSelectionScreen().getMainFrame().dispose();
                mainGUI = new MainGUI(user.getTeam(), teamNames);
                addActionListenersMainGUI();
            }
        }
                );
        
        initialGUI.getTeamSelectionScreen().getBackButton().addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        initialGUI.getTeamSelectionScreen().getMainFrame().
                                dispose();
                        initialGUI.getWelcomeScreen().getMainFrame().setVisible
                                (true);
                    }
                });
       
    }
    
    private void addActionListenersMainGUI()
    {
        switch(user.getTeam()){
            case "Administration Team":
                addActionListenersAdminTaskSelection();
                break;
            case "Information Team":
                addActionListenersInfoTaskSelection();
                break;   
            case "Control Office":
                addActionListenersControlOfficeTaskSelection();
                break;    
        }
    }
    
    private void addActionListenersAdminTaskSelection(){
        mainGUI.getTaskSelectionScreen().getButtons().
                get("writeNewCOTicketButton").addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        ControlOfficeEntryForm cofe = createLimitedEntryForm();
                    }
                }
                );
    }
    
    private void addActionListenersInfoTaskSelection(){
        mainGUI.getTaskSelectionScreen().getButtons().
                get("writeNewCOTicketButton").addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        System.out.println("Place Holder");
                    }
                }
                );
    }
    
    private void addActionListenersControlOfficeTaskSelection(){
        mainGUI.getTaskSelectionScreen().getButtons().
                get("writeNewCOTicketButton").addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        System.out.println("Place Holder");
                    }
                }
                );
    }
    
    /**
     * Create the form, with some data prefilled, so that it can be entered into
     * the database.
     * @return The form ready to be filled in
     */
    private ControlOfficeEntryForm createLimitedEntryForm()
    {
        ControlOfficeEntryForm cofe = new ControlOfficeEntryForm(true);
        cofe.getTicketReferenceTextField().setText("Automated");
        cofe.getDateTimeTextField().setText("Automated");
        cofe.getTicketWrittenComboBox().addItem(user.getTeam());
        cofe.getTicketWrittenComboBox().setSelectedIndex(0);
        ArrayList<String> teamMembers = mysqlEngine.
                enumerateTeamMembers(user.getTeam());
        Iterator<String> it1 = teamMembers.iterator();
        while (it1.hasNext()){
            cofe.getTeamMembersComboBox().addItem(it1.next());
        }
        return cofe;
        
    }
    
    public InitialGUI getInitialGUI() {
        return initialGUI;
    }
    
    
    
    /**
     * Return the user object from the Main System.
     * @return The User object.
     */
    private User getUser()
    {
        return user;
    }
}
