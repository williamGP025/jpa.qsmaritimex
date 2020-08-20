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
import mx.tab.wgp.qsmaritimex.entidades.menu.RolMenu;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.IllegalOrphanException;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.menu.Rol;
import mx.tab.wgp.qsmaritimex.entidades.usuario.UserRol;

/**
 *
 * @author William
 */
public class RolJpaController implements Serializable {

    public RolJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rol rol) {
        if (rol.getRolMenuCollection() == null) {
            rol.setRolMenuCollection(new ArrayList<RolMenu>());
        }
        if (rol.getUserRolCollection() == null) {
            rol.setUserRolCollection(new ArrayList<UserRol>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<RolMenu> attachedRolMenuCollection = new ArrayList<RolMenu>();
            for (RolMenu rolMenuCollectionRolMenuToAttach : rol.getRolMenuCollection()) {
                rolMenuCollectionRolMenuToAttach = em.getReference(rolMenuCollectionRolMenuToAttach.getClass(), rolMenuCollectionRolMenuToAttach.getMenuRolId());
                attachedRolMenuCollection.add(rolMenuCollectionRolMenuToAttach);
            }
            rol.setRolMenuCollection(attachedRolMenuCollection);
            Collection<UserRol> attachedUserRolCollection = new ArrayList<UserRol>();
            for (UserRol userRolCollectionUserRolToAttach : rol.getUserRolCollection()) {
                userRolCollectionUserRolToAttach = em.getReference(userRolCollectionUserRolToAttach.getClass(), userRolCollectionUserRolToAttach.getUserRolId());
                attachedUserRolCollection.add(userRolCollectionUserRolToAttach);
            }
            rol.setUserRolCollection(attachedUserRolCollection);
            em.persist(rol);
            for (RolMenu rolMenuCollectionRolMenu : rol.getRolMenuCollection()) {
                Rol oldRolIdOfRolMenuCollectionRolMenu = rolMenuCollectionRolMenu.getRolId();
                rolMenuCollectionRolMenu.setRolId(rol);
                rolMenuCollectionRolMenu = em.merge(rolMenuCollectionRolMenu);
                if (oldRolIdOfRolMenuCollectionRolMenu != null) {
                    oldRolIdOfRolMenuCollectionRolMenu.getRolMenuCollection().remove(rolMenuCollectionRolMenu);
                    oldRolIdOfRolMenuCollectionRolMenu = em.merge(oldRolIdOfRolMenuCollectionRolMenu);
                }
            }
            for (UserRol userRolCollectionUserRol : rol.getUserRolCollection()) {
                Rol oldRolIdOfUserRolCollectionUserRol = userRolCollectionUserRol.getRolId();
                userRolCollectionUserRol.setRolId(rol);
                userRolCollectionUserRol = em.merge(userRolCollectionUserRol);
                if (oldRolIdOfUserRolCollectionUserRol != null) {
                    oldRolIdOfUserRolCollectionUserRol.getUserRolCollection().remove(userRolCollectionUserRol);
                    oldRolIdOfUserRolCollectionUserRol = em.merge(oldRolIdOfUserRolCollectionUserRol);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rol rol) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol persistentRol = em.find(Rol.class, rol.getRolId());
            Collection<RolMenu> rolMenuCollectionOld = persistentRol.getRolMenuCollection();
            Collection<RolMenu> rolMenuCollectionNew = rol.getRolMenuCollection();
            Collection<UserRol> userRolCollectionOld = persistentRol.getUserRolCollection();
            Collection<UserRol> userRolCollectionNew = rol.getUserRolCollection();
            List<String> illegalOrphanMessages = null;
            for (UserRol userRolCollectionOldUserRol : userRolCollectionOld) {
                if (!userRolCollectionNew.contains(userRolCollectionOldUserRol)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UserRol " + userRolCollectionOldUserRol + " since its rolId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<RolMenu> attachedRolMenuCollectionNew = new ArrayList<RolMenu>();
            for (RolMenu rolMenuCollectionNewRolMenuToAttach : rolMenuCollectionNew) {
                rolMenuCollectionNewRolMenuToAttach = em.getReference(rolMenuCollectionNewRolMenuToAttach.getClass(), rolMenuCollectionNewRolMenuToAttach.getMenuRolId());
                attachedRolMenuCollectionNew.add(rolMenuCollectionNewRolMenuToAttach);
            }
            rolMenuCollectionNew = attachedRolMenuCollectionNew;
            rol.setRolMenuCollection(rolMenuCollectionNew);
            Collection<UserRol> attachedUserRolCollectionNew = new ArrayList<UserRol>();
            for (UserRol userRolCollectionNewUserRolToAttach : userRolCollectionNew) {
                userRolCollectionNewUserRolToAttach = em.getReference(userRolCollectionNewUserRolToAttach.getClass(), userRolCollectionNewUserRolToAttach.getUserRolId());
                attachedUserRolCollectionNew.add(userRolCollectionNewUserRolToAttach);
            }
            userRolCollectionNew = attachedUserRolCollectionNew;
            rol.setUserRolCollection(userRolCollectionNew);
            rol = em.merge(rol);
            for (RolMenu rolMenuCollectionOldRolMenu : rolMenuCollectionOld) {
                if (!rolMenuCollectionNew.contains(rolMenuCollectionOldRolMenu)) {
                    rolMenuCollectionOldRolMenu.setRolId(null);
                    rolMenuCollectionOldRolMenu = em.merge(rolMenuCollectionOldRolMenu);
                }
            }
            for (RolMenu rolMenuCollectionNewRolMenu : rolMenuCollectionNew) {
                if (!rolMenuCollectionOld.contains(rolMenuCollectionNewRolMenu)) {
                    Rol oldRolIdOfRolMenuCollectionNewRolMenu = rolMenuCollectionNewRolMenu.getRolId();
                    rolMenuCollectionNewRolMenu.setRolId(rol);
                    rolMenuCollectionNewRolMenu = em.merge(rolMenuCollectionNewRolMenu);
                    if (oldRolIdOfRolMenuCollectionNewRolMenu != null && !oldRolIdOfRolMenuCollectionNewRolMenu.equals(rol)) {
                        oldRolIdOfRolMenuCollectionNewRolMenu.getRolMenuCollection().remove(rolMenuCollectionNewRolMenu);
                        oldRolIdOfRolMenuCollectionNewRolMenu = em.merge(oldRolIdOfRolMenuCollectionNewRolMenu);
                    }
                }
            }
            for (UserRol userRolCollectionNewUserRol : userRolCollectionNew) {
                if (!userRolCollectionOld.contains(userRolCollectionNewUserRol)) {
                    Rol oldRolIdOfUserRolCollectionNewUserRol = userRolCollectionNewUserRol.getRolId();
                    userRolCollectionNewUserRol.setRolId(rol);
                    userRolCollectionNewUserRol = em.merge(userRolCollectionNewUserRol);
                    if (oldRolIdOfUserRolCollectionNewUserRol != null && !oldRolIdOfUserRolCollectionNewUserRol.equals(rol)) {
                        oldRolIdOfUserRolCollectionNewUserRol.getUserRolCollection().remove(userRolCollectionNewUserRol);
                        oldRolIdOfUserRolCollectionNewUserRol = em.merge(oldRolIdOfUserRolCollectionNewUserRol);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rol.getRolId();
                if (findRol(id) == null) {
                    throw new NonexistentEntityException("The rol with id " + id + " no longer exists.");
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
            Rol rol;
            try {
                rol = em.getReference(Rol.class, id);
                rol.getRolId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rol with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<UserRol> userRolCollectionOrphanCheck = rol.getUserRolCollection();
            for (UserRol userRolCollectionOrphanCheckUserRol : userRolCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Rol (" + rol + ") cannot be destroyed since the UserRol " + userRolCollectionOrphanCheckUserRol + " in its userRolCollection field has a non-nullable rolId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<RolMenu> rolMenuCollection = rol.getRolMenuCollection();
            for (RolMenu rolMenuCollectionRolMenu : rolMenuCollection) {
                rolMenuCollectionRolMenu.setRolId(null);
                rolMenuCollectionRolMenu = em.merge(rolMenuCollectionRolMenu);
            }
            em.remove(rol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rol> findRolEntities() {
        return findRolEntities(true, -1, -1);
    }

    public List<Rol> findRolEntities(int maxResults, int firstResult) {
        return findRolEntities(false, maxResults, firstResult);
    }

    private List<Rol> findRolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rol.class));
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

    public Rol findRol(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rol.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rol> rt = cq.from(Rol.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
