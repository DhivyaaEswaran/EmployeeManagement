package com.ideas2it.sessionfactory;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

/**
 * singleton class for connecting to database
 *
 * @version  1.0 29-05-2021.
 * @author   Dhivyaa Eswaran
 */
public class DataBaseConnectivity {
    private static DataBaseConnectivity dataBaseConnectivity = null;
    private static SessionFactory sessionFactory = null;

    private DataBaseConnectivity() {
    }

    /**
     * This method create new connection if 
     * connection is not done already
     *
     * @return dataBaseConnectivity
     */
    public static DataBaseConnectivity getInstance() {

        if (null == dataBaseConnectivity) {
            dataBaseConnectivity = new DataBaseConnectivity();
        }
        return dataBaseConnectivity;
    }
	
    /**
     * SessionFactory created to database.
     *
     * @return factory for storing details of employees to database.
     */
    public static SessionFactory getSessionFactory() {

        try {
           
            if (null == sessionFactory) {
                sessionFactory = new Configuration().configure
                        ("hibernate/properties/hibernate.cfg.xml").buildSessionFactory();
            } 
            if (null == sessionFactory) {
            	System.out.println("session factory is null");
            }
        } catch (HibernateException e) {
            System.out.println(e);			
        }
        return sessionFactory;
    }
}