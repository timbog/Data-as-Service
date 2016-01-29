package com.emc.daas.tomcat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by timofb on 11/22/2015.
 */
@WebListener
public class DaaSServletContextListener implements ServletContextListener {
    private static Log logger = LogFactory.getLog(DaaSServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //servletContextEvent.getServletContext().addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
                //.addMappingForUrlPatterns(null, false, "/*");
        logger.info("ServletContext initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("ServletContext destroyed");
    }
}
