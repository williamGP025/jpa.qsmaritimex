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
    @NamedQuery(name = "LogLoginAudit.findAll", query = "SELECT l FROM LogLoginAudit l"),
    @NamedQuery(name = "LogLoginAudit.findByLogLoginAuditId", query = "SELECT l FROM LogLoginAudit l WHERE l.logLoginAuditId = :logLoginAuditId"),
    @NamedQuery(name = "LogLoginAudit.findByUserId", query = "SELECT l FROM LogLoginAudit l WHERE l.userId = :userId"),
    @NamedQuery(name = "LogLoginAudit.findByUserName", query = "SELECT l FROM LogLoginAudit l WHERE l.userName = :userName"),
    @NamedQuery(name = "LogLoginAudit.findByEventDate", query = "SELECT l FROM LogLoginAudit l WHERE l.eventDate = :eventDate"),
    @NamedQuery(name = "LogLoginAudit.findByDescription", query = "SELECT l FROM LogLoginAudit l WHERE l.description = :description"),
    @NamedQuery(name = "LogLoginAudit.findByLoginAuditFailed", query = "SELECT l FROM LogLoginAudit l WHERE l.loginAuditFailed = :loginAuditFailed")})
public class LogLoginAudit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long logLoginAuditId;
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
    private boolean loginAuditFailed;

    public LogLoginAudit() {
    }

    public LogLoginAudit(Long logLoginAuditId) {
        this.logLoginAuditId = logLoginAuditId;
    }

    public LogLoginAudit(Long logLoginAuditId, int userId, String userName, Date eventDate, String description, boolean loginAuditFailed) {
        this.logLoginAuditId = logLoginAuditId;
        this.userId = userId;
        this.userName = userName;
        this.eventDate = eventDate;
        this.description = description;
        this.loginAuditFailed = loginAuditFailed;
    }

    public Long getLogLoginAuditId() {
        return logLoginAuditId;
    }

    public void setLogLoginAuditId(Long logLoginAuditId) {
        this.logLoginAuditId = logLoginAuditId;
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

    public boolean getLoginAuditFailed() {
        return loginAuditFailed;
    }

    public void setLoginAuditFailed(boolean loginAuditFailed) {
        this.loginAuditFailed = loginAuditFailed;
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
