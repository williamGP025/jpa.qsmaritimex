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
import mx.tab.wgp.qsmaritimex.entidades.ContainerStatus;

/**
 *
 * @author WilliamGP025
 */
public class ContainerStatusJpaController implements Serializable {

    public ContainerStatusJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ContainerStatus containerStatus) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(containerStatus);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ContainerStatus containerStatus) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            containerStatus = em.merge(containerStatus);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = containerStatus.getContainerStatusId();
                if (findContainerStatus(id) == null) {
                    throw new NonexistentEntityException("The containerStatus with id " + id + " no longer exists.");
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
            ContainerStatus containerStatus;
            try {
                containerStatus = em.getReference(ContainerStatus.class, id);
                containerStatus.getContainerStatusId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The containerStatus with id " + id + " no longer exists.", enfe);
            }
            em.remove(containerStatus);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ContainerStatus> findContainerStatusEntities() {
        return findContainerStatusEntities(true, -1, -1);
    }

    public List<ContainerStatus> findContainerStatusEntities(int maxResults, int firstResult) {
        return findContainerStatusEntities(false, maxResults, firstResult);
    }

    private List<ContainerStatus> findContainerStatusEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ContainerStatus.class));
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

    public ContainerStatus findContainerStatus(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ContainerStatus.class, id);
        } finally {
            em.close();
        }
    }

    public int getContainerStatusCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ContainerStatus> rt = cq.from(ContainerStatus.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
