/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import io.MYSQLEngine;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import system.User;

/**
 *
 * @author jonathanrainer
 */
public class ControlOfficeEntryForm {

    // The MYSQL engine to allow this form access to the database
    private MYSQLEngine mysqlEngine;
    // A frame to hold the content of this entry form.
    private JFrame mainFrame;
    // The template to give a templated JFrame within which the content can 
    // be placed.
    private JPanel frameContent;
    private Template template;
    private JLabel introTextLabel;
    private JLabel ticketReferenceLabel;
    private JTextField ticketReferenceTextField;
    private JLabel dateTimeLabel;
    private JTextField dateTimeTextField;
    private JLabel ticketWrittenLabel;
    private JComboBox ticketWrittenComboBox;
    private JComboBox teamMembersComboBox;
    private JLabel problemLocationLabel;
    private JLabel problemLocationHelperLabel;
    private JComboBox problemLocationComboBox1;
    private JComboBox problemLocationComboBox2;
    private JComboBox problemLocationComboBox3;
    private JComboBox problemLocationComboBox4;
    private JLabel problemDescriptionLabel;
    private JTextArea problemDescriptionTextArea;
    private JLabel problemDescriptionExplanationLabel;
    private JLabel forCISLabel;
    private JLabel keyWordsLabel;
    private JLabel keyWordExplanationLabel;
    private JLabel delegateImpactLabel;
    private JComboBox delegateImpactComboBox;
    private JPanel keyWordGrid;
    private ArrayList<JRadioButton> buttonsInGrid;
    private JLabel showOnCISLabel;
    private JRadioButton showOnCISRadioButton;
    private JLabel asAtLabel;
    private JLabel jobProgressLabel;
    private JComboBox jobProgressComboBox;
    private JTextField asAtTextField;
    private JLabel ticketAllocatedToLabel;
    private JComboBox ticketAllocatedToComboBox;
    private JLabel problemReportedByLabel;
    private JTextField problemReportedByTextField;
    private JLabel whoIsALabel;
    private JComboBox whoIsAComboBox;
    private JLabel contactViaLabel;
    private JComboBox contactViaComboBox;
    private JTextField contactNumberTextField;
    private JLabel locationVenueVillageLabel;
    private JComboBox locationVenueVillageComboBox;
    private JLabel updatesLabel;
    private JLabel estimatedCompletionByLabel;
    private JLabel updatedAtLabel;
    private JLabel ifNotAQuickFixLabel;
    private JTextArea updateTextArea1;
    private JTextField estimatedCompletionByTextField1;
    private JTextField updatedAtTextField1;
    private JTextArea updateTextArea2;
    private JTextField estimatedCompletionByTextField2;
    private JTextField updatedAtTextField2;
    private JTextArea updateTextArea3;
    private JTextField estimatedCompletionByTextField3;
    private JTextField updatedAtTextField3;
    private JLabel jobCompletedLabel;
    private JRadioButton jobCompletedRadioButton;
    private JTextField jobCompletedTextField;
    private JLabel ifJobNotCompletedLabel;
    private JLabel nextUpdateDueLabel;
    private JTextField nextUpdateDueTextField;
    private JButton submitFormButton;
    private JButton resetFormButton;
    private JButton printFormButton;

    public ControlOfficeEntryForm(boolean entry, User user)
    {
        //
        template = new Template();
        // Define a blank JLabel so that layout can more easily be specified
        String formTitle = "Control Office Job Ticket <br> Input Screen";
        if (!entry)
        {
            String frameTitle = "NWNE Nexus - Control Office Job Ticket - Updating Screen";
            basicFormCreation(frameTitle, formTitle);
            controlOfficeFormCreation();
            int lastRow = updateFormAddition();
            submitResetButtons(lastRow);
            printButton(lastRow, entry);
            
        } else
        {
            String frameTitle = "NWNE Nexus- Control Office Job Ticket - Input Screen ";
            if (user.getTeam().equals("Control Office"))
            {
                basicFormCreation(frameTitle,formTitle);
                int lastRow = controlOfficeFormCreation();
                submitResetButtons(lastRow);
                printButton(lastRow, entry);
            } else
            {
                basicFormCreation(frameTitle, formTitle);
                int lastRow = entryFormCreation();
                submitResetButtons(lastRow);
            }

        }

    }
    
    

    private void basicFormCreation(String frameTitle, String formTitle)
    {
                // Get initial templated Frame
        mainFrame = template.giveTemplatedJFrame(frameTitle);
        // Get initial content Panel
        frameContent = template.giveFormTemplatedJPanel(formTitle);
        
        // Create the introText label
        String introText = "<html>This Form is to raise Jobs Tickets for the "
                + "Control Office Team to Manage. Please fill in as much detail as possible. <br><br>";
        introTextLabel = new JLabel(introText);
        GridBagConstraints introTextLabelConstraints = template.
                createGridBagConstraints(0, 1, GridBagConstraints.NONE, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                5);
        frameContent.add(introTextLabel, introTextLabelConstraints);
        
        JPanel keyPanel = new JPanel(new GridLayout(1,4));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        JLabel keyLabel = new JLabel("Key:");
        JLabel yellowAutomated = new JLabel(" Automated ");
        yellowAutomated.setBackground(Color.YELLOW);
        yellowAutomated.setOpaque(true);
        yellowAutomated.setBorder(border);
        JLabel whiteInput = new JLabel(" Input ");
        whiteInput.setBackground(Color.WHITE);
        whiteInput.setOpaque(true);
        whiteInput.setBorder(border);
        JLabel greyControlOffice = new JLabel(" Control Office ");
        greyControlOffice.setBackground(Color.LIGHT_GRAY);
        greyControlOffice.setOpaque(true);
        greyControlOffice.setBorder(border);
        keyPanel.add(keyLabel);
        keyPanel.add(yellowAutomated);
        keyPanel.add(whiteInput);
        keyPanel.add(greyControlOffice);
        GridBagConstraints keyPanelConstraints = template.
                createGridBagConstraints(0, 2, GridBagConstraints.NONE, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                5);
        frameContent.add(keyPanel,keyPanelConstraints);
        
        /**
         * First Row of Form
         */
        //Create the ticket reference label and constraints
        ticketReferenceLabel = new JLabel(template.headingString("Ticket Reference:", 3));
        GridBagConstraints ticketReferenceLabelConstraints = template.
                createGridBagConstraints(0, 3, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(ticketReferenceLabel, ticketReferenceLabelConstraints);

        //Create the ticket reference text box and constraints
        ticketReferenceTextField = new JTextField("");
        ticketReferenceTextField.setBackground(Color.yellow);
        GridBagConstraints ticketReferenceTextFieldConstraints = template.
                createGridBagConstraints(1, 3, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(ticketReferenceTextField, ticketReferenceTextFieldConstraints);

        //Create a spacer to allow good aesthetic
        GridBagConstraints spacer1Constraints = template.
                createGridBagConstraints(2, 3, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(Box.createHorizontalStrut(100), spacer1Constraints);

        //Create the Date/Time label
        dateTimeLabel = new JLabel(template.headingString("Date/Time:", 3));
        GridBagConstraints dateTimeLabelConstraints = template.
                createGridBagConstraints(3, 3, GridBagConstraints.NONE, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.EAST, 0.0, 0.0, 1,
                1);
        frameContent.add(dateTimeLabel, dateTimeLabelConstraints);

        //Create the Date/Time text box and constraints
        dateTimeTextField = new JTextField("");
        dateTimeTextField.setBackground(Color.yellow);
        GridBagConstraints dateTimeTextFieldConstraints = template.
                createGridBagConstraints(4, 3, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(dateTimeTextField, dateTimeTextFieldConstraints);

        /**
         * Second Row Of Form
         */
        //Create the Ticket Written By Label and constraints
        ticketWrittenLabel = new JLabel(template.headingString("Ticket Written By:", 3));
        GridBagConstraints ticketWrittenLabelConstraints = template.
                createGridBagConstraints(0, 4, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(ticketWrittenLabel, ticketWrittenLabelConstraints);

        //Create the Ticket Written text box and constraints
        ticketWrittenComboBox = new JComboBox();
        GridBagConstraints ticketWrittenComboBoxConstraints = template.
                createGridBagConstraints(1, 4, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 5), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(ticketWrittenComboBox, ticketWrittenComboBoxConstraints);

        teamMembersComboBox = new JComboBox();
        GridBagConstraints teamMembersComboBoxConstraints = template.
                createGridBagConstraints(2, 4, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(teamMembersComboBox, teamMembersComboBoxConstraints);

        /**
         * Third Row of Form
         */
        //Create the Where is the Problem Label and constraints
        problemLocationLabel = new JLabel(template.headingString("Where Is The Problem? :", 3));
        GridBagConstraints problemLocationLabelConstraints = template.
                createGridBagConstraints(0, 5, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(problemLocationLabel, problemLocationLabelConstraints);

        //Create the Where is the Problem helper Label and constraints
        String helperText = "Choose one of each of the following options.";
        problemLocationHelperLabel = new JLabel(template.headingString(helperText, 4));
        GridBagConstraints problemLocationHelperLabelConstraints = template.
                createGridBagConstraints(1, 5, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.EAST, 0.0, 0.0, 1,
                4);
        frameContent.add(problemLocationHelperLabel, problemLocationHelperLabelConstraints);

        /**
         * Fourth Row of Form
         */
        //Create a spacer to allow good aesthetic
        GridBagConstraints spacer2Constraints = template.
                createGridBagConstraints(0, 6, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(Box.createHorizontalStrut(50), spacer2Constraints);

        //Add in ComboBox 1
        problemLocationComboBox1 = new JComboBox();
        GridBagConstraints problemLocationComboBox1Constraints = template.
                createGridBagConstraints(1, 6, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 5), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(problemLocationComboBox1, problemLocationComboBox1Constraints);

        //Add in ComboBox 2
        problemLocationComboBox2 = new JComboBox();
        GridBagConstraints problemLocationComboBox2Constraints = template.
                createGridBagConstraints(2, 6, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 5), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(problemLocationComboBox2, problemLocationComboBox2Constraints);

        //Add in ComboBox 3
        problemLocationComboBox3 = new JComboBox();
        GridBagConstraints problemLocationComboBox3Constraints = template.
                createGridBagConstraints(3, 6, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 5), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(problemLocationComboBox3, problemLocationComboBox3Constraints);

        //Add in ComboBox 4
        problemLocationComboBox4 = new JComboBox();
        GridBagConstraints problemLocationComboBox4Constraints = template.
                createGridBagConstraints(4, 6, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(problemLocationComboBox4, problemLocationComboBox4Constraints);

        /**
         * Fifth Row of Form
         */
        //Create the Where is the Problem Label and constraints
        problemDescriptionLabel = new JLabel(template.headingString("What Is The Problem? :", 3));
        GridBagConstraints problemDescriptionLabelConstraints = template.
                createGridBagConstraints(0, 7, GridBagConstraints.NONE, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.NORTHWEST, 0.0, 0.0, 1,
                1);
        frameContent.add(problemDescriptionLabel, problemDescriptionLabelConstraints);

        //Create Text Area for Problem Description
        problemDescriptionTextArea = new JTextArea();
        problemDescriptionTextArea.setLineWrap(true);
        problemDescriptionTextArea.setMargin(new Insets(10, 10, 10, 10));
        problemDescriptionTextArea.setPreferredSize(new Dimension(520, 70));
        problemDescriptionTextArea.setBorder(border);
        GridBagConstraints problemDescriptionTextAreaConstraints = template.
                createGridBagConstraints(1, 7, GridBagConstraints.NONE, 5, 5,
                new Insets(10, 0, 0, 0), GridBagConstraints.NORTHWEST, 0.0, 0.0, 4,
                4);
        frameContent.add(problemDescriptionTextArea, problemDescriptionTextAreaConstraints);

        // Create the helper text next to description 
        problemDescriptionExplanationLabel = new JLabel(template.headingString(" Be brief: <br>"
                + " You only <br> have 256 <br> characters.", 4));
        GridBagConstraints problemDescriptionExplanationLabelConstraints = template.
                createGridBagConstraints(4, 7, GridBagConstraints.NONE, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.EAST, 0.0, 0.0, 1,
                1);
        frameContent.add(problemDescriptionExplanationLabel,
                problemDescriptionExplanationLabelConstraints);

        /*
         * 6th Row of the Form
         */

        //Create the For the CIS label
        forCISLabel = new JLabel(template.headingString("For The CIS :", 3));
        GridBagConstraints forCISLabelConstraints = template.
                createGridBagConstraints(0, 11, GridBagConstraints.NONE, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.NORTHWEST, 0.0, 0.0, 1,
                1);
        frameContent.add(forCISLabel, forCISLabelConstraints);

        //Create the Key words label
        keyWordsLabel = new JLabel(template.headingString("Key Words", 3));
        GridBagConstraints keyWordsLabelConstraints = template.
                createGridBagConstraints(1, 11, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.NORTH, 0.0, 0.0, 1,
                1);
        frameContent.add(keyWordsLabel, keyWordsLabelConstraints);

        //Create the Key Word Explanation label
        keyWordExplanationLabel = new JLabel(template.headingString("Pick Up to 5 of the following", 5));
        GridBagConstraints keyWordExplanationLabelConstraints = template.
                createGridBagConstraints(0, 12, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.NORTH, 0.0, 0.0, 1,
                1);
        frameContent.add(keyWordExplanationLabel, keyWordExplanationLabelConstraints);

        keyWordGrid = new JPanel();
        GridLayout twoRowsEightColumns = new GridLayout(3, 6);
        keyWordGrid.setLayout(twoRowsEightColumns);
        buttonsInGrid = new ArrayList<>();
        ArrayList<String> labelsForButtons = new ArrayList<>();
        Collections.addAll(labelsForButtons, "Gas Bottle","Hand Towels",
                "Shower","Sink","Soap","Toilet/Urinal","Toilet Paper",
                "Water","Blocked","Broken", "Cold", "Dirty", "Flooded",
                "Electricity", "Too Hot", "Other", "None");
        int i;
        for (i = 0; i < 16; i++) {
            JRadioButton buttonToAdd = new JRadioButton(labelsForButtons.get(i));
            buttonToAdd.setName(labelsForButtons.get(i));
            buttonsInGrid.add(buttonToAdd);
        }
        for (i = 0; i < 16; i++) {
            keyWordGrid.add(buttonsInGrid.get(i));
        }
        GridBagConstraints keyWordGridConstraints = template.
                createGridBagConstraints(1, 12, GridBagConstraints.NONE, 10, 10,
                new Insets(0, 0, 0, 0), GridBagConstraints.NORTHWEST, 0.0, 0.0, 2,
                4);
        frameContent.add(keyWordGrid, keyWordGridConstraints);


        //Add in Problem Reported By  Label
        problemReportedByLabel = new JLabel(template.headingString("Problem Reported By:", 3));
        GridBagConstraints problemReportedByLabelConstraints = template.
                createGridBagConstraints(0, 16, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        frameContent.add(problemReportedByLabel, problemReportedByLabelConstraints);

        //Add in Problem Reported By Text Field
        problemReportedByTextField = new JTextField();
        GridBagConstraints problemReportedByTextFieldConstraints = template.
                createGridBagConstraints(1, 16, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(problemReportedByTextField, problemReportedByTextFieldConstraints);

        //Add in Who Is A Combo Box
        whoIsAComboBox = new JComboBox();
        GridBagConstraints whoIsAComboBoxConstraints = template.
                createGridBagConstraints(2, 16, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 10, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(whoIsAComboBox, whoIsAComboBoxConstraints);

        //Add in Contact Via Label
        contactViaLabel = new JLabel(template.headingString("Contact Via:", 3));
        GridBagConstraints contactViaLabelConstraints = template.
                createGridBagConstraints(0, 17, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        frameContent.add(contactViaLabel, contactViaLabelConstraints);

        //Add in Contact Via ComboBox
        contactViaComboBox = new JComboBox();
        GridBagConstraints contactViaComboBoxConstraints = template.
                createGridBagConstraints(1, 17, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        frameContent.add(contactViaComboBox, contactViaComboBoxConstraints);

        //Add in Problem Reported By Text Field
        contactNumberTextField = new JTextField();
        GridBagConstraints contactNumberTextFieldConstraints = template.
                createGridBagConstraints(2, 17, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 10, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(contactNumberTextField, contactNumberTextFieldConstraints);

        //Add in Location/Venue/Village Label
        locationVenueVillageLabel = new JLabel(template.headingString("Location/Venue/Village:", 3));
        GridBagConstraints locationVenueVillageLabelConstraints = template.
                createGridBagConstraints(3, 16, GridBagConstraints.NONE, 5, 5,
                new Insets(0, 10, 0, 0), GridBagConstraints.EAST, 0.0, 0.0, 1,
                1);
        frameContent.add(locationVenueVillageLabel, locationVenueVillageLabelConstraints);

        //Add in Location/Venue/Village ComboBox
        locationVenueVillageComboBox = new JComboBox();
        GridBagConstraints locationVenueVillageComboBoxConstraints = template.
                createGridBagConstraints(4, 16, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 5, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        frameContent.add(locationVenueVillageComboBox, locationVenueVillageComboBoxConstraints);
    }
    
    private int entryFormCreation(){        
        mainFrame.getContentPane().add(new JScrollPane(frameContent), BorderLayout.CENTER);
        mainFrame.pack();
        mainFrame.setVisible(true);
        
        return 18;
    }
    
    private int controlOfficeFormCreation(){
        //Create the Delegate Impact label
        delegateImpactLabel = new JLabel(template.headingString("Delegate Impact", 3));
        GridBagConstraints delegateImpactLabelConstraints = template.
                createGridBagConstraints(0, 19, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.NORTH, 0.0, 0.0, 1,
                1);
        frameContent.add(delegateImpactLabel, delegateImpactLabelConstraints);

        //Create the Show On CIS label
        showOnCISLabel = new JLabel(template.headingString("Show On CIS?", 3));
        GridBagConstraints showOnCISLabelConstraints = template.
                createGridBagConstraints(2, 19, GridBagConstraints.NONE, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.EAST, 0.0, 0.0, 1,
                1);
        frameContent.add(showOnCISLabel, showOnCISLabelConstraints);

        //Create the Job Progress label
        jobProgressLabel = new JLabel(template.headingString("Job Progress", 3));
        GridBagConstraints jobProgressLabelConstraints = template.
                createGridBagConstraints(2, 20, GridBagConstraints.NONE, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.EAST, 0.0, 0.0, 1,
                1);
        frameContent.add(jobProgressLabel, jobProgressLabelConstraints);

        //Add in Delegate Impact Combo Box
        delegateImpactComboBox = new JComboBox();
        GridBagConstraints delegateImpactComboBoxConstraints = template.
                createGridBagConstraints(1, 19, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(delegateImpactComboBox, delegateImpactComboBoxConstraints);

        //Add in Show on CIS Radio Button
        showOnCISRadioButton = new JRadioButton();
        GridBagConstraints showOnCISRadioButtonConstraints = template.
                createGridBagConstraints(3, 19, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(showOnCISRadioButton, showOnCISRadioButtonConstraints);

        //Add in Job Progress Combo Box
        jobProgressComboBox = new JComboBox();
        GridBagConstraints jobProgressComboBoxConstraints = template.
                createGridBagConstraints(3, 20, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 10), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(jobProgressComboBox, jobProgressComboBoxConstraints);

        //Add in As At Label
        asAtLabel = new JLabel(template.headingString("As At:", 3));
        GridBagConstraints asAtLabelConstraints = template.
                createGridBagConstraints(4, 19, GridBagConstraints.NONE, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        frameContent.add(asAtLabel, asAtLabelConstraints);

        //Add in As At Text Field
        asAtTextField = new JTextField();
        asAtTextField.setBackground(Color.yellow);
        GridBagConstraints asAtTextFieldConstraints = template.
                createGridBagConstraints(4, 20, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(asAtTextField, asAtTextFieldConstraints);

        //Add in Ticket Allocated to Label
        ticketAllocatedToLabel = new JLabel(template.headingString("Ticket Allocated To:", 3));
        GridBagConstraints ticketAllocatedToLabelConstraints = template.
                createGridBagConstraints(0, 20, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        frameContent.add(ticketAllocatedToLabel, ticketAllocatedToLabelConstraints);

        //Add in Ticket Allocated Combo Box
        ticketAllocatedToComboBox = new JComboBox();
        GridBagConstraints ticketAllocatedToComboBoxConstraints = template.
                createGridBagConstraints(1, 20, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(ticketAllocatedToComboBox, ticketAllocatedToComboBoxConstraints);
        
        
        mainFrame.getContentPane().add(new JScrollPane(frameContent), BorderLayout.CENTER);
        mainFrame.pack();
        mainFrame.setVisible(true);
        
        return 20;
    }
    
    
    private int updateFormAddition() {
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        
        //Add in Updates Label
        updatesLabel = new JLabel(template.headingString("Updates", 3));
        GridBagConstraints updatesLabelConstraints = template.
                createGridBagConstraints(0, 21, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        frameContent.add(updatesLabel, updatesLabelConstraints);

        //Add in Estimataed Completion By Label
        estimatedCompletionByLabel = new JLabel(template.headingString("Estimated Completion By:", 3));
        GridBagConstraints estimatedCompletionByLabelConstraints = template.
                createGridBagConstraints(3, 21, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        frameContent.add(estimatedCompletionByLabel, estimatedCompletionByLabelConstraints);

        //Add in Updated At Label
        updatedAtLabel = new JLabel(template.headingString("Updated At:", 3));
        GridBagConstraints updatedAtLabelConstraints = template.
                createGridBagConstraints(4, 21, GridBagConstraints.NONE, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        frameContent.add(updatedAtLabel, updatedAtLabelConstraints);

        //Add in if Not Quick Fix Label
        ifNotAQuickFixLabel = new JLabel(template.headingString("If Not A Quick Fix", 3));
        GridBagConstraints ifNotAQuickFixLabelConstraints = template.
                createGridBagConstraints(1, 21, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        frameContent.add(ifNotAQuickFixLabel, ifNotAQuickFixLabelConstraints);

        //Add in the first Problem Updated Text Field
        updateTextArea1 = new JTextArea();
        updateTextArea1.setLineWrap(true);
        updateTextArea1.setPreferredSize(new Dimension(345, 45));
        updateTextArea1.setBorder(border);
        GridBagConstraints updateTextArea1Constraints = template.
                createGridBagConstraints(1, 22, GridBagConstraints.NONE, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.NORTHWEST, 0.0, 0.0, 3,
                2);
        frameContent.add(updateTextArea1, updateTextArea1Constraints);

        //Add in Estimated Completion By Text Field
        estimatedCompletionByTextField1 = new JTextField();
        estimatedCompletionByTextField1.setColumns(13);
        GridBagConstraints estimatedCompletionByTextField1Constraints = template.
                createGridBagConstraints(3, 22, GridBagConstraints.NONE, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        frameContent.add(estimatedCompletionByTextField1, estimatedCompletionByTextField1Constraints);

        JLabel timestampExample = new JLabel(template.headingString("e.g. 23/11/1963 17:15", 4));
        GridBagConstraints timestampExampleLabelConstraints = template.
                createGridBagConstraints(3, 23, GridBagConstraints.NONE, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.NORTH, 0.0, 0.0, 1,
                1);
        frameContent.add(timestampExample, timestampExampleLabelConstraints);
        
        //Add in Updated At Text Field
        updatedAtTextField1 = new JTextField();
        updatedAtTextField1.setBackground(Color.YELLOW);
        GridBagConstraints updatedAtTextField1Constraints = template.
                createGridBagConstraints(4, 22, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        frameContent.add(updatedAtTextField1, updatedAtTextField1Constraints);

        //Create a spacer to allow good aesthetic
        GridBagConstraints spacer3Constraints = template.
                createGridBagConstraints(0, 21, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 4,
                1);
        frameContent.add(Box.createVerticalStrut(100), spacer3Constraints);

        //Add in the second Problem Updated Text Field
        updateTextArea2 = new JTextArea();
        updateTextArea2.setLineWrap(true);
        updateTextArea2.setPreferredSize(new Dimension(345, 45));
        updateTextArea2.setBorder(border);
        GridBagConstraints updateTextArea2Constraints = template.
                createGridBagConstraints(1, 25, GridBagConstraints.NONE, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 3,
                2);
        frameContent.add(updateTextArea2, updateTextArea2Constraints);

        //Add in the second Estimated Completion By Text Field
        estimatedCompletionByTextField2 = new JTextField();
        estimatedCompletionByTextField2.setColumns(13);
        GridBagConstraints estimatedCompletionByTextField2Constraints = template.
                createGridBagConstraints(3, 26, GridBagConstraints.NONE, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 2,
                1);
        frameContent.add(estimatedCompletionByTextField2, estimatedCompletionByTextField2Constraints);

        //Add in the second Updated At Text Field
        updatedAtTextField2 = new JTextField();
        updatedAtTextField2.setBackground(Color.YELLOW);
        GridBagConstraints updatedAtTextField2Constraints = template.
                createGridBagConstraints(4, 26, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 2,
                1);
        frameContent.add(updatedAtTextField2, updatedAtTextField2Constraints);

        //Create a spacer to allow good aesthetic
        GridBagConstraints spacer4Constraints = template.
                createGridBagConstraints(0, 25, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 4,
                1);
        frameContent.add(Box.createVerticalStrut(60), spacer4Constraints);

        //Add in the third Problem Updated Text Field
        updateTextArea3 = new JTextArea();
        updateTextArea3.setLineWrap(true);
        updateTextArea3.setPreferredSize(new Dimension(345, 45));
        updateTextArea3.setBorder(border);
        GridBagConstraints updateTextArea3Constraints = template.
                createGridBagConstraints(1, 28, GridBagConstraints.NONE, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.SOUTHWEST, 0.0, 0.0, 3,
                2);
        frameContent.add(updateTextArea3, updateTextArea3Constraints);

        //Add in the second Estimated Completion By Text Field
        estimatedCompletionByTextField3 = new JTextField();
        estimatedCompletionByTextField3.setColumns(13);
        GridBagConstraints estimatedCompletionByTextField3Constraints = template.
                createGridBagConstraints(3, 29, GridBagConstraints.NONE, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 2,
                1);
        frameContent.add(estimatedCompletionByTextField3, estimatedCompletionByTextField3Constraints);

        //Add in the second Updated At Text Field
        updatedAtTextField3 = new JTextField();
        updatedAtTextField3.setBackground(Color.YELLOW);
        GridBagConstraints updatedAtTextField3Constraints = template.
                createGridBagConstraints(4, 29, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 2,
                1);
        frameContent.add(updatedAtTextField3, updatedAtTextField3Constraints);

        //Add in Job Completed Label
        jobCompletedLabel = new JLabel(template.headingString("Job Closed:", 3));
        GridBagConstraints jobCompletedLabelConstraints = template.
                createGridBagConstraints(0, 31, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        frameContent.add(jobCompletedLabel, jobCompletedLabelConstraints);

        //Add in Show on CIS Radio Button
        jobCompletedRadioButton = new JRadioButton();
        GridBagConstraints jobCompletedRadioButtonConstraints = template.
                createGridBagConstraints(1, 31, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(jobCompletedRadioButton, jobCompletedRadioButtonConstraints);

        //Add in the Job Completed Text Field
        jobCompletedTextField = new JTextField();
        jobCompletedTextField.setBackground(Color.YELLOW);
        GridBagConstraints jobCompletedTextFieldConstraints = template.
                createGridBagConstraints(4, 31, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(jobCompletedTextField, jobCompletedTextFieldConstraints);

        //Add in if Job Not Completed Label
        ifJobNotCompletedLabel = new JLabel(template.headingString("If Job Not Closed:", 3));
        GridBagConstraints ifJobNotCompletedLabelConstraints = template.
                createGridBagConstraints(0, 32, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        frameContent.add(ifJobNotCompletedLabel, ifJobNotCompletedLabelConstraints);

        //Add in if Job Not Completed Label
        nextUpdateDueLabel = new JLabel(template.headingString("Next Update Due", 3));
        GridBagConstraints nextUpdateDueLabelConstraints = template.
                createGridBagConstraints(1, 32, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        frameContent.add(nextUpdateDueLabel, nextUpdateDueLabelConstraints);

        //Add in the Job Completed Text Field
        nextUpdateDueTextField = new JTextField();
        nextUpdateDueTextField.setBackground(Color.yellow);
        GridBagConstraints nextUpdateDueTextFieldConstraints = template.
                createGridBagConstraints(2, 32, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(nextUpdateDueTextField, nextUpdateDueTextFieldConstraints);
                

        
       
        
        mainFrame.pack();
        mainFrame.setVisible(true);
        
        return 33;

    }
    
    private void submitResetButtons(int lastRow)
    {
         //Add in submit and reset buttons
        //Add in the Submit Form Button
        submitFormButton = new JButton("Submit Form");
        GridBagConstraints submitFormButtonConstraints = template.
                createGridBagConstraints(0, lastRow+1, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(10, 0, 20, 15), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(submitFormButton, submitFormButtonConstraints);
        
        //Add in a reset form Button
        resetFormButton = new JButton("Reset Form");
        GridBagConstraints resetFormButtonConstraints = template.
                createGridBagConstraints(1, lastRow+1, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(10, 0, 20, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(resetFormButton, resetFormButtonConstraints);
       
        
    }
    
    private void printButton(int lastRow, boolean entry)
    {
        //Add in a print Button
        String buttonText = "";
        if(entry)
        {
            buttonText = "Print & Submit Form";
        }
        else
        {
            buttonText = "Print Form";
        }
        printFormButton = new JButton(buttonText);
        GridBagConstraints printFormButtonConstraints = template.
                createGridBagConstraints(4, lastRow+1, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 20, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        frameContent.add(printFormButton, printFormButtonConstraints);
        
    }

    public JTextField getTicketReferenceTextField() {
        return ticketReferenceTextField;
    }

    public JTextField getDateTimeTextField() {
        return dateTimeTextField;
    }

    public JComboBox getTicketWrittenComboBox() {
        return ticketWrittenComboBox;
    }

    public JComboBox getTeamMembersComboBox() {
        return teamMembersComboBox;
    }

    public JComboBox getProblemLocationComboBox1() {
        return problemLocationComboBox1;
    }

    public JComboBox getProblemLocationComboBox2() {
        return problemLocationComboBox2;
    }

    public JComboBox getProblemLocationComboBox3() {
        return problemLocationComboBox3;
    }

    public JComboBox getProblemLocationComboBox4() {
        return problemLocationComboBox4;
    }

    public ArrayList<JRadioButton> getButtonsInGrid() {
        return buttonsInGrid;
    }

    public JTextField getProblemReportedByTextField() {
        return problemReportedByTextField;
    }

    public JComboBox getWhoIsAComboBox() {
        return whoIsAComboBox;
    }

    public JComboBox getContactViaComboBox() {
        return contactViaComboBox;
    }

    public JTextField getContactNumberTextField() {
        return contactNumberTextField;
    }

    public JComboBox getLocationVenueVillageComboBox() {
        return locationVenueVillageComboBox;
    }

    public JButton getSubmitFormButton()
    {
        return submitFormButton;
    }

    public JButton getResetFormButton()
    {
        return resetFormButton;
    }

    public JTextArea getProblemDescriptionTextArea()
    {
        return problemDescriptionTextArea;
    }

    public JFrame getMainFrame()
    {
        return mainFrame;
    }

    public JComboBox getDelegateImpactComboBox()
    {
        return delegateImpactComboBox;
    }

    public JRadioButton getShowOnCISRadioButton()
    {
        return showOnCISRadioButton;
    }

    public JComboBox getJobProgressComboBox()
    {
        return jobProgressComboBox;
    }

    public JTextField getAsAtTextField()
    {
        return asAtTextField;
    }

    public JComboBox getTicketAllocatedToComboBox()
    {
        return ticketAllocatedToComboBox;
    }

    public JTextArea getUpdateTextArea1()
    {
        return updateTextArea1;
    }

    public JTextField getEstimatedCompletionByTextField1()
    {
        return estimatedCompletionByTextField1;
    }

    public JTextField getUpdatedAtTextField1()
    {
        return updatedAtTextField1;
    }

    public JTextArea getUpdateTextArea2()
    {
        return updateTextArea2;
    }

    public JTextField getEstimatedCompletionByTextField2()
    {
        return estimatedCompletionByTextField2;
    }

    public JTextField getUpdatedAtTextField2()
    {
        return updatedAtTextField2;
    }

    public JTextArea getUpdateTextArea3()
    {
        return updateTextArea3;
    }

    public JTextField getEstimatedCompletionByTextField3()
    {
        return estimatedCompletionByTextField3;
    }

    public JTextField getUpdatedAtTextField3()
    {
        return updatedAtTextField3;
    }

    public JRadioButton getJobCompletedRadioButton()
    {
        return jobCompletedRadioButton;
    }

    public JTextField getJobCompletedTextField()
    {
        return jobCompletedTextField;
    }

    public JTextField getNextUpdateDueTextField()
    {
        return nextUpdateDueTextField;
    }

    public JButton getPrintFormButton() {
        return printFormButton;
    }
    
    
    
    
}
