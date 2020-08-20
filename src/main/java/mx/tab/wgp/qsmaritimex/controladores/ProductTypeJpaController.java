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
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ProductType;

/**
 *
 * @author William
 */
public class ProductTypeJpaController implements Serializable {

    public ProductTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductType productType) {
        if (productType.getServiceOrderProductTypeCollection() == null) {
            productType.setServiceOrderProductTypeCollection(new ArrayList<ServiceOrderProductType>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ServiceOrderProductType> attachedServiceOrderProductTypeCollection = new ArrayList<ServiceOrderProductType>();
            for (ServiceOrderProductType serviceOrderProductTypeCollectionServiceOrderProductTypeToAttach : productType.getServiceOrderProductTypeCollection()) {
                serviceOrderProductTypeCollectionServiceOrderProductTypeToAttach = em.getReference(serviceOrderProductTypeCollectionServiceOrderProductTypeToAttach.getClass(), serviceOrderProductTypeCollectionServiceOrderProductTypeToAttach.getServiceOrderProductTypePK());
                attachedServiceOrderProductTypeCollection.add(serviceOrderProductTypeCollectionServiceOrderProductTypeToAttach);
            }
            productType.setServiceOrderProductTypeCollection(attachedServiceOrderProductTypeCollection);
            em.persist(productType);
            for (ServiceOrderProductType serviceOrderProductTypeCollectionServiceOrderProductType : productType.getServiceOrderProductTypeCollection()) {
                ProductType oldProductTypeOfServiceOrderProductTypeCollectionServiceOrderProductType = serviceOrderProductTypeCollectionServiceOrderProductType.getProductType();
                serviceOrderProductTypeCollectionServiceOrderProductType.setProductType(productType);
                serviceOrderProductTypeCollectionServiceOrderProductType = em.merge(serviceOrderProductTypeCollectionServiceOrderProductType);
                if (oldProductTypeOfServiceOrderProductTypeCollectionServiceOrderProductType != null) {
                    oldProductTypeOfServiceOrderProductTypeCollectionServiceOrderProductType.getServiceOrderProductTypeCollection().remove(serviceOrderProductTypeCollectionServiceOrderProductType);
                    oldProductTypeOfServiceOrderProductTypeCollectionServiceOrderProductType = em.merge(oldProductTypeOfServiceOrderProductTypeCollectionServiceOrderProductType);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductType productType) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductType persistentProductType = em.find(ProductType.class, productType.getProductTypeId());
            Collection<ServiceOrderProductType> serviceOrderProductTypeCollectionOld = persistentProductType.getServiceOrderProductTypeCollection();
            Collection<ServiceOrderProductType> serviceOrderProductTypeCollectionNew = productType.getServiceOrderProductTypeCollection();
            List<String> illegalOrphanMessages = null;
            for (ServiceOrderProductType serviceOrderProductTypeCollectionOldServiceOrderProductType : serviceOrderProductTypeCollectionOld) {
                if (!serviceOrderProductTypeCollectionNew.contains(serviceOrderProductTypeCollectionOldServiceOrderProductType)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ServiceOrderProductType " + serviceOrderProductTypeCollectionOldServiceOrderProductType + " since its productType field is not nullable.");
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
            productType.setServiceOrderProductTypeCollection(serviceOrderProductTypeCollectionNew);
            productType = em.merge(productType);
            for (ServiceOrderProductType serviceOrderProductTypeCollectionNewServiceOrderProductType : serviceOrderProductTypeCollectionNew) {
                if (!serviceOrderProductTypeCollectionOld.contains(serviceOrderProductTypeCollectionNewServiceOrderProductType)) {
                    ProductType oldProductTypeOfServiceOrderProductTypeCollectionNewServiceOrderProductType = serviceOrderProductTypeCollectionNewServiceOrderProductType.getProductType();
                    serviceOrderProductTypeCollectionNewServiceOrderProductType.setProductType(productType);
                    serviceOrderProductTypeCollectionNewServiceOrderProductType = em.merge(serviceOrderProductTypeCollectionNewServiceOrderProductType);
                    if (oldProductTypeOfServiceOrderProductTypeCollectionNewServiceOrderProductType != null && !oldProductTypeOfServiceOrderProductTypeCollectionNewServiceOrderProductType.equals(productType)) {
                        oldProductTypeOfServiceOrderProductTypeCollectionNewServiceOrderProductType.getServiceOrderProductTypeCollection().remove(serviceOrderProductTypeCollectionNewServiceOrderProductType);
                        oldProductTypeOfServiceOrderProductTypeCollectionNewServiceOrderProductType = em.merge(oldProductTypeOfServiceOrderProductTypeCollectionNewServiceOrderProductType);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = productType.getProductTypeId();
                if (findProductType(id) == null) {
                    throw new NonexistentEntityException("The productType with id " + id + " no longer exists.");
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
            ProductType productType;
            try {
                productType = em.getReference(ProductType.class, id);
                productType.getProductTypeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productType with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ServiceOrderProductType> serviceOrderProductTypeCollectionOrphanCheck = productType.getServiceOrderProductTypeCollection();
            for (ServiceOrderProductType serviceOrderProductTypeCollectionOrphanCheckServiceOrderProductType : serviceOrderProductTypeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ProductType (" + productType + ") cannot be destroyed since the ServiceOrderProductType " + serviceOrderProductTypeCollectionOrphanCheckServiceOrderProductType + " in its serviceOrderProductTypeCollection field has a non-nullable productType field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(productType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductType> findProductTypeEntities() {
        return findProductTypeEntities(true, -1, -1);
    }

    public List<ProductType> findProductTypeEntities(int maxResults, int firstResult) {
        return findProductTypeEntities(false, maxResults, firstResult);
    }

    private List<ProductType> findProductTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductType.class));
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

    public ProductType findProductType(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductType.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductType> rt = cq.from(ProductType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
