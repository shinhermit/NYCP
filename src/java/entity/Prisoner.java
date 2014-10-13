/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

/**
 *
 * @author josuah
 */
@Entity
@Table(name = "PRISONER")    
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prisoner.findAll", query = "SELECT p FROM Prisoner p"),
    @NamedQuery(name = "Prisoner.findByPrisonFileNumber", query = "SELECT p FROM Prisoner p WHERE p.prisonFileNumber = :prisonFileNumber"),
    @NamedQuery(name = "Prisoner.findByGivenName", query = "SELECT p FROM Prisoner p WHERE p.givenName = :givenName"),
    @NamedQuery(name = "Prisoner.findBySurname", query = "SELECT p FROM Prisoner p WHERE p.surname = :surname"),
    @NamedQuery(name = "Prisoner.findByDateOfBirth", query = "SELECT p FROM Prisoner p WHERE p.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "Prisoner.findByPlaceOfBirth", query = "SELECT p FROM Prisoner p WHERE p.placeOfBirth = :placeOfBirth")})
public class Prisoner implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PRISON_FILE_NUMBER")
    private String prisonFileNumber;
    @Size(max = 30)
    @Column(name = "GIVEN_NAME")
    private String givenName;
    @Size(max = 30)
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "DATE_OF_BIRTH")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Size(max = 30)
    @Column(name = "PLACE_OF_BIRTH")
    private String placeOfBirth;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PRISONER_CRIMINAL_CASE", 
               joinColumns = {
                   @JoinColumn(name= "PRISON_FILE_NUMBER", referencedColumnName = "PRISON_FILE_NUMBER")
               },
               inverseJoinColumns = {
                   @JoinColumn(name = "CRIMINAL_CASE_NUMBER", referencedColumnName = "CRIMINAL_CASE_NUMBER"),
                   @JoinColumn(name= "JURISDICTION_NAME",     referencedColumnName = "JURISDICTION_NAME"),
               })
    private Set<CriminalCase> criminalCaseSet;
    
    public Prisoner() {
        criminalCaseSet = new HashSet();
    }

    public Prisoner(String prisonFileNumber) {
        criminalCaseSet = new HashSet();
        this.prisonFileNumber = prisonFileNumber;
    }

    public String getPrisonFileNumber() {
        return prisonFileNumber;
    }

    public void setPrisonFileNumber(String prisonFileNumber) {
        this.prisonFileNumber = prisonFileNumber;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    /**
     * @return the criminalCaseSet
     */
    public Set<CriminalCase> getCriminalCaseSet() {
        return criminalCaseSet;
    }

    /**
     * @param criminalCaseSet the criminalCaseSet to set
     */
    public void setCriminalCaseSet(Set<CriminalCase> criminalCaseSet) {
        this.criminalCaseSet = criminalCaseSet;
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
        if (!(object instanceof Prisoner)) {
            return false;
        }
        Prisoner other = (Prisoner) object;
        if ((this.prisonFileNumber == null && other.prisonFileNumber != null) || (this.prisonFileNumber != null && !this.prisonFileNumber.equals(other.prisonFileNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Prisoner[ prisonFileNumber=" + prisonFileNumber + " ]";
    }
}
