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
import mx.tab.wgp.qsmaritimex.controladores.exceptions.PreexistingEntityException;
import mx.tab.wgp.qsmaritimex.entidades.ProductType;
import mx.tab.wgp.qsmaritimex.entidades.ServiceOrder;
import mx.tab.wgp.qsmaritimex.entidades.ServiceOrderProductType;
import mx.tab.wgp.qsmaritimex.entidades.ServiceOrderProductTypePK;
import mx.tab.wgp.qsmaritimex.entidades.WeightUnit;

/**
 *
 * @author WilliamGP025
 */
public class ServiceOrderProductTypeJpaController implements Serializable {

    public ServiceOrderProductTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServiceOrderProductType serviceOrderProductType) throws PreexistingEntityException, Exception {
        if (serviceOrderProductType.getServiceOrderProductTypePK() == null) {
            serviceOrderProductType.setServiceOrderProductTypePK(new ServiceOrderProductTypePK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductType productType = serviceOrderProductType.getProductType();
            if (productType != null) {
                productType = em.getReference(productType.getClass(), productType.getProductTypeId());
                serviceOrderProductType.setProductType(productType);
            }
            ServiceOrder serviceOrder = serviceOrderProductType.getServiceOrder();
            if (serviceOrder != null) {
                serviceOrder = em.getReference(serviceOrder.getClass(), serviceOrder.getServiceOrderId());
                serviceOrderProductType.setServiceOrder(serviceOrder);
            }
            WeightUnit weightUnitId = serviceOrderProductType.getWeightUnitId();
            if (weightUnitId != null) {
                weightUnitId = em.getReference(weightUnitId.getClass(), weightUnitId.getWeightUnitId());
                serviceOrderProductType.setWeightUnitId(weightUnitId);
            }
            em.persist(serviceOrderProductType);
            if (productType != null) {
                productType.getServiceOrderProductTypeCollection().add(serviceOrderProductType);
                productType = em.merge(productType);
            }
            if (serviceOrder != null) {
                serviceOrder.getServiceOrderProductTypeCollection().add(serviceOrderProductType);
                serviceOrder = em.merge(serviceOrder);
            }
            if (weightUnitId != null) {
                weightUnitId.getServiceOrderProductTypeCollection().add(serviceOrderProductType);
                weightUnitId = em.merge(weightUnitId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findServiceOrderProductType(serviceOrderProductType.getServiceOrderProductTypePK()) != null) {
                throw new PreexistingEntityException("ServiceOrderProductType " + serviceOrderProductType + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServiceOrderProductType serviceOrderProductType) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceOrderProductType persistentServiceOrderProductType = em.find(ServiceOrderProductType.class, serviceOrderProductType.getServiceOrderProductTypePK());
            ProductType productTypeOld = persistentServiceOrderProductType.getProductType();
            ProductType productTypeNew = serviceOrderProductType.getProductType();
            ServiceOrder serviceOrderOld = persistentServiceOrderProductType.getServiceOrder();
            ServiceOrder serviceOrderNew = serviceOrderProductType.getServiceOrder();
            WeightUnit weightUnitIdOld = persistentServiceOrderProductType.getWeightUnitId();
            WeightUnit weightUnitIdNew = serviceOrderProductType.getWeightUnitId();
            if (productTypeNew != null) {
                productTypeNew = em.getReference(productTypeNew.getClass(), productTypeNew.getProductTypeId());
                serviceOrderProductType.setProductType(productTypeNew);
            }
            if (serviceOrderNew != null) {
                serviceOrderNew = em.getReference(serviceOrderNew.getClass(), serviceOrderNew.getServiceOrderId());
                serviceOrderProductType.setServiceOrder(serviceOrderNew);
            }
            if (weightUnitIdNew != null) {
                weightUnitIdNew = em.getReference(weightUnitIdNew.getClass(), weightUnitIdNew.getWeightUnitId());
                serviceOrderProductType.setWeightUnitId(weightUnitIdNew);
            }
            serviceOrderProductType = em.merge(serviceOrderProductType);
            if (productTypeOld != null && !productTypeOld.equals(productTypeNew)) {
                productTypeOld.getServiceOrderProductTypeCollection().remove(serviceOrderProductType);
                productTypeOld = em.merge(productTypeOld);
            }
            if (productTypeNew != null && !productTypeNew.equals(productTypeOld)) {
                productTypeNew.getServiceOrderProductTypeCollection().add(serviceOrderProductType);
                productTypeNew = em.merge(productTypeNew);
            }
            if (serviceOrderOld != null && !serviceOrderOld.equals(serviceOrderNew)) {
                serviceOrderOld.getServiceOrderProductTypeCollection().remove(serviceOrderProductType);
                serviceOrderOld = em.merge(serviceOrderOld);
            }
            if (serviceOrderNew != null && !serviceOrderNew.equals(serviceOrderOld)) {
                serviceOrderNew.getServiceOrderProductTypeCollection().add(serviceOrderProductType);
                serviceOrderNew = em.merge(serviceOrderNew);
            }
            if (weightUnitIdOld != null && !weightUnitIdOld.equals(weightUnitIdNew)) {
                weightUnitIdOld.getServiceOrderProductTypeCollection().remove(serviceOrderProductType);
                weightUnitIdOld = em.merge(weightUnitIdOld);
            }
            if (weightUnitIdNew != null && !weightUnitIdNew.equals(weightUnitIdOld)) {
                weightUnitIdNew.getServiceOrderProductTypeCollection().add(serviceOrderProductType);
                weightUnitIdNew = em.merge(weightUnitIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ServiceOrderProductTypePK id = serviceOrderProductType.getServiceOrderProductTypePK();
                if (findServiceOrderProductType(id) == null) {
                    throw new NonexistentEntityException("The serviceOrderProductType with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ServiceOrderProductTypePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceOrderProductType serviceOrderProductType;
            try {
                serviceOrderProductType = em.getReference(ServiceOrderProductType.class, id);
                serviceOrderProductType.getServiceOrderProductTypePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The serviceOrderProductType with id " + id + " no longer exists.", enfe);
            }
            ProductType productType = serviceOrderProductType.getProductType();
            if (productType != null) {
                productType.getServiceOrderProductTypeCollection().remove(serviceOrderProductType);
                productType = em.merge(productType);
            }
            ServiceOrder serviceOrder = serviceOrderProductType.getServiceOrder();
            if (serviceOrder != null) {
                serviceOrder.getServiceOrderProductTypeCollection().remove(serviceOrderProductType);
                serviceOrder = em.merge(serviceOrder);
            }
            WeightUnit weightUnitId = serviceOrderProductType.getWeightUnitId();
            if (weightUnitId != null) {
                weightUnitId.getServiceOrderProductTypeCollection().remove(serviceOrderProductType);
                weightUnitId = em.merge(weightUnitId);
            }
            em.remove(serviceOrderProductType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServiceOrderProductType> findServiceOrderProductTypeEntities() {
        return findServiceOrderProductTypeEntities(true, -1, -1);
    }

    public List<ServiceOrderProductType> findServiceOrderProductTypeEntities(int maxResults, int firstResult) {
        return findServiceOrderProductTypeEntities(false, maxResults, firstResult);
    }

    private List<ServiceOrderProductType> findServiceOrderProductTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServiceOrderProductType.class));
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

    public ServiceOrderProductType findServiceOrderProductType(ServiceOrderProductTypePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServiceOrderProductType.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiceOrderProductTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServiceOrderProductType> rt = cq.from(ServiceOrderProductType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
