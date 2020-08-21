/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades.nominacion;

import mx.tab.wgp.qsmaritimex.entidades.usuario.User;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import mx.tab.wgp.qsmaritimex.entidades.Currency;

/**
 *
 * @author William
 */
@Entity
@XmlRootElement
@Table(name = "ServiceOrder")
public class ServiceOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false, name = "ServiceOrderId", precision = 19, scale = 0)
    private BigInteger serviceOrderId;
    @Column(precision = 12, scale = 4)
    private BigDecimal billingAdjustment;
    @Column(length = 500)
    private String comments;
    private Boolean exchangeModified;
    @Column(precision = 12, scale = 4)
    private BigDecimal exchangeRate;
    @Column(precision = 12, scale = 4)
    private BigDecimal finalExchangeRate;
    @Basic(optional = false)
    @Column(nullable = false, length = 500)
    private String operatingInstructions;
    @Column(precision = 19, scale = 2)
    private BigDecimal paymentReceiptId;
    @Basic(optional = false)
    @Column(nullable = false, length = 8)
    private String serviceOrderCode;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date serviceOrderCreationDate;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    private Boolean typeServiceOrder;
    //-----------------------------------
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceOrderId")
    private Collection<ServiceOrderService> serviceOrderServiceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceOrderId")
    private Collection<ReferenceShipOwner> referenceShipOwnerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceOrderId")
    private Collection<ServiceOrderFile> serviceOrderFileCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceOrderId")
    private Collection<ActingRoles> actingRolesCollection;
    //---------------------------------------------------------------------------
    //Llave compuesta desde ServiceOrderProductType
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceOrder")
    private Collection<ServiceOrderProductType> serviceOrderProductTypeCollection;
    //----------------------------------------------------------------------------
    @OneToMany(mappedBy = "serviceOrderIdFather")
    private Collection<ServiceOrderJoin> serviceOrderJoinCollection;
    @OneToMany(mappedBy = "serviceOrderIdSon")
    private Collection<ServiceOrderJoin> serviceOrderJoinCollection1;
    //-----------------------------------
    @JoinColumn(name = "CurrencyId", referencedColumnName = "CurrencyId", nullable = false)
    @ManyToOne(optional = false)
    private Currency currencyId;
    @JoinColumn(name = "ItineraryId", referencedColumnName = "ItineraryId", nullable = false)
    @ManyToOne(optional = false)
    private Itinerary itineraryId;
    @JoinColumn(name = "ServiceOrderStatusId", referencedColumnName = "ServiceOrderStatusId", nullable = false)
    @ManyToOne(optional = false)
    private ServiceOrderStatus serviceOrderStatusId;
    @JoinColumn(name = "ShipOwnerId", referencedColumnName = "ShipOwnerId", nullable = false)
    @ManyToOne(optional = false)
    private ShipOwner shipOwnerId;
    @JoinColumn(name = "LineId", referencedColumnName = "LineId", nullable = false)
    @ManyToOne(optional = false)
    private Line lineId;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId", nullable = false)
    @ManyToOne(optional = false)
    private User userId;

    public ServiceOrder() {
    }

    public ServiceOrder(BigInteger serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public ServiceOrder(BigInteger serviceOrderId, String operatingInstructions, String serviceOrderCode, Date serviceOrderCreationDate, boolean status, Line lineId) {
        this.serviceOrderId = serviceOrderId;
        this.operatingInstructions = operatingInstructions;
        this.serviceOrderCode = serviceOrderCode;
        this.serviceOrderCreationDate = serviceOrderCreationDate;
        this.status = status;
        this.lineId = lineId;
    }

    public BigInteger getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(BigInteger serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public BigDecimal getBillingAdjustment() {
        return billingAdjustment;
    }

    public void setBillingAdjustment(BigDecimal billingAdjustment) {
        this.billingAdjustment = billingAdjustment;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getExchangeModified() {
        return exchangeModified;
    }

    public void setExchangeModified(Boolean exchangeModified) {
        this.exchangeModified = exchangeModified;
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

    public BigDecimal getPaymentReceiptId() {
        return paymentReceiptId;
    }

    public void setPaymentReceiptId(BigDecimal paymentReceiptId) {
        this.paymentReceiptId = paymentReceiptId;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Boolean getTypeServiceOrder() {
        return typeServiceOrder;
    }

    public void setTypeServiceOrder(Boolean typeServiceOrder) {
        this.typeServiceOrder = typeServiceOrder;
    }

    public Line getLineId() {
        return lineId;
    }

    public void setLineId(Line lineId) {
        this.lineId = lineId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "ServiceOrder{" + "serviceOrderId=" + serviceOrderId + ", billingAdjustment=" + billingAdjustment + ", comments=" + comments + ", exchangeModified=" + exchangeModified + ", exchangeRate=" + exchangeRate + ", finalExchangeRate=" + finalExchangeRate + ", operatingInstructions=" + operatingInstructions + ", paymentReceiptId=" + paymentReceiptId + ", serviceOrderCode=" + serviceOrderCode + ", serviceOrderCreationDate=" + serviceOrderCreationDate + ", status=" + status + ", typeServiceOrder=" + typeServiceOrder + ", userId=" + userId + ", currencyId=" + currencyId + ", itineraryId=" + itineraryId + ", serviceOrderStatusId=" + serviceOrderStatusId + ", shipOwnerId=" + shipOwnerId + ", lineId=" + lineId + '}';
    }
}
