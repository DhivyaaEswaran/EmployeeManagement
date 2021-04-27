package com.ideas2it.sessionfactory;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * singleton class for connecting to database
 *
 * @version 12-04-2021 
 * @author Dhivyaa Eswaran 
 */
public class DataBaseConnectivity {    
    private static DataBaseConnectivity dataBaseConnectivity = null;
    private static Connection connection = null;

    private DataBaseConnectivity() {   
    }

    /**
     * This method create new connection if 
     * connection is not done already
     *
     * @return dataBaseConnection
     */
    public static DataBaseConnectivity getInstance() {
        
        if(dataBaseConnectivity == null) {
            dataBaseConnectivity = new DataBaseConnectivity();
        }
        return dataBaseConnectivity;
    }

    /**
     * Here we connect with mysql database
     *
     * @return connection
     */ 
    public static Connection getConnection() {        
        Connection connection = null;

        try {      
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeemanagement",
                    "root","dhivyaa");
        } catch(Exception e) {
             System.out.println("failed to connect");
        }
        return connection;   
    } 
}