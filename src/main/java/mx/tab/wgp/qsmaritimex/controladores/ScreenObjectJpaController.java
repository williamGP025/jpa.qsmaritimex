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
import mx.tab.wgp.qsmaritimex.entidades.pantalla.Screen;
import mx.tab.wgp.qsmaritimex.entidades.pantalla.SpecialRolScreenObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.pantalla.RolScreenObject;
import mx.tab.wgp.qsmaritimex.entidades.pantalla.ScreenObject;

/**
 *
 * @author William
 */
public class ScreenObjectJpaController implements Serializable {

    public ScreenObjectJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ScreenObject screenObject) {
        if (screenObject.getSpecialRolScreenObjectCollection() == null) {
            screenObject.setSpecialRolScreenObjectCollection(new ArrayList<SpecialRolScreenObject>());
        }
        if (screenObject.getRolScreenObjectCollection() == null) {
            screenObject.setRolScreenObjectCollection(new ArrayList<RolScreenObject>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Screen screenId = screenObject.getScreenId();
            if (screenId != null) {
                screenId = em.getReference(screenId.getClass(), screenId.getScreenId());
                screenObject.setScreenId(screenId);
            }
            Collection<SpecialRolScreenObject> attachedSpecialRolScreenObjectCollection = new ArrayList<SpecialRolScreenObject>();
            for (SpecialRolScreenObject specialRolScreenObjectCollectionSpecialRolScreenObjectToAttach : screenObject.getSpecialRolScreenObjectCollection()) {
                specialRolScreenObjectCollectionSpecialRolScreenObjectToAttach = em.getReference(specialRolScreenObjectCollectionSpecialRolScreenObjectToAttach.getClass(), specialRolScreenObjectCollectionSpecialRolScreenObjectToAttach.getIdSpecialScreenObject());
                attachedSpecialRolScreenObjectCollection.add(specialRolScreenObjectCollectionSpecialRolScreenObjectToAttach);
            }
            screenObject.setSpecialRolScreenObjectCollection(attachedSpecialRolScreenObjectCollection);
            Collection<RolScreenObject> attachedRolScreenObjectCollection = new ArrayList<RolScreenObject>();
            for (RolScreenObject rolScreenObjectCollectionRolScreenObjectToAttach : screenObject.getRolScreenObjectCollection()) {
                rolScreenObjectCollectionRolScreenObjectToAttach = em.getReference(rolScreenObjectCollectionRolScreenObjectToAttach.getClass(), rolScreenObjectCollectionRolScreenObjectToAttach.getScreenObjectRolId());
                attachedRolScreenObjectCollection.add(rolScreenObjectCollectionRolScreenObjectToAttach);
            }
            screenObject.setRolScreenObjectCollection(attachedRolScreenObjectCollection);
            em.persist(screenObject);
            if (screenId != null) {
                screenId.getScreenObjectCollection().add(screenObject);
                screenId = em.merge(screenId);
            }
            for (SpecialRolScreenObject specialRolScreenObjectCollectionSpecialRolScreenObject : screenObject.getSpecialRolScreenObjectCollection()) {
                ScreenObject oldIdScreenObjectOfSpecialRolScreenObjectCollectionSpecialRolScreenObject = specialRolScreenObjectCollectionSpecialRolScreenObject.getIdScreenObject();
                specialRolScreenObjectCollectionSpecialRolScreenObject.setIdScreenObject(screenObject);
                specialRolScreenObjectCollectionSpecialRolScreenObject = em.merge(specialRolScreenObjectCollectionSpecialRolScreenObject);
                if (oldIdScreenObjectOfSpecialRolScreenObjectCollectionSpecialRolScreenObject != null) {
                    oldIdScreenObjectOfSpecialRolScreenObjectCollectionSpecialRolScreenObject.getSpecialRolScreenObjectCollection().remove(specialRolScreenObjectCollectionSpecialRolScreenObject);
                    oldIdScreenObjectOfSpecialRolScreenObjectCollectionSpecialRolScreenObject = em.merge(oldIdScreenObjectOfSpecialRolScreenObjectCollectionSpecialRolScreenObject);
                }
            }
            for (RolScreenObject rolScreenObjectCollectionRolScreenObject : screenObject.getRolScreenObjectCollection()) {
                ScreenObject oldScreenObjectIdOfRolScreenObjectCollectionRolScreenObject = rolScreenObjectCollectionRolScreenObject.getScreenObjectId();
                rolScreenObjectCollectionRolScreenObject.setScreenObjectId(screenObject);
                rolScreenObjectCollectionRolScreenObject = em.merge(rolScreenObjectCollectionRolScreenObject);
                if (oldScreenObjectIdOfRolScreenObjectCollectionRolScreenObject != null) {
                    oldScreenObjectIdOfRolScreenObjectCollectionRolScreenObject.getRolScreenObjectCollection().remove(rolScreenObjectCollectionRolScreenObject);
                    oldScreenObjectIdOfRolScreenObjectCollectionRolScreenObject = em.merge(oldScreenObjectIdOfRolScreenObjectCollectionRolScreenObject);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ScreenObject screenObject) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ScreenObject persistentScreenObject = em.find(ScreenObject.class, screenObject.getScreenObjectId());
            Screen screenIdOld = persistentScreenObject.getScreenId();
            Screen screenIdNew = screenObject.getScreenId();
            Collection<SpecialRolScreenObject> specialRolScreenObjectCollectionOld = persistentScreenObject.getSpecialRolScreenObjectCollection();
            Collection<SpecialRolScreenObject> specialRolScreenObjectCollectionNew = screenObject.getSpecialRolScreenObjectCollection();
            Collection<RolScreenObject> rolScreenObjectCollectionOld = persistentScreenObject.getRolScreenObjectCollection();
            Collection<RolScreenObject> rolScreenObjectCollectionNew = screenObject.getRolScreenObjectCollection();
            if (screenIdNew != null) {
                screenIdNew = em.getReference(screenIdNew.getClass(), screenIdNew.getScreenId());
                screenObject.setScreenId(screenIdNew);
            }
            Collection<SpecialRolScreenObject> attachedSpecialRolScreenObjectCollectionNew = new ArrayList<SpecialRolScreenObject>();
            for (SpecialRolScreenObject specialRolScreenObjectCollectionNewSpecialRolScreenObjectToAttach : specialRolScreenObjectCollectionNew) {
                specialRolScreenObjectCollectionNewSpecialRolScreenObjectToAttach = em.getReference(specialRolScreenObjectCollectionNewSpecialRolScreenObjectToAttach.getClass(), specialRolScreenObjectCollectionNewSpecialRolScreenObjectToAttach.getIdSpecialScreenObject());
                attachedSpecialRolScreenObjectCollectionNew.add(specialRolScreenObjectCollectionNewSpecialRolScreenObjectToAttach);
            }
            specialRolScreenObjectCollectionNew = attachedSpecialRolScreenObjectCollectionNew;
            screenObject.setSpecialRolScreenObjectCollection(specialRolScreenObjectCollectionNew);
            Collection<RolScreenObject> attachedRolScreenObjectCollectionNew = new ArrayList<RolScreenObject>();
            for (RolScreenObject rolScreenObjectCollectionNewRolScreenObjectToAttach : rolScreenObjectCollectionNew) {
                rolScreenObjectCollectionNewRolScreenObjectToAttach = em.getReference(rolScreenObjectCollectionNewRolScreenObjectToAttach.getClass(), rolScreenObjectCollectionNewRolScreenObjectToAttach.getScreenObjectRolId());
                attachedRolScreenObjectCollectionNew.add(rolScreenObjectCollectionNewRolScreenObjectToAttach);
            }
            rolScreenObjectCollectionNew = attachedRolScreenObjectCollectionNew;
            screenObject.setRolScreenObjectCollection(rolScreenObjectCollectionNew);
            screenObject = em.merge(screenObject);
            if (screenIdOld != null && !screenIdOld.equals(screenIdNew)) {
                screenIdOld.getScreenObjectCollection().remove(screenObject);
                screenIdOld = em.merge(screenIdOld);
            }
            if (screenIdNew != null && !screenIdNew.equals(screenIdOld)) {
                screenIdNew.getScreenObjectCollection().add(screenObject);
                screenIdNew = em.merge(screenIdNew);
            }
            for (SpecialRolScreenObject specialRolScreenObjectCollectionOldSpecialRolScreenObject : specialRolScreenObjectCollectionOld) {
                if (!specialRolScreenObjectCollectionNew.contains(specialRolScreenObjectCollectionOldSpecialRolScreenObject)) {
                    specialRolScreenObjectCollectionOldSpecialRolScreenObject.setIdScreenObject(null);
                    specialRolScreenObjectCollectionOldSpecialRolScreenObject = em.merge(specialRolScreenObjectCollectionOldSpecialRolScreenObject);
                }
            }
            for (SpecialRolScreenObject specialRolScreenObjectCollectionNewSpecialRolScreenObject : specialRolScreenObjectCollectionNew) {
                if (!specialRolScreenObjectCollectionOld.contains(specialRolScreenObjectCollectionNewSpecialRolScreenObject)) {
                    ScreenObject oldIdScreenObjectOfSpecialRolScreenObjectCollectionNewSpecialRolScreenObject = specialRolScreenObjectCollectionNewSpecialRolScreenObject.getIdScreenObject();
                    specialRolScreenObjectCollectionNewSpecialRolScreenObject.setIdScreenObject(screenObject);
                    specialRolScreenObjectCollectionNewSpecialRolScreenObject = em.merge(specialRolScreenObjectCollectionNewSpecialRolScreenObject);
                    if (oldIdScreenObjectOfSpecialRolScreenObjectCollectionNewSpecialRolScreenObject != null && !oldIdScreenObjectOfSpecialRolScreenObjectCollectionNewSpecialRolScreenObject.equals(screenObject)) {
                        oldIdScreenObjectOfSpecialRolScreenObjectCollectionNewSpecialRolScreenObject.getSpecialRolScreenObjectCollection().remove(specialRolScreenObjectCollectionNewSpecialRolScreenObject);
                        oldIdScreenObjectOfSpecialRolScreenObjectCollectionNewSpecialRolScreenObject = em.merge(oldIdScreenObjectOfSpecialRolScreenObjectCollectionNewSpecialRolScreenObject);
                    }
                }
            }
            for (RolScreenObject rolScreenObjectCollectionOldRolScreenObject : rolScreenObjectCollectionOld) {
                if (!rolScreenObjectCollectionNew.contains(rolScreenObjectCollectionOldRolScreenObject)) {
                    rolScreenObjectCollectionOldRolScreenObject.setScreenObjectId(null);
                    rolScreenObjectCollectionOldRolScreenObject = em.merge(rolScreenObjectCollectionOldRolScreenObject);
                }
            }
            for (RolScreenObject rolScreenObjectCollectionNewRolScreenObject : rolScreenObjectCollectionNew) {
                if (!rolScreenObjectCollectionOld.contains(rolScreenObjectCollectionNewRolScreenObject)) {
                    ScreenObject oldScreenObjectIdOfRolScreenObjectCollectionNewRolScreenObject = rolScreenObjectCollectionNewRolScreenObject.getScreenObjectId();
                    rolScreenObjectCollectionNewRolScreenObject.setScreenObjectId(screenObject);
                    rolScreenObjectCollectionNewRolScreenObject = em.merge(rolScreenObjectCollectionNewRolScreenObject);
                    if (oldScreenObjectIdOfRolScreenObjectCollectionNewRolScreenObject != null && !oldScreenObjectIdOfRolScreenObjectCollectionNewRolScreenObject.equals(screenObject)) {
                        oldScreenObjectIdOfRolScreenObjectCollectionNewRolScreenObject.getRolScreenObjectCollection().remove(rolScreenObjectCollectionNewRolScreenObject);
                        oldScreenObjectIdOfRolScreenObjectCollectionNewRolScreenObject = em.merge(oldScreenObjectIdOfRolScreenObjectCollectionNewRolScreenObject);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = screenObject.getScreenObjectId();
                if (findScreenObject(id) == null) {
                    throw new NonexistentEntityException("The screenObject with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ScreenObject screenObject;
            try {
                screenObject = em.getReference(ScreenObject.class, id);
                screenObject.getScreenObjectId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The screenObject with id " + id + " no longer exists.", enfe);
            }
            Screen screenId = screenObject.getScreenId();
            if (screenId != null) {
                screenId.getScreenObjectCollection().remove(screenObject);
                screenId = em.merge(screenId);
            }
            Collection<SpecialRolScreenObject> specialRolScreenObjectCollection = screenObject.getSpecialRolScreenObjectCollection();
            for (SpecialRolScreenObject specialRolScreenObjectCollectionSpecialRolScreenObject : specialRolScreenObjectCollection) {
                specialRolScreenObjectCollectionSpecialRolScreenObject.setIdScreenObject(null);
                specialRolScreenObjectCollectionSpecialRolScreenObject = em.merge(specialRolScreenObjectCollectionSpecialRolScreenObject);
            }
            Collection<RolScreenObject> rolScreenObjectCollection = screenObject.getRolScreenObjectCollection();
            for (RolScreenObject rolScreenObjectCollectionRolScreenObject : rolScreenObjectCollection) {
                rolScreenObjectCollectionRolScreenObject.setScreenObjectId(null);
                rolScreenObjectCollectionRolScreenObject = em.merge(rolScreenObjectCollectionRolScreenObject);
            }
            em.remove(screenObject);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ScreenObject> findScreenObjectEntities() {
        return findScreenObjectEntities(true, -1, -1);
    }

    public List<ScreenObject> findScreenObjectEntities(int maxResults, int firstResult) {
        return findScreenObjectEntities(false, maxResults, firstResult);
    }

    private List<ScreenObject> findScreenObjectEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ScreenObject.class));
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

    public ScreenObject findScreenObject(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ScreenObject.class, id);
        } finally {
            em.close();
        }
    }

    public int getScreenObjectCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ScreenObject> rt = cq.from(ScreenObject.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
