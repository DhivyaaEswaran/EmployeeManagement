package com.ideas2it.projectmanagement.service;

import java.sql.Date;
import java.util.List;

import com.ideas2it.projectmanagement.model.Project;

/**
 * In this class we perform crud operations(create,read,update,delete)
 */
public interface ProjectService {

    /**
     * Here we create update display delete values for projects 
     *
     * @param id - id
     * @param name - name
     * @param start date - start date
     * @param end date - end date
     * @param manager - manager
     */
    public void createProject(String projectName,
             Date startDate, Date endDate, String manager);
    
    /**
     * Here we display individual details of project
     *
     * @param id - id
     */
    public String getIndividualProject(int projectId);

    /**
     * Here we display all the project details
     */
   // public List<String> getProject();

    /**
     * Here we update all details for project
     * 
     * @param id - Id
     * @param name - Name
     * @param startDate - StartDate
     * @param endDate - EndDate
     * @param manager - Manager
     */
    public void updateProject(int projectId, String projectName, 
             Date startDate, Date endDate, String manger);

    /**
     * Here we check project id
     *
     * @param projectId - projectId
     */
    public boolean checkId(int projectId);
}