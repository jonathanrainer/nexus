/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.util.ArrayList;
import java.util.Iterator;
import org.joda.time.DateTime;

/**
 *
 * @author jonathanrainer
 */
public class Ticket
{
    private int jobRefId;
    private DateTime dateTime;
    private User ticketRaisedBy;
    private String problemLocation;
    private String problemDescription;
    private ArrayList<String> CISKeywords;
    private String reportedBy;
    private String whoIsA;
    private String contactVia;
    private String contactNumber;
    private String locationVenueVillage;
    private String delegateImpact;
    private boolean showOnCIS;
    private ArrayList<String> updateDescriptions;
    private ArrayList<DateTime> estimatedCompletions;
    private ArrayList<DateTime> updatedAt;
    private DateTime jobClosed;
    private DateTime nextUpdateDue;

    public Ticket(int jobRefId, DateTime dateTime, User ticketRaisedBy, 
            String problemLocation, String problemDescription, 
            ArrayList<String> CISKeywords, String reportedBy, 
            String whoIsA, String contactVia, String contactNumber, 
            String locationVenueVillage, String delegateImpact, boolean showOnCIS, 
            ArrayList<String> updateDescriptions, 
            ArrayList<DateTime> estimatedCompletions, 
            ArrayList<DateTime> updatedAt, DateTime jobClosed, 
            DateTime nextUpdateDue)
    {
        this.jobRefId = jobRefId;
        this.dateTime = dateTime;
        this.ticketRaisedBy = ticketRaisedBy;
        this.problemLocation = problemLocation;
        this.problemDescription = problemDescription;
        this.CISKeywords = CISKeywords;
        this.reportedBy = reportedBy;
        this.whoIsA = whoIsA;
        this.contactVia = contactVia;
        this.contactNumber = contactNumber;
        this.locationVenueVillage = locationVenueVillage;
        this.delegateImpact = delegateImpact;
        this.showOnCIS = showOnCIS;
        this.updateDescriptions = updateDescriptions;
        this.estimatedCompletions = estimatedCompletions;
        this.updatedAt = updatedAt;
        this.jobClosed = jobClosed;
        this.nextUpdateDue = nextUpdateDue;
    }
    
    public String dataValidationEntry()
    {
        problemDescription = textValidation(problemDescription);
        reportedBy = textValidation(reportedBy);
        if(ticketRaisedBy.getName().equals("Choose One"))
        {
            return "No team name selected";
        }
        if(problemLocation.contains("Select a Location") || problemLocation.
                contains("Select a Facility"))
        {
            return "Location not specified correctly";
        }
        if(problemDescription.length() > 256)
        {
            return "Over 256 characters in description field";
        }
        if(problemDescription.equals(""))
        {
            return "Description must be specified";
        }
        if(CISKeywords.size() > 5)
        {
            return "Too many keywords specified";
        }
        if(whoIsA.equals("Choose One"))
        {
            return "Role of person reporting is not specified";
        }
        if(contactVia.equals("Choose One"))
        {
            return "Contact method must be decided, choose \"None required\""
                    + "if the person doesn't need contacting.";
        }
        if(contactNumber.equals(""))
        {
            return "A number must be entered as a contact number";
        }
        if(locationVenueVillage.equals("Choose One"))
        {
            return "A Location, Venue or Village must be specified.";
        }
        
        return "Passed";
    }
    
    public String textValidation(String unvalidatedText)
    {
        System.out.println(unvalidatedText);
        unvalidatedText = unvalidatedText.replace("\"", "\\\"");
        unvalidatedText = unvalidatedText.replace("\\" , "\\\\");
        unvalidatedText = unvalidatedText.replace("%", "\\%");
        return unvalidatedText;
    }

    public int getJobRefId()
    {
        return jobRefId;
    }

    public DateTime getDateTime()
    {
        return dateTime;
    }

    public User getTicketRaisedBy()
    {
        return ticketRaisedBy;
    }

    public String getProblemLocation()
    {
        return problemLocation;
    }

    public String getProblemDescription()
    {
        return problemDescription;
    }

    public ArrayList<String> getCISKeywords()
    {
        return CISKeywords;
    }
    
    public String getCISKeywordsAsString()
    {
        Iterator<String> it1 = CISKeywords.iterator();
        String keyWords = "";
        while(it1.hasNext())
        {
            keyWords = keyWords + it1.next() + ";";
        }
        return keyWords;
    }

    public String getReportedBy()
    {
        return reportedBy;
    }

    public String getWhoIsA()
    {
        return whoIsA;
    }

    public String getContactVia()
    {
        return contactVia;
    }

    public String getLocationVenueVillage()
    {
        return locationVenueVillage;
    }

    public String getDelegateImpact()
    {
        return delegateImpact;
    }

    public boolean isShowOnCIS()
    {
        return showOnCIS;
    }

    public ArrayList<String> getUpdateDescriptions()
    {
        return updateDescriptions;
    }

    public ArrayList<DateTime> getEstimatedCompletions()
    {
        return estimatedCompletions;
    }

    public ArrayList<DateTime> getUpdatedAt()
    {
        return updatedAt;
    }

    public DateTime getJobClosed()
    {
        return jobClosed;
    }

    public DateTime getNextUpdateDue()
    {
        return nextUpdateDue;
    }
    
    public String getContactNumber()
    {
        return contactNumber;
    }
    
}
