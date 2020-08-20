/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades.nominacion;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author William
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VesselType.findAll", query = "SELECT v FROM VesselType v"),
    @NamedQuery(name = "VesselType.findByVesselTypeId", query = "SELECT v FROM VesselType v WHERE v.vesselTypeId = :vesselTypeId"),
    @NamedQuery(name = "VesselType.findByDescriptionMX", query = "SELECT v FROM VesselType v WHERE v.descriptionMX = :descriptionMX"),
    @NamedQuery(name = "VesselType.findByDescriptionUS", query = "SELECT v FROM VesselType v WHERE v.descriptionUS = :descriptionUS"),
    @NamedQuery(name = "VesselType.findByStatus", query = "SELECT v FROM VesselType v WHERE v.status = :status")})
public class VesselType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer vesselTypeId;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String descriptionMX;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String descriptionUS;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vesselTypeId")
    private Collection<Vessel> vesselCollection;

    public VesselType() {
    }

    public VesselType(Integer vesselTypeId) {
        this.vesselTypeId = vesselTypeId;
    }

    public VesselType(Integer vesselTypeId, String descriptionMX, String descriptionUS, boolean status) {
        this.vesselTypeId = vesselTypeId;
        this.descriptionMX = descriptionMX;
        this.descriptionUS = descriptionUS;
        this.status = status;
    }

    public Integer getVesselTypeId() {
        return vesselTypeId;
    }

    public void setVesselTypeId(Integer vesselTypeId) {
        this.vesselTypeId = vesselTypeId;
    }

    public String getDescriptionMX() {
        return descriptionMX;
    }

    public void setDescriptionMX(String descriptionMX) {
        this.descriptionMX = descriptionMX;
    }

    public String getDescriptionUS() {
        return descriptionUS;
    }

    public void setDescriptionUS(String descriptionUS) {
        this.descriptionUS = descriptionUS;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Vessel> getVesselCollection() {
        return vesselCollection;
    }

    public void setVesselCollection(Collection<Vessel> vesselCollection) {
        this.vesselCollection = vesselCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vesselTypeId != null ? vesselTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VesselType)) {
            return false;
        }
        VesselType other = (VesselType) object;
        if ((this.vesselTypeId == null && other.vesselTypeId != null) || (this.vesselTypeId != null && !this.vesselTypeId.equals(other.vesselTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.VesselType[ vesselTypeId=" + vesselTypeId + " ]";
    }
    
}
