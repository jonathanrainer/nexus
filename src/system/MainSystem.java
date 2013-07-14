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
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/**
 * The highest level of the system as a whole, all major functionality will, at
 * least in principle, be controlled from here.
 *
 * @author jonathanrainer
 */
public class MainSystem
{

    private MYSQLEngine mysqlEngine;
    private ArrayList<String> teamNames;
    private InitialGUI initialGUI;
    private MainGUI mainGUI;
    private User user;
    private ArrayList<String> masterListBox1;
    private HashMap<String, ArrayList<String>> masterListBox2;
    private HashMap<String, ArrayList<String>> masterListBox3;
    private HashMap<String, ArrayList<String>> masterListBox4;

    /**
     * Construct all the components of the Main System and such that it can have
     * control over them.
     */
    public MainSystem()
    {
        mysqlEngine = new MYSQLEngine("localhost", "Nexus", "nexus", "nexus2713");
        teamNames = mysqlEngine.enumerateTeamNames();
        initialGUI = new InitialGUI(teamNames);
        addActionListenersInitialGUI();
    }

    /**
     * Construct the Main System and then set the first GUI window so it's
     * visible.
     *
     * @param args Any command line arguments
     */
    public static void main(String[] args)
    {
        MainSystem mainSystem = new MainSystem();
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
        initialGUI.getWelcomeScreen().getContinueButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                initialGUI.getWelcomeScreen().getMainFrame().dispose();
                initialGUI.getTeamSelectionScreen().getMainFrame().setVisible(true);
            }
        });

        /**
         * Add an ActionListener to the continue button on the Team Selection
         * screen. This creates a user object and then begins to construct the
         * underlying system properly.
         */
        initialGUI.getTeamSelectionScreen().getContinueButton().addActionListener(new ActionListener()
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
        });

        initialGUI.getTeamSelectionScreen().getBackButton().addActionListener(
                new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                initialGUI.getTeamSelectionScreen().getMainFrame().
                        dispose();
                initialGUI.getWelcomeScreen().getMainFrame().setVisible(true);
            }
        });

    }

    private void addActionListenersMainGUI()
    {
        switch (user.getTeam())
        {
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

    private void addActionListenersAdminTaskSelection()
    {
        mainGUI.getTaskSelectionScreen().getButtons().
                get("writeNewCOTicketButton").addActionListener(
                new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ControlOfficeEntryForm cofe = createLimitedEntryForm();

            }
        });
    }

    private void addActionListenersInfoTaskSelection()
    {
        mainGUI.getTaskSelectionScreen().getButtons().
                get("writeNewCOTicketButton").addActionListener(
                new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Place Holder");
            }
        });
    }

    private void addActionListenersControlOfficeTaskSelection()
    {
        mainGUI.getTaskSelectionScreen().getButtons().
                get("writeNewCOTicketButton").addActionListener(
                new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Place Holder");
            }
        });
    }

    /**
     * Create the form, with some data prefilled, so that it can be entered into
     * the database.
     *
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
        while (it1.hasNext())
        {
            cofe.getTeamMembersComboBox().addItem(it1.next());
        }

        //Generate master lists
        masterListBox1 = new ArrayList<>();
        Collections.addAll(masterListBox1, "Village", "Gate", "Venue", "Site");
        Iterator it2 = masterListBox1.iterator();
        cofe.getProblemLocationComboBox1().addItem("Select a Location");
        while (it2.hasNext())
        {
            cofe.getProblemLocationComboBox1().addItem(it2.next());
        }
        generateMasterLists();
        //Create HashMap for ComboBox 2 it should link together the string
        //selected in the previous box with the new set of strings for the next
        //combo box.


        cofe.getProblemLocationComboBox1().addActionListener(
                new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (e.getActionCommand().equals("comboBoxChanged")
                        && !(cofe.getProblemLocationComboBox1().getSelectedItem().
                        equals("Select a Location")))
                {
                    ArrayList<String> secondBoxOptions = masterListBox2.get(cofe.getProblemLocationComboBox1().getSelectedItem());
                    cofe.getProblemLocationComboBox2().removeAllItems();
                    cofe.getProblemLocationComboBox3().removeAllItems();
                    cofe.getProblemLocationComboBox4().removeAllItems();
                    Iterator<String> secondBoxIterator = secondBoxOptions.iterator();
                    cofe.getProblemLocationComboBox2().addItem("Select a Location");
                    while (secondBoxIterator.hasNext())
                    {
                        cofe.getProblemLocationComboBox2().addItem(secondBoxIterator.next());
                    }
                    cofe.getProblemLocationComboBox2().setSelectedIndex(0);
                }
            }
        });

        cofe.getProblemLocationComboBox2().addActionListener(
                new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (e.getActionCommand().equals("comboBoxChanged"))
                {
                    if (!(cofe.getProblemLocationComboBox2().
                            getSelectedItem() == null)
                            && !(cofe.getProblemLocationComboBox2().
                            getSelectedItem().equals("Select a Location")))
                    {
                        ArrayList<String> thirdBoxOptions = masterListBox3.get(
                                cofe.getProblemLocationComboBox2().getSelectedItem());
                        cofe.getProblemLocationComboBox3().removeAllItems();
                        cofe.getProblemLocationComboBox4().removeAllItems();
                        Iterator<String> thirdBoxIterator = thirdBoxOptions.iterator();
                        if (!(thirdBoxOptions.size() == 1))
                        {
                            cofe.getProblemLocationComboBox3().addItem("Select a Location");
                        }
                        while (thirdBoxIterator.hasNext())
                        {
                            cofe.getProblemLocationComboBox3().addItem(thirdBoxIterator.next());
                        }

                    }

                }
            }
        });

        cofe.getProblemLocationComboBox3().addActionListener(
                new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (e.getActionCommand().equals("comboBoxChanged"))
                {
                    if (!(cofe.getProblemLocationComboBox3().
                            getSelectedItem() == null
                            || cofe.getProblemLocationComboBox3().
                            getSelectedItem().equals("Select a Location")))
                    {
                        ArrayList<String> fourthBoxOptions = new ArrayList<>();
                        if (cofe.getProblemLocationComboBox3().getSelectedItem().equals("NA"))
                        {
                            fourthBoxOptions =
                                    masterListBox4.get(cofe.
                                    getProblemLocationComboBox2().
                                    getSelectedItem() + "-"
                                    + cofe.getProblemLocationComboBox3().
                                    getSelectedItem());
                        } else
                        {
                            fourthBoxOptions =
                                    masterListBox4.get(cofe.getProblemLocationComboBox3()
                                    .getSelectedItem());
                        }
                        cofe.getProblemLocationComboBox4().removeAllItems();
                        Iterator<String> fourthBoxIterator =
                                fourthBoxOptions.iterator();
                        if (!(fourthBoxOptions.size() == 1))
                        {
                            cofe.getProblemLocationComboBox4().addItem("Select a Facility");
                        }
                        while (fourthBoxIterator.hasNext())
                        {
                            cofe.getProblemLocationComboBox4().addItem(fourthBoxIterator.next());
                        }
                    }

                }
            }
        });

        return cofe;

    }

    private ArrayList<String> generateMasterListBox2(ArrayList<String> fieldsBox1)
    {
        masterListBox2 = new HashMap<>();
        // Village Colour ArrayList
        ArrayList<String> villageColour = new ArrayList<>();
        Collections.addAll(villageColour, "Blue", "Green", "Purple", "Red", "Yellow");
        //Gate Colour ArrayList (To differentiate gates from Villages,
        //there are spaces at the end of common names
        ArrayList<String> gateColour = new ArrayList<>();
        Collections.addAll(gateColour, "Blue (Outer)", "Blue (Inner)", "Green ",
                "White (Outer)", "White (Inner)", "Red ", "Yellow ", "H.F.G",
                "Black (Ped)", "Brown (Ped)");
        //Venue Type ArrayList
        ArrayList<String> venueType = new ArrayList<>();
        Collections.addAll(venueType, "Grown Ups", "Children/Youth", "General");
        // Site ArrayList
        ArrayList<String> site = new ArrayList<>();
        Collections.addAll(site, "Car Park (Day)", "Car Park (Main)", "Runway",
                "Bays", "BunkerBins", "Hallam Building", "Freezer Packs",
                "Hospitality", "Kitchens", "Ticket Office (Blue)",
                "Ticket Office (Red)", "Band Stand", "Fencing", "Pond");

        ArrayList<ArrayList<String>> listOfLists = new ArrayList<>();
        Collections.addAll(listOfLists, villageColour, gateColour, venueType,
                site);

        ArrayList<String> listOfAllPotentialItems = new ArrayList<>();

        int i = 0;
        while (i < listOfLists.size())
        {
            masterListBox2.put(fieldsBox1.get(i), listOfLists.get(i));
            listOfAllPotentialItems.addAll(listOfLists.get(i));
            i++;
        }

        return listOfAllPotentialItems;


    }

    private ArrayList<String> generateMasterListBox3(ArrayList<String> fieldsBox2)
    {
        masterListBox3 = new HashMap<>();
        //Villages Options
        ArrayList<String> blue = new ArrayList<>();
        Collections.addAll(blue, "Blue 1", "Blue 2", "Blue 3", "Blue 4", "Blue 5",
                "Blue 6", "Blue 7", "Blue 8", "Blue 9", "Blue 10");
        ArrayList<String> green = new ArrayList<>();
        Collections.addAll(green, "Green 1", "Green 2", "Green 3", "Green 4",
                "Green 5", "Green 6", "Green 7");
        ArrayList<String> purple = new ArrayList<>();
        Collections.addAll(purple, "Purple 1", "Purple 2", "Purple 3", "Purple 4");
        ArrayList<String> red = new ArrayList<>();
        Collections.addAll(red, "Red 1", "Red 2", "Red 3", "Red 4", "Red 5", "Red 6",
                "Red 7");
        ArrayList<String> yellow = new ArrayList<>();
        Collections.addAll(yellow, "Yellow 0", "Yellow 1", "Yellow 2", "Yellow 3",
                "Yellow 4", "Yellow 5", "Yellow 6");

        //Gates Options
        ArrayList<String> nextBox = new ArrayList<>();
        nextBox.add("NA");

        //Adults options
        ArrayList<String> adultsVenues = new ArrayList<>();
        Collections.addAll(adultsVenues, "Meeting Place (inc. Café)",
                "Impact", "Burn", "Leaders' Lounge", "Pastoral Prayer",
                "Sanctuary", "Arts 3:16", "Manifest", "Renovate", "Synergy",
                "Upper Room", "Tearfun (inc. Café)");
        //Childrens and Youth Options
        ArrayList<String> childrensYouthVenues = new ArrayList<>();
        Collections.addAll(childrensYouthVenues, "Gems", "Pebbles",
                "Stepping Stones", "Ground Breakers", "Rock Solid",
                "Boulder Gang", "Fridge", "Club One", "Thirst", "Flava",
                "Sports Field", "Our Place");
        //General Venues Options
        ArrayList<String> generalVenues = new ArrayList<>();
        Collections.addAll(generalVenues, "Food Court", "Marketplace (inc. Café)",
                "Medical Centre", "New Wine FM");

        //Site Options
        ArrayList<String> carParkDaySite = new ArrayList<>();
        Collections.addAll(carParkDaySite, "NA");
        ArrayList<String> carParkMainSite = new ArrayList<>();
        Collections.addAll(carParkMainSite, "NA");
        ArrayList<String> runwaySite = new ArrayList<>();
        Collections.addAll(runwaySite, "NA");
        ArrayList<String> baysSite = new ArrayList<>();
        Collections.addAll(baysSite, "Bay 1", "Bay 2", "Bay 3", "Bay 4", "Bay 5");
        ArrayList<String> bunkerBinsSite = new ArrayList<>();
        Collections.addAll(bunkerBinsSite, "End of Sheds", "Red Gate", "WigWam");
        ArrayList<String> hallamBuildingSite = new ArrayList<>();
        Collections.addAll(hallamBuildingSite, "Admin", "Control Office",
                "Finance", "Information", "Production", "Kitchen", "Team Lounge", "General");
        ArrayList<String> freezerPacksSite = new ArrayList<>();
        Collections.addAll(freezerPacksSite, "NA");
        ArrayList<String> hospitalitySite = new ArrayList<>();
        Collections.addAll(hospitalitySite, "NA");
        ArrayList<String> kitchensSite = new ArrayList<>();
        Collections.addAll(kitchensSite, "NA");
        ArrayList<String> ticketOfficeBlueSite = new ArrayList<>();
        Collections.addAll(ticketOfficeBlueSite, "NA");
        ArrayList<String> ticketOfficeRedSite = new ArrayList<>();
        Collections.addAll(ticketOfficeRedSite, "NA");
        ArrayList<String> BandStandSite = new ArrayList<>();
        Collections.addAll(BandStandSite, "NA");
        ArrayList<String> fencingSite = new ArrayList<>();
        Collections.addAll(fencingSite, "NA");
        ArrayList<String> pondSite = new ArrayList<>();
        Collections.addAll(pondSite, "NA");

        ArrayList<ArrayList<String>> listOfLists = new ArrayList<>();
        Collections.addAll(listOfLists, blue, green, purple, red, yellow,
                nextBox, nextBox, nextBox, nextBox, nextBox, nextBox,
                nextBox, nextBox, nextBox, nextBox, adultsVenues,
                childrensYouthVenues, generalVenues, carParkDaySite,
                carParkMainSite, runwaySite, baysSite, bunkerBinsSite,
                hallamBuildingSite, freezerPacksSite, hospitalitySite, kitchensSite,
                ticketOfficeBlueSite, ticketOfficeRedSite, BandStandSite, fencingSite, pondSite);

        ArrayList<String> listOfAllPotentialItems = new ArrayList<>();

        int i = 0;
        while (i < listOfLists.size())
        {
            masterListBox3.put(fieldsBox2.get(i), listOfLists.get(i));
            listOfAllPotentialItems.addAll(listOfLists.get(i));
            i++;
        }

        return listOfAllPotentialItems;
    }

    private void generateMasterListBox4(ArrayList<String> fieldsBox3, 
            ArrayList<String> fieldsBox2)
    {
        masterListBox4 = new HashMap<String,ArrayList<String>>();
        //Blue Villages ArrayLists
        ArrayList<String> blue1 = new ArrayList<>();
        Collections.addAll(blue1, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> blue2 = new ArrayList<>();
        Collections.addAll(blue2, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> blue3 = new ArrayList<>();
        Collections.addAll(blue3, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> blue4 = new ArrayList<>();
        Collections.addAll(blue4, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> blue5 = new ArrayList<>();
        Collections.addAll(blue5, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> blue6 = new ArrayList<>();
        Collections.addAll(blue6, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> blue7 = new ArrayList<>();
        Collections.addAll(blue7, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> blue8 = new ArrayList<>();
        Collections.addAll(blue8, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> blue9 = new ArrayList<>();
        Collections.addAll(blue9, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> blue10 = new ArrayList<>();
        Collections.addAll(blue10, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        // Green Villages ArrayLists
        ArrayList<String> green1 = new ArrayList<>();
        Collections.addAll(green1, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> green2 = new ArrayList<>();
        Collections.addAll(green2, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> green3 = new ArrayList<>();
        Collections.addAll(green3, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> green4 = new ArrayList<>();
        Collections.addAll(green4, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> green5 = new ArrayList<>();
        Collections.addAll(green5, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> green6 = new ArrayList<>();
        Collections.addAll(green6, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> green7 = new ArrayList<>();
        Collections.addAll(green7, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        //Purple Villages ArrayList
        ArrayList<String> purple1 = new ArrayList<>();
        Collections.addAll(purple1, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> purple2 = new ArrayList<>();
        Collections.addAll(purple2, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> purple3 = new ArrayList<>();
        Collections.addAll(purple3, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> purple4 = new ArrayList<>();
        Collections.addAll(purple4, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        //Red Villages
        ArrayList<String> red1 = new ArrayList<>();
        Collections.addAll(red1, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> red2 = new ArrayList<>();
        Collections.addAll(red2, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> red3 = new ArrayList<>();
        Collections.addAll(red3, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> red4 = new ArrayList<>();
        Collections.addAll(red4, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> red5 = new ArrayList<>();
        Collections.addAll(red5, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> red6 = new ArrayList<>();
        Collections.addAll(red6, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> red7 = new ArrayList<>();
        Collections.addAll(red7, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        //Yellow Vilages
        ArrayList<String> yellow0 = new ArrayList<>();
        Collections.addAll(yellow0, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> yellow1 = new ArrayList<>();
        Collections.addAll(yellow1, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> yellow2 = new ArrayList<>();
        Collections.addAll(yellow2, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> yellow3 = new ArrayList<>();
        Collections.addAll(yellow3, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> yellow4 = new ArrayList<>();
        Collections.addAll(yellow4, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> yellow5 = new ArrayList<>();
        Collections.addAll(yellow5, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        ArrayList<String> yellow6 = new ArrayList<>();
        Collections.addAll(yellow6, "Toilet (Male)", "Toilet (Female)", 
        "Toilet (Disabled)", "Shower", "Elsan Point", "Fire Point", "Water Supply",
        "Electrical Hook-Up", "Lighting", "ViF 9", "Other");
        
         //Gates Options
        ArrayList<String> blueOuterGate = new ArrayList<>();
        Collections.addAll(blueOuterGate, "Toilet (Male)", "Toilet(Female)", "Toilet (Disabled)", "Shower"
                , "Elsan Point", "Water Supply", "Ticket Office", "Gate", "Lighting",
                "Power Supply", "Other");
        ArrayList<String> blueInnerGate = new ArrayList<>();
        Collections.addAll(blueInnerGate, "Toilet (Male)", "Toilet(Female)", "Toilet (Disabled)", "Shower"
                , "Elsan Point", "Water Supply",  "Ticket Office", "Gate", "Lighting",
                "Power Supply", "Other");
        ArrayList<String> greenGate = new ArrayList<>();
        Collections.addAll(greenGate, "Toilet (Male)", "Toilet(Female)", "Toilet (Disabled)", "Shower"
                , "Elsan Point", "Water Supply",  "Ticket Office", "Gate", "Lighting",
                "Power Supply", "Other");
        ArrayList<String> whiteOuterGate = new ArrayList<>();
        Collections.addAll(whiteOuterGate, "Toilet (Male)", "Toilet(Female)", "Toilet (Disabled)", "Shower"
                , "Elsan Point", "Water Supply",  "Ticket Office", "Gate", "Lighting",
                "Power Supply", "Other");
        ArrayList<String> whiteInnerGate = new ArrayList<>();
        Collections.addAll(whiteInnerGate, "Toilet (Male)", "Toilet(Female)", "Toilet (Disabled)", "Shower"
                , "Elsan Point", "Water Supply",  "Ticket Office", "Gate", "Lighting",
                "Power Supply", "Other");
        ArrayList<String> redGate = new ArrayList<>();
        Collections.addAll(redGate, "Toilet (Male)", "Toilet(Female)", "Toilet (Disabled)", "Shower"
                , "Elsan Point", "Water Supply",  "Ticket Office", "Gate", "Lighting",
                "Power Supply", "Other");
        ArrayList<String> yellowGate = new ArrayList<>();
        Collections.addAll(yellowGate, "Toilet (Male)", "Toilet(Female)", "Toilet (Disabled)", "Shower"
                , "Elsan Point", "Water Supply",  "Ticket Office", "Gate", "Lighting",
                "Power Supply", "Other");
        ArrayList<String> hfgGate = new ArrayList<>();
        Collections.addAll(hfgGate, "Toilet (Male)", "Toilet(Female)", "Toilet (Disabled)", "Shower"
                , "Elsan Point", "Water Supply",  "Ticket Office", "Gate", "Lighting",
                "Power Supply", "Other");
        ArrayList<String> blackPedGate = new ArrayList<>();
        Collections.addAll(blackPedGate, "Toilet (Male)", "Toilet(Female)", "Toilet (Disabled)", "Shower"
                , "Elsan Point", "Water Supply",  "Ticket Office", "Gate", "Lighting",
                "Power Supply", "Other");
        ArrayList<String> brownPedGate = new ArrayList<>();
        Collections.addAll(brownPedGate, "Toilet (Male)", "Toilet(Female)", "Toilet (Disabled)", "Shower"
                , "Elsan Point", "Water Supply",  "Ticket Office", "Gate", "Lighting",
                "Power Supply", "Other");
        
        //Venues Options
        //Adult Venues Facilities
        ArrayList<String> meetingPlace = new ArrayList<>();
        Collections.addAll(meetingPlace, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower (Male)", "Shower (Female)", 
                "Shower (Disabled)", "Kitchen", "Stage", "Café", "Lighting",
                "Power Supply", "Other");
        ArrayList<String> impact = new ArrayList<>();
        Collections.addAll(impact, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower (Male)", "Shower (Female)", 
                "Shower (Disabled)", "Kitchen", "Stage", "Café", "Lighting",
                "Power Supply", "Other");
        ArrayList<String> burn = new ArrayList<>();
        Collections.addAll(burn, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower (Male)", "Shower (Female)", 
                "Shower (Disabled)", "Kitchen", "Stage", "Café", "Lighting",
                "Power Supply", "Other");
        ArrayList<String> leadersLounge = new ArrayList<>();
        Collections.addAll(leadersLounge, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower (Male)", "Shower (Female)", 
                "Shower (Disabled)", "Kitchen", "Stage", "Café", "Lighting",
                "Power Supply", "Other");
        ArrayList<String> pastoralPrayer = new ArrayList<>();
        Collections.addAll(pastoralPrayer, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower (Male)", "Shower (Female)", 
                "Shower (Disabled)", "Kitchen", "Stage", "Café", "Lighting",
                "Power Supply", "Other");
        ArrayList<String> sanctuary = new ArrayList<>();
        Collections.addAll(sanctuary, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower (Male)", "Shower (Female)", 
                "Shower (Disabled)", "Kitchen", "Stage", "Café", "Lighting",
                "Power Supply", "Other");
        ArrayList<String> arts316 = new ArrayList<>();
        Collections.addAll(arts316, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower (Male)", "Shower (Female)", 
                "Shower (Disabled)", "Kitchen", "Stage", "Café", "Lighting",
                "Power Supply", "Other");
        ArrayList<String> manifest = new ArrayList<>();
        Collections.addAll(manifest, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower (Male)", "Shower (Female)", 
                "Shower (Disabled)", "Kitchen", "Stage", "Café", "Lighting",
                "Power Supply", "Other");
        ArrayList<String> renovate = new ArrayList<>();
        Collections.addAll(renovate, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower (Male)", "Shower (Female)", 
                "Shower (Disabled)", "Kitchen", "Stage", "Café", "Lighting",
                "Power Supply", "Other");
        ArrayList<String> synergy = new ArrayList<>();
        Collections.addAll(synergy, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower (Male)", "Shower (Female)", 
                "Shower (Disabled)", "Kitchen", "Stage", "Café", "Lighting",
                "Power Supply", "Other");
        ArrayList<String> upperRoom = new ArrayList<>();
        Collections.addAll(upperRoom, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower (Male)", "Shower (Female)", 
                "Shower (Disabled)", "Kitchen", "Stage", "Café", "Lighting",
                "Power Supply", "Other");
        ArrayList<String> tearfund = new ArrayList<>();
        Collections.addAll(tearfund, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower (Male)", "Shower (Female)", 
                "Shower (Disabled)", "Kitchen", "Stage", "Café", "Lighting",
                "Power Supply", "Other");
        //Children/Youth Venues Facilties
        ArrayList<String> gems = new ArrayList<>();
        Collections.addAll(gems, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower", "Kitchen", "Stage", "Lighting",
                "Power Supply", "CYF 09", "Other");
        ArrayList<String> pebbles = new ArrayList<>();
        Collections.addAll(pebbles, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower", "Kitchen", "Stage", "Lighting",
                "Power Supply", "CYF 09", "Other");
        ArrayList<String> steppingStones = new ArrayList<>();
        Collections.addAll(steppingStones, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower", "Kitchen", "Stage", "Lighting",
                "Power Supply", "CYF 09", "Other");
        ArrayList<String> groundBreakers = new ArrayList<>();
        Collections.addAll(groundBreakers, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower", "Kitchen", "Stage", "Lighting",
                "Power Supply", "CYF 09", "Other");
        ArrayList<String> rockSolid = new ArrayList<>();
        Collections.addAll(rockSolid, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower", "Kitchen", "Stage", "Lighting",
                "Power Supply", "CYF 09", "Other");
        ArrayList<String> boulderGang = new ArrayList<>();
        Collections.addAll(boulderGang, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower", "Kitchen", "Stage", "Lighting",
                "Power Supply", "CYF 09", "Other");
        ArrayList<String> fridge = new ArrayList<>();
        Collections.addAll(fridge, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower", "Kitchen", "Stage", "Lighting",
                "Power Supply", "CYF 09", "Other");
        ArrayList<String> clubOne = new ArrayList<>();
        Collections.addAll(clubOne, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower", "Kitchen", "Stage", "Lighting",
                "Power Supply", "CYF 09", "Other");
        ArrayList<String> thirst = new ArrayList<>();
        Collections.addAll(thirst, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower", "Kitchen", "Stage", "Lighting",
                "Power Supply", "CYF 09", "Other");
        ArrayList<String> flava = new ArrayList<>();
        Collections.addAll(flava, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower", "Kitchen", "Stage", "Lighting",
                "Power Supply", "CYF 09", "Other");
        ArrayList<String> sportsField = new ArrayList<>();
        Collections.addAll(sportsField, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower", "Kitchen", "Stage", "Lighting",
                "Power Supply", "CYF 09", "Other");
        ArrayList<String> ourPlace = new ArrayList<>();
        Collections.addAll(ourPlace, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower", "Kitchen", "Stage", "Lighting",
                "Power Supply", "CYF 09", "Other");
        //General Venues Facilities
        ArrayList<String> foodCourt = new ArrayList<>();
        Collections.addAll(foodCourt, "NA");
        ArrayList<String> marketPlace = new ArrayList<>();
        Collections.addAll(marketPlace, "Toilet (Male)", "Toilet (Female)", 
                "Toilet (Disabled)", "Shower", "Kitchen", "Lighting",
                "Power Supply", "MPF 08", "MPF 09", "Other");
        ArrayList<String> medicalCentre = new ArrayList<>();
        Collections.addAll(medicalCentre, "NA");
        ArrayList<String> newWineFM = new ArrayList<>();
        Collections.addAll(newWineFM, "NA");
        
        //Site Facilities
        ArrayList<String> carParkDay = new ArrayList<>();
        Collections.addAll(carParkDay, "NA");
        ArrayList<String> carParkMain = new ArrayList<>();
        Collections.addAll(carParkMain, "NA");
        ArrayList<String> runway = new ArrayList<>();
        Collections.addAll(runway, "NA");
        ArrayList<String> bay1 = new ArrayList<>();
        Collections.addAll(bay1, "Bays Faciltiies");
        ArrayList<String> bay2 = new ArrayList<>();
        Collections.addAll(bay2, "Bays Faciltiies");
        ArrayList<String> bay3 = new ArrayList<>();
        Collections.addAll(bay3, "Bays Faciltiies");
        ArrayList<String> bay4 = new ArrayList<>();
        Collections.addAll(bay4, "Bays Faciltiies");
        ArrayList<String> bay5 = new ArrayList<>();
        Collections.addAll(bay5, "Bays Faciltiies");
        ArrayList<String> endOfShedsBB = new ArrayList<>();
        Collections.addAll(endOfShedsBB, "Toilet", "Shower");
        ArrayList<String> redGateBB = new ArrayList<>();
        Collections.addAll(redGateBB, "Toilet", "Shower");
        ArrayList<String> wigWamBB = new ArrayList<>();
        Collections.addAll(wigWamBB, "Toilet", "Shower");
        ArrayList<String> admin = new ArrayList<>();
        Collections.addAll(admin, "Toilet (Male)", "Toilet(Female)",
                "Toilet (Disabled)", "Shower (Male)", "Shower (Female)");
        ArrayList<String> controlOffice = new ArrayList<>();
        Collections.addAll(controlOffice, "Toilet (Male)", "Toilet(Female)",
                "Toilet (Disabled)", "Shower (Male)", "Shower (Female)");
        ArrayList<String> finance = new ArrayList<>();
        Collections.addAll(finance, "Toilet (Male)", "Toilet(Female)",
                "Toilet (Disabled)", "Shower (Male)", "Shower (Female)");
        ArrayList<String> information = new ArrayList<>();
        Collections.addAll(information, "Toilet (Male)", "Toilet(Female)",
                "Toilet (Disabled)", "Shower (Male)", "Shower (Female)");
        ArrayList<String> production = new ArrayList<>();
        Collections.addAll(production, "Toilet (Male)", "Toilet(Female)",
                "Toilet (Disabled)", "Shower (Male)", "Shower (Female)");
        ArrayList<String> kitchen = new ArrayList<>();
        Collections.addAll(kitchen, "Toilet (Male)", "Toilet(Female)",
                "Toilet (Disabled)", "Shower (Male)", "Shower (Female)");
        ArrayList<String> teamLounge = new ArrayList<>();
        Collections.addAll(teamLounge, "Toilet (Male)", "Toilet(Female)",
                "Toilet (Disabled)", "Shower (Male)", "Shower (Female)");
        ArrayList<String> general = new ArrayList<>();
        Collections.addAll(general, "Toilet (Male)", "Toilet(Female)",
                "Toilet (Disabled)", "Shower (Male)", "Shower (Female)");
        ArrayList<String> freezerPacks = new ArrayList<>();
        Collections.addAll(freezerPacks, "NA");
        ArrayList<String> hospitality = new ArrayList<>();
        Collections.addAll(hospitality, "NA");
        ArrayList<String> kitchens = new ArrayList<>();
        Collections.addAll(kitchens, "NA");
        ArrayList<String> ticketOfficeBlue = new ArrayList<>();
        Collections.addAll(ticketOfficeBlue, "NA");
        ArrayList<String> ticketOfficeRed = new ArrayList<>();
        Collections.addAll(ticketOfficeRed, "NA");
        ArrayList<String> bandStand = new ArrayList<>();
        Collections.addAll(bandStand, "NA");
        ArrayList<String> fencing = new ArrayList<>();
        Collections.addAll(fencing, "NA");
        ArrayList<String> pond = new ArrayList<>();
        Collections.addAll(pond, "NA");
        
        ArrayList<ArrayList<String>> listOfLists = new ArrayList<>();
        Collections.addAll(listOfLists, blue1, blue2, blue3, blue4, blue5,
                blue6, blue7, blue8, blue9, blue10, green1, green2, green3, 
                green4, green5, green6, green7, purple1, purple2, purple3,
                purple4, red1, red2, red3, red4, red5, red6, red7, yellow0,
                yellow1, yellow2, yellow3, yellow4, yellow5, yellow6, blueOuterGate,
                blueInnerGate, greenGate, whiteOuterGate, whiteInnerGate, redGate,
                yellowGate, hfgGate, blackPedGate, brownPedGate, meetingPlace,
                impact, burn, leadersLounge, pastoralPrayer, sanctuary, arts316,
                manifest, renovate, synergy, upperRoom, tearfund, gems, pebbles,
                steppingStones, groundBreakers, rockSolid, boulderGang, fridge,
                clubOne, thirst, flava, sportsField, ourPlace, foodCourt, marketPlace,
                medicalCentre, newWineFM, carParkDay, carParkMain, runway,
                bay1, bay2, bay3, bay4, bay5, endOfShedsBB, redGateBB, wigWamBB,
                admin, controlOffice, finance, information, production, kitchen,
                teamLounge, general, freezerPacks, hospitality, kitchens, ticketOfficeBlue,
                ticketOfficeRed, bandStand, fencing, pond);

        int i = 0;
        while (i < listOfLists.size())
        {
            if(fieldsBox3.get(i).equals("NA") && i < 45)
            {
                int marker = i - 35;
                masterListBox4.put(fieldsBox2.get(marker) + "-" + fieldsBox3.get(i), listOfLists.get(i));
                i++;
            }
            else if(fieldsBox3.get(i).equals("NA") && (i > 45 && i < 77))
            {
                int marker = i - 55;
                masterListBox4.put(fieldsBox2.get(marker) + "-" + fieldsBox3.get(i), listOfLists.get(i));
                i++;
            }
            else if(fieldsBox3.get(i).equals("NA") && (i > 91))
            {
                int marker = i - 68;
                masterListBox4.put(fieldsBox2.get(marker) + "-" + fieldsBox3.get(i), listOfLists.get(i));
                i++;
            }
            else
            {
                masterListBox4.put(fieldsBox3.get(i), listOfLists.get(i));
                i++;

            }
        }
    }
    
    private void generateMasterLists()
    {
        ArrayList<String> potentialFieldsBox2 = generateMasterListBox2(masterListBox1);
        ArrayList<String> potentialFieldsBox3 = generateMasterListBox3(potentialFieldsBox2);
        generateMasterListBox4(potentialFieldsBox3, potentialFieldsBox2);
    }

    public InitialGUI getInitialGUI()
    {
        return initialGUI;
    }

    /**
     * Return the user object from the Main System.
     *
     * @return The User object.
     */
    private User getUser()
    {
        return user;
    }
}
