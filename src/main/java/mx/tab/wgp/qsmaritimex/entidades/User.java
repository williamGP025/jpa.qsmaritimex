/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author William
 */
@Entity
@Table(name = "[User]")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "UserId", nullable = false)
    private Integer userId;
    @Column(name = "PhoneNumber")
    private String phoneNumber;
    @Basic(optional = false)
    @Column(name = "UserName", nullable = false)
    private String userName;
    @Basic(optional = false)
    @Column(name = "Password", nullable = false)
    private String password;
    @Column(name = "Email")
    private String email;
    @Column(name = "Name")
    private String name;
    @Column(name = "LastName")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "InAD", nullable = false)
    private Boolean inAD;
    @Basic(optional = false)
    @Column(name = "Status", nullable = false)
    private Boolean status;
    @Basic(optional = false)
    @Column(name = "CreateDate", nullable = false)    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    //-----------------------------------
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "UserId")
    private Collection<UserLine> userLineCollection;

    public User() {
    }

    public Boolean getInAD() {
        return inAD;
    }

    public void setInAD(Boolean inAD) {
        this.inAD = inAD;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Collection<UserLine> getUserLineCollection() {
        return userLineCollection;
    }

    public void setUserLineCollection(Collection<UserLine> userLineCollection) {
        this.userLineCollection = userLineCollection;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", phoneNumber=" + phoneNumber + ", userName=" + userName + ", password=" + password + ", email=" + email + ", name=" + name + ", lastName=" + lastName + ", inAD=" + inAD + ", status=" + status + ", createDate=" + createDate + '}';
    }
}