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
    public List<Employee> getEmployee();

    /**
     * Here we update address details of employee
     *
     * @param address - address
     */
    public void updateAddress(Address address);

    /**
     * Here we get employee for update
     *
     * @param id - id
     * @return employee
     */
    public Employee getEmployeeForUpdate(int id);

    /**
     * Here we get address value for update
     *
     * @param id - id
     * @return address
     */
    public Address getAddressForUpdate(int id);

    /**
     * Here we check id of employee
     *
     * @param id - id
     */
    public int checkId(int id);

    /**
     * Here we delete employee details
     *
     * @param employee - employee
     */
    public void saveOrUpdateEmployee(Employee employee);    
}