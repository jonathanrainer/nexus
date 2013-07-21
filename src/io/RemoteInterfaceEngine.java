/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import com.trilead.ssh2.ChannelCondition;
import com.trilead.ssh2.Connection;
import com.trilead.ssh2.SCPClient;
import com.trilead.ssh2.Session;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author jonathanrainer
 */
public class RemoteInterfaceEngine
{
    private static final String LOCALCSVDIR = "/Users/jonathanrainer/Documents/Nexus/csvUploadDir/";
    private static final String REMOTECSVLOCATION = "/home/nexususer/Nexus/csvTransferDir/";
    private static final String REMOTETEXLOCATION = "/home/nexususer/Nexus/latexTemplates/";
    private static final String REMOTEOUTPUTLOCATION = "/home/nexususer/Nexus/pdfTickets/";
    private static final String LOCALOUTPUTLOCATION = "/Users/jonathanrainer/Documents/Nexus/pdfTickets/";
    private static final String TEXLOGGINGLOCATION = "/home/nexususer/Nexus/logging/";
    private String hostname;
    private String username;
    private String password;

    public RemoteInterfaceEngine()
    {
        hostname = "jonathanrainer.asuscomm.com";
        username = "nexususer";
        password = "nexuspassword";
    }

    public void transferLocalToRemote(String fileName)
    {
        
        try
        {
            Connection conn = new Connection(hostname);

            conn.connect();

            /* Authenticate.
             * If you get an IOException saying something like
             * "Authentication method password not supported by the server at this stage."
             * then please check the FAQ.
             */

            boolean isAuthenticated = conn.authenticateWithPassword(username, password);

            if (isAuthenticated == false)
            {
                throw new IOException("Authentication failed.");
            }

            /* Create a session */

            SCPClient scpClient = new SCPClient(conn);
            
            scpClient.put(LOCALCSVDIR + fileName, fileName, REMOTECSVLOCATION, "0755");

            /* Close the connection */

            conn.close();

        } catch (IOException e)
        {
            e.printStackTrace(System.err);
            System.exit(2);
        }
    }
    
    public void compileLatexFiles(String fileName)
    {
        try
        {
            Connection conn = new Connection(hostname);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(username, password);
            if(isAuthenticated == false)
            {
                throw new IOException("Authentication Failed!");
            }
            
            Session sesh = conn.openSession();
            sesh.execCommand(compileLatexFilesCommand(fileName));
            String postScriptCommand = "dvi2ps -c " + REMOTEOUTPUTLOCATION + FilenameUtils.removeExtension(fileName) + ".ps " 
                    + REMOTEOUTPUTLOCATION + FilenameUtils.removeExtension(fileName) + ".dvi";
            System.out.println(postScriptCommand);
            sesh.close();
            Session psSesh = conn.openSession();
            psSesh.execCommand(postScriptCommand);
            try
            {
                Thread.sleep(8000l);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(RemoteInterfaceEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
            psSesh.close();
            conn.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getCause());
        }
            
    }
    
    private String compileLatexFilesCommand(String fileName)
    {
        String fileNameSansExtension = FilenameUtils.removeExtension(fileName);
        String command = "latex -output-directory " + REMOTEOUTPUTLOCATION
                + " -jobname=" + fileNameSansExtension + " '\\providecommand{\\csvFileLocation}{"
                + REMOTECSVLOCATION + fileName + "}\\input{ " + REMOTETEXLOCATION + "jobticket.tex}'"
                + " >> " + TEXLOGGINGLOCATION + fileNameSansExtension + "-log.txt";
        return command;
    }
    
    public String transferRemoteToLocal(String fileName)
    {
        String pdfFileName = "";        
        try
        {
            Connection conn = new Connection(hostname);

            conn.connect();

            /* Authenticate.
             * If you get an IOException saying something like
             * "Authentication method password not supported by the server at this stage."
             * then please check the FAQ.
             */

            boolean isAuthenticated = conn.authenticateWithPassword(username, password);

            if (isAuthenticated == false)
            {
                throw new IOException("Authentication failed.");
            }

            /* Create a session */

            SCPClient scpClient = new SCPClient(conn);
            
            pdfFileName = FilenameUtils.removeExtension(fileName) + ".ps";
            scpClient.get(REMOTEOUTPUTLOCATION + pdfFileName, LOCALOUTPUTLOCATION);
            /* Close the connection */

            conn.close();

        } catch (IOException e)
        {
            e.printStackTrace(System.err);
            System.exit(2);
        }
        return LOCALOUTPUTLOCATION + pdfFileName;
    }
}
