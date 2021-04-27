package com.ideas2it.projectmanagement.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
 
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.projectmanagement.dao.ProjectDao;
import com.ideas2it.projectmanagement.model.Project;
import com.ideas2it.sessionfactory.DataBaseConnectivity;

/**
 * In this class we perform crud operation(create,read,
 * update,delete, assign,unassign)
 */
public class ProjectDaoImpl implements ProjectDao {

    /**
     * {@inheritdoc}
     */
    @Override
    public void createProject(Project project) {
        Connection connection = DataBaseConnectivity.getConnection();
        String insertQuery = "INSERT INTO project (project_id, project_name,"
                + "start_date, end_date, manager)"
                + "VALUES (?,?,?,?,?)";
        
        try {
            PreparedStatement preparedStatement
                    = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, project.getId());
            preparedStatement.setString(2, project.getName());
            preparedStatement.setDate(3, project.getStartDate());
            preparedStatement.setDate(4, project.getEndDate());
            preparedStatement.setString(5, project.getManager());
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
    public void assignEmployee(Project project) {
        Connection connection = DataBaseConnectivity.getConnection();
        String insertQuery = "INSERT INTO project_employee"
                + " (project_id, employee_id) VALUES (?, ?)";
        List<Employee> employeeList = project.getEmployees(); 
        
        try {
            PreparedStatement preparedStatement
                    = connection.prepareStatement(insertQuery);
                    
            for (Employee employee : employeeList) {
                preparedStatement.setInt(1, project.getId());
                preparedStatement.setInt(2, employee.getId());
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
    public void unassignEmployee(Project project) {
        Connection connection = DataBaseConnectivity.getConnection();
        String deleteQuery = "DELETE FROM project_employee WHERE"
                + " employee_id = ? and project_id = ?";
        List<Employee> employeeValues = project.getEmployees(); 
        
        try {
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(deleteQuery);            
         
            for (Employee employee : employeeValues) {
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
    public Project getEmployeeProject(int id) {
        Connection connection = DataBaseConnectivity.getConnection();
        List<Employee> employee = new ArrayList<Employee>();
        String selectQuery = "SELECT Project.project_id, Project.project_name, Project.start_date,"
                + "Project.end_date, Project.manager, Employee.id, Employee.name,"
                + "Employee.salary, Employee.date_of_birth, Employee.email_id,"
                + "Employee.mobile_number FROM Project LEFT JOIN project_employee "
                + "ON Project.project_id = project_employee.project_id "
                + "LEFT JOIN Employee ON  Employee.id = project_employee.employee_id "
                + "WHERE Project.project_id = ?";
        Project project = null;

        try {
            PreparedStatement preparedStatement
                    = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            project = new Project(resultSet.getInt("project_id"),
                     resultSet.getString("name"),
                     resultSet.getDate("start_date"),
                     resultSet.getDate("end_date"),
                     resultSet.getString("manager"));
 
            do {
                employee.add(new Employee(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("salary"),
                        resultSet.getDate("date_of_birth"),
                        resultSet.getString("email_id"),
                        resultSet.getString("mobile_number")));
            } while (resultSet.next());
            project.setEmployees(employee);                     
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();   
            }
        }
        return project;  
    } 
       
    /**
     * {@inheritdoc}
     */
    @Override
    public void deleteProject(int id) {
        Connection connection = DataBaseConnectivity.getConnection();
        String deleteQuery = "Update project SET is_deleted = 1 WHERE id = ?";

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
    public void updateProject(Project project) {
        Connection connection = DataBaseConnectivity.getConnection();
        String updateQuery = "UPDATE project set project_name = ?, start_date = ?,"
                + "end_date = ?, manager = ? WHERE project_id = ?";

        try {
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, project.getName());
            preparedStatement.setDate(2, project.getStartDate());
            preparedStatement.setDate(3, project.getEndDate());
            preparedStatement.setString(4, project.getManager()); 
            preparedStatement.setInt(5, project.getId());
            preparedStatement.executeUpdate();
            System.out.println("Updated successfully");
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
    public Project getProject(int id) {
        Connection connection = DataBaseConnectivity.getConnection();
        String selectQuery = "SELECT project_id, project_name, start_date,"
                + "end_date, manager FROM"
                + " Project WHERE project_id = ?";
        Project project = null;
 
        try {
            PreparedStatement preparedStatement
                    = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            project = new Project(resultSet.getInt("project_id"),
                    resultSet.getString("project_name"),
                    resultSet.getDate("start_date"),
                    resultSet.getDate("end_date"),
                    resultSet.getString("manager"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();   
            }
        }
        return project;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Project getIndividualProject(int id) {
        Connection connection = DataBaseConnectivity.getConnection();
        String selectQuery = "SELECT project_id, project_name, start_date,"
                + " end_date, manager FROM Project"
                + " WHERE Project.project_id = ? and is_deleted = 0";
        Project project = null;

        try {
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(selectQuery); 
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                project = getProjectValue(resultSet);               
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
        return project;
    }

    /**
     * Here we get project values for displaying individual project values
     * 
     * @param resultset - resultset
     * @return project
     */
    public Project getProjectValue(ResultSet resultSet) {
        Connection connection = DataBaseConnectivity.getConnection();
        List<Project> list = new ArrayList<Project>();
        Project project = null;
       
        try {            
            int id = resultSet.getInt("project_id");
            String name = resultSet.getString("project_name");
            Date startDate = resultSet.getDate("start_date");
            Date endDate = resultSet.getDate("end_date");
            String manager = resultSet.getString("manager");
            resultSet.next();
            project = new Project(id, name,
                    startDate, endDate, manager);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();    
            }
        }
        return project;          
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Project> getProject() {
        Connection connection = DataBaseConnectivity.getConnection();
        List<Project> projectValues = new ArrayList<Project>();
        String selectQuery = "SELECT project_id, project_name, start_date,"
                + " end_date, manager FROM Project WHERE is_deleted = 0";
        Project project = null;
         
        try {
            PreparedStatement preparedStatement 
                    = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
                       
            while (resultSet.next()) {
                project = new Project(resultSet.getInt("project_id"),
                        resultSet.getString("project_name"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date"),
                        resultSet.getString("manager"));
               projectValues.add(project);
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
        return projectValues;
    }
} 