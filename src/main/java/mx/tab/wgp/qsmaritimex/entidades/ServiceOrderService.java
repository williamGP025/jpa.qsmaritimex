/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
    @NamedQuery(name = "ServiceOrderService.findAll", query = "SELECT s FROM ServiceOrderService s"),
    @NamedQuery(name = "ServiceOrderService.findByServiceOrderServiceId", query = "SELECT s FROM ServiceOrderService s WHERE s.serviceOrderServiceId = :serviceOrderServiceId"),
    @NamedQuery(name = "ServiceOrderService.findByAmountPaid", query = "SELECT s FROM ServiceOrderService s WHERE s.amountPaid = :amountPaid"),
    @NamedQuery(name = "ServiceOrderService.findByCatchErrorAX", query = "SELECT s FROM ServiceOrderService s WHERE s.catchErrorAX = :catchErrorAX"),
    @NamedQuery(name = "ServiceOrderService.findByCreationDate", query = "SELECT s FROM ServiceOrderService s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ServiceOrderService.findByCreditNoteBalance", query = "SELECT s FROM ServiceOrderService s WHERE s.creditNoteBalance = :creditNoteBalance"),
    @NamedQuery(name = "ServiceOrderService.findByCurrencyFee", query = "SELECT s FROM ServiceOrderService s WHERE s.currencyFee = :currencyFee"),
    @NamedQuery(name = "ServiceOrderService.findByEstimatedPaymentDate", query = "SELECT s FROM ServiceOrderService s WHERE s.estimatedPaymentDate = :estimatedPaymentDate"),
    @NamedQuery(name = "ServiceOrderService.findByExchangeRateAX", query = "SELECT s FROM ServiceOrderService s WHERE s.exchangeRateAX = :exchangeRateAX"),
    @NamedQuery(name = "ServiceOrderService.findByFromCancelation", query = "SELECT s FROM ServiceOrderService s WHERE s.fromCancelation = :fromCancelation"),
    @NamedQuery(name = "ServiceOrderService.findByInvoiceDate", query = "SELECT s FROM ServiceOrderService s WHERE s.invoiceDate = :invoiceDate"),
    @NamedQuery(name = "ServiceOrderService.findByInvoiceNumber", query = "SELECT s FROM ServiceOrderService s WHERE s.invoiceNumber = :invoiceNumber"),
    @NamedQuery(name = "ServiceOrderService.findByIscreditNoteBit", query = "SELECT s FROM ServiceOrderService s WHERE s.iscreditNoteBit = :iscreditNoteBit"),
    @NamedQuery(name = "ServiceOrderService.findByReinvoicing", query = "SELECT s FROM ServiceOrderService s WHERE s.reinvoicing = :reinvoicing"),
    @NamedQuery(name = "ServiceOrderService.findByStatus", query = "SELECT s FROM ServiceOrderService s WHERE s.status = :status"),
    @NamedQuery(name = "ServiceOrderService.findByTotalPaid", query = "SELECT s FROM ServiceOrderService s WHERE s.totalPaid = :totalPaid"),
    @NamedQuery(name = "ServiceOrderService.findByTotalPrice", query = "SELECT s FROM ServiceOrderService s WHERE s.totalPrice = :totalPrice"),
    @NamedQuery(name = "ServiceOrderService.findByTotalPriceUSD", query = "SELECT s FROM ServiceOrderService s WHERE s.totalPriceUSD = :totalPriceUSD"),
    @NamedQuery(name = "ServiceOrderService.findByUpdateDate", query = "SELECT s FROM ServiceOrderService s WHERE s.updateDate = :updateDate"),
    @NamedQuery(name = "ServiceOrderService.findByValidInvoice", query = "SELECT s FROM ServiceOrderService s WHERE s.validInvoice = :validInvoice"),
    @NamedQuery(name = "ServiceOrderService.findByVoucher", query = "SELECT s FROM ServiceOrderService s WHERE s.voucher = :voucher"),
    @NamedQuery(name = "ServiceOrderService.findByServiceId", query = "SELECT s FROM ServiceOrderService s WHERE s.serviceId = :serviceId"),
    @NamedQuery(name = "ServiceOrderService.findByUpdateUserId", query = "SELECT s FROM ServiceOrderService s WHERE s.updateUserId = :updateUserId")})
public class ServiceOrderService implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false, precision = 19, scale = 0)
    private BigDecimal serviceOrderServiceId;
    @Column(precision = 12, scale = 4)
    private BigDecimal amountPaid;
    @Column(length = 500)
    private String catchErrorAX;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(precision = 12, scale = 4)
    private BigDecimal creditNoteBalance;
    @Basic(optional = false)
    @Column(nullable = false)
    private int currencyFee;
    @Temporal(TemporalType.TIMESTAMP)
    private Date estimatedPaymentDate;
    @Column(precision = 12, scale = 4)
    private BigDecimal exchangeRateAX;
    private Boolean fromCancelation;
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate;
    @Column(length = 500)
    private String invoiceNumber;
    private Boolean iscreditNoteBit;
    private Boolean reinvoicing;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @Column(precision = 12, scale = 4)
    private BigDecimal totalPaid;
    @Column(precision = 12, scale = 4)
    private BigDecimal totalPrice;
    @Column(precision = 12, scale = 4)
    private BigDecimal totalPriceUSD;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    private Boolean validInvoice;
    @Column(length = 50)
    private String voucher;
    private Integer serviceId;
    private Integer updateUserId;
    @JoinColumn(name = "CurrencyId", referencedColumnName = "currencyId")
    @ManyToOne
    private Currency currencyId;
    @JoinColumn(name = "ServiceOrderId", referencedColumnName = "serviceOrderId", nullable = false)
    @ManyToOne(optional = false)
    private ServiceOrder serviceOrderId;
    @JoinColumn(name = "ServiceOrderServiceStatusId", referencedColumnName = "serviceOrderServiceStatusId")
    @ManyToOne
    private ServiceOrderServicesStatus serviceOrderServiceStatusId;
    @JoinColumn(name = "ServiceOrderServiceTypeId", referencedColumnName = "serviceOrderServicesTypeId")
    @ManyToOne
    private ServiceOrderServicesType serviceOrderServiceTypeId;
    @JoinColumn(name = "ServiceTransferLogId", referencedColumnName = "serviceTransferLogId")
    @ManyToOne
    private ServiceTransferLog serviceTransferLogId;
    @JoinColumn(name = "VendorInvoiceID", referencedColumnName = "vendorInvoiceID")
    @ManyToOne
    private VendorInvoice vendorInvoiceID;
    @JoinColumn(name = "VendorInvoiceOldID", referencedColumnName = "vendorInvoiceID")
    @ManyToOne
    private VendorInvoice vendorInvoiceOldID;
    @JoinColumn(name = "VendorId", referencedColumnName = "vendorId")
    @ManyToOne
    private VendorProfile vendorId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceOrderServiceId")
    private Collection<VendorFile> vendorFileCollection;

    public ServiceOrderService() {
    }

    public ServiceOrderService(BigDecimal serviceOrderServiceId) {
        this.serviceOrderServiceId = serviceOrderServiceId;
    }

    public ServiceOrderService(BigDecimal serviceOrderServiceId, int currencyFee, boolean status) {
        this.serviceOrderServiceId = serviceOrderServiceId;
        this.currencyFee = currencyFee;
        this.status = status;
    }

    public BigDecimal getServiceOrderServiceId() {
        return serviceOrderServiceId;
    }

    public void setServiceOrderServiceId(BigDecimal serviceOrderServiceId) {
        this.serviceOrderServiceId = serviceOrderServiceId;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getCatchErrorAX() {
        return catchErrorAX;
    }

    public void setCatchErrorAX(String catchErrorAX) {
        this.catchErrorAX = catchErrorAX;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getCreditNoteBalance() {
        return creditNoteBalance;
    }

    public void setCreditNoteBalance(BigDecimal creditNoteBalance) {
        this.creditNoteBalance = creditNoteBalance;
    }

    public int getCurrencyFee() {
        return currencyFee;
    }

    public void setCurrencyFee(int currencyFee) {
        this.currencyFee = currencyFee;
    }

    public Date getEstimatedPaymentDate() {
        return estimatedPaymentDate;
    }

    public void setEstimatedPaymentDate(Date estimatedPaymentDate) {
        this.estimatedPaymentDate = estimatedPaymentDate;
    }

    public BigDecimal getExchangeRateAX() {
        return exchangeRateAX;
    }

    public void setExchangeRateAX(BigDecimal exchangeRateAX) {
        this.exchangeRateAX = exchangeRateAX;
    }

    public Boolean getFromCancelation() {
        return fromCancelation;
    }

    public void setFromCancelation(Boolean fromCancelation) {
        this.fromCancelation = fromCancelation;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Boolean getIscreditNoteBit() {
        return iscreditNoteBit;
    }

    public void setIscreditNoteBit(Boolean iscreditNoteBit) {
        this.iscreditNoteBit = iscreditNoteBit;
    }

    public Boolean getReinvoicing() {
        return reinvoicing;
    }

    public void setReinvoicing(Boolean reinvoicing) {
        this.reinvoicing = reinvoicing;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(BigDecimal totalPaid) {
        this.totalPaid = totalPaid;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalPriceUSD() {
        return totalPriceUSD;
    }

    public void setTotalPriceUSD(BigDecimal totalPriceUSD) {
        this.totalPriceUSD = totalPriceUSD;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean getValidInvoice() {
        return validInvoice;
    }

    public void setValidInvoice(Boolean validInvoice) {
        this.validInvoice = validInvoice;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Currency getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Currency currencyId) {
        this.currencyId = currencyId;
    }

    public ServiceOrder getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(ServiceOrder serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public ServiceOrderServicesStatus getServiceOrderServiceStatusId() {
        return serviceOrderServiceStatusId;
    }

    public void setServiceOrderServiceStatusId(ServiceOrderServicesStatus serviceOrderServiceStatusId) {
        this.serviceOrderServiceStatusId = serviceOrderServiceStatusId;
    }

    public ServiceOrderServicesType getServiceOrderServiceTypeId() {
        return serviceOrderServiceTypeId;
    }

    public void setServiceOrderServiceTypeId(ServiceOrderServicesType serviceOrderServiceTypeId) {
        this.serviceOrderServiceTypeId = serviceOrderServiceTypeId;
    }

    public ServiceTransferLog getServiceTransferLogId() {
        return serviceTransferLogId;
    }

    public void setServiceTransferLogId(ServiceTransferLog serviceTransferLogId) {
        this.serviceTransferLogId = serviceTransferLogId;
    }

    public VendorInvoice getVendorInvoiceID() {
        return vendorInvoiceID;
    }

    public void setVendorInvoiceID(VendorInvoice vendorInvoiceID) {
        this.vendorInvoiceID = vendorInvoiceID;
    }

    public VendorInvoice getVendorInvoiceOldID() {
        return vendorInvoiceOldID;
    }

    public void setVendorInvoiceOldID(VendorInvoice vendorInvoiceOldID) {
        this.vendorInvoiceOldID = vendorInvoiceOldID;
    }

    public VendorProfile getVendorId() {
        return vendorId;
    }

    public void setVendorId(VendorProfile vendorId) {
        this.vendorId = vendorId;
    }

    @XmlTransient
    public Collection<VendorFile> getVendorFileCollection() {
        return vendorFileCollection;
    }

    public void setVendorFileCollection(Collection<VendorFile> vendorFileCollection) {
        this.vendorFileCollection = vendorFileCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceOrderServiceId != null ? serviceOrderServiceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceOrderService)) {
            return false;
        }
        ServiceOrderService other = (ServiceOrderService) object;
        if ((this.serviceOrderServiceId == null && other.serviceOrderServiceId != null) || (this.serviceOrderServiceId != null && !this.serviceOrderServiceId.equals(other.serviceOrderServiceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ServiceOrderService[ serviceOrderServiceId=" + serviceOrderServiceId + " ]";
    }
    
}
