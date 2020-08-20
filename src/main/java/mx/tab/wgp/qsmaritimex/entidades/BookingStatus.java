/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tab.wgp.qsmaritimex.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WilliamGP025
 */
@Entity
@Table(catalog = "QSMaritimex", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BookingStatus.findAll", query = "SELECT b FROM BookingStatus b"),
    @NamedQuery(name = "BookingStatus.findByBookingStatusId", query = "SELECT b FROM BookingStatus b WHERE b.bookingStatusId = :bookingStatusId"),
    @NamedQuery(name = "BookingStatus.findByStatusId", query = "SELECT b FROM BookingStatus b WHERE b.statusId = :statusId"),
    @NamedQuery(name = "BookingStatus.findByDescription", query = "SELECT b FROM BookingStatus b WHERE b.description = :description"),
    @NamedQuery(name = "BookingStatus.findByStatus", query = "SELECT b FROM BookingStatus b WHERE b.status = :status")})
public class BookingStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer bookingStatusId;
    @Basic(optional = false)
    @Column(nullable = false)
    private int statusId;
    @Column(length = 50)
    private String description;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;

    public BookingStatus() {
    }

    public BookingStatus(Integer bookingStatusId) {
        this.bookingStatusId = bookingStatusId;
    }

    public BookingStatus(Integer bookingStatusId, int statusId, boolean status) {
        this.bookingStatusId = bookingStatusId;
        this.statusId = statusId;
        this.status = status;
    }

    public Integer getBookingStatusId() {
        return bookingStatusId;
    }

    public void setBookingStatusId(Integer bookingStatusId) {
        this.bookingStatusId = bookingStatusId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookingStatusId != null ? bookingStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookingStatus)) {
            return false;
        }
        BookingStatus other = (BookingStatus) object;
        if ((this.bookingStatusId == null && other.bookingStatusId != null) || (this.bookingStatusId != null && !this.bookingStatusId.equals(other.bookingStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.BookingStatus[ bookingStatusId=" + bookingStatusId + " ]";
    }
    
}