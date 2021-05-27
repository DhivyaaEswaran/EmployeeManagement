package com.ideas2it.employeemanagement.dao.impl;

import java.sql.Date;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
    public void saveOrUpdateEmployee(Employee employee) {
        Session session = null;

        try {
            session = dataBaseConnectivity.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(employee);   
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            closeSession(session);
        }  
    }    

    /**
     * {@inheritdoc}
     */
    @Override
    public Employee getIndividualEmployee(int id) {
        Session session = null;
        Employee employee = null;
  
        try {
            session = dataBaseConnectivity.getSessionFactory().openSession();
            employee = (Employee) session.get(Employee.class, id);           
            employee.getAddresses().size();
            employee.getProjects().size();
        } catch (HibernateException exception) {
            exception.printStackTrace();     
        } finally {
            closeSession(session);
        }   
        return employee; 
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Employee> getEmployees() {
        Session session = null;
        Project project = null;
        List<Employee> value = null;
 
        try {
            session = dataBaseConnectivity.getSessionFactory().openSession();    
            Query query = session.createQuery("from Employee employee WHERE is_deleted = 0");
            value = (List<Employee>)query.list();    
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            closeSession(session);
        }   
        return value;         
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public int checkId(int id) {
        Session session = null;
        int count = 0;
        
        try {
            session = dataBaseConnectivity.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT COUNT(id)"
                    + " FROM Employee employee WHERE id = :id and is_deleted = 0");
            query.setParameter("id", id);
            count = ((Long)query.uniqueResult()).intValue();          
        } catch (HibernateException exception) { 
            exception.printStackTrace();               
        } finally {
            closeSession(session);
        } 
        return count;  
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public int checkDeletedId(int id) {
        Session session = null;
        int count = 0;
        
        try {
            session = dataBaseConnectivity.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT COUNT(id)"
                    + " FROM Employee employee WHERE id = :id and is_deleted = 1");
            query.setParameter("id", id);
            count = ((Long)query.uniqueResult()).intValue();          
        } catch (HibernateException exception) { 
            exception.printStackTrace();               
        } finally {
            closeSession(session);
        } 
        return count;  
    }

    /**
     * Here we close session
     */
    public void closeSession(Session session) {

        if (session != null) {
            session.close();
        }
    }
}           