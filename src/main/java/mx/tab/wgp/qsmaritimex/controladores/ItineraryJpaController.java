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
import mx.tab.wgp.qsmaritimex.entidades.ArrivalInformation;
import mx.tab.wgp.qsmaritimex.entidades.CargoType;
import mx.tab.wgp.qsmaritimex.entidades.Port;
import mx.tab.wgp.qsmaritimex.entidades.ScaleType;
import mx.tab.wgp.qsmaritimex.entidades.Vessel;
import mx.tab.wgp.qsmaritimex.entidades.VesselStatus;
import mx.tab.wgp.qsmaritimex.entidades.ServiceOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.IllegalOrphanException;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.Itinerary;

/**
 *
 * @author WilliamGP025
 */
public class ItineraryJpaController implements Serializable {

    public ItineraryJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Itinerary itinerary) {
        if (itinerary.getServiceOrderCollection() == null) {
            itinerary.setServiceOrderCollection(new ArrayList<ServiceOrder>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrivalInformation arrivalInformationId = itinerary.getArrivalInformationId();
            if (arrivalInformationId != null) {
                arrivalInformationId = em.getReference(arrivalInformationId.getClass(), arrivalInformationId.getArrivalInformationId());
                itinerary.setArrivalInformationId(arrivalInformationId);
            }
            CargoType cargoTypeId = itinerary.getCargoTypeId();
            if (cargoTypeId != null) {
                cargoTypeId = em.getReference(cargoTypeId.getClass(), cargoTypeId.getCargoTypeId());
                itinerary.setCargoTypeId(cargoTypeId);
            }
            Port startPortId = itinerary.getStartPortId();
            if (startPortId != null) {
                startPortId = em.getReference(startPortId.getClass(), startPortId.getPortId());
                itinerary.setStartPortId(startPortId);
            }
            Port endPortId = itinerary.getEndPortId();
            if (endPortId != null) {
                endPortId = em.getReference(endPortId.getClass(), endPortId.getPortId());
                itinerary.setEndPortId(endPortId);
            }
            Port operatingPortId = itinerary.getOperatingPortId();
            if (operatingPortId != null) {
                operatingPortId = em.getReference(operatingPortId.getClass(), operatingPortId.getPortId());
                itinerary.setOperatingPortId(operatingPortId);
            }
            ScaleType scaleTypeId = itinerary.getScaleTypeId();
            if (scaleTypeId != null) {
                scaleTypeId = em.getReference(scaleTypeId.getClass(), scaleTypeId.getScaleTypeId());
                itinerary.setScaleTypeId(scaleTypeId);
            }
            Vessel vesselId = itinerary.getVesselId();
            if (vesselId != null) {
                vesselId = em.getReference(vesselId.getClass(), vesselId.getVesselId());
                itinerary.setVesselId(vesselId);
            }
            VesselStatus vesselStatusId = itinerary.getVesselStatusId();
            if (vesselStatusId != null) {
                vesselStatusId = em.getReference(vesselStatusId.getClass(), vesselStatusId.getVesselStatusId());
                itinerary.setVesselStatusId(vesselStatusId);
            }
            Collection<ServiceOrder> attachedServiceOrderCollection = new ArrayList<ServiceOrder>();
            for (ServiceOrder serviceOrderCollectionServiceOrderToAttach : itinerary.getServiceOrderCollection()) {
                serviceOrderCollectionServiceOrderToAttach = em.getReference(serviceOrderCollectionServiceOrderToAttach.getClass(), serviceOrderCollectionServiceOrderToAttach.getServiceOrderId());
                attachedServiceOrderCollection.add(serviceOrderCollectionServiceOrderToAttach);
            }
            itinerary.setServiceOrderCollection(attachedServiceOrderCollection);
            em.persist(itinerary);
            if (arrivalInformationId != null) {
                arrivalInformationId.getItineraryCollection().add(itinerary);
                arrivalInformationId = em.merge(arrivalInformationId);
            }
            if (cargoTypeId != null) {
                cargoTypeId.getItineraryCollection().add(itinerary);
                cargoTypeId = em.merge(cargoTypeId);
            }
            if (startPortId != null) {
                startPortId.getItineraryCollection().add(itinerary);
                startPortId = em.merge(startPortId);
            }
            if (endPortId != null) {
                endPortId.getItineraryCollection().add(itinerary);
                endPortId = em.merge(endPortId);
            }
            if (operatingPortId != null) {
                operatingPortId.getItineraryCollection().add(itinerary);
                operatingPortId = em.merge(operatingPortId);
            }
            if (scaleTypeId != null) {
                scaleTypeId.getItineraryCollection().add(itinerary);
                scaleTypeId = em.merge(scaleTypeId);
            }
            if (vesselId != null) {
                vesselId.getItineraryCollection().add(itinerary);
                vesselId = em.merge(vesselId);
            }
            if (vesselStatusId != null) {
                vesselStatusId.getItineraryCollection().add(itinerary);
                vesselStatusId = em.merge(vesselStatusId);
            }
            for (ServiceOrder serviceOrderCollectionServiceOrder : itinerary.getServiceOrderCollection()) {
                Itinerary oldItineraryIdOfServiceOrderCollectionServiceOrder = serviceOrderCollectionServiceOrder.getItineraryId();
                serviceOrderCollectionServiceOrder.setItineraryId(itinerary);
                serviceOrderCollectionServiceOrder = em.merge(serviceOrderCollectionServiceOrder);
                if (oldItineraryIdOfServiceOrderCollectionServiceOrder != null) {
                    oldItineraryIdOfServiceOrderCollectionServiceOrder.getServiceOrderCollection().remove(serviceOrderCollectionServiceOrder);
                    oldItineraryIdOfServiceOrderCollectionServiceOrder = em.merge(oldItineraryIdOfServiceOrderCollectionServiceOrder);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Itinerary itinerary) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Itinerary persistentItinerary = em.find(Itinerary.class, itinerary.getItineraryId());
            ArrivalInformation arrivalInformationIdOld = persistentItinerary.getArrivalInformationId();
            ArrivalInformation arrivalInformationIdNew = itinerary.getArrivalInformationId();
            CargoType cargoTypeIdOld = persistentItinerary.getCargoTypeId();
            CargoType cargoTypeIdNew = itinerary.getCargoTypeId();
            Port startPortIdOld = persistentItinerary.getStartPortId();
            Port startPortIdNew = itinerary.getStartPortId();
            Port endPortIdOld = persistentItinerary.getEndPortId();
            Port endPortIdNew = itinerary.getEndPortId();
            Port operatingPortIdOld = persistentItinerary.getOperatingPortId();
            Port operatingPortIdNew = itinerary.getOperatingPortId();
            ScaleType scaleTypeIdOld = persistentItinerary.getScaleTypeId();
            ScaleType scaleTypeIdNew = itinerary.getScaleTypeId();
            Vessel vesselIdOld = persistentItinerary.getVesselId();
            Vessel vesselIdNew = itinerary.getVesselId();
            VesselStatus vesselStatusIdOld = persistentItinerary.getVesselStatusId();
            VesselStatus vesselStatusIdNew = itinerary.getVesselStatusId();
            Collection<ServiceOrder> serviceOrderCollectionOld = persistentItinerary.getServiceOrderCollection();
            Collection<ServiceOrder> serviceOrderCollectionNew = itinerary.getServiceOrderCollection();
            List<String> illegalOrphanMessages = null;
            for (ServiceOrder serviceOrderCollectionOldServiceOrder : serviceOrderCollectionOld) {
                if (!serviceOrderCollectionNew.contains(serviceOrderCollectionOldServiceOrder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ServiceOrder " + serviceOrderCollectionOldServiceOrder + " since its itineraryId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (arrivalInformationIdNew != null) {
                arrivalInformationIdNew = em.getReference(arrivalInformationIdNew.getClass(), arrivalInformationIdNew.getArrivalInformationId());
                itinerary.setArrivalInformationId(arrivalInformationIdNew);
            }
            if (cargoTypeIdNew != null) {
                cargoTypeIdNew = em.getReference(cargoTypeIdNew.getClass(), cargoTypeIdNew.getCargoTypeId());
                itinerary.setCargoTypeId(cargoTypeIdNew);
            }
            if (startPortIdNew != null) {
                startPortIdNew = em.getReference(startPortIdNew.getClass(), startPortIdNew.getPortId());
                itinerary.setStartPortId(startPortIdNew);
            }
            if (endPortIdNew != null) {
                endPortIdNew = em.getReference(endPortIdNew.getClass(), endPortIdNew.getPortId());
                itinerary.setEndPortId(endPortIdNew);
            }
            if (operatingPortIdNew != null) {
                operatingPortIdNew = em.getReference(operatingPortIdNew.getClass(), operatingPortIdNew.getPortId());
                itinerary.setOperatingPortId(operatingPortIdNew);
            }
            if (scaleTypeIdNew != null) {
                scaleTypeIdNew = em.getReference(scaleTypeIdNew.getClass(), scaleTypeIdNew.getScaleTypeId());
                itinerary.setScaleTypeId(scaleTypeIdNew);
            }
            if (vesselIdNew != null) {
                vesselIdNew = em.getReference(vesselIdNew.getClass(), vesselIdNew.getVesselId());
                itinerary.setVesselId(vesselIdNew);
            }
            if (vesselStatusIdNew != null) {
                vesselStatusIdNew = em.getReference(vesselStatusIdNew.getClass(), vesselStatusIdNew.getVesselStatusId());
                itinerary.setVesselStatusId(vesselStatusIdNew);
            }
            Collection<ServiceOrder> attachedServiceOrderCollectionNew = new ArrayList<ServiceOrder>();
            for (ServiceOrder serviceOrderCollectionNewServiceOrderToAttach : serviceOrderCollectionNew) {
                serviceOrderCollectionNewServiceOrderToAttach = em.getReference(serviceOrderCollectionNewServiceOrderToAttach.getClass(), serviceOrderCollectionNewServiceOrderToAttach.getServiceOrderId());
                attachedServiceOrderCollectionNew.add(serviceOrderCollectionNewServiceOrderToAttach);
            }
            serviceOrderCollectionNew = attachedServiceOrderCollectionNew;
            itinerary.setServiceOrderCollection(serviceOrderCollectionNew);
            itinerary = em.merge(itinerary);
            if (arrivalInformationIdOld != null && !arrivalInformationIdOld.equals(arrivalInformationIdNew)) {
                arrivalInformationIdOld.getItineraryCollection().remove(itinerary);
                arrivalInformationIdOld = em.merge(arrivalInformationIdOld);
            }
            if (arrivalInformationIdNew != null && !arrivalInformationIdNew.equals(arrivalInformationIdOld)) {
                arrivalInformationIdNew.getItineraryCollection().add(itinerary);
                arrivalInformationIdNew = em.merge(arrivalInformationIdNew);
            }
            if (cargoTypeIdOld != null && !cargoTypeIdOld.equals(cargoTypeIdNew)) {
                cargoTypeIdOld.getItineraryCollection().remove(itinerary);
                cargoTypeIdOld = em.merge(cargoTypeIdOld);
            }
            if (cargoTypeIdNew != null && !cargoTypeIdNew.equals(cargoTypeIdOld)) {
                cargoTypeIdNew.getItineraryCollection().add(itinerary);
                cargoTypeIdNew = em.merge(cargoTypeIdNew);
            }
            if (startPortIdOld != null && !startPortIdOld.equals(startPortIdNew)) {
                startPortIdOld.getItineraryCollection().remove(itinerary);
                startPortIdOld = em.merge(startPortIdOld);
            }
            if (startPortIdNew != null && !startPortIdNew.equals(startPortIdOld)) {
                startPortIdNew.getItineraryCollection().add(itinerary);
                startPortIdNew = em.merge(startPortIdNew);
            }
            if (endPortIdOld != null && !endPortIdOld.equals(endPortIdNew)) {
                endPortIdOld.getItineraryCollection().remove(itinerary);
                endPortIdOld = em.merge(endPortIdOld);
            }
            if (endPortIdNew != null && !endPortIdNew.equals(endPortIdOld)) {
                endPortIdNew.getItineraryCollection().add(itinerary);
                endPortIdNew = em.merge(endPortIdNew);
            }
            if (operatingPortIdOld != null && !operatingPortIdOld.equals(operatingPortIdNew)) {
                operatingPortIdOld.getItineraryCollection().remove(itinerary);
                operatingPortIdOld = em.merge(operatingPortIdOld);
            }
            if (operatingPortIdNew != null && !operatingPortIdNew.equals(operatingPortIdOld)) {
                operatingPortIdNew.getItineraryCollection().add(itinerary);
                operatingPortIdNew = em.merge(operatingPortIdNew);
            }
            if (scaleTypeIdOld != null && !scaleTypeIdOld.equals(scaleTypeIdNew)) {
                scaleTypeIdOld.getItineraryCollection().remove(itinerary);
                scaleTypeIdOld = em.merge(scaleTypeIdOld);
            }
            if (scaleTypeIdNew != null && !scaleTypeIdNew.equals(scaleTypeIdOld)) {
                scaleTypeIdNew.getItineraryCollection().add(itinerary);
                scaleTypeIdNew = em.merge(scaleTypeIdNew);
            }
            if (vesselIdOld != null && !vesselIdOld.equals(vesselIdNew)) {
                vesselIdOld.getItineraryCollection().remove(itinerary);
                vesselIdOld = em.merge(vesselIdOld);
            }
            if (vesselIdNew != null && !vesselIdNew.equals(vesselIdOld)) {
                vesselIdNew.getItineraryCollection().add(itinerary);
                vesselIdNew = em.merge(vesselIdNew);
            }
            if (vesselStatusIdOld != null && !vesselStatusIdOld.equals(vesselStatusIdNew)) {
                vesselStatusIdOld.getItineraryCollection().remove(itinerary);
                vesselStatusIdOld = em.merge(vesselStatusIdOld);
            }
            if (vesselStatusIdNew != null && !vesselStatusIdNew.equals(vesselStatusIdOld)) {
                vesselStatusIdNew.getItineraryCollection().add(itinerary);
                vesselStatusIdNew = em.merge(vesselStatusIdNew);
            }
            for (ServiceOrder serviceOrderCollectionNewServiceOrder : serviceOrderCollectionNew) {
                if (!serviceOrderCollectionOld.contains(serviceOrderCollectionNewServiceOrder)) {
                    Itinerary oldItineraryIdOfServiceOrderCollectionNewServiceOrder = serviceOrderCollectionNewServiceOrder.getItineraryId();
                    serviceOrderCollectionNewServiceOrder.setItineraryId(itinerary);
                    serviceOrderCollectionNewServiceOrder = em.merge(serviceOrderCollectionNewServiceOrder);
                    if (oldItineraryIdOfServiceOrderCollectionNewServiceOrder != null && !oldItineraryIdOfServiceOrderCollectionNewServiceOrder.equals(itinerary)) {
                        oldItineraryIdOfServiceOrderCollectionNewServiceOrder.getServiceOrderCollection().remove(serviceOrderCollectionNewServiceOrder);
                        oldItineraryIdOfServiceOrderCollectionNewServiceOrder = em.merge(oldItineraryIdOfServiceOrderCollectionNewServiceOrder);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = itinerary.getItineraryId();
                if (findItinerary(id) == null) {
                    throw new NonexistentEntityException("The itinerary with id " + id + " no longer exists.");
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
            Itinerary itinerary;
            try {
                itinerary = em.getReference(Itinerary.class, id);
                itinerary.getItineraryId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The itinerary with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ServiceOrder> serviceOrderCollectionOrphanCheck = itinerary.getServiceOrderCollection();
            for (ServiceOrder serviceOrderCollectionOrphanCheckServiceOrder : serviceOrderCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Itinerary (" + itinerary + ") cannot be destroyed since the ServiceOrder " + serviceOrderCollectionOrphanCheckServiceOrder + " in its serviceOrderCollection field has a non-nullable itineraryId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            ArrivalInformation arrivalInformationId = itinerary.getArrivalInformationId();
            if (arrivalInformationId != null) {
                arrivalInformationId.getItineraryCollection().remove(itinerary);
                arrivalInformationId = em.merge(arrivalInformationId);
            }
            CargoType cargoTypeId = itinerary.getCargoTypeId();
            if (cargoTypeId != null) {
                cargoTypeId.getItineraryCollection().remove(itinerary);
                cargoTypeId = em.merge(cargoTypeId);
            }
            Port startPortId = itinerary.getStartPortId();
            if (startPortId != null) {
                startPortId.getItineraryCollection().remove(itinerary);
                startPortId = em.merge(startPortId);
            }
            Port endPortId = itinerary.getEndPortId();
            if (endPortId != null) {
                endPortId.getItineraryCollection().remove(itinerary);
                endPortId = em.merge(endPortId);
            }
            Port operatingPortId = itinerary.getOperatingPortId();
            if (operatingPortId != null) {
                operatingPortId.getItineraryCollection().remove(itinerary);
                operatingPortId = em.merge(operatingPortId);
            }
            ScaleType scaleTypeId = itinerary.getScaleTypeId();
            if (scaleTypeId != null) {
                scaleTypeId.getItineraryCollection().remove(itinerary);
                scaleTypeId = em.merge(scaleTypeId);
            }
            Vessel vesselId = itinerary.getVesselId();
            if (vesselId != null) {
                vesselId.getItineraryCollection().remove(itinerary);
                vesselId = em.merge(vesselId);
            }
            VesselStatus vesselStatusId = itinerary.getVesselStatusId();
            if (vesselStatusId != null) {
                vesselStatusId.getItineraryCollection().remove(itinerary);
                vesselStatusId = em.merge(vesselStatusId);
            }
            em.remove(itinerary);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Itinerary> findItineraryEntities() {
        return findItineraryEntities(true, -1, -1);
    }

    public List<Itinerary> findItineraryEntities(int maxResults, int firstResult) {
        return findItineraryEntities(false, maxResults, firstResult);
    }

    private List<Itinerary> findItineraryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Itinerary.class));
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

    public Itinerary findItinerary(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Itinerary.class, id);
        } finally {
            em.close();
        }
    }

    public int getItineraryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Itinerary> rt = cq.from(Itinerary.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
