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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stefan
 */
@Entity
@Table(name = "odslusani_kursevi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OdslusaniKursevi.findAll", query = "SELECT o FROM OdslusaniKursevi o"),
//    @NamedQuery(name = "OdslusaniKursevi.findByPredavac", query = "SELECT o FROM OdslusaniKursevi o WHERE o.idpredavac = :idpredavac"),
    @NamedQuery(name = "OdslusaniKursevi.findById", query = "SELECT o FROM OdslusaniKursevi o WHERE o.id = :id")})
public class OdslusaniKursevi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "idpredavac", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Predavac idpredavac;
    @JoinColumn(name = "idkurs", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Kurs idkurs;

    public OdslusaniKursevi() {
    }

    public OdslusaniKursevi(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Predavac getIdpredavac() {
        return idpredavac;
    }

    public void setIdpredavac(Predavac idpredavac) {
        this.idpredavac = idpredavac;
    }

    public Kurs getIdkurs() {
        return idkurs;
    }

    public void setIdkurs(Kurs idkurs) {
        this.idkurs = idkurs;
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
        if (!(object instanceof OdslusaniKursevi)) {
            return false;
        }
        OdslusaniKursevi other = (OdslusaniKursevi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.fon.hakaton16.domains.OdslusaniKursevi[ id=" + id + " ]";
    }
    
}
