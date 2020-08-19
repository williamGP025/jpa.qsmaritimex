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
import mx.tab.wgp.qsmaritimex.entidades.ActingRolesType;
import mx.tab.wgp.qsmaritimex.entidades.Service;
import mx.tab.wgp.qsmaritimex.entidades.ServiceOrderServicesTemplate;

/**
 *
 * @author WilliamGP025
 */
public class ServiceOrderServicesTemplateJpaController implements Serializable {

    public ServiceOrderServicesTemplateJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServiceOrderServicesTemplate serviceOrderServicesTemplate) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ActingRolesType actingRolesTypeId = serviceOrderServicesTemplate.getActingRolesTypeId();
            if (actingRolesTypeId != null) {
                actingRolesTypeId = em.getReference(actingRolesTypeId.getClass(), actingRolesTypeId.getActingRolesTypeId());
                serviceOrderServicesTemplate.setActingRolesTypeId(actingRolesTypeId);
            }
            Service serviceId = serviceOrderServicesTemplate.getServiceId();
            if (serviceId != null) {
                serviceId = em.getReference(serviceId.getClass(), serviceId.getServiceId());
                serviceOrderServicesTemplate.setServiceId(serviceId);
            }
            em.persist(serviceOrderServicesTemplate);
            if (actingRolesTypeId != null) {
                actingRolesTypeId.getServiceOrderServicesTemplateCollection().add(serviceOrderServicesTemplate);
                actingRolesTypeId = em.merge(actingRolesTypeId);
            }
            if (serviceId != null) {
                serviceId.getServiceOrderServicesTemplateCollection().add(serviceOrderServicesTemplate);
                serviceId = em.merge(serviceId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServiceOrderServicesTemplate serviceOrderServicesTemplate) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceOrderServicesTemplate persistentServiceOrderServicesTemplate = em.find(ServiceOrderServicesTemplate.class, serviceOrderServicesTemplate.getServiceOrderServicesTemplateId());
            ActingRolesType actingRolesTypeIdOld = persistentServiceOrderServicesTemplate.getActingRolesTypeId();
            ActingRolesType actingRolesTypeIdNew = serviceOrderServicesTemplate.getActingRolesTypeId();
            Service serviceIdOld = persistentServiceOrderServicesTemplate.getServiceId();
            Service serviceIdNew = serviceOrderServicesTemplate.getServiceId();
            if (actingRolesTypeIdNew != null) {
                actingRolesTypeIdNew = em.getReference(actingRolesTypeIdNew.getClass(), actingRolesTypeIdNew.getActingRolesTypeId());
                serviceOrderServicesTemplate.setActingRolesTypeId(actingRolesTypeIdNew);
            }
            if (serviceIdNew != null) {
                serviceIdNew = em.getReference(serviceIdNew.getClass(), serviceIdNew.getServiceId());
                serviceOrderServicesTemplate.setServiceId(serviceIdNew);
            }
            serviceOrderServicesTemplate = em.merge(serviceOrderServicesTemplate);
            if (actingRolesTypeIdOld != null && !actingRolesTypeIdOld.equals(actingRolesTypeIdNew)) {
                actingRolesTypeIdOld.getServiceOrderServicesTemplateCollection().remove(serviceOrderServicesTemplate);
                actingRolesTypeIdOld = em.merge(actingRolesTypeIdOld);
            }
            if (actingRolesTypeIdNew != null && !actingRolesTypeIdNew.equals(actingRolesTypeIdOld)) {
                actingRolesTypeIdNew.getServiceOrderServicesTemplateCollection().add(serviceOrderServicesTemplate);
                actingRolesTypeIdNew = em.merge(actingRolesTypeIdNew);
            }
            if (serviceIdOld != null && !serviceIdOld.equals(serviceIdNew)) {
                serviceIdOld.getServiceOrderServicesTemplateCollection().remove(serviceOrderServicesTemplate);
                serviceIdOld = em.merge(serviceIdOld);
            }
            if (serviceIdNew != null && !serviceIdNew.equals(serviceIdOld)) {
                serviceIdNew.getServiceOrderServicesTemplateCollection().add(serviceOrderServicesTemplate);
                serviceIdNew = em.merge(serviceIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = serviceOrderServicesTemplate.getServiceOrderServicesTemplateId();
                if (findServiceOrderServicesTemplate(id) == null) {
                    throw new NonexistentEntityException("The serviceOrderServicesTemplate with id " + id + " no longer exists.");
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
            ServiceOrderServicesTemplate serviceOrderServicesTemplate;
            try {
                serviceOrderServicesTemplate = em.getReference(ServiceOrderServicesTemplate.class, id);
                serviceOrderServicesTemplate.getServiceOrderServicesTemplateId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The serviceOrderServicesTemplate with id " + id + " no longer exists.", enfe);
            }
            ActingRolesType actingRolesTypeId = serviceOrderServicesTemplate.getActingRolesTypeId();
            if (actingRolesTypeId != null) {
                actingRolesTypeId.getServiceOrderServicesTemplateCollection().remove(serviceOrderServicesTemplate);
                actingRolesTypeId = em.merge(actingRolesTypeId);
            }
            Service serviceId = serviceOrderServicesTemplate.getServiceId();
            if (serviceId != null) {
                serviceId.getServiceOrderServicesTemplateCollection().remove(serviceOrderServicesTemplate);
                serviceId = em.merge(serviceId);
            }
            em.remove(serviceOrderServicesTemplate);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServiceOrderServicesTemplate> findServiceOrderServicesTemplateEntities() {
        return findServiceOrderServicesTemplateEntities(true, -1, -1);
    }

    public List<ServiceOrderServicesTemplate> findServiceOrderServicesTemplateEntities(int maxResults, int firstResult) {
        return findServiceOrderServicesTemplateEntities(false, maxResults, firstResult);
    }

    private List<ServiceOrderServicesTemplate> findServiceOrderServicesTemplateEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServiceOrderServicesTemplate.class));
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

    public ServiceOrderServicesTemplate findServiceOrderServicesTemplate(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServiceOrderServicesTemplate.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiceOrderServicesTemplateCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServiceOrderServicesTemplate> rt = cq.from(ServiceOrderServicesTemplate.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
