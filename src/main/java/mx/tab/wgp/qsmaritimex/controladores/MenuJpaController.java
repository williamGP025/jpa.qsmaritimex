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
import mx.tab.wgp.qsmaritimex.entidades.RolMenu;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.tab.wgp.qsmaritimex.controladores.exceptions.NonexistentEntityException;
import mx.tab.wgp.qsmaritimex.entidades.Menu;
import mx.tab.wgp.qsmaritimex.entidades.MenuSection;

/**
 *
 * @author WilliamGP025
 */
public class MenuJpaController implements Serializable {

    public MenuJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Menu menu) {
        if (menu.getRolMenuCollection() == null) {
            menu.setRolMenuCollection(new ArrayList<RolMenu>());
        }
        if (menu.getMenuSectionCollection() == null) {
            menu.setMenuSectionCollection(new ArrayList<MenuSection>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<RolMenu> attachedRolMenuCollection = new ArrayList<RolMenu>();
            for (RolMenu rolMenuCollectionRolMenuToAttach : menu.getRolMenuCollection()) {
                rolMenuCollectionRolMenuToAttach = em.getReference(rolMenuCollectionRolMenuToAttach.getClass(), rolMenuCollectionRolMenuToAttach.getMenuRolId());
                attachedRolMenuCollection.add(rolMenuCollectionRolMenuToAttach);
            }
            menu.setRolMenuCollection(attachedRolMenuCollection);
            Collection<MenuSection> attachedMenuSectionCollection = new ArrayList<MenuSection>();
            for (MenuSection menuSectionCollectionMenuSectionToAttach : menu.getMenuSectionCollection()) {
                menuSectionCollectionMenuSectionToAttach = em.getReference(menuSectionCollectionMenuSectionToAttach.getClass(), menuSectionCollectionMenuSectionToAttach.getMenuSectionId());
                attachedMenuSectionCollection.add(menuSectionCollectionMenuSectionToAttach);
            }
            menu.setMenuSectionCollection(attachedMenuSectionCollection);
            em.persist(menu);
            for (RolMenu rolMenuCollectionRolMenu : menu.getRolMenuCollection()) {
                Menu oldMenuIdOfRolMenuCollectionRolMenu = rolMenuCollectionRolMenu.getMenuId();
                rolMenuCollectionRolMenu.setMenuId(menu);
                rolMenuCollectionRolMenu = em.merge(rolMenuCollectionRolMenu);
                if (oldMenuIdOfRolMenuCollectionRolMenu != null) {
                    oldMenuIdOfRolMenuCollectionRolMenu.getRolMenuCollection().remove(rolMenuCollectionRolMenu);
                    oldMenuIdOfRolMenuCollectionRolMenu = em.merge(oldMenuIdOfRolMenuCollectionRolMenu);
                }
            }
            for (MenuSection menuSectionCollectionMenuSection : menu.getMenuSectionCollection()) {
                Menu oldMenuIdOfMenuSectionCollectionMenuSection = menuSectionCollectionMenuSection.getMenuId();
                menuSectionCollectionMenuSection.setMenuId(menu);
                menuSectionCollectionMenuSection = em.merge(menuSectionCollectionMenuSection);
                if (oldMenuIdOfMenuSectionCollectionMenuSection != null) {
                    oldMenuIdOfMenuSectionCollectionMenuSection.getMenuSectionCollection().remove(menuSectionCollectionMenuSection);
                    oldMenuIdOfMenuSectionCollectionMenuSection = em.merge(oldMenuIdOfMenuSectionCollectionMenuSection);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Menu menu) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Menu persistentMenu = em.find(Menu.class, menu.getMenuId());
            Collection<RolMenu> rolMenuCollectionOld = persistentMenu.getRolMenuCollection();
            Collection<RolMenu> rolMenuCollectionNew = menu.getRolMenuCollection();
            Collection<MenuSection> menuSectionCollectionOld = persistentMenu.getMenuSectionCollection();
            Collection<MenuSection> menuSectionCollectionNew = menu.getMenuSectionCollection();
            Collection<RolMenu> attachedRolMenuCollectionNew = new ArrayList<RolMenu>();
            for (RolMenu rolMenuCollectionNewRolMenuToAttach : rolMenuCollectionNew) {
                rolMenuCollectionNewRolMenuToAttach = em.getReference(rolMenuCollectionNewRolMenuToAttach.getClass(), rolMenuCollectionNewRolMenuToAttach.getMenuRolId());
                attachedRolMenuCollectionNew.add(rolMenuCollectionNewRolMenuToAttach);
            }
            rolMenuCollectionNew = attachedRolMenuCollectionNew;
            menu.setRolMenuCollection(rolMenuCollectionNew);
            Collection<MenuSection> attachedMenuSectionCollectionNew = new ArrayList<MenuSection>();
            for (MenuSection menuSectionCollectionNewMenuSectionToAttach : menuSectionCollectionNew) {
                menuSectionCollectionNewMenuSectionToAttach = em.getReference(menuSectionCollectionNewMenuSectionToAttach.getClass(), menuSectionCollectionNewMenuSectionToAttach.getMenuSectionId());
                attachedMenuSectionCollectionNew.add(menuSectionCollectionNewMenuSectionToAttach);
            }
            menuSectionCollectionNew = attachedMenuSectionCollectionNew;
            menu.setMenuSectionCollection(menuSectionCollectionNew);
            menu = em.merge(menu);
            for (RolMenu rolMenuCollectionOldRolMenu : rolMenuCollectionOld) {
                if (!rolMenuCollectionNew.contains(rolMenuCollectionOldRolMenu)) {
                    rolMenuCollectionOldRolMenu.setMenuId(null);
                    rolMenuCollectionOldRolMenu = em.merge(rolMenuCollectionOldRolMenu);
                }
            }
            for (RolMenu rolMenuCollectionNewRolMenu : rolMenuCollectionNew) {
                if (!rolMenuCollectionOld.contains(rolMenuCollectionNewRolMenu)) {
                    Menu oldMenuIdOfRolMenuCollectionNewRolMenu = rolMenuCollectionNewRolMenu.getMenuId();
                    rolMenuCollectionNewRolMenu.setMenuId(menu);
                    rolMenuCollectionNewRolMenu = em.merge(rolMenuCollectionNewRolMenu);
                    if (oldMenuIdOfRolMenuCollectionNewRolMenu != null && !oldMenuIdOfRolMenuCollectionNewRolMenu.equals(menu)) {
                        oldMenuIdOfRolMenuCollectionNewRolMenu.getRolMenuCollection().remove(rolMenuCollectionNewRolMenu);
                        oldMenuIdOfRolMenuCollectionNewRolMenu = em.merge(oldMenuIdOfRolMenuCollectionNewRolMenu);
                    }
                }
            }
            for (MenuSection menuSectionCollectionOldMenuSection : menuSectionCollectionOld) {
                if (!menuSectionCollectionNew.contains(menuSectionCollectionOldMenuSection)) {
                    menuSectionCollectionOldMenuSection.setMenuId(null);
                    menuSectionCollectionOldMenuSection = em.merge(menuSectionCollectionOldMenuSection);
                }
            }
            for (MenuSection menuSectionCollectionNewMenuSection : menuSectionCollectionNew) {
                if (!menuSectionCollectionOld.contains(menuSectionCollectionNewMenuSection)) {
                    Menu oldMenuIdOfMenuSectionCollectionNewMenuSection = menuSectionCollectionNewMenuSection.getMenuId();
                    menuSectionCollectionNewMenuSection.setMenuId(menu);
                    menuSectionCollectionNewMenuSection = em.merge(menuSectionCollectionNewMenuSection);
                    if (oldMenuIdOfMenuSectionCollectionNewMenuSection != null && !oldMenuIdOfMenuSectionCollectionNewMenuSection.equals(menu)) {
                        oldMenuIdOfMenuSectionCollectionNewMenuSection.getMenuSectionCollection().remove(menuSectionCollectionNewMenuSection);
                        oldMenuIdOfMenuSectionCollectionNewMenuSection = em.merge(oldMenuIdOfMenuSectionCollectionNewMenuSection);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = menu.getMenuId();
                if (findMenu(id) == null) {
                    throw new NonexistentEntityException("The menu with id " + id + " no longer exists.");
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
            Menu menu;
            try {
                menu = em.getReference(Menu.class, id);
                menu.getMenuId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The menu with id " + id + " no longer exists.", enfe);
            }
            Collection<RolMenu> rolMenuCollection = menu.getRolMenuCollection();
            for (RolMenu rolMenuCollectionRolMenu : rolMenuCollection) {
                rolMenuCollectionRolMenu.setMenuId(null);
                rolMenuCollectionRolMenu = em.merge(rolMenuCollectionRolMenu);
            }
            Collection<MenuSection> menuSectionCollection = menu.getMenuSectionCollection();
            for (MenuSection menuSectionCollectionMenuSection : menuSectionCollection) {
                menuSectionCollectionMenuSection.setMenuId(null);
                menuSectionCollectionMenuSection = em.merge(menuSectionCollectionMenuSection);
            }
            em.remove(menu);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Menu> findMenuEntities() {
        return findMenuEntities(true, -1, -1);
    }

    public List<Menu> findMenuEntities(int maxResults, int firstResult) {
        return findMenuEntities(false, maxResults, firstResult);
    }

    private List<Menu> findMenuEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Menu.class));
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

    public Menu findMenu(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Menu.class, id);
        } finally {
            em.close();
        }
    }

    public int getMenuCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Menu> rt = cq.from(Menu.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
