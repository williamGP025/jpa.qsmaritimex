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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WilliamGP025
 */
@Entity
@Table(catalog = "QSMaritimex", schema = "dbo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ServiceOrderFileId"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceOrderFile.findAll", query = "SELECT s FROM ServiceOrderFile s"),
    @NamedQuery(name = "ServiceOrderFile.findByServiceOrderFileId", query = "SELECT s FROM ServiceOrderFile s WHERE s.serviceOrderFileId = :serviceOrderFileId"),
    @NamedQuery(name = "ServiceOrderFile.findByFileName", query = "SELECT s FROM ServiceOrderFile s WHERE s.fileName = :fileName"),
    @NamedQuery(name = "ServiceOrderFile.findByFileType", query = "SELECT s FROM ServiceOrderFile s WHERE s.fileType = :fileType"),
    @NamedQuery(name = "ServiceOrderFile.findByStatus", query = "SELECT s FROM ServiceOrderFile s WHERE s.status = :status")})
public class ServiceOrderFile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 36)
    private String serviceOrderFileId;
    @Basic(optional = false)
    @Column(nullable = false, length = 120)
    private String fileName;
    @Basic(optional = false)
    @Column(nullable = false, length = 10)
    private String fileType;
    @Lob
    private Serializable serviceOrderDocument;
    private Boolean status;
    @JoinColumn(name = "ServiceOrderId", referencedColumnName = "ServiceOrderId", nullable = false)
    @ManyToOne(optional = false)
    private ServiceOrder serviceOrderId;
    @JoinColumn(name = "ServiceOrderFileTypeId", referencedColumnName = "ServiceOrderFileTypeId", nullable = false)
    @ManyToOne(optional = false)
    private ServiceOrderFileType serviceOrderFileTypeId;

    public ServiceOrderFile() {
    }

    public ServiceOrderFile(String serviceOrderFileId) {
        this.serviceOrderFileId = serviceOrderFileId;
    }

    public ServiceOrderFile(String serviceOrderFileId, String fileName, String fileType) {
        this.serviceOrderFileId = serviceOrderFileId;
        this.fileName = fileName;
        this.fileType = fileType;
    }

    public String getServiceOrderFileId() {
        return serviceOrderFileId;
    }

    public void setServiceOrderFileId(String serviceOrderFileId) {
        this.serviceOrderFileId = serviceOrderFileId;
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

    public Serializable getServiceOrderDocument() {
        return serviceOrderDocument;
    }

    public void setServiceOrderDocument(Serializable serviceOrderDocument) {
        this.serviceOrderDocument = serviceOrderDocument;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ServiceOrder getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(ServiceOrder serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public ServiceOrderFileType getServiceOrderFileTypeId() {
        return serviceOrderFileTypeId;
    }

    public void setServiceOrderFileTypeId(ServiceOrderFileType serviceOrderFileTypeId) {
        this.serviceOrderFileTypeId = serviceOrderFileTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceOrderFileId != null ? serviceOrderFileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceOrderFile)) {
            return false;
        }
        ServiceOrderFile other = (ServiceOrderFile) object;
        if ((this.serviceOrderFileId == null && other.serviceOrderFileId != null) || (this.serviceOrderFileId != null && !this.serviceOrderFileId.equals(other.serviceOrderFileId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ServiceOrderFile[ serviceOrderFileId=" + serviceOrderFileId + " ]";
    }
    
}
