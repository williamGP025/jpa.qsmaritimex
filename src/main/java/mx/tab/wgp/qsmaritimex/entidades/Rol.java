/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author William
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r"),
    @NamedQuery(name = "Rol.findByRolId", query = "SELECT r FROM Rol r WHERE r.rolId = :rolId"),
    @NamedQuery(name = "Rol.findByCreateDate", query = "SELECT r FROM Rol r WHERE r.createDate = :createDate"),
    @NamedQuery(name = "Rol.findByDescription", query = "SELECT r FROM Rol r WHERE r.description = :description"),
    @NamedQuery(name = "Rol.findByModifyDate", query = "SELECT r FROM Rol r WHERE r.modifyDate = :modifyDate"),
    @NamedQuery(name = "Rol.findByRolName", query = "SELECT r FROM Rol r WHERE r.rolName = :rolName"),
    @NamedQuery(name = "Rol.findByStatus", query = "SELECT r FROM Rol r WHERE r.status = :status")})
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer rolId;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String description;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String rolName;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @OneToMany(mappedBy = "rolId")
    private Collection<RolMenu> rolMenuCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolId")
    private Collection<UserRol> userRolCollection;

    public Rol() {
    }

    public Rol(Integer rolId) {
        this.rolId = rolId;
    }

    public Rol(Integer rolId, Date createDate, String description, Date modifyDate, String rolName, boolean status) {
        this.rolId = rolId;
        this.createDate = createDate;
        this.description = description;
        this.modifyDate = modifyDate;
        this.rolName = rolName;
        this.status = status;
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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
    public Collection<UserRol> getUserRolCollection() {
        return userRolCollection;
    }

    public void setUserRolCollection(Collection<UserRol> userRolCollection) {
        this.userRolCollection = userRolCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolId != null ? rolId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.rolId == null && other.rolId != null) || (this.rolId != null && !this.rolId.equals(other.rolId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.Rol[ rolId=" + rolId + " ]";
    }
    
}
