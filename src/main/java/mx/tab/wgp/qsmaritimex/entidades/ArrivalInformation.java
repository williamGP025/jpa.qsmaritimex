/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author William
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArrivalInformation.findAll", query = "SELECT a FROM ArrivalInformation a"),
    @NamedQuery(name = "ArrivalInformation.findByArrivalInformationId", query = "SELECT a FROM ArrivalInformation a WHERE a.arrivalInformationId = :arrivalInformationId"),
    @NamedQuery(name = "ArrivalInformation.findByAta", query = "SELECT a FROM ArrivalInformation a WHERE a.ata = :ata"),
    @NamedQuery(name = "ArrivalInformation.findByAtb", query = "SELECT a FROM ArrivalInformation a WHERE a.atb = :atb"),
    @NamedQuery(name = "ArrivalInformation.findByAts", query = "SELECT a FROM ArrivalInformation a WHERE a.ats = :ats"),
    @NamedQuery(name = "ArrivalInformation.findByDescription", query = "SELECT a FROM ArrivalInformation a WHERE a.description = :description"),
    @NamedQuery(name = "ArrivalInformation.findByEta", query = "SELECT a FROM ArrivalInformation a WHERE a.eta = :eta"),
    @NamedQuery(name = "ArrivalInformation.findByEtb", query = "SELECT a FROM ArrivalInformation a WHERE a.etb = :etb"),
    @NamedQuery(name = "ArrivalInformation.findByEts", query = "SELECT a FROM ArrivalInformation a WHERE a.ets = :ets"),
    @NamedQuery(name = "ArrivalInformation.findByShipStatus", query = "SELECT a FROM ArrivalInformation a WHERE a.shipStatus = :shipStatus"),
    @NamedQuery(name = "ArrivalInformation.findByStatus", query = "SELECT a FROM ArrivalInformation a WHERE a.status = :status")})
public class ArrivalInformation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer arrivalInformationId;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ata;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date atb;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ats;
    @Basic(optional = false)
    @Column(nullable = false, length = 500)
    private String description;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date eta;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date etb;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ets;
    @Basic(optional = false)
    @Column(nullable = false)
    private int shipStatus;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "arrivalInformationId")
    private Collection<Itinerary> itineraryCollection;

    public ArrivalInformation() {
    }

    public ArrivalInformation(Integer arrivalInformationId) {
        this.arrivalInformationId = arrivalInformationId;
    }

    public ArrivalInformation(Integer arrivalInformationId, Date ata, Date atb, Date ats, String description, Date eta, Date etb, Date ets, int shipStatus, boolean status) {
        this.arrivalInformationId = arrivalInformationId;
        this.ata = ata;
        this.atb = atb;
        this.ats = ats;
        this.description = description;
        this.eta = eta;
        this.etb = etb;
        this.ets = ets;
        this.shipStatus = shipStatus;
        this.status = status;
    }

    public Integer getArrivalInformationId() {
        return arrivalInformationId;
    }

    public void setArrivalInformationId(Integer arrivalInformationId) {
        this.arrivalInformationId = arrivalInformationId;
    }

    public Date getAta() {
        return ata;
    }

    public void setAta(Date ata) {
        this.ata = ata;
    }

    public Date getAtb() {
        return atb;
    }

    public void setAtb(Date atb) {
        this.atb = atb;
    }

    public Date getAts() {
        return ats;
    }

    public void setAts(Date ats) {
        this.ats = ats;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEta() {
        return eta;
    }

    public void setEta(Date eta) {
        this.eta = eta;
    }

    public Date getEtb() {
        return etb;
    }

    public void setEtb(Date etb) {
        this.etb = etb;
    }

    public Date getEts() {
        return ets;
    }

    public void setEts(Date ets) {
        this.ets = ets;
    }

    public int getShipStatus() {
        return shipStatus;
    }

    public void setShipStatus(int shipStatus) {
        this.shipStatus = shipStatus;
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
        hash += (arrivalInformationId != null ? arrivalInformationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArrivalInformation)) {
            return false;
        }
        ArrivalInformation other = (ArrivalInformation) object;
        if ((this.arrivalInformationId == null && other.arrivalInformationId != null) || (this.arrivalInformationId != null && !this.arrivalInformationId.equals(other.arrivalInformationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ArrivalInformation[ arrivalInformationId=" + arrivalInformationId + " ]";
    }
    
}
