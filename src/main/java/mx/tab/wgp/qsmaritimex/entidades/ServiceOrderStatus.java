/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author William
 */
@Entity
@Table(name = "ServiceOrderStatus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceOrderStatus.findAll", query = "SELECT s FROM ServiceOrderStatus s"),
    @NamedQuery(name = "ServiceOrderStatus.findByServiceOrderStatusId", query = "SELECT s FROM ServiceOrderStatus s WHERE s.serviceOrderStatusId = :serviceOrderStatusId"),
    @NamedQuery(name = "ServiceOrderStatus.findByDescription", query = "SELECT s FROM ServiceOrderStatus s WHERE s.description = :description"),
    @NamedQuery(name = "ServiceOrderStatus.findByStatus", query = "SELECT s FROM ServiceOrderStatus s WHERE s.status = :status")})
public class ServiceOrderStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Short serviceOrderStatusId;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String description;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    //--------------------------------------------------------------------------
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceOrderStatusId")
    private Collection<ServiceOrder> serviceOrderCollection;

    public ServiceOrderStatus() {
    }

    public ServiceOrderStatus(Short serviceOrderStatusId) {
        this.serviceOrderStatusId = serviceOrderStatusId;
    }

    public ServiceOrderStatus(Short serviceOrderStatusId, String description, boolean status) {
        this.serviceOrderStatusId = serviceOrderStatusId;
        this.description = description;
        this.status = status;
    }

    public Short getServiceOrderStatusId() {
        return serviceOrderStatusId;
    }

    public void setServiceOrderStatusId(Short serviceOrderStatusId) {
        this.serviceOrderStatusId = serviceOrderStatusId;
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
    public Collection<ServiceOrder> getServiceOrderCollection() {
        return serviceOrderCollection;
    }

    public void setServiceOrderCollection(Collection<ServiceOrder> serviceOrderCollection) {
        this.serviceOrderCollection = serviceOrderCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceOrderStatusId != null ? serviceOrderStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceOrderStatus)) {
            return false;
        }
        ServiceOrderStatus other = (ServiceOrderStatus) object;
        if ((this.serviceOrderStatusId == null && other.serviceOrderStatusId != null) || (this.serviceOrderStatusId != null && !this.serviceOrderStatusId.equals(other.serviceOrderStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ServiceOrderStatus[ serviceOrderStatusId=" + serviceOrderStatusId + " ]";
    }
    
}
