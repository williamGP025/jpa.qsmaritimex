/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades.usuario;

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
import mx.tab.wgp.qsmaritimex.entidades.Enterprise;

/**
 *
 * @author William
 */
@Entity
@XmlRootElement
@Table(name = "UserEnterprise")
public class UserEnterprise implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer userEnterpriseId;
    //--------------------------------------------------------------------------
    @JoinColumn(name = "UserId", referencedColumnName = "UserId", nullable = false)
    @ManyToOne(optional = false)
    private User userId;
    
    @JoinColumn(name = "EnterpriseId", referencedColumnName = "enterpriseId", nullable = false)
    @ManyToOne(optional = false)
    private Enterprise enterpriseId;

    public UserEnterprise() {
    }

    public UserEnterprise(Integer userEnterpriseId) {
        this.userEnterpriseId = userEnterpriseId;
    }

    public UserEnterprise(Integer userEnterpriseId, User userId) {
        this.userEnterpriseId = userEnterpriseId;
        this.userId = userId;
    }

    public Integer getUserEnterpriseId() {
        return userEnterpriseId;
    }

    public void setUserEnterpriseId(Integer userEnterpriseId) {
        this.userEnterpriseId = userEnterpriseId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Enterprise getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Enterprise enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userEnterpriseId != null ? userEnterpriseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserEnterprise)) {
            return false;
        }
        UserEnterprise other = (UserEnterprise) object;
        if ((this.userEnterpriseId == null && other.userEnterpriseId != null) || (this.userEnterpriseId != null && !this.userEnterpriseId.equals(other.userEnterpriseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.UserEnterprise[ userEnterpriseId=" + userEnterpriseId + " ]";
    }
    
}
