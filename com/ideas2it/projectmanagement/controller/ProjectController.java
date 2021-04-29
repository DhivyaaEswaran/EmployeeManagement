package com.ideas2it.projectmanagement.controller;

import java.sql.Date;
import java.util.List;

import com.ideas2it.projectmanagement.service.impl.ProjectServiceImpl;
import com.ideas2it.projectmanagement.service.ProjectService;
import com.ideas2it.projectmanagement.model.Project;

/**
 * Here we perform crud operations(create,read,update,delete)
 */
public class ProjectController {
    private ProjectService projectService = new ProjectServiceImpl();

    /**
     * Here we are creating values for project
     *
     * @param id - id
     * @param name - Name
     * @param startDate - state Date
     * @param endDate - end Date
     * @param manager - manager
     */
    public void createProject(String projectName, 
            Date startDate, Date endDate, String manager) {
        projectService.createProject(projectName, startDate,
                endDate, manager);
    }

    /**
     * Here we display individual details of project
     *
     * @param id - id
     * @return string
     */
    public String getIndividualProject(int projectId) {
        return projectService.getIndividualProject(projectId);
    }

    /**
     * Here we display all details of project
     *
     * @return string
     */
    /**public List<String> getProject() {
        return projectService.getProject();
    }*/

    /**
     * Here we update all the details for project
     *
     * @param id - id
     * @param name - name
     * @param startDate - startDate
     * @param endDate - endDate
     * @param manager - manager
     */
    public void updateProject(int projectId, String projectName, 
            Date startDate, Date endDate, String manager) {
        projectService.updateProject(projectId, projectName,
                startDate, endDate, manager);
    }

    /**
     * Here we check project id 
     *
     * @param projectId - projectId
     */
    public boolean checkId(int projectId) {
        return projectService.checkId(projectId);
    }
}