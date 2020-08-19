/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades;

import java.io.Serializable;
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
    @NamedQuery(name = "ServiceOrderServicesTemplate.findAll", query = "SELECT s FROM ServiceOrderServicesTemplate s"),
    @NamedQuery(name = "ServiceOrderServicesTemplate.findByServiceOrderServicesTemplateId", query = "SELECT s FROM ServiceOrderServicesTemplate s WHERE s.serviceOrderServicesTemplateId = :serviceOrderServicesTemplateId"),
    @NamedQuery(name = "ServiceOrderServicesTemplate.findByOrder", query = "SELECT s FROM ServiceOrderServicesTemplate s WHERE s.order = :order"),
    @NamedQuery(name = "ServiceOrderServicesTemplate.findByCount", query = "SELECT s FROM ServiceOrderServicesTemplate s WHERE s.count = :count")})
public class ServiceOrderServicesTemplate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer serviceOrderServicesTemplateId;
    @Basic(optional = false)
    @Column(nullable = false)
    private int order;
    @Basic(optional = false)
    @Column(nullable = false)
    private int count;
    @JoinColumn(name = "ActingRolesTypeId", referencedColumnName = "ActingRolesTypeId", nullable = false)
    @ManyToOne(optional = false)
    private ActingRolesType actingRolesTypeId;
    @JoinColumn(name = "ServiceId", referencedColumnName = "ServiceId", nullable = false)
    @ManyToOne(optional = false)
    private Service serviceId;

    public ServiceOrderServicesTemplate() {
    }

    public ServiceOrderServicesTemplate(Integer serviceOrderServicesTemplateId) {
        this.serviceOrderServicesTemplateId = serviceOrderServicesTemplateId;
    }

    public ServiceOrderServicesTemplate(Integer serviceOrderServicesTemplateId, int order, int count) {
        this.serviceOrderServicesTemplateId = serviceOrderServicesTemplateId;
        this.order = order;
        this.count = count;
    }

    public Integer getServiceOrderServicesTemplateId() {
        return serviceOrderServicesTemplateId;
    }

    public void setServiceOrderServicesTemplateId(Integer serviceOrderServicesTemplateId) {
        this.serviceOrderServicesTemplateId = serviceOrderServicesTemplateId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ActingRolesType getActingRolesTypeId() {
        return actingRolesTypeId;
    }

    public void setActingRolesTypeId(ActingRolesType actingRolesTypeId) {
        this.actingRolesTypeId = actingRolesTypeId;
    }

    public Service getServiceId() {
        return serviceId;
    }

    public void setServiceId(Service serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceOrderServicesTemplateId != null ? serviceOrderServicesTemplateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceOrderServicesTemplate)) {
            return false;
        }
        ServiceOrderServicesTemplate other = (ServiceOrderServicesTemplate) object;
        if ((this.serviceOrderServicesTemplateId == null && other.serviceOrderServicesTemplateId != null) || (this.serviceOrderServicesTemplateId != null && !this.serviceOrderServicesTemplateId.equals(other.serviceOrderServicesTemplateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ServiceOrderServicesTemplate[ serviceOrderServicesTemplateId=" + serviceOrderServicesTemplateId + " ]";
    }
    
}
