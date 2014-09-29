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
public class CriminalCasePK implements Serializable {
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

    public CriminalCasePK() {
    }

    public CriminalCasePK(String criminalCaseNumber, String jurisdictionName) {
        this.criminalCaseNumber = criminalCaseNumber;
        this.jurisdictionName = jurisdictionName;
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
        hash += (criminalCaseNumber != null ? criminalCaseNumber.hashCode() : 0);
        hash += (jurisdictionName != null ? jurisdictionName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CriminalCasePK)) {
            return false;
        }
        CriminalCasePK other = (CriminalCasePK) object;
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
        return "entity.CriminalCasePK[ criminalCaseNumber=" + criminalCaseNumber + ", jurisdictionName=" + jurisdictionName + " ]";
    }
    
}
