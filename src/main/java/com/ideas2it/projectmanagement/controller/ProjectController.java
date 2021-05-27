package com.ideas2it.projectmanagement.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImpl;
import com.ideas2it.projectmanagement.model.Project;
import com.ideas2it.projectmanagement.service.ProjectService;
import com.ideas2it.projectmanagement.service.impl.ProjectServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProjectController
 */
public class ProjectController extends HttpServlet {
	private ProjectService projectService = new ProjectServiceImpl();
	private EmployeeService employeeService = new EmployeeServiceImpl();

    /**
     * Default constructor. 
     */
    public ProjectController() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "index":
			index(request, response);
			break;
		case "projectMainPage":
			projectMainPage(request, response);
			break;
		case "projectForm":
			projectForm(request, response);
			break;
		case "deleteProject":
			deleteProject(request, response);
			break;
		case "restore":
			restore(request, response);
			break;
		case "restoreProject":
			restoreProject(request, response);
			break;
		case "editProject":
			editProject(request, response);
			break;		
		case "displayProject":
			displayProject(request, response);
			break;
		case "assignedProject":
			assignedProject(request, response);
			break;
		default:
			break;
		}
	}
	
	/**
	 * This is main page, redirecting to index
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
	 * Here we redirect assigned projects values to assignedProject
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void assignedProject(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int projectId = Integer.parseInt(request.getParameter("projectId"));
		request.setAttribute("project"
				, projectService.getIndividualProject(projectId));
        request.getRequestDispatcher("assignedProject.jsp")
                .forward(request, response);	
        projectMainPage(request, response);
	}

	/**
	 * Here we display all project for assigning
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void displayProject(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int projectId = Integer.parseInt(request.getParameter("projectId"));
		List<Employee> employee = employeeService.getAllEmployee();
	    List<String> employeeProject = new ArrayList<String>();
	    Project project = projectService.getIndividualProject(projectId);
	    employeeProject.add(project.toString());
	    for(Employee values : employee) {
	    	employeeProject.add(values.toString());
	    }
        request.setAttribute("employeeProject", employeeProject);
        request.getRequestDispatcher("assignProject.jsp")
                .forward(request, response);		
	}

	/**
	 * Here we pass project values for update and redirect to projectForm
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void editProject(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int projectId = Integer.parseInt(request.getParameter("projectId"));
		request.setAttribute("project", 
				projectService.getIndividualProject(projectId));
        request.getRequestDispatcher("projectForm.jsp")
                .forward(request, response);		
	}

	/**
	 * Here we redirect the project to restored project
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void restore(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
			HttpServletResponse response) throws ServletException, IOException {
	    int projectId = Integer.parseInt(request.getParameter("projectId"));
	    projectService.restoreProject(projectId);
	    projectMainPage(request, response);
	}

	/**
	 * Here we delete an project
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void deleteProject(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("projectId"));		
	    projectService.deleteProject(id);
	    projectMainPage(request, response);
	}

	/**
	 * Here we pass project values and redirect it to projectMainPage
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
    private void projectMainPage(HttpServletRequest request,
    		HttpServletResponse response) throws ServletException, IOException {
	    request.setAttribute("projectList",
	    		projectService.getProject());
	    request.getRequestDispatcher("projectMainPage.jsp")
	            .forward(request, response);
    }

    /**
     * Here we pass project values and redirect it to projectForm
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void projectForm(HttpServletRequest request, 
    		HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("projectForm.jsp")
                .forward(request, response);
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		String action= request.getParameter("action");
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
		default:
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
			HttpServletResponse response) throws ServletException, IOException {
		int projectId = Integer.parseInt(request.getParameter("projectId"));
		List<Integer> employeeId = new ArrayList<Integer>();
		String[] values = request.getParameterValues("selected");
		for (int index = 0; index < values.length; index++) {
			employeeId.add(Integer.parseInt(values[index]));
		}
		List<List<Integer>> unassignProject = projectService
				.checkIdForAssignAndUnassign(projectId, employeeId);
        projectService.unassignEmployee(projectId, unassignProject.get(1));
        projectMainPage(request, response);
		
	}

	/**
	 * Here we assign project to employee
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void assign(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		int projectId = Integer.parseInt(request.getParameter("id"));
		List<Integer> employeeId = new ArrayList<Integer>();
		String[] values = request.getParameterValues("selected");
		for (int index = 0; index < values.length; index++) {
			employeeId.add(Integer.parseInt(values[index]));
		}
		List<List<Integer>> assignProject 
		        = projectService.checkIdForAssignAndUnassign(projectId, employeeId);
        projectService.assignEmployee(projectId, assignProject.get(0));
        request.setAttribute("project",
        		projectService.getIndividualProject(projectId));
        request.getRequestDispatcher("assignedProject.jsp").forward(request, response);
	}

	/**
	 * Here we update details of project
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateProject(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		int projectId = Integer.parseInt(request.getParameter("projectId"));
		String projectName = request.getParameter("projectName"); 
		Date startDate = Date.valueOf(request.getParameter("startDate"));
		Date endDate = Date.valueOf(request.getParameter("endDate"));
		String manager = request.getParameter("manager");
		projectService.updateProject(projectId,
				projectName, startDate, endDate, manager);
		projectMainPage(request, response);
    }

	/**
	 * Here we insert details of project 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void insertProject(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		String projectName = request.getParameter("projectName"); 
		Date startDate = Date.valueOf(request.getParameter("startDate"));
		Date endDate = Date.valueOf(request.getParameter("endDate"));
		String manager = request.getParameter("manager");
		projectService.createProject(projectName,
				startDate, endDate, manager);
		projectMainPage(request, response);
	}
}
