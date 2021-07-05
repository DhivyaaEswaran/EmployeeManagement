package com.ideas2it.employeemanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.ideas2it.employeemanagement.dao.EmployeeDao;
import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.exception.UserDefinedException;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.projectmanagement.model.Project;
import com.ideas2it.projectmanagement.service.ProjectService;

/**
 * In this class we perform crud operations(create,read,update,delete)
 */
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao employeeDao;
	private ProjectService projectService;
	private CustomLogger log = new CustomLogger(EmployeeServiceImpl.class);

	/**
	 * Constructor for employeeDao
	 * @param employeeDao
	 */
	public EmployeeServiceImpl(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	/**
	 * Setter method contains project service
	 * @param projectService
	 */
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createOrUpdateEmployee(Employee employee) throws UserDefinedException {
		try {
			employeeDao.saveOrUpdateEmployee(employee);
		} catch (UserDefinedException e) {
			log.error("Error occured while creation or updation of employee", e);		
			throw new UserDefinedException("There is an "
					+ "issue while creating or updating");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Employee> getAllEmployees() 
			throws UserDefinedException {
		return employeeDao.getEmployees();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addEmployeeAddress(Address address, int employeeId) 
			throws UserDefinedException {
		try {
			Employee employee = employeeDao.getIndividualEmployee(employeeId);
			List<Address> addresses = employee.getAddresses();
			addresses.add(address);
			employee.setAddresses(addresses);
			employeeDao.saveOrUpdateEmployee(employee);
		} catch (UserDefinedException e) {
			log.error("Issue occured when adding address to employee", e);		
			throw new UserDefinedException(e.getMessage());
		} catch (NullPointerException e) {
			throw new UserDefinedException("No such employee found for adding address");
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteEmployee(int employeeId) 
			throws UserDefinedException {
		try {
			Employee employee = employeeDao.getIndividualEmployee(employeeId);
			List<Project> projects = employee.getProjects();
			projects.clear();
			employee.setIsDeleted(true);
			employee.setProjects(projects);
			employeeDao.saveOrUpdateEmployee(employee);
		} catch (UserDefinedException e) {
			log.error("Issue: While deleting an employee", e);		
			throw new UserDefinedException(e.getMessage());
		} catch (NullPointerException e) {
			throw new UserDefinedException("There might be no employee found");
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void restoreEmployee(int employeeId) 
			throws UserDefinedException {
		try {
			Employee employee = employeeDao.getIndividualEmployee(employeeId);
			employee.setIsDeleted(false);
			employeeDao.saveOrUpdateEmployee(employee);
		} catch (UserDefinedException e) {
			log.error("Issue: Occured when restoring an deleted employee", e);		
			throw new UserDefinedException(e.getMessage());
		} catch (NullPointerException e) {
			throw new UserDefinedException("There might be no"
					+ " employee deleted for restoring");
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void assignProject(int employeeId, List<Integer> projectId) 
			throws UserDefinedException {
		try {			
			Employee employee = employeeDao.getIndividualEmployee(employeeId);
			List<Project> assignedProjects = employee.getProjects();
			assignedProjects.addAll(projectService.getProjectValues(projectId));
			employee.setProjects(assignedProjects);
			employeeDao.saveOrUpdateEmployee(employee);
		} catch (UserDefinedException e) {
			log.error("Issue: While assigning employee to projects", e);		
			throw new UserDefinedException(e.getMessage());
		} catch (NullPointerException e) {
			throw new UserDefinedException("There might be no"
					+ " employee or projects for assigning");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getUnassignedProjectsForDisplay(int employeeId) 
			throws UserDefinedException {
		List<String> projectList = new ArrayList<String>();
		try {
			Employee employee = employeeDao.getIndividualEmployee(employeeId);
			projectList.add(employee.toString());
			getUnassignedProject(projectService.getProject(),
					employee.getProjects(), projectList);
		} catch (UserDefinedException e) {
			log.error("Issue when displaying unassigned project", e);		
			throw new UserDefinedException(e.getMessage());
		} catch (NullPointerException e) {
			throw new UserDefinedException("No projects have been assigned for display");
		}
		return projectList;
	}
	
	/**
	 * Here we display unassigned projects
	 * 
	 * @param allProjects
	 * @param assignedProjects
	 * @return
	 */
	private void getUnassignedProject(List<Project> allProjects,
			List<Project> assignedProjects, List<String> projectList) {
		if (assignedProjects.isEmpty()) {
			for (Project project : allProjects) {
				projectList.add(project.toString());
			}
		} else {
			for (Project project : assignedProjects) {
				if(allProjects.contains(project)) {
			        allProjects.remove(project);
				}
			}
			
			for (Project project : allProjects) {
				projectList.add(project.toString());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Address getIndividualAddress(int employeeId, int addressId) 
			throws UserDefinedException {
		Address address = null;
		try {
			Employee employee = employeeDao.getIndividualEmployee(employeeId);	

			for (Address addresses : employee.getAddresses()) {
				if (addressId == addresses.getAddressId()) {
					address = addresses;
				}
			}
		} catch (UserDefinedException e) {
			log.error("Issue occured while fetching an employee address", e);		
			throw new UserDefinedException(e.getMessage());
		} catch (NullPointerException e) {
			throw new UserDefinedException("No employee found while "
					+ "displaying address of an employee");
		}
		return address;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean checkId(int employeeId) throws UserDefinedException {
		int employee = 0;
		try {
			employee = employeeDao.checkId(employeeId);
		} catch (UserDefinedException e) {
			log.error("Issue in fetching count value", e);		
			throw new UserDefinedException("There is an issue while"
					+ " checking an employee id");
		}
		return (employee == 0) ? false : true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean checkDeletedId(int employeeId) throws UserDefinedException {
		int employee = 0;
		try {
			employee = employeeDao.checkDeletedId(employeeId);
		} catch (UserDefinedException e) {
			log.error("Issue in fetching count value", e);		
			throw new UserDefinedException("There is an issue while"
					+ " checking an employee id for deletion");
		}
		return (employee == 0) ? false : true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAddress(int employeeId, int addressId) 
			throws UserDefinedException {
		try {
			Employee employee = employeeDao.getIndividualEmployee(employeeId);

			for (Address addresses : employee.getAddresses()) {

				if (addressId == addresses.getAddressId()) {
					addresses.setIsDeleted(true);
				}
			}
			employeeDao.saveOrUpdateEmployee(employee);
		} catch (UserDefinedException e) {
			log.error("Issue: Occured when deleting an address of an employee", e);		
			throw new UserDefinedException(e.getMessage());
		} catch (NullPointerException e) {
			throw new UserDefinedException("No address found for employee to be deleted");
		}
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean checkValidMobileNumber(String mobileNumber) {
		return Pattern.matches("[789][0-9]{9}", mobileNumber);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean checkValidEmailId(String emailId) {
		return Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", emailId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Employee getIndividualEmployee(int employeeId) 
			throws UserDefinedException {
		return employeeDao.getIndividualEmployee(employeeId);
	}

	/**
	 * {@inheritDoc} 
	 */
	@Override
	public List<Address> getEmployeeAddress(int employeeId) 
			throws UserDefinedException {
		List<Address> addressList = new ArrayList<Address>();
		try {
			Employee employee = employeeDao.getIndividualEmployee(employeeId);	

			for (Address address : employee.getAddresses()) {

				if (false == address.getIsDeleted()) {
					addressList.add(address);
				} 
			}
		} catch (UserDefinedException e) {
			log.error("Issue: Issue in fetching employee's address", e);		
			throw new UserDefinedException(e.getMessage());
		} catch (NullPointerException e) {
			throw new UserDefinedException("There my be no address for an employee");
		}
		return addressList;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateAddress(Address address, int employeeId, int addressId) 
			throws UserDefinedException {
		try {
			Employee employee = employeeDao.getIndividualEmployee(employeeId);
			List<Address> addressList = employee.getAddresses();
            Address oldAddress = null;
            
			for (Address addresses : addressList) {
				
				if (addressId == addresses.getAddressId()) {
					oldAddress = addresses;
				}
			}	
			oldAddress.setDoorNumber(address.getDoorNumber());
			oldAddress.setStreetName(address.getStreetName());
			oldAddress.setDistrict(address.getDistrict());
			oldAddress.setState(address.getState());
			oldAddress.setCountry(address.getCountry());
			oldAddress.setPinCode(address.getPinCode());
			employee.setAddresses(addressList);
			employeeDao.saveOrUpdateEmployee(employee);
		} catch (UserDefinedException | NullPointerException e) {
			log.error("Issue occured while updating an employee address", e);		
			throw new UserDefinedException(e.getMessage());
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void unassignProject(int employeeId, List<Integer> projectId) 
			throws UserDefinedException {
		try {		
			Employee employee = employeeDao.getIndividualEmployee(employeeId);
			List<Project> assignedProjects = employee.getProjects();
			
			for (Integer projectIds : projectId) {
				for (Project project : employee.getProjects()) {
					if (project.getProjectId() == projectIds) {
						assignedProjects.remove(project);
						break;
					}
				}
			}
			employee.setProjects(assignedProjects);
			employeeDao.saveOrUpdateEmployee(employee);
		} catch (NullPointerException e) {
			log.error("Issue: So such project assigned for unassigning", e);
			throw new UserDefinedException(e.getMessage());
		} catch (UserDefinedException e) {
			throw new UserDefinedException("There might be no"
					+ " projects assigned for unassigning");
		}
	}
}