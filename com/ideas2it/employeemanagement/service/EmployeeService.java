package com.ideas2it.employeemanagement.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.ideas2it.employeemanagement.model.Employee;

/**
 * In this class we perform crud operations(create,read,update,delete)
 */
public interface EmployeeService {

    /**
     * Here we create details for employee
     *
     * @param id - Employee id
     * @param name - Employee name
     * @param salary - Employee salary
     * @param dateOfBirth - Employee dateOfBirth
     * @param mobileNumber - Employee mobile number
     * @param emailId - Employee emailId
     */
    public void createEmployee(String name, double salary, 
            Date dateOfBirth, String mobileNumber, String emailId);


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
            String district, String state, String country, int pinCode);
} 