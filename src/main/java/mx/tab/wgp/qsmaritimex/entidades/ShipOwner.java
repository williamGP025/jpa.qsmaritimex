/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "ShipOwner.findAll", query = "SELECT s FROM ShipOwner s"),
    @NamedQuery(name = "ShipOwner.findByShipOwnerId", query = "SELECT s FROM ShipOwner s WHERE s.shipOwnerId = :shipOwnerId"),
    @NamedQuery(name = "ShipOwner.findByShipOwnerName", query = "SELECT s FROM ShipOwner s WHERE s.shipOwnerName = :shipOwnerName"),
    @NamedQuery(name = "ShipOwner.findByRfc", query = "SELECT s FROM ShipOwner s WHERE s.rfc = :rfc"),
    @NamedQuery(name = "ShipOwner.findByDistrict", query = "SELECT s FROM ShipOwner s WHERE s.district = :district"),
    @NamedQuery(name = "ShipOwner.findByState", query = "SELECT s FROM ShipOwner s WHERE s.state = :state"),
    @NamedQuery(name = "ShipOwner.findByStreet", query = "SELECT s FROM ShipOwner s WHERE s.street = :street"),
    @NamedQuery(name = "ShipOwner.findByStreetNumber", query = "SELECT s FROM ShipOwner s WHERE s.streetNumber = :streetNumber"),
    @NamedQuery(name = "ShipOwner.findByCp", query = "SELECT s FROM ShipOwner s WHERE s.cp = :cp"),
    @NamedQuery(name = "ShipOwner.findByCity", query = "SELECT s FROM ShipOwner s WHERE s.city = :city"),
    @NamedQuery(name = "ShipOwner.findByContactName", query = "SELECT s FROM ShipOwner s WHERE s.contactName = :contactName"),
    @NamedQuery(name = "ShipOwner.findByContactEmail", query = "SELECT s FROM ShipOwner s WHERE s.contactEmail = :contactEmail"),
    @NamedQuery(name = "ShipOwner.findByContactPhone", query = "SELECT s FROM ShipOwner s WHERE s.contactPhone = :contactPhone"),
    @NamedQuery(name = "ShipOwner.findByStatus", query = "SELECT s FROM ShipOwner s WHERE s.status = :status"),
    @NamedQuery(name = "ShipOwner.findByCreationDate", query = "SELECT s FROM ShipOwner s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ShipOwner.findByUpdateDate", query = "SELECT s FROM ShipOwner s WHERE s.updateDate = :updateDate"),
    @NamedQuery(name = "ShipOwner.findByCustomerIdAx", query = "SELECT s FROM ShipOwner s WHERE s.customerIdAx = :customerIdAx")})
public class ShipOwner implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long shipOwnerId;
    @Basic(optional = false)
    @Column(nullable = false, length = 300)
    private String shipOwnerName;
    @Basic(optional = false)
    @Column(nullable = false, length = 15)
    private String rfc;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String district;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String state;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String street;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String streetNumber;
    @Basic(optional = false)
    @Column(nullable = false, length = 10)
    private String cp;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String city;
    @Basic(optional = false)
    @Column(nullable = false, length = 80)
    private String contactName;
    @Basic(optional = false)
    @Column(nullable = false, length = 60)
    private String contactEmail;
    @Basic(optional = false)
    @Column(nullable = false, length = 25)
    private String contactPhone;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    private BigInteger customerIdAx;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shipOwnerId")
    private Collection<ReferenceShipOwner> referenceShipOwnerCollection;
    @OneToMany(mappedBy = "shipOwnerId")
    private Collection<Line> lineCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shipOwnerId")
    private Collection<ServiceOrder> serviceOrderCollection;
    @JoinColumn(name = "CountryId", referencedColumnName = "CountryId", nullable = false)
    @ManyToOne(optional = false)
    private Country countryId;

    public ShipOwner() {
    }

    public ShipOwner(Long shipOwnerId) {
        this.shipOwnerId = shipOwnerId;
    }

    public ShipOwner(Long shipOwnerId, String shipOwnerName, String rfc, String district, String state, String street, String streetNumber, String cp, String city, String contactName, String contactEmail, String contactPhone, boolean status) {
        this.shipOwnerId = shipOwnerId;
        this.shipOwnerName = shipOwnerName;
        this.rfc = rfc;
        this.district = district;
        this.state = state;
        this.street = street;
        this.streetNumber = streetNumber;
        this.cp = cp;
        this.city = city;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.status = status;
    }

    public Long getShipOwnerId() {
        return shipOwnerId;
    }

    public void setShipOwnerId(Long shipOwnerId) {
        this.shipOwnerId = shipOwnerId;
    }

    public String getShipOwnerName() {
        return shipOwnerName;
    }

    public void setShipOwnerName(String shipOwnerName) {
        this.shipOwnerName = shipOwnerName;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public BigInteger getCustomerIdAx() {
        return customerIdAx;
    }

    public void setCustomerIdAx(BigInteger customerIdAx) {
        this.customerIdAx = customerIdAx;
    }

    @XmlTransient
    public Collection<ReferenceShipOwner> getReferenceShipOwnerCollection() {
        return referenceShipOwnerCollection;
    }

    public void setReferenceShipOwnerCollection(Collection<ReferenceShipOwner> referenceShipOwnerCollection) {
        this.referenceShipOwnerCollection = referenceShipOwnerCollection;
    }

    @XmlTransient
    public Collection<Line> getLineCollection() {
        return lineCollection;
    }

    public void setLineCollection(Collection<Line> lineCollection) {
        this.lineCollection = lineCollection;
    }

    @XmlTransient
    public Collection<ServiceOrder> getServiceOrderCollection() {
        return serviceOrderCollection;
    }

    public void setServiceOrderCollection(Collection<ServiceOrder> serviceOrderCollection) {
        this.serviceOrderCollection = serviceOrderCollection;
    }

    public Country getCountryId() {
        return countryId;
    }

    public void setCountryId(Country countryId) {
        this.countryId = countryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shipOwnerId != null ? shipOwnerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShipOwner)) {
            return false;
        }
        ShipOwner other = (ShipOwner) object;
        if ((this.shipOwnerId == null && other.shipOwnerId != null) || (this.shipOwnerId != null && !this.shipOwnerId.equals(other.shipOwnerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ShipOwner[ shipOwnerId=" + shipOwnerId + " ]";
    }
    
}
