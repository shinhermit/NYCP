/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
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

/**
 *
 * @author josuah
 */
@Entity
@Table(name = "MOTIVE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Motive.findAll", query = "SELECT m FROM Motive m"),
    @NamedQuery(name = "Motive.findByMotiveNumber", query = "SELECT m FROM Motive m WHERE m.motiveNumber = :motiveNumber"),
    @NamedQuery(name = "Motive.findByMotiveLabel", query = "SELECT m FROM Motive m WHERE m.motiveLabel = :motiveLabel")})
public class Motive implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "MOTIVE_NUMBER")
    private String motiveNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MOTIVE_LABEL")
    private String motiveLabel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "motiveNumber")
    private Collection<Incarceration> incarcerationCollection;

    public Motive() {
    }

    public Motive(String motiveNumber) {
        this.motiveNumber = motiveNumber;
    }

    public Motive(String motiveNumber, String motiveLabel) {
        this.motiveNumber = motiveNumber;
        this.motiveLabel = motiveLabel;
    }

    public String getMotiveNumber() {
        return motiveNumber;
    }

    public void setMotiveNumber(String motiveNumber) {
        this.motiveNumber = motiveNumber;
    }

    public String getMotiveLabel() {
        return motiveLabel;
    }

    public void setMotiveLabel(String motiveLabel) {
        this.motiveLabel = motiveLabel;
    }

    @XmlTransient
    public Collection<Incarceration> getIncarcerationCollection() {
        return incarcerationCollection;
    }

    public void setIncarcerationCollection(Collection<Incarceration> incarcerationCollection) {
        this.incarcerationCollection = incarcerationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (motiveNumber != null ? motiveNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Motive)) {
            return false;
        }
        Motive other = (Motive) object;
        if ((this.motiveNumber == null && other.motiveNumber != null) || (this.motiveNumber != null && !this.motiveNumber.equals(other.motiveNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Motive[ motiveNumber=" + motiveNumber + " ]";
    }
    
}
