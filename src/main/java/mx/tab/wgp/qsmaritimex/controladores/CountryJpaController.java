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
import mx.tab.wgp.qsmaritimex.entidades.Port;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.IllegalOrphanException;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.Country;
import mx.tab.wgp.qsmaritimex.entidades.Vessel;
import mx.tab.wgp.qsmaritimex.entidades.ShipOwner;

/**
 *
 * @author WilliamGP025
 */
public class CountryJpaController implements Serializable {

    public CountryJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Country country) {
        if (country.getPortCollection() == null) {
            country.setPortCollection(new ArrayList<Port>());
        }
        if (country.getVesselCollection() == null) {
            country.setVesselCollection(new ArrayList<Vessel>());
        }
        if (country.getShipOwnerCollection() == null) {
            country.setShipOwnerCollection(new ArrayList<ShipOwner>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Port> attachedPortCollection = new ArrayList<Port>();
            for (Port portCollectionPortToAttach : country.getPortCollection()) {
                portCollectionPortToAttach = em.getReference(portCollectionPortToAttach.getClass(), portCollectionPortToAttach.getPortId());
                attachedPortCollection.add(portCollectionPortToAttach);
            }
            country.setPortCollection(attachedPortCollection);
            Collection<Vessel> attachedVesselCollection = new ArrayList<Vessel>();
            for (Vessel vesselCollectionVesselToAttach : country.getVesselCollection()) {
                vesselCollectionVesselToAttach = em.getReference(vesselCollectionVesselToAttach.getClass(), vesselCollectionVesselToAttach.getVesselId());
                attachedVesselCollection.add(vesselCollectionVesselToAttach);
            }
            country.setVesselCollection(attachedVesselCollection);
            Collection<ShipOwner> attachedShipOwnerCollection = new ArrayList<ShipOwner>();
            for (ShipOwner shipOwnerCollectionShipOwnerToAttach : country.getShipOwnerCollection()) {
                shipOwnerCollectionShipOwnerToAttach = em.getReference(shipOwnerCollectionShipOwnerToAttach.getClass(), shipOwnerCollectionShipOwnerToAttach.getShipOwnerId());
                attachedShipOwnerCollection.add(shipOwnerCollectionShipOwnerToAttach);
            }
            country.setShipOwnerCollection(attachedShipOwnerCollection);
            em.persist(country);
            for (Port portCollectionPort : country.getPortCollection()) {
                Country oldCountryIdOfPortCollectionPort = portCollectionPort.getCountryId();
                portCollectionPort.setCountryId(country);
                portCollectionPort = em.merge(portCollectionPort);
                if (oldCountryIdOfPortCollectionPort != null) {
                    oldCountryIdOfPortCollectionPort.getPortCollection().remove(portCollectionPort);
                    oldCountryIdOfPortCollectionPort = em.merge(oldCountryIdOfPortCollectionPort);
                }
            }
            for (Vessel vesselCollectionVessel : country.getVesselCollection()) {
                Country oldCountryIdOfVesselCollectionVessel = vesselCollectionVessel.getCountryId();
                vesselCollectionVessel.setCountryId(country);
                vesselCollectionVessel = em.merge(vesselCollectionVessel);
                if (oldCountryIdOfVesselCollectionVessel != null) {
                    oldCountryIdOfVesselCollectionVessel.getVesselCollection().remove(vesselCollectionVessel);
                    oldCountryIdOfVesselCollectionVessel = em.merge(oldCountryIdOfVesselCollectionVessel);
                }
            }
            for (ShipOwner shipOwnerCollectionShipOwner : country.getShipOwnerCollection()) {
                Country oldCountryIdOfShipOwnerCollectionShipOwner = shipOwnerCollectionShipOwner.getCountryId();
                shipOwnerCollectionShipOwner.setCountryId(country);
                shipOwnerCollectionShipOwner = em.merge(shipOwnerCollectionShipOwner);
                if (oldCountryIdOfShipOwnerCollectionShipOwner != null) {
                    oldCountryIdOfShipOwnerCollectionShipOwner.getShipOwnerCollection().remove(shipOwnerCollectionShipOwner);
                    oldCountryIdOfShipOwnerCollectionShipOwner = em.merge(oldCountryIdOfShipOwnerCollectionShipOwner);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Country country) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Country persistentCountry = em.find(Country.class, country.getCountryId());
            Collection<Port> portCollectionOld = persistentCountry.getPortCollection();
            Collection<Port> portCollectionNew = country.getPortCollection();
            Collection<Vessel> vesselCollectionOld = persistentCountry.getVesselCollection();
            Collection<Vessel> vesselCollectionNew = country.getVesselCollection();
            Collection<ShipOwner> shipOwnerCollectionOld = persistentCountry.getShipOwnerCollection();
            Collection<ShipOwner> shipOwnerCollectionNew = country.getShipOwnerCollection();
            List<String> illegalOrphanMessages = null;
            for (Port portCollectionOldPort : portCollectionOld) {
                if (!portCollectionNew.contains(portCollectionOldPort)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Port " + portCollectionOldPort + " since its countryId field is not nullable.");
                }
            }
            for (Vessel vesselCollectionOldVessel : vesselCollectionOld) {
                if (!vesselCollectionNew.contains(vesselCollectionOldVessel)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Vessel " + vesselCollectionOldVessel + " since its countryId field is not nullable.");
                }
            }
            for (ShipOwner shipOwnerCollectionOldShipOwner : shipOwnerCollectionOld) {
                if (!shipOwnerCollectionNew.contains(shipOwnerCollectionOldShipOwner)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ShipOwner " + shipOwnerCollectionOldShipOwner + " since its countryId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Port> attachedPortCollectionNew = new ArrayList<Port>();
            for (Port portCollectionNewPortToAttach : portCollectionNew) {
                portCollectionNewPortToAttach = em.getReference(portCollectionNewPortToAttach.getClass(), portCollectionNewPortToAttach.getPortId());
                attachedPortCollectionNew.add(portCollectionNewPortToAttach);
            }
            portCollectionNew = attachedPortCollectionNew;
            country.setPortCollection(portCollectionNew);
            Collection<Vessel> attachedVesselCollectionNew = new ArrayList<Vessel>();
            for (Vessel vesselCollectionNewVesselToAttach : vesselCollectionNew) {
                vesselCollectionNewVesselToAttach = em.getReference(vesselCollectionNewVesselToAttach.getClass(), vesselCollectionNewVesselToAttach.getVesselId());
                attachedVesselCollectionNew.add(vesselCollectionNewVesselToAttach);
            }
            vesselCollectionNew = attachedVesselCollectionNew;
            country.setVesselCollection(vesselCollectionNew);
            Collection<ShipOwner> attachedShipOwnerCollectionNew = new ArrayList<ShipOwner>();
            for (ShipOwner shipOwnerCollectionNewShipOwnerToAttach : shipOwnerCollectionNew) {
                shipOwnerCollectionNewShipOwnerToAttach = em.getReference(shipOwnerCollectionNewShipOwnerToAttach.getClass(), shipOwnerCollectionNewShipOwnerToAttach.getShipOwnerId());
                attachedShipOwnerCollectionNew.add(shipOwnerCollectionNewShipOwnerToAttach);
            }
            shipOwnerCollectionNew = attachedShipOwnerCollectionNew;
            country.setShipOwnerCollection(shipOwnerCollectionNew);
            country = em.merge(country);
            for (Port portCollectionNewPort : portCollectionNew) {
                if (!portCollectionOld.contains(portCollectionNewPort)) {
                    Country oldCountryIdOfPortCollectionNewPort = portCollectionNewPort.getCountryId();
                    portCollectionNewPort.setCountryId(country);
                    portCollectionNewPort = em.merge(portCollectionNewPort);
                    if (oldCountryIdOfPortCollectionNewPort != null && !oldCountryIdOfPortCollectionNewPort.equals(country)) {
                        oldCountryIdOfPortCollectionNewPort.getPortCollection().remove(portCollectionNewPort);
                        oldCountryIdOfPortCollectionNewPort = em.merge(oldCountryIdOfPortCollectionNewPort);
                    }
                }
            }
            for (Vessel vesselCollectionNewVessel : vesselCollectionNew) {
                if (!vesselCollectionOld.contains(vesselCollectionNewVessel)) {
                    Country oldCountryIdOfVesselCollectionNewVessel = vesselCollectionNewVessel.getCountryId();
                    vesselCollectionNewVessel.setCountryId(country);
                    vesselCollectionNewVessel = em.merge(vesselCollectionNewVessel);
                    if (oldCountryIdOfVesselCollectionNewVessel != null && !oldCountryIdOfVesselCollectionNewVessel.equals(country)) {
                        oldCountryIdOfVesselCollectionNewVessel.getVesselCollection().remove(vesselCollectionNewVessel);
                        oldCountryIdOfVesselCollectionNewVessel = em.merge(oldCountryIdOfVesselCollectionNewVessel);
                    }
                }
            }
            for (ShipOwner shipOwnerCollectionNewShipOwner : shipOwnerCollectionNew) {
                if (!shipOwnerCollectionOld.contains(shipOwnerCollectionNewShipOwner)) {
                    Country oldCountryIdOfShipOwnerCollectionNewShipOwner = shipOwnerCollectionNewShipOwner.getCountryId();
                    shipOwnerCollectionNewShipOwner.setCountryId(country);
                    shipOwnerCollectionNewShipOwner = em.merge(shipOwnerCollectionNewShipOwner);
                    if (oldCountryIdOfShipOwnerCollectionNewShipOwner != null && !oldCountryIdOfShipOwnerCollectionNewShipOwner.equals(country)) {
                        oldCountryIdOfShipOwnerCollectionNewShipOwner.getShipOwnerCollection().remove(shipOwnerCollectionNewShipOwner);
                        oldCountryIdOfShipOwnerCollectionNewShipOwner = em.merge(oldCountryIdOfShipOwnerCollectionNewShipOwner);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = country.getCountryId();
                if (findCountry(id) == null) {
                    throw new NonexistentEntityException("The country with id " + id + " no longer exists.");
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
            Country country;
            try {
                country = em.getReference(Country.class, id);
                country.getCountryId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The country with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Port> portCollectionOrphanCheck = country.getPortCollection();
            for (Port portCollectionOrphanCheckPort : portCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Country (" + country + ") cannot be destroyed since the Port " + portCollectionOrphanCheckPort + " in its portCollection field has a non-nullable countryId field.");
            }
            Collection<Vessel> vesselCollectionOrphanCheck = country.getVesselCollection();
            for (Vessel vesselCollectionOrphanCheckVessel : vesselCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Country (" + country + ") cannot be destroyed since the Vessel " + vesselCollectionOrphanCheckVessel + " in its vesselCollection field has a non-nullable countryId field.");
            }
            Collection<ShipOwner> shipOwnerCollectionOrphanCheck = country.getShipOwnerCollection();
            for (ShipOwner shipOwnerCollectionOrphanCheckShipOwner : shipOwnerCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Country (" + country + ") cannot be destroyed since the ShipOwner " + shipOwnerCollectionOrphanCheckShipOwner + " in its shipOwnerCollection field has a non-nullable countryId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(country);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Country> findCountryEntities() {
        return findCountryEntities(true, -1, -1);
    }

    public List<Country> findCountryEntities(int maxResults, int firstResult) {
        return findCountryEntities(false, maxResults, firstResult);
    }

    private List<Country> findCountryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Country.class));
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

    public Country findCountry(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Country.class, id);
        } finally {
            em.close();
        }
    }

    public int getCountryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Country> rt = cq.from(Country.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
