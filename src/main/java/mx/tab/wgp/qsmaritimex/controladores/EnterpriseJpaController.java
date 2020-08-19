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
import mx.tab.wgp.qsmaritimex.entidades.UserEnterprise;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.IllegalOrphanException;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.Enterprise;

/**
 *
 * @author WilliamGP025
 */
public class EnterpriseJpaController implements Serializable {

    public EnterpriseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Enterprise enterprise) {
        if (enterprise.getUserEnterpriseCollection() == null) {
            enterprise.setUserEnterpriseCollection(new ArrayList<UserEnterprise>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<UserEnterprise> attachedUserEnterpriseCollection = new ArrayList<UserEnterprise>();
            for (UserEnterprise userEnterpriseCollectionUserEnterpriseToAttach : enterprise.getUserEnterpriseCollection()) {
                userEnterpriseCollectionUserEnterpriseToAttach = em.getReference(userEnterpriseCollectionUserEnterpriseToAttach.getClass(), userEnterpriseCollectionUserEnterpriseToAttach.getUserEnterpriseId());
                attachedUserEnterpriseCollection.add(userEnterpriseCollectionUserEnterpriseToAttach);
            }
            enterprise.setUserEnterpriseCollection(attachedUserEnterpriseCollection);
            em.persist(enterprise);
            for (UserEnterprise userEnterpriseCollectionUserEnterprise : enterprise.getUserEnterpriseCollection()) {
                Enterprise oldEnterpriseIdOfUserEnterpriseCollectionUserEnterprise = userEnterpriseCollectionUserEnterprise.getEnterpriseId();
                userEnterpriseCollectionUserEnterprise.setEnterpriseId(enterprise);
                userEnterpriseCollectionUserEnterprise = em.merge(userEnterpriseCollectionUserEnterprise);
                if (oldEnterpriseIdOfUserEnterpriseCollectionUserEnterprise != null) {
                    oldEnterpriseIdOfUserEnterpriseCollectionUserEnterprise.getUserEnterpriseCollection().remove(userEnterpriseCollectionUserEnterprise);
                    oldEnterpriseIdOfUserEnterpriseCollectionUserEnterprise = em.merge(oldEnterpriseIdOfUserEnterpriseCollectionUserEnterprise);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Enterprise enterprise) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Enterprise persistentEnterprise = em.find(Enterprise.class, enterprise.getEnterpriseId());
            Collection<UserEnterprise> userEnterpriseCollectionOld = persistentEnterprise.getUserEnterpriseCollection();
            Collection<UserEnterprise> userEnterpriseCollectionNew = enterprise.getUserEnterpriseCollection();
            List<String> illegalOrphanMessages = null;
            for (UserEnterprise userEnterpriseCollectionOldUserEnterprise : userEnterpriseCollectionOld) {
                if (!userEnterpriseCollectionNew.contains(userEnterpriseCollectionOldUserEnterprise)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UserEnterprise " + userEnterpriseCollectionOldUserEnterprise + " since its enterpriseId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<UserEnterprise> attachedUserEnterpriseCollectionNew = new ArrayList<UserEnterprise>();
            for (UserEnterprise userEnterpriseCollectionNewUserEnterpriseToAttach : userEnterpriseCollectionNew) {
                userEnterpriseCollectionNewUserEnterpriseToAttach = em.getReference(userEnterpriseCollectionNewUserEnterpriseToAttach.getClass(), userEnterpriseCollectionNewUserEnterpriseToAttach.getUserEnterpriseId());
                attachedUserEnterpriseCollectionNew.add(userEnterpriseCollectionNewUserEnterpriseToAttach);
            }
            userEnterpriseCollectionNew = attachedUserEnterpriseCollectionNew;
            enterprise.setUserEnterpriseCollection(userEnterpriseCollectionNew);
            enterprise = em.merge(enterprise);
            for (UserEnterprise userEnterpriseCollectionNewUserEnterprise : userEnterpriseCollectionNew) {
                if (!userEnterpriseCollectionOld.contains(userEnterpriseCollectionNewUserEnterprise)) {
                    Enterprise oldEnterpriseIdOfUserEnterpriseCollectionNewUserEnterprise = userEnterpriseCollectionNewUserEnterprise.getEnterpriseId();
                    userEnterpriseCollectionNewUserEnterprise.setEnterpriseId(enterprise);
                    userEnterpriseCollectionNewUserEnterprise = em.merge(userEnterpriseCollectionNewUserEnterprise);
                    if (oldEnterpriseIdOfUserEnterpriseCollectionNewUserEnterprise != null && !oldEnterpriseIdOfUserEnterpriseCollectionNewUserEnterprise.equals(enterprise)) {
                        oldEnterpriseIdOfUserEnterpriseCollectionNewUserEnterprise.getUserEnterpriseCollection().remove(userEnterpriseCollectionNewUserEnterprise);
                        oldEnterpriseIdOfUserEnterpriseCollectionNewUserEnterprise = em.merge(oldEnterpriseIdOfUserEnterpriseCollectionNewUserEnterprise);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enterprise.getEnterpriseId();
                if (findEnterprise(id) == null) {
                    throw new NonexistentEntityException("The enterprise with id " + id + " no longer exists.");
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
            Enterprise enterprise;
            try {
                enterprise = em.getReference(Enterprise.class, id);
                enterprise.getEnterpriseId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enterprise with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<UserEnterprise> userEnterpriseCollectionOrphanCheck = enterprise.getUserEnterpriseCollection();
            for (UserEnterprise userEnterpriseCollectionOrphanCheckUserEnterprise : userEnterpriseCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Enterprise (" + enterprise + ") cannot be destroyed since the UserEnterprise " + userEnterpriseCollectionOrphanCheckUserEnterprise + " in its userEnterpriseCollection field has a non-nullable enterpriseId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(enterprise);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Enterprise> findEnterpriseEntities() {
        return findEnterpriseEntities(true, -1, -1);
    }

    public List<Enterprise> findEnterpriseEntities(int maxResults, int firstResult) {
        return findEnterpriseEntities(false, maxResults, firstResult);
    }

    private List<Enterprise> findEnterpriseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Enterprise.class));
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

    public Enterprise findEnterprise(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Enterprise.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnterpriseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Enterprise> rt = cq.from(Enterprise.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
