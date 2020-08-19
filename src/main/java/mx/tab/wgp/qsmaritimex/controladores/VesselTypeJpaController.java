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
import mx.tab.wgp.qsmaritimex.entidades.Vessel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.IllegalOrphanException;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.VesselType;

/**
 *
 * @author WilliamGP025
 */
public class VesselTypeJpaController implements Serializable {

    public VesselTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VesselType vesselType) {
        if (vesselType.getVesselCollection() == null) {
            vesselType.setVesselCollection(new ArrayList<Vessel>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Vessel> attachedVesselCollection = new ArrayList<Vessel>();
            for (Vessel vesselCollectionVesselToAttach : vesselType.getVesselCollection()) {
                vesselCollectionVesselToAttach = em.getReference(vesselCollectionVesselToAttach.getClass(), vesselCollectionVesselToAttach.getVesselId());
                attachedVesselCollection.add(vesselCollectionVesselToAttach);
            }
            vesselType.setVesselCollection(attachedVesselCollection);
            em.persist(vesselType);
            for (Vessel vesselCollectionVessel : vesselType.getVesselCollection()) {
                VesselType oldVesselTypeIdOfVesselCollectionVessel = vesselCollectionVessel.getVesselTypeId();
                vesselCollectionVessel.setVesselTypeId(vesselType);
                vesselCollectionVessel = em.merge(vesselCollectionVessel);
                if (oldVesselTypeIdOfVesselCollectionVessel != null) {
                    oldVesselTypeIdOfVesselCollectionVessel.getVesselCollection().remove(vesselCollectionVessel);
                    oldVesselTypeIdOfVesselCollectionVessel = em.merge(oldVesselTypeIdOfVesselCollectionVessel);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VesselType vesselType) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VesselType persistentVesselType = em.find(VesselType.class, vesselType.getVesselTypeId());
            Collection<Vessel> vesselCollectionOld = persistentVesselType.getVesselCollection();
            Collection<Vessel> vesselCollectionNew = vesselType.getVesselCollection();
            List<String> illegalOrphanMessages = null;
            for (Vessel vesselCollectionOldVessel : vesselCollectionOld) {
                if (!vesselCollectionNew.contains(vesselCollectionOldVessel)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Vessel " + vesselCollectionOldVessel + " since its vesselTypeId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Vessel> attachedVesselCollectionNew = new ArrayList<Vessel>();
            for (Vessel vesselCollectionNewVesselToAttach : vesselCollectionNew) {
                vesselCollectionNewVesselToAttach = em.getReference(vesselCollectionNewVesselToAttach.getClass(), vesselCollectionNewVesselToAttach.getVesselId());
                attachedVesselCollectionNew.add(vesselCollectionNewVesselToAttach);
            }
            vesselCollectionNew = attachedVesselCollectionNew;
            vesselType.setVesselCollection(vesselCollectionNew);
            vesselType = em.merge(vesselType);
            for (Vessel vesselCollectionNewVessel : vesselCollectionNew) {
                if (!vesselCollectionOld.contains(vesselCollectionNewVessel)) {
                    VesselType oldVesselTypeIdOfVesselCollectionNewVessel = vesselCollectionNewVessel.getVesselTypeId();
                    vesselCollectionNewVessel.setVesselTypeId(vesselType);
                    vesselCollectionNewVessel = em.merge(vesselCollectionNewVessel);
                    if (oldVesselTypeIdOfVesselCollectionNewVessel != null && !oldVesselTypeIdOfVesselCollectionNewVessel.equals(vesselType)) {
                        oldVesselTypeIdOfVesselCollectionNewVessel.getVesselCollection().remove(vesselCollectionNewVessel);
                        oldVesselTypeIdOfVesselCollectionNewVessel = em.merge(oldVesselTypeIdOfVesselCollectionNewVessel);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vesselType.getVesselTypeId();
                if (findVesselType(id) == null) {
                    throw new NonexistentEntityException("The vesselType with id " + id + " no longer exists.");
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
            VesselType vesselType;
            try {
                vesselType = em.getReference(VesselType.class, id);
                vesselType.getVesselTypeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vesselType with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Vessel> vesselCollectionOrphanCheck = vesselType.getVesselCollection();
            for (Vessel vesselCollectionOrphanCheckVessel : vesselCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This VesselType (" + vesselType + ") cannot be destroyed since the Vessel " + vesselCollectionOrphanCheckVessel + " in its vesselCollection field has a non-nullable vesselTypeId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(vesselType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VesselType> findVesselTypeEntities() {
        return findVesselTypeEntities(true, -1, -1);
    }

    public List<VesselType> findVesselTypeEntities(int maxResults, int firstResult) {
        return findVesselTypeEntities(false, maxResults, firstResult);
    }

    private List<VesselType> findVesselTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VesselType.class));
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

    public VesselType findVesselType(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VesselType.class, id);
        } finally {
            em.close();
        }
    }

    public int getVesselTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VesselType> rt = cq.from(VesselType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
