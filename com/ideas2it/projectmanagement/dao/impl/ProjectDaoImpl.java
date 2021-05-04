package com.ideas2it.projectmanagement.dao.impl;

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
    public void createProject(Project project) {
        Session session = null;
        Transaction transaction = null;

        try {
            SessionFactory sessionFactory = dataBaseConnectivity.getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(project);
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
    public void saveOrUpdateProject(Project project) {
        Session session = null;
        Transaction transaction = null;

        try {
            SessionFactory sessionFactory = dataBaseConnectivity.getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(project);   
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

    /**
     * {@inheritdoc}
     */
     @Override
     public Project getIndividualProject(int projectId) {
         Session session = null;
         Project project = null;
 
         try {
             SessionFactory sessionFactory = dataBaseConnectivity.getSessionFactory();
             session = sessionFactory.openSession(); 
             project = (Project)session.get(Project.class, projectId);
             project.getEmployees().size();                     
         } catch (HibernateException exception) {
             exception.printStackTrace();    
         } finally {
           
             if (session != null) {
                 session.close();
             }
         }   
         return project; 
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Project> getProject() {
        Session session = null;
        Project project = null;
        List<Project> value = null;
 
        try {
            SessionFactory sessionFactory = dataBaseConnectivity.getSessionFactory();
            session = sessionFactory.openSession();    
            Query query = session.createQuery("from Project project WHERE is_deleted = 0");
            value = (List<Project>) query.list();    
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
    public void updateProject(Project project) {
        Session session = null;

        try {
            SessionFactory sessionFactory = dataBaseConnectivity.getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(project);
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
    public Project getProjectForUpdate(int projectId) {
        Transaction transaction = null;
        Session session = null;
        Project projects = null;
 
        try {
            SessionFactory sessionFactory = dataBaseConnectivity.getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction(); 
            projects = (Project) session.get(Project.class, projectId);
        } catch (HibernateException exception) {

            if (transaction != null) {
                session.getTransaction().rollback();
            }
            exception.printStackTrace();
        } finally {
            session.close();
        }   
        return projects; 
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public int checkId(int projectId) {
        Session session = null;
        int count = 0;
        
        try {
            SessionFactory sessionFactory = dataBaseConnectivity.getSessionFactory();
            session = sessionFactory.openSession();
            Query query = session.createQuery("SELECT COUNT(id)"
                    + " FROM Project project WHERE project_id = :id");
            query.setParameter("id", projectId);
            count = ((Long)query.uniqueResult()).intValue();          
        } catch (HibernateException exception) { 
            exception.printStackTrace();               
        } finally {
            session.close();
        } 
        return count;  
    }
} 