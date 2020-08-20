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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author William
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Currency.findAll", query = "SELECT c FROM Currency c"),
    @NamedQuery(name = "Currency.findByCurrencyId", query = "SELECT c FROM Currency c WHERE c.currencyId = :currencyId"),
    @NamedQuery(name = "Currency.findByAbbreviation", query = "SELECT c FROM Currency c WHERE c.abbreviation = :abbreviation"),
    @NamedQuery(name = "Currency.findByEnable", query = "SELECT c FROM Currency c WHERE c.enable = :enable"),
    @NamedQuery(name = "Currency.findByName", query = "SELECT c FROM Currency c WHERE c.name = :name"),
    @NamedQuery(name = "Currency.findByStatus", query = "SELECT c FROM Currency c WHERE c.status = :status")})
public class Currency implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Short currencyId;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String abbreviation;
    @Column(length = 10)
    private String enable;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String name;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @OneToMany(mappedBy = "currencyId")
    private Collection<ServiceOrderService> serviceOrderServiceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "currencyId")
    private Collection<ServiceOrder> serviceOrderCollection;

    public Currency() {
    }

    public Currency(Short currencyId) {
        this.currencyId = currencyId;
    }

    public Currency(Short currencyId, String abbreviation, String name, boolean status) {
        this.currencyId = currencyId;
        this.abbreviation = abbreviation;
        this.name = name;
        this.status = status;
    }

    public Short getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Short currencyId) {
        this.currencyId = currencyId;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public Collection<ServiceOrder> getServiceOrderCollection() {
        return serviceOrderCollection;
    }

    public void setServiceOrderCollection(Collection<ServiceOrder> serviceOrderCollection) {
        this.serviceOrderCollection = serviceOrderCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (currencyId != null ? currencyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Currency)) {
            return false;
        }
        Currency other = (Currency) object;
        if ((this.currencyId == null && other.currencyId != null) || (this.currencyId != null && !this.currencyId.equals(other.currencyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.Currency[ currencyId=" + currencyId + " ]";
    }
    
}
