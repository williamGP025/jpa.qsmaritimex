/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades;

import java.io.Serializable;
import java.math.BigInteger;
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
    @NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s"),
    @NamedQuery(name = "Service.findByServiceId", query = "SELECT s FROM Service s WHERE s.serviceId = :serviceId"),
    @NamedQuery(name = "Service.findByDescription", query = "SELECT s FROM Service s WHERE s.description = :description"),
    @NamedQuery(name = "Service.findByDescriptionEng", query = "SELECT s FROM Service s WHERE s.descriptionEng = :descriptionEng"),
    @NamedQuery(name = "Service.findByChargeTypeId", query = "SELECT s FROM Service s WHERE s.chargeTypeId = :chargeTypeId"),
    @NamedQuery(name = "Service.findByIsFee", query = "SELECT s FROM Service s WHERE s.isFee = :isFee"),
    @NamedQuery(name = "Service.findByAssociatedCharge", query = "SELECT s FROM Service s WHERE s.associatedCharge = :associatedCharge"),
    @NamedQuery(name = "Service.findByItemId", query = "SELECT s FROM Service s WHERE s.itemId = :itemId"),
    @NamedQuery(name = "Service.findBySiglas", query = "SELECT s FROM Service s WHERE s.siglas = :siglas"),
    @NamedQuery(name = "Service.findByOldId", query = "SELECT s FROM Service s WHERE s.oldId = :oldId"),
    @NamedQuery(name = "Service.findByBLCharge", query = "SELECT s FROM Service s WHERE s.bLCharge = :bLCharge"),
    @NamedQuery(name = "Service.findByOrder", query = "SELECT s FROM Service s WHERE s.order = :order"),
    @NamedQuery(name = "Service.findByVendorDefaultRecId", query = "SELECT s FROM Service s WHERE s.vendorDefaultRecId = :vendorDefaultRecId"),
    @NamedQuery(name = "Service.findByVendorDefaultName", query = "SELECT s FROM Service s WHERE s.vendorDefaultName = :vendorDefaultName"),
    @NamedQuery(name = "Service.findByStatus", query = "SELECT s FROM Service s WHERE s.status = :status")})
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer serviceId;
    @Basic(optional = false)
    @Column(nullable = false, length = 200)
    private String description;
    @Column(length = 200)
    private String descriptionEng;
    @Basic(optional = false)
    @Column(nullable = false)
    private int chargeTypeId;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean isFee;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 53, scale = 0)
    private Double associatedCharge;
    @Column(length = 50)
    private String itemId;
    @Column(length = 50)
    private String siglas;
    private Integer oldId;
    private Boolean bLCharge;
    private Integer order;
    private BigInteger vendorDefaultRecId;
    @Column(length = 200)
    private String vendorDefaultName;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @OneToMany(mappedBy = "serviceId")
    private Collection<ServiceOrderService> serviceOrderServiceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceId")
    private Collection<ServiceOrderServicesTemplate> serviceOrderServicesTemplateCollection;
    @JoinColumn(name = "ServiceTypeId", referencedColumnName = "ServiceTypeId")
    @ManyToOne
    private ServiceType serviceTypeId;

    public Service() {
    }

    public Service(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Service(Integer serviceId, String description, int chargeTypeId, boolean isFee, boolean status) {
        this.serviceId = serviceId;
        this.description = description;
        this.chargeTypeId = chargeTypeId;
        this.isFee = isFee;
        this.status = status;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionEng() {
        return descriptionEng;
    }

    public void setDescriptionEng(String descriptionEng) {
        this.descriptionEng = descriptionEng;
    }

    public int getChargeTypeId() {
        return chargeTypeId;
    }

    public void setChargeTypeId(int chargeTypeId) {
        this.chargeTypeId = chargeTypeId;
    }

    public boolean getIsFee() {
        return isFee;
    }

    public void setIsFee(boolean isFee) {
        this.isFee = isFee;
    }

    public Double getAssociatedCharge() {
        return associatedCharge;
    }

    public void setAssociatedCharge(Double associatedCharge) {
        this.associatedCharge = associatedCharge;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public Integer getOldId() {
        return oldId;
    }

    public void setOldId(Integer oldId) {
        this.oldId = oldId;
    }

    public Boolean getBLCharge() {
        return bLCharge;
    }

    public void setBLCharge(Boolean bLCharge) {
        this.bLCharge = bLCharge;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public BigInteger getVendorDefaultRecId() {
        return vendorDefaultRecId;
    }

    public void setVendorDefaultRecId(BigInteger vendorDefaultRecId) {
        this.vendorDefaultRecId = vendorDefaultRecId;
    }

    public String getVendorDefaultName() {
        return vendorDefaultName;
    }

    public void setVendorDefaultName(String vendorDefaultName) {
        this.vendorDefaultName = vendorDefaultName;
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
    public Collection<ServiceOrderServicesTemplate> getServiceOrderServicesTemplateCollection() {
        return serviceOrderServicesTemplateCollection;
    }

    public void setServiceOrderServicesTemplateCollection(Collection<ServiceOrderServicesTemplate> serviceOrderServicesTemplateCollection) {
        this.serviceOrderServicesTemplateCollection = serviceOrderServicesTemplateCollection;
    }

    public ServiceType getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(ServiceType serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceId != null ? serviceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.serviceId == null && other.serviceId != null) || (this.serviceId != null && !this.serviceId.equals(other.serviceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.Service[ serviceId=" + serviceId + " ]";
    }
    
}
