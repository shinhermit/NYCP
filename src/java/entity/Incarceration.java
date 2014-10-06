/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author josuah
 */
@Entity
@Table(name = "INCARCERATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Incarceration.findAll", query = "SELECT i FROM Incarceration i"),
    @NamedQuery(name = "Incarceration.findByPrisonFileNumber", query = "SELECT i FROM Incarceration i WHERE i.prisonFileNumber = :prisonFileNumber"),
    @NamedQuery(name = "Incarceration.findByDateOfIncarceration", query = "SELECT i FROM Incarceration i WHERE i.dateOfIncarceration = :dateOfIncarceration")})
public class Incarceration implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PRISON_FILE_NUMBER")
    private String prisonFileNumber;
    @Column(name = "DATE_OF_INCARCERATION")
    @Temporal(TemporalType.DATE)
    private Date dateOfIncarceration;
    @JoinColumns({
        @JoinColumn(name = "PRISON_FILE_NUMBER", referencedColumnName = "PRISON_FILE_NUMBER", insertable = false, updatable = false),
        @JoinColumn(name = "CRIMINAL_CASE_NUMBER", referencedColumnName = "CRIMINAL_CASE_NUMBER"),
        @JoinColumn(name = "JURISDICTION_NAME", referencedColumnName = "JURISDICTION_NAME")})
    @ManyToOne(optional = false)
    private PrisonerCriminalCase prisonerCriminalCase;
    @JoinColumn(name = "MOTIVE_NUMBER", referencedColumnName = "MOTIVE_NUMBER")
    @ManyToOne(optional = false)
    private Motive motiveNumber;

    public Incarceration() {
    }

    public Incarceration(String prisonFileNumber) {
        this.prisonFileNumber = prisonFileNumber;
    }

    public String getPrisonFileNumber() {
        return prisonFileNumber;
    }

    public void setPrisonFileNumber(String prisonFileNumber) {
        this.prisonFileNumber = prisonFileNumber;
    }

    public Date getDateOfIncarceration() {
        return dateOfIncarceration;
    }

    public void setDateOfIncarceration(Date dateOfIncarceration) {
        this.dateOfIncarceration = dateOfIncarceration;
    }

    public PrisonerCriminalCase getPrisonerCriminalCase() {
        return prisonerCriminalCase;
    }

    public void setPrisonerCriminalCase(PrisonerCriminalCase prisonerCriminalCase) {
        this.prisonerCriminalCase = prisonerCriminalCase;
    }

    public Motive getMotiveNumber() {
        return motiveNumber;
    }

    public void setMotiveNumber(Motive motiveNumber) {
        this.motiveNumber = motiveNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prisonFileNumber != null ? prisonFileNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Incarceration)) {
            return false;
        }
        Incarceration other = (Incarceration) object;
        if ((this.prisonFileNumber == null && other.prisonFileNumber != null) || (this.prisonFileNumber != null && !this.prisonFileNumber.equals(other.prisonFileNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Incarceration[ prisonFileNumber=" + prisonFileNumber + " ]";
    }
    
}
