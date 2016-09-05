package main.java.controller;
import main.java.model.ProjectDB;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "controller.ContextListener", urlPatterns = {"/controller.ContextListener"})
public class ContextListener implements ServletContextListener {
    final static Logger logger = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.debug(":: START contextInitialized :: ");
        try {
            ProjectDB.getInstance();
        } catch (Exception e) {
            logger.error(e, e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
