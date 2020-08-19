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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "VesselPerLine.findAll", query = "SELECT v FROM VesselPerLine v"),
    @NamedQuery(name = "VesselPerLine.findByVesselPerLineId", query = "SELECT v FROM VesselPerLine v WHERE v.vesselPerLineId = :vesselPerLineId"),
    @NamedQuery(name = "VesselPerLine.findByStatus", query = "SELECT v FROM VesselPerLine v WHERE v.status = :status")})
public class VesselPerLine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer vesselPerLineId;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @JoinColumn(name = "LineId", referencedColumnName = "LineId", nullable = false)
    @ManyToOne(optional = false)
    private Line lineId;
    @JoinColumn(name = "VesselId", referencedColumnName = "VesselId", nullable = false)
    @ManyToOne(optional = false)
    private Vessel vesselId;

    public VesselPerLine() {
    }

    public VesselPerLine(Integer vesselPerLineId) {
        this.vesselPerLineId = vesselPerLineId;
    }

    public VesselPerLine(Integer vesselPerLineId, boolean status) {
        this.vesselPerLineId = vesselPerLineId;
        this.status = status;
    }

    public Integer getVesselPerLineId() {
        return vesselPerLineId;
    }

    public void setVesselPerLineId(Integer vesselPerLineId) {
        this.vesselPerLineId = vesselPerLineId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Line getLineId() {
        return lineId;
    }

    public void setLineId(Line lineId) {
        this.lineId = lineId;
    }

    public Vessel getVesselId() {
        return vesselId;
    }

    public void setVesselId(Vessel vesselId) {
        this.vesselId = vesselId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vesselPerLineId != null ? vesselPerLineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VesselPerLine)) {
            return false;
        }
        VesselPerLine other = (VesselPerLine) object;
        if ((this.vesselPerLineId == null && other.vesselPerLineId != null) || (this.vesselPerLineId != null && !this.vesselPerLineId.equals(other.vesselPerLineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.VesselPerLine[ vesselPerLineId=" + vesselPerLineId + " ]";
    }
    
}
