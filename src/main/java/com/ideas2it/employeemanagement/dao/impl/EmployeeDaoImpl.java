package com.ideas2it.employeemanagement.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;

import com.ideas2it.employeemanagement.dao.EmployeeDao;
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
	 * {@inheritDoc} 
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
			log.error("The error maybe due to transaction "
					+ "exception or constraint violation exception" + employee.getId(), e);
			throw new UserDefinedException("save or update employee failure");
		} finally {
			closeSession(session);
		}  
	}    

	/**
	 * {@inheritDoc
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
			log.error("There is an issue while fetching an employee", exception);
			throw new UserDefinedException("employee fetch failure");
		} finally {
			closeSession(session);
		}   
		return employee; 
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Employee> getEmployees() throws UserDefinedException{
		Session session = null;
		List<Employee> employees = null;

		try {
			
			if (DataBaseConnectivity.getSessionFactory() == null) {
				System.out.println("get session factory null");
			}
			session = DataBaseConnectivity.getSessionFactory().openSession();		
			employees = session.createQuery("from Employee employee WHERE is_deleted = 0").getResultList();
		} catch (HibernateException e) {
			log.error("There is an issue while fetching all employee."
					+ "Please check the hql query", e);
			throw new UserDefinedException("employees fetch failure");
		} finally {
			closeSession(session);
		}   
		return employees;         
	}

	/**
	 * {@inheritDoc}
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
		} catch (HibernateException exception) { 
			log.error("There is an issue in hql query", exception); 
			throw new UserDefinedException("employee existance status cannot be checked");
		} finally {
			closeSession(session);
		} 
		return count;  
	}

	/**
	 * {@inheritDoc}
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
		} catch (HibernateException exception) { 
			log.error("There is an issue in hql query", exception);
			throw new UserDefinedException("employee deleted status cannot be checked");
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