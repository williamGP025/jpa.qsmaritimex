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
import mx.tab.wgp.qsmaritimex.entidades.nominacion.Line;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.Vessel;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.VesselPerLine;

/**
 *
 * @author William
 */
public class VesselPerLineJpaController implements Serializable {

    public VesselPerLineJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VesselPerLine vesselPerLine) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Line lineId = vesselPerLine.getLineId();
            if (lineId != null) {
                lineId = em.getReference(lineId.getClass(), lineId.getLineId());
                vesselPerLine.setLineId(lineId);
            }
            Vessel vesselId = vesselPerLine.getVesselId();
            if (vesselId != null) {
                vesselId = em.getReference(vesselId.getClass(), vesselId.getVesselId());
                vesselPerLine.setVesselId(vesselId);
            }
            em.persist(vesselPerLine);
            if (lineId != null) {
                lineId.getVesselPerLineCollection().add(vesselPerLine);
                lineId = em.merge(lineId);
            }
            if (vesselId != null) {
                vesselId.getVesselPerLineCollection().add(vesselPerLine);
                vesselId = em.merge(vesselId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VesselPerLine vesselPerLine) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VesselPerLine persistentVesselPerLine = em.find(VesselPerLine.class, vesselPerLine.getVesselPerLineId());
            Line lineIdOld = persistentVesselPerLine.getLineId();
            Line lineIdNew = vesselPerLine.getLineId();
            Vessel vesselIdOld = persistentVesselPerLine.getVesselId();
            Vessel vesselIdNew = vesselPerLine.getVesselId();
            if (lineIdNew != null) {
                lineIdNew = em.getReference(lineIdNew.getClass(), lineIdNew.getLineId());
                vesselPerLine.setLineId(lineIdNew);
            }
            if (vesselIdNew != null) {
                vesselIdNew = em.getReference(vesselIdNew.getClass(), vesselIdNew.getVesselId());
                vesselPerLine.setVesselId(vesselIdNew);
            }
            vesselPerLine = em.merge(vesselPerLine);
            if (lineIdOld != null && !lineIdOld.equals(lineIdNew)) {
                lineIdOld.getVesselPerLineCollection().remove(vesselPerLine);
                lineIdOld = em.merge(lineIdOld);
            }
            if (lineIdNew != null && !lineIdNew.equals(lineIdOld)) {
                lineIdNew.getVesselPerLineCollection().add(vesselPerLine);
                lineIdNew = em.merge(lineIdNew);
            }
            if (vesselIdOld != null && !vesselIdOld.equals(vesselIdNew)) {
                vesselIdOld.getVesselPerLineCollection().remove(vesselPerLine);
                vesselIdOld = em.merge(vesselIdOld);
            }
            if (vesselIdNew != null && !vesselIdNew.equals(vesselIdOld)) {
                vesselIdNew.getVesselPerLineCollection().add(vesselPerLine);
                vesselIdNew = em.merge(vesselIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vesselPerLine.getVesselPerLineId();
                if (findVesselPerLine(id) == null) {
                    throw new NonexistentEntityException("The vesselPerLine with id " + id + " no longer exists.");
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
            VesselPerLine vesselPerLine;
            try {
                vesselPerLine = em.getReference(VesselPerLine.class, id);
                vesselPerLine.getVesselPerLineId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vesselPerLine with id " + id + " no longer exists.", enfe);
            }
            Line lineId = vesselPerLine.getLineId();
            if (lineId != null) {
                lineId.getVesselPerLineCollection().remove(vesselPerLine);
                lineId = em.merge(lineId);
            }
            Vessel vesselId = vesselPerLine.getVesselId();
            if (vesselId != null) {
                vesselId.getVesselPerLineCollection().remove(vesselPerLine);
                vesselId = em.merge(vesselId);
            }
            em.remove(vesselPerLine);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VesselPerLine> findVesselPerLineEntities() {
        return findVesselPerLineEntities(true, -1, -1);
    }

    public List<VesselPerLine> findVesselPerLineEntities(int maxResults, int firstResult) {
        return findVesselPerLineEntities(false, maxResults, firstResult);
    }

    private List<VesselPerLine> findVesselPerLineEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VesselPerLine.class));
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

    public VesselPerLine findVesselPerLine(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VesselPerLine.class, id);
        } finally {
            em.close();
        }
    }

    public int getVesselPerLineCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VesselPerLine> rt = cq.from(VesselPerLine.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
