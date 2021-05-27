package com.ideas2it.employeemanagement.model;

import java.sql.Date;
import java.util.List;

import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.projectmanagement.model.Project;

/** 
 * POJO class containing employee details
 * it includes details such as ID, name, mobile number, date of birth, emailId 
 * @author Dhivyaa Eswaran
 * @version 1.0 29-05-2021
 */    
public class Employee {
    private int id;
    private String name;
    private double salary;
    private Date dateOfBirth;
    private String emailId;
    private String mobileNumber;
    private List<Address> addresses;
    private boolean isDeleted;
    private List<Project> projects;
    
    /**
     * Here we get employee details and address details as list
     *
     * @param name - employee name
     * @param salary - employee salary
     * @param dateOfBirth - employee dateOfBirth
     * @param emailId - employee emailId
     * @param address - address list
     */ 
    public Employee(String name, double salary,
            Date dateOfBirth, String mobileNumber, String emailId,
            List<Address> addresses) {
        this.name = name;
        this.salary = salary;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
        this.addresses = addresses;
    }

    public Employee() {
    } 
  
    /**
     * Here we get employee details
     *
     * @param name - employee name
     * @param salary - employee salary
     * @param dateOfBirth - employee dateOfBirth
     * @param emailId - employee emailId
     */
    public Employee(String name, double salary, Date dateOfBirth,
            String emailId, String mobileNumber) {
        this.name = name;
        this.salary = salary;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
    }

    public List<Project> getProjects() {
        return projects;  
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public String getName() { 
        return name;
    }

    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public List<Address> getAddresses() {
        return addresses;
    } 

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    } 

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
    
    public String toString() {
        return  id + "," + name + "," + salary + "," +
                dateOfBirth + "," + emailId + "," + mobileNumber;                
    }
}