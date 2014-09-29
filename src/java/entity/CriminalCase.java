/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import entity.primaryKeys.CriminalCasePK;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author josuah
 */
@Entity
@Table(name = "CRIMINAL_CASE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CriminalCase.findAll", query = "SELECT c FROM CriminalCase c"),
    @NamedQuery(name = "CriminalCase.findByCriminalCaseNumber", query = "SELECT c FROM CriminalCase c WHERE c.criminalCasePK.criminalCaseNumber = :criminalCaseNumber"),
    @NamedQuery(name = "CriminalCase.findByJurisdictionName", query = "SELECT c FROM CriminalCase c WHERE c.criminalCasePK.jurisdictionName = :jurisdictionName"),
    @NamedQuery(name = "CriminalCase.findByDateOfCriminalCase", query = "SELECT c FROM CriminalCase c WHERE c.dateOfCriminalCase = :dateOfCriminalCase")})
public class CriminalCase implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CriminalCasePK criminalCasePK;
    @Column(name = "DATE_OF_CRIMINAL_CASE")
    @Temporal(TemporalType.DATE)
    private Date dateOfCriminalCase;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "criminalCase")
    private Collection<PrisonerCriminalCase> prisonerCriminalCaseCollection;

    public CriminalCase() {
    }

    public CriminalCase(CriminalCasePK criminalCasePK) {
        this.criminalCasePK = criminalCasePK;
    }

    public CriminalCase(String criminalCaseNumber, String jurisdictionName) {
        this.criminalCasePK = new CriminalCasePK(criminalCaseNumber, jurisdictionName);
    }

    public CriminalCasePK getCriminalCasePK() {
        return criminalCasePK;
    }

    public void setCriminalCasePK(CriminalCasePK criminalCasePK) {
        this.criminalCasePK = criminalCasePK;
    }

    public Date getDateOfCriminalCase() {
        return dateOfCriminalCase;
    }

    public void setDateOfCriminalCase(Date dateOfCriminalCase) {
        this.dateOfCriminalCase = dateOfCriminalCase;
    }

    @XmlTransient
    public Collection<PrisonerCriminalCase> getPrisonerCriminalCaseCollection() {
        return prisonerCriminalCaseCollection;
    }

    public void setPrisonerCriminalCaseCollection(Collection<PrisonerCriminalCase> prisonerCriminalCaseCollection) {
        this.prisonerCriminalCaseCollection = prisonerCriminalCaseCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (criminalCasePK != null ? criminalCasePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CriminalCase)) {
            return false;
        }
        CriminalCase other = (CriminalCase) object;
        if ((this.criminalCasePK == null && other.criminalCasePK != null) || (this.criminalCasePK != null && !this.criminalCasePK.equals(other.criminalCasePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CriminalCase[ criminalCasePK=" + criminalCasePK + " ]";
    }
    
}
