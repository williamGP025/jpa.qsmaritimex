/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WilliamGP025
 */
@Entity
@Table(catalog = "QSMaritimex", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceOrderProductType.findAll", query = "SELECT s FROM ServiceOrderProductType s"),
    @NamedQuery(name = "ServiceOrderProductType.findByServiceOrderId", query = "SELECT s FROM ServiceOrderProductType s WHERE s.serviceOrderProductTypePK.serviceOrderId = :serviceOrderId"),
    @NamedQuery(name = "ServiceOrderProductType.findByProductTypeId", query = "SELECT s FROM ServiceOrderProductType s WHERE s.serviceOrderProductTypePK.productTypeId = :productTypeId"),
    @NamedQuery(name = "ServiceOrderProductType.findByQuantity", query = "SELECT s FROM ServiceOrderProductType s WHERE s.quantity = :quantity")})
public class ServiceOrderProductType implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ServiceOrderProductTypePK serviceOrderProductTypePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 12, scale = 2)
    private BigDecimal quantity;
    @JoinColumn(name = "ProductTypeId", referencedColumnName = "ProductTypeId", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ProductType productType;
    @JoinColumn(name = "ServiceOrderId", referencedColumnName = "ServiceOrderId", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ServiceOrder serviceOrder;
    @JoinColumn(name = "WeightUnitId", referencedColumnName = "WeightUnitId", nullable = false)
    @ManyToOne(optional = false)
    private WeightUnit weightUnitId;

    public ServiceOrderProductType() {
    }

    public ServiceOrderProductType(ServiceOrderProductTypePK serviceOrderProductTypePK) {
        this.serviceOrderProductTypePK = serviceOrderProductTypePK;
    }

    public ServiceOrderProductType(long serviceOrderId, int productTypeId) {
        this.serviceOrderProductTypePK = new ServiceOrderProductTypePK(serviceOrderId, productTypeId);
    }

    public ServiceOrderProductTypePK getServiceOrderProductTypePK() {
        return serviceOrderProductTypePK;
    }

    public void setServiceOrderProductTypePK(ServiceOrderProductTypePK serviceOrderProductTypePK) {
        this.serviceOrderProductTypePK = serviceOrderProductTypePK;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    public WeightUnit getWeightUnitId() {
        return weightUnitId;
    }

    public void setWeightUnitId(WeightUnit weightUnitId) {
        this.weightUnitId = weightUnitId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceOrderProductTypePK != null ? serviceOrderProductTypePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceOrderProductType)) {
            return false;
        }
        ServiceOrderProductType other = (ServiceOrderProductType) object;
        if ((this.serviceOrderProductTypePK == null && other.serviceOrderProductTypePK != null) || (this.serviceOrderProductTypePK != null && !this.serviceOrderProductTypePK.equals(other.serviceOrderProductTypePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ServiceOrderProductType[ serviceOrderProductTypePK=" + serviceOrderProductTypePK + " ]";
    }
    
}
