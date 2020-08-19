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
    @NamedQuery(name = "WeightUnit.findAll", query = "SELECT w FROM WeightUnit w"),
    @NamedQuery(name = "WeightUnit.findByWeightUnitId", query = "SELECT w FROM WeightUnit w WHERE w.weightUnitId = :weightUnitId"),
    @NamedQuery(name = "WeightUnit.findByWeightCode", query = "SELECT w FROM WeightUnit w WHERE w.weightCode = :weightCode"),
    @NamedQuery(name = "WeightUnit.findByDescription", query = "SELECT w FROM WeightUnit w WHERE w.description = :description"),
    @NamedQuery(name = "WeightUnit.findByCRMCode", query = "SELECT w FROM WeightUnit w WHERE w.cRMCode = :cRMCode"),
    @NamedQuery(name = "WeightUnit.findByStatus", query = "SELECT w FROM WeightUnit w WHERE w.status = :status")})
public class WeightUnit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer weightUnitId;
    private Integer weightCode;
    @Column(length = 300)
    private String description;
    @Column(length = 200)
    private String cRMCode;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "weightUnitId")
    private Collection<ServiceOrderProductType> serviceOrderProductTypeCollection;

    public WeightUnit() {
    }

    public WeightUnit(Integer weightUnitId) {
        this.weightUnitId = weightUnitId;
    }

    public WeightUnit(Integer weightUnitId, boolean status) {
        this.weightUnitId = weightUnitId;
        this.status = status;
    }

    public Integer getWeightUnitId() {
        return weightUnitId;
    }

    public void setWeightUnitId(Integer weightUnitId) {
        this.weightUnitId = weightUnitId;
    }

    public Integer getWeightCode() {
        return weightCode;
    }

    public void setWeightCode(Integer weightCode) {
        this.weightCode = weightCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCRMCode() {
        return cRMCode;
    }

    public void setCRMCode(String cRMCode) {
        this.cRMCode = cRMCode;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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
        hash += (weightUnitId != null ? weightUnitId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WeightUnit)) {
            return false;
        }
        WeightUnit other = (WeightUnit) object;
        if ((this.weightUnitId == null && other.weightUnitId != null) || (this.weightUnitId != null && !this.weightUnitId.equals(other.weightUnitId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.WeightUnit[ weightUnitId=" + weightUnitId + " ]";
    }
    
}
