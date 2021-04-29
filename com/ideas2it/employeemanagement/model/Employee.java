package com.ideas2it.employeemanagement.model;

import java.sql.Date;
import java.util.List;

import com.ideas2it.projectmanagement.model.Project;

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
        return "\nId:" + this.id + "\nName:" + this.name + "\nSalary:" 
                + this.salary + "\nDateOfBirth:" + this.dateOfBirth 
                + "\nMobileNumber:" + this.mobileNumber + "\nEmailId:" + this.emailId;              
    }
}