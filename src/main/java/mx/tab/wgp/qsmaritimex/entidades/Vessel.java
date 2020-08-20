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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author William
 */
@Entity
@XmlRootElement
@Table(name = "Vessel")
public class Vessel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Short vesselId;
    @Column(length = 255)
    private String bow;
    @Column(length = 255)
    private String callLetters;
    @Column(length = 255)
    private String capTEUS;
    @Column(length = 255)
    private String captain;
    @Column(length = 255)
    private String classification;
    @Column(length = 255)
    private String constructionYear;
    @Column(length = 255)
    private String craneCapacity;
    @Column(length = 255)
    private String cranes;
    @Column(length = 255)
    private String deadWeight;
    @Column(length = 255)
    private String draft;
    @Column(length = 255)
    private String email;
    @Column(length = 255)
    private String fax;
    @Column(length = 255)
    private String grt;
    @Column(length = 255)
    private String hatch;
    @Column(length = 255)
    private String holds;
    @Column(length = 255)
    private String lengthVessel;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String name;
    @Column(length = 255)
    private String nrt;
    @Column(length = 255)
    private String owner;
    @Column(length = 255)
    private String prop;
    @Column(length = 255)
    private String registryNumIMO;
    @Column(length = 255)
    private String satcom;
    @Column(length = 255)
    private String sleeve;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @Column(length = 255)
    private String stern;
    @Column(length = 255)
    private String telephone;
    @Column(length = 255)
    private String telex;
    @Column(length = 255)
    private String trafficType;
    //-------------------------------------------------------------    
    @JoinColumn(name = "ServiceTypeId", referencedColumnName = "ServiceTypeId", nullable = true)
    @ManyToOne(optional = true)
    private ServiceType serviceTypeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vesselId")
    private Collection<Itinerary> itineraryCollection;
    @JoinColumn(name = "CountryId", referencedColumnName = "countryId", nullable = false)
    @ManyToOne(optional = false)
    private Country countryId;
    @JoinColumn(name = "VesselTypeId", referencedColumnName = "vesselTypeId", nullable = false)
    @ManyToOne(optional = false)
    private VesselType vesselTypeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vesselId")
    private Collection<VesselPerLine> vesselPerLineCollection;

    public Vessel() {
    }

    public Vessel(Short vesselId) {
        this.vesselId = vesselId;
    }

    public Vessel(Short vesselId, String name, boolean status) {
        this.vesselId = vesselId;
        this.name = name;
        this.status = status;
    }

    public Short getVesselId() {
        return vesselId;
    }

    public void setVesselId(Short vesselId) {
        this.vesselId = vesselId;
    }

    public String getBow() {
        return bow;
    }

    public void setBow(String bow) {
        this.bow = bow;
    }

    public String getCallLetters() {
        return callLetters;
    }

    public void setCallLetters(String callLetters) {
        this.callLetters = callLetters;
    }

    public String getCapTEUS() {
        return capTEUS;
    }

    public void setCapTEUS(String capTEUS) {
        this.capTEUS = capTEUS;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(String constructionYear) {
        this.constructionYear = constructionYear;
    }

    public String getCraneCapacity() {
        return craneCapacity;
    }

    public void setCraneCapacity(String craneCapacity) {
        this.craneCapacity = craneCapacity;
    }

    public String getCranes() {
        return cranes;
    }

    public void setCranes(String cranes) {
        this.cranes = cranes;
    }

    public String getDeadWeight() {
        return deadWeight;
    }

    public void setDeadWeight(String deadWeight) {
        this.deadWeight = deadWeight;
    }

    public String getDraft() {
        return draft;
    }

    public void setDraft(String draft) {
        this.draft = draft;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getGrt() {
        return grt;
    }

    public void setGrt(String grt) {
        this.grt = grt;
    }

    public String getHatch() {
        return hatch;
    }

    public void setHatch(String hatch) {
        this.hatch = hatch;
    }

    public String getHolds() {
        return holds;
    }

    public void setHolds(String holds) {
        this.holds = holds;
    }

    public String getLengthVessel() {
        return lengthVessel;
    }

    public void setLengthVessel(String lengthVessel) {
        this.lengthVessel = lengthVessel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNrt() {
        return nrt;
    }

    public void setNrt(String nrt) {
        this.nrt = nrt;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public String getRegistryNumIMO() {
        return registryNumIMO;
    }

    public void setRegistryNumIMO(String registryNumIMO) {
        this.registryNumIMO = registryNumIMO;
    }

    public String getSatcom() {
        return satcom;
    }

    public void setSatcom(String satcom) {
        this.satcom = satcom;
    }

    public String getSleeve() {
        return sleeve;
    }

    public void setSleeve(String sleeve) {
        this.sleeve = sleeve;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStern() {
        return stern;
    }

    public void setStern(String stern) {
        this.stern = stern;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelex() {
        return telex;
    }

    public void setTelex(String telex) {
        this.telex = telex;
    }

    public String getTrafficType() {
        return trafficType;
    }

    public void setTrafficType(String trafficType) {
        this.trafficType = trafficType;
    }

    public ServiceType getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(ServiceType serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    @XmlTransient
    public Collection<Itinerary> getItineraryCollection() {
        return itineraryCollection;
    }

    public void setItineraryCollection(Collection<Itinerary> itineraryCollection) {
        this.itineraryCollection = itineraryCollection;
    }

    public Country getCountryId() {
        return countryId;
    }

    public void setCountryId(Country countryId) {
        this.countryId = countryId;
    }

    public VesselType getVesselTypeId() {
        return vesselTypeId;
    }

    public void setVesselTypeId(VesselType vesselTypeId) {
        this.vesselTypeId = vesselTypeId;
    }

    @XmlTransient
    public Collection<VesselPerLine> getVesselPerLineCollection() {
        return vesselPerLineCollection;
    }

    public void setVesselPerLineCollection(Collection<VesselPerLine> vesselPerLineCollection) {
        this.vesselPerLineCollection = vesselPerLineCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vesselId != null ? vesselId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vessel)) {
            return false;
        }
        Vessel other = (Vessel) object;
        if ((this.vesselId == null && other.vesselId != null) || (this.vesselId != null && !this.vesselId.equals(other.vesselId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.Vessel[ vesselId=" + vesselId + " ]";
    }

}
