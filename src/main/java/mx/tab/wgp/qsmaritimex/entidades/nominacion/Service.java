/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades.nominacion;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author William
 */
@Entity
@Table(name = "Service")
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ServiceId", nullable = false)
    @Basic(optional = false)
    private Integer serviceId;
    @Column(name = "ChargeTypeId", nullable = false)
    @Basic(optional = false)
    private Integer chargeTypeId;
    @Column(name = "OldId")
    private Integer oldId;
    @Column(name = "[Order]")
    private Integer order;
    @Column(name = "Description", nullable = false)
    @Basic(optional = false)
    private String description;
    @Column(name = "DescriptionEng")
    private String descriptionEng;
    @Column(name = "ItemId")
    private String itemId;
    @Column(name = "Siglas")
    private String siglas;
    @Column(name = "VendorDefaultName")
    private String vendorDefaultName;

    @Column(name = "IsFee", nullable = false)
    @Basic(optional = false)
    private Boolean isFee;
    @Column(name = "BLCharge")
    private Boolean bLCharge;
    @Column(name = "[Status]", nullable = false)
    @Basic(optional = false)
    private Boolean status;

    @Column(name = "[VendorDefaultRecId]")
    private BigInteger vendorDefaultRecId;
    @Column(name = "[AssociatedCharge]")
    private Float associatedCharge;
    //-------------------------------------------------------------    
    @JoinColumn(name = "ServiceTypeId", referencedColumnName = "ServiceTypeId", nullable = true)
    @ManyToOne(optional = true)
    private ServiceType serviceTypeId;
    //-------------------------------------------------------------
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ServiceId")
    private Collection<ServiceOrderService> serviceOrderServiceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ServiceId")
    private Collection<ServiceOrderServicesTemplate> serviceOrderServicesTemplateCollection;

    public Service() {
    }

    public Collection<ServiceOrderServicesTemplate> getServiceOrderServicesTemplateCollection() {
        return serviceOrderServicesTemplateCollection;
    }

    public void setServiceOrderServicesTemplateCollection(Collection<ServiceOrderServicesTemplate> serviceOrderServicesTemplateCollection) {
        this.serviceOrderServicesTemplateCollection = serviceOrderServicesTemplateCollection;
    }

    public Collection<ServiceOrderService> getServiceOrderServiceCollection() {
        return serviceOrderServiceCollection;
    }

    public void setServiceOrderServiceCollection(Collection<ServiceOrderService> serviceOrderServiceCollection) {
        this.serviceOrderServiceCollection = serviceOrderServiceCollection;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getChargeTypeId() {
        return chargeTypeId;
    }

    public void setChargeTypeId(Integer chargeTypeId) {
        this.chargeTypeId = chargeTypeId;
    }

    public Integer getOldId() {
        return oldId;
    }

    public void setOldId(Integer oldId) {
        this.oldId = oldId;
    }

    public ServiceType getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(ServiceType serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
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

    public String getVendorDefaultName() {
        return vendorDefaultName;
    }

    public void setVendorDefaultName(String vendorDefaultName) {
        this.vendorDefaultName = vendorDefaultName;
    }

    public Boolean getIsFee() {
        return isFee;
    }

    public void setIsFee(Boolean isFee) {
        this.isFee = isFee;
    }

    public Boolean getbLCharge() {
        return bLCharge;
    }

    public void setbLCharge(Boolean bLCharge) {
        this.bLCharge = bLCharge;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public BigInteger getVendorDefaultRecId() {
        return vendorDefaultRecId;
    }

    public void setVendorDefaultRecId(BigInteger vendorDefaultRecId) {
        this.vendorDefaultRecId = vendorDefaultRecId;
    }

    public Float getAssociatedCharge() {
        return associatedCharge;
    }

    public void setAssociatedCharge(Float associatedCharge) {
        this.associatedCharge = associatedCharge;
    }

}
