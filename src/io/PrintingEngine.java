/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import au.com.bytecode.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import org.joda.time.DateTime;
import system.Ticket;
import system.User;

/**
 *
 * @author jonathanrainer
 */
public class PrintingEngine
{
    private CSVWriter csvWriter;
    private static final String[] headings = {"jobRefId", "raisedDate",
    "raisedTime", "raisedByName", "raisedByTeam", "allocatedTeam",
    "problemLocation", "problemDescription", "problemReportedBy",
    "whoIsA", "contactName", "contactNumber", "showOnCIS"};
    
    public PrintingEngine()
    {
        
    }
    
    public boolean printFile(Ticket ticket, String fileName)
    {
        try
        {
            File csvFile = new File("/Users/jonathanrainer/Documents/Nexus/csvUploadDir/" + fileName);
            csvFile.createNewFile();
            FileWriter fileWriter = new FileWriter(csvFile);
            csvWriter = new CSVWriter(fileWriter);
            int jobRefId = ticket.getJobRefId();
            DateTime raised = ticket.getDateTime();
            User ticketRaisedBy = ticket.getTicketRaisedBy();
            String ticketAllocatedTo = ticket.getTicketAllocatedTo();
            String problemLocation = ticket.getProblemLocation();
            problemLocation = problemLocation.replace("-", ", ");
            String problemDescription = ticket.getProblemDescription();
            String reportedBy = ticket.getReportedBy();
            String whoIsA = ticket.getWhoIsA();
            String contactMethod = ticket.getContactVia();
            String contactNumber = ticket.getContactNumber();
            boolean showOnCIS = ticket.isShowOnCIS();
            String onCIS;
            if(showOnCIS)
            {
                onCIS = "Yes";
            }
            else
            {
                onCIS = "No";
            }
            String[] data = {""+jobRefId, raised.toString("dd/MM/yyyy"), 
                raised.toString("HH:mm"), ticketRaisedBy.getName(), 
                ticketRaisedBy.getTeam(), ticketAllocatedTo, problemLocation,
                problemDescription, reportedBy, whoIsA, contactMethod, contactNumber, onCIS};
            csvWriter.writeNext(headings);
            csvWriter.writeNext(data);
            csvWriter.close();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
        finally
        {
            
        }
                
        
    }
    
}
