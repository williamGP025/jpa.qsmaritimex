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
import mx.tab.wgp.qsmaritimex.entidades.VendorInvoiceStatus;
import mx.tab.wgp.qsmaritimex.entidades.ServiceOrderService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.VendorInvoice;

/**
 *
 * @author WilliamGP025
 */
public class VendorInvoiceJpaController implements Serializable {

    public VendorInvoiceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VendorInvoice vendorInvoice) {
        if (vendorInvoice.getServiceOrderServiceCollection() == null) {
            vendorInvoice.setServiceOrderServiceCollection(new ArrayList<ServiceOrderService>());
        }
        if (vendorInvoice.getServiceOrderServiceCollection1() == null) {
            vendorInvoice.setServiceOrderServiceCollection1(new ArrayList<ServiceOrderService>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VendorInvoiceStatus vendorInvoiceStatusId = vendorInvoice.getVendorInvoiceStatusId();
            if (vendorInvoiceStatusId != null) {
                vendorInvoiceStatusId = em.getReference(vendorInvoiceStatusId.getClass(), vendorInvoiceStatusId.getVendorInvoiceStatusId());
                vendorInvoice.setVendorInvoiceStatusId(vendorInvoiceStatusId);
            }
            Collection<ServiceOrderService> attachedServiceOrderServiceCollection = new ArrayList<ServiceOrderService>();
            for (ServiceOrderService serviceOrderServiceCollectionServiceOrderServiceToAttach : vendorInvoice.getServiceOrderServiceCollection()) {
                serviceOrderServiceCollectionServiceOrderServiceToAttach = em.getReference(serviceOrderServiceCollectionServiceOrderServiceToAttach.getClass(), serviceOrderServiceCollectionServiceOrderServiceToAttach.getServiceOrderServiceId());
                attachedServiceOrderServiceCollection.add(serviceOrderServiceCollectionServiceOrderServiceToAttach);
            }
            vendorInvoice.setServiceOrderServiceCollection(attachedServiceOrderServiceCollection);
            Collection<ServiceOrderService> attachedServiceOrderServiceCollection1 = new ArrayList<ServiceOrderService>();
            for (ServiceOrderService serviceOrderServiceCollection1ServiceOrderServiceToAttach : vendorInvoice.getServiceOrderServiceCollection1()) {
                serviceOrderServiceCollection1ServiceOrderServiceToAttach = em.getReference(serviceOrderServiceCollection1ServiceOrderServiceToAttach.getClass(), serviceOrderServiceCollection1ServiceOrderServiceToAttach.getServiceOrderServiceId());
                attachedServiceOrderServiceCollection1.add(serviceOrderServiceCollection1ServiceOrderServiceToAttach);
            }
            vendorInvoice.setServiceOrderServiceCollection1(attachedServiceOrderServiceCollection1);
            em.persist(vendorInvoice);
            if (vendorInvoiceStatusId != null) {
                vendorInvoiceStatusId.getVendorInvoiceCollection().add(vendorInvoice);
                vendorInvoiceStatusId = em.merge(vendorInvoiceStatusId);
            }
            for (ServiceOrderService serviceOrderServiceCollectionServiceOrderService : vendorInvoice.getServiceOrderServiceCollection()) {
                VendorInvoice oldVendorInvoiceOldIDOfServiceOrderServiceCollectionServiceOrderService = serviceOrderServiceCollectionServiceOrderService.getVendorInvoiceOldID();
                serviceOrderServiceCollectionServiceOrderService.setVendorInvoiceOldID(vendorInvoice);
                serviceOrderServiceCollectionServiceOrderService = em.merge(serviceOrderServiceCollectionServiceOrderService);
                if (oldVendorInvoiceOldIDOfServiceOrderServiceCollectionServiceOrderService != null) {
                    oldVendorInvoiceOldIDOfServiceOrderServiceCollectionServiceOrderService.getServiceOrderServiceCollection().remove(serviceOrderServiceCollectionServiceOrderService);
                    oldVendorInvoiceOldIDOfServiceOrderServiceCollectionServiceOrderService = em.merge(oldVendorInvoiceOldIDOfServiceOrderServiceCollectionServiceOrderService);
                }
            }
            for (ServiceOrderService serviceOrderServiceCollection1ServiceOrderService : vendorInvoice.getServiceOrderServiceCollection1()) {
                VendorInvoice oldVendorInvoiceIDOfServiceOrderServiceCollection1ServiceOrderService = serviceOrderServiceCollection1ServiceOrderService.getVendorInvoiceID();
                serviceOrderServiceCollection1ServiceOrderService.setVendorInvoiceID(vendorInvoice);
                serviceOrderServiceCollection1ServiceOrderService = em.merge(serviceOrderServiceCollection1ServiceOrderService);
                if (oldVendorInvoiceIDOfServiceOrderServiceCollection1ServiceOrderService != null) {
                    oldVendorInvoiceIDOfServiceOrderServiceCollection1ServiceOrderService.getServiceOrderServiceCollection1().remove(serviceOrderServiceCollection1ServiceOrderService);
                    oldVendorInvoiceIDOfServiceOrderServiceCollection1ServiceOrderService = em.merge(oldVendorInvoiceIDOfServiceOrderServiceCollection1ServiceOrderService);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VendorInvoice vendorInvoice) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VendorInvoice persistentVendorInvoice = em.find(VendorInvoice.class, vendorInvoice.getVendorInvoiceID());
            VendorInvoiceStatus vendorInvoiceStatusIdOld = persistentVendorInvoice.getVendorInvoiceStatusId();
            VendorInvoiceStatus vendorInvoiceStatusIdNew = vendorInvoice.getVendorInvoiceStatusId();
            Collection<ServiceOrderService> serviceOrderServiceCollectionOld = persistentVendorInvoice.getServiceOrderServiceCollection();
            Collection<ServiceOrderService> serviceOrderServiceCollectionNew = vendorInvoice.getServiceOrderServiceCollection();
            Collection<ServiceOrderService> serviceOrderServiceCollection1Old = persistentVendorInvoice.getServiceOrderServiceCollection1();
            Collection<ServiceOrderService> serviceOrderServiceCollection1New = vendorInvoice.getServiceOrderServiceCollection1();
            if (vendorInvoiceStatusIdNew != null) {
                vendorInvoiceStatusIdNew = em.getReference(vendorInvoiceStatusIdNew.getClass(), vendorInvoiceStatusIdNew.getVendorInvoiceStatusId());
                vendorInvoice.setVendorInvoiceStatusId(vendorInvoiceStatusIdNew);
            }
            Collection<ServiceOrderService> attachedServiceOrderServiceCollectionNew = new ArrayList<ServiceOrderService>();
            for (ServiceOrderService serviceOrderServiceCollectionNewServiceOrderServiceToAttach : serviceOrderServiceCollectionNew) {
                serviceOrderServiceCollectionNewServiceOrderServiceToAttach = em.getReference(serviceOrderServiceCollectionNewServiceOrderServiceToAttach.getClass(), serviceOrderServiceCollectionNewServiceOrderServiceToAttach.getServiceOrderServiceId());
                attachedServiceOrderServiceCollectionNew.add(serviceOrderServiceCollectionNewServiceOrderServiceToAttach);
            }
            serviceOrderServiceCollectionNew = attachedServiceOrderServiceCollectionNew;
            vendorInvoice.setServiceOrderServiceCollection(serviceOrderServiceCollectionNew);
            Collection<ServiceOrderService> attachedServiceOrderServiceCollection1New = new ArrayList<ServiceOrderService>();
            for (ServiceOrderService serviceOrderServiceCollection1NewServiceOrderServiceToAttach : serviceOrderServiceCollection1New) {
                serviceOrderServiceCollection1NewServiceOrderServiceToAttach = em.getReference(serviceOrderServiceCollection1NewServiceOrderServiceToAttach.getClass(), serviceOrderServiceCollection1NewServiceOrderServiceToAttach.getServiceOrderServiceId());
                attachedServiceOrderServiceCollection1New.add(serviceOrderServiceCollection1NewServiceOrderServiceToAttach);
            }
            serviceOrderServiceCollection1New = attachedServiceOrderServiceCollection1New;
            vendorInvoice.setServiceOrderServiceCollection1(serviceOrderServiceCollection1New);
            vendorInvoice = em.merge(vendorInvoice);
            if (vendorInvoiceStatusIdOld != null && !vendorInvoiceStatusIdOld.equals(vendorInvoiceStatusIdNew)) {
                vendorInvoiceStatusIdOld.getVendorInvoiceCollection().remove(vendorInvoice);
                vendorInvoiceStatusIdOld = em.merge(vendorInvoiceStatusIdOld);
            }
            if (vendorInvoiceStatusIdNew != null && !vendorInvoiceStatusIdNew.equals(vendorInvoiceStatusIdOld)) {
                vendorInvoiceStatusIdNew.getVendorInvoiceCollection().add(vendorInvoice);
                vendorInvoiceStatusIdNew = em.merge(vendorInvoiceStatusIdNew);
            }
            for (ServiceOrderService serviceOrderServiceCollectionOldServiceOrderService : serviceOrderServiceCollectionOld) {
                if (!serviceOrderServiceCollectionNew.contains(serviceOrderServiceCollectionOldServiceOrderService)) {
                    serviceOrderServiceCollectionOldServiceOrderService.setVendorInvoiceOldID(null);
                    serviceOrderServiceCollectionOldServiceOrderService = em.merge(serviceOrderServiceCollectionOldServiceOrderService);
                }
            }
            for (ServiceOrderService serviceOrderServiceCollectionNewServiceOrderService : serviceOrderServiceCollectionNew) {
                if (!serviceOrderServiceCollectionOld.contains(serviceOrderServiceCollectionNewServiceOrderService)) {
                    VendorInvoice oldVendorInvoiceOldIDOfServiceOrderServiceCollectionNewServiceOrderService = serviceOrderServiceCollectionNewServiceOrderService.getVendorInvoiceOldID();
                    serviceOrderServiceCollectionNewServiceOrderService.setVendorInvoiceOldID(vendorInvoice);
                    serviceOrderServiceCollectionNewServiceOrderService = em.merge(serviceOrderServiceCollectionNewServiceOrderService);
                    if (oldVendorInvoiceOldIDOfServiceOrderServiceCollectionNewServiceOrderService != null && !oldVendorInvoiceOldIDOfServiceOrderServiceCollectionNewServiceOrderService.equals(vendorInvoice)) {
                        oldVendorInvoiceOldIDOfServiceOrderServiceCollectionNewServiceOrderService.getServiceOrderServiceCollection().remove(serviceOrderServiceCollectionNewServiceOrderService);
                        oldVendorInvoiceOldIDOfServiceOrderServiceCollectionNewServiceOrderService = em.merge(oldVendorInvoiceOldIDOfServiceOrderServiceCollectionNewServiceOrderService);
                    }
                }
            }
            for (ServiceOrderService serviceOrderServiceCollection1OldServiceOrderService : serviceOrderServiceCollection1Old) {
                if (!serviceOrderServiceCollection1New.contains(serviceOrderServiceCollection1OldServiceOrderService)) {
                    serviceOrderServiceCollection1OldServiceOrderService.setVendorInvoiceID(null);
                    serviceOrderServiceCollection1OldServiceOrderService = em.merge(serviceOrderServiceCollection1OldServiceOrderService);
                }
            }
            for (ServiceOrderService serviceOrderServiceCollection1NewServiceOrderService : serviceOrderServiceCollection1New) {
                if (!serviceOrderServiceCollection1Old.contains(serviceOrderServiceCollection1NewServiceOrderService)) {
                    VendorInvoice oldVendorInvoiceIDOfServiceOrderServiceCollection1NewServiceOrderService = serviceOrderServiceCollection1NewServiceOrderService.getVendorInvoiceID();
                    serviceOrderServiceCollection1NewServiceOrderService.setVendorInvoiceID(vendorInvoice);
                    serviceOrderServiceCollection1NewServiceOrderService = em.merge(serviceOrderServiceCollection1NewServiceOrderService);
                    if (oldVendorInvoiceIDOfServiceOrderServiceCollection1NewServiceOrderService != null && !oldVendorInvoiceIDOfServiceOrderServiceCollection1NewServiceOrderService.equals(vendorInvoice)) {
                        oldVendorInvoiceIDOfServiceOrderServiceCollection1NewServiceOrderService.getServiceOrderServiceCollection1().remove(serviceOrderServiceCollection1NewServiceOrderService);
                        oldVendorInvoiceIDOfServiceOrderServiceCollection1NewServiceOrderService = em.merge(oldVendorInvoiceIDOfServiceOrderServiceCollection1NewServiceOrderService);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vendorInvoice.getVendorInvoiceID();
                if (findVendorInvoice(id) == null) {
                    throw new NonexistentEntityException("The vendorInvoice with id " + id + " no longer exists.");
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
            VendorInvoice vendorInvoice;
            try {
                vendorInvoice = em.getReference(VendorInvoice.class, id);
                vendorInvoice.getVendorInvoiceID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vendorInvoice with id " + id + " no longer exists.", enfe);
            }
            VendorInvoiceStatus vendorInvoiceStatusId = vendorInvoice.getVendorInvoiceStatusId();
            if (vendorInvoiceStatusId != null) {
                vendorInvoiceStatusId.getVendorInvoiceCollection().remove(vendorInvoice);
                vendorInvoiceStatusId = em.merge(vendorInvoiceStatusId);
            }
            Collection<ServiceOrderService> serviceOrderServiceCollection = vendorInvoice.getServiceOrderServiceCollection();
            for (ServiceOrderService serviceOrderServiceCollectionServiceOrderService : serviceOrderServiceCollection) {
                serviceOrderServiceCollectionServiceOrderService.setVendorInvoiceOldID(null);
                serviceOrderServiceCollectionServiceOrderService = em.merge(serviceOrderServiceCollectionServiceOrderService);
            }
            Collection<ServiceOrderService> serviceOrderServiceCollection1 = vendorInvoice.getServiceOrderServiceCollection1();
            for (ServiceOrderService serviceOrderServiceCollection1ServiceOrderService : serviceOrderServiceCollection1) {
                serviceOrderServiceCollection1ServiceOrderService.setVendorInvoiceID(null);
                serviceOrderServiceCollection1ServiceOrderService = em.merge(serviceOrderServiceCollection1ServiceOrderService);
            }
            em.remove(vendorInvoice);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VendorInvoice> findVendorInvoiceEntities() {
        return findVendorInvoiceEntities(true, -1, -1);
    }

    public List<VendorInvoice> findVendorInvoiceEntities(int maxResults, int firstResult) {
        return findVendorInvoiceEntities(false, maxResults, firstResult);
    }

    private List<VendorInvoice> findVendorInvoiceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VendorInvoice.class));
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

    public VendorInvoice findVendorInvoice(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VendorInvoice.class, id);
        } finally {
            em.close();
        }
    }

    public int getVendorInvoiceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VendorInvoice> rt = cq.from(VendorInvoice.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
