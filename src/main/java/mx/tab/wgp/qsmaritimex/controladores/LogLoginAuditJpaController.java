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
import mx.tab.wgp.qsmaritimex.entidades.bitacora.LogLoginAudit;

/**
 *
 * @author William
 */
public class LogLoginAuditJpaController implements Serializable {

    public LogLoginAuditJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LogLoginAudit logLoginAudit) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(logLoginAudit);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LogLoginAudit logLoginAudit) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            logLoginAudit = em.merge(logLoginAudit);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = logLoginAudit.getLogLoginAuditId();
                if (findLogLoginAudit(id) == null) {
                    throw new NonexistentEntityException("The logLoginAudit with id " + id + " no longer exists.");
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
            LogLoginAudit logLoginAudit;
            try {
                logLoginAudit = em.getReference(LogLoginAudit.class, id);
                logLoginAudit.getLogLoginAuditId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The logLoginAudit with id " + id + " no longer exists.", enfe);
            }
            em.remove(logLoginAudit);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LogLoginAudit> findLogLoginAuditEntities() {
        return findLogLoginAuditEntities(true, -1, -1);
    }

    public List<LogLoginAudit> findLogLoginAuditEntities(int maxResults, int firstResult) {
        return findLogLoginAuditEntities(false, maxResults, firstResult);
    }

    private List<LogLoginAudit> findLogLoginAuditEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LogLoginAudit.class));
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

    public LogLoginAudit findLogLoginAudit(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LogLoginAudit.class, id);
        } finally {
            em.close();
        }
    }

    public int getLogLoginAuditCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LogLoginAudit> rt = cq.from(LogLoginAudit.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
