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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author WilliamGP025
 */
@Entity
@Table(catalog = "QSMaritimex", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActingRolesType.findAll", query = "SELECT a FROM ActingRolesType a"),
    @NamedQuery(name = "ActingRolesType.findByActingRolesTypeId", query = "SELECT a FROM ActingRolesType a WHERE a.actingRolesTypeId = :actingRolesTypeId"),
    @NamedQuery(name = "ActingRolesType.findByDescription", query = "SELECT a FROM ActingRolesType a WHERE a.description = :description"),
    @NamedQuery(name = "ActingRolesType.findByDescriptionEng", query = "SELECT a FROM ActingRolesType a WHERE a.descriptionEng = :descriptionEng"),
    @NamedQuery(name = "ActingRolesType.findByStatus", query = "SELECT a FROM ActingRolesType a WHERE a.status = :status")})
public class ActingRolesType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer actingRolesTypeId;
    @Column(length = 50)
    private String description;
    @Column(length = 100)
    private String descriptionEng;
    private Boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actingRolesTypeId")
    private Collection<ActingRoles> actingRolesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actingRolesTypeId")
    private Collection<ServiceOrderServicesTemplate> serviceOrderServicesTemplateCollection;

    public ActingRolesType() {
    }

    public ActingRolesType(Integer actingRolesTypeId) {
        this.actingRolesTypeId = actingRolesTypeId;
    }

    public Integer getActingRolesTypeId() {
        return actingRolesTypeId;
    }

    public void setActingRolesTypeId(Integer actingRolesTypeId) {
        this.actingRolesTypeId = actingRolesTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionEng() {
        return descriptionEng;
    }

    public void setDescriptionEng(String descriptionEng) {
        this.descriptionEng = descriptionEng;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<ActingRoles> getActingRolesCollection() {
        return actingRolesCollection;
    }

    public void setActingRolesCollection(Collection<ActingRoles> actingRolesCollection) {
        this.actingRolesCollection = actingRolesCollection;
    }

    @XmlTransient
    public Collection<ServiceOrderServicesTemplate> getServiceOrderServicesTemplateCollection() {
        return serviceOrderServicesTemplateCollection;
    }

    public void setServiceOrderServicesTemplateCollection(Collection<ServiceOrderServicesTemplate> serviceOrderServicesTemplateCollection) {
        this.serviceOrderServicesTemplateCollection = serviceOrderServicesTemplateCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actingRolesTypeId != null ? actingRolesTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActingRolesType)) {
            return false;
        }
        ActingRolesType other = (ActingRolesType) object;
        if ((this.actingRolesTypeId == null && other.actingRolesTypeId != null) || (this.actingRolesTypeId != null && !this.actingRolesTypeId.equals(other.actingRolesTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.tab.wgp.qsmaritimex.entidades.ActingRolesType[ actingRolesTypeId=" + actingRolesTypeId + " ]";
    }
    
}
