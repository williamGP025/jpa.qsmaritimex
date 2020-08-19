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
    @NamedQuery(name = "LogTokenAudit.findAll", query = "SELECT l FROM LogTokenAudit l"),
    @NamedQuery(name = "LogTokenAudit.findByLogTokenAuditId", query = "SELECT l FROM LogTokenAudit l WHERE l.logTokenAuditId = :logTokenAuditId"),
    @NamedQuery(name = "LogTokenAudit.findByUserId", query = "SELECT l FROM LogTokenAudit l WHERE l.userId = :userId"),
    @NamedQuery(name = "LogTokenAudit.findByUserName", query = "SELECT l FROM LogTokenAudit l WHERE l.userName = :userName"),
    @NamedQuery(name = "LogTokenAudit.findByEventDate", query = "SELECT l FROM LogTokenAudit l WHERE l.eventDate = :eventDate"),
    @NamedQuery(name = "LogTokenAudit.findByDescription", query = "SELECT l FROM LogTokenAudit l WHERE l.description = :description"),
    @NamedQuery(name = "LogTokenAudit.findByTokenAuditFailed", query = "SELECT l FROM LogTokenAudit l WHERE l.tokenAuditFailed = :tokenAuditFailed")})
public class LogTokenAudit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long logTokenAuditId;
    @Basic(optional = false)
    @Column(nullable = false)
    private int userId;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String userName;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;
    @Basic(optional = false)
    @Column(nullable = false, length = 1000)
    private String description;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean tokenAuditFailed;

    public LogTokenAudit() {
    }

    public LogTokenAudit(Long logTokenAuditId) {
        this.logTokenAuditId = logTokenAuditId;
    }

    public LogTokenAudit(Long logTokenAuditId, int userId, String userName, Date eventDate, String description, boolean tokenAuditFailed) {
        this.logTokenAuditId = logTokenAuditId;
        this.userId = userId;
        this.userName = userName;
        this.eventDate = eventDate;
        this.description = description;
        this.tokenAuditFailed = tokenAuditFailed;
    }

    public Long getLogTokenAuditId() {
        return logTokenAuditId;
    }

    public void setLogTokenAuditId(Long logTokenAuditId) {
        this.logTokenAuditId = logTokenAuditId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getTokenAuditFailed() {
        return tokenAuditFailed;
    }

    public void setTokenAuditFailed(boolean tokenAuditFailed) {
        this.tokenAuditFailed = tokenAuditFailed;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logTokenAuditId != null ? logTokenAuditId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogTokenAudit)) {
            return false;
        }
        LogTokenAudit other = (LogTokenAudit) object;
        if ((this.logTokenAuditId == null && other.logTokenAuditId != null) || (this.logTokenAuditId != null && !this.logTokenAuditId.equals(other.logTokenAuditId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.LogTokenAudit[ logTokenAuditId=" + logTokenAuditId + " ]";
    }
    
}
