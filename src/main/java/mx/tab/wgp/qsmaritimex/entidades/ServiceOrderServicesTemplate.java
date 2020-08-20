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
import javax.persistence.Table;

/**
 *
 * @author William
 */
@Entity
@Table(name = "ServiceOrderServicesTemplate")
public class ServiceOrderServicesTemplate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ServiceOrderServicesTemplateId", nullable = false)
    @Basic(optional = false)
    private Integer serviceOrderServicesTemplateId;
    @Column(name = "ActingRolesTypeId", nullable = false)
    @Basic(optional = false)
    private Integer actingRolesTypeId;
    @Column(name = "[Order]", nullable = false)
    @Basic(optional = false)
    private Integer order;
    @Column(name = "[Count]", nullable = false)
    @Basic(optional = false)
    private Integer count;
    //--------------------------------------------------------------------------
    @JoinColumn(name = "ServiceId", referencedColumnName = "ServiceId", nullable = false)
    @ManyToOne(optional = false)
    private Service serviceId;

    public ServiceOrderServicesTemplate() {
    }

    public Integer getServiceOrderServicesTemplateId() {
        return serviceOrderServicesTemplateId;
    }

    public void setServiceOrderServicesTemplateId(Integer serviceOrderServicesTemplateId) {
        this.serviceOrderServicesTemplateId = serviceOrderServicesTemplateId;
    }

    public Integer getActingRolesTypeId() {
        return actingRolesTypeId;
    }

    public void setActingRolesTypeId(Integer actingRolesTypeId) {
        this.actingRolesTypeId = actingRolesTypeId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Service getServiceId() {
        return serviceId;
    }

    public void setServiceId(Service serviceId) {
        this.serviceId = serviceId;
    }

}
