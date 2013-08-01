/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import au.com.bytecode.opencsv.CSVWriter;
import io.MYSQLEngine;
import io.RemoteInterfaceEngine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.joda.time.DateTime;

/**
 *
 * @author Jonathan Rainer
 */
public class PrintRunner extends SwingWorker<Void,String> {
    
    private Ticket ticket;
    private RemoteInterfaceEngine remoteInterfaceEngine;
    private MYSQLEngine mysqlEngine;
    private CSVWriter csvWriter;
    private static final String[] headings = {"jobRefId", "raisedDate",
    "raisedTime", "raisedByName", "raisedByTeam", "allocatedTeam",
    "problemLocation", "problemDescription", "problemReportedBy",
    "whoIsA", "contactName", "contactNumber", "showOnCIS"};
    //private static final String LOCALCSVDIR = "/home/jonathanrainer/Nexus/localCSV/";
    //private static final String LOGGINGDIR = "/home/jonathanrainer/Nexus/logs/";
    private static final String LOCALCSVDIR = "C:\\Nexus\\localCSV\\";
    private static final String LOGGINGDIR = "C:\\Nexus\\logs\\";
    private static final String LOGGINGDATEFORMAT = "ddMMyyyyHHmm";

    public PrintRunner(Ticket ticket, RemoteInterfaceEngine 
            remoteInterfaceEngine, MYSQLEngine mysqlEngine)
    {
        this.ticket = ticket;
        this.remoteInterfaceEngine = remoteInterfaceEngine;
        this.mysqlEngine = mysqlEngine;
    }
    
    @Override
    protected Void doInBackground() throws Exception {
        
        DateTime now = new DateTime();
        String fileName = ticket.getJobRefId() + "_"
                + now.toString("ddMMyyyyHHmm") + ".csv";
        firePropertyChange("Message", "", "Creating Latex File... \n");
        printFile(ticket, fileName);
        firePropertyChange("Message", "", "Latex File Created ... \n");
        firePropertyChange("Message", "", "Transferring to Remote File Store... \n");
        remoteInterfaceEngine.transferLocalToRemote(fileName);
        firePropertyChange("Message", "", "Transfer Complete... \n");
        firePropertyChange("Message", "", "Compiling Latex File... \n");
        remoteInterfaceEngine.compileLatexFiles(fileName);
        firePropertyChange("Message", "", "Latex File Complied... \n");
        firePropertyChange("Message", "", "Transfer File Back to Local File Store... \n");
        String finishedTicket = remoteInterfaceEngine.transferRemoteToLocal(fileName);
        firePropertyChange("Message", "", "Invoking Print Dialog... \n");
        printPDF(finishedTicket);
        firePropertyChange("Message", "", "Ticket Printed... \n");
        firePropertyChange("Message", "", "Clearing Local Directories... \n");
        FileUtils.cleanDirectory(new File(LOCALCSVDIR));
        FileUtils.cleanDirectory(new File(FilenameUtils.getFullPath(finishedTicket)));
        if(ticket.getJobProgress().equals("Issue Reported"))
        {
            mysqlEngine.markTicketPrinted(ticket);
        }        
        return null;
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
        + "SumatraPDF.exe" + " -print-dialog -exit-on-print  " + filePath);
         builder.redirectErrorStream(true);
        try {
            Process p = builder.start();
            // Debugging code if necessary
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        DateTime now = new DateTime();
        File loggingFile = new File(LOGGINGDIR + "printLog_" + now.toString(LOGGINGDATEFORMAT) + ".txt");
        FileWriter fw = new FileWriter(loggingFile);
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            fw.write(line);
        }
        fw.close();
        r.close();
        } catch (IOException ex) {
            Logger.getLogger(PrintRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
