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
    @NamedQuery(name = "VesselStatus.findAll", query = "SELECT v FROM VesselStatus v"),
    @NamedQuery(name = "VesselStatus.findByVesselStatusId", query = "SELECT v FROM VesselStatus v WHERE v.vesselStatusId = :vesselStatusId"),
    @NamedQuery(name = "VesselStatus.findByDescription", query = "SELECT v FROM VesselStatus v WHERE v.description = :description"),
    @NamedQuery(name = "VesselStatus.findByStatus", query = "SELECT v FROM VesselStatus v WHERE v.status = :status")})
public class VesselStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Short vesselStatusId;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String description;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vesselStatusId")
    private Collection<Itinerary> itineraryCollection;

    public VesselStatus() {
    }

    public VesselStatus(Short vesselStatusId) {
        this.vesselStatusId = vesselStatusId;
    }

    public VesselStatus(Short vesselStatusId, String description, boolean status) {
        this.vesselStatusId = vesselStatusId;
        this.description = description;
        this.status = status;
    }

    public Short getVesselStatusId() {
        return vesselStatusId;
    }

    public void setVesselStatusId(Short vesselStatusId) {
        this.vesselStatusId = vesselStatusId;
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
    public Collection<Itinerary> getItineraryCollection() {
        return itineraryCollection;
    }

    public void setItineraryCollection(Collection<Itinerary> itineraryCollection) {
        this.itineraryCollection = itineraryCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vesselStatusId != null ? vesselStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VesselStatus)) {
            return false;
        }
        VesselStatus other = (VesselStatus) object;
        if ((this.vesselStatusId == null && other.vesselStatusId != null) || (this.vesselStatusId != null && !this.vesselStatusId.equals(other.vesselStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.VesselStatus[ vesselStatusId=" + vesselStatusId + " ]";
    }
    
}
