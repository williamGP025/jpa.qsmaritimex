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
    @NamedQuery(name = "NotesServicesOrderService.findAll", query = "SELECT n FROM NotesServicesOrderService n"),
    @NamedQuery(name = "NotesServicesOrderService.findByIdNotesServiceOrderService", query = "SELECT n FROM NotesServicesOrderService n WHERE n.idNotesServiceOrderService = :idNotesServiceOrderService"),
    @NamedQuery(name = "NotesServicesOrderService.findByServiceOrderServiceId", query = "SELECT n FROM NotesServicesOrderService n WHERE n.serviceOrderServiceId = :serviceOrderServiceId"),
    @NamedQuery(name = "NotesServicesOrderService.findByDescription", query = "SELECT n FROM NotesServicesOrderService n WHERE n.description = :description"),
    @NamedQuery(name = "NotesServicesOrderService.findByNotes", query = "SELECT n FROM NotesServicesOrderService n WHERE n.notes = :notes"),
    @NamedQuery(name = "NotesServicesOrderService.findByStatus", query = "SELECT n FROM NotesServicesOrderService n WHERE n.status = :status")})
public class NotesServicesOrderService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(nullable = false)
    private int idNotesServiceOrderService;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Long serviceOrderServiceId;
    @Column(length = 500)
    private String description;
    @Column(length = 500)
    private String notes;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean status;

    public NotesServicesOrderService() {
    }

    public NotesServicesOrderService(Long serviceOrderServiceId) {
        this.serviceOrderServiceId = serviceOrderServiceId;
    }

    public NotesServicesOrderService(Long serviceOrderServiceId, int idNotesServiceOrderService, boolean status) {
        this.serviceOrderServiceId = serviceOrderServiceId;
        this.idNotesServiceOrderService = idNotesServiceOrderService;
        this.status = status;
    }

    public int getIdNotesServiceOrderService() {
        return idNotesServiceOrderService;
    }

    public void setIdNotesServiceOrderService(int idNotesServiceOrderService) {
        this.idNotesServiceOrderService = idNotesServiceOrderService;
    }

    public Long getServiceOrderServiceId() {
        return serviceOrderServiceId;
    }

    public void setServiceOrderServiceId(Long serviceOrderServiceId) {
        this.serviceOrderServiceId = serviceOrderServiceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
        hash += (serviceOrderServiceId != null ? serviceOrderServiceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotesServicesOrderService)) {
            return false;
        }
        NotesServicesOrderService other = (NotesServicesOrderService) object;
        if ((this.serviceOrderServiceId == null && other.serviceOrderServiceId != null) || (this.serviceOrderServiceId != null && !this.serviceOrderServiceId.equals(other.serviceOrderServiceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.NotesServicesOrderService[ serviceOrderServiceId=" + serviceOrderServiceId + " ]";
    }
    
}
