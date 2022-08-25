package com.revature.servlets;


import com.revature.services.DataAccessService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DependencyLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DataAccessService.getConnection();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}