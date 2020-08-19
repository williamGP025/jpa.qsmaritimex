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
import mx.tab.wgp.qsmaritimex.entidades.NotesServicesOrderService;

/**
 *
 * @author WilliamGP025
 */
public class NotesServicesOrderServiceJpaController implements Serializable {

    public NotesServicesOrderServiceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NotesServicesOrderService notesServicesOrderService) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(notesServicesOrderService);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNotesServicesOrderService(notesServicesOrderService.getServiceOrderServiceId()) != null) {
                throw new PreexistingEntityException("NotesServicesOrderService " + notesServicesOrderService + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NotesServicesOrderService notesServicesOrderService) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            notesServicesOrderService = em.merge(notesServicesOrderService);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = notesServicesOrderService.getServiceOrderServiceId();
                if (findNotesServicesOrderService(id) == null) {
                    throw new NonexistentEntityException("The notesServicesOrderService with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NotesServicesOrderService notesServicesOrderService;
            try {
                notesServicesOrderService = em.getReference(NotesServicesOrderService.class, id);
                notesServicesOrderService.getServiceOrderServiceId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The notesServicesOrderService with id " + id + " no longer exists.", enfe);
            }
            em.remove(notesServicesOrderService);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NotesServicesOrderService> findNotesServicesOrderServiceEntities() {
        return findNotesServicesOrderServiceEntities(true, -1, -1);
    }

    public List<NotesServicesOrderService> findNotesServicesOrderServiceEntities(int maxResults, int firstResult) {
        return findNotesServicesOrderServiceEntities(false, maxResults, firstResult);
    }

    private List<NotesServicesOrderService> findNotesServicesOrderServiceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NotesServicesOrderService.class));
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

    public NotesServicesOrderService findNotesServicesOrderService(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NotesServicesOrderService.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotesServicesOrderServiceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NotesServicesOrderService> rt = cq.from(NotesServicesOrderService.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
