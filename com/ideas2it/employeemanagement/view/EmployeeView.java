package com.ideas2it.employeemanagement.view;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.employeemanagement.controller.EmployeeController;
import com.ideas2it.employeemanagement.model.Employee;

/**
 * In this class we perform crud operations(create,read,update,delete)
 */
public class EmployeeView {
    private EmployeeController employeeController = new EmployeeController();
    private Scanner scanner = new Scanner(System.in);    

    /**
     * Here we select an option to be performed 
     */
    public void homePage() {        
        int userOption;     
        
        do {
            System.out.println("\nSELECT AN OPTION THAT YOU WANT TO PERFORM"
                    + "\n1. Create 2. Display 3. Update 4. Delete" 
                    + " 5. Assign 6. Unassign 7. Add address 8. Exit");
            userOption = scanner.nextInt();

            switch (userOption) {
                case 1:  createEmployee();                     
                         break;
                case 2:  chooseDisplayOption();                    
                         break;             
                case 3:  chooseUpdateOptionForEmployee();                    
                         break;
                case 4:  chooseDeleteOptionForEmployee();
                         break;
                case 5:  assignProject();
                         break;
                case 6:  unassignProject();
                         break;
                case 7:  addEmployeeAddress();
                         break;
                case 8:  System.out.println("Exit");                   
                         break;      
                default: System.out.println("invalid entry");                     
                         break; 
            }
        } while (8 != userOption);     
    }
     
    /**
     * Here we are creating values for employee such as id, name, salary,    
     * date of birth, address, emailId
     */
    public void createEmployee() {              
        String name = getEmployeeName();         
        double salary = getEmployeeSalary();        
        Date dateOfBirth = getEmployeeDateOfBirth();                       
        String mobileNumber = getValidMobileNumber();
        String emailId = getEmployeeEmailId();                
        employeeController.createEmployee(name, 
                salary, dateOfBirth, mobileNumber, emailId);  
        createAddress();       
        System.out.println("\nEMPLOYEES DETAILS HAVE BEEN CREATED");
    }
    
    /**
     * Here we get the valid mobile number for employee 
     *
     * @return mobileNumber
     */
    public String getValidMobileNumber() {
        String mobileNumber = getEmployeeMobileNumber();
             
        while (!employeeController.checkValidMobileNumber(mobileNumber)) {
            System.out.println("\nEMPLOYEE MOBILE NUMBER DOES NOT MATCH");  
            mobileNumber = getEmployeeMobileNumber(); 
        }
        return mobileNumber;
    }

    /**
     * Here we get the valid email id for employee
     *
     * @return emailId
     */
    public String getValidEmailId() {
        String emailId = getEmployeeEmailId(); 
    
        while (!employeeController.checkValidEmailId(emailId)) {
            System.out.println("\nEMPLOYEE EMAIL ID DOES NOT MATCH");  
            emailId = getEmployeeEmailId();
        }
        return emailId;       
    } 

    /**
     * Here we get the employee id as input
     *
     * @return id
     */
    public int getEmployeeId() {
        System.out.println("EMPLOYEE ID:");
        return scanner.nextInt();    
    }

    /**
     * Here we get the employee name as input
     *
     * @return name
     */
    public String getEmployeeName() {
        System.out.println("NAME:");
        return scanner.skip("[\r\n]+").nextLine();    
    }
    
    /**
     * Here we get the employee salary as input
     *
     * @return salary
     */
    public double getEmployeeSalary() {
        System.out.println("SALARY");
        return scanner.nextDouble();    
    }

    /**
     * Here we get the employee mobile number as input
     *
     * @return mobile number
     */
    public String getEmployeeMobileNumber() {
        System.out.println("MOBILENUMBER:");
        return scanner.next(); 
    } 

    /**
     * Here we get the employee emailid as input
     *
     * @return emailid
     */
    public String getEmployeeEmailId() {
        System.out.println("EMAILID:");
        return scanner.next(); 
    } 

    /**
     * Here we get the employee address as input
     * We get both permanent address and temoprary address
     */
    public void createAddress() {  
        System.out.print("How many address you want to create?");
        int count = scanner.nextInt();
        int id = getEmployeeId();
 
        for (int index = 0; index < count; index++) { 
            System.out.println("DOOR NUMBER:");
            String doorNumber = scanner.next();
            System.out.println("STREET NAME:");
            String streetName = scanner.skip("[\r\n]+").nextLine();
            System.out.println("DISTRICT:");
            String district = scanner.nextLine();
            System.out.println("STATE:");
            String state = scanner.nextLine();
            System.out.println("COUNTRY:");
            String country = scanner.nextLine();
            System.out.println("PINCODE:");
            int pinCode = scanner.nextInt();
            employeeController.addEmployeeAddress(id, doorNumber, 
                    streetName, district, state, country, pinCode);
        }
    }

    /**
     * Here we get the employee date of birth as input
     *
     * @return date of birth
     */
    public Date getEmployeeDateOfBirth() {
        System.out.println("DATEOFBIRTH:");
        Date dateOfBirth = Date.valueOf(scanner.next());         
        return dateOfBirth;  
    }  

    /**
     * Here we get id for address
     *
     * @return id
     */
    public int getAddressId() {
        System.out.println("ADDRESS ID:");
        return scanner.nextInt();
    }

    /**
     * Here we get address list doornumber for employee
     *
     * @return door number
     */
    public String getAddressDoorNumber() {
        System.out.println("DOOR NUMBER:");
        return scanner.next();
    }

    /**
     * Here we get address list street name for employee
     *
     * @return street name
     */
    public String getAddressStreetName() {
        System.out.println("STREET NAME:");
        return scanner.skip("[\r\n]+").nextLine();
    }

    /**
     * Here we get address list district for employee
     *
     * @return district
     */
    public String getAddressDistrict() {
        System.out.println("DISTRICT:");
        return scanner.nextLine();
    }

    /** 
     * Here we get address list State for employee
     *
     * @return state
     */
    public String getAddressState() {
        System.out.println("STATE:");
        return scanner.nextLine();
    }

    /** 
     * Here we get address list country for employee
     *
     * @return country
     */
    public String getAddressCountry() {
        System.out.println("COUNTRY:");
        return scanner.nextLine();
    }

    /** 
     * Here we get address list pincode for employee
     *
     * @return pincode
     */
    public int getAddressPinCode() {
        System.out.println("PINCODE:");
        return scanner.nextInt();
    }   

    /**
     * Here we choose an option for displaying employee details
     */
    public void chooseDisplayOption() {              
        String printStatement = ("\nYOU ARE HERE TO SELECT AN OPTION FOR DISPLAY OPTION"
                + "\nSELECT AN OPTION YOU NEED TO PERFORM" 
                + "\n1.DISPLAY ALL"
                + "\n2.DISPLAY INDIVIDUAL"
                + "\n3.DISPLAY ASSIGNED PROJECT"
                + "\n4.EXIT");                
        System.out.println(printStatement);
        int userOption = scanner.nextInt();
        
        switch (userOption) {
            case 1:  displayAllEmployee();
                     break;      
            case 2:  displayIndividualEmployee();
                     break;
            case 3:  displayAssignedProject();
                     break;
            case 4:  System.out.println("EXIT");
                    break;
            default: System.out.println("\nINVALID ENTRY");
                     break;
        }
    }

    /** 
     * Here we display individual details of employee
     */
    public void displayIndividualEmployee() {
        int id = getEmployeeId();

        if (employeeController.checkId(id)) {
            List<String> employeeDetails = employeeController.getIndividualEmployee(id); 
         
            for (String employee : employeeDetails) {
                System.out.println(employee);
            } 
        } else {
            System.out.println("Invalid id");
            displayIndividualEmployee();
        }    
    } 

    /** 
     * Here we display all the details of employee
     */
    public void displayAllEmployee() {
        List<String> employeeValues = employeeController.getAllEmployee();

        for (String employee : employeeValues) {
            System.out.println(employee);
        }
    }   

    /**
     * Here we select an option for updating employee values
     */
    public void chooseUpdateOptionForEmployee() {
        String printStatement = "\nYOU ARE HERE TO SELECT AN OPTION"
                + " FOR EMPLOYEE UPDATE\nSELECT AN OPTION YOU NEED TO PERFORM"
                + "\n1.UPDATE EMPLOYEES ALL DETAILS"
                + "\n2.INDIVIDUAL EMPLOYEE"
                + "\n3.UPDATE ADDRESS "
                + "\n4.EXIT";
        System.out.println(printStatement);
        int userOption = scanner.nextInt();
  
        switch (userOption) {
            case 1:  updateEmployee();
                     break;      
            case 2:  updateIndividualEmployee();
                     break;
            case 3:  updateAddress();                    
                     break;
            case 4:  System.out.println("Exit");
                     break;
            default: System.out.println("\nINVALID ENTRY");
                     break;
        }
    }

    /**
     * Here we update all the details of employee
     */   
    public void updateEmployee() {
        System.out.println("ENTER THE EMPLOYEE ID TO BE UPDATED");
        int id = getEmployeeId();                                    
        String name = getEmployeeName();                     
        double salary = getEmployeeSalary();                    
        Date dateOfBirth = getEmployeeDateOfBirth();                                                   
        String mobileNumber = getValidMobileNumber();            
        String emailId = getValidEmailId();        
        employeeController.updateEmployee(id, name, 
                salary, dateOfBirth, emailId, mobileNumber);         
        System.out.println("EMPLOYEE DETAILS HAVE BEEN UPDATED");                  
    } 

    /**
     * Here we update the individual employee details such as salary
     * dateOfBirth, emailId, mobileNumber
     */
    public void updateIndividualEmployee() {
        System.out.println("\nUPDATE INDIVIDUAL EMPLOYEE DETAILS"
                + "\nENTER THE EMPLOYEE ID TO BE UPDATED");
        int id = scanner.nextInt();
        int userOption;

        do {
            System.out.println("\nSELECT AN OPTION THAT YOU WANT TO PERFORM"
                    + "\n1. SALARY \n2. DATEOFBIRTH" 
                    + "\n3. EMAILID \n4. MOBILENUMBER \n5. EXIT");
            userOption = scanner.nextInt();

            switch (userOption) {                
                case 1:  double salary = getEmployeeSalary();
                         employeeController.updateSalary(id, salary);
                         break;
                case 2:  Date dateOfBirth = getEmployeeDateOfBirth();
                         employeeController.updateDateOfBirth(id, dateOfBirth);
                         break;
                case 3:  String emailId = getValidEmailId();
                         employeeController.updateEmailId(id, emailId);
                         break;
                case 4:  String mobileNumber = getValidMobileNumber();
                         employeeController.updateMobileNumber(id, mobileNumber);
                         break;
                case 5:  System.out.println("\nYOU HAVE UPDATED THE VALUES");
                         break;
                default: System.out.println("\nINVALID ENTRY");
                         continue;
           }
       } while (5 != userOption); 
    }

    /**
     * Here we update all the employees address details 
     */
    public void updateAddress() {
        System.out.println("ENTER THE ADDRESS ID TO BE UPDATED");
        int id = getEmployeeId();
        int addressId = getAddressId();                                    
        String doorNumber = getAddressDoorNumber();                     
        String streetName = getAddressStreetName();                    
        String district = getAddressDistrict();                                                   
        String state = getAddressState();            
        String country = getAddressCountry();
        int pinCode = getAddressPinCode();
        employeeController.updateAddress(id, addressId, doorNumber, 
                streetName, district, state, country, pinCode);         
        System.out.println("EMPLOYEES ADDRESS DETAILS HAVE BEEN UPDATED"); 
    } 
       
    /**
     * Here we add multiple address for employee
     */
    public void addEmployeeAddress() {
        int id = getEmployeeId();

        if (employeeController.checkId(id)) {
            System.out.println("Door number");
            String doorNumber = scanner.next();
            System.out.println("STREET NAME:");
            String streetName = scanner.skip("[\r\n]+").nextLine();
            System.out.println("DISTRICT:");
            String district = scanner.nextLine();
            System.out.println("STATE:");
            String state = scanner.nextLine();
            System.out.println("COUNTRY:");
            String country = scanner.nextLine();
            System.out.println("PINCODE:");
            int pinCode = scanner.nextInt();
            employeeController.addEmployeeAddress(id, doorNumber, 
                    streetName, district, state, country, pinCode);
            System.out.println("\naddress details have been created");
        } else {
            System.out.println("Invalid id");
        }
    }    

    /**
     * Here we check id for employee
     */
    public boolean checkId() {
        int id = getEmployeeId();
        employeeController.checkId(id);
        return false;
    } 

    /**
     * Here we select an option for deleting employee
     * details and address details
     */
    public void chooseDeleteOptionForEmployee() {
        String printStatement = "\nYOU ARE HERE TO SELECT AN OPTION"
                + " FOR EMPLOYEE DELETE\nSELECT AN OPTION YOU NEED TO PERFORM"
                + "\n1. DELETE EMPLOYEE"
                + "\n2. DELETE ADDRESS"
                + "\n3. RESTORE EMPLOYEE"
                + "\n4. EXIT";
        System.out.println(printStatement);
        int userOption = scanner.nextInt();
  
        switch (userOption) {
            case 1:  deleteEmployee();
                     break;      
            case 2:  deleteAddress();
                     break;
            case 3:  restoreEmployee();
                     break;
            case 4:  System.out.println("Exit");
                     break;
            default: System.out.println("\nINVALID ENTRY");
                     break;
        }
    }

    /**
     * Here we delete employee details 
     */
    public void deleteEmployee() {
        int id = getEmployeeId();

        if (employeeController.checkId(id)) {
            employeeController.deleteEmployee(id);
            System.out.println("\nEMPLOYEE HAVE BEEN DELETED");
        } else {
            System.out.println("Invalid id");
        }
    }

    /**
     * Here we restore employee details
     */
    public void restoreEmployee() {
        int id = getEmployeeId();

        if (employeeController.checkDeletedId(id)) {
            employeeController.restoreEmployee(id);
            System.out.println("\nEmployee have been restored");
        } else {
            System.out.println("Invalid id");
        }
    }

    /**
     * Here we delete address details 
     */
    public void deleteAddress() {
        int id = getEmployeeId();
        int addressId = getAddressId();
        employeeController.deleteAddress(id, addressId);
        System.out.println("Address details have been deleted");
    }  

    /**
     * Here we assign project values for employee
     */
    public void assignProject() {
        int id = getEmployeeId();        
        List<Integer> projectIdList = new ArrayList<Integer>();

        if (employeeController.checkId(id)) {
            System.out.println("How many project you need to assign:");
            int count = scanner.nextInt();
            for (int index = 0; index < count; index++) {
                projectIdList.add(getProjectId());
            } 
            List<List<Integer>> projectIdListForAssign = 
                    employeeController.checkIdForAssignAndUnassign(id, projectIdList);

            if (projectIdListForAssign.get(1).isEmpty()) {
                System.out.println("Already assigned");
            } else {
                employeeController.assignProject(id, projectIdListForAssign.get(1));
                System.out.println("\nEmployee details have been assigned");
            }
        } else {
            System.out.println("Invalid id");
        }
    }

    /**
     * Here we unassign project values for employee
     */
    public void unassignProject() {
        int id = getEmployeeId();
        List<Integer> projectIdList = new ArrayList<Integer>();

        if (employeeController.checkId(id)) {

            System.out.println("How many project you need to unassign:");
            int count = scanner.nextInt();
            for (int index = 0; index < count; index++) {
                projectIdList.add(getProjectId());
            } 
            List<List<Integer>> projectIdListForUnassign = 
                    employeeController.checkIdForAssignAndUnassign(id, projectIdList);

            if (projectIdListForUnassign.get(0).isEmpty()) {
                System.out.println("Already unassigned");
            } else {
                employeeController.unassignProject(id, projectIdListForUnassign.get(0));
                System.out.println("\nsuccessfully unassigned");
            }
        } else {
            System.out.println("Invalid id");
        }
    }

    /**
     * Here we display assigned project
     */
    public void displayAssignedProject() {
        int id = getEmployeeId();

        if (employeeController.checkId(id)) {

            List<String> employees = employeeController.getAssignedProject(id);
        
            for (String employeeValues : employees) {
                System.out.println(employeeValues);    
            }  
        } else {
            System.out.println("Invalid id");
        }      
    } 

    /**
     * Here we get project id for assign and unassign
     *
     * @return projectId
     */
    public int getProjectId() {
        System.out.println("\nPROJECT ID:");
        return scanner.nextInt(); 
    }     
}