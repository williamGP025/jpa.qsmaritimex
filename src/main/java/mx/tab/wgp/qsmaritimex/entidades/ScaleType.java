/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades;

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
    @NamedQuery(name = "ScaleType.findAll", query = "SELECT s FROM ScaleType s"),
    @NamedQuery(name = "ScaleType.findByScaleTypeId", query = "SELECT s FROM ScaleType s WHERE s.scaleTypeId = :scaleTypeId"),
    @NamedQuery(name = "ScaleType.findByDescription", query = "SELECT s FROM ScaleType s WHERE s.description = :description"),
    @NamedQuery(name = "ScaleType.findByStatus", query = "SELECT s FROM ScaleType s WHERE s.status = :status")})
public class ScaleType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Short scaleTypeId;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String description;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "scaleTypeId")
    private Collection<Itinerary> itineraryCollection;

    public ScaleType() {
    }

    public ScaleType(Short scaleTypeId) {
        this.scaleTypeId = scaleTypeId;
    }

    public ScaleType(Short scaleTypeId, String description, boolean status) {
        this.scaleTypeId = scaleTypeId;
        this.description = description;
        this.status = status;
    }

    public Short getScaleTypeId() {
        return scaleTypeId;
    }

    public void setScaleTypeId(Short scaleTypeId) {
        this.scaleTypeId = scaleTypeId;
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
        hash += (scaleTypeId != null ? scaleTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScaleType)) {
            return false;
        }
        ScaleType other = (ScaleType) object;
        if ((this.scaleTypeId == null && other.scaleTypeId != null) || (this.scaleTypeId != null && !this.scaleTypeId.equals(other.scaleTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ScaleType[ scaleTypeId=" + scaleTypeId + " ]";
    }
    
}
