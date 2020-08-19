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
import mx.tab.wgp.qsmaritimex.entidades.ServiceOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.IllegalOrphanException;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.ServiceOrderStatus;

/**
 *
 * @author WilliamGP025
 */
public class ServiceOrderStatusJpaController implements Serializable {

    public ServiceOrderStatusJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServiceOrderStatus serviceOrderStatus) {
        if (serviceOrderStatus.getServiceOrderCollection() == null) {
            serviceOrderStatus.setServiceOrderCollection(new ArrayList<ServiceOrder>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ServiceOrder> attachedServiceOrderCollection = new ArrayList<ServiceOrder>();
            for (ServiceOrder serviceOrderCollectionServiceOrderToAttach : serviceOrderStatus.getServiceOrderCollection()) {
                serviceOrderCollectionServiceOrderToAttach = em.getReference(serviceOrderCollectionServiceOrderToAttach.getClass(), serviceOrderCollectionServiceOrderToAttach.getServiceOrderId());
                attachedServiceOrderCollection.add(serviceOrderCollectionServiceOrderToAttach);
            }
            serviceOrderStatus.setServiceOrderCollection(attachedServiceOrderCollection);
            em.persist(serviceOrderStatus);
            for (ServiceOrder serviceOrderCollectionServiceOrder : serviceOrderStatus.getServiceOrderCollection()) {
                ServiceOrderStatus oldServiceOrderStatusIdOfServiceOrderCollectionServiceOrder = serviceOrderCollectionServiceOrder.getServiceOrderStatusId();
                serviceOrderCollectionServiceOrder.setServiceOrderStatusId(serviceOrderStatus);
                serviceOrderCollectionServiceOrder = em.merge(serviceOrderCollectionServiceOrder);
                if (oldServiceOrderStatusIdOfServiceOrderCollectionServiceOrder != null) {
                    oldServiceOrderStatusIdOfServiceOrderCollectionServiceOrder.getServiceOrderCollection().remove(serviceOrderCollectionServiceOrder);
                    oldServiceOrderStatusIdOfServiceOrderCollectionServiceOrder = em.merge(oldServiceOrderStatusIdOfServiceOrderCollectionServiceOrder);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServiceOrderStatus serviceOrderStatus) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceOrderStatus persistentServiceOrderStatus = em.find(ServiceOrderStatus.class, serviceOrderStatus.getServiceOrderStatusId());
            Collection<ServiceOrder> serviceOrderCollectionOld = persistentServiceOrderStatus.getServiceOrderCollection();
            Collection<ServiceOrder> serviceOrderCollectionNew = serviceOrderStatus.getServiceOrderCollection();
            List<String> illegalOrphanMessages = null;
            for (ServiceOrder serviceOrderCollectionOldServiceOrder : serviceOrderCollectionOld) {
                if (!serviceOrderCollectionNew.contains(serviceOrderCollectionOldServiceOrder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ServiceOrder " + serviceOrderCollectionOldServiceOrder + " since its serviceOrderStatusId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<ServiceOrder> attachedServiceOrderCollectionNew = new ArrayList<ServiceOrder>();
            for (ServiceOrder serviceOrderCollectionNewServiceOrderToAttach : serviceOrderCollectionNew) {
                serviceOrderCollectionNewServiceOrderToAttach = em.getReference(serviceOrderCollectionNewServiceOrderToAttach.getClass(), serviceOrderCollectionNewServiceOrderToAttach.getServiceOrderId());
                attachedServiceOrderCollectionNew.add(serviceOrderCollectionNewServiceOrderToAttach);
            }
            serviceOrderCollectionNew = attachedServiceOrderCollectionNew;
            serviceOrderStatus.setServiceOrderCollection(serviceOrderCollectionNew);
            serviceOrderStatus = em.merge(serviceOrderStatus);
            for (ServiceOrder serviceOrderCollectionNewServiceOrder : serviceOrderCollectionNew) {
                if (!serviceOrderCollectionOld.contains(serviceOrderCollectionNewServiceOrder)) {
                    ServiceOrderStatus oldServiceOrderStatusIdOfServiceOrderCollectionNewServiceOrder = serviceOrderCollectionNewServiceOrder.getServiceOrderStatusId();
                    serviceOrderCollectionNewServiceOrder.setServiceOrderStatusId(serviceOrderStatus);
                    serviceOrderCollectionNewServiceOrder = em.merge(serviceOrderCollectionNewServiceOrder);
                    if (oldServiceOrderStatusIdOfServiceOrderCollectionNewServiceOrder != null && !oldServiceOrderStatusIdOfServiceOrderCollectionNewServiceOrder.equals(serviceOrderStatus)) {
                        oldServiceOrderStatusIdOfServiceOrderCollectionNewServiceOrder.getServiceOrderCollection().remove(serviceOrderCollectionNewServiceOrder);
                        oldServiceOrderStatusIdOfServiceOrderCollectionNewServiceOrder = em.merge(oldServiceOrderStatusIdOfServiceOrderCollectionNewServiceOrder);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = serviceOrderStatus.getServiceOrderStatusId();
                if (findServiceOrderStatus(id) == null) {
                    throw new NonexistentEntityException("The serviceOrderStatus with id " + id + " no longer exists.");
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
            ServiceOrderStatus serviceOrderStatus;
            try {
                serviceOrderStatus = em.getReference(ServiceOrderStatus.class, id);
                serviceOrderStatus.getServiceOrderStatusId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The serviceOrderStatus with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ServiceOrder> serviceOrderCollectionOrphanCheck = serviceOrderStatus.getServiceOrderCollection();
            for (ServiceOrder serviceOrderCollectionOrphanCheckServiceOrder : serviceOrderCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ServiceOrderStatus (" + serviceOrderStatus + ") cannot be destroyed since the ServiceOrder " + serviceOrderCollectionOrphanCheckServiceOrder + " in its serviceOrderCollection field has a non-nullable serviceOrderStatusId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(serviceOrderStatus);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServiceOrderStatus> findServiceOrderStatusEntities() {
        return findServiceOrderStatusEntities(true, -1, -1);
    }

    public List<ServiceOrderStatus> findServiceOrderStatusEntities(int maxResults, int firstResult) {
        return findServiceOrderStatusEntities(false, maxResults, firstResult);
    }

    private List<ServiceOrderStatus> findServiceOrderStatusEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServiceOrderStatus.class));
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

    public ServiceOrderStatus findServiceOrderStatus(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServiceOrderStatus.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiceOrderStatusCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServiceOrderStatus> rt = cq.from(ServiceOrderStatus.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
