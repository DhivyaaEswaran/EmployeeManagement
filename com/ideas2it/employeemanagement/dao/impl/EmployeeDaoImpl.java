package com.ideas2it.employeemanagement.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.dao.EmployeeDao;
import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.projectmanagement.model.Project;
import com.ideas2it.sessionfactory.DataBaseConnectivity;

/**
 * In this class we perform insert, delete, update, delete for employee
 */
public class EmployeeDaoImpl implements EmployeeDao {
    private DataBaseConnectivity dataBaseConnectivity  
            = DataBaseConnectivity.getInstance();

    /**
     * {@inheritdoc}
     */
    @Override
    public void insertEmployee(Employee employee) {        
        Connection connection = DataBaseConnectivity.getConnection();
        List<Address> insert = new ArrayList<Address>();
        insert = employee.getAddresses();
        String insertQuery = "INSERT INTO employee (id, name,"
                + "salary,  date_of_birth, email_id, mobile_number)"
                + "VALUES (?,?,?,?,?,?)";
        String insertAddress = "INSERT INTO address (employee_id, door_number,"
                + "street_name, district, state, country, pin_code)"
                + "VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement  
                    = connection.prepareStatement(insertQuery);
            PreparedStatement ps  
                    = connection.prepareStatement(insertAddress);
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setDouble(3, employee.getSalary());
            preparedStatement.setDate(4, employee.getDateOfBirth());
            preparedStatement.setString(5, employee.getEmailId());
            preparedStatement.setString(6, employee.getMobileNumber());
            preparedStatement.executeUpdate();        

            for (Address address : insert) {                                
                ps.setInt(1, address.getId());
                ps.setString(2, address.getDoorNumber());
                ps.setString(3, address.getStreetName());
                ps.setString(4, address.getDistrict());
                ps.setString(5, address.getState());
                ps.setString(6, address.getCountry());
                ps.setInt(7, address.getPinCode());
                ps.addBatch();            
            } 
            ps.executeBatch();
            insert.clear();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();   
            }
        }
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public void assignProject(Employee employee) {
        Connection connection = DataBaseConnectivity.getConnection();
        String insertQuery = "INSERT INTO project_employee"
                + " (employee_id, project_id) VALUES (?, ?)";
        List<Project> projectList = employee.getProjects(); 
        
        try {
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(insertQuery);
                    
            for (Project project : projectList) {
                preparedStatement.setInt(1, employee.getId());
                preparedStatement.setInt(2, project.getId());
                preparedStatement.executeUpdate();
            }            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();   
            }
        }
    }
   
    /**
     * {@inheritdoc}
     */
    @Override
    public void unassignProject(Employee employee) {
        Connection connection = DataBaseConnectivity.getConnection();
        String deleteQuery = "DELETE FROM project_employee WHERE"
                + " employee_id = ? and project_id = ?";
        List<Project> projectList = employee.getProjects();
        
        try {
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(deleteQuery);
                                   
            for (Project project : projectList) {
                preparedStatement.setInt(1, employee.getId());
                preparedStatement.setInt(2, project.getId());
                preparedStatement.executeUpdate(); 
            }                                     
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();    
            }
        }
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Employee getEmployeeProject(int id) {
        Connection connection = DataBaseConnectivity.getConnection();
        List<Project> project = new ArrayList<Project>();
        String selectQuery = "SELECT Employee.id, Employee.name, Employee.salary,"
                + "Employee.date_of_birth, Employee.email_id, Employee.mobile_number, "
                + "Project.project_id, Project.project_name, Project.start_date, "
                + "Project.end_date, Project.manager FROM Employee LEFT JOIN project_employee " 
                + "ON Employee.id = project_employee.employee_id "
                + "LEFT JOIN Project ON Project.project_id "
                + " = project_employee.project_id WHERE employee.id = ?";
        Employee employee = null;
            
        try {
            PreparedStatement preparedStatement
                    = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            employee = new Employee(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getDouble("salary"),
                    resultSet.getDate("date_of_birth"),
                    resultSet.getString("email_id"),
                    resultSet.getString("mobile_number"));
 
             do {
                 project.add(new Project(resultSet.getInt("project_id"),
                         resultSet.getString("project_name"),
                         resultSet.getDate("start_date"),
                         resultSet.getDate("end_date"),
                         resultSet.getString("manager")));
             } while (resultSet.next());
             employee.setProjects(project);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace(); 
            }
        }
        return employee;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void deleteEmployee(int id) {     
        Connection connection = DataBaseConnectivity.getConnection();
        String deleteQuery = "Update employee, address SET "
                + " employee.is_deleted = 1, address.is_deleted = 1"
                + " WHERE employee.id = ? and address.employee_id = ?";
        
        try {
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(deleteQuery);           
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();    
            }
        }          
    } 

    /**
     * {@inheritdoc}
     */
    @Override
    public void deleteAddress(int id) {
        Connection connection = DataBaseConnectivity.getConnection();
        String deleteQuery = "Update address SET is_deleted = 1 WHERE id = ?";

        try {
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Employee getIndividualEmployee(int id) {
        Connection connection = DataBaseConnectivity.getConnection();
        String selectQuery = "SELECT Employee.id, Employee.name, Employee.salary,"
                + "Employee.date_of_birth, Employee.email_id, Employee.mobile_number,"
                + " Address.employee_id, Address.door_number, Address.street_name,"
                + " Address.district, Address.state, Address.country, Address.pin_code"
                + " FROM Employee LEFT JOIN Address ON Employee.id = Address.employee_id"
                + " WHERE Employee.id = ?";
        Employee employee = null;  

        try {
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(selectQuery);                   
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
                                    
            if (resultSet.next()) {                          
                employee = getEmployeeValues(resultSet);               
            }            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();  
            }
        }
        return employee;    
    } 
   
    /**
     * Here we get values of employee
     * 
     * @param resultset - resultSet
     * @return employee - employee
     */
    public Employee getEmployeeValues(ResultSet resultSet) {
        Connection connection = DataBaseConnectivity.getConnection();
        List<Address> address = new ArrayList<Address>();
        Employee employee = null;

        try {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double salary = resultSet.getDouble("salary");
            Date dateOfBirth = resultSet.getDate("date_of_birth");
            String emailId = resultSet.getString("email_id");
            String mobileNumber = resultSet.getString("mobile_number");

            do {
                address.add(new Address(
                        resultSet.getInt("employee_id"),
                        resultSet.getString("door_number"),
                        resultSet.getString("street_name"),
                        resultSet.getString("district"),
                        resultSet.getString("state"),
                        resultSet.getString("country"),
                        resultSet.getInt("pin_code")));             
            } while (resultSet.next());
            employee = new Employee(id, name, salary,
                    dateOfBirth, emailId, mobileNumber, address);                                                  
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();    
            }
        }
        return employee; 
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Address> getIndividualAddress(int employeeId) {
        Connection connection = DataBaseConnectivity.getConnection();
        String selectQuery = "SELECT employee_id, door_number, street_name, district,"
                + "state, country, pin_code FROM Address WHERE Address.is_deleted = 0 "
                + "and employee_id = ? ";
        List<Address> addressList = new ArrayList<Address>();
        try {
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();            

            while(resultSet.next()) {
                Address address = new Address(
                        resultSet.getInt("employee_id"),
                        resultSet.getString("door_number"),
                        resultSet.getString("street_name"),
                        resultSet.getString("district"),
                        resultSet.getString("state"),
                        resultSet.getString("country"),
                        resultSet.getInt("pin_code"));
                addressList.add(address);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();   
            }
        }
        return addressList; 
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Employee> getAllEmployee() {
        Connection connection = DataBaseConnectivity.getConnection();
        List<Employee> employeeDetails = new ArrayList<Employee>();
        String selectQuery = "SELECT Employee.id, name, salary, date_of_birth,"
                + " email_id, mobile_number FROM Employee WHERE is_deleted = 0";               
        Employee employee = null;
                
        try {
            Statement statement = connection.createStatement();            
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                employee = new Employee(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("salary"),
                        resultSet.getDate("date_of_birth"),
                        resultSet.getString("email_id"),
                        resultSet.getString("mobile_number"));   
                employeeDetails.add(employee);                
           }                                                
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();   
            }
        }
       return employeeDetails;
    }

    /**
     * {@inheritdoc}
     */
    @Override     
    public void updateEmployee(Employee employee) {
         Connection connection = DataBaseConnectivity.getConnection();
         String updateQuery = "update employee set name = ?, salary = ?,"
                 + "date_of_birth = ?, email_id = ?, mobile_number = ? WHERE id = ?";
 
         try {
             PreparedStatement preparedStatement 
                     = connection.prepareStatement(updateQuery);             
             preparedStatement.setString(1, employee.getName());
             preparedStatement.setDouble(2, employee.getSalary());
             preparedStatement.setDate(3, employee.getDateOfBirth());
             preparedStatement.setString(4, employee.getEmailId());
             preparedStatement.setString(5, employee.getMobileNumber());
             preparedStatement.executeUpdate();
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();  
            }
        }
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Employee getEmployeeForUpdate(int id) {
        Connection connection = DataBaseConnectivity.getConnection();
        String selectQuery = "SELECT id, name, salary, date_of_birth, "
                + "email_id, mobile_number  FROM Employee WHERE id = ?";
        Employee employee = null;
        
        try {
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            employee = new Employee(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getDouble("salary"),
                    resultSet.getDate("date_of_birth"),
                    resultSet.getString("email_id"),
                    resultSet.getString("mobile_number"));            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();   
            }
        } 
        return employee;  
    }
   
    /**
     * {@inheritdoc}
     */
    @Override
    public void updateAddress(Address address) {
        Connection connection = DataBaseConnectivity.getConnection();
        String updateQuery = "update address set door_number = ?, street_name = ?,"
                + "district = ?, state = ?, country = ?,"
                + " pin_code = ? where employee_id = ? and id = ?";

        try {
             PreparedStatement preparedStatement 
                     = connection.prepareStatement(updateQuery);             
             preparedStatement.setString(1, address.getDoorNumber());
             preparedStatement.setString(2, address.getStreetName());
             preparedStatement.setString(3, address.getDistrict());
             preparedStatement.setString(4, address.getState());
             preparedStatement.setString(5, address.getCountry());
             preparedStatement.setInt(6, address.getPinCode());
             preparedStatement.setInt(7, address.getId());
             preparedStatement.setInt(8, address.getAddressId());
             preparedStatement.executeUpdate();
        } catch (Exception e) {
             e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();   
            }
        }      
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Address getAddressValue(int EmployeeId) {
        Connection connection = DataBaseConnectivity.getConnection();
        String selectQuery = "Select employee_id, door_number, street_name, district,"
                + "state, country, pin_code From Address Where employee_id = ?";
        Address address = null;

        try {
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, EmployeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            address = new Address(resultSet.getInt("employee_id"),
                    resultSet.getString("door_number"),
                    resultSet.getString("street_name"),
                    resultSet.getString("district"),
                    resultSet.getString("state"),
                    resultSet.getString("country"),
                    resultSet.getInt("pin_code"));                                          
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();    
            }
        }
        return address;     
    }   

    /**
     * {@inhertidoc}
     */
    @Override
    public void addEmployeeAddress(Address address) {
         Connection connection = DataBaseConnectivity.getConnection();    
         String insertQuery = "INSERT INTO address (employee_id, door_number,"
                + "street_name, district, state, country, pin_code)"
                + "VALUES (?,?,?,?,?,?,?)";

         try {             
             PreparedStatement preparedStatement 
                     = connection.prepareStatement(insertQuery);           
             preparedStatement.setInt(1, address.getId());
             preparedStatement.setString(2, address.getDoorNumber());
             preparedStatement.setString(3, address.getStreetName());
             preparedStatement.setString(4, address.getDistrict());
             preparedStatement.setString(5, address.getState());
             preparedStatement.setString(6, address.getCountry());
             preparedStatement.setInt(7, address.getPinCode());
             preparedStatement.executeUpdate(); 
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();  
            }
        }
    }
}           