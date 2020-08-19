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
import mx.tab.wgp.qsmaritimex.entidades.Rol;
import mx.tab.wgp.qsmaritimex.entidades.User;
import mx.tab.wgp.qsmaritimex.entidades.UserRol;

/**
 *
 * @author WilliamGP025
 */
public class UserRolJpaController implements Serializable {

    public UserRolJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserRol userRol) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol rolId = userRol.getRolId();
            if (rolId != null) {
                rolId = em.getReference(rolId.getClass(), rolId.getRolId());
                userRol.setRolId(rolId);
            }
            User userId = userRol.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getUserId());
                userRol.setUserId(userId);
            }
            em.persist(userRol);
            if (rolId != null) {
                rolId.getUserRolCollection().add(userRol);
                rolId = em.merge(rolId);
            }
            if (userId != null) {
                userId.getUserRolCollection().add(userRol);
                userId = em.merge(userId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UserRol userRol) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UserRol persistentUserRol = em.find(UserRol.class, userRol.getUserRolId());
            Rol rolIdOld = persistentUserRol.getRolId();
            Rol rolIdNew = userRol.getRolId();
            User userIdOld = persistentUserRol.getUserId();
            User userIdNew = userRol.getUserId();
            if (rolIdNew != null) {
                rolIdNew = em.getReference(rolIdNew.getClass(), rolIdNew.getRolId());
                userRol.setRolId(rolIdNew);
            }
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getUserId());
                userRol.setUserId(userIdNew);
            }
            userRol = em.merge(userRol);
            if (rolIdOld != null && !rolIdOld.equals(rolIdNew)) {
                rolIdOld.getUserRolCollection().remove(userRol);
                rolIdOld = em.merge(rolIdOld);
            }
            if (rolIdNew != null && !rolIdNew.equals(rolIdOld)) {
                rolIdNew.getUserRolCollection().add(userRol);
                rolIdNew = em.merge(rolIdNew);
            }
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getUserRolCollection().remove(userRol);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getUserRolCollection().add(userRol);
                userIdNew = em.merge(userIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = userRol.getUserRolId();
                if (findUserRol(id) == null) {
                    throw new NonexistentEntityException("The userRol with id " + id + " no longer exists.");
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
            UserRol userRol;
            try {
                userRol = em.getReference(UserRol.class, id);
                userRol.getUserRolId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userRol with id " + id + " no longer exists.", enfe);
            }
            Rol rolId = userRol.getRolId();
            if (rolId != null) {
                rolId.getUserRolCollection().remove(userRol);
                rolId = em.merge(rolId);
            }
            User userId = userRol.getUserId();
            if (userId != null) {
                userId.getUserRolCollection().remove(userRol);
                userId = em.merge(userId);
            }
            em.remove(userRol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UserRol> findUserRolEntities() {
        return findUserRolEntities(true, -1, -1);
    }

    public List<UserRol> findUserRolEntities(int maxResults, int firstResult) {
        return findUserRolEntities(false, maxResults, firstResult);
    }

    private List<UserRol> findUserRolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserRol.class));
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

    public UserRol findUserRol(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserRol.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserRolCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UserRol> rt = cq.from(UserRol.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
