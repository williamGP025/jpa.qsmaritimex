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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author William
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceOrderServicesType.findAll", query = "SELECT s FROM ServiceOrderServicesType s"),
    @NamedQuery(name = "ServiceOrderServicesType.findByServiceOrderServicesTypeId", query = "SELECT s FROM ServiceOrderServicesType s WHERE s.serviceOrderServicesTypeId = :serviceOrderServicesTypeId"),
    @NamedQuery(name = "ServiceOrderServicesType.findByDescription", query = "SELECT s FROM ServiceOrderServicesType s WHERE s.description = :description"),
    @NamedQuery(name = "ServiceOrderServicesType.findByStatus", query = "SELECT s FROM ServiceOrderServicesType s WHERE s.status = :status")})
public class ServiceOrderServicesType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer serviceOrderServicesTypeId;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String description;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @OneToMany(mappedBy = "serviceOrderServiceTypeId")
    private Collection<ServiceOrderService> serviceOrderServiceCollection;

    public ServiceOrderServicesType() {
    }

    public ServiceOrderServicesType(Integer serviceOrderServicesTypeId) {
        this.serviceOrderServicesTypeId = serviceOrderServicesTypeId;
    }

    public ServiceOrderServicesType(Integer serviceOrderServicesTypeId, String description, boolean status) {
        this.serviceOrderServicesTypeId = serviceOrderServicesTypeId;
        this.description = description;
        this.status = status;
    }

    public Integer getServiceOrderServicesTypeId() {
        return serviceOrderServicesTypeId;
    }

    public void setServiceOrderServicesTypeId(Integer serviceOrderServicesTypeId) {
        this.serviceOrderServicesTypeId = serviceOrderServicesTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<ServiceOrderService> getServiceOrderServiceCollection() {
        return serviceOrderServiceCollection;
    }

    public void setServiceOrderServiceCollection(Collection<ServiceOrderService> serviceOrderServiceCollection) {
        this.serviceOrderServiceCollection = serviceOrderServiceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceOrderServicesTypeId != null ? serviceOrderServicesTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceOrderServicesType)) {
            return false;
        }
        ServiceOrderServicesType other = (ServiceOrderServicesType) object;
        if ((this.serviceOrderServicesTypeId == null && other.serviceOrderServicesTypeId != null) || (this.serviceOrderServicesTypeId != null && !this.serviceOrderServicesTypeId.equals(other.serviceOrderServicesTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ServiceOrderServicesType[ serviceOrderServicesTypeId=" + serviceOrderServicesTypeId + " ]";
    }
    
}
