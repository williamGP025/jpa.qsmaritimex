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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author WilliamGP025
 */
@Entity
@Table(catalog = "QSMaritimex", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CargoType.findAll", query = "SELECT c FROM CargoType c"),
    @NamedQuery(name = "CargoType.findByCargoTypeId", query = "SELECT c FROM CargoType c WHERE c.cargoTypeId = :cargoTypeId"),
    @NamedQuery(name = "CargoType.findByDescription", query = "SELECT c FROM CargoType c WHERE c.description = :description"),
    @NamedQuery(name = "CargoType.findByStatus", query = "SELECT c FROM CargoType c WHERE c.status = :status")})
public class CargoType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Short cargoTypeId;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String description;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargoTypeId")
    private Collection<Itinerary> itineraryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargoTypeId")
    private Collection<UserCargoType> userCargoTypeCollection;

    public CargoType() {
    }

    public CargoType(Short cargoTypeId) {
        this.cargoTypeId = cargoTypeId;
    }

    public CargoType(Short cargoTypeId, String description, boolean status) {
        this.cargoTypeId = cargoTypeId;
        this.description = description;
        this.status = status;
    }

    public Short getCargoTypeId() {
        return cargoTypeId;
    }

    public void setCargoTypeId(Short cargoTypeId) {
        this.cargoTypeId = cargoTypeId;
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

    @XmlTransient
    public Collection<UserCargoType> getUserCargoTypeCollection() {
        return userCargoTypeCollection;
    }

    public void setUserCargoTypeCollection(Collection<UserCargoType> userCargoTypeCollection) {
        this.userCargoTypeCollection = userCargoTypeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cargoTypeId != null ? cargoTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CargoType)) {
            return false;
        }
        CargoType other = (CargoType) object;
        if ((this.cargoTypeId == null && other.cargoTypeId != null) || (this.cargoTypeId != null && !this.cargoTypeId.equals(other.cargoTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.CargoType[ cargoTypeId=" + cargoTypeId + " ]";
    }
    
}
