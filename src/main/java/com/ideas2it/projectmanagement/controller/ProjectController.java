package com.ideas2it.projectmanagement.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImpl;
import com.ideas2it.exception.UserDefinedException;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.projectmanagement.dao.impl.ProjectDaoImpl;
import com.ideas2it.projectmanagement.model.Project;
import com.ideas2it.projectmanagement.service.ProjectService;
import com.ideas2it.projectmanagement.service.impl.ProjectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Servlet implementation class ProjectController
 */
public class ProjectController extends HttpServlet {
	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
	ProjectService projectService = applicationContext.getBean("projectService", ProjectService.class);
	private CustomLogger log = new CustomLogger(ProjectDaoImpl.class);


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		String action = request.getParameter("action");
		
		switch (action) {
			case "projectMainPage": 
			    projectMainPage(request, response);
				break;
			case "projectForm": 
				projectForm(request, response);
				break;        		
			case "goToEditProject": 
				goToEditProject(request, response);
				break;		
			case "displayProject": 
				displayProject(request, response);
				break;		
			default: 
				doGetOptions(request, response, action);
				break;
			}
	}

	/**
	 * @param action 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGetOptions(HttpServletRequest request, 
			HttpServletResponse response, String action) 
					throws ServletException, IOException {
		
		switch (action) {
		    case "deleteProject": 
		    	deleteProject(request, response);
		        break;
		    case "goToRestore":
		    	goToRestore(request, response);
		        break;
		    case "restoreProject": 
		    	restoreProject(request, response);
		        break;
		    case "goToAssignedProject":
		    	goToAssignedProject(request, response);
		        break;
		}
	}

	/**
	 * Here we redirect assigned projects values to assignedProject
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToAssignedProject(HttpServletRequest request,
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		try {
			int projectId = Integer.parseInt(request.getParameter("projectId"));
			request.setAttribute("project",
					projectService.getIndividualProject(projectId));
			request.getRequestDispatcher("assignedProject.jsp").forward(request, response);	
			projectMainPage(request, response);
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
	 * Here we display all project for assigning
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void displayProject(HttpServletRequest request,
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		try {
			int projectId = Integer.parseInt(request.getParameter("projectId"));
			List<Employee> employees = projectService.displayEmployees();
			List<String> employeeProject = new ArrayList<String>();
			Project project = projectService.getIndividualProject(projectId);
			employeeProject.add(project.toString());
			
			for(Employee values : employees) {
				employeeProject.add(values.toString());
			}
			request.setAttribute("employeeProject", employeeProject);
			request.getRequestDispatcher("assignProject.jsp").forward(request, response);
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
	 * Here we pass project values for update and redirect to projectForm
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToEditProject(HttpServletRequest request,
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		try {
			int projectId = Integer.parseInt(request.getParameter("projectId"));
			request.setAttribute("project", 
					projectService.getIndividualProject(projectId));
			request.getRequestDispatcher("projectForm.jsp").forward(request, response);			
		} catch (NumberFormatException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			log.error("Something went wrong", e);
		} catch (UserDefinedException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * Here we redirect the project to restored project
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void goToRestore(HttpServletRequest request,
			HttpServletResponse response) 
					throws ServletException, IOException {
		response.sendRedirect("restoreProject.jsp");		
	}

	/**
	 * Here we restore deleted project
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException 
	 */
	private void restoreProject(HttpServletRequest request,
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		try {
			int projectId = Integer.parseInt(request.getParameter("projectId"));
			projectService.restoreProject(projectId);
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
	 * Here we delete an project
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void deleteProject(HttpServletRequest request,
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		try {
			int id = Integer.parseInt(request.getParameter("projectId"));		
			projectService.deleteProject(id);
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
	 * Here we pass project values and redirect it to projectMainPage
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void projectMainPage(HttpServletRequest request,
			HttpServletResponse response) 
					throws ServletException, IOException {
		try {
			request.setAttribute("projectList",
					projectService.getProject());
		    request.getRequestDispatcher("projectMainPage.jsp").forward(request, response);
		} catch (UserDefinedException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * Here we pass project values and redirect it to projectForm
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void projectForm(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		request.getRequestDispatcher("projectForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
	    String action = request.getParameter("action");
			
		switch (action) {
		    case "insert": 
		        insertProject(request, response);
			    break;
			case "update": 
			    updateProject(request, response);
			    break;
			case "assign":
			    assign(request, response);
			    break;
			case "unassign":
			    unassign(request, response);
			    break;
			} 
	}

	/**
	 * Here we unassign project that was assigned
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void unassign(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		try {
			int projectId = Integer.parseInt(request.getParameter("projectId"));
			List<Integer> employeeIdList = new ArrayList<Integer>();
			String[] values = request.getParameterValues("selected");
			
			for (int index = 0; index < values.length; index++) {
				employeeIdList.add(Integer.parseInt(values[index]));
			}
			List<Integer> unassignProject = projectService
					.checkIdForAssignAndUnassign(projectId, employeeIdList, "unassign");
			projectService.unassignEmployee(projectId, unassignProject);
			request.setAttribute("message", "Successfully unassigned");
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} catch (NullPointerException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			log.error("Something went wrong", e);
		} catch (UserDefinedException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * Here we assign project to employee
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void assign(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		try {
			int projectId = Integer.parseInt(request.getParameter("id"));
			List<Integer> employeeIdList = new ArrayList<Integer>();
			String[] values = request.getParameterValues("selected");
			
			for (int index = 0; index < values.length; index++) {
				employeeIdList.add(Integer.parseInt(values[index]));
			}
			List<Integer> assignProject = projectService.checkIdForAssignAndUnassign(projectId,
					employeeIdList, "assign");
			projectService.assignEmployee(projectId, assignProject);
			request.setAttribute("project",
					projectService.getIndividualProject(projectId));
			request.getRequestDispatcher("assignedProject.jsp").forward(request, response);
		} catch (NullPointerException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			log.error("Something went wrong", e);
		} catch (UserDefinedException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * Here we update details of project
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException 
	 */
	private void updateProject(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		try {
			int projectId = Integer.parseInt(request.getParameter("projectId"));
			String projectName = request.getParameter("projectName"); 
			Date startDate = Date.valueOf(request.getParameter("startDate"));
			Date endDate = Date.valueOf(request.getParameter("endDate"));
			String manager = request.getParameter("manager");
			projectService.updateProject(projectId,
					projectName, startDate, endDate, manager);
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
	 * Here we insert details of project 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void insertProject(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		try {
			String projectName = request.getParameter("projectName"); 
			Date startDate = Date.valueOf(request.getParameter("startDate"));
			Date endDate = Date.valueOf(request.getParameter("endDate"));
			String manager = request.getParameter("manager");
			projectService.createProject(projectName,
					startDate, endDate, manager);
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
}
