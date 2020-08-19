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
import mx.tab.wgp.qsmaritimex.entidades.Country;
import mx.tab.wgp.qsmaritimex.entidades.ServiceType;
import mx.tab.wgp.qsmaritimex.entidades.VesselType;
import mx.tab.wgp.qsmaritimex.entidades.Itinerary;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.IllegalOrphanException;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.Vessel;
import mx.tab.wgp.qsmaritimex.entidades.VesselPerLine;

/**
 *
 * @author WilliamGP025
 */
public class VesselJpaController implements Serializable {

    public VesselJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vessel vessel) {
        if (vessel.getItineraryCollection() == null) {
            vessel.setItineraryCollection(new ArrayList<Itinerary>());
        }
        if (vessel.getVesselPerLineCollection() == null) {
            vessel.setVesselPerLineCollection(new ArrayList<VesselPerLine>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Country countryId = vessel.getCountryId();
            if (countryId != null) {
                countryId = em.getReference(countryId.getClass(), countryId.getCountryId());
                vessel.setCountryId(countryId);
            }
            ServiceType serviceTypeId = vessel.getServiceTypeId();
            if (serviceTypeId != null) {
                serviceTypeId = em.getReference(serviceTypeId.getClass(), serviceTypeId.getServiceTypeId());
                vessel.setServiceTypeId(serviceTypeId);
            }
            VesselType vesselTypeId = vessel.getVesselTypeId();
            if (vesselTypeId != null) {
                vesselTypeId = em.getReference(vesselTypeId.getClass(), vesselTypeId.getVesselTypeId());
                vessel.setVesselTypeId(vesselTypeId);
            }
            Collection<Itinerary> attachedItineraryCollection = new ArrayList<Itinerary>();
            for (Itinerary itineraryCollectionItineraryToAttach : vessel.getItineraryCollection()) {
                itineraryCollectionItineraryToAttach = em.getReference(itineraryCollectionItineraryToAttach.getClass(), itineraryCollectionItineraryToAttach.getItineraryId());
                attachedItineraryCollection.add(itineraryCollectionItineraryToAttach);
            }
            vessel.setItineraryCollection(attachedItineraryCollection);
            Collection<VesselPerLine> attachedVesselPerLineCollection = new ArrayList<VesselPerLine>();
            for (VesselPerLine vesselPerLineCollectionVesselPerLineToAttach : vessel.getVesselPerLineCollection()) {
                vesselPerLineCollectionVesselPerLineToAttach = em.getReference(vesselPerLineCollectionVesselPerLineToAttach.getClass(), vesselPerLineCollectionVesselPerLineToAttach.getVesselPerLineId());
                attachedVesselPerLineCollection.add(vesselPerLineCollectionVesselPerLineToAttach);
            }
            vessel.setVesselPerLineCollection(attachedVesselPerLineCollection);
            em.persist(vessel);
            if (countryId != null) {
                countryId.getVesselCollection().add(vessel);
                countryId = em.merge(countryId);
            }
            if (serviceTypeId != null) {
                serviceTypeId.getVesselCollection().add(vessel);
                serviceTypeId = em.merge(serviceTypeId);
            }
            if (vesselTypeId != null) {
                vesselTypeId.getVesselCollection().add(vessel);
                vesselTypeId = em.merge(vesselTypeId);
            }
            for (Itinerary itineraryCollectionItinerary : vessel.getItineraryCollection()) {
                Vessel oldVesselIdOfItineraryCollectionItinerary = itineraryCollectionItinerary.getVesselId();
                itineraryCollectionItinerary.setVesselId(vessel);
                itineraryCollectionItinerary = em.merge(itineraryCollectionItinerary);
                if (oldVesselIdOfItineraryCollectionItinerary != null) {
                    oldVesselIdOfItineraryCollectionItinerary.getItineraryCollection().remove(itineraryCollectionItinerary);
                    oldVesselIdOfItineraryCollectionItinerary = em.merge(oldVesselIdOfItineraryCollectionItinerary);
                }
            }
            for (VesselPerLine vesselPerLineCollectionVesselPerLine : vessel.getVesselPerLineCollection()) {
                Vessel oldVesselIdOfVesselPerLineCollectionVesselPerLine = vesselPerLineCollectionVesselPerLine.getVesselId();
                vesselPerLineCollectionVesselPerLine.setVesselId(vessel);
                vesselPerLineCollectionVesselPerLine = em.merge(vesselPerLineCollectionVesselPerLine);
                if (oldVesselIdOfVesselPerLineCollectionVesselPerLine != null) {
                    oldVesselIdOfVesselPerLineCollectionVesselPerLine.getVesselPerLineCollection().remove(vesselPerLineCollectionVesselPerLine);
                    oldVesselIdOfVesselPerLineCollectionVesselPerLine = em.merge(oldVesselIdOfVesselPerLineCollectionVesselPerLine);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vessel vessel) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vessel persistentVessel = em.find(Vessel.class, vessel.getVesselId());
            Country countryIdOld = persistentVessel.getCountryId();
            Country countryIdNew = vessel.getCountryId();
            ServiceType serviceTypeIdOld = persistentVessel.getServiceTypeId();
            ServiceType serviceTypeIdNew = vessel.getServiceTypeId();
            VesselType vesselTypeIdOld = persistentVessel.getVesselTypeId();
            VesselType vesselTypeIdNew = vessel.getVesselTypeId();
            Collection<Itinerary> itineraryCollectionOld = persistentVessel.getItineraryCollection();
            Collection<Itinerary> itineraryCollectionNew = vessel.getItineraryCollection();
            Collection<VesselPerLine> vesselPerLineCollectionOld = persistentVessel.getVesselPerLineCollection();
            Collection<VesselPerLine> vesselPerLineCollectionNew = vessel.getVesselPerLineCollection();
            List<String> illegalOrphanMessages = null;
            for (Itinerary itineraryCollectionOldItinerary : itineraryCollectionOld) {
                if (!itineraryCollectionNew.contains(itineraryCollectionOldItinerary)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Itinerary " + itineraryCollectionOldItinerary + " since its vesselId field is not nullable.");
                }
            }
            for (VesselPerLine vesselPerLineCollectionOldVesselPerLine : vesselPerLineCollectionOld) {
                if (!vesselPerLineCollectionNew.contains(vesselPerLineCollectionOldVesselPerLine)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain VesselPerLine " + vesselPerLineCollectionOldVesselPerLine + " since its vesselId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (countryIdNew != null) {
                countryIdNew = em.getReference(countryIdNew.getClass(), countryIdNew.getCountryId());
                vessel.setCountryId(countryIdNew);
            }
            if (serviceTypeIdNew != null) {
                serviceTypeIdNew = em.getReference(serviceTypeIdNew.getClass(), serviceTypeIdNew.getServiceTypeId());
                vessel.setServiceTypeId(serviceTypeIdNew);
            }
            if (vesselTypeIdNew != null) {
                vesselTypeIdNew = em.getReference(vesselTypeIdNew.getClass(), vesselTypeIdNew.getVesselTypeId());
                vessel.setVesselTypeId(vesselTypeIdNew);
            }
            Collection<Itinerary> attachedItineraryCollectionNew = new ArrayList<Itinerary>();
            for (Itinerary itineraryCollectionNewItineraryToAttach : itineraryCollectionNew) {
                itineraryCollectionNewItineraryToAttach = em.getReference(itineraryCollectionNewItineraryToAttach.getClass(), itineraryCollectionNewItineraryToAttach.getItineraryId());
                attachedItineraryCollectionNew.add(itineraryCollectionNewItineraryToAttach);
            }
            itineraryCollectionNew = attachedItineraryCollectionNew;
            vessel.setItineraryCollection(itineraryCollectionNew);
            Collection<VesselPerLine> attachedVesselPerLineCollectionNew = new ArrayList<VesselPerLine>();
            for (VesselPerLine vesselPerLineCollectionNewVesselPerLineToAttach : vesselPerLineCollectionNew) {
                vesselPerLineCollectionNewVesselPerLineToAttach = em.getReference(vesselPerLineCollectionNewVesselPerLineToAttach.getClass(), vesselPerLineCollectionNewVesselPerLineToAttach.getVesselPerLineId());
                attachedVesselPerLineCollectionNew.add(vesselPerLineCollectionNewVesselPerLineToAttach);
            }
            vesselPerLineCollectionNew = attachedVesselPerLineCollectionNew;
            vessel.setVesselPerLineCollection(vesselPerLineCollectionNew);
            vessel = em.merge(vessel);
            if (countryIdOld != null && !countryIdOld.equals(countryIdNew)) {
                countryIdOld.getVesselCollection().remove(vessel);
                countryIdOld = em.merge(countryIdOld);
            }
            if (countryIdNew != null && !countryIdNew.equals(countryIdOld)) {
                countryIdNew.getVesselCollection().add(vessel);
                countryIdNew = em.merge(countryIdNew);
            }
            if (serviceTypeIdOld != null && !serviceTypeIdOld.equals(serviceTypeIdNew)) {
                serviceTypeIdOld.getVesselCollection().remove(vessel);
                serviceTypeIdOld = em.merge(serviceTypeIdOld);
            }
            if (serviceTypeIdNew != null && !serviceTypeIdNew.equals(serviceTypeIdOld)) {
                serviceTypeIdNew.getVesselCollection().add(vessel);
                serviceTypeIdNew = em.merge(serviceTypeIdNew);
            }
            if (vesselTypeIdOld != null && !vesselTypeIdOld.equals(vesselTypeIdNew)) {
                vesselTypeIdOld.getVesselCollection().remove(vessel);
                vesselTypeIdOld = em.merge(vesselTypeIdOld);
            }
            if (vesselTypeIdNew != null && !vesselTypeIdNew.equals(vesselTypeIdOld)) {
                vesselTypeIdNew.getVesselCollection().add(vessel);
                vesselTypeIdNew = em.merge(vesselTypeIdNew);
            }
            for (Itinerary itineraryCollectionNewItinerary : itineraryCollectionNew) {
                if (!itineraryCollectionOld.contains(itineraryCollectionNewItinerary)) {
                    Vessel oldVesselIdOfItineraryCollectionNewItinerary = itineraryCollectionNewItinerary.getVesselId();
                    itineraryCollectionNewItinerary.setVesselId(vessel);
                    itineraryCollectionNewItinerary = em.merge(itineraryCollectionNewItinerary);
                    if (oldVesselIdOfItineraryCollectionNewItinerary != null && !oldVesselIdOfItineraryCollectionNewItinerary.equals(vessel)) {
                        oldVesselIdOfItineraryCollectionNewItinerary.getItineraryCollection().remove(itineraryCollectionNewItinerary);
                        oldVesselIdOfItineraryCollectionNewItinerary = em.merge(oldVesselIdOfItineraryCollectionNewItinerary);
                    }
                }
            }
            for (VesselPerLine vesselPerLineCollectionNewVesselPerLine : vesselPerLineCollectionNew) {
                if (!vesselPerLineCollectionOld.contains(vesselPerLineCollectionNewVesselPerLine)) {
                    Vessel oldVesselIdOfVesselPerLineCollectionNewVesselPerLine = vesselPerLineCollectionNewVesselPerLine.getVesselId();
                    vesselPerLineCollectionNewVesselPerLine.setVesselId(vessel);
                    vesselPerLineCollectionNewVesselPerLine = em.merge(vesselPerLineCollectionNewVesselPerLine);
                    if (oldVesselIdOfVesselPerLineCollectionNewVesselPerLine != null && !oldVesselIdOfVesselPerLineCollectionNewVesselPerLine.equals(vessel)) {
                        oldVesselIdOfVesselPerLineCollectionNewVesselPerLine.getVesselPerLineCollection().remove(vesselPerLineCollectionNewVesselPerLine);
                        oldVesselIdOfVesselPerLineCollectionNewVesselPerLine = em.merge(oldVesselIdOfVesselPerLineCollectionNewVesselPerLine);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = vessel.getVesselId();
                if (findVessel(id) == null) {
                    throw new NonexistentEntityException("The vessel with id " + id + " no longer exists.");
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
            Vessel vessel;
            try {
                vessel = em.getReference(Vessel.class, id);
                vessel.getVesselId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vessel with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Itinerary> itineraryCollectionOrphanCheck = vessel.getItineraryCollection();
            for (Itinerary itineraryCollectionOrphanCheckItinerary : itineraryCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vessel (" + vessel + ") cannot be destroyed since the Itinerary " + itineraryCollectionOrphanCheckItinerary + " in its itineraryCollection field has a non-nullable vesselId field.");
            }
            Collection<VesselPerLine> vesselPerLineCollectionOrphanCheck = vessel.getVesselPerLineCollection();
            for (VesselPerLine vesselPerLineCollectionOrphanCheckVesselPerLine : vesselPerLineCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vessel (" + vessel + ") cannot be destroyed since the VesselPerLine " + vesselPerLineCollectionOrphanCheckVesselPerLine + " in its vesselPerLineCollection field has a non-nullable vesselId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Country countryId = vessel.getCountryId();
            if (countryId != null) {
                countryId.getVesselCollection().remove(vessel);
                countryId = em.merge(countryId);
            }
            ServiceType serviceTypeId = vessel.getServiceTypeId();
            if (serviceTypeId != null) {
                serviceTypeId.getVesselCollection().remove(vessel);
                serviceTypeId = em.merge(serviceTypeId);
            }
            VesselType vesselTypeId = vessel.getVesselTypeId();
            if (vesselTypeId != null) {
                vesselTypeId.getVesselCollection().remove(vessel);
                vesselTypeId = em.merge(vesselTypeId);
            }
            em.remove(vessel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vessel> findVesselEntities() {
        return findVesselEntities(true, -1, -1);
    }

    public List<Vessel> findVesselEntities(int maxResults, int firstResult) {
        return findVesselEntities(false, maxResults, firstResult);
    }

    private List<Vessel> findVesselEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vessel.class));
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

    public Vessel findVessel(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vessel.class, id);
        } finally {
            em.close();
        }
    }

    public int getVesselCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vessel> rt = cq.from(Vessel.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
