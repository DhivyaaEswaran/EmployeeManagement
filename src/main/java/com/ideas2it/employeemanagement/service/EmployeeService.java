package com.ideas2it.employeemanagement.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.exception.UserDefinedException;
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
     * @throws UserDefinedException 
     */
    public void createEmployee(Employee employee) throws UserDefinedException;
    
    /**
     * Here we get employee values for assigning it to project
     *
     * @param employeeId - employee id
     * @throws UserDefinedException 
     */
    public List<Employee> getEmployeeValues(List<Integer> employeeId) throws UserDefinedException;

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
     * @throws UserDefinedException 
     */
    public Employee getIndividualEmployee(int id) throws UserDefinedException;
    
    /**
     * Fetch individual address of an employee.
     * 
     * @param id
     * @param addressId
     * @return list of address
     * @throws UserDefinedException
     */
    public List<Address>  getIndividualAddress(int id, int addressId) throws UserDefinedException;

    /**
     * Here we display all the details of employee
     * 
     * @return list of employee
     * @throws UserDefinedException 
     */
    public List<Employee> getAllEmployee() throws UserDefinedException;

    /**
     * Here we update all the details for employees
     *
     * @param id - Employee id
     * @param name - Employee name
     * @param salary - Employee salary
     * @param dateOfBirth - Employee dateOfBirth
     * @param mobileNumber - Employee mobile number
     * @param emailId - Employee emailId
     * @throws UserDefinedException  
     */
    public void updateEmployee(Employee employee) throws UserDefinedException;

    /**
     * Here we update salary for individual employee 
     *
     * @param id - id
     * @param salary - salary
     * @throws UserDefinedException 
     */
    public void updateSalary(int id, double salary) throws UserDefinedException;

    /**
     * Here we update date of birth for individual employee 
     *
     * @param id - id
     * @param date of birth - date of birth
     * @throws UserDefinedException 
     */
    public void updateDateOfBirth(int id, Date dateOfBirth) throws UserDefinedException;

    /**
     * Here we update mobile number for individual employee 
     *
     * @param id - id
     * @param mobile number - mobile number
     * @throws UserDefinedException 
     */
    public void updateMobileNumber(int id, String mobileNumber) throws UserDefinedException;

    /**
     * Here we update email id for individual employee 
     *
     * @param id - id
     * @param email id - email id
     * @throws UserDefinedException 
     */
    public void updateEmailId(int id, String emailId) throws UserDefinedException;  

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
     * @throws UserDefinedException 
     */
    public void updateAddress(Address address, int id) throws UserDefinedException; 

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
     * @throws UserDefinedException 
     */
    public void addEmployeeAddress(Address address, int id) throws UserDefinedException;
       
    /**
     * Here we check id for employee
     *
     * @param id - id 
     * @throws UserDefinedException 
     */
    public boolean checkId(int id) throws UserDefinedException;

    /**
     * Here we check deleted employee id
     *
     * @param id - id 
     * @throws UserDefinedException 
     */
    public boolean checkDeletedId(int id) throws UserDefinedException;

    /**
     * Here we delete details of employee
     *
     * @param id - id
     * @throws UserDefinedException 
     */
    public void deleteEmployee(int id) throws UserDefinedException;

    /**
     * Here we restore employee that was deleted
     *
     * @param id - id
     * @throws UserDefinedException 
     */
    public void restoreEmployee(int id) throws UserDefinedException;

    /**
     * Here we delete details for address
     *
     * @param id - Employee id
     * @throws UserDefinedException  
     */
    public void deleteAddress(int id, int addressId) throws UserDefinedException;

    /**
     * Here we assign project details to employee
     *
     * @param employee id - Employee id
     * @param  project id- project id
     * @throws UserDefinedException  
     */
    public void assignProject(int employeeId, List<Integer> projectList) throws UserDefinedException;
    
    /**
     * Here we unassign project values to employee
     *
     * @param employee id - Employee id
     * @param project id - project id
     * @throws UserDefinedException  
     */
    public void unassignProject(int employeeId, List<Integer> projectList) throws UserDefinedException;

    /**
     * Here we check project id and employee id
     * present in projectEmployee or not
     *
     * @param id - employee id
     * @param projectId - project id
     * @throws UserDefinedException 
     */
    public List<Integer> checkIdForAssignAndUnassign(int id,
            List<Integer> projectIdList, String value) throws UserDefinedException;

    /**
     * Here we display assigned values
     *
     * @param id - employeeId 
     * @throws UserDefinedException 
     */
    public List<Project> getAssignedProject(int id) throws UserDefinedException;
    
    /**
     * We fetch all project datas.
     * 
     * @return List of projects
     * @throws UserDefinedException 
     */
    public List<Project> displayProjects() throws UserDefinedException;
    
    /**
     * Fetch employee data along with unassigned projects.
     * 
     * @param id
     * @return List of projects along with employee
     * @throws UserDefinedException
     */
    public List<String> getProjectsForDisplay(int id) throws UserDefinedException;
} 