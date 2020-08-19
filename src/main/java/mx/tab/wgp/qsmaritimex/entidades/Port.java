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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Port.findAll", query = "SELECT p FROM Port p"),
    @NamedQuery(name = "Port.findByPortId", query = "SELECT p FROM Port p WHERE p.portId = :portId"),
    @NamedQuery(name = "Port.findByPortCode", query = "SELECT p FROM Port p WHERE p.portCode = :portCode"),
    @NamedQuery(name = "Port.findByPortName", query = "SELECT p FROM Port p WHERE p.portName = :portName"),
    @NamedQuery(name = "Port.findByStatus", query = "SELECT p FROM Port p WHERE p.status = :status")})
public class Port implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer portId;
    @Column(length = 50)
    private String portCode;
    @Column(length = 50)
    private String portName;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @JoinColumn(name = "CountryId", referencedColumnName = "CountryId", nullable = false)
    @ManyToOne(optional = false)
    private Country countryId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "startPortId")
    private Collection<Itinerary> itineraryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "endPortId")
    private Collection<Itinerary> itineraryCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "operatingPortId")
    private Collection<Itinerary> itineraryCollection2;

    public Port() {
    }

    public Port(Integer portId) {
        this.portId = portId;
    }

    public Port(Integer portId, boolean status) {
        this.portId = portId;
        this.status = status;
    }

    public Integer getPortId() {
        return portId;
    }

    public void setPortId(Integer portId) {
        this.portId = portId;
    }

    public String getPortCode() {
        return portCode;
    }

    public void setPortCode(String portCode) {
        this.portCode = portCode;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Country getCountryId() {
        return countryId;
    }

    public void setCountryId(Country countryId) {
        this.countryId = countryId;
    }

    @XmlTransient
    public Collection<Itinerary> getItineraryCollection() {
        return itineraryCollection;
    }

    public void setItineraryCollection(Collection<Itinerary> itineraryCollection) {
        this.itineraryCollection = itineraryCollection;
    }

    @XmlTransient
    public Collection<Itinerary> getItineraryCollection1() {
        return itineraryCollection1;
    }

    public void setItineraryCollection1(Collection<Itinerary> itineraryCollection1) {
        this.itineraryCollection1 = itineraryCollection1;
    }

    @XmlTransient
    public Collection<Itinerary> getItineraryCollection2() {
        return itineraryCollection2;
    }

    public void setItineraryCollection2(Collection<Itinerary> itineraryCollection2) {
        this.itineraryCollection2 = itineraryCollection2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (portId != null ? portId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Port)) {
            return false;
        }
        Port other = (Port) object;
        if ((this.portId == null && other.portId != null) || (this.portId != null && !this.portId.equals(other.portId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.Port[ portId=" + portId + " ]";
    }
    
}
