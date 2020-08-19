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
    @NamedQuery(name = "VendorProfile.findAll", query = "SELECT v FROM VendorProfile v"),
    @NamedQuery(name = "VendorProfile.findByVendorId", query = "SELECT v FROM VendorProfile v WHERE v.vendorId = :vendorId"),
    @NamedQuery(name = "VendorProfile.findByName", query = "SELECT v FROM VendorProfile v WHERE v.name = :name"),
    @NamedQuery(name = "VendorProfile.findByPassword", query = "SELECT v FROM VendorProfile v WHERE v.password = :password"),
    @NamedQuery(name = "VendorProfile.findByEmail", query = "SELECT v FROM VendorProfile v WHERE v.email = :email"),
    @NamedQuery(name = "VendorProfile.findByPhone", query = "SELECT v FROM VendorProfile v WHERE v.phone = :phone"),
    @NamedQuery(name = "VendorProfile.findByVendorAccount", query = "SELECT v FROM VendorProfile v WHERE v.vendorAccount = :vendorAccount"),
    @NamedQuery(name = "VendorProfile.findByStatus", query = "SELECT v FROM VendorProfile v WHERE v.status = :status")})
public class VendorProfile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long vendorId;
    @Basic(optional = false)
    @Column(nullable = false, length = 150)
    private String name;
    @Basic(optional = false)
    @Column(nullable = false, length = 25)
    private String password;
    @Column(length = 150)
    private String email;
    @Column(length = 20)
    private String phone;
    @Column(length = 40)
    private String vendorAccount;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;
    @OneToMany(mappedBy = "vendorId")
    private Collection<ServiceOrderService> serviceOrderServiceCollection;

    public VendorProfile() {
    }

    public VendorProfile(Long vendorId) {
        this.vendorId = vendorId;
    }

    public VendorProfile(Long vendorId, String name, String password, boolean status) {
        this.vendorId = vendorId;
        this.name = name;
        this.password = password;
        this.status = status;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVendorAccount() {
        return vendorAccount;
    }

    public void setVendorAccount(String vendorAccount) {
        this.vendorAccount = vendorAccount;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<ServiceOrderService> getServiceOrderServiceCollection() {
        return serviceOrderServiceCollection;
    }

    public void setServiceOrderServiceCollection(Collection<ServiceOrderService> serviceOrderServiceCollection) {
        this.serviceOrderServiceCollection = serviceOrderServiceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vendorId != null ? vendorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VendorProfile)) {
            return false;
        }
        VendorProfile other = (VendorProfile) object;
        if ((this.vendorId == null && other.vendorId != null) || (this.vendorId != null && !this.vendorId.equals(other.vendorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.VendorProfile[ vendorId=" + vendorId + " ]";
    }
    
}
