/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.hakaton16.domains;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "buildings")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Buildings.findAll", query = "SELECT b FROM Buildings b"),
    @NamedQuery(name = "Buildings.findById", query = "SELECT b FROM Buildings b WHERE b.id = :id"),
    @NamedQuery(name = "Buildings.findByIdGThen", query = "SELECT b FROM Buildings b WHERE b.id > :id"),
    @NamedQuery(name = "Buildings.findDomovi", query = "SELECT b FROM Buildings b WHERE b.id < 24"),
//    @NamedQuery(name = "Buildings.findCitaonice", query = "SELECT b FROM Buildings b WHERE b.id < 24"),
    @NamedQuery(name = "Buildings.findByBuildingName", query = "SELECT b FROM Buildings b WHERE b.buildingName = :buildingName"),
    @NamedQuery(name = "Buildings.findByDate", query = "SELECT b FROM Buildings b WHERE b.date = :date"),
    @NamedQuery(name = "Buildings.findByDescription", query = "SELECT b FROM Buildings b WHERE b.description = :description"),
    @NamedQuery(name = "Buildings.findByFeatures", query = "SELECT b FROM Buildings b WHERE b.features = :features"),
    @NamedQuery(name = "Buildings.findByWebpage", query = "SELECT b FROM Buildings b WHERE b.webpage = :webpage"),
    @NamedQuery(name = "Buildings.findByLongitude", query = "SELECT b FROM Buildings b WHERE b.longitude = :longitude"),
    @NamedQuery(name = "Buildings.findByLatitude", query = "SELECT b FROM Buildings b WHERE b.latitude = :latitude"),
    @NamedQuery(name = "Buildings.findByGeopolygon", query = "SELECT b FROM Buildings b WHERE b.geopolygon = :geopolygon"),
    @NamedQuery(name = "Buildings.findBySite", query = "SELECT b FROM Buildings b WHERE b.site = :site")})
public class Buildings implements Serializable {
    @OneToMany(mappedBy = "citaonicacol")
    private List<Citaonica> citaonicaList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 48)
    @Column(name = "Building_Name")
    private String buildingName;
    @Column(name = "Date")
    private Integer date;
    @Size(max = 436)
    @Column(name = "Description")
    private String description;
    @Size(max = 38)
    @Column(name = "Features")
    private String features;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 66)
    @Column(name = "Webpage")
    private String webpage;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitude")
    private BigDecimal longitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "latitude")
    private BigDecimal latitude;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 543)
    @Column(name = "Geo_polygon")
    private String geopolygon;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "Site")
    private String site;
    @JoinColumn(name = "Type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Types type;
    @JoinColumn(name = "Organisation", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Organisation organisation;

    public Buildings() {
    }

    public Buildings(Integer id) {
        this.id = id;
    }

    public Buildings(Integer id, String buildingName, String webpage, BigDecimal longitude, BigDecimal latitude, String geopolygon, String site) {
        this.id = id;
        this.buildingName = buildingName;
        this.webpage = webpage;
        this.longitude = longitude;
        this.latitude = latitude;
        this.geopolygon = geopolygon;
        this.site = site;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getGeopolygon() {
        return geopolygon;
    }

    public void setGeopolygon(String geopolygon) {
        this.geopolygon = geopolygon;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
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
        if (!(object instanceof Buildings)) {
            return false;
        }
        Buildings other = (Buildings) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.fon.hakaton16.domains.Buildings[ id=" + id + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public List<Citaonica> getCitaonicaList() {
        return citaonicaList;
    }

    public void setCitaonicaList(List<Citaonica> citaonicaList) {
        this.citaonicaList = citaonicaList;
    }
    
}
