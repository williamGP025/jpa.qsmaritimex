/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades.nominacion;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author William
 */
@Embeddable
public class ServiceOrderProductTypePK implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(nullable = false, name = "ProductTypeId")
    private int productTypeId;
    @Basic(optional = false)
    @Column(nullable = false, name = "ServiceOrderId", precision = 19, scale = 0)
    private BigInteger serviceOrderId;

    public ServiceOrderProductTypePK() {
    }

    public ServiceOrderProductTypePK(int productTypeId, BigInteger serviceOrderId) {
        this.productTypeId = productTypeId;
        this.serviceOrderId = serviceOrderId;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public BigInteger getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(BigInteger serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) productTypeId;
        hash += (serviceOrderId != null ? serviceOrderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceOrderProductTypePK)) {
            return false;
        }
        ServiceOrderProductTypePK other = (ServiceOrderProductTypePK) object;
        if (this.productTypeId != other.productTypeId) {
            return false;
        }
        if ((this.serviceOrderId == null && other.serviceOrderId != null) || (this.serviceOrderId != null && !this.serviceOrderId.equals(other.serviceOrderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ServiceOrderProductTypePK[ productTypeId=" + productTypeId + ", serviceOrderId=" + serviceOrderId + " ]";
    }

}
