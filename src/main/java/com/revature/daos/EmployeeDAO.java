package com.revature.daos;

import com.revature.exceptions.AccessDeniedException;
import com.revature.pojos.Employee;
import com.revature.services.DataAccessService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class EmployeeDAO implements DataSourceCRUD<Employee> {

    Connection connection;

    public EmployeeDAO() {
        this.connection = DataAccessService.getConnection();
    }

    @Override
    public void create(Employee employee) {
        try {
            String sql = "INSERT INTO employees(emp_first_name, emp_last_name, emp_email, emp_password, emp_role) VALUES(?, ?, ?, ?, 'user')";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getEmail());
            statement.setString(4, employee.getPassword());
            //statement.setString(5, employee.getRole());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Employee read(int id) {

        Employee employee = new Employee();

        try {
            String sql = "SELECT * FROM employees WHERE emp_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                employee.setEmployeeId(resultSet.getInt("emp_id"));
                employee.setFirstName(resultSet.getString("emp_first_name"));
                employee.setLastName(resultSet.getString("emp_last_name"));
                employee.setEmail(resultSet.getString("emp_email"));
                employee.setPassword(resultSet.getString("emp_password"));
                employee.setRole(resultSet.getString("emp_role"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> readAll() {
        List<Employee> employeeList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM employees";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("emp_id"));
                employee.setFirstName(resultSet.getString("emp_first_name"));
                employee.setLastName(resultSet.getString("emp_last_name"));
                employee.setEmail(resultSet.getString("emp_email"));
                employee.setPassword(resultSet.getString("emp_password"));
                employee.setRole(resultSet.getString("emp_role"));

                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public void update(Employee employee) {

        try {
            String sql = "UPDATE employees SET emp_first_name = ?, emp_last_name = ?, emp_email = ?, emp_password = ?, role = ? WHERE emp_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getEmail());
            statement.setString(4, employee.getPassword());
            statement.setString(5, employee.getRole());
            statement.setInt(6, employee.getEmployeeId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM employees WHERE emp_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Employee authenticate(String email, String password) {
        Employee employee = new Employee();

        try {
            String sql = "select * from employees where emp_email = ? and emp_password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                employee.setEmployeeId(resultSet.getInt("emp_id"));
                employee.setFirstName(resultSet.getString("emp_first_name"));
                employee.setLastName(resultSet.getString("emp_last_name"));
                employee.setEmail(resultSet.getString("emp_email"));
                employee.setPassword(resultSet.getString("emp_password"));
                employee.setRole(resultSet.getString("emp_role"));

            } else {
                throw new AccessDeniedException("Access denied!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

}
