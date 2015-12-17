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
@Table(name = "sites")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sites.findAll", query = "SELECT s FROM Sites s"),
    @NamedQuery(name = "Sites.findByIdVarchar", query = "SELECT s FROM Sites s WHERE s.idVarchar = :idVarchar"),
    @NamedQuery(name = "Sites.findByEn", query = "SELECT s FROM Sites s WHERE s.en = :en"),
    @NamedQuery(name = "Sites.findBySrrs", query = "SELECT s FROM Sites s WHERE s.srrs = :srrs")})
public class Sites implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "id_varchar")
    private String idVarchar;
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

    public Sites() {
    }

    public Sites(String idVarchar) {
        this.idVarchar = idVarchar;
    }

    public Sites(String idVarchar, String en, String srrs) {
        this.idVarchar = idVarchar;
        this.en = en;
        this.srrs = srrs;
    }

    public String getIdVarchar() {
        return idVarchar;
    }

    public void setIdVarchar(String idVarchar) {
        this.idVarchar = idVarchar;
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
        hash += (idVarchar != null ? idVarchar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sites)) {
            return false;
        }
        Sites other = (Sites) object;
        if ((this.idVarchar == null && other.idVarchar != null) || (this.idVarchar != null && !this.idVarchar.equals(other.idVarchar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.fon.hakaton16.domains.Sites[ idVarchar=" + idVarchar + " ]";
    }
    
}
