/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WilliamGP025
 */
@Entity
@Table(catalog = "QSMaritimex", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenuSection.findAll", query = "SELECT m FROM MenuSection m"),
    @NamedQuery(name = "MenuSection.findByMenuSectionId", query = "SELECT m FROM MenuSection m WHERE m.menuSectionId = :menuSectionId"),
    @NamedQuery(name = "MenuSection.findByMenuSectionParentId", query = "SELECT m FROM MenuSection m WHERE m.menuSectionParentId = :menuSectionParentId"),
    @NamedQuery(name = "MenuSection.findByMenuSectionOrder", query = "SELECT m FROM MenuSection m WHERE m.menuSectionOrder = :menuSectionOrder"),
    @NamedQuery(name = "MenuSection.findByMenuSectionDescription", query = "SELECT m FROM MenuSection m WHERE m.menuSectionDescription = :menuSectionDescription"),
    @NamedQuery(name = "MenuSection.findByMenuSectionCss", query = "SELECT m FROM MenuSection m WHERE m.menuSectionCss = :menuSectionCss"),
    @NamedQuery(name = "MenuSection.findByStatus", query = "SELECT m FROM MenuSection m WHERE m.status = :status")})
public class MenuSection implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer menuSectionId;
    private Integer menuSectionParentId;
    private Integer menuSectionOrder;
    @Column(length = 100)
    private String menuSectionDescription;
    @Column(length = 50)
    private String menuSectionCss;
    private Boolean status;
    @JoinColumn(name = "MenuId", referencedColumnName = "MenuId")
    @ManyToOne
    private Menu menuId;

    public MenuSection() {
    }

    public MenuSection(Integer menuSectionId) {
        this.menuSectionId = menuSectionId;
    }

    public Integer getMenuSectionId() {
        return menuSectionId;
    }

    public void setMenuSectionId(Integer menuSectionId) {
        this.menuSectionId = menuSectionId;
    }

    public Integer getMenuSectionParentId() {
        return menuSectionParentId;
    }

    public void setMenuSectionParentId(Integer menuSectionParentId) {
        this.menuSectionParentId = menuSectionParentId;
    }

    public Integer getMenuSectionOrder() {
        return menuSectionOrder;
    }

    public void setMenuSectionOrder(Integer menuSectionOrder) {
        this.menuSectionOrder = menuSectionOrder;
    }

    public String getMenuSectionDescription() {
        return menuSectionDescription;
    }

    public void setMenuSectionDescription(String menuSectionDescription) {
        this.menuSectionDescription = menuSectionDescription;
    }

    public String getMenuSectionCss() {
        return menuSectionCss;
    }

    public void setMenuSectionCss(String menuSectionCss) {
        this.menuSectionCss = menuSectionCss;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Menu getMenuId() {
        return menuId;
    }

    public void setMenuId(Menu menuId) {
        this.menuId = menuId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menuSectionId != null ? menuSectionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuSection)) {
            return false;
        }
        MenuSection other = (MenuSection) object;
        if ((this.menuSectionId == null && other.menuSectionId != null) || (this.menuSectionId != null && !this.menuSectionId.equals(other.menuSectionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.MenuSection[ menuSectionId=" + menuSectionId + " ]";
    }
    
}
