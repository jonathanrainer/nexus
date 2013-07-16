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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

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
    private DataStructures dataStructures;
    

    /**
     * Construct all the components of the Main System and such that it can have
     * control over them.
     */
    public MainSystem()
    {
        mysqlEngine = new MYSQLEngine("localhost", "Nexus", "nexus", "nexus2713");
        teamNames = mysqlEngine.enumerateTeamNames();
        initialGUI = new InitialGUI(teamNames);
        dataStructures = new DataStructures();
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
                ControlOfficeEntryForm cofe = createLimitedEntryForm();
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
                ControlOfficeEntryForm cofe = createLimitedEntryForm();
            }
        });
        
        JMenuBar mainMenu = mainGUI.getTaskSelectionScreen().getMainFrame().getJMenuBar();
        JMenu fileMenu = mainMenu.getMenu(0);
        JMenuItem checkForPotentialDuplicates = new JMenuItem("Check For Potential Duplicates");
        JMenuItem checkForUnprintedTickets = new JMenuItem("Check For Unprinted Job Tickets");
        fileMenu.add(checkForPotentialDuplicates);
        fileMenu.add(checkForUnprintedTickets);
        
        mainGUI.getTaskSelectionScreen().getButtons().
                get("viewAmendCOTicketButton").addActionListener(
                new ActionListener()
                {
                   public void actionPerformed(ActionEvent e)
                   {
                       createUpdateAmendEntryForm();
                       
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
        cofe.getTeamMembersComboBox().addItem("Choose One");
        Iterator<String> it1 = teamMembers.iterator();
        while (it1.hasNext())
        {
            cofe.getTeamMembersComboBox().addItem(it1.next());
        }

        Iterator it2 = dataStructures.getMasterListBox1().iterator();
        cofe.getProblemLocationComboBox1().addItem("Select a Location");
        while (it2.hasNext())
        {
            cofe.getProblemLocationComboBox1().addItem(it2.next());
        }
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
                    ArrayList<String> secondBoxOptions = dataStructures.getMasterListBox2().
                            get(cofe.getProblemLocationComboBox1().getSelectedItem().toString());
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
                else if(cofe.getProblemLocationComboBox1().getSelectedItem().
                        equals("Select a Location"))
                {
                    cofe.getProblemLocationComboBox2().removeAllItems();
                    cofe.getProblemLocationComboBox3().removeAllItems();
                    cofe.getProblemLocationComboBox4().removeAllItems();
                }
            }
        });

        cofe.getProblemLocationComboBox2().addActionListener(
                new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (e.getActionCommand().equals("comboBoxChanged") 
                        && !(cofe.getProblemLocationComboBox2().getSelectedItem() == null)
                        && !(cofe.getProblemLocationComboBox2().getSelectedItem().equals("Select a Location")))
                    {
                        ArrayList<String> thirdBoxOptions = dataStructures.getMasterListBox3().get(
                                cofe.getProblemLocationComboBox2().getSelectedItem().toString());
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
                if (!(cofe.getProblemLocationComboBox2().getSelectedItem() == null)
                        && (cofe.getProblemLocationComboBox2().getSelectedItem().equals("Select a Location")))
                {
                    cofe.getProblemLocationComboBox3().removeAllItems();
                    cofe.getProblemLocationComboBox4().removeAllItems();
                }
            }
        });

        cofe.getProblemLocationComboBox3().addActionListener(
                new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (e.getActionCommand().equals("comboBoxChanged") 
                        && !(cofe.getProblemLocationComboBox3().getSelectedItem() == null 
                        || cofe.getProblemLocationComboBox3().getSelectedItem().equals("Select a Location")))
                    {
                        ArrayList<String> fourthBoxOptions = new ArrayList<>();
                        if (cofe.getProblemLocationComboBox3().getSelectedItem().equals("NA"))
                        {
                            fourthBoxOptions =
                                    dataStructures.getMasterListBox4().get(cofe.
                                    getProblemLocationComboBox2().
                                    getSelectedItem() + "-"
                                    + cofe.getProblemLocationComboBox3().
                                    getSelectedItem());
                        } else
                        {
                            fourthBoxOptions =
                                    dataStructures.getMasterListBox4().get(cofe.getProblemLocationComboBox3()
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
                if (!(cofe.getProblemLocationComboBox3().getSelectedItem() == null)
                        && (cofe.getProblemLocationComboBox3().getSelectedItem().equals("Select a Location")))
                {
                    cofe.getProblemLocationComboBox4().removeAllItems();
                }

                }
            });

        cofe.getWhoIsAComboBox().addItem("Choose One");
        cofe.getWhoIsAComboBox().addItem("Delegate");
        cofe.getWhoIsAComboBox().addItem("Speaker");
        cofe.getWhoIsAComboBox().addItem("Team Member");
        
        cofe.getContactViaComboBox().addItem("Choose One");
        cofe.getContactViaComboBox().addItem("Mobile");
        cofe.getContactViaComboBox().addItem("Radio");
        cofe.getContactViaComboBox().addItem("Village Host");
        cofe.getContactViaComboBox().addItem("Village");
        cofe.getContactViaComboBox().addItem("Not Required");
        
        cofe.getContactViaComboBox().addActionListener(new ActionListener()
                {
                   @Override
                   public void actionPerformed(ActionEvent e)
                   {
                       if(e.getActionCommand().equals("comboBoxChanged") &&
                               !(cofe.getContactViaComboBox().getSelectedItem().
                               toString().equals("Choose One")) && 
                               !(cofe.getContactViaComboBox().getSelectedItem().
                               toString().equals("Mobile")))
                       {
                           cofe.getContactNumberTextField().setText(
                                   cofe.getContactViaComboBox().
                                   getSelectedItem().toString());
                       }
                       else
                       {
                           cofe.getContactNumberTextField().setText("");
                       }
                   }
                });
        
        cofe.getLocationVenueVillageComboBox().addItem("Choose One");
        cofe.getLocationVenueVillageComboBox().addItem("Venue");
        cofe.getLocationVenueVillageComboBox().addItem("Village");
        cofe.getLocationVenueVillageComboBox().addItem("Not Required");
 
        cofe.getSubmitFormButton().addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        String team = user.getTeam();
                        user.setName(cofe.getTeamMembersComboBox().getSelectedItem().toString());
                        String member = user.getName();
                        String problemLocation = cofe.
                                getProblemLocationComboBox1().getSelectedItem()
                                + "-" + cofe.getProblemLocationComboBox2().getSelectedItem()
                                + "-" + cofe.getProblemLocationComboBox3().getSelectedItem()
                                + "-" + cofe.getProblemLocationComboBox4().getSelectedItem();
                        String problemDescription = cofe.getProblemDescriptionTextArea().getText();
                        Iterator<JRadioButton> buttonsIterator = cofe.getButtonsInGrid().iterator();
                        ArrayList<String> keyWords = new ArrayList<String>();
                        while (buttonsIterator.hasNext())
                        {
                            JRadioButton buttonUnderConsideration = buttonsIterator.next();
                            if(buttonUnderConsideration.isSelected())
                            {
                                keyWords.add(buttonUnderConsideration.getName());
                            }
                        }
                        String problemReportedBy = cofe.getProblemReportedByTextField().getText();
                        String whoIsA = cofe.getWhoIsAComboBox().getSelectedItem().toString();
                        String contactVia = cofe.getContactViaComboBox().getSelectedItem().toString();
                        String contactNumber = cofe.getContactNumberTextField().getText();
                        String locationVenueVillage = (String) cofe.getLocationVenueVillageComboBox().
                                getSelectedItem().toString();
                        Ticket ticket = new Ticket(0, null, user, 
                                problemLocation, problemDescription, keyWords, 
                                problemReportedBy, whoIsA, contactVia, contactNumber,
                                locationVenueVillage, "Low", false, null, "Issue Reported", null, null,
                                null, null, null);
                        String validationResult = ticket.dataValidationEntry();
                        if(validationResult.equals("Passed"))
                        {
                            if(!(mysqlEngine.isTicketDuplicate(ticket)))
                            {
                                if(mysqlEngine.submitTicket(ticket, false))
                                {
                                    JOptionPane.showMessageDialog(cofe.getMainFrame(),
                                            "The Job Ticket has been submitted. \n"
                                            + "The Job ID is: " + ticket.getJobRefId()
                                            + "\nIt was submitted at: " + ticket.getDateTime().toString("d/M/y H:m"));
                                    cofe.getMainFrame().dispose();
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(cofe.getMainFrame(), "MYSQL isn't happy...");
                                }
                            }
                            else
                            {
                                mysqlEngine.submitTicket(ticket, true);
                            }
                        }   
                        else
                        {
                            JOptionPane.showMessageDialog(cofe.getMainFrame(), validationResult);
                        }
                    }
                });
        
        cofe.getResetFormButton().addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        cofe.getTicketWrittenComboBox().setSelectedIndex(0);
                        cofe.getProblemLocationComboBox1().setSelectedIndex(0);
                        cofe.getProblemDescriptionTextArea().setText("");
                        Iterator<JRadioButton> buttonsIterator = cofe.getButtonsInGrid().iterator();
                        while(buttonsIterator.hasNext())
                        {
                            buttonsIterator.next().setSelected(false);
                        }
                        cofe.getProblemReportedByTextField().setText("");
                        cofe.getWhoIsAComboBox().setSelectedIndex(0);
                        cofe.getContactViaComboBox().setSelectedIndex(0);
                        cofe.getContactNumberTextField().setText("");
                        cofe.getLocationVenueVillageComboBox().setSelectedIndex(0);
                    }
                });
        
        return cofe;
    }

    private ControlOfficeEntryForm createUpdateAmendEntryForm()
    {
        String ticketID = JOptionPane.showInputDialog(
                mainGUI.getTaskSelectionScreen().getMainFrame(), 
                "Please enter the Ticket ID of the Job Ticket you wish to"
                + "update.", "Update/Amend Ticket Search", 
                JOptionPane.QUESTION_MESSAGE);
        final ControlOfficeEntryForm cofeAmmend = new ControlOfficeEntryForm(false);
        Ticket ticket = mysqlEngine.retrieveTicket(ticketID);
        
        //Add information back into the ticket
        cofeAmmend.getTicketReferenceTextField().setText("" + ticket.getJobRefId());
        cofeAmmend.getTicketReferenceTextField().setEnabled(false);
        cofeAmmend.getDateTimeTextField().setText(ticket.getDateTime().toString("d/M/y - H:mm"));
        cofeAmmend.getDateTimeTextField().setEnabled(false);
        cofeAmmend.getTicketWrittenComboBox().addItem(ticket.getTicketRaisedBy().getTeam());
        cofeAmmend.getTicketWrittenComboBox().setSelectedIndex(0);
        cofeAmmend.getTicketWrittenComboBox().setEnabled(false);
        cofeAmmend.getTeamMembersComboBox().addItem(ticket.getTicketRaisedBy().getName());
        cofeAmmend.getTeamMembersComboBox().setSelectedIndex(0);
        cofeAmmend.getTeamMembersComboBox().setEnabled(false);
        
        String[] problemLocation = ticket.getProblemLocation().split("-");
        cofeAmmend.getProblemLocationComboBox1().addItem(problemLocation[0]);
        cofeAmmend.getProblemLocationComboBox1().setSelectedIndex(0);
        cofeAmmend.getProblemLocationComboBox1().setEnabled(false);
        cofeAmmend.getProblemLocationComboBox2().addItem(problemLocation[1]);
        cofeAmmend.getProblemLocationComboBox2().setSelectedIndex(0);
        cofeAmmend.getProblemLocationComboBox2().setEnabled(false);
        cofeAmmend.getProblemLocationComboBox3().addItem(problemLocation[2]);
        cofeAmmend.getProblemLocationComboBox3().setSelectedIndex(0);
        cofeAmmend.getProblemLocationComboBox3().setEnabled(false);
        cofeAmmend.getProblemLocationComboBox4().addItem(problemLocation[3]);
        cofeAmmend.getProblemLocationComboBox4().setSelectedIndex(0);
        cofeAmmend.getProblemLocationComboBox4().setEnabled(false);
        
        cofeAmmend.getProblemDescriptionTextArea().setText(ticket.getProblemDescription());
        cofeAmmend.getProblemDescriptionTextArea().setEnabled(false);
        
        Iterator<String> it1 = ticket.getCISKeywords().iterator();
        while(it1.hasNext())
        {
            String keyword = it1.next();
            Iterator<JRadioButton> it2 = cofeAmmend.getButtonsInGrid().iterator();
            while(it2.hasNext())
            {
                JRadioButton buttonToConsider = it2.next();
                if(buttonToConsider.getName().equals(keyword))
                {
                    buttonToConsider.setSelected(true);
                }
                buttonToConsider.setEnabled(false);
            }
        }
        
        cofeAmmend.getProblemReportedByTextField().setText(ticket.getReportedBy());
        cofeAmmend.getProblemReportedByTextField().setEnabled(false);
        cofeAmmend.getWhoIsAComboBox().addItem(ticket.getWhoIsA());
        cofeAmmend.getWhoIsAComboBox().setSelectedIndex(0);
        cofeAmmend.getWhoIsAComboBox().setEnabled(false);
        
        cofeAmmend.getContactViaComboBox().addItem(ticket.getContactVia());
        cofeAmmend.getContactViaComboBox().setSelectedIndex(0);
        cofeAmmend.getContactViaComboBox().setEnabled(false);
        cofeAmmend.getContactNumberTextField().setText(ticket.getContactNumber());
        cofeAmmend.getContactNumberTextField().setEnabled(false);
        cofeAmmend.getLocationVenueVillageComboBox().addItem(ticket.getLocationVenueVillage());
        cofeAmmend.getLocationVenueVillageComboBox().setSelectedIndex(0);
        cofeAmmend.getLocationVenueVillageComboBox().setEnabled(false);
        
        // All fields that should be filled in are above
        
        cofeAmmend.getDelegateImpactComboBox().addItem("Low");
        cofeAmmend.getDelegateImpactComboBox().addItem("Medium");
        cofeAmmend.getDelegateImpactComboBox().addItem("High");
        cofeAmmend.getDelegateImpactComboBox().setSelectedItem(0);
        
        cofeAmmend.getTicketAllocatedToComboBox().addItem("Please Choose One");
        cofeAmmend.getTicketAllocatedToComboBox().addItem("Control Office");
        cofeAmmend.getTicketAllocatedToComboBox().addItem("Control Office (Admin)");
        cofeAmmend.getTicketAllocatedToComboBox().addItem("Day Stewards");
        cofeAmmend.getTicketAllocatedToComboBox().addItem("Night Stewards");
        cofeAmmend.getTicketAllocatedToComboBox().addItem("AV");
        cofeAmmend.getTicketAllocatedToComboBox().addItem("Finance");
        cofeAmmend.getTicketAllocatedToComboBox().addItem("Hospitality");
        cofeAmmend.getTicketAllocatedToComboBox().addItem("Information");
        cofeAmmend.getTicketAllocatedToComboBox().addItem("IT Support");
        cofeAmmend.getTicketAllocatedToComboBox().addItem("Logistics");
        cofeAmmend.getTicketAllocatedToComboBox().addItem("Production");
        cofeAmmend.getTicketAllocatedToComboBox().addItem("Site Crew");
        cofeAmmend.getTicketAllocatedToComboBox().addItem("WigWam");
        cofeAmmend.getTicketAllocatedToComboBox().addItem("Andy (Showground)");
        cofeAmmend.getTicketAllocatedToComboBox().addItem("Jackie (Cleaners)");
        cofeAmmend.getTicketAllocatedToComboBox().addItem("Other");
        
        cofeAmmend.getJobProgressComboBox().addItem("Issue Reported");
        cofeAmmend.getJobProgressComboBox().addItem("Ticket Printed");
        cofeAmmend.getJobProgressComboBox().addItem("Job In Progress");
        cofeAmmend.getJobProgressComboBox().addItem("Job Escalated");
        cofeAmmend.getJobProgressComboBox().addItem("Job Done");
        
        return cofeAmmend;
        
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
