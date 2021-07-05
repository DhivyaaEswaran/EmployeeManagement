package com.ideas2it.employeemanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.exception.UserDefinedException;

/**
 * Processing the values to employee service from jsp  
 * through employee controller
 * 
 * @author DhivyaaEswaran
 */
@Controller
public class EmployeeController {

	ApplicationContext applicationContext =
			new ClassPathXmlApplicationContext("beans.xml");
	EmployeeService employeeService = 
			applicationContext.getBean("employeeService",
			EmployeeService.class);

	/**
	 * Fetching all employee details and pass to employeeMainPage.
	 * 
	 * @return modelAndView
	 */
	@GetMapping(value = "/getAllEmployees")
	private ModelAndView getAllEmployees() {
		ModelAndView modelAndView = new ModelAndView();

		try {			
			modelAndView.addObject("employee",
					employeeService.getAllEmployees());
			modelAndView.setViewName("employeeMainPage");
		} catch (UserDefinedException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

	/**
	 * Call employee create form.
	 * 
	 * @return ModelAndView
	 */
	@GetMapping("/goToEmployeeForm")
	public ModelAndView getEmployeeForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("createEmployee");
		modelAndView.addObject("employee", new Employee());
		return modelAndView;
	}
	
	/**
	 * Create the employee details by updating
	 * values to service layer.
	 * 
	 * @param employee
	 * @return modelAndView
	 */
	@PostMapping(value = "/createEmployee")
	public ModelAndView createEmployee(@ModelAttribute("employee") Employee employee) { 
		ModelAndView modelAndView = new ModelAndView();

		try {
			employeeService.createOrUpdateEmployee(employee);	
			modelAndView.addObject("message", "created successfully");
			modelAndView.setViewName("message");
		} catch (UserDefinedException | NullPointerException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;		
	}
	
	/**
	 * Deleting an employee data.
	 * 
	 * @param employeeId
	 * @return modelAndView
	 */
	@GetMapping(value = "/deleteEmployee")
	public ModelAndView deleteEmployee(@RequestParam("id") int employeeId) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			employeeService.deleteEmployee(employeeId);
			modelAndView.addObject("message", "deleted successfully");
			modelAndView.setViewName("message");
		} catch (UserDefinedException | NullPointerException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

	/**
	 * Restoring an employee details.
	 * 
	 * @param employeeId
	 * @return modelAndView
	 */
	@GetMapping(value = "/restore")
	public ModelAndView restoreEmployee(@RequestParam ("id") int employeeId) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			employeeService.restoreEmployee(employeeId);
			modelAndView.addObject("message", "restored successfully");
			modelAndView.setViewName("message");
		} catch (UserDefinedException | NullPointerException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

	/**
	 * Call restore employee form.
	 * 
	 * @return String
	 */
	@GetMapping(value = "/goToRestore")
	public String goToRestorePage() {
		return "employeeRestore";
	}
	
	/**
	 * Call address create form
	 * @param employeeId
	 * @return ModelAndView
	 */
	@GetMapping("/goToAddressForm")
	public ModelAndView getAddressForm(@RequestParam("id") int employeeId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("createAddress");
		modelAndView.addObject("address", new Address());
		modelAndView.addObject("employeeId", employeeId);
		return modelAndView;
	}

	/**
	 * Fetching individual address details for update.
	 * 
	 * @param employeeId
	 * @param addressId
	 * @return modelAndView
	 */
	@GetMapping("/goToEditAddress")
	public ModelAndView getUpdateAddressPage(@RequestParam("id") int employeeId, 
			@RequestParam("addressId") int addressId) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			modelAndView.addObject("address", 
					employeeService.getIndividualAddress(employeeId, addressId));
			modelAndView.setViewName("addressForm");
			modelAndView.addObject("employeeId", employeeId);
		} catch (UserDefinedException | NullPointerException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

	/**
	 * Updating an individual address of an employee.
	 * 
	 * @param address
	 * @param employeeId
	 * @return modelAndView
	 */
	@PostMapping("/updateAddress")
	public ModelAndView updateAddress(@ModelAttribute("address") Address address,
			@RequestParam("employeeId") int employeeId, @RequestParam("addressId") int addressId) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			employeeService.updateAddress(address, employeeId, addressId);
			modelAndView.addObject("message", "updated successfully");
			modelAndView.setViewName("message");
		} catch (UserDefinedException | NullPointerException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

	/**
	 * Adding new address to an individual employee.
	 * 
	 * @param address
	 * @param employeeId
	 * @return modelAndView
	 */
	@PostMapping(value = "/insertAddress")
	public ModelAndView saveAddress(@ModelAttribute("address") Address address,
			@RequestParam("id") int employeeId) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			employeeService.addEmployeeAddress(address, employeeId);
			modelAndView.addObject("message", "created successfully");
			modelAndView.setViewName("message");
		} catch (UserDefinedException | NullPointerException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

	/**
	 * Fetching individual address of an employee and
	 * display in view address.
	 * 
	 * @param employeeId
	 * @return modelAndView
	 */
	@GetMapping(value = "/viewAddress")
	public ModelAndView getIndividualAddress(@RequestParam("id") int employeeId) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			List<Address> addresses = employeeService.getEmployeeAddress(employeeId);
			
			if (!(addresses.isEmpty())) {
				modelAndView.addObject("address", addresses);
				modelAndView.addObject("employeeId", employeeId);
				modelAndView.setViewName("viewAddress");
			} else {
				modelAndView.addObject("message",
						"This employee doesn't have any address");
				modelAndView.setViewName("error");
			}		
		} catch (UserDefinedException | NullPointerException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;		
	}

	/**
	 * Deleting an individual address of an employee.
	 * 
	 * @param addressId
	 * @param employeeId
	 * @return modelAndView
	 */
	@RequestMapping(value = "/deleteAddress", method = RequestMethod.GET)
	public ModelAndView deleteAddress(@RequestParam("addressId") int addressId, 
			@RequestParam("employeeId") int employeeId)  {
		ModelAndView modelAndView = new ModelAndView();

		try {
			employeeService.deleteAddress(employeeId, addressId);
			modelAndView.addObject("message", "deleted successfully");
			modelAndView.setViewName("message");
		} catch (UserDefinedException | NullPointerException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

	/**
	 * Fetching unassigned projects on an employee.
	 * 
	 * @param employeeId
	 * @return modelAndView
	 */
	@GetMapping(value = "/displayUnassignedProjects")
	public ModelAndView getUnassignedProjects(@RequestParam("id") int employeeId) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			modelAndView.addObject("employeeProject",
					employeeService.getUnassignedProjectsForDisplay(employeeId));
			modelAndView.setViewName("assignEmployee");
		} catch (UserDefinedException | NullPointerException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

	/**
	 * Assigning projects to an employee.
	 * 
	 * @param projectId
	 * @param employeeId
	 * @return modelAndView
	 */
	@PostMapping(value = "/assignProject")
	public ModelAndView assignProject(@RequestParam ("selected") String[] projectId, 
			@RequestParam("employeeId") int employeeId) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			List<Integer> projectIdList = new ArrayList<Integer>();

			for (int index = 0; index < projectId.length; index++) {
				projectIdList.add(Integer.parseInt(projectId[index]));
			}
			employeeService.assignProject(employeeId, projectIdList);
			modelAndView.addObject("message", "assigned successfully");
			modelAndView.setViewName("message");
		} catch (UserDefinedException | NullPointerException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

	/**
	 * Unassigning projects to an employee.
	 * 
	 * @param projectId
	 * @param employeeId
	 * @return modelAndView
	 */
	@PostMapping(value = "/unassign")
	public ModelAndView unassignProject(@RequestParam("selected") String[] projectId,
			@RequestParam("employeeId") int employeeId) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			List<Integer> projectIdList = new ArrayList<Integer>();

			for (int index = 0; index < projectId.length; index++) {
				projectIdList.add(Integer.parseInt(projectId[index]));
			}
			employeeService.unassignProject(employeeId, projectIdList);
			modelAndView.addObject("message", "unassigned successfully");
			modelAndView.setViewName("message");
		} catch (UserDefinedException | NullPointerException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

	/**
	 * Fetching individual employee data.
	 * 
	 * @param employeeId
	 * @return modelAndView
	 */
	@GetMapping(value = "/displayAssignedProject")
	public ModelAndView displayAssignedProject(@RequestParam("id") int employeeId) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			Employee employee = employeeService.getIndividualEmployee(employeeId);
			
			if (employee.getProjects().isEmpty()) {
				modelAndView.addObject("message",
						"No project have been assigned for employee");
				modelAndView.setViewName("error");				
			} else {
				modelAndView.addObject("assignEmployee", employee);
				modelAndView.setViewName("assignedEmployee");
			}		
		} catch (UserDefinedException | NullPointerException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}
	
	/**
	 * We fetch individual employee data from service layer and pass 
	 * to employeeForm
	 * 
	 * @param employeeId
	 * @return modelAndView
	 */
	@GetMapping("/goToEditEmployee")
	public ModelAndView getUpdateEmployeeForm(@RequestParam("id") int employeeId) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			modelAndView.addObject("employee", 
					employeeService.getIndividualEmployee(employeeId));
			modelAndView.setViewName("createEmployee");
		} catch (UserDefinedException | NullPointerException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

	/**
	 * Update an individual employee details.
	 * 
	 * @param employee
	 * @return modelAndView
	 */
	@PostMapping(value = "/updateEmployee")
	public ModelAndView updateEmployee(@ModelAttribute Employee employee) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			employeeService.createOrUpdateEmployee(employee);
			modelAndView.addObject("message", "updated successfully");
			modelAndView.setViewName("message");
		} catch (UserDefinedException | NullPointerException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

	/**
	 * Call index page.
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/index")
	public String goToIndexPage() {
		return "index";
	}

	/**
	 * This is main page of employee management
	 * 
	 * @return String
	 */
	@GetMapping("/")
	public String getIndexPage() {
		return "index";
	}
}