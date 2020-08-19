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
import mx.tab.wgp.qsmaritimex.entidades.Currency;
import mx.tab.wgp.qsmaritimex.entidades.Service;
import mx.tab.wgp.qsmaritimex.entidades.ServiceOrder;
import mx.tab.wgp.qsmaritimex.entidades.ServiceOrderServicesStatus;
import mx.tab.wgp.qsmaritimex.entidades.ServiceOrderServicesType;
import mx.tab.wgp.qsmaritimex.entidades.ServiceTransferLog;
import mx.tab.wgp.qsmaritimex.entidades.User;
import mx.tab.wgp.qsmaritimex.entidades.VendorInvoice;
import mx.tab.wgp.qsmaritimex.entidades.VendorProfile;
import mx.tab.wgp.qsmaritimex.entidades.VendorFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.IllegalOrphanException;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.ServiceOrderService;

/**
 *
 * @author WilliamGP025
 */
public class ServiceOrderServiceJpaController implements Serializable {

    public ServiceOrderServiceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServiceOrderService serviceOrderService) {
        if (serviceOrderService.getVendorFileCollection() == null) {
            serviceOrderService.setVendorFileCollection(new ArrayList<VendorFile>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Currency currencyId = serviceOrderService.getCurrencyId();
            if (currencyId != null) {
                currencyId = em.getReference(currencyId.getClass(), currencyId.getCurrencyId());
                serviceOrderService.setCurrencyId(currencyId);
            }
            Service serviceId = serviceOrderService.getServiceId();
            if (serviceId != null) {
                serviceId = em.getReference(serviceId.getClass(), serviceId.getServiceId());
                serviceOrderService.setServiceId(serviceId);
            }
            ServiceOrder serviceOrderId = serviceOrderService.getServiceOrderId();
            if (serviceOrderId != null) {
                serviceOrderId = em.getReference(serviceOrderId.getClass(), serviceOrderId.getServiceOrderId());
                serviceOrderService.setServiceOrderId(serviceOrderId);
            }
            ServiceOrderServicesStatus serviceOrderServiceStatusId = serviceOrderService.getServiceOrderServiceStatusId();
            if (serviceOrderServiceStatusId != null) {
                serviceOrderServiceStatusId = em.getReference(serviceOrderServiceStatusId.getClass(), serviceOrderServiceStatusId.getServiceOrderServiceStatusId());
                serviceOrderService.setServiceOrderServiceStatusId(serviceOrderServiceStatusId);
            }
            ServiceOrderServicesType serviceOrderServiceTypeId = serviceOrderService.getServiceOrderServiceTypeId();
            if (serviceOrderServiceTypeId != null) {
                serviceOrderServiceTypeId = em.getReference(serviceOrderServiceTypeId.getClass(), serviceOrderServiceTypeId.getServiceOrderServicesTypeId());
                serviceOrderService.setServiceOrderServiceTypeId(serviceOrderServiceTypeId);
            }
            ServiceTransferLog serviceTransferLogId = serviceOrderService.getServiceTransferLogId();
            if (serviceTransferLogId != null) {
                serviceTransferLogId = em.getReference(serviceTransferLogId.getClass(), serviceTransferLogId.getServiceTransferLogId());
                serviceOrderService.setServiceTransferLogId(serviceTransferLogId);
            }
            User updateUserId = serviceOrderService.getUpdateUserId();
            if (updateUserId != null) {
                updateUserId = em.getReference(updateUserId.getClass(), updateUserId.getUserId());
                serviceOrderService.setUpdateUserId(updateUserId);
            }
            VendorInvoice vendorInvoiceOldID = serviceOrderService.getVendorInvoiceOldID();
            if (vendorInvoiceOldID != null) {
                vendorInvoiceOldID = em.getReference(vendorInvoiceOldID.getClass(), vendorInvoiceOldID.getVendorInvoiceID());
                serviceOrderService.setVendorInvoiceOldID(vendorInvoiceOldID);
            }
            VendorInvoice vendorInvoiceID = serviceOrderService.getVendorInvoiceID();
            if (vendorInvoiceID != null) {
                vendorInvoiceID = em.getReference(vendorInvoiceID.getClass(), vendorInvoiceID.getVendorInvoiceID());
                serviceOrderService.setVendorInvoiceID(vendorInvoiceID);
            }
            VendorProfile vendorId = serviceOrderService.getVendorId();
            if (vendorId != null) {
                vendorId = em.getReference(vendorId.getClass(), vendorId.getVendorId());
                serviceOrderService.setVendorId(vendorId);
            }
            Collection<VendorFile> attachedVendorFileCollection = new ArrayList<VendorFile>();
            for (VendorFile vendorFileCollectionVendorFileToAttach : serviceOrderService.getVendorFileCollection()) {
                vendorFileCollectionVendorFileToAttach = em.getReference(vendorFileCollectionVendorFileToAttach.getClass(), vendorFileCollectionVendorFileToAttach.getVendorFileId());
                attachedVendorFileCollection.add(vendorFileCollectionVendorFileToAttach);
            }
            serviceOrderService.setVendorFileCollection(attachedVendorFileCollection);
            em.persist(serviceOrderService);
            if (currencyId != null) {
                currencyId.getServiceOrderServiceCollection().add(serviceOrderService);
                currencyId = em.merge(currencyId);
            }
            if (serviceId != null) {
                serviceId.getServiceOrderServiceCollection().add(serviceOrderService);
                serviceId = em.merge(serviceId);
            }
            if (serviceOrderId != null) {
                serviceOrderId.getServiceOrderServiceCollection().add(serviceOrderService);
                serviceOrderId = em.merge(serviceOrderId);
            }
            if (serviceOrderServiceStatusId != null) {
                serviceOrderServiceStatusId.getServiceOrderServiceCollection().add(serviceOrderService);
                serviceOrderServiceStatusId = em.merge(serviceOrderServiceStatusId);
            }
            if (serviceOrderServiceTypeId != null) {
                serviceOrderServiceTypeId.getServiceOrderServiceCollection().add(serviceOrderService);
                serviceOrderServiceTypeId = em.merge(serviceOrderServiceTypeId);
            }
            if (serviceTransferLogId != null) {
                serviceTransferLogId.getServiceOrderServiceCollection().add(serviceOrderService);
                serviceTransferLogId = em.merge(serviceTransferLogId);
            }
            if (updateUserId != null) {
                updateUserId.getServiceOrderServiceCollection().add(serviceOrderService);
                updateUserId = em.merge(updateUserId);
            }
            if (vendorInvoiceOldID != null) {
                vendorInvoiceOldID.getServiceOrderServiceCollection().add(serviceOrderService);
                vendorInvoiceOldID = em.merge(vendorInvoiceOldID);
            }
            if (vendorInvoiceID != null) {
                vendorInvoiceID.getServiceOrderServiceCollection().add(serviceOrderService);
                vendorInvoiceID = em.merge(vendorInvoiceID);
            }
            if (vendorId != null) {
                vendorId.getServiceOrderServiceCollection().add(serviceOrderService);
                vendorId = em.merge(vendorId);
            }
            for (VendorFile vendorFileCollectionVendorFile : serviceOrderService.getVendorFileCollection()) {
                ServiceOrderService oldServiceOrderServiceIdOfVendorFileCollectionVendorFile = vendorFileCollectionVendorFile.getServiceOrderServiceId();
                vendorFileCollectionVendorFile.setServiceOrderServiceId(serviceOrderService);
                vendorFileCollectionVendorFile = em.merge(vendorFileCollectionVendorFile);
                if (oldServiceOrderServiceIdOfVendorFileCollectionVendorFile != null) {
                    oldServiceOrderServiceIdOfVendorFileCollectionVendorFile.getVendorFileCollection().remove(vendorFileCollectionVendorFile);
                    oldServiceOrderServiceIdOfVendorFileCollectionVendorFile = em.merge(oldServiceOrderServiceIdOfVendorFileCollectionVendorFile);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServiceOrderService serviceOrderService) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceOrderService persistentServiceOrderService = em.find(ServiceOrderService.class, serviceOrderService.getServiceOrderServiceId());
            Currency currencyIdOld = persistentServiceOrderService.getCurrencyId();
            Currency currencyIdNew = serviceOrderService.getCurrencyId();
            Service serviceIdOld = persistentServiceOrderService.getServiceId();
            Service serviceIdNew = serviceOrderService.getServiceId();
            ServiceOrder serviceOrderIdOld = persistentServiceOrderService.getServiceOrderId();
            ServiceOrder serviceOrderIdNew = serviceOrderService.getServiceOrderId();
            ServiceOrderServicesStatus serviceOrderServiceStatusIdOld = persistentServiceOrderService.getServiceOrderServiceStatusId();
            ServiceOrderServicesStatus serviceOrderServiceStatusIdNew = serviceOrderService.getServiceOrderServiceStatusId();
            ServiceOrderServicesType serviceOrderServiceTypeIdOld = persistentServiceOrderService.getServiceOrderServiceTypeId();
            ServiceOrderServicesType serviceOrderServiceTypeIdNew = serviceOrderService.getServiceOrderServiceTypeId();
            ServiceTransferLog serviceTransferLogIdOld = persistentServiceOrderService.getServiceTransferLogId();
            ServiceTransferLog serviceTransferLogIdNew = serviceOrderService.getServiceTransferLogId();
            User updateUserIdOld = persistentServiceOrderService.getUpdateUserId();
            User updateUserIdNew = serviceOrderService.getUpdateUserId();
            VendorInvoice vendorInvoiceOldIDOld = persistentServiceOrderService.getVendorInvoiceOldID();
            VendorInvoice vendorInvoiceOldIDNew = serviceOrderService.getVendorInvoiceOldID();
            VendorInvoice vendorInvoiceIDOld = persistentServiceOrderService.getVendorInvoiceID();
            VendorInvoice vendorInvoiceIDNew = serviceOrderService.getVendorInvoiceID();
            VendorProfile vendorIdOld = persistentServiceOrderService.getVendorId();
            VendorProfile vendorIdNew = serviceOrderService.getVendorId();
            Collection<VendorFile> vendorFileCollectionOld = persistentServiceOrderService.getVendorFileCollection();
            Collection<VendorFile> vendorFileCollectionNew = serviceOrderService.getVendorFileCollection();
            List<String> illegalOrphanMessages = null;
            for (VendorFile vendorFileCollectionOldVendorFile : vendorFileCollectionOld) {
                if (!vendorFileCollectionNew.contains(vendorFileCollectionOldVendorFile)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain VendorFile " + vendorFileCollectionOldVendorFile + " since its serviceOrderServiceId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (currencyIdNew != null) {
                currencyIdNew = em.getReference(currencyIdNew.getClass(), currencyIdNew.getCurrencyId());
                serviceOrderService.setCurrencyId(currencyIdNew);
            }
            if (serviceIdNew != null) {
                serviceIdNew = em.getReference(serviceIdNew.getClass(), serviceIdNew.getServiceId());
                serviceOrderService.setServiceId(serviceIdNew);
            }
            if (serviceOrderIdNew != null) {
                serviceOrderIdNew = em.getReference(serviceOrderIdNew.getClass(), serviceOrderIdNew.getServiceOrderId());
                serviceOrderService.setServiceOrderId(serviceOrderIdNew);
            }
            if (serviceOrderServiceStatusIdNew != null) {
                serviceOrderServiceStatusIdNew = em.getReference(serviceOrderServiceStatusIdNew.getClass(), serviceOrderServiceStatusIdNew.getServiceOrderServiceStatusId());
                serviceOrderService.setServiceOrderServiceStatusId(serviceOrderServiceStatusIdNew);
            }
            if (serviceOrderServiceTypeIdNew != null) {
                serviceOrderServiceTypeIdNew = em.getReference(serviceOrderServiceTypeIdNew.getClass(), serviceOrderServiceTypeIdNew.getServiceOrderServicesTypeId());
                serviceOrderService.setServiceOrderServiceTypeId(serviceOrderServiceTypeIdNew);
            }
            if (serviceTransferLogIdNew != null) {
                serviceTransferLogIdNew = em.getReference(serviceTransferLogIdNew.getClass(), serviceTransferLogIdNew.getServiceTransferLogId());
                serviceOrderService.setServiceTransferLogId(serviceTransferLogIdNew);
            }
            if (updateUserIdNew != null) {
                updateUserIdNew = em.getReference(updateUserIdNew.getClass(), updateUserIdNew.getUserId());
                serviceOrderService.setUpdateUserId(updateUserIdNew);
            }
            if (vendorInvoiceOldIDNew != null) {
                vendorInvoiceOldIDNew = em.getReference(vendorInvoiceOldIDNew.getClass(), vendorInvoiceOldIDNew.getVendorInvoiceID());
                serviceOrderService.setVendorInvoiceOldID(vendorInvoiceOldIDNew);
            }
            if (vendorInvoiceIDNew != null) {
                vendorInvoiceIDNew = em.getReference(vendorInvoiceIDNew.getClass(), vendorInvoiceIDNew.getVendorInvoiceID());
                serviceOrderService.setVendorInvoiceID(vendorInvoiceIDNew);
            }
            if (vendorIdNew != null) {
                vendorIdNew = em.getReference(vendorIdNew.getClass(), vendorIdNew.getVendorId());
                serviceOrderService.setVendorId(vendorIdNew);
            }
            Collection<VendorFile> attachedVendorFileCollectionNew = new ArrayList<VendorFile>();
            for (VendorFile vendorFileCollectionNewVendorFileToAttach : vendorFileCollectionNew) {
                vendorFileCollectionNewVendorFileToAttach = em.getReference(vendorFileCollectionNewVendorFileToAttach.getClass(), vendorFileCollectionNewVendorFileToAttach.getVendorFileId());
                attachedVendorFileCollectionNew.add(vendorFileCollectionNewVendorFileToAttach);
            }
            vendorFileCollectionNew = attachedVendorFileCollectionNew;
            serviceOrderService.setVendorFileCollection(vendorFileCollectionNew);
            serviceOrderService = em.merge(serviceOrderService);
            if (currencyIdOld != null && !currencyIdOld.equals(currencyIdNew)) {
                currencyIdOld.getServiceOrderServiceCollection().remove(serviceOrderService);
                currencyIdOld = em.merge(currencyIdOld);
            }
            if (currencyIdNew != null && !currencyIdNew.equals(currencyIdOld)) {
                currencyIdNew.getServiceOrderServiceCollection().add(serviceOrderService);
                currencyIdNew = em.merge(currencyIdNew);
            }
            if (serviceIdOld != null && !serviceIdOld.equals(serviceIdNew)) {
                serviceIdOld.getServiceOrderServiceCollection().remove(serviceOrderService);
                serviceIdOld = em.merge(serviceIdOld);
            }
            if (serviceIdNew != null && !serviceIdNew.equals(serviceIdOld)) {
                serviceIdNew.getServiceOrderServiceCollection().add(serviceOrderService);
                serviceIdNew = em.merge(serviceIdNew);
            }
            if (serviceOrderIdOld != null && !serviceOrderIdOld.equals(serviceOrderIdNew)) {
                serviceOrderIdOld.getServiceOrderServiceCollection().remove(serviceOrderService);
                serviceOrderIdOld = em.merge(serviceOrderIdOld);
            }
            if (serviceOrderIdNew != null && !serviceOrderIdNew.equals(serviceOrderIdOld)) {
                serviceOrderIdNew.getServiceOrderServiceCollection().add(serviceOrderService);
                serviceOrderIdNew = em.merge(serviceOrderIdNew);
            }
            if (serviceOrderServiceStatusIdOld != null && !serviceOrderServiceStatusIdOld.equals(serviceOrderServiceStatusIdNew)) {
                serviceOrderServiceStatusIdOld.getServiceOrderServiceCollection().remove(serviceOrderService);
                serviceOrderServiceStatusIdOld = em.merge(serviceOrderServiceStatusIdOld);
            }
            if (serviceOrderServiceStatusIdNew != null && !serviceOrderServiceStatusIdNew.equals(serviceOrderServiceStatusIdOld)) {
                serviceOrderServiceStatusIdNew.getServiceOrderServiceCollection().add(serviceOrderService);
                serviceOrderServiceStatusIdNew = em.merge(serviceOrderServiceStatusIdNew);
            }
            if (serviceOrderServiceTypeIdOld != null && !serviceOrderServiceTypeIdOld.equals(serviceOrderServiceTypeIdNew)) {
                serviceOrderServiceTypeIdOld.getServiceOrderServiceCollection().remove(serviceOrderService);
                serviceOrderServiceTypeIdOld = em.merge(serviceOrderServiceTypeIdOld);
            }
            if (serviceOrderServiceTypeIdNew != null && !serviceOrderServiceTypeIdNew.equals(serviceOrderServiceTypeIdOld)) {
                serviceOrderServiceTypeIdNew.getServiceOrderServiceCollection().add(serviceOrderService);
                serviceOrderServiceTypeIdNew = em.merge(serviceOrderServiceTypeIdNew);
            }
            if (serviceTransferLogIdOld != null && !serviceTransferLogIdOld.equals(serviceTransferLogIdNew)) {
                serviceTransferLogIdOld.getServiceOrderServiceCollection().remove(serviceOrderService);
                serviceTransferLogIdOld = em.merge(serviceTransferLogIdOld);
            }
            if (serviceTransferLogIdNew != null && !serviceTransferLogIdNew.equals(serviceTransferLogIdOld)) {
                serviceTransferLogIdNew.getServiceOrderServiceCollection().add(serviceOrderService);
                serviceTransferLogIdNew = em.merge(serviceTransferLogIdNew);
            }
            if (updateUserIdOld != null && !updateUserIdOld.equals(updateUserIdNew)) {
                updateUserIdOld.getServiceOrderServiceCollection().remove(serviceOrderService);
                updateUserIdOld = em.merge(updateUserIdOld);
            }
            if (updateUserIdNew != null && !updateUserIdNew.equals(updateUserIdOld)) {
                updateUserIdNew.getServiceOrderServiceCollection().add(serviceOrderService);
                updateUserIdNew = em.merge(updateUserIdNew);
            }
            if (vendorInvoiceOldIDOld != null && !vendorInvoiceOldIDOld.equals(vendorInvoiceOldIDNew)) {
                vendorInvoiceOldIDOld.getServiceOrderServiceCollection().remove(serviceOrderService);
                vendorInvoiceOldIDOld = em.merge(vendorInvoiceOldIDOld);
            }
            if (vendorInvoiceOldIDNew != null && !vendorInvoiceOldIDNew.equals(vendorInvoiceOldIDOld)) {
                vendorInvoiceOldIDNew.getServiceOrderServiceCollection().add(serviceOrderService);
                vendorInvoiceOldIDNew = em.merge(vendorInvoiceOldIDNew);
            }
            if (vendorInvoiceIDOld != null && !vendorInvoiceIDOld.equals(vendorInvoiceIDNew)) {
                vendorInvoiceIDOld.getServiceOrderServiceCollection().remove(serviceOrderService);
                vendorInvoiceIDOld = em.merge(vendorInvoiceIDOld);
            }
            if (vendorInvoiceIDNew != null && !vendorInvoiceIDNew.equals(vendorInvoiceIDOld)) {
                vendorInvoiceIDNew.getServiceOrderServiceCollection().add(serviceOrderService);
                vendorInvoiceIDNew = em.merge(vendorInvoiceIDNew);
            }
            if (vendorIdOld != null && !vendorIdOld.equals(vendorIdNew)) {
                vendorIdOld.getServiceOrderServiceCollection().remove(serviceOrderService);
                vendorIdOld = em.merge(vendorIdOld);
            }
            if (vendorIdNew != null && !vendorIdNew.equals(vendorIdOld)) {
                vendorIdNew.getServiceOrderServiceCollection().add(serviceOrderService);
                vendorIdNew = em.merge(vendorIdNew);
            }
            for (VendorFile vendorFileCollectionNewVendorFile : vendorFileCollectionNew) {
                if (!vendorFileCollectionOld.contains(vendorFileCollectionNewVendorFile)) {
                    ServiceOrderService oldServiceOrderServiceIdOfVendorFileCollectionNewVendorFile = vendorFileCollectionNewVendorFile.getServiceOrderServiceId();
                    vendorFileCollectionNewVendorFile.setServiceOrderServiceId(serviceOrderService);
                    vendorFileCollectionNewVendorFile = em.merge(vendorFileCollectionNewVendorFile);
                    if (oldServiceOrderServiceIdOfVendorFileCollectionNewVendorFile != null && !oldServiceOrderServiceIdOfVendorFileCollectionNewVendorFile.equals(serviceOrderService)) {
                        oldServiceOrderServiceIdOfVendorFileCollectionNewVendorFile.getVendorFileCollection().remove(vendorFileCollectionNewVendorFile);
                        oldServiceOrderServiceIdOfVendorFileCollectionNewVendorFile = em.merge(oldServiceOrderServiceIdOfVendorFileCollectionNewVendorFile);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = serviceOrderService.getServiceOrderServiceId();
                if (findServiceOrderService(id) == null) {
                    throw new NonexistentEntityException("The serviceOrderService with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceOrderService serviceOrderService;
            try {
                serviceOrderService = em.getReference(ServiceOrderService.class, id);
                serviceOrderService.getServiceOrderServiceId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The serviceOrderService with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<VendorFile> vendorFileCollectionOrphanCheck = serviceOrderService.getVendorFileCollection();
            for (VendorFile vendorFileCollectionOrphanCheckVendorFile : vendorFileCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ServiceOrderService (" + serviceOrderService + ") cannot be destroyed since the VendorFile " + vendorFileCollectionOrphanCheckVendorFile + " in its vendorFileCollection field has a non-nullable serviceOrderServiceId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Currency currencyId = serviceOrderService.getCurrencyId();
            if (currencyId != null) {
                currencyId.getServiceOrderServiceCollection().remove(serviceOrderService);
                currencyId = em.merge(currencyId);
            }
            Service serviceId = serviceOrderService.getServiceId();
            if (serviceId != null) {
                serviceId.getServiceOrderServiceCollection().remove(serviceOrderService);
                serviceId = em.merge(serviceId);
            }
            ServiceOrder serviceOrderId = serviceOrderService.getServiceOrderId();
            if (serviceOrderId != null) {
                serviceOrderId.getServiceOrderServiceCollection().remove(serviceOrderService);
                serviceOrderId = em.merge(serviceOrderId);
            }
            ServiceOrderServicesStatus serviceOrderServiceStatusId = serviceOrderService.getServiceOrderServiceStatusId();
            if (serviceOrderServiceStatusId != null) {
                serviceOrderServiceStatusId.getServiceOrderServiceCollection().remove(serviceOrderService);
                serviceOrderServiceStatusId = em.merge(serviceOrderServiceStatusId);
            }
            ServiceOrderServicesType serviceOrderServiceTypeId = serviceOrderService.getServiceOrderServiceTypeId();
            if (serviceOrderServiceTypeId != null) {
                serviceOrderServiceTypeId.getServiceOrderServiceCollection().remove(serviceOrderService);
                serviceOrderServiceTypeId = em.merge(serviceOrderServiceTypeId);
            }
            ServiceTransferLog serviceTransferLogId = serviceOrderService.getServiceTransferLogId();
            if (serviceTransferLogId != null) {
                serviceTransferLogId.getServiceOrderServiceCollection().remove(serviceOrderService);
                serviceTransferLogId = em.merge(serviceTransferLogId);
            }
            User updateUserId = serviceOrderService.getUpdateUserId();
            if (updateUserId != null) {
                updateUserId.getServiceOrderServiceCollection().remove(serviceOrderService);
                updateUserId = em.merge(updateUserId);
            }
            VendorInvoice vendorInvoiceOldID = serviceOrderService.getVendorInvoiceOldID();
            if (vendorInvoiceOldID != null) {
                vendorInvoiceOldID.getServiceOrderServiceCollection().remove(serviceOrderService);
                vendorInvoiceOldID = em.merge(vendorInvoiceOldID);
            }
            VendorInvoice vendorInvoiceID = serviceOrderService.getVendorInvoiceID();
            if (vendorInvoiceID != null) {
                vendorInvoiceID.getServiceOrderServiceCollection().remove(serviceOrderService);
                vendorInvoiceID = em.merge(vendorInvoiceID);
            }
            VendorProfile vendorId = serviceOrderService.getVendorId();
            if (vendorId != null) {
                vendorId.getServiceOrderServiceCollection().remove(serviceOrderService);
                vendorId = em.merge(vendorId);
            }
            em.remove(serviceOrderService);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServiceOrderService> findServiceOrderServiceEntities() {
        return findServiceOrderServiceEntities(true, -1, -1);
    }

    public List<ServiceOrderService> findServiceOrderServiceEntities(int maxResults, int firstResult) {
        return findServiceOrderServiceEntities(false, maxResults, firstResult);
    }

    private List<ServiceOrderService> findServiceOrderServiceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServiceOrderService.class));
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

    public ServiceOrderService findServiceOrderService(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServiceOrderService.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiceOrderServiceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServiceOrderService> rt = cq.from(ServiceOrderService.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
