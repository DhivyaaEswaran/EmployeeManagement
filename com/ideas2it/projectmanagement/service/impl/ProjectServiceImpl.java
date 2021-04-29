package com.ideas2it.projectmanagement.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.model.Employee;
//import com.ideas2it.employeemanagement.service.EmployeeService;
//import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImpl;
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
        projectDao.createProject(project);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public String getIndividualProject(int projectId) {
        Project project = projectDao.getIndividualProject(projectId);        
        return project.toString();
    }

    /**
     * {@inheritdoc}
     */
    /**@Override
    public List<String> getProject() {
        List<String> projectValues = new ArrayList<String>();
        List<Project> projectDetails = projectDao.getProject(projectId); 
        
        for (Project project : projectDetails) {
            projectValues.add(project.toString());
        }  
        return projectValues;      
    }*/

    /**
     * {@inheritdoc}
     */
    @Override
    public void updateProject(int projectId, String projectName,
            Date startDate, Date endDate, String manager) {
        Project project = projectDao.getProjectForUpdate(projectId);       
        project.setProjectId(projectId);
        project.setProjectName(projectName);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        project.setManager(manager);
        projectDao.updateProject(project);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean checkId(int projectId) {        
        int project = projectDao.checkId(projectId);      
        return (project == 0) ? false : true;
    }
}