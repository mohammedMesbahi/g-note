package estm.dsic.jee.rest;

import estm.dsic.jee.cors.CorsFilter;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;

@WebListener

public class AppInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        // Register CorsFilter
        FilterRegistration.Dynamic corsFilter = servletContext.addFilter("CorsFilter", new CorsFilter());
        corsFilter.addMappingForUrlPatterns(null, false, "/*");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // No action needed here
    }
}