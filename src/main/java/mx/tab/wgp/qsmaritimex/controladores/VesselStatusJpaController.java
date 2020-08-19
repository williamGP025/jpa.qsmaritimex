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
import mx.tab.wgp.qsmaritimex.entidades.Itinerary;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.IllegalOrphanException;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.VesselStatus;

/**
 *
 * @author WilliamGP025
 */
public class VesselStatusJpaController implements Serializable {

    public VesselStatusJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VesselStatus vesselStatus) {
        if (vesselStatus.getItineraryCollection() == null) {
            vesselStatus.setItineraryCollection(new ArrayList<Itinerary>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Itinerary> attachedItineraryCollection = new ArrayList<Itinerary>();
            for (Itinerary itineraryCollectionItineraryToAttach : vesselStatus.getItineraryCollection()) {
                itineraryCollectionItineraryToAttach = em.getReference(itineraryCollectionItineraryToAttach.getClass(), itineraryCollectionItineraryToAttach.getItineraryId());
                attachedItineraryCollection.add(itineraryCollectionItineraryToAttach);
            }
            vesselStatus.setItineraryCollection(attachedItineraryCollection);
            em.persist(vesselStatus);
            for (Itinerary itineraryCollectionItinerary : vesselStatus.getItineraryCollection()) {
                VesselStatus oldVesselStatusIdOfItineraryCollectionItinerary = itineraryCollectionItinerary.getVesselStatusId();
                itineraryCollectionItinerary.setVesselStatusId(vesselStatus);
                itineraryCollectionItinerary = em.merge(itineraryCollectionItinerary);
                if (oldVesselStatusIdOfItineraryCollectionItinerary != null) {
                    oldVesselStatusIdOfItineraryCollectionItinerary.getItineraryCollection().remove(itineraryCollectionItinerary);
                    oldVesselStatusIdOfItineraryCollectionItinerary = em.merge(oldVesselStatusIdOfItineraryCollectionItinerary);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VesselStatus vesselStatus) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VesselStatus persistentVesselStatus = em.find(VesselStatus.class, vesselStatus.getVesselStatusId());
            Collection<Itinerary> itineraryCollectionOld = persistentVesselStatus.getItineraryCollection();
            Collection<Itinerary> itineraryCollectionNew = vesselStatus.getItineraryCollection();
            List<String> illegalOrphanMessages = null;
            for (Itinerary itineraryCollectionOldItinerary : itineraryCollectionOld) {
                if (!itineraryCollectionNew.contains(itineraryCollectionOldItinerary)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Itinerary " + itineraryCollectionOldItinerary + " since its vesselStatusId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Itinerary> attachedItineraryCollectionNew = new ArrayList<Itinerary>();
            for (Itinerary itineraryCollectionNewItineraryToAttach : itineraryCollectionNew) {
                itineraryCollectionNewItineraryToAttach = em.getReference(itineraryCollectionNewItineraryToAttach.getClass(), itineraryCollectionNewItineraryToAttach.getItineraryId());
                attachedItineraryCollectionNew.add(itineraryCollectionNewItineraryToAttach);
            }
            itineraryCollectionNew = attachedItineraryCollectionNew;
            vesselStatus.setItineraryCollection(itineraryCollectionNew);
            vesselStatus = em.merge(vesselStatus);
            for (Itinerary itineraryCollectionNewItinerary : itineraryCollectionNew) {
                if (!itineraryCollectionOld.contains(itineraryCollectionNewItinerary)) {
                    VesselStatus oldVesselStatusIdOfItineraryCollectionNewItinerary = itineraryCollectionNewItinerary.getVesselStatusId();
                    itineraryCollectionNewItinerary.setVesselStatusId(vesselStatus);
                    itineraryCollectionNewItinerary = em.merge(itineraryCollectionNewItinerary);
                    if (oldVesselStatusIdOfItineraryCollectionNewItinerary != null && !oldVesselStatusIdOfItineraryCollectionNewItinerary.equals(vesselStatus)) {
                        oldVesselStatusIdOfItineraryCollectionNewItinerary.getItineraryCollection().remove(itineraryCollectionNewItinerary);
                        oldVesselStatusIdOfItineraryCollectionNewItinerary = em.merge(oldVesselStatusIdOfItineraryCollectionNewItinerary);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = vesselStatus.getVesselStatusId();
                if (findVesselStatus(id) == null) {
                    throw new NonexistentEntityException("The vesselStatus with id " + id + " no longer exists.");
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
            VesselStatus vesselStatus;
            try {
                vesselStatus = em.getReference(VesselStatus.class, id);
                vesselStatus.getVesselStatusId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vesselStatus with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Itinerary> itineraryCollectionOrphanCheck = vesselStatus.getItineraryCollection();
            for (Itinerary itineraryCollectionOrphanCheckItinerary : itineraryCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This VesselStatus (" + vesselStatus + ") cannot be destroyed since the Itinerary " + itineraryCollectionOrphanCheckItinerary + " in its itineraryCollection field has a non-nullable vesselStatusId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(vesselStatus);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VesselStatus> findVesselStatusEntities() {
        return findVesselStatusEntities(true, -1, -1);
    }

    public List<VesselStatus> findVesselStatusEntities(int maxResults, int firstResult) {
        return findVesselStatusEntities(false, maxResults, firstResult);
    }

    private List<VesselStatus> findVesselStatusEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VesselStatus.class));
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

    public VesselStatus findVesselStatus(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VesselStatus.class, id);
        } finally {
            em.close();
        }
    }

    public int getVesselStatusCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VesselStatus> rt = cq.from(VesselStatus.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
