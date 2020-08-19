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
    @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c"),
    @NamedQuery(name = "Country.findByCountryId", query = "SELECT c FROM Country c WHERE c.countryId = :countryId"),
    @NamedQuery(name = "Country.findByCountryCode", query = "SELECT c FROM Country c WHERE c.countryCode = :countryCode"),
    @NamedQuery(name = "Country.findByDescriptionMX", query = "SELECT c FROM Country c WHERE c.descriptionMX = :descriptionMX"),
    @NamedQuery(name = "Country.findByDescriptionUS", query = "SELECT c FROM Country c WHERE c.descriptionUS = :descriptionUS"),
    @NamedQuery(name = "Country.findByStatus", query = "SELECT c FROM Country c WHERE c.status = :status")})
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer countryId;
    @Column(length = 50)
    private String countryCode;
    @Column(length = 200)
    private String descriptionMX;
    @Column(length = 200)
    private String descriptionUS;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "countryId")
    private Collection<Port> portCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "countryId")
    private Collection<Vessel> vesselCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "countryId")
    private Collection<ShipOwner> shipOwnerCollection;

    public Country() {
    }

    public Country(Integer countryId) {
        this.countryId = countryId;
    }

    public Country(Integer countryId, boolean status) {
        this.countryId = countryId;
        this.status = status;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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
    public Collection<Port> getPortCollection() {
        return portCollection;
    }

    public void setPortCollection(Collection<Port> portCollection) {
        this.portCollection = portCollection;
    }

    @XmlTransient
    public Collection<Vessel> getVesselCollection() {
        return vesselCollection;
    }

    public void setVesselCollection(Collection<Vessel> vesselCollection) {
        this.vesselCollection = vesselCollection;
    }

    @XmlTransient
    public Collection<ShipOwner> getShipOwnerCollection() {
        return shipOwnerCollection;
    }

    public void setShipOwnerCollection(Collection<ShipOwner> shipOwnerCollection) {
        this.shipOwnerCollection = shipOwnerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (countryId != null ? countryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        if ((this.countryId == null && other.countryId != null) || (this.countryId != null && !this.countryId.equals(other.countryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.Country[ countryId=" + countryId + " ]";
    }
    
}
