/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import io.MYSQLEngine;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    private JLabel ticketReferenceLabel;
    private JTextField ticketReferenceDisplay;
    private JLabel dateTimeLabel;
    private JTextField dateTimeDisplay;

    public ControlOfficeEntryForm(MYSQLEngine mysqlEngine) {
        mysqlEngine = this.mysqlEngine;
        template = new Template();
        // Define a blank JLabel so that layout can more easily be specified

        mainFrame = template.giveTemplatedJFrame("test");
        ticketReferenceLabel = new JLabel(template.headingString
                ("Ticket Reference",2));
        GridBagConstraints ticketReferenceLabelConstraints = template.
                createGridBagConstraints(0, 3, GridBagConstraints.BOTH, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        mainFrame.add(ticketReferenceLabel, ticketReferenceLabelConstraints);
        //
        ticketReferenceDisplay = new JTextField(extendIDField());
        ticketReferenceDisplay.setEditable(false);
        GridBagConstraints ticketReferenceDisplayConstraints = template.
                createGridBagConstraints(1, 3, GridBagConstraints.HORIZONTAL, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        mainFrame.add(ticketReferenceDisplay,ticketReferenceDisplayConstraints);
        //
        GridBagConstraints spacingDisplayConstraints = template.
                createGridBagConstraints(2, 3, GridBagConstraints.HORIZONTAL, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.WEST, 0.0, 0.0, 1,
                1);
        mainFrame.add(Box.createHorizontalStrut(100),spacingDisplayConstraints);
        //
        dateTimeLabel = new JLabel(template.headingString("Date/Time",2));
        GridBagConstraints dateTimeLabelConstraints = template.
                createGridBagConstraints(3, 3, GridBagConstraints.BOTH, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.EAST, 0.0, 0.0, 1,
                1);
        mainFrame.add(dateTimeLabel, dateTimeLabelConstraints);
        dateTimeDisplay = new JTextField("0000000");
        dateTimeDisplay.setEditable(false);
        GridBagConstraints dateTimeDisplayConstraints = template.
                createGridBagConstraints(4, 3, GridBagConstraints.HORIZONTAL, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.EAST, 0.0, 0.0, 1,
                1);
        mainFrame.add(dateTimeDisplay,dateTimeDisplayConstraints);
        mainFrame.setVisible(true);
    }
    
    private String extendIDField()
    {
        return "0000";
    }
}
