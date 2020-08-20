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
    @NamedQuery(name = "LogLoginAudit.findAll", query = "SELECT l FROM LogLoginAudit l"),
    @NamedQuery(name = "LogLoginAudit.findByLogLoginAuditId", query = "SELECT l FROM LogLoginAudit l WHERE l.logLoginAuditId = :logLoginAuditId"),
    @NamedQuery(name = "LogLoginAudit.findByDescription", query = "SELECT l FROM LogLoginAudit l WHERE l.description = :description"),
    @NamedQuery(name = "LogLoginAudit.findByEventDate", query = "SELECT l FROM LogLoginAudit l WHERE l.eventDate = :eventDate"),
    @NamedQuery(name = "LogLoginAudit.findByLoginAuditFailed", query = "SELECT l FROM LogLoginAudit l WHERE l.loginAuditFailed = :loginAuditFailed"),
    @NamedQuery(name = "LogLoginAudit.findByUserId", query = "SELECT l FROM LogLoginAudit l WHERE l.userId = :userId"),
    @NamedQuery(name = "LogLoginAudit.findByUserName", query = "SELECT l FROM LogLoginAudit l WHERE l.userName = :userName")})
public class LogLoginAudit implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false, precision = 19, scale = 0)
    private BigDecimal logLoginAuditId;
    @Basic(optional = false)
    @Column(nullable = false, length = 1000)
    private String description;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean loginAuditFailed;
    @Basic(optional = false)
    @Column(nullable = false)
    private int userId;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String userName;

    public LogLoginAudit() {
    }

    public LogLoginAudit(BigDecimal logLoginAuditId) {
        this.logLoginAuditId = logLoginAuditId;
    }

    public LogLoginAudit(BigDecimal logLoginAuditId, String description, Date eventDate, boolean loginAuditFailed, int userId, String userName) {
        this.logLoginAuditId = logLoginAuditId;
        this.description = description;
        this.eventDate = eventDate;
        this.loginAuditFailed = loginAuditFailed;
        this.userId = userId;
        this.userName = userName;
    }

    public BigDecimal getLogLoginAuditId() {
        return logLoginAuditId;
    }

    public void setLogLoginAuditId(BigDecimal logLoginAuditId) {
        this.logLoginAuditId = logLoginAuditId;
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

    public boolean getLoginAuditFailed() {
        return loginAuditFailed;
    }

    public void setLoginAuditFailed(boolean loginAuditFailed) {
        this.loginAuditFailed = loginAuditFailed;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logLoginAuditId != null ? logLoginAuditId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogLoginAudit)) {
            return false;
        }
        LogLoginAudit other = (LogLoginAudit) object;
        if ((this.logLoginAuditId == null && other.logLoginAuditId != null) || (this.logLoginAuditId != null && !this.logLoginAuditId.equals(other.logLoginAuditId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.LogLoginAudit[ logLoginAuditId=" + logLoginAuditId + " ]";
    }
    
}
