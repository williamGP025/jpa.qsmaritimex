/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.controladores;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.bitacora.LogError;

/**
 *
 * @author William
 */
public class LogErrorJpaController implements Serializable {

    public LogErrorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LogError logError) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(logError);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LogError logError) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            logError = em.merge(logError);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = logError.getLogErrorId();
                if (findLogError(id) == null) {
                    throw new NonexistentEntityException("The logError with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LogError logError;
            try {
                logError = em.getReference(LogError.class, id);
                logError.getLogErrorId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The logError with id " + id + " no longer exists.", enfe);
            }
            em.remove(logError);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LogError> findLogErrorEntities() {
        return findLogErrorEntities(true, -1, -1);
    }

    public List<LogError> findLogErrorEntities(int maxResults, int firstResult) {
        return findLogErrorEntities(false, maxResults, firstResult);
    }

    private List<LogError> findLogErrorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LogError.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public LogError findLogError(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LogError.class, id);
        } finally {
            em.close();
        }
    }

    public int getLogErrorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LogError> rt = cq.from(LogError.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
