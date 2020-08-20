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
import mx.tab.wgp.qsmaritimex.entidades.bitacora.LogWarning;

/**
 *
 * @author William
 */
public class LogWarningJpaController implements Serializable {

    public LogWarningJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LogWarning logWarning) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(logWarning);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LogWarning logWarning) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            logWarning = em.merge(logWarning);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = logWarning.getLogWarningId();
                if (findLogWarning(id) == null) {
                    throw new NonexistentEntityException("The logWarning with id " + id + " no longer exists.");
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
            LogWarning logWarning;
            try {
                logWarning = em.getReference(LogWarning.class, id);
                logWarning.getLogWarningId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The logWarning with id " + id + " no longer exists.", enfe);
            }
            em.remove(logWarning);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LogWarning> findLogWarningEntities() {
        return findLogWarningEntities(true, -1, -1);
    }

    public List<LogWarning> findLogWarningEntities(int maxResults, int firstResult) {
        return findLogWarningEntities(false, maxResults, firstResult);
    }

    private List<LogWarning> findLogWarningEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LogWarning.class));
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

    public LogWarning findLogWarning(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LogWarning.class, id);
        } finally {
            em.close();
        }
    }

    public int getLogWarningCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LogWarning> rt = cq.from(LogWarning.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
