/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author William
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogError.findAll", query = "SELECT l FROM LogError l"),
    @NamedQuery(name = "LogError.findByLogErrorId", query = "SELECT l FROM LogError l WHERE l.logErrorId = :logErrorId"),
    @NamedQuery(name = "LogError.findByDescription", query = "SELECT l FROM LogError l WHERE l.description = :description"),
    @NamedQuery(name = "LogError.findByEventDate", query = "SELECT l FROM LogError l WHERE l.eventDate = :eventDate"),
    @NamedQuery(name = "LogError.findByExInnerException", query = "SELECT l FROM LogError l WHERE l.exInnerException = :exInnerException"),
    @NamedQuery(name = "LogError.findByExInnerExceptionTrace", query = "SELECT l FROM LogError l WHERE l.exInnerExceptionTrace = :exInnerExceptionTrace"),
    @NamedQuery(name = "LogError.findByExMessageException", query = "SELECT l FROM LogError l WHERE l.exMessageException = :exMessageException"),
    @NamedQuery(name = "LogError.findByExMethodException", query = "SELECT l FROM LogError l WHERE l.exMethodException = :exMethodException"),
    @NamedQuery(name = "LogError.findByExSource", query = "SELECT l FROM LogError l WHERE l.exSource = :exSource"),
    @NamedQuery(name = "LogError.findByLegacySystem", query = "SELECT l FROM LogError l WHERE l.legacySystem = :legacySystem"),
    @NamedQuery(name = "LogError.findByUserId", query = "SELECT l FROM LogError l WHERE l.userId = :userId")})
public class LogError implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false, precision = 19, scale = 0)
    private BigDecimal logErrorId;
    @Column(length = 5000)
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;
    @Column(length = 2000)
    private String exInnerException;
    @Column(length = 5000)
    private String exInnerExceptionTrace;
    @Column(length = 2000)
    private String exMessageException;
    @Column(length = 1000)
    private String exMethodException;
    @Column(length = 500)
    private String exSource;
    private Boolean legacySystem;
    private Integer userId;

    public LogError() {
    }

    public LogError(BigDecimal logErrorId) {
        this.logErrorId = logErrorId;
    }

    public BigDecimal getLogErrorId() {
        return logErrorId;
    }

    public void setLogErrorId(BigDecimal logErrorId) {
        this.logErrorId = logErrorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getExInnerException() {
        return exInnerException;
    }

    public void setExInnerException(String exInnerException) {
        this.exInnerException = exInnerException;
    }

    public String getExInnerExceptionTrace() {
        return exInnerExceptionTrace;
    }

    public void setExInnerExceptionTrace(String exInnerExceptionTrace) {
        this.exInnerExceptionTrace = exInnerExceptionTrace;
    }

    public String getExMessageException() {
        return exMessageException;
    }

    public void setExMessageException(String exMessageException) {
        this.exMessageException = exMessageException;
    }

    public String getExMethodException() {
        return exMethodException;
    }

    public void setExMethodException(String exMethodException) {
        this.exMethodException = exMethodException;
    }

    public String getExSource() {
        return exSource;
    }

    public void setExSource(String exSource) {
        this.exSource = exSource;
    }

    public Boolean getLegacySystem() {
        return legacySystem;
    }

    public void setLegacySystem(Boolean legacySystem) {
        this.legacySystem = legacySystem;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logErrorId != null ? logErrorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogError)) {
            return false;
        }
        LogError other = (LogError) object;
        if ((this.logErrorId == null && other.logErrorId != null) || (this.logErrorId != null && !this.logErrorId.equals(other.logErrorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.LogError[ logErrorId=" + logErrorId + " ]";
    }
    
}
