package com.ideas2it.projectmanagement.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImpl;
import com.ideas2it.projectmanagement.dao.impl.ProjectDaoImpl;
import com.ideas2it.projectmanagement.dao.ProjectDao;
import com.ideas2it.projectmanagement.model.Project;
import com.ideas2it.projectmanagement.service.ProjectService;

/**
 * In this class we perform crud operations(create,read,update,
 * delete,assign,unassign)
 */
public class ProjectServiceImpl implements ProjectService {
    private ProjectDao projectDao = new ProjectDaoImpl();
   
    /**
     * {@inheritdoc}
     */
    @Override
    public void createProject(int id, String name, 
            Date startDate, Date endDate, String manager) {
        Project project = new Project(id, name,
                startDate, endDate, manager);
        projectDao.createProject(project);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Project> getProjectValues(List<Integer> projectId) {
        List<Project> project = projectDao.getProject();
        List<Project> projectData = new ArrayList<Project>();          
        
        for (Project projectDetails : project) {
        
            if (projectId.contains(projectDetails.getId())) {
                projectData.add(projectDetails);
            }
        }
        return projectData;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void assignEmployee(int id, List<Integer> employeeList) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        Project project = projectDao.getEmployeeProject(id);
        List<Employee> employee = 
                employeeService.getEmployeeValues(employeeList);
        project.setId(id);
        project.setEmployees(employee);
        projectDao.assignEmployee(project);
    }

    /**
     * {@inheritdoc} 
     */
    @Override
    public void unassignEmployee(int id, List<Integer> employeeList) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        Project project = projectDao.getEmployeeProject(id);
        List<Employee> employee = 
                employeeService.getEmployeeValues(employeeList);
        project.setId(id);
        project.setEmployees(employee);
        projectDao.unassignEmployee(project);    
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<List<Integer>> checkIdForAssignAndUnassign(int id,
            List<Integer> employeeIdList) {
        Project project = projectDao.getEmployeeProject(id);
        List<Employee> employeeList = project.getEmployees();
        List<Integer> employeeIdValues = new ArrayList<Integer>();
        List<Integer> assignedValues = new ArrayList<Integer>();
        List<Integer> unassignedValues = new ArrayList<Integer>();
        List<List<Integer>> employeeId = new ArrayList<List<Integer>>();
        
        for (Employee employee : employeeList) {
            employeeIdValues.add(employee.getId());
        }
        
        for (Integer employeeValue : employeeIdList) {

            for (Integer employee : employeeIdValues) {

                if (employeeValue == employee) {
                    assignedValues.add(employeeValue); 
                } else {
                    unassignedValues.add(employeeValue);
                }      
            }
       }
       employeeId.add(assignedValues);
       employeeId.add(unassignedValues);
       return employeeId;
    }

    /**
     * {@inheritdoc}
     */
    @Override    
    public List<String> getAssignedEmployee(int id) {
        Project project = projectDao.getEmployeeProject(id);
        List<Employee> employeeData = project.getEmployees();
        List<String> employees = new ArrayList<String>(); 
        employees.add(project.toString());        

        for (Employee employee : employeeData) {
            employees.add(employee.toString());   
        }
        return employees;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void deleteProject(int id) {
        projectDao.deleteProject(id);    
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void updateProject(int id, String name,
            Date startDate, Date endDate, String manager) {
        Project project = projectDao.getProject(id);
        project.setId(id);        
        project.setName(name);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        project.setManager(manager);
        projectDao.updateProject(project);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void updateName(int id, String name) {
        Project project = projectDao.getProject(id);
        project.setId(id);
        project.setName(name);
        project.setStartDate(project.getStartDate());
        project.setEndDate(project.getEndDate());
        project.setManager(project.getManager());
        projectDao.updateProject(project);
    }   

    /**
     * {@inheritdoc}
     */
    @Override
    public void updateStartDate(int id, Date startDate) {
        Project project = projectDao.getProject(id);    
        project.setId(id);
        project.setName(project.getName());
        project.setStartDate(startDate);
        project.setEndDate(project.getEndDate());
        project.setManager(project.getManager());
        projectDao.updateProject(project);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void updateEndDate(int id, Date endDate) {
        Project project = projectDao.getProject(id);
        project.setId(id);
        project.setName(project.getName());
        project.setStartDate(project.getStartDate());
        project.setEndDate(endDate);
        project.setManager(project.getManager());
        projectDao.updateProject(project);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void updateManager(int id, String manager) {
        Project project = projectDao.getProject(id);
        project.setId(id);
        project.setName(project.getName());
        project.setStartDate(project.getStartDate());
        project.setEndDate(project.getEndDate());
        project.setManager(manager);
        projectDao.updateProject(project);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public String getIndividualProject(int id) {
        return projectDao.getIndividualProject(id).toString();
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<String> getProject() {
        List<String> projectValues = new ArrayList<String>();
        List<Project> projectDetails = projectDao.getProject(); 
        
        for (Project project : projectDetails) {
            projectValues.add(project.toString());
        }  
        return projectValues;      
    }
}