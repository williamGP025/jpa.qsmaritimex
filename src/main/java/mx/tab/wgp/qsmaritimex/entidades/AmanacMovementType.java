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
    @NamedQuery(name = "AmanacMovementType.findAll", query = "SELECT a FROM AmanacMovementType a"),
    @NamedQuery(name = "AmanacMovementType.findByAmanacMovementTypeId", query = "SELECT a FROM AmanacMovementType a WHERE a.amanacMovementTypeId = :amanacMovementTypeId"),
    @NamedQuery(name = "AmanacMovementType.findByCode", query = "SELECT a FROM AmanacMovementType a WHERE a.code = :code"),
    @NamedQuery(name = "AmanacMovementType.findByDescription", query = "SELECT a FROM AmanacMovementType a WHERE a.description = :description"),
    @NamedQuery(name = "AmanacMovementType.findByStatus", query = "SELECT a FROM AmanacMovementType a WHERE a.status = :status")})
public class AmanacMovementType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer amanacMovementTypeId;
    @Basic(optional = false)
    @Column(nullable = false)
    private int code;
    @Basic(optional = false)
    @Column(nullable = false, length = 200)
    private String description;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;

    public AmanacMovementType() {
    }

    public AmanacMovementType(Integer amanacMovementTypeId) {
        this.amanacMovementTypeId = amanacMovementTypeId;
    }

    public AmanacMovementType(Integer amanacMovementTypeId, int code, String description, boolean status) {
        this.amanacMovementTypeId = amanacMovementTypeId;
        this.code = code;
        this.description = description;
        this.status = status;
    }

    public Integer getAmanacMovementTypeId() {
        return amanacMovementTypeId;
    }

    public void setAmanacMovementTypeId(Integer amanacMovementTypeId) {
        this.amanacMovementTypeId = amanacMovementTypeId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash += (amanacMovementTypeId != null ? amanacMovementTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AmanacMovementType)) {
            return false;
        }
        AmanacMovementType other = (AmanacMovementType) object;
        if ((this.amanacMovementTypeId == null && other.amanacMovementTypeId != null) || (this.amanacMovementTypeId != null && !this.amanacMovementTypeId.equals(other.amanacMovementTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.AmanacMovementType[ amanacMovementTypeId=" + amanacMovementTypeId + " ]";
    }
    
}
