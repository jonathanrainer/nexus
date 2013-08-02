/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import io.MYSQLEngine;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jonathanrainer
 */
public class RetrievalsMenu
{
    private Template template;
    private JFrame mainFrame;
    private JPanel contentPane;
    private JButton allTickets;
    private JButton ticketPrinted;
    private JButton jobInProgress;
    private JButton jobEscalated;
    private JButton jobDone;
    
    public RetrievalsMenu()
    {
        template = new Template();
        mainFrame = template.giveTemplatedJFrame("NWNE Nexus - Retrievals Menu");
        JLabel title = new JLabel(template.headingString("Retrievals Menu", 2), JLabel.CENTER);
        contentPane = template.giveMenuTemplatedJPanel("NWNE Nexus - Retrievals Menu");
        GridBagConstraints titleConstraints = template.
                createGridBagConstraints(0, 1, GridBagConstraints.BOTH, 10, 10,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                5);
        allTickets = new JButton(template.headingString("Retrieve All Tickets", 3));
        GridBagConstraints allTicketsConstraints = template.
                createGridBagConstraints(0, 2, GridBagConstraints.BOTH, 10, 10,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                5);
        ticketPrinted = new JButton(template.headingString("Retrieve All Tickets Marked 'Ticket Printed'", 3));
        GridBagConstraints ticketPrintedConstraints = template.
                createGridBagConstraints(0, 3, GridBagConstraints.BOTH, 10, 10,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                5);
        jobInProgress = new JButton(template.headingString("Retrieve All Tickets Marked 'Job In Progress'", 3));
        GridBagConstraints jobInProgressConstraints = template.
                createGridBagConstraints(0, 4, GridBagConstraints.BOTH, 10, 10,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                5);
        jobEscalated = new JButton(template.headingString("Retrieve All Tickets Marked 'Job Escalated", 3));
        GridBagConstraints jobEscalatedConstraints = template.
                createGridBagConstraints(0, 5, GridBagConstraints.BOTH, 10, 10,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                5);
        jobDone = new JButton(template.headingString("Retrieve All Tickets Marked 'Job Done'", 3));
        GridBagConstraints jobDoneConstraints = template.
                createGridBagConstraints(0, 6, GridBagConstraints.BOTH, 10, 10,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                5);
        contentPane.add(title, titleConstraints);
        contentPane.add(allTickets, allTicketsConstraints);
        contentPane.add(ticketPrinted, ticketPrintedConstraints);
        contentPane.add(jobInProgress, jobInProgressConstraints);
        contentPane.add(jobEscalated, jobEscalatedConstraints);
        contentPane.add(jobDone, jobDoneConstraints);
        mainFrame.add(contentPane);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public JFrame getMainFrame()
    {
        return mainFrame;
    }

    public JPanel getContentPane()
    {
        return contentPane;
    }

    public JButton getAllTickets()
    {
        return allTickets;
    }

    public JButton getTicketPrinted()
    {
        return ticketPrinted;
    }

    public JButton getJobInProgress()
    {
        return jobInProgress;
    }

    public JButton getJobEscalated()
    {
        return jobEscalated;
    }

    public JButton getJobDone()
    {
        return jobDone;
    }
    
    
}
