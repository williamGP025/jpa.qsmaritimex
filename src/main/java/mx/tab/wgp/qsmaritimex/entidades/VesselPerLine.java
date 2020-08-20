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
 * @author William
 */
@Entity
@XmlRootElement
@Table(name = "VesselPerLine")
@NamedQueries({
    @NamedQuery(name = "VesselPerLine.findAll", query = "SELECT v FROM VesselPerLine v"),
    @NamedQuery(name = "VesselPerLine.findByVesselPerLineId", query = "SELECT v FROM VesselPerLine v WHERE v.vesselPerLineId = :vesselPerLineId"),
    @NamedQuery(name = "VesselPerLine.findByStatus", query = "SELECT v FROM VesselPerLine v WHERE v.status = :status"),
    @NamedQuery(name = "VesselPerLine.findByLineId", query = "SELECT v FROM VesselPerLine v WHERE v.lineId = :lineId")})
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
    @JoinColumn(name = "VesselId", referencedColumnName = "vesselId", nullable = false)
    @ManyToOne(optional = false)
    private Vessel vesselId;
    //-----------------------------------
    @JoinColumn(name = "LineId", referencedColumnName = "lineId", nullable = false)
    @ManyToOne(optional = false)
    private Line lineId;

    public VesselPerLine() {
    }

    public VesselPerLine(Integer vesselPerLineId) {
        this.vesselPerLineId = vesselPerLineId;
    }

    public VesselPerLine(Integer vesselPerLineId, boolean status, Line lineId) {
        this.vesselPerLineId = vesselPerLineId;
        this.status = status;
        this.lineId = lineId;
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
    public String toString() {
        return "VesselPerLine{" + "vesselPerLineId=" + vesselPerLineId + ", status=" + status + ", vesselId=" + vesselId + ", lineId=" + lineId + '}';
    }
}