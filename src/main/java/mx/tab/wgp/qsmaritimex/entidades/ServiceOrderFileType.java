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
 * @author WilliamGP025
 */
@Entity
@Table(catalog = "QSMaritimex", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceOrderFileType.findAll", query = "SELECT s FROM ServiceOrderFileType s"),
    @NamedQuery(name = "ServiceOrderFileType.findByServiceOrderFileTypeId", query = "SELECT s FROM ServiceOrderFileType s WHERE s.serviceOrderFileTypeId = :serviceOrderFileTypeId"),
    @NamedQuery(name = "ServiceOrderFileType.findByDescription", query = "SELECT s FROM ServiceOrderFileType s WHERE s.description = :description"),
    @NamedQuery(name = "ServiceOrderFileType.findByStatus", query = "SELECT s FROM ServiceOrderFileType s WHERE s.status = :status")})
public class ServiceOrderFileType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Short serviceOrderFileTypeId;
    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    private String description;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceOrderFileTypeId")
    private Collection<ServiceOrderFile> serviceOrderFileCollection;

    public ServiceOrderFileType() {
    }

    public ServiceOrderFileType(Short serviceOrderFileTypeId) {
        this.serviceOrderFileTypeId = serviceOrderFileTypeId;
    }

    public ServiceOrderFileType(Short serviceOrderFileTypeId, String description, boolean status) {
        this.serviceOrderFileTypeId = serviceOrderFileTypeId;
        this.description = description;
        this.status = status;
    }

    public Short getServiceOrderFileTypeId() {
        return serviceOrderFileTypeId;
    }

    public void setServiceOrderFileTypeId(Short serviceOrderFileTypeId) {
        this.serviceOrderFileTypeId = serviceOrderFileTypeId;
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
    public Collection<ServiceOrderFile> getServiceOrderFileCollection() {
        return serviceOrderFileCollection;
    }

    public void setServiceOrderFileCollection(Collection<ServiceOrderFile> serviceOrderFileCollection) {
        this.serviceOrderFileCollection = serviceOrderFileCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceOrderFileTypeId != null ? serviceOrderFileTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceOrderFileType)) {
            return false;
        }
        ServiceOrderFileType other = (ServiceOrderFileType) object;
        if ((this.serviceOrderFileTypeId == null && other.serviceOrderFileTypeId != null) || (this.serviceOrderFileTypeId != null && !this.serviceOrderFileTypeId.equals(other.serviceOrderFileTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ServiceOrderFileType[ serviceOrderFileTypeId=" + serviceOrderFileTypeId + " ]";
    }
    
}
