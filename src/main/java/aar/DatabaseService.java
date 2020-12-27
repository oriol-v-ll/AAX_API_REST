package aar;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.logging.Logger;


/**
 * This class performs the insert, read, delete and search operations
 *
 *
 */
public class DatabaseService {

    Logger log = Logger.getLogger(DatabaseService.class.getName());


    public int insert(User user) {
        EntityManager entityManager = EntityManagerListener.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
           
            
        }
        return user.getId();
    }
    
    public int insert(KPI kpi) {
        EntityManager entityManager = EntityManagerListener.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(kpi);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
           
            
        }
        return kpi.getId();
    }

    public User read(int id) {
        EntityManager entityManager = EntityManagerListener.createEntityManager();
        User product = null;
        try {
            product = entityManager.find(User.class, id);
        } finally {
            entityManager.close();
           
            if (product == null) log.warning("No records records were found with given id value");

        }
        return product;
    }

    public boolean delete(int id) {
	    EntityManager entityManager = EntityManagerListener.createEntityManager();
        boolean result = false;
        try {
            entityManager.getTransaction().begin();
            User entity = null;
           
            entity = entityManager.find(User.class, id);
            if (entity != null) {
                entityManager.remove(entity);
                entityManager.getTransaction().commit();
                result = true;
            } else {
                log.warning("No records records were found with given id value !!");
                result = false;
            }
            
        } catch (Exception e) {
            result = false;
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
          
        }
        return result;
    }

    public List<KPI> search(String key, String value) {
	    EntityManager entityManager = EntityManagerListener.createEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<KPI> criteriaQuery = criteriaBuilder.createQuery(KPI.class);
            Root<KPI> root = criteriaQuery.from(KPI.class);
            criteriaQuery.select(root);
            Predicate where = criteriaBuilder.equal(root.get(key), value);
            criteriaQuery.where(where);
            return entityManager.createQuery(criteriaQuery).getResultList();
        } finally {
            entityManager.close();
           
        }
    }

    public List<KPI> findAll() {
        EntityManager entityManager = EntityManagerListener.createEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<KPI> criteriaQuery = criteriaBuilder.createQuery(KPI.class);
 
            Root<KPI> rootEntry = criteriaQuery.from(KPI.class);
            CriteriaQuery<KPI> all = criteriaQuery.select(rootEntry);
            TypedQuery<KPI> allQuery = entityManager.createQuery(all);
            return allQuery.getResultList();
        } finally {
            entityManager.close();
           
        }
    }
}
