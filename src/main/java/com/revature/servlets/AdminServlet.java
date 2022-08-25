package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminServlet extends HttpServlet {

    EmployeeService service;
    ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new EmployeeService();
        this.mapper = new ObjectMapper();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

}
