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
import mx.tab.wgp.qsmaritimex.entidades.LogTokenAudit;

/**
 *
 * @author WilliamGP025
 */
public class LogTokenAuditJpaController implements Serializable {

    public LogTokenAuditJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LogTokenAudit logTokenAudit) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(logTokenAudit);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LogTokenAudit logTokenAudit) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            logTokenAudit = em.merge(logTokenAudit);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = logTokenAudit.getLogTokenAuditId();
                if (findLogTokenAudit(id) == null) {
                    throw new NonexistentEntityException("The logTokenAudit with id " + id + " no longer exists.");
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
            LogTokenAudit logTokenAudit;
            try {
                logTokenAudit = em.getReference(LogTokenAudit.class, id);
                logTokenAudit.getLogTokenAuditId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The logTokenAudit with id " + id + " no longer exists.", enfe);
            }
            em.remove(logTokenAudit);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LogTokenAudit> findLogTokenAuditEntities() {
        return findLogTokenAuditEntities(true, -1, -1);
    }

    public List<LogTokenAudit> findLogTokenAuditEntities(int maxResults, int firstResult) {
        return findLogTokenAuditEntities(false, maxResults, firstResult);
    }

    private List<LogTokenAudit> findLogTokenAuditEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LogTokenAudit.class));
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

    public LogTokenAudit findLogTokenAudit(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LogTokenAudit.class, id);
        } finally {
            em.close();
        }
    }

    public int getLogTokenAuditCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LogTokenAudit> rt = cq.from(LogTokenAudit.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
