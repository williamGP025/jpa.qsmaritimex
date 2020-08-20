/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades.menu;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author William
 */
@Entity
@XmlRootElement
@Table(name = "RolMenu")
public class RolMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer menuRolId;
    @JoinColumn(name = "MenuId", referencedColumnName = "menuId")
    @ManyToOne
    private Menu menuId;
    @JoinColumn(name = "RolId", referencedColumnName = "rolId")
    @ManyToOne
    private Rol rolId;

    public RolMenu() {
    }

    public RolMenu(Integer menuRolId) {
        this.menuRolId = menuRolId;
    }

    public Integer getMenuRolId() {
        return menuRolId;
    }

    public void setMenuRolId(Integer menuRolId) {
        this.menuRolId = menuRolId;
    }

    public Menu getMenuId() {
        return menuId;
    }

    public void setMenuId(Menu menuId) {
        this.menuId = menuId;
    }

    public Rol getRolId() {
        return rolId;
    }

    public void setRolId(Rol rolId) {
        this.rolId = rolId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menuRolId != null ? menuRolId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolMenu)) {
            return false;
        }
        RolMenu other = (RolMenu) object;
        if ((this.menuRolId == null && other.menuRolId != null) || (this.menuRolId != null && !this.menuRolId.equals(other.menuRolId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.RolMenu[ menuRolId=" + menuRolId + " ]";
    }
    
}
