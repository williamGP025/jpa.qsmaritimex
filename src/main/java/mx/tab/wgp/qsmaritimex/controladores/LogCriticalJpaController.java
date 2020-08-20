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
import mx.tab.wgp.qsmaritimex.entidades.bitacora.LogCritical;

/**
 *
 * @author William
 */
public class LogCriticalJpaController implements Serializable {

    public LogCriticalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LogCritical logCritical) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(logCritical);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LogCritical logCritical) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            logCritical = em.merge(logCritical);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = logCritical.getLogCriticalId();
                if (findLogCritical(id) == null) {
                    throw new NonexistentEntityException("The logCritical with id " + id + " no longer exists.");
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
            LogCritical logCritical;
            try {
                logCritical = em.getReference(LogCritical.class, id);
                logCritical.getLogCriticalId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The logCritical with id " + id + " no longer exists.", enfe);
            }
            em.remove(logCritical);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LogCritical> findLogCriticalEntities() {
        return findLogCriticalEntities(true, -1, -1);
    }

    public List<LogCritical> findLogCriticalEntities(int maxResults, int firstResult) {
        return findLogCriticalEntities(false, maxResults, firstResult);
    }

    private List<LogCritical> findLogCriticalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LogCritical.class));
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

    public LogCritical findLogCritical(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LogCritical.class, id);
        } finally {
            em.close();
        }
    }

    public int getLogCriticalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LogCritical> rt = cq.from(LogCritical.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
