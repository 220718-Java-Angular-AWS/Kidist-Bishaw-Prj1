package com.revature.servlets;

import com.revature.exceptions.AccessDeniedException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HealthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        resp.getWriter().println("Heartbeat.");

        Cookie cookie = new Cookie("test", "cookie");

        throw new AccessDeniedException("Test this exception for error handling");
    }
}