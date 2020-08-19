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
import mx.tab.wgp.qsmaritimex.entidades.ScreenObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.Screen;

/**
 *
 * @author WilliamGP025
 */
public class ScreenJpaController implements Serializable {

    public ScreenJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Screen screen) {
        if (screen.getScreenObjectCollection() == null) {
            screen.setScreenObjectCollection(new ArrayList<ScreenObject>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ScreenObject> attachedScreenObjectCollection = new ArrayList<ScreenObject>();
            for (ScreenObject screenObjectCollectionScreenObjectToAttach : screen.getScreenObjectCollection()) {
                screenObjectCollectionScreenObjectToAttach = em.getReference(screenObjectCollectionScreenObjectToAttach.getClass(), screenObjectCollectionScreenObjectToAttach.getScreenObjectId());
                attachedScreenObjectCollection.add(screenObjectCollectionScreenObjectToAttach);
            }
            screen.setScreenObjectCollection(attachedScreenObjectCollection);
            em.persist(screen);
            for (ScreenObject screenObjectCollectionScreenObject : screen.getScreenObjectCollection()) {
                Screen oldScreenIdOfScreenObjectCollectionScreenObject = screenObjectCollectionScreenObject.getScreenId();
                screenObjectCollectionScreenObject.setScreenId(screen);
                screenObjectCollectionScreenObject = em.merge(screenObjectCollectionScreenObject);
                if (oldScreenIdOfScreenObjectCollectionScreenObject != null) {
                    oldScreenIdOfScreenObjectCollectionScreenObject.getScreenObjectCollection().remove(screenObjectCollectionScreenObject);
                    oldScreenIdOfScreenObjectCollectionScreenObject = em.merge(oldScreenIdOfScreenObjectCollectionScreenObject);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Screen screen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Screen persistentScreen = em.find(Screen.class, screen.getScreenId());
            Collection<ScreenObject> screenObjectCollectionOld = persistentScreen.getScreenObjectCollection();
            Collection<ScreenObject> screenObjectCollectionNew = screen.getScreenObjectCollection();
            Collection<ScreenObject> attachedScreenObjectCollectionNew = new ArrayList<ScreenObject>();
            for (ScreenObject screenObjectCollectionNewScreenObjectToAttach : screenObjectCollectionNew) {
                screenObjectCollectionNewScreenObjectToAttach = em.getReference(screenObjectCollectionNewScreenObjectToAttach.getClass(), screenObjectCollectionNewScreenObjectToAttach.getScreenObjectId());
                attachedScreenObjectCollectionNew.add(screenObjectCollectionNewScreenObjectToAttach);
            }
            screenObjectCollectionNew = attachedScreenObjectCollectionNew;
            screen.setScreenObjectCollection(screenObjectCollectionNew);
            screen = em.merge(screen);
            for (ScreenObject screenObjectCollectionOldScreenObject : screenObjectCollectionOld) {
                if (!screenObjectCollectionNew.contains(screenObjectCollectionOldScreenObject)) {
                    screenObjectCollectionOldScreenObject.setScreenId(null);
                    screenObjectCollectionOldScreenObject = em.merge(screenObjectCollectionOldScreenObject);
                }
            }
            for (ScreenObject screenObjectCollectionNewScreenObject : screenObjectCollectionNew) {
                if (!screenObjectCollectionOld.contains(screenObjectCollectionNewScreenObject)) {
                    Screen oldScreenIdOfScreenObjectCollectionNewScreenObject = screenObjectCollectionNewScreenObject.getScreenId();
                    screenObjectCollectionNewScreenObject.setScreenId(screen);
                    screenObjectCollectionNewScreenObject = em.merge(screenObjectCollectionNewScreenObject);
                    if (oldScreenIdOfScreenObjectCollectionNewScreenObject != null && !oldScreenIdOfScreenObjectCollectionNewScreenObject.equals(screen)) {
                        oldScreenIdOfScreenObjectCollectionNewScreenObject.getScreenObjectCollection().remove(screenObjectCollectionNewScreenObject);
                        oldScreenIdOfScreenObjectCollectionNewScreenObject = em.merge(oldScreenIdOfScreenObjectCollectionNewScreenObject);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = screen.getScreenId();
                if (findScreen(id) == null) {
                    throw new NonexistentEntityException("The screen with id " + id + " no longer exists.");
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
            Screen screen;
            try {
                screen = em.getReference(Screen.class, id);
                screen.getScreenId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The screen with id " + id + " no longer exists.", enfe);
            }
            Collection<ScreenObject> screenObjectCollection = screen.getScreenObjectCollection();
            for (ScreenObject screenObjectCollectionScreenObject : screenObjectCollection) {
                screenObjectCollectionScreenObject.setScreenId(null);
                screenObjectCollectionScreenObject = em.merge(screenObjectCollectionScreenObject);
            }
            em.remove(screen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Screen> findScreenEntities() {
        return findScreenEntities(true, -1, -1);
    }

    public List<Screen> findScreenEntities(int maxResults, int firstResult) {
        return findScreenEntities(false, maxResults, firstResult);
    }

    private List<Screen> findScreenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Screen.class));
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

    public Screen findScreen(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Screen.class, id);
        } finally {
            em.close();
        }
    }

    public int getScreenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Screen> rt = cq.from(Screen.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
