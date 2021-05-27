package com.ideas2it.employeemanagement.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.projectmanagement.model.Project;

/**
 * In this class we perform crud operations(create,read,update,delete)
 */
public interface EmployeeService {

    /**
     * Here we create details for employee
     *
     * @param name - Employee name
     * @param salary - Employee salary
     * @param dateOfBirth - Employee dateOfBirth
     * @param mobileNumber - Employee mobile number
     * @param emailId - Employee emailId
     */
    public void createEmployee(String name, double salary, 
            Date dateOfBirth, String mobileNumber, String emailId);
    
    /**
     * Here we get employee values for assigning it to project
     *
     * @param employeeId - employee id
     */
    public List<Employee> getEmployeeValues(List<Integer> employeeId);

    /**
     * Here we get the valid mobile number for employees
     * @return true if employee mobile number is valid
     *         else false  
     */
    public boolean checkValidMobileNumber(String mobileNumber); 

    /**
     * Here we get the valid email id for employees
     * @return true if employee email id is valid
     *         else false 
     */
    public boolean checkValidEmailId(String emailId); 

    /**
     * Here we display individual details of employee
     *
     * @param id - id
     */
    public Employee getIndividualEmployee(int id);

    /**
     * Here we display all the details of employee
     */
    public List<Employee> getAllEmployee();

    /**
     * Here we update all the details for employees
     *
     * @param id - Employee id
     * @param name - Employee name
     * @param salary - Employee salary
     * @param dateOfBirth - Employee dateOfBirth
     * @param mobileNumber - Employee mobile number
     * @param emailId - Employee emailId
     */
    public void updateEmployee(int id, String name, double salary, 
            Date dateOfBirth, String emailId, String mobileNumber);

    /**
     * Here we update salary for individual employee 
     *
     * @param id - id
     * @param salary - salary
     */
    public void updateSalary(int id, double salary);

    /**
     * Here we update date of birth for individual employee 
     *
     * @param id - id
     * @param date of birth - date of birth
     */
    public void updateDateOfBirth(int id, Date dateOfBirth);

    /**
     * Here we update mobile number for individual employee 
     *
     * @param id - id
     * @param mobile number - mobile number
     */
    public void updateMobileNumber(int id, String mobileNumber);

    /**
     * Here we update email id for individual employee 
     *
     * @param id - id
     * @param email id - email id
     */
    public void updateEmailId(int id, String emailId);  

    /**
     * Here we update all the address details for employee
     *
     * @param id - Address id
     * @param doorNumber - doorNumber
     * @param streetName - streetName
     * @param district - district
     * @param state - state
     * @param country - country
     * @param pinCode - pinCode
     */
    public void updateAddress(int id, int addressId, String doorNumber, String streetName, 
            String district, String state, String country, int pinCode); 

    /**
     * Here we add multile address for employee 
     *
     * @param id - Address id
     * @param doorNumber - doorNumber
     * @param streetName - streetName
     * @param district - district
     * @param state - state
     * @param country - country
     * @param pinCode - pinCode
     */
    public void addEmployeeAddress(int id, String doorNumber, String streetName,
            String district, String state, String country, int pinCode);
       
    /**
     * Here we check id for employee
     *
     * @param id - id
     */
    public boolean checkId(int id);

    /**
     * Here we check deleted employee id
     *
     * @param id - id
     */
    public boolean checkDeletedId(int id);

    /**
     * Here we delete details of employee
     *
     * @param id - id
     */
    public void deleteEmployee(int id);

    /**
     * Here we restore employee that was deleted
     *
     * @param id - id
     */
    public void restoreEmployee(int id);

    /**
     * Here we delete details for address
     *
     * @param id - Employee id
     */
    public void deleteAddress(int id, int addressId);

    /**
     * Here we assign project details to employee
     *
     * @param employee id - Employee id
     * @param  project id- project id
     */
    public void assignProject(int employeeId, List<Integer> projectList);
    
    /**
     * Here we unassign project values to employee
     *
     * @param employee id - Employee id
     * @param project id - project id
     */
    public void unassignProject(int employeeId, List<Integer> projectList);

    /**
     * Here we check project id and employee id
     * present in projectEmployee or not
     *
     * @param id - employee id
     * @param projectId - project id
     */
    public List<List<Integer>> checkIdForAssignAndUnassign(int id,
            List<Integer> projectIdList);

    /**
     * Here we display assigned values
     *
     * @param id - employeeId
     */
    public List<Project> getAssignedProject(int id);  
} 
