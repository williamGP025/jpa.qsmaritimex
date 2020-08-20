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
import mx.tab.wgp.qsmaritimex.entidades.nominacion.Line;
import mx.tab.wgp.qsmaritimex.entidades.usuario.User;
import mx.tab.wgp.qsmaritimex.entidades.usuario.UserLine;

/**
 *
 * @author William
 */
public class UserLineJpaController implements Serializable {

    public UserLineJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserLine userLine) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Line lineId = userLine.getLineId();
            if (lineId != null) {
                lineId = em.getReference(lineId.getClass(), lineId.getLineId());
                userLine.setLineId(lineId);
            }
            User userId = userLine.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getUserId());
                userLine.setUserId(userId);
            }
            em.persist(userLine);
            if (lineId != null) {
                lineId.getUserLineCollection().add(userLine);
                lineId = em.merge(lineId);
            }
            if (userId != null) {
                userId.getUserLineCollection().add(userLine);
                userId = em.merge(userId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UserLine userLine) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UserLine persistentUserLine = em.find(UserLine.class, userLine.getUserLineId());
            Line lineIdOld = persistentUserLine.getLineId();
            Line lineIdNew = userLine.getLineId();
            User userIdOld = persistentUserLine.getUserId();
            User userIdNew = userLine.getUserId();
            if (lineIdNew != null) {
                lineIdNew = em.getReference(lineIdNew.getClass(), lineIdNew.getLineId());
                userLine.setLineId(lineIdNew);
            }
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getUserId());
                userLine.setUserId(userIdNew);
            }
            userLine = em.merge(userLine);
            if (lineIdOld != null && !lineIdOld.equals(lineIdNew)) {
                lineIdOld.getUserLineCollection().remove(userLine);
                lineIdOld = em.merge(lineIdOld);
            }
            if (lineIdNew != null && !lineIdNew.equals(lineIdOld)) {
                lineIdNew.getUserLineCollection().add(userLine);
                lineIdNew = em.merge(lineIdNew);
            }
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getUserLineCollection().remove(userLine);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getUserLineCollection().add(userLine);
                userIdNew = em.merge(userIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = userLine.getUserLineId();
                if (findUserLine(id) == null) {
                    throw new NonexistentEntityException("The userLine with id " + id + " no longer exists.");
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
            UserLine userLine;
            try {
                userLine = em.getReference(UserLine.class, id);
                userLine.getUserLineId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userLine with id " + id + " no longer exists.", enfe);
            }
            Line lineId = userLine.getLineId();
            if (lineId != null) {
                lineId.getUserLineCollection().remove(userLine);
                lineId = em.merge(lineId);
            }
            User userId = userLine.getUserId();
            if (userId != null) {
                userId.getUserLineCollection().remove(userLine);
                userId = em.merge(userId);
            }
            em.remove(userLine);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UserLine> findUserLineEntities() {
        return findUserLineEntities(true, -1, -1);
    }

    public List<UserLine> findUserLineEntities(int maxResults, int firstResult) {
        return findUserLineEntities(false, maxResults, firstResult);
    }

    private List<UserLine> findUserLineEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserLine.class));
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

    public UserLine findUserLine(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserLine.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserLineCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UserLine> rt = cq.from(UserLine.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
