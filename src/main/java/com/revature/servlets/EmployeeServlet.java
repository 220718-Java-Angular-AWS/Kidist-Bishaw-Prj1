package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.Employee;
import com.revature.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


public class EmployeeServlet extends HttpServlet {

    EmployeeService service;
    ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new EmployeeService();
        this.mapper = new ObjectMapper();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String empId = request.getParameter("emp_id");
        String role = request.getParameter("role");

        if(empId == null || role == "admin"){
            response.getWriter().println(mapper.writeValueAsString(service.getAllEmployees()));
        }
        else {
            response.getWriter().println(mapper.writeValueAsString(service.getEmployee(Integer.parseInt(empId))));
        }

        response.setContentType("Application/Json; Charset=UTF-8");
        response.setStatus(200);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = request.getReader();

        while (bufferedReader.ready()){
            stringBuilder.append(bufferedReader.readLine());
        }
        service.saveEmployee(mapper.readValue(stringBuilder.toString(), Employee.class));
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = request.getReader();

        while (bufferedReader.ready()){
            stringBuilder.append(bufferedReader.readLine());
        }
        service.updateEmployee(mapper.readValue(stringBuilder.toString(), Employee.class));
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        service.deleteEmployee(Integer.parseInt(request.getParameter("emp_id")));
    }

    public void destroy(){
    }

}
