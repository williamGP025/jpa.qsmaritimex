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
import mx.tab.wgp.qsmaritimex.entidades.ArrivalInformation;

/**
 *
 * @author WilliamGP025
 */
public class ArrivalInformationJpaController implements Serializable {

    public ArrivalInformationJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ArrivalInformation arrivalInformation) {
        if (arrivalInformation.getItineraryCollection() == null) {
            arrivalInformation.setItineraryCollection(new ArrayList<Itinerary>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Itinerary> attachedItineraryCollection = new ArrayList<Itinerary>();
            for (Itinerary itineraryCollectionItineraryToAttach : arrivalInformation.getItineraryCollection()) {
                itineraryCollectionItineraryToAttach = em.getReference(itineraryCollectionItineraryToAttach.getClass(), itineraryCollectionItineraryToAttach.getItineraryId());
                attachedItineraryCollection.add(itineraryCollectionItineraryToAttach);
            }
            arrivalInformation.setItineraryCollection(attachedItineraryCollection);
            em.persist(arrivalInformation);
            for (Itinerary itineraryCollectionItinerary : arrivalInformation.getItineraryCollection()) {
                ArrivalInformation oldArrivalInformationIdOfItineraryCollectionItinerary = itineraryCollectionItinerary.getArrivalInformationId();
                itineraryCollectionItinerary.setArrivalInformationId(arrivalInformation);
                itineraryCollectionItinerary = em.merge(itineraryCollectionItinerary);
                if (oldArrivalInformationIdOfItineraryCollectionItinerary != null) {
                    oldArrivalInformationIdOfItineraryCollectionItinerary.getItineraryCollection().remove(itineraryCollectionItinerary);
                    oldArrivalInformationIdOfItineraryCollectionItinerary = em.merge(oldArrivalInformationIdOfItineraryCollectionItinerary);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ArrivalInformation arrivalInformation) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrivalInformation persistentArrivalInformation = em.find(ArrivalInformation.class, arrivalInformation.getArrivalInformationId());
            Collection<Itinerary> itineraryCollectionOld = persistentArrivalInformation.getItineraryCollection();
            Collection<Itinerary> itineraryCollectionNew = arrivalInformation.getItineraryCollection();
            List<String> illegalOrphanMessages = null;
            for (Itinerary itineraryCollectionOldItinerary : itineraryCollectionOld) {
                if (!itineraryCollectionNew.contains(itineraryCollectionOldItinerary)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Itinerary " + itineraryCollectionOldItinerary + " since its arrivalInformationId field is not nullable.");
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
            arrivalInformation.setItineraryCollection(itineraryCollectionNew);
            arrivalInformation = em.merge(arrivalInformation);
            for (Itinerary itineraryCollectionNewItinerary : itineraryCollectionNew) {
                if (!itineraryCollectionOld.contains(itineraryCollectionNewItinerary)) {
                    ArrivalInformation oldArrivalInformationIdOfItineraryCollectionNewItinerary = itineraryCollectionNewItinerary.getArrivalInformationId();
                    itineraryCollectionNewItinerary.setArrivalInformationId(arrivalInformation);
                    itineraryCollectionNewItinerary = em.merge(itineraryCollectionNewItinerary);
                    if (oldArrivalInformationIdOfItineraryCollectionNewItinerary != null && !oldArrivalInformationIdOfItineraryCollectionNewItinerary.equals(arrivalInformation)) {
                        oldArrivalInformationIdOfItineraryCollectionNewItinerary.getItineraryCollection().remove(itineraryCollectionNewItinerary);
                        oldArrivalInformationIdOfItineraryCollectionNewItinerary = em.merge(oldArrivalInformationIdOfItineraryCollectionNewItinerary);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = arrivalInformation.getArrivalInformationId();
                if (findArrivalInformation(id) == null) {
                    throw new NonexistentEntityException("The arrivalInformation with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrivalInformation arrivalInformation;
            try {
                arrivalInformation = em.getReference(ArrivalInformation.class, id);
                arrivalInformation.getArrivalInformationId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The arrivalInformation with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Itinerary> itineraryCollectionOrphanCheck = arrivalInformation.getItineraryCollection();
            for (Itinerary itineraryCollectionOrphanCheckItinerary : itineraryCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ArrivalInformation (" + arrivalInformation + ") cannot be destroyed since the Itinerary " + itineraryCollectionOrphanCheckItinerary + " in its itineraryCollection field has a non-nullable arrivalInformationId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(arrivalInformation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ArrivalInformation> findArrivalInformationEntities() {
        return findArrivalInformationEntities(true, -1, -1);
    }

    public List<ArrivalInformation> findArrivalInformationEntities(int maxResults, int firstResult) {
        return findArrivalInformationEntities(false, maxResults, firstResult);
    }

    private List<ArrivalInformation> findArrivalInformationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ArrivalInformation.class));
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

    public ArrivalInformation findArrivalInformation(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ArrivalInformation.class, id);
        } finally {
            em.close();
        }
    }

    public int getArrivalInformationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ArrivalInformation> rt = cq.from(ArrivalInformation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
