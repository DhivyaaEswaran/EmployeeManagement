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
     * Here we delete details of project
     *
     * @param id - id
     */
    public void deleteProject(int projectId) {
        projectService.deleteProject(projectId);
    }

    /**
     * Here we retore project that was deleted
     *
     * @param id - id
     */
    public void restoreProject(int projectId) {
        projectService.restoreProject(projectId);
    }

    /**
     * Here we check id that was deleted
     *
     * @param id - id
     */
    public boolean checkDeletedId(int projectId) {
        return projectService.checkDeletedId(projectId);
    }

    /**
     * Here we display individual details of project
     *
     * @param id - id
     * @return string
     */
    public List<String> getIndividualProject(int projectId) {
        return projectService.getIndividualProject(projectId);
    }

    /**
     * Here we display all details of project
     *
     * @return string
     */
    public List<String> getProject() {
        return projectService.getProject();
    }

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
     * Here we update individual project name 
     *
     * @pram id - id
     * @param name - name
     */
    public void updateName(int id, String name) {
        projectService.updateName(id, name);
    }
 
    /**
     * Here we update individual project name
     *
     * @param id - id
     * @param startDate - startDate
     */
    public void updateStartDate(int id, Date startDate) {
        projectService.updateStartDate(id, startDate);    
    }

    /**
     * Here we update individual project end date
     *
     * @param id - id
     * @param endDate - endDate
     */
    public void updateEndDate(int id, Date endDate) {
        projectService.updateEndDate(id, endDate);
    }

    /**
     * Here we update individual project manager
     *
     * @param id - id
     * @param manager - manager
     */
    public void updateManager(int id, String manager) {
        projectService.updateManager(id, manager);
    }

    /**
     * Here we check project id 
     *
     * @param projectId - projectId
     */
    public boolean checkId(int projectId) {
        return projectService.checkId(projectId);
    }

    /**
     * Here we check both project id and employee id 
     * present in projectEmployee or not
     *
     * @param id - id
     * @param employeeList - employeeList
     */
    public List<List<Integer>> checkIdForAssignAndUnassign(int projectId,
            List<Integer> employeeList) {
        return projectService.checkIdForAssignAndUnassign(projectId, employeeList);
    }
 
    /**
     * Here we assign employee values to project
     *
     * @param id - id
     * @param employeeIdList - list
     */
    public void assignEmployee(int id, List<Integer> employeeIdList) {
        projectService.assignEmployee(id, employeeIdList);
    }

    /**
     * Here we unassign employee values to project
     *
     * @param id - id
     * @param employeeIdList - list
     */
    public void unassignEmployee(int id, List employeeIdList) {
        projectService.unassignEmployee(id, employeeIdList);
    }

    /**
     * Here we display assignged values
     *
     * @param id - project id
     */
    public List<String> getAssignedEmployee(int id) {
       return projectService.getAssignedEmployee(id);
    }
}