package com.ideas2it.employeemanagement.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.dao.impl.EmployeeDaoImpl;
import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImpl;
import com.ideas2it.exception.UserDefinedException;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.projectmanagement.dao.impl.ProjectDaoImpl;
import com.ideas2it.projectmanagement.model.Project;
import com.ideas2it.projectmanagement.service.ProjectService;
import com.ideas2it.projectmanagement.service.impl.ProjectServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeeController
 */
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeService employeeService = new EmployeeServiceImpl();
	private ProjectService projectService = new ProjectServiceImpl();
	private CustomLogger log = new CustomLogger(EmployeeDaoImpl.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action= request.getParameter("action");
		
		switch (action) {
		    case "index": 
			    index(request, response);
			    break;
		    case "employeeMainPage": 
			    employeeMainPage(request, response);
			    break;
		    case "employeeForm": 
			    employeeForm(request, response);
			    break;
		    case "addressForm":	
			    addressForm(request, response);
			    break;
		    case "goToEditEmployee":
			    goToEditEmployee(request, response);
			    break;
			default : 
			    doGetOptions(request, response, action);
			    break;
		     }
		
		
	}

	/**
	 * @param action 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	private void doGetOptions(HttpServletRequest request,
			HttpServletResponse response, String action) 
					throws ServletException, IOException { 
		switch (action) {
		    case "deleteEmployee": 
			    deleteEmployee(request, response);
			    break;
		    case "deleteAddress": 
			    deleteAddress(request, response);                  
			    break;
		    case "goToRestore":	
			    goToRestore(request, response);
			    break;
		    case "restoreEmployee": 
			    restoreEmployee(request, response);
			    break;
		    case "goToAssignedEmployee": 
			    goToAssignedEmployee(request, response);
			    break;
		    case "displayProject": 
			    displayProject(request, response);
			    break;
		    case "goToEditAddress":	
			    goToEditAddress(request, response);
			    break;
		    case "viewAddress":	
			    viewAddress(request, response);
			    break;	
		}
	}

	/**
	 * Here we redirect index
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void index(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("index.jsp").forward(request, response);		
	}

	/**
	 * Here we display all projects for assign
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void displayProject(HttpServletRequest request,
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));	
			List<Project> projects = employeeService.displayProjects();
			List<String> employeeProject = new ArrayList<String>();
			Employee employee = employeeService.getIndividualEmployee(id);
			employeeProject.add(employee.toString());
			
			for(Project values : projects) {
				employeeProject.add(values.toString());
			}
			request.setAttribute("employeeProject", employeeProject);
			request.getRequestDispatcher("assignEmployee.jsp").forward(request,
					response);
		} catch (NumberFormatException e) {
			request.getRequestDispatcher("error.jsp").forward(request,
					response);
			log.error("Something went wrong", e);
		} catch (UserDefinedException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * Here we pass values and redirect to assignedEmployee
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToAssignedEmployee(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("assignEmployee", 
					employeeService.getIndividualEmployee(id));
			request.getRequestDispatcher("assignedEmployee.jsp").forward(request,
					response);
		} catch (NumberFormatException e) {
			request.getRequestDispatcher("error.jsp").forward(request,
					response);
			log.error("Something went wrong", e);
		} catch (UserDefinedException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * Here we delete address of an employee
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void deleteAddress(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		try {
			int id = Integer.parseInt(request.getParameter("employeeId"));
			int addressId = Integer.parseInt(request.getParameter("addressId"));
			employeeService.deleteAddress(id, addressId);
			employeeMainPage(request, response);	
		} catch (NumberFormatException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			log.error("Something went wrong", e);
		} catch (UserDefinedException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * Here we view address details of an employee
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void viewAddress(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		try {	
			int id = Integer.parseInt(request.getParameter("id"));
			Employee employee = employeeService.getIndividualEmployee(id);
			List<Address> addresses = new ArrayList<Address>();
			for (Address addressValue : employee.getAddresses()) {
				if (false == addressValue.getIsDeleted()) {
					addresses.add(addressValue);
				}
			}
			request.setAttribute("address", addresses);
			request.getRequestDispatcher("viewAddress.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			log.error("Something went wrong", e);
		} catch (UserDefinedException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * Here we edit details of address for an employee
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToEditAddress(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			int addressId = Integer.parseInt(request.getParameter("addressId"));
			Employee employee = employeeService.getIndividualEmployee(id);
			List<Address> addresses = employee.getAddresses();
			List<Address> addressList = new ArrayList<Address>();
			
			for (Address addressValue:addresses) {
				
				if(addressId == addressValue.getAddressId()) {
					addressList.add(addressValue);
				}
			}
			request.setAttribute("address", addressList);
			request.getRequestDispatcher("addressForm.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			log.error("Something went wrong", e);
		} catch (UserDefinedException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * Here we redirect addressForm
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addressForm(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("employee", id);				
			request.getRequestDispatcher("addressForm.jsp").forward(request,
					response);
		} catch (NumberFormatException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			log.error("Something went wrong", e);
		} 
	}

	/**
	 * Here we edit details of an employee
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToEditEmployee(HttpServletRequest request, 
			HttpServletResponse response)
					throws ServletException, IOException {
		
		try {	
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("employee", 
					employeeService.getIndividualEmployee(id));
			request.getRequestDispatcher("employeeForm.jsp").forward(request,
					response);
		} catch (NumberFormatException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			log.error("Something went wrong", e);
		} catch (UserDefinedException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * Here we restore deleted employee
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException 
	 */
	private void restoreEmployee(HttpServletRequest request, 
			HttpServletResponse response)
					throws ServletException, IOException {
		
		try {			
			int id = Integer.parseInt(request.getParameter("id"));
			employeeService.restoreEmployee(id);
			request.setAttribute("message", "Successfully restored");
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			log.error("Something went wrong", e);
		} catch (UserDefinedException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * Here we redirect employeeRestore
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToRestore(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("employeeRestore.jsp");		
	}

	/**
	 * Here we delete an employee
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException 
	 */
	private void deleteEmployee(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		try {		
			int id = Integer.parseInt(request.getParameter("id"));
			employeeService.deleteEmployee(id);
			request.setAttribute("message", "Successfully deleted");
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			log.error("Something went wrong", e);
		} catch (UserDefinedException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * Here we pass values and redirect to employeeMainPage
	 * @param request
	 * @param response
	 */
	private void employeeMainPage(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		try {
			request.setAttribute("employeeList", employeeService.getAllEmployee());
		    request.getRequestDispatcher("employeeMainPage.jsp").forward(request,
				    response);
		} catch (UserDefinedException e) {
			log.error("Something went wrong", e);
		}
	}

	/**
	 * Here we redirect employeeForm
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void employeeForm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("employeeForm.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {		
	
        String action = request.getParameter("action");
		switch (action) {
	        case "insert": 
				insertEmployee(request, response);
				break;
			case "update": 
				updateEmployee(request, response);
				break;
			case "insertAddress": 
				insertAddress(request, response);
				break;
			case "updateAddress": 
				updateAddress(request, response);
				break;
			case "assign":
				assign(request, response);
				break;
			case "unassign" : 
				unassign(request, response);
				break;
		}
		
	}
	/**
	 * Here we update address details of an employee
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException 
	 */
	private void updateAddress(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {	
			int id = Integer.parseInt(request.getParameter("id"));
			int addressId = Integer.parseInt(request.getParameter("addressId"));
			String doorNumber = request.getParameter("doorNumber");
			String streetName = request.getParameter("streetName");
			String district = request.getParameter("district");
			String state = request.getParameter("state");
			String country = request.getParameter("country");
			int pinCode = Integer.parseInt(request.getParameter("pinCode"));
			employeeService.updateAddress(id, addressId, doorNumber, 
					streetName, district, state, country, pinCode);
			employeeMainPage(request, response);
		} catch (NumberFormatException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			log.error("Something went wrong", e);
		} catch (UserDefinedException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}

	}

	/**
	 * Here we insert address for an employee
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void insertAddress(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {	
			int id = Integer.parseInt(request.getParameter("id"));
			String doorNumber = request.getParameter("doorNumber");
			String streetName = request.getParameter("streetName");
			String district = request.getParameter("district");
			String state = request.getParameter("state");
			String country = request.getParameter("country");
			int pinCode = Integer.parseInt(request.getParameter("pinCode"));
			employeeService.addEmployeeAddress(id, doorNumber,
					streetName, district, state, country, pinCode);
			viewAddress(request, response);
		} catch (NumberFormatException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			log.error("Something went wrong", e);
		} catch (UserDefinedException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}

	}

	/**
	 * Here we update details of employee 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateEmployee(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {	
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			double salary = Double.parseDouble(request.getParameter("salary"));
			Date dateOfBirth = Date.valueOf(request.getParameter("dateOfBirth"));
			String mobileNumber = request.getParameter("mobileNumber");
			String emailId = request.getParameter("emailId");  
			employeeService.updateEmployee(id, name, salary, 
					dateOfBirth, mobileNumber, emailId);
			request.setAttribute("message", "Successfully updated");
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			log.error("Something went wrong", e);
		} catch (UserDefinedException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * Here we insert an employee
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void insertEmployee(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String name = request.getParameter("name");
			double salary = Double.parseDouble(request.getParameter("salary"));
			Date dateOfBirth = Date.valueOf(request.getParameter("dateOfBirth"));
			String mobileNumber = request.getParameter("mobileNumber");
			String emailId = request.getParameter("emailId");
			employeeService.createEmployee(name, salary,
					dateOfBirth, mobileNumber, emailId);
			request.setAttribute("message", "Successfully inserted");
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			log.error("Something went wrong", e);		
		} catch (UserDefinedException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} 
	}

	/**
	 * Here we assign employee to project
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void assign(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int employeeId = Integer.parseInt(request.getParameter("id"));
			List<Integer> projectIdList = new ArrayList<Integer>();
			String[] values = request.getParameterValues("selected");
			
			for (int index = 0; index < values.length; index++) {
				projectIdList.add(Integer.parseInt(values[index]));
			}
			List<Integer> employeeList 
			        = employeeService.checkIdForAssignAndUnassign(employeeId, projectIdList, "assign");
			employeeService.assignProject(employeeId, employeeList);
			request.setAttribute("assignEmployee", 
					employeeService.getIndividualEmployee(employeeId));
			request.getRequestDispatcher("assignedEmployee.jsp").forward(request,
					response);
		} catch ( NullPointerException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			log.error("Something went wrong", e);
		} catch (UserDefinedException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}

	/**
	 * Here we unassign employee to project
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException 
	 */
	private void unassign(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int employeeId = Integer.parseInt(request.getParameter("id"));
			List<Integer> projectIdList = new ArrayList<Integer>();
			String[] values = request.getParameterValues("selected");
			
			for (int index = 0; index < values.length; index++) {
				projectIdList.add(Integer.parseInt(values[index]));
			}
			List<Integer> employeeList = employeeService
					.checkIdForAssignAndUnassign(employeeId, projectIdList, "unassign");
			employeeService.unassignProject(employeeId,
					employeeList);
			request.setAttribute("message", "Successfully unassigned");
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} catch ( NullPointerException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			log.error("Something went wrong", e);
		} catch (UserDefinedException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}
