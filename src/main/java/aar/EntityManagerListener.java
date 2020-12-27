package aar;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class EntityManagerListener implements ServletContextListener{

    private static EntityManagerFactory entityManager;
    
    Logger log = Logger.getLogger(EntityManagerListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        entityManager = Persistence.createEntityManagerFactory("InMemH2DB");

        DatabaseService d = new DatabaseService();
        KPI kpi = new KPI ("Product Sales");
        KPI kpi1 = new KPI ("Product Expenses");
        KPI kpi2 = new KPI ("Product Expenses Budget");
        KPI kpi3 = new KPI ("Product Sales Target");
        KPI kpi4 = new KPI ("Return on Equity");
        KPI kpi5 = new KPI ("Return on Investment");
        KPI kpi6 = new KPI ("Return on Assets");

        d.insert(kpi);
        d.insert(kpi1);
        d.insert(kpi2);
        d.insert(kpi3);
        d.insert(kpi4);
        d.insert(kpi5);
        d.insert(kpi6);
        
 
        
        log.log(Level.INFO, "Initialized database correctly!");
    }
 
    @Override
    public void contextDestroyed(ServletContextEvent e) {

        entityManager.close();
        log.log(Level.INFO, "Destroying context.... tomcat stopping!");
    }

    public static EntityManager createEntityManager() {
        if (entityManager == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }

        return entityManager.createEntityManager();
    }
}
    
