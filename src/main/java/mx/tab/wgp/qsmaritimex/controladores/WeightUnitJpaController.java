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
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrderProductType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.IllegalOrphanException;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.WeightUnit;

/**
 *
 * @author William
 */
public class WeightUnitJpaController implements Serializable {

    public WeightUnitJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(WeightUnit weightUnit) {
        if (weightUnit.getServiceOrderProductTypeCollection() == null) {
            weightUnit.setServiceOrderProductTypeCollection(new ArrayList<ServiceOrderProductType>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ServiceOrderProductType> attachedServiceOrderProductTypeCollection = new ArrayList<ServiceOrderProductType>();
            for (ServiceOrderProductType serviceOrderProductTypeCollectionServiceOrderProductTypeToAttach : weightUnit.getServiceOrderProductTypeCollection()) {
                serviceOrderProductTypeCollectionServiceOrderProductTypeToAttach = em.getReference(serviceOrderProductTypeCollectionServiceOrderProductTypeToAttach.getClass(), serviceOrderProductTypeCollectionServiceOrderProductTypeToAttach.getServiceOrderProductTypePK());
                attachedServiceOrderProductTypeCollection.add(serviceOrderProductTypeCollectionServiceOrderProductTypeToAttach);
            }
            weightUnit.setServiceOrderProductTypeCollection(attachedServiceOrderProductTypeCollection);
            em.persist(weightUnit);
            for (ServiceOrderProductType serviceOrderProductTypeCollectionServiceOrderProductType : weightUnit.getServiceOrderProductTypeCollection()) {
                WeightUnit oldWeightUnitIdOfServiceOrderProductTypeCollectionServiceOrderProductType = serviceOrderProductTypeCollectionServiceOrderProductType.getWeightUnitId();
                serviceOrderProductTypeCollectionServiceOrderProductType.setWeightUnitId(weightUnit);
                serviceOrderProductTypeCollectionServiceOrderProductType = em.merge(serviceOrderProductTypeCollectionServiceOrderProductType);
                if (oldWeightUnitIdOfServiceOrderProductTypeCollectionServiceOrderProductType != null) {
                    oldWeightUnitIdOfServiceOrderProductTypeCollectionServiceOrderProductType.getServiceOrderProductTypeCollection().remove(serviceOrderProductTypeCollectionServiceOrderProductType);
                    oldWeightUnitIdOfServiceOrderProductTypeCollectionServiceOrderProductType = em.merge(oldWeightUnitIdOfServiceOrderProductTypeCollectionServiceOrderProductType);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(WeightUnit weightUnit) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            WeightUnit persistentWeightUnit = em.find(WeightUnit.class, weightUnit.getWeightUnitId());
            Collection<ServiceOrderProductType> serviceOrderProductTypeCollectionOld = persistentWeightUnit.getServiceOrderProductTypeCollection();
            Collection<ServiceOrderProductType> serviceOrderProductTypeCollectionNew = weightUnit.getServiceOrderProductTypeCollection();
            List<String> illegalOrphanMessages = null;
            for (ServiceOrderProductType serviceOrderProductTypeCollectionOldServiceOrderProductType : serviceOrderProductTypeCollectionOld) {
                if (!serviceOrderProductTypeCollectionNew.contains(serviceOrderProductTypeCollectionOldServiceOrderProductType)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ServiceOrderProductType " + serviceOrderProductTypeCollectionOldServiceOrderProductType + " since its weightUnitId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<ServiceOrderProductType> attachedServiceOrderProductTypeCollectionNew = new ArrayList<ServiceOrderProductType>();
            for (ServiceOrderProductType serviceOrderProductTypeCollectionNewServiceOrderProductTypeToAttach : serviceOrderProductTypeCollectionNew) {
                serviceOrderProductTypeCollectionNewServiceOrderProductTypeToAttach = em.getReference(serviceOrderProductTypeCollectionNewServiceOrderProductTypeToAttach.getClass(), serviceOrderProductTypeCollectionNewServiceOrderProductTypeToAttach.getServiceOrderProductTypePK());
                attachedServiceOrderProductTypeCollectionNew.add(serviceOrderProductTypeCollectionNewServiceOrderProductTypeToAttach);
            }
            serviceOrderProductTypeCollectionNew = attachedServiceOrderProductTypeCollectionNew;
            weightUnit.setServiceOrderProductTypeCollection(serviceOrderProductTypeCollectionNew);
            weightUnit = em.merge(weightUnit);
            for (ServiceOrderProductType serviceOrderProductTypeCollectionNewServiceOrderProductType : serviceOrderProductTypeCollectionNew) {
                if (!serviceOrderProductTypeCollectionOld.contains(serviceOrderProductTypeCollectionNewServiceOrderProductType)) {
                    WeightUnit oldWeightUnitIdOfServiceOrderProductTypeCollectionNewServiceOrderProductType = serviceOrderProductTypeCollectionNewServiceOrderProductType.getWeightUnitId();
                    serviceOrderProductTypeCollectionNewServiceOrderProductType.setWeightUnitId(weightUnit);
                    serviceOrderProductTypeCollectionNewServiceOrderProductType = em.merge(serviceOrderProductTypeCollectionNewServiceOrderProductType);
                    if (oldWeightUnitIdOfServiceOrderProductTypeCollectionNewServiceOrderProductType != null && !oldWeightUnitIdOfServiceOrderProductTypeCollectionNewServiceOrderProductType.equals(weightUnit)) {
                        oldWeightUnitIdOfServiceOrderProductTypeCollectionNewServiceOrderProductType.getServiceOrderProductTypeCollection().remove(serviceOrderProductTypeCollectionNewServiceOrderProductType);
                        oldWeightUnitIdOfServiceOrderProductTypeCollectionNewServiceOrderProductType = em.merge(oldWeightUnitIdOfServiceOrderProductTypeCollectionNewServiceOrderProductType);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = weightUnit.getWeightUnitId();
                if (findWeightUnit(id) == null) {
                    throw new NonexistentEntityException("The weightUnit with id " + id + " no longer exists.");
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
            WeightUnit weightUnit;
            try {
                weightUnit = em.getReference(WeightUnit.class, id);
                weightUnit.getWeightUnitId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The weightUnit with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ServiceOrderProductType> serviceOrderProductTypeCollectionOrphanCheck = weightUnit.getServiceOrderProductTypeCollection();
            for (ServiceOrderProductType serviceOrderProductTypeCollectionOrphanCheckServiceOrderProductType : serviceOrderProductTypeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This WeightUnit (" + weightUnit + ") cannot be destroyed since the ServiceOrderProductType " + serviceOrderProductTypeCollectionOrphanCheckServiceOrderProductType + " in its serviceOrderProductTypeCollection field has a non-nullable weightUnitId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(weightUnit);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<WeightUnit> findWeightUnitEntities() {
        return findWeightUnitEntities(true, -1, -1);
    }

    public List<WeightUnit> findWeightUnitEntities(int maxResults, int firstResult) {
        return findWeightUnitEntities(false, maxResults, firstResult);
    }

    private List<WeightUnit> findWeightUnitEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(WeightUnit.class));
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

    public WeightUnit findWeightUnit(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(WeightUnit.class, id);
        } finally {
            em.close();
        }
    }

    public int getWeightUnitCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<WeightUnit> rt = cq.from(WeightUnit.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
