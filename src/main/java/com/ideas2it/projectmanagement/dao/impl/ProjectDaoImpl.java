package com.ideas2it.projectmanagement.dao.impl;

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

import com.ideas2it.employeemanagement.dao.impl.EmployeeDaoImpl;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.exception.UserDefinedException;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.projectmanagement.dao.ProjectDao;
import com.ideas2it.projectmanagement.model.Project;
import com.ideas2it.sessionfactory.DataBaseConnectivity;

/**
 * In this class we perform crud operation(create,read,
 * update,delete, assign,unassign)
 */
public class ProjectDaoImpl implements ProjectDao {
	private DataBaseConnectivity dataBaseConnectivity = DataBaseConnectivity.getInstance();
	private CustomLogger log = new CustomLogger(ProjectDaoImpl.class);

	/**
	 * {@inheritdoc}
	 */
	@Override
	public void saveOrUpdateProject(Project project) throws UserDefinedException {
		Session session = null;
		
		try { 
			session = dataBaseConnectivity.getSessionFactory().openSession();
			session.beginTransaction();
			session.saveOrUpdate(project);   
			session.getTransaction().commit();
			log.info("successfully inserted");
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			log.error("Cannot save or update project", e);
			throw new UserDefinedException("cannot insert project");    		
		} finally {
			closeSession(session);
		}  
	}   

	/**
	 * {@inheritdoc}
	 */
	@Override
	public Project getIndividualProject(int projectId) throws UserDefinedException{
		Session session = null;
		Project project = null;

		try {
			session = dataBaseConnectivity.getSessionFactory().openSession();
			project = (Project)session.get(Project.class, projectId);
			project.getEmployees().size();   
			log.info("successfully displayed");
		} catch (HibernateException exception) {
			log.error("Cannot display project", exception);
			exception.printStackTrace(); 
			throw new UserDefinedException("cannot display project"); 
		} finally {
			closeSession(session);
		}   
		return project; 
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public List<Project> getProjects() throws UserDefinedException{
		Session session = null;
		List<Project> projectValues = null;

		try {
			session = dataBaseConnectivity.getSessionFactory().openSession();    
			Query query = session.createQuery("from Project project WHERE is_deleted = 0");
			projectValues = (List<Project>) query.list();   
			log.info("successfully displayed");
		} catch (HibernateException e) {
			log.error("Cannot display all projects", e);
			e.printStackTrace();
			throw new UserDefinedException("cannot display project"); 
		} finally {
			closeSession(session);
		}   
		return projectValues;     
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public int checkId(int projectId) throws UserDefinedException{
		Session session = null;
		int count = 0;

		try {
			session = dataBaseConnectivity.getSessionFactory().openSession();
			Query query = session.createQuery("SELECT COUNT(id)"
					+ " FROM Project project WHERE project_id = :id and is_deleted = 0");
			query.setParameter("id", projectId);
			count = ((Long)query.uniqueResult()).intValue();
			log.info("successfully checked");
		} catch (HibernateException exception) { 
			log.error("Invalid id", exception);
			exception.printStackTrace();   
			throw new UserDefinedException("cannot check project id"); 
		} finally {
			closeSession(session);
		} 
		return count;  
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public int checkDeletedId(int projectId) throws UserDefinedException {
		Session session = null;
		int count = 0;

		try {
			session = dataBaseConnectivity.getSessionFactory().openSession();
			Query query = session.createQuery("SELECT COUNT(id)"
					+ " FROM Project project WHERE project_id = :id and is_deleted = 1");
			query.setParameter("id", projectId);
			count = ((Long)query.uniqueResult()).intValue();   
			log.info("successfully checked");
		} catch (HibernateException exception) { 
			log.error("Invalid id", exception);
			exception.printStackTrace(); 
			throw new UserDefinedException("cannot check deleted project id"); 
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