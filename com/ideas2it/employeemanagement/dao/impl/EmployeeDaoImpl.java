package com.ideas2it.employeemanagement.dao.impl;

import java.sql.Date;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ideas2it.employeemanagement.dao.EmployeeDao;
import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.projectmanagement.model.Project;
import com.ideas2it.sessionfactory.DataBaseConnectivity;

/**
 * In this class we perform insert, delete, update, delete for employee
 */
public class EmployeeDaoImpl implements EmployeeDao {
    private DataBaseConnectivity dataBaseConnectivity  
            = DataBaseConnectivity.getInstance();

    /**
     * {@inheritdoc}
     */
    @Override
    public void insertEmployee(Employee employee) {        
        Session session = null;
        Transaction transaction = null;
        
        try {
            SessionFactory sessionFactory = dataBaseConnectivity.getSessionFactory();
            session = sessionFactory.openSession();
                    session.beginTransaction();
            session.save(employee);
                    session.getTransaction().commit();
        } catch (HibernateException exception) {
 
           if (transaction != null) {
                session.getTransaction().rollback();
            }
            exception.printStackTrace();
        } finally {
            session.close();
        }
    }
}           