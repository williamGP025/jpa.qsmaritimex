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
    @NamedQuery(name = "ContainerType.findAll", query = "SELECT c FROM ContainerType c"),
    @NamedQuery(name = "ContainerType.findByContainerTypeId", query = "SELECT c FROM ContainerType c WHERE c.containerTypeId = :containerTypeId"),
    @NamedQuery(name = "ContainerType.findByCapacity", query = "SELECT c FROM ContainerType c WHERE c.capacity = :capacity"),
    @NamedQuery(name = "ContainerType.findByCubicCapacity", query = "SELECT c FROM ContainerType c WHERE c.cubicCapacity = :cubicCapacity"),
    @NamedQuery(name = "ContainerType.findByDescription", query = "SELECT c FROM ContainerType c WHERE c.description = :description"),
    @NamedQuery(name = "ContainerType.findBySize", query = "SELECT c FROM ContainerType c WHERE c.size = :size"),
    @NamedQuery(name = "ContainerType.findByStatus", query = "SELECT c FROM ContainerType c WHERE c.status = :status")})
public class ContainerType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer containerTypeId;
    private Integer capacity;
    private Integer cubicCapacity;
    @Column(length = 100)
    private String description;
    private Integer size;
    private Boolean status;

    public ContainerType() {
    }

    public ContainerType(Integer containerTypeId) {
        this.containerTypeId = containerTypeId;
    }

    public Integer getContainerTypeId() {
        return containerTypeId;
    }

    public void setContainerTypeId(Integer containerTypeId) {
        this.containerTypeId = containerTypeId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getCubicCapacity() {
        return cubicCapacity;
    }

    public void setCubicCapacity(Integer cubicCapacity) {
        this.cubicCapacity = cubicCapacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (containerTypeId != null ? containerTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContainerType)) {
            return false;
        }
        ContainerType other = (ContainerType) object;
        if ((this.containerTypeId == null && other.containerTypeId != null) || (this.containerTypeId != null && !this.containerTypeId.equals(other.containerTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ContainerType[ containerTypeId=" + containerTypeId + " ]";
    }
    
}
