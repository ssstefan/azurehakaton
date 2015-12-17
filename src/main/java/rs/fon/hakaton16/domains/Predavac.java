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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "predavac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Predavac.findAll", query = "SELECT p FROM Predavac p order by p.rank desc"),
    @NamedQuery(name = "Predavac.findById", query = "SELECT p FROM Predavac p WHERE p.id = :id"),
    @NamedQuery(name = "Predavac.findByIme", query = "SELECT p FROM Predavac p WHERE p.ime = :ime"),
    @NamedQuery(name = "Predavac.findBySifra", query = "SELECT p FROM Predavac p WHERE p.sifra = :sifra"),
    @NamedQuery(name = "Predavac.findByRank", query = "SELECT p FROM Predavac p WHERE p.rank = :rank")})
public class Predavac implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpredavac")
    private List<OdslusaniKursevi> odslusaniKurseviList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ime")
    private String ime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "sifra")
    private String sifra;
    @Column(name = "rank")
    private Integer rank;
    @OneToMany(mappedBy = "predavacid")
    private List<Kurs> kursList;

    public Predavac() {
    }

    public Predavac(Integer id) {
        this.id = id;
    }

    public Predavac(Integer id, String ime, String sifra) {
        this.id = id;
        this.ime = ime;
        this.sifra = sifra;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @XmlTransient
    @JsonIgnore
    public List<Kurs> getKursList() {
        return kursList;
    }

    public void setKursList(List<Kurs> kursList) {
        this.kursList = kursList;
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
        if (!(object instanceof Predavac)) {
            return false;
        }
        Predavac other = (Predavac) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.fon.hakaton16.domains.Predavac[ id=" + id + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public List<OdslusaniKursevi> getOdslusaniKurseviList() {
        return odslusaniKurseviList;
    }

    public void setOdslusaniKurseviList(List<OdslusaniKursevi> odslusaniKurseviList) {
        this.odslusaniKurseviList = odslusaniKurseviList;
    }
    
}
