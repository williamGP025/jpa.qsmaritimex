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
    @NamedQuery(name = "LogWarning.findAll", query = "SELECT l FROM LogWarning l"),
    @NamedQuery(name = "LogWarning.findByLogWarningId", query = "SELECT l FROM LogWarning l WHERE l.logWarningId = :logWarningId"),
    @NamedQuery(name = "LogWarning.findByFinding", query = "SELECT l FROM LogWarning l WHERE l.finding = :finding"),
    @NamedQuery(name = "LogWarning.findByProcess", query = "SELECT l FROM LogWarning l WHERE l.process = :process"),
    @NamedQuery(name = "LogWarning.findByClass1", query = "SELECT l FROM LogWarning l WHERE l.class1 = :class1"),
    @NamedQuery(name = "LogWarning.findByMethod", query = "SELECT l FROM LogWarning l WHERE l.method = :method"),
    @NamedQuery(name = "LogWarning.findByEventDate", query = "SELECT l FROM LogWarning l WHERE l.eventDate = :eventDate"),
    @NamedQuery(name = "LogWarning.findByUserId", query = "SELECT l FROM LogWarning l WHERE l.userId = :userId"),
    @NamedQuery(name = "LogWarning.findByDescription", query = "SELECT l FROM LogWarning l WHERE l.description = :description"),
    @NamedQuery(name = "LogWarning.findByLegacySystem", query = "SELECT l FROM LogWarning l WHERE l.legacySystem = :legacySystem")})
public class LogWarning implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long logWarningId;
    @Column(length = 250)
    private String finding;
    @Column(length = 100)
    private String process;
    @Column(name = "Class", length = 50)
    private String class1;
    @Column(length = 50)
    private String method;
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;
    private Integer userId;
    @Column(length = 1000)
    private String description;
    private Boolean legacySystem;

    public LogWarning() {
    }

    public LogWarning(Long logWarningId) {
        this.logWarningId = logWarningId;
    }

    public Long getLogWarningId() {
        return logWarningId;
    }

    public void setLogWarningId(Long logWarningId) {
        this.logWarningId = logWarningId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getLegacySystem() {
        return legacySystem;
    }

    public void setLegacySystem(Boolean legacySystem) {
        this.legacySystem = legacySystem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logWarningId != null ? logWarningId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogWarning)) {
            return false;
        }
        LogWarning other = (LogWarning) object;
        if ((this.logWarningId == null && other.logWarningId != null) || (this.logWarningId != null && !this.logWarningId.equals(other.logWarningId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.LogWarning[ logWarningId=" + logWarningId + " ]";
    }
    
}
