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
    @NamedQuery(name = "ServiceOrderService.findAll", query = "SELECT s FROM ServiceOrderService s"),
    @NamedQuery(name = "ServiceOrderService.findByServiceOrderServiceId", query = "SELECT s FROM ServiceOrderService s WHERE s.serviceOrderServiceId = :serviceOrderServiceId"),
    @NamedQuery(name = "ServiceOrderService.findByTotalPrice", query = "SELECT s FROM ServiceOrderService s WHERE s.totalPrice = :totalPrice"),
    @NamedQuery(name = "ServiceOrderService.findByEstimatedPaymentDate", query = "SELECT s FROM ServiceOrderService s WHERE s.estimatedPaymentDate = :estimatedPaymentDate"),
    @NamedQuery(name = "ServiceOrderService.findByTotalPriceUSD", query = "SELECT s FROM ServiceOrderService s WHERE s.totalPriceUSD = :totalPriceUSD"),
    @NamedQuery(name = "ServiceOrderService.findByVoucher", query = "SELECT s FROM ServiceOrderService s WHERE s.voucher = :voucher"),
    @NamedQuery(name = "ServiceOrderService.findByValidInvoice", query = "SELECT s FROM ServiceOrderService s WHERE s.validInvoice = :validInvoice"),
    @NamedQuery(name = "ServiceOrderService.findByTotalPaid", query = "SELECT s FROM ServiceOrderService s WHERE s.totalPaid = :totalPaid"),
    @NamedQuery(name = "ServiceOrderService.findByCreationDate", query = "SELECT s FROM ServiceOrderService s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ServiceOrderService.findByInvoiceNumber", query = "SELECT s FROM ServiceOrderService s WHERE s.invoiceNumber = :invoiceNumber"),
    @NamedQuery(name = "ServiceOrderService.findByInvoiceDate", query = "SELECT s FROM ServiceOrderService s WHERE s.invoiceDate = :invoiceDate"),
    @NamedQuery(name = "ServiceOrderService.findByCurrencyFee", query = "SELECT s FROM ServiceOrderService s WHERE s.currencyFee = :currencyFee"),
    @NamedQuery(name = "ServiceOrderService.findByReinvoicing", query = "SELECT s FROM ServiceOrderService s WHERE s.reinvoicing = :reinvoicing"),
    @NamedQuery(name = "ServiceOrderService.findByIscreditNoteBit", query = "SELECT s FROM ServiceOrderService s WHERE s.iscreditNoteBit = :iscreditNoteBit"),
    @NamedQuery(name = "ServiceOrderService.findByUpdateDate", query = "SELECT s FROM ServiceOrderService s WHERE s.updateDate = :updateDate"),
    @NamedQuery(name = "ServiceOrderService.findByExchangeRateAX", query = "SELECT s FROM ServiceOrderService s WHERE s.exchangeRateAX = :exchangeRateAX"),
    @NamedQuery(name = "ServiceOrderService.findByCreditNoteBalance", query = "SELECT s FROM ServiceOrderService s WHERE s.creditNoteBalance = :creditNoteBalance"),
    @NamedQuery(name = "ServiceOrderService.findByAmountPaid", query = "SELECT s FROM ServiceOrderService s WHERE s.amountPaid = :amountPaid"),
    @NamedQuery(name = "ServiceOrderService.findByCatchErrorAX", query = "SELECT s FROM ServiceOrderService s WHERE s.catchErrorAX = :catchErrorAX"),
    @NamedQuery(name = "ServiceOrderService.findByFromCancelation", query = "SELECT s FROM ServiceOrderService s WHERE s.fromCancelation = :fromCancelation"),
    @NamedQuery(name = "ServiceOrderService.findByStatus", query = "SELECT s FROM ServiceOrderService s WHERE s.status = :status")})
public class ServiceOrderService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long serviceOrderServiceId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 12, scale = 4)
    private BigDecimal totalPrice;
    @Temporal(TemporalType.TIMESTAMP)
    private Date estimatedPaymentDate;
    @Column(precision = 12, scale = 4)
    private BigDecimal totalPriceUSD;
    @Column(length = 50)
    private String voucher;
    private Boolean validInvoice;
    @Column(precision = 12, scale = 4)
    private BigDecimal totalPaid;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(length = 500)
    private String invoiceNumber;
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate;
    @Basic(optional = false)
    @Column(nullable = false)
    private int currencyFee;
    private Boolean reinvoicing;
    private Boolean iscreditNoteBit;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(precision = 12, scale = 4)
    private BigDecimal exchangeRateAX;
    @Column(precision = 12, scale = 4)
    private BigDecimal creditNoteBalance;
    @Column(precision = 12, scale = 4)
    private BigDecimal amountPaid;
    @Column(length = 500)
    private String catchErrorAX;
    private Boolean fromCancelation;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @JoinColumn(name = "CurrencyId", referencedColumnName = "CurrencyId")
    @ManyToOne
    private Currency currencyId;
    @JoinColumn(name = "ServiceId", referencedColumnName = "ServiceId")
    @ManyToOne
    private Service serviceId;
    @JoinColumn(name = "ServiceOrderId", referencedColumnName = "ServiceOrderId", nullable = false)
    @ManyToOne(optional = false)
    private ServiceOrder serviceOrderId;
    @JoinColumn(name = "ServiceOrderServiceStatusId", referencedColumnName = "ServiceOrderServiceStatusId")
    @ManyToOne
    private ServiceOrderServicesStatus serviceOrderServiceStatusId;
    @JoinColumn(name = "ServiceOrderServiceTypeId", referencedColumnName = "ServiceOrderServicesTypeId")
    @ManyToOne
    private ServiceOrderServicesType serviceOrderServiceTypeId;
    @JoinColumn(name = "ServiceTransferLogId", referencedColumnName = "ServiceTransferLogId")
    @ManyToOne
    private ServiceTransferLog serviceTransferLogId;
    @JoinColumn(name = "UpdateUserId", referencedColumnName = "UserId")
    @ManyToOne
    private User updateUserId;
    @JoinColumn(name = "VendorInvoiceOldID", referencedColumnName = "VendorInvoiceID")
    @ManyToOne
    private VendorInvoice vendorInvoiceOldID;
    @JoinColumn(name = "VendorInvoiceID", referencedColumnName = "VendorInvoiceID")
    @ManyToOne
    private VendorInvoice vendorInvoiceID;
    @JoinColumn(name = "VendorId", referencedColumnName = "VendorId")
    @ManyToOne
    private VendorProfile vendorId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceOrderServiceId")
    private Collection<VendorFile> vendorFileCollection;

    public ServiceOrderService() {
    }

    public ServiceOrderService(Long serviceOrderServiceId) {
        this.serviceOrderServiceId = serviceOrderServiceId;
    }

    public ServiceOrderService(Long serviceOrderServiceId, int currencyFee, boolean status) {
        this.serviceOrderServiceId = serviceOrderServiceId;
        this.currencyFee = currencyFee;
        this.status = status;
    }

    public Long getServiceOrderServiceId() {
        return serviceOrderServiceId;
    }

    public void setServiceOrderServiceId(Long serviceOrderServiceId) {
        this.serviceOrderServiceId = serviceOrderServiceId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getEstimatedPaymentDate() {
        return estimatedPaymentDate;
    }

    public void setEstimatedPaymentDate(Date estimatedPaymentDate) {
        this.estimatedPaymentDate = estimatedPaymentDate;
    }

    public BigDecimal getTotalPriceUSD() {
        return totalPriceUSD;
    }

    public void setTotalPriceUSD(BigDecimal totalPriceUSD) {
        this.totalPriceUSD = totalPriceUSD;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public Boolean getValidInvoice() {
        return validInvoice;
    }

    public void setValidInvoice(Boolean validInvoice) {
        this.validInvoice = validInvoice;
    }

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(BigDecimal totalPaid) {
        this.totalPaid = totalPaid;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public int getCurrencyFee() {
        return currencyFee;
    }

    public void setCurrencyFee(int currencyFee) {
        this.currencyFee = currencyFee;
    }

    public Boolean getReinvoicing() {
        return reinvoicing;
    }

    public void setReinvoicing(Boolean reinvoicing) {
        this.reinvoicing = reinvoicing;
    }

    public Boolean getIscreditNoteBit() {
        return iscreditNoteBit;
    }

    public void setIscreditNoteBit(Boolean iscreditNoteBit) {
        this.iscreditNoteBit = iscreditNoteBit;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public BigDecimal getExchangeRateAX() {
        return exchangeRateAX;
    }

    public void setExchangeRateAX(BigDecimal exchangeRateAX) {
        this.exchangeRateAX = exchangeRateAX;
    }

    public BigDecimal getCreditNoteBalance() {
        return creditNoteBalance;
    }

    public void setCreditNoteBalance(BigDecimal creditNoteBalance) {
        this.creditNoteBalance = creditNoteBalance;
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

    public Boolean getFromCancelation() {
        return fromCancelation;
    }

    public void setFromCancelation(Boolean fromCancelation) {
        this.fromCancelation = fromCancelation;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Currency getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Currency currencyId) {
        this.currencyId = currencyId;
    }

    public Service getServiceId() {
        return serviceId;
    }

    public void setServiceId(Service serviceId) {
        this.serviceId = serviceId;
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

    public User getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(User updateUserId) {
        this.updateUserId = updateUserId;
    }

    public VendorInvoice getVendorInvoiceOldID() {
        return vendorInvoiceOldID;
    }

    public void setVendorInvoiceOldID(VendorInvoice vendorInvoiceOldID) {
        this.vendorInvoiceOldID = vendorInvoiceOldID;
    }

    public VendorInvoice getVendorInvoiceID() {
        return vendorInvoiceID;
    }

    public void setVendorInvoiceID(VendorInvoice vendorInvoiceID) {
        this.vendorInvoiceID = vendorInvoiceID;
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
