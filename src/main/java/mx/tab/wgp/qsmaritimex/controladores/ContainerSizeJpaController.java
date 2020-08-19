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
import mx.tab.wgp.qsmaritimex.entidades.ContainerSize;

/**
 *
 * @author WilliamGP025
 */
public class ContainerSizeJpaController implements Serializable {

    public ContainerSizeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ContainerSize containerSize) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(containerSize);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ContainerSize containerSize) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            containerSize = em.merge(containerSize);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = containerSize.getContainerSizeId();
                if (findContainerSize(id) == null) {
                    throw new NonexistentEntityException("The containerSize with id " + id + " no longer exists.");
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
            ContainerSize containerSize;
            try {
                containerSize = em.getReference(ContainerSize.class, id);
                containerSize.getContainerSizeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The containerSize with id " + id + " no longer exists.", enfe);
            }
            em.remove(containerSize);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ContainerSize> findContainerSizeEntities() {
        return findContainerSizeEntities(true, -1, -1);
    }

    public List<ContainerSize> findContainerSizeEntities(int maxResults, int firstResult) {
        return findContainerSizeEntities(false, maxResults, firstResult);
    }

    private List<ContainerSize> findContainerSizeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ContainerSize.class));
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

    public ContainerSize findContainerSize(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ContainerSize.class, id);
        } finally {
            em.close();
        }
    }

    public int getContainerSizeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ContainerSize> rt = cq.from(ContainerSize.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
