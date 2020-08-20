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
import mx.tab.wgp.qsmaritimex.entidades.nominacion.CargoType;

/**
 *
 * @author William
 */
@Entity
@XmlRootElement
@Table(name = "UserCargoType")
public class UserCargoType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer userCargoTypeId;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    //--------------------------------------------------------------------------
    @JoinColumn(name = "UserId", referencedColumnName = "UserId", nullable = false)
    @ManyToOne(optional = false)
    private User userId;
    
    @JoinColumn(name = "CargoTypeId", referencedColumnName = "cargoTypeId", nullable = false)
    @ManyToOne(optional = false)
    private CargoType cargoTypeId;

    public UserCargoType() {
    }

    public UserCargoType(Integer userCargoTypeId) {
        this.userCargoTypeId = userCargoTypeId;
    }

    public UserCargoType(Integer userCargoTypeId, boolean status, User userId) {
        this.userCargoTypeId = userCargoTypeId;
        this.status = status;
        this.userId = userId;
    }

    public Integer getUserCargoTypeId() {
        return userCargoTypeId;
    }

    public void setUserCargoTypeId(Integer userCargoTypeId) {
        this.userCargoTypeId = userCargoTypeId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public CargoType getCargoTypeId() {
        return cargoTypeId;
    }

    public void setCargoTypeId(CargoType cargoTypeId) {
        this.cargoTypeId = cargoTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userCargoTypeId != null ? userCargoTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserCargoType)) {
            return false;
        }
        UserCargoType other = (UserCargoType) object;
        if ((this.userCargoTypeId == null && other.userCargoTypeId != null) || (this.userCargoTypeId != null && !this.userCargoTypeId.equals(other.userCargoTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.UserCargoType[ userCargoTypeId=" + userCargoTypeId + " ]";
    }
    
}
