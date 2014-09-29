/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.primaryKeys;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author josuah
 */
@Embeddable
public class PrisonerCriminalCasePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PRISON_FILE_NUMBER")
    private String prisonFileNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CRIMINAL_CASE_NUMBER")
    private String criminalCaseNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "JURISDICTION_NAME")
    private String jurisdictionName;

    public PrisonerCriminalCasePK() {
    }

    public PrisonerCriminalCasePK(String prisonFileNumber, String criminalCaseNumber, String jurisdictionName) {
        this.prisonFileNumber = prisonFileNumber;
        this.criminalCaseNumber = criminalCaseNumber;
        this.jurisdictionName = jurisdictionName;
    }

    public String getPrisonFileNumber() {
        return prisonFileNumber;
    }

    public void setPrisonFileNumber(String prisonFileNumber) {
        this.prisonFileNumber = prisonFileNumber;
    }

    public String getCriminalCaseNumber() {
        return criminalCaseNumber;
    }

    public void setCriminalCaseNumber(String criminalCaseNumber) {
        this.criminalCaseNumber = criminalCaseNumber;
    }

    public String getJurisdictionName() {
        return jurisdictionName;
    }

    public void setJurisdictionName(String jurisdictionName) {
        this.jurisdictionName = jurisdictionName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prisonFileNumber != null ? prisonFileNumber.hashCode() : 0);
        hash += (criminalCaseNumber != null ? criminalCaseNumber.hashCode() : 0);
        hash += (jurisdictionName != null ? jurisdictionName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrisonerCriminalCasePK)) {
            return false;
        }
        PrisonerCriminalCasePK other = (PrisonerCriminalCasePK) object;
        if ((this.prisonFileNumber == null && other.prisonFileNumber != null) || (this.prisonFileNumber != null && !this.prisonFileNumber.equals(other.prisonFileNumber))) {
            return false;
        }
        if ((this.criminalCaseNumber == null && other.criminalCaseNumber != null) || (this.criminalCaseNumber != null && !this.criminalCaseNumber.equals(other.criminalCaseNumber))) {
            return false;
        }
        if ((this.jurisdictionName == null && other.jurisdictionName != null) || (this.jurisdictionName != null && !this.jurisdictionName.equals(other.jurisdictionName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PrisonerCriminalCasePK[ prisonFileNumber=" + prisonFileNumber + ", criminalCaseNumber=" + criminalCaseNumber + ", jurisdictionName=" + jurisdictionName + " ]";
    }
    
}
