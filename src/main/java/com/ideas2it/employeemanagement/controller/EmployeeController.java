package com.ideas2it.employeemanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 * @author Dhivyaa Eswaran
 */
@Controller
public class EmployeeController {
	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
	EmployeeService employeeService = applicationContext.getBean("employeeService", EmployeeService.class);
    
	/**
	 * Fetching all employee details and pass to employeeMainPage.
	 * 
	 * @return modelAndView
	 */
	@GetMapping(value = "/employeeMainPage")
	private ModelAndView displayEmployee() {
		ModelAndView modelAndView = new ModelAndView();
		
		try {			
			modelAndView.addObject("employeeList",
					employeeService.getAllEmployee());
			modelAndView.setViewName("employeeMainPage");
		} catch (UserDefinedException e) {
			modelAndView.addObject("message" , e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

	/**
	 * Call employee create form.
	 * 
	 * @param model
	 * @return String
	 */
	@GetMapping("/employeeForm")
	public String getCreateEmployeePage(Model model) {
		model.addAttribute("employee", new Employee());
		return "createEmployee";
	}

	/**
	 * Create the employee details by updating
	 * values to service layer.
	 * 
	 * @param employee
	 * @return modelAndView
	 */
	@PostMapping(value = "/saveOrUpdateEmployee")
	public ModelAndView createEmployee(@ModelAttribute Employee employee) { 
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			employeeService.createEmployee(employee);	
			modelAndView.addObject("message" , "created successfully");
			modelAndView.setViewName("error");
		} catch (UserDefinedException e) {
			modelAndView.addObject("message" , e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;		
	}

	/**
	 * We fetch individual employee data from service layer and pass 
	 * to employeeForm
	 * 
	 * @param model
	 * @param id
	 * @return String
	 */
	@GetMapping("/goToEditEmployee")
	public String getUpdateEmployeePage(Model model,
			@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			model.addAttribute("employee", 
					employeeService.getIndividualEmployee(id));
		} catch (UserDefinedException e) {
			modelAndView.addObject("message" , e.getMessage());
			modelAndView.setViewName("error");
		}
		return "employeeForm";
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
			employeeService.updateEmployee(employee);
			modelAndView.addObject("message" , "updated successfully");
			modelAndView.setViewName("error");
		} catch (UserDefinedException e) {
			modelAndView.addObject("message" , e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

	/**
	 * Fetching individual address details for update.
	 * 
	 * @param model
	 * @param id
	 * @param addressId
	 * @return String
	 */
	@GetMapping("/goToEditAddress")
	public String getUpdateAddressPage(Model model,
			@RequestParam("id") int id, 
			@RequestParam("addressId") int addressId) {
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			model.addAttribute("address", 
					employeeService.getIndividualAddress(id, addressId));
		} catch (UserDefinedException e) {
			modelAndView.addObject("message" , e.getMessage());
			modelAndView.setViewName("error");
		}
		return "addressForm";
	}

	/**
	 * Updating an individual address of an employee.
	 * 
	 * @param address
	 * @param id
	 * @return modelAndView
	 */
	@PostMapping("/updateAddress")
	public ModelAndView updateAddress(@ModelAttribute Address address,
			@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			employeeService.updateAddress(address, id);
			modelAndView.addObject("message" , "updated successfully");
			modelAndView.setViewName("error");
		} catch (UserDefinedException e) {
			modelAndView.addObject("message" , e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

	/**
	 * Adding new address to an individual employee.
	 * 
	 * @param address
	 * @param id
	 * @return modelAndView
	 */
	@PostMapping(value = "/insertAddress")
	public ModelAndView saveAddress(@ModelAttribute("address") Address address,
			@RequestParam("id") int id) {
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			employeeService.addEmployeeAddress(address, id);
			modelAndView.addObject("message" , "created successfully");
			modelAndView.setViewName("error");
		} catch (UserDefinedException e) {
			modelAndView.addObject("message" , e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

	/**
	 * Call address create form
	 * @param model
	 * @param id
	 * @return String
	 */
	@GetMapping("/addressForm")
	public ModelAndView getCreateAddressPage(@RequestParam int id) {
		
		System.out.println("hello" + id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/createAddress");
		modelAndView.addObject("address", new Address());
		modelAndView.addObject("id", id);
		return modelAndView;
	}

	/**
	 * Deleting an employee data.
	 * 
	 * @param id
	 * @return modelAndView
	 */
	@PostMapping(value = "/deleteEmployee")
	public ModelAndView deleteEmployee(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			employeeService.deleteEmployee(id);
			modelAndView.addObject("message" , "deleted successfully");
			modelAndView.setViewName("error");
		} catch (UserDefinedException e) {
			modelAndView.addObject("message" , e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

	/**
	 * Restoring an employee details.
	 * 
	 * @param id
	 * @return modelAndView
	 */
	@GetMapping(value = "/restore")
	public ModelAndView goToRestoreEmployee(@RequestParam ("id") int id) {
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			employeeService.restoreEmployee(id);
			modelAndView.addObject("message", "restored successfully");
			modelAndView.setViewName("error");
		} catch (UserDefinedException e) {
			modelAndView.addObject("message" , e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

	/**
	 * Call restore employee form.
	 * 
	 * @param model
	 * @return String
	 */
	@PostMapping(value = "/goToRestore")
	public String restoreEmployee(Model model) {
		model.addAttribute("employeeRestore", new Employee());
		return "employeeRestore";
	}

	/**
	 * Fetching individual address of an employee and
	 * display in view address.
	 * 
	 * @param id
	 * @return modelAndView
	 */
	@GetMapping(value = "/viewAddress")
	public ModelAndView goToViewAddress(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			modelAndView.addObject("address",
					employeeService.getIndividualEmployee(id)
					.getAddresses());
			modelAndView.setViewName("viewAddress");
		} catch (UserDefinedException e) {
			modelAndView.addObject("message" , e.getMessage());
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
			modelAndView.addObject("message" , "deleted successfully");
			modelAndView.setViewName("error");
		} catch (UserDefinedException e) {
			modelAndView.addObject("message" , e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

	/**
	 * Fetching unassigned projects on an employee.
	 * 
	 * @param id
	 * @return modelAndView
	 * 
	 */
	@GetMapping(value = "/displayProject")
	public ModelAndView goToAssignEmployee(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			modelAndView.addObject("employeeProject",
					employeeService.getProjectsForDisplay(id));
			modelAndView.setViewName("assignEmployee");
		} catch (UserDefinedException e) {
			modelAndView.addObject("message" , e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

	/**
	 * Assigning projects to an employee.
	 * 
	 * @param id
	 * @param employeeId
	 * @return modelAndView
	 */
	@PostMapping(value = "/assignProject")
	public ModelAndView assignProject(@RequestParam ("selected") String[] id, 
			@RequestParam("employeeId") int employeeId) {
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			List<Integer> projectIdList = new ArrayList<Integer>();

			for (int index = 0; index < id.length; index++) {
				projectIdList.add(Integer.parseInt(id[index]));
			}
			employeeService.assignProject(employeeId, projectIdList);
			modelAndView.addObject("message" , "assigned successfully");
			modelAndView.setViewName("error");
		} catch (UserDefinedException e) {
			modelAndView.addObject("message" , e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

	/**
	 * Unassigning projects to an employee.
	 * 
	 * @param id
	 * @param employeeId
	 * @return modelAndView
	 */
	@PostMapping(value = "/unassign")
	public ModelAndView unassignProject(@RequestParam("selected") String[] id,
			@RequestParam("employeeId") int employeeId) {
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			List<Integer> projectIdList = new ArrayList<Integer>();

			for (int index = 0; index < id.length; index++) {
				projectIdList.add(Integer.parseInt(id[index]));
			}
			employeeService.unassignProject(employeeId, projectIdList);
			modelAndView.addObject("message" , "unassigned successfully");
			modelAndView.setViewName("error");
		} catch (UserDefinedException e) {
			modelAndView.addObject("message" , e.getMessage());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

	/**
	 * Fetching individual employee data.
	 * 
	 * @param id
	 * @return modelAndView
	 */
	@GetMapping(value = "/displayAssignedProject")
	public ModelAndView displayAssignedProject(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			modelAndView.addObject("assignEmployee",
					employeeService.getIndividualEmployee(id));
			modelAndView.setViewName("assignedEmployee");
		} catch (UserDefinedException e) {
			modelAndView.addObject("message" , e.getMessage());
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
	 * This is main page of employeemanagement
	 * 
	 * @return String
	 */
	@GetMapping("/")
	public String getIndexPage() {
		return "index";
	}
}