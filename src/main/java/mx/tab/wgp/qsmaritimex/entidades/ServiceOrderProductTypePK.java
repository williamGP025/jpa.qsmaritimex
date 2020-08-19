/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author WilliamGP025
 */
@Embeddable
public class ServiceOrderProductTypePK implements Serializable {

    @Basic(optional = false)
    @Column(nullable = false)
    private long serviceOrderId;
    @Basic(optional = false)
    @Column(nullable = false)
    private int productTypeId;

    public ServiceOrderProductTypePK() {
    }

    public ServiceOrderProductTypePK(long serviceOrderId, int productTypeId) {
        this.serviceOrderId = serviceOrderId;
        this.productTypeId = productTypeId;
    }

    public long getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(long serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) serviceOrderId;
        hash += (int) productTypeId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceOrderProductTypePK)) {
            return false;
        }
        ServiceOrderProductTypePK other = (ServiceOrderProductTypePK) object;
        if (this.serviceOrderId != other.serviceOrderId) {
            return false;
        }
        if (this.productTypeId != other.productTypeId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ServiceOrderProductTypePK[ serviceOrderId=" + serviceOrderId + ", productTypeId=" + productTypeId + " ]";
    }
    
}
