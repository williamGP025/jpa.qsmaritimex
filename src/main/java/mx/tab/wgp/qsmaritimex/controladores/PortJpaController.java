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
import mx.tab.wgp.qsmaritimex.entidades.nominacion.Itinerary;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.IllegalOrphanException;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.Port;

/**
 *
 * @author William
 */
public class PortJpaController implements Serializable {

    public PortJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Port port) {
        if (port.getItineraryCollection() == null) {
            port.setItineraryCollection(new ArrayList<Itinerary>());
        }
        if (port.getItineraryCollection1() == null) {
            port.setItineraryCollection1(new ArrayList<Itinerary>());
        }
        if (port.getItineraryCollection2() == null) {
            port.setItineraryCollection2(new ArrayList<Itinerary>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Country countryId = port.getCountryId();
            if (countryId != null) {
                countryId = em.getReference(countryId.getClass(), countryId.getCountryId());
                port.setCountryId(countryId);
            }
            Collection<Itinerary> attachedItineraryCollection = new ArrayList<Itinerary>();
            for (Itinerary itineraryCollectionItineraryToAttach : port.getItineraryCollection()) {
                itineraryCollectionItineraryToAttach = em.getReference(itineraryCollectionItineraryToAttach.getClass(), itineraryCollectionItineraryToAttach.getItineraryId());
                attachedItineraryCollection.add(itineraryCollectionItineraryToAttach);
            }
            port.setItineraryCollection(attachedItineraryCollection);
            Collection<Itinerary> attachedItineraryCollection1 = new ArrayList<Itinerary>();
            for (Itinerary itineraryCollection1ItineraryToAttach : port.getItineraryCollection1()) {
                itineraryCollection1ItineraryToAttach = em.getReference(itineraryCollection1ItineraryToAttach.getClass(), itineraryCollection1ItineraryToAttach.getItineraryId());
                attachedItineraryCollection1.add(itineraryCollection1ItineraryToAttach);
            }
            port.setItineraryCollection1(attachedItineraryCollection1);
            Collection<Itinerary> attachedItineraryCollection2 = new ArrayList<Itinerary>();
            for (Itinerary itineraryCollection2ItineraryToAttach : port.getItineraryCollection2()) {
                itineraryCollection2ItineraryToAttach = em.getReference(itineraryCollection2ItineraryToAttach.getClass(), itineraryCollection2ItineraryToAttach.getItineraryId());
                attachedItineraryCollection2.add(itineraryCollection2ItineraryToAttach);
            }
            port.setItineraryCollection2(attachedItineraryCollection2);
            em.persist(port);
            if (countryId != null) {
                countryId.getPortCollection().add(port);
                countryId = em.merge(countryId);
            }
            for (Itinerary itineraryCollectionItinerary : port.getItineraryCollection()) {
                Port oldEndPortIdOfItineraryCollectionItinerary = itineraryCollectionItinerary.getEndPortId();
                itineraryCollectionItinerary.setEndPortId(port);
                itineraryCollectionItinerary = em.merge(itineraryCollectionItinerary);
                if (oldEndPortIdOfItineraryCollectionItinerary != null) {
                    oldEndPortIdOfItineraryCollectionItinerary.getItineraryCollection().remove(itineraryCollectionItinerary);
                    oldEndPortIdOfItineraryCollectionItinerary = em.merge(oldEndPortIdOfItineraryCollectionItinerary);
                }
            }
            for (Itinerary itineraryCollection1Itinerary : port.getItineraryCollection1()) {
                Port oldOperatingPortIdOfItineraryCollection1Itinerary = itineraryCollection1Itinerary.getOperatingPortId();
                itineraryCollection1Itinerary.setOperatingPortId(port);
                itineraryCollection1Itinerary = em.merge(itineraryCollection1Itinerary);
                if (oldOperatingPortIdOfItineraryCollection1Itinerary != null) {
                    oldOperatingPortIdOfItineraryCollection1Itinerary.getItineraryCollection1().remove(itineraryCollection1Itinerary);
                    oldOperatingPortIdOfItineraryCollection1Itinerary = em.merge(oldOperatingPortIdOfItineraryCollection1Itinerary);
                }
            }
            for (Itinerary itineraryCollection2Itinerary : port.getItineraryCollection2()) {
                Port oldStartPortIdOfItineraryCollection2Itinerary = itineraryCollection2Itinerary.getStartPortId();
                itineraryCollection2Itinerary.setStartPortId(port);
                itineraryCollection2Itinerary = em.merge(itineraryCollection2Itinerary);
                if (oldStartPortIdOfItineraryCollection2Itinerary != null) {
                    oldStartPortIdOfItineraryCollection2Itinerary.getItineraryCollection2().remove(itineraryCollection2Itinerary);
                    oldStartPortIdOfItineraryCollection2Itinerary = em.merge(oldStartPortIdOfItineraryCollection2Itinerary);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Port port) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Port persistentPort = em.find(Port.class, port.getPortId());
            Country countryIdOld = persistentPort.getCountryId();
            Country countryIdNew = port.getCountryId();
            Collection<Itinerary> itineraryCollectionOld = persistentPort.getItineraryCollection();
            Collection<Itinerary> itineraryCollectionNew = port.getItineraryCollection();
            Collection<Itinerary> itineraryCollection1Old = persistentPort.getItineraryCollection1();
            Collection<Itinerary> itineraryCollection1New = port.getItineraryCollection1();
            Collection<Itinerary> itineraryCollection2Old = persistentPort.getItineraryCollection2();
            Collection<Itinerary> itineraryCollection2New = port.getItineraryCollection2();
            List<String> illegalOrphanMessages = null;
            for (Itinerary itineraryCollectionOldItinerary : itineraryCollectionOld) {
                if (!itineraryCollectionNew.contains(itineraryCollectionOldItinerary)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Itinerary " + itineraryCollectionOldItinerary + " since its endPortId field is not nullable.");
                }
            }
            for (Itinerary itineraryCollection1OldItinerary : itineraryCollection1Old) {
                if (!itineraryCollection1New.contains(itineraryCollection1OldItinerary)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Itinerary " + itineraryCollection1OldItinerary + " since its operatingPortId field is not nullable.");
                }
            }
            for (Itinerary itineraryCollection2OldItinerary : itineraryCollection2Old) {
                if (!itineraryCollection2New.contains(itineraryCollection2OldItinerary)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Itinerary " + itineraryCollection2OldItinerary + " since its startPortId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (countryIdNew != null) {
                countryIdNew = em.getReference(countryIdNew.getClass(), countryIdNew.getCountryId());
                port.setCountryId(countryIdNew);
            }
            Collection<Itinerary> attachedItineraryCollectionNew = new ArrayList<Itinerary>();
            for (Itinerary itineraryCollectionNewItineraryToAttach : itineraryCollectionNew) {
                itineraryCollectionNewItineraryToAttach = em.getReference(itineraryCollectionNewItineraryToAttach.getClass(), itineraryCollectionNewItineraryToAttach.getItineraryId());
                attachedItineraryCollectionNew.add(itineraryCollectionNewItineraryToAttach);
            }
            itineraryCollectionNew = attachedItineraryCollectionNew;
            port.setItineraryCollection(itineraryCollectionNew);
            Collection<Itinerary> attachedItineraryCollection1New = new ArrayList<Itinerary>();
            for (Itinerary itineraryCollection1NewItineraryToAttach : itineraryCollection1New) {
                itineraryCollection1NewItineraryToAttach = em.getReference(itineraryCollection1NewItineraryToAttach.getClass(), itineraryCollection1NewItineraryToAttach.getItineraryId());
                attachedItineraryCollection1New.add(itineraryCollection1NewItineraryToAttach);
            }
            itineraryCollection1New = attachedItineraryCollection1New;
            port.setItineraryCollection1(itineraryCollection1New);
            Collection<Itinerary> attachedItineraryCollection2New = new ArrayList<Itinerary>();
            for (Itinerary itineraryCollection2NewItineraryToAttach : itineraryCollection2New) {
                itineraryCollection2NewItineraryToAttach = em.getReference(itineraryCollection2NewItineraryToAttach.getClass(), itineraryCollection2NewItineraryToAttach.getItineraryId());
                attachedItineraryCollection2New.add(itineraryCollection2NewItineraryToAttach);
            }
            itineraryCollection2New = attachedItineraryCollection2New;
            port.setItineraryCollection2(itineraryCollection2New);
            port = em.merge(port);
            if (countryIdOld != null && !countryIdOld.equals(countryIdNew)) {
                countryIdOld.getPortCollection().remove(port);
                countryIdOld = em.merge(countryIdOld);
            }
            if (countryIdNew != null && !countryIdNew.equals(countryIdOld)) {
                countryIdNew.getPortCollection().add(port);
                countryIdNew = em.merge(countryIdNew);
            }
            for (Itinerary itineraryCollectionNewItinerary : itineraryCollectionNew) {
                if (!itineraryCollectionOld.contains(itineraryCollectionNewItinerary)) {
                    Port oldEndPortIdOfItineraryCollectionNewItinerary = itineraryCollectionNewItinerary.getEndPortId();
                    itineraryCollectionNewItinerary.setEndPortId(port);
                    itineraryCollectionNewItinerary = em.merge(itineraryCollectionNewItinerary);
                    if (oldEndPortIdOfItineraryCollectionNewItinerary != null && !oldEndPortIdOfItineraryCollectionNewItinerary.equals(port)) {
                        oldEndPortIdOfItineraryCollectionNewItinerary.getItineraryCollection().remove(itineraryCollectionNewItinerary);
                        oldEndPortIdOfItineraryCollectionNewItinerary = em.merge(oldEndPortIdOfItineraryCollectionNewItinerary);
                    }
                }
            }
            for (Itinerary itineraryCollection1NewItinerary : itineraryCollection1New) {
                if (!itineraryCollection1Old.contains(itineraryCollection1NewItinerary)) {
                    Port oldOperatingPortIdOfItineraryCollection1NewItinerary = itineraryCollection1NewItinerary.getOperatingPortId();
                    itineraryCollection1NewItinerary.setOperatingPortId(port);
                    itineraryCollection1NewItinerary = em.merge(itineraryCollection1NewItinerary);
                    if (oldOperatingPortIdOfItineraryCollection1NewItinerary != null && !oldOperatingPortIdOfItineraryCollection1NewItinerary.equals(port)) {
                        oldOperatingPortIdOfItineraryCollection1NewItinerary.getItineraryCollection1().remove(itineraryCollection1NewItinerary);
                        oldOperatingPortIdOfItineraryCollection1NewItinerary = em.merge(oldOperatingPortIdOfItineraryCollection1NewItinerary);
                    }
                }
            }
            for (Itinerary itineraryCollection2NewItinerary : itineraryCollection2New) {
                if (!itineraryCollection2Old.contains(itineraryCollection2NewItinerary)) {
                    Port oldStartPortIdOfItineraryCollection2NewItinerary = itineraryCollection2NewItinerary.getStartPortId();
                    itineraryCollection2NewItinerary.setStartPortId(port);
                    itineraryCollection2NewItinerary = em.merge(itineraryCollection2NewItinerary);
                    if (oldStartPortIdOfItineraryCollection2NewItinerary != null && !oldStartPortIdOfItineraryCollection2NewItinerary.equals(port)) {
                        oldStartPortIdOfItineraryCollection2NewItinerary.getItineraryCollection2().remove(itineraryCollection2NewItinerary);
                        oldStartPortIdOfItineraryCollection2NewItinerary = em.merge(oldStartPortIdOfItineraryCollection2NewItinerary);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = port.getPortId();
                if (findPort(id) == null) {
                    throw new NonexistentEntityException("The port with id " + id + " no longer exists.");
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
            Port port;
            try {
                port = em.getReference(Port.class, id);
                port.getPortId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The port with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Itinerary> itineraryCollectionOrphanCheck = port.getItineraryCollection();
            for (Itinerary itineraryCollectionOrphanCheckItinerary : itineraryCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Port (" + port + ") cannot be destroyed since the Itinerary " + itineraryCollectionOrphanCheckItinerary + " in its itineraryCollection field has a non-nullable endPortId field.");
            }
            Collection<Itinerary> itineraryCollection1OrphanCheck = port.getItineraryCollection1();
            for (Itinerary itineraryCollection1OrphanCheckItinerary : itineraryCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Port (" + port + ") cannot be destroyed since the Itinerary " + itineraryCollection1OrphanCheckItinerary + " in its itineraryCollection1 field has a non-nullable operatingPortId field.");
            }
            Collection<Itinerary> itineraryCollection2OrphanCheck = port.getItineraryCollection2();
            for (Itinerary itineraryCollection2OrphanCheckItinerary : itineraryCollection2OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Port (" + port + ") cannot be destroyed since the Itinerary " + itineraryCollection2OrphanCheckItinerary + " in its itineraryCollection2 field has a non-nullable startPortId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Country countryId = port.getCountryId();
            if (countryId != null) {
                countryId.getPortCollection().remove(port);
                countryId = em.merge(countryId);
            }
            em.remove(port);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Port> findPortEntities() {
        return findPortEntities(true, -1, -1);
    }

    public List<Port> findPortEntities(int maxResults, int firstResult) {
        return findPortEntities(false, maxResults, firstResult);
    }

    private List<Port> findPortEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Port.class));
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

    public Port findPort(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Port.class, id);
        } finally {
            em.close();
        }
    }

    public int getPortCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Port> rt = cq.from(Port.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
