/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import io.MYSQLEngine;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import system.DataCollector;
import system.Ticket;
import gui.Template;

/**
 *
 * @author Jonathan Rainer
 */
public final class DashBoard {
    
    private JFrame mainFrame;
    private DataCollector dataCollector;
    private GridBagLayout layout;
    private Template template;
    private ArrayList<Ticket> issueReportedTickets;
    private ArrayList<Ticket> ticketPrintedTickets;
    private ArrayList<Ticket> jobInProgressTickets;
    private ArrayList<Ticket> newTickets;
    private static final int TOPROW = 0;
    
    public DashBoard(MYSQLEngine mysqlEngine)
    {
        template = new Template();
        
        issueReportedTickets = new ArrayList<>();
        ticketPrintedTickets = new ArrayList<>();
        jobInProgressTickets = new ArrayList<>();
        newTickets = new ArrayList<>();
        
        mainFrame = new JFrame("NWNE Nexus Dashboard");
        
        layout = new GridBagLayout();
        
        mainFrame.setLayout(layout);
        mainFrame.setPreferredSize(new Dimension(1024, 800));
        
        dataCollector = new DataCollector(issueReportedTickets, ticketPrintedTickets,
                jobInProgressTickets, this, mysqlEngine);
        
        getData();
        
        dashBoardUpdate();
        
        mainFrame.pack();
        mainFrame.setVisible(true);
        
    }
    
    private void getData()
    {
        dataCollector.start();
    }
    
    public synchronized void dashBoardUpdate()
    {
        int i = TOPROW;
        JPanel issueReportedHeader = new JPanel();
        JLabel issueReportedHeaderLabel = new JLabel("<html><h1><b>Issue Reported</b></h1></html>");
        GridBagConstraints issueReportedHeaderConstraints = template.
                createGridBagConstraints(0, i, GridBagConstraints.NONE, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                4);
        i++;
        Iterator<Ticket> it1 = issueReportedTickets.iterator();
        while(it1.hasNext())
        {
            Ticket ticketToAdd = it1.next();
            String textToDisplay = 
                    "<html><h2><b> ID: </h2></b> <h4>" + ticketToAdd.getJobRefId() +
                            "</h4><br><br>";
            JLabel labelToAdd = new JLabel(textToDisplay);
            JPanel panelToAdd = new JPanel();
            panelToAdd.add(labelToAdd);
            GridBagConstraints panelConstraints = template.
                createGridBagConstraints(0, i, GridBagConstraints.NONE, 0, 0,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                4);
            i++;
            mainFrame.add(panelToAdd, panelConstraints);
        }
        mainFrame.repaint();
        System.out.println(issueReportedTickets);
    }

    public synchronized void setIssueReportedTickets(ArrayList<Ticket> issueReportedTickets) {
        this.issueReportedTickets = issueReportedTickets;
    }

    public synchronized void setTicketPrintedTickets(ArrayList<Ticket> ticketPrintedTickets) {
        this.ticketPrintedTickets = ticketPrintedTickets;
    }

    public synchronized void setJobInProgressTickets(ArrayList<Ticket> jobInProgressTickets) {
        this.jobInProgressTickets = jobInProgressTickets;
    }

    public synchronized void setNewTickets(ArrayList<Ticket> newTickets) {
        this.newTickets = newTickets;
    }
    
    
    
    
    
    
}
