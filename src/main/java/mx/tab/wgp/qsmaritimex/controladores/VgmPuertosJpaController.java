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
import mx.tab.wgp.qsmaritimex.entidades.VgmPuertos;

/**
 *
 * @author WilliamGP025
 */
public class VgmPuertosJpaController implements Serializable {

    public VgmPuertosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VgmPuertos vgmPuertos) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(vgmPuertos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVgmPuertos(vgmPuertos.getVgmPuertosId()) != null) {
                throw new PreexistingEntityException("VgmPuertos " + vgmPuertos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VgmPuertos vgmPuertos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vgmPuertos = em.merge(vgmPuertos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vgmPuertos.getVgmPuertosId();
                if (findVgmPuertos(id) == null) {
                    throw new NonexistentEntityException("The vgmPuertos with id " + id + " no longer exists.");
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
            VgmPuertos vgmPuertos;
            try {
                vgmPuertos = em.getReference(VgmPuertos.class, id);
                vgmPuertos.getVgmPuertosId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vgmPuertos with id " + id + " no longer exists.", enfe);
            }
            em.remove(vgmPuertos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VgmPuertos> findVgmPuertosEntities() {
        return findVgmPuertosEntities(true, -1, -1);
    }

    public List<VgmPuertos> findVgmPuertosEntities(int maxResults, int firstResult) {
        return findVgmPuertosEntities(false, maxResults, firstResult);
    }

    private List<VgmPuertos> findVgmPuertosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VgmPuertos.class));
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

    public VgmPuertos findVgmPuertos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VgmPuertos.class, id);
        } finally {
            em.close();
        }
    }

    public int getVgmPuertosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VgmPuertos> rt = cq.from(VgmPuertos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
