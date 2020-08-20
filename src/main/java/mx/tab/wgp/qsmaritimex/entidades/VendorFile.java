/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import mx.tab.wgp.qsmaritimex.entidades.nominacion.ServiceOrderService;

/**
 *
 * @author William
 */
@Entity
@XmlRootElement
@Table(name = "VendorFile")
public class VendorFile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 36)
    private String vendorFileId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 12, scale = 4)
    private BigDecimal amount;
    @Column(length = 10)
    private String fileName;
    @Column(length = 10)
    private String fileType;
    private Boolean status;
    @Lob
    private Serializable vendorDocument;
    private Integer vendorFileTypeId;
    @JoinColumn(name = "ServiceOrderServiceId", referencedColumnName = "serviceOrderServiceId", nullable = false)
    @ManyToOne(optional = false)
    private ServiceOrderService serviceOrderServiceId;

    public VendorFile() {
    }

    public VendorFile(String vendorFileId) {
        this.vendorFileId = vendorFileId;
    }

    public String getVendorFileId() {
        return vendorFileId;
    }

    public void setVendorFileId(String vendorFileId) {
        this.vendorFileId = vendorFileId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Serializable getVendorDocument() {
        return vendorDocument;
    }

    public void setVendorDocument(Serializable vendorDocument) {
        this.vendorDocument = vendorDocument;
    }

    public Integer getVendorFileTypeId() {
        return vendorFileTypeId;
    }

    public void setVendorFileTypeId(Integer vendorFileTypeId) {
        this.vendorFileTypeId = vendorFileTypeId;
    }

    public ServiceOrderService getServiceOrderServiceId() {
        return serviceOrderServiceId;
    }

    public void setServiceOrderServiceId(ServiceOrderService serviceOrderServiceId) {
        this.serviceOrderServiceId = serviceOrderServiceId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vendorFileId != null ? vendorFileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VendorFile)) {
            return false;
        }
        VendorFile other = (VendorFile) object;
        if ((this.vendorFileId == null && other.vendorFileId != null) || (this.vendorFileId != null && !this.vendorFileId.equals(other.vendorFileId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.VendorFile[ vendorFileId=" + vendorFileId + " ]";
    }
    
}
