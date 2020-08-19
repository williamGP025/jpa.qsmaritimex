/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.controladores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.LogProcesSuccessful;

/**
 *
 * @author WilliamGP025
 */
public class LogProcesSuccessfulJpaController implements Serializable {

    public LogProcesSuccessfulJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LogProcesSuccessful logProcesSuccessful) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(logProcesSuccessful);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LogProcesSuccessful logProcesSuccessful) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            logProcesSuccessful = em.merge(logProcesSuccessful);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = logProcesSuccessful.getLogProcesSuccessfulId();
                if (findLogProcesSuccessful(id) == null) {
                    throw new NonexistentEntityException("The logProcesSuccessful with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LogProcesSuccessful logProcesSuccessful;
            try {
                logProcesSuccessful = em.getReference(LogProcesSuccessful.class, id);
                logProcesSuccessful.getLogProcesSuccessfulId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The logProcesSuccessful with id " + id + " no longer exists.", enfe);
            }
            em.remove(logProcesSuccessful);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LogProcesSuccessful> findLogProcesSuccessfulEntities() {
        return findLogProcesSuccessfulEntities(true, -1, -1);
    }

    public List<LogProcesSuccessful> findLogProcesSuccessfulEntities(int maxResults, int firstResult) {
        return findLogProcesSuccessfulEntities(false, maxResults, firstResult);
    }

    private List<LogProcesSuccessful> findLogProcesSuccessfulEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LogProcesSuccessful.class));
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

    public LogProcesSuccessful findLogProcesSuccessful(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LogProcesSuccessful.class, id);
        } finally {
            em.close();
        }
    }

    public int getLogProcesSuccessfulCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LogProcesSuccessful> rt = cq.from(LogProcesSuccessful.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
