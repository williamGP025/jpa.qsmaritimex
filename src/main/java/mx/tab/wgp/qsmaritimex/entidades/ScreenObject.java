/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author WilliamGP025
 */
@Entity
@Table(catalog = "QSMaritimex", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScreenObject.findAll", query = "SELECT s FROM ScreenObject s"),
    @NamedQuery(name = "ScreenObject.findByScreenObjectId", query = "SELECT s FROM ScreenObject s WHERE s.screenObjectId = :screenObjectId"),
    @NamedQuery(name = "ScreenObject.findByScreenObjectName", query = "SELECT s FROM ScreenObject s WHERE s.screenObjectName = :screenObjectName"),
    @NamedQuery(name = "ScreenObject.findByScreenObjectDescription", query = "SELECT s FROM ScreenObject s WHERE s.screenObjectDescription = :screenObjectDescription")})
public class ScreenObject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer screenObjectId;
    @Column(length = 100)
    private String screenObjectName;
    @Column(length = 500)
    private String screenObjectDescription;
    @OneToMany(mappedBy = "idScreenObject")
    private Collection<SpecialRolScreenObject> specialRolScreenObjectCollection;
    @JoinColumn(name = "ScreenId", referencedColumnName = "ScreenId")
    @ManyToOne
    private Screen screenId;
    @OneToMany(mappedBy = "screenObjectId")
    private Collection<RolScreenObject> rolScreenObjectCollection;

    public ScreenObject() {
    }

    public ScreenObject(Integer screenObjectId) {
        this.screenObjectId = screenObjectId;
    }

    public Integer getScreenObjectId() {
        return screenObjectId;
    }

    public void setScreenObjectId(Integer screenObjectId) {
        this.screenObjectId = screenObjectId;
    }

    public String getScreenObjectName() {
        return screenObjectName;
    }

    public void setScreenObjectName(String screenObjectName) {
        this.screenObjectName = screenObjectName;
    }

    public String getScreenObjectDescription() {
        return screenObjectDescription;
    }

    public void setScreenObjectDescription(String screenObjectDescription) {
        this.screenObjectDescription = screenObjectDescription;
    }

    @XmlTransient
    public Collection<SpecialRolScreenObject> getSpecialRolScreenObjectCollection() {
        return specialRolScreenObjectCollection;
    }

    public void setSpecialRolScreenObjectCollection(Collection<SpecialRolScreenObject> specialRolScreenObjectCollection) {
        this.specialRolScreenObjectCollection = specialRolScreenObjectCollection;
    }

    public Screen getScreenId() {
        return screenId;
    }

    public void setScreenId(Screen screenId) {
        this.screenId = screenId;
    }

    @XmlTransient
    public Collection<RolScreenObject> getRolScreenObjectCollection() {
        return rolScreenObjectCollection;
    }

    public void setRolScreenObjectCollection(Collection<RolScreenObject> rolScreenObjectCollection) {
        this.rolScreenObjectCollection = rolScreenObjectCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (screenObjectId != null ? screenObjectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScreenObject)) {
            return false;
        }
        ScreenObject other = (ScreenObject) object;
        if ((this.screenObjectId == null && other.screenObjectId != null) || (this.screenObjectId != null && !this.screenObjectId.equals(other.screenObjectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ScreenObject[ screenObjectId=" + screenObjectId + " ]";
    }
    
}
