/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import entity.primaryKeys.PrisonerCriminalCasePK;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author josuah
 */
@Entity
@Table(name = "PRISONER_CRIMINAL_CASE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrisonerCriminalCase.findAll", query = "SELECT p FROM PrisonerCriminalCase p"),
    @NamedQuery(name = "PrisonerCriminalCase.findByPrisonFileNumber", query = "SELECT p FROM PrisonerCriminalCase p WHERE p.prisonerCriminalCasePK.prisonFileNumber = :prisonFileNumber"),
    @NamedQuery(name = "PrisonerCriminalCase.findByCriminalCaseNumber", query = "SELECT p FROM PrisonerCriminalCase p WHERE p.prisonerCriminalCasePK.criminalCaseNumber = :criminalCaseNumber"),
    @NamedQuery(name = "PrisonerCriminalCase.findByJurisdictionName", query = "SELECT p FROM PrisonerCriminalCase p WHERE p.prisonerCriminalCasePK.jurisdictionName = :jurisdictionName")})
public class PrisonerCriminalCase implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prisonerCriminalCase")
    private Collection<Incarceration> incarcerationCollection;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrisonerCriminalCasePK prisonerCriminalCasePK;
    @JoinColumn(name = "PRISON_FILE_NUMBER", referencedColumnName = "PRISON_FILE_NUMBER", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Prisoner prisoner;
    @JoinColumns({
        @JoinColumn(name = "CRIMINAL_CASE_NUMBER", referencedColumnName = "CRIMINAL_CASE_NUMBER", insertable = false, updatable = false),
        @JoinColumn(name = "JURISDICTION_NAME", referencedColumnName = "JURISDICTION_NAME", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CriminalCase criminalCase;

    public PrisonerCriminalCase() {
    }

    public PrisonerCriminalCase(PrisonerCriminalCasePK prisonerCriminalCasePK) {
        this.prisonerCriminalCasePK = prisonerCriminalCasePK;
    }

    public PrisonerCriminalCase(String prisonFileNumber, String criminalCaseNumber, String jurisdictionName) {
        this.prisonerCriminalCasePK = new PrisonerCriminalCasePK(prisonFileNumber, criminalCaseNumber, jurisdictionName);
    }

    public PrisonerCriminalCasePK getPrisonerCriminalCasePK() {
        return prisonerCriminalCasePK;
    }

    public void setPrisonerCriminalCasePK(PrisonerCriminalCasePK prisonerCriminalCasePK) {
        this.prisonerCriminalCasePK = prisonerCriminalCasePK;
    }

    public Prisoner getPrisoner() {
        return prisoner;
    }

    public void setPrisoner(Prisoner prisoner) {
        this.prisoner = prisoner;
    }

    public CriminalCase getCriminalCase() {
        return criminalCase;
    }

    public void setCriminalCase(CriminalCase criminalCase) {
        this.criminalCase = criminalCase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prisonerCriminalCasePK != null ? prisonerCriminalCasePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrisonerCriminalCase)) {
            return false;
        }
        PrisonerCriminalCase other = (PrisonerCriminalCase) object;
        if ((this.prisonerCriminalCasePK == null && other.prisonerCriminalCasePK != null) || (this.prisonerCriminalCasePK != null && !this.prisonerCriminalCasePK.equals(other.prisonerCriminalCasePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PrisonerCriminalCase[ prisonerCriminalCasePK=" + prisonerCriminalCasePK + " ]";
    }

    @XmlTransient
    public Collection<Incarceration> getIncarcerationCollection() {
        return incarcerationCollection;
    }

    public void setIncarcerationCollection(Collection<Incarceration> incarcerationCollection) {
        this.incarcerationCollection = incarcerationCollection;
    }
    
}
