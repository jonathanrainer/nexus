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
import java.util.HashMap;
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
    
    private void addActionListenersAdminTaskSelection() {
        mainGUI.getTaskSelectionScreen().getButtons().
                get("writeNewCOTicketButton").addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControlOfficeEntryForm cofe = createLimitedEntryForm();
                
            }
        });
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
        final ControlOfficeEntryForm cofe = new ControlOfficeEntryForm(true);
        //Set the text in the two automated fields and make them unwriteable
        cofe.getTicketReferenceTextField().setText("Automated");
        cofe.getTicketReferenceTextField().setEditable(false);
        cofe.getDateTimeTextField().setText("Automated");
        cofe.getDateTimeTextField().setEditable(false);
        
        //Add to the ticket written combo box with the team the user is from
        cofe.getTicketWrittenComboBox().addItem(user.getTeam());
        cofe.getTicketWrittenComboBox().setSelectedIndex(0);
        cofe.getTicketWrittenComboBox().setEnabled(false);
        
        //Iterate over the team members to give a choice
        ArrayList<String> teamMembers = mysqlEngine.
                enumerateTeamMembers(user.getTeam());
        Iterator<String> it1 = teamMembers.iterator();
        while (it1.hasNext()){
            cofe.getTeamMembersComboBox().addItem(it1.next());
        }
        
        //Add data to first combo box to describe the problem
        ArrayList<String> mainList = new ArrayList<>();
        mainList.add("Village");
        mainList.add("Gate");
        mainList.add("Venue");
        mainList.add("Site");
        mainList.add("ShowGround");
        Iterator it2 = mainList.iterator();
        while(it2.hasNext()){
            cofe.getProblemLocationComboBox1().addItem(it2.next());
        }
        
        //Create HashMap for ComboBox 2 it should link together the string
        //selected in the previous box with the new set of strings for the next
        //combo box.
        final HashMap<String,ArrayList<String>> masterBox2 = 
                generateMasterListBox2(mainList);
        final HashMap<String,ArrayList<String>> masterBox3 = 
                generateMasterListBox3(cofe.getProblemLocationComboBox2().
                getSelectedItem());
        
            
        cofe.getProblemLocationComboBox1().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("comboBoxChanged")){
                    ArrayList<String> secondBoxOptions = masterBox2.get
                            (cofe.getProblemLocationComboBox1().getSelectedItem());
                    cofe.getProblemLocationComboBox2().removeAllItems();
                    cofe.getProblemLocationComboBox3().removeAllItems();
                    cofe.getProblemLocationComboBox4().removeAllItems();
                    Iterator<String> secondBoxIterator = secondBoxOptions.iterator();
                    while(secondBoxIterator.hasNext()){
                        cofe.getProblemLocationComboBox2().addItem(secondBoxIterator.next());
                    }
                }
            }
        });
        
        return cofe;
        
    }
    
    private HashMap<String,ArrayList<String>> generateMasterListBox2(ArrayList<String> fieldsBox1)
    {
        HashMap<String,ArrayList<String>> masterListBox2 = new HashMap<>();
        // Village Colour ArrayList
        ArrayList<String> villageColour = new ArrayList<>();
        villageColour.add("Blue");
        villageColour.add("Green");
        villageColour.add("Purple");
        villageColour.add("Red");
        villageColour.add("Yellow");
        //Gate Colour ArrayList
        ArrayList<String> gateColour = new ArrayList<>();
        gateColour.add("Blue (Outer)");
        gateColour.add("Blue (Inner)");
        gateColour.add("Green");
        gateColour.add("White (Outer)");
        gateColour.add("Red");
        gateColour.add("Yellow");
        gateColour.add("H.F.G");
        gateColour.add("Black (Ped)");
        gateColour.add("Brown (Ped)");
        //Venue Type ArrayList
        ArrayList<String> venueType = new ArrayList<>();
        venueType.add("Grown Ups");
        venueType.add("Children/Youth");
        venueType.add("General");
        // Site ArrayList
        ArrayList<String> site = new ArrayList<>();
        site.add("Car Park (Day)");
        site.add("Car Park (Main");
        site.add("Runway");
        site.add("Bays");
        site.add("BunkerBins");
        site.add("Freezer Packs");
        site.add("Hospitality");
        site.add("Kitchens");
        site.add("Ticket Office (Blue)");
        site.add("Ticket Office (Red)");
        //Showground ArrayList
        ArrayList<String> showGround = new ArrayList<>();
        showGround.add("Bandstand");
        showGround.add("Compound");
        showGround.add("Pond");
        showGround.add("SG Office");
        
        ArrayList<ArrayList<String>> listOfLists2 = new ArrayList<>();
        listOfLists2.add(villageColour);
        listOfLists2.add(gateColour);
        listOfLists2.add(venueType);
        listOfLists2.add(site);
        listOfLists2.add(showGround);
        
        int i = 0;
        while(i < listOfLists2.size())
        {
            masterListBox2.put(fieldsBox1.get(i), listOfLists2.get(i));
            i++;
        }
        
        return masterListBox2;
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
