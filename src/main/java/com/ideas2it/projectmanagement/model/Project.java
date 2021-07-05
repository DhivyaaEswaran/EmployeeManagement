package com.ideas2it.projectmanagement.model;

import java.sql.Date;
import java.util.List;

import com.ideas2it.employeemanagement.model.Employee;

/**
 * POJO class containing project details
 * it includes details such as Id, name, startDate, endDate, manager
 *
 * @author Dhivyaa eswaran
 * @version 1.0 29-05-2021
 */
public class Project {
    private int projectId;
    private String projectName;
    private Date startDate;
    private Date endDate;
    private String manager;
    private boolean isDeleted;
    private List<Employee> employees;

    public Project() {
    }   
    
   /**
    * Here we get project values
    *
    * @param id - id
    * @param name - name
    * @param startDate - startDate
    * @param endDate - endDate
    * @param manager - manager
    */
    public Project(String projectName, Date startDate,
            Date endDate, String manager) {        
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.manager = manager;
    }

   /**
    * Here we get project values and employee list
    *
    * @param id - id
    * @param name - name
    * @param startDate - startDate
    * @param endDate - endDate
    * @param manager - manager
    * @param employee - employee list
    */   
    public Project(int projectId, String projectName, Date startDate,
            Date endDate, String manager, List <Employee> employees) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.manager = manager;
        this.employees = employees;
    } 

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getManager() {
        return manager;
    }

    public List<Employee> getEmployees(){
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    public String toString() {
        return  projectId + "," + projectName + "," + manager;                
    }
    
    /**
     * Here we override equals
     */
    @Override
    public boolean equals(Object object) {
    	return (this.projectId == ((Project) object).projectId);
    }
}