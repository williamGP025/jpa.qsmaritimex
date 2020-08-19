/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.controladores;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mx.tab.wgp.qsmaritimex.entidades.ActingRoles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.IllegalOrphanException;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.ActingRolesType;
import mx.tab.wgp.qsmaritimex.entidades.ServiceOrderServicesTemplate;

/**
 *
 * @author WilliamGP025
 */
public class ActingRolesTypeJpaController implements Serializable {

    public ActingRolesTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ActingRolesType actingRolesType) {
        if (actingRolesType.getActingRolesCollection() == null) {
            actingRolesType.setActingRolesCollection(new ArrayList<ActingRoles>());
        }
        if (actingRolesType.getServiceOrderServicesTemplateCollection() == null) {
            actingRolesType.setServiceOrderServicesTemplateCollection(new ArrayList<ServiceOrderServicesTemplate>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ActingRoles> attachedActingRolesCollection = new ArrayList<ActingRoles>();
            for (ActingRoles actingRolesCollectionActingRolesToAttach : actingRolesType.getActingRolesCollection()) {
                actingRolesCollectionActingRolesToAttach = em.getReference(actingRolesCollectionActingRolesToAttach.getClass(), actingRolesCollectionActingRolesToAttach.getActingRoleId());
                attachedActingRolesCollection.add(actingRolesCollectionActingRolesToAttach);
            }
            actingRolesType.setActingRolesCollection(attachedActingRolesCollection);
            Collection<ServiceOrderServicesTemplate> attachedServiceOrderServicesTemplateCollection = new ArrayList<ServiceOrderServicesTemplate>();
            for (ServiceOrderServicesTemplate serviceOrderServicesTemplateCollectionServiceOrderServicesTemplateToAttach : actingRolesType.getServiceOrderServicesTemplateCollection()) {
                serviceOrderServicesTemplateCollectionServiceOrderServicesTemplateToAttach = em.getReference(serviceOrderServicesTemplateCollectionServiceOrderServicesTemplateToAttach.getClass(), serviceOrderServicesTemplateCollectionServiceOrderServicesTemplateToAttach.getServiceOrderServicesTemplateId());
                attachedServiceOrderServicesTemplateCollection.add(serviceOrderServicesTemplateCollectionServiceOrderServicesTemplateToAttach);
            }
            actingRolesType.setServiceOrderServicesTemplateCollection(attachedServiceOrderServicesTemplateCollection);
            em.persist(actingRolesType);
            for (ActingRoles actingRolesCollectionActingRoles : actingRolesType.getActingRolesCollection()) {
                ActingRolesType oldActingRolesTypeIdOfActingRolesCollectionActingRoles = actingRolesCollectionActingRoles.getActingRolesTypeId();
                actingRolesCollectionActingRoles.setActingRolesTypeId(actingRolesType);
                actingRolesCollectionActingRoles = em.merge(actingRolesCollectionActingRoles);
                if (oldActingRolesTypeIdOfActingRolesCollectionActingRoles != null) {
                    oldActingRolesTypeIdOfActingRolesCollectionActingRoles.getActingRolesCollection().remove(actingRolesCollectionActingRoles);
                    oldActingRolesTypeIdOfActingRolesCollectionActingRoles = em.merge(oldActingRolesTypeIdOfActingRolesCollectionActingRoles);
                }
            }
            for (ServiceOrderServicesTemplate serviceOrderServicesTemplateCollectionServiceOrderServicesTemplate : actingRolesType.getServiceOrderServicesTemplateCollection()) {
                ActingRolesType oldActingRolesTypeIdOfServiceOrderServicesTemplateCollectionServiceOrderServicesTemplate = serviceOrderServicesTemplateCollectionServiceOrderServicesTemplate.getActingRolesTypeId();
                serviceOrderServicesTemplateCollectionServiceOrderServicesTemplate.setActingRolesTypeId(actingRolesType);
                serviceOrderServicesTemplateCollectionServiceOrderServicesTemplate = em.merge(serviceOrderServicesTemplateCollectionServiceOrderServicesTemplate);
                if (oldActingRolesTypeIdOfServiceOrderServicesTemplateCollectionServiceOrderServicesTemplate != null) {
                    oldActingRolesTypeIdOfServiceOrderServicesTemplateCollectionServiceOrderServicesTemplate.getServiceOrderServicesTemplateCollection().remove(serviceOrderServicesTemplateCollectionServiceOrderServicesTemplate);
                    oldActingRolesTypeIdOfServiceOrderServicesTemplateCollectionServiceOrderServicesTemplate = em.merge(oldActingRolesTypeIdOfServiceOrderServicesTemplateCollectionServiceOrderServicesTemplate);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ActingRolesType actingRolesType) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ActingRolesType persistentActingRolesType = em.find(ActingRolesType.class, actingRolesType.getActingRolesTypeId());
            Collection<ActingRoles> actingRolesCollectionOld = persistentActingRolesType.getActingRolesCollection();
            Collection<ActingRoles> actingRolesCollectionNew = actingRolesType.getActingRolesCollection();
            Collection<ServiceOrderServicesTemplate> serviceOrderServicesTemplateCollectionOld = persistentActingRolesType.getServiceOrderServicesTemplateCollection();
            Collection<ServiceOrderServicesTemplate> serviceOrderServicesTemplateCollectionNew = actingRolesType.getServiceOrderServicesTemplateCollection();
            List<String> illegalOrphanMessages = null;
            for (ActingRoles actingRolesCollectionOldActingRoles : actingRolesCollectionOld) {
                if (!actingRolesCollectionNew.contains(actingRolesCollectionOldActingRoles)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ActingRoles " + actingRolesCollectionOldActingRoles + " since its actingRolesTypeId field is not nullable.");
                }
            }
            for (ServiceOrderServicesTemplate serviceOrderServicesTemplateCollectionOldServiceOrderServicesTemplate : serviceOrderServicesTemplateCollectionOld) {
                if (!serviceOrderServicesTemplateCollectionNew.contains(serviceOrderServicesTemplateCollectionOldServiceOrderServicesTemplate)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ServiceOrderServicesTemplate " + serviceOrderServicesTemplateCollectionOldServiceOrderServicesTemplate + " since its actingRolesTypeId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<ActingRoles> attachedActingRolesCollectionNew = new ArrayList<ActingRoles>();
            for (ActingRoles actingRolesCollectionNewActingRolesToAttach : actingRolesCollectionNew) {
                actingRolesCollectionNewActingRolesToAttach = em.getReference(actingRolesCollectionNewActingRolesToAttach.getClass(), actingRolesCollectionNewActingRolesToAttach.getActingRoleId());
                attachedActingRolesCollectionNew.add(actingRolesCollectionNewActingRolesToAttach);
            }
            actingRolesCollectionNew = attachedActingRolesCollectionNew;
            actingRolesType.setActingRolesCollection(actingRolesCollectionNew);
            Collection<ServiceOrderServicesTemplate> attachedServiceOrderServicesTemplateCollectionNew = new ArrayList<ServiceOrderServicesTemplate>();
            for (ServiceOrderServicesTemplate serviceOrderServicesTemplateCollectionNewServiceOrderServicesTemplateToAttach : serviceOrderServicesTemplateCollectionNew) {
                serviceOrderServicesTemplateCollectionNewServiceOrderServicesTemplateToAttach = em.getReference(serviceOrderServicesTemplateCollectionNewServiceOrderServicesTemplateToAttach.getClass(), serviceOrderServicesTemplateCollectionNewServiceOrderServicesTemplateToAttach.getServiceOrderServicesTemplateId());
                attachedServiceOrderServicesTemplateCollectionNew.add(serviceOrderServicesTemplateCollectionNewServiceOrderServicesTemplateToAttach);
            }
            serviceOrderServicesTemplateCollectionNew = attachedServiceOrderServicesTemplateCollectionNew;
            actingRolesType.setServiceOrderServicesTemplateCollection(serviceOrderServicesTemplateCollectionNew);
            actingRolesType = em.merge(actingRolesType);
            for (ActingRoles actingRolesCollectionNewActingRoles : actingRolesCollectionNew) {
                if (!actingRolesCollectionOld.contains(actingRolesCollectionNewActingRoles)) {
                    ActingRolesType oldActingRolesTypeIdOfActingRolesCollectionNewActingRoles = actingRolesCollectionNewActingRoles.getActingRolesTypeId();
                    actingRolesCollectionNewActingRoles.setActingRolesTypeId(actingRolesType);
                    actingRolesCollectionNewActingRoles = em.merge(actingRolesCollectionNewActingRoles);
                    if (oldActingRolesTypeIdOfActingRolesCollectionNewActingRoles != null && !oldActingRolesTypeIdOfActingRolesCollectionNewActingRoles.equals(actingRolesType)) {
                        oldActingRolesTypeIdOfActingRolesCollectionNewActingRoles.getActingRolesCollection().remove(actingRolesCollectionNewActingRoles);
                        oldActingRolesTypeIdOfActingRolesCollectionNewActingRoles = em.merge(oldActingRolesTypeIdOfActingRolesCollectionNewActingRoles);
                    }
                }
            }
            for (ServiceOrderServicesTemplate serviceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate : serviceOrderServicesTemplateCollectionNew) {
                if (!serviceOrderServicesTemplateCollectionOld.contains(serviceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate)) {
                    ActingRolesType oldActingRolesTypeIdOfServiceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate = serviceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate.getActingRolesTypeId();
                    serviceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate.setActingRolesTypeId(actingRolesType);
                    serviceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate = em.merge(serviceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate);
                    if (oldActingRolesTypeIdOfServiceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate != null && !oldActingRolesTypeIdOfServiceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate.equals(actingRolesType)) {
                        oldActingRolesTypeIdOfServiceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate.getServiceOrderServicesTemplateCollection().remove(serviceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate);
                        oldActingRolesTypeIdOfServiceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate = em.merge(oldActingRolesTypeIdOfServiceOrderServicesTemplateCollectionNewServiceOrderServicesTemplate);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = actingRolesType.getActingRolesTypeId();
                if (findActingRolesType(id) == null) {
                    throw new NonexistentEntityException("The actingRolesType with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ActingRolesType actingRolesType;
            try {
                actingRolesType = em.getReference(ActingRolesType.class, id);
                actingRolesType.getActingRolesTypeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actingRolesType with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ActingRoles> actingRolesCollectionOrphanCheck = actingRolesType.getActingRolesCollection();
            for (ActingRoles actingRolesCollectionOrphanCheckActingRoles : actingRolesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ActingRolesType (" + actingRolesType + ") cannot be destroyed since the ActingRoles " + actingRolesCollectionOrphanCheckActingRoles + " in its actingRolesCollection field has a non-nullable actingRolesTypeId field.");
            }
            Collection<ServiceOrderServicesTemplate> serviceOrderServicesTemplateCollectionOrphanCheck = actingRolesType.getServiceOrderServicesTemplateCollection();
            for (ServiceOrderServicesTemplate serviceOrderServicesTemplateCollectionOrphanCheckServiceOrderServicesTemplate : serviceOrderServicesTemplateCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ActingRolesType (" + actingRolesType + ") cannot be destroyed since the ServiceOrderServicesTemplate " + serviceOrderServicesTemplateCollectionOrphanCheckServiceOrderServicesTemplate + " in its serviceOrderServicesTemplateCollection field has a non-nullable actingRolesTypeId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(actingRolesType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ActingRolesType> findActingRolesTypeEntities() {
        return findActingRolesTypeEntities(true, -1, -1);
    }

    public List<ActingRolesType> findActingRolesTypeEntities(int maxResults, int firstResult) {
        return findActingRolesTypeEntities(false, maxResults, firstResult);
    }

    private List<ActingRolesType> findActingRolesTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ActingRolesType.class));
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

    public ActingRolesType findActingRolesType(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ActingRolesType.class, id);
        } finally {
            em.close();
        }
    }

    public int getActingRolesTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ActingRolesType> rt = cq.from(ActingRolesType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
