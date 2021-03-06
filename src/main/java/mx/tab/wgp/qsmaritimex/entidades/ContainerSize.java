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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author William
 */
@Entity
@XmlRootElement
@Table(name = "ContainerSize")
public class ContainerSize implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer containerSizeId;
    private Integer size;
    private Boolean status;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 53, scale = 0)
    private Double teus;

    public ContainerSize() {
    }

    public ContainerSize(Integer containerSizeId) {
        this.containerSizeId = containerSizeId;
    }

    public Integer getContainerSizeId() {
        return containerSizeId;
    }

    public void setContainerSizeId(Integer containerSizeId) {
        this.containerSizeId = containerSizeId;
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

    public Double getTeus() {
        return teus;
    }

    public void setTeus(Double teus) {
        this.teus = teus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (containerSizeId != null ? containerSizeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContainerSize)) {
            return false;
        }
        ContainerSize other = (ContainerSize) object;
        if ((this.containerSizeId == null && other.containerSizeId != null) || (this.containerSizeId != null && !this.containerSizeId.equals(other.containerSizeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ContainerSize[ containerSizeId=" + containerSizeId + " ]";
    }
    
}
