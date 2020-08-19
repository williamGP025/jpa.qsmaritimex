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
import mx.tab.wgp.qsmaritimex.entidades.VendorInvoice;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.IllegalOrphanException;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.VendorInvoiceStatus;

/**
 *
 * @author WilliamGP025
 */
public class VendorInvoiceStatusJpaController implements Serializable {

    public VendorInvoiceStatusJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VendorInvoiceStatus vendorInvoiceStatus) {
        if (vendorInvoiceStatus.getVendorInvoiceCollection() == null) {
            vendorInvoiceStatus.setVendorInvoiceCollection(new ArrayList<VendorInvoice>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<VendorInvoice> attachedVendorInvoiceCollection = new ArrayList<VendorInvoice>();
            for (VendorInvoice vendorInvoiceCollectionVendorInvoiceToAttach : vendorInvoiceStatus.getVendorInvoiceCollection()) {
                vendorInvoiceCollectionVendorInvoiceToAttach = em.getReference(vendorInvoiceCollectionVendorInvoiceToAttach.getClass(), vendorInvoiceCollectionVendorInvoiceToAttach.getVendorInvoiceID());
                attachedVendorInvoiceCollection.add(vendorInvoiceCollectionVendorInvoiceToAttach);
            }
            vendorInvoiceStatus.setVendorInvoiceCollection(attachedVendorInvoiceCollection);
            em.persist(vendorInvoiceStatus);
            for (VendorInvoice vendorInvoiceCollectionVendorInvoice : vendorInvoiceStatus.getVendorInvoiceCollection()) {
                VendorInvoiceStatus oldVendorInvoiceStatusIdOfVendorInvoiceCollectionVendorInvoice = vendorInvoiceCollectionVendorInvoice.getVendorInvoiceStatusId();
                vendorInvoiceCollectionVendorInvoice.setVendorInvoiceStatusId(vendorInvoiceStatus);
                vendorInvoiceCollectionVendorInvoice = em.merge(vendorInvoiceCollectionVendorInvoice);
                if (oldVendorInvoiceStatusIdOfVendorInvoiceCollectionVendorInvoice != null) {
                    oldVendorInvoiceStatusIdOfVendorInvoiceCollectionVendorInvoice.getVendorInvoiceCollection().remove(vendorInvoiceCollectionVendorInvoice);
                    oldVendorInvoiceStatusIdOfVendorInvoiceCollectionVendorInvoice = em.merge(oldVendorInvoiceStatusIdOfVendorInvoiceCollectionVendorInvoice);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VendorInvoiceStatus vendorInvoiceStatus) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VendorInvoiceStatus persistentVendorInvoiceStatus = em.find(VendorInvoiceStatus.class, vendorInvoiceStatus.getVendorInvoiceStatusId());
            Collection<VendorInvoice> vendorInvoiceCollectionOld = persistentVendorInvoiceStatus.getVendorInvoiceCollection();
            Collection<VendorInvoice> vendorInvoiceCollectionNew = vendorInvoiceStatus.getVendorInvoiceCollection();
            List<String> illegalOrphanMessages = null;
            for (VendorInvoice vendorInvoiceCollectionOldVendorInvoice : vendorInvoiceCollectionOld) {
                if (!vendorInvoiceCollectionNew.contains(vendorInvoiceCollectionOldVendorInvoice)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain VendorInvoice " + vendorInvoiceCollectionOldVendorInvoice + " since its vendorInvoiceStatusId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<VendorInvoice> attachedVendorInvoiceCollectionNew = new ArrayList<VendorInvoice>();
            for (VendorInvoice vendorInvoiceCollectionNewVendorInvoiceToAttach : vendorInvoiceCollectionNew) {
                vendorInvoiceCollectionNewVendorInvoiceToAttach = em.getReference(vendorInvoiceCollectionNewVendorInvoiceToAttach.getClass(), vendorInvoiceCollectionNewVendorInvoiceToAttach.getVendorInvoiceID());
                attachedVendorInvoiceCollectionNew.add(vendorInvoiceCollectionNewVendorInvoiceToAttach);
            }
            vendorInvoiceCollectionNew = attachedVendorInvoiceCollectionNew;
            vendorInvoiceStatus.setVendorInvoiceCollection(vendorInvoiceCollectionNew);
            vendorInvoiceStatus = em.merge(vendorInvoiceStatus);
            for (VendorInvoice vendorInvoiceCollectionNewVendorInvoice : vendorInvoiceCollectionNew) {
                if (!vendorInvoiceCollectionOld.contains(vendorInvoiceCollectionNewVendorInvoice)) {
                    VendorInvoiceStatus oldVendorInvoiceStatusIdOfVendorInvoiceCollectionNewVendorInvoice = vendorInvoiceCollectionNewVendorInvoice.getVendorInvoiceStatusId();
                    vendorInvoiceCollectionNewVendorInvoice.setVendorInvoiceStatusId(vendorInvoiceStatus);
                    vendorInvoiceCollectionNewVendorInvoice = em.merge(vendorInvoiceCollectionNewVendorInvoice);
                    if (oldVendorInvoiceStatusIdOfVendorInvoiceCollectionNewVendorInvoice != null && !oldVendorInvoiceStatusIdOfVendorInvoiceCollectionNewVendorInvoice.equals(vendorInvoiceStatus)) {
                        oldVendorInvoiceStatusIdOfVendorInvoiceCollectionNewVendorInvoice.getVendorInvoiceCollection().remove(vendorInvoiceCollectionNewVendorInvoice);
                        oldVendorInvoiceStatusIdOfVendorInvoiceCollectionNewVendorInvoice = em.merge(oldVendorInvoiceStatusIdOfVendorInvoiceCollectionNewVendorInvoice);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = vendorInvoiceStatus.getVendorInvoiceStatusId();
                if (findVendorInvoiceStatus(id) == null) {
                    throw new NonexistentEntityException("The vendorInvoiceStatus with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Short id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VendorInvoiceStatus vendorInvoiceStatus;
            try {
                vendorInvoiceStatus = em.getReference(VendorInvoiceStatus.class, id);
                vendorInvoiceStatus.getVendorInvoiceStatusId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vendorInvoiceStatus with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<VendorInvoice> vendorInvoiceCollectionOrphanCheck = vendorInvoiceStatus.getVendorInvoiceCollection();
            for (VendorInvoice vendorInvoiceCollectionOrphanCheckVendorInvoice : vendorInvoiceCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This VendorInvoiceStatus (" + vendorInvoiceStatus + ") cannot be destroyed since the VendorInvoice " + vendorInvoiceCollectionOrphanCheckVendorInvoice + " in its vendorInvoiceCollection field has a non-nullable vendorInvoiceStatusId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(vendorInvoiceStatus);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VendorInvoiceStatus> findVendorInvoiceStatusEntities() {
        return findVendorInvoiceStatusEntities(true, -1, -1);
    }

    public List<VendorInvoiceStatus> findVendorInvoiceStatusEntities(int maxResults, int firstResult) {
        return findVendorInvoiceStatusEntities(false, maxResults, firstResult);
    }

    private List<VendorInvoiceStatus> findVendorInvoiceStatusEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VendorInvoiceStatus.class));
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

    public VendorInvoiceStatus findVendorInvoiceStatus(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VendorInvoiceStatus.class, id);
        } finally {
            em.close();
        }
    }

    public int getVendorInvoiceStatusCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VendorInvoiceStatus> rt = cq.from(VendorInvoiceStatus.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
