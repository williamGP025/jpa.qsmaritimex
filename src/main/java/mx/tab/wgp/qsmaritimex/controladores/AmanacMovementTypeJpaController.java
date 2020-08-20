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
import mx.tab.wgp.qsmaritimex.entidades.AmanacMovementType;

/**
 *
 * @author William
 */
public class AmanacMovementTypeJpaController implements Serializable {

    public AmanacMovementTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AmanacMovementType amanacMovementType) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(amanacMovementType);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAmanacMovementType(amanacMovementType.getAmanacMovementTypeId()) != null) {
                throw new PreexistingEntityException("AmanacMovementType " + amanacMovementType + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AmanacMovementType amanacMovementType) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            amanacMovementType = em.merge(amanacMovementType);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = amanacMovementType.getAmanacMovementTypeId();
                if (findAmanacMovementType(id) == null) {
                    throw new NonexistentEntityException("The amanacMovementType with id " + id + " no longer exists.");
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
            AmanacMovementType amanacMovementType;
            try {
                amanacMovementType = em.getReference(AmanacMovementType.class, id);
                amanacMovementType.getAmanacMovementTypeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The amanacMovementType with id " + id + " no longer exists.", enfe);
            }
            em.remove(amanacMovementType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AmanacMovementType> findAmanacMovementTypeEntities() {
        return findAmanacMovementTypeEntities(true, -1, -1);
    }

    public List<AmanacMovementType> findAmanacMovementTypeEntities(int maxResults, int firstResult) {
        return findAmanacMovementTypeEntities(false, maxResults, firstResult);
    }

    private List<AmanacMovementType> findAmanacMovementTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AmanacMovementType.class));
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

    public AmanacMovementType findAmanacMovementType(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AmanacMovementType.class, id);
        } finally {
            em.close();
        }
    }

    public int getAmanacMovementTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AmanacMovementType> rt = cq.from(AmanacMovementType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
