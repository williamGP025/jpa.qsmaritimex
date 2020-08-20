/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.controladores;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrderJoin;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrderJoinType;

/**
 *
 * @author William
 */
public class ServiceOrderJoinTypeJpaController implements Serializable {

    public ServiceOrderJoinTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServiceOrderJoinType serviceOrderJoinType) {
        if (serviceOrderJoinType.getServiceOrderJoinCollection() == null) {
            serviceOrderJoinType.setServiceOrderJoinCollection(new ArrayList<ServiceOrderJoin>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ServiceOrderJoin> attachedServiceOrderJoinCollection = new ArrayList<ServiceOrderJoin>();
            for (ServiceOrderJoin serviceOrderJoinCollectionServiceOrderJoinToAttach : serviceOrderJoinType.getServiceOrderJoinCollection()) {
                serviceOrderJoinCollectionServiceOrderJoinToAttach = em.getReference(serviceOrderJoinCollectionServiceOrderJoinToAttach.getClass(), serviceOrderJoinCollectionServiceOrderJoinToAttach.getServiceOrderJoinId());
                attachedServiceOrderJoinCollection.add(serviceOrderJoinCollectionServiceOrderJoinToAttach);
            }
            serviceOrderJoinType.setServiceOrderJoinCollection(attachedServiceOrderJoinCollection);
            em.persist(serviceOrderJoinType);
            for (ServiceOrderJoin serviceOrderJoinCollectionServiceOrderJoin : serviceOrderJoinType.getServiceOrderJoinCollection()) {
                ServiceOrderJoinType oldServiceOrderJoinTypeIdOfServiceOrderJoinCollectionServiceOrderJoin = serviceOrderJoinCollectionServiceOrderJoin.getServiceOrderJoinTypeId();
                serviceOrderJoinCollectionServiceOrderJoin.setServiceOrderJoinTypeId(serviceOrderJoinType);
                serviceOrderJoinCollectionServiceOrderJoin = em.merge(serviceOrderJoinCollectionServiceOrderJoin);
                if (oldServiceOrderJoinTypeIdOfServiceOrderJoinCollectionServiceOrderJoin != null) {
                    oldServiceOrderJoinTypeIdOfServiceOrderJoinCollectionServiceOrderJoin.getServiceOrderJoinCollection().remove(serviceOrderJoinCollectionServiceOrderJoin);
                    oldServiceOrderJoinTypeIdOfServiceOrderJoinCollectionServiceOrderJoin = em.merge(oldServiceOrderJoinTypeIdOfServiceOrderJoinCollectionServiceOrderJoin);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServiceOrderJoinType serviceOrderJoinType) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceOrderJoinType persistentServiceOrderJoinType = em.find(ServiceOrderJoinType.class, serviceOrderJoinType.getServiceOrderJoinTypeId());
            Collection<ServiceOrderJoin> serviceOrderJoinCollectionOld = persistentServiceOrderJoinType.getServiceOrderJoinCollection();
            Collection<ServiceOrderJoin> serviceOrderJoinCollectionNew = serviceOrderJoinType.getServiceOrderJoinCollection();
            Collection<ServiceOrderJoin> attachedServiceOrderJoinCollectionNew = new ArrayList<ServiceOrderJoin>();
            for (ServiceOrderJoin serviceOrderJoinCollectionNewServiceOrderJoinToAttach : serviceOrderJoinCollectionNew) {
                serviceOrderJoinCollectionNewServiceOrderJoinToAttach = em.getReference(serviceOrderJoinCollectionNewServiceOrderJoinToAttach.getClass(), serviceOrderJoinCollectionNewServiceOrderJoinToAttach.getServiceOrderJoinId());
                attachedServiceOrderJoinCollectionNew.add(serviceOrderJoinCollectionNewServiceOrderJoinToAttach);
            }
            serviceOrderJoinCollectionNew = attachedServiceOrderJoinCollectionNew;
            serviceOrderJoinType.setServiceOrderJoinCollection(serviceOrderJoinCollectionNew);
            serviceOrderJoinType = em.merge(serviceOrderJoinType);
            for (ServiceOrderJoin serviceOrderJoinCollectionOldServiceOrderJoin : serviceOrderJoinCollectionOld) {
                if (!serviceOrderJoinCollectionNew.contains(serviceOrderJoinCollectionOldServiceOrderJoin)) {
                    serviceOrderJoinCollectionOldServiceOrderJoin.setServiceOrderJoinTypeId(null);
                    serviceOrderJoinCollectionOldServiceOrderJoin = em.merge(serviceOrderJoinCollectionOldServiceOrderJoin);
                }
            }
            for (ServiceOrderJoin serviceOrderJoinCollectionNewServiceOrderJoin : serviceOrderJoinCollectionNew) {
                if (!serviceOrderJoinCollectionOld.contains(serviceOrderJoinCollectionNewServiceOrderJoin)) {
                    ServiceOrderJoinType oldServiceOrderJoinTypeIdOfServiceOrderJoinCollectionNewServiceOrderJoin = serviceOrderJoinCollectionNewServiceOrderJoin.getServiceOrderJoinTypeId();
                    serviceOrderJoinCollectionNewServiceOrderJoin.setServiceOrderJoinTypeId(serviceOrderJoinType);
                    serviceOrderJoinCollectionNewServiceOrderJoin = em.merge(serviceOrderJoinCollectionNewServiceOrderJoin);
                    if (oldServiceOrderJoinTypeIdOfServiceOrderJoinCollectionNewServiceOrderJoin != null && !oldServiceOrderJoinTypeIdOfServiceOrderJoinCollectionNewServiceOrderJoin.equals(serviceOrderJoinType)) {
                        oldServiceOrderJoinTypeIdOfServiceOrderJoinCollectionNewServiceOrderJoin.getServiceOrderJoinCollection().remove(serviceOrderJoinCollectionNewServiceOrderJoin);
                        oldServiceOrderJoinTypeIdOfServiceOrderJoinCollectionNewServiceOrderJoin = em.merge(oldServiceOrderJoinTypeIdOfServiceOrderJoinCollectionNewServiceOrderJoin);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = serviceOrderJoinType.getServiceOrderJoinTypeId();
                if (findServiceOrderJoinType(id) == null) {
                    throw new NonexistentEntityException("The serviceOrderJoinType with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Short id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceOrderJoinType serviceOrderJoinType;
            try {
                serviceOrderJoinType = em.getReference(ServiceOrderJoinType.class, id);
                serviceOrderJoinType.getServiceOrderJoinTypeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The serviceOrderJoinType with id " + id + " no longer exists.", enfe);
            }
            Collection<ServiceOrderJoin> serviceOrderJoinCollection = serviceOrderJoinType.getServiceOrderJoinCollection();
            for (ServiceOrderJoin serviceOrderJoinCollectionServiceOrderJoin : serviceOrderJoinCollection) {
                serviceOrderJoinCollectionServiceOrderJoin.setServiceOrderJoinTypeId(null);
                serviceOrderJoinCollectionServiceOrderJoin = em.merge(serviceOrderJoinCollectionServiceOrderJoin);
            }
            em.remove(serviceOrderJoinType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServiceOrderJoinType> findServiceOrderJoinTypeEntities() {
        return findServiceOrderJoinTypeEntities(true, -1, -1);
    }

    public List<ServiceOrderJoinType> findServiceOrderJoinTypeEntities(int maxResults, int firstResult) {
        return findServiceOrderJoinTypeEntities(false, maxResults, firstResult);
    }

    private List<ServiceOrderJoinType> findServiceOrderJoinTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServiceOrderJoinType.class));
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

    public ServiceOrderJoinType findServiceOrderJoinType(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServiceOrderJoinType.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiceOrderJoinTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServiceOrderJoinType> rt = cq.from(ServiceOrderJoinType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
