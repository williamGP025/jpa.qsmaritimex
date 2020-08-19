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
import mx.tab.wgp.qsmaritimex.entidades.CargoType;
import mx.tab.wgp.qsmaritimex.entidades.User;
import mx.tab.wgp.qsmaritimex.entidades.UserCargoType;

/**
 *
 * @author WilliamGP025
 */
public class UserCargoTypeJpaController implements Serializable {

    public UserCargoTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserCargoType userCargoType) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CargoType cargoTypeId = userCargoType.getCargoTypeId();
            if (cargoTypeId != null) {
                cargoTypeId = em.getReference(cargoTypeId.getClass(), cargoTypeId.getCargoTypeId());
                userCargoType.setCargoTypeId(cargoTypeId);
            }
            User userId = userCargoType.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getUserId());
                userCargoType.setUserId(userId);
            }
            em.persist(userCargoType);
            if (cargoTypeId != null) {
                cargoTypeId.getUserCargoTypeCollection().add(userCargoType);
                cargoTypeId = em.merge(cargoTypeId);
            }
            if (userId != null) {
                userId.getUserCargoTypeCollection().add(userCargoType);
                userId = em.merge(userId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UserCargoType userCargoType) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UserCargoType persistentUserCargoType = em.find(UserCargoType.class, userCargoType.getUserCargoTypeId());
            CargoType cargoTypeIdOld = persistentUserCargoType.getCargoTypeId();
            CargoType cargoTypeIdNew = userCargoType.getCargoTypeId();
            User userIdOld = persistentUserCargoType.getUserId();
            User userIdNew = userCargoType.getUserId();
            if (cargoTypeIdNew != null) {
                cargoTypeIdNew = em.getReference(cargoTypeIdNew.getClass(), cargoTypeIdNew.getCargoTypeId());
                userCargoType.setCargoTypeId(cargoTypeIdNew);
            }
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getUserId());
                userCargoType.setUserId(userIdNew);
            }
            userCargoType = em.merge(userCargoType);
            if (cargoTypeIdOld != null && !cargoTypeIdOld.equals(cargoTypeIdNew)) {
                cargoTypeIdOld.getUserCargoTypeCollection().remove(userCargoType);
                cargoTypeIdOld = em.merge(cargoTypeIdOld);
            }
            if (cargoTypeIdNew != null && !cargoTypeIdNew.equals(cargoTypeIdOld)) {
                cargoTypeIdNew.getUserCargoTypeCollection().add(userCargoType);
                cargoTypeIdNew = em.merge(cargoTypeIdNew);
            }
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getUserCargoTypeCollection().remove(userCargoType);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getUserCargoTypeCollection().add(userCargoType);
                userIdNew = em.merge(userIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = userCargoType.getUserCargoTypeId();
                if (findUserCargoType(id) == null) {
                    throw new NonexistentEntityException("The userCargoType with id " + id + " no longer exists.");
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
            UserCargoType userCargoType;
            try {
                userCargoType = em.getReference(UserCargoType.class, id);
                userCargoType.getUserCargoTypeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userCargoType with id " + id + " no longer exists.", enfe);
            }
            CargoType cargoTypeId = userCargoType.getCargoTypeId();
            if (cargoTypeId != null) {
                cargoTypeId.getUserCargoTypeCollection().remove(userCargoType);
                cargoTypeId = em.merge(cargoTypeId);
            }
            User userId = userCargoType.getUserId();
            if (userId != null) {
                userId.getUserCargoTypeCollection().remove(userCargoType);
                userId = em.merge(userId);
            }
            em.remove(userCargoType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UserCargoType> findUserCargoTypeEntities() {
        return findUserCargoTypeEntities(true, -1, -1);
    }

    public List<UserCargoType> findUserCargoTypeEntities(int maxResults, int firstResult) {
        return findUserCargoTypeEntities(false, maxResults, firstResult);
    }

    private List<UserCargoType> findUserCargoTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserCargoType.class));
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

    public UserCargoType findUserCargoType(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserCargoType.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserCargoTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UserCargoType> rt = cq.from(UserCargoType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
