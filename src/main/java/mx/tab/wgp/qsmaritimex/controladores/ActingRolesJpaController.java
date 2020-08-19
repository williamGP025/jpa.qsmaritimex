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
import mx.tab.wgp.qsmaritimex.entidades.ActingRoles;
import mx.tab.wgp.qsmaritimex.entidades.ActingRolesType;
import mx.tab.wgp.qsmaritimex.entidades.ServiceOrder;

/**
 *
 * @author WilliamGP025
 */
public class ActingRolesJpaController implements Serializable {

    public ActingRolesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ActingRoles actingRoles) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ActingRolesType actingRolesTypeId = actingRoles.getActingRolesTypeId();
            if (actingRolesTypeId != null) {
                actingRolesTypeId = em.getReference(actingRolesTypeId.getClass(), actingRolesTypeId.getActingRolesTypeId());
                actingRoles.setActingRolesTypeId(actingRolesTypeId);
            }
            ServiceOrder serviceOrderId = actingRoles.getServiceOrderId();
            if (serviceOrderId != null) {
                serviceOrderId = em.getReference(serviceOrderId.getClass(), serviceOrderId.getServiceOrderId());
                actingRoles.setServiceOrderId(serviceOrderId);
            }
            em.persist(actingRoles);
            if (actingRolesTypeId != null) {
                actingRolesTypeId.getActingRolesCollection().add(actingRoles);
                actingRolesTypeId = em.merge(actingRolesTypeId);
            }
            if (serviceOrderId != null) {
                serviceOrderId.getActingRolesCollection().add(actingRoles);
                serviceOrderId = em.merge(serviceOrderId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ActingRoles actingRoles) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ActingRoles persistentActingRoles = em.find(ActingRoles.class, actingRoles.getActingRoleId());
            ActingRolesType actingRolesTypeIdOld = persistentActingRoles.getActingRolesTypeId();
            ActingRolesType actingRolesTypeIdNew = actingRoles.getActingRolesTypeId();
            ServiceOrder serviceOrderIdOld = persistentActingRoles.getServiceOrderId();
            ServiceOrder serviceOrderIdNew = actingRoles.getServiceOrderId();
            if (actingRolesTypeIdNew != null) {
                actingRolesTypeIdNew = em.getReference(actingRolesTypeIdNew.getClass(), actingRolesTypeIdNew.getActingRolesTypeId());
                actingRoles.setActingRolesTypeId(actingRolesTypeIdNew);
            }
            if (serviceOrderIdNew != null) {
                serviceOrderIdNew = em.getReference(serviceOrderIdNew.getClass(), serviceOrderIdNew.getServiceOrderId());
                actingRoles.setServiceOrderId(serviceOrderIdNew);
            }
            actingRoles = em.merge(actingRoles);
            if (actingRolesTypeIdOld != null && !actingRolesTypeIdOld.equals(actingRolesTypeIdNew)) {
                actingRolesTypeIdOld.getActingRolesCollection().remove(actingRoles);
                actingRolesTypeIdOld = em.merge(actingRolesTypeIdOld);
            }
            if (actingRolesTypeIdNew != null && !actingRolesTypeIdNew.equals(actingRolesTypeIdOld)) {
                actingRolesTypeIdNew.getActingRolesCollection().add(actingRoles);
                actingRolesTypeIdNew = em.merge(actingRolesTypeIdNew);
            }
            if (serviceOrderIdOld != null && !serviceOrderIdOld.equals(serviceOrderIdNew)) {
                serviceOrderIdOld.getActingRolesCollection().remove(actingRoles);
                serviceOrderIdOld = em.merge(serviceOrderIdOld);
            }
            if (serviceOrderIdNew != null && !serviceOrderIdNew.equals(serviceOrderIdOld)) {
                serviceOrderIdNew.getActingRolesCollection().add(actingRoles);
                serviceOrderIdNew = em.merge(serviceOrderIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = actingRoles.getActingRoleId();
                if (findActingRoles(id) == null) {
                    throw new NonexistentEntityException("The actingRoles with id " + id + " no longer exists.");
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
            ActingRoles actingRoles;
            try {
                actingRoles = em.getReference(ActingRoles.class, id);
                actingRoles.getActingRoleId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actingRoles with id " + id + " no longer exists.", enfe);
            }
            ActingRolesType actingRolesTypeId = actingRoles.getActingRolesTypeId();
            if (actingRolesTypeId != null) {
                actingRolesTypeId.getActingRolesCollection().remove(actingRoles);
                actingRolesTypeId = em.merge(actingRolesTypeId);
            }
            ServiceOrder serviceOrderId = actingRoles.getServiceOrderId();
            if (serviceOrderId != null) {
                serviceOrderId.getActingRolesCollection().remove(actingRoles);
                serviceOrderId = em.merge(serviceOrderId);
            }
            em.remove(actingRoles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ActingRoles> findActingRolesEntities() {
        return findActingRolesEntities(true, -1, -1);
    }

    public List<ActingRoles> findActingRolesEntities(int maxResults, int firstResult) {
        return findActingRolesEntities(false, maxResults, firstResult);
    }

    private List<ActingRoles> findActingRolesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ActingRoles.class));
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

    public ActingRoles findActingRoles(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ActingRoles.class, id);
        } finally {
            em.close();
        }
    }

    public int getActingRolesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ActingRoles> rt = cq.from(ActingRoles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
