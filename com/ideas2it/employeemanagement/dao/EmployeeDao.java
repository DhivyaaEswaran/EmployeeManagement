package com.ideas2it.employeemanagement.dao;

import java.util.List;
import java.sql.ResultSet;

import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;

/**
 * In this class we perform crud opertations(create,read,update,delete)
 */
public interface EmployeeDao {

    /**
     * Here we insert the details for employee
     *
     * @param employee - Employee
     */
    public void insertEmployee(Employee employee); 
}