/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.controladores;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mx.tab.wgp.qsmaritimex.entidades.Country;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.Line;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.IllegalOrphanException;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ReferenceShipOwner;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrder;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ShipOwner;

/**
 *
 * @author William
 */
public class ShipOwnerJpaController implements Serializable {

    public ShipOwnerJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ShipOwner shipOwner) {
        if (shipOwner.getLineCollection() == null) {
            shipOwner.setLineCollection(new ArrayList<Line>());
        }
        if (shipOwner.getReferenceShipOwnerCollection() == null) {
            shipOwner.setReferenceShipOwnerCollection(new ArrayList<ReferenceShipOwner>());
        }
        if (shipOwner.getServiceOrderCollection() == null) {
            shipOwner.setServiceOrderCollection(new ArrayList<ServiceOrder>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Country countryId = shipOwner.getCountryId();
            if (countryId != null && countryId.getCountryId() != null) {
                countryId = em.getReference(countryId.getClass(), countryId.getCountryId());
                shipOwner.setCountryId(countryId);
            }
            Collection<Line> attachedLineCollection = new ArrayList<Line>();
            for (Line lineCollectionLineToAttach : shipOwner.getLineCollection()) {
                lineCollectionLineToAttach = em.getReference(lineCollectionLineToAttach.getClass(), lineCollectionLineToAttach.getLineId());
                attachedLineCollection.add(lineCollectionLineToAttach);
            }
            shipOwner.setLineCollection(attachedLineCollection);
            Collection<ReferenceShipOwner> attachedReferenceShipOwnerCollection = new ArrayList<ReferenceShipOwner>();
            for (ReferenceShipOwner referenceShipOwnerCollectionReferenceShipOwnerToAttach : shipOwner.getReferenceShipOwnerCollection()) {
                referenceShipOwnerCollectionReferenceShipOwnerToAttach = em.getReference(referenceShipOwnerCollectionReferenceShipOwnerToAttach.getClass(), referenceShipOwnerCollectionReferenceShipOwnerToAttach.getReferenceShipOwnerId());
                attachedReferenceShipOwnerCollection.add(referenceShipOwnerCollectionReferenceShipOwnerToAttach);
            }
            shipOwner.setReferenceShipOwnerCollection(attachedReferenceShipOwnerCollection);
            Collection<ServiceOrder> attachedServiceOrderCollection = new ArrayList<ServiceOrder>();
            for (ServiceOrder serviceOrderCollectionServiceOrderToAttach : shipOwner.getServiceOrderCollection()) {
                serviceOrderCollectionServiceOrderToAttach = em.getReference(serviceOrderCollectionServiceOrderToAttach.getClass(), serviceOrderCollectionServiceOrderToAttach.getServiceOrderId());
                attachedServiceOrderCollection.add(serviceOrderCollectionServiceOrderToAttach);
            }
            shipOwner.setServiceOrderCollection(attachedServiceOrderCollection);
            em.persist(shipOwner);
            if (countryId != null) {
                countryId.getShipOwnerCollection().add(shipOwner);
                countryId = em.merge(countryId);
            }
            for (Line lineCollectionLine : shipOwner.getLineCollection()) {
                ShipOwner oldShipOwnerIdOfLineCollectionLine = lineCollectionLine.getShipOwnerId();
                lineCollectionLine.setShipOwnerId(shipOwner);
                lineCollectionLine = em.merge(lineCollectionLine);
                if (oldShipOwnerIdOfLineCollectionLine != null) {
                    oldShipOwnerIdOfLineCollectionLine.getLineCollection().remove(lineCollectionLine);
                    oldShipOwnerIdOfLineCollectionLine = em.merge(oldShipOwnerIdOfLineCollectionLine);
                }
            }
            for (ReferenceShipOwner referenceShipOwnerCollectionReferenceShipOwner : shipOwner.getReferenceShipOwnerCollection()) {
                ShipOwner oldShipOwnerIdOfReferenceShipOwnerCollectionReferenceShipOwner = referenceShipOwnerCollectionReferenceShipOwner.getShipOwnerId();
                referenceShipOwnerCollectionReferenceShipOwner.setShipOwnerId(shipOwner);
                referenceShipOwnerCollectionReferenceShipOwner = em.merge(referenceShipOwnerCollectionReferenceShipOwner);
                if (oldShipOwnerIdOfReferenceShipOwnerCollectionReferenceShipOwner != null) {
                    oldShipOwnerIdOfReferenceShipOwnerCollectionReferenceShipOwner.getReferenceShipOwnerCollection().remove(referenceShipOwnerCollectionReferenceShipOwner);
                    oldShipOwnerIdOfReferenceShipOwnerCollectionReferenceShipOwner = em.merge(oldShipOwnerIdOfReferenceShipOwnerCollectionReferenceShipOwner);
                }
            }
            for (ServiceOrder serviceOrderCollectionServiceOrder : shipOwner.getServiceOrderCollection()) {
                ShipOwner oldShipOwnerIdOfServiceOrderCollectionServiceOrder = serviceOrderCollectionServiceOrder.getShipOwnerId();
                serviceOrderCollectionServiceOrder.setShipOwnerId(shipOwner);
                serviceOrderCollectionServiceOrder = em.merge(serviceOrderCollectionServiceOrder);
                if (oldShipOwnerIdOfServiceOrderCollectionServiceOrder != null) {
                    oldShipOwnerIdOfServiceOrderCollectionServiceOrder.getServiceOrderCollection().remove(serviceOrderCollectionServiceOrder);
                    oldShipOwnerIdOfServiceOrderCollectionServiceOrder = em.merge(oldShipOwnerIdOfServiceOrderCollectionServiceOrder);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ShipOwner shipOwner) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ShipOwner persistentShipOwner = em.find(ShipOwner.class, shipOwner.getShipOwnerId());
            Country countryIdOld = persistentShipOwner.getCountryId();
            Country countryIdNew = shipOwner.getCountryId();
            Collection<Line> lineCollectionOld = persistentShipOwner.getLineCollection();
            Collection<Line> lineCollectionNew = shipOwner.getLineCollection();
            Collection<ReferenceShipOwner> referenceShipOwnerCollectionOld = persistentShipOwner.getReferenceShipOwnerCollection();
            Collection<ReferenceShipOwner> referenceShipOwnerCollectionNew = shipOwner.getReferenceShipOwnerCollection();
            Collection<ServiceOrder> serviceOrderCollectionOld = persistentShipOwner.getServiceOrderCollection();
            Collection<ServiceOrder> serviceOrderCollectionNew = shipOwner.getServiceOrderCollection();
            List<String> illegalOrphanMessages = null;
            for (ReferenceShipOwner referenceShipOwnerCollectionOldReferenceShipOwner : referenceShipOwnerCollectionOld) {
                if (!referenceShipOwnerCollectionNew.contains(referenceShipOwnerCollectionOldReferenceShipOwner)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ReferenceShipOwner " + referenceShipOwnerCollectionOldReferenceShipOwner + " since its shipOwnerId field is not nullable.");
                }
            }
            for (ServiceOrder serviceOrderCollectionOldServiceOrder : serviceOrderCollectionOld) {
                if (!serviceOrderCollectionNew.contains(serviceOrderCollectionOldServiceOrder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ServiceOrder " + serviceOrderCollectionOldServiceOrder + " since its shipOwnerId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (countryIdNew != null) {
                countryIdNew = em.getReference(countryIdNew.getClass(), countryIdNew.getCountryId());
                shipOwner.setCountryId(countryIdNew);
            }
            Collection<Line> attachedLineCollectionNew = new ArrayList<Line>();
            for (Line lineCollectionNewLineToAttach : lineCollectionNew) {
                lineCollectionNewLineToAttach = em.getReference(lineCollectionNewLineToAttach.getClass(), lineCollectionNewLineToAttach.getLineId());
                attachedLineCollectionNew.add(lineCollectionNewLineToAttach);
            }
            lineCollectionNew = attachedLineCollectionNew;
            shipOwner.setLineCollection(lineCollectionNew);
            Collection<ReferenceShipOwner> attachedReferenceShipOwnerCollectionNew = new ArrayList<ReferenceShipOwner>();
            for (ReferenceShipOwner referenceShipOwnerCollectionNewReferenceShipOwnerToAttach : referenceShipOwnerCollectionNew) {
                referenceShipOwnerCollectionNewReferenceShipOwnerToAttach = em.getReference(referenceShipOwnerCollectionNewReferenceShipOwnerToAttach.getClass(), referenceShipOwnerCollectionNewReferenceShipOwnerToAttach.getReferenceShipOwnerId());
                attachedReferenceShipOwnerCollectionNew.add(referenceShipOwnerCollectionNewReferenceShipOwnerToAttach);
            }
            referenceShipOwnerCollectionNew = attachedReferenceShipOwnerCollectionNew;
            shipOwner.setReferenceShipOwnerCollection(referenceShipOwnerCollectionNew);
            Collection<ServiceOrder> attachedServiceOrderCollectionNew = new ArrayList<ServiceOrder>();
            for (ServiceOrder serviceOrderCollectionNewServiceOrderToAttach : serviceOrderCollectionNew) {
                serviceOrderCollectionNewServiceOrderToAttach = em.getReference(serviceOrderCollectionNewServiceOrderToAttach.getClass(), serviceOrderCollectionNewServiceOrderToAttach.getServiceOrderId());
                attachedServiceOrderCollectionNew.add(serviceOrderCollectionNewServiceOrderToAttach);
            }
            serviceOrderCollectionNew = attachedServiceOrderCollectionNew;
            shipOwner.setServiceOrderCollection(serviceOrderCollectionNew);
            shipOwner = em.merge(shipOwner);
            if (countryIdOld != null && !countryIdOld.equals(countryIdNew)) {
                countryIdOld.getShipOwnerCollection().remove(shipOwner);
                countryIdOld = em.merge(countryIdOld);
            }
            if (countryIdNew != null && !countryIdNew.equals(countryIdOld)) {
                countryIdNew.getShipOwnerCollection().add(shipOwner);
                countryIdNew = em.merge(countryIdNew);
            }
            for (Line lineCollectionOldLine : lineCollectionOld) {
                if (!lineCollectionNew.contains(lineCollectionOldLine)) {
                    lineCollectionOldLine.setShipOwnerId(null);
                    lineCollectionOldLine = em.merge(lineCollectionOldLine);
                }
            }
            for (Line lineCollectionNewLine : lineCollectionNew) {
                if (!lineCollectionOld.contains(lineCollectionNewLine)) {
                    ShipOwner oldShipOwnerIdOfLineCollectionNewLine = lineCollectionNewLine.getShipOwnerId();
                    lineCollectionNewLine.setShipOwnerId(shipOwner);
                    lineCollectionNewLine = em.merge(lineCollectionNewLine);
                    if (oldShipOwnerIdOfLineCollectionNewLine != null && !oldShipOwnerIdOfLineCollectionNewLine.equals(shipOwner)) {
                        oldShipOwnerIdOfLineCollectionNewLine.getLineCollection().remove(lineCollectionNewLine);
                        oldShipOwnerIdOfLineCollectionNewLine = em.merge(oldShipOwnerIdOfLineCollectionNewLine);
                    }
                }
            }
            for (ReferenceShipOwner referenceShipOwnerCollectionNewReferenceShipOwner : referenceShipOwnerCollectionNew) {
                if (!referenceShipOwnerCollectionOld.contains(referenceShipOwnerCollectionNewReferenceShipOwner)) {
                    ShipOwner oldShipOwnerIdOfReferenceShipOwnerCollectionNewReferenceShipOwner = referenceShipOwnerCollectionNewReferenceShipOwner.getShipOwnerId();
                    referenceShipOwnerCollectionNewReferenceShipOwner.setShipOwnerId(shipOwner);
                    referenceShipOwnerCollectionNewReferenceShipOwner = em.merge(referenceShipOwnerCollectionNewReferenceShipOwner);
                    if (oldShipOwnerIdOfReferenceShipOwnerCollectionNewReferenceShipOwner != null && !oldShipOwnerIdOfReferenceShipOwnerCollectionNewReferenceShipOwner.equals(shipOwner)) {
                        oldShipOwnerIdOfReferenceShipOwnerCollectionNewReferenceShipOwner.getReferenceShipOwnerCollection().remove(referenceShipOwnerCollectionNewReferenceShipOwner);
                        oldShipOwnerIdOfReferenceShipOwnerCollectionNewReferenceShipOwner = em.merge(oldShipOwnerIdOfReferenceShipOwnerCollectionNewReferenceShipOwner);
                    }
                }
            }
            for (ServiceOrder serviceOrderCollectionNewServiceOrder : serviceOrderCollectionNew) {
                if (!serviceOrderCollectionOld.contains(serviceOrderCollectionNewServiceOrder)) {
                    ShipOwner oldShipOwnerIdOfServiceOrderCollectionNewServiceOrder = serviceOrderCollectionNewServiceOrder.getShipOwnerId();
                    serviceOrderCollectionNewServiceOrder.setShipOwnerId(shipOwner);
                    serviceOrderCollectionNewServiceOrder = em.merge(serviceOrderCollectionNewServiceOrder);
                    if (oldShipOwnerIdOfServiceOrderCollectionNewServiceOrder != null && !oldShipOwnerIdOfServiceOrderCollectionNewServiceOrder.equals(shipOwner)) {
                        oldShipOwnerIdOfServiceOrderCollectionNewServiceOrder.getServiceOrderCollection().remove(serviceOrderCollectionNewServiceOrder);
                        oldShipOwnerIdOfServiceOrderCollectionNewServiceOrder = em.merge(oldShipOwnerIdOfServiceOrderCollectionNewServiceOrder);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigInteger id = shipOwner.getShipOwnerId();
                if (findShipOwner(id) == null) {
                    throw new NonexistentEntityException("The shipOwner with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigInteger id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ShipOwner shipOwner;
            try {
                shipOwner = em.getReference(ShipOwner.class, id);
                shipOwner.getShipOwnerId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The shipOwner with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ReferenceShipOwner> referenceShipOwnerCollectionOrphanCheck = shipOwner.getReferenceShipOwnerCollection();
            for (ReferenceShipOwner referenceShipOwnerCollectionOrphanCheckReferenceShipOwner : referenceShipOwnerCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ShipOwner (" + shipOwner + ") cannot be destroyed since the ReferenceShipOwner " + referenceShipOwnerCollectionOrphanCheckReferenceShipOwner + " in its referenceShipOwnerCollection field has a non-nullable shipOwnerId field.");
            }
            Collection<ServiceOrder> serviceOrderCollectionOrphanCheck = shipOwner.getServiceOrderCollection();
            for (ServiceOrder serviceOrderCollectionOrphanCheckServiceOrder : serviceOrderCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ShipOwner (" + shipOwner + ") cannot be destroyed since the ServiceOrder " + serviceOrderCollectionOrphanCheckServiceOrder + " in its serviceOrderCollection field has a non-nullable shipOwnerId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Country countryId = shipOwner.getCountryId();
            if (countryId != null) {
                countryId.getShipOwnerCollection().remove(shipOwner);
                countryId = em.merge(countryId);
            }
            Collection<Line> lineCollection = shipOwner.getLineCollection();
            for (Line lineCollectionLine : lineCollection) {
                lineCollectionLine.setShipOwnerId(null);
                lineCollectionLine = em.merge(lineCollectionLine);
            }
            em.remove(shipOwner);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ShipOwner> findShipOwnerEntities() {
        return findShipOwnerEntities(true, -1, -1);
    }

    public List<ShipOwner> findShipOwnerEntities(int maxResults, int firstResult) {
        return findShipOwnerEntities(false, maxResults, firstResult);
    }

    private List<ShipOwner> findShipOwnerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ShipOwner.class));
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

    public ShipOwner findShipOwner(BigInteger id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ShipOwner.class, id);
        } finally {
            em.close();
        }
    }

    public int getShipOwnerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ShipOwner> rt = cq.from(ShipOwner.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}