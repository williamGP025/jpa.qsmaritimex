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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WilliamGP025
 */
@Entity
@Table(catalog = "QSMaritimex", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParameterSetting.findAll", query = "SELECT p FROM ParameterSetting p"),
    @NamedQuery(name = "ParameterSetting.findByParameterSettingId", query = "SELECT p FROM ParameterSetting p WHERE p.parameterSettingId = :parameterSettingId"),
    @NamedQuery(name = "ParameterSetting.findByName", query = "SELECT p FROM ParameterSetting p WHERE p.name = :name"),
    @NamedQuery(name = "ParameterSetting.findByValue", query = "SELECT p FROM ParameterSetting p WHERE p.value = :value"),
    @NamedQuery(name = "ParameterSetting.findByDescription", query = "SELECT p FROM ParameterSetting p WHERE p.description = :description"),
    @NamedQuery(name = "ParameterSetting.findByStatus", query = "SELECT p FROM ParameterSetting p WHERE p.status = :status")})
public class ParameterSetting implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer parameterSettingId;
    @Basic(optional = false)
    @Column(nullable = false, length = 200)
    private String name;
    @Basic(optional = false)
    @Column(nullable = false, length = 1000)
    private String value;
    @Basic(optional = false)
    @Column(nullable = false, length = 200)
    private String description;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;

    public ParameterSetting() {
    }

    public ParameterSetting(Integer parameterSettingId) {
        this.parameterSettingId = parameterSettingId;
    }

    public ParameterSetting(Integer parameterSettingId, String name, String value, String description, boolean status) {
        this.parameterSettingId = parameterSettingId;
        this.name = name;
        this.value = value;
        this.description = description;
        this.status = status;
    }

    public Integer getParameterSettingId() {
        return parameterSettingId;
    }

    public void setParameterSettingId(Integer parameterSettingId) {
        this.parameterSettingId = parameterSettingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
