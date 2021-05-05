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
 
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.projectmanagement.dao.ProjectDao;
import com.ideas2it.projectmanagement.model.Project;
import com.ideas2it.sessionfactory.DataBaseConnectivity;

/**
 * In this class we perform crud operation(create,read,
 * update,delete, assign,unassign)
 */
public class ProjectDaoImpl implements ProjectDao {
    private DataBaseConnectivity dataBaseConnectivity
            = DataBaseConnectivity.getInstance();

    /**
     * {@inheritdoc}
     */
    @Override
    public void saveOrUpdateProject(Project project) {
        Session session = null;
        Transaction transaction = null;

        try { 
            session = dataBaseConnectivity.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(project);   
            session.getTransaction().commit();
        } catch (HibernateException e) {

            if (transaction != null) {
                session.getTransaction().rollback();
            }
        e.printStackTrace();
        } finally {
            closeSession(session);
        }    
    }   

    /**
     * {@inheritdoc}
     */
     @Override
     public Project getIndividualProject(int projectId) {
         Session session = null;
         Project project = null;
 
         try {
             session = dataBaseConnectivity.getSessionFactory().openSession();
             project = (Project)session.get(Project.class, projectId);
             project.getEmployees().size();                     
         } catch (HibernateException exception) {
             exception.printStackTrace();    
         } finally {
             closeSession(session);
         }   
         return project; 
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Project> getProjects() {
        Session session = null;
        List<Project> projectValues = null;
 
        try {
            session = dataBaseConnectivity.getSessionFactory().openSession();    
            Query query = session.createQuery("from Project project WHERE is_deleted = 0");
            projectValues = (List<Project>) query.list();    
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            closeSession(session);
        }   
        return projectValues;     
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public int checkId(int projectId) {
        Session session = null;
        int count = 0;
        
        try {
            session = dataBaseConnectivity.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT COUNT(id)"
                    + " FROM Project project WHERE project_id = :id and is_deleted = 0");
            query.setParameter("id", projectId);
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
    public int checkDeletedId(int projectId) {
        Session session = null;
        int count = 0;
        
        try {
            session = dataBaseConnectivity.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT COUNT(id)"
                    + " FROM Project project WHERE project_id = :id and is_deleted = 1");
            query.setParameter("id", projectId);
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