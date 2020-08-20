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
import mx.tab.wgp.qsmaritimex.entidades.bitacora.LogInformational;

/**
 *
 * @author William
 */
public class LogInformationalJpaController implements Serializable {

    public LogInformationalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LogInformational logInformational) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(logInformational);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LogInformational logInformational) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            logInformational = em.merge(logInformational);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = logInformational.getLogInformationalId();
                if (findLogInformational(id) == null) {
                    throw new NonexistentEntityException("The logInformational with id " + id + " no longer exists.");
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
            LogInformational logInformational;
            try {
                logInformational = em.getReference(LogInformational.class, id);
                logInformational.getLogInformationalId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The logInformational with id " + id + " no longer exists.", enfe);
            }
            em.remove(logInformational);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LogInformational> findLogInformationalEntities() {
        return findLogInformationalEntities(true, -1, -1);
    }

    public List<LogInformational> findLogInformationalEntities(int maxResults, int firstResult) {
        return findLogInformationalEntities(false, maxResults, firstResult);
    }

    private List<LogInformational> findLogInformationalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LogInformational.class));
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

    public LogInformational findLogInformational(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LogInformational.class, id);
        } finally {
            em.close();
        }
    }

    public int getLogInformationalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LogInformational> rt = cq.from(LogInformational.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
