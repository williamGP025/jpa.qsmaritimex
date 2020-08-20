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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author William
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserRol.findAll", query = "SELECT u FROM UserRol u"),
    @NamedQuery(name = "UserRol.findByUserRolId", query = "SELECT u FROM UserRol u WHERE u.userRolId = :userRolId"),
    @NamedQuery(name = "UserRol.findByUserId", query = "SELECT u FROM UserRol u WHERE u.userId = :userId")})
public class UserRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer userRolId;
    @Basic(optional = false)
    @Column(nullable = false)
    private int userId;
    @JoinColumn(name = "RolId", referencedColumnName = "rolId", nullable = false)
    @ManyToOne(optional = false)
    private Rol rolId;

    public UserRol() {
    }

    public UserRol(Integer userRolId) {
        this.userRolId = userRolId;
    }

    public UserRol(Integer userRolId, int userId) {
        this.userRolId = userRolId;
        this.userId = userId;
    }

    public Integer getUserRolId() {
        return userRolId;
    }

    public void setUserRolId(Integer userRolId) {
        this.userRolId = userRolId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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
