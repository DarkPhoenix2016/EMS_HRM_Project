package main.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Employee {

    public static List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish database connection
            connection = MySqlConnection.connect();
            if (connection != null) {
                // SQL query to retrieve all employees
                String query = "SELECT * FROM 01_00_employee";
                preparedStatement = connection.prepareStatement(query);

                // Execute the query
                resultSet = preparedStatement.executeQuery();

                // Iterate through the result set and create Employee objects
                while (resultSet.next()) {
                    Employee employee = new Employee();
                    employee.setEmpNumber(resultSet.getString("EmpNumber"));
                    employee.setFirstName(resultSet.getString("FirstName"));
                    employee.setLastName(resultSet.getString("LastName"));
                    employee.setName(resultSet.getString("Name"));
                    employee.setNICNumber(resultSet.getString("NICNumber"));
                    employee.setGender(resultSet.getString("Gender"));
                    employee.setDOB(resultSet.getDate("DOB"));
                    employee.setPhoneNumber(resultSet.getString("PhoneNumber"));
                    employee.setAddress(resultSet.getString("Address"));
                    employee.setStatus(resultSet.getString("Status"));
                    employees.add(employee);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close connections and resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return employees;
    }

    // Define private fields for Employee attributes
    private String empNumber;
    private String firstName;
    private String lastName;
    private String name;
    private String NICNumber;
    private String gender;
    private java.sql.Date DOB;
    private String phoneNumber;
    private String address;
    private String status;

    // Define getters and setters for Employee attributes
    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNICNumber() {
        return NICNumber;
    }

    public void setNICNumber(String NICNumber) {
        this.NICNumber = NICNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public java.sql.Date getDOB() {
        return DOB;
    }

    public void setDOB(java.sql.Date DOB) {
        this.DOB = DOB;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
