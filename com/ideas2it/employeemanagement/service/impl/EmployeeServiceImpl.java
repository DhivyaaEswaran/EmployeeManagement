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
    private EmployeeDao employeeDao = new EmployeeDaoImpl(); 
    
    /**
     * {@inheritdoc}
     */
    @Override
    public void createEmployee(String name, double salary, 
            Date dateOfBirth, String mobileNumber, String emailId) {
        Employee employee = new Employee(name, salary, 
                dateOfBirth, mobileNumber, emailId);
        employeeDao.saveOrUpdateEmployee(employee);
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
    public void addEmployeeAddress(int id, String doorNumber, String streetName,
            String district, String state, String country, int pinCode) {
        Employee employeeValues = employeeDao.getIndividualEmployee(id);
        List<Address> addressValues = employeeValues.getAddresses();
        Address address = new Address(doorNumber, streetName, district,
                state, country, pinCode);
        addressValues.add(address);
        employeeValues.setAddresses(addressValues);
        employeeDao.saveOrUpdateEmployee(employeeValues);
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
            
            if (!addressDetails.getIsDeleted()) {         
                employeeDetails.add(addressDetails.toString());
            }
        }
        address.clear();
        return employeeDetails;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<String> getAllEmployee() {
        List<String> employeeDetails = new ArrayList<String>();       
        List<Employee> employeeValues = employeeDao.getEmployees();

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
        Employee employee = employeeDao.getIndividualEmployee(id);             
        employee.setId(id);
        employee.setName(name);
        employee.setSalary(salary);
        employee.setDateOfBirth(dateOfBirth);
        employee.setEmailId(emailId);
        employee.setMobileNumber(mobileNumber);
        employeeDao.saveOrUpdateEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void updateSalary(int id, double salary) {
        Employee employee = employeeDao.getIndividualEmployee(id);
        employee.setId(id);   
        employee.setSalary(salary); 
        employeeDao.saveOrUpdateEmployee(employee);
    }    

    /**
     * {@inheritdoc}
     */
    @Override
    public void updateDateOfBirth(int id, Date dateOfBirth) {
        Employee employee = employeeDao.getIndividualEmployee(id);
        employee.setId(id);
        employee.setDateOfBirth(dateOfBirth);
        employeeDao.saveOrUpdateEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void updateEmailId(int id, String emailId) {
        Employee employee = employeeDao.getIndividualEmployee(id);
        employee.setId(id);
        employee.setEmailId(emailId);
        employeeDao.saveOrUpdateEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */ 
    @Override
    public void updateMobileNumber(int id, String mobileNumber) {
        Employee employee = employeeDao.getIndividualEmployee(id);
        employee.setId(id);
        employee.setMobileNumber(mobileNumber);
        employeeDao.saveOrUpdateEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void updateAddress(int id, int addressId, String doorNumber, String streetName,
            String district, String state, String country, int pinCode) {
        Employee employee = employeeDao.getIndividualEmployee(id);             
        List<Address> addressValues = employee.getAddresses();

        for (Address address : addressValues) {

            if (addressId == address.getAddressId()) {
                address.setAddressId(addressId);
                address.setDoorNumber(doorNumber);
                address.setStreetName(streetName);
                address.setDistrict(district);
                address.setState(state);
                address.setCountry(country);
                address.setPinCode(pinCode);
            }
        }
        employeeDao.saveOrUpdateEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean checkId(int id) {        
        int employee = employeeDao.checkId(id);      
        return (employee == 0) ? false : true;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean checkDeletedId(int id) {        
        int employee = employeeDao.checkDeletedId(id);      
        return (employee == 0) ? false : true;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void deleteEmployee(int id) {
        Employee employee = employeeDao.getIndividualEmployee(id);
        List<Project> project = employee.getProjects();
        project.clear();
        employee.setId(id);
        employee.setIsDeleted(true);
        employee.setProjects(project);
        employeeDao.saveOrUpdateEmployee(employee);
    } 

    /**
     * {@inheritdoc}
     */
    @Override
    public void restoreEmployee(int id) {
        Employee employee = employeeDao.getIndividualEmployee(id);
        employee.setId(id);
        employee.setIsDeleted(false);
        employeeDao.saveOrUpdateEmployee(employee);
    } 

    /**
     * {@inheritdoc}
     */
    @Override
    public void deleteAddress(int id, int addressId) { 
        Employee employee = employeeDao.getIndividualEmployee(id);  
        List<Address> address = employee.getAddresses();
        for(Address addressValues : address){
            
            if (addressId == addressValues.getAddressId()) {
                addressValues.setIsDeleted(true);
            }
        }                     
        employeeDao.saveOrUpdateEmployee(employee);  
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void assignProject(int id, List<Integer> projectId) {
        ProjectService projectService = new ProjectServiceImpl();
        Employee employee = employeeDao.getIndividualEmployee(id);
        List<Project> project 
                = projectService.getProjectValues(projectId);
        List<Project> assignedProject = employee.getProjects();
        assignedProject.addAll(project);
        employee.setId(id);
        employee.setProjects(assignedProject);
        employeeDao.saveOrUpdateEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void unassignProject(int id, List<Integer> projectId) {
        ProjectService projectService = new ProjectServiceImpl();
        List<Project> project 
                = projectService.getProjectValues(projectId);
        Employee employee = employeeDao.getIndividualEmployee(id);
        List<Project> assignedProject 
                = employee.getProjects();

        for (int index = 0; index < project.size(); index++) {

            for (int indexValues = 0; indexValues < assignedProject.size(); indexValues++) {

                if ((project.get(index)).getProjectId() 
                        == (assignedProject.get(indexValues)).getProjectId()) {
                    assignedProject.remove(indexValues);   
                }
            }
        }
        employee.setId(id);
        employee.setProjects(assignedProject);
        employeeDao.saveOrUpdateEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
     public List<List<Integer>> checkIdForAssignAndUnassign(int id,
             List<Integer> projectIdList) {
        Employee employee = employeeDao.getIndividualEmployee(id);
        List<Project> projectDetails = employee.getProjects();
        List<Integer> projectIdValues = new ArrayList<Integer>();
        List<Integer> assignedValues = new ArrayList<Integer>();
        List<Integer> unassignedValues = new ArrayList<Integer>();
        List<List<Integer>> projectId = new ArrayList<List<Integer>>();
        
        if (projectDetails.isEmpty()) {
            unassignedValues = projectIdList;
        } else {        
            for (Project project : projectDetails) {
                projectIdValues.add(project.getProjectId());
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
       }
       projectId.add(assignedValues);
       projectId.add(unassignedValues);
       return projectId;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Employee> getEmployeeValues(List<Integer> employeeList) {
        List<Employee> employee = employeeDao.getEmployees();
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
    public List<String> getAssignedProject(int id) {
        Employee employee = employeeDao.getIndividualEmployee(id);
        List<Project> projectData = employee.getProjects();
        List<String> projectDetails = new ArrayList<String>();
        projectDetails.add(employee.toString());
 
        for(Project project : projectData) {
            projectDetails.add(project.toString());
        }
        return projectDetails;
    }
}