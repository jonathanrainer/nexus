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

/**
 *
 * @author jonathanrainer
 */

public class MYSQLEngine {
    
     // JDBC driver name and database URL
   private String JDBC_DRIVER;
   private String DB_URL;

   //  Database credentials
   private String USER;
   private String PASS;
   
   public MYSQLEngine(String mysqlhost, String mysqldatabase, String mysqluser
           ,  String mysqlpassword)
   {
       JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
       DB_URL = "jdbc:mysql://" + mysqlhost + "/" + mysqldatabase;
       USER = mysqluser;
       PASS = mysqlpassword;
   }
   
    /**
     *
     */
    public ArrayList<String> enumerateTeamNames()
   {
       // Set up the initial connection and statement objects
       Connection conn = null;
       Statement stmt = null;
       ArrayList<String> results = new ArrayList<String>();
       // Begin try block so SQL Exceptions can be handled later
       try
       {
           //Register JDBC driver
           Class.forName("com.mysql.jdbc.Driver");
           //Open a connection
           conn = DriverManager.getConnection(DB_URL,USER,PASS);
           
           //Create and Execute the SQL Query
           stmt = conn.createStatement();
           String sql = "SELECT SUBSTRING( COLUMN_TYPE, 5 ) FROM " +
                   "information_schema.COLUMNS WHERE TABLE_SCHEMA = 'Nexus' "
                   + "AND TABLE_NAME = 'Users' AND COLUMN_NAME = 'team'";
           ResultSet rs = stmt.executeQuery(sql);
           
           // Extract the resulting data from the ResultSet
           while(rs.next())
           {
               String stringToSplit = rs.getString(1);
               stringToSplit = stringToSplit.replaceAll("[(\"\')]","");
               String[] argumentArray = stringToSplit.split(",");
               Arrays.sort(argumentArray);
               int i = 0;
               while (i < argumentArray.length)
               {
                   if(argumentArray[i].equals("Super Users"))
                   {
                       i++;
                   }
                   else
                   {
                       results.add(argumentArray[i]);
                       i++;
                   }
               }
           }
           rs.close();
           stmt.close();
           conn.close();
       }
       
       catch(SQLException se)
       {
      //Handle errors for JDBC
       }
       
       catch(Exception e)
       {
      //Handle errors for Class.forName
       }
       finally
       {
      //finally block used to close resources
           try
           {
               if (stmt!=null) 
               {
                   stmt.close();
               }
           }
           catch(SQLException se2)
           {
               
           }// nothing we can do
           
           try
           {
               if (conn!=null)
               {
                   conn.close();
               }
           }
           catch(SQLException se)
           {
           
           }
       }
       return results;
   }
   
    
}
