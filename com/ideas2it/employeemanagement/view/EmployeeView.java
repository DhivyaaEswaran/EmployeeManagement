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
    //            case 2:  chooseDisplayOption();                    
      //                   break;             
        //        case 3:  chooseUpdateOptionForEmployee();                    
          //               break;
            //    case 4:  chooseDeleteOptionForEmployee();
              //           break;
                //case 5:  assignProject();
                  //       break;
             //   case 6:  unassignProject();
               //          break;
                //case 7:  addEmployeeAddress();
                  //       break;
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
        createAddress();        
        employeeController.createEmployee(name, 
                salary, dateOfBirth, mobileNumber, emailId);        
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
 
        for (int index = 0; index < count; index++) { 
            System.out.println("permanent address and temporary address");   
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
            employeeController.createAddress(doorNumber, 
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
}