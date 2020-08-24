/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades.nominacion;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
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
import mx.tab.wgp.qsmaritimex.entidades.Country;

/**
 *
 * @author William
 */
@Entity
@Table(name = "ShipOwner")
@XmlRootElement
public class ShipOwner implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields
    // consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false, precision = 19, scale = 0)
    private BigInteger shipOwnerId;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String city;
    @Basic(optional = false)
    @Column(nullable = false, length = 60)
    private String contactEmail;
    @Basic(optional = false)
    @Column(nullable = false, length = 80)
    private String contactName;
    @Basic(optional = false)
    @Column(nullable = false, length = 25)
    private String contactPhone;
    @Basic(optional = false)
    @Column(nullable = false, length = 10)
    private String cp;
    @Basic
    @Column(name = "CreationDate")
    private LocalDateTime creationDate;
    @Column(precision = 19, scale = 2)
    private BigInteger customerIdAx;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String district;
    @Basic(optional = false)
    @Column(nullable = false, length = 15)
    private String rfc;
    @Basic(optional = false)
    @Column(nullable = false, length = 300)
    private String shipOwnerName;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String state;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String street;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String streetNumber;
    @Basic
    @Column(name = "UpdateDate")
    private LocalDateTime updateDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shipOwnerId")
    private Collection<ReferenceShipOwner> referenceShipOwnerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shipOwnerId")
    private Collection<ServiceOrder> serviceOrderCollection;
    @JoinColumn(name = "CountryId", referencedColumnName = "countryId", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Country countryId;
    // -------------------------
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shipOwnerId")
    private Collection<Line> lineCollection;

    public ShipOwner() {
        this.status = true;
        this.creationDate = LocalDateTime.now();
    }

    public ShipOwner(BigInteger shipOwnerId) {
        this.shipOwnerId = shipOwnerId;
    }

    public ShipOwner(BigInteger shipOwnerId, String city, String contactEmail, String contactName, String contactPhone,
            String cp, String district, String rfc, String shipOwnerName, String state, boolean status, String street,
            String streetNumber) {
        this.shipOwnerId = shipOwnerId;
        this.city = city;
        this.contactEmail = contactEmail;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.cp = cp;
        this.district = district;
        this.rfc = rfc;
        this.shipOwnerName = shipOwnerName;
        this.state = state;
        this.status = status;
        this.street = street;
        this.streetNumber = streetNumber;

        this.creationDate = LocalDateTime.now();
    }

    public Collection<Line> getLineCollection() {
        return lineCollection;
    }

    public void setLineCollection(Collection<Line> lineCollection) {
        this.lineCollection = lineCollection;
    }

    public BigInteger getShipOwnerId() {
        return shipOwnerId;
    }

    public void setShipOwnerId(BigInteger shipOwnerId) {
        this.shipOwnerId = shipOwnerId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public BigInteger getCustomerIdAx() {
        return customerIdAx;
    }

    public void setCustomerIdAx(BigInteger customerIdAx) {
        this.customerIdAx = customerIdAx;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getShipOwnerName() {
        return shipOwnerName;
    }

    public void setShipOwnerName(String shipOwnerName) {
        this.shipOwnerName = shipOwnerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @XmlTransient
    public Collection<ReferenceShipOwner> getReferenceShipOwnerCollection() {
        return referenceShipOwnerCollection;
    }

    public void setReferenceShipOwnerCollection(Collection<ReferenceShipOwner> referenceShipOwnerCollection) {
        this.referenceShipOwnerCollection = referenceShipOwnerCollection;
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
        if ((this.shipOwnerId == null && other.shipOwnerId != null)
                || (this.shipOwnerId != null && !this.shipOwnerId.equals(other.shipOwnerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ShipOwner[ shipOwnerId=" + shipOwnerId + " ]";
    }

}