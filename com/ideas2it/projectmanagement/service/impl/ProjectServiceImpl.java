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
    public void createProject(String projectName, 
            Date startDate, Date endDate, String manager) {
        Project project = new Project(projectName,
                startDate, endDate, manager);
        projectDao.saveOrUpdateProject(project);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void deleteProject(int projectId) {
        Project project = projectDao.getIndividualProject(projectId);
        List<Employee> employee = project.getEmployees();
        employee.clear();
        project.setProjectId(projectId);
        project.setIsDeleted(true);
        project.setEmployees(employee);
        projectDao.saveOrUpdateProject(project);    
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void restoreProject(int projectId) {
        Project project = projectDao.getIndividualProject(projectId);
        project.setProjectId(projectId);
        project.setIsDeleted(false);
        projectDao.saveOrUpdateProject(project);    
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean checkDeletedId(int projectId) {
        int project = projectDao.checkDeletedId(projectId);      
        return (project == 0) ? false : true;
    }          

    /**
     * {@inheritdoc}
     */
    @Override
    public List<String> getIndividualProject(int projectId) {
        Project project = projectDao.getIndividualProject(projectId);        
        List<Employee> employee = project.getEmployees();
        List<String> projectDetails = new ArrayList<String>();
        projectDetails.add(project.toString());

        for (Employee employeeValues : employee) {
            projectDetails.add(employeeValues.toString());
        }
        return projectDetails;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<String> getProject() {
        List<String> projectValues = new ArrayList<String>();
        List<Project> projectDetails = projectDao.getProjects(); 
        
        for (Project project : projectDetails) {
            projectValues.add(project.toString());
        }  
        return projectValues;      
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void updateProject(int projectId, String projectName,
            Date startDate, Date endDate, String manager) {
        Project project = projectDao.getIndividualProject(projectId);       
        project.setProjectId(projectId);
        project.setProjectName(projectName);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        project.setManager(manager);
        projectDao.saveOrUpdateProject(project);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void updateName(int projectId, String projectName) {
        Project project = projectDao.getIndividualProject(projectId);
        project.setProjectId(projectId);
        project.setProjectName(projectName);
        projectDao.saveOrUpdateProject(project);
    }   

    /**
     * {@inheritdoc}
     */
    @Override
    public void updateStartDate(int projectId, Date startDate) {
        Project project = projectDao.getIndividualProject(projectId);    
        project.setProjectId(projectId);
        project.setStartDate(startDate);
        projectDao.saveOrUpdateProject(project);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void updateEndDate(int projectId, Date endDate) {
        Project project = projectDao.getIndividualProject(projectId);
        project.setProjectId(projectId);
        project.setEndDate(endDate);
        projectDao.saveOrUpdateProject(project);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void updateManager(int projectId, String manager) {
        Project project = projectDao.getIndividualProject(projectId);
        project.setProjectId(projectId);   
        project.setManager(manager);
        projectDao.saveOrUpdateProject(project);
    }
 
    /**
     * {@inheritdoc}
     */
    @Override
    public boolean checkId(int projectId) {        
        int project = projectDao.checkId(projectId);      
        return (project == 0) ? false : true;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<List<Integer>> checkIdForAssignAndUnassign(int projectId,
            List<Integer> employeeIdList) {
        Project project = projectDao.getIndividualProject(projectId);
        List<Employee> employeeList = project.getEmployees();
        List<Integer> employeeIdValues = new ArrayList<Integer>();
        List<Integer> assignedValues = new ArrayList<Integer>();
        List<Integer> unassignedValues = new ArrayList<Integer>();
        List<List<Integer>> employeeId = new ArrayList<List<Integer>>();
        
        if (employeeList.isEmpty()) {
            unassignedValues = employeeIdList;
        } else {
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
       }
       employeeId.add(assignedValues);
       employeeId.add(unassignedValues);
       return employeeId;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Project> getProjectValues(List<Integer> projectId) {
        List<Project> project = projectDao.getProjects();
        List<Project> projectData = new ArrayList<Project>();          
        
        for (Project projectDetails : project) {
        
            if (projectId.contains(projectDetails.getProjectId())) {
                projectData.add(projectDetails);
            }
        }
        return projectData;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void assignEmployee(int projectId, List<Integer> employeeList) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        Project project = projectDao.getIndividualProject(projectId);        
        List<Employee> employee = 
                employeeService.getEmployeeValues(employeeList);
        List<Employee> assignedEmployee = project.getEmployees();
        assignedEmployee.addAll(employee);
        project.setProjectId(projectId);
        project.setEmployees(assignedEmployee);
        projectDao.saveOrUpdateProject(project);
    }

    /**
     * {@inheritdoc} 
     */
    @Override
    public void unassignEmployee(int projectId, List<Integer> employeeList) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        Project project = projectDao.getIndividualProject(projectId);
        List<Employee> assignedEmployee = project.getEmployees();
        List<Employee> employee = 
                employeeService.getEmployeeValues(employeeList);

        for (int index = 0; index < employee.size(); index++) {

            for (int indexValues = 0; indexValues < assignedEmployee.size(); indexValues++) {

                if ((employee.get(index)).getId() == (assignedEmployee.get(indexValues).getId())) {
                    assignedEmployee.remove(indexValues);   
                }
            }
        }
        project.setProjectId(projectId);
        project.setEmployees(assignedEmployee);
        projectDao.saveOrUpdateProject(project);    
    }

    /**
     * {@inheritdoc}
     */
    @Override    
    public List<String> getAssignedEmployee(int projectId) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        Project project = projectDao.getIndividualProject(projectId);
        List<Employee> employeeData = project.getEmployees();
        List<String> employees = new ArrayList<String>(); 
        
        employees.add(project.toString());        

        for (Employee employee : employeeData) {
            employees.add(employee.toString());   
        }
        return employees;
    }
}