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
    @NamedQuery(name = "AmanacMovement.findAll", query = "SELECT a FROM AmanacMovement a"),
    @NamedQuery(name = "AmanacMovement.findByAmanacMovementId", query = "SELECT a FROM AmanacMovement a WHERE a.amanacMovementId = :amanacMovementId"),
    @NamedQuery(name = "AmanacMovement.findByCode", query = "SELECT a FROM AmanacMovement a WHERE a.code = :code"),
    @NamedQuery(name = "AmanacMovement.findByRecordNumber", query = "SELECT a FROM AmanacMovement a WHERE a.recordNumber = :recordNumber"),
    @NamedQuery(name = "AmanacMovement.findByStatus", query = "SELECT a FROM AmanacMovement a WHERE a.status = :status")})
public class AmanacMovement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer amanacMovementId;
    @Basic(optional = false)
    @Column(nullable = false)
    private int code;
    @Basic(optional = false)
    @Column(nullable = false)
    private int recordNumber;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;

    public AmanacMovement() {
    }

    public AmanacMovement(Integer amanacMovementId) {
        this.amanacMovementId = amanacMovementId;
    }

    public AmanacMovement(Integer amanacMovementId, int code, int recordNumber, boolean status) {
        this.amanacMovementId = amanacMovementId;
        this.code = code;
        this.recordNumber = recordNumber;
        this.status = status;
    }

    public Integer getAmanacMovementId() {
        return amanacMovementId;
    }

    public void setAmanacMovementId(Integer amanacMovementId) {
        this.amanacMovementId = amanacMovementId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
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
        hash += (amanacMovementId != null ? amanacMovementId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AmanacMovement)) {
            return false;
        }
        AmanacMovement other = (AmanacMovement) object;
        if ((this.amanacMovementId == null && other.amanacMovementId != null) || (this.amanacMovementId != null && !this.amanacMovementId.equals(other.amanacMovementId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.AmanacMovement[ amanacMovementId=" + amanacMovementId + " ]";
    }
    
}
