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
import mx.tab.wgp.qsmaritimex.entidades.CargoType;
import mx.tab.wgp.qsmaritimex.entidades.UserCargoType;

/**
 *
 * @author WilliamGP025
 */
public class CargoTypeJpaController implements Serializable {

    public CargoTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CargoType cargoType) {
        if (cargoType.getItineraryCollection() == null) {
            cargoType.setItineraryCollection(new ArrayList<Itinerary>());
        }
        if (cargoType.getUserCargoTypeCollection() == null) {
            cargoType.setUserCargoTypeCollection(new ArrayList<UserCargoType>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Itinerary> attachedItineraryCollection = new ArrayList<Itinerary>();
            for (Itinerary itineraryCollectionItineraryToAttach : cargoType.getItineraryCollection()) {
                itineraryCollectionItineraryToAttach = em.getReference(itineraryCollectionItineraryToAttach.getClass(), itineraryCollectionItineraryToAttach.getItineraryId());
                attachedItineraryCollection.add(itineraryCollectionItineraryToAttach);
            }
            cargoType.setItineraryCollection(attachedItineraryCollection);
            Collection<UserCargoType> attachedUserCargoTypeCollection = new ArrayList<UserCargoType>();
            for (UserCargoType userCargoTypeCollectionUserCargoTypeToAttach : cargoType.getUserCargoTypeCollection()) {
                userCargoTypeCollectionUserCargoTypeToAttach = em.getReference(userCargoTypeCollectionUserCargoTypeToAttach.getClass(), userCargoTypeCollectionUserCargoTypeToAttach.getUserCargoTypeId());
                attachedUserCargoTypeCollection.add(userCargoTypeCollectionUserCargoTypeToAttach);
            }
            cargoType.setUserCargoTypeCollection(attachedUserCargoTypeCollection);
            em.persist(cargoType);
            for (Itinerary itineraryCollectionItinerary : cargoType.getItineraryCollection()) {
                CargoType oldCargoTypeIdOfItineraryCollectionItinerary = itineraryCollectionItinerary.getCargoTypeId();
                itineraryCollectionItinerary.setCargoTypeId(cargoType);
                itineraryCollectionItinerary = em.merge(itineraryCollectionItinerary);
                if (oldCargoTypeIdOfItineraryCollectionItinerary != null) {
                    oldCargoTypeIdOfItineraryCollectionItinerary.getItineraryCollection().remove(itineraryCollectionItinerary);
                    oldCargoTypeIdOfItineraryCollectionItinerary = em.merge(oldCargoTypeIdOfItineraryCollectionItinerary);
                }
            }
            for (UserCargoType userCargoTypeCollectionUserCargoType : cargoType.getUserCargoTypeCollection()) {
                CargoType oldCargoTypeIdOfUserCargoTypeCollectionUserCargoType = userCargoTypeCollectionUserCargoType.getCargoTypeId();
                userCargoTypeCollectionUserCargoType.setCargoTypeId(cargoType);
                userCargoTypeCollectionUserCargoType = em.merge(userCargoTypeCollectionUserCargoType);
                if (oldCargoTypeIdOfUserCargoTypeCollectionUserCargoType != null) {
                    oldCargoTypeIdOfUserCargoTypeCollectionUserCargoType.getUserCargoTypeCollection().remove(userCargoTypeCollectionUserCargoType);
                    oldCargoTypeIdOfUserCargoTypeCollectionUserCargoType = em.merge(oldCargoTypeIdOfUserCargoTypeCollectionUserCargoType);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CargoType cargoType) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CargoType persistentCargoType = em.find(CargoType.class, cargoType.getCargoTypeId());
            Collection<Itinerary> itineraryCollectionOld = persistentCargoType.getItineraryCollection();
            Collection<Itinerary> itineraryCollectionNew = cargoType.getItineraryCollection();
            Collection<UserCargoType> userCargoTypeCollectionOld = persistentCargoType.getUserCargoTypeCollection();
            Collection<UserCargoType> userCargoTypeCollectionNew = cargoType.getUserCargoTypeCollection();
            List<String> illegalOrphanMessages = null;
            for (Itinerary itineraryCollectionOldItinerary : itineraryCollectionOld) {
                if (!itineraryCollectionNew.contains(itineraryCollectionOldItinerary)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Itinerary " + itineraryCollectionOldItinerary + " since its cargoTypeId field is not nullable.");
                }
            }
            for (UserCargoType userCargoTypeCollectionOldUserCargoType : userCargoTypeCollectionOld) {
                if (!userCargoTypeCollectionNew.contains(userCargoTypeCollectionOldUserCargoType)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UserCargoType " + userCargoTypeCollectionOldUserCargoType + " since its cargoTypeId field is not nullable.");
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
            cargoType.setItineraryCollection(itineraryCollectionNew);
            Collection<UserCargoType> attachedUserCargoTypeCollectionNew = new ArrayList<UserCargoType>();
            for (UserCargoType userCargoTypeCollectionNewUserCargoTypeToAttach : userCargoTypeCollectionNew) {
                userCargoTypeCollectionNewUserCargoTypeToAttach = em.getReference(userCargoTypeCollectionNewUserCargoTypeToAttach.getClass(), userCargoTypeCollectionNewUserCargoTypeToAttach.getUserCargoTypeId());
                attachedUserCargoTypeCollectionNew.add(userCargoTypeCollectionNewUserCargoTypeToAttach);
            }
            userCargoTypeCollectionNew = attachedUserCargoTypeCollectionNew;
            cargoType.setUserCargoTypeCollection(userCargoTypeCollectionNew);
            cargoType = em.merge(cargoType);
            for (Itinerary itineraryCollectionNewItinerary : itineraryCollectionNew) {
                if (!itineraryCollectionOld.contains(itineraryCollectionNewItinerary)) {
                    CargoType oldCargoTypeIdOfItineraryCollectionNewItinerary = itineraryCollectionNewItinerary.getCargoTypeId();
                    itineraryCollectionNewItinerary.setCargoTypeId(cargoType);
                    itineraryCollectionNewItinerary = em.merge(itineraryCollectionNewItinerary);
                    if (oldCargoTypeIdOfItineraryCollectionNewItinerary != null && !oldCargoTypeIdOfItineraryCollectionNewItinerary.equals(cargoType)) {
                        oldCargoTypeIdOfItineraryCollectionNewItinerary.getItineraryCollection().remove(itineraryCollectionNewItinerary);
                        oldCargoTypeIdOfItineraryCollectionNewItinerary = em.merge(oldCargoTypeIdOfItineraryCollectionNewItinerary);
                    }
                }
            }
            for (UserCargoType userCargoTypeCollectionNewUserCargoType : userCargoTypeCollectionNew) {
                if (!userCargoTypeCollectionOld.contains(userCargoTypeCollectionNewUserCargoType)) {
                    CargoType oldCargoTypeIdOfUserCargoTypeCollectionNewUserCargoType = userCargoTypeCollectionNewUserCargoType.getCargoTypeId();
                    userCargoTypeCollectionNewUserCargoType.setCargoTypeId(cargoType);
                    userCargoTypeCollectionNewUserCargoType = em.merge(userCargoTypeCollectionNewUserCargoType);
                    if (oldCargoTypeIdOfUserCargoTypeCollectionNewUserCargoType != null && !oldCargoTypeIdOfUserCargoTypeCollectionNewUserCargoType.equals(cargoType)) {
                        oldCargoTypeIdOfUserCargoTypeCollectionNewUserCargoType.getUserCargoTypeCollection().remove(userCargoTypeCollectionNewUserCargoType);
                        oldCargoTypeIdOfUserCargoTypeCollectionNewUserCargoType = em.merge(oldCargoTypeIdOfUserCargoTypeCollectionNewUserCargoType);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = cargoType.getCargoTypeId();
                if (findCargoType(id) == null) {
                    throw new NonexistentEntityException("The cargoType with id " + id + " no longer exists.");
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
            CargoType cargoType;
            try {
                cargoType = em.getReference(CargoType.class, id);
                cargoType.getCargoTypeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cargoType with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Itinerary> itineraryCollectionOrphanCheck = cargoType.getItineraryCollection();
            for (Itinerary itineraryCollectionOrphanCheckItinerary : itineraryCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CargoType (" + cargoType + ") cannot be destroyed since the Itinerary " + itineraryCollectionOrphanCheckItinerary + " in its itineraryCollection field has a non-nullable cargoTypeId field.");
            }
            Collection<UserCargoType> userCargoTypeCollectionOrphanCheck = cargoType.getUserCargoTypeCollection();
            for (UserCargoType userCargoTypeCollectionOrphanCheckUserCargoType : userCargoTypeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CargoType (" + cargoType + ") cannot be destroyed since the UserCargoType " + userCargoTypeCollectionOrphanCheckUserCargoType + " in its userCargoTypeCollection field has a non-nullable cargoTypeId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cargoType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CargoType> findCargoTypeEntities() {
        return findCargoTypeEntities(true, -1, -1);
    }

    public List<CargoType> findCargoTypeEntities(int maxResults, int firstResult) {
        return findCargoTypeEntities(false, maxResults, firstResult);
    }

    private List<CargoType> findCargoTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CargoType.class));
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

    public CargoType findCargoType(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CargoType.class, id);
        } finally {
            em.close();
        }
    }

    public int getCargoTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CargoType> rt = cq.from(CargoType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
