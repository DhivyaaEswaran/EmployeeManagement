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
     * @param id - Employee id
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
     * Here we get employee address details
     *
     * @param id - Address id
     * @param doorNumber - doorNumber
     * @param streetName - streetName
     * @param district - district
     * @param state - state
     * @param country - country
     * @param pinCode - pinCode
     */
    public void createAddress(String doorNumber, String streetName, 
            String district, String state, String country, int pinCode) {
        employeeService.createAddress(doorNumber, streetName, 
                district, state, country, pinCode);  
    }
}