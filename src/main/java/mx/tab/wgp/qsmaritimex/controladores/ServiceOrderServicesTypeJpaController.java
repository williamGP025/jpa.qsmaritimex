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
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrderServicesType;

/**
 *
 * @author William
 */
public class ServiceOrderServicesTypeJpaController implements Serializable {

    public ServiceOrderServicesTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServiceOrderServicesType serviceOrderServicesType) {
        if (serviceOrderServicesType.getServiceOrderServiceCollection() == null) {
            serviceOrderServicesType.setServiceOrderServiceCollection(new ArrayList<ServiceOrderService>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ServiceOrderService> attachedServiceOrderServiceCollection = new ArrayList<ServiceOrderService>();
            for (ServiceOrderService serviceOrderServiceCollectionServiceOrderServiceToAttach : serviceOrderServicesType.getServiceOrderServiceCollection()) {
                serviceOrderServiceCollectionServiceOrderServiceToAttach = em.getReference(serviceOrderServiceCollectionServiceOrderServiceToAttach.getClass(), serviceOrderServiceCollectionServiceOrderServiceToAttach.getServiceOrderServiceId());
                attachedServiceOrderServiceCollection.add(serviceOrderServiceCollectionServiceOrderServiceToAttach);
            }
            serviceOrderServicesType.setServiceOrderServiceCollection(attachedServiceOrderServiceCollection);
            em.persist(serviceOrderServicesType);
            for (ServiceOrderService serviceOrderServiceCollectionServiceOrderService : serviceOrderServicesType.getServiceOrderServiceCollection()) {
                ServiceOrderServicesType oldServiceOrderServiceTypeIdOfServiceOrderServiceCollectionServiceOrderService = serviceOrderServiceCollectionServiceOrderService.getServiceOrderServiceTypeId();
                serviceOrderServiceCollectionServiceOrderService.setServiceOrderServiceTypeId(serviceOrderServicesType);
                serviceOrderServiceCollectionServiceOrderService = em.merge(serviceOrderServiceCollectionServiceOrderService);
                if (oldServiceOrderServiceTypeIdOfServiceOrderServiceCollectionServiceOrderService != null) {
                    oldServiceOrderServiceTypeIdOfServiceOrderServiceCollectionServiceOrderService.getServiceOrderServiceCollection().remove(serviceOrderServiceCollectionServiceOrderService);
                    oldServiceOrderServiceTypeIdOfServiceOrderServiceCollectionServiceOrderService = em.merge(oldServiceOrderServiceTypeIdOfServiceOrderServiceCollectionServiceOrderService);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServiceOrderServicesType serviceOrderServicesType) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceOrderServicesType persistentServiceOrderServicesType = em.find(ServiceOrderServicesType.class, serviceOrderServicesType.getServiceOrderServicesTypeId());
            Collection<ServiceOrderService> serviceOrderServiceCollectionOld = persistentServiceOrderServicesType.getServiceOrderServiceCollection();
            Collection<ServiceOrderService> serviceOrderServiceCollectionNew = serviceOrderServicesType.getServiceOrderServiceCollection();
            Collection<ServiceOrderService> attachedServiceOrderServiceCollectionNew = new ArrayList<ServiceOrderService>();
            for (ServiceOrderService serviceOrderServiceCollectionNewServiceOrderServiceToAttach : serviceOrderServiceCollectionNew) {
                serviceOrderServiceCollectionNewServiceOrderServiceToAttach = em.getReference(serviceOrderServiceCollectionNewServiceOrderServiceToAttach.getClass(), serviceOrderServiceCollectionNewServiceOrderServiceToAttach.getServiceOrderServiceId());
                attachedServiceOrderServiceCollectionNew.add(serviceOrderServiceCollectionNewServiceOrderServiceToAttach);
            }
            serviceOrderServiceCollectionNew = attachedServiceOrderServiceCollectionNew;
            serviceOrderServicesType.setServiceOrderServiceCollection(serviceOrderServiceCollectionNew);
            serviceOrderServicesType = em.merge(serviceOrderServicesType);
            for (ServiceOrderService serviceOrderServiceCollectionOldServiceOrderService : serviceOrderServiceCollectionOld) {
                if (!serviceOrderServiceCollectionNew.contains(serviceOrderServiceCollectionOldServiceOrderService)) {
                    serviceOrderServiceCollectionOldServiceOrderService.setServiceOrderServiceTypeId(null);
                    serviceOrderServiceCollectionOldServiceOrderService = em.merge(serviceOrderServiceCollectionOldServiceOrderService);
                }
            }
            for (ServiceOrderService serviceOrderServiceCollectionNewServiceOrderService : serviceOrderServiceCollectionNew) {
                if (!serviceOrderServiceCollectionOld.contains(serviceOrderServiceCollectionNewServiceOrderService)) {
                    ServiceOrderServicesType oldServiceOrderServiceTypeIdOfServiceOrderServiceCollectionNewServiceOrderService = serviceOrderServiceCollectionNewServiceOrderService.getServiceOrderServiceTypeId();
                    serviceOrderServiceCollectionNewServiceOrderService.setServiceOrderServiceTypeId(serviceOrderServicesType);
                    serviceOrderServiceCollectionNewServiceOrderService = em.merge(serviceOrderServiceCollectionNewServiceOrderService);
                    if (oldServiceOrderServiceTypeIdOfServiceOrderServiceCollectionNewServiceOrderService != null && !oldServiceOrderServiceTypeIdOfServiceOrderServiceCollectionNewServiceOrderService.equals(serviceOrderServicesType)) {
                        oldServiceOrderServiceTypeIdOfServiceOrderServiceCollectionNewServiceOrderService.getServiceOrderServiceCollection().remove(serviceOrderServiceCollectionNewServiceOrderService);
                        oldServiceOrderServiceTypeIdOfServiceOrderServiceCollectionNewServiceOrderService = em.merge(oldServiceOrderServiceTypeIdOfServiceOrderServiceCollectionNewServiceOrderService);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = serviceOrderServicesType.getServiceOrderServicesTypeId();
                if (findServiceOrderServicesType(id) == null) {
                    throw new NonexistentEntityException("The serviceOrderServicesType with id " + id + " no longer exists.");
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
            ServiceOrderServicesType serviceOrderServicesType;
            try {
                serviceOrderServicesType = em.getReference(ServiceOrderServicesType.class, id);
                serviceOrderServicesType.getServiceOrderServicesTypeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The serviceOrderServicesType with id " + id + " no longer exists.", enfe);
            }
            Collection<ServiceOrderService> serviceOrderServiceCollection = serviceOrderServicesType.getServiceOrderServiceCollection();
            for (ServiceOrderService serviceOrderServiceCollectionServiceOrderService : serviceOrderServiceCollection) {
                serviceOrderServiceCollectionServiceOrderService.setServiceOrderServiceTypeId(null);
                serviceOrderServiceCollectionServiceOrderService = em.merge(serviceOrderServiceCollectionServiceOrderService);
            }
            em.remove(serviceOrderServicesType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServiceOrderServicesType> findServiceOrderServicesTypeEntities() {
        return findServiceOrderServicesTypeEntities(true, -1, -1);
    }

    public List<ServiceOrderServicesType> findServiceOrderServicesTypeEntities(int maxResults, int firstResult) {
        return findServiceOrderServicesTypeEntities(false, maxResults, firstResult);
    }

    private List<ServiceOrderServicesType> findServiceOrderServicesTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServiceOrderServicesType.class));
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

    public ServiceOrderServicesType findServiceOrderServicesType(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServiceOrderServicesType.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiceOrderServicesTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServiceOrderServicesType> rt = cq.from(ServiceOrderServicesType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
