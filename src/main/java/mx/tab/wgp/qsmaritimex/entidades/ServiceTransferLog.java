/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author William
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceTransferLog.findAll", query = "SELECT s FROM ServiceTransferLog s"),
    @NamedQuery(name = "ServiceTransferLog.findByServiceTransferLogId", query = "SELECT s FROM ServiceTransferLog s WHERE s.serviceTransferLogId = :serviceTransferLogId"),
    @NamedQuery(name = "ServiceTransferLog.findByAmount", query = "SELECT s FROM ServiceTransferLog s WHERE s.amount = :amount"),
    @NamedQuery(name = "ServiceTransferLog.findByNote", query = "SELECT s FROM ServiceTransferLog s WHERE s.note = :note"),
    @NamedQuery(name = "ServiceTransferLog.findByServiceOrderEndId", query = "SELECT s FROM ServiceTransferLog s WHERE s.serviceOrderEndId = :serviceOrderEndId"),
    @NamedQuery(name = "ServiceTransferLog.findByServiceOrderOriginId", query = "SELECT s FROM ServiceTransferLog s WHERE s.serviceOrderOriginId = :serviceOrderOriginId"),
    @NamedQuery(name = "ServiceTransferLog.findByServiceOrderServicesEndId", query = "SELECT s FROM ServiceTransferLog s WHERE s.serviceOrderServicesEndId = :serviceOrderServicesEndId"),
    @NamedQuery(name = "ServiceTransferLog.findByServiceOrderServicesOriginId", query = "SELECT s FROM ServiceTransferLog s WHERE s.serviceOrderServicesOriginId = :serviceOrderServicesOriginId"),
    @NamedQuery(name = "ServiceTransferLog.findByStatus", query = "SELECT s FROM ServiceTransferLog s WHERE s.status = :status"),
    @NamedQuery(name = "ServiceTransferLog.findByUpdatedBy", query = "SELECT s FROM ServiceTransferLog s WHERE s.updatedBy = :updatedBy"),
    @NamedQuery(name = "ServiceTransferLog.findByUpdatedOn", query = "SELECT s FROM ServiceTransferLog s WHERE s.updatedOn = :updatedOn")})
public class ServiceTransferLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer serviceTransferLogId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 19, scale = 4)
    private BigDecimal amount;
    @Column(length = 1000)
    private String note;
    private Integer serviceOrderEndId;
    private Integer serviceOrderOriginId;
    private Integer serviceOrderServicesEndId;
    private Integer serviceOrderServicesOriginId;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @Column(length = 50)
    private String updatedBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;
    @OneToMany(mappedBy = "serviceTransferLogId")
    private Collection<ServiceOrderService> serviceOrderServiceCollection;

    public ServiceTransferLog() {
    }

    public ServiceTransferLog(Integer serviceTransferLogId) {
        this.serviceTransferLogId = serviceTransferLogId;
    }

    public ServiceTransferLog(Integer serviceTransferLogId, boolean status) {
        this.serviceTransferLogId = serviceTransferLogId;
        this.status = status;
    }

    public Integer getServiceTransferLogId() {
        return serviceTransferLogId;
    }

    public void setServiceTransferLogId(Integer serviceTransferLogId) {
        this.serviceTransferLogId = serviceTransferLogId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getServiceOrderEndId() {
        return serviceOrderEndId;
    }

    public void setServiceOrderEndId(Integer serviceOrderEndId) {
        this.serviceOrderEndId = serviceOrderEndId;
    }

    public Integer getServiceOrderOriginId() {
        return serviceOrderOriginId;
    }

    public void setServiceOrderOriginId(Integer serviceOrderOriginId) {
        this.serviceOrderOriginId = serviceOrderOriginId;
    }

    public Integer getServiceOrderServicesEndId() {
        return serviceOrderServicesEndId;
    }

    public void setServiceOrderServicesEndId(Integer serviceOrderServicesEndId) {
        this.serviceOrderServicesEndId = serviceOrderServicesEndId;
    }

    public Integer getServiceOrderServicesOriginId() {
        return serviceOrderServicesOriginId;
    }

    public void setServiceOrderServicesOriginId(Integer serviceOrderServicesOriginId) {
        this.serviceOrderServicesOriginId = serviceOrderServicesOriginId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
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
        hash += (serviceTransferLogId != null ? serviceTransferLogId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceTransferLog)) {
            return false;
        }
        ServiceTransferLog other = (ServiceTransferLog) object;
        if ((this.serviceTransferLogId == null && other.serviceTransferLogId != null) || (this.serviceTransferLogId != null && !this.serviceTransferLogId.equals(other.serviceTransferLogId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ServiceTransferLog[ serviceTransferLogId=" + serviceTransferLogId + " ]";
    }
    
}
