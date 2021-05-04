package com.ideas2it.employeemanagement.dao.impl;

import java.sql.Date;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
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

    /**
     * {@inheritdoc}
     */
    @Override
    public Employee getIndividualEmployee(int id) {
        Session session = null;
        Employee employee = null;
  
        try {
            SessionFactory sessionFactory = dataBaseConnectivity.getSessionFactory();
            session = sessionFactory.openSession();
            employee = (Employee) session.get(Employee.class, id);           
            employee.getAddresses().size();
            employee.getProjects().size();
        } catch (HibernateException exception) {
            exception.printStackTrace();     
        } finally {
           
            if (session != null) {
                 session.close();
            }
        }   
        return employee; 
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Employee> getEmployee() {
        Session session = null;
        Project project = null;
        List<Employee> value = null;
 
        try {
            SessionFactory sessionFactory = dataBaseConnectivity.getSessionFactory();
            session = sessionFactory.openSession();    
            Query query = session.createQuery("from Employee employee WHERE is_deleted = 0");
            value = (List<Employee>) query.list();    
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
           
            if (session != null) {
                session.close();
            }
        }   
        return value;         
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void updateAddress(Address address) {
        Session session = null;

        try {
            SessionFactory sessionFactory = dataBaseConnectivity.getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(address);
            session.getTransaction().commit();
        } catch (Exception e) {

            if (session != null) {
                session.getTransaction().rollback();
            }
        e.printStackTrace();
        } finally {
            session.close();
        }     
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Employee getEmployeeForUpdate(int id) { 
        Transaction transaction = null;
        Session session = null;
        Employee employees = null;
 
        try {
            SessionFactory sessionFactory = dataBaseConnectivity.getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction(); 
            employees = (Employee) session.get(Employee.class, id);
        } catch (HibernateException exception) {

            if (transaction != null) {
                session.getTransaction().rollback();
            }
            exception.printStackTrace();
        } finally {
            session.close();
        }   
        return employees; 
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Address getAddressForUpdate(int id) { 
        Transaction transaction = null;
        Session session = null;
        Address address = null;
 
        try {
            SessionFactory sessionFactory = dataBaseConnectivity.getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction(); 
            address = (Address) session.get(Address.class, id);
        } catch (HibernateException exception) {

            if (transaction != null) {
                session.getTransaction().rollback();
            }
            exception.printStackTrace();
        } finally {
            session.close();
        }   
        return address; 
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public int checkId(int id) {
        Session session = null;
        int count = 0;
        
        try {
            SessionFactory sessionFactory = dataBaseConnectivity.getSessionFactory();
            session = sessionFactory.openSession();
            Query query = session.createQuery("SELECT COUNT(id)"
                    + " FROM Employee employee WHERE id = :id");
            query.setParameter("id", id);
            count = ((Long)query.uniqueResult()).intValue();          
        } catch (HibernateException exception) { 
            exception.printStackTrace();               
        } finally {
            session.close();
        } 
        return count;  
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void saveOrUpdateEmployee(Employee employee) {
        Session session = null;
        Transaction transaction = null;

        try {
            SessionFactory sessionFactory = dataBaseConnectivity.getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(employee);   
            session.getTransaction().commit();
        } catch (HibernateException e) {

            if (transaction != null) {
                session.getTransaction().rollback();
            }
        e.printStackTrace();
        } finally {
            session.close();
        }  
    }    
}           