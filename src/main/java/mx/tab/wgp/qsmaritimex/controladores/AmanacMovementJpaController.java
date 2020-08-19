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
import mx.tab.wgp.qsmaritimex.entidades.AmanacMovement;

/**
 *
 * @author WilliamGP025
 */
public class AmanacMovementJpaController implements Serializable {

    public AmanacMovementJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AmanacMovement amanacMovement) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(amanacMovement);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAmanacMovement(amanacMovement.getAmanacMovementId()) != null) {
                throw new PreexistingEntityException("AmanacMovement " + amanacMovement + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AmanacMovement amanacMovement) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            amanacMovement = em.merge(amanacMovement);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = amanacMovement.getAmanacMovementId();
                if (findAmanacMovement(id) == null) {
                    throw new NonexistentEntityException("The amanacMovement with id " + id + " no longer exists.");
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
            AmanacMovement amanacMovement;
            try {
                amanacMovement = em.getReference(AmanacMovement.class, id);
                amanacMovement.getAmanacMovementId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The amanacMovement with id " + id + " no longer exists.", enfe);
            }
            em.remove(amanacMovement);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AmanacMovement> findAmanacMovementEntities() {
        return findAmanacMovementEntities(true, -1, -1);
    }

    public List<AmanacMovement> findAmanacMovementEntities(int maxResults, int firstResult) {
        return findAmanacMovementEntities(false, maxResults, firstResult);
    }

    private List<AmanacMovement> findAmanacMovementEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AmanacMovement.class));
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

    public AmanacMovement findAmanacMovement(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AmanacMovement.class, id);
        } finally {
            em.close();
        }
    }

    public int getAmanacMovementCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AmanacMovement> rt = cq.from(AmanacMovement.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
