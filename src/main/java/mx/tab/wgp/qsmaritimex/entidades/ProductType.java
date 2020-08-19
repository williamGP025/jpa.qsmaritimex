/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "ProductType.findAll", query = "SELECT p FROM ProductType p"),
    @NamedQuery(name = "ProductType.findByProductTypeId", query = "SELECT p FROM ProductType p WHERE p.productTypeId = :productTypeId"),
    @NamedQuery(name = "ProductType.findByDescriptionMX", query = "SELECT p FROM ProductType p WHERE p.descriptionMX = :descriptionMX"),
    @NamedQuery(name = "ProductType.findByDescriptionUS", query = "SELECT p FROM ProductType p WHERE p.descriptionUS = :descriptionUS"),
    @NamedQuery(name = "ProductType.findBySyncCRM", query = "SELECT p FROM ProductType p WHERE p.syncCRM = :syncCRM"),
    @NamedQuery(name = "ProductType.findByStatus", query = "SELECT p FROM ProductType p WHERE p.status = :status")})
public class ProductType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer productTypeId;
    @Basic(optional = false)
    @Column(nullable = false, length = 500)
    private String descriptionMX;
    @Column(length = 50)
    private String descriptionUS;
    private Boolean syncCRM;
    private Boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productType")
    private Collection<ServiceOrderProductType> serviceOrderProductTypeCollection;

    public ProductType() {
    }

    public ProductType(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public ProductType(Integer productTypeId, String descriptionMX) {
        this.productTypeId = productTypeId;
        this.descriptionMX = descriptionMX;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getDescriptionMX() {
        return descriptionMX;
    }

    public void setDescriptionMX(String descriptionMX) {
        this.descriptionMX = descriptionMX;
    }

    public String getDescriptionUS() {
        return descriptionUS;
    }

    public void setDescriptionUS(String descriptionUS) {
        this.descriptionUS = descriptionUS;
    }

    public Boolean getSyncCRM() {
        return syncCRM;
    }

    public void setSyncCRM(Boolean syncCRM) {
        this.syncCRM = syncCRM;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<ServiceOrderProductType> getServiceOrderProductTypeCollection() {
        return serviceOrderProductTypeCollection;
    }

    public void setServiceOrderProductTypeCollection(Collection<ServiceOrderProductType> serviceOrderProductTypeCollection) {
        this.serviceOrderProductTypeCollection = serviceOrderProductTypeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productTypeId != null ? productTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductType)) {
            return false;
        }
        ProductType other = (ProductType) object;
        if ((this.productTypeId == null && other.productTypeId != null) || (this.productTypeId != null && !this.productTypeId.equals(other.productTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ProductType[ productTypeId=" + productTypeId + " ]";
    }
    
}
