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
     * @param name - name
     * @param start date - start date
     * @param end date - end date
     * @param manager - manager
     */
    public void createProject(String projectName,
             Date startDate, Date endDate, String manager);
   
    /**
     * Here we delete details of project
     *
     * @param id
     */
    public void deleteProject(int projectId);

    /**
     * Here we restore project that was deleted
     *
     * @param id
     */
    public void restoreProject(int projectId);
        
    /**
     * Here we display individual details of project
     *
     * @param id - id
     */
    public List<String> getIndividualProject(int projectId);

    /**
     * Here we display all the project details
     */
    public List<String> getProject();

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
     * Here we update project name for project
     * @param id - id
     * @param name - name
     */
     public void updateName(int id, String name);

    /**
     * Here we update project start date for project
     * 
     * @param id - id
     * @param stateDate - startDate
     */
     public void updateStartDate(int id, Date startDate);

    /**
     * Here we update project end date for project
     *
     * @param id - id
     * @param endDate - endDate
     */
    public void updateEndDate(int id, Date endDate);

    /**
     * Here we update project manager for project
     *
     * @param id - id
     * @param manager - manager
     */
    public void updateManager(int id, String manager);

    /**
     * Here we check project id
     *
     * @param projectId - projectId
     */
    public boolean checkId(int projectId);

    /**
     * Here we get project details for assigning and unassinging
     */
    public List<Project> getProjectValues(List<Integer> projectId);

    /**
     * Here we check project id
     *
     * @param projectId - projectId
     */
     public List<List<Integer>> checkIdForAssignAndUnassign(int projectId,
            List<Integer> employeeIdList);

    /**
     * Here we assign employee details to project
     *
     * @param id - id
     * @param employeeId - employeeId
     */
    public void assignEmployee(int id, List<Integer> employeeList);

    /**
     * Here we unassign employee values to project
     *
     * @param id - id
     * @param employeeId - employeeId
     */
    public void unassignEmployee(int id, List<Integer> employeeIdList);

    /**
     * Here we display assigned values
     *
     * @param id
     */
    public List<String> getAssignedEmployee(int id);
}