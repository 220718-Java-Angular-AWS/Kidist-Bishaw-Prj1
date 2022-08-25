package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.Employee;

import com.revature.services.EmployeeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    ObjectMapper mapper;
    EmployeeService service;

    @Override
    public void init() throws ServletException {
        this.mapper = new ObjectMapper();
        this.service = new EmployeeService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Authenticate
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = request.getReader();

        while (buffer.ready()) {
            builder.append(buffer.readLine());
        }

        String json = builder.toString();

        Employee employee = mapper.readValue(json, Employee.class);
        Employee authEmployee = service.authenticate(employee.getEmail(), employee.getPassword());


        if (authEmployee != null) {

            response.getWriter().write(mapper.writeValueAsString(authEmployee));
            response.addHeader("JWT", authEmployee.getEmployeeId().toString());

            response.setStatus(200);

        } else {
            response.setStatus(403);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.sendRedirect(request.getContextPath() + "/employees");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if(email != null && password != null){
            Employee authEmployee = service.authenticate(email, password);
            if(authEmployee != null){
                String json = mapper.writeValueAsString(authEmployee);
                response.getWriter().println(json);
            }
        }
        //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/employees");
        //dispatcher.forward(request, response);

        response.setContentType("Application/Json; Charset=UTF-8");
        response.setStatus(200);
    }
}
