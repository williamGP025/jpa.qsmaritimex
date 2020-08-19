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
import mx.tab.wgp.qsmaritimex.entidades.ServiceOrder;
import mx.tab.wgp.qsmaritimex.entidades.ServiceOrderJoin;
import mx.tab.wgp.qsmaritimex.entidades.ServiceOrderJoinType;

/**
 *
 * @author WilliamGP025
 */
public class ServiceOrderJoinJpaController implements Serializable {

    public ServiceOrderJoinJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServiceOrderJoin serviceOrderJoin) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceOrder serviceOrderIdFather = serviceOrderJoin.getServiceOrderIdFather();
            if (serviceOrderIdFather != null) {
                serviceOrderIdFather = em.getReference(serviceOrderIdFather.getClass(), serviceOrderIdFather.getServiceOrderId());
                serviceOrderJoin.setServiceOrderIdFather(serviceOrderIdFather);
            }
            ServiceOrder serviceOrderIdSon = serviceOrderJoin.getServiceOrderIdSon();
            if (serviceOrderIdSon != null) {
                serviceOrderIdSon = em.getReference(serviceOrderIdSon.getClass(), serviceOrderIdSon.getServiceOrderId());
                serviceOrderJoin.setServiceOrderIdSon(serviceOrderIdSon);
            }
            ServiceOrderJoinType serviceOrderJoinTypeId = serviceOrderJoin.getServiceOrderJoinTypeId();
            if (serviceOrderJoinTypeId != null) {
                serviceOrderJoinTypeId = em.getReference(serviceOrderJoinTypeId.getClass(), serviceOrderJoinTypeId.getServiceOrderJoinTypeId());
                serviceOrderJoin.setServiceOrderJoinTypeId(serviceOrderJoinTypeId);
            }
            em.persist(serviceOrderJoin);
            if (serviceOrderIdFather != null) {
                serviceOrderIdFather.getServiceOrderJoinCollection().add(serviceOrderJoin);
                serviceOrderIdFather = em.merge(serviceOrderIdFather);
            }
            if (serviceOrderIdSon != null) {
                serviceOrderIdSon.getServiceOrderJoinCollection().add(serviceOrderJoin);
                serviceOrderIdSon = em.merge(serviceOrderIdSon);
            }
            if (serviceOrderJoinTypeId != null) {
                serviceOrderJoinTypeId.getServiceOrderJoinCollection().add(serviceOrderJoin);
                serviceOrderJoinTypeId = em.merge(serviceOrderJoinTypeId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServiceOrderJoin serviceOrderJoin) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceOrderJoin persistentServiceOrderJoin = em.find(ServiceOrderJoin.class, serviceOrderJoin.getServiceOrderJoinId());
            ServiceOrder serviceOrderIdFatherOld = persistentServiceOrderJoin.getServiceOrderIdFather();
            ServiceOrder serviceOrderIdFatherNew = serviceOrderJoin.getServiceOrderIdFather();
            ServiceOrder serviceOrderIdSonOld = persistentServiceOrderJoin.getServiceOrderIdSon();
            ServiceOrder serviceOrderIdSonNew = serviceOrderJoin.getServiceOrderIdSon();
            ServiceOrderJoinType serviceOrderJoinTypeIdOld = persistentServiceOrderJoin.getServiceOrderJoinTypeId();
            ServiceOrderJoinType serviceOrderJoinTypeIdNew = serviceOrderJoin.getServiceOrderJoinTypeId();
            if (serviceOrderIdFatherNew != null) {
                serviceOrderIdFatherNew = em.getReference(serviceOrderIdFatherNew.getClass(), serviceOrderIdFatherNew.getServiceOrderId());
                serviceOrderJoin.setServiceOrderIdFather(serviceOrderIdFatherNew);
            }
            if (serviceOrderIdSonNew != null) {
                serviceOrderIdSonNew = em.getReference(serviceOrderIdSonNew.getClass(), serviceOrderIdSonNew.getServiceOrderId());
                serviceOrderJoin.setServiceOrderIdSon(serviceOrderIdSonNew);
            }
            if (serviceOrderJoinTypeIdNew != null) {
                serviceOrderJoinTypeIdNew = em.getReference(serviceOrderJoinTypeIdNew.getClass(), serviceOrderJoinTypeIdNew.getServiceOrderJoinTypeId());
                serviceOrderJoin.setServiceOrderJoinTypeId(serviceOrderJoinTypeIdNew);
            }
            serviceOrderJoin = em.merge(serviceOrderJoin);
            if (serviceOrderIdFatherOld != null && !serviceOrderIdFatherOld.equals(serviceOrderIdFatherNew)) {
                serviceOrderIdFatherOld.getServiceOrderJoinCollection().remove(serviceOrderJoin);
                serviceOrderIdFatherOld = em.merge(serviceOrderIdFatherOld);
            }
            if (serviceOrderIdFatherNew != null && !serviceOrderIdFatherNew.equals(serviceOrderIdFatherOld)) {
                serviceOrderIdFatherNew.getServiceOrderJoinCollection().add(serviceOrderJoin);
                serviceOrderIdFatherNew = em.merge(serviceOrderIdFatherNew);
            }
            if (serviceOrderIdSonOld != null && !serviceOrderIdSonOld.equals(serviceOrderIdSonNew)) {
                serviceOrderIdSonOld.getServiceOrderJoinCollection().remove(serviceOrderJoin);
                serviceOrderIdSonOld = em.merge(serviceOrderIdSonOld);
            }
            if (serviceOrderIdSonNew != null && !serviceOrderIdSonNew.equals(serviceOrderIdSonOld)) {
                serviceOrderIdSonNew.getServiceOrderJoinCollection().add(serviceOrderJoin);
                serviceOrderIdSonNew = em.merge(serviceOrderIdSonNew);
            }
            if (serviceOrderJoinTypeIdOld != null && !serviceOrderJoinTypeIdOld.equals(serviceOrderJoinTypeIdNew)) {
                serviceOrderJoinTypeIdOld.getServiceOrderJoinCollection().remove(serviceOrderJoin);
                serviceOrderJoinTypeIdOld = em.merge(serviceOrderJoinTypeIdOld);
            }
            if (serviceOrderJoinTypeIdNew != null && !serviceOrderJoinTypeIdNew.equals(serviceOrderJoinTypeIdOld)) {
                serviceOrderJoinTypeIdNew.getServiceOrderJoinCollection().add(serviceOrderJoin);
                serviceOrderJoinTypeIdNew = em.merge(serviceOrderJoinTypeIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = serviceOrderJoin.getServiceOrderJoinId();
                if (findServiceOrderJoin(id) == null) {
                    throw new NonexistentEntityException("The serviceOrderJoin with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceOrderJoin serviceOrderJoin;
            try {
                serviceOrderJoin = em.getReference(ServiceOrderJoin.class, id);
                serviceOrderJoin.getServiceOrderJoinId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The serviceOrderJoin with id " + id + " no longer exists.", enfe);
            }
            ServiceOrder serviceOrderIdFather = serviceOrderJoin.getServiceOrderIdFather();
            if (serviceOrderIdFather != null) {
                serviceOrderIdFather.getServiceOrderJoinCollection().remove(serviceOrderJoin);
                serviceOrderIdFather = em.merge(serviceOrderIdFather);
            }
            ServiceOrder serviceOrderIdSon = serviceOrderJoin.getServiceOrderIdSon();
            if (serviceOrderIdSon != null) {
                serviceOrderIdSon.getServiceOrderJoinCollection().remove(serviceOrderJoin);
                serviceOrderIdSon = em.merge(serviceOrderIdSon);
            }
            ServiceOrderJoinType serviceOrderJoinTypeId = serviceOrderJoin.getServiceOrderJoinTypeId();
            if (serviceOrderJoinTypeId != null) {
                serviceOrderJoinTypeId.getServiceOrderJoinCollection().remove(serviceOrderJoin);
                serviceOrderJoinTypeId = em.merge(serviceOrderJoinTypeId);
            }
            em.remove(serviceOrderJoin);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServiceOrderJoin> findServiceOrderJoinEntities() {
        return findServiceOrderJoinEntities(true, -1, -1);
    }

    public List<ServiceOrderJoin> findServiceOrderJoinEntities(int maxResults, int firstResult) {
        return findServiceOrderJoinEntities(false, maxResults, firstResult);
    }

    private List<ServiceOrderJoin> findServiceOrderJoinEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServiceOrderJoin.class));
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

    public ServiceOrderJoin findServiceOrderJoin(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServiceOrderJoin.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiceOrderJoinCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServiceOrderJoin> rt = cq.from(ServiceOrderJoin.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
