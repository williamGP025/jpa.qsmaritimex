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
import mx.tab.wgp.qsmaritimex.entidades.BLTransportType;

/**
 *
 * @author WilliamGP025
 */
public class BLTransportTypeJpaController implements Serializable {

    public BLTransportTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BLTransportType BLTransportType) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(BLTransportType);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBLTransportType(BLTransportType.getBLTransportTypeId()) != null) {
                throw new PreexistingEntityException("BLTransportType " + BLTransportType + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BLTransportType BLTransportType) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BLTransportType = em.merge(BLTransportType);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = BLTransportType.getBLTransportTypeId();
                if (findBLTransportType(id) == null) {
                    throw new NonexistentEntityException("The bLTransportType with id " + id + " no longer exists.");
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
            BLTransportType BLTransportType;
            try {
                BLTransportType = em.getReference(BLTransportType.class, id);
                BLTransportType.getBLTransportTypeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The BLTransportType with id " + id + " no longer exists.", enfe);
            }
            em.remove(BLTransportType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BLTransportType> findBLTransportTypeEntities() {
        return findBLTransportTypeEntities(true, -1, -1);
    }

    public List<BLTransportType> findBLTransportTypeEntities(int maxResults, int firstResult) {
        return findBLTransportTypeEntities(false, maxResults, firstResult);
    }

    private List<BLTransportType> findBLTransportTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BLTransportType.class));
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

    public BLTransportType findBLTransportType(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BLTransportType.class, id);
        } finally {
            em.close();
        }
    }

    public int getBLTransportTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BLTransportType> rt = cq.from(BLTransportType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
