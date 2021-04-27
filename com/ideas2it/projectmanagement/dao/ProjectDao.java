package com.ideas2it.projectmanagement.dao;

import java.sql.ResultSet;
import java.util.List;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.projectmanagement.model.Project;

/**
 * In this class we perform crud operations(create,read,update,delete)
 */
public interface ProjectDao {

    /**
     * Here we insert the details for project
     *
     * @param project - project
     */
    public void createProject(Project project);

    /**
     * Here we assign employee values for project
     *
     * @param project - project   
     */
    public void assignEmployee(Project project);

    /**
     * Here we check id for assigning employee 
     * 
     * @param id - projectId
     */
    public Project getEmployeeProject(int id);
  
    /**
     * Here we unaassign employee details to project
     *
     * @param project - project
     */
    public void unassignEmployee(Project project);

    /**
     * Here we delete details for project
     *
     * @param id - id
     */
    public void deleteProject(int id);
    
    /**
     * Here we update all details of project
     *
     * @param project - project
     */
    public void updateProject(Project project);

    /**
     * Here we update individual details for project
     *
     * @param id - id
     * @return project
     */
     public Project getProject(int id);
 
    /**
     * Here we display individual details of project
     *
     * @param id - id
     * @return project
     */
    public Project getIndividualProject(int projectId);   

    /**
     * Here we display all the project details
     *
     * @return project 
     */
    public List<Project> getProject();
}
