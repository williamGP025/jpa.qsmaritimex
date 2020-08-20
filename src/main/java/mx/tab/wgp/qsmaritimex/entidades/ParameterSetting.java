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
@Table(name = "ParameterSetting")
public class ParameterSetting implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer parameterSettingId;
    @Basic(optional = false)
    @Column(nullable = false, length = 200)
    private String description;
    @Basic(optional = false)
    @Column(nullable = false, length = 200)
    private String name;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @Basic(optional = false)
    @Column(nullable = false, length = 1000)
    private String value;

    public ParameterSetting() {
    }

    public ParameterSetting(Integer parameterSettingId) {
        this.parameterSettingId = parameterSettingId;
    }

    public ParameterSetting(Integer parameterSettingId, String description, String name, boolean status, String value) {
        this.parameterSettingId = parameterSettingId;
        this.description = description;
        this.name = name;
        this.status = status;
        this.value = value;
    }

    public Integer getParameterSettingId() {
        return parameterSettingId;
    }

    public void setParameterSettingId(Integer parameterSettingId) {
        this.parameterSettingId = parameterSettingId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parameterSettingId != null ? parameterSettingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParameterSetting)) {
            return false;
        }
        ParameterSetting other = (ParameterSetting) object;
        if ((this.parameterSettingId == null && other.parameterSettingId != null) || (this.parameterSettingId != null && !this.parameterSettingId.equals(other.parameterSettingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ParameterSetting[ parameterSettingId=" + parameterSettingId + " ]";
    }
    
}
