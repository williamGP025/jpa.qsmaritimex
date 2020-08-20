/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades;

import mx.tab.wgp.qsmaritimex.entidades.usuario.UserEnterprise;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "Enterprise")
public class Enterprise implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer enterpriseId;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String name;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enterpriseId")
    private Collection<UserEnterprise> userEnterpriseCollection;

    public Enterprise() {
    }

    public Enterprise(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Enterprise(Integer enterpriseId, String name, boolean status) {
        this.enterpriseId = enterpriseId;
        this.name = name;
        this.status = status;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
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

    @XmlTransient
    public Collection<UserEnterprise> getUserEnterpriseCollection() {
        return userEnterpriseCollection;
    }

    public void setUserEnterpriseCollection(Collection<UserEnterprise> userEnterpriseCollection) {
        this.userEnterpriseCollection = userEnterpriseCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (enterpriseId != null ? enterpriseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enterprise)) {
            return false;
        }
        Enterprise other = (Enterprise) object;
        if ((this.enterpriseId == null && other.enterpriseId != null) || (this.enterpriseId != null && !this.enterpriseId.equals(other.enterpriseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.Enterprise[ enterpriseId=" + enterpriseId + " ]";
    }
    
}
