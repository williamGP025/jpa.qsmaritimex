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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "UserCargoType.findAll", query = "SELECT u FROM UserCargoType u"),
    @NamedQuery(name = "UserCargoType.findByUserCargoTypeId", query = "SELECT u FROM UserCargoType u WHERE u.userCargoTypeId = :userCargoTypeId"),
    @NamedQuery(name = "UserCargoType.findByStatus", query = "SELECT u FROM UserCargoType u WHERE u.status = :status")})
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
    @JoinColumn(name = "CargoTypeId", referencedColumnName = "CargoTypeId", nullable = false)
    @ManyToOne(optional = false)
    private CargoType cargoTypeId;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId", nullable = false)
    @ManyToOne(optional = false)
    private User userId;

    public UserCargoType() {
    }

    public UserCargoType(Integer userCargoTypeId) {
        this.userCargoTypeId = userCargoTypeId;
    }

    public UserCargoType(Integer userCargoTypeId, boolean status) {
        this.userCargoTypeId = userCargoTypeId;
        this.status = status;
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

    public CargoType getCargoTypeId() {
        return cargoTypeId;
    }

    public void setCargoTypeId(CargoType cargoTypeId) {
        this.cargoTypeId = cargoTypeId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
