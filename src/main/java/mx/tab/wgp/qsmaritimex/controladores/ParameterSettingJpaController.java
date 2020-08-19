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
import mx.tab.wgp.qsmaritimex.entidades.ParameterSetting;

/**
 *
 * @author WilliamGP025
 */
public class ParameterSettingJpaController implements Serializable {

    public ParameterSettingJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ParameterSetting parameterSetting) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(parameterSetting);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ParameterSetting parameterSetting) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            parameterSetting = em.merge(parameterSetting);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = parameterSetting.getParameterSettingId();
                if (findParameterSetting(id) == null) {
                    throw new NonexistentEntityException("The parameterSetting with id " + id + " no longer exists.");
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
            ParameterSetting parameterSetting;
            try {
                parameterSetting = em.getReference(ParameterSetting.class, id);
                parameterSetting.getParameterSettingId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The parameterSetting with id " + id + " no longer exists.", enfe);
            }
            em.remove(parameterSetting);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ParameterSetting> findParameterSettingEntities() {
        return findParameterSettingEntities(true, -1, -1);
    }

    public List<ParameterSetting> findParameterSettingEntities(int maxResults, int firstResult) {
        return findParameterSettingEntities(false, maxResults, firstResult);
    }

    private List<ParameterSetting> findParameterSettingEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ParameterSetting.class));
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

    public ParameterSetting findParameterSetting(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ParameterSetting.class, id);
        } finally {
            em.close();
        }
    }

    public int getParameterSettingCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ParameterSetting> rt = cq.from(ParameterSetting.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
