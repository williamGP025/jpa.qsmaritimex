/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.controladores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.pantalla.RolScreenObject;
import mx.tab.wgp.qsmaritimex.entidades.pantalla.ScreenObject;

/**
 *
 * @author William
 */
public class RolScreenObjectJpaController implements Serializable {

    public RolScreenObjectJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RolScreenObject rolScreenObject) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ScreenObject screenObjectId = rolScreenObject.getScreenObjectId();
            if (screenObjectId != null) {
                screenObjectId = em.getReference(screenObjectId.getClass(), screenObjectId.getScreenObjectId());
                rolScreenObject.setScreenObjectId(screenObjectId);
            }
            em.persist(rolScreenObject);
            if (screenObjectId != null) {
                screenObjectId.getRolScreenObjectCollection().add(rolScreenObject);
                screenObjectId = em.merge(screenObjectId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RolScreenObject rolScreenObject) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RolScreenObject persistentRolScreenObject = em.find(RolScreenObject.class, rolScreenObject.getScreenObjectRolId());
            ScreenObject screenObjectIdOld = persistentRolScreenObject.getScreenObjectId();
            ScreenObject screenObjectIdNew = rolScreenObject.getScreenObjectId();
            if (screenObjectIdNew != null) {
                screenObjectIdNew = em.getReference(screenObjectIdNew.getClass(), screenObjectIdNew.getScreenObjectId());
                rolScreenObject.setScreenObjectId(screenObjectIdNew);
            }
            rolScreenObject = em.merge(rolScreenObject);
            if (screenObjectIdOld != null && !screenObjectIdOld.equals(screenObjectIdNew)) {
                screenObjectIdOld.getRolScreenObjectCollection().remove(rolScreenObject);
                screenObjectIdOld = em.merge(screenObjectIdOld);
            }
            if (screenObjectIdNew != null && !screenObjectIdNew.equals(screenObjectIdOld)) {
                screenObjectIdNew.getRolScreenObjectCollection().add(rolScreenObject);
                screenObjectIdNew = em.merge(screenObjectIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rolScreenObject.getScreenObjectRolId();
                if (findRolScreenObject(id) == null) {
                    throw new NonexistentEntityException("The rolScreenObject with id " + id + " no longer exists.");
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
            RolScreenObject rolScreenObject;
            try {
                rolScreenObject = em.getReference(RolScreenObject.class, id);
                rolScreenObject.getScreenObjectRolId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rolScreenObject with id " + id + " no longer exists.", enfe);
            }
            ScreenObject screenObjectId = rolScreenObject.getScreenObjectId();
            if (screenObjectId != null) {
                screenObjectId.getRolScreenObjectCollection().remove(rolScreenObject);
                screenObjectId = em.merge(screenObjectId);
            }
            em.remove(rolScreenObject);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RolScreenObject> findRolScreenObjectEntities() {
        return findRolScreenObjectEntities(true, -1, -1);
    }

    public List<RolScreenObject> findRolScreenObjectEntities(int maxResults, int firstResult) {
        return findRolScreenObjectEntities(false, maxResults, firstResult);
    }

    private List<RolScreenObject> findRolScreenObjectEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RolScreenObject.class));
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

    public RolScreenObject findRolScreenObject(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RolScreenObject.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolScreenObjectCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RolScreenObject> rt = cq.from(RolScreenObject.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
