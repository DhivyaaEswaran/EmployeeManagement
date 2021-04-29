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
                    + " 5. UNASSIGN 6. DELETE 7. EXIT");
            userOption = scanner.nextInt();

            switch (userOption) {
                case 1:  createProject();
                         break;
                case 2:  chooseUpdateOptionForProject();
                         break;
                case 3:  chooseDisplayOption();
                         break;
              //  case 4:  assignEmployee();
                //         break;
               // case 5:  unassignEmployee();
                 //        break;
               // case 6:  deleteProject();
                 //        break;
                case 7:  System.out.println("Exit");
                         break;
                default: System.out.println("Invalid entry");
                         break;
             }
         } while (7 != userOption);
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
            //case 1:  displayProject();
              //       break;
            case 2:  displayIndividualProject();
                     break;
            //case 3:  displayAssignedEmployee();
             //        break;
            case 4:  System.out.println("Exit");
                     break;
            default: System.out.println("\nInvalid entry");
                     break;
        }
    }

    /**
     * Here we display all the details of project
     */
    /**public void displayProject() {
        List<String> projectValues = projectController.getProject();
        
            for (String project : projectValues) {
                System.out.println(project);
            }      
    }*/

    /**
     * Here we display individual details of project
     */ 
    public void displayIndividualProject() {
        int projectId = getId();

        if (projectController.checkId(projectId)) {            
            System.out.println(projectController.getIndividualProject(projectId));
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
            //case 2:  updateIndividualProject();
              //       break;
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
     * 
     */
    public boolean checkId() {
        int projectId = getId();
        projectController.checkId(projectId);
        return false;
    }
}