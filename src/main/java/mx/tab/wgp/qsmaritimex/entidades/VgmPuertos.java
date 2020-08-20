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
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author William
 */
@Entity
@Table(name = "Vgm_Puertos")
@XmlRootElement
public class VgmPuertos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer vgmPuertosId;
    @Basic(optional = false)
    @Column(nullable = false)
    private int idPuerto;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String name;
    @Basic(optional = false)
    @Column(nullable = false)
    private int numPuertoLiner;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;

    public VgmPuertos() {
    }

    public VgmPuertos(Integer vgmPuertosId) {
        this.vgmPuertosId = vgmPuertosId;
    }

    public VgmPuertos(Integer vgmPuertosId, int idPuerto, String name, int numPuertoLiner, boolean status) {
        this.vgmPuertosId = vgmPuertosId;
        this.idPuerto = idPuerto;
        this.name = name;
        this.numPuertoLiner = numPuertoLiner;
        this.status = status;
    }

    public Integer getVgmPuertosId() {
        return vgmPuertosId;
    }

    public void setVgmPuertosId(Integer vgmPuertosId) {
        this.vgmPuertosId = vgmPuertosId;
    }

    public int getIdPuerto() {
        return idPuerto;
    }

    public void setIdPuerto(int idPuerto) {
        this.idPuerto = idPuerto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumPuertoLiner() {
        return numPuertoLiner;
    }

    public void setNumPuertoLiner(int numPuertoLiner) {
        this.numPuertoLiner = numPuertoLiner;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vgmPuertosId != null ? vgmPuertosId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VgmPuertos)) {
            return false;
        }
        VgmPuertos other = (VgmPuertos) object;
        if ((this.vgmPuertosId == null && other.vgmPuertosId != null) || (this.vgmPuertosId != null && !this.vgmPuertosId.equals(other.vgmPuertosId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.VgmPuertos[ vgmPuertosId=" + vgmPuertosId + " ]";
    }
    
}
