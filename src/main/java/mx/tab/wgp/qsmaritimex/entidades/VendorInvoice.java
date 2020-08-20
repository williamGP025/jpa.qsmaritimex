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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrderService;

/**
 *
 * @author William
 */
@Entity
@XmlRootElement
@Table(name = "VendorInvoice")
public class VendorInvoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer vendorInvoiceID;
    @Column(length = 200)
    private String advancePaymentFile;
    @Column(length = 500)
    private String description;
    private Integer invoiceFile;
    @Column(length = 500)
    private String invoiceFileName;
    @Column(length = 500)
    private String notes;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @OneToMany(mappedBy = "vendorInvoiceID")
    private Collection<ServiceOrderService> serviceOrderServiceCollection;
    @OneToMany(mappedBy = "vendorInvoiceOldID")
    private Collection<ServiceOrderService> serviceOrderServiceCollection1;
    @JoinColumn(name = "VendorInvoiceStatusId", referencedColumnName = "vendorInvoiceStatusId", nullable = false)
    @ManyToOne(optional = false)
    private VendorInvoiceStatus vendorInvoiceStatusId;

    public VendorInvoice() {
    }

    public VendorInvoice(Integer vendorInvoiceID) {
        this.vendorInvoiceID = vendorInvoiceID;
    }

    public VendorInvoice(Integer vendorInvoiceID, boolean status) {
        this.vendorInvoiceID = vendorInvoiceID;
        this.status = status;
    }

    public Integer getVendorInvoiceID() {
        return vendorInvoiceID;
    }

    public void setVendorInvoiceID(Integer vendorInvoiceID) {
        this.vendorInvoiceID = vendorInvoiceID;
    }

    public String getAdvancePaymentFile() {
        return advancePaymentFile;
    }

    public void setAdvancePaymentFile(String advancePaymentFile) {
        this.advancePaymentFile = advancePaymentFile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getInvoiceFile() {
        return invoiceFile;
    }

    public void setInvoiceFile(Integer invoiceFile) {
        this.invoiceFile = invoiceFile;
    }

    public String getInvoiceFileName() {
        return invoiceFileName;
    }

    public void setInvoiceFileName(String invoiceFileName) {
        this.invoiceFileName = invoiceFileName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    @XmlTransient
    public Collection<ServiceOrderService> getServiceOrderServiceCollection1() {
        return serviceOrderServiceCollection1;
    }

    public void setServiceOrderServiceCollection1(Collection<ServiceOrderService> serviceOrderServiceCollection1) {
        this.serviceOrderServiceCollection1 = serviceOrderServiceCollection1;
    }

    public VendorInvoiceStatus getVendorInvoiceStatusId() {
        return vendorInvoiceStatusId;
    }

    public void setVendorInvoiceStatusId(VendorInvoiceStatus vendorInvoiceStatusId) {
        this.vendorInvoiceStatusId = vendorInvoiceStatusId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vendorInvoiceID != null ? vendorInvoiceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VendorInvoice)) {
            return false;
        }
        VendorInvoice other = (VendorInvoice) object;
        if ((this.vendorInvoiceID == null && other.vendorInvoiceID != null) || (this.vendorInvoiceID != null && !this.vendorInvoiceID.equals(other.vendorInvoiceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.VendorInvoice[ vendorInvoiceID=" + vendorInvoiceID + " ]";
    }
    
}
