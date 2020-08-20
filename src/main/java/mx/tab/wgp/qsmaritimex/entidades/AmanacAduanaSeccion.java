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
@Table(name = "AmanacAduanaSeccion")
public class AmanacAduanaSeccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer aduanaSeccionCode;
    @Column(length = 200)
    private String name;
    private Integer portId;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;

    public AmanacAduanaSeccion() {
    }

    public AmanacAduanaSeccion(Integer aduanaSeccionCode) {
        this.aduanaSeccionCode = aduanaSeccionCode;
    }

    public AmanacAduanaSeccion(Integer aduanaSeccionCode, boolean status) {
        this.aduanaSeccionCode = aduanaSeccionCode;
        this.status = status;
    }

    public Integer getAduanaSeccionCode() {
        return aduanaSeccionCode;
    }

    public void setAduanaSeccionCode(Integer aduanaSeccionCode) {
        this.aduanaSeccionCode = aduanaSeccionCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPortId() {
        return portId;
    }

    public void setPortId(Integer portId) {
        this.portId = portId;
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
        hash += (aduanaSeccionCode != null ? aduanaSeccionCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AmanacAduanaSeccion)) {
            return false;
        }
        AmanacAduanaSeccion other = (AmanacAduanaSeccion) object;
        if ((this.aduanaSeccionCode == null && other.aduanaSeccionCode != null) || (this.aduanaSeccionCode != null && !this.aduanaSeccionCode.equals(other.aduanaSeccionCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.AmanacAduanaSeccion[ aduanaSeccionCode=" + aduanaSeccionCode + " ]";
    }
    
}
