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
import mx.tab.wgp.qsmaritimex.entidades.menu.Menu;
import mx.tab.wgp.qsmaritimex.entidades.menu.Rol;
import mx.tab.wgp.qsmaritimex.entidades.menu.RolMenu;

/**
 *
 * @author William
 */
public class RolMenuJpaController implements Serializable {

    public RolMenuJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RolMenu rolMenu) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Menu menuId = rolMenu.getMenuId();
            if (menuId != null) {
                menuId = em.getReference(menuId.getClass(), menuId.getMenuId());
                rolMenu.setMenuId(menuId);
            }
            Rol rolId = rolMenu.getRolId();
            if (rolId != null) {
                rolId = em.getReference(rolId.getClass(), rolId.getRolId());
                rolMenu.setRolId(rolId);
            }
            em.persist(rolMenu);
            if (menuId != null) {
                menuId.getRolMenuCollection().add(rolMenu);
                menuId = em.merge(menuId);
            }
            if (rolId != null) {
                rolId.getRolMenuCollection().add(rolMenu);
                rolId = em.merge(rolId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RolMenu rolMenu) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RolMenu persistentRolMenu = em.find(RolMenu.class, rolMenu.getMenuRolId());
            Menu menuIdOld = persistentRolMenu.getMenuId();
            Menu menuIdNew = rolMenu.getMenuId();
            Rol rolIdOld = persistentRolMenu.getRolId();
            Rol rolIdNew = rolMenu.getRolId();
            if (menuIdNew != null) {
                menuIdNew = em.getReference(menuIdNew.getClass(), menuIdNew.getMenuId());
                rolMenu.setMenuId(menuIdNew);
            }
            if (rolIdNew != null) {
                rolIdNew = em.getReference(rolIdNew.getClass(), rolIdNew.getRolId());
                rolMenu.setRolId(rolIdNew);
            }
            rolMenu = em.merge(rolMenu);
            if (menuIdOld != null && !menuIdOld.equals(menuIdNew)) {
                menuIdOld.getRolMenuCollection().remove(rolMenu);
                menuIdOld = em.merge(menuIdOld);
            }
            if (menuIdNew != null && !menuIdNew.equals(menuIdOld)) {
                menuIdNew.getRolMenuCollection().add(rolMenu);
                menuIdNew = em.merge(menuIdNew);
            }
            if (rolIdOld != null && !rolIdOld.equals(rolIdNew)) {
                rolIdOld.getRolMenuCollection().remove(rolMenu);
                rolIdOld = em.merge(rolIdOld);
            }
            if (rolIdNew != null && !rolIdNew.equals(rolIdOld)) {
                rolIdNew.getRolMenuCollection().add(rolMenu);
                rolIdNew = em.merge(rolIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rolMenu.getMenuRolId();
                if (findRolMenu(id) == null) {
                    throw new NonexistentEntityException("The rolMenu with id " + id + " no longer exists.");
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
            RolMenu rolMenu;
            try {
                rolMenu = em.getReference(RolMenu.class, id);
                rolMenu.getMenuRolId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rolMenu with id " + id + " no longer exists.", enfe);
            }
            Menu menuId = rolMenu.getMenuId();
            if (menuId != null) {
                menuId.getRolMenuCollection().remove(rolMenu);
                menuId = em.merge(menuId);
            }
            Rol rolId = rolMenu.getRolId();
            if (rolId != null) {
                rolId.getRolMenuCollection().remove(rolMenu);
                rolId = em.merge(rolId);
            }
            em.remove(rolMenu);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RolMenu> findRolMenuEntities() {
        return findRolMenuEntities(true, -1, -1);
    }

    public List<RolMenu> findRolMenuEntities(int maxResults, int firstResult) {
        return findRolMenuEntities(false, maxResults, firstResult);
    }

    private List<RolMenu> findRolMenuEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RolMenu.class));
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

    public RolMenu findRolMenu(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RolMenu.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolMenuCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RolMenu> rt = cq.from(RolMenu.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
