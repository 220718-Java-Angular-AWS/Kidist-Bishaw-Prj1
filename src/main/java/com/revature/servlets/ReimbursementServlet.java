package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.Reimbursement;
import com.revature.services.ReimbursementService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class ReimbursementServlet extends HttpServlet {
    ReimbursementService service;
    ObjectMapper mapper;

    public void init(){
        this.service = new ReimbursementService();
        this.mapper = new ObjectMapper();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String reimbursementId = request.getParameter("reimbursement_id");
        String empId = request.getParameter("emp_id");
        String status = request.getParameter("reimbursement_status");

        if(empId != null){
            if(status != null){
                response.getWriter().println(mapper.writeValueAsString(service.getReimbursementbyEmpIdStatus(Integer.parseInt(empId), status)));
            }else {
                response.getWriter().println(mapper.writeValueAsString(service.getReimbursementbyEmpId(Integer.parseInt(empId))));
            }
        }
        else if (reimbursementId != null) {
            response.getWriter().println(mapper.writeValueAsString(service.getReimbursement(Integer.parseInt(reimbursementId))));
        }
        else if(status != null){
            response.getWriter().println(mapper.writeValueAsString(service.getReimbursementbyStatus(status)));
        }
        else {
            response.getWriter().println(mapper.writeValueAsString(service.getAllReimbursements()));
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

        service.saveReimbursement(mapper.readValue(stringBuilder.toString(), Reimbursement.class));

        response.setContentType("text/html");
        response.setStatus(200);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = request.getReader();

        while (bufferedReader.ready()){
            stringBuilder.append(bufferedReader.readLine());
        }
        service.updateReimbursement(mapper.readValue(stringBuilder.toString(), Reimbursement.class));
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        service.deleteReimbursement(Integer.parseInt(request.getParameter("reimbursement_id")));
    }

    public void destroy(){
    }
}
