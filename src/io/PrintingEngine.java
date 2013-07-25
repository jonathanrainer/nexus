/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import au.com.bytecode.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private static final String LOCALCSVDIR = "C:\\Nexus\\localCSV\\";
    
    public PrintingEngine()
    {
        
    }
    
    public boolean printFile(Ticket ticket, String fileName)
    {
        try
        {
            File csvFile = new File(LOCALCSVDIR + fileName);
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
   
    public void printPDF(String filePath)
    {
         ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "C:" + 
                 File.separatorChar + "SumatraPDF" + File.separatorChar 
        + "SumatraPDF.exe" + " -print-dialog -silent -exit-on-print  " + filePath);
         builder.redirectErrorStream(true);
        try {
            Process p = builder.start();
            // Debugging code if necessary
//            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
//        String line;
//        while (true) {
//            line = r.readLine();
//            if (line == null) { break; }
//            System.out.println(line);
//        }
        } catch (IOException ex) {
            Logger.getLogger(PrintingEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
