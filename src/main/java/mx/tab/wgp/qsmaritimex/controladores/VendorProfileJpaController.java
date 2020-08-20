/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.controladores;

import java.io.Serializable;
import java.math.BigDecimal;
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
import mx.tab.wgp.qsmaritimex.entidades.VendorProfile;

/**
 *
 * @author William
 */
public class VendorProfileJpaController implements Serializable {

    public VendorProfileJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VendorProfile vendorProfile) {
        if (vendorProfile.getServiceOrderServiceCollection() == null) {
            vendorProfile.setServiceOrderServiceCollection(new ArrayList<ServiceOrderService>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ServiceOrderService> attachedServiceOrderServiceCollection = new ArrayList<ServiceOrderService>();
            for (ServiceOrderService serviceOrderServiceCollectionServiceOrderServiceToAttach : vendorProfile.getServiceOrderServiceCollection()) {
                serviceOrderServiceCollectionServiceOrderServiceToAttach = em.getReference(serviceOrderServiceCollectionServiceOrderServiceToAttach.getClass(), serviceOrderServiceCollectionServiceOrderServiceToAttach.getServiceOrderServiceId());
                attachedServiceOrderServiceCollection.add(serviceOrderServiceCollectionServiceOrderServiceToAttach);
            }
            vendorProfile.setServiceOrderServiceCollection(attachedServiceOrderServiceCollection);
            em.persist(vendorProfile);
            for (ServiceOrderService serviceOrderServiceCollectionServiceOrderService : vendorProfile.getServiceOrderServiceCollection()) {
                VendorProfile oldVendorIdOfServiceOrderServiceCollectionServiceOrderService = serviceOrderServiceCollectionServiceOrderService.getVendorId();
                serviceOrderServiceCollectionServiceOrderService.setVendorId(vendorProfile);
                serviceOrderServiceCollectionServiceOrderService = em.merge(serviceOrderServiceCollectionServiceOrderService);
                if (oldVendorIdOfServiceOrderServiceCollectionServiceOrderService != null) {
                    oldVendorIdOfServiceOrderServiceCollectionServiceOrderService.getServiceOrderServiceCollection().remove(serviceOrderServiceCollectionServiceOrderService);
                    oldVendorIdOfServiceOrderServiceCollectionServiceOrderService = em.merge(oldVendorIdOfServiceOrderServiceCollectionServiceOrderService);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VendorProfile vendorProfile) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VendorProfile persistentVendorProfile = em.find(VendorProfile.class, vendorProfile.getVendorId());
            Collection<ServiceOrderService> serviceOrderServiceCollectionOld = persistentVendorProfile.getServiceOrderServiceCollection();
            Collection<ServiceOrderService> serviceOrderServiceCollectionNew = vendorProfile.getServiceOrderServiceCollection();
            Collection<ServiceOrderService> attachedServiceOrderServiceCollectionNew = new ArrayList<ServiceOrderService>();
            for (ServiceOrderService serviceOrderServiceCollectionNewServiceOrderServiceToAttach : serviceOrderServiceCollectionNew) {
                serviceOrderServiceCollectionNewServiceOrderServiceToAttach = em.getReference(serviceOrderServiceCollectionNewServiceOrderServiceToAttach.getClass(), serviceOrderServiceCollectionNewServiceOrderServiceToAttach.getServiceOrderServiceId());
                attachedServiceOrderServiceCollectionNew.add(serviceOrderServiceCollectionNewServiceOrderServiceToAttach);
            }
            serviceOrderServiceCollectionNew = attachedServiceOrderServiceCollectionNew;
            vendorProfile.setServiceOrderServiceCollection(serviceOrderServiceCollectionNew);
            vendorProfile = em.merge(vendorProfile);
            for (ServiceOrderService serviceOrderServiceCollectionOldServiceOrderService : serviceOrderServiceCollectionOld) {
                if (!serviceOrderServiceCollectionNew.contains(serviceOrderServiceCollectionOldServiceOrderService)) {
                    serviceOrderServiceCollectionOldServiceOrderService.setVendorId(null);
                    serviceOrderServiceCollectionOldServiceOrderService = em.merge(serviceOrderServiceCollectionOldServiceOrderService);
                }
            }
            for (ServiceOrderService serviceOrderServiceCollectionNewServiceOrderService : serviceOrderServiceCollectionNew) {
                if (!serviceOrderServiceCollectionOld.contains(serviceOrderServiceCollectionNewServiceOrderService)) {
                    VendorProfile oldVendorIdOfServiceOrderServiceCollectionNewServiceOrderService = serviceOrderServiceCollectionNewServiceOrderService.getVendorId();
                    serviceOrderServiceCollectionNewServiceOrderService.setVendorId(vendorProfile);
                    serviceOrderServiceCollectionNewServiceOrderService = em.merge(serviceOrderServiceCollectionNewServiceOrderService);
                    if (oldVendorIdOfServiceOrderServiceCollectionNewServiceOrderService != null && !oldVendorIdOfServiceOrderServiceCollectionNewServiceOrderService.equals(vendorProfile)) {
                        oldVendorIdOfServiceOrderServiceCollectionNewServiceOrderService.getServiceOrderServiceCollection().remove(serviceOrderServiceCollectionNewServiceOrderService);
                        oldVendorIdOfServiceOrderServiceCollectionNewServiceOrderService = em.merge(oldVendorIdOfServiceOrderServiceCollectionNewServiceOrderService);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = vendorProfile.getVendorId();
                if (findVendorProfile(id) == null) {
                    throw new NonexistentEntityException("The vendorProfile with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VendorProfile vendorProfile;
            try {
                vendorProfile = em.getReference(VendorProfile.class, id);
                vendorProfile.getVendorId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vendorProfile with id " + id + " no longer exists.", enfe);
            }
            Collection<ServiceOrderService> serviceOrderServiceCollection = vendorProfile.getServiceOrderServiceCollection();
            for (ServiceOrderService serviceOrderServiceCollectionServiceOrderService : serviceOrderServiceCollection) {
                serviceOrderServiceCollectionServiceOrderService.setVendorId(null);
                serviceOrderServiceCollectionServiceOrderService = em.merge(serviceOrderServiceCollectionServiceOrderService);
            }
            em.remove(vendorProfile);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VendorProfile> findVendorProfileEntities() {
        return findVendorProfileEntities(true, -1, -1);
    }

    public List<VendorProfile> findVendorProfileEntities(int maxResults, int firstResult) {
        return findVendorProfileEntities(false, maxResults, firstResult);
    }

    private List<VendorProfile> findVendorProfileEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VendorProfile.class));
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

    public VendorProfile findVendorProfile(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VendorProfile.class, id);
        } finally {
            em.close();
        }
    }

    public int getVendorProfileCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VendorProfile> rt = cq.from(VendorProfile.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
