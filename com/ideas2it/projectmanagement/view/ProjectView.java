package com.ideas2it.projectmanagement.view;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.projectmanagement.controller.ProjectController;
import com.ideas2it.projectmanagement.model.Project;

/**
 * In this class we perfoem crud operation(create,display,update,delete)
 */
public class ProjectView {
    private ProjectController projectController = new ProjectController();
    private Scanner scanner = new Scanner(System.in);

    /**
     * Here we select an option to be performed
     */
    public void homePage() {
        int userOption;
   
        do {
            System.out.println("You are in project side"
                    + "\nSELECT AN OPTION THAT YOU WANT TO PERFORM"
                    + "\n1. CREATE 2. UPDATE 3. DISPLAY 4. ASSIGN"
                    + " 5. UNASSIGN 6. DELETE 7. RESTORE 8. EXIT");
            userOption = scanner.nextInt();

            switch (userOption) {
                case 1:  createProject();
                         break;
                case 2:  chooseUpdateOptionForProject();
                         break;
                case 3:  chooseDisplayOption();
                         break;
                case 4:  assignEmployee();
                         break;
                case 5:  unassignEmployee();
                         break;
                case 6:  deleteProject();
                         break;
                case 7:  restoreProject();
                         break;
                case 8:  System.out.println("Exit");
                         break;
                default: System.out.println("Invalid entry");
                         break;
             }
         } while (8 != userOption);
     }

    /**
     * Here we are creating values for project such as id, projectName,
     * project start Date, project end date, project manager
     */
    public void createProject() {        
        String projectName = getName();        
        Date startDate = getStartDate();
        Date endDate = getEndDate();
        String manager = getManager();
        projectController.createProject(projectName,
                startDate, endDate, manager);
        System.out.println("\nPROJECT DETAILS HAVE BEEN CREATED"); 
    }

    /**
     * Here we get project id
     *
     * @return projectId
     */
    public int getId() {
        System.out.println("PROJECT ID:");
        return scanner.nextInt();
    }
   
    /**
     * Here we get name for project
     *
     * @return projet name
     */
    public String getName() {
        System.out.println("PROJECT NAME:");
        return scanner.skip("[\r\n]+").nextLine();
    }
   
    /**
     * Here we get start date for project
     *
     * @return project start date
     */
    public Date getStartDate() {
        System.out.println("PROJECT START DATE:");
        Date startDate = Date.valueOf(scanner.next());
        return startDate;
    }
   
    /**
     * Here we get end date for project
     *
     * @return project end date
     */   
    public Date getEndDate() {
        System.out.println("PROJECT END DATE:");
        Date endDate = Date.valueOf(scanner.next());
        return endDate;
    }
   
    /**
     * Here we get manager for project
     *
     * @return project manager
     */
    public String getManager() {
        System.out.println("PROJECT MANAGER:");
        return scanner.skip("[\r\n]+").nextLine();
    }

    /**
     * Here we delete project details
     */
    public void deleteProject() {
        int projectId = getId();
        projectController.deleteProject(projectId);
        System.out.println("Successfully deleted");
    }
   
    /**
     * Here we restore project that was deleted
     */
    public void restoreProject() {
        int projectId = getId();
        projectController.restoreProject(projectId);
        System.out.println("Successfully restored");
    }

    /**
     * Here we choose an option for diplay project details
     */
    public void chooseDisplayOption() {
        String printStatement = "\nYou are here to select an option for display"
                + "\nSelect an option you need to perform"
                + "\n1. Display All"
                + "\n2. Display Individual"
                + "\n3. Display Assigned values"
                + "\n4. Exit";
        System.out.println(printStatement);
        int userOption = scanner.nextInt();

        switch (userOption) {
            case 1:  displayProject();
                     break;
            case 2:  displayIndividualProject();
                     break;
            case 3:  displayAssignedEmployee();
                     break;
            case 4:  System.out.println("Exit");
                     break;
            default: System.out.println("\nInvalid entry");
                     break;
        }
    }

    /**
     * Here we display all the details of project
     */
    public void displayProject() {
        List<String> projectValues = projectController.getProject();
        
        for (String project : projectValues) {
            System.out.println(project);
        }      
    }

    /**
     * Here we display individual details of project
     */ 
    public void displayIndividualProject() {
        int projectId = getId();

        if (projectController.checkId(projectId)) {  
            List<String> projectDetails = projectController.getIndividualProject(projectId);
           
            for (String project : projectDetails) {
                System.out.println(project);
            }
        } else {
            System.out.println("Invalid id");
            displayIndividualProject();
        }
    }

    /**
     * Here we choose an option for updating project details
     */
    public void chooseUpdateOptionForProject() {
        String printStatement = "\nYOU ARE HERE TO SELECT AN OPTION"
                + "FOR UPDATING PROJECT DETAILS"
                + "\nSELECT AN OPTION THAT YOU NEED TO PERFORM"
                + "\n1. UPDATE ALL"
                + "\n2. UPDATE INDIVIDUAL"
                + "\n3. EXIT";
        System.out.println(printStatement);
        int userOption = scanner.nextInt();

        switch (userOption) {
            case 1:  updateProject();
                     break;
            case 2:  updateIndividualProject();
                     break;
            case 3:  System.out.println("EXIT");
                     break;
            default: System.out.println("Invalid entry");
                     break;
        }
    }

    /**
     * Here we update all the project details
     */
    public void updateProject() {
        int projectId = getId();
        
        if (projectController.checkId(projectId)) { 
            String projectName = getName();
            Date startDate = getStartDate();
            Date endDate = getEndDate();
            String manager = getManager();
            projectController.updateProject(projectId, projectName, startDate,
                    endDate, manager);
        } else {
            System.out.println("Invalid id");
            updateProject();
        }        
    }

    /**
     * Here we update individual project details
     */
    public void updateIndividualProject() {
        System.out.println("\nUPDATE INDIVIDUAL PROJECT DETAILS"
                + "\nENTER THE PROJECT ID TO BE UPDATED");
        int id = scanner.nextInt();
        int userOption;

        do {
            System.out.println("\nSELECT AN OPTION THAT YOU WANT TO PERFORM"
                    + "\n1. NAME \n2. STARTDATE \n3. ENDDATE"
                    + "\n4. MANAGER \n5. EXIT");
            userOption = scanner.nextInt();
            
            switch (userOption) {
                case 1:  String name = getName();
                         projectController.updateName(id, name);
                         break;
                case 2:  Date startDate = getStartDate();
                         projectController.updateStartDate(id, startDate);
                         break;
                case 3:  Date endDate = getEndDate();
                         projectController.updateEndDate(id, endDate);
                         break;
                case 4:  String manager = getManager();
                         projectController.updateManager(id, manager);
                         break;
                case 5:  System.out.println("EXIT");
                         break;
                default: System.out.println("\nINVALID ENTRY");
                         continue;
            }
        } while (5 != userOption);
    }

    /**
     * Here we check id for employee
     */
    public boolean checkId() {
        int projectId = getId();
        projectController.checkId(projectId);
        return false;
    }

    /**
     * Here we assign list of employee to be assigned in project
     */
    public void assignEmployee() {                       
        int projectId = getId();
        System.out.println("How many employee you need to assign:");
        int count = scanner.nextInt();
        List<Integer> employeeIdList = new ArrayList<Integer>();

        for (int index = 0; index < count; index++) {
            employeeIdList.add(getEmployeeId());
        }     
        List<List<Integer>> employeeIdForAssign   
                = projectController.checkIdForAssignAndUnassign(projectId, employeeIdList); 

        if (employeeIdForAssign.get(1).isEmpty()) { 
            System.out.println("Employee already assigned");
        } else {       
            projectController.assignEmployee(projectId, employeeIdForAssign.get(1));
            System.out.println("\nVales have been assigned");
        }
    }

    /**
     * Here we unssign employee values to project
     */
    public void unassignEmployee() {
       int projectId = getId();
       System.out.println("How many employee you need to unassign:");
       int count = scanner.nextInt();
       List<Integer> employeeIdList = new ArrayList<Integer>();

       for (int index = 0; index < count; index++) {
           employeeIdList.add(getEmployeeId());
       }        
       List<List<Integer>> employeeIdForUnassign 
               = projectController.checkIdForAssignAndUnassign(projectId, employeeIdList); 
       System.out.println(employeeIdForUnassign.get(0));
       if (employeeIdForUnassign.get(0).isEmpty()) {
           System.out.println("Already unassigned");
       } else {          
           projectController.unassignEmployee(projectId, employeeIdForUnassign.get(0));
           System.out.println("\nValues have been unassigned");
       }
    }

    /**
     * Here we display assigned employees
     */
    public void displayAssignedEmployee() {
        int id = getId();
        List<String> projects = projectController.getAssignedEmployee(id);
        
        for (String projectValues : projects) {
            System.out.println(projectValues);
        }
    }

    /**
     * Here we get employee id for assign and unassign
     *
     * @return employeeId 
     */
    public int getEmployeeId() {
        System.out.println("Employee Id:");
        return scanner.nextInt();
    }
}