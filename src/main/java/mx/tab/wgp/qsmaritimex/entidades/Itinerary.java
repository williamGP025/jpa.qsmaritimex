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
    @NamedQuery(name = "Itinerary.findAll", query = "SELECT i FROM Itinerary i"),
    @NamedQuery(name = "Itinerary.findByItineraryId", query = "SELECT i FROM Itinerary i WHERE i.itineraryId = :itineraryId"),
    @NamedQuery(name = "Itinerary.findByJourneyNumber", query = "SELECT i FROM Itinerary i WHERE i.journeyNumber = :journeyNumber"),
    @NamedQuery(name = "Itinerary.findByDaysInPort", query = "SELECT i FROM Itinerary i WHERE i.daysInPort = :daysInPort"),
    @NamedQuery(name = "Itinerary.findByStatus", query = "SELECT i FROM Itinerary i WHERE i.status = :status")})
public class Itinerary implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long itineraryId;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String journeyNumber;
    @Basic(optional = false)
    @Column(nullable = false)
    private short daysInPort;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @JoinColumn(name = "ArrivalInformationId", referencedColumnName = "ArrivalInformationId", nullable = false)
    @ManyToOne(optional = false)
    private ArrivalInformation arrivalInformationId;
    @JoinColumn(name = "CargoTypeId", referencedColumnName = "CargoTypeId", nullable = false)
    @ManyToOne(optional = false)
    private CargoType cargoTypeId;
    @JoinColumn(name = "StartPortId", referencedColumnName = "PortId", nullable = false)
    @ManyToOne(optional = false)
    private Port startPortId;
    @JoinColumn(name = "EndPortId", referencedColumnName = "PortId", nullable = false)
    @ManyToOne(optional = false)
    private Port endPortId;
    @JoinColumn(name = "OperatingPortId", referencedColumnName = "PortId", nullable = false)
    @ManyToOne(optional = false)
    private Port operatingPortId;
    @JoinColumn(name = "ScaleTypeId", referencedColumnName = "ScaleTypeId", nullable = false)
    @ManyToOne(optional = false)
    private ScaleType scaleTypeId;
    @JoinColumn(name = "VesselId", referencedColumnName = "VesselId", nullable = false)
    @ManyToOne(optional = false)
    private Vessel vesselId;
    @JoinColumn(name = "VesselStatusId", referencedColumnName = "VesselStatusId", nullable = false)
    @ManyToOne(optional = false)
    private VesselStatus vesselStatusId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itineraryId")
    private Collection<ServiceOrder> serviceOrderCollection;

    public Itinerary() {
    }

    public Itinerary(Long itineraryId) {
        this.itineraryId = itineraryId;
    }

    public Itinerary(Long itineraryId, String journeyNumber, short daysInPort, boolean status) {
        this.itineraryId = itineraryId;
        this.journeyNumber = journeyNumber;
        this.daysInPort = daysInPort;
        this.status = status;
    }

    public Long getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(Long itineraryId) {
        this.itineraryId = itineraryId;
    }

    public String getJourneyNumber() {
        return journeyNumber;
    }

    public void setJourneyNumber(String journeyNumber) {
        this.journeyNumber = journeyNumber;
    }

    public short getDaysInPort() {
        return daysInPort;
    }

    public void setDaysInPort(short daysInPort) {
        this.daysInPort = daysInPort;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrivalInformation getArrivalInformationId() {
        return arrivalInformationId;
    }

    public void setArrivalInformationId(ArrivalInformation arrivalInformationId) {
        this.arrivalInformationId = arrivalInformationId;
    }

    public CargoType getCargoTypeId() {
        return cargoTypeId;
    }

    public void setCargoTypeId(CargoType cargoTypeId) {
        this.cargoTypeId = cargoTypeId;
    }

    public Port getStartPortId() {
        return startPortId;
    }

    public void setStartPortId(Port startPortId) {
        this.startPortId = startPortId;
    }

    public Port getEndPortId() {
        return endPortId;
    }

    public void setEndPortId(Port endPortId) {
        this.endPortId = endPortId;
    }

    public Port getOperatingPortId() {
        return operatingPortId;
    }

    public void setOperatingPortId(Port operatingPortId) {
        this.operatingPortId = operatingPortId;
    }

    public ScaleType getScaleTypeId() {
        return scaleTypeId;
    }

    public void setScaleTypeId(ScaleType scaleTypeId) {
        this.scaleTypeId = scaleTypeId;
    }

    public Vessel getVesselId() {
        return vesselId;
    }

    public void setVesselId(Vessel vesselId) {
        this.vesselId = vesselId;
    }

    public VesselStatus getVesselStatusId() {
        return vesselStatusId;
    }

    public void setVesselStatusId(VesselStatus vesselStatusId) {
        this.vesselStatusId = vesselStatusId;
    }

    @XmlTransient
    public Collection<ServiceOrder> getServiceOrderCollection() {
        return serviceOrderCollection;
    }

    public void setServiceOrderCollection(Collection<ServiceOrder> serviceOrderCollection) {
        this.serviceOrderCollection = serviceOrderCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itineraryId != null ? itineraryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itinerary)) {
            return false;
        }
        Itinerary other = (Itinerary) object;
        if ((this.itineraryId == null && other.itineraryId != null) || (this.itineraryId != null && !this.itineraryId.equals(other.itineraryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.Itinerary[ itineraryId=" + itineraryId + " ]";
    }
    
}
