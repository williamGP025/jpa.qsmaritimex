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
import mx.tab.wgp.qsmaritimex.entidades.ReferenceShipOwner;
import mx.tab.wgp.qsmaritimex.entidades.ServiceOrder;
import mx.tab.wgp.qsmaritimex.entidades.ShipOwner;

/**
 *
 * @author WilliamGP025
 */
public class ReferenceShipOwnerJpaController implements Serializable {

    public ReferenceShipOwnerJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ReferenceShipOwner referenceShipOwner) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceOrder serviceOrderId = referenceShipOwner.getServiceOrderId();
            if (serviceOrderId != null) {
                serviceOrderId = em.getReference(serviceOrderId.getClass(), serviceOrderId.getServiceOrderId());
                referenceShipOwner.setServiceOrderId(serviceOrderId);
            }
            ShipOwner shipOwnerId = referenceShipOwner.getShipOwnerId();
            if (shipOwnerId != null) {
                shipOwnerId = em.getReference(shipOwnerId.getClass(), shipOwnerId.getShipOwnerId());
                referenceShipOwner.setShipOwnerId(shipOwnerId);
            }
            em.persist(referenceShipOwner);
            if (serviceOrderId != null) {
                serviceOrderId.getReferenceShipOwnerCollection().add(referenceShipOwner);
                serviceOrderId = em.merge(serviceOrderId);
            }
            if (shipOwnerId != null) {
                shipOwnerId.getReferenceShipOwnerCollection().add(referenceShipOwner);
                shipOwnerId = em.merge(shipOwnerId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ReferenceShipOwner referenceShipOwner) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ReferenceShipOwner persistentReferenceShipOwner = em.find(ReferenceShipOwner.class, referenceShipOwner.getReferenceShipOwnerId());
            ServiceOrder serviceOrderIdOld = persistentReferenceShipOwner.getServiceOrderId();
            ServiceOrder serviceOrderIdNew = referenceShipOwner.getServiceOrderId();
            ShipOwner shipOwnerIdOld = persistentReferenceShipOwner.getShipOwnerId();
            ShipOwner shipOwnerIdNew = referenceShipOwner.getShipOwnerId();
            if (serviceOrderIdNew != null) {
                serviceOrderIdNew = em.getReference(serviceOrderIdNew.getClass(), serviceOrderIdNew.getServiceOrderId());
                referenceShipOwner.setServiceOrderId(serviceOrderIdNew);
            }
            if (shipOwnerIdNew != null) {
                shipOwnerIdNew = em.getReference(shipOwnerIdNew.getClass(), shipOwnerIdNew.getShipOwnerId());
                referenceShipOwner.setShipOwnerId(shipOwnerIdNew);
            }
            referenceShipOwner = em.merge(referenceShipOwner);
            if (serviceOrderIdOld != null && !serviceOrderIdOld.equals(serviceOrderIdNew)) {
                serviceOrderIdOld.getReferenceShipOwnerCollection().remove(referenceShipOwner);
                serviceOrderIdOld = em.merge(serviceOrderIdOld);
            }
            if (serviceOrderIdNew != null && !serviceOrderIdNew.equals(serviceOrderIdOld)) {
                serviceOrderIdNew.getReferenceShipOwnerCollection().add(referenceShipOwner);
                serviceOrderIdNew = em.merge(serviceOrderIdNew);
            }
            if (shipOwnerIdOld != null && !shipOwnerIdOld.equals(shipOwnerIdNew)) {
                shipOwnerIdOld.getReferenceShipOwnerCollection().remove(referenceShipOwner);
                shipOwnerIdOld = em.merge(shipOwnerIdOld);
            }
            if (shipOwnerIdNew != null && !shipOwnerIdNew.equals(shipOwnerIdOld)) {
                shipOwnerIdNew.getReferenceShipOwnerCollection().add(referenceShipOwner);
                shipOwnerIdNew = em.merge(shipOwnerIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = referenceShipOwner.getReferenceShipOwnerId();
                if (findReferenceShipOwner(id) == null) {
                    throw new NonexistentEntityException("The referenceShipOwner with id " + id + " no longer exists.");
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
            ReferenceShipOwner referenceShipOwner;
            try {
                referenceShipOwner = em.getReference(ReferenceShipOwner.class, id);
                referenceShipOwner.getReferenceShipOwnerId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The referenceShipOwner with id " + id + " no longer exists.", enfe);
            }
            ServiceOrder serviceOrderId = referenceShipOwner.getServiceOrderId();
            if (serviceOrderId != null) {
                serviceOrderId.getReferenceShipOwnerCollection().remove(referenceShipOwner);
                serviceOrderId = em.merge(serviceOrderId);
            }
            ShipOwner shipOwnerId = referenceShipOwner.getShipOwnerId();
            if (shipOwnerId != null) {
                shipOwnerId.getReferenceShipOwnerCollection().remove(referenceShipOwner);
                shipOwnerId = em.merge(shipOwnerId);
            }
            em.remove(referenceShipOwner);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ReferenceShipOwner> findReferenceShipOwnerEntities() {
        return findReferenceShipOwnerEntities(true, -1, -1);
    }

    public List<ReferenceShipOwner> findReferenceShipOwnerEntities(int maxResults, int firstResult) {
        return findReferenceShipOwnerEntities(false, maxResults, firstResult);
    }

    private List<ReferenceShipOwner> findReferenceShipOwnerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ReferenceShipOwner.class));
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

    public ReferenceShipOwner findReferenceShipOwner(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReferenceShipOwner.class, id);
        } finally {
            em.close();
        }
    }

    public int getReferenceShipOwnerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ReferenceShipOwner> rt = cq.from(ReferenceShipOwner.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
