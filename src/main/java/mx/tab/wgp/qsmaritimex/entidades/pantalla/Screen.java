/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades.pantalla;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author William
 */
@Entity
@XmlRootElement
@Table(name = "Screen")
public class Screen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer screenId;
    @Column(length = 500)
    private String screenDescription;
    @Column(length = 100)
    private String screenName;
    private Boolean status;
    @OneToMany(mappedBy = "screenId")
    private Collection<ScreenObject> screenObjectCollection;

    public Screen() {
    }

    public Screen(Integer screenId) {
        this.screenId = screenId;
    }

    public Integer getScreenId() {
        return screenId;
    }

    public void setScreenId(Integer screenId) {
        this.screenId = screenId;
    }

    public String getScreenDescription() {
        return screenDescription;
    }

    public void setScreenDescription(String screenDescription) {
        this.screenDescription = screenDescription;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<ScreenObject> getScreenObjectCollection() {
        return screenObjectCollection;
    }

    public void setScreenObjectCollection(Collection<ScreenObject> screenObjectCollection) {
        this.screenObjectCollection = screenObjectCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (screenId != null ? screenId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Screen)) {
            return false;
        }
        Screen other = (Screen) object;
        if ((this.screenId == null && other.screenId != null) || (this.screenId != null && !this.screenId.equals(other.screenId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.Screen[ screenId=" + screenId + " ]";
    }
    
}
