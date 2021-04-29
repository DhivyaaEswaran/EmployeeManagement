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
     * Here we display individual details of project
     *
     * @param id - id
     * @return project
     */
    public Project getIndividualProject(int projectId);

    /**
     * Here we update the details of project
     *
     * @param project - project
     */   
    public void updateProject(Project project);

    /**
     * Here we get projectId for update
     *
     * @param projectId - projectId
     */
    public Project getProjectForUpdate(int projectId);

    /**
     * Here we display all the project details
     *
     * @return project 
     */
    //public Project getProject();

    /**
     * Here we check project id
     *
     * @param projectId - projectId
     */
    public int checkId(int projectId);
}
