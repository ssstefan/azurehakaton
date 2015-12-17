/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.hakaton16.domains;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stefan
 */
@Entity
@Table(name = "features")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Features.findAll", query = "SELECT f FROM Features f"),
    @NamedQuery(name = "Features.findById", query = "SELECT f FROM Features f WHERE f.id = :id"),
    @NamedQuery(name = "Features.findByIdLThen", query = "SELECT f FROM Features f WHERE f.id < :id"),
    @NamedQuery(name = "Features.findByEn", query = "SELECT f FROM Features f WHERE f.en = :en"),
    @NamedQuery(name = "Features.findBySrrs", query = "SELECT f FROM Features f WHERE f.srrs = :srrs")})
public class Features implements Serializable {
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

    public Features() {
    }

    public Features(Integer id) {
        this.id = id;
    }

    public Features(Integer id, String en, String srrs) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Features)) {
            return false;
        }
        Features other = (Features) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.fon.hakaton16.domains.Features[ id=" + id + " ]";
    }
    
}
