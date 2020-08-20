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
@Table(name = "SpecialRolScreenObject")
public class SpecialRolScreenObject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idSpecialScreenObject;
    private Integer idUser;
    @Column(length = 50)
    private String property;
    @Column(length = 50)
    private String value;
    @JoinColumn(name = "idScreenObject", referencedColumnName = "screenObjectId")
    @ManyToOne
    private ScreenObject idScreenObject;

    public SpecialRolScreenObject() {
    }

    public SpecialRolScreenObject(Integer idSpecialScreenObject) {
        this.idSpecialScreenObject = idSpecialScreenObject;
    }

    public Integer getIdSpecialScreenObject() {
        return idSpecialScreenObject;
    }

    public void setIdSpecialScreenObject(Integer idSpecialScreenObject) {
        this.idSpecialScreenObject = idSpecialScreenObject;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ScreenObject getIdScreenObject() {
        return idScreenObject;
    }

    public void setIdScreenObject(ScreenObject idScreenObject) {
        this.idScreenObject = idScreenObject;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSpecialScreenObject != null ? idSpecialScreenObject.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpecialRolScreenObject)) {
            return false;
        }
        SpecialRolScreenObject other = (SpecialRolScreenObject) object;
        if ((this.idSpecialScreenObject == null && other.idSpecialScreenObject != null) || (this.idSpecialScreenObject != null && !this.idSpecialScreenObject.equals(other.idSpecialScreenObject))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.SpecialRolScreenObject[ idSpecialScreenObject=" + idSpecialScreenObject + " ]";
    }
    
}
