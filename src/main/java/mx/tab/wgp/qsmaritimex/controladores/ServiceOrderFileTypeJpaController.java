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
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrderFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.IllegalOrphanException;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrderFileType;

/**
 *
 * @author William
 */
public class ServiceOrderFileTypeJpaController implements Serializable {

    public ServiceOrderFileTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServiceOrderFileType serviceOrderFileType) {
        if (serviceOrderFileType.getServiceOrderFileCollection() == null) {
            serviceOrderFileType.setServiceOrderFileCollection(new ArrayList<ServiceOrderFile>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ServiceOrderFile> attachedServiceOrderFileCollection = new ArrayList<ServiceOrderFile>();
            for (ServiceOrderFile serviceOrderFileCollectionServiceOrderFileToAttach : serviceOrderFileType.getServiceOrderFileCollection()) {
                serviceOrderFileCollectionServiceOrderFileToAttach = em.getReference(serviceOrderFileCollectionServiceOrderFileToAttach.getClass(), serviceOrderFileCollectionServiceOrderFileToAttach.getServiceOrderFileId());
                attachedServiceOrderFileCollection.add(serviceOrderFileCollectionServiceOrderFileToAttach);
            }
            serviceOrderFileType.setServiceOrderFileCollection(attachedServiceOrderFileCollection);
            em.persist(serviceOrderFileType);
            for (ServiceOrderFile serviceOrderFileCollectionServiceOrderFile : serviceOrderFileType.getServiceOrderFileCollection()) {
                ServiceOrderFileType oldServiceOrderFileTypeIdOfServiceOrderFileCollectionServiceOrderFile = serviceOrderFileCollectionServiceOrderFile.getServiceOrderFileTypeId();
                serviceOrderFileCollectionServiceOrderFile.setServiceOrderFileTypeId(serviceOrderFileType);
                serviceOrderFileCollectionServiceOrderFile = em.merge(serviceOrderFileCollectionServiceOrderFile);
                if (oldServiceOrderFileTypeIdOfServiceOrderFileCollectionServiceOrderFile != null) {
                    oldServiceOrderFileTypeIdOfServiceOrderFileCollectionServiceOrderFile.getServiceOrderFileCollection().remove(serviceOrderFileCollectionServiceOrderFile);
                    oldServiceOrderFileTypeIdOfServiceOrderFileCollectionServiceOrderFile = em.merge(oldServiceOrderFileTypeIdOfServiceOrderFileCollectionServiceOrderFile);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServiceOrderFileType serviceOrderFileType) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceOrderFileType persistentServiceOrderFileType = em.find(ServiceOrderFileType.class, serviceOrderFileType.getServiceOrderFileTypeId());
            Collection<ServiceOrderFile> serviceOrderFileCollectionOld = persistentServiceOrderFileType.getServiceOrderFileCollection();
            Collection<ServiceOrderFile> serviceOrderFileCollectionNew = serviceOrderFileType.getServiceOrderFileCollection();
            List<String> illegalOrphanMessages = null;
            for (ServiceOrderFile serviceOrderFileCollectionOldServiceOrderFile : serviceOrderFileCollectionOld) {
                if (!serviceOrderFileCollectionNew.contains(serviceOrderFileCollectionOldServiceOrderFile)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ServiceOrderFile " + serviceOrderFileCollectionOldServiceOrderFile + " since its serviceOrderFileTypeId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<ServiceOrderFile> attachedServiceOrderFileCollectionNew = new ArrayList<ServiceOrderFile>();
            for (ServiceOrderFile serviceOrderFileCollectionNewServiceOrderFileToAttach : serviceOrderFileCollectionNew) {
                serviceOrderFileCollectionNewServiceOrderFileToAttach = em.getReference(serviceOrderFileCollectionNewServiceOrderFileToAttach.getClass(), serviceOrderFileCollectionNewServiceOrderFileToAttach.getServiceOrderFileId());
                attachedServiceOrderFileCollectionNew.add(serviceOrderFileCollectionNewServiceOrderFileToAttach);
            }
            serviceOrderFileCollectionNew = attachedServiceOrderFileCollectionNew;
            serviceOrderFileType.setServiceOrderFileCollection(serviceOrderFileCollectionNew);
            serviceOrderFileType = em.merge(serviceOrderFileType);
            for (ServiceOrderFile serviceOrderFileCollectionNewServiceOrderFile : serviceOrderFileCollectionNew) {
                if (!serviceOrderFileCollectionOld.contains(serviceOrderFileCollectionNewServiceOrderFile)) {
                    ServiceOrderFileType oldServiceOrderFileTypeIdOfServiceOrderFileCollectionNewServiceOrderFile = serviceOrderFileCollectionNewServiceOrderFile.getServiceOrderFileTypeId();
                    serviceOrderFileCollectionNewServiceOrderFile.setServiceOrderFileTypeId(serviceOrderFileType);
                    serviceOrderFileCollectionNewServiceOrderFile = em.merge(serviceOrderFileCollectionNewServiceOrderFile);
                    if (oldServiceOrderFileTypeIdOfServiceOrderFileCollectionNewServiceOrderFile != null && !oldServiceOrderFileTypeIdOfServiceOrderFileCollectionNewServiceOrderFile.equals(serviceOrderFileType)) {
                        oldServiceOrderFileTypeIdOfServiceOrderFileCollectionNewServiceOrderFile.getServiceOrderFileCollection().remove(serviceOrderFileCollectionNewServiceOrderFile);
                        oldServiceOrderFileTypeIdOfServiceOrderFileCollectionNewServiceOrderFile = em.merge(oldServiceOrderFileTypeIdOfServiceOrderFileCollectionNewServiceOrderFile);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = serviceOrderFileType.getServiceOrderFileTypeId();
                if (findServiceOrderFileType(id) == null) {
                    throw new NonexistentEntityException("The serviceOrderFileType with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Short id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceOrderFileType serviceOrderFileType;
            try {
                serviceOrderFileType = em.getReference(ServiceOrderFileType.class, id);
                serviceOrderFileType.getServiceOrderFileTypeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The serviceOrderFileType with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ServiceOrderFile> serviceOrderFileCollectionOrphanCheck = serviceOrderFileType.getServiceOrderFileCollection();
            for (ServiceOrderFile serviceOrderFileCollectionOrphanCheckServiceOrderFile : serviceOrderFileCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ServiceOrderFileType (" + serviceOrderFileType + ") cannot be destroyed since the ServiceOrderFile " + serviceOrderFileCollectionOrphanCheckServiceOrderFile + " in its serviceOrderFileCollection field has a non-nullable serviceOrderFileTypeId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(serviceOrderFileType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServiceOrderFileType> findServiceOrderFileTypeEntities() {
        return findServiceOrderFileTypeEntities(true, -1, -1);
    }

    public List<ServiceOrderFileType> findServiceOrderFileTypeEntities(int maxResults, int firstResult) {
        return findServiceOrderFileTypeEntities(false, maxResults, firstResult);
    }

    private List<ServiceOrderFileType> findServiceOrderFileTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServiceOrderFileType.class));
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

    public ServiceOrderFileType findServiceOrderFileType(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServiceOrderFileType.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiceOrderFileTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServiceOrderFileType> rt = cq.from(ServiceOrderFileType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
