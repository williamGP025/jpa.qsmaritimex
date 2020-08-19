/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author WilliamGP025
 */
@Entity
@Table(catalog = "QSMaritimex", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m"),
    @NamedQuery(name = "Menu.findByMenuId", query = "SELECT m FROM Menu m WHERE m.menuId = :menuId"),
    @NamedQuery(name = "Menu.findByMenuName", query = "SELECT m FROM Menu m WHERE m.menuName = :menuName"),
    @NamedQuery(name = "Menu.findByMenuDescription", query = "SELECT m FROM Menu m WHERE m.menuDescription = :menuDescription"),
    @NamedQuery(name = "Menu.findByMenuURL", query = "SELECT m FROM Menu m WHERE m.menuURL = :menuURL"),
    @NamedQuery(name = "Menu.findByMenuCss", query = "SELECT m FROM Menu m WHERE m.menuCss = :menuCss"),
    @NamedQuery(name = "Menu.findByMenuParentId", query = "SELECT m FROM Menu m WHERE m.menuParentId = :menuParentId"),
    @NamedQuery(name = "Menu.findByMenuOrder", query = "SELECT m FROM Menu m WHERE m.menuOrder = :menuOrder"),
    @NamedQuery(name = "Menu.findByStatus", query = "SELECT m FROM Menu m WHERE m.status = :status")})
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer menuId;
    @Column(length = 100)
    private String menuName;
    @Column(length = 100)
    private String menuDescription;
    @Column(length = 100)
    private String menuURL;
    @Column(length = 50)
    private String menuCss;
    private Integer menuParentId;
    private Integer menuOrder;
    private Boolean status;
    @OneToMany(mappedBy = "menuId")
    private Collection<RolMenu> rolMenuCollection;
    @OneToMany(mappedBy = "menuId")
    private Collection<MenuSection> menuSectionCollection;

    public Menu() {
    }

    public Menu(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public String getMenuURL() {
        return menuURL;
    }

    public void setMenuURL(String menuURL) {
        this.menuURL = menuURL;
    }

    public String getMenuCss() {
        return menuCss;
    }

    public void setMenuCss(String menuCss) {
        this.menuCss = menuCss;
    }

    public Integer getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(Integer menuParentId) {
        this.menuParentId = menuParentId;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<RolMenu> getRolMenuCollection() {
        return rolMenuCollection;
    }

    public void setRolMenuCollection(Collection<RolMenu> rolMenuCollection) {
        this.rolMenuCollection = rolMenuCollection;
    }

    @XmlTransient
    public Collection<MenuSection> getMenuSectionCollection() {
        return menuSectionCollection;
    }

    public void setMenuSectionCollection(Collection<MenuSection> menuSectionCollection) {
        this.menuSectionCollection = menuSectionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menuId != null ? menuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.menuId == null && other.menuId != null) || (this.menuId != null && !this.menuId.equals(other.menuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.Menu[ menuId=" + menuId + " ]";
    }
    
}
