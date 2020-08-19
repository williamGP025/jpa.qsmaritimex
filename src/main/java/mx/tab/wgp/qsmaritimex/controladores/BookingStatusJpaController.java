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
import mx.tab.wgp.qsmaritimex.entidades.BookingStatus;

/**
 *
 * @author WilliamGP025
 */
public class BookingStatusJpaController implements Serializable {

    public BookingStatusJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BookingStatus bookingStatus) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(bookingStatus);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBookingStatus(bookingStatus.getBookingStatusId()) != null) {
                throw new PreexistingEntityException("BookingStatus " + bookingStatus + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BookingStatus bookingStatus) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            bookingStatus = em.merge(bookingStatus);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bookingStatus.getBookingStatusId();
                if (findBookingStatus(id) == null) {
                    throw new NonexistentEntityException("The bookingStatus with id " + id + " no longer exists.");
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
            BookingStatus bookingStatus;
            try {
                bookingStatus = em.getReference(BookingStatus.class, id);
                bookingStatus.getBookingStatusId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bookingStatus with id " + id + " no longer exists.", enfe);
            }
            em.remove(bookingStatus);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BookingStatus> findBookingStatusEntities() {
        return findBookingStatusEntities(true, -1, -1);
    }

    public List<BookingStatus> findBookingStatusEntities(int maxResults, int firstResult) {
        return findBookingStatusEntities(false, maxResults, firstResult);
    }

    private List<BookingStatus> findBookingStatusEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BookingStatus.class));
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

    public BookingStatus findBookingStatus(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BookingStatus.class, id);
        } finally {
            em.close();
        }
    }

    public int getBookingStatusCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BookingStatus> rt = cq.from(BookingStatus.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
