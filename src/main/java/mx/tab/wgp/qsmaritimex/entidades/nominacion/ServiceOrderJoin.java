/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades.nominacion;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author William
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceOrderJoin.findAll", query = "SELECT s FROM ServiceOrderJoin s"),
    @NamedQuery(name = "ServiceOrderJoin.findByServiceOrderJoinId", query = "SELECT s FROM ServiceOrderJoin s WHERE s.serviceOrderJoinId = :serviceOrderJoinId")})
public class ServiceOrderJoin implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false, precision = 19, scale = 0)
    private BigDecimal serviceOrderJoinId;
    @JoinColumn(name = "ServiceOrderIdFather", referencedColumnName = "serviceOrderId")
    @ManyToOne
    private ServiceOrder serviceOrderIdFather;
    @JoinColumn(name = "ServiceOrderIdSon", referencedColumnName = "serviceOrderId")
    @ManyToOne
    private ServiceOrder serviceOrderIdSon;
    @JoinColumn(name = "ServiceOrderJoinTypeId", referencedColumnName = "serviceOrderJoinTypeId")
    @ManyToOne
    private ServiceOrderJoinType serviceOrderJoinTypeId;

    public ServiceOrderJoin() {
    }

    public ServiceOrderJoin(BigDecimal serviceOrderJoinId) {
        this.serviceOrderJoinId = serviceOrderJoinId;
    }

    public BigDecimal getServiceOrderJoinId() {
        return serviceOrderJoinId;
    }

    public void setServiceOrderJoinId(BigDecimal serviceOrderJoinId) {
        this.serviceOrderJoinId = serviceOrderJoinId;
    }

    public ServiceOrder getServiceOrderIdFather() {
        return serviceOrderIdFather;
    }

    public void setServiceOrderIdFather(ServiceOrder serviceOrderIdFather) {
        this.serviceOrderIdFather = serviceOrderIdFather;
    }

    public ServiceOrder getServiceOrderIdSon() {
        return serviceOrderIdSon;
    }

    public void setServiceOrderIdSon(ServiceOrder serviceOrderIdSon) {
        this.serviceOrderIdSon = serviceOrderIdSon;
    }

    public ServiceOrderJoinType getServiceOrderJoinTypeId() {
        return serviceOrderJoinTypeId;
    }

    public void setServiceOrderJoinTypeId(ServiceOrderJoinType serviceOrderJoinTypeId) {
        this.serviceOrderJoinTypeId = serviceOrderJoinTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceOrderJoinId != null ? serviceOrderJoinId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceOrderJoin)) {
            return false;
        }
        ServiceOrderJoin other = (ServiceOrderJoin) object;
        if ((this.serviceOrderJoinId == null && other.serviceOrderJoinId != null) || (this.serviceOrderJoinId != null && !this.serviceOrderJoinId.equals(other.serviceOrderJoinId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ServiceOrderJoin[ serviceOrderJoinId=" + serviceOrderJoinId + " ]";
    }
    
}
