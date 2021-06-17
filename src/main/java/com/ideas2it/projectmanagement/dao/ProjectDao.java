package com.ideas2it.projectmanagement.dao;

import java.sql.ResultSet;
import java.util.List;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.exception.UserDefinedException;
import com.ideas2it.projectmanagement.model.Project;

/**
 * In this class we perform crud operations(create,read,update,delete)
 */
public interface ProjectDao {

    /**
     * Here we delete details of project
     *
     * @param projectId
     * @throws EmployeeNotFoundException  
     * @throws UserDefinedException 
     */
    public void saveOrUpdateProject(Project project) throws UserDefinedException;

    /**
     * Here we display individual details of project
     *
     * @param id - id
     * @return project 
     * @throws UserDefinedException 
     */
    public Project getIndividualProject(int projectId) throws UserDefinedException;

    /**
     * Here we dispaly all projects
     *
     * @param id - id
     * @return list 
     * @throws UserDefinedException 
     */
    public List<Project> getProjects() throws UserDefinedException; 

    /**
     * Here we check project id
     *
     * @param projectId - projectId
     * @throws UserNotFoundException 
     */
    public int checkId(int projectId) throws  UserDefinedException;

    /**
     * Here we check id that was deleted
     *
     * @param id - id
     * @throws UserDefinedException 
     */
    public int checkDeletedId(int projectId) throws UserDefinedException;
}