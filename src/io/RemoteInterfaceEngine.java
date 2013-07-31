/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import com.trilead.ssh2.Connection;
import com.trilead.ssh2.SCPClient;
import com.trilead.ssh2.SFTPv3Client;
import com.trilead.ssh2.SFTPv3DirectoryEntry;
import com.trilead.ssh2.SFTPv3FileHandle;
import com.trilead.ssh2.Session;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author jonathanrainer
 */
public class RemoteInterfaceEngine
{
    private static final String LOCALCSVDIR = "C:\\Nexus\\localCSV\\";
    private static final String REMOTECSVLOCATION = "/home/nexususer/Nexus/csvTransferDir/";
    private static final String REMOTETEXLOCATION = "/home/nexususer/Nexus/latexTemplates/jobTickets";
    private static final String REMOTEOUTPUTLOCATION = "/home/nexususer/Nexus/pdfTickets/";
    private static final String LOCALOUTPUTLOCATION = "C:\\Nexus\\pdfTickets\\";
    private static final String TEXLOGGINGLOCATION = "/home/nexususer/Nexus/logging/";
    private String hostname;
    private String username;
    private String password;

    public RemoteInterfaceEngine()
    {
        hostname = "192.168.8.5";
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
            SFTPv3Client sftpClient = new SFTPv3Client(conn);
            Session sesh = conn.openSession();
            sesh.execCommand(compileLatexFilesCommand(fileName));
             boolean fileExists = false;
            while(!fileExists)
            {
                Iterator<SFTPv3DirectoryEntry> it1 = sftpClient.ls(REMOTEOUTPUTLOCATION).iterator();
                while(it1.hasNext())
                {
                    if(it1.next().filename.contains(".pdf"))
                    {
                        fileExists = true;
                    }
                }
            }
            sesh.close();
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
        String command = "pdflatex -output-directory " + REMOTEOUTPUTLOCATION
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

            SFTPv3Client sftpClient = new SFTPv3Client(conn);
            
            pdfFileName = FilenameUtils.removeExtension(fileName) + ".pdf";
            
            File file = new File(LOCALOUTPUTLOCATION + pdfFileName);
 
            // if file doesnt exists, then create it
            if (!file.exists())
            {
                file.createNewFile();
            }
            
            
            
            FileOutputStream fos = new FileOutputStream(file, true);
            SFTPv3FileHandle handle = sftpClient.openFileRO(REMOTEOUTPUTLOCATION + pdfFileName);
            
            
            int i = 0;
            byte[] byteArray = new byte[4096];
            while(sftpClient.read(handle, (i * 4096), byteArray, 0, 4096) != -1)
            {
                fos.write(byteArray);
                i++;
            }
            sftpClient.closeFile(handle);
            sftpClient.close();
            /* Close the connection */
            conn.close();
            fos.flush();
            fos.close();

        } catch (IOException e)
        {
            e.printStackTrace(System.err);
            System.exit(2);
        }
        return LOCALOUTPUTLOCATION + pdfFileName;
    }
}
