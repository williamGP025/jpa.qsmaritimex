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
    @NamedQuery(name = "ServiceOrderServicesStatus.findAll", query = "SELECT s FROM ServiceOrderServicesStatus s"),
    @NamedQuery(name = "ServiceOrderServicesStatus.findByServiceOrderServiceStatusId", query = "SELECT s FROM ServiceOrderServicesStatus s WHERE s.serviceOrderServiceStatusId = :serviceOrderServiceStatusId"),
    @NamedQuery(name = "ServiceOrderServicesStatus.findByDescription", query = "SELECT s FROM ServiceOrderServicesStatus s WHERE s.description = :description"),
    @NamedQuery(name = "ServiceOrderServicesStatus.findByStatus", query = "SELECT s FROM ServiceOrderServicesStatus s WHERE s.status = :status")})
public class ServiceOrderServicesStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Short serviceOrderServiceStatusId;
    @Column(length = 50)
    private String description;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @OneToMany(mappedBy = "serviceOrderServiceStatusId")
    private Collection<ServiceOrderService> serviceOrderServiceCollection;

    public ServiceOrderServicesStatus() {
    }

    public ServiceOrderServicesStatus(Short serviceOrderServiceStatusId) {
        this.serviceOrderServiceStatusId = serviceOrderServiceStatusId;
    }

    public ServiceOrderServicesStatus(Short serviceOrderServiceStatusId, boolean status) {
        this.serviceOrderServiceStatusId = serviceOrderServiceStatusId;
        this.status = status;
    }

    public Short getServiceOrderServiceStatusId() {
        return serviceOrderServiceStatusId;
    }

    public void setServiceOrderServiceStatusId(Short serviceOrderServiceStatusId) {
        this.serviceOrderServiceStatusId = serviceOrderServiceStatusId;
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
        hash += (serviceOrderServiceStatusId != null ? serviceOrderServiceStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceOrderServicesStatus)) {
            return false;
        }
        ServiceOrderServicesStatus other = (ServiceOrderServicesStatus) object;
        if ((this.serviceOrderServiceStatusId == null && other.serviceOrderServiceStatusId != null) || (this.serviceOrderServiceStatusId != null && !this.serviceOrderServiceStatusId.equals(other.serviceOrderServiceStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ServiceOrderServicesStatus[ serviceOrderServiceStatusId=" + serviceOrderServiceStatusId + " ]";
    }
    
}
