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
    public void createProject(int id, String name,
             Date startDate, Date endDate, String manager);
    
    /**
     * Here we get project details for assigning and unassinging
     */
    public List<Project> getProjectValues(List<Integer> projectId);

    /**
     * Here we get project values for assign and unassign
     *
     * @param id - id
     */
    //public Project getProjectDetails(int id);

    /**
     * Here we assign employee details to project
     *
     * @param id - id
     * @param employeeId - employeeId
     */
    public void assignEmployee(int id, List<Integer> employeeList);

    /**
     * Here we check project id and employee id
     * present in projectEmployee or not
     *
     * @param id - id
     * @param employeeId - employeeId
     */
    public List<List<Integer>> checkIdForAssignAndUnassign
            (int id, List<Integer> employeeIdList);

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

    /**
     * Here we delete details of project
     *
     * @param id
     */
    public void deleteProject(int id);
    
    /**
     * Here we update all details for project
     * 
     * @param id - Id
     * @param name - Name
     * @param startDate - StartDate
     * @param endDate - EndDate
     * @param manager - Manager
     */
    public void updateProject(int id, String name, 
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
     * Here we display individual details of project
     *
     * @param id - id
     */
    public String getIndividualProject(int id);

    /**
     * Here we display all the project details
     */
    public List<String> getProject();
}