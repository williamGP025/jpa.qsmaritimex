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
    @NamedQuery(name = "ServiceType.findAll", query = "SELECT s FROM ServiceType s"),
    @NamedQuery(name = "ServiceType.findByServiceTypeId", query = "SELECT s FROM ServiceType s WHERE s.serviceTypeId = :serviceTypeId"),
    @NamedQuery(name = "ServiceType.findByDescription", query = "SELECT s FROM ServiceType s WHERE s.description = :description"),
    @NamedQuery(name = "ServiceType.findByOrder", query = "SELECT s FROM ServiceType s WHERE s.order = :order"),
    @NamedQuery(name = "ServiceType.findByStatus", query = "SELECT s FROM ServiceType s WHERE s.status = :status")})
public class ServiceType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer serviceTypeId;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String description;
    private Integer order;
    private Boolean status;
    @OneToMany(mappedBy = "serviceTypeId")
    private Collection<Vessel> vesselCollection;
    @OneToMany(mappedBy = "serviceTypeId")
    private Collection<Service> serviceCollection;

    public ServiceType() {
    }

    public ServiceType(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public ServiceType(Integer serviceTypeId, String description) {
        this.serviceTypeId = serviceTypeId;
        this.description = description;
    }

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Vessel> getVesselCollection() {
        return vesselCollection;
    }

    public void setVesselCollection(Collection<Vessel> vesselCollection) {
        this.vesselCollection = vesselCollection;
    }

    @XmlTransient
    public Collection<Service> getServiceCollection() {
        return serviceCollection;
    }

    public void setServiceCollection(Collection<Service> serviceCollection) {
        this.serviceCollection = serviceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceTypeId != null ? serviceTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceType)) {
            return false;
        }
        ServiceType other = (ServiceType) object;
        if ((this.serviceTypeId == null && other.serviceTypeId != null) || (this.serviceTypeId != null && !this.serviceTypeId.equals(other.serviceTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ServiceType[ serviceTypeId=" + serviceTypeId + " ]";
    }
    
}
