package com.ideas2it.projectmanagement.service;

import java.sql.Date;
import java.util.List;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.exception.UserDefinedException;
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
     * @throws UserDefinedException  
     */
    public void createProject(String projectName,
             Date startDate, Date endDate, String manager) throws UserDefinedException;
   
    /**
     * Here we delete details of project
     *
     * @param id
     * @throws UserDefinedException  
     */
    public void deleteProject(int projectId) throws UserDefinedException;

    /**
     * Here we restore project that was deleted
     *
     * @param id
     * @throws UserDefinedException 
     */
    public void restoreProject(int projectId) throws UserDefinedException;

    /**
     * Here we check id that was deleted
     *
     * @param id - id 
     * @throws UserDefinedException 
     */
    public boolean checkDeletedId(int projectId) throws UserDefinedException; 
        
    /**
     * Here we display individual details of project
     *
     * @param id - id
     * @throws UserDefinedException 
     */
    public Project getIndividualProject(int projectId) throws UserDefinedException;

    /**
     * Here we display all the project details
     * @throws UserDefinedException 
     */
    public List<Project> getProject() throws UserDefinedException;

    /**
     * Here we update all details for project
     * 
     * @param id - Id
     * @param name - Name
     * @param startDate - StartDate
     * @param endDate - EndDate
     * @param manager - Manager
     * @throws UserDefinedException 
     */
    public void updateProject(int projectId, String projectName, 
             Date startDate, Date endDate, String manger) throws UserDefinedException;

    /**
     * Here we update project name for project
     * @param id - id
     * @param name - name
     * @throws UserDefinedException 
     */
     public void updateName(int id, String name) throws UserDefinedException;

    /**
     * Here we update project start date for project
     * 
     * @param id - id
     * @param stateDate - startDate
     * @throws UserDefinedException 
     */
     public void updateStartDate(int id, Date startDate) throws UserDefinedException;

    /**
     * Here we update project end date for project
     *
     * @param id - id
     * @param endDate - endDate
     * @throws UserDefinedException  
     */
    public void updateEndDate(int id, Date endDate) throws UserDefinedException;

    /**
     * Here we update project manager for project
     *
     * @param id - id
     * @param manager - manager
     * @throws UserDefinedException 
     */
    public void updateManager(int id, String manager) throws UserDefinedException;

    /**
     * Here we check project id
     *
     * @param projectId - projectId
     * @throws UserDefinedException 
     */
    public boolean checkId(int projectId) throws UserDefinedException;

    /**
     * Here we get project details for assigning and unassinging
     * @throws UserDefinedException 
     */
    public List<Project> getProjectValues(List<Integer> projectId) throws UserDefinedException;

    /**
     * Here we check project id
     *
     * @param projectId - projectId
     * @throws UserDefinedException 
     */
     public List<Integer> checkIdForAssignAndUnassign(int projectId,
            List<Integer> employeeIdList, String values) throws UserDefinedException;

    /**
     * Here we assign employee details to project
     *
     * @param id - id
     * @param employeeId - employeeId
     * @throws UserDefinedException  
     */
    public void assignEmployee(int id, List<Integer> employeeList) throws UserDefinedException;

    /**
     * Here we unassign employee values to project
     *
     * @param id - id
     * @param employeeId - employeeId
     * @throws UserDefinedException 
     * @throws EmployeeNotFoundException 
     */
    public void unassignEmployee(int id, List<Integer> employeeIdList) throws UserDefinedException;

    /**
     * Here we display assigned values
     *
     * @param id
     * @throws UserDefinedException 
     */
    public List<String> getAssignedEmployee(int id) throws UserDefinedException;
    
    /**
     * 
     * @return
     * @throws UserDefinedException 
     */
    public List<Employee> displayEmployees() throws UserDefinedException;
}