/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import org.joda.time.DateTime;
import system.Ticket;

/**
 * A class to encapsulate the connectivity to the MYSQL database.
 *
 * @author jonathanrainer
 */
public class MYSQLEngine
{

    // JDBC driver name and database URL
    private String JDBC_DRIVER;
    private String DB_URL;
    //  Database credentials
    private String USER;
    private String PASS;

    /**
     * Constructor to create the object, requiring the host, database, username
     * and password. Root access is not required but it would be good to use a
     * dedicated user with a fairly high level of privilege but root is not
     * required at the moment.
     *
     * @param mysqlhost URL of the MYSQL server.
     * @param mysqldatabase Name of the MYSQL database.
     * @param mysqluser Username for the MYSQL database.
     * @param mysqlpassword Password for the above use on the MYSQL database.
     */
    public MYSQLEngine(String mysqlhost, String mysqldatabase, String mysqluser, String mysqlpassword)
    {
        JDBC_DRIVER = "com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://" + mysqlhost + "/" + mysqldatabase;
        USER = mysqluser;
        PASS = mysqlpassword;
    }

    /**
     * Method to look at the attached database and extract, from the information
     * schema, the team names stored in the ENUM of the same name in the
     * database. In short, when someone puts a user into the Users table in the
     * database they have to be assigned a team based on an ENUM embedded in the
     * team field, this method extracts all the choices so they can be put into
     * a combo box.
     *
     * @return An ArrayList of the team names as dictated by the database.
     */
    public ArrayList<String> enumerateTeamNames()
    {
        // Set up the initial connection and statement objects
        Connection conn = null;
        Statement stmt = null;
        // Create a new ArrayList to store the results of the MYSQL query.
        ArrayList<String> results = new ArrayList<>();
        // Begin try block so SQL Exceptions can be handled later
        try
        {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Create and Execute the SQL Query
            stmt = conn.createStatement();
            /**
             * Query states: Look in the information_schema table and extract,
             * to a substring, the entire contents of the ENUM called team.
             */
            String sql = "SELECT SUBSTRING( COLUMN_TYPE, 5 ) FROM "
                    + "information_schema.COLUMNS WHERE TABLE_SCHEMA = 'Nexus' "
                    + "AND TABLE_NAME = 'Users' AND COLUMN_NAME = 'team'";
            ResultSet rs = stmt.executeQuery(sql);

            // Extract the resulting data from the ResultSet
            while (rs.next())
            {
                String stringToSplit = rs.getString(1);
                // Remove junk characters
                stringToSplit = stringToSplit.replaceAll("[(\"\')]", "");
                // Split the string using the comma as a delimiter
                String[] argumentArray = stringToSplit.split(",");
                Arrays.sort(argumentArray);
                int i = 0;
                /**
                 * Remove Super Users from the list since login in as a Super
                 * User will be dealt with seperately.
                 */
                while (i < argumentArray.length)
                {
                    if (argumentArray[i].equals("Super Users"))
                    {
                        i++;
                    } else
                    {
                        results.add(argumentArray[i]);
                        i++;
                    }
                }
            }
            // Close all the statements, result set and connection.
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se)
        {
            //Handle errors for JDBC
        } catch (Exception e)
        {
            //Handle errors for Class.forName
        } finally
        {
            //finally block used to close resources should all else fail
            try
            {
                if (stmt != null)
                {
                    stmt.close();
                }
            } catch (SQLException se2)
            {
            }// nothing we can do

            try
            {
                if (conn != null)
                {
                    conn.close();
                }
            } catch (SQLException se)
            {
            }
        }
        return results;
    }

    public ArrayList<String> enumerateTeamMembers(String team)
    {
        // Set up the initial connection and statement objects
        Connection conn = null;
        Statement stmt = null;
        // Create a new ArrayList to store the results of the MYSQL query.
        ArrayList<String> results = new ArrayList<>();
        // Begin try block so SQL Exceptions can be handled later
        try
        {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Create and Execute the SQL Query
            stmt = conn.createStatement();
            /**
             * Query states: Select all the users in a particular team
             */
            String sql;
            sql = "SELECT `name` FROM `Users` WHERE `team` = '" + team + "'";
            try (ResultSet rs = stmt.executeQuery(sql))
            {
                while (rs.next())
                {
                    results.add(rs.getString(1));
                }
            }
            stmt.close();
            conn.close();
        } catch (SQLException se)
        {
            //Handle errors for JDBC
        } catch (Exception e)
        {
            //Handle errors for Class.forName
        } finally
        {
            //finally block used to close resources should all else fail
            try
            {
                if (stmt != null)
                {
                    stmt.close();
                }
            } catch (SQLException se2)
            {
            }// nothing we can do

            try
            {
                if (conn != null)
                {
                    conn.close();
                }
            } catch (SQLException se)
            {
            }
        }
        return results;
    }

    public boolean submitTicket(Ticket ticket, boolean duplicate)
    {
        // Set up the initial connection and statement objects
        Connection conn = null;
        Statement stmt1 = null;
        Statement stmt2 = null;
        boolean success = false;
        // Begin try block so SQL Exceptions can be handled later
        try
        {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Create and Execute the SQL Query
            stmt1 = conn.createStatement();
            stmt2 = conn.createStatement();
            /**
             * Query states: Select all the users in a particular team
             */
            String table = "";
            if(duplicate)
            {
                table = "`DuplicateQueue`";
            }
            else
            {
                table = "`Tickets`";
            }
            String insertionQuery;
            insertionQuery = "INSERT INTO " + table +  "VALUES (NULL, NOW(), '" + 
                    ticket.getTicketRaisedBy().getTeam() + "', "
                    + "'" + ticket.getTicketRaisedBy().getName() + "', " + "'" 
                    + ticket.getProblemLocation() + "', " 
                    + "'" + ticket.getProblemDescription() + "', " + "'" + 
                    ticket.getCISKeywordsAsString() + "', "
                    + "'" + ticket.getReportedBy() + "', " + "'" + 
                    ticket.getWhoIsA()+ "', " + "'" + ticket.getContactVia() + 
                    "', '" + ticket.getContactNumber() + "', " + "'" + 
                    ticket.getLocationVenueVillage() + "', " + "'Low', '0', NULL,"
                    + " 'Issue Reported', NULL, NULL, NULL, NULL, NULL, NULL, "
                    + "NULL, NULL, NULL, NULL, NULL);";
            stmt1.executeUpdate(insertionQuery);
            String query2 = "SELECT * FROM `Tickets` WHERE `CISKeywords` LIKE '" + 
                    ticket.getCISKeywordsAsString() + "' AND `problemLocation`"
                    + "LIKE '" + ticket.getProblemLocation() + "' AND `reportedBy`"
                    + " LIKE '" + ticket.getReportedBy() + "';";
            ResultSet rs = stmt2.executeQuery(query2);
            while(rs.next())
            {
                ticket.setJobRefId(rs.getInt("jobRefId"));
                String timeStamp = rs.getString("dateTime");
                ticket.setDateTime(timeStampToDateTime(timeStamp));
            }
            success = true;
            stmt1.close();
            stmt2.close();
            conn.close();
        } catch (SQLException se)
        {
            System.out.println(se.getSQLState());
            //Handle errors for JDBC
        } catch (Exception e)
        {
            //Handle errors for Class.forName
        } finally
        {
            //finally block used to close resources should all else fail
            try
            {
                if (stmt1 != null || stmt2 != null)
                {
                    stmt1.close();
                    stmt2.close();
                }
            } catch (SQLException se2)
            {
            }// nothing we can do

            try
            {
                if (conn != null)
                {
                    conn.close();
                }
            } catch (SQLException se)
            {
            }
        }
        return success;
    }
    
    public boolean isTicketDuplicate(Ticket ticket)
    {
        // Set up the initial connection and statement objects
        Connection conn = null;
        Statement stmt = null;
        boolean success = false;
        // Begin try block so SQL Exceptions can be handled later
        try
        {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Create and Execute the SQL Query
            stmt = conn.createStatement();
            /**
             * Query states: Select all the users in a particular team
             */
            String sql;
            sql = "SELECT * FROM `Tickets` WHERE `CISKeywords` LIKE '" + 
                    ticket.getCISKeywordsAsString() + "' AND `problemLocation`"
                    + "LIKE '" + ticket.getProblemLocation() + "' AND `reportedBy`"
                    + " LIKE '" + ticket.getReportedBy() + "';";
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.isBeforeFirst())
            {
            }
            else
            {
                success = true;
            }
            stmt.close();
            conn.close();
        } catch (SQLException se)
        {
            System.out.println(se.getSQLState());
            //Handle errors for JDBC
        } catch (Exception e)
        {
            //Handle errors for Class.forName
        } finally
        {
            //finally block used to close resources should all else fail
            try
            {
                if (stmt != null)
                {
                    stmt.close();
                }
            } catch (SQLException se2)
            {
            }// nothing we can do

            try
            {
                if (conn != null)
                {
                    conn.close();
                }
            } catch (SQLException se)
            {
            }
        }
        return success;
    }
    
    private DateTime timeStampToDateTime(String timeStamp)
    {
        System.out.println(timeStamp.substring(0, 4));
        int year = Integer.parseInt(timeStamp.substring(0, 4));
        int month = Integer.parseInt(timeStamp.substring(5, 7));
        int dayOfMonth = Integer.parseInt(timeStamp.substring(8, 10));
        int hourOfDay = Integer.parseInt(timeStamp.substring(11, 13));
        int minuteOfDay = Integer.parseInt(timeStamp.substring(14, 16));
        return new DateTime(year, month, dayOfMonth, hourOfDay, minuteOfDay);
    } 
}
