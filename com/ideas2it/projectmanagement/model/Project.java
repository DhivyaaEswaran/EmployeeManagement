package com.ideas2it.projectmanagement.model;

import java.sql.Date;
import java.util.List;

import com.ideas2it.employeemanagement.model.Employee;

/**
 * POJO class containing project details
 * it includes details such as Id, name, startDate, endDate, manager
 *
 * @author Dhivyaa eswaran
 * @version 1.0 16-04-2021
 */
public class Project {
    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
    private String manager;
    private List<Employee> employee;
    
   /**
    * Here we get project values
    *
    * @param id - id
    * @param name - name
    * @param startDate - startDate
    * @param endDate - endDate
    * @param manager - manager
    */
    public Project(int id, String name, Date startDate,
            Date endDate, String manager) {
        this.id = id;
        this.name = name;
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
    public Project(int id, String name, Date startDate,
            Date endDate, String manager, List <Employee> employee) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.manager = manager;
        this.employee = employee;
    } 

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
        return employee;
    }

    public void setEmployees(List<Employee> employee) {
        this.employee = employee;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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

    public String toString() {
        return "\nId:" + this.id + "\nName:" + this.name
                + "\nStartDate:" + this.startDate + "\nEndDate:"
                + this.endDate + "\nManager:" + this.manager;
    }
}