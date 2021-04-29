import com.ideas2it.employeemanagement.view.EmployeeView;
import com.ideas2it.projectmanagement.view.ProjectView;

import java.util.Scanner;

/**
 * Here we perform crud operations(create,read,update,delete)
 *
 * @author Dhivyaa Eswaran
 * @version 1.0  16-4-2021 
 */ 
public class EmployeeManagement {
     
    /**
     * Here we call show home page method from employee view
     */
    public static void main(String args[]) {  
        Scanner scanner = new Scanner(System.in);        
        int userOption;

        do {
            System.out.println("LIST OF OPTION YOU CAN PERFORM"
                    + "\n1. EmployeeManagement \n2. ProjectManagement \n3. Exit"
                    + "\nSELECT AN OPTION THAT YOU WANT TO PERFORM");
            userOption = scanner.nextInt();

            switch (userOption) {
                case 1:  EmployeeView employeeView = new EmployeeView();
                         employeeView.homePage();
                         break;
                case 2:  ProjectView projectView = new ProjectView();
                         projectView.homePage();
                         break;
                case 3:  System.out.println("Exit");
                         break;
                default: System.out.println("Invalid entry");
                         break;
            }  
        } while (3 != userOption);                
    }
}