package com.revature.services;

import com.revature.daos.EmployeeDAO;
import com.revature.pojos.Employee;

import java.util.List;

public class EmployeeService {

    private EmployeeDAO dao;

    public EmployeeService() {
        this.dao = new EmployeeDAO();
    }

    public void saveEmployee(Employee employee){
        dao.create(employee);
    }
    public Employee getEmployee(int id){
        return dao.read(id);
    }

    public List<Employee> getAllEmployees(){
        return dao.readAll();
    }

    public void updateEmployee(Employee employee){

        dao.update(employee);
    }

    public void deleteEmployee(int id){
        dao.delete(id);
    }

    public Employee authenticate(String email, String password) {
        return dao.authenticate(email, password);
    }
}
