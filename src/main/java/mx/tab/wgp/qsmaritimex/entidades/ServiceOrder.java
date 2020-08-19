/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
    @NamedQuery(name = "ServiceOrder.findAll", query = "SELECT s FROM ServiceOrder s"),
    @NamedQuery(name = "ServiceOrder.findByServiceOrderId", query = "SELECT s FROM ServiceOrder s WHERE s.serviceOrderId = :serviceOrderId"),
    @NamedQuery(name = "ServiceOrder.findByServiceOrderCode", query = "SELECT s FROM ServiceOrder s WHERE s.serviceOrderCode = :serviceOrderCode"),
    @NamedQuery(name = "ServiceOrder.findByServiceOrderCreationDate", query = "SELECT s FROM ServiceOrder s WHERE s.serviceOrderCreationDate = :serviceOrderCreationDate"),
    @NamedQuery(name = "ServiceOrder.findByExchangeRate", query = "SELECT s FROM ServiceOrder s WHERE s.exchangeRate = :exchangeRate"),
    @NamedQuery(name = "ServiceOrder.findByFinalExchangeRate", query = "SELECT s FROM ServiceOrder s WHERE s.finalExchangeRate = :finalExchangeRate"),
    @NamedQuery(name = "ServiceOrder.findByOperatingInstructions", query = "SELECT s FROM ServiceOrder s WHERE s.operatingInstructions = :operatingInstructions"),
    @NamedQuery(name = "ServiceOrder.findByComments", query = "SELECT s FROM ServiceOrder s WHERE s.comments = :comments"),
    @NamedQuery(name = "ServiceOrder.findByBillingAdjustment", query = "SELECT s FROM ServiceOrder s WHERE s.billingAdjustment = :billingAdjustment"),
    @NamedQuery(name = "ServiceOrder.findByPaymentReceiptId", query = "SELECT s FROM ServiceOrder s WHERE s.paymentReceiptId = :paymentReceiptId"),
    @NamedQuery(name = "ServiceOrder.findByExchangeModified", query = "SELECT s FROM ServiceOrder s WHERE s.exchangeModified = :exchangeModified"),
    @NamedQuery(name = "ServiceOrder.findByTypeServiceOrder", query = "SELECT s FROM ServiceOrder s WHERE s.typeServiceOrder = :typeServiceOrder"),
    @NamedQuery(name = "ServiceOrder.findByStatus", query = "SELECT s FROM ServiceOrder s WHERE s.status = :status")})
public class ServiceOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long serviceOrderId;
    @Basic(optional = false)
    @Column(nullable = false, length = 8)
    private String serviceOrderCode;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date serviceOrderCreationDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 12, scale = 4)
    private BigDecimal exchangeRate;
    @Column(precision = 12, scale = 4)
    private BigDecimal finalExchangeRate;
    @Basic(optional = false)
    @Column(nullable = false, length = 500)
    private String operatingInstructions;
    @Column(length = 500)
    private String comments;
    @Column(precision = 12, scale = 4)
    private BigDecimal billingAdjustment;
    private BigInteger paymentReceiptId;
    private Boolean exchangeModified;
    private Boolean typeServiceOrder;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceOrderId")
    private Collection<ServiceOrderService> serviceOrderServiceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceOrderId")
    private Collection<ReferenceShipOwner> referenceShipOwnerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceOrderId")
    private Collection<ServiceOrderFile> serviceOrderFileCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceOrderId")
    private Collection<ActingRoles> actingRolesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceOrder")
    private Collection<ServiceOrderProductType> serviceOrderProductTypeCollection;
    @OneToMany(mappedBy = "serviceOrderIdFather")
    private Collection<ServiceOrderJoin> serviceOrderJoinCollection;
    @OneToMany(mappedBy = "serviceOrderIdSon")
    private Collection<ServiceOrderJoin> serviceOrderJoinCollection1;
    @JoinColumn(name = "CurrencyId", referencedColumnName = "CurrencyId", nullable = false)
    @ManyToOne(optional = false)
    private Currency currencyId;
    @JoinColumn(name = "ItineraryId", referencedColumnName = "ItineraryId", nullable = false)
    @ManyToOne(optional = false)
    private Itinerary itineraryId;
    @JoinColumn(name = "LineId", referencedColumnName = "LineId", nullable = false)
    @ManyToOne(optional = false)
    private Line lineId;
    @JoinColumn(name = "ServiceOrderStatusId", referencedColumnName = "ServiceOrderStatusId", nullable = false)
    @ManyToOne(optional = false)
    private ServiceOrderStatus serviceOrderStatusId;
    @JoinColumn(name = "ShipOwnerId", referencedColumnName = "ShipOwnerId", nullable = false)
    @ManyToOne(optional = false)
    private ShipOwner shipOwnerId;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @ManyToOne
    private User userId;

    public ServiceOrder() {
    }

    public ServiceOrder(Long serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public ServiceOrder(Long serviceOrderId, String serviceOrderCode, Date serviceOrderCreationDate, String operatingInstructions, boolean status) {
        this.serviceOrderId = serviceOrderId;
        this.serviceOrderCode = serviceOrderCode;
        this.serviceOrderCreationDate = serviceOrderCreationDate;
        this.operatingInstructions = operatingInstructions;
        this.status = status;
    }

    public Long getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(Long serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public String getServiceOrderCode() {
        return serviceOrderCode;
    }

    public void setServiceOrderCode(String serviceOrderCode) {
        this.serviceOrderCode = serviceOrderCode;
    }

    public Date getServiceOrderCreationDate() {
        return serviceOrderCreationDate;
    }

    public void setServiceOrderCreationDate(Date serviceOrderCreationDate) {
        this.serviceOrderCreationDate = serviceOrderCreationDate;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getFinalExchangeRate() {
        return finalExchangeRate;
    }

    public void setFinalExchangeRate(BigDecimal finalExchangeRate) {
        this.finalExchangeRate = finalExchangeRate;
    }

    public String getOperatingInstructions() {
        return operatingInstructions;
    }

    public void setOperatingInstructions(String operatingInstructions) {
        this.operatingInstructions = operatingInstructions;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public BigDecimal getBillingAdjustment() {
        return billingAdjustment;
    }

    public void setBillingAdjustment(BigDecimal billingAdjustment) {
        this.billingAdjustment = billingAdjustment;
    }

    public BigInteger getPaymentReceiptId() {
        return paymentReceiptId;
    }

    public void setPaymentReceiptId(BigInteger paymentReceiptId) {
        this.paymentReceiptId = paymentReceiptId;
    }

    public Boolean getExchangeModified() {
        return exchangeModified;
    }

    public void setExchangeModified(Boolean exchangeModified) {
        this.exchangeModified = exchangeModified;
    }

    public Boolean getTypeServiceOrder() {
        return typeServiceOrder;
    }

    public void setTypeServiceOrder(Boolean typeServiceOrder) {
        this.typeServiceOrder = typeServiceOrder;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<ServiceOrderService> getServiceOrderServiceCollection() {
        return serviceOrderServiceCollection;
    }

    public void setServiceOrderServiceCollection(Collection<ServiceOrderService> serviceOrderServiceCollection) {
        this.serviceOrderServiceCollection = serviceOrderServiceCollection;
    }

    @XmlTransient
    public Collection<ReferenceShipOwner> getReferenceShipOwnerCollection() {
        return referenceShipOwnerCollection;
    }

    public void setReferenceShipOwnerCollection(Collection<ReferenceShipOwner> referenceShipOwnerCollection) {
        this.referenceShipOwnerCollection = referenceShipOwnerCollection;
    }

    @XmlTransient
    public Collection<ServiceOrderFile> getServiceOrderFileCollection() {
        return serviceOrderFileCollection;
    }

    public void setServiceOrderFileCollection(Collection<ServiceOrderFile> serviceOrderFileCollection) {
        this.serviceOrderFileCollection = serviceOrderFileCollection;
    }

    @XmlTransient
    public Collection<ActingRoles> getActingRolesCollection() {
        return actingRolesCollection;
    }

    public void setActingRolesCollection(Collection<ActingRoles> actingRolesCollection) {
        this.actingRolesCollection = actingRolesCollection;
    }

    @XmlTransient
    public Collection<ServiceOrderProductType> getServiceOrderProductTypeCollection() {
        return serviceOrderProductTypeCollection;
    }

    public void setServiceOrderProductTypeCollection(Collection<ServiceOrderProductType> serviceOrderProductTypeCollection) {
        this.serviceOrderProductTypeCollection = serviceOrderProductTypeCollection;
    }

    @XmlTransient
    public Collection<ServiceOrderJoin> getServiceOrderJoinCollection() {
        return serviceOrderJoinCollection;
    }

    public void setServiceOrderJoinCollection(Collection<ServiceOrderJoin> serviceOrderJoinCollection) {
        this.serviceOrderJoinCollection = serviceOrderJoinCollection;
    }

    @XmlTransient
    public Collection<ServiceOrderJoin> getServiceOrderJoinCollection1() {
        return serviceOrderJoinCollection1;
    }

    public void setServiceOrderJoinCollection1(Collection<ServiceOrderJoin> serviceOrderJoinCollection1) {
        this.serviceOrderJoinCollection1 = serviceOrderJoinCollection1;
    }

    public Currency getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Currency currencyId) {
        this.currencyId = currencyId;
    }

    public Itinerary getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(Itinerary itineraryId) {
        this.itineraryId = itineraryId;
    }

    public Line getLineId() {
        return lineId;
    }

    public void setLineId(Line lineId) {
        this.lineId = lineId;
    }

    public ServiceOrderStatus getServiceOrderStatusId() {
        return serviceOrderStatusId;
    }

    public void setServiceOrderStatusId(ServiceOrderStatus serviceOrderStatusId) {
        this.serviceOrderStatusId = serviceOrderStatusId;
    }

    public ShipOwner getShipOwnerId() {
        return shipOwnerId;
    }

    public void setShipOwnerId(ShipOwner shipOwnerId) {
        this.shipOwnerId = shipOwnerId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceOrderId != null ? serviceOrderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceOrder)) {
            return false;
        }
        ServiceOrder other = (ServiceOrder) object;
        if ((this.serviceOrderId == null && other.serviceOrderId != null) || (this.serviceOrderId != null && !this.serviceOrderId.equals(other.serviceOrderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ServiceOrder[ serviceOrderId=" + serviceOrderId + " ]";
    }
    
}
