package com.ideas2it.employeemanagement.dao;

import java.util.List;
import java.util.Date;
import java.sql.ResultSet;

import com.ideas2it.employeemanagement.dao.impl.EmployeeDaoImpl;
import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.exception.UserDefinedException;

/**
 * In this class we perform crud opertations(create,read,update,delete)
 */
public interface EmployeeDao {

    /**
     * Here we display individual details of employee
     *
     * @param id - id
     * @return employee
     * @throws UserDefinedException 
     */
    public Employee getIndividualEmployee(int id) throws UserDefinedException;

    /**
     * Here we display all employee 
     * @throws UserDefinedException 
     */
    public List<Employee> getEmployees() throws UserDefinedException;

    /**
     * Here we check id of employee
     *
     * @param id - id
     * @throws UserDefinedException 
     */
    public int checkId(int id) throws UserDefinedException;

    /**
     * Here we check deleted employee id
     *
     * @param id - id
     * @throws UserDefinedException
     */
    public int checkDeletedId(int id) throws UserDefinedException;

    /**
     * Here we delete employee details
     *
     * @param employee - employee
     * @throws UserDefinedException 
     */
    public void saveOrUpdateEmployee(Employee employee)  throws UserDefinedException;    
}