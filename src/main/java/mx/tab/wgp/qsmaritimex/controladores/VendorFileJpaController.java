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
import mx.tab.wgp.qsmaritimex.controladores.exceptions.PreexistingEntityException;
import mx.tab.wgp.qsmaritimex.entidades.VendorFile;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrderService;

/**
 *
 * @author William
 */
public class VendorFileJpaController implements Serializable {

    public VendorFileJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VendorFile vendorFile) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceOrderService serviceOrderServiceId = vendorFile.getServiceOrderServiceId();
            if (serviceOrderServiceId != null) {
                serviceOrderServiceId = em.getReference(serviceOrderServiceId.getClass(), serviceOrderServiceId.getServiceOrderServiceId());
                vendorFile.setServiceOrderServiceId(serviceOrderServiceId);
            }
            em.persist(vendorFile);
            if (serviceOrderServiceId != null) {
                serviceOrderServiceId.getVendorFileCollection().add(vendorFile);
                serviceOrderServiceId = em.merge(serviceOrderServiceId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVendorFile(vendorFile.getVendorFileId()) != null) {
                throw new PreexistingEntityException("VendorFile " + vendorFile + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VendorFile vendorFile) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VendorFile persistentVendorFile = em.find(VendorFile.class, vendorFile.getVendorFileId());
            ServiceOrderService serviceOrderServiceIdOld = persistentVendorFile.getServiceOrderServiceId();
            ServiceOrderService serviceOrderServiceIdNew = vendorFile.getServiceOrderServiceId();
            if (serviceOrderServiceIdNew != null) {
                serviceOrderServiceIdNew = em.getReference(serviceOrderServiceIdNew.getClass(), serviceOrderServiceIdNew.getServiceOrderServiceId());
                vendorFile.setServiceOrderServiceId(serviceOrderServiceIdNew);
            }
            vendorFile = em.merge(vendorFile);
            if (serviceOrderServiceIdOld != null && !serviceOrderServiceIdOld.equals(serviceOrderServiceIdNew)) {
                serviceOrderServiceIdOld.getVendorFileCollection().remove(vendorFile);
                serviceOrderServiceIdOld = em.merge(serviceOrderServiceIdOld);
            }
            if (serviceOrderServiceIdNew != null && !serviceOrderServiceIdNew.equals(serviceOrderServiceIdOld)) {
                serviceOrderServiceIdNew.getVendorFileCollection().add(vendorFile);
                serviceOrderServiceIdNew = em.merge(serviceOrderServiceIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = vendorFile.getVendorFileId();
                if (findVendorFile(id) == null) {
                    throw new NonexistentEntityException("The vendorFile with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VendorFile vendorFile;
            try {
                vendorFile = em.getReference(VendorFile.class, id);
                vendorFile.getVendorFileId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vendorFile with id " + id + " no longer exists.", enfe);
            }
            ServiceOrderService serviceOrderServiceId = vendorFile.getServiceOrderServiceId();
            if (serviceOrderServiceId != null) {
                serviceOrderServiceId.getVendorFileCollection().remove(vendorFile);
                serviceOrderServiceId = em.merge(serviceOrderServiceId);
            }
            em.remove(vendorFile);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VendorFile> findVendorFileEntities() {
        return findVendorFileEntities(true, -1, -1);
    }

    public List<VendorFile> findVendorFileEntities(int maxResults, int firstResult) {
        return findVendorFileEntities(false, maxResults, firstResult);
    }

    private List<VendorFile> findVendorFileEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VendorFile.class));
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

    public VendorFile findVendorFile(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VendorFile.class, id);
        } finally {
            em.close();
        }
    }

    public int getVendorFileCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VendorFile> rt = cq.from(VendorFile.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
