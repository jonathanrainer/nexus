/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import gui.DashBoard;
import io.MYSQLEngine;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonathan Rainer
 */
public class DataCollector extends Thread {
    
    private ArrayList<Ticket> issueReportedTickets;
    private ArrayList<Ticket> ticketPrintedTickets;
    private ArrayList<Ticket> jobInProgressTickets;
    private DashBoard dashBoard;
    private MYSQLEngine mysqlEngine;
    
    public DataCollector(ArrayList<Ticket> issueReportedTickets,
            ArrayList<Ticket> ticketPrintedTickets, ArrayList<Ticket>
                    jobInProgressTickets, DashBoard dashBoard, 
                    MYSQLEngine mysqlEngine)
    {
        this.issueReportedTickets = issueReportedTickets;
        this.ticketPrintedTickets = ticketPrintedTickets;
        this.jobInProgressTickets = jobInProgressTickets;
        this.dashBoard = dashBoard;
        this.mysqlEngine = mysqlEngine;
    }
    
    @Override
    public synchronized void run()
    {
        while(true)
        {
        ArrayList<Ticket> newIssueReportedTickets = mysqlEngine.
                getIssueReportedTickets();
        ArrayList<Ticket> newTicketPrintedTickets = mysqlEngine.
                getTicketPrintedTickets();
        ArrayList<Ticket> newJobInProgressTickets = mysqlEngine.
                getjobInProgressTickets();
        
        int i = 0;
        ArrayList<Ticket> newTickets = new ArrayList<>();
        while (i < newIssueReportedTickets.size())
        {
            if(!issueReportedTickets.contains(newIssueReportedTickets.get(i)))
            {
                newTickets.add(newIssueReportedTickets.get(i));
            }   
            i++;
        }
        
        int j = 0;
        while (j < newTicketPrintedTickets.size())
        {
            if(!ticketPrintedTickets.contains(newTicketPrintedTickets.get(i)))
            {
                newTickets.add(newTicketPrintedTickets.get(i));
            }   
            j++;
        }
        
        int k = 0;
        while (k < newJobInProgressTickets.size())
        {
            if(!jobInProgressTickets.contains(newJobInProgressTickets.get(i)))
            {
                newTickets.add(newJobInProgressTickets.get(i));
            }   
            k++;
        }
        
        dashBoard.setIssueReportedTickets(newIssueReportedTickets);
        dashBoard.setTicketPrintedTickets(newTicketPrintedTickets);
        dashBoard.setJobInProgressTickets(newJobInProgressTickets);
        dashBoard.setNewTickets(newTickets);
        
        dashBoard.dashBoardUpdate();
            try {
                sleep(300000l);
            } catch (InterruptedException ex) {
                Logger.getLogger(DataCollector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

 
