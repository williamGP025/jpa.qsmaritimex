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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WilliamGP025
 */
@Entity
@Table(catalog = "QSMaritimex", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransmissionStatus.findAll", query = "SELECT t FROM TransmissionStatus t"),
    @NamedQuery(name = "TransmissionStatus.findByTransmissionStatusId", query = "SELECT t FROM TransmissionStatus t WHERE t.transmissionStatusId = :transmissionStatusId"),
    @NamedQuery(name = "TransmissionStatus.findByDescripcion", query = "SELECT t FROM TransmissionStatus t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TransmissionStatus.findByStatus", query = "SELECT t FROM TransmissionStatus t WHERE t.status = :status")})
public class TransmissionStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer transmissionStatusId;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String descripcion;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;

    public TransmissionStatus() {
    }

    public TransmissionStatus(Integer transmissionStatusId) {
        this.transmissionStatusId = transmissionStatusId;
    }

    public TransmissionStatus(Integer transmissionStatusId, String descripcion, boolean status) {
        this.transmissionStatusId = transmissionStatusId;
        this.descripcion = descripcion;
        this.status = status;
    }

    public Integer getTransmissionStatusId() {
        return transmissionStatusId;
    }

    public void setTransmissionStatusId(Integer transmissionStatusId) {
        this.transmissionStatusId = transmissionStatusId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transmissionStatusId != null ? transmissionStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransmissionStatus)) {
            return false;
        }
        TransmissionStatus other = (TransmissionStatus) object;
        if ((this.transmissionStatusId == null && other.transmissionStatusId != null) || (this.transmissionStatusId != null && !this.transmissionStatusId.equals(other.transmissionStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.TransmissionStatus[ transmissionStatusId=" + transmissionStatusId + " ]";
    }
    
}
