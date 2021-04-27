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
     * Here we assign project values for employee
     *
     * @param employee - Employee
     */
    public void assignProject(Employee employee);
    
    /**
     * Here we unassign project details to employee
     *
     * @param employee - Employee
     */
    public void unassignProject(Employee employee); 
 
    /**
     * Here we check id for assigning project
     *
     * @param id - employee id
     */ 
    public Employee getEmployeeProject(int id);

    /**
     * Here we delete details of employee
     *
     * @param id - id
     */
    public void deleteEmployee(int id);

    /**
     * Here we delete details for address
     *
     * @param id - Employee id 
     */
    public void deleteAddress(int id);

    /**
     * Here we display individual details of employee
     *
     * @param id - id
     */
    public Employee getIndividualEmployee(int id);

    /**
     * Here we display individual address details of employee
     *
     * @param id - id
     */
    public List getIndividualAddress(int EmployeeId);

    /**
     * Here we display all details of employee
     *
     * @param id - id
     */
    public List<Employee> getAllEmployee();

    /**
     * Here we update all details of employee
     *
     * @param employee - employee pojo
     */
    public void updateEmployee(Employee employee);

    /**
     * Here we get address value details of employee
     *
     * @param Employee id - employeeid
     */
    public Address getAddressValue(int EmployeeId);

    /**
     * Here we add multiple address for employee
     *
     * @param EmployeeId - employeeId
     */
    public void addEmployeeAddress(Address address);

    /**
     * Here we update all the address details of employee
     *
     * @param address - address
     */ 
    public void updateAddress(Address address);

    /**
     * Here we get all the values for updating employee details
     * 
     * @param id - Employee id
     */
    public Employee getEmployeeForUpdate(int id);
}