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
import mx.tab.wgp.qsmaritimex.entidades.ScreenObject;
import mx.tab.wgp.qsmaritimex.entidades.SpecialRolScreenObject;

/**
 *
 * @author WilliamGP025
 */
public class SpecialRolScreenObjectJpaController implements Serializable {

    public SpecialRolScreenObjectJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SpecialRolScreenObject specialRolScreenObject) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ScreenObject idScreenObject = specialRolScreenObject.getIdScreenObject();
            if (idScreenObject != null) {
                idScreenObject = em.getReference(idScreenObject.getClass(), idScreenObject.getScreenObjectId());
                specialRolScreenObject.setIdScreenObject(idScreenObject);
            }
            em.persist(specialRolScreenObject);
            if (idScreenObject != null) {
                idScreenObject.getSpecialRolScreenObjectCollection().add(specialRolScreenObject);
                idScreenObject = em.merge(idScreenObject);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SpecialRolScreenObject specialRolScreenObject) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SpecialRolScreenObject persistentSpecialRolScreenObject = em.find(SpecialRolScreenObject.class, specialRolScreenObject.getIdSpecialScreenObject());
            ScreenObject idScreenObjectOld = persistentSpecialRolScreenObject.getIdScreenObject();
            ScreenObject idScreenObjectNew = specialRolScreenObject.getIdScreenObject();
            if (idScreenObjectNew != null) {
                idScreenObjectNew = em.getReference(idScreenObjectNew.getClass(), idScreenObjectNew.getScreenObjectId());
                specialRolScreenObject.setIdScreenObject(idScreenObjectNew);
            }
            specialRolScreenObject = em.merge(specialRolScreenObject);
            if (idScreenObjectOld != null && !idScreenObjectOld.equals(idScreenObjectNew)) {
                idScreenObjectOld.getSpecialRolScreenObjectCollection().remove(specialRolScreenObject);
                idScreenObjectOld = em.merge(idScreenObjectOld);
            }
            if (idScreenObjectNew != null && !idScreenObjectNew.equals(idScreenObjectOld)) {
                idScreenObjectNew.getSpecialRolScreenObjectCollection().add(specialRolScreenObject);
                idScreenObjectNew = em.merge(idScreenObjectNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = specialRolScreenObject.getIdSpecialScreenObject();
                if (findSpecialRolScreenObject(id) == null) {
                    throw new NonexistentEntityException("The specialRolScreenObject with id " + id + " no longer exists.");
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
            SpecialRolScreenObject specialRolScreenObject;
            try {
                specialRolScreenObject = em.getReference(SpecialRolScreenObject.class, id);
                specialRolScreenObject.getIdSpecialScreenObject();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The specialRolScreenObject with id " + id + " no longer exists.", enfe);
            }
            ScreenObject idScreenObject = specialRolScreenObject.getIdScreenObject();
            if (idScreenObject != null) {
                idScreenObject.getSpecialRolScreenObjectCollection().remove(specialRolScreenObject);
                idScreenObject = em.merge(idScreenObject);
            }
            em.remove(specialRolScreenObject);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SpecialRolScreenObject> findSpecialRolScreenObjectEntities() {
        return findSpecialRolScreenObjectEntities(true, -1, -1);
    }

    public List<SpecialRolScreenObject> findSpecialRolScreenObjectEntities(int maxResults, int firstResult) {
        return findSpecialRolScreenObjectEntities(false, maxResults, firstResult);
    }

    private List<SpecialRolScreenObject> findSpecialRolScreenObjectEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SpecialRolScreenObject.class));
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

    public SpecialRolScreenObject findSpecialRolScreenObject(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SpecialRolScreenObject.class, id);
        } finally {
            em.close();
        }
    }

    public int getSpecialRolScreenObjectCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SpecialRolScreenObject> rt = cq.from(SpecialRolScreenObject.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
