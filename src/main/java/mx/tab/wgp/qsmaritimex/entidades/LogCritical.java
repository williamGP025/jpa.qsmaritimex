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
    @NamedQuery(name = "LogCritical.findAll", query = "SELECT l FROM LogCritical l"),
    @NamedQuery(name = "LogCritical.findByLogCriticalId", query = "SELECT l FROM LogCritical l WHERE l.logCriticalId = :logCriticalId"),
    @NamedQuery(name = "LogCritical.findByFinding", query = "SELECT l FROM LogCritical l WHERE l.finding = :finding"),
    @NamedQuery(name = "LogCritical.findByProcess", query = "SELECT l FROM LogCritical l WHERE l.process = :process"),
    @NamedQuery(name = "LogCritical.findByClass1", query = "SELECT l FROM LogCritical l WHERE l.class1 = :class1"),
    @NamedQuery(name = "LogCritical.findByMethod", query = "SELECT l FROM LogCritical l WHERE l.method = :method"),
    @NamedQuery(name = "LogCritical.findByEventDate", query = "SELECT l FROM LogCritical l WHERE l.eventDate = :eventDate"),
    @NamedQuery(name = "LogCritical.findByUserId", query = "SELECT l FROM LogCritical l WHERE l.userId = :userId"),
    @NamedQuery(name = "LogCritical.findByDescription", query = "SELECT l FROM LogCritical l WHERE l.description = :description"),
    @NamedQuery(name = "LogCritical.findByLegacySystem", query = "SELECT l FROM LogCritical l WHERE l.legacySystem = :legacySystem")})
public class LogCritical implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long logCriticalId;
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

    public LogCritical() {
    }

    public LogCritical(Long logCriticalId) {
        this.logCriticalId = logCriticalId;
    }

    public LogCritical(Long logCriticalId, String finding, String process, String class1, String method, Date eventDate, int userId, String description, boolean legacySystem) {
        this.logCriticalId = logCriticalId;
        this.finding = finding;
        this.process = process;
        this.class1 = class1;
        this.method = method;
        this.eventDate = eventDate;
        this.userId = userId;
        this.description = description;
        this.legacySystem = legacySystem;
    }

    public Long getLogCriticalId() {
        return logCriticalId;
    }

    public void setLogCriticalId(Long logCriticalId) {
        this.logCriticalId = logCriticalId;
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
        hash += (logCriticalId != null ? logCriticalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogCritical)) {
            return false;
        }
        LogCritical other = (LogCritical) object;
        if ((this.logCriticalId == null && other.logCriticalId != null) || (this.logCriticalId != null && !this.logCriticalId.equals(other.logCriticalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.LogCritical[ logCriticalId=" + logCriticalId + " ]";
    }
    
}
