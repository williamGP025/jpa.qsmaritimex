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
    @NamedQuery(name = "UserLine.findAll", query = "SELECT u FROM UserLine u"),
    @NamedQuery(name = "UserLine.findByUserLineId", query = "SELECT u FROM UserLine u WHERE u.userLineId = :userLineId")})
public class UserLine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer userLineId;
    @JoinColumn(name = "LineId", referencedColumnName = "LineId", nullable = false)
    @ManyToOne(optional = false)
    private Line lineId;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId", nullable = false)
    @ManyToOne(optional = false)
    private User userId;

    public UserLine() {
    }

    public UserLine(Integer userLineId) {
        this.userLineId = userLineId;
    }

    public Integer getUserLineId() {
        return userLineId;
    }

    public void setUserLineId(Integer userLineId) {
        this.userLineId = userLineId;
    }

    public Line getLineId() {
        return lineId;
    }

    public void setLineId(Line lineId) {
        this.lineId = lineId;
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
        hash += (userLineId != null ? userLineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserLine)) {
            return false;
        }
        UserLine other = (UserLine) object;
        if ((this.userLineId == null && other.userLineId != null) || (this.userLineId != null && !this.userLineId.equals(other.userLineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.UserLine[ userLineId=" + userLineId + " ]";
    }
    
}
