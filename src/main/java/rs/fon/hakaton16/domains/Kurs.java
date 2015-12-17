/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.hakaton16.domains;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "kurs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kurs.findAll", query = "SELECT k FROM Kurs k"),
    @NamedQuery(name = "Kurs.findById", query = "SELECT k FROM Kurs k WHERE k.id = :id"),
    @NamedQuery(name = "Kurs.findByIme", query = "SELECT k FROM Kurs k WHERE k.ime = :ime"),
    @NamedQuery(name = "Kurs.findByDatumKursa", query = "SELECT k FROM Kurs k WHERE k.datumKursa = :datumKursa"),
    @NamedQuery(name = "Kurs.findByKapacitet", query = "SELECT k FROM Kurs k WHERE k.kapacitet = :kapacitet"),
    @NamedQuery(name = "Kurs.findByZamena", query = "SELECT k FROM Kurs k WHERE k.zamena = :zamena"),
    @NamedQuery(name = "Kurs.findByPopunjeno", query = "SELECT k FROM Kurs k WHERE k.popunjeno = :popunjeno")})
public class Kurs implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idkurs")
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
    @Column(name = "datumKursa")
    @Temporal(TemporalType.DATE)
    private Date datumKursa;
    @Column(name = "kapacitet")
    private Integer kapacitet;
    @Size(max = 150)
    @Column(name = "zamena")
    private String zamena;
    @Column(name = "popunjeno")
    private Integer popunjeno;
    @JoinColumn(name = "predavacid", referencedColumnName = "id")
    @ManyToOne
    private Predavac predavacid;

    public Kurs() {
    }

    public Kurs(Integer id) {
        this.id = id;
    }

    public Kurs(Integer id, String ime) {
        this.id = id;
        this.ime = ime;
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

    public Date getDatumKursa() {
        return datumKursa;
    }

    public void setDatumKursa(Date datumKursa) {
        this.datumKursa = datumKursa;
    }

    public Integer getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(Integer kapacitet) {
        this.kapacitet = kapacitet;
    }

    public String getZamena() {
        return zamena;
    }

    public void setZamena(String zamena) {
        this.zamena = zamena;
    }

    public Integer getPopunjeno() {
        return popunjeno;
    }

    public void setPopunjeno(Integer popunjeno) {
        this.popunjeno = popunjeno;
    }

    public Predavac getPredavacid() {
        return predavacid;
    }

    public void setPredavacid(Predavac predavacid) {
        this.predavacid = predavacid;
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
        if (!(object instanceof Kurs)) {
            return false;
        }
        Kurs other = (Kurs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.fon.hakaton16.domains.Kurs[ id=" + id + " ]";
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
