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
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrder;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrderFile;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrderFileType;

/**
 *
 * @author William
 */
public class ServiceOrderFileJpaController implements Serializable {

    public ServiceOrderFileJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServiceOrderFile serviceOrderFile) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceOrder serviceOrderId = serviceOrderFile.getServiceOrderId();
            if (serviceOrderId != null) {
                serviceOrderId = em.getReference(serviceOrderId.getClass(), serviceOrderId.getServiceOrderId());
                serviceOrderFile.setServiceOrderId(serviceOrderId);
            }
            ServiceOrderFileType serviceOrderFileTypeId = serviceOrderFile.getServiceOrderFileTypeId();
            if (serviceOrderFileTypeId != null) {
                serviceOrderFileTypeId = em.getReference(serviceOrderFileTypeId.getClass(), serviceOrderFileTypeId.getServiceOrderFileTypeId());
                serviceOrderFile.setServiceOrderFileTypeId(serviceOrderFileTypeId);
            }
            em.persist(serviceOrderFile);
            if (serviceOrderId != null) {
                serviceOrderId.getServiceOrderFileCollection().add(serviceOrderFile);
                serviceOrderId = em.merge(serviceOrderId);
            }
            if (serviceOrderFileTypeId != null) {
                serviceOrderFileTypeId.getServiceOrderFileCollection().add(serviceOrderFile);
                serviceOrderFileTypeId = em.merge(serviceOrderFileTypeId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findServiceOrderFile(serviceOrderFile.getServiceOrderFileId()) != null) {
                throw new PreexistingEntityException("ServiceOrderFile " + serviceOrderFile + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServiceOrderFile serviceOrderFile) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceOrderFile persistentServiceOrderFile = em.find(ServiceOrderFile.class, serviceOrderFile.getServiceOrderFileId());
            ServiceOrder serviceOrderIdOld = persistentServiceOrderFile.getServiceOrderId();
            ServiceOrder serviceOrderIdNew = serviceOrderFile.getServiceOrderId();
            ServiceOrderFileType serviceOrderFileTypeIdOld = persistentServiceOrderFile.getServiceOrderFileTypeId();
            ServiceOrderFileType serviceOrderFileTypeIdNew = serviceOrderFile.getServiceOrderFileTypeId();
            if (serviceOrderIdNew != null) {
                serviceOrderIdNew = em.getReference(serviceOrderIdNew.getClass(), serviceOrderIdNew.getServiceOrderId());
                serviceOrderFile.setServiceOrderId(serviceOrderIdNew);
            }
            if (serviceOrderFileTypeIdNew != null) {
                serviceOrderFileTypeIdNew = em.getReference(serviceOrderFileTypeIdNew.getClass(), serviceOrderFileTypeIdNew.getServiceOrderFileTypeId());
                serviceOrderFile.setServiceOrderFileTypeId(serviceOrderFileTypeIdNew);
            }
            serviceOrderFile = em.merge(serviceOrderFile);
            if (serviceOrderIdOld != null && !serviceOrderIdOld.equals(serviceOrderIdNew)) {
                serviceOrderIdOld.getServiceOrderFileCollection().remove(serviceOrderFile);
                serviceOrderIdOld = em.merge(serviceOrderIdOld);
            }
            if (serviceOrderIdNew != null && !serviceOrderIdNew.equals(serviceOrderIdOld)) {
                serviceOrderIdNew.getServiceOrderFileCollection().add(serviceOrderFile);
                serviceOrderIdNew = em.merge(serviceOrderIdNew);
            }
            if (serviceOrderFileTypeIdOld != null && !serviceOrderFileTypeIdOld.equals(serviceOrderFileTypeIdNew)) {
                serviceOrderFileTypeIdOld.getServiceOrderFileCollection().remove(serviceOrderFile);
                serviceOrderFileTypeIdOld = em.merge(serviceOrderFileTypeIdOld);
            }
            if (serviceOrderFileTypeIdNew != null && !serviceOrderFileTypeIdNew.equals(serviceOrderFileTypeIdOld)) {
                serviceOrderFileTypeIdNew.getServiceOrderFileCollection().add(serviceOrderFile);
                serviceOrderFileTypeIdNew = em.merge(serviceOrderFileTypeIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = serviceOrderFile.getServiceOrderFileId();
                if (findServiceOrderFile(id) == null) {
                    throw new NonexistentEntityException("The serviceOrderFile with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceOrderFile serviceOrderFile;
            try {
                serviceOrderFile = em.getReference(ServiceOrderFile.class, id);
                serviceOrderFile.getServiceOrderFileId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The serviceOrderFile with id " + id + " no longer exists.", enfe);
            }
            ServiceOrder serviceOrderId = serviceOrderFile.getServiceOrderId();
            if (serviceOrderId != null) {
                serviceOrderId.getServiceOrderFileCollection().remove(serviceOrderFile);
                serviceOrderId = em.merge(serviceOrderId);
            }
            ServiceOrderFileType serviceOrderFileTypeId = serviceOrderFile.getServiceOrderFileTypeId();
            if (serviceOrderFileTypeId != null) {
                serviceOrderFileTypeId.getServiceOrderFileCollection().remove(serviceOrderFile);
                serviceOrderFileTypeId = em.merge(serviceOrderFileTypeId);
            }
            em.remove(serviceOrderFile);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServiceOrderFile> findServiceOrderFileEntities() {
        return findServiceOrderFileEntities(true, -1, -1);
    }

    public List<ServiceOrderFile> findServiceOrderFileEntities(int maxResults, int firstResult) {
        return findServiceOrderFileEntities(false, maxResults, firstResult);
    }

    private List<ServiceOrderFile> findServiceOrderFileEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServiceOrderFile.class));
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

    public ServiceOrderFile findServiceOrderFile(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServiceOrderFile.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiceOrderFileCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServiceOrderFile> rt = cq.from(ServiceOrderFile.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
