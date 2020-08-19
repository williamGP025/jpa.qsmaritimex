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
    @NamedQuery(name = "VendorInvoiceStatus.findAll", query = "SELECT v FROM VendorInvoiceStatus v"),
    @NamedQuery(name = "VendorInvoiceStatus.findByVendorInvoiceStatusId", query = "SELECT v FROM VendorInvoiceStatus v WHERE v.vendorInvoiceStatusId = :vendorInvoiceStatusId"),
    @NamedQuery(name = "VendorInvoiceStatus.findByDescription", query = "SELECT v FROM VendorInvoiceStatus v WHERE v.description = :description"),
    @NamedQuery(name = "VendorInvoiceStatus.findByStatus", query = "SELECT v FROM VendorInvoiceStatus v WHERE v.status = :status")})
public class VendorInvoiceStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Short vendorInvoiceStatusId;
    @Column(length = 50)
    private String description;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendorInvoiceStatusId")
    private Collection<VendorInvoice> vendorInvoiceCollection;

    public VendorInvoiceStatus() {
    }

    public VendorInvoiceStatus(Short vendorInvoiceStatusId) {
        this.vendorInvoiceStatusId = vendorInvoiceStatusId;
    }

    public VendorInvoiceStatus(Short vendorInvoiceStatusId, boolean status) {
        this.vendorInvoiceStatusId = vendorInvoiceStatusId;
        this.status = status;
    }

    public Short getVendorInvoiceStatusId() {
        return vendorInvoiceStatusId;
    }

    public void setVendorInvoiceStatusId(Short vendorInvoiceStatusId) {
        this.vendorInvoiceStatusId = vendorInvoiceStatusId;
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
    public Collection<VendorInvoice> getVendorInvoiceCollection() {
        return vendorInvoiceCollection;
    }

    public void setVendorInvoiceCollection(Collection<VendorInvoice> vendorInvoiceCollection) {
        this.vendorInvoiceCollection = vendorInvoiceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vendorInvoiceStatusId != null ? vendorInvoiceStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VendorInvoiceStatus)) {
            return false;
        }
        VendorInvoiceStatus other = (VendorInvoiceStatus) object;
        if ((this.vendorInvoiceStatusId == null && other.vendorInvoiceStatusId != null) || (this.vendorInvoiceStatusId != null && !this.vendorInvoiceStatusId.equals(other.vendorInvoiceStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.VendorInvoiceStatus[ vendorInvoiceStatusId=" + vendorInvoiceStatusId + " ]";
    }
    
}
