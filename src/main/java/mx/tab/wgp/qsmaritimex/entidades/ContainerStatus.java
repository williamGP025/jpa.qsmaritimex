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
@Table(name = "ContainerStatus")
public class ContainerStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer containerStatusId;
    @Column(length = 50)
    private String descriptionMX;
    @Column(length = 50)
    private String descriptionUS;
    private Boolean status;

    public ContainerStatus() {
    }

    public ContainerStatus(Integer containerStatusId) {
        this.containerStatusId = containerStatusId;
    }

    public Integer getContainerStatusId() {
        return containerStatusId;
    }

    public void setContainerStatusId(Integer containerStatusId) {
        this.containerStatusId = containerStatusId;
    }

    public String getDescriptionMX() {
        return descriptionMX;
    }

    public void setDescriptionMX(String descriptionMX) {
        this.descriptionMX = descriptionMX;
    }

    public String getDescriptionUS() {
        return descriptionUS;
    }

    public void setDescriptionUS(String descriptionUS) {
        this.descriptionUS = descriptionUS;
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
        hash += (containerStatusId != null ? containerStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContainerStatus)) {
            return false;
        }
        ContainerStatus other = (ContainerStatus) object;
        if ((this.containerStatusId == null && other.containerStatusId != null) || (this.containerStatusId != null && !this.containerStatusId.equals(other.containerStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ContainerStatus[ containerStatusId=" + containerStatusId + " ]";
    }
    
}
