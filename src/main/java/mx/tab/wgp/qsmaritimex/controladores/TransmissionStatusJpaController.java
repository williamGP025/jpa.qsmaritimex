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
import mx.tab.wgp.qsmaritimex.entidades.TransmissionStatus;

/**
 *
 * @author William
 */
public class TransmissionStatusJpaController implements Serializable {

    public TransmissionStatusJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TransmissionStatus transmissionStatus) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(transmissionStatus);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTransmissionStatus(transmissionStatus.getTransmissionStatusId()) != null) {
                throw new PreexistingEntityException("TransmissionStatus " + transmissionStatus + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TransmissionStatus transmissionStatus) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            transmissionStatus = em.merge(transmissionStatus);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = transmissionStatus.getTransmissionStatusId();
                if (findTransmissionStatus(id) == null) {
                    throw new NonexistentEntityException("The transmissionStatus with id " + id + " no longer exists.");
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
            TransmissionStatus transmissionStatus;
            try {
                transmissionStatus = em.getReference(TransmissionStatus.class, id);
                transmissionStatus.getTransmissionStatusId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The transmissionStatus with id " + id + " no longer exists.", enfe);
            }
            em.remove(transmissionStatus);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TransmissionStatus> findTransmissionStatusEntities() {
        return findTransmissionStatusEntities(true, -1, -1);
    }

    public List<TransmissionStatus> findTransmissionStatusEntities(int maxResults, int firstResult) {
        return findTransmissionStatusEntities(false, maxResults, firstResult);
    }

    private List<TransmissionStatus> findTransmissionStatusEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TransmissionStatus.class));
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

    public TransmissionStatus findTransmissionStatus(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TransmissionStatus.class, id);
        } finally {
            em.close();
        }
    }

    public int getTransmissionStatusCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TransmissionStatus> rt = cq.from(TransmissionStatus.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
