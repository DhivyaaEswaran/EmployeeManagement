package com.ideas2it.employeemanagement.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import com.ideas2it.employeemanagement.dao.EmployeeDao;
import com.ideas2it.employeemanagement.dao.impl.EmployeeDaoImpl;
import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.exception.UserDefinedException;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.projectmanagement.dao.impl.ProjectDaoImpl;
import com.ideas2it.projectmanagement.model.Project;
import com.ideas2it.projectmanagement.service.impl.ProjectServiceImpl;
import com.ideas2it.projectmanagement.service.ProjectService;

/**
 * In this class we perform crud operations(create,read,update,delete)
 */
public class EmployeeServiceImpl implements EmployeeService {         
	private EmployeeDao employeeDao = new EmployeeDaoImpl();
	private CustomLogger log = new CustomLogger(EmployeeDaoImpl.class);

	/**
	 * {@inheritdoc}
	 */
	@Override
	public void createEmployee(String name, double salary, 
			Date dateOfBirth, String mobileNumber, String emailId) throws UserDefinedException {
		
		try {
			Employee employee = new Employee(name, salary, 
					dateOfBirth, mobileNumber, emailId);
			employeeDao.saveOrUpdateEmployee(employee);
		} catch (NullPointerException e) {
			log.error("Something went wrong", e);
		}
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
			String district, String state, String country, int pinCode) throws UserDefinedException {
		
		try {
			Employee employeeValues = employeeDao.getIndividualEmployee(id);
			List<Address> addressValues = employeeValues.getAddresses();
			Address address = new Address(doorNumber, streetName, district,
					state, country, pinCode);
			addressValues.add(address);
			employeeValues.setAddresses(addressValues);
			employeeDao.saveOrUpdateEmployee(employeeValues);
		} catch (NullPointerException e) {
			log.error("Something went wrong", e);
		}
	} 

	/**
	 * {@inheritdoc}
	 */
	@Override
	public Employee getIndividualEmployee(int id) throws UserDefinedException {
		Employee employee =  employeeDao.getIndividualEmployee(id);  
		return employee;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public List<Employee> getAllEmployee() throws UserDefinedException{      
		List<Employee> employeeValues = employeeDao.getEmployees();
		return employeeValues;
	}

	/**
	 * {@inheritdoc} 
	 */
	@Override    
	public void updateEmployee(int id, String name, double salary, 
			Date dateOfBirth, String mobileNumber, String emailId) throws UserDefinedException {
		
		try {
			Employee employee = employeeDao.getIndividualEmployee(id);             
			employee.setId(id);
			employee.setName(name);
			employee.setSalary(salary);
			employee.setDateOfBirth(dateOfBirth);
			employee.setMobileNumber(mobileNumber);
			employee.setEmailId(emailId);
			employeeDao.saveOrUpdateEmployee(employee);
		} catch (NullPointerException e) {
			log.error("Something went wrong", e);
		}
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public void updateSalary(int id, double salary) throws UserDefinedException {
		
		try {
			Employee employee = employeeDao.getIndividualEmployee(id);
			employee.setId(id);   
			employee.setSalary(salary); 
			employeeDao.saveOrUpdateEmployee(employee);
		} catch (NullPointerException e) {
			log.error("Something went wrong", e);
		}
	}    

	/**
	 * {@inheritdoc} 
	 */
	@Override
	public void updateDateOfBirth(int id, Date dateOfBirth) throws UserDefinedException {
		
		try {
			Employee employee = employeeDao.getIndividualEmployee(id);
			employee.setId(id);
			employee.setDateOfBirth(dateOfBirth);
			employeeDao.saveOrUpdateEmployee(employee);
		} catch (NullPointerException e) {
			log.error("Something went wrong", e);
		}
	}

	/**
	 * {@inheritdoc}
	 * @throws EmployeeNotFoundException 
	 */
	@Override
	public void updateEmailId(int id, String emailId) throws UserDefinedException {
		
		try {	
			Employee employee = employeeDao.getIndividualEmployee(id);
			employee.setId(id);
			employee.setEmailId(emailId);
			employeeDao.saveOrUpdateEmployee(employee);
		} catch (NullPointerException e) {
			log.error("Something went wrong", e);
		}
	}

	/**
	 * {@inheritdoc}
	 * @throws EmployeeNotFoundException 
	 */ 
	@Override
	public void updateMobileNumber(int id, String mobileNumber) throws UserDefinedException {
		
		try {		
			Employee employee = employeeDao.getIndividualEmployee(id);
			employee.setId(id);
			employee.setMobileNumber(mobileNumber);
			employeeDao.saveOrUpdateEmployee(employee);
		} catch (NullPointerException e) {
			log.error("Something went wrong", e);
		}
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public void updateAddress(int id, int addressId, String doorNumber, String streetName,
			String district, String state, String country, int pinCode) throws UserDefinedException {
		
		try {
			Employee employee = employeeDao.getIndividualEmployee(id);             
			List<Address> addressValues = employee.getAddresses();

			for (Address address : addressValues) {

				if (addressId == address.getAddressId()) {
					address.setId(addressId);
					address.setDoorNumber(doorNumber);
					address.setStreetName(streetName);
					address.setDistrict(district);
					address.setState(state);
					address.setCountry(country);
					address.setPinCode(pinCode);
				}
			}
			employeeDao.saveOrUpdateEmployee(employee);
		} catch (NullPointerException e) {
			log.error("Something went wrong", e);
		}
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public boolean checkId(int id) throws UserDefinedException{        
		int employee = employeeDao.checkId(id);      
		return (employee == 0) ? false : true;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public boolean checkDeletedId(int id) throws UserDefinedException {        
		int employee = employeeDao.checkDeletedId(id);      
		return (employee == 0) ? false : true;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public void deleteEmployee(int id) throws UserDefinedException {
		
		try {	
			Employee employee = employeeDao.getIndividualEmployee(id);
			List<Project> project = employee.getProjects();
			project.clear();
			employee.setId(id);
			employee.setIsDeleted(true);
			employee.setProjects(project);
			employeeDao.saveOrUpdateEmployee(employee);
		} catch (NullPointerException e) {
			log.error("Something went wrong", e);
		}
	} 

	/**
	 * {@inheritdoc}
	 */
	@Override
	public void restoreEmployee(int id) throws UserDefinedException {
		Employee employee = employeeDao.getIndividualEmployee(id);
		employee.setId(id);
		employee.setIsDeleted(false);
		employeeDao.saveOrUpdateEmployee(employee);
	} 

	/**
	 * {@inheritdoc}
	 */
	@Override
	public void deleteAddress(int id, int addressId) throws UserDefinedException { 
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
	public void assignProject(int id, List<Integer> projectId) throws UserDefinedException {
		
		try {	
			ProjectService projectService = new ProjectServiceImpl();
			Employee employee = employeeDao.getIndividualEmployee(id);
			List<Project> project 
			= projectService.getProjectValues(projectId);
			List<Project> assignedProject = employee.getProjects();
			assignedProject.addAll(project);
			employee.setId(id);
			employee.setProjects(assignedProject);
			employeeDao.saveOrUpdateEmployee(employee);
		} catch (NullPointerException e) {
			log.error("Something went wrong", e);
		}
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public void unassignProject(int id, List<Integer> projectId) throws UserDefinedException {
		
		try {		
			ProjectService projectService = new ProjectServiceImpl();
			List<Project> project = projectService.getProjectValues(projectId);
			Employee employee = employeeDao.getIndividualEmployee(id);
			List<Project> assignedProject = employee.getProjects();

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
		} catch (NullPointerException e) {
			log.error("Something went wrong", e);
		}
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public List<Integer> checkIdForAssignAndUnassign(int id,
			List<Integer> projectIdList, String value) throws UserDefinedException {
		Employee employee = employeeDao.getIndividualEmployee(id);
		List<Project> projectValues = employee.getProjects();
		List<Integer> projectId = new ArrayList<Integer>();
		List<Integer> projectIdForAssign = new ArrayList<Integer>();
		List<Integer> projectIdForUnassign = new ArrayList<Integer>();
		boolean isChecked;
		
		if (null == projectValues) {
			Collections.copy(projectIdForAssign, projectIdList);
		} else {

			for (Project projectList : projectValues) {
				projectId.add(projectList.getProjectId());
			}
			for (Integer unassignedId : projectIdList) {
				isChecked = false;

				for (Integer assignedId : projectId) {

					if (unassignedId == assignedId) {
						isChecked = true;
						projectIdForUnassign.add(unassignedId);
					}
				}
				if (false == isChecked) {
					projectIdForAssign.add(unassignedId);
				}
			}
		}       
		return ("assign" == value) ? projectIdForAssign : projectIdForUnassign;
	}

	/**
	 * {@inheritdoc}
	 */
	@Override
	public List<Employee> getEmployeeValues(List<Integer> employeeList) throws UserDefinedException{
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
	public List<Project> getAssignedProject(int id) throws UserDefinedException {
		Employee employee = employeeDao.getIndividualEmployee(id);
		List<Project> projectData = employee.getProjects();
		return projectData;
	}

	/**
	 * {@inheritdoc}
	 */
	public List<Project> displayProjects() throws UserDefinedException {
		ProjectService projectService = new ProjectServiceImpl();
		List<Project> projects = projectService.getProject();
		return projects;
	}
}
