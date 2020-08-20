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
import mx.tab.wgp.qsmaritimex.entidades.nominacion.Vessel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.Service;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceType;

/**
 *
 * @author William
 */
public class ServiceTypeJpaController implements Serializable {

    public ServiceTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServiceType serviceType) {
        if (serviceType.getVesselCollection() == null) {
            serviceType.setVesselCollection(new ArrayList<Vessel>());
        }
        if (serviceType.getServiceCollection() == null) {
            serviceType.setServiceCollection(new ArrayList<Service>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Vessel> attachedVesselCollection = new ArrayList<Vessel>();
            for (Vessel vesselCollectionVesselToAttach : serviceType.getVesselCollection()) {
                vesselCollectionVesselToAttach = em.getReference(vesselCollectionVesselToAttach.getClass(), vesselCollectionVesselToAttach.getVesselId());
                attachedVesselCollection.add(vesselCollectionVesselToAttach);
            }
            serviceType.setVesselCollection(attachedVesselCollection);
            Collection<Service> attachedServiceCollection = new ArrayList<Service>();
            for (Service serviceCollectionServiceToAttach : serviceType.getServiceCollection()) {
                serviceCollectionServiceToAttach = em.getReference(serviceCollectionServiceToAttach.getClass(), serviceCollectionServiceToAttach.getServiceId());
                attachedServiceCollection.add(serviceCollectionServiceToAttach);
            }
            serviceType.setServiceCollection(attachedServiceCollection);
            em.persist(serviceType);
            for (Vessel vesselCollectionVessel : serviceType.getVesselCollection()) {
                ServiceType oldServiceTypeIdOfVesselCollectionVessel = vesselCollectionVessel.getServiceTypeId();
                vesselCollectionVessel.setServiceTypeId(serviceType);
                vesselCollectionVessel = em.merge(vesselCollectionVessel);
                if (oldServiceTypeIdOfVesselCollectionVessel != null) {
                    oldServiceTypeIdOfVesselCollectionVessel.getVesselCollection().remove(vesselCollectionVessel);
                    oldServiceTypeIdOfVesselCollectionVessel = em.merge(oldServiceTypeIdOfVesselCollectionVessel);
                }
            }
            for (Service serviceCollectionService : serviceType.getServiceCollection()) {
                ServiceType oldServiceTypeIdOfServiceCollectionService = serviceCollectionService.getServiceTypeId();
                serviceCollectionService.setServiceTypeId(serviceType);
                serviceCollectionService = em.merge(serviceCollectionService);
                if (oldServiceTypeIdOfServiceCollectionService != null) {
                    oldServiceTypeIdOfServiceCollectionService.getServiceCollection().remove(serviceCollectionService);
                    oldServiceTypeIdOfServiceCollectionService = em.merge(oldServiceTypeIdOfServiceCollectionService);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServiceType serviceType) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceType persistentServiceType = em.find(ServiceType.class, serviceType.getServiceTypeId());
            Collection<Vessel> vesselCollectionOld = persistentServiceType.getVesselCollection();
            Collection<Vessel> vesselCollectionNew = serviceType.getVesselCollection();
            Collection<Service> serviceCollectionOld = persistentServiceType.getServiceCollection();
            Collection<Service> serviceCollectionNew = serviceType.getServiceCollection();
            Collection<Vessel> attachedVesselCollectionNew = new ArrayList<Vessel>();
            for (Vessel vesselCollectionNewVesselToAttach : vesselCollectionNew) {
                vesselCollectionNewVesselToAttach = em.getReference(vesselCollectionNewVesselToAttach.getClass(), vesselCollectionNewVesselToAttach.getVesselId());
                attachedVesselCollectionNew.add(vesselCollectionNewVesselToAttach);
            }
            vesselCollectionNew = attachedVesselCollectionNew;
            serviceType.setVesselCollection(vesselCollectionNew);
            Collection<Service> attachedServiceCollectionNew = new ArrayList<Service>();
            for (Service serviceCollectionNewServiceToAttach : serviceCollectionNew) {
                serviceCollectionNewServiceToAttach = em.getReference(serviceCollectionNewServiceToAttach.getClass(), serviceCollectionNewServiceToAttach.getServiceId());
                attachedServiceCollectionNew.add(serviceCollectionNewServiceToAttach);
            }
            serviceCollectionNew = attachedServiceCollectionNew;
            serviceType.setServiceCollection(serviceCollectionNew);
            serviceType = em.merge(serviceType);
            for (Vessel vesselCollectionOldVessel : vesselCollectionOld) {
                if (!vesselCollectionNew.contains(vesselCollectionOldVessel)) {
                    vesselCollectionOldVessel.setServiceTypeId(null);
                    vesselCollectionOldVessel = em.merge(vesselCollectionOldVessel);
                }
            }
            for (Vessel vesselCollectionNewVessel : vesselCollectionNew) {
                if (!vesselCollectionOld.contains(vesselCollectionNewVessel)) {
                    ServiceType oldServiceTypeIdOfVesselCollectionNewVessel = vesselCollectionNewVessel.getServiceTypeId();
                    vesselCollectionNewVessel.setServiceTypeId(serviceType);
                    vesselCollectionNewVessel = em.merge(vesselCollectionNewVessel);
                    if (oldServiceTypeIdOfVesselCollectionNewVessel != null && !oldServiceTypeIdOfVesselCollectionNewVessel.equals(serviceType)) {
                        oldServiceTypeIdOfVesselCollectionNewVessel.getVesselCollection().remove(vesselCollectionNewVessel);
                        oldServiceTypeIdOfVesselCollectionNewVessel = em.merge(oldServiceTypeIdOfVesselCollectionNewVessel);
                    }
                }
            }
            for (Service serviceCollectionOldService : serviceCollectionOld) {
                if (!serviceCollectionNew.contains(serviceCollectionOldService)) {
                    serviceCollectionOldService.setServiceTypeId(null);
                    serviceCollectionOldService = em.merge(serviceCollectionOldService);
                }
            }
            for (Service serviceCollectionNewService : serviceCollectionNew) {
                if (!serviceCollectionOld.contains(serviceCollectionNewService)) {
                    ServiceType oldServiceTypeIdOfServiceCollectionNewService = serviceCollectionNewService.getServiceTypeId();
                    serviceCollectionNewService.setServiceTypeId(serviceType);
                    serviceCollectionNewService = em.merge(serviceCollectionNewService);
                    if (oldServiceTypeIdOfServiceCollectionNewService != null && !oldServiceTypeIdOfServiceCollectionNewService.equals(serviceType)) {
                        oldServiceTypeIdOfServiceCollectionNewService.getServiceCollection().remove(serviceCollectionNewService);
                        oldServiceTypeIdOfServiceCollectionNewService = em.merge(oldServiceTypeIdOfServiceCollectionNewService);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = serviceType.getServiceTypeId();
                if (findServiceType(id) == null) {
                    throw new NonexistentEntityException("The serviceType with id " + id + " no longer exists.");
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
            ServiceType serviceType;
            try {
                serviceType = em.getReference(ServiceType.class, id);
                serviceType.getServiceTypeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The serviceType with id " + id + " no longer exists.", enfe);
            }
            Collection<Vessel> vesselCollection = serviceType.getVesselCollection();
            for (Vessel vesselCollectionVessel : vesselCollection) {
                vesselCollectionVessel.setServiceTypeId(null);
                vesselCollectionVessel = em.merge(vesselCollectionVessel);
            }
            Collection<Service> serviceCollection = serviceType.getServiceCollection();
            for (Service serviceCollectionService : serviceCollection) {
                serviceCollectionService.setServiceTypeId(null);
                serviceCollectionService = em.merge(serviceCollectionService);
            }
            em.remove(serviceType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServiceType> findServiceTypeEntities() {
        return findServiceTypeEntities(true, -1, -1);
    }

    public List<ServiceType> findServiceTypeEntities(int maxResults, int firstResult) {
        return findServiceTypeEntities(false, maxResults, firstResult);
    }

    private List<ServiceType> findServiceTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServiceType.class));
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

    public ServiceType findServiceType(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServiceType.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiceTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServiceType> rt = cq.from(ServiceType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
