/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades.pantalla;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author William
 */
@Entity
@XmlRootElement
@Table(name = "RolScreenObject")
public class RolScreenObject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer screenObjectRolId;
    @Column(length = 50)
    private String property;
    private Integer rolId;
    @Column(length = 50)
    private String value;
    @JoinColumn(name = "ScreenObjectId", referencedColumnName = "screenObjectId")
    @ManyToOne
    private ScreenObject screenObjectId;

    public RolScreenObject() {
    }

    public RolScreenObject(Integer screenObjectRolId) {
        this.screenObjectRolId = screenObjectRolId;
    }

    public Integer getScreenObjectRolId() {
        return screenObjectRolId;
    }

    public void setScreenObjectRolId(Integer screenObjectRolId) {
        this.screenObjectRolId = screenObjectRolId;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ScreenObject getScreenObjectId() {
        return screenObjectId;
    }

    public void setScreenObjectId(ScreenObject screenObjectId) {
        this.screenObjectId = screenObjectId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (screenObjectRolId != null ? screenObjectRolId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolScreenObject)) {
            return false;
        }
        RolScreenObject other = (RolScreenObject) object;
        if ((this.screenObjectRolId == null && other.screenObjectRolId != null) || (this.screenObjectRolId != null && !this.screenObjectRolId.equals(other.screenObjectRolId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.RolScreenObject[ screenObjectRolId=" + screenObjectRolId + " ]";
    }
    
}
