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
        User user = new User("Louise Pearce", "Journalist");
        User user2 = new User("Anton Grace", "Doctor");
        User user3 = new User("Maria Obama", "Teacher");
        d.insert(user);
        d.insert(user2);
        d.insert(user3);
        
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
    
