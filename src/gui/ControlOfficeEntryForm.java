/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import io.MYSQLEngine;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
    private JComboBox keyWordComboBox;
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
    private JTextField updatedAtByTextField1;
    private JTextArea updateTextArea2;
    private JTextField estimatedCompletionByTextField2;
    private JTextField updatedAtByTextField2;
    private JTextArea updateTextArea3;
    private JTextField estimatedCompletionByTextField3;
    private JTextField updatedAtByTextField3;
    private JLabel jobCompletedLabel;
    private JRadioButton jobCompletedRadioButton;
    private JTextField jobCompletedTextField;
    private JLabel ifJobNotCompletedLabel;
    private JLabel nextUpdateDueLabel;
    private JTextField nextUpdateDueTextField;
    private JLabel nextUpdateDueExplanationLabel;

    public ControlOfficeEntryForm(MYSQLEngine mysqlEngine, boolean entry) {
        // Create Record in Database that relates to this form
        mysqlEngine = this.mysqlEngine;
        //
        template = new Template();
        // Define a blank JLabel so that layout can more easily be specified

        if(entry)
        {
            String title = "NWNE Nexus - Control Office Job Ticket Entry";
            formCreation(title);
        }
        
    }
    
    private void formCreation(String title)
    {
        // Get initial templated Frame
        mainFrame = template.giveGridBagTemplatedJFrame(title);
        
        // Create the introText label
        String introText = "<html>This form is to raise Job Tickets for"
                + " Control Office to manage / administer.<br><br>"
                + "The white areas are for reporting the Job. <p>"
                + "The yellow areas are automated <p>"
                + "The grey areas are for Control Office Admin.<br><br>"
                + "Some of the information is for audit purposes and other parts <p>"
                + "help to run the Delegate Information Screen - so please fill"
                + "in as much as possible.</html>";
        introTextLabel = new JLabel(introText);
        GridBagConstraints introTextLabelConstraints = template.
                createGridBagConstraints(0, 1, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                5);
        mainFrame.add(introTextLabel,introTextLabelConstraints);
        
        /**
         * First Row of Form
         */
        
        //Create the ticket reference label and constraints
        ticketReferenceLabel = new JLabel(template.headingString("Ticket Reference:", 3));
        GridBagConstraints ticketReferenceLabelConstraints = template.
                createGridBagConstraints(0, 2, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        mainFrame.add(ticketReferenceLabel,ticketReferenceLabelConstraints);
        
        //Create the ticket reference text box and constraints
        ticketReferenceTextField = new JTextField("");
        ticketReferenceTextField.setBackground(Color.yellow);
        GridBagConstraints ticketReferenceTextFieldConstraints = template.
                createGridBagConstraints(1, 2, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        mainFrame.add(ticketReferenceTextField, ticketReferenceTextFieldConstraints);
        
        //Create a spacer to allow good aesthetic
        GridBagConstraints spacer1Constraints = template.
                createGridBagConstraints(2, 2, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        mainFrame.add(Box.createHorizontalStrut(100),spacer1Constraints);
        
        //Create the Date/Time label
        dateTimeLabel = new JLabel(template.headingString("Date/Time:", 3));
        GridBagConstraints dateTimeLabelConstraints = template.
                createGridBagConstraints(3, 2, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        mainFrame.add(dateTimeLabel,dateTimeLabelConstraints);
        
        //Create the Date/Time text box and constraints
        dateTimeTextField = new JTextField("");
        dateTimeTextField.setBackground(Color.yellow);
        GridBagConstraints dateTimeTextFieldConstraints = template.
                createGridBagConstraints(4, 2, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        mainFrame.add(dateTimeTextField, dateTimeTextFieldConstraints);
        
        /**
         * Second Row Of Form
         */
        
        //Create the Ticket Written By Label and constraints
        ticketWrittenLabel = new JLabel(template.headingString("Ticket Written By:", 3));
        GridBagConstraints ticketWrittenLabelConstraints = template.
                createGridBagConstraints(0, 3, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        mainFrame.add(ticketWrittenLabel,ticketWrittenLabelConstraints);
        
        //Create the Ticket Written text box and constraints
        ticketWrittenComboBox = new JComboBox();
        GridBagConstraints ticketWrittenComboBoxConstraints = template.
                createGridBagConstraints(1, 3, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        mainFrame.add(ticketWrittenComboBox, ticketWrittenComboBoxConstraints);
        
        teamMembersComboBox = new JComboBox();
        GridBagConstraints teamMembersComboBoxConstraints = template.
                createGridBagConstraints(2, 3, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        mainFrame.add(teamMembersComboBox,teamMembersComboBoxConstraints);
        
        /**
         * Third Row of Form
         */
        
        //Create the Where is the Problem Label and constraints
        problemLocationLabel = new JLabel(template.headingString("Where Is The Problem? :", 3));
        GridBagConstraints problemLocationLabelConstraints = template.
                createGridBagConstraints(0, 4, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        mainFrame.add(problemLocationLabel,problemLocationLabelConstraints);
        
        //Create the Where is the Problem helper Label and constraints
        String helperText = "Choose one of each of the following options.";
        problemLocationHelperLabel = new JLabel(template.headingString(helperText, 4));
        GridBagConstraints problemLocationHelperLabelConstraints = template.
                createGridBagConstraints(1, 4, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.EAST, 0.0, 0.0, 1,
                4);
        mainFrame.add(problemLocationHelperLabel,problemLocationHelperLabelConstraints);
        
        /**
         * Fourth Row of Form
         */
        
        //Create a spacer to allow good aesthetic
        GridBagConstraints spacer2Constraints = template.
                createGridBagConstraints(0, 5, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        mainFrame.add(Box.createHorizontalStrut(50),spacer2Constraints);
        
        //Add in ComboBox 1
        problemLocationComboBox1 = new JComboBox();
        GridBagConstraints problemLocationComboBox1Constraints = template.
                createGridBagConstraints(1, 5, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        mainFrame.add(problemLocationComboBox1,problemLocationComboBox1Constraints);
        
        //Add in ComboBox 2
        problemLocationComboBox2 = new JComboBox();
        GridBagConstraints problemLocationComboBox2Constraints = template.
                createGridBagConstraints(2, 5, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        mainFrame.add(problemLocationComboBox2,problemLocationComboBox2Constraints);
        
        //Add in ComboBox 3
        problemLocationComboBox3 = new JComboBox();
        GridBagConstraints problemLocationComboBox3Constraints = template.
                createGridBagConstraints(3, 5, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        mainFrame.add(problemLocationComboBox3,problemLocationComboBox3Constraints);
        
        //Add in ComboBox 4
        problemLocationComboBox4 = new JComboBox();
        GridBagConstraints problemLocationComboBox4Constraints = template.
                createGridBagConstraints(4, 5, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        mainFrame.add(problemLocationComboBox4,problemLocationComboBox4Constraints);
        
        /**
         * Fifth Row of Form
         */
        
        //Create the Where is the Problem Label and constraints
        problemDescriptionLabel = new JLabel(template.headingString("What Is The Problem? :", 3));
        GridBagConstraints problemDescriptionLabelConstraints = template.
                createGridBagConstraints(0, 6, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.NORTH, 0.0, 0.0, 1,
                1);
        mainFrame.add(problemDescriptionLabel,problemDescriptionLabelConstraints);
        
        //Create Text Area for Problem Description
        problemDescriptionTextArea = new JTextArea();
        GridBagConstraints problemDescriptionTextAreaConstraints = template.
                createGridBagConstraints(1, 6, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.NORTH, 0.0, 0.0, 3,
                3);
        mainFrame.add(problemDescriptionTextArea,problemDescriptionTextAreaConstraints);
        
        // Create the helper text next to description 
        problemDescriptionExplanationLabel = new JLabel(template.headingString(" Be brief:"
                + " You only <br> have 256 characters.", 4));
        GridBagConstraints problemDescriptionExplanationLabelConstraints = template.
                createGridBagConstraints(4, 6, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.EAST, 0.0, 0.0, 1,
                1);
        mainFrame.add(problemDescriptionExplanationLabel,
                problemDescriptionExplanationLabelConstraints);
        
        /*
         * 6th Row of the Form
         */
        
        //Create the For the CIS label
        forCISLabel = new JLabel(template.headingString("For The CIS :", 3));
        GridBagConstraints forCISLabelConstraints = template.
                createGridBagConstraints(0, 7, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.NORTH, 0.0, 0.0, 4,
                1);
        mainFrame.add(forCISLabel,forCISLabelConstraints);
        
        //Create the Key words label
        keyWordsLabel = new JLabel(template.headingString("Key Words", 3));
        GridBagConstraints keyWordsLabelConstraints = template.
                createGridBagConstraints(1, 7, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.NORTH, 0.0, 0.0, 1,
                1);
        mainFrame.add(keyWordsLabel,keyWordsLabelConstraints);
        
        //Create the Delegate Impact label
        delegateImpactLabel = new JLabel(template.headingString("Delegate Impact", 3));
        GridBagConstraints delegateImpactLabelConstraints = template.
                createGridBagConstraints(2, 7, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.NORTH, 0.0, 0.0, 1,
                1);
        mainFrame.add(delegateImpactLabel,delegateImpactLabelConstraints);
        
        //Create the Key Word Explanation label
        keyWordExplanationLabel = new JLabel(template.headingString("Pick Up to 5 of the following", 5));
        GridBagConstraints keyWordExplanationLabelConstraints = template.
                createGridBagConstraints(1, 8, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.NORTH, 0.0, 0.0, 1,
                1);
        mainFrame.add(keyWordExplanationLabel,keyWordExplanationLabelConstraints);
        
        //Add in Key Words Combo Box
        keyWordComboBox = new JComboBox();
        GridBagConstraints keyWordComboBoxConstraints = template.
                createGridBagConstraints(1, 9, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 2,
                1);
        mainFrame.add(keyWordComboBox, keyWordComboBoxConstraints);
        
        //Create the Show On CIS label
        showOnCISLabel = new JLabel(template.headingString("Show On CIS?", 3));
        GridBagConstraints showOnCISLabelConstraints = template.
                createGridBagConstraints(2, 8, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.NORTH, 0.0, 0.0, 1,
                1);
        mainFrame.add(showOnCISLabel,showOnCISLabelConstraints);
        
        //Create the Job Progress label
        jobProgressLabel = new JLabel(template.headingString("Job Progress", 3));
        GridBagConstraints jobProgressLabelConstraints = template.
                createGridBagConstraints(2, 9, GridBagConstraints.BOTH, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.NORTH, 0.0, 0.0, 1,
                1);
        mainFrame.add(jobProgressLabel,jobProgressLabelConstraints);
        
        //Add in Delegate Impact Combo Box
        delegateImpactComboBox = new JComboBox();
        GridBagConstraints delegateImpactComboBoxConstraints = template.
                createGridBagConstraints(3, 7, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        mainFrame.add(delegateImpactComboBox, delegateImpactComboBoxConstraints);
        
        //Add in Show on CIS Radio Button
        showOnCISRadioButton = new JRadioButton();
        GridBagConstraints showOnCISRadioButtonConstraints = template.
                createGridBagConstraints(3, 8, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        mainFrame.add(showOnCISRadioButton, showOnCISRadioButtonConstraints);
        
        //Add in Job Progress Combo Box
        jobProgressComboBox = new JComboBox();
        GridBagConstraints jobProgressComboBoxConstraints = template.
                createGridBagConstraints(3, 9, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        mainFrame.add(jobProgressComboBox, jobProgressComboBoxConstraints);
        
        //Add in As At Label
        asAtLabel = new JLabel(template.headingString("As At:", 3));
        GridBagConstraints asAtLabelConstraints = template.
                createGridBagConstraints(4, 8, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        mainFrame.add(asAtLabel, asAtLabelConstraints);
        
        //Add in As At Text Field
        asAtTextField = new JTextField();
        asAtTextField.setBackground(Color.yellow);
        GridBagConstraints asAtTextFieldConstraints = template.
                createGridBagConstraints(4, 9, GridBagConstraints.HORIZONTAL, 5, 5,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        mainFrame.add(asAtTextField, asAtTextFieldConstraints);
        
        
        
        mainFrame.setVisible(true);
        
        
    }
}
