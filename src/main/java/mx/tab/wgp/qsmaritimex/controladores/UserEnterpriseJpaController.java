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
import mx.tab.wgp.qsmaritimex.entidades.usuario.User;
import mx.tab.wgp.qsmaritimex.entidades.Enterprise;
import mx.tab.wgp.qsmaritimex.entidades.usuario.UserEnterprise;

/**
 *
 * @author William
 */
public class UserEnterpriseJpaController implements Serializable {

    public UserEnterpriseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserEnterprise userEnterprise) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User userId = userEnterprise.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getUserId());
                userEnterprise.setUserId(userId);
            }
            Enterprise enterpriseId = userEnterprise.getEnterpriseId();
            if (enterpriseId != null) {
                enterpriseId = em.getReference(enterpriseId.getClass(), enterpriseId.getEnterpriseId());
                userEnterprise.setEnterpriseId(enterpriseId);
            }
            em.persist(userEnterprise);
            if (userId != null) {
                userId.getUserEnterpriseCollection().add(userEnterprise);
                userId = em.merge(userId);
            }
            if (enterpriseId != null) {
                enterpriseId.getUserEnterpriseCollection().add(userEnterprise);
                enterpriseId = em.merge(enterpriseId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UserEnterprise userEnterprise) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UserEnterprise persistentUserEnterprise = em.find(UserEnterprise.class, userEnterprise.getUserEnterpriseId());
            User userIdOld = persistentUserEnterprise.getUserId();
            User userIdNew = userEnterprise.getUserId();
            Enterprise enterpriseIdOld = persistentUserEnterprise.getEnterpriseId();
            Enterprise enterpriseIdNew = userEnterprise.getEnterpriseId();
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getUserId());
                userEnterprise.setUserId(userIdNew);
            }
            if (enterpriseIdNew != null) {
                enterpriseIdNew = em.getReference(enterpriseIdNew.getClass(), enterpriseIdNew.getEnterpriseId());
                userEnterprise.setEnterpriseId(enterpriseIdNew);
            }
            userEnterprise = em.merge(userEnterprise);
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getUserEnterpriseCollection().remove(userEnterprise);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getUserEnterpriseCollection().add(userEnterprise);
                userIdNew = em.merge(userIdNew);
            }
            if (enterpriseIdOld != null && !enterpriseIdOld.equals(enterpriseIdNew)) {
                enterpriseIdOld.getUserEnterpriseCollection().remove(userEnterprise);
                enterpriseIdOld = em.merge(enterpriseIdOld);
            }
            if (enterpriseIdNew != null && !enterpriseIdNew.equals(enterpriseIdOld)) {
                enterpriseIdNew.getUserEnterpriseCollection().add(userEnterprise);
                enterpriseIdNew = em.merge(enterpriseIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = userEnterprise.getUserEnterpriseId();
                if (findUserEnterprise(id) == null) {
                    throw new NonexistentEntityException("The userEnterprise with id " + id + " no longer exists.");
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
            UserEnterprise userEnterprise;
            try {
                userEnterprise = em.getReference(UserEnterprise.class, id);
                userEnterprise.getUserEnterpriseId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userEnterprise with id " + id + " no longer exists.", enfe);
            }
            User userId = userEnterprise.getUserId();
            if (userId != null) {
                userId.getUserEnterpriseCollection().remove(userEnterprise);
                userId = em.merge(userId);
            }
            Enterprise enterpriseId = userEnterprise.getEnterpriseId();
            if (enterpriseId != null) {
                enterpriseId.getUserEnterpriseCollection().remove(userEnterprise);
                enterpriseId = em.merge(enterpriseId);
            }
            em.remove(userEnterprise);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UserEnterprise> findUserEnterpriseEntities() {
        return findUserEnterpriseEntities(true, -1, -1);
    }

    public List<UserEnterprise> findUserEnterpriseEntities(int maxResults, int firstResult) {
        return findUserEnterpriseEntities(false, maxResults, firstResult);
    }

    private List<UserEnterprise> findUserEnterpriseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserEnterprise.class));
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

    public UserEnterprise findUserEnterprise(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserEnterprise.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserEnterpriseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UserEnterprise> rt = cq.from(UserEnterprise.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
