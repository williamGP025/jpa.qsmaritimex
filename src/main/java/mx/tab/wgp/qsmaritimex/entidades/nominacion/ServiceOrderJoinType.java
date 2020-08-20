/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades.nominacion;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author William
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceOrderJoinType.findAll", query = "SELECT s FROM ServiceOrderJoinType s"),
    @NamedQuery(name = "ServiceOrderJoinType.findByServiceOrderJoinTypeId", query = "SELECT s FROM ServiceOrderJoinType s WHERE s.serviceOrderJoinTypeId = :serviceOrderJoinTypeId"),
    @NamedQuery(name = "ServiceOrderJoinType.findByDescription", query = "SELECT s FROM ServiceOrderJoinType s WHERE s.description = :description"),
    @NamedQuery(name = "ServiceOrderJoinType.findByStatus", query = "SELECT s FROM ServiceOrderJoinType s WHERE s.status = :status")})
public class ServiceOrderJoinType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Short serviceOrderJoinTypeId;
    @Column(length = 50)
    private String description;
    private Boolean status;
    @OneToMany(mappedBy = "serviceOrderJoinTypeId")
    private Collection<ServiceOrderJoin> serviceOrderJoinCollection;

    public ServiceOrderJoinType() {
    }

    public ServiceOrderJoinType(Short serviceOrderJoinTypeId) {
        this.serviceOrderJoinTypeId = serviceOrderJoinTypeId;
    }

    public Short getServiceOrderJoinTypeId() {
        return serviceOrderJoinTypeId;
    }

    public void setServiceOrderJoinTypeId(Short serviceOrderJoinTypeId) {
        this.serviceOrderJoinTypeId = serviceOrderJoinTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<ServiceOrderJoin> getServiceOrderJoinCollection() {
        return serviceOrderJoinCollection;
    }

    public void setServiceOrderJoinCollection(Collection<ServiceOrderJoin> serviceOrderJoinCollection) {
        this.serviceOrderJoinCollection = serviceOrderJoinCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceOrderJoinTypeId != null ? serviceOrderJoinTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceOrderJoinType)) {
            return false;
        }
        ServiceOrderJoinType other = (ServiceOrderJoinType) object;
        if ((this.serviceOrderJoinTypeId == null && other.serviceOrderJoinTypeId != null) || (this.serviceOrderJoinTypeId != null && !this.serviceOrderJoinTypeId.equals(other.serviceOrderJoinTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ServiceOrderJoinType[ serviceOrderJoinTypeId=" + serviceOrderJoinTypeId + " ]";
    }
    
}
