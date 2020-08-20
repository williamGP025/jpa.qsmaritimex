/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades.nominacion;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author William
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReferenceShipOwner.findAll", query = "SELECT r FROM ReferenceShipOwner r"),
    @NamedQuery(name = "ReferenceShipOwner.findByReferenceShipOwnerId", query = "SELECT r FROM ReferenceShipOwner r WHERE r.referenceShipOwnerId = :referenceShipOwnerId"),
    @NamedQuery(name = "ReferenceShipOwner.findByStatus", query = "SELECT r FROM ReferenceShipOwner r WHERE r.status = :status")})
public class ReferenceShipOwner implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer referenceShipOwnerId;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @JoinColumn(name = "ServiceOrderId", referencedColumnName = "serviceOrderId", nullable = false)
    @ManyToOne(optional = false)
    private ServiceOrder serviceOrderId;
    @JoinColumn(name = "ShipOwnerId", referencedColumnName = "shipOwnerId", nullable = false)
    @ManyToOne(optional = false)
    private ShipOwner shipOwnerId;

    public ReferenceShipOwner() {
    }

    public ReferenceShipOwner(Integer referenceShipOwnerId) {
        this.referenceShipOwnerId = referenceShipOwnerId;
    }

    public ReferenceShipOwner(Integer referenceShipOwnerId, boolean status) {
        this.referenceShipOwnerId = referenceShipOwnerId;
        this.status = status;
    }

    public Integer getReferenceShipOwnerId() {
        return referenceShipOwnerId;
    }

    public void setReferenceShipOwnerId(Integer referenceShipOwnerId) {
        this.referenceShipOwnerId = referenceShipOwnerId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ServiceOrder getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(ServiceOrder serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public ShipOwner getShipOwnerId() {
        return shipOwnerId;
    }

    public void setShipOwnerId(ShipOwner shipOwnerId) {
        this.shipOwnerId = shipOwnerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (referenceShipOwnerId != null ? referenceShipOwnerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReferenceShipOwner)) {
            return false;
        }
        ReferenceShipOwner other = (ReferenceShipOwner) object;
        if ((this.referenceShipOwnerId == null && other.referenceShipOwnerId != null) || (this.referenceShipOwnerId != null && !this.referenceShipOwnerId.equals(other.referenceShipOwnerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ReferenceShipOwner[ referenceShipOwnerId=" + referenceShipOwnerId + " ]";
    }
    
}
