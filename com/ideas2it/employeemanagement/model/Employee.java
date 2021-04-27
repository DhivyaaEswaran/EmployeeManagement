 package com.ideas2it.employeemanagement.model;

import java.sql.Date;
import java.util.List;

import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.projectmanagement.model.Project;

/** 
 * POJO class containing employee details
 * it includes details such as ID, name, mobile number, date of birth, emailId 
 * @author Dhivyaa Eswaran
 * @version 1.0 15-03-2021
 */        
public class Employee {
    private String name;
    private int id;
    private double salary;
    private Date dateOfBirth;
    private String mobileNumber;
    private String emailId;
    private List<Address> address;
    private List<Project> project;

    /**
     * Here we get employee details and address details as list
     *
     * @param id - employee id
     * @param name - employee name
     * @param salary - employee salary
     * @param dateOfBirth - employee dateOfBirth
     * @param emailId - employee emailId
     * @param address - address list
     */
    public Employee(int id, String name, double salary, Date dateOfBirth,
            String mobileNumber, String emailId, List<Address> address) {
        this.name = name;
        this.id = id;
        this.salary = salary;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
    }

    /**
     * Here we get employee details,address details as list and project as list
     *
     * @param id - employee id
     * @param name - employee name
     * @param salary - employee salary
     * @param dateOfBirth - employee dateOfBirth
     * @param emailId - employee emailId
     * @param address - address list
     * @param project - project list
     */
    public Employee(int id, String name, double salary, Date dateOfBirth,
            String mobileNumber, String emailId, List<Address> address,
            List<Project> project) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
        this.address = address;
        this.project = project;
    }

    /**
     * Here we get employee details
     *
     * @param id - employee id
     * @param name - employee name
     * @param salary - employee salary
     * @param dateOfBirth - employee dateOfBirth
     * @param emailId - employee emailId
     */
    public Employee(int id, String name, double salary, Date dateOfBirth,
            String emailId, String mobileNumber) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.dateOfBirth = dateOfBirth;
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
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
        return address;
    } 
  
    public List<Project> getProjects() {
        return project;
    }

    public void setProjects(List<Project> project) {
        this.project = project;
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

    public void setAddresses(List<Address> address) {
        this.address = address;
    }
      
    public String toString() {
        return "\nId:" + this.id + "\nName:" + this.name + "\nSalary:" 
                + this.salary + "\nDateOfBirth:" + this.dateOfBirth 
                + "\nMobileNumber:" + this.mobileNumber + "\nEmailId:" + this.emailId;              
    }
}