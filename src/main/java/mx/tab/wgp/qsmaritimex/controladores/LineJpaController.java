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
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ShipOwner;
import mx.tab.wgp.qsmaritimex.entidades.Company;
import mx.tab.wgp.qsmaritimex.entidades.usuario.UserLine;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.IllegalOrphanException;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.Line;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.VesselPerLine;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrder;

/**
 *
 * @author William
 */
public class LineJpaController implements Serializable {

    public LineJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Line line) {
        if (line.getUserLineCollection() == null) {
            line.setUserLineCollection(new ArrayList<UserLine>());
        }
        if (line.getVesselPerLineCollection() == null) {
            line.setVesselPerLineCollection(new ArrayList<VesselPerLine>());
        }
        if (line.getServiceOrderCollection() == null) {
            line.setServiceOrderCollection(new ArrayList<ServiceOrder>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ShipOwner shipOwnerId = line.getShipOwnerId();
            if (shipOwnerId != null) {
                shipOwnerId = em.getReference(shipOwnerId.getClass(), shipOwnerId.getShipOwnerId());
                line.setShipOwnerId(shipOwnerId);
            }
            Company companyId = line.getCompanyId();
            if (companyId != null) {
                companyId = em.getReference(companyId.getClass(), companyId.getCompanyId());
                line.setCompanyId(companyId);
            }
            Collection<UserLine> attachedUserLineCollection = new ArrayList<UserLine>();
            for (UserLine userLineCollectionUserLineToAttach : line.getUserLineCollection()) {
                userLineCollectionUserLineToAttach = em.getReference(userLineCollectionUserLineToAttach.getClass(), userLineCollectionUserLineToAttach.getUserLineId());
                attachedUserLineCollection.add(userLineCollectionUserLineToAttach);
            }
            line.setUserLineCollection(attachedUserLineCollection);
            Collection<VesselPerLine> attachedVesselPerLineCollection = new ArrayList<VesselPerLine>();
            for (VesselPerLine vesselPerLineCollectionVesselPerLineToAttach : line.getVesselPerLineCollection()) {
                vesselPerLineCollectionVesselPerLineToAttach = em.getReference(vesselPerLineCollectionVesselPerLineToAttach.getClass(), vesselPerLineCollectionVesselPerLineToAttach.getVesselPerLineId());
                attachedVesselPerLineCollection.add(vesselPerLineCollectionVesselPerLineToAttach);
            }
            line.setVesselPerLineCollection(attachedVesselPerLineCollection);
            Collection<ServiceOrder> attachedServiceOrderCollection = new ArrayList<ServiceOrder>();
            for (ServiceOrder serviceOrderCollectionServiceOrderToAttach : line.getServiceOrderCollection()) {
                serviceOrderCollectionServiceOrderToAttach = em.getReference(serviceOrderCollectionServiceOrderToAttach.getClass(), serviceOrderCollectionServiceOrderToAttach.getServiceOrderId());
                attachedServiceOrderCollection.add(serviceOrderCollectionServiceOrderToAttach);
            }
            line.setServiceOrderCollection(attachedServiceOrderCollection);
            em.persist(line);
            if (shipOwnerId != null) {
                shipOwnerId.getLineCollection().add(line);
                shipOwnerId = em.merge(shipOwnerId);
            }
            if (companyId != null) {
                companyId.getLineCollection().add(line);
                companyId = em.merge(companyId);
            }
            for (UserLine userLineCollectionUserLine : line.getUserLineCollection()) {
                Line oldLineIdOfUserLineCollectionUserLine = userLineCollectionUserLine.getLineId();
                userLineCollectionUserLine.setLineId(line);
                userLineCollectionUserLine = em.merge(userLineCollectionUserLine);
                if (oldLineIdOfUserLineCollectionUserLine != null) {
                    oldLineIdOfUserLineCollectionUserLine.getUserLineCollection().remove(userLineCollectionUserLine);
                    oldLineIdOfUserLineCollectionUserLine = em.merge(oldLineIdOfUserLineCollectionUserLine);
                }
            }
            for (VesselPerLine vesselPerLineCollectionVesselPerLine : line.getVesselPerLineCollection()) {
                Line oldLineIdOfVesselPerLineCollectionVesselPerLine = vesselPerLineCollectionVesselPerLine.getLineId();
                vesselPerLineCollectionVesselPerLine.setLineId(line);
                vesselPerLineCollectionVesselPerLine = em.merge(vesselPerLineCollectionVesselPerLine);
                if (oldLineIdOfVesselPerLineCollectionVesselPerLine != null) {
                    oldLineIdOfVesselPerLineCollectionVesselPerLine.getVesselPerLineCollection().remove(vesselPerLineCollectionVesselPerLine);
                    oldLineIdOfVesselPerLineCollectionVesselPerLine = em.merge(oldLineIdOfVesselPerLineCollectionVesselPerLine);
                }
            }
            for (ServiceOrder serviceOrderCollectionServiceOrder : line.getServiceOrderCollection()) {
                Line oldLineIdOfServiceOrderCollectionServiceOrder = serviceOrderCollectionServiceOrder.getLineId();
                serviceOrderCollectionServiceOrder.setLineId(line);
                serviceOrderCollectionServiceOrder = em.merge(serviceOrderCollectionServiceOrder);
                if (oldLineIdOfServiceOrderCollectionServiceOrder != null) {
                    oldLineIdOfServiceOrderCollectionServiceOrder.getServiceOrderCollection().remove(serviceOrderCollectionServiceOrder);
                    oldLineIdOfServiceOrderCollectionServiceOrder = em.merge(oldLineIdOfServiceOrderCollectionServiceOrder);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Line line) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Line persistentLine = em.find(Line.class, line.getLineId());
            ShipOwner shipOwnerIdOld = persistentLine.getShipOwnerId();
            ShipOwner shipOwnerIdNew = line.getShipOwnerId();
            Company companyIdOld = persistentLine.getCompanyId();
            Company companyIdNew = line.getCompanyId();
            Collection<UserLine> userLineCollectionOld = persistentLine.getUserLineCollection();
            Collection<UserLine> userLineCollectionNew = line.getUserLineCollection();
            Collection<VesselPerLine> vesselPerLineCollectionOld = persistentLine.getVesselPerLineCollection();
            Collection<VesselPerLine> vesselPerLineCollectionNew = line.getVesselPerLineCollection();
            Collection<ServiceOrder> serviceOrderCollectionOld = persistentLine.getServiceOrderCollection();
            Collection<ServiceOrder> serviceOrderCollectionNew = line.getServiceOrderCollection();
            List<String> illegalOrphanMessages = null;
            for (UserLine userLineCollectionOldUserLine : userLineCollectionOld) {
                if (!userLineCollectionNew.contains(userLineCollectionOldUserLine)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UserLine " + userLineCollectionOldUserLine + " since its lineId field is not nullable.");
                }
            }
            for (VesselPerLine vesselPerLineCollectionOldVesselPerLine : vesselPerLineCollectionOld) {
                if (!vesselPerLineCollectionNew.contains(vesselPerLineCollectionOldVesselPerLine)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain VesselPerLine " + vesselPerLineCollectionOldVesselPerLine + " since its lineId field is not nullable.");
                }
            }
            for (ServiceOrder serviceOrderCollectionOldServiceOrder : serviceOrderCollectionOld) {
                if (!serviceOrderCollectionNew.contains(serviceOrderCollectionOldServiceOrder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ServiceOrder " + serviceOrderCollectionOldServiceOrder + " since its lineId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (shipOwnerIdNew != null) {
                shipOwnerIdNew = em.getReference(shipOwnerIdNew.getClass(), shipOwnerIdNew.getShipOwnerId());
                line.setShipOwnerId(shipOwnerIdNew);
            }
            if (companyIdNew != null) {
                companyIdNew = em.getReference(companyIdNew.getClass(), companyIdNew.getCompanyId());
                line.setCompanyId(companyIdNew);
            }
            Collection<UserLine> attachedUserLineCollectionNew = new ArrayList<UserLine>();
            for (UserLine userLineCollectionNewUserLineToAttach : userLineCollectionNew) {
                userLineCollectionNewUserLineToAttach = em.getReference(userLineCollectionNewUserLineToAttach.getClass(), userLineCollectionNewUserLineToAttach.getUserLineId());
                attachedUserLineCollectionNew.add(userLineCollectionNewUserLineToAttach);
            }
            userLineCollectionNew = attachedUserLineCollectionNew;
            line.setUserLineCollection(userLineCollectionNew);
            Collection<VesselPerLine> attachedVesselPerLineCollectionNew = new ArrayList<VesselPerLine>();
            for (VesselPerLine vesselPerLineCollectionNewVesselPerLineToAttach : vesselPerLineCollectionNew) {
                vesselPerLineCollectionNewVesselPerLineToAttach = em.getReference(vesselPerLineCollectionNewVesselPerLineToAttach.getClass(), vesselPerLineCollectionNewVesselPerLineToAttach.getVesselPerLineId());
                attachedVesselPerLineCollectionNew.add(vesselPerLineCollectionNewVesselPerLineToAttach);
            }
            vesselPerLineCollectionNew = attachedVesselPerLineCollectionNew;
            line.setVesselPerLineCollection(vesselPerLineCollectionNew);
            Collection<ServiceOrder> attachedServiceOrderCollectionNew = new ArrayList<ServiceOrder>();
            for (ServiceOrder serviceOrderCollectionNewServiceOrderToAttach : serviceOrderCollectionNew) {
                serviceOrderCollectionNewServiceOrderToAttach = em.getReference(serviceOrderCollectionNewServiceOrderToAttach.getClass(), serviceOrderCollectionNewServiceOrderToAttach.getServiceOrderId());
                attachedServiceOrderCollectionNew.add(serviceOrderCollectionNewServiceOrderToAttach);
            }
            serviceOrderCollectionNew = attachedServiceOrderCollectionNew;
            line.setServiceOrderCollection(serviceOrderCollectionNew);
            line = em.merge(line);
            if (shipOwnerIdOld != null && !shipOwnerIdOld.equals(shipOwnerIdNew)) {
                shipOwnerIdOld.getLineCollection().remove(line);
                shipOwnerIdOld = em.merge(shipOwnerIdOld);
            }
            if (shipOwnerIdNew != null && !shipOwnerIdNew.equals(shipOwnerIdOld)) {
                shipOwnerIdNew.getLineCollection().add(line);
                shipOwnerIdNew = em.merge(shipOwnerIdNew);
            }
            if (companyIdOld != null && !companyIdOld.equals(companyIdNew)) {
                companyIdOld.getLineCollection().remove(line);
                companyIdOld = em.merge(companyIdOld);
            }
            if (companyIdNew != null && !companyIdNew.equals(companyIdOld)) {
                companyIdNew.getLineCollection().add(line);
                companyIdNew = em.merge(companyIdNew);
            }
            for (UserLine userLineCollectionNewUserLine : userLineCollectionNew) {
                if (!userLineCollectionOld.contains(userLineCollectionNewUserLine)) {
                    Line oldLineIdOfUserLineCollectionNewUserLine = userLineCollectionNewUserLine.getLineId();
                    userLineCollectionNewUserLine.setLineId(line);
                    userLineCollectionNewUserLine = em.merge(userLineCollectionNewUserLine);
                    if (oldLineIdOfUserLineCollectionNewUserLine != null && !oldLineIdOfUserLineCollectionNewUserLine.equals(line)) {
                        oldLineIdOfUserLineCollectionNewUserLine.getUserLineCollection().remove(userLineCollectionNewUserLine);
                        oldLineIdOfUserLineCollectionNewUserLine = em.merge(oldLineIdOfUserLineCollectionNewUserLine);
                    }
                }
            }
            for (VesselPerLine vesselPerLineCollectionNewVesselPerLine : vesselPerLineCollectionNew) {
                if (!vesselPerLineCollectionOld.contains(vesselPerLineCollectionNewVesselPerLine)) {
                    Line oldLineIdOfVesselPerLineCollectionNewVesselPerLine = vesselPerLineCollectionNewVesselPerLine.getLineId();
                    vesselPerLineCollectionNewVesselPerLine.setLineId(line);
                    vesselPerLineCollectionNewVesselPerLine = em.merge(vesselPerLineCollectionNewVesselPerLine);
                    if (oldLineIdOfVesselPerLineCollectionNewVesselPerLine != null && !oldLineIdOfVesselPerLineCollectionNewVesselPerLine.equals(line)) {
                        oldLineIdOfVesselPerLineCollectionNewVesselPerLine.getVesselPerLineCollection().remove(vesselPerLineCollectionNewVesselPerLine);
                        oldLineIdOfVesselPerLineCollectionNewVesselPerLine = em.merge(oldLineIdOfVesselPerLineCollectionNewVesselPerLine);
                    }
                }
            }
            for (ServiceOrder serviceOrderCollectionNewServiceOrder : serviceOrderCollectionNew) {
                if (!serviceOrderCollectionOld.contains(serviceOrderCollectionNewServiceOrder)) {
                    Line oldLineIdOfServiceOrderCollectionNewServiceOrder = serviceOrderCollectionNewServiceOrder.getLineId();
                    serviceOrderCollectionNewServiceOrder.setLineId(line);
                    serviceOrderCollectionNewServiceOrder = em.merge(serviceOrderCollectionNewServiceOrder);
                    if (oldLineIdOfServiceOrderCollectionNewServiceOrder != null && !oldLineIdOfServiceOrderCollectionNewServiceOrder.equals(line)) {
                        oldLineIdOfServiceOrderCollectionNewServiceOrder.getServiceOrderCollection().remove(serviceOrderCollectionNewServiceOrder);
                        oldLineIdOfServiceOrderCollectionNewServiceOrder = em.merge(oldLineIdOfServiceOrderCollectionNewServiceOrder);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = line.getLineId();
                if (findLine(id) == null) {
                    throw new NonexistentEntityException("The line with id " + id + " no longer exists.");
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
            Line line;
            try {
                line = em.getReference(Line.class, id);
                line.getLineId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The line with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<UserLine> userLineCollectionOrphanCheck = line.getUserLineCollection();
            for (UserLine userLineCollectionOrphanCheckUserLine : userLineCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Line (" + line + ") cannot be destroyed since the UserLine " + userLineCollectionOrphanCheckUserLine + " in its userLineCollection field has a non-nullable lineId field.");
            }
            Collection<VesselPerLine> vesselPerLineCollectionOrphanCheck = line.getVesselPerLineCollection();
            for (VesselPerLine vesselPerLineCollectionOrphanCheckVesselPerLine : vesselPerLineCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Line (" + line + ") cannot be destroyed since the VesselPerLine " + vesselPerLineCollectionOrphanCheckVesselPerLine + " in its vesselPerLineCollection field has a non-nullable lineId field.");
            }
            Collection<ServiceOrder> serviceOrderCollectionOrphanCheck = line.getServiceOrderCollection();
            for (ServiceOrder serviceOrderCollectionOrphanCheckServiceOrder : serviceOrderCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Line (" + line + ") cannot be destroyed since the ServiceOrder " + serviceOrderCollectionOrphanCheckServiceOrder + " in its serviceOrderCollection field has a non-nullable lineId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            ShipOwner shipOwnerId = line.getShipOwnerId();
            if (shipOwnerId != null) {
                shipOwnerId.getLineCollection().remove(line);
                shipOwnerId = em.merge(shipOwnerId);
            }
            Company companyId = line.getCompanyId();
            if (companyId != null) {
                companyId.getLineCollection().remove(line);
                companyId = em.merge(companyId);
            }
            em.remove(line);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Line> findLineEntities() {
        return findLineEntities(true, -1, -1);
    }

    public List<Line> findLineEntities(int maxResults, int firstResult) {
        return findLineEntities(false, maxResults, firstResult);
    }

    private List<Line> findLineEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Line.class));
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

    public Line findLine(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Line.class, id);
        } finally {
            em.close();
        }
    }

    public int getLineCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Line> rt = cq.from(Line.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
