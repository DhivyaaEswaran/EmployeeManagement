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
    public void createEmployee(int id, String name, double salary, 
            Date dateOfBirth, String mobileNumber, String emailId) {
        Employee employee = new Employee(id, name, salary, 
                dateOfBirth, mobileNumber, emailId, addressList);
        employeeDao.insertEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Employee> getEmployeeValues(List<Integer> employeeList) {
        List<Employee> employee = employeeDao.getAllEmployee();
        List<Employee> employeeData = new ArrayList<Employee>();          
        
        for (Employee employeeDetails : employee) {
        
            if (employeeList.contains(employeeDetails.getId())) {
                employeeData.add(employeeDetails);
            }
        }
        return employeeData;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void assignProject(int id, List<Integer> projectId) {
        ProjectService projectService = new ProjectServiceImpl();
        Employee employee = employeeDao.getEmployeeProject(id);
        List<Project> project 
                = projectService.getProjectValues(projectId);
        employee.setId(id);
        employee.setProjects(project);
        employeeDao.assignProject(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void unassignProject(int id, List<Integer> projectId) {
        ProjectService projectService = new ProjectServiceImpl();
        Employee employee = employeeDao.getEmployeeProject(id);
        List<Project> project 
                = projectService.getProjectValues(projectId);
        employee.setId(id);
        employee.setProjects(project);
        employeeDao.unassignProject(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
     public List<List<Integer>> checkIdForAssignAndUnassign(int id,
             List<Integer> projectIdList) {
        Employee employee = employeeDao.getEmployeeProject(id);
        List<Project> projectDetails = employee.getProjects();
        List<Integer> projectIdValues = new ArrayList<Integer>();
        List<Integer> assignedValues = new ArrayList<Integer>();
        List<Integer> unassignedValues = new ArrayList<Integer>();
        List<List<Integer>> projectId = new ArrayList<List<Integer>>();
        
        for (Project project : projectDetails) {
            projectIdValues.add(project.getId());
        }
        
        for (Integer projectValue : projectIdList) {

            for (Integer project : projectIdValues) {

                if (projectValue == project) {
                    assignedValues.add(projectValue);
                    
                } else {
                    unassignedValues.add(projectValue);                    
                } 
            }
       }
       projectId.add(assignedValues);
       projectId.add(unassignedValues);
       return projectId;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<String> getAssignedProject(int id) {
        Employee employee = employeeDao.getEmployeeProject(id);
        List<Project> projectData = employee.getProjects();
        List<String> projectDetails = new ArrayList<String>();
        projectDetails.add(employee.toString());
 
        for(Project project : projectData) {
            projectDetails.add(project.toString());
        }
        return projectDetails;
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
    public void createAddress(int id, String doorNumber, String streetName, 
            String district, String state, String country, int pinCode) { 
        Address address = new Address(id, doorNumber, streetName,
                district, state, country, pinCode);
        addressList.add(address);            
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void addEmployeeAddress(int id, String doorNumber, String streetName,
            String district, String state, String country, int pinCode) {
        Address addressValue = new Address(id, doorNumber, streetName,
                district, state, country, pinCode);
        employeeDao.addEmployeeAddress(addressValue);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void deleteEmployee(int id) {
        employeeDao.deleteEmployee(id);
    } 

    /**
     * {@inheritdoc}
     */
    @Override
    public void deleteAddress(int id) {
        employeeDao.deleteAddress(id);  
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<String> getIndividualEmployee(int id) {
        Employee employee =  employeeDao.getIndividualEmployee(id);  
        List<Address> address = employee.getAddresses();
        List<String> employeeDetails = new ArrayList<String>();
        employeeDetails.add(employee.toString());
        
        for (Address addressDetails : address) {
            employeeDetails.add(addressDetails.toString());
        }
        address.clear();
        return employeeDetails;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<String> getIndividualEmployeeAddress(int id) {
        List<Address> addressValues = employeeDao.getIndividualAddress(id);
        List<String> addresses = new ArrayList<String>();

        for(Address addressList : addressValues) {
            addresses.add(addressList.toString());
        } 
        return addresses;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<String> getAllEmployee() {
        List<String> employeeDetails = new ArrayList<String>();       
        List<Employee> employeeValues = employeeDao.getAllEmployee();

        for (Employee employee : employeeValues) {
             employeeDetails.add(employee.toString());
        } 
        return employeeDetails;
    }

    /**
     * {@inheritdoc}
     */
    @Override    
    public void updateEmployee(int id, String name, double salary, 
            Date dateOfBirth, String emailId, String mobileNumber) {
        Employee employee = employeeDao.getEmployeeForUpdate(id);             
        employee.setId(id);
        employee.setName(name);
        employee.setSalary(salary);
        employee.setDateOfBirth(dateOfBirth);
        employee.setEmailId(emailId);
        employee.setMobileNumber(mobileNumber);
        employeeDao.updateEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void updateAddress(int employeeId, int addressId, String doorNumber, String streetName,
            String district, String state, String country, int pinCode) {
        Address address = employeeDao.getAddressValue(employeeId);             
        address.setId(employeeId);
        address.setAddressId(addressId);
        address.setDoorNumber(doorNumber);
        address.setStreetName(streetName);
        address.setDistrict(district);
        address.setState(state);
        address.setCountry(country);
        address.setPinCode(pinCode);
        employeeDao.updateAddress(address);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void updateSalary(int id, double salary) {
        Employee employee = employeeDao.getEmployeeForUpdate(id);
        employee.setId(id);
        employee.setName(employee.getName());
        employee.setSalary(salary);
        employee.setDateOfBirth(employee.getDateOfBirth());
        employee.setEmailId(employee.getEmailId());
        employee.setMobileNumber(employee.getMobileNumber());
        employeeDao.updateEmployee(employee);
    }    

    /**
     * {@inheritdoc}
     */
    @Override
    public void updateDateOfBirth(int id, Date dateOfBirth) {
        Employee employee = employeeDao.getEmployeeForUpdate(id);
        employee.setId(id);
        employee.setName(employee.getName());
        employee.setSalary(employee.getSalary());
        employee.setDateOfBirth(dateOfBirth);
        employee.setEmailId(employee.getEmailId());
        employee.setMobileNumber(employee.getMobileNumber());
        employeeDao.updateEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void updateEmailId(int id, String emailId) {
        Employee employee = employeeDao.getEmployeeForUpdate(id);
        employee.setId(id);
        employee.setName(employee.getName());
        employee.setSalary(employee.getSalary());
        employee.setDateOfBirth(employee.getDateOfBirth());
        employee.setEmailId(emailId);
        employee.setMobileNumber(employee.getMobileNumber());
        employeeDao.updateEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */ 
    @Override
    public void updateMobileNumber(int id, String mobileNumber) {
        Employee employee = employeeDao.getEmployeeForUpdate(id);
        employee.setId(id);
        employee.setName(employee.getName());
        employee.setSalary(employee.getSalary());
        employee.setDateOfBirth(employee.getDateOfBirth());
        employee.setEmailId(employee.getEmailId());
        employee.setMobileNumber(mobileNumber);
        employeeDao.updateEmployee(employee);
    }
}