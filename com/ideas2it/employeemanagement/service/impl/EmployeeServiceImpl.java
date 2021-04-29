package com.ideas2it.employeemanagement.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.ideas2it.employeemanagement.dao.EmployeeDao;
import com.ideas2it.employeemanagement.dao.impl.EmployeeDaoImpl;
import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.projectmanagement.model.Project;
import com.ideas2it.projectmanagement.service.impl.ProjectServiceImpl;
import com.ideas2it.projectmanagement.service.ProjectService;
 
/**
 * In this class we perform crud operations(create,read,update,delete)
 */
public class EmployeeServiceImpl implements EmployeeService {
    private List<Address> addressList = new ArrayList<Address>();     
    private EmployeeDao employeeDao = new EmployeeDaoImpl(); 
    
    /**
     * {@inheritdoc}
     */
    @Override
    public void createEmployee(String name, double salary, 
            Date dateOfBirth, String mobileNumber, String emailId) {
        Employee employee = new Employee(name, salary, 
                dateOfBirth, mobileNumber, emailId, addressList);
        employeeDao.insertEmployee(employee);
    }


    /**
     * {@inheritdoc}
     */
    @Override
    public boolean checkValidMobileNumber(String mobileNumber) {
        return Pattern.matches("[789][0-9]{9}", mobileNumber);        
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public boolean checkValidEmailId(String emailId) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(emailRegex, emailId); 
    } 

    /**
     * {@inheritdoc}
     */
    @Override
    public void createAddress(String doorNumber, String streetName, 
            String district, String state, String country, int pinCode) { 
        Address address = new Address(doorNumber, streetName,
                district, state, country, pinCode);
        addressList.add(address);            
    }
}