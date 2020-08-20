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
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrderService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrderServicesStatus;

/**
 *
 * @author William
 */
public class ServiceOrderServicesStatusJpaController implements Serializable {

    public ServiceOrderServicesStatusJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServiceOrderServicesStatus serviceOrderServicesStatus) {
        if (serviceOrderServicesStatus.getServiceOrderServiceCollection() == null) {
            serviceOrderServicesStatus.setServiceOrderServiceCollection(new ArrayList<ServiceOrderService>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ServiceOrderService> attachedServiceOrderServiceCollection = new ArrayList<ServiceOrderService>();
            for (ServiceOrderService serviceOrderServiceCollectionServiceOrderServiceToAttach : serviceOrderServicesStatus.getServiceOrderServiceCollection()) {
                serviceOrderServiceCollectionServiceOrderServiceToAttach = em.getReference(serviceOrderServiceCollectionServiceOrderServiceToAttach.getClass(), serviceOrderServiceCollectionServiceOrderServiceToAttach.getServiceOrderServiceId());
                attachedServiceOrderServiceCollection.add(serviceOrderServiceCollectionServiceOrderServiceToAttach);
            }
            serviceOrderServicesStatus.setServiceOrderServiceCollection(attachedServiceOrderServiceCollection);
            em.persist(serviceOrderServicesStatus);
            for (ServiceOrderService serviceOrderServiceCollectionServiceOrderService : serviceOrderServicesStatus.getServiceOrderServiceCollection()) {
                ServiceOrderServicesStatus oldServiceOrderServiceStatusIdOfServiceOrderServiceCollectionServiceOrderService = serviceOrderServiceCollectionServiceOrderService.getServiceOrderServiceStatusId();
                serviceOrderServiceCollectionServiceOrderService.setServiceOrderServiceStatusId(serviceOrderServicesStatus);
                serviceOrderServiceCollectionServiceOrderService = em.merge(serviceOrderServiceCollectionServiceOrderService);
                if (oldServiceOrderServiceStatusIdOfServiceOrderServiceCollectionServiceOrderService != null) {
                    oldServiceOrderServiceStatusIdOfServiceOrderServiceCollectionServiceOrderService.getServiceOrderServiceCollection().remove(serviceOrderServiceCollectionServiceOrderService);
                    oldServiceOrderServiceStatusIdOfServiceOrderServiceCollectionServiceOrderService = em.merge(oldServiceOrderServiceStatusIdOfServiceOrderServiceCollectionServiceOrderService);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServiceOrderServicesStatus serviceOrderServicesStatus) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceOrderServicesStatus persistentServiceOrderServicesStatus = em.find(ServiceOrderServicesStatus.class, serviceOrderServicesStatus.getServiceOrderServiceStatusId());
            Collection<ServiceOrderService> serviceOrderServiceCollectionOld = persistentServiceOrderServicesStatus.getServiceOrderServiceCollection();
            Collection<ServiceOrderService> serviceOrderServiceCollectionNew = serviceOrderServicesStatus.getServiceOrderServiceCollection();
            Collection<ServiceOrderService> attachedServiceOrderServiceCollectionNew = new ArrayList<ServiceOrderService>();
            for (ServiceOrderService serviceOrderServiceCollectionNewServiceOrderServiceToAttach : serviceOrderServiceCollectionNew) {
                serviceOrderServiceCollectionNewServiceOrderServiceToAttach = em.getReference(serviceOrderServiceCollectionNewServiceOrderServiceToAttach.getClass(), serviceOrderServiceCollectionNewServiceOrderServiceToAttach.getServiceOrderServiceId());
                attachedServiceOrderServiceCollectionNew.add(serviceOrderServiceCollectionNewServiceOrderServiceToAttach);
            }
            serviceOrderServiceCollectionNew = attachedServiceOrderServiceCollectionNew;
            serviceOrderServicesStatus.setServiceOrderServiceCollection(serviceOrderServiceCollectionNew);
            serviceOrderServicesStatus = em.merge(serviceOrderServicesStatus);
            for (ServiceOrderService serviceOrderServiceCollectionOldServiceOrderService : serviceOrderServiceCollectionOld) {
                if (!serviceOrderServiceCollectionNew.contains(serviceOrderServiceCollectionOldServiceOrderService)) {
                    serviceOrderServiceCollectionOldServiceOrderService.setServiceOrderServiceStatusId(null);
                    serviceOrderServiceCollectionOldServiceOrderService = em.merge(serviceOrderServiceCollectionOldServiceOrderService);
                }
            }
            for (ServiceOrderService serviceOrderServiceCollectionNewServiceOrderService : serviceOrderServiceCollectionNew) {
                if (!serviceOrderServiceCollectionOld.contains(serviceOrderServiceCollectionNewServiceOrderService)) {
                    ServiceOrderServicesStatus oldServiceOrderServiceStatusIdOfServiceOrderServiceCollectionNewServiceOrderService = serviceOrderServiceCollectionNewServiceOrderService.getServiceOrderServiceStatusId();
                    serviceOrderServiceCollectionNewServiceOrderService.setServiceOrderServiceStatusId(serviceOrderServicesStatus);
                    serviceOrderServiceCollectionNewServiceOrderService = em.merge(serviceOrderServiceCollectionNewServiceOrderService);
                    if (oldServiceOrderServiceStatusIdOfServiceOrderServiceCollectionNewServiceOrderService != null && !oldServiceOrderServiceStatusIdOfServiceOrderServiceCollectionNewServiceOrderService.equals(serviceOrderServicesStatus)) {
                        oldServiceOrderServiceStatusIdOfServiceOrderServiceCollectionNewServiceOrderService.getServiceOrderServiceCollection().remove(serviceOrderServiceCollectionNewServiceOrderService);
                        oldServiceOrderServiceStatusIdOfServiceOrderServiceCollectionNewServiceOrderService = em.merge(oldServiceOrderServiceStatusIdOfServiceOrderServiceCollectionNewServiceOrderService);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = serviceOrderServicesStatus.getServiceOrderServiceStatusId();
                if (findServiceOrderServicesStatus(id) == null) {
                    throw new NonexistentEntityException("The serviceOrderServicesStatus with id " + id + " no longer exists.");
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
            ServiceOrderServicesStatus serviceOrderServicesStatus;
            try {
                serviceOrderServicesStatus = em.getReference(ServiceOrderServicesStatus.class, id);
                serviceOrderServicesStatus.getServiceOrderServiceStatusId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The serviceOrderServicesStatus with id " + id + " no longer exists.", enfe);
            }
            Collection<ServiceOrderService> serviceOrderServiceCollection = serviceOrderServicesStatus.getServiceOrderServiceCollection();
            for (ServiceOrderService serviceOrderServiceCollectionServiceOrderService : serviceOrderServiceCollection) {
                serviceOrderServiceCollectionServiceOrderService.setServiceOrderServiceStatusId(null);
                serviceOrderServiceCollectionServiceOrderService = em.merge(serviceOrderServiceCollectionServiceOrderService);
            }
            em.remove(serviceOrderServicesStatus);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServiceOrderServicesStatus> findServiceOrderServicesStatusEntities() {
        return findServiceOrderServicesStatusEntities(true, -1, -1);
    }

    public List<ServiceOrderServicesStatus> findServiceOrderServicesStatusEntities(int maxResults, int firstResult) {
        return findServiceOrderServicesStatusEntities(false, maxResults, firstResult);
    }

    private List<ServiceOrderServicesStatus> findServiceOrderServicesStatusEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServiceOrderServicesStatus.class));
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

    public ServiceOrderServicesStatus findServiceOrderServicesStatus(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServiceOrderServicesStatus.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiceOrderServicesStatusCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServiceOrderServicesStatus> rt = cq.from(ServiceOrderServicesStatus.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
