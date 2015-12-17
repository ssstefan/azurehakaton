/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.hakaton16.domains;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author stefan
 */
@Entity
@Table(name = "citaonica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Citaonica.findAll", query = "SELECT c FROM Citaonica c"),
    @NamedQuery(name = "Citaonica.findById", query = "SELECT c FROM Citaonica c WHERE c.id = :id"),
    @NamedQuery(name = "Citaonica.findByKapacitet", query = "SELECT c FROM Citaonica c WHERE c.kapacitet = :kapacitet"),
    @NamedQuery(name = "Citaonica.findByPopunjenost", query = "SELECT c FROM Citaonica c WHERE c.popunjenost = :popunjenost")})
public class Citaonica implements Serializable {
    @OneToMany(mappedBy = "citaonicaid")
    private List<Student> studentList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "kapacitet")
    private Integer kapacitet;
    @Column(name = "popunjenost")
    private Integer popunjenost;
    @JoinColumn(name = "citaonicacol", referencedColumnName = "id")
    @ManyToOne
    private Buildings citaonicacol;

    public Citaonica() {
    }

    public Citaonica(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(Integer kapacitet) {
        this.kapacitet = kapacitet;
    }

    public Integer getPopunjenost() {
        return popunjenost;
    }

    public void setPopunjenost(Integer popunjenost) {
        this.popunjenost = popunjenost;
    }

    public Buildings getCitaonicacol() {
        return citaonicacol;
    }

    public void setCitaonicacol(Buildings citaonicacol) {
        this.citaonicacol = citaonicacol;
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
        if (!(object instanceof Citaonica)) {
            return false;
        }
        Citaonica other = (Citaonica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.fon.hakaton16.domains.Citaonica[ id=" + id + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    
}
