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
    @NamedQuery(name = "BLTransportType.findAll", query = "SELECT b FROM BLTransportType b"),
    @NamedQuery(name = "BLTransportType.findByBLTransportTypeId", query = "SELECT b FROM BLTransportType b WHERE b.bLTransportTypeId = :bLTransportTypeId"),
    @NamedQuery(name = "BLTransportType.findByIntTransportTypeId", query = "SELECT b FROM BLTransportType b WHERE b.intTransportTypeId = :intTransportTypeId"),
    @NamedQuery(name = "BLTransportType.findByStatus", query = "SELECT b FROM BLTransportType b WHERE b.status = :status"),
    @NamedQuery(name = "BLTransportType.findByStrDescription", query = "SELECT b FROM BLTransportType b WHERE b.strDescription = :strDescription"),
    @NamedQuery(name = "BLTransportType.findByStrName", query = "SELECT b FROM BLTransportType b WHERE b.strName = :strName")})
public class BLTransportType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer bLTransportTypeId;
    @Basic(optional = false)
    @Column(nullable = false)
    private int intTransportTypeId;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @Column(length = 50)
    private String strDescription;
    @Column(length = 10)
    private String strName;

    public BLTransportType() {
    }

    public BLTransportType(Integer bLTransportTypeId) {
        this.bLTransportTypeId = bLTransportTypeId;
    }

    public BLTransportType(Integer bLTransportTypeId, int intTransportTypeId, boolean status) {
        this.bLTransportTypeId = bLTransportTypeId;
        this.intTransportTypeId = intTransportTypeId;
        this.status = status;
    }

    public Integer getBLTransportTypeId() {
        return bLTransportTypeId;
    }

    public void setBLTransportTypeId(Integer bLTransportTypeId) {
        this.bLTransportTypeId = bLTransportTypeId;
    }

    public int getIntTransportTypeId() {
        return intTransportTypeId;
    }

    public void setIntTransportTypeId(int intTransportTypeId) {
        this.intTransportTypeId = intTransportTypeId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bLTransportTypeId != null ? bLTransportTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BLTransportType)) {
            return false;
        }
        BLTransportType other = (BLTransportType) object;
        if ((this.bLTransportTypeId == null && other.bLTransportTypeId != null) || (this.bLTransportTypeId != null && !this.bLTransportTypeId.equals(other.bLTransportTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.BLTransportType[ bLTransportTypeId=" + bLTransportTypeId + " ]";
    }
    
}
