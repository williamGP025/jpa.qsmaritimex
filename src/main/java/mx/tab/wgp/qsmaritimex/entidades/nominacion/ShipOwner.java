/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades.nominacion;

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
import mx.tab.wgp.qsmaritimex.entidades.Country;

/**
 *
 * @author William
 */
@Entity
@Table(name = "ShipOwner")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShipOwner.findAll", query = "SELECT s FROM ShipOwner s"),
    @NamedQuery(name = "ShipOwner.findByShipOwnerId", query = "SELECT s FROM ShipOwner s WHERE s.shipOwnerId = :shipOwnerId"),
    @NamedQuery(name = "ShipOwner.findByCity", query = "SELECT s FROM ShipOwner s WHERE s.city = :city"),
    @NamedQuery(name = "ShipOwner.findByContactEmail", query = "SELECT s FROM ShipOwner s WHERE s.contactEmail = :contactEmail"),
    @NamedQuery(name = "ShipOwner.findByContactName", query = "SELECT s FROM ShipOwner s WHERE s.contactName = :contactName"),
    @NamedQuery(name = "ShipOwner.findByContactPhone", query = "SELECT s FROM ShipOwner s WHERE s.contactPhone = :contactPhone"),
    @NamedQuery(name = "ShipOwner.findByCp", query = "SELECT s FROM ShipOwner s WHERE s.cp = :cp"),
    @NamedQuery(name = "ShipOwner.findByCreationDate", query = "SELECT s FROM ShipOwner s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ShipOwner.findByCustomerIdAx", query = "SELECT s FROM ShipOwner s WHERE s.customerIdAx = :customerIdAx"),
    @NamedQuery(name = "ShipOwner.findByDistrict", query = "SELECT s FROM ShipOwner s WHERE s.district = :district"),
    @NamedQuery(name = "ShipOwner.findByRfc", query = "SELECT s FROM ShipOwner s WHERE s.rfc = :rfc"),
    @NamedQuery(name = "ShipOwner.findByShipOwnerName", query = "SELECT s FROM ShipOwner s WHERE s.shipOwnerName = :shipOwnerName"),
    @NamedQuery(name = "ShipOwner.findByState", query = "SELECT s FROM ShipOwner s WHERE s.state = :state"),
    @NamedQuery(name = "ShipOwner.findByStatus", query = "SELECT s FROM ShipOwner s WHERE s.status = :status"),
    @NamedQuery(name = "ShipOwner.findByStreet", query = "SELECT s FROM ShipOwner s WHERE s.street = :street"),
    @NamedQuery(name = "ShipOwner.findByStreetNumber", query = "SELECT s FROM ShipOwner s WHERE s.streetNumber = :streetNumber"),
    @NamedQuery(name = "ShipOwner.findByUpdateDate", query = "SELECT s FROM ShipOwner s WHERE s.updateDate = :updateDate")})
public class ShipOwner implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
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
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
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
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shipOwnerId")
    private Collection<ReferenceShipOwner> referenceShipOwnerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shipOwnerId")
    private Collection<ServiceOrder> serviceOrderCollection;
    @JoinColumn(name = "CountryId", referencedColumnName = "countryId", nullable = false)
    @ManyToOne(optional = false)
    private Country countryId;
    //-------------------------
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shipOwnerId")
    private Collection<Line> lineCollection;

    public Collection<Line> getLineCollection() {
        return lineCollection;
    }

    public void setLineCollection(Collection<Line> lineCollection) {
        this.lineCollection = lineCollection;
    }

    public ShipOwner() {
    }

    public ShipOwner(BigInteger shipOwnerId) {
        this.shipOwnerId = shipOwnerId;
    }

    public ShipOwner(BigInteger shipOwnerId, String city, String contactEmail, String contactName, String contactPhone, String cp, String district, String rfc, String shipOwnerName, String state, boolean status, String street, String streetNumber) {
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
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
