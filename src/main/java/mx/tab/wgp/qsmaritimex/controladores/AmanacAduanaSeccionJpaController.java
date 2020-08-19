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
import mx.tab.wgp.qsmaritimex.entidades.AmanacAduanaSeccion;

/**
 *
 * @author WilliamGP025
 */
public class AmanacAduanaSeccionJpaController implements Serializable {

    public AmanacAduanaSeccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AmanacAduanaSeccion amanacAduanaSeccion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(amanacAduanaSeccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AmanacAduanaSeccion amanacAduanaSeccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            amanacAduanaSeccion = em.merge(amanacAduanaSeccion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = amanacAduanaSeccion.getAduanaSeccionCode();
                if (findAmanacAduanaSeccion(id) == null) {
                    throw new NonexistentEntityException("The amanacAduanaSeccion with id " + id + " no longer exists.");
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
            AmanacAduanaSeccion amanacAduanaSeccion;
            try {
                amanacAduanaSeccion = em.getReference(AmanacAduanaSeccion.class, id);
                amanacAduanaSeccion.getAduanaSeccionCode();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The amanacAduanaSeccion with id " + id + " no longer exists.", enfe);
            }
            em.remove(amanacAduanaSeccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AmanacAduanaSeccion> findAmanacAduanaSeccionEntities() {
        return findAmanacAduanaSeccionEntities(true, -1, -1);
    }

    public List<AmanacAduanaSeccion> findAmanacAduanaSeccionEntities(int maxResults, int firstResult) {
        return findAmanacAduanaSeccionEntities(false, maxResults, firstResult);
    }

    private List<AmanacAduanaSeccion> findAmanacAduanaSeccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AmanacAduanaSeccion.class));
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

    public AmanacAduanaSeccion findAmanacAduanaSeccion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AmanacAduanaSeccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getAmanacAduanaSeccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AmanacAduanaSeccion> rt = cq.from(AmanacAduanaSeccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
