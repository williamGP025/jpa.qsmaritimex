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
import mx.tab.wgp.qsmaritimex.entidades.ScaleType;

/**
 *
 * @author WilliamGP025
 */
public class ScaleTypeJpaController implements Serializable {

    public ScaleTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ScaleType scaleType) {
        if (scaleType.getItineraryCollection() == null) {
            scaleType.setItineraryCollection(new ArrayList<Itinerary>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Itinerary> attachedItineraryCollection = new ArrayList<Itinerary>();
            for (Itinerary itineraryCollectionItineraryToAttach : scaleType.getItineraryCollection()) {
                itineraryCollectionItineraryToAttach = em.getReference(itineraryCollectionItineraryToAttach.getClass(), itineraryCollectionItineraryToAttach.getItineraryId());
                attachedItineraryCollection.add(itineraryCollectionItineraryToAttach);
            }
            scaleType.setItineraryCollection(attachedItineraryCollection);
            em.persist(scaleType);
            for (Itinerary itineraryCollectionItinerary : scaleType.getItineraryCollection()) {
                ScaleType oldScaleTypeIdOfItineraryCollectionItinerary = itineraryCollectionItinerary.getScaleTypeId();
                itineraryCollectionItinerary.setScaleTypeId(scaleType);
                itineraryCollectionItinerary = em.merge(itineraryCollectionItinerary);
                if (oldScaleTypeIdOfItineraryCollectionItinerary != null) {
                    oldScaleTypeIdOfItineraryCollectionItinerary.getItineraryCollection().remove(itineraryCollectionItinerary);
                    oldScaleTypeIdOfItineraryCollectionItinerary = em.merge(oldScaleTypeIdOfItineraryCollectionItinerary);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ScaleType scaleType) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ScaleType persistentScaleType = em.find(ScaleType.class, scaleType.getScaleTypeId());
            Collection<Itinerary> itineraryCollectionOld = persistentScaleType.getItineraryCollection();
            Collection<Itinerary> itineraryCollectionNew = scaleType.getItineraryCollection();
            List<String> illegalOrphanMessages = null;
            for (Itinerary itineraryCollectionOldItinerary : itineraryCollectionOld) {
                if (!itineraryCollectionNew.contains(itineraryCollectionOldItinerary)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Itinerary " + itineraryCollectionOldItinerary + " since its scaleTypeId field is not nullable.");
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
            scaleType.setItineraryCollection(itineraryCollectionNew);
            scaleType = em.merge(scaleType);
            for (Itinerary itineraryCollectionNewItinerary : itineraryCollectionNew) {
                if (!itineraryCollectionOld.contains(itineraryCollectionNewItinerary)) {
                    ScaleType oldScaleTypeIdOfItineraryCollectionNewItinerary = itineraryCollectionNewItinerary.getScaleTypeId();
                    itineraryCollectionNewItinerary.setScaleTypeId(scaleType);
                    itineraryCollectionNewItinerary = em.merge(itineraryCollectionNewItinerary);
                    if (oldScaleTypeIdOfItineraryCollectionNewItinerary != null && !oldScaleTypeIdOfItineraryCollectionNewItinerary.equals(scaleType)) {
                        oldScaleTypeIdOfItineraryCollectionNewItinerary.getItineraryCollection().remove(itineraryCollectionNewItinerary);
                        oldScaleTypeIdOfItineraryCollectionNewItinerary = em.merge(oldScaleTypeIdOfItineraryCollectionNewItinerary);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = scaleType.getScaleTypeId();
                if (findScaleType(id) == null) {
                    throw new NonexistentEntityException("The scaleType with id " + id + " no longer exists.");
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
            ScaleType scaleType;
            try {
                scaleType = em.getReference(ScaleType.class, id);
                scaleType.getScaleTypeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The scaleType with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Itinerary> itineraryCollectionOrphanCheck = scaleType.getItineraryCollection();
            for (Itinerary itineraryCollectionOrphanCheckItinerary : itineraryCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ScaleType (" + scaleType + ") cannot be destroyed since the Itinerary " + itineraryCollectionOrphanCheckItinerary + " in its itineraryCollection field has a non-nullable scaleTypeId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(scaleType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ScaleType> findScaleTypeEntities() {
        return findScaleTypeEntities(true, -1, -1);
    }

    public List<ScaleType> findScaleTypeEntities(int maxResults, int firstResult) {
        return findScaleTypeEntities(false, maxResults, firstResult);
    }

    private List<ScaleType> findScaleTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ScaleType.class));
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

    public ScaleType findScaleType(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ScaleType.class, id);
        } finally {
            em.close();
        }
    }

    public int getScaleTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ScaleType> rt = cq.from(ScaleType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
