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
     * Here we delete details of project
     *
     * @param projectId
     */
    public void saveOrUpdateProject(Project project);

    /**
     * Here we display individual details of project
     *
     * @param id - id
     * @return project
     */
    public Project getIndividualProject(int projectId);

    /**
     * Here we dispaly all projects
     *
     * @param id - id
     * @return list
     */
    public List<Project> getProjects(); 

    /**
     * Here we check project id
     *
     * @param projectId - projectId
     */
    public int checkId(int projectId);

    /**
     * Here we check id that was deleted
     *
     * @param id - id
     */
    public int checkDeletedId(int projectId);
}