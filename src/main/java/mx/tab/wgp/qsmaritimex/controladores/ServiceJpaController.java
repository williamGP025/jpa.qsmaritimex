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
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceType;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrderServicesTemplate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.IllegalOrphanException;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.Service;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrderService;

/**
 *
 * @author William
 */
public class ServiceJpaController implements Serializable {

    public ServiceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Service service) {
        if (service.getServiceOrderServicesTemplateCollection() == null) {
            service.setServiceOrderServicesTemplateCollection(new ArrayList<ServiceOrderServicesTemplate>());
        }
        if (service.getServiceOrderServiceCollection() == null) {
            service.setServiceOrderServiceCollection(new ArrayList<ServiceOrderService>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceType serviceTypeId = service.getServiceTypeId();
            if (serviceTypeId != null) {
                serviceTypeId = em.getReference(serviceTypeId.getClass(), serviceTypeId.getServiceTypeId());
                service.setServiceTypeId(serviceTypeId);
            }
            Collection<ServiceOrderServicesTemplate> attachedServiceOrderServicesTemplateCollection = new ArrayList<ServiceOrderServicesTemplate>();
            for (ServiceOrderServicesTemplate serviceOrderServicesTemplateCollectionServiceOrderServicesTemplateToAttach : service.getServiceOrderServicesTemplateCollection()) {
                serviceOrderServicesTemplateCollectionServiceOrderServicesTemplateToAttach = em.getReference(serviceOrderServicesTemplateCollectionServiceOrderServicesTemplateToAttach.getClass(), serviceOrderServicesTemplateCollectionServiceOrderServicesTemplateToAttach.getServiceOrderServicesTemplateId());
                attachedServiceOrderServicesTemplateCollection.add(serviceOrderServicesTemplateCollectionServiceOrderServicesTemplateToAttach);
            }
            service.setServiceOrderServicesTemplateCollection(attachedServiceOrderServicesTemplateCollection);
            Collection<ServiceOrderService> attachedServiceOrderServiceCollection = new ArrayList<ServiceOrderService>();
            for (ServiceOrderService serviceOrderServiceCollectionServiceOrderServiceToAttach : service.getServiceOrderServiceCollection()) {
                serviceOrderServiceCollectionServiceOrderServiceToAttach = em.getReference(serviceOrderServiceCollectionServiceOrderServiceToAttach.getClass(), serviceOrderServiceCollectionServiceOrderServiceToAttach.getServiceOrderServiceId());
                attachedServiceOrderServiceCollection.add(serviceOrderServiceCollectionServiceOrderServiceToAttach);
            }
            service.setServiceOrderServiceCollection(attachedServiceOrderServiceCollection);
            em.persist(service);
            if (serviceTypeId != null) {
                serviceTypeId.getServiceCollection().add(service);
                serviceTypeId = em.merge(serviceTypeId);
            }
            for (ServiceOrderServicesTemplate serviceOrderServicesTemplateCollectionServiceOrderServicesTemplate : service.getServiceOrderServicesTemplateCollection()) {
                Service oldServiceIdOfServiceOrderServicesTemplateCollectionServiceOrderServicesTemplate = serviceOrderServicesTemplateCollectionServiceOrderServicesTemplate.getServiceId();
                serviceOrderServicesTemplateCollectionServiceOrderServicesTemplate.setServiceId(service);
                serviceOrderServicesTemplateCollectionServiceOrderServicesTemplate = em.merge(serviceOrderServicesTemplateCollectionServiceOrderServicesTemplate);
                if (oldServiceIdOfServiceOrderServicesTemplateCollectionServiceOrderServicesTemplate != null) {
                    oldServiceIdOfServiceOrderServicesTemplateCollectionServiceOrderServicesTemplate.getServiceOrderServicesTemplateCollection().remove(serviceOrderServicesTemplateCollectionServiceOrderServicesTemplate);
                    oldServiceIdOfServiceOrderServicesTemplateCollectionServiceOrderServicesTemplate = em.merge(oldServiceIdOfServiceOrderServicesTemplateCollectionServiceOrderServicesTemplate);
                }
            }
            for (ServiceOrderService serviceOrderServiceCollectionServiceOrderService : service.getServiceOrderServiceCollection()) {
                Service oldServiceIdOfServiceOrderServiceCollectionServiceOrderService = serviceOrderServiceCollectionServiceOrderService.getServiceId();
                serviceOrderServiceCollectionServiceOrderService.setServiceId(service);
                serviceOrderServiceCollectionServiceOrderService = em.merge(serviceOrderServiceCollectionServiceOrderService);
                if (oldServiceIdOfServiceOrderServiceCollectionServiceOrderService != null) {
                    oldServiceIdOfServiceOrderServiceCollectionServiceOrderService.getServiceOrderServiceCollection().remove(serviceOrderServiceCollectionServiceOrderService);
                    oldServiceIdOfServiceOrderServiceCollectionServiceOrderService = em.merge(oldServiceIdOfServiceOrderServiceCollectionServiceOrderService);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Service service) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Service persistentService = em.find(Service.class, service.getServiceId());
            ServiceType serviceTypeIdOld = persistentService.getServiceTypeId();
            ServiceType serviceTypeIdNew = service.getServiceTypeId();
            Collection<ServiceOrderServicesTemplate> serviceOrderServicesTemplateCollectionOld = persistentService.getServiceOrderServicesTemplateCollection();
            Collection<ServiceOrderServicesTemplate> serviceOrderServicesTemplateCollectionNew = service.getServiceOrderServicesTemplateCollection();
            Collection<ServiceOrderService> serviceOrderServiceCollectionOld = persistentService.getServiceOrderServiceCollection();
            Collection<ServiceOrderService> serviceOrderServiceCollectionNew = service.getServiceOrderServiceCollection();
            List<String> illegalOrphanMessages = null;
            for (ServiceOrderServicesTemplate serviceOrderServicesTemplateCollectionOldServiceOrderServicesTemplate : serviceOrderServicesTemplateCollectionOld) {
                if (!serviceOrderServicesTemplateCollectionNew.contains(serviceOrderServicesTemplateCollectionOldServiceOrderServicesTemplate)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ServiceOrderServicesTemplate " + serviceOrderServicesTemplateCollectionOldServiceOrderServicesTemplate + " since its serviceId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (serviceTypeIdNew != null) {
                serviceTypeIdNew = em.getReference(serviceTypeIdNew.getClass(), serviceTypeIdNew.getServiceTypeId());
                service.setServiceTypeId(serviceTypeIdNew);
            }
            Collection<ServiceOrderServicesTemplate> attachedServiceOrderServicesTemplateCollectionNew = new ArrayList<ServiceOrderServicesTemplate>();
            for (ServiceOrderServicesTemplate serviceOrderServicesTemplateCollectionNewServiceOrderServicesTemplateToAttach : serviceOrderServicesTemplateCollectionNew) {
                serviceOrderServicesTemplateCollectionNewServiceOrderServicesTemplateToAttach = em.getReference(serviceOrderServicesTemplateCollectionNewServiceOrderServicesTemplateToAttach.getClass(), serviceOrderServicesTemplateCollectionNewServiceOrderServicesTemplateToAttach.getServiceOrderServicesTemplateId());
                attachedServiceOrderServicesTemplateCollectionNew.add(serviceOrderServicesTemplateCollectionNewServiceOrderServicesTemplateToAttach);
            }
            serviceOrderServicesTemplateCollectionNew = attachedServiceOrderServicesTemplateCollectionNew;
            service.setServiceOrderServicesTemplateCollection(serviceOrderServicesTemplateCollectionNew);
            Collection<ServiceOrderService> attachedServiceOrderServiceCollectionNew = new ArrayList<ServiceOrderService>();
            for (ServiceOrderService serviceOrderServiceCollectionNewServiceOrderServiceToAttach : serviceOrderServiceCollectionNew) {
                serviceOrderServiceCollectionNewServiceOrderServiceToAttach = em.getReference(serviceOrderServiceCollectionNewServiceOrderServiceToAttach.getClass(), serviceOrderServiceCollectionNewServiceOrderServiceToAttach.getServiceOrderServiceId());
                attachedServiceOrderServiceCollectionNew.add(serviceOrderServiceCollectionNewServiceOrderServiceToAttach);
            }
            serviceOrderServiceCollectionNew = attachedServiceOrderServiceCollectionNew;
            service.setServiceOrderServiceCollection(serviceOrderServiceCollectionNew);
            service = em.merge(service);
            if (serviceTypeIdOld != null && !serviceTypeIdOld.equals(serviceTypeIdNew)) {
                serviceTypeIdOld.getServiceCollection().remove(service);
                serviceTypeIdOld = em.merge(serviceTypeIdOld);
            }
            if (serviceTypeIdNew != null && !serviceTypeIdNew.equals(serviceTypeIdOld)) {
                serviceTypeIdNew.getServiceCollection().add(service);
                serviceTypeIdNew = em.merge(serviceTypeIdNew);
            }
            for (ServiceOrderServicesTemplate serviceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate : serviceOrderServicesTemplateCollectionNew) {
                if (!serviceOrderServicesTemplateCollectionOld.contains(serviceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate)) {
                    Service oldServiceIdOfServiceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate = serviceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate.getServiceId();
                    serviceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate.setServiceId(service);
                    serviceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate = em.merge(serviceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate);
                    if (oldServiceIdOfServiceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate != null && !oldServiceIdOfServiceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate.equals(service)) {
                        oldServiceIdOfServiceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate.getServiceOrderServicesTemplateCollection().remove(serviceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate);
                        oldServiceIdOfServiceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate = em.merge(oldServiceIdOfServiceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate);
                    }
                }
            }
            for (ServiceOrderService serviceOrderServiceCollectionOldServiceOrderService : serviceOrderServiceCollectionOld) {
                if (!serviceOrderServiceCollectionNew.contains(serviceOrderServiceCollectionOldServiceOrderService)) {
                    serviceOrderServiceCollectionOldServiceOrderService.setServiceId(null);
                    serviceOrderServiceCollectionOldServiceOrderService = em.merge(serviceOrderServiceCollectionOldServiceOrderService);
                }
            }
            for (ServiceOrderService serviceOrderServiceCollectionNewServiceOrderService : serviceOrderServiceCollectionNew) {
                if (!serviceOrderServiceCollectionOld.contains(serviceOrderServiceCollectionNewServiceOrderService)) {
                    Service oldServiceIdOfServiceOrderServiceCollectionNewServiceOrderService = serviceOrderServiceCollectionNewServiceOrderService.getServiceId();
                    serviceOrderServiceCollectionNewServiceOrderService.setServiceId(service);
                    serviceOrderServiceCollectionNewServiceOrderService = em.merge(serviceOrderServiceCollectionNewServiceOrderService);
                    if (oldServiceIdOfServiceOrderServiceCollectionNewServiceOrderService != null && !oldServiceIdOfServiceOrderServiceCollectionNewServiceOrderService.equals(service)) {
                        oldServiceIdOfServiceOrderServiceCollectionNewServiceOrderService.getServiceOrderServiceCollection().remove(serviceOrderServiceCollectionNewServiceOrderService);
                        oldServiceIdOfServiceOrderServiceCollectionNewServiceOrderService = em.merge(oldServiceIdOfServiceOrderServiceCollectionNewServiceOrderService);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = service.getServiceId();
                if (findService(id) == null) {
                    throw new NonexistentEntityException("The service with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Service service;
            try {
                service = em.getReference(Service.class, id);
                service.getServiceId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The service with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ServiceOrderServicesTemplate> serviceOrderServicesTemplateCollectionOrphanCheck = service.getServiceOrderServicesTemplateCollection();
            for (ServiceOrderServicesTemplate serviceOrderServicesTemplateCollectionOrphanCheckServiceOrderServicesTemplate : serviceOrderServicesTemplateCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Service (" + service + ") cannot be destroyed since the ServiceOrderServicesTemplate " + serviceOrderServicesTemplateCollectionOrphanCheckServiceOrderServicesTemplate + " in its serviceOrderServicesTemplateCollection field has a non-nullable serviceId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            ServiceType serviceTypeId = service.getServiceTypeId();
            if (serviceTypeId != null) {
                serviceTypeId.getServiceCollection().remove(service);
                serviceTypeId = em.merge(serviceTypeId);
            }
            Collection<ServiceOrderService> serviceOrderServiceCollection = service.getServiceOrderServiceCollection();
            for (ServiceOrderService serviceOrderServiceCollectionServiceOrderService : serviceOrderServiceCollection) {
                serviceOrderServiceCollectionServiceOrderService.setServiceId(null);
                serviceOrderServiceCollectionServiceOrderService = em.merge(serviceOrderServiceCollectionServiceOrderService);
            }
            em.remove(service);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Service> findServiceEntities() {
        return findServiceEntities(true, -1, -1);
    }

    public List<Service> findServiceEntities(int maxResults, int firstResult) {
        return findServiceEntities(false, maxResults, firstResult);
    }

    private List<Service> findServiceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Service.class));
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

    public Service findService(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Service.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Service> rt = cq.from(Service.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
