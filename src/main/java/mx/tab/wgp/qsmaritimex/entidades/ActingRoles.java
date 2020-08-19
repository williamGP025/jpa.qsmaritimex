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
    @NamedQuery(name = "ActingRoles.findAll", query = "SELECT a FROM ActingRoles a"),
    @NamedQuery(name = "ActingRoles.findByActingRoleId", query = "SELECT a FROM ActingRoles a WHERE a.actingRoleId = :actingRoleId"),
    @NamedQuery(name = "ActingRoles.findByStatus", query = "SELECT a FROM ActingRoles a WHERE a.status = :status")})
public class ActingRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer actingRoleId;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @JoinColumn(name = "ActingRolesTypeId", referencedColumnName = "ActingRolesTypeId", nullable = false)
    @ManyToOne(optional = false)
    private ActingRolesType actingRolesTypeId;
    @JoinColumn(name = "ServiceOrderId", referencedColumnName = "ServiceOrderId", nullable = false)
    @ManyToOne(optional = false)
    private ServiceOrder serviceOrderId;

    public ActingRoles() {
    }

    public ActingRoles(Integer actingRoleId) {
        this.actingRoleId = actingRoleId;
    }

    public ActingRoles(Integer actingRoleId, boolean status) {
        this.actingRoleId = actingRoleId;
        this.status = status;
    }

    public Integer getActingRoleId() {
        return actingRoleId;
    }

    public void setActingRoleId(Integer actingRoleId) {
        this.actingRoleId = actingRoleId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ActingRolesType getActingRolesTypeId() {
        return actingRolesTypeId;
    }

    public void setActingRolesTypeId(ActingRolesType actingRolesTypeId) {
        this.actingRolesTypeId = actingRolesTypeId;
    }

    public ServiceOrder getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(ServiceOrder serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actingRoleId != null ? actingRoleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActingRoles)) {
            return false;
        }
        ActingRoles other = (ActingRoles) object;
        if ((this.actingRoleId == null && other.actingRoleId != null) || (this.actingRoleId != null && !this.actingRoleId.equals(other.actingRoleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ActingRoles[ actingRoleId=" + actingRoleId + " ]";
    }
    
}
