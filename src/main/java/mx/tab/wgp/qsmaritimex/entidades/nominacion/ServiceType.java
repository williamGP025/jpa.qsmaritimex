/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades.nominacion;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author William
 */
@Entity
@Table(name = "ServiceType")
public class ServiceType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ServiceTypeId", nullable = false)
    private Integer serviceTypeId;
    @Basic(optional = false)
    @Column(name = "Description", nullable = false)
    private String description;
    @Column(name = "[Order]")
    private Integer order;
    @Column(name = "[Status]")
    private Boolean status;
    //-------------------------------------------------------------
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceTypeId", fetch = FetchType.LAZY)
    private Collection<Service> serviceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceTypeId")
    private Collection<Vessel> vesselCollection;

    public ServiceType() {
    }

    public Collection<Vessel> getVesselCollection() {
        return vesselCollection;
    }

    public void setVesselCollection(Collection<Vessel> vesselCollection) {
        this.vesselCollection = vesselCollection;
    }

    public Collection<Service> getServiceCollection() {
        return serviceCollection;
    }

    public void setServiceCollection(Collection<Service> serviceCollection) {
        this.serviceCollection = serviceCollection;
    }

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
