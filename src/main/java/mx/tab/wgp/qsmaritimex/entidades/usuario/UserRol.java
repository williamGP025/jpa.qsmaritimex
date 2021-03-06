/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades.usuario;

import mx.tab.wgp.qsmaritimex.entidades.menu.Rol;
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
@Table(name = "UserRol")
@XmlRootElement
public class UserRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer userRolId;
    @JoinColumn(name = "RolId", referencedColumnName = "rolId", nullable = false)
    @ManyToOne(optional = false)
    private Rol rolId;
    //--------------------------------------------------------------------------
    @JoinColumn(name = "UserId", referencedColumnName = "UserId", nullable = false)
    @ManyToOne(optional = false)
    private User userId;

    public UserRol() {
    }

    public UserRol(Integer userRolId) {
        this.userRolId = userRolId;
    }

    public UserRol(Integer userRolId, User userId) {
        this.userRolId = userRolId;
        this.userId = userId;
    }

    public Integer getUserRolId() {
        return userRolId;
    }

    public void setUserRolId(Integer userRolId) {
        this.userRolId = userRolId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        hash += (userRolId != null ? userRolId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserRol)) {
            return false;
        }
        UserRol other = (UserRol) object;
        if ((this.userRolId == null && other.userRolId != null) || (this.userRolId != null && !this.userRolId.equals(other.userRolId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.UserRol[ userRolId=" + userRolId + " ]";
    }

}
