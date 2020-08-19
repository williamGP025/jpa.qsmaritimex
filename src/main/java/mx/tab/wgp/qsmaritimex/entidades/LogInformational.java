/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WilliamGP025
 */
@Entity
@Table(catalog = "QSMaritimex", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogInformational.findAll", query = "SELECT l FROM LogInformational l"),
    @NamedQuery(name = "LogInformational.findByLogInformationalId", query = "SELECT l FROM LogInformational l WHERE l.logInformationalId = :logInformationalId"),
    @NamedQuery(name = "LogInformational.findByFinding", query = "SELECT l FROM LogInformational l WHERE l.finding = :finding"),
    @NamedQuery(name = "LogInformational.findByProcess", query = "SELECT l FROM LogInformational l WHERE l.process = :process"),
    @NamedQuery(name = "LogInformational.findByClass1", query = "SELECT l FROM LogInformational l WHERE l.class1 = :class1"),
    @NamedQuery(name = "LogInformational.findByMethod", query = "SELECT l FROM LogInformational l WHERE l.method = :method"),
    @NamedQuery(name = "LogInformational.findByEventDate", query = "SELECT l FROM LogInformational l WHERE l.eventDate = :eventDate"),
    @NamedQuery(name = "LogInformational.findByUserId", query = "SELECT l FROM LogInformational l WHERE l.userId = :userId"),
    @NamedQuery(name = "LogInformational.findByDescription", query = "SELECT l FROM LogInformational l WHERE l.description = :description"),
    @NamedQuery(name = "LogInformational.findByLegacySystem", query = "SELECT l FROM LogInformational l WHERE l.legacySystem = :legacySystem")})
public class LogInformational implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long logInformationalId;
    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    private String finding;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String process;
    @Basic(optional = false)
    @Column(name = "Class", nullable = false, length = 50)
    private String class1;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String method;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;
    @Basic(optional = false)
    @Column(nullable = false)
    private int userId;
    @Basic(optional = false)
    @Column(nullable = false, length = 1000)
    private String description;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean legacySystem;

    public LogInformational() {
    }

    public LogInformational(Long logInformationalId) {
        this.logInformationalId = logInformationalId;
    }

    public LogInformational(Long logInformationalId, String finding, String process, String class1, String method, Date eventDate, int userId, String description, boolean legacySystem) {
        this.logInformationalId = logInformationalId;
        this.finding = finding;
        this.process = process;
        this.class1 = class1;
        this.method = method;
        this.eventDate = eventDate;
        this.userId = userId;
        this.description = description;
        this.legacySystem = legacySystem;
    }

    public Long getLogInformationalId() {
        return logInformationalId;
    }

    public void setLogInformationalId(Long logInformationalId) {
        this.logInformationalId = logInformationalId;
    }

    public String getFinding() {
        return finding;
    }

    public void setFinding(String finding) {
        this.finding = finding;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getClass1() {
        return class1;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getLegacySystem() {
        return legacySystem;
    }

    public void setLegacySystem(boolean legacySystem) {
        this.legacySystem = legacySystem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logInformationalId != null ? logInformationalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogInformational)) {
            return false;
        }
        LogInformational other = (LogInformational) object;
        if ((this.logInformationalId == null && other.logInformationalId != null) || (this.logInformationalId != null && !this.logInformationalId.equals(other.logInformationalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.LogInformational[ logInformationalId=" + logInformationalId + " ]";
    }
    
}
