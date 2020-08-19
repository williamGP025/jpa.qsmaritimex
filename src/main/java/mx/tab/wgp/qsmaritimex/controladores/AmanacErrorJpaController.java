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
import mx.tab.wgp.qsmaritimex.controladores.exceptions.PreexistingEntityException;
import mx.tab.wgp.qsmaritimex.entidades.AmanacError;

/**
 *
 * @author WilliamGP025
 */
public class AmanacErrorJpaController implements Serializable {

    public AmanacErrorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AmanacError amanacError) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(amanacError);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAmanacError(amanacError.getAmanacErrorId()) != null) {
                throw new PreexistingEntityException("AmanacError " + amanacError + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AmanacError amanacError) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            amanacError = em.merge(amanacError);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = amanacError.getAmanacErrorId();
                if (findAmanacError(id) == null) {
                    throw new NonexistentEntityException("The amanacError with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AmanacError amanacError;
            try {
                amanacError = em.getReference(AmanacError.class, id);
                amanacError.getAmanacErrorId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The amanacError with id " + id + " no longer exists.", enfe);
            }
            em.remove(amanacError);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AmanacError> findAmanacErrorEntities() {
        return findAmanacErrorEntities(true, -1, -1);
    }

    public List<AmanacError> findAmanacErrorEntities(int maxResults, int firstResult) {
        return findAmanacErrorEntities(false, maxResults, firstResult);
    }

    private List<AmanacError> findAmanacErrorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AmanacError.class));
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

    public AmanacError findAmanacError(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AmanacError.class, id);
        } finally {
            em.close();
        }
    }

    public int getAmanacErrorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AmanacError> rt = cq.from(AmanacError.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
