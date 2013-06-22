/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.sql.*;
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
   
   
    
}
