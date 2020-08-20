/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author William
 */
@Entity
@XmlRootElement
@Table(name = "Line")
@NamedQueries({
    @NamedQuery(name = "Line.findAll", query = "SELECT l FROM Line l"),
    @NamedQuery(name = "Line.findByLineId", query = "SELECT l FROM Line l WHERE l.lineId = :lineId"),
    @NamedQuery(name = "Line.findByName", query = "SELECT l FROM Line l WHERE l.name = :name"),
    @NamedQuery(name = "Line.findByLineCode", query = "SELECT l FROM Line l WHERE l.lineCode = :lineCode"),
    @NamedQuery(name = "Line.findByLineNo", query = "SELECT l FROM Line l WHERE l.lineNo = :lineNo"),
    @NamedQuery(name = "Line.findByCaat", query = "SELECT l FROM Line l WHERE l.caat = :caat"),
    @NamedQuery(name = "Line.findByNotificationEmail", query = "SELECT l FROM Line l WHERE l.notificationEmail = :notificationEmail"),
    @NamedQuery(name = "Line.findByShipOwnerId", query = "SELECT l FROM Line l WHERE l.shipOwnerId = :shipOwnerId"),
    @NamedQuery(name = "Line.findByStatus", query = "SELECT l FROM Line l WHERE l.status = :status")})
public class Line implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false, name = "LineId")
    private Short lineId;
    @Basic(optional = false)
    @Column(nullable = false, length = 50, name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(nullable = false, length = 50, name = "LineCode")
    private String lineCode;
    @Column(name = "[LineNo]")
    private Integer lineNo;
    @Column(length = 50, name = "CAAT")
    private String caat;
    @Column(length = 50, name = "NotificationEmail")
    private String notificationEmail;
    @Basic(optional = false)
    @Column(nullable = false, name = "Status")
    private boolean status;
    //--------------------------------------------------------------------------------------
    @JoinColumn(name = "CompanyId", referencedColumnName = "companyId", nullable = false)
    @ManyToOne(optional = false)
    private Company companyId;
    @JoinColumn(name = "ShipOwnerId", referencedColumnName = "shipOwnerId", nullable = true)
    @ManyToOne(optional = true)
    private ShipOwner shipOwnerId;
    //--------------------------------------------------------------------------------------
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lineId")
    private Collection<UserLine> userLineCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lineId")
    private Collection<VesselPerLine> vesselPerLineCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lineId")
    private Collection<ServiceOrder> serviceOrderCollection;

    public Line() {
    }

    public Collection<UserLine> getUserLineCollection() {
        return userLineCollection;
    }

    public void setUserLineCollection(Collection<UserLine> userLineCollection) {
        this.userLineCollection = userLineCollection;
    }

    public Collection<VesselPerLine> getVesselPerLineCollection() {
        return vesselPerLineCollection;
    }

    public void setVesselPerLineCollection(Collection<VesselPerLine> vesselPerLineCollection) {
        this.vesselPerLineCollection = vesselPerLineCollection;
    }

    public Collection<ServiceOrder> getServiceOrderCollection() {
        return serviceOrderCollection;
    }

    public void setServiceOrderCollection(Collection<ServiceOrder> serviceOrderCollection) {
        this.serviceOrderCollection = serviceOrderCollection;
    }

    public Collection<UserLine> getLineCollection() {
        return userLineCollection;
    }

    public void setLineCollection(Collection<UserLine> lineCollection) {
        this.userLineCollection = lineCollection;
    }

    public Line(Short lineId) {
        this.lineId = lineId;
    }

    public Line(Short lineId, String name, String lineCode, boolean status) {
        this.lineId = lineId;
        this.name = name;
        this.lineCode = lineCode;
        this.status = status;
    }

    public Short getLineId() {
        return lineId;
    }

    public void setLineId(Short lineId) {
        this.lineId = lineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public Integer getLineNo() {
        return lineNo;
    }

    public void setLineNo(Integer lineNo) {
        this.lineNo = lineNo;
    }

    public String getCaat() {
        return caat;
    }

    public void setCaat(String caat) {
        this.caat = caat;
    }

    public String getNotificationEmail() {
        return notificationEmail;
    }

    public void setNotificationEmail(String notificationEmail) {
        this.notificationEmail = notificationEmail;
    }

    public ShipOwner getShipOwnerId() {
        return shipOwnerId;
    }

    public void setShipOwnerId(ShipOwner shipOwnerId) {
        this.shipOwnerId = shipOwnerId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "Line{" + "lineId=" + lineId + ", name=" + name + ", lineCode=" + lineCode + ", lineNo=" + lineNo + ", caat=" + caat + ", notificationEmail=" + notificationEmail + ", status=" + status + ", companyId=" + companyId + ", shipOwnerId=" + shipOwnerId + '}';
    }

}