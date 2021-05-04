package com.ideas2it.employeemanagement.controller;

import java.sql.Date;
import java.util.List;

import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImpl;

/**
 * In this class we perform crud operations(create,read,update,delete)
 */
public class EmployeeController {
    private EmployeeService employeeService = new EmployeeServiceImpl();
    
    /**
     * Here we are creating values for employee
     * 
     * @param name - Employee name
     * @param salary - Employee salary
     * @param dateOfBirth - Employee dateOfBirth
     * @param mobileNumber - Employee mobile number
     * @param emailId - Employee emailId
     */
    public void createEmployee(String name, double salary, 
            Date dateOfBirth, String mobileNumber, String emailId) {      
        employeeService.createEmployee(name, salary, 
                dateOfBirth, mobileNumber, emailId);           
    }

    /**
     * Here we get the valid mobile number
     *
     * @param mobile number - Employee mobile number
     *
     * @return true if employee mobile number is present
     *         else false 
     */
    public boolean checkValidMobileNumber(String mobileNumber) {
        return employeeService.checkValidMobileNumber(mobileNumber);   
    }

    /**
     * Here we get the valid email id 
     *
     * @param email id - Employee email id
     *
     * @return true if employee id is present
     *         else false    
     */
    public boolean checkValidEmailId(String emailId) {
        return employeeService.checkValidEmailId(emailId);        
    } 

    /**
     * Here we display individual details of employee
     *
     * @param id - id
     * @return id
     */
    public List<String> getIndividualEmployee(int id) {
        return employeeService.getIndividualEmployee(id);        
    }

    /**
     * Here we display all details of employee
     */
    public List<String> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    /**
     * Here we are updating all the values for employee
     * 
     * @param id - Employee id
     * @param name - Employee name
     * @param salary - Employee salary
     * @param dateOfBirth - Employee dateOfBirth
     * @param mobileNumber - Employee mobile number
     * @param emailId - Employee emailId
     */
    public void updateEmployee(int id, String name, double salary, 
            Date dateOfBirth, String emailId, String mobileNumber) {      
        employeeService.updateEmployee(id, name, salary, 
                dateOfBirth, emailId, mobileNumber);       
    }
    
    /**
     * Here we update salary for individual employee 
     *
     * @param id - id
     * @param salary - salary
     */
    public void updateSalary(int id, double salary) {
        employeeService.updateSalary(id, salary);
    }

    /**
     * Here we update date of birth for individual employee 
     *
     * @param id - id
     * @param date of birth - date of birth
     */
    public void updateDateOfBirth(int id, Date dateOfBirth) {
        employeeService.updateDateOfBirth(id, dateOfBirth);
    }

    /**
     * Here we update mailId for individual employee 
     *
     * @param id - id
     * @param emailId - emailId
     */
    public void updateEmailId(int id, String emailId) {
        employeeService.updateEmailId(id, emailId);
    }

    /**
     * Here we update mobile number for individual employee 
     *
     * @param id - id
     * @param mobile number - mobile number
     */
    public void updateMobileNumber(int id, String mobileNumber) {
        employeeService.updateMobileNumber(id, mobileNumber);
    }

    /**
     * Here we are updating all the address details for employee
     *
     * @param id - Address id
     * @param doorNumber - doorNumber
     * @param streetName - streetName
     * @param district - district
     * @param state - state
     * @param country - country
     * @param pinCode - pinCode
     */
    public void updateAddress(int id, int addressId, String doorNumber, 
            String streetName, String district, String state,
            String country, int pinCode) {
        employeeService.updateAddress(id, addressId, doorNumber, 
                streetName, district, state, country, pinCode);
    }

    /**
     * Here we add multiple address for employee
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
            String district, String state, String country, int pinCode) {
        employeeService.addEmployeeAddress(id, doorNumber, streetName, district, 
                state, country, pinCode);
    }

    /**
     * Here we check employee id 
     *
     * @param id - id
     */
    public boolean checkId(int id) {
        return employeeService.checkId(id);
    }

    /**
     * Here we delete details of employee
     *
     * @param id - id
     */
    public void deleteEmployee(int id) {
        employeeService.deleteEmployee(id);
    }

    /**
     * Here we restore employee that was deleted
     *
     * @param id - id
     */
    public void restoreEmployee(int id) {
        employeeService.restoreEmployee(id);
    }

    /**
     * Here we delete details of addres
     *
     * @param id - id
     */
    public void deleteAddress(int id, int addressId) {
        employeeService.deleteAddress(id, addressId);
    }

    /**
     * Here we assign project values to employee
     * 
     * @param id - Employee Id
     * @param projectIdList - list
     */
    public void assignProject(int id, List<Integer> projectIdList) {
        employeeService.assignProject(id, projectIdList);
    }

    /**
     * Here we unassign project values to employee
     *
     * @param id - Employee id
     * @param projectIdList - list
     */
    public void unassignProject(int id, List<Integer> projectIdList) {
        employeeService.unassignProject(id, projectIdList);
    }

    /**
     * Here we check both project id and employee id 
     * present in projectEmployee or not
     *
     * @param id - id
     * @param projectList - list
     */
    public List<List<Integer>> checkIdForAssignAndUnassign(int id, List<Integer> projectIdList) {
        return employeeService.checkIdForAssignAndUnassign(id, projectIdList);
    }

    /**
     * Here we display assigned project values
     *
     * @param id - projectId
     */
    public List<String> getAssignedProject(int id) {
        return employeeService.getAssignedProject(id);    
    }
}