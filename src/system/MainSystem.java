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
import java.util.Iterator;
import javax.swing.JButton;
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
                            get(cofe.getProblemLocationComboBox1().getSelectedItem());
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
            public void actionPerformed(ActionEvent e)
            {
                if (e.getActionCommand().equals("comboBoxChanged") 
                        && !(cofe.getProblemLocationComboBox2().getSelectedItem() == null)
                        && !(cofe.getProblemLocationComboBox2().getSelectedItem().equals("Select a Location")))
                    {
                        ArrayList<String> thirdBoxOptions = dataStructures.getMasterListBox3().get(
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
                                locationVenueVillage, "Low", false, null, null,
                                null, null, null);
                        String validationResult = ticket.dataValidationEntry();
                        if(validationResult.equals("Passed"))
                        {
                            if(mysqlEngine.submitTicket(ticket))
                            {
                                JOptionPane.showMessageDialog(cofe.getMainFrame(), "It seems to have worked");
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(cofe.getMainFrame(), "It don't work :(");
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(cofe.getMainFrame(), validationResult);
                        }
                        
                        ;
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
