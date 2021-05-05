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
     * Here we display individual details of employee
     *
     * @param id - id
     * @return employee
     */
    public Employee getIndividualEmployee(int id);

    /**
     * Here we display all employee 
     */
    public List<Employee> getEmployees();

    /**
     * Here we check id of employee
     *
     * @param id - id
     */
    public int checkId(int id);

    /**
     * Here we check deleted employee id
     *
     * @param id - id
     */
    public int checkDeletedId(int id);

    /**
     * Here we delete employee details
     *
     * @param employee - employee
     */
    public void saveOrUpdateEmployee(Employee employee);    
}