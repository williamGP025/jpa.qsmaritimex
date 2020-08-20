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
import mx.tab.wgp.qsmaritimex.entidades.usuario.UserCargoType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.IllegalOrphanException;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.usuario.UserEnterprise;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrderService;
import mx.tab.wgp.qsmaritimex.entidades.usuario.UserRol;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrder;
import mx.tab.wgp.qsmaritimex.entidades.usuario.User;
import mx.tab.wgp.qsmaritimex.entidades.usuario.UserLine;

/**
 *
 * @author William
 */
public class UserJpaController implements Serializable {

    public UserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) {
        if (user.getUserCargoTypeCollection() == null) {
            user.setUserCargoTypeCollection(new ArrayList<UserCargoType>());
        }
        if (user.getUserEnterpriseCollection() == null) {
            user.setUserEnterpriseCollection(new ArrayList<UserEnterprise>());
        }
        if (user.getServiceOrderServiceCollection() == null) {
            user.setServiceOrderServiceCollection(new ArrayList<ServiceOrderService>());
        }
        if (user.getUserRolCollection() == null) {
            user.setUserRolCollection(new ArrayList<UserRol>());
        }
        if (user.getServiceOrderCollection() == null) {
            user.setServiceOrderCollection(new ArrayList<ServiceOrder>());
        }
        if (user.getUserLineCollection() == null) {
            user.setUserLineCollection(new ArrayList<UserLine>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<UserCargoType> attachedUserCargoTypeCollection = new ArrayList<UserCargoType>();
            for (UserCargoType userCargoTypeCollectionUserCargoTypeToAttach : user.getUserCargoTypeCollection()) {
                userCargoTypeCollectionUserCargoTypeToAttach = em.getReference(userCargoTypeCollectionUserCargoTypeToAttach.getClass(), userCargoTypeCollectionUserCargoTypeToAttach.getUserCargoTypeId());
                attachedUserCargoTypeCollection.add(userCargoTypeCollectionUserCargoTypeToAttach);
            }
            user.setUserCargoTypeCollection(attachedUserCargoTypeCollection);
            Collection<UserEnterprise> attachedUserEnterpriseCollection = new ArrayList<UserEnterprise>();
            for (UserEnterprise userEnterpriseCollectionUserEnterpriseToAttach : user.getUserEnterpriseCollection()) {
                userEnterpriseCollectionUserEnterpriseToAttach = em.getReference(userEnterpriseCollectionUserEnterpriseToAttach.getClass(), userEnterpriseCollectionUserEnterpriseToAttach.getUserEnterpriseId());
                attachedUserEnterpriseCollection.add(userEnterpriseCollectionUserEnterpriseToAttach);
            }
            user.setUserEnterpriseCollection(attachedUserEnterpriseCollection);
            Collection<ServiceOrderService> attachedServiceOrderServiceCollection = new ArrayList<ServiceOrderService>();
            for (ServiceOrderService serviceOrderServiceCollectionServiceOrderServiceToAttach : user.getServiceOrderServiceCollection()) {
                serviceOrderServiceCollectionServiceOrderServiceToAttach = em.getReference(serviceOrderServiceCollectionServiceOrderServiceToAttach.getClass(), serviceOrderServiceCollectionServiceOrderServiceToAttach.getServiceOrderServiceId());
                attachedServiceOrderServiceCollection.add(serviceOrderServiceCollectionServiceOrderServiceToAttach);
            }
            user.setServiceOrderServiceCollection(attachedServiceOrderServiceCollection);
            Collection<UserRol> attachedUserRolCollection = new ArrayList<UserRol>();
            for (UserRol userRolCollectionUserRolToAttach : user.getUserRolCollection()) {
                userRolCollectionUserRolToAttach = em.getReference(userRolCollectionUserRolToAttach.getClass(), userRolCollectionUserRolToAttach.getUserRolId());
                attachedUserRolCollection.add(userRolCollectionUserRolToAttach);
            }
            user.setUserRolCollection(attachedUserRolCollection);
            Collection<ServiceOrder> attachedServiceOrderCollection = new ArrayList<ServiceOrder>();
            for (ServiceOrder serviceOrderCollectionServiceOrderToAttach : user.getServiceOrderCollection()) {
                serviceOrderCollectionServiceOrderToAttach = em.getReference(serviceOrderCollectionServiceOrderToAttach.getClass(), serviceOrderCollectionServiceOrderToAttach.getServiceOrderId());
                attachedServiceOrderCollection.add(serviceOrderCollectionServiceOrderToAttach);
            }
            user.setServiceOrderCollection(attachedServiceOrderCollection);
            Collection<UserLine> attachedUserLineCollection = new ArrayList<UserLine>();
            for (UserLine userLineCollectionUserLineToAttach : user.getUserLineCollection()) {
                userLineCollectionUserLineToAttach = em.getReference(userLineCollectionUserLineToAttach.getClass(), userLineCollectionUserLineToAttach.getUserLineId());
                attachedUserLineCollection.add(userLineCollectionUserLineToAttach);
            }
            user.setUserLineCollection(attachedUserLineCollection);
            em.persist(user);
            for (UserCargoType userCargoTypeCollectionUserCargoType : user.getUserCargoTypeCollection()) {
                User oldUserIdOfUserCargoTypeCollectionUserCargoType = userCargoTypeCollectionUserCargoType.getUserId();
                userCargoTypeCollectionUserCargoType.setUserId(user);
                userCargoTypeCollectionUserCargoType = em.merge(userCargoTypeCollectionUserCargoType);
                if (oldUserIdOfUserCargoTypeCollectionUserCargoType != null) {
                    oldUserIdOfUserCargoTypeCollectionUserCargoType.getUserCargoTypeCollection().remove(userCargoTypeCollectionUserCargoType);
                    oldUserIdOfUserCargoTypeCollectionUserCargoType = em.merge(oldUserIdOfUserCargoTypeCollectionUserCargoType);
                }
            }
            for (UserEnterprise userEnterpriseCollectionUserEnterprise : user.getUserEnterpriseCollection()) {
                User oldUserIdOfUserEnterpriseCollectionUserEnterprise = userEnterpriseCollectionUserEnterprise.getUserId();
                userEnterpriseCollectionUserEnterprise.setUserId(user);
                userEnterpriseCollectionUserEnterprise = em.merge(userEnterpriseCollectionUserEnterprise);
                if (oldUserIdOfUserEnterpriseCollectionUserEnterprise != null) {
                    oldUserIdOfUserEnterpriseCollectionUserEnterprise.getUserEnterpriseCollection().remove(userEnterpriseCollectionUserEnterprise);
                    oldUserIdOfUserEnterpriseCollectionUserEnterprise = em.merge(oldUserIdOfUserEnterpriseCollectionUserEnterprise);
                }
            }
            for (ServiceOrderService serviceOrderServiceCollectionServiceOrderService : user.getServiceOrderServiceCollection()) {
                User oldUpdateUserIdOfServiceOrderServiceCollectionServiceOrderService = serviceOrderServiceCollectionServiceOrderService.getUpdateUserId();
                serviceOrderServiceCollectionServiceOrderService.setUpdateUserId(user);
                serviceOrderServiceCollectionServiceOrderService = em.merge(serviceOrderServiceCollectionServiceOrderService);
                if (oldUpdateUserIdOfServiceOrderServiceCollectionServiceOrderService != null) {
                    oldUpdateUserIdOfServiceOrderServiceCollectionServiceOrderService.getServiceOrderServiceCollection().remove(serviceOrderServiceCollectionServiceOrderService);
                    oldUpdateUserIdOfServiceOrderServiceCollectionServiceOrderService = em.merge(oldUpdateUserIdOfServiceOrderServiceCollectionServiceOrderService);
                }
            }
            for (UserRol userRolCollectionUserRol : user.getUserRolCollection()) {
                User oldUserIdOfUserRolCollectionUserRol = userRolCollectionUserRol.getUserId();
                userRolCollectionUserRol.setUserId(user);
                userRolCollectionUserRol = em.merge(userRolCollectionUserRol);
                if (oldUserIdOfUserRolCollectionUserRol != null) {
                    oldUserIdOfUserRolCollectionUserRol.getUserRolCollection().remove(userRolCollectionUserRol);
                    oldUserIdOfUserRolCollectionUserRol = em.merge(oldUserIdOfUserRolCollectionUserRol);
                }
            }
            for (ServiceOrder serviceOrderCollectionServiceOrder : user.getServiceOrderCollection()) {
                User oldUserIdOfServiceOrderCollectionServiceOrder = serviceOrderCollectionServiceOrder.getUserId();
                serviceOrderCollectionServiceOrder.setUserId(user);
                serviceOrderCollectionServiceOrder = em.merge(serviceOrderCollectionServiceOrder);
                if (oldUserIdOfServiceOrderCollectionServiceOrder != null) {
                    oldUserIdOfServiceOrderCollectionServiceOrder.getServiceOrderCollection().remove(serviceOrderCollectionServiceOrder);
                    oldUserIdOfServiceOrderCollectionServiceOrder = em.merge(oldUserIdOfServiceOrderCollectionServiceOrder);
                }
            }
            for (UserLine userLineCollectionUserLine : user.getUserLineCollection()) {
                User oldUserIdOfUserLineCollectionUserLine = userLineCollectionUserLine.getUserId();
                userLineCollectionUserLine.setUserId(user);
                userLineCollectionUserLine = em.merge(userLineCollectionUserLine);
                if (oldUserIdOfUserLineCollectionUserLine != null) {
                    oldUserIdOfUserLineCollectionUserLine.getUserLineCollection().remove(userLineCollectionUserLine);
                    oldUserIdOfUserLineCollectionUserLine = em.merge(oldUserIdOfUserLineCollectionUserLine);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(User user) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User persistentUser = em.find(User.class, user.getUserId());
            Collection<UserCargoType> userCargoTypeCollectionOld = persistentUser.getUserCargoTypeCollection();
            Collection<UserCargoType> userCargoTypeCollectionNew = user.getUserCargoTypeCollection();
            Collection<UserEnterprise> userEnterpriseCollectionOld = persistentUser.getUserEnterpriseCollection();
            Collection<UserEnterprise> userEnterpriseCollectionNew = user.getUserEnterpriseCollection();
            Collection<ServiceOrderService> serviceOrderServiceCollectionOld = persistentUser.getServiceOrderServiceCollection();
            Collection<ServiceOrderService> serviceOrderServiceCollectionNew = user.getServiceOrderServiceCollection();
            Collection<UserRol> userRolCollectionOld = persistentUser.getUserRolCollection();
            Collection<UserRol> userRolCollectionNew = user.getUserRolCollection();
            Collection<ServiceOrder> serviceOrderCollectionOld = persistentUser.getServiceOrderCollection();
            Collection<ServiceOrder> serviceOrderCollectionNew = user.getServiceOrderCollection();
            Collection<UserLine> userLineCollectionOld = persistentUser.getUserLineCollection();
            Collection<UserLine> userLineCollectionNew = user.getUserLineCollection();
            List<String> illegalOrphanMessages = null;
            for (UserCargoType userCargoTypeCollectionOldUserCargoType : userCargoTypeCollectionOld) {
                if (!userCargoTypeCollectionNew.contains(userCargoTypeCollectionOldUserCargoType)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UserCargoType " + userCargoTypeCollectionOldUserCargoType + " since its userId field is not nullable.");
                }
            }
            for (UserEnterprise userEnterpriseCollectionOldUserEnterprise : userEnterpriseCollectionOld) {
                if (!userEnterpriseCollectionNew.contains(userEnterpriseCollectionOldUserEnterprise)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UserEnterprise " + userEnterpriseCollectionOldUserEnterprise + " since its userId field is not nullable.");
                }
            }
            for (UserRol userRolCollectionOldUserRol : userRolCollectionOld) {
                if (!userRolCollectionNew.contains(userRolCollectionOldUserRol)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UserRol " + userRolCollectionOldUserRol + " since its userId field is not nullable.");
                }
            }
            for (ServiceOrder serviceOrderCollectionOldServiceOrder : serviceOrderCollectionOld) {
                if (!serviceOrderCollectionNew.contains(serviceOrderCollectionOldServiceOrder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ServiceOrder " + serviceOrderCollectionOldServiceOrder + " since its userId field is not nullable.");
                }
            }
            for (UserLine userLineCollectionOldUserLine : userLineCollectionOld) {
                if (!userLineCollectionNew.contains(userLineCollectionOldUserLine)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UserLine " + userLineCollectionOldUserLine + " since its userId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<UserCargoType> attachedUserCargoTypeCollectionNew = new ArrayList<UserCargoType>();
            for (UserCargoType userCargoTypeCollectionNewUserCargoTypeToAttach : userCargoTypeCollectionNew) {
                userCargoTypeCollectionNewUserCargoTypeToAttach = em.getReference(userCargoTypeCollectionNewUserCargoTypeToAttach.getClass(), userCargoTypeCollectionNewUserCargoTypeToAttach.getUserCargoTypeId());
                attachedUserCargoTypeCollectionNew.add(userCargoTypeCollectionNewUserCargoTypeToAttach);
            }
            userCargoTypeCollectionNew = attachedUserCargoTypeCollectionNew;
            user.setUserCargoTypeCollection(userCargoTypeCollectionNew);
            Collection<UserEnterprise> attachedUserEnterpriseCollectionNew = new ArrayList<UserEnterprise>();
            for (UserEnterprise userEnterpriseCollectionNewUserEnterpriseToAttach : userEnterpriseCollectionNew) {
                userEnterpriseCollectionNewUserEnterpriseToAttach = em.getReference(userEnterpriseCollectionNewUserEnterpriseToAttach.getClass(), userEnterpriseCollectionNewUserEnterpriseToAttach.getUserEnterpriseId());
                attachedUserEnterpriseCollectionNew.add(userEnterpriseCollectionNewUserEnterpriseToAttach);
            }
            userEnterpriseCollectionNew = attachedUserEnterpriseCollectionNew;
            user.setUserEnterpriseCollection(userEnterpriseCollectionNew);
            Collection<ServiceOrderService> attachedServiceOrderServiceCollectionNew = new ArrayList<ServiceOrderService>();
            for (ServiceOrderService serviceOrderServiceCollectionNewServiceOrderServiceToAttach : serviceOrderServiceCollectionNew) {
                serviceOrderServiceCollectionNewServiceOrderServiceToAttach = em.getReference(serviceOrderServiceCollectionNewServiceOrderServiceToAttach.getClass(), serviceOrderServiceCollectionNewServiceOrderServiceToAttach.getServiceOrderServiceId());
                attachedServiceOrderServiceCollectionNew.add(serviceOrderServiceCollectionNewServiceOrderServiceToAttach);
            }
            serviceOrderServiceCollectionNew = attachedServiceOrderServiceCollectionNew;
            user.setServiceOrderServiceCollection(serviceOrderServiceCollectionNew);
            Collection<UserRol> attachedUserRolCollectionNew = new ArrayList<UserRol>();
            for (UserRol userRolCollectionNewUserRolToAttach : userRolCollectionNew) {
                userRolCollectionNewUserRolToAttach = em.getReference(userRolCollectionNewUserRolToAttach.getClass(), userRolCollectionNewUserRolToAttach.getUserRolId());
                attachedUserRolCollectionNew.add(userRolCollectionNewUserRolToAttach);
            }
            userRolCollectionNew = attachedUserRolCollectionNew;
            user.setUserRolCollection(userRolCollectionNew);
            Collection<ServiceOrder> attachedServiceOrderCollectionNew = new ArrayList<ServiceOrder>();
            for (ServiceOrder serviceOrderCollectionNewServiceOrderToAttach : serviceOrderCollectionNew) {
                serviceOrderCollectionNewServiceOrderToAttach = em.getReference(serviceOrderCollectionNewServiceOrderToAttach.getClass(), serviceOrderCollectionNewServiceOrderToAttach.getServiceOrderId());
                attachedServiceOrderCollectionNew.add(serviceOrderCollectionNewServiceOrderToAttach);
            }
            serviceOrderCollectionNew = attachedServiceOrderCollectionNew;
            user.setServiceOrderCollection(serviceOrderCollectionNew);
            Collection<UserLine> attachedUserLineCollectionNew = new ArrayList<UserLine>();
            for (UserLine userLineCollectionNewUserLineToAttach : userLineCollectionNew) {
                userLineCollectionNewUserLineToAttach = em.getReference(userLineCollectionNewUserLineToAttach.getClass(), userLineCollectionNewUserLineToAttach.getUserLineId());
                attachedUserLineCollectionNew.add(userLineCollectionNewUserLineToAttach);
            }
            userLineCollectionNew = attachedUserLineCollectionNew;
            user.setUserLineCollection(userLineCollectionNew);
            user = em.merge(user);
            for (UserCargoType userCargoTypeCollectionNewUserCargoType : userCargoTypeCollectionNew) {
                if (!userCargoTypeCollectionOld.contains(userCargoTypeCollectionNewUserCargoType)) {
                    User oldUserIdOfUserCargoTypeCollectionNewUserCargoType = userCargoTypeCollectionNewUserCargoType.getUserId();
                    userCargoTypeCollectionNewUserCargoType.setUserId(user);
                    userCargoTypeCollectionNewUserCargoType = em.merge(userCargoTypeCollectionNewUserCargoType);
                    if (oldUserIdOfUserCargoTypeCollectionNewUserCargoType != null && !oldUserIdOfUserCargoTypeCollectionNewUserCargoType.equals(user)) {
                        oldUserIdOfUserCargoTypeCollectionNewUserCargoType.getUserCargoTypeCollection().remove(userCargoTypeCollectionNewUserCargoType);
                        oldUserIdOfUserCargoTypeCollectionNewUserCargoType = em.merge(oldUserIdOfUserCargoTypeCollectionNewUserCargoType);
                    }
                }
            }
            for (UserEnterprise userEnterpriseCollectionNewUserEnterprise : userEnterpriseCollectionNew) {
                if (!userEnterpriseCollectionOld.contains(userEnterpriseCollectionNewUserEnterprise)) {
                    User oldUserIdOfUserEnterpriseCollectionNewUserEnterprise = userEnterpriseCollectionNewUserEnterprise.getUserId();
                    userEnterpriseCollectionNewUserEnterprise.setUserId(user);
                    userEnterpriseCollectionNewUserEnterprise = em.merge(userEnterpriseCollectionNewUserEnterprise);
                    if (oldUserIdOfUserEnterpriseCollectionNewUserEnterprise != null && !oldUserIdOfUserEnterpriseCollectionNewUserEnterprise.equals(user)) {
                        oldUserIdOfUserEnterpriseCollectionNewUserEnterprise.getUserEnterpriseCollection().remove(userEnterpriseCollectionNewUserEnterprise);
                        oldUserIdOfUserEnterpriseCollectionNewUserEnterprise = em.merge(oldUserIdOfUserEnterpriseCollectionNewUserEnterprise);
                    }
                }
            }
            for (ServiceOrderService serviceOrderServiceCollectionOldServiceOrderService : serviceOrderServiceCollectionOld) {
                if (!serviceOrderServiceCollectionNew.contains(serviceOrderServiceCollectionOldServiceOrderService)) {
                    serviceOrderServiceCollectionOldServiceOrderService.setUpdateUserId(null);
                    serviceOrderServiceCollectionOldServiceOrderService = em.merge(serviceOrderServiceCollectionOldServiceOrderService);
                }
            }
            for (ServiceOrderService serviceOrderServiceCollectionNewServiceOrderService : serviceOrderServiceCollectionNew) {
                if (!serviceOrderServiceCollectionOld.contains(serviceOrderServiceCollectionNewServiceOrderService)) {
                    User oldUpdateUserIdOfServiceOrderServiceCollectionNewServiceOrderService = serviceOrderServiceCollectionNewServiceOrderService.getUpdateUserId();
                    serviceOrderServiceCollectionNewServiceOrderService.setUpdateUserId(user);
                    serviceOrderServiceCollectionNewServiceOrderService = em.merge(serviceOrderServiceCollectionNewServiceOrderService);
                    if (oldUpdateUserIdOfServiceOrderServiceCollectionNewServiceOrderService != null && !oldUpdateUserIdOfServiceOrderServiceCollectionNewServiceOrderService.equals(user)) {
                        oldUpdateUserIdOfServiceOrderServiceCollectionNewServiceOrderService.getServiceOrderServiceCollection().remove(serviceOrderServiceCollectionNewServiceOrderService);
                        oldUpdateUserIdOfServiceOrderServiceCollectionNewServiceOrderService = em.merge(oldUpdateUserIdOfServiceOrderServiceCollectionNewServiceOrderService);
                    }
                }
            }
            for (UserRol userRolCollectionNewUserRol : userRolCollectionNew) {
                if (!userRolCollectionOld.contains(userRolCollectionNewUserRol)) {
                    User oldUserIdOfUserRolCollectionNewUserRol = userRolCollectionNewUserRol.getUserId();
                    userRolCollectionNewUserRol.setUserId(user);
                    userRolCollectionNewUserRol = em.merge(userRolCollectionNewUserRol);
                    if (oldUserIdOfUserRolCollectionNewUserRol != null && !oldUserIdOfUserRolCollectionNewUserRol.equals(user)) {
                        oldUserIdOfUserRolCollectionNewUserRol.getUserRolCollection().remove(userRolCollectionNewUserRol);
                        oldUserIdOfUserRolCollectionNewUserRol = em.merge(oldUserIdOfUserRolCollectionNewUserRol);
                    }
                }
            }
            for (ServiceOrder serviceOrderCollectionNewServiceOrder : serviceOrderCollectionNew) {
                if (!serviceOrderCollectionOld.contains(serviceOrderCollectionNewServiceOrder)) {
                    User oldUserIdOfServiceOrderCollectionNewServiceOrder = serviceOrderCollectionNewServiceOrder.getUserId();
                    serviceOrderCollectionNewServiceOrder.setUserId(user);
                    serviceOrderCollectionNewServiceOrder = em.merge(serviceOrderCollectionNewServiceOrder);
                    if (oldUserIdOfServiceOrderCollectionNewServiceOrder != null && !oldUserIdOfServiceOrderCollectionNewServiceOrder.equals(user)) {
                        oldUserIdOfServiceOrderCollectionNewServiceOrder.getServiceOrderCollection().remove(serviceOrderCollectionNewServiceOrder);
                        oldUserIdOfServiceOrderCollectionNewServiceOrder = em.merge(oldUserIdOfServiceOrderCollectionNewServiceOrder);
                    }
                }
            }
            for (UserLine userLineCollectionNewUserLine : userLineCollectionNew) {
                if (!userLineCollectionOld.contains(userLineCollectionNewUserLine)) {
                    User oldUserIdOfUserLineCollectionNewUserLine = userLineCollectionNewUserLine.getUserId();
                    userLineCollectionNewUserLine.setUserId(user);
                    userLineCollectionNewUserLine = em.merge(userLineCollectionNewUserLine);
                    if (oldUserIdOfUserLineCollectionNewUserLine != null && !oldUserIdOfUserLineCollectionNewUserLine.equals(user)) {
                        oldUserIdOfUserLineCollectionNewUserLine.getUserLineCollection().remove(userLineCollectionNewUserLine);
                        oldUserIdOfUserLineCollectionNewUserLine = em.merge(oldUserIdOfUserLineCollectionNewUserLine);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = user.getUserId();
                if (findUser(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
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
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getUserId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<UserCargoType> userCargoTypeCollectionOrphanCheck = user.getUserCargoTypeCollection();
            for (UserCargoType userCargoTypeCollectionOrphanCheckUserCargoType : userCargoTypeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the UserCargoType " + userCargoTypeCollectionOrphanCheckUserCargoType + " in its userCargoTypeCollection field has a non-nullable userId field.");
            }
            Collection<UserEnterprise> userEnterpriseCollectionOrphanCheck = user.getUserEnterpriseCollection();
            for (UserEnterprise userEnterpriseCollectionOrphanCheckUserEnterprise : userEnterpriseCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the UserEnterprise " + userEnterpriseCollectionOrphanCheckUserEnterprise + " in its userEnterpriseCollection field has a non-nullable userId field.");
            }
            Collection<UserRol> userRolCollectionOrphanCheck = user.getUserRolCollection();
            for (UserRol userRolCollectionOrphanCheckUserRol : userRolCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the UserRol " + userRolCollectionOrphanCheckUserRol + " in its userRolCollection field has a non-nullable userId field.");
            }
            Collection<ServiceOrder> serviceOrderCollectionOrphanCheck = user.getServiceOrderCollection();
            for (ServiceOrder serviceOrderCollectionOrphanCheckServiceOrder : serviceOrderCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the ServiceOrder " + serviceOrderCollectionOrphanCheckServiceOrder + " in its serviceOrderCollection field has a non-nullable userId field.");
            }
            Collection<UserLine> userLineCollectionOrphanCheck = user.getUserLineCollection();
            for (UserLine userLineCollectionOrphanCheckUserLine : userLineCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the UserLine " + userLineCollectionOrphanCheckUserLine + " in its userLineCollection field has a non-nullable userId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<ServiceOrderService> serviceOrderServiceCollection = user.getServiceOrderServiceCollection();
            for (ServiceOrderService serviceOrderServiceCollectionServiceOrderService : serviceOrderServiceCollection) {
                serviceOrderServiceCollectionServiceOrderService.setUpdateUserId(null);
                serviceOrderServiceCollectionServiceOrderService = em.merge(serviceOrderServiceCollectionServiceOrderService);
            }
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
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

    public User findUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
