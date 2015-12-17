/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.hakaton16.domains;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author stefan
 */
@Entity
@Table(name = "organisation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Organisation.findAll", query = "SELECT o FROM Organisation o"),
    @NamedQuery(name = "Organisation.findById", query = "SELECT o FROM Organisation o WHERE o.id = :id"),
    @NamedQuery(name = "Organisation.findByEn", query = "SELECT o FROM Organisation o WHERE o.en = :en"),
    @NamedQuery(name = "Organisation.findBySrrs", query = "SELECT o FROM Organisation o WHERE o.srrs = :srrs")})
public class Organisation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 21)
    @Column(name = "en")
    private String en;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 24)
    @Column(name = "srrs")
    private String srrs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organisation")
    private List<Buildings> buildingsList;

    public Organisation() {
    }

    public Organisation(Integer id) {
        this.id = id;
    }

    public Organisation(Integer id, String en, String srrs) {
        this.id = id;
        this.en = en;
        this.srrs = srrs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getSrrs() {
        return srrs;
    }

    public void setSrrs(String srrs) {
        this.srrs = srrs;
    }

    @XmlTransient
    @JsonIgnore
    public List<Buildings> getBuildingsList() {
        return buildingsList;
    }

    public void setBuildingsList(List<Buildings> buildingsList) {
        this.buildingsList = buildingsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Organisation)) {
            return false;
        }
        Organisation other = (Organisation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.fon.hakaton16.domains.Organisation[ id=" + id + " ]";
    }
    
}
