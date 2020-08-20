/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.controladores;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.Line;
import mx.tab.wgp.qsmaritimex.entidades.usuario.User;
import mx.tab.wgp.qsmaritimex.entidades.Currency;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.Itinerary;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrderStatus;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ShipOwner;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrderService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.IllegalOrphanException;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ReferenceShipOwner;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrderFile;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ActingRoles;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrder;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrderProductType;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrderJoin;

/**
 *
 * @author William
 */
public class ServiceOrderJpaController implements Serializable {

    public ServiceOrderJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServiceOrder serviceOrder) {
        if (serviceOrder.getServiceOrderServiceCollection() == null) {
            serviceOrder.setServiceOrderServiceCollection(new ArrayList<ServiceOrderService>());
        }
        if (serviceOrder.getReferenceShipOwnerCollection() == null) {
            serviceOrder.setReferenceShipOwnerCollection(new ArrayList<ReferenceShipOwner>());
        }
        if (serviceOrder.getServiceOrderFileCollection() == null) {
            serviceOrder.setServiceOrderFileCollection(new ArrayList<ServiceOrderFile>());
        }
        if (serviceOrder.getActingRolesCollection() == null) {
            serviceOrder.setActingRolesCollection(new ArrayList<ActingRoles>());
        }
        if (serviceOrder.getServiceOrderProductTypeCollection() == null) {
            serviceOrder.setServiceOrderProductTypeCollection(new ArrayList<ServiceOrderProductType>());
        }
        if (serviceOrder.getServiceOrderJoinCollection() == null) {
            serviceOrder.setServiceOrderJoinCollection(new ArrayList<ServiceOrderJoin>());
        }
        if (serviceOrder.getServiceOrderJoinCollection1() == null) {
            serviceOrder.setServiceOrderJoinCollection1(new ArrayList<ServiceOrderJoin>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Line lineId = serviceOrder.getLineId();
            if (lineId != null) {
                lineId = em.getReference(lineId.getClass(), lineId.getLineId());
                serviceOrder.setLineId(lineId);
            }
            User userId = serviceOrder.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getUserId());
                serviceOrder.setUserId(userId);
            }
            Currency currencyId = serviceOrder.getCurrencyId();
            if (currencyId != null) {
                currencyId = em.getReference(currencyId.getClass(), currencyId.getCurrencyId());
                serviceOrder.setCurrencyId(currencyId);
            }
            Itinerary itineraryId = serviceOrder.getItineraryId();
            if (itineraryId != null) {
                itineraryId = em.getReference(itineraryId.getClass(), itineraryId.getItineraryId());
                serviceOrder.setItineraryId(itineraryId);
            }
            ServiceOrderStatus serviceOrderStatusId = serviceOrder.getServiceOrderStatusId();
            if (serviceOrderStatusId != null) {
                serviceOrderStatusId = em.getReference(serviceOrderStatusId.getClass(), serviceOrderStatusId.getServiceOrderStatusId());
                serviceOrder.setServiceOrderStatusId(serviceOrderStatusId);
            }
            ShipOwner shipOwnerId = serviceOrder.getShipOwnerId();
            if (shipOwnerId != null) {
                shipOwnerId = em.getReference(shipOwnerId.getClass(), shipOwnerId.getShipOwnerId());
                serviceOrder.setShipOwnerId(shipOwnerId);
            }
            Collection<ServiceOrderService> attachedServiceOrderServiceCollection = new ArrayList<ServiceOrderService>();
            for (ServiceOrderService serviceOrderServiceCollectionServiceOrderServiceToAttach : serviceOrder.getServiceOrderServiceCollection()) {
                serviceOrderServiceCollectionServiceOrderServiceToAttach = em.getReference(serviceOrderServiceCollectionServiceOrderServiceToAttach.getClass(), serviceOrderServiceCollectionServiceOrderServiceToAttach.getServiceOrderServiceId());
                attachedServiceOrderServiceCollection.add(serviceOrderServiceCollectionServiceOrderServiceToAttach);
            }
            serviceOrder.setServiceOrderServiceCollection(attachedServiceOrderServiceCollection);
            Collection<ReferenceShipOwner> attachedReferenceShipOwnerCollection = new ArrayList<ReferenceShipOwner>();
            for (ReferenceShipOwner referenceShipOwnerCollectionReferenceShipOwnerToAttach : serviceOrder.getReferenceShipOwnerCollection()) {
                referenceShipOwnerCollectionReferenceShipOwnerToAttach = em.getReference(referenceShipOwnerCollectionReferenceShipOwnerToAttach.getClass(), referenceShipOwnerCollectionReferenceShipOwnerToAttach.getReferenceShipOwnerId());
                attachedReferenceShipOwnerCollection.add(referenceShipOwnerCollectionReferenceShipOwnerToAttach);
            }
            serviceOrder.setReferenceShipOwnerCollection(attachedReferenceShipOwnerCollection);
            Collection<ServiceOrderFile> attachedServiceOrderFileCollection = new ArrayList<ServiceOrderFile>();
            for (ServiceOrderFile serviceOrderFileCollectionServiceOrderFileToAttach : serviceOrder.getServiceOrderFileCollection()) {
                serviceOrderFileCollectionServiceOrderFileToAttach = em.getReference(serviceOrderFileCollectionServiceOrderFileToAttach.getClass(), serviceOrderFileCollectionServiceOrderFileToAttach.getServiceOrderFileId());
                attachedServiceOrderFileCollection.add(serviceOrderFileCollectionServiceOrderFileToAttach);
            }
            serviceOrder.setServiceOrderFileCollection(attachedServiceOrderFileCollection);
            Collection<ActingRoles> attachedActingRolesCollection = new ArrayList<ActingRoles>();
            for (ActingRoles actingRolesCollectionActingRolesToAttach : serviceOrder.getActingRolesCollection()) {
                actingRolesCollectionActingRolesToAttach = em.getReference(actingRolesCollectionActingRolesToAttach.getClass(), actingRolesCollectionActingRolesToAttach.getActingRoleId());
                attachedActingRolesCollection.add(actingRolesCollectionActingRolesToAttach);
            }
            serviceOrder.setActingRolesCollection(attachedActingRolesCollection);
            Collection<ServiceOrderProductType> attachedServiceOrderProductTypeCollection = new ArrayList<ServiceOrderProductType>();
            for (ServiceOrderProductType serviceOrderProductTypeCollectionServiceOrderProductTypeToAttach : serviceOrder.getServiceOrderProductTypeCollection()) {
                serviceOrderProductTypeCollectionServiceOrderProductTypeToAttach = em.getReference(serviceOrderProductTypeCollectionServiceOrderProductTypeToAttach.getClass(), serviceOrderProductTypeCollectionServiceOrderProductTypeToAttach.getServiceOrderProductTypePK());
                attachedServiceOrderProductTypeCollection.add(serviceOrderProductTypeCollectionServiceOrderProductTypeToAttach);
            }
            serviceOrder.setServiceOrderProductTypeCollection(attachedServiceOrderProductTypeCollection);
            Collection<ServiceOrderJoin> attachedServiceOrderJoinCollection = new ArrayList<ServiceOrderJoin>();
            for (ServiceOrderJoin serviceOrderJoinCollectionServiceOrderJoinToAttach : serviceOrder.getServiceOrderJoinCollection()) {
                serviceOrderJoinCollectionServiceOrderJoinToAttach = em.getReference(serviceOrderJoinCollectionServiceOrderJoinToAttach.getClass(), serviceOrderJoinCollectionServiceOrderJoinToAttach.getServiceOrderJoinId());
                attachedServiceOrderJoinCollection.add(serviceOrderJoinCollectionServiceOrderJoinToAttach);
            }
            serviceOrder.setServiceOrderJoinCollection(attachedServiceOrderJoinCollection);
            Collection<ServiceOrderJoin> attachedServiceOrderJoinCollection1 = new ArrayList<ServiceOrderJoin>();
            for (ServiceOrderJoin serviceOrderJoinCollection1ServiceOrderJoinToAttach : serviceOrder.getServiceOrderJoinCollection1()) {
                serviceOrderJoinCollection1ServiceOrderJoinToAttach = em.getReference(serviceOrderJoinCollection1ServiceOrderJoinToAttach.getClass(), serviceOrderJoinCollection1ServiceOrderJoinToAttach.getServiceOrderJoinId());
                attachedServiceOrderJoinCollection1.add(serviceOrderJoinCollection1ServiceOrderJoinToAttach);
            }
            serviceOrder.setServiceOrderJoinCollection1(attachedServiceOrderJoinCollection1);
            em.persist(serviceOrder);
            if (lineId != null) {
                lineId.getServiceOrderCollection().add(serviceOrder);
                lineId = em.merge(lineId);
            }
            if (userId != null) {
                userId.getServiceOrderCollection().add(serviceOrder);
                userId = em.merge(userId);
            }
            if (currencyId != null) {
                currencyId.getServiceOrderCollection().add(serviceOrder);
                currencyId = em.merge(currencyId);
            }
            if (itineraryId != null) {
                itineraryId.getServiceOrderCollection().add(serviceOrder);
                itineraryId = em.merge(itineraryId);
            }
            if (serviceOrderStatusId != null) {
                serviceOrderStatusId.getServiceOrderCollection().add(serviceOrder);
                serviceOrderStatusId = em.merge(serviceOrderStatusId);
            }
            if (shipOwnerId != null) {
                shipOwnerId.getServiceOrderCollection().add(serviceOrder);
                shipOwnerId = em.merge(shipOwnerId);
            }
            for (ServiceOrderService serviceOrderServiceCollectionServiceOrderService : serviceOrder.getServiceOrderServiceCollection()) {
                ServiceOrder oldServiceOrderIdOfServiceOrderServiceCollectionServiceOrderService = serviceOrderServiceCollectionServiceOrderService.getServiceOrderId();
                serviceOrderServiceCollectionServiceOrderService.setServiceOrderId(serviceOrder);
                serviceOrderServiceCollectionServiceOrderService = em.merge(serviceOrderServiceCollectionServiceOrderService);
                if (oldServiceOrderIdOfServiceOrderServiceCollectionServiceOrderService != null) {
                    oldServiceOrderIdOfServiceOrderServiceCollectionServiceOrderService.getServiceOrderServiceCollection().remove(serviceOrderServiceCollectionServiceOrderService);
                    oldServiceOrderIdOfServiceOrderServiceCollectionServiceOrderService = em.merge(oldServiceOrderIdOfServiceOrderServiceCollectionServiceOrderService);
                }
            }
            for (ReferenceShipOwner referenceShipOwnerCollectionReferenceShipOwner : serviceOrder.getReferenceShipOwnerCollection()) {
                ServiceOrder oldServiceOrderIdOfReferenceShipOwnerCollectionReferenceShipOwner = referenceShipOwnerCollectionReferenceShipOwner.getServiceOrderId();
                referenceShipOwnerCollectionReferenceShipOwner.setServiceOrderId(serviceOrder);
                referenceShipOwnerCollectionReferenceShipOwner = em.merge(referenceShipOwnerCollectionReferenceShipOwner);
                if (oldServiceOrderIdOfReferenceShipOwnerCollectionReferenceShipOwner != null) {
                    oldServiceOrderIdOfReferenceShipOwnerCollectionReferenceShipOwner.getReferenceShipOwnerCollection().remove(referenceShipOwnerCollectionReferenceShipOwner);
                    oldServiceOrderIdOfReferenceShipOwnerCollectionReferenceShipOwner = em.merge(oldServiceOrderIdOfReferenceShipOwnerCollectionReferenceShipOwner);
                }
            }
            for (ServiceOrderFile serviceOrderFileCollectionServiceOrderFile : serviceOrder.getServiceOrderFileCollection()) {
                ServiceOrder oldServiceOrderIdOfServiceOrderFileCollectionServiceOrderFile = serviceOrderFileCollectionServiceOrderFile.getServiceOrderId();
                serviceOrderFileCollectionServiceOrderFile.setServiceOrderId(serviceOrder);
                serviceOrderFileCollectionServiceOrderFile = em.merge(serviceOrderFileCollectionServiceOrderFile);
                if (oldServiceOrderIdOfServiceOrderFileCollectionServiceOrderFile != null) {
                    oldServiceOrderIdOfServiceOrderFileCollectionServiceOrderFile.getServiceOrderFileCollection().remove(serviceOrderFileCollectionServiceOrderFile);
                    oldServiceOrderIdOfServiceOrderFileCollectionServiceOrderFile = em.merge(oldServiceOrderIdOfServiceOrderFileCollectionServiceOrderFile);
                }
            }
            for (ActingRoles actingRolesCollectionActingRoles : serviceOrder.getActingRolesCollection()) {
                ServiceOrder oldServiceOrderIdOfActingRolesCollectionActingRoles = actingRolesCollectionActingRoles.getServiceOrderId();
                actingRolesCollectionActingRoles.setServiceOrderId(serviceOrder);
                actingRolesCollectionActingRoles = em.merge(actingRolesCollectionActingRoles);
                if (oldServiceOrderIdOfActingRolesCollectionActingRoles != null) {
                    oldServiceOrderIdOfActingRolesCollectionActingRoles.getActingRolesCollection().remove(actingRolesCollectionActingRoles);
                    oldServiceOrderIdOfActingRolesCollectionActingRoles = em.merge(oldServiceOrderIdOfActingRolesCollectionActingRoles);
                }
            }
            for (ServiceOrderProductType serviceOrderProductTypeCollectionServiceOrderProductType : serviceOrder.getServiceOrderProductTypeCollection()) {
                ServiceOrder oldServiceOrderOfServiceOrderProductTypeCollectionServiceOrderProductType = serviceOrderProductTypeCollectionServiceOrderProductType.getServiceOrder();
                serviceOrderProductTypeCollectionServiceOrderProductType.setServiceOrder(serviceOrder);
                serviceOrderProductTypeCollectionServiceOrderProductType = em.merge(serviceOrderProductTypeCollectionServiceOrderProductType);
                if (oldServiceOrderOfServiceOrderProductTypeCollectionServiceOrderProductType != null) {
                    oldServiceOrderOfServiceOrderProductTypeCollectionServiceOrderProductType.getServiceOrderProductTypeCollection().remove(serviceOrderProductTypeCollectionServiceOrderProductType);
                    oldServiceOrderOfServiceOrderProductTypeCollectionServiceOrderProductType = em.merge(oldServiceOrderOfServiceOrderProductTypeCollectionServiceOrderProductType);
                }
            }
            for (ServiceOrderJoin serviceOrderJoinCollectionServiceOrderJoin : serviceOrder.getServiceOrderJoinCollection()) {
                ServiceOrder oldServiceOrderIdFatherOfServiceOrderJoinCollectionServiceOrderJoin = serviceOrderJoinCollectionServiceOrderJoin.getServiceOrderIdFather();
                serviceOrderJoinCollectionServiceOrderJoin.setServiceOrderIdFather(serviceOrder);
                serviceOrderJoinCollectionServiceOrderJoin = em.merge(serviceOrderJoinCollectionServiceOrderJoin);
                if (oldServiceOrderIdFatherOfServiceOrderJoinCollectionServiceOrderJoin != null) {
                    oldServiceOrderIdFatherOfServiceOrderJoinCollectionServiceOrderJoin.getServiceOrderJoinCollection().remove(serviceOrderJoinCollectionServiceOrderJoin);
                    oldServiceOrderIdFatherOfServiceOrderJoinCollectionServiceOrderJoin = em.merge(oldServiceOrderIdFatherOfServiceOrderJoinCollectionServiceOrderJoin);
                }
            }
            for (ServiceOrderJoin serviceOrderJoinCollection1ServiceOrderJoin : serviceOrder.getServiceOrderJoinCollection1()) {
                ServiceOrder oldServiceOrderIdSonOfServiceOrderJoinCollection1ServiceOrderJoin = serviceOrderJoinCollection1ServiceOrderJoin.getServiceOrderIdSon();
                serviceOrderJoinCollection1ServiceOrderJoin.setServiceOrderIdSon(serviceOrder);
                serviceOrderJoinCollection1ServiceOrderJoin = em.merge(serviceOrderJoinCollection1ServiceOrderJoin);
                if (oldServiceOrderIdSonOfServiceOrderJoinCollection1ServiceOrderJoin != null) {
                    oldServiceOrderIdSonOfServiceOrderJoinCollection1ServiceOrderJoin.getServiceOrderJoinCollection1().remove(serviceOrderJoinCollection1ServiceOrderJoin);
                    oldServiceOrderIdSonOfServiceOrderJoinCollection1ServiceOrderJoin = em.merge(oldServiceOrderIdSonOfServiceOrderJoinCollection1ServiceOrderJoin);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServiceOrder serviceOrder) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceOrder persistentServiceOrder = em.find(ServiceOrder.class, serviceOrder.getServiceOrderId());
            Line lineIdOld = persistentServiceOrder.getLineId();
            Line lineIdNew = serviceOrder.getLineId();
            User userIdOld = persistentServiceOrder.getUserId();
            User userIdNew = serviceOrder.getUserId();
            Currency currencyIdOld = persistentServiceOrder.getCurrencyId();
            Currency currencyIdNew = serviceOrder.getCurrencyId();
            Itinerary itineraryIdOld = persistentServiceOrder.getItineraryId();
            Itinerary itineraryIdNew = serviceOrder.getItineraryId();
            ServiceOrderStatus serviceOrderStatusIdOld = persistentServiceOrder.getServiceOrderStatusId();
            ServiceOrderStatus serviceOrderStatusIdNew = serviceOrder.getServiceOrderStatusId();
            ShipOwner shipOwnerIdOld = persistentServiceOrder.getShipOwnerId();
            ShipOwner shipOwnerIdNew = serviceOrder.getShipOwnerId();
            Collection<ServiceOrderService> serviceOrderServiceCollectionOld = persistentServiceOrder.getServiceOrderServiceCollection();
            Collection<ServiceOrderService> serviceOrderServiceCollectionNew = serviceOrder.getServiceOrderServiceCollection();
            Collection<ReferenceShipOwner> referenceShipOwnerCollectionOld = persistentServiceOrder.getReferenceShipOwnerCollection();
            Collection<ReferenceShipOwner> referenceShipOwnerCollectionNew = serviceOrder.getReferenceShipOwnerCollection();
            Collection<ServiceOrderFile> serviceOrderFileCollectionOld = persistentServiceOrder.getServiceOrderFileCollection();
            Collection<ServiceOrderFile> serviceOrderFileCollectionNew = serviceOrder.getServiceOrderFileCollection();
            Collection<ActingRoles> actingRolesCollectionOld = persistentServiceOrder.getActingRolesCollection();
            Collection<ActingRoles> actingRolesCollectionNew = serviceOrder.getActingRolesCollection();
            Collection<ServiceOrderProductType> serviceOrderProductTypeCollectionOld = persistentServiceOrder.getServiceOrderProductTypeCollection();
            Collection<ServiceOrderProductType> serviceOrderProductTypeCollectionNew = serviceOrder.getServiceOrderProductTypeCollection();
            Collection<ServiceOrderJoin> serviceOrderJoinCollectionOld = persistentServiceOrder.getServiceOrderJoinCollection();
            Collection<ServiceOrderJoin> serviceOrderJoinCollectionNew = serviceOrder.getServiceOrderJoinCollection();
            Collection<ServiceOrderJoin> serviceOrderJoinCollection1Old = persistentServiceOrder.getServiceOrderJoinCollection1();
            Collection<ServiceOrderJoin> serviceOrderJoinCollection1New = serviceOrder.getServiceOrderJoinCollection1();
            List<String> illegalOrphanMessages = null;
            for (ServiceOrderService serviceOrderServiceCollectionOldServiceOrderService : serviceOrderServiceCollectionOld) {
                if (!serviceOrderServiceCollectionNew.contains(serviceOrderServiceCollectionOldServiceOrderService)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ServiceOrderService " + serviceOrderServiceCollectionOldServiceOrderService + " since its serviceOrderId field is not nullable.");
                }
            }
            for (ReferenceShipOwner referenceShipOwnerCollectionOldReferenceShipOwner : referenceShipOwnerCollectionOld) {
                if (!referenceShipOwnerCollectionNew.contains(referenceShipOwnerCollectionOldReferenceShipOwner)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ReferenceShipOwner " + referenceShipOwnerCollectionOldReferenceShipOwner + " since its serviceOrderId field is not nullable.");
                }
            }
            for (ServiceOrderFile serviceOrderFileCollectionOldServiceOrderFile : serviceOrderFileCollectionOld) {
                if (!serviceOrderFileCollectionNew.contains(serviceOrderFileCollectionOldServiceOrderFile)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ServiceOrderFile " + serviceOrderFileCollectionOldServiceOrderFile + " since its serviceOrderId field is not nullable.");
                }
            }
            for (ActingRoles actingRolesCollectionOldActingRoles : actingRolesCollectionOld) {
                if (!actingRolesCollectionNew.contains(actingRolesCollectionOldActingRoles)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ActingRoles " + actingRolesCollectionOldActingRoles + " since its serviceOrderId field is not nullable.");
                }
            }
            for (ServiceOrderProductType serviceOrderProductTypeCollectionOldServiceOrderProductType : serviceOrderProductTypeCollectionOld) {
                if (!serviceOrderProductTypeCollectionNew.contains(serviceOrderProductTypeCollectionOldServiceOrderProductType)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ServiceOrderProductType " + serviceOrderProductTypeCollectionOldServiceOrderProductType + " since its serviceOrder field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (lineIdNew != null) {
                lineIdNew = em.getReference(lineIdNew.getClass(), lineIdNew.getLineId());
                serviceOrder.setLineId(lineIdNew);
            }
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getUserId());
                serviceOrder.setUserId(userIdNew);
            }
            if (currencyIdNew != null) {
                currencyIdNew = em.getReference(currencyIdNew.getClass(), currencyIdNew.getCurrencyId());
                serviceOrder.setCurrencyId(currencyIdNew);
            }
            if (itineraryIdNew != null) {
                itineraryIdNew = em.getReference(itineraryIdNew.getClass(), itineraryIdNew.getItineraryId());
                serviceOrder.setItineraryId(itineraryIdNew);
            }
            if (serviceOrderStatusIdNew != null) {
                serviceOrderStatusIdNew = em.getReference(serviceOrderStatusIdNew.getClass(), serviceOrderStatusIdNew.getServiceOrderStatusId());
                serviceOrder.setServiceOrderStatusId(serviceOrderStatusIdNew);
            }
            if (shipOwnerIdNew != null) {
                shipOwnerIdNew = em.getReference(shipOwnerIdNew.getClass(), shipOwnerIdNew.getShipOwnerId());
                serviceOrder.setShipOwnerId(shipOwnerIdNew);
            }
            Collection<ServiceOrderService> attachedServiceOrderServiceCollectionNew = new ArrayList<ServiceOrderService>();
            for (ServiceOrderService serviceOrderServiceCollectionNewServiceOrderServiceToAttach : serviceOrderServiceCollectionNew) {
                serviceOrderServiceCollectionNewServiceOrderServiceToAttach = em.getReference(serviceOrderServiceCollectionNewServiceOrderServiceToAttach.getClass(), serviceOrderServiceCollectionNewServiceOrderServiceToAttach.getServiceOrderServiceId());
                attachedServiceOrderServiceCollectionNew.add(serviceOrderServiceCollectionNewServiceOrderServiceToAttach);
            }
            serviceOrderServiceCollectionNew = attachedServiceOrderServiceCollectionNew;
            serviceOrder.setServiceOrderServiceCollection(serviceOrderServiceCollectionNew);
            Collection<ReferenceShipOwner> attachedReferenceShipOwnerCollectionNew = new ArrayList<ReferenceShipOwner>();
            for (ReferenceShipOwner referenceShipOwnerCollectionNewReferenceShipOwnerToAttach : referenceShipOwnerCollectionNew) {
                referenceShipOwnerCollectionNewReferenceShipOwnerToAttach = em.getReference(referenceShipOwnerCollectionNewReferenceShipOwnerToAttach.getClass(), referenceShipOwnerCollectionNewReferenceShipOwnerToAttach.getReferenceShipOwnerId());
                attachedReferenceShipOwnerCollectionNew.add(referenceShipOwnerCollectionNewReferenceShipOwnerToAttach);
            }
            referenceShipOwnerCollectionNew = attachedReferenceShipOwnerCollectionNew;
            serviceOrder.setReferenceShipOwnerCollection(referenceShipOwnerCollectionNew);
            Collection<ServiceOrderFile> attachedServiceOrderFileCollectionNew = new ArrayList<ServiceOrderFile>();
            for (ServiceOrderFile serviceOrderFileCollectionNewServiceOrderFileToAttach : serviceOrderFileCollectionNew) {
                serviceOrderFileCollectionNewServiceOrderFileToAttach = em.getReference(serviceOrderFileCollectionNewServiceOrderFileToAttach.getClass(), serviceOrderFileCollectionNewServiceOrderFileToAttach.getServiceOrderFileId());
                attachedServiceOrderFileCollectionNew.add(serviceOrderFileCollectionNewServiceOrderFileToAttach);
            }
            serviceOrderFileCollectionNew = attachedServiceOrderFileCollectionNew;
            serviceOrder.setServiceOrderFileCollection(serviceOrderFileCollectionNew);
            Collection<ActingRoles> attachedActingRolesCollectionNew = new ArrayList<ActingRoles>();
            for (ActingRoles actingRolesCollectionNewActingRolesToAttach : actingRolesCollectionNew) {
                actingRolesCollectionNewActingRolesToAttach = em.getReference(actingRolesCollectionNewActingRolesToAttach.getClass(), actingRolesCollectionNewActingRolesToAttach.getActingRoleId());
                attachedActingRolesCollectionNew.add(actingRolesCollectionNewActingRolesToAttach);
            }
            actingRolesCollectionNew = attachedActingRolesCollectionNew;
            serviceOrder.setActingRolesCollection(actingRolesCollectionNew);
            Collection<ServiceOrderProductType> attachedServiceOrderProductTypeCollectionNew = new ArrayList<ServiceOrderProductType>();
            for (ServiceOrderProductType serviceOrderProductTypeCollectionNewServiceOrderProductTypeToAttach : serviceOrderProductTypeCollectionNew) {
                serviceOrderProductTypeCollectionNewServiceOrderProductTypeToAttach = em.getReference(serviceOrderProductTypeCollectionNewServiceOrderProductTypeToAttach.getClass(), serviceOrderProductTypeCollectionNewServiceOrderProductTypeToAttach.getServiceOrderProductTypePK());
                attachedServiceOrderProductTypeCollectionNew.add(serviceOrderProductTypeCollectionNewServiceOrderProductTypeToAttach);
            }
            serviceOrderProductTypeCollectionNew = attachedServiceOrderProductTypeCollectionNew;
            serviceOrder.setServiceOrderProductTypeCollection(serviceOrderProductTypeCollectionNew);
            Collection<ServiceOrderJoin> attachedServiceOrderJoinCollectionNew = new ArrayList<ServiceOrderJoin>();
            for (ServiceOrderJoin serviceOrderJoinCollectionNewServiceOrderJoinToAttach : serviceOrderJoinCollectionNew) {
                serviceOrderJoinCollectionNewServiceOrderJoinToAttach = em.getReference(serviceOrderJoinCollectionNewServiceOrderJoinToAttach.getClass(), serviceOrderJoinCollectionNewServiceOrderJoinToAttach.getServiceOrderJoinId());
                attachedServiceOrderJoinCollectionNew.add(serviceOrderJoinCollectionNewServiceOrderJoinToAttach);
            }
            serviceOrderJoinCollectionNew = attachedServiceOrderJoinCollectionNew;
            serviceOrder.setServiceOrderJoinCollection(serviceOrderJoinCollectionNew);
            Collection<ServiceOrderJoin> attachedServiceOrderJoinCollection1New = new ArrayList<ServiceOrderJoin>();
            for (ServiceOrderJoin serviceOrderJoinCollection1NewServiceOrderJoinToAttach : serviceOrderJoinCollection1New) {
                serviceOrderJoinCollection1NewServiceOrderJoinToAttach = em.getReference(serviceOrderJoinCollection1NewServiceOrderJoinToAttach.getClass(), serviceOrderJoinCollection1NewServiceOrderJoinToAttach.getServiceOrderJoinId());
                attachedServiceOrderJoinCollection1New.add(serviceOrderJoinCollection1NewServiceOrderJoinToAttach);
            }
            serviceOrderJoinCollection1New = attachedServiceOrderJoinCollection1New;
            serviceOrder.setServiceOrderJoinCollection1(serviceOrderJoinCollection1New);
            serviceOrder = em.merge(serviceOrder);
            if (lineIdOld != null && !lineIdOld.equals(lineIdNew)) {
                lineIdOld.getServiceOrderCollection().remove(serviceOrder);
                lineIdOld = em.merge(lineIdOld);
            }
            if (lineIdNew != null && !lineIdNew.equals(lineIdOld)) {
                lineIdNew.getServiceOrderCollection().add(serviceOrder);
                lineIdNew = em.merge(lineIdNew);
            }
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getServiceOrderCollection().remove(serviceOrder);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getServiceOrderCollection().add(serviceOrder);
                userIdNew = em.merge(userIdNew);
            }
            if (currencyIdOld != null && !currencyIdOld.equals(currencyIdNew)) {
                currencyIdOld.getServiceOrderCollection().remove(serviceOrder);
                currencyIdOld = em.merge(currencyIdOld);
            }
            if (currencyIdNew != null && !currencyIdNew.equals(currencyIdOld)) {
                currencyIdNew.getServiceOrderCollection().add(serviceOrder);
                currencyIdNew = em.merge(currencyIdNew);
            }
            if (itineraryIdOld != null && !itineraryIdOld.equals(itineraryIdNew)) {
                itineraryIdOld.getServiceOrderCollection().remove(serviceOrder);
                itineraryIdOld = em.merge(itineraryIdOld);
            }
            if (itineraryIdNew != null && !itineraryIdNew.equals(itineraryIdOld)) {
                itineraryIdNew.getServiceOrderCollection().add(serviceOrder);
                itineraryIdNew = em.merge(itineraryIdNew);
            }
            if (serviceOrderStatusIdOld != null && !serviceOrderStatusIdOld.equals(serviceOrderStatusIdNew)) {
                serviceOrderStatusIdOld.getServiceOrderCollection().remove(serviceOrder);
                serviceOrderStatusIdOld = em.merge(serviceOrderStatusIdOld);
            }
            if (serviceOrderStatusIdNew != null && !serviceOrderStatusIdNew.equals(serviceOrderStatusIdOld)) {
                serviceOrderStatusIdNew.getServiceOrderCollection().add(serviceOrder);
                serviceOrderStatusIdNew = em.merge(serviceOrderStatusIdNew);
            }
            if (shipOwnerIdOld != null && !shipOwnerIdOld.equals(shipOwnerIdNew)) {
                shipOwnerIdOld.getServiceOrderCollection().remove(serviceOrder);
                shipOwnerIdOld = em.merge(shipOwnerIdOld);
            }
            if (shipOwnerIdNew != null && !shipOwnerIdNew.equals(shipOwnerIdOld)) {
                shipOwnerIdNew.getServiceOrderCollection().add(serviceOrder);
                shipOwnerIdNew = em.merge(shipOwnerIdNew);
            }
            for (ServiceOrderService serviceOrderServiceCollectionNewServiceOrderService : serviceOrderServiceCollectionNew) {
                if (!serviceOrderServiceCollectionOld.contains(serviceOrderServiceCollectionNewServiceOrderService)) {
                    ServiceOrder oldServiceOrderIdOfServiceOrderServiceCollectionNewServiceOrderService = serviceOrderServiceCollectionNewServiceOrderService.getServiceOrderId();
                    serviceOrderServiceCollectionNewServiceOrderService.setServiceOrderId(serviceOrder);
                    serviceOrderServiceCollectionNewServiceOrderService = em.merge(serviceOrderServiceCollectionNewServiceOrderService);
                    if (oldServiceOrderIdOfServiceOrderServiceCollectionNewServiceOrderService != null && !oldServiceOrderIdOfServiceOrderServiceCollectionNewServiceOrderService.equals(serviceOrder)) {
                        oldServiceOrderIdOfServiceOrderServiceCollectionNewServiceOrderService.getServiceOrderServiceCollection().remove(serviceOrderServiceCollectionNewServiceOrderService);
                        oldServiceOrderIdOfServiceOrderServiceCollectionNewServiceOrderService = em.merge(oldServiceOrderIdOfServiceOrderServiceCollectionNewServiceOrderService);
                    }
                }
            }
            for (ReferenceShipOwner referenceShipOwnerCollectionNewReferenceShipOwner : referenceShipOwnerCollectionNew) {
                if (!referenceShipOwnerCollectionOld.contains(referenceShipOwnerCollectionNewReferenceShipOwner)) {
                    ServiceOrder oldServiceOrderIdOfReferenceShipOwnerCollectionNewReferenceShipOwner = referenceShipOwnerCollectionNewReferenceShipOwner.getServiceOrderId();
                    referenceShipOwnerCollectionNewReferenceShipOwner.setServiceOrderId(serviceOrder);
                    referenceShipOwnerCollectionNewReferenceShipOwner = em.merge(referenceShipOwnerCollectionNewReferenceShipOwner);
                    if (oldServiceOrderIdOfReferenceShipOwnerCollectionNewReferenceShipOwner != null && !oldServiceOrderIdOfReferenceShipOwnerCollectionNewReferenceShipOwner.equals(serviceOrder)) {
                        oldServiceOrderIdOfReferenceShipOwnerCollectionNewReferenceShipOwner.getReferenceShipOwnerCollection().remove(referenceShipOwnerCollectionNewReferenceShipOwner);
                        oldServiceOrderIdOfReferenceShipOwnerCollectionNewReferenceShipOwner = em.merge(oldServiceOrderIdOfReferenceShipOwnerCollectionNewReferenceShipOwner);
                    }
                }
            }
            for (ServiceOrderFile serviceOrderFileCollectionNewServiceOrderFile : serviceOrderFileCollectionNew) {
                if (!serviceOrderFileCollectionOld.contains(serviceOrderFileCollectionNewServiceOrderFile)) {
                    ServiceOrder oldServiceOrderIdOfServiceOrderFileCollectionNewServiceOrderFile = serviceOrderFileCollectionNewServiceOrderFile.getServiceOrderId();
                    serviceOrderFileCollectionNewServiceOrderFile.setServiceOrderId(serviceOrder);
                    serviceOrderFileCollectionNewServiceOrderFile = em.merge(serviceOrderFileCollectionNewServiceOrderFile);
                    if (oldServiceOrderIdOfServiceOrderFileCollectionNewServiceOrderFile != null && !oldServiceOrderIdOfServiceOrderFileCollectionNewServiceOrderFile.equals(serviceOrder)) {
                        oldServiceOrderIdOfServiceOrderFileCollectionNewServiceOrderFile.getServiceOrderFileCollection().remove(serviceOrderFileCollectionNewServiceOrderFile);
                        oldServiceOrderIdOfServiceOrderFileCollectionNewServiceOrderFile = em.merge(oldServiceOrderIdOfServiceOrderFileCollectionNewServiceOrderFile);
                    }
                }
            }
            for (ActingRoles actingRolesCollectionNewActingRoles : actingRolesCollectionNew) {
                if (!actingRolesCollectionOld.contains(actingRolesCollectionNewActingRoles)) {
                    ServiceOrder oldServiceOrderIdOfActingRolesCollectionNewActingRoles = actingRolesCollectionNewActingRoles.getServiceOrderId();
                    actingRolesCollectionNewActingRoles.setServiceOrderId(serviceOrder);
                    actingRolesCollectionNewActingRoles = em.merge(actingRolesCollectionNewActingRoles);
                    if (oldServiceOrderIdOfActingRolesCollectionNewActingRoles != null && !oldServiceOrderIdOfActingRolesCollectionNewActingRoles.equals(serviceOrder)) {
                        oldServiceOrderIdOfActingRolesCollectionNewActingRoles.getActingRolesCollection().remove(actingRolesCollectionNewActingRoles);
                        oldServiceOrderIdOfActingRolesCollectionNewActingRoles = em.merge(oldServiceOrderIdOfActingRolesCollectionNewActingRoles);
                    }
                }
            }
            for (ServiceOrderProductType serviceOrderProductTypeCollectionNewServiceOrderProductType : serviceOrderProductTypeCollectionNew) {
                if (!serviceOrderProductTypeCollectionOld.contains(serviceOrderProductTypeCollectionNewServiceOrderProductType)) {
                    ServiceOrder oldServiceOrderOfServiceOrderProductTypeCollectionNewServiceOrderProductType = serviceOrderProductTypeCollectionNewServiceOrderProductType.getServiceOrder();
                    serviceOrderProductTypeCollectionNewServiceOrderProductType.setServiceOrder(serviceOrder);
                    serviceOrderProductTypeCollectionNewServiceOrderProductType = em.merge(serviceOrderProductTypeCollectionNewServiceOrderProductType);
                    if (oldServiceOrderOfServiceOrderProductTypeCollectionNewServiceOrderProductType != null && !oldServiceOrderOfServiceOrderProductTypeCollectionNewServiceOrderProductType.equals(serviceOrder)) {
                        oldServiceOrderOfServiceOrderProductTypeCollectionNewServiceOrderProductType.getServiceOrderProductTypeCollection().remove(serviceOrderProductTypeCollectionNewServiceOrderProductType);
                        oldServiceOrderOfServiceOrderProductTypeCollectionNewServiceOrderProductType = em.merge(oldServiceOrderOfServiceOrderProductTypeCollectionNewServiceOrderProductType);
                    }
                }
            }
            for (ServiceOrderJoin serviceOrderJoinCollectionOldServiceOrderJoin : serviceOrderJoinCollectionOld) {
                if (!serviceOrderJoinCollectionNew.contains(serviceOrderJoinCollectionOldServiceOrderJoin)) {
                    serviceOrderJoinCollectionOldServiceOrderJoin.setServiceOrderIdFather(null);
                    serviceOrderJoinCollectionOldServiceOrderJoin = em.merge(serviceOrderJoinCollectionOldServiceOrderJoin);
                }
            }
            for (ServiceOrderJoin serviceOrderJoinCollectionNewServiceOrderJoin : serviceOrderJoinCollectionNew) {
                if (!serviceOrderJoinCollectionOld.contains(serviceOrderJoinCollectionNewServiceOrderJoin)) {
                    ServiceOrder oldServiceOrderIdFatherOfServiceOrderJoinCollectionNewServiceOrderJoin = serviceOrderJoinCollectionNewServiceOrderJoin.getServiceOrderIdFather();
                    serviceOrderJoinCollectionNewServiceOrderJoin.setServiceOrderIdFather(serviceOrder);
                    serviceOrderJoinCollectionNewServiceOrderJoin = em.merge(serviceOrderJoinCollectionNewServiceOrderJoin);
                    if (oldServiceOrderIdFatherOfServiceOrderJoinCollectionNewServiceOrderJoin != null && !oldServiceOrderIdFatherOfServiceOrderJoinCollectionNewServiceOrderJoin.equals(serviceOrder)) {
                        oldServiceOrderIdFatherOfServiceOrderJoinCollectionNewServiceOrderJoin.getServiceOrderJoinCollection().remove(serviceOrderJoinCollectionNewServiceOrderJoin);
                        oldServiceOrderIdFatherOfServiceOrderJoinCollectionNewServiceOrderJoin = em.merge(oldServiceOrderIdFatherOfServiceOrderJoinCollectionNewServiceOrderJoin);
                    }
                }
            }
            for (ServiceOrderJoin serviceOrderJoinCollection1OldServiceOrderJoin : serviceOrderJoinCollection1Old) {
                if (!serviceOrderJoinCollection1New.contains(serviceOrderJoinCollection1OldServiceOrderJoin)) {
                    serviceOrderJoinCollection1OldServiceOrderJoin.setServiceOrderIdSon(null);
                    serviceOrderJoinCollection1OldServiceOrderJoin = em.merge(serviceOrderJoinCollection1OldServiceOrderJoin);
                }
            }
            for (ServiceOrderJoin serviceOrderJoinCollection1NewServiceOrderJoin : serviceOrderJoinCollection1New) {
                if (!serviceOrderJoinCollection1Old.contains(serviceOrderJoinCollection1NewServiceOrderJoin)) {
                    ServiceOrder oldServiceOrderIdSonOfServiceOrderJoinCollection1NewServiceOrderJoin = serviceOrderJoinCollection1NewServiceOrderJoin.getServiceOrderIdSon();
                    serviceOrderJoinCollection1NewServiceOrderJoin.setServiceOrderIdSon(serviceOrder);
                    serviceOrderJoinCollection1NewServiceOrderJoin = em.merge(serviceOrderJoinCollection1NewServiceOrderJoin);
                    if (oldServiceOrderIdSonOfServiceOrderJoinCollection1NewServiceOrderJoin != null && !oldServiceOrderIdSonOfServiceOrderJoinCollection1NewServiceOrderJoin.equals(serviceOrder)) {
                        oldServiceOrderIdSonOfServiceOrderJoinCollection1NewServiceOrderJoin.getServiceOrderJoinCollection1().remove(serviceOrderJoinCollection1NewServiceOrderJoin);
                        oldServiceOrderIdSonOfServiceOrderJoinCollection1NewServiceOrderJoin = em.merge(oldServiceOrderIdSonOfServiceOrderJoinCollection1NewServiceOrderJoin);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = serviceOrder.getServiceOrderId();
                if (findServiceOrder(id) == null) {
                    throw new NonexistentEntityException("The serviceOrder with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiceOrder serviceOrder;
            try {
                serviceOrder = em.getReference(ServiceOrder.class, id);
                serviceOrder.getServiceOrderId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The serviceOrder with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ServiceOrderService> serviceOrderServiceCollectionOrphanCheck = serviceOrder.getServiceOrderServiceCollection();
            for (ServiceOrderService serviceOrderServiceCollectionOrphanCheckServiceOrderService : serviceOrderServiceCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ServiceOrder (" + serviceOrder + ") cannot be destroyed since the ServiceOrderService " + serviceOrderServiceCollectionOrphanCheckServiceOrderService + " in its serviceOrderServiceCollection field has a non-nullable serviceOrderId field.");
            }
            Collection<ReferenceShipOwner> referenceShipOwnerCollectionOrphanCheck = serviceOrder.getReferenceShipOwnerCollection();
            for (ReferenceShipOwner referenceShipOwnerCollectionOrphanCheckReferenceShipOwner : referenceShipOwnerCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ServiceOrder (" + serviceOrder + ") cannot be destroyed since the ReferenceShipOwner " + referenceShipOwnerCollectionOrphanCheckReferenceShipOwner + " in its referenceShipOwnerCollection field has a non-nullable serviceOrderId field.");
            }
            Collection<ServiceOrderFile> serviceOrderFileCollectionOrphanCheck = serviceOrder.getServiceOrderFileCollection();
            for (ServiceOrderFile serviceOrderFileCollectionOrphanCheckServiceOrderFile : serviceOrderFileCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ServiceOrder (" + serviceOrder + ") cannot be destroyed since the ServiceOrderFile " + serviceOrderFileCollectionOrphanCheckServiceOrderFile + " in its serviceOrderFileCollection field has a non-nullable serviceOrderId field.");
            }
            Collection<ActingRoles> actingRolesCollectionOrphanCheck = serviceOrder.getActingRolesCollection();
            for (ActingRoles actingRolesCollectionOrphanCheckActingRoles : actingRolesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ServiceOrder (" + serviceOrder + ") cannot be destroyed since the ActingRoles " + actingRolesCollectionOrphanCheckActingRoles + " in its actingRolesCollection field has a non-nullable serviceOrderId field.");
            }
            Collection<ServiceOrderProductType> serviceOrderProductTypeCollectionOrphanCheck = serviceOrder.getServiceOrderProductTypeCollection();
            for (ServiceOrderProductType serviceOrderProductTypeCollectionOrphanCheckServiceOrderProductType : serviceOrderProductTypeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ServiceOrder (" + serviceOrder + ") cannot be destroyed since the ServiceOrderProductType " + serviceOrderProductTypeCollectionOrphanCheckServiceOrderProductType + " in its serviceOrderProductTypeCollection field has a non-nullable serviceOrder field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Line lineId = serviceOrder.getLineId();
            if (lineId != null) {
                lineId.getServiceOrderCollection().remove(serviceOrder);
                lineId = em.merge(lineId);
            }
            User userId = serviceOrder.getUserId();
            if (userId != null) {
                userId.getServiceOrderCollection().remove(serviceOrder);
                userId = em.merge(userId);
            }
            Currency currencyId = serviceOrder.getCurrencyId();
            if (currencyId != null) {
                currencyId.getServiceOrderCollection().remove(serviceOrder);
                currencyId = em.merge(currencyId);
            }
            Itinerary itineraryId = serviceOrder.getItineraryId();
            if (itineraryId != null) {
                itineraryId.getServiceOrderCollection().remove(serviceOrder);
                itineraryId = em.merge(itineraryId);
            }
            ServiceOrderStatus serviceOrderStatusId = serviceOrder.getServiceOrderStatusId();
            if (serviceOrderStatusId != null) {
                serviceOrderStatusId.getServiceOrderCollection().remove(serviceOrder);
                serviceOrderStatusId = em.merge(serviceOrderStatusId);
            }
            ShipOwner shipOwnerId = serviceOrder.getShipOwnerId();
            if (shipOwnerId != null) {
                shipOwnerId.getServiceOrderCollection().remove(serviceOrder);
                shipOwnerId = em.merge(shipOwnerId);
            }
            Collection<ServiceOrderJoin> serviceOrderJoinCollection = serviceOrder.getServiceOrderJoinCollection();
            for (ServiceOrderJoin serviceOrderJoinCollectionServiceOrderJoin : serviceOrderJoinCollection) {
                serviceOrderJoinCollectionServiceOrderJoin.setServiceOrderIdFather(null);
                serviceOrderJoinCollectionServiceOrderJoin = em.merge(serviceOrderJoinCollectionServiceOrderJoin);
            }
            Collection<ServiceOrderJoin> serviceOrderJoinCollection1 = serviceOrder.getServiceOrderJoinCollection1();
            for (ServiceOrderJoin serviceOrderJoinCollection1ServiceOrderJoin : serviceOrderJoinCollection1) {
                serviceOrderJoinCollection1ServiceOrderJoin.setServiceOrderIdSon(null);
                serviceOrderJoinCollection1ServiceOrderJoin = em.merge(serviceOrderJoinCollection1ServiceOrderJoin);
            }
            em.remove(serviceOrder);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServiceOrder> findServiceOrderEntities() {
        return findServiceOrderEntities(true, -1, -1);
    }

    public List<ServiceOrder> findServiceOrderEntities(int maxResults, int firstResult) {
        return findServiceOrderEntities(false, maxResults, firstResult);
    }

    private List<ServiceOrder> findServiceOrderEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServiceOrder.class));
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

    public ServiceOrder findServiceOrder(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServiceOrder.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiceOrderCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServiceOrder> rt = cq.from(ServiceOrder.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
