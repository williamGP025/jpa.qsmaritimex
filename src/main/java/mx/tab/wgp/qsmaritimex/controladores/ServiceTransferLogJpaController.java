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
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrderService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.PreexistingEntityException;
import mx.tab.wgp.qsmaritimex.entidades.ServiceTransferLog;

/**
 *
 * @author William
 */
public class ServiceTransferLogJpaController implements Serializable {

    public ServiceTransferLogJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServiceTransferLog serviceTransferLog) throws PreexistingEntityException, Exception {
        if (serviceTransferLog.getServiceOrderServiceCollection() == null) {
            serviceTransferLog.setServiceOrderServiceCollection(new ArrayList<ServiceOrderService>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ServiceOrderService> attachedServiceOrderServiceCollection = new ArrayList<ServiceOrderService>();
            for (ServiceOrderService serviceOrderServiceCollectionServiceOrderServiceToAttach : serviceTransferLog.getServiceOrderServiceCollection()) {
                serviceOrderServiceCollectionServiceOrderServiceToAttach = em.getReference(serviceOrderServiceCollectionServiceOrderServiceToAttach.getClass(), serviceOrderServiceCollectionServiceOrderServiceToAttach.getServiceOrderServiceId());
                attachedServiceOrderServiceCollection.add(serviceOrderServiceCollectionServiceOrderServiceToAttach);
            }
            serviceTransferLog.setServiceOrderServiceCollection(attachedServiceOrderServiceCollection);
            em.persist(serviceTransferLog);
            for (ServiceOrderService serviceOrderServiceCollectionServiceOrderService : serviceTransferLog.getServiceOrderServiceCollection()) {
                ServiceTransferLog oldServiceTransferLogIdOfServiceOrderServiceCollectionServiceOrderService = serviceOrderServiceCollectionServiceOrderService.getServiceTransferLogId();
                serviceOrderServiceCollectionServiceOrderService.setServiceTransferLogId(serviceTransferLog);
                serviceOrderServiceCollectionServiceOrderService = em.merge(serviceOrderServiceCollectionServiceOrderService);
                if (oldServiceTransferLogIdOfServiceOrderServiceCollectionServiceOrderService != null) {
                    oldServiceTransferLogIdOfServiceOrderServiceCollectionServiceOrderService.getServiceOrderServiceCollection().remove(serviceOrderServiceCollectionServiceOrderService);
                    oldServiceTransferLogIdOfServiceOrderServiceCollectionServiceOrderService = em.merge(oldServiceTransferLogIdOfServiceOrderServiceCollectionServiceOrderService);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findServiceTransferLog(serviceTransferLog.getServiceTransferLogId()) != null) {
                throw new PreexistingEntityException("ServiceTransferLog " + serviceTransferLog + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServiceTransferLog serviceTransferLog) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceTransferLog persistentServiceTransferLog = em.find(ServiceTransferLog.class, serviceTransferLog.getServiceTransferLogId());
            Collection<ServiceOrderService> serviceOrderServiceCollectionOld = persistentServiceTransferLog.getServiceOrderServiceCollection();
            Collection<ServiceOrderService> serviceOrderServiceCollectionNew = serviceTransferLog.getServiceOrderServiceCollection();
            Collection<ServiceOrderService> attachedServiceOrderServiceCollectionNew = new ArrayList<ServiceOrderService>();
            for (ServiceOrderService serviceOrderServiceCollectionNewServiceOrderServiceToAttach : serviceOrderServiceCollectionNew) {
                serviceOrderServiceCollectionNewServiceOrderServiceToAttach = em.getReference(serviceOrderServiceCollectionNewServiceOrderServiceToAttach.getClass(), serviceOrderServiceCollectionNewServiceOrderServiceToAttach.getServiceOrderServiceId());
                attachedServiceOrderServiceCollectionNew.add(serviceOrderServiceCollectionNewServiceOrderServiceToAttach);
            }
            serviceOrderServiceCollectionNew = attachedServiceOrderServiceCollectionNew;
            serviceTransferLog.setServiceOrderServiceCollection(serviceOrderServiceCollectionNew);
            serviceTransferLog = em.merge(serviceTransferLog);
            for (ServiceOrderService serviceOrderServiceCollectionOldServiceOrderService : serviceOrderServiceCollectionOld) {
                if (!serviceOrderServiceCollectionNew.contains(serviceOrderServiceCollectionOldServiceOrderService)) {
                    serviceOrderServiceCollectionOldServiceOrderService.setServiceTransferLogId(null);
                    serviceOrderServiceCollectionOldServiceOrderService = em.merge(serviceOrderServiceCollectionOldServiceOrderService);
                }
            }
            for (ServiceOrderService serviceOrderServiceCollectionNewServiceOrderService : serviceOrderServiceCollectionNew) {
                if (!serviceOrderServiceCollectionOld.contains(serviceOrderServiceCollectionNewServiceOrderService)) {
                    ServiceTransferLog oldServiceTransferLogIdOfServiceOrderServiceCollectionNewServiceOrderService = serviceOrderServiceCollectionNewServiceOrderService.getServiceTransferLogId();
                    serviceOrderServiceCollectionNewServiceOrderService.setServiceTransferLogId(serviceTransferLog);
                    serviceOrderServiceCollectionNewServiceOrderService = em.merge(serviceOrderServiceCollectionNewServiceOrderService);
                    if (oldServiceTransferLogIdOfServiceOrderServiceCollectionNewServiceOrderService != null && !oldServiceTransferLogIdOfServiceOrderServiceCollectionNewServiceOrderService.equals(serviceTransferLog)) {
                        oldServiceTransferLogIdOfServiceOrderServiceCollectionNewServiceOrderService.getServiceOrderServiceCollection().remove(serviceOrderServiceCollectionNewServiceOrderService);
                        oldServiceTransferLogIdOfServiceOrderServiceCollectionNewServiceOrderService = em.merge(oldServiceTransferLogIdOfServiceOrderServiceCollectionNewServiceOrderService);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = serviceTransferLog.getServiceTransferLogId();
                if (findServiceTransferLog(id) == null) {
                    throw new NonexistentEntityException("The serviceTransferLog with id " + id + " no longer exists.");
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
            ServiceTransferLog serviceTransferLog;
            try {
                serviceTransferLog = em.getReference(ServiceTransferLog.class, id);
                serviceTransferLog.getServiceTransferLogId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The serviceTransferLog with id " + id + " no longer exists.", enfe);
            }
            Collection<ServiceOrderService> serviceOrderServiceCollection = serviceTransferLog.getServiceOrderServiceCollection();
            for (ServiceOrderService serviceOrderServiceCollectionServiceOrderService : serviceOrderServiceCollection) {
                serviceOrderServiceCollectionServiceOrderService.setServiceTransferLogId(null);
                serviceOrderServiceCollectionServiceOrderService = em.merge(serviceOrderServiceCollectionServiceOrderService);
            }
            em.remove(serviceTransferLog);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServiceTransferLog> findServiceTransferLogEntities() {
        return findServiceTransferLogEntities(true, -1, -1);
    }

    public List<ServiceTransferLog> findServiceTransferLogEntities(int maxResults, int firstResult) {
        return findServiceTransferLogEntities(false, maxResults, firstResult);
    }

    private List<ServiceTransferLog> findServiceTransferLogEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServiceTransferLog.class));
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

    public ServiceTransferLog findServiceTransferLog(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServiceTransferLog.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiceTransferLogCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServiceTransferLog> rt = cq.from(ServiceTransferLog.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
