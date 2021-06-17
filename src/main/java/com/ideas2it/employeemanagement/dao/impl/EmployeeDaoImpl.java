package com.ideas2it.employeemanagement.dao.impl;

import java.sql.Date;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ideas2it.employeemanagement.dao.EmployeeDao;
import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.exception.UserDefinedException;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.projectmanagement.model.Project;
import com.ideas2it.sessionfactory.DataBaseConnectivity;

/**
 * In this class we perform insert, delete, update, delete for employee
 */
public class EmployeeDaoImpl implements EmployeeDao {
	private DataBaseConnectivity dataBaseConnectivity = DataBaseConnectivity.getInstance();
	private CustomLogger log = new CustomLogger(EmployeeDaoImpl.class);

	/**
	 * {@inheritdoc} 
	 */
	@Override
	public void saveOrUpdateEmployee(Employee employee) throws UserDefinedException {
		Session session = null;
		
		try {    	
			session = dataBaseConnectivity.getSessionFactory().openSession();
			session.beginTransaction();
			session.saveOrUpdate(employee);   
			session.getTransaction().commit();   
			log.info("successfully added or updated employee " + employee.getId());			
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			log.error("cannot insert employee" + employee.getId(), e);
			throw new UserDefinedException("Cannot insert employee");
		} finally {
			closeSession(session);
		}  
	}    

	/**
	 * {@inheritdoc}
	 */
	@Override
	public Employee getIndividualEmployee(int id) throws UserDefinedException{
		Session session = null;
		Employee employee = null;

		try {
			session = dataBaseConnectivity.getSessionFactory().openSession();
			employee = (Employee) session.get(Employee.class, id); 
			employee.getAddresses().size();
			employee.getProjects().size();
			log.info("successfully displayed");
		} catch (HibernateException exception) {
			log.error("cannot display employee", exception);
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
	public List<Employee> getEmployees() throws UserDefinedException{
		Session session = null;
		Project project = null;
		List<Employee> employees = null;

		try {
			if (DataBaseConnectivity.getSessionFactory() == null) {
				System.out.println("get session factory null");
			}
			session = DataBaseConnectivity.getSessionFactory().openSession();
			if(session == null) {
				System.out.println("---dhivyaa---");
			}			
			employees = session.createQuery("from Employee employee WHERE is_deleted = 0").getResultList();
			log.info("successfully displayed");
		} catch (HibernateException e) {
			log.error("Cannot dispaly all employees", e);
			e.printStackTrace();
		} finally {
			closeSession(session);
		}   
		return employees;         
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public int checkId(int id) throws UserDefinedException {
		Session session = null;
		int count = 0;

		try {
			session = dataBaseConnectivity.getSessionFactory().openSession();
			Query query = session.createQuery("SELECT COUNT(id)"
					+ " FROM Employee employee WHERE id = :id and is_deleted = 0");
			query.setParameter("id", id);
			count = ((Long)query.uniqueResult()).intValue();
			log.info("successfully checked");
		} catch (HibernateException exception) { 
			log.error("Invalid id", exception);
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
	public int checkDeletedId(int id) throws UserDefinedException{
		Session session = null;
		int count = 0;

		try {
			session = dataBaseConnectivity.getSessionFactory().openSession();
			Query query = session.createQuery("SELECT COUNT(id)"
					+ " FROM Employee employee WHERE id = :id and is_deleted = 1");
			query.setParameter("id", id);
			count = ((Long)query.uniqueResult()).intValue();   
			log.info("successfully checked");
		} catch (HibernateException exception) { 
			log.error("Invalid id", exception);
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