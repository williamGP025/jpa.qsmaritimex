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
    @NamedQuery(name = "AmanacError.findAll", query = "SELECT a FROM AmanacError a"),
    @NamedQuery(name = "AmanacError.findByAmanacErrorId", query = "SELECT a FROM AmanacError a WHERE a.amanacErrorId = :amanacErrorId"),
    @NamedQuery(name = "AmanacError.findByCode", query = "SELECT a FROM AmanacError a WHERE a.code = :code"),
    @NamedQuery(name = "AmanacError.findByDescription", query = "SELECT a FROM AmanacError a WHERE a.description = :description"),
    @NamedQuery(name = "AmanacError.findByDescriptionEng", query = "SELECT a FROM AmanacError a WHERE a.descriptionEng = :descriptionEng"),
    @NamedQuery(name = "AmanacError.findByStatus", query = "SELECT a FROM AmanacError a WHERE a.status = :status")})
public class AmanacError implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer amanacErrorId;
    @Basic(optional = false)
    @Column(nullable = false)
    private int code;
    @Column(length = 255)
    private String description;
    @Column(length = 255)
    private String descriptionEng;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;

    public AmanacError() {
    }

    public AmanacError(Integer amanacErrorId) {
        this.amanacErrorId = amanacErrorId;
    }

    public AmanacError(Integer amanacErrorId, int code, boolean status) {
        this.amanacErrorId = amanacErrorId;
        this.code = code;
        this.status = status;
    }

    public Integer getAmanacErrorId() {
        return amanacErrorId;
    }

    public void setAmanacErrorId(Integer amanacErrorId) {
        this.amanacErrorId = amanacErrorId;
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

    public String getDescriptionEng() {
        return descriptionEng;
    }

    public void setDescriptionEng(String descriptionEng) {
        this.descriptionEng = descriptionEng;
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
        hash += (amanacErrorId != null ? amanacErrorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AmanacError)) {
            return false;
        }
        AmanacError other = (AmanacError) object;
        if ((this.amanacErrorId == null && other.amanacErrorId != null) || (this.amanacErrorId != null && !this.amanacErrorId.equals(other.amanacErrorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.AmanacError[ amanacErrorId=" + amanacErrorId + " ]";
    }
    
}
